package apelsin.service;


import apelsin.entity.Category;
import apelsin.payload.ApiResponse;
import apelsin.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public ApiResponse addCategory(Category categoryDTO) {
        boolean exists = categoryRepository.existsByName(categoryDTO.getName());
        if (exists) return new ApiResponse("Failed", false);
        Category category = new Category();
        category.setName(categoryDTO.getName());
        categoryRepository.save(category);
        return new ApiResponse("Success", true);
    }

    public ApiResponse editCategory(Integer id, Category categoryDTO) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) return new ApiResponse("Failed", false);
        Category category = optionalCategory.get();
        category.setName(categoryDTO.getName());
        categoryRepository.save(category);
        return new ApiResponse("Success", true);
    }

    public List<Category> getAll() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }

    public ApiResponse getOne(int id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (!categoryOptional.isPresent()) return new ApiResponse("Failed", false);
        return new ApiResponse("Success", true, categoryOptional.get());
    }

    public ApiResponse deleteCategory(int id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (!categoryOptional.isPresent()) return new ApiResponse("Failed", false);
        categoryRepository.deleteById(id);
        return new ApiResponse("Success", true);
    }

    public ApiResponse getCategoryProductId(Integer id) {
        Optional<?> byProductId = categoryRepository.findByProductId(id);
        return new ApiResponse("Success", true, byProductId.get());
    }
}
