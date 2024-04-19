package com.tttn.ThucTapTotNghiep.projectsService.wrapper;

import java.sql.Timestamp;

public class CreateProjectForm {
    private String project_name;
    private int project_of_group;
    private String project_description;
    private int created_by;
    private Timestamp expired_day;

    public CreateProjectForm() {
    }

    public CreateProjectForm(String project_name, int project_of_group, String project_description, int created_by, Timestamp expired_day) {
        this.project_name = project_name;
        this.project_of_group = project_of_group;
        this.project_description = project_description;
        this.created_by = created_by;
        this.expired_day = expired_day;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public int getProject_of_group() {
        return project_of_group;
    }

    public void setProject_of_group(int project_of_group) {
        this.project_of_group = project_of_group;
    }

    public String getProject_description() {
        return project_description;
    }

    public void setProject_description(String project_description) {
        this.project_description = project_description;
    }

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public Timestamp getExpired_day() {
        return expired_day;
    }

    public void setExpired_day(Timestamp expired_day) {
        this.expired_day = expired_day;
    }
}
