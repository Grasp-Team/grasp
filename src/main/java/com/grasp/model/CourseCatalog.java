package com.grasp.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "courseCatalog", schema = "course")
public class CourseCatalog {

    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "subject", nullable = false)
    private String subject;
    @Column(name = "catalog_number", nullable = false)
    private Integer catalogNumber;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "description")
    private String description;
    @Column(name = "academic_level")
    private String academicLevel;
    @Column(name = "calendar_year")
    private Integer calendarYear;
    @Column(name = "url")
    private String url;


    public CourseCatalog() {
    }

    public CourseCatalog(String code, String subject, Integer catalogNumber, String courseName,
                         String description, String academicLevel, Integer calendarYear, String url) {
        this.code = code;
        this.subject = subject;
        this.catalogNumber = catalogNumber;
        this.courseName = courseName;
        this.description = description;
        this.academicLevel = academicLevel;
        this.calendarYear = calendarYear;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CourseCatalog)) {
            return false;
        }

        CourseCatalog that = (CourseCatalog) o;

        if (!getCode().equals(that.getCode())) {
            return false;
        }
        if (!getSubject().equals(that.getSubject())) {
            return false;
        }
        if (!getCatalogNumber().equals(that.getCatalogNumber())) {
            return false;
        }
        if (getCourseName() != null ? !getCourseName().equals(that.getCourseName()) : that.getCourseName() != null) {
            return false;
        }
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that
                .getDescription() != null) {
            return false;
        }
        if (getAcademicLevel() != null ? !getAcademicLevel().equals(that.getAcademicLevel()) : that
                .getAcademicLevel() != null) {
            return false;
        }
        if (getCalendarYear() != null ? !getCalendarYear().equals(that.getCalendarYear()) : that
                .getCalendarYear() != null) {
            return false;
        }
        return getUrl() != null ? getUrl().equals(that.getUrl()) : that.getUrl() == null;
    }

    @Override
    public int hashCode() {
        int result = getCode().hashCode();
        result = 31 * result + getSubject().hashCode();
        result = 31 * result + getCatalogNumber().hashCode();
        result = 31 * result + (getCourseName() != null ? getCourseName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getAcademicLevel() != null ? getAcademicLevel().hashCode() : 0);
        result = 31 * result + (getCalendarYear() != null ? getCalendarYear().hashCode() : 0);
        result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
        return result;
    }
}
