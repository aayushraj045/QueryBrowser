package com.db;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.*;

public class StartUpPage extends JFrame {

    private JButton cmd1 = new JButton("CANCEL");
    private JButton cmd2 = new JButton("PROCEED");
    private JComboBox com1 = new JComboBox();
    private JPanel p1, p2;

    private void combobox() {
        com1.addItem("NONE");
        com1.addItem("MYSQL");
        com1.addItem("ORACLE");
    }

    private void label() {
        ImageIcon img = new ImageIcon(getClass().getResource("selectdatabase.jpg"));
        JLabel l1 = new JLabel(img);

        p2.add(l1);
    }

    private void Button() {



        cmd1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                StartUpPage.this.setVisible(false);
            }
        });

        this.getRootPane().
                setDefaultButton(cmd2);
        cmd2.addActionListener(new ActionListener() {
           private Connection cn;

            @Override
            public void actionPerformed(ActionEvent ae) {
                String db = com1.getSelectedItem().toString().toUpperCase();
                if (db.equals("NONE")) {
                    StartUpPage.this.setVisible(false);
                }
                if (db.equals("MYSQL")) {
                    MySqlQueryBrowser p = new MySqlQueryBrowser(cn);
//MySqlLoginForm p=new MySqlLoginForm();
                //    p.pack();
                    StartUpPage.this.setVisible(false);
                }


                if (db.equals("ORACLE")) {

                    OracleBrowserForm f= new OracleBrowserForm();


                    StartUpPage.this.setVisible(false);
                }

            }
        });
    }

    private void Component() {



        p1 = new JPanel(new GridLayout(1, 4, 5, 5));
        p1.setBorder(BorderFactory.createTitledBorder("Select Database"));
        p1.add(new JLabel("SELECT DATABASE"));
        p1.add(com1);
        p1.add(cmd1);
        p1.add(cmd2);
        combobox();
        this.getContentPane().add(p1, BorderLayout.SOUTH);
        p2 = new JPanel();
        p2.setBorder(BorderFactory.createTitledBorder(""));
        label();

        this.getContentPane().add(p2, BorderLayout.NORTH);
        Button();

    }

    public StartUpPage() {

        this.Component();
        this.setVisible(true);
        this.setSize(500, 500);
        this.setLocation(250, 150);



    }

    public static void main(String[] args) {
        try {
            StartUpPage p = new StartUpPage();
            SwingUtilities.updateComponentTreeUI(p);
            p.pack();
        } catch (Exception ex) {
        }
    }
}
