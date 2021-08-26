package com.example.demo.repository.wfi;

import com.example.demo.entities.wfi.WfiData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WfiDataRepository extends CrudRepository<WfiData,Integer> {

}
