package com.example.orangehackathon.service;

import com.example.orangehackathon.dto.DashboardDTO;
import com.example.orangehackathon.dto.SupplierDTO;
import com.example.orangehackathon.entity.Supplier;
import com.example.orangehackathon.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public ResponseEntity<?> addSupplier(SupplierDTO supplierDTO) {
        Supplier supplier=new Supplier(supplierDTO);
        supplierRepository.save(supplier);
        return new ResponseEntity<>(supplier,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> showAllSuppliers() {
        List<Supplier> suppliers = (List<Supplier>) supplierRepository.findAll();
        return new ResponseEntity<>(suppliers,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> deleteSupplier(Long id) {
        if(!supplierRepository.findById(id).isPresent()){
            return new ResponseEntity<>("Invalid supplier ID",HttpStatus.BAD_REQUEST);
        }
        supplierRepository.deleteById(id);
        return new ResponseEntity<>("Supplier deleted Successfully",HttpStatus.ACCEPTED);
    }

    public Supplier findSupplierById(Long supplierId) {
        return supplierRepository.findById(supplierId).orElse(null);
    }

    public ResponseEntity<?> getDashboard(DashboardDTO dashboardDTO) {
        dashboardDTO.setTotalPaid(0);
        dashboardDTO.setTotalDebt(0);
        List<Supplier> suppliers = (List<Supplier>) supplierRepository.findAll();
        dashboardDTO.setSuppliers(suppliers);
        for(Supplier supplier : suppliers){
            dashboardDTO.setTotalDebt(dashboardDTO.getTotalDebt()+supplier.getDebt());
            dashboardDTO.setTotalPaid(dashboardDTO.getTotalPaid()+supplier.getPaid());
        }
        dashboardDTO.setTotalRemaining(dashboardDTO.getTotalDebt()- dashboardDTO.getTotalRemaining());
        return new ResponseEntity<>(dashboardDTO, HttpStatus.ACCEPTED);
    }
}
