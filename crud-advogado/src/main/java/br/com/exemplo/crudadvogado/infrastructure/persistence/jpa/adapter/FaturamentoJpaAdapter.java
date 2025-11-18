package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter;

import br.com.exemplo.crudadvogado.core.adapter.gateway.FaturamentoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.faturamento.FaturamentoMensalResponse;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.ParcelaJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Repository
@Transactional
public class FaturamentoJpaAdapter implements FaturamentoGateway {

    private final ParcelaJpaRepository parcelaJpaRepository;

    public FaturamentoJpaAdapter(ParcelaJpaRepository parcelaJpaRepository) {
        this.parcelaJpaRepository = parcelaJpaRepository;
    }

    @Override
    public List<FaturamentoMensalResponse> obterFaturamentoUltimos6Meses() {
        List<FaturamentoMensalResponse> faturamentoMensal = new ArrayList<>();
        LocalDate dataAtual = LocalDate.now();

        for (int i = 5; i >= 0; i--) {
            LocalDate mesReferencia = dataAtual.minusMonths(i);
            String mesAbreviado = obterMesAbreviado(mesReferencia);
            BigDecimal faturamento = calcularFaturamentoMes(mesReferencia);

            faturamentoMensal.add(new FaturamentoMensalResponse(mesAbreviado, faturamento));
        }

        return faturamentoMensal;
    }

    private String obterMesAbreviado(LocalDate data) {
        return data.getMonth()
                .getDisplayName(TextStyle.SHORT, new Locale("pt", "BR"))
                .substring(0, 3)
                .toUpperCase();
    }

    private BigDecimal calcularFaturamentoMes(LocalDate mesReferencia) {
        LocalDate inicioMes = mesReferencia.withDayOfMonth(1);
        LocalDate fimMes = mesReferencia.withDayOfMonth(mesReferencia.lengthOfMonth());

        return parcelaJpaRepository.calcularFaturamentoPorPeriodo(inicioMes, fimMes);
    }
}
