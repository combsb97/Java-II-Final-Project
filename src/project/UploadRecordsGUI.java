/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ben
 */
public class UploadRecordsGUI extends JFrame {

    private final String TITLE = "Upload Records";
    private final String UPLOAD = "Upload";
    private final String CANCEL = "Cancel";
    private final String PATH = "File path: ";

    private Project project;
    private JButton uploadBtn, cancelBtn;
    private JLabel pathLabel;
    private JTextField pathTxt;

    public UploadRecordsGUI(Project project) {
        this.project = project;
        setTitle(TITLE);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        buildPanel();
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * buildPanel() is used to add all necessary elements to the optionOneWindow
     * Object
     */
    public void buildPanel() {

        JPanel mainPanel = new JPanel(); // Main panel containg label and text field
        mainPanel.setLayout(new GridLayout(2, 1));

        // Panel for input of file path
        JPanel inputPanel = new JPanel();

        // Panel for buttons
        JPanel btnPanel = new JPanel();
        btnPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        pathLabel = new JLabel(PATH);
        pathTxt = new JTextField(32);

        uploadBtn = new JButton(UPLOAD);
        uploadBtn.addActionListener(new MainButtonHandler());
        cancelBtn = new JButton(CANCEL);
        cancelBtn.addActionListener(new MainButtonHandler());

        inputPanel.add(pathLabel);
        inputPanel.add(pathTxt);

        btnPanel.add(uploadBtn);
        btnPanel.add(cancelBtn);

        mainPanel.add(inputPanel);
        mainPanel.add(btnPanel);

        add(mainPanel);
    }

    /**
     * this implements ActionListener and is used by the buttons on the
     * UploadRecordsGUI called. It has a constructor that accepts an
     * UploadRecordGui Object in order to allow the cancel button to dispose the
     * JFrame on click
     */
    private class MainButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();

            if (cmd.equals(UPLOAD)) {
                try {
                    project.getInputData(pathTxt.getText());
                    pathTxt.setText("");
                    JOptionPane.showMessageDialog(null, "Valid Student Records Uploaded Successfully", "Record Upload", -1);
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "File Not Found", "Key Search Results", 1);
                }
            }
            if (cmd.equals(CANCEL)) {
                dispose();
            }
        }

    }
}
