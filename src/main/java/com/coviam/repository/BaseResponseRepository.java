package com.coviam.repository;

import com.coviam.dto.BaseResponseDTO;

import java.util.List;

public interface BaseResponseRepository {

    public List<BaseResponseDTO> findAll(String cacheName);

    public BaseResponseDTO get(String cacheName, String cacheKey);

    public void save(String cacheName, String cacheKey, BaseResponseDTO cacheValue);

    public void insert(String cacheName, String cacheKey, BaseResponseDTO cacheValue);

    public void delete(String cacheName, String cacheKey);

    public void deleteAll(String cacheName);

    public boolean exists(String cacheName, String cacheKey);

    public Long count(String cacheName);
}
