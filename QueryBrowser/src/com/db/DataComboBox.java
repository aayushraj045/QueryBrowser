package com.db;
import java.util.Vector;
import javax.swing.JOptionPane;
public class DataComboBox extends javax.swing.JComboBox
{
	private java.util.Vector<String> value;
	
	public DataComboBox()
	{
		value=new java.util.Vector<String>();
	}
	public DataComboBox(java.sql.Connection cn,String sql)		
	{
		value=new java.util.Vector<String>();
		setData(cn,sql);
	}
	
	public String getBackValue()
	{
		return value.get(this.getSelectedIndex());
	}
	public String getFrontValue()
	{
		return this.getSelectedItem().toString();
	}
	
	public void setData(java.sql.Connection cn,String sql)
	{
		try
		{
			value.removeAllElements();
			this.removeAllItems();
                        
			java.sql.PreparedStatement ps=cn.prepareStatement(sql);
			java.sql.ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				value.add(rs.getString(1));
				this.addItem(rs.getString(2));
			}
		}
		catch(Exception e){}
	}
	public void setData(java.sql.Connection cn,String sql,boolean flag)
	{
		try
		{
			value.removeAllElements();
			this.removeAllItems();
                        
			java.sql.PreparedStatement ps=cn.prepareStatement(sql);
			java.sql.ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				String v=rs.getString(1);
				value.add(v);
				this.addItem(v);
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e.toString());
		}
	}
	public void setData(String frontValue,String backValue)
	{
		this.addItem(frontValue);
		value.add(backValue);
	}
        public Vector<String> getValues()
        {
            return this.value;
        }
}
