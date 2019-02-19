package com.db;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

public class OracleQuerybrowserForm extends JFrame {

    protected Connection cn;
    private JButton cmd1, cmd2, cmd3, cmd4, cmd5, b1, cmd6, cmd7;
    private JTextArea txtr1;
    private JToolBar tlb;
    private JTree tree1, tree2;
    private DefaultMutableTreeNode root1, root2;
    private JTabbedPane tab1;
    private JPopupMenu Pmenu;
    private JMenuItem menuItem;
    private JMenuBar bar;
    private JMenu Table, Advanced, Edit, View, Administrator;
    private JMenuItem table1, table2, table3, table4, table5;
    private JMenuItem adv1;
    private JMenuItem edit1, edit2;
    private JMenuItem view1, view2;
    private JMenuItem admin1, admin2;
    private DataTable table;

    private void PopUpMenu() {

        Pmenu = new JPopupMenu();
        menuItem = new JMenuItem("Create new Table");
        Pmenu.add(menuItem);
        menuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                CreateTable c = new CreateTable(cn);
                c.pack();
                setTree();
            }
        });

        menuItem = new JMenuItem("Insert Row");
        Pmenu.add(menuItem);
        menuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String name = tree1.getLastSelectedPathComponent().toString();
                Helper.insertRow(cn, table, name);

            }
        });
        menuItem = new JMenuItem("Rename Table");
        Pmenu.add(menuItem);
        menuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = tree1.getLastSelectedPathComponent().toString().trim();
                RenameOracleTable r = new RenameOracleTable(cn, name);
                r.pack();
                

            }
        });

        menuItem = new JMenuItem("Drop Table");
        Pmenu.add(menuItem);
        menuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                droptable();
                setTree();
            }
        });

        menuItem = new JMenuItem("Refresh");
        Pmenu.add(menuItem);
        menuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                setTree();
                txtr1.setText("");
            }
        });

    }

    public void MenuContainer() {
        table1 = new JMenuItem("Open Table");
        table1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
   
                String name = tree1.getLastSelectedPathComponent().toString();
                
                TreeNode node = (TreeNode) tree1.getLastSelectedPathComponent();
                if (node.isLeaf()) {
                    JOptionPane.showMessageDialog(null, "Click OK to open" +" "+ name+" " + "Table");
                    table.setData(cn, " SELECT * FROM " + name);
                }
            
                }
        });

        table2 = new JMenuItem("Rename Table");
        table2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                RenameOracleTable r = new RenameOracleTable(cn, null);
                r.pack();
                }
        });

        table3 = new JMenuItem("Create Table");
        table3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                CreateTable c = new CreateTable(cn);
                c.pack();

            }
        });

        table4 = new JMenuItem("Drop Table");
        table4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                droptable();
                setTree();
            }
        });


        table5 = new JMenuItem("Create using Another Table");
        table5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                CreateTable c = new CreateTable(cn);
                c.pack();
            }
        });



        adv1 = new JMenuItem("Print");
        adv1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (table.getRowCount() > 0) {
                    Helper.printTable(table);
                }
            }
        });

        edit1 = new JMenuItem("Insert Record");
        edit1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                initInsertValues();
            }
        });
        edit2 = new JMenuItem("Delete Record");
        edit2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    TreeNode node = (TreeNode) tree1.getLastSelectedPathComponent();
                    String sql = "DELETE FROM " + node.getParent().toString() + " WHERE " + node + "=" + JOptionPane.showInputDialog("Enter Value For : " + node);
                    JOptionPane.showMessageDialog(null, sql);
                    PreparedStatement ps = cn.prepareStatement(sql);

                    int a = ps.executeUpdate();
                    if (a > 0) {
                        table.setData(cn, "Select * from " + node.getParent().toString());
                    }
                } catch (Exception e) {
                }

            }
        });

        view1 = new JMenuItem("Create View");
        view1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String name = tree1.getLastSelectedPathComponent().toString().trim();
                CreateView e = new CreateView(cn, name);
                e.pack();

            }
        });

        view2 = new JMenuItem("Drop View");
        view2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String name = tree1.getLastSelectedPathComponent().toString().trim();
                DropView d = new DropView(cn, name);
                d.pack();

            }
        });

        admin1 = new JMenuItem("Create User");
        admin1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                
                CreateUser c = new CreateUser(cn);
                c.pack();
            }
        });
        admin2 = new JMenuItem("Drop User");
        admin2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                String name = tree1.getLastSelectedPathComponent().toString().trim();
               // DropUser e=new DropUser(cn,name);
               // e.pack();

            }
        });

        Table = new JMenu("Table");
        Advanced = new JMenu("Advanced");
        Edit = new JMenu("Edit");
        View = new JMenu("View");
        Administrator = new JMenu("Administrator");

        Table.add(table1);
        Table.add(table2);
        Table.add(table3);
        Table.add(table4);
        Table.add(table5);
        Advanced.add(adv1);
        Edit.add(edit1);
        Edit.add(edit2);
        View.add(view1);
        View.add(view2);
        Administrator.add(admin1);
        Administrator.add(admin2);



        bar = new JMenuBar();
        bar.add(Table);
        bar.add(Advanced);
        bar.add(Edit);
        bar.add(View);
        bar.add(Administrator);
        this.setJMenuBar(bar);
    }

    private void initTree() {
        try {

            root1 = new DefaultMutableTreeNode("---------" + cn.getMetaData() + "---------");
            this.setTree();
            root2 = new DefaultMutableTreeNode("History");

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }


        tree1 = new JTree(root1);
        tree1.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent me) {
                if (me.isPopupTrigger()) {
                    Pmenu.show(me.getComponent(), me.getX(), me.getY());
                }
            }
        });

        tree2 = new JTree(root2);

        tab1 = new JTabbedPane();
        tab1.add("Schemata", new JScrollPane(tree1));
        tab1.add("History", new JScrollPane(tree2));

        JPanel p1 = new JPanel(new GridLayout(1, 1));
        p1.add(tab1);


        this.getContentPane().add(p1, BorderLayout.EAST);
    }

    private void initToolbar() {

        tlb = new JToolBar();

        txtr1 = new JTextArea();
        cmd1 = new JButton(new ImageIcon(getClass().getResource("left.jpg")));
        cmd1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                OracleBrowserForm q = new OracleBrowserForm();

                OracleQuerybrowserForm.this.setVisible(false);

            }
        });
        cmd2 = new JButton(new ImageIcon(getClass().getResource("right.jpg")));
        cmd3 = new JButton(new ImageIcon(getClass().getResource("refresh.png")));
        cmd3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                txtr1.setText("");  		
      		     setTree(); 
            }
        });
        
        cmd4 = new JButton(new ImageIcon(getClass().getResource("success.png")));
        cmd5 = new JButton(new ImageIcon(getClass().getResource("cancel.png")));
        cmd5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                OracleQuerybrowserForm.this.setVisible(false);
            }
        });
        cmd4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    if (txtr1.getText().length() > 0) {
                        String sql = txtr1.getText().toUpperCase();
                        if (sql.startsWith("SELECT") || sql.startsWith("DESC") || sql.startsWith("SHOW")) {
                            table.setData(cn, sql);
                        } else {
                            PreparedStatement ps = cn.prepareStatement(sql);
                            int a = ps.executeUpdate();
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        });
        tlb.add(cmd1);
        tlb.add(cmd2);
        tlb.add(cmd3);
        tlb.add(txtr1);
        tlb.add(cmd4);
        tlb.add(cmd5);

        this.getContentPane().add(tlb, BorderLayout.NORTH);
    }

    private void initTable() {

        table = new DataTable();
        JPanel p1 = new JPanel(new GridLayout(1, 1, 5, 5));
        p1.setBorder(BorderFactory.createTitledBorder("Result Set"));
        p1.add(new JScrollPane(table));
        this.getContentPane().add(p1, BorderLayout.CENTER);

    }

    private void initcomponents() {
        this.MenuContainer();
        this.initTree();
        PopUpMenu();
        this.initTable();
        cmd6 = new JButton("APPLY INSERT");
        cmd6.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                initInsertValues();
            }
        });



        cmd7 = new JButton("APPLY DELETE");
        cmd7.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {

                    TreeNode node = (TreeNode) tree1.getLastSelectedPathComponent();
                    
                    String sql = "DELETE FROM " + node.getParent().toString() + " WHERE " + node + "=" + JOptionPane.showInputDialog("Enter Value For : " + node);
                    JOptionPane.showMessageDialog(null, sql);
                    PreparedStatement ps = cn.prepareStatement(sql);

                    int a = ps.executeUpdate();
                    if (a > 0) {
                        table.setData(cn, "Select * from " + node.getParent().toString());
                    }
                } catch (Exception e) {
                }

            }
        });

        JPanel p2 = new JPanel(new GridLayout(1, 2, 5, 5));
        p2.setBorder(BorderFactory.createTitledBorder(""));
        p2.add(cmd6);
        p2.add(cmd7);

        this.getContentPane().add(p2, BorderLayout.SOUTH);
    }

    private void droptable() {
        try {

            String name = tree1.getLastSelectedPathComponent().toString();
            TreeNode node = (TreeNode) tree1.getLastSelectedPathComponent();
            if (!node.isLeaf()) {
                if (table.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, name);
                    String sql = " DROP TABLE " + name;
                    PreparedStatement ps = cn.prepareStatement(sql);
                    int a = ps.executeUpdate();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public OracleQuerybrowserForm(Connection con) {

        cn = con;

        this.initToolbar();
        this.initcomponents();
        this.setTitle("Oracle Query Browser.....");
        this.setVisible(true);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void initInsertValues() {
        try {

            Vector v = new Vector();
            String name = tree1.getLastSelectedPathComponent().toString();
            int count = table.getColumnCount();

            String str = "INSERT INTO " + name + " VALUES (";

            for (int i = 0; i < table.getColumnCount() - 1; i++) {
                str += "?,";
                v.addElement(table.getValueAt(table.getSelectedRow(), i).toString());
            }
            str += "?)";
            v.addElement(table.getValueAt(table.getSelectedRow(), count - 1).toString());
            JOptionPane.showMessageDialog(null, str);
            PreparedStatement ps = cn.prepareStatement(str);
            for (int k = 1; k <= count; k++) {
                ps.setString(k, (String) v.get(k - 1));
            }
            int a = ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    private void setTree() {
        root1.removeAllChildren();
        java.util.Iterator itr = Helper.getOracleTables(cn);
        while (itr.hasNext()) {
            String n = (String) itr.next();
            DefaultMutableTreeNode temp = new DefaultMutableTreeNode(n);
            setFields(temp, n);
            root1.add(temp);
        }
        tree1.updateUI();
    }

    private void setFields(DefaultMutableTreeNode parent, String tableName) {
        try {
            PreparedStatement ps = cn.prepareStatement("SELECT * FROM TAB  " + tableName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                parent.add(new DefaultMutableTreeNode(rs.getString(1)));
            }
        } catch (Exception e) {
        }
    }
}
