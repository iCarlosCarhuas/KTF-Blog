package com.app.ktf.blog.service.serviceImpl;

import com.app.ktf.blog.entity.PostEntity;
import com.app.ktf.blog.repository.PostRepository;
import com.app.ktf.blog.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl extends GenericServiceImpl<PostEntity, Long> implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository){
        super(postRepository);
        this.postRepository = postRepository;
    }
}
