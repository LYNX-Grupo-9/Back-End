package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ParcelaGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.financeiro.FaturadoUltimos6MesesResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.financeiro.PendenteUltimos6MesesResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.financeiro.ProximoPagamentoResponse;
import br.com.exemplo.crudadvogado.core.domain.Parcela;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.ParcelaEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper.ParcelaMapper;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.ParcelaJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Optional;

@Repository
@Transactional
public class ParcelaJpaAdapter implements ParcelaGateway {

    private final ParcelaJpaRepository parcelaRepository;
    private final ParcelaMapper mapper;

    public ParcelaJpaAdapter(ParcelaJpaRepository parcelaRepository, ParcelaMapper mapper) {
        this.parcelaRepository = parcelaRepository;
        this.mapper = mapper;
    }

    @Override
    public Parcela criar(Parcela domain) {
        ParcelaEntity entity = mapper.toEntity(domain);
        ParcelaEntity parcelaSalvo = parcelaRepository.save(entity);
        return mapper.toDomain(parcelaSalvo);
    }

    @Override
    public BigDecimal calcularTotalPendenteMesAtual() {
        return parcelaRepository.calcularTotalPendenteMesAtual();
    }

    @Override
    public BigDecimal calcularTotalPendenteMesAnterior() {
        return parcelaRepository.calcularTotalPendenteMesAnterior();
    }

    @Override
    public Long contarClientesComPendenciasAtrasadasMesAtual() {
        return parcelaRepository.contarClientesComPendenciasAtrasadasMesAtual();
    }

    @Override
    public Long contarClientesComPendenciasAtrasadasMesAnterior() {
        return parcelaRepository.contarClientesComPendenciasAtrasadasMesAnterior();
    }

    @Override
    public Long contarProcessosComPendenciasAtrasadasMesAtual() {
        return parcelaRepository.contarProcessosComPendenciasAtrasadasMesAtual();
    }

    @Override
    public Long contarProcessosComPendenciasAtrasadasMesAnterior() {
        return parcelaRepository.contarProcessosComPendenciasAtrasadasMesAnterior();
    }

    @Override
    public BigDecimal calcularTotalAReceberProximos30Dias() {
        return parcelaRepository.calcularTotalAReceberProximos30Dias();
    }

    @Override
    public BigDecimal calcularTotalAReceberUltimos30Dias() {
        return parcelaRepository.calcularTotalAReceberUltimos30Dias();
    }


    @Override
    public BigDecimal calcularTotalFaturadoMesAtual() {
        LocalDate hoje = LocalDate.now();
        LocalDate inicioMes = hoje.withDayOfMonth(1);
        return parcelaRepository.calcularTotalFaturadoMesAtual(inicioMes, hoje);
    }

    @Override
    public BigDecimal calcularTotalFaturadoMesAnterior() {
        LocalDate hoje = LocalDate.now();
        LocalDate mesAnterior = hoje.minusMonths(1);
        LocalDate inicioMesAnterior = mesAnterior.withDayOfMonth(1);
        LocalDate fimMesAnterior = mesAnterior.withDayOfMonth(mesAnterior.lengthOfMonth());
        return parcelaRepository.calcularTotalFaturadoMesAnterior(inicioMesAnterior, fimMesAnterior);
    }

    @Override
    public Optional<ProximoPagamentoResponse> obterProximoPagamento() {
        return parcelaRepository.obterProximaParcela()
                .map(this::mapearParaProximoPagamentoResponse);
    }

    private ProximoPagamentoResponse mapearParaProximoPagamentoResponse(Object[] resultado) {
        Long idParcela = ((Number) resultado[0]).longValue();
        BigDecimal valor = (BigDecimal) resultado[1];
        LocalDate vencimento = ((java.sql.Date) resultado[2]).toLocalDate();
        String status = (String) resultado[3];
        Long idCliente = ((Number) resultado[4]).longValue();
        String nomeCliente = (String) resultado[5];
        Long idLancamento = ((Number) resultado[6]).longValue();
        Integer numParcelas = ((Number) resultado[7]).intValue();
        Integer parcelaAtual = ((Number) resultado[8]).intValue();

        return new ProximoPagamentoResponse(
                idCliente,
                nomeCliente,
                status,
                vencimento,
                numParcelas,
                parcelaAtual,
                valor
        );
    }


    @Override
    public FaturadoUltimos6MesesResponse calcularFaturadoUltimos6Meses() {
        Object[] resultado = parcelaRepository.calcularFaturadoUltimos6Meses();
        return mapearParaFaturadoResponse(resultado);
    }

    @Override
    public PendenteUltimos6MesesResponse calcularPendenteUltimos6Meses() {
        Object[] resultado = parcelaRepository.calcularPendenteUltimos6Meses();
        return mapearParaPendenteResponse(resultado);
    }

    private FaturadoUltimos6MesesResponse mapearParaFaturadoResponse(Object[] resultado) {
        BigDecimal total30 = ((Number) resultado[0]).longValue() == 0 ?
                BigDecimal.ZERO : new BigDecimal(resultado[0].toString());
        BigDecimal total60 = ((Number) resultado[1]).longValue() == 0 ?
                BigDecimal.ZERO : new BigDecimal(resultado[1].toString());
        BigDecimal total90 = ((Number) resultado[2]).longValue() == 0 ?
                BigDecimal.ZERO : new BigDecimal(resultado[2].toString());
        BigDecimal total120 = ((Number) resultado[3]).longValue() == 0 ?
                BigDecimal.ZERO : new BigDecimal(resultado[3].toString());
        BigDecimal total150 = ((Number) resultado[4]).longValue() == 0 ?
                BigDecimal.ZERO : new BigDecimal(resultado[4].toString());
        BigDecimal total180 = ((Number) resultado[5]).longValue() == 0 ?
                BigDecimal.ZERO : new BigDecimal(resultado[5].toString());

        return new FaturadoUltimos6MesesResponse(total30, total60, total90, total120, total150, total180);
    }

    private PendenteUltimos6MesesResponse mapearParaPendenteResponse(Object[] resultado) {
        BigDecimal total30 = ((Number) resultado[0]).longValue() == 0 ?
                BigDecimal.ZERO : new BigDecimal(resultado[0].toString());
        BigDecimal total60 = ((Number) resultado[1]).longValue() == 0 ?
                BigDecimal.ZERO : new BigDecimal(resultado[1].toString());
        BigDecimal total90 = ((Number) resultado[2]).longValue() == 0 ?
                BigDecimal.ZERO : new BigDecimal(resultado[2].toString());
        BigDecimal total120 = ((Number) resultado[3]).longValue() == 0 ?
                BigDecimal.ZERO : new BigDecimal(resultado[3].toString());
        BigDecimal total150 = ((Number) resultado[4]).longValue() == 0 ?
                BigDecimal.ZERO : new BigDecimal(resultado[4].toString());
        BigDecimal total180 = ((Number) resultado[5]).longValue() == 0 ?
                BigDecimal.ZERO : new BigDecimal(resultado[5].toString());

        return new PendenteUltimos6MesesResponse(total30, total60, total90, total120, total150, total180);
    }
}
