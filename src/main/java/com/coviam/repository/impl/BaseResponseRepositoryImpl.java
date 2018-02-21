package com.coviam.repository.impl;

import com.coviam.dto.BaseResponseDTO;
import com.coviam.repository.BaseResponseRepository;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class BaseResponseRepositoryImpl implements BaseResponseRepository {


    @Autowired
    Logger logger;
    private RedisTemplate<String, BaseResponseDTO> redisTemplate;
    private HashOperations hashOps;

    @Autowired
    public BaseResponseRepositoryImpl(RedisTemplate<String, BaseResponseDTO> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init(){
        this.hashOps = this.redisTemplate.opsForHash();
    }

    @Override
    public List<BaseResponseDTO> findAll(String cacheName) {
        return null;
    }

    @Override
    public BaseResponseDTO get(String cacheName, String cacheKey) {
        BaseResponseDTO cachedResponse = new BaseResponseDTO();
        try {

             cachedResponse = (BaseResponseDTO) hashOps.get(cacheName, cacheKey.hashCode());
             if(cachedResponse != null) {
                 logger.debug(" getting results from cache");
             }
        }catch(Exception e){
            logger.error("getting Exception in getting results from cache");
        }
        return cachedResponse;

    }

    @Override
    public void save(String cacheName, String cacheKey, BaseResponseDTO cacheValue) {
        try {
            hashOps.put(cacheName, cacheKey.hashCode(), cacheValue);
            logger.debug("saving data to Redis cache");
        }catch(Exception e){
            logger.error("getting error while saving data Redis cache");
        }

    }

    @Override
    public void insert(String cacheName, String cacheKey, BaseResponseDTO cacheValue) {
        try {
            hashOps.put(cacheName, cacheKey.hashCode(), cacheValue);
            logger.debug("saving data to Redis cache");
        }catch(Exception e){
            logger.error("getting error while saving data Redis cache");
        }

    }

    @Override
    public void delete(String cacheName, String cacheKey) {

    }

    @Override
    public void deleteAll(String cacheName) {

    }

    @Override
    public boolean exists(String cacheName, String cacheKey) {
        return false;
    }

    @Override
    public Long count(String cacheName) {
        return null;
    }
}
