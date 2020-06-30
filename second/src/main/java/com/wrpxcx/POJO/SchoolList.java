package com.wrpxcx.POJO;

public class SchoolList {
    private int schoolId;
    private String schoolName;

    public SchoolList(int schoolId, String schoolName) {
        this.schoolId = schoolId;
        this.schoolName = schoolName;
    }

    public SchoolList(){}

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        return "SchoolList{" +
                "schoolId=" + schoolId +
                ", schoolName='" + schoolName + '\'' +
                '}';
    }
}
