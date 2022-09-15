/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

/**
 *
 * @author Ben
 */
public class Course implements KeyFinder {

    private int crn;
    private String name;
    private int credits;

    public Course(int crn, String n, int credits) {
        this.crn = crn;
        name = n;
        this.credits = credits;
    }

    public int getCRN() {
        return crn;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    @Override
    public String toString() {
        return String.format("%d, %s, %d", crn, name, credits);
    }

    @Override
    public int getKey() {
        return crn;
    }

    @Override
    public boolean sameKey(int k) {
        return k == crn;
    }

}
