package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository;

import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.ParcelaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ParcelaJpaRepository extends JpaRepository<ParcelaEntity, Long> {

    @Query(value = """
        SELECT COALESCE(SUM(p.valor), 0)
        FROM parcela p
        WHERE p.status = 'PENDENTE'
          AND MONTH(p.data_vencimento) = MONTH(CURRENT_DATE)
          AND YEAR(p.data_vencimento) = YEAR(CURRENT_DATE)
        """, nativeQuery = true)
    BigDecimal calcularTotalPendenteMesAtual();

    @Query(value = """
        SELECT COALESCE(SUM(p.valor), 0)
        FROM parcela p
        WHERE p.status = 'PENDENTE'
          AND MONTH(p.data_vencimento) = MONTH(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH))
          AND YEAR(p.data_vencimento) = YEAR(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH))
        """, nativeQuery = true)
    BigDecimal calcularTotalPendenteMesAnterior();

    @Query(value = """

            SELECT COUNT(DISTINCT pr.id_processo)
        FROM parcela p
        JOIN lancamento l ON l.id_lancamento = p.id_lancamento
        JOIN processo pr ON pr.id_processo = l.id_processo
        WHERE p.status = 'ATRASO'
          AND MONTH(p.data_vencimento) = MONTH(CURRENT_DATE)
          AND YEAR(p.data_vencimento) = YEAR(CURRENT_DATE);
        
        """, nativeQuery = true)
    Long contarClientesComPendenciasAtrasadasMesAtual();

    @Query(value = """
        SELECT COUNT(DISTINCT p.processo_id)
        FROM parcela p
        WHERE p.status = 'ATRASADO'
          AND MONTH(p.data_vencimento) = MONTH(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH))
          AND YEAR(p.data_vencimento) = YEAR(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH))
        """, nativeQuery = true)
    Long contarClientesComPendenciasAtrasadasMesAnterior();

    @Query(value = """
        SELECT COUNT(DISTINCT p.processo_id)
        FROM parcela p
        WHERE p.status = 'ATRASO'
          AND MONTH(p.data_vencimento) = MONTH(CURRENT_DATE)
          AND YEAR(p.data_vencimento) = YEAR(CURRENT_DATE)
        """, nativeQuery = true)
    Long contarProcessosComPendenciasAtrasadasMesAtual();

    @Query(value = """
        SELECT COUNT(DISTINCT p.processo_id)
        FROM parcela p
        WHERE p.status = 'ATRASO'
          AND MONTH(p.data_vencimento) = MONTH(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH))
          AND YEAR(p.data_vencimento) = YEAR(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH))
        """, nativeQuery = true)
    Long contarProcessosComPendenciasAtrasadasMesAnterior();

    @Query(value = """
        SELECT COALESCE(SUM(p.valor), 0)
        FROM parcela p
        WHERE p.status = 'PENDENTE'
          AND p.data_vencimento BETWEEN CURRENT_DATE AND DATE_ADD(CURRENT_DATE, INTERVAL 30 DAY)
        """, nativeQuery = true)
    BigDecimal calcularTotalAReceberProximos30Dias();

    @Query(value = """
        SELECT COALESCE(SUM(p.valor), 0)
        FROM parcela p
        WHERE p.status = 'PENDENTE'
          AND p.data_vencimento BETWEEN DATE_SUB(CURRENT_DATE, INTERVAL 30 DAY) AND CURRENT_DATE
        """, nativeQuery = true)
    BigDecimal calcularTotalAReceberUltimos30Dias();

    // CORREÇÃO: Removido data_pagamento que não existe - usando data_vencimento
    @Query(value = """
        SELECT COALESCE(SUM(p.valor), 0)
        FROM parcela p
        WHERE p.status = 'PAGO'
          AND p.data_vencimento BETWEEN :inicio AND :fim
        """, nativeQuery = true)
    BigDecimal calcularFaturamentoPorPeriodo(@Param("inicio") LocalDate inicio,
                                             @Param("fim") LocalDate fim);

    @Query(value = """
        SELECT COALESCE(SUM(p.valor), 0)
        FROM parcela p
        WHERE p.status = 'PAGO'
          AND p.data_vencimento BETWEEN :inicioMes AND :dataAtual
        """, nativeQuery = true)
    BigDecimal calcularTotalFaturadoMesAtual(@Param("inicioMes") LocalDate inicioMes,
                                             @Param("dataAtual") LocalDate dataAtual);

    @Query(value = """
        SELECT COALESCE(SUM(p.valor), 0)
        FROM parcela p
        WHERE p.status = 'PAGO'
          AND p.data_vencimento BETWEEN :inicioMesAnterior AND :fimMesAnterior
        """, nativeQuery = true)
    BigDecimal calcularTotalFaturadoMesAnterior(@Param("inicioMesAnterior") LocalDate inicioMesAnterior,
                                                @Param("fimMesAnterior") LocalDate fimMesAnterior);

    @Query(value = """
        SELECT p.id_parcela, p.valor, p.data_vencimento, p.status,
               c.id_cliente, c.nome as nome_cliente,
               l.id_lancamento,
               (SELECT COUNT(p2.id_parcela) FROM parcela p2 WHERE p2.id_lancamento = p.id_lancamento) as total_parcelas,
               (SELECT COUNT(p3.id_parcela) FROM parcela p3 WHERE p3.id_lancamento = p.id_lancamento AND p3.data_vencimento <= p.data_vencimento) as parcela_atual
        FROM parcela p
        INNER JOIN lancamento l ON p.id_lancamento = l.id_lancamento
        INNER JOIN cliente c ON l.id_cliente = c.id_cliente
        WHERE p.status IN ('PENDENTE', 'ATRASO')
        ORDER BY p.data_vencimento ASC
        LIMIT 1
        """, nativeQuery = true)
    Optional<Object[]> obterProximaParcela();

    @Query(value = """
    SELECT 
        CAST(COALESCE(SUM(CASE 
            WHEN p.data_vencimento BETWEEN DATE_SUB(CURRENT_DATE, INTERVAL 30 DAY) AND CURRENT_DATE
            THEN p.valor ELSE 0 
        END), 0) AS DECIMAL(38,2)) as total30,
        CAST(COALESCE(SUM(CASE 
            WHEN p.data_vencimento BETWEEN DATE_SUB(CURRENT_DATE, INTERVAL 60 DAY) AND DATE_SUB(CURRENT_DATE, INTERVAL 31 DAY)
            THEN p.valor ELSE 0 
        END), 0) AS DECIMAL(38,2)) as total60,
        CAST(COALESCE(SUM(CASE 
            WHEN p.data_vencimento BETWEEN DATE_SUB(CURRENT_DATE, INTERVAL 90 DAY) AND DATE_SUB(CURRENT_DATE, INTERVAL 61 DAY)
            THEN p.valor ELSE 0 
        END), 0) AS DECIMAL(38,2)) as total90,
        CAST(COALESCE(SUM(CASE 
            WHEN p.data_vencimento BETWEEN DATE_SUB(CURRENT_DATE, INTERVAL 120 DAY) AND DATE_SUB(CURRENT_DATE, INTERVAL 91 DAY)
            THEN p.valor ELSE 0 
        END), 0) AS DECIMAL(38,2)) as total120,
        CAST(COALESCE(SUM(CASE 
            WHEN p.data_vencimento BETWEEN DATE_SUB(CURRENT_DATE, INTERVAL 150 DAY) AND DATE_SUB(CURRENT_DATE, INTERVAL 121 DAY)
            THEN p.valor ELSE 0 
        END), 0) AS DECIMAL(38,2)) as total150,
        CAST(COALESCE(SUM(CASE 
            WHEN p.data_vencimento BETWEEN DATE_SUB(CURRENT_DATE, INTERVAL 180 DAY) AND DATE_SUB(CURRENT_DATE, INTERVAL 151 DAY)
            THEN p.valor ELSE 0 
        END), 0) AS DECIMAL(38,2)) as total180
    FROM parcela p
    WHERE p.status = 'PAGO'
""", nativeQuery = true)
    List<Object[]> calcularFaturadoUltimos6Meses();

    @Query(value = """
    SELECT 
        CAST(COALESCE(SUM(CASE 
            WHEN p.data_vencimento BETWEEN DATE_SUB(CURRENT_DATE, INTERVAL 30 DAY) AND CURRENT_DATE
            THEN p.valor ELSE 0 
        END), 0) AS DECIMAL(38,2)) as total30,
        CAST(COALESCE(SUM(CASE 
            WHEN p.data_vencimento BETWEEN DATE_SUB(CURRENT_DATE, INTERVAL 60 DAY) AND DATE_SUB(CURRENT_DATE, INTERVAL 31 DAY)
            THEN p.valor ELSE 0 
        END), 0) AS DECIMAL(38,2)) as total60,
        CAST(COALESCE(SUM(CASE 
            WHEN p.data_vencimento BETWEEN DATE_SUB(CURRENT_DATE, INTERVAL 90 DAY) AND DATE_SUB(CURRENT_DATE, INTERVAL 61 DAY)
            THEN p.valor ELSE 0 
        END), 0) AS DECIMAL(38,2)) as total90,
        CAST(COALESCE(SUM(CASE 
            WHEN p.data_vencimento BETWEEN DATE_SUB(CURRENT_DATE, INTERVAL 120 DAY) AND DATE_SUB(CURRENT_DATE, INTERVAL 91 DAY)
            THEN p.valor ELSE 0 
        END), 0) AS DECIMAL(38,2)) as total120,
        CAST(COALESCE(SUM(CASE 
            WHEN p.data_vencimento BETWEEN DATE_SUB(CURRENT_DATE, INTERVAL 150 DAY) AND DATE_SUB(CURRENT_DATE, INTERVAL 121 DAY)
            THEN p.valor ELSE 0 
        END), 0) AS DECIMAL(38,2)) as total150,
        CAST(COALESCE(SUM(CASE 
            WHEN p.data_vencimento BETWEEN DATE_SUB(CURRENT_DATE, INTERVAL 180 DAY) AND DATE_SUB(CURRENT_DATE, INTERVAL 151 DAY)
            THEN p.valor ELSE 0 
        END), 0) AS DECIMAL(38,2)) as total180
    FROM parcela p
    WHERE p.status = 'PENDENTE'
""", nativeQuery = true)
    List<Object[]> calcularPendenteUltimos6Meses();
}