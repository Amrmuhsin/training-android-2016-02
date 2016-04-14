package com.artivisi.app.android.pembayaran.dto;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by jimmy on 13/04/16.
 */
public class Page<T> {

    private long totalElements;
    private int totalPages;
    private boolean last;
    private int numberOfElements;
    private boolean first;
    private int size;
    private int number;
    private List<Map<String, String>> content;

    public List<Map<String, String>> getContent() {
        return content;
    }

    public void setContent(List<Map<String, String>> content) {
        this.content = content;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
