package com.microstock.apistock.infraestructur.driving_http.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microstock.apistock.domain.interfaces.IArticleService;
import com.microstock.apistock.domain.interfaces.IBrandService;
import com.microstock.apistock.domain.interfaces.ICategoryService;
import com.microstock.apistock.domain.util.PaginArticle;
import com.microstock.apistock.infraestructur.driven_rp.mapper.IBrandToEntityMapper;
import com.microstock.apistock.infraestructur.driven_rp.mapper.ICategoriaToEntitymapper;
import com.microstock.apistock.infraestructur.driving_http.dtos.request.ArticleDtoAdd;
import com.microstock.apistock.infraestructur.driving_http.mappers.IArticleRequestAddMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/article")
@Service
@AllArgsConstructor
public class ControllerArticle {
    private final IArticleService articleService;
    private final IArticleRequestAddMapper articleRequestMapper;
    private final ICategoryService serviceCategory;
    private final IBrandService serviceBrand;
    private final ICategoriaToEntitymapper mapperCategory;
    private final IBrandToEntityMapper mapperBrand;

    @Operation(summary = "Method for creating artile", description = "This method allows you to create a new article by providing the necessary data in the body of the request.\n\n"
            + //
            "rules:\n\n" + //
            "       - the name must be unique\n\n" + //
            "       - They must have a minimum of 1 associated category and a maximum of 2.\n\n" + //
            "       - They cannot have repeated categories.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "400", description = " Invalid parameter. Possible errors:\n\n" +
                    "    - `name`: Cannot be null.\n\n" +
                    "    - `name`: the name is already exist.\n\n" +
                    "    - `description`:Cannot be null.\n\n" +
                    "    - 'price': Cannot be null.\n\n" +
                    "    - 'quantity': It cannot be a negative number.\n\n" +
                    "    - 'brandId': must belong to a brand.\n\n" +
                    "    - ;brand': brand not found +id.\n\n" +
                    "    - 'categoriesId': must belong to 1 or maximum 3 categories and cannot be repeated.\n\n" +
                    "    - 'categories': Category not found + id.\n\n")
    })

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addArticle")
    public ResponseEntity<Void> addArticle(@Valid @RequestBody ArticleDtoAdd request) {
        articleService.createArticle(articleRequestMapper.toArticleModel(request, serviceCategory, serviceBrand,
                mapperBrand, mapperCategory));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Method for listing all articles", description = "This method allows you to list the page articles, as well as being able to list"
            +
            " them ascending or descending by name of categories, brand of article..\n\n" + //
            "rules:\n\n" + //
            "-It should be possible to parameterize whether I want to list the articles in descending " +
            "or ascending order, by the name of the article, by the name of the brand or category.\n\n" +
            "-For each listed article, I need to bring the list of categories with only the name and ID.\n\n" + //
            "-This service must be paginated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of articles and pagination data"),
            @ApiResponse(responseCode = "400", description = " Invalid parameter. Possible errors:\n\n" +
                    "    - `page`: Cannot be negative.\n\n" +
                    "    - `size`: Must be greater than zero.\n\n" +
                    "    - `order`: Must be 'asc' or 'desc'.\n\n" +
                    "    - 'order name': must be 'category', 'brand' or 'article' only"),

            @ApiResponse(responseCode = "404", description = "    - 'Not article in this pages', Toal pages is:\n\n" +
                    "   - 'Not article found' ")
    })
    @GetMapping("/")
    public ResponseEntity<PaginArticle> getAllArticles(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "asc") String orden,
            @RequestParam(defaultValue = "article") String nameOrden) {

        return ResponseEntity.ok(articleService.getAllArticles(page, size, orden, nameOrden));
    }

    @PreAuthorize("hasRole('AUX_BODEGA')")
    @PatchMapping("/increment")
    public ResponseEntity<String> increment(@Valid @RequestParam Integer idArticle,
            @RequestParam Integer quantity) {

        return ResponseEntity.ok(articleService.incrementArticles(idArticle, quantity));
    }

    @PreAuthorize("hasRole('AUX_BODEGA')")
    @PatchMapping("/rollback")
    public ResponseEntity<Void> rollback(@Valid @RequestParam Integer idArticle,
            @RequestParam Integer quantity) {

        articleService.rollbackArticles(idArticle, quantity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
