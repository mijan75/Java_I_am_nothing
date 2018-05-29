package com.example.BillPaymentAdminPanel.repository;

import com.example.BillPaymentAdminPanel.model.BillInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<BillInfo, Integer>{
    List<BillInfo> findByStakeHolderId(int id);
}
