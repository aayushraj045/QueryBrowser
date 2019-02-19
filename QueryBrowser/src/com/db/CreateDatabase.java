package com.db;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;
public class CreateDatabase extends JFrame
{
	private Connection cn;
	private JLabel l1,l2;
	private JTextField txt1;
	private JButton cmd1,cmd2;
	private void initQuery()
	{
		try
		{
		String str1="CREATE DATABASE "+ txt1.getText();
		JOptionPane.showMessageDialog(null,str1);
		PreparedStatement ps=cn.prepareStatement(str1);
         int a=ps.executeUpdate();
		}	
			catch(Exception e)	
			{
				JOptionPane.showMessageDialog(null,e.getMessage());
			}
		
	}
	 private void initComponent()
	 {
	 	 l1=new JLabel("Please Enter a Name For THe New Schema ");
	 	 l2=new JLabel("Enter New Schema");
	 	 txt1=new JTextField(10);
	 	 cmd1=new JButton("O.K");
	 	 cmd1.addActionListener(new ActionListener()
		{
            @Override
			public void actionPerformed(ActionEvent ae)
			{
				initQuery();
			}
			
		});
	
	 	 cmd2=new JButton("Discard");
	 	 cmd2.addActionListener(new ActionListener()
		{
            @Override
			public void actionPerformed(ActionEvent ae)
			{
			    CreateDatabase.this.setVisible(false);
			}
			
		});
	 	 
	 	 JPanel p1=new JPanel(new GridLayout(1,1));
	 	 p1.setBorder(BorderFactory.createTitledBorder(""));
	 	 p1.add(l1);
		
		this.getContentPane().add(p1,BorderLayout.NORTH);
	 	 
	 	JPanel p2=new JPanel(new GridLayout(2,2,5,5));
	 	p2.setBorder(BorderFactory.createTitledBorder(""));
	 	p2.add(l2);
		p2.add(txt1);
		p2.add(cmd1);
		p2.add(cmd2);
		this.getContentPane().add(p2,BorderLayout.SOUTH);
	  	
	 }
	 public CreateDatabase(Connection con)
	  {
	  	  	cn=con;
	  	  	this.initComponent();
			this.setTitle("Create Database");
			this.setVisible(true);
			this.setLocation(300,300);
		    Helper.setPosition(this);
          this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          }
}
