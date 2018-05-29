package com.example.BillPaymentAdminPanel.repository;

import com.example.BillPaymentAdminPanel.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    public Users findByUsername(String username);
    List<Users> findByStatus(int id);
}
