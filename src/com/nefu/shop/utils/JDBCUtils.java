package com.nefu.shop.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.PreparedStatement;

import java.sql.Connection;

public class JDBCUtils {

	private static DataSource datasources = null;

	static {

		datasources = new ComboPooledDataSource();

	}

	public static DataSource getDataSource() {

		return datasources;
	}

	/**
	 * get Connection
	 */

	public static Connection getConnection() {

		Connection conn = null;

		try {

			conn = datasources.getConnection();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;

	}

	/**
	 * close Connection
	 * 
	 * @throws SQLException
	 */

	public static void Closes(Connection con, PreparedStatement ps, ResultSet re)
			throws SQLException {

		if (ps != null) {
			ps.close();
		}
		if (re != null) {
			re.close();
		}

		if (con != null) {
			con.close();
		}

	}

	public static void Closes(Connection con) throws SQLException {

		if (con != null) {
			con.close();
		}

	}

}
