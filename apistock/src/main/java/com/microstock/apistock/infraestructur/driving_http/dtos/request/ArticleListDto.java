package com.microstock.apistock.infraestructur.driving_http.dtos.request;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ArticleListDto {
   private List<Integer> ids;
}
