package com.db;
import java.sql.Connection;
import java.sql.DriverManager;
public class Oracle
{
	public String ERROR;
	
    private String ip;
    private String port;
    private String sid;
    private String user;
    private String password;
    private String url;

    private void makeUrl()
    {
        this.url="jdbc:oracle:thin:@"+this.ip+":"+this.port+":"+this.sid;
    }
    public Oracle()
    {
        this.ip="127.0.0.1";
        this.port="1521";
        this.sid="xe";
        this.user="system";
        this.password="";
        this.makeUrl();
    }
 public Oracle(String ip)
    {
        this.ip=ip;
        this.port="1521";
        this.sid="xe";
        this.user="sytem";
        this.password="";
        this.makeUrl();
    }
    public Oracle(String ip,String port)
    {
        this.ip=ip;
        this.port=port;
         this.sid="xe";
        this.user="system";
        this.password="";
        this.makeUrl();
    }
    public Oracle(String ip,String port,String database)
    {
        this.ip=ip;
        this.port=port;
         this.sid="xe";
        this.user="Oracle";
        this.password="";
        this.makeUrl();
    }
    public Oracle(String ip,String port,String database,String user)
    {
        this.ip=ip;
        this.port=port;
        this.sid="xe";
        this.user=user;
        this.password="";
        this.makeUrl();
    }
    public Oracle(String ip,String port,String database,String user,String password)
    {
        this.ip=ip;
        this.port=port;
        this.sid=sid;
        this.user=user;
        this.password=password;
        this.makeUrl();
    }

    public Connection getOracleConnection()
    {
        Connection cn=null;
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            cn=DriverManager.getConnection(this.url,this.user,this.password);
        }
        catch(Exception e)
        {
        	this.ERROR=e.getMessage();
        }
         return cn;
    }
  public Connection getOracleConnection(boolean commitMode)
    {
        Connection cn=null;
        try
        {
            Class.forName ("oracle.jdbc.driver.OracleDriver"); 
              
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
    public String getSid()
    {
    	return this.sid;
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
    public void setSid(String s)
    {
    	this.sid=s;
    }
}
