package com.microstock.apistock.infraestructur.driving_http.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.microstock.apistock.domain.interfaces.IBrandService;
import com.microstock.apistock.infraestructur.driving_http.dtos.request.BrandDtoAdd;
import com.microstock.apistock.infraestructur.driving_http.mappers.IBrandRequestAddMapper;
import com.microstock.apistock.infraestructur.util.ConstantsInfraestructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/brand")
@Service 
@AllArgsConstructor
public class ControllerBrand {
    private final IBrandService brandService;
    private final IBrandRequestAddMapper brandRequestMapper;

    @Operation(summary = "Method for creating brands", 
    description = "This method allows you to create a new brand by providing the necessary data in the body of the request.\n\n" + //
                "rules:\n\n"+//
                "       - the name must be unique\n\n" + //
                "       - the name cannot be longer than 50 characters\n\n" + //
                "       - the description cannot be longer than 120 characters"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = ""),
        @ApiResponse(responseCode = "400", description = " Invalid parameter. Possible errors:\n\n" +
                                                          "    - `name`: Cannot be null.\n\n" +
                                                          "    - `name`: the name is already exist.\n\n" +
                                                          "    - 'name': the name cannot be loger than 50 characters.\n\n"+
                                                          "    - `description`:Cannot be null.\n\n"+
                                                          "    - 'description': Cannot be loger than 120 characters")
    })

    @PostMapping("/")
    public ResponseEntity<Void> addProduct(@RequestBody BrandDtoAdd request) {
       try {
            brandService.createBrand(brandRequestMapper.toBrand(request));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ConstantsInfraestructure.ERROR_CATEGORY, e);
        }
    }
}
