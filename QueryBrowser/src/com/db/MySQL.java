
package com.db;
import java.sql.Connection;
import java.sql.DriverManager;
public class MySQL
{
	public String ERROR;
	
    private String ip;
    private String port;
    private String database;
    private String user;
    private String password;
    private String url;

    private void makeUrl()
    {
       this.url="jdbc:mysql://"+this.ip+":"+this.port+"/"+this.database;
    }
    
public MySQL()
    {
        this.ip="127.0.0.1";
        this.port="3306";
        this.database="mysql";
        this.user="root";
        this.password="";
        this.makeUrl();
    }
    public MySQL(String ip)
    {
        this.ip=ip;
        this.port="3306";
        this.database="mysql";
        this.user="root";
        this.password="";
        this.makeUrl();
    }
  
  public MySQL(String ip,String port)
    {
        this.ip=ip;
        this.port=port;
        this.database="mysql";
        this.user="root";
        this.password="";
        this.makeUrl();
    }
    public MySQL(String ip,String port,String database)
    {
        this.ip=ip;
        this.port=port;
        this.database=database;
        this.user="root";
        this.password="";
        this.makeUrl();
    }
    
public MySQL(String ip,String port,String database,String user)
    {
        this.ip=ip;
        this.port=port;
        this.database=database;
        this.user=user;
        this.password="";
        this.makeUrl();
    }
    public MySQL(String ip,String port,String database,String user,String password)
    {
        this.ip=ip;
        this.port=port;
        this.database=database;
        this.user=user;
        this.password=password;
        this.makeUrl();
    }

 public Connection getConnection()
    {
        Connection cn=null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            cn=DriverManager.getConnection(this.url,this.user,this.password);
        }
        catch(Exception e)
        {
        	this.ERROR=e.getMessage();
        }
         return cn;
    }
   
 public Connection getConnection(boolean commitMode)
    {
        Connection cn=null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            cn=DriverManager.getConnection(this.url,this.user,this.password);
            if(!cn.isClosed())
            {
                cn.setAutoCommit(commitMode);
            }
        }
        catch(Exception e)
        {
        	this.ERROR=e.getMessage();
        }
         return cn;
    }
    public void setIP(String ip)
    {
        this.ip=ip;
        this.makeUrl();
    }
    public void setPort(String port)
    {
        this.port=port;
        this.makeUrl();
    }
    public void setDatabase(String database)
    {
        this.database=database;
        this.makeUrl();
    }
    public void setUser(String user)
    {
        this.user=user;
        this.makeUrl();
    }
  
 public void setPassword(String password)
    {
        this.password=password;
    }
    public String getIP()
    {
        return this.ip;
    }
    public String getPort()
    {
        return this.port;
    }
    public String getDatabase()
    {
        return this.database;
    }
    public String getUser()
    {
        return this.user;
    }
    

public String getPassword()
    {
        return this.password;
    }
    public String getUrl()
    {
        return this.url;
    }
}
