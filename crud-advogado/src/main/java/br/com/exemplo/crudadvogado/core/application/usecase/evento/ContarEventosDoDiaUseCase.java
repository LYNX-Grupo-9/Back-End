package br.com.exemplo.crudadvogado.core.application.usecase.evento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.EventoGateway;
import br.com.exemplo.crudadvogado.core.domain.Evento;

import java.util.*;

public class ContarEventosDoDiaUseCase {

    private final EventoGateway eventoGateway;

    public ContarEventosDoDiaUseCase(EventoGateway eventoGateway) {
        this.eventoGateway = eventoGateway;
    }

    public Map<String, Long> executar(UUID idAdvogado) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date inicioDia = calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date fimDia = calendar.getTime();

        List<Evento> eventos = eventoGateway.findByAdvogadoIdAdvogadoAndDataReuniaoBetween(
                idAdvogado, inicioDia, fimDia
        );

        Map<String, Long> resultado = new HashMap<>();
        resultado.put("quantidadeEvento", (long) eventos.size());

        return resultado;
    }
}
