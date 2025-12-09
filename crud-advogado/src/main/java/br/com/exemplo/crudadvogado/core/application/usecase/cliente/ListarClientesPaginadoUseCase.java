package br.com.exemplo.crudadvogado.core.application.usecase.cliente;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ClienteGateway;
import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.cliente.ClienteResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.cliente.PageCacheDTO;
import br.com.exemplo.crudadvogado.core.domain.Cliente;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class ListarClientesPaginadoUseCase {

    private final ClienteGateway clienteGateway;
    private final ProcessoGateway processoGateway;

    public ListarClientesPaginadoUseCase(ClienteGateway clienteGateway, ProcessoGateway processoGateway) {
        this.clienteGateway = clienteGateway;
        this.processoGateway = processoGateway;
    }

    @Cacheable(
            value = "clientesPaginados",
            key = "T(java.util.Objects).hash(#pageable.pageNumber, #pageable.pageSize, #pageable.sort.toString())"
    )
    public Object executar(Pageable pageable) {

        int safeSize = pageable.getPageSize() < 1 ? 1 : pageable.getPageSize();
        int safePage = pageable.getPageNumber() < 0 ? 0 : pageable.getPageNumber();

        Pageable safePageable = PageRequest.of(safePage, safeSize, pageable.getSort());

        Page<Cliente> clientes = clienteGateway.listarPaginado(safePageable);

        Page<ClienteResponse> page = clientes.map(cliente -> {
            Long processos = processoGateway.contarProcessosPorCliente(cliente.getIdCliente());
            return new ClienteResponse(
                    cliente.getIdCliente(),
                    cliente.getNome(),
                    cliente.getDocumento(),
                    cliente.getTipoDocumento(),
                    cliente.getEmail().getEnderacoEmail(),
                    cliente.getTelefone(),
                    cliente.getEndereco(),
                    cliente.getGenero().getValor(),
                    cliente.getDataNascimento(),
                    cliente.getEstadoCivil().getValor(),
                    cliente.getProfissao(),
                    cliente.getPassaporte(),
                    cliente.getCnh(),
                    cliente.getNaturalidade(),
                    cliente.getIdAdvogado(),
                    processos
            );
        });

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
