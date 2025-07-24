package com.app.ktf.blog.service.serviceImpl;

import com.app.ktf.blog.entity.AnswerEntity;
import com.app.ktf.blog.repository.AnswerRepository;
import com.app.ktf.blog.service.AnswerService;

public class AnswerServiceImpl extends GenericServiceImpl<AnswerEntity,Long> implements AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository){
        super(answerRepository);
        this.answerRepository = answerRepository;
    }
}
