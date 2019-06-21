package com.auto.Automation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.auto.Automation.service.ReadPath;

public class BirthdayDetailsDao {
	public ResultSet getResultSet() {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet resultset = null;
		
         
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ReadPath.pattern);
		String today_date = simpleDateFormat.format(new Date());
		
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection (ReadPath.dbUrl, ReadPath.dbUserName,ReadPath.dbPassword);
			
			String sql = "select name,email_id from "+ReadPath.tableName+" where "+ReadPath.bdyDate+" = '" + today_date +"'";
						
			stat=conn.prepareStatement(sql);
			
			resultset = stat.executeQuery();
			
		}catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return resultset;
	}
}
