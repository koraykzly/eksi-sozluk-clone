package com.example.eksi.payload.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PaginationRequest {

    public static final int DEFAULT_SIZE = 10;
    public static final int MAX_SIZE = 100;

    @Min(0)
    private int page = 0;

    @Min(1)
    @Max(MAX_SIZE)
    private Integer size;

    public PaginationRequest() {
    }

    public PaginationRequest(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public PageRequest toPageRequest() {
        return toPageRequest(DEFAULT_SIZE);
    }

    public PageRequest toPageRequest(int defaultSize) {
        return PageRequest.of(page, getResolvedSize(defaultSize));
    }

    public PageRequest toPageRequest(Sort sort) {
        return toPageRequest(DEFAULT_SIZE, sort);
    }

    public PageRequest toPageRequest(int defaultSize, Sort sort) {
        return PageRequest.of(page, getResolvedSize(defaultSize), sort);
    }

    private int getResolvedSize(int defaultSize) {
        return size != null ? size : defaultSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
