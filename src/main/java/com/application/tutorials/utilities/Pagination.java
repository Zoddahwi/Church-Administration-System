package com.application.tutorials.utilities;

import lombok.Data;

import java.util.List;

@Data
public class Pagination<T> {

    private List<T> content;
    private int pageNo;
    private int pageSize;
    private int sort;
    private int totalElements;
    private int totalPages;
    private boolean hasNext;
    private boolean hasPrevious;

}
