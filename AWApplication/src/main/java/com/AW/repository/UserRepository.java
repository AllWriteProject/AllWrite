package com.AW.repository;

import org.springframework.data.jpa.repository.JpaRepository;



import com.AW.entity.UserDtls;

public interface UserRepository extends JpaRepository<UserDtls,Integer>{
	
	public UserDtls findByEmail(String email);
	

}