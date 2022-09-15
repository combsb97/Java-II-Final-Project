/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

import java.util.Comparator;

/**
 *
 * @author Ben
 */
public class Student extends Person implements Comparable<Student> {

    @Override
    public int compareTo(Student other) {
        return this.getLastName().compareTo(other.getLastName());
    }

    public enum StudentType {
        NoMajor, FullTime, PartTime
    };

    private StudentType studentType;
    private String major;
    private float balance;

    /**
     * This constructor is called for a student record that has a major
     *
     * @param f first name of student
     * @param l last name of student
     * @param p student password
     * @param e student email
     * @param b student bannerId
     * @param st student type
     * @param m student major
     * @param bal student balance
     * @throws Exception if student type NoMajor is declared with major or if
     * student type is neither FullTime nor PartTime
     */
    public Student(String f, String l, String p, String e, int b, String st, String m, float bal) throws Exception {

        super(f, l, p, e, b);

        if (st.equals(StudentType.FullTime.toString()) || st.equals(StudentType.PartTime.toString())) {
            studentType = StudentType.valueOf(st);
        } else if (st.equals(StudentType.NoMajor.toString())) {
            throw new Exception("Invalid Student Type: " + st + " with major declared");
        } else {
            throw new Exception("Invalid Student Type: " + st);
        }

        major = m;
        balance = bal;

    }

    /**
     * This constructor is called for a student record without a major
     *
     * @param f first name of student
     * @param l last name of student
     * @param p student password
     * @param e student email
     * @param b student bannerId
     * @param st student type
     * @param bal student balance
     * @throws Exception if student type FullTime or PartTime declared without
     * major or if student type is not recognized
     */
    public Student(String f, String l, String p, String e, int b, String st, float bal) throws Exception {
        super(f, l, p, e, b);
        if (st.equals(StudentType.NoMajor.toString())) {
            studentType = StudentType.valueOf(st);
        } else if (st.equals(StudentType.FullTime.toString()) || st.equals(StudentType.PartTime.toString())) {
            throw new Exception("Invalid Student Type: " + st + " without major declared");
        } else {
            throw new Exception("Invalid Person Type: " + st);
        }
        balance = bal;

    }

    /**
     * This constructor is called when adding a student record and a new
     * bannerId and email is to be generated
     *
     * @param f first name of student
     * @param l last name of student
     * @param p student password
     * @param st student type
     * @param m student major
     * @param bal student balance
     * @throws Exception if major is missing for student types FullTime and
     * PartTime or if major is present for student type NoMajor
     */
    public Student(String f, String l, String p, String st, String m, float bal) throws Exception {
        super(f, l, p);
        studentType = StudentType.valueOf(st.replace(" ", ""));
        major = m;
        balance = bal;
        System.out.println(bal);
    }

    public StudentType getStudentType() {
        return studentType;
    }

    public String getStudentTypeString() {
        return studentType.toString();
    }

    public String getMajor() {
        return this.major;
    }

    @Override
    public String toString() {
        if (studentType.equals(StudentType.FullTime) || studentType.equals(StudentType.PartTime)) {
            return super.toString() + String.format("Student Type: %s, Major: %s, Balance: %.2f", studentType.toString(), major, balance);
        } else {
            return super.toString() + String.format("Student Type: %s, Balance: %.2f", studentType.toString(), balance);
        }
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
