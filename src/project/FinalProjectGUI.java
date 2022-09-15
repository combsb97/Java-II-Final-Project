/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
//import javax.swing.border.*;
//import java.awt.event.*;

/**
 *
 * @author Ben
 */
public final class FinalProjectGUI extends JFrame {

    final private int WINDOW_HEIGHT = 300;
    final private int WINDOW_WIDTH = 400;
    final private String TITLE = "MCC Record System";
    final private String WELCOME_MSG = "Welcome to MCC's Record System";
    final private String INSTRUCTION = "Please choose from the following options:";
    final private String OPTION_ONE = "Upload Records from File";
    final private String OPTION_TWO = "Add New Student";
    final private String OPTION_THREE = "Download Statistics";
    final private String OPTION_FOUR = "Find Information";
    final private String OPTION_FIVE = "Exit Program";

    private Project project;
    private JButton btnOne, btnTwo, btnThree, btnFour, btnFive;
    private JLabel welcomeMsg, instruction;

    public FinalProjectGUI(Project project) {
        this.project = project;
        setTitle(TITLE);
        setSize(WINDOW_HEIGHT, WINDOW_WIDTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildMainPanel();
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void buildMainPanel() {
        setLayout(new BorderLayout()); // Set overall layout of JFrame

        JPanel mainPanel = new JPanel(); // welcome message panel
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 20));
        mainPanel.setLayout(new GridLayout(2, 1));

        JPanel btnContainer = new JPanel();
        btnContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel btnPanel = new JPanel(); // option buttons panel

        btnPanel.setLayout(new GridLayout(2, 3));

        JPanel footPanel = new JPanel(); // create a footer panel

        // Create label with welcome message
        welcomeMsg = new JLabel(WELCOME_MSG);
        welcomeMsg.setFont(new Font("Serif", Font.BOLD, 20));
        instruction = new JLabel(INSTRUCTION);
        // Add label to main panel group
        mainPanel.add(welcomeMsg);
        mainPanel.add(instruction);

        // Create buttons for options
        btnOne = new JButton(OPTION_ONE);
        btnOne.addActionListener(new MainButtonHandler());
        btnTwo = new JButton(OPTION_TWO);
        btnTwo.addActionListener(new MainButtonHandler());
        btnThree = new JButton(OPTION_THREE);
        btnThree.addActionListener(new MainButtonHandler());
        btnFour = new JButton(OPTION_FOUR);
        btnFour.addActionListener(new MainButtonHandler());
        btnFive = new JButton(OPTION_FIVE);
        btnFive.addActionListener(new MainButtonHandler());

        // Add label to button panel group
        btnPanel.add(btnOne);
        btnPanel.add(btnTwo);
        btnPanel.add(btnThree);
        btnPanel.add(btnFour);
        btnPanel.add(btnFive);
        btnContainer.add(btnPanel);

        //add panel groups to main JFrame
        add(mainPanel, BorderLayout.NORTH);
        add(btnContainer, BorderLayout.CENTER);
        add(footPanel, BorderLayout.SOUTH);

    }

    private class MainButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();

            if (cmd.equals(OPTION_ONE)) {
                UploadRecordsGUI w1 = new UploadRecordsGUI(project);
            }
            if (cmd.equals(OPTION_TWO)) {
                AddStudentGUI w2 = new AddStudentGUI(project);
            }
            if (cmd.equals(OPTION_THREE)) {
                DownloadStatisticsGUI w3 = new DownloadStatisticsGUI(project);
            }
            if (cmd.equals(OPTION_FOUR)) {
                FindInfoGUI w4 = new FindInfoGUI(project);
            }
            if (cmd.equals(OPTION_FIVE)) {
                System.exit(0);
            }
        }

    }
}