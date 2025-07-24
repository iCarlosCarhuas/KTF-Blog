package com.app.ktf.blog.service.serviceImpl;

import com.app.ktf.blog.entity.QuizEntity;
import com.app.ktf.blog.repository.QuizRepository;
import com.app.ktf.blog.service.QuizService;
import org.springframework.stereotype.Service;

@Service
public class QuizServiceImpl extends GenericServiceImpl<QuizEntity, Long> implements QuizService {

    private final QuizRepository quizRepository;

    public QuizServiceImpl(QuizRepository quizRepository){
        super(quizRepository);
        this.quizRepository = quizRepository;
    }
}
