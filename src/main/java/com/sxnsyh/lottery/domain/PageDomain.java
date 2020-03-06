package com.sxnsyh.lottery.domain;

import lombok.Data;

import java.util.List;

@Data
public class PageDomain<T> {
    private long total;
    private List<T> dataSet;
}
