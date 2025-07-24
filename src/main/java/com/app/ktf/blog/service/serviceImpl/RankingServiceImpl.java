package com.app.ktf.blog.service.serviceImpl;

import com.app.ktf.blog.entity.RankingEntity;
import com.app.ktf.blog.repository.RankingRepository;
import com.app.ktf.blog.service.RankingService;

public class RankingServiceImpl extends GenericServiceImpl<RankingEntity, Long> implements RankingService {
    private final RankingRepository rankingRepository;

    public RankingServiceImpl(RankingRepository rankingRepository){
        super(rankingRepository);
        this.rankingRepository = rankingRepository;
    }
}
