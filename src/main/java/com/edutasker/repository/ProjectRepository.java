package com.edutasker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edutasker.entity.Project;
@Repository
public interface ProjectRepository extends JpaRepository<Project,Long > {
	@Query("select p from Project p where p.assigned_by.id=:instructorId")
	public List<Project> createdByInstructor(Long instructorId);
}
