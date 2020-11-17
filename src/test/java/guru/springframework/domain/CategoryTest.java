package guru.springframework.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before
    public void setUp(){
        category = new Category();
    }

    @Test
    public void getId() {
        Long idValue = 4L;
        category.setId(idValue);
        assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription() {
        String desc = "word";
        category.setDescription(desc);
        assertEquals(desc, category.getDescription());
    }

    @Test
    public void getRecipes() {
        Recipe r1 = new Recipe();
        Recipe r2 = new Recipe();
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(r1);
        recipes.add(r2);
        category.setRecipes(recipes);
        assertEquals(recipes, category.getRecipes());
    }
}