package com.app.ktf.blog.service.serviceImpl;

import com.app.ktf.blog.entity.CategoryEntity;
import com.app.ktf.blog.repository.CategoryRepository;
import com.app.ktf.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends GenericServiceImpl<CategoryEntity, Long> implements CategoryService {

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        super(repository);
    }

}
