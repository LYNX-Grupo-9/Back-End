package br.com.exemplo.crudadvogado.core.application.dto.response.processo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {

    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;

    public static <T> PageResponse<T> from(Page<T> page) {
        return new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

    @SuppressWarnings("unchecked")
    public static <T> PageResponse<T> fromCached(
            Object cached,
            Class<T> elementClass,
            ObjectMapper objectMapper
    ) {
        Map<String, Object> map = (Map<String, Object>) cached;

        List<T> items = ((List<?>) map.get("content"))
                .stream()
                .map(o -> objectMapper.convertValue(o, elementClass))
                .toList();

        return new PageResponse<>(
                items,
                (Integer) map.get("page"),
                (Integer) map.get("size"),
                ((Number) map.get("totalElements")).longValue(),
                (Integer) map.get("totalPages")
        );
    }
}