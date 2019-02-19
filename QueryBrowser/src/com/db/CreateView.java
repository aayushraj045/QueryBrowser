package com.db;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;
public class CreateView extends JFrame
{
	private Connection cn;
	private JLabel l1;
	private JButton cmd1,cmd2;
	private JTextField txt1;
	 private String tablename;
	private void cmdButton()
	{
		
		cmd1=new JButton("Create View");
    	cmd1.addActionListener(new ActionListener()
		{
            @Override
			public void actionPerformed(ActionEvent ae)
			{	
				
				try
				{
								
                 	String sql=" CREATE VIEW "+txt1.getText().toString()+" AS "+"SELECT * FROM "+tablename;
                 	PreparedStatement ps=cn.prepareStatement(sql);
                 	ps.executeUpdate();
                 	JOptionPane.showMessageDialog(null,sql);
                 	JOptionPane.showMessageDialog(null," VIEW SUCCESSFULLY CREATED ");
			   }	
			  	catch(Exception e)
				{
					JOptionPane.showMessageDialog(CreateView.this,e.getMessage());
				}
			}
				
		 });
		
		cmd2=new JButton(" cancel");
		cmd2.addActionListener(new ActionListener()
		{
            @Override
			public void actionPerformed(ActionEvent ae)
			{			
											
               CreateView.this.setVisible(false);
			}
				
		});
	}
	
	private void initComponent()
	{   
		txt1=new JTextField(10);		
		this.cmdButton();
		l1=new JLabel("ENTER THE VIEW U YOU WANT TO CREATE ");	
		
		JPanel p1=new JPanel(new GridLayout(1,2,5,5));
		p1.setBorder(BorderFactory.createTitledBorder(""));
		p1.add(l1);
		p1.add(txt1);
	
		JPanel p2=new JPanel(new GridLayout(1,2,5,5));
		p1.setBorder(BorderFactory.createTitledBorder(""));
		p2.add(cmd1);
		p2.add(cmd2);
		this.getContentPane().add(p1,BorderLayout.NORTH);
		this.getContentPane().add(p2,BorderLayout.SOUTH);
		
	}
	public CreateView(Connection con,String str)
	{ 	
		cn=con;
		this.tablename=str;
		this.initComponent();
		this.setTitle("Create View");
		this.setSize(200,200);
	   	this.setVisible(true);
		Helper.setPosition(this);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
}	
