package com.kk.coding.excercise.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kk.coding.excercise.model.URLInfo;

public interface URLInfoMongoRepository extends MongoRepository<URLInfo, String>{
	
	List <URLInfo> findByValue(String value);

}
