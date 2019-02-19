package com.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
public class DataTable extends JTable
{
	private javax.swing.table.DefaultTableModel model;
	public DataTable()
	{
		model=new javax.swing.table.DefaultTableModel();
		this.setModel(model);
	}
	public DataTable(String a[])
	{
		model=new javax.swing.table.DefaultTableModel();
		for(int i=0;i<a.length;i++)
		{
			model.addColumn(a[i]);
		}
		this.setModel(model);
	}
	public DataTable(javax.swing.table.DefaultTableModel m)
	{
		model=m;		
		this.setModel(model);
	}
	public DataTable(Connection cn,String sql)
	{
		setData(cn,sql);
	}
	public DataTable(java.awt.Dimension dimension)
	{
		model=new javax.swing.table.DefaultTableModel();
		this.setModel(model);
		this.setPreferredScrollableViewportSize(dimension);
		this.updateUI();
		this.repaint();
	}
	public void setData(Connection cn,String sql)
	{
		try
		{
			clearTable();
			java.util.Vector v1=new java.util.Vector();
			PreparedStatement ps=cn.prepareStatement(sql.toUpperCase().trim());
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				java.util.Vector v2=new java.util.Vector();
				for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
				{
					try
					{
						v2.add(rs.getString(i).toUpperCase());
					}
					catch(Exception e11)
					{
						v2.add("");
					}
					
				}
				v1.add(v2);
			}
			java.util.Vector v3=new java.util.Vector();
			for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
			{
				v3.add(rs.getMetaData().getColumnLabel(i).toUpperCase());
			}
			model.setDataVector(v1,v3);
			
		}
        catch(SQLException sqle)
         {
                    JOptionPane.showMessageDialog(null, sqle.toString());
          }
		catch(Exception e)
         {
                    JOptionPane.showMessageDialog(null, e.toString());
          }
	}
	public void setData(ResultSet rs)
	{
		try
		{
			clearTable();
				
			while(rs.next())
			{
				java.util.Vector v2=new java.util.Vector();
				for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
				{
					v2.add(rs.getString(i).toUpperCase());
				}
				model.addRow(v2);
				
			}			
		}
        catch(SQLException sqle)
         {
                    JOptionPane.showMessageDialog(null, sqle.toString());
          }
		catch(Exception e)
         {
                    JOptionPane.showMessageDialog(null, e.toString());
          }
	}
    public String [] getTableSelectedRowData(int row)
        {
            if(this.getRowCount()>0 && row<this.getRowCount())
            {
                String a[]=new String[this.getColumnCount()];
                for(int i=0;i<a.length;i++)
                {
                    a[i]=this.getValueAt(row,i).toString().toUpperCase().trim();
                }
                return a;
            }
            else
            {
                return null;
            }
            
        }
	public void createRow(java.util.Vector v)
	{
		model.addRow(v);
	}
	public void deleteRow(int rowNumber)
	{
		if(this.getRowCount()>0)
		{
			model.removeRow(rowNumber);
		}
	}
    public void clearTable()
        {
            Vector a=new Vector();
            for(int i=0;i<model.getColumnCount();i++)
            {
                a.add(model.getColumnName(i));
            }
            model.setDataVector(new Vector(), a);
        }
    public javax.swing.table.DefaultTableModel    getDataTableModel()
    {
        return this.model;
    }
}
