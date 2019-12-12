package com.jccd.crudexample.repository;

import com.jccd.crudexample.entity.Developer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends ReactiveMongoRepository<Developer,String> {
}
