package com.db;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;
public class RenameTable extends JFrame
{
	private Connection cn;
	private JLabel l1,l2;
	private JTextField txt1;
	private JButton cmd1,cmd2;
	private DataComboBox cbo;
    private void initLabel()
	{
		
		l1=new JLabel(" OLD TABLE NAME");
		l2=new JLabel(" NEW TABLE NAME");
	}
	private void initTextBox()
	{
		txt1=new JTextField(10);
	    
	}
	private void initComboBox()
	{
		cbo=new DataComboBox();
		cbo.setData(cn,"Show Tables",true);
			
	}
	private void initCmdButton()
	{
	 cmd1=new JButton("Rename");
	 cmd1.addActionListener(new ActionListener()
	{
            @Override
		public void actionPerformed(ActionEvent ae)
			{
			   initQuery();
			}
			
    	});
	 cmd2=new JButton("Cancel");	
	 cmd2.addActionListener(new ActionListener()
		{
            @Override
			public void actionPerformed(ActionEvent ae)
			{
			   clear();
			}
			
		});
	}
	private void initQuery()
	{
		try
		{
		 
		    
			String str1="ALTER TABLE "+ cbo.getBackValue()+" RENAME TO "+txt1.getText().toString();
			PreparedStatement ps=cn.prepareStatement(str1);
			ps.executeUpdate();
		  	JOptionPane.showMessageDialog(null,"Successfully Renamed");
			
		}
		catch(Exception e)
			{	
				JOptionPane.showMessageDialog(null,e.getMessage());
			}
					
		
	}
	private void clear()
	{
	  txt1.setText("");
		
	}
	private void initComponent()
	{
		this.initLabel();
 	
 		this.initCmdButton();
 		this.initTextBox();
 		 this.initComboBox();		
		JPanel p1=new JPanel(new GridLayout(2,2,5,5));
		p1.setBorder(BorderFactory.createTitledBorder(""));
		p1.add(l1);
		p1.add(cbo);
		p1.add(l2);
		p1.add(txt1);
		
		JPanel p2=new JPanel(new GridLayout(1,2,5,5));
		p2.setBorder(BorderFactory.createTitledBorder(""));
		p2.add(cmd1);
		p2.add(cmd2);
		
		this.getContentPane().add(p1,BorderLayout.NORTH);
		this.getContentPane().add(p2,BorderLayout.SOUTH);
	}
	public RenameTable(Connection con)
    {
			cn=con;
			this.initComponent();
			this.setTitle("Rename Table");
			this.setVisible(true);
			//this.setLocation(300,300);
			this.setSize(300,300);
			Helper.setPosition(this);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

}
