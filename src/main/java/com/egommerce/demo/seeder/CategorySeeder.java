package com.egommerce.demo.seeder;

import com.egommerce.demo.model.Category.Category;
import com.egommerce.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategorySeeder {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategorySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void seed() {
        if (categoryRepository.count() > 0) {
            return;
        }

        Category category1 = new Category("Keyboards");
        Category category2 = new Category("Keycaps");
        Category category3 = new Category("Switches");
        Category category4 = new Category("PCBs");
        Category category5 = new Category("Cases");

        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);
        categoryRepository.save(category4);
        categoryRepository.save(category5);
    }
}
