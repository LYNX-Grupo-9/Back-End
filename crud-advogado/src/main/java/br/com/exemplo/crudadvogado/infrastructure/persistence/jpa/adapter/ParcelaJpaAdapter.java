package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ParcelaGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.financeiro.FaturadoUltimos6MesesResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.financeiro.PendenteUltimos6MesesResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.financeiro.ProximoPagamentoResponse;
import br.com.exemplo.crudadvogado.core.domain.Parcela;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.ParcelaEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper.ParcelaMapper;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.ParcelaJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class ParcelaJpaAdapter implements ParcelaGateway {

    private final ParcelaJpaRepository parcelaRepository;
    private final ParcelaMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(ParcelaJpaAdapter.class);

    public ParcelaJpaAdapter(ParcelaJpaRepository parcelaRepository, ParcelaMapper mapper) {
        this.parcelaRepository = parcelaRepository;
        this.mapper = mapper;
    }

    @Override
    public Parcela criar(Parcela domain) {
        try {
            ParcelaEntity entity = mapper.toEntity(domain);
            ParcelaEntity parcelaSalvo = parcelaRepository.save(entity);
            return mapper.toDomain(parcelaSalvo);
        } catch (Exception e) {
            logger.error("Erro ao criar parcela", e);
            throw new RuntimeException("Erro ao criar parcela", e);
        }
    }

    @Override
    public BigDecimal calcularTotalPendenteMesAtual() {
        try {
            return parcelaRepository.calcularTotalPendenteMesAtual();
        } catch (Exception e) {
            logger.error("Erro ao calcular total pendente mês atual", e);
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal calcularTotalPendenteMesAnterior() {
        try {
            return parcelaRepository.calcularTotalPendenteMesAnterior();
        } catch (Exception e) {
            logger.error("Erro ao calcular total pendente mês anterior", e);
            return BigDecimal.ZERO;
        }
    }

    @Override
    public Long contarClientesComPendenciasAtrasadasMesAtual() {
        try {
            return parcelaRepository.contarClientesComPendenciasAtrasadasMesAtual();
        } catch (Exception e) {
            logger.error("Erro ao contar clientes com pendências atrasadas mês atual", e);
            return 0L;
        }
    }

    @Override
    public Long contarClientesComPendenciasAtrasadasMesAnterior() {
        try {
            return parcelaRepository.contarClientesComPendenciasAtrasadasMesAnterior();
        } catch (Exception e) {
            logger.error("Erro ao contar clientes com pendências atrasadas mês anterior", e);
            return 0L;
        }
    }

    @Override
    public Long contarProcessosComPendenciasAtrasadasMesAtual() {
        try {
            return parcelaRepository.contarProcessosComPendenciasAtrasadasMesAtual();
        } catch (Exception e) {
            logger.error("Erro ao contar processos com pendências atrasadas mês atual", e);
            return 0L;
        }
    }

    @Override
    public Long contarProcessosComPendenciasAtrasadasMesAnterior() {
        try {
            return parcelaRepository.contarProcessosComPendenciasAtrasadasMesAnterior();
        } catch (Exception e) {
            logger.error("Erro ao contar processos com pendências atrasadas mês anterior", e);
            return 0L;
        }
    }

    @Override
    public BigDecimal calcularTotalAReceberProximos30Dias() {
        try {
            return parcelaRepository.calcularTotalAReceberProximos30Dias();
        } catch (Exception e) {
            logger.error("Erro ao calcular total a receber próximos 30 dias", e);
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal calcularTotalAReceberUltimos30Dias() {
        try {
            return parcelaRepository.calcularTotalAReceberUltimos30Dias();
        } catch (Exception e) {
            logger.error("Erro ao calcular total a receber últimos 30 dias", e);
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal calcularTotalFaturadoMesAtual() {
        try {
            LocalDate hoje = LocalDate.now();
            LocalDate inicioMes = hoje.withDayOfMonth(1);
            return parcelaRepository.calcularTotalFaturadoMesAtual(inicioMes, hoje);
        } catch (Exception e) {
            logger.error("Erro ao calcular total faturado mês atual", e);
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal calcularTotalFaturadoMesAnterior() {
        try {
            LocalDate hoje = LocalDate.now();
            LocalDate mesAnterior = hoje.minusMonths(1);
            LocalDate inicioMesAnterior = mesAnterior.withDayOfMonth(1);
            LocalDate fimMesAnterior = mesAnterior.withDayOfMonth(mesAnterior.lengthOfMonth());
            return parcelaRepository.calcularTotalFaturadoMesAnterior(inicioMesAnterior, fimMesAnterior);
        } catch (Exception e) {
            logger.error("Erro ao calcular total faturado mês anterior", e);
            return BigDecimal.ZERO;
        }
    }

    @Override
    public Optional<ProximoPagamentoResponse> obterProximoPagamento() {
        try {
            return parcelaRepository.obterProximaParcela()
                    .map(this::mapearParaProximoPagamentoResponse)
                    .filter(response -> response != null);
        } catch (Exception e) {
            logger.error("Erro ao obter próximo pagamento", e);
            return Optional.empty();
        }
    }

    private ProximoPagamentoResponse mapearParaProximoPagamentoResponse(Object[] resultado) {
        try {
            if (resultado == null || resultado.length < 9) {
                logger.warn("Resultado inválido para próximo pagamento: {}", Arrays.toString(resultado));
                return null;
            }

            Long idParcela = converterParaLong(resultado[0]);
            BigDecimal valor = converterParaBigDecimal(resultado[1]);
            LocalDate vencimento = converterParaLocalDate(resultado[2]);
            String status = converterParaString(resultado[3]);
            Long idCliente = converterParaLong(resultado[4]);
            String nomeCliente = converterParaString(resultado[5]);
            Long idLancamento = converterParaLong(resultado[6]);
            Integer numParcelas = converterParaInteger(resultado[7]);
            Integer parcelaAtual = converterParaInteger(resultado[8]);

            if (valor == null || vencimento == null || nomeCliente == null) {
                logger.warn("Dados inválidos no próximo pagamento: valor={}, vencimento={}, nomeCliente={}",
                        valor, vencimento, nomeCliente);
                return null;
            }

            return new ProximoPagamentoResponse(
                    idCliente,
                    nomeCliente,
                    status,
                    vencimento,
                    numParcelas,
                    parcelaAtual,
                    valor
            );
        } catch (Exception e) {
            logger.error("Erro ao mapear próximo pagamento: {}", Arrays.toString(resultado), e);
            return null;
        }
    }

    @Override
    public FaturadoUltimos6MesesResponse calcularFaturadoUltimos6Meses() {
        try {
            // REPOSITORY RETORNA List<Object[]>
            List<Object[]> lista = parcelaRepository.calcularFaturadoUltimos6Meses();

            if (lista == null || lista.isEmpty()) {
                logger.debug("Lista faturado vazia - retornando zeros");
                return new FaturadoUltimos6MesesResponse(
                        BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                        BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO
                );
            }

            // Log linha a linha (cada linha é um Object[])
            lista.forEach(l -> logger.debug("FATURADO linha: {}", Arrays.toString(l)));

            // usamos apenas a primeira linha (é o formato da query agregada)
            Object[] resultado = lista.get(0);

            return mapearParaFaturadoResponse(resultado);
        } catch (Exception e) {
            logger.error("Erro ao calcular faturado últimos 6 meses", e);
            return new FaturadoUltimos6MesesResponse(
                    BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                    BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO
            );
        }
    }

    @Override
    public PendenteUltimos6MesesResponse calcularPendenteUltimos6Meses() {
        try {
            // REPOSITORY RETORNA List<Object[]>
            List<Object[]> lista = parcelaRepository.calcularPendenteUltimos6Meses();

            if (lista == null || lista.isEmpty()) {
                logger.debug("Lista pendente vazia - retornando zeros");
                return new PendenteUltimos6MesesResponse(
                        BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                        BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO
                );
            }

            // Log linha a linha
            lista.forEach(l -> logger.debug("PENDENTE linha: {}", Arrays.toString(l)));

            Object[] resultado = lista.get(0);

            return mapearParaPendenteResponse(resultado);
        } catch (Exception e) {
            logger.error("Erro ao calcular pendente últimos 6 meses", e);
            return new PendenteUltimos6MesesResponse(
                    BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                    BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO
            );
        }
    }

    private FaturadoUltimos6MesesResponse mapearParaFaturadoResponse(Object[] resultado) {
        if (resultado == null || resultado.length != 6) {
            logger.warn("Resultado inválido para faturado: {}", Arrays.toString(resultado));
            return new FaturadoUltimos6MesesResponse(
                    BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                    BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO
            );
        }

        try {
            BigDecimal total30 = converterParaBigDecimal(resultado[0]);
            BigDecimal total60 = converterParaBigDecimal(resultado[1]);
            BigDecimal total90 = converterParaBigDecimal(resultado[2]);
            BigDecimal total120 = converterParaBigDecimal(resultado[3]);
            BigDecimal total150 = converterParaBigDecimal(resultado[4]);
            BigDecimal total180 = converterParaBigDecimal(resultado[5]);

            logger.debug("Valores faturados mapeados: 30d={}, 60d={}, 90d={}, 120d={}, 150d={}, 180d={}",
                    total30, total60, total90, total120, total150, total180);

            return new FaturadoUltimos6MesesResponse(total30, total60, total90, total120, total150, total180);
        } catch (Exception e) {
            logger.error("Erro ao mapear resposta faturado: {}", Arrays.toString(resultado), e);
            return new FaturadoUltimos6MesesResponse(
                    BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                    BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO
            );
        }
    }

    private PendenteUltimos6MesesResponse mapearParaPendenteResponse(Object[] resultado) {
        if (resultado == null || resultado.length != 6) {
            logger.warn("Resultado inválido para pendente: {}", Arrays.toString(resultado));
            return new PendenteUltimos6MesesResponse(
                    BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                    BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO
            );
        }

        try {
            BigDecimal total30 = converterParaBigDecimal(resultado[0]);
            BigDecimal total60 = converterParaBigDecimal(resultado[1]);
            BigDecimal total90 = converterParaBigDecimal(resultado[2]);
            BigDecimal total120 = converterParaBigDecimal(resultado[3]);
            BigDecimal total150 = converterParaBigDecimal(resultado[4]);
            BigDecimal total180 = converterParaBigDecimal(resultado[5]);

            logger.debug("Valores pendentes mapeados: 30d={}, 60d={}, 90d={}, 120d={}, 150d={}, 180d={}",
                    total30, total60, total90, total120, total150, total180);

            return new PendenteUltimos6MesesResponse(total30, total60, total90, total120, total150, total180);
        } catch (Exception e) {
            logger.error("Erro ao mapear resposta pendente: {}", Arrays.toString(resultado), e);
            return new PendenteUltimos6MesesResponse(
                    BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                    BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO
            );
        }
    }

    private BigDecimal converterParaBigDecimal(Object valor) {
        if (valor == null) {
            logger.debug("Valor é nulo, retornando ZERO");
            return BigDecimal.ZERO;
        }

        try {
            logger.debug("Convertendo valor: {} (tipo: {})", valor, valor.getClass().getSimpleName());

            if (valor instanceof BigDecimal) {
                return (BigDecimal) valor;
            } else if (valor instanceof Double) {
                return BigDecimal.valueOf((Double) valor);
            } else if (valor instanceof Float) {
                return BigDecimal.valueOf((Float) valor);
            } else if (valor instanceof Long) {
                return BigDecimal.valueOf((Long) valor);
            } else if (valor instanceof Integer) {
                return BigDecimal.valueOf((Integer) valor);
            } else if (valor instanceof Number) {
                return BigDecimal.valueOf(((Number) valor).doubleValue());
            } else if (valor instanceof String) {
                String strValor = ((String) valor).trim();
                if (strValor.isEmpty() || "null".equalsIgnoreCase(strValor)) {
                    return BigDecimal.ZERO;
                }
                return new BigDecimal(strValor);
            } else {
                String strValor = valor.toString().trim();
                if (strValor.isEmpty() || "null".equalsIgnoreCase(strValor)) {
                    return BigDecimal.ZERO;
                }
                return new BigDecimal(strValor);
            }
        } catch (NumberFormatException e) {
            logger.warn("Não foi possível converter valor para BigDecimal: '{}' (tipo: {})",
                    valor, valor.getClass().getSimpleName(), e);
            return BigDecimal.ZERO;
        } catch (Exception e) {
            logger.warn("Erro inesperado ao converter valor '{}' (tipo: {})",
                    valor, valor.getClass().getSimpleName(), e);
            return BigDecimal.ZERO;
        }
    }

    private Long converterParaLong(Object valor) {
        if (valor == null) {
            return null;
        }

        try {
            if (valor instanceof Number) {
                return ((Number) valor).longValue();
            } else if (valor instanceof String) {
                String strValor = ((String) valor).trim();
                if (strValor.isEmpty() || "null".equalsIgnoreCase(strValor)) {
                    return null;
                }
                return Long.parseLong(strValor);
            } else {
                return Long.parseLong(valor.toString());
            }
        } catch (NumberFormatException e) {
            logger.warn("Não foi possível converter para Long: '{}'", valor);
            return null;
        }
    }

    private Integer converterParaInteger(Object valor) {
        if (valor == null) {
            return null;
        }

        try {
            if (valor instanceof Number) {
                return ((Number) valor).intValue();
            } else if (valor instanceof String) {
                String strValor = ((String) valor).trim();
                if (strValor.isEmpty() || "null".equalsIgnoreCase(strValor)) {
                    return null;
                }
                return Integer.parseInt(strValor);
            } else {
                return Integer.parseInt(valor.toString());
            }
        } catch (NumberFormatException e) {
            logger.warn("Não foi possível converter para Integer: '{}'", valor);
            return null;
        }
    }

    private String converterParaString(Object valor) {
        if (valor == null) {
            return null;
        }
        return valor.toString().trim();
    }

    private LocalDate converterParaLocalDate(Object valor) {
        if (valor == null) {
            return null;
        }

        try {
            if (valor instanceof java.sql.Date) {
                return ((java.sql.Date) valor).toLocalDate();
            } else if (valor instanceof java.util.Date) {
                return new java.sql.Timestamp(((java.util.Date) valor).getTime()).toLocalDateTime().toLocalDate();
            } else if (valor instanceof LocalDate) {
                return (LocalDate) valor;
            } else if (valor instanceof String) {
                return LocalDate.parse((String) valor);
            } else {
                logger.warn("Tipo não suportado para LocalDate: {}", valor.getClass().getSimpleName());
                return null;
            }
        } catch (Exception e) {
            logger.warn("Erro ao converter para LocalDate: '{}'", valor, e);
            return null;
        }
    }
}
