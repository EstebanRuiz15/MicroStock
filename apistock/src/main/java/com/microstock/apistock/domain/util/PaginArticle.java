package com.microstock.apistock.domain.util;

import java.util.List;

import com.microstock.apistock.domain.util.article.ArticleDto;

public class PaginArticle {
    private List<ArticleDto> articles;
    private  Integer  currentPage;
    private  Integer size;
    private  Integer totalpages;
    private  Integer totalData;
  
    public PaginArticle(List<ArticleDto> articles, Integer currentPage, Integer size, Integer totalpages,
            Integer totalData) {
        this.articles = articles;
        this.currentPage = currentPage;
        this.size = size;
        this.totalpages = totalpages;
        this.totalData = totalData;
    }

    public List<ArticleDto> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleDto> articles) {
        this.articles = articles;
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

    public Integer getTotalpages() {
        return totalpages;
    }

    public void setTotalpages(Integer totalpages) {
        this.totalpages = totalpages;
    }

    public Integer getTotalData() {
        return totalData;
    }

    public void setTotalData(Integer totalData) {
        this.totalData = totalData;
    }
    
    
}
