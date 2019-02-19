package com.db;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class AlterTable extends JFrame
{
	private int previousRowCount;
	
    private Connection cn;
    private String tablename;
    private DefaultTableModel model;
	private JLabel l1,l2;
	private JTextField txt1;
	private JButton cmd1,cmd2,cmd3,cmd4;
	private DataComboBox cbo;
	private DataTable table;
    private void initLabel()
	 {
		
		l1=new JLabel(" TABLE NAME");
	    l2=new JLabel(" TABLE  NAME");
	}
	private void initComboBox()
	{
		cbo=new DataComboBox();
		cbo.setData(cn," Show Tables ",true);
		cbo.setSelectedItem(this.tablename);
	}
	private void initTable()
    { 
    	
    	 table=new DataTable();
        JPanel p2=new JPanel(new GridLayout(1,1,5,5));
    	p2.setBorder(BorderFactory.createTitledBorder("Result Set"));
        p2.add(new JScrollPane(table));
    	this.getContentPane().add(p2,BorderLayout.CENTER);
    	
    }   
	private void initCmdButton()
	{
	cmd1=new JButton("ADD ROW");
	cmd1.addActionListener(new ActionListener()
		{
            @Override
			public void actionPerformed(ActionEvent ae)
			{          	  
				table.getDataTableModel().addRow(new Vector());
			}
				
		});
	  	
	cmd2=new JButton("ADD");

	
	cmd3=new JButton("REMOVE ROW");
	cmd3.addActionListener(new ActionListener()
	{
            @Override
		public void actionPerformed(ActionEvent ae)
		{         
		     int row=table.getSelectedRow();
			table.getDataTableModel().removeRow(row);
			
		}		
		});
	
	
	cmd4=new JButton("Discard");
	cmd4.addActionListener(new ActionListener()
		{
            @Override
			public void actionPerformed(ActionEvent ae)
			{			
											
               AlterTable.this.setVisible(false);
			}
				
		});
	cmd2.addActionListener(new ActionListener()
		{
            @Override
			public void actionPerformed(ActionEvent ae)
			{    
		      	String str="";
				try
				{
					int i=0;
					String sql="ALTER TABLE "+tablename+" ";
					
				    for(i=previousRowCount;i<table.getRowCount()-1;i++)
					{				   
						String a1=table.getValueAt(i,0).toString();
						String a2=table.getValueAt(i,1).toString();
						//String a3=table.getValueAt(i,2).toString();
						String a4=table.getValueAt(i,3).toString();
						sql+="ADD COLUMN"+" "+a1+" "+a2+" "+a4+",";
						
					}
					
					String a1=table.getValueAt(i,0).toString();
					String a2=table.getValueAt(i,1).toString();
					//String a3=table.getValueAt(i,2).toString();
					String a4=table.getValueAt(i,3).toString();
					sql+="ADD COLUMN"+" "+a1+" "+a2+" "+a4;
			    	JOptionPane.showMessageDialog(AlterTable.this,sql);
			    	PreparedStatement ps=cn.prepareStatement(sql);
					int a=ps.executeUpdate();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(AlterTable.this,e.getMessage());
				}
			}
				
		});	
	}
	private  void initTextBox()
	{
		txt1=new JTextField(10);
		txt1.setText(this.tablename);
	
	  
	}
	private void initComponent()
	{
		this.initLabel();
  		this.initCmdButton();
 		this.initTextBox();
 		this.initComboBox();
 		this.initTable();
 		JPanel p1=new JPanel(new GridLayout(1,4,5,5));
		p1.setBorder(BorderFactory.createTitledBorder(""));
		p1.add(l1);
		p1.add(txt1);
		p1.add(l2);
		p1.add(cbo);
		this.getContentPane().add(p1,BorderLayout.NORTH);
		
	   JPanel p3=new JPanel(new GridLayout(1,3,5,5));
		//p3.setBorder(BorderFactory.createTitledBorder(""));
		p3.add(cmd1);
		p3.add(cmd2);
		p3.add(cmd3);
		JPanel p4=new JPanel(new GridLayout(1,1,5,5));
		//p4.setBorder(BorderFactory.createTitledBorder(""));
		p4.add(cmd4);
		JPanel p0=new JPanel(new GridLayout(2,1,5,5));
		p0.setBorder(BorderFactory.createTitledBorder(""));
		p0.add(p3);
		p0.add(p4);
				
		this.getContentPane().add(p0,BorderLayout.SOUTH);
	
	}
	public AlterTable(Connection con,String str)
	{   
		cn=con;
		this.tablename=str;
		
		this.initComponent();
		this.setTitle("ALTER  TABLE");
		this.setVisible(true);
		//this.setLocation(300,300);
     	this.setSize(300,300);
		Helper.setPosition(this);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		structure();
	}
	private void structure()			
    {
		try
		{
		  table.clearTable();
		  Vector v1=new Vector();
	      PreparedStatement ps=cn.prepareStatement("DESC "+tablename);
		  ResultSet rs=ps.executeQuery();
		  while(rs.next())
	    	{
			  Vector v2=new Vector();
			   for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
			  	{
			     					
			    	v2.add(rs.getString(i));
			     }
			   	v1.add(v2);			     					
			  }
			   Vector v3=new Vector();
			  	for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
			  	{
			    	v3.add(rs.getMetaData().getColumnLabel(i));
			    }
			  table.getDataTableModel().setDataVector(v1,v3);
			  table.updateUI();
			  previousRowCount=table.getRowCount();
	     	}
         	
    catch(Exception e)
	{
	   JOptionPane.showMessageDialog(null,e.toString());
	}
			        	 
}	
  
}
