package guru.springframework.controllers;

import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class RecipeController {

  private RecipeService recipeService;
  public RecipeController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }


  @RequestMapping({"/recipe/show/{id}"})
  public String showById(@PathVariable Long id, Model model) {
    model.addAttribute("recipe", recipeService.findById(id));
    return "recipe/show";
  }
}
