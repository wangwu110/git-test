package com.sinoufc.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class test {

	private static String drive_class_name = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@10.20.144.134:1521:bafedb";
	private static String username = "bafe";
	private static String password = "bafe";
	private static int max_active = 20;

	private static BasicDataSource dataSource;
	static {
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName(drive_class_name);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setMaxActive(max_active);
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	public static void main(String[] args) {

		Connection con = new test().getConnection();
		if (con != null) {
			System.out.println("连接数据库成功了");
		}
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
