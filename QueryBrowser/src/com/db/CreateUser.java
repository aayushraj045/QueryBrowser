package com.db;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
public class CreateUser extends JFrame
{
	private Connection cn;
	private JLabel l1,l2,l3;
	private JButton cmd1;
	private JTextField txt1;
	private JPasswordField jpf;
    private DataComboBox cbo;
    
    private void initComboBox()
    {
    	
    	cbo=new DataComboBox();
    	try
    	{
    		ResultSet rs=cn.prepareStatement("Show databases").executeQuery();
    		while(rs.next())
    		{
    			cbo.addItem(rs.getString(1));
    		}
			
    	}
    	catch(Exception e){}
    	
     }
   
private void initComponent()
	{   
		this.initComboBox();	
		this.cmdButton();
		l1=new JLabel("DATABASE ");	
		l2=new JLabel("User Name");
		l3=new JLabel("Password");
		
		txt1=new JTextField(10);
		jpf=new JPasswordField(10);
		JPanel p1=new JPanel(new GridLayout(3,2,5,5));
		p1.setBorder(BorderFactory.createTitledBorder(""));
		p1.add(l1);
		p1.add(cbo);
		p1.add(l2);
		p1.add(txt1);
		p1.add(l3);
		p1.add(jpf);
		
		JPanel p2=new JPanel();
		p1.setBorder(BorderFactory.createTitledBorder(""));
		p2.add(cmd1);
		this.getContentPane().add(p1,BorderLayout.NORTH);
		this.getContentPane().add(p2,BorderLayout.SOUTH);
		
	}
		
	private void cmdButton()
	{
		
		cmd1=new JButton("Create user");
		cmd1.addActionListener(new ActionListener()
		{
            @Override
			public void actionPerformed(ActionEvent ae)
			{ 
				 try
				 {
				 
			   String sql = " GRANT ALL PRIVILEGES ON "+cbo.getSelectedItem().toString()+".*"+" TO "+txt1.getText()+"@localhost IDENTIFIED BY \""+jpf.getText()+"\"";
			   //String sql = " CREATE USER "+txt1.getText()+"@localhost IDENTIFIED BY \""+jpf.getText()+"\"";
			  	PreparedStatement ps=cn.prepareStatement(sql);
			    int a=ps.executeUpdate();
			    JOptionPane.showMessageDialog(null,sql);
			    JOptionPane.showMessageDialog(null," USER SUCCESSFULLY CREATED");
				 }
				 catch(Exception e)
				 {
				 	JOptionPane.showMessageDialog(null,e.getMessage());
				 }
              }
			});
		}		
	public CreateUser(Connection con)
	{ 	
		cn=con;
		this.initComponent();
		this.setTitle("Create User");
		this.setSize(200,200);
	   	this.setVisible(true);
		Helper.setPosition(this);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
}
 
