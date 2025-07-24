package com.app.ktf.blog.service.serviceImpl;

import com.app.ktf.blog.entity.QuestionEntity;
import com.app.ktf.blog.repository.QuestionRepository;
import com.app.ktf.blog.service.QuestionService;

public class QuestionServiceImpl extends GenericServiceImpl<QuestionEntity, Long> implements QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository){
        super(questionRepository);
        this.questionRepository = questionRepository;
    }
}
