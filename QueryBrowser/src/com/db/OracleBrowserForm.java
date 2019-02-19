package com.db;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.*;
public class OracleBrowserForm extends JFrame
{
			
	private  JLabel l1,l3,l4,l5,l6,l7;
	private JTextField txt1,txt2,txt3,txt4;
	private JButton cmd1,cmd2,cmd3,cmd4;
	private JPasswordField jpf;
			 
			
	private void cmdbutton()
	{
		cmd1=new JButton("Details >>");
		cmd2=new JButton("O.K");
		this.getRootPane().setDefaultButton(cmd2);
		cmd2.addActionListener(new ActionListener()
		{
            @Override
			public void actionPerformed(ActionEvent ae)
			{
				Oracle p=new Oracle();
				try
				{
						
						p.setIP(txt1.getText());
						p.setPort(txt2.getText());
					    p.setSid(txt3.getText());
						p.setUser(txt4.getText());
						p.setPassword(jpf.getText());
						
						Connection cn=p.getOracleConnection();
                                               if(!cn.isClosed())
						{
                                                    
							OracleQuerybrowserForm f=new OracleQuerybrowserForm(cn);						
							OracleBrowserForm.this.setVisible(false);
                                                        
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Login Failed...Retry\n\n"+p.ERROR);
						}				
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,"Login Failed...Retry\n\n"+p.ERROR);
				}		
			}
				
		});
			
		cmd3=new JButton("Clear");
		cmd3.addActionListener(new ActionListener()
		{
            @Override
			public void actionPerformed(ActionEvent ae)
			{			
					
					clear();						
               
			}
				
		});
		
		cmd4=new JButton("Cancel");
		cmd4.addActionListener(new ActionListener()
		{
            @Override
			public void actionPerformed(ActionEvent ae)
			{			
											
              OracleBrowserForm.this.setVisible(false);
			}
				
		});
	}
	
	private void initLabel()
	{
		l1=new JLabel(new ImageIcon(getClass().getResource("oraclelog.jpg")));
		
		l3=new JLabel("Server Host");
		l4=new JLabel("Port");
		l5=new JLabel("S.ID NO.");
		l6=new JLabel("Username");
		l7=new JLabel("Password");
	
	}
		
	private void textfield()
			{
			  txt1=new JTextField(10);
			  txt1.setText("127.0.0.1");
			  txt2=new  JTextField(2);
			  txt2.setText("1521");
			  txt3=new JTextField(10);
			  txt3.setText("xe");
			  txt4=new JTextField(10);
			  
		    }
	private void clear()
		{
			txt1.setText("");
			txt2.setText("");
			txt3.setText("");
			txt4.setText("");
			jpf.setText("");
		
			
		}
	
	private void initComponents()
	 {
	    	  this.initLabel();
		     this.textfield();
		     this.cmdbutton();
		     
			 JPanel p1=new JPanel();
			p1.setBorder(BorderFactory.createTitledBorder(""));
			p1.add(l1);
	
			JPanel p2=new JPanel(new GridLayout(6,2,5,5));
			p2.setBorder(BorderFactory.createTitledBorder("Connect to Oracle server Instance"));
		
			
			p2.add(l3);
			p2.add(txt1);
			p2.add(l4);
			p2.add(txt2);
			p2.add(l5);
			p2.add(txt3);
			p2.add(l6);
			p2.add(txt4);
			p2.add(l7);
			jpf=new JPasswordField(10);
			p2.add(jpf);
			
		
								
			JPanel p3= new  JPanel(new GridLayout(1,4,5,5));
			p3.setBorder(BorderFactory.createTitledBorder(""));
			p3.add(cmd1);
			p3.add(cmd2);
			p3.add(cmd3);
			p3.add(cmd4);
			
			this.getContentPane().add(p1,BorderLayout.NORTH);
  			this.getContentPane().add(p2,BorderLayout.CENTER);
			this.getContentPane().add(p3,BorderLayout.SOUTH);    	
	    			
    	}
	public OracleBrowserForm()
	{
		this.initComponents();
		this.setTitle("Oracle Query Browser");
		this.setSize(450,450);
		this.setLocation(300,300);
		this.setVisible(true);
		this.setResizable(false);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
	}

 
}