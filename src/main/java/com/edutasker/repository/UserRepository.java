package com.edutasker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edutasker.entity.Project;
import com.edutasker.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	@Query("select u from User u where u.email=:email")
	Optional<User> findbyEmail(String email);
	
	@Query("select u.assignedProjects from User u where u.id= :Id")
	List<Project> assignedProjects(Long Id);
}
