package com.dylanhersee.Project.Trends.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dylanhersee.Project.Trends.model.Trends;

public class TrendRepository {

    public interface Repository extends MongoRepository<Trends, String>{
        List<Trends> findByUsername (String username);
        List<Trends> findbyTrendCategory(String trendCategory);
    }

}
