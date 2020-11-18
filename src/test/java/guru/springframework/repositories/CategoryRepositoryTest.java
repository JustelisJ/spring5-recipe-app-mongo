package guru.springframework.repositories;

import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByDescription() {
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");

        assertTrue(categoryOptional.isPresent());
        assertEquals("American", categoryOptional.get().getDescription());
    }

    @Test
    public void findByDescription2() {
        Optional<Category> categoryOptional = categoryRepository.findByDescription("Mexican");

        assertTrue(categoryOptional.isPresent());
        assertEquals("Mexican", categoryOptional.get().getDescription());
    }
}