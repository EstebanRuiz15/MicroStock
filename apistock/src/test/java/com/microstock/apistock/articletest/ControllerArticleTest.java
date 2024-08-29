package com.microstock.apistock.articletest;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.microstock.apistock.domain.interfaces.IArticleService;
import com.microstock.apistock.infraestructur.driving_http.controllers.ControllerArticle;
import com.microstock.apistock.infraestructur.driving_http.mappers.IArticleRequestAddMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerArticleTest {

    private MockMvc mockMvc;

    @Mock
    private IArticleService articleService;

    @Mock
    private IArticleRequestAddMapper articleRequestAddMapper;

    @InjectMocks
    private ControllerArticle controllerArticle;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controllerArticle).build();
    }

    @Test
     void addArticle_ValidRequest_ReturnsCreatedStatus() throws Exception {
        
        doNothing().when(articleService).createArticle(any());

        // Act & Assert
        mockMvc.perform(post("/article/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"name\": \"Article Name\",\n" +
                        "  \"description\": \"Description\",\n" +
                        "  \"price\": 100.0,\n" +
                        "  \"quantity\": 10,\n" +
                        "  \"brandId\": 1,\n" +
                        "  \"categoriesId\": [1, 2]\n" +
                        "}"))
                .andExpect(status().isCreated());
    }

    @Test
void addArticle_InvalidRequest_ReturnsBadRequest() throws Exception {
    // Arrange
    String invalidRequest = "{" +
            "\"name\": \"\"," +
            "\"description\": \"\"," +
            "\"price\": -100.0," +
            "\"quantity\": -10," +
            "\"brandId\": -1," +
            "\"categoriesId\": [1, 1]" +
            "}";

    // Act & Assert
    mockMvc.perform(post("/article/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(invalidRequest))
            .andExpect(status().isBadRequest());
        MvcResult result = mockMvc.perform(post("/article/")
        .contentType(MediaType.APPLICATION_JSON)
        .content(invalidRequest))
        .andExpect(status().isBadRequest())
        .andReturn();

String responseContent = result.getResponse().getContentAsString();
System.out.println("Response JSON: " + responseContent);
}
}