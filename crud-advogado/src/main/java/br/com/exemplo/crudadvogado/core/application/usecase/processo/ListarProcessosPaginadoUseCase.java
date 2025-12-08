package br.com.exemplo.crudadvogado.core.application.usecase.processo;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.processo.PageCacheDTO;
import br.com.exemplo.crudadvogado.core.application.dto.response.processo.ProcessoResponse;
import br.com.exemplo.crudadvogado.core.domain.Processo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Object executar(Pageable pageable) {

        int safeSize = pageable.getPageSize() < 1 ? 1 : pageable.getPageSize();
        int safePage = pageable.getPageNumber() < 0 ? 0 : pageable.getPageNumber();

        Pageable safePageable = PageRequest.of(safePage, safeSize, pageable.getSort());

        Page<Processo> processos = processoGateway.listarPaginado(safePageable);
        Page<ProcessoResponse> page = processos.map(ProcessoResponse::new);

        return new PageCacheDTO<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast(),
                page.getNumberOfElements(),
                page.isEmpty()
        );
    }
}
