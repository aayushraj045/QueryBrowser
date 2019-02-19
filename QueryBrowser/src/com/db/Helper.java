package com.db;
//import com.mysql.jdbc.DatabaseMetaData;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JOptionPane;
public class Helper
{
	public static String getKey(int range)
	{
		java.util.Random r=new java.util.Random();
		int key=r.nextInt(range);
		return key+"";
	}
	public static boolean isEmpty(javax.swing.JTextField txt)
	{
		if(txt.getText().length()>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	} 
	public static void open(String application)
	{
		try
		{
			Runtime r=Runtime.getRuntime();
			Process p=r.exec(application);
		}
		catch(Exception e)
		{
			javax.swing.JOptionPane.showMessageDialog(null,e.getMessage());
		}
		
	}
    public static Connection openConnection()
        {
            Connection cn=null;
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                cn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysql","root","");
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e.toString());
            }
            return cn;
        }
    public static Connection openConnection(String database)
        {
            Connection cn=null;
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                cn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/"+database,"root","");
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e.toString());
            }
            return cn;
        }
    public static Connection openConnection(String database,String port)
        {
            Connection cn=null;
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                cn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:"+port+"/"+database,"root","");
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e.toString());
            }
            return cn;
        }
    public static void closeConnection(Connection con)
        {
            try
            {
                if(!con.isClosed())
                {
                    con.close();
                }
            }
            catch(Exception e){}            
        }
    public static Connection openOracleConnection()
        {
            Connection cn=null;
            try
            {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
                cn.setAutoCommit(false);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e.toString());
            }
            return cn;
        }
     
     
    public static String readFile(File file)
		{
			String str="";
			try
			{
				FileInputStream fis=new FileInputStream(file);
				for(int i=fis.read();i!=-1;i=fis.read())
				{
					str+=(char)i;
				}
				fis.close();
			}
			catch(Exception e){}
			return str;
		}
	public static boolean writeFile(File file,String content)
		{
			boolean  flag=false;
			try
			{
				FileOutputStream fos=new FileOutputStream(file);
				fos.write(content.getBytes());
				fos.close();
				flag=file.exists();
			}
			catch(Exception e){}
			return flag;
		}
    public static Iterator<String> getTables(Connection cn)
        {
            Vector <String> vector=new Vector<String>();
            try
            {
                DatabaseMetaData meta=(DatabaseMetaData) cn.getMetaData();
                String[] TABLE_TYPES = {"TABLE"};
                ResultSet tables = meta.getTables(null, null, null, TABLE_TYPES);
                while (tables.next())
                {
                    vector.addElement(tables.getString("TABLE_NAME"));
                }
            }
            catch(Exception e){}
            return vector.iterator();
        }
    public static Iterator<String> getOracleTables(Connection cn)
        {
            Vector <String> vector=new Vector<String>();
           	
            try
            {
            	
                PreparedStatement ps=cn.prepareStatement("SELECT * FROM TAB");
                ResultSet tables=ps.executeQuery();
                while (tables.next())
                {
                    vector.addElement(tables.getString(1));
                }
                tables.close();
            }
            catch(Exception e){}
            
            return vector.iterator();
        }
    public static void setPosition(javax.swing.JFrame frame)
        {
        	java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
  			int height = screenSize.height;
  			int width = screenSize.width;
  			frame.setSize(width/2, height/2); 
  			frame.setLocationRelativeTo(null);
        }
    public static void setPosition(javax.swing.JWindow frame)
        {
        	java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
  			int height = screenSize.height;
  			int width = screenSize.width;
  			frame.setSize(width/2, height/2); 
  			frame.setLocationRelativeTo(null);
        }
    public static void setPosition(javax.swing.JInternalFrame frame)
        {
        	java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
  			int height = screenSize.height;
  			int width = screenSize.width;
  			frame.setSize(width/2, height/2); 
  			//frame.setLocationRelativeTo(null);
        }
    public static void insertRow(Connection cn,DataTable table,String tableName)
     {
     	try
     	{
		
			table.setData(cn,"Select * from "+tableName);
			Vector v1=new Vector();
     		
     		table.getDataTableModel().addRow(v1);
     		table.updateUI();
     	}     	
     	catch(Exception e)
     	{
     		JOptionPane.showMessageDialog(null,e.toString());
     	}
     }
    public static void printTable(DataTable table)
     {
     	try
     	{
     		table.print();
     	}
     	catch(Exception e)
     	{
     		JOptionPane.showMessageDialog(null,e.getMessage());
     	}
     }}