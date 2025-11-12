package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository;

import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.ParcelaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
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

    // CORREÇÃO: Removido p.cliente.id - substitua pelo campo correto quando souber a estrutura
    @Query(value = """
        SELECT COUNT(DISTINCT p.processo_id)
        FROM parcela p
        WHERE p.status = 'ATRASADO'
          AND MONTH(p.data_vencimento) = MONTH(CURRENT_DATE)
          AND YEAR(p.data_vencimento) = YEAR(CURRENT_DATE)
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
        WHERE p.status = 'ATRASADO'
          AND MONTH(p.data_vencimento) = MONTH(CURRENT_DATE)
          AND YEAR(p.data_vencimento) = YEAR(CURRENT_DATE)
        """, nativeQuery = true)
    Long contarProcessosComPendenciasAtrasadasMesAtual();

    @Query(value = """
        SELECT COUNT(DISTINCT p.processo_id)
        FROM parcela p
        WHERE p.status = 'ATRASADO'
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

}