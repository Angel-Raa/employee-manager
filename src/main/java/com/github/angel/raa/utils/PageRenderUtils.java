/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.raa.utils;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author aguero
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageRenderUtils<T> {
    private String url;
    private org.springframework.data.domain.Page<T> page;
    private int totalPages;
    private int numElementsPerPage;
    private int actualPage;
    private List<PageableUtils> pages;

    public PageRenderUtils(String url, org.springframework.data.domain.Page<T> page) {
        int from;
        int until;
        this.url = url;
        this.page = page;
        this.pages = new ArrayList<>();
        this.numElementsPerPage = 5;
        this.totalPages = page.getTotalPages();
        this.actualPage = page.getNumber() + 1;

        if (totalPages <= numElementsPerPage) {
            from = 1;
            until = totalPages;
        } else {
            if (actualPage <= numElementsPerPage / 2) {
                from = 1;
                until = numElementsPerPage;
            } else if (actualPage >= totalPages - numElementsPerPage / 2) {
                from = totalPages - numElementsPerPage + 1;
                until = numElementsPerPage;
            } else {
                from = actualPage - numElementsPerPage / 2;
                until = numElementsPerPage;
            }
        }
        for (int i = 0; i <= until; i++) {
            pages.add(new PageableUtils(from + i, this.actualPage == from + i));
        }

    }

    public boolean isHasPrevious() {
        return page.hasPrevious();
    }

    public boolean isLast() {
        return page.isLast();
    }

    public boolean isFirst() {
        return page.isFirst();
    }

    public boolean isHasNext() {
        return page.hasNext();
    }
}
