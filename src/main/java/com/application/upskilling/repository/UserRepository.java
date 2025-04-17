package com.application.upskilling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.upskilling.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
