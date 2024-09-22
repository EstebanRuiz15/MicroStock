package com.microstock.apistock.domain.interfaces;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import com.microstock.apistock.domain.model.Article;
import com.microstock.apistock.domain.util.PaginArticle;

public interface IArticleService {
    void createArticle(Article article);
    PaginArticle getAllArticles(Integer page, Integer size,String orden, String nameOrden);
    String incrementArticles(Integer idArticle,Integer quantity);
    void rollbackArticles (Integer idArticle, Integer quantity);
    boolean validItemExist(Integer idArticle);
    Integer validQuantityItems( Integer idArticle);
    boolean validCategories(@RequestParam List<Integer> listId);
}
