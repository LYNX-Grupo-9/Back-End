package br.com.exemplo.crudadvogado.core.application.dto.response.cliente;

import java.util.List;

public record PageCacheDTO<T>(
        List<T> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages,
        boolean first,
        boolean last,
        int numberOfElements,
        boolean empty
) {}
