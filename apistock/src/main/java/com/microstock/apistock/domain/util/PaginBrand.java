package com.microstock.apistock.domain.util;

import java.util.List;

import com.microstock.apistock.domain.model.Brand;
public class PaginBrand {
     
    private  List<Brand> brand;
    private  Integer  currentPage;
    private  Integer size;
    private  Integer totalpages;
    private  Integer totalData;

    public PaginBrand(List<Brand> brand, Integer currentPage, Integer size, Integer totalpages, Integer totalData) {
        this.brand = brand;
        this.currentPage = currentPage;
        this.size = size;
        this.totalpages = totalpages;
        this.totalData = totalData;
    }
    public List<Brand> getBrands() {
        return brand;
    }
    public void setBrand(List<Brand> brand) {
        this.brand = brand;
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
