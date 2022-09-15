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
public class FindInfoGUI extends JFrame {

    private Project project;
    private final String TITLE = "Find Information";
    private final String FIND = "Find";
    private final String CANCEL = "Cancel";
    private final String KEY = "Key:";

    private JLabel key;
    private JTextField keyTxt;
    private JButton addBtn, cancelBtn;

    public FindInfoGUI(Project project) {
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

        // Panel for search key input
        JPanel keyPanel = new JPanel();
        keyPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 20, 20, 20),
                BorderFactory.createTitledBorder("Search")));

        key = new JLabel(KEY, SwingConstants.CENTER);
        keyTxt = new JTextField(15);

        addBtn = new JButton(FIND);
        addBtn.addActionListener(new ButtonHandler());
        cancelBtn = new JButton(CANCEL);
        cancelBtn.addActionListener(new ButtonHandler());

        keyPanel.add(key);
        keyPanel.add(keyTxt);
        keyPanel.add(addBtn);
        keyPanel.add(cancelBtn);

        add(keyPanel, BorderLayout.NORTH);

    }

    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            if (cmd.equals(FIND)) {
                try {
                    int key = Integer.parseInt(keyTxt.getText());
                    //System.out.println(project.findKey(key).toString());
                    JOptionPane.showMessageDialog(null, project.findKey(key).toString(), "Key Search Results", -1);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Key: " + keyTxt.getText(), "Error", -1);
                }
            }

            if (cmd.equals(CANCEL)) {
                dispose();
            }
        }

    }
}

class SearchResultGUI extends JFrame {

    private final String TITLE = "Search Results";

    SearchResultGUI() {
        setTitle(TITLE);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        buildPanel();
        pack();
        setResizable(false);
        setVisible(true);
    }

    public void buildPanel() {
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 20, 20, 20),
                BorderFactory.createTitledBorder("Search")));
    }

}
