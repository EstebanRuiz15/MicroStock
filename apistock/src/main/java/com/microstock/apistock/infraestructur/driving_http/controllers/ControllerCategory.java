package com.microstock.apistock.infraestructur.driving_http.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.microstock.apistock.domain.interfaces.ICategoryService;
import com.microstock.apistock.domain.util.PaginCategory;
import com.microstock.apistock.infraestructur.driving_http.dtos.request.CategoryDtoAdd;
import com.microstock.apistock.infraestructur.driving_http.mappers.IMapperPeticionAdd;
import com.microstock.apistock.infraestructur.util.ConstantsInfraestructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
@RestController
@RequestMapping("/category")
@Service 
public class ControllerCategory {
    
    private final ICategoryService serviceCategoria;
    private final IMapperPeticionAdd mapperadd;

    public ControllerCategory(ICategoryService serviceCategoria, IMapperPeticionAdd mapperadd) {
        this.serviceCategoria= serviceCategoria;
        this.mapperadd=mapperadd;
    }

    @Operation(summary = "Method for creating categories", 
    description = "This method allows you to create a new category by providing the necessary data in the body of the request.\n\n" + //
                "rules:\n\n"+//
                "       - the name must be unique\n\n" + //
                "       - the name cannot be longer than 50 characters\n\n" + //
                "       - the description cannot be longer than 90 characters"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = ""),
        @ApiResponse(responseCode = "400", description = " Invalid parameter. Possible errors:\n\n" +
                                                          "    - `name`: Cannot be null.\n\n" +
                                                          "    - `name`: the name is already exist.\n\n" +
                                                          "    - `description`:Cannot be null.")
    })

    @PostMapping("/add")
    public ResponseEntity<Void> crearCategoria(@RequestBody CategoryDtoAdd category) {
        try {
            serviceCategoria.createCategory(mapperadd.toCategoria(category));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ConstantsInfraestructure.ERROR_CATEGORY, e);
        }
    }
    
    @Operation(summary = "Method for listing categories", 
    description = "This method allows you to list the page categories, as well as being able to list them ascending or descending by name..\n\n" + //
                "rules:\n\n"+//
                 "       -It should be possible to parameterize whether"
                         +" I want to bring the categories ordered ascending or descending by name\n\n" + //
                 "       -This service must be paginated")
    @ApiResponses(value = {
         @ApiResponse(responseCode = "200", description = "List of categories and pagination data"),
         @ApiResponse(responseCode = "400", description = " Invalid parameter. Possible errors:\n\n" +
                                                          "    - `page`: Cannot be negative.\n\n" +
                                                          "    - `size`: Must be greater than zero.\n\n" +
                                                          "    - `order`: Must be 'asc' or 'desc'."),
         @ApiResponse(responseCode = "404", description = "    - 'Not categories in this pages', Toal pages is:\n\n"+
                                                           "   - 'Not categories found")
                })
    @GetMapping("/")
    public ResponseEntity<PaginCategory>getAllCategory(
                            @RequestParam (defaultValue = "0")Integer page,
                            @RequestParam (defaultValue = "10") Integer size,
                            @RequestParam (defaultValue = "asc") String orden) {
        
        return ResponseEntity.ok(serviceCategoria.getAllCategory(page, size, orden));
    }
}