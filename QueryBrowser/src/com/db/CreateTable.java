package com.db;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class CreateTable extends JFrame
{
	private Connection cn;
	private JLabel l1;
	private JTable table;
	private DefaultTableModel model;
	private JButton cmd1,cmd2,cmd3;
	private JTextField txt1;
	private JPanel p1,p2,p3;
	private void initTabel()
	{
		model=new DefaultTableModel();
		model.addColumn("FIELD");
		model.addColumn("TYPE");
		model.addColumn("SIZE");
		model.addColumn("CONSTRAINT");
	/*	model.addRow(new java.util.Vector());
		model.addRow(new java.util.Vector());
		model.addRow(new java.util.Vector());
		model.addRow(new java.util.Vector());
		model.addRow(new java.util.Vector());
		model.addRow(new java.util.Vector());
		model.addRow(new java.util.Vector());
		model.addRow(new java.util.Vector());
		model.addRow(new java.util.Vector());
	        model.addRow(new java.util.Vector());*/
		table=new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(400,150));
		
	}
	private void initLabel()
	{
		l1=new JLabel("TABLE NAME");
	}
	private void initTextBox()
	{
		txt1=new JTextField(10);
	}
	private void initButton()
	{
		cmd1=new JButton("GENERATE");
                   
		cmd2=new JButton("ADD ROW");
		cmd2.addActionListener(new ActionListener()
		{
            @Override
			public void actionPerformed(ActionEvent ae)
			{ 
				model.addRow(new java.util.Vector());
				String a[]={"INTEGER","VARCHAR","DOUBLE","VARCHAR2","NUMBER","TEXT","DATE/TIME"};
				table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JComboBox(a)));
			}
			
		});
		
		
		cmd3=new JButton("REMOVE ROW");
		cmd3.addActionListener(new ActionListener()
		{
            @Override
			public void actionPerformed(ActionEvent ae)
			{ 
				 
			   int row=table.getSelectedRow();
			   model.removeRow(row);
			}
			});
		cmd1.addActionListener(new ActionListener()
		{
            @Override
			public void actionPerformed(ActionEvent ae)
			{
				initQuery();
			}
			
		});
	}
	private void initPanel()
	{
		this.initButton();
		this.initTabel();
		this.initTextBox();
		this.initLabel();
		p1=new JPanel(new GridLayout(1,2,5,5));
		p1.setBorder(BorderFactory.createTitledBorder("create table"));
		p1.add(l1);
		p1.add(txt1);
		this.getContentPane().add(p1,BorderLayout.NORTH);
		p2=new JPanel(new GridLayout(1,0,5,5));
		p2.setBorder(BorderFactory.createTitledBorder("table"));
		
		p2.add(new JScrollPane(table));
		
		this.getContentPane().add(p2,BorderLayout.CENTER);
		p3=new JPanel(new GridLayout(1,3,5,5));
		p3.setBorder(BorderFactory.createTitledBorder(""));
		p3.add(cmd2);
		p3.add(cmd3);
		p3.add(cmd1);
		this.getContentPane().add(p3,BorderLayout.SOUTH);
	}
	public void initQuery()
	{

		try	
			{
				
				String str4="";
				
 				if(table.getRowCount()>0&&txt1.getText().length()>0)
				{
					String str2="";
					
					for(int i=0;i<table.getRowCount();i++)
					{	
						
						String field=table.getValueAt(i,0).toString();
						String type=table.getValueAt(i,1).toString();
						String size=table.getValueAt(i,2).toString();
						String constraint=table.getValueAt(i,3).toString();
						String str=field+" "+type+"("+size+") "+constraint;
						str2=str2+str+" , ";

						str="";
						
					}
                                        for(int i=0;i<=str2.length()-3;i++)
					{
						str4=str4+str2.charAt(i);
					}
					String str1="CREATE TABLE "+ txt1.getText().toString()+"("+str4+")";
                                       
                                        System.out.println(str1);
					JOptionPane.showMessageDialog(null,str1);
					PreparedStatement ps=cn.prepareStatement(str1);
					int a = ps.executeUpdate();
                                        
						
				}
				else
				{
				
				JOptionPane.showMessageDialog(null,"DATA NOT ENTERED");
				}
				
		    }
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage());
                        System.out.print(e.getMessage());
		}
	}
		
		public CreateTable(Connection con)
		{
			cn=con;
			this.initPanel();
			this.setTitle("CREATE TABLE");
			this.setVisible(true);
			this.setLocation(300,300);
			Helper.setPosition(this);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

}
