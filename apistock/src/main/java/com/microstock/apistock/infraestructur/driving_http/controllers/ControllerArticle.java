package com.microstock.apistock.infraestructur.driving_http.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microstock.apistock.domain.interfaces.IArticleService;
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
    private final IArticleRequestAddMapper articleRequestAddMapper;

    
    @Operation(summary = "Method for creating artile", 
    description = "This method allows you to create a new article by providing the necessary data in the body of the request.\n\n" + //
                "rules:\n\n"+//
                "       - the name must be unique\n\n" + //
                "       - They must have a minimum of 1 associated category and a maximum of 2.\n\n" + //
                "       - They cannot have repeated categories."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = ""),
        @ApiResponse(responseCode = "400", description = " Invalid parameter. Possible errors:\n\n" +
                                                          "    - `name`: Cannot be null.\n\n" +
                                                          "    - `name`: the name is already exist.\n\n" +
                                                          "    - `description`:Cannot be null.\n\n"+
                                                          "    - 'price': Cannot be null.\n\n"+
                                                          "    - 'quantity': It cannot be a negative number.\n\n"+
                                                          "    - 'brandId': must belong to a brand.\n\n"+
                                                          "    - ;brand': brand not found +id.\n\n"+
                                                          "    - 'categoriesId': must belong to 1 or maximum 3 categories and cannot be repeated.\n\n"+
                                                          "    - 'categories': Category not found + id.\n\n")
    })

    @PostMapping("/")
    public ResponseEntity<Void> addArticle(@Valid @RequestBody  ArticleDtoAdd request) {
        articleService.createArticle(articleRequestAddMapper.toArticle(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
