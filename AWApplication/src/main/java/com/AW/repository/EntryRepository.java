package com.AW.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AW.entity.Entry;

public interface EntryRepository extends JpaRepository<Entry, Integer>{
	
	@Query("from Entry as n where n.userDtls.id=:uid")
	Page<Entry> findyNotesByUser(@Param("uid") int uid,Pageable p);
	
	

}