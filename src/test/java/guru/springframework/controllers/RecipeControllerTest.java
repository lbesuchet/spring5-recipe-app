package guru.springframework.controllers;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

public class RecipeControllerTest {

  @Mock
  RecipeServiceImpl recipeService;
  @Mock
  Model model;

  RecipeController controller;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    controller = new RecipeController(recipeService);
  }

  @Test
  public void testGetRecipe() throws Exception {
    Recipe recipe = new Recipe();
    recipe.setId(1L);

    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    when(recipeService.findById(eq(1L))).thenReturn(recipe);
    mockMvc.perform(get("/recipe/1/show"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipe/show"))
        .andExpect(model().attributeExists("recipe"));
  }
}
