package cn.bdqn.dao;

import org.springframework.stereotype.Controller;
import java.sql.*;
import java.util.ArrayList;

public class BaseDao {
	//数据库操作配置参数
	private String driver="com.mysql.jdbc.Driver";//数据库驱动字符串
	private String url="jdbc:mysql://192.168.136.133:3306/ssmbuild";//数据库路径
	private String user="root";//数据库用户名
	private String pwd="root";//数据库密码
	/**
	 * 获取连接对象的方法##  pfs
	 */
	public Connection getConnection(){
		Connection conn=null;
		try {
			//1.加载驱动
			Class.forName(driver);
			//2.建立连接
			conn= DriverManager.getConnection(url,user,pwd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}


	public void closeAll(ResultSet rs,Statement stmt,Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=conn){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ResultSet executeQuery(String sql,ArrayList<Object> params){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			if(params!=null){
				for(int i=0;i<params.size();i++){
					pstmt.setObject(i+1,params.get(i));
				}
			}
			rs=pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public int executeNoQuery(String sql,ArrayList<Object> params){
		Connection conn=null;
		int row=0;
		PreparedStatement pstmt=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			if(params!=null){
				for(int i=0;i<params.size();i++){
					pstmt.setObject(i+1, params.get(i));
				}
			}
			row=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeAll(null,pstmt,conn);
		}
		return row;
	}
}
