package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ParcelaGateway;
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
}
