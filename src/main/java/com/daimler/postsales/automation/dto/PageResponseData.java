package com.daimler.postsales.automation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PageResponseData<T> {
    private Integer totalPages;
    private Integer pageNumber;
    private Long totalCount;
    private Integer pageSize;
    private List<T> content;

    public PageResponseData(Page<T> pagedData) {
        this.totalPages = pagedData.getTotalPages();
        this.pageNumber = pagedData.getNumber();
        this.totalCount = pagedData.getTotalElements();
        this.pageSize = pagedData.getSize();
        this.content = pagedData.getContent();
    }
}