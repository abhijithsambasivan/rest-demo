package com.example.restservice.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityRepo extends JpaRepository<UserModel, Long> {

	UserModel findByName(String name);

}
