package com.tttn.ThucTapTotNghiep.projectsService.repository;

import com.tttn.ThucTapTotNghiep.projectsService.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer> {
    List<Project> findByProjectOfGroup(Integer projectOfGroup);
    Project findOneByProjectId(Integer projectId);
    Boolean existsByProjectName(String projectName);
}
