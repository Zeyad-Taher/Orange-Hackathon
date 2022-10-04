package com.example.orangehackathon.service;

import com.example.orangehackathon.dto.SupplierDTO;
import com.example.orangehackathon.entity.Supplier;
import com.example.orangehackathon.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public void addSupplier(SupplierDTO supplierDTO) {
        Supplier supplier=new Supplier(supplierDTO);
        supplierRepository.save(supplier);
    }

    public ArrayList<Supplier> showAllSuppliers() {
        return (ArrayList<Supplier>) supplierRepository.findAll();
    }

    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }

    public Supplier findSupplierById(Long supplierId) {
        return supplierRepository.findById(supplierId).get();
    }

    public void saveSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }
}
