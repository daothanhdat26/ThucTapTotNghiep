package com.tttn.ThucTapTotNghiep.projectsService.repository;

import com.tttn.ThucTapTotNghiep.projectsService.model.ProjectLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectLogRepository extends JpaRepository<ProjectLog,Integer> {
    List<ProjectLog> getAllByLogOfProject(Integer logOfProject);
}
