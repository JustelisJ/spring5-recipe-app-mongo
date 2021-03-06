package guru.springframework.service;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.converters.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    @Mock
    RecipeRepository recipeRepository;
    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;
    IngredientService ingredientService;

    public IngredientServiceImplTest() {
        ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, ingredientCommandToIngredient,
                recipeRepository, unitOfMeasureRepository);
    }

    @Test
    public void findByRecipeIdAndIngredientId() {
        Recipe recipe = new Recipe();
        recipe.setId("1L");

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId("1L");

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId("2L");

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId("3L");

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);
        Optional<Recipe> optionalRecipe = Optional.of(recipe);

        when(recipeRepository.findById(anyString())).thenReturn(optionalRecipe);

        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId("1L", "3L");

        assertEquals("3L", ingredientCommand.getId());
        verify(recipeRepository, times(1)).findById(anyString());
    }

    @Test
    public void testSaveRecipeCommand() {
        IngredientCommand command = new IngredientCommand();
        command.setId("3L");
        command.setRecipeId("2L");

        Optional<Recipe> recipeOptional = Optional.of(new Recipe());

        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(Ingredient.builder().id("3L").build());

        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        assertEquals("3L", savedCommand.getId());
        verify(recipeRepository, times(1)).findById(anyString());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    public void testIngredientDeleteById() {
        Recipe recipe = new Recipe();
        recipe.setId("1L");

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId("1L");
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId("2L");

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

        ingredientService.deleteById("1L", "1L");

        verify(recipeRepository, times(1)).findById(anyString());
        verify(recipeRepository, times(1)).save(any());
    }

    @Test
    public void testIngredientDeleteByIdFailed() {
        Recipe recipe = new Recipe();
        recipe.setId("1L");

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId("1L");
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId("2L");

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

        ingredientService.deleteById("1L", "3L");

        verify(recipeRepository, times(1)).findById(anyString());
        verify(recipeRepository, times(0)).save(any());
    }
}