package com.microstock.apistock.infraestructur.driving_http.dtos.request;

import java.util.List;

import com.microstock.apistock.domain.util.IUniqueElements;
import com.microstock.apistock.infraestructur.util.ConstantsInfraestructure;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Valid
public class ArticleDtoAdd {

    @NotBlank(message = ConstantsInfraestructure.ERROR_NAME_NULL)
    private  String name;
    @NotBlank(message=ConstantsInfraestructure.ERROR_DESCRIPTION_NULL)
    private  String description;
    @Positive(message=ConstantsInfraestructure.ERROR_PRICE)
    private  Double price;
    @PositiveOrZero(message = ConstantsInfraestructure.ERROR_QUANTITY)
    private  Integer quantity;
    @PositiveOrZero
    private Integer brandId;
    @NotEmpty
    @Size(min =ConstantsInfraestructure.ONE, max =ConstantsInfraestructure.THREE,
    message=ConstantsInfraestructure.ERROR_CATEGORIES_1TO3)
    @IUniqueElements
    private  List<Integer> categoriesId;


}
