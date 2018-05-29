package com.example.BillPaymentAdminPanel.repository;

import com.example.BillPaymentAdminPanel.model.StakeHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StakeHolderRepository extends JpaRepository<StakeHolder, Integer>{
    List<StakeHolder> findByStatus(int id);
    StakeHolder findByIdAndAddress(int id, String address);
}
