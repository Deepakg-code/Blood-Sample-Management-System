package com.jsp.bsm.repository;

import com.jsp.bsm.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

     Optional<Admin> findByUser_Email(String userName);
}
