package br.com.exemplo.crudadvogado.core.application.usecase.processo;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.processo.ProcessoResponse;
import br.com.exemplo.crudadvogado.core.domain.Processo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ListarProcessosPaginadoUseCase {
    private final ProcessoGateway processoGateway;


    public ListarProcessosPaginadoUseCase(ProcessoGateway processoGateway) {
        this.processoGateway = processoGateway;
    }

    @Cacheable(
            value = "processosPaginados",
            key = "T(java.util.Objects).hash(#pageable.pageNumber, #pageable.pageSize, #pageable.sort.toString())"
    )
    public Page<ProcessoResponse> executar(Pageable pageable) {
        System.out.println("📄 CONSULTANDO BANCO - Processos paginados - Página: " +
                pageable.getPageNumber() + ", Tamanho: " + pageable.getPageSize() +
                ", Ordenação: " + pageable.getSort());

        Page<Processo> processos = processoGateway.listarPaginado(pageable);
        return processos.map(ProcessoResponse::new);
    }
}
