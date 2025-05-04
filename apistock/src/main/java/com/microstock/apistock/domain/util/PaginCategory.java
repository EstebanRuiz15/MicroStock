package com.microstock.apistock.domain.util;

import java.util.List;

import com.microstock.apistock.domain.model.Category;

public class PaginCategory {
    
    private  List<Category> category;
    private  Integer  currentPage;
    private  Integer size;
    private  Integer totalpages;
    private  Integer totalData;

    public PaginCategory(List<Category> category, Integer currentPage, Integer size, Integer totalpages, Integer totalData) {
        this.category = category;
        this.currentPage = currentPage;
        this.size = size;
        this.totalpages = totalpages;
        this.totalData = totalData;
    }
    public List<Category> getCategory() {
        return category;
    }
    public void setCategory(List<Category> category) {
        this.category = category;
    }
    public Integer getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
    public Integer getSize() {
        return size;
    }
    public void setSize(Integer size) {
        this.size = size;
    }
    public Integer getTotalPages() {
        return totalpages;
    }
    public void setTotalPages(Integer totalpages) {
        this.totalpages = totalpages;
    }
    public Integer getTotalData() {
        return totalData;
    }
    public void setTotalData(Integer totalData) {
        this.totalData = totalData;
    }
    


}
