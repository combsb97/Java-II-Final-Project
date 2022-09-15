/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

/**
 *
 * @author Ben
 */
public class Faculty extends Person {

    public enum FacultyType {
        Professor, Adjunct
    };

    private FacultyType facultyType;
    private String department;

    public Faculty(String f, String l, String p, String e, int b, String ft, String dept) throws Exception {
        super(f, l, p, e, b);
        if (ft.equals(FacultyType.Adjunct.toString()) || ft.equals(FacultyType.Professor.toString())) {
            facultyType = FacultyType.valueOf(ft);
        } else {
            throw new Exception("Invalid Faculty Type");
        }
        department = dept;
    }

    public FacultyType getFacultyType() {
        return facultyType;
    }

    public String getFacultyTypeString() {
        return facultyType.toString();
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("Faculty Type: %s, Dept: %s", facultyType.toString(), department);
    }

    @Override
    public int getKey() {
        return this.getBannerId();
    }

    @Override
    public boolean sameKey(int k) {
        return this.getBannerId() == k;
    }

}
