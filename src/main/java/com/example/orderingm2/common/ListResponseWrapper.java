package com.example.orderingm2.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * This class represents a basic dto wrapper for all results returned from
 * the API, this can be extended for adding extra info and custom data
 */
@Data
@NoArgsConstructor
public class ListResponseWrapper<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 7406407370431353543L;

    private List<T> result;

    private Metadata metadata;

    public static final ListResponseWrapper EMPTY_LIST_RESPONSE = new ListResponseWrapper<>(List.of(), 0, 0L);

    public ListResponseWrapper(List items, Integer totalPages, Long totalItems) {
        result = items;
        metadata = new Metadata();
        metadata.setTotalItems(totalItems);
        metadata.setTotalPages(totalPages);
    }

    public ListResponseWrapper(Page items) {
        result = items.getContent();
        metadata = new Metadata();
        metadata.setTotalItems(items.getTotalElements());
        metadata.setTotalPages(items.getTotalPages());
    }

    @JsonIgnore
    public Long getTotalItems() {
        return metadata.getTotalItems();
    }

    @JsonIgnore
    public Integer getTotalPages() {
        return metadata.getTotalPages();
    }
}

@Data
class Metadata {
    private Integer totalPages;
    private Long totalItems;

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
    }
}
