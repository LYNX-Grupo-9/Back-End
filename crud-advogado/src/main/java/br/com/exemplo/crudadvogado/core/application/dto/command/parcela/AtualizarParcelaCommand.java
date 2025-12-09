package br.com.exemplo.crudadvogado.core.application.dto.command.parcela;

import br.com.exemplo.crudadvogado.core.domain.valueObjects.parcela.Status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public record AtualizarParcelaCommand(
        BigDecimal valor,
        Date dataVencimento,
        Status status,
        Long idLancamento
) {}