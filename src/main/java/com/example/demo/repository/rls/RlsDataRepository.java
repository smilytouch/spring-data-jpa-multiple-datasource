package com.example.demo.repository.rls;

import com.example.demo.entities.rls.RlsData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RlsDataRepository extends CrudRepository<RlsData,Integer> {
}
