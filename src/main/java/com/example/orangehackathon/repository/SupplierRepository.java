package com.example.orangehackathon.repository;

import com.example.orangehackathon.entity.Supplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier,Long> {
}
