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
public class DownloadStatisticsGUI extends JFrame {

    private Project project;
    private final String TITLE = "Download Statistics";
    private final String DOWNLOAD = "Download";
    private final String CANCEL = "Cancel";
    private final String PATH = "File path: ";

    private JButton uploadBtn, cancelBtn;
    private JLabel pathLabel;
    private JTextField pathTxt;

    public DownloadStatisticsGUI(Project project) {
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
     * buildPanel() is used to add all necessary elements to the
     * optionThreeWindow Object
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

        uploadBtn = new JButton(DOWNLOAD);
        uploadBtn.addActionListener(new ButtonHandler());
        cancelBtn = new JButton(CANCEL);
        cancelBtn.addActionListener(new ButtonHandler());

        inputPanel.add(pathLabel);
        inputPanel.add(pathTxt);

        btnPanel.add(uploadBtn);
        btnPanel.add(cancelBtn);

        mainPanel.add(inputPanel);
        mainPanel.add(btnPanel);

        add(mainPanel);
    }

    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();

            if (cmd.equals(DOWNLOAD)) {
                try {
                    project.outputData(pathTxt.getText());
                    pathTxt.setText("");
                    JOptionPane.showMessageDialog(null,"Download Successful", "Download Records Message", 1);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage(), "Error", 1);
                }
            }

            if (cmd.equals(CANCEL)) {
                dispose();
            }
        }

    }
}
