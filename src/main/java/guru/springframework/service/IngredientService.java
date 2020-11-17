package guru.springframework.service;

import guru.springframework.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteById(String recipeId, String ingredientId);

}
