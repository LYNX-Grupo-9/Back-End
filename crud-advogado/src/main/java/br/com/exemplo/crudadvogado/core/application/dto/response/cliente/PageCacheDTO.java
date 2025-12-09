package br.com.exemplo.crudadvogado.core.application.dto.response.cliente;

import java.util.Collection;
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
) {
    public int getPage() {
        return 0;
    }

    public int getSize() {
        return 0;
    }

    public int getTotalElements() {
        return 0;
    }

    public int getTotalPages() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    public Collection<Object> getContent() {
        return List.of();
    }
}
