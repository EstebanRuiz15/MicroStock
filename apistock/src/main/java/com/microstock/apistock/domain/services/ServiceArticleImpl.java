package com.microstock.apistock.domain.services;

import java.util.Optional;
import java.util.stream.Collectors;

import java.util.Map;

import com.microstock.apistock.domain.exception.ErrorException;
import com.microstock.apistock.domain.exception.ErrorExceptionParam;
import com.microstock.apistock.domain.exception.ErrorNotFoudArticle;
import com.microstock.apistock.domain.interfaces.IArticleRepositoryPort;
import com.microstock.apistock.domain.interfaces.IArticleService;
import com.microstock.apistock.domain.model.Article;
import com.microstock.apistock.domain.model.Category;
import com.microstock.apistock.domain.util.ConstantsDomain;
import com.microstock.apistock.domain.util.PaginArticle;
import com.microstock.apistock.domain.util.article.ArticleDto;
import com.microstock.apistock.domain.util.article.CategoryDto;
import java.util.Comparator;
import java.util.List;
import java.util.HashMap;

public class ServiceArticleImpl implements IArticleService {

    private final IArticleRepositoryPort repository;

    public ServiceArticleImpl(IArticleRepositoryPort repository) {

        this.repository = repository;
    }

    @Override
    public void createArticle(Article article) {
        Optional<Article> exist = repository.findByNombreIgnoreCase(article.getName().trim());
        if (exist.isPresent()) {
            throw new ErrorException(ConstantsDomain.NAME_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        }
        repository.saveArticle(article);
    }

    @Override
    public PaginArticle getAllArticles(Integer page, Integer size, String orden, String nameOrden) {
        if (!orden.trim().equalsIgnoreCase(ConstantsDomain.ASC)
                && !orden.trim().equalsIgnoreCase(ConstantsDomain.DESC)) {
            throw new ErrorExceptionParam(ConstantsDomain.ORDEN_DIFERENT_ASC_OR_DESC_EXCEPTION_MESSAGE);
        }

        if (page < ConstantsDomain.ZERO) {
            throw new ErrorExceptionParam(ConstantsDomain.PAGE_MIN_CHARACTER_EXCEPTION_MESSAGE);
        }

        if (size <= ConstantsDomain.ZERO) {
            throw new ErrorExceptionParam(ConstantsDomain.SIZE_MIN_CHARACTER_EXCEPTION_MESSAGE);
        }

        if (!nameOrden.trim().equalsIgnoreCase(ConstantsDomain.ARTICLE)
                && !nameOrden.trim().equalsIgnoreCase(ConstantsDomain.BRAND)
                && !nameOrden.trim().equalsIgnoreCase(ConstantsDomain.CATEGORY)) {
            throw new ErrorExceptionParam(ConstantsDomain.ORDEN_NAME_DIFERENT_EXCEPTION_MESSAGE);
        }

        List<Article> allArticle = repository.finByArticle();
        List<ArticleDto> listDto = mapToDtoList(allArticle, nameOrden, orden);

        if (allArticle.isEmpty()) {
            throw new ErrorException(ConstantsDomain.NO_DATA_ARTICLE_EXCEPTION_MESSAGE);
        }

        // Calculate index
        Integer totalArticles = allArticle.size();
        Integer from = Math.min(page * size, totalArticles);
        Integer to = Math.min((page + ConstantsDomain.ONE) * size, totalArticles);

        // get categories of the current page
        List<ArticleDto> articlepage = listDto.subList(from, to);

        // calculate total of pages
        Integer totalPage = (int) Math.ceil((double) totalArticles / size);

        if (articlepage.isEmpty()) {
            throw new ErrorException(ConstantsDomain.NO_ARTICLES_FOUND_EXCEPTION_MESSAGE +
                    ConstantsDomain.TOTAL_PAGES + totalPage);
        }

        return new PaginArticle(
                articlepage,
                page,
                size,
                totalPage,
                totalArticles);
    }

    private static ArticleDto mapToDto(Article article) {
        List<CategoryDto> categories = article.getCategories().stream()
                .map(category -> new CategoryDto(category.getId(), category.getName()))
                .sorted(Comparator.comparing(CategoryDto::getName))
                .collect(Collectors.toList());

        return new ArticleDto(
                article.getId(),
                article.getName(),
                article.getDescription(),
                article.getPrice(),
                article.getQuantity(),
                article.getBrand().getName(),
                categories);
    }

    @Override
    public String incrementArticles(Integer idArticle, Integer quantity) {
        Optional<Article> exist = repository.findById(idArticle);
        if (!exist.isPresent()) {
            throw new ErrorNotFoudArticle(ConstantsDomain.NO_ARTICLE_FOUND);

        }
        Article updateArticle = exist.get();
        updateArticle.setQuantity(updateArticle.getQuantity() + quantity);
        return repository.saveUpdate(updateArticle);
    }

    @Override
    public void rollbackArticles(Integer idArticle, Integer quantity) {

        Article article = repository.findById(idArticle).get();
        article.setQuantity(article.getQuantity() - quantity);
        repository.saveArticle(article);
    }

    @Override
    public boolean validItemExist( Integer idArticle){
        return repository.findById(idArticle).isPresent();
    };

    @Override
    public Integer validQuantityItems( Integer idArticle){
       return repository.findById(idArticle).get().getQuantity();
    };

    @Override 
    public boolean validCategories(List<Integer> listId){
        Map<Long, Integer> categoryCount = new HashMap<>();
        List<Article> articles=repository.findAllById(listId);
        for (Article article : articles) {
            for (Category category : article.getCategories()) {
                Long categoryId=category.getId();
                categoryCount.put(categoryId, categoryCount.getOrDefault(categoryId, 0) + 1);
            }
        }
        
        for (Integer count : categoryCount.values()) {
            if (count > 2) {
                return false; 
            }
        }

        return true;
    }

    private static List<ArticleDto> mapToDtoList(List<Article> articles, String sortBy, String ascending) {
        Comparator<ArticleDto> comparator;

        switch (sortBy) {
            case "brand":
                comparator = Comparator.comparing(ArticleDto::getbrandName, String.CASE_INSENSITIVE_ORDER);
                break;
            case "category":
                comparator = Comparator.comparing(article -> article.getCategories().get(0).getName(),
                        String.CASE_INSENSITIVE_ORDER);
                break;
            case "article":
            default:
                comparator = Comparator.comparing(ArticleDto::getName, String.CASE_INSENSITIVE_ORDER);
                break;
        }

        if (ConstantsDomain.DESC.equalsIgnoreCase(ascending)) {
            comparator = comparator.reversed();
        }

        return articles.stream()
                .map(ServiceArticleImpl::mapToDto)
                .sorted(comparator)
                .collect(Collectors.toList());
    }

}
