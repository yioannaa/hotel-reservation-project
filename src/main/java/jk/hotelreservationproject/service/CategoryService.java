package jk.hotelreservationproject.service;

import jk.hotelreservationproject.model.Category;
import jk.hotelreservationproject.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Size;
import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> showAllCategories(){
        return categoryRepository.findAll();
    }


    public Category showCategory(Long categoryId){
        return categoryRepository.getOne(categoryId);
    }

}
