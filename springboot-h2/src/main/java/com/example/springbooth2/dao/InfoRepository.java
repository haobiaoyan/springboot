package com.example.springbooth2.dao;

import com.example.springbooth2.domain.Info;
import org.springframework.data.repository.CrudRepository;

public interface InfoRepository extends CrudRepository<Info,Integer> {
}
