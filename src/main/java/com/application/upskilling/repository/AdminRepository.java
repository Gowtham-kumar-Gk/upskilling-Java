package com.application.upskilling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.upskilling.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

}
