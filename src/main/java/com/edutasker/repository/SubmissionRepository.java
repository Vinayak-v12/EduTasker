package com.edutasker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edutasker.entity.Project;
import com.edutasker.entity.Submission;
@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
	 List<Submission> findByProjectId(long projectId);
	 @Query("SELECT s FROM Submission s WHERE s.project.assigned_by.id = :instructorId AND s.is_graded=false")
	 List<Submission> findSubmissionsForInstructor(@Param("instructorId") Long instructorId);
}
