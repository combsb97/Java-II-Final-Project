/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author Ben
 */
public class AddStudentGUI extends JFrame {

    private Project project;
    private final String TITLE = "Add New Student";
    private final String FNAME = "First Name:";
    private final String LNAME = "Last Name:";
    private final String PASSWORD = "Password:";
    private final String CONFIRM_PASS = "Confirm Password:";
    private final String FULL_TIME = "Full Time";
    private final String PART_TIME = "Part Time";
    private final String NO_MAJOR = "No Major";
    private final String ADD_STUDENT = "Add Student";
    private final String CANCEL = "Cancel";
    private final String BALANCE = "Balance:";
    private final String MAJOR = "Major:";

    private JLabel fNamel, lNamel, passl, confPassl, ball, majorl;
    private JTextField fNameTxt, lNameTxt, passTxt, confPassTxt, balTxt, majorTxt;
    private JRadioButton rFull, rPart, rNoMajor;
    private JButton addBtn, cancelBtn;
    private ButtonGroup radioGrp;

    private String lName, fName, pass, confPass, stype, major;
    private float bal;

    public AddStudentGUI(Project project) {
        this.project = project;
        setTitle(TITLE);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        buildPanel();
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void buildPanel() {
        setLayout(new BorderLayout()); // Set overall layout of JFrame

        // Panel to contain all input text fields
        JPanel inputContainer = new JPanel();
        inputContainer.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 10, 20),
                BorderFactory.createTitledBorder("Enter Student Info")));
        inputContainer.setLayout(new GridLayout(2, 2));

        // Panel for name input
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new GridLayout(2, 2));

        // Panel for password input
        JPanel passPanel = new JPanel();
        passPanel.setLayout(new GridLayout(2, 2));

        // Panel for student type selection
        JPanel radioPanel = new JPanel();
        radioPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 20, 20, 20),
                BorderFactory.createTitledBorder("Select Student Type")));
        radioPanel.setLayout(new GridLayout(3, 1));

        // Panel for major
        JPanel majorPanel = new JPanel();
        majorPanel.setLayout(new GridLayout(2, 2));

        // Panel for balance password input
        JPanel balPanel = new JPanel();
        balPanel.setLayout(new GridLayout(2, 2));

        // Panel for buttons
        JPanel btnPanel = new JPanel();
        btnPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        fNamel = new JLabel(FNAME, SwingConstants.CENTER);
        lNamel = new JLabel(LNAME, SwingConstants.CENTER);
        passl = new JLabel(PASSWORD, SwingConstants.CENTER);
        confPassl = new JLabel(CONFIRM_PASS, SwingConstants.CENTER);
        majorl = new JLabel(MAJOR, SwingConstants.CENTER);
        ball = new JLabel(BALANCE, SwingConstants.CENTER);

        fNameTxt = new JTextField(15);
        lNameTxt = new JTextField(15);
        passTxt = new JTextField(15);
        confPassTxt = new JTextField(15);
        majorTxt = new JTextField(15);
        balTxt = new JTextField(15);

        radioGrp = new ButtonGroup();
        rFull = new JRadioButton(FULL_TIME);
        rFull.addActionListener(new RButtonHandler());
        rPart = new JRadioButton(PART_TIME);
        rPart.addActionListener(new RButtonHandler());
        rNoMajor = new JRadioButton(NO_MAJOR);
        rNoMajor.addActionListener(new RButtonHandler());
        radioGrp.add(rFull);
        radioGrp.add(rPart);
        radioGrp.add(rNoMajor);

        addBtn = new JButton(ADD_STUDENT);
        addBtn.addActionListener(new ButtonHandler());
        cancelBtn = new JButton(CANCEL);
        cancelBtn.addActionListener(new ButtonHandler());

        namePanel.add(lNamel);
        namePanel.add(lNameTxt);

        namePanel.add(fNamel);
        namePanel.add(fNameTxt);

        passPanel.add(passl);
        passPanel.add(passTxt);

        passPanel.add(confPassl);
        passPanel.add(confPassTxt);

        majorPanel.add(majorl);
        majorPanel.add(majorTxt);

        majorPanel.add(ball);
        majorPanel.add(balTxt);

        inputContainer.add(namePanel);
        inputContainer.add(passPanel);
        inputContainer.add(majorPanel);
        inputContainer.add(balPanel);

        radioPanel.add(rFull);
        radioPanel.add(rPart);
        radioPanel.add(rNoMajor);

        btnPanel.add(addBtn);
        btnPanel.add(cancelBtn);

        add(inputContainer, BorderLayout.NORTH);
        add(radioPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

    }

    /**
     * this creates and Student Object if all criteria are met and add the
     * Student to the project data ArrayList.
     */
    public void addStudent() {
        try {
            passTxt.setBackground(Color.white);

            //set lastName if lastName textfield contains a String, else set
            //background of textfield to red
            if (!lNameTxt.getText().isBlank() || !lNameTxt.getText().isEmpty()) {
                lNameTxt.setBackground(Color.white);
                lName = lNameTxt.getText();
            } else {
                lNameTxt.setBackground(Color.red);
                JOptionPane.showMessageDialog(null, "Last Name Required", "Error", 1);
            }

            //set firstName if firstName textfield contains a String, else set
            //background of textfield to red
            if (!fNameTxt.getText().isBlank() || !fNameTxt.getText().isEmpty()) {
                fNameTxt.setBackground(Color.white);
                fName = fNameTxt.getText();
            } else {
                fNameTxt.setBackground(Color.red);
                JOptionPane.showMessageDialog(null, "First Name Required", "Error", 1);
            }

            //set password if password and confirm password are equal, else set
            //background of textfield to red
            if (passTxt.getText().equals(confPassTxt.getText())
                    && !passTxt.getText().isBlank() && !confPassTxt.getText().isBlank()
                    && !passTxt.getText().isEmpty() && !confPassTxt.getText().isEmpty()) {
                pass = passTxt.getText();
            } else {
                passTxt.setBackground(Color.red);
                JOptionPane.showMessageDialog(null, "Passwords Do Not Match", "Error", 1);
            }

            //set major
            if (rNoMajor.isSelected() && (!majorTxt.getText().isBlank() || !majorTxt.getText().isEmpty())) {
                majorTxt.setBackground(Color.red);
                throw new Exception("Student Type: No Major Cannot Have Declared Major");
            } else if((rFull.isSelected() || rPart.isSelected()) && (majorTxt.getText().isBlank() || majorTxt.getText().isEmpty())){
                majorTxt.setBackground(Color.red);
                throw new Exception(String.format("Student Type: %s, Major Must Be Declared",stype));
            } else {
                majorTxt.setBackground(Color.white);
                major = majorTxt.getText();
            }

            //set balance if balance textfield contains a valid number, else set
            //background of textfield to red
            //try {
            if (balTxt.getText().isBlank() || balTxt.getText().isEmpty()) {
                balTxt.setBackground(Color.red);
                throw new Exception("Invalid Balance");
            } else {
                balTxt.setBackground(Color.white);
                bal = Float.parseFloat(balTxt.getText());
            }
            //} catch (NumberFormatException e) {
            //    ball.setBackground(Color.red);
            //JOptionPane.showMessageDialog(null, "Invalid Balance", "Error", 1);
            //}

            // try to create student object from input and add to the project data  
            // ArrayList. Catch and output error
            //try {
            //create student object
            Student student = new Student(lName, fName, pass, stype, major, bal);
            // call project.addStudent and pass student through
            project.addStudent(student);
            // Success message displaying email and bannerid generated for new student
            JOptionPane.showMessageDialog(null,
                    String.format("Student Added Successfully\n Email: %s BannerId: %d",
                            student.getEmail(), student.getBannerId()), "Add Student", -1);
            //clear fields
            clearForm();
        } catch (InvalidPasswordException e) {
            passTxt.setBackground(Color.red);
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 1);
        } catch (NullPointerException e) {
            // Do nothing. null password is passed through and caught by InvalidPasswordException
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 1);
        }

    }

    /**
     * this method sets all the text in the textfields to blank. call if
     * project.addStudent() is successful
     */
    public void clearForm() {
        fNameTxt.setText("");
        lNameTxt.setText("");
        passTxt.setText("");
        confPassTxt.setText("");
        majorTxt.setText("");
        balTxt.setText("");
    }

    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();

            if (cmd.equals(ADD_STUDENT)) {
                addStudent();
            }

            if (cmd.equals(CANCEL)) {
                dispose();
            }
        }

    }

    private class RButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();

            if (cmd.equals(FULL_TIME)) {
                stype = "Full Time";
            } else if (cmd.equals(PART_TIME)) {
                stype = "Part Time";
            } else if (cmd.equals(NO_MAJOR)) {
                stype = "NoMajor";
            }

        }

    }
}
