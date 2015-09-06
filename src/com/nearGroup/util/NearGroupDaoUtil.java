package com.nearGroup.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.nearGroup.db.DBManager;

public class NearGroupDaoUtil {

	private static final Logger logger = Logger.getLogger(NearGroupDaoUtil.class);
	

	public static int getTotalRecord(String sql,String connectionType) throws SQLException {

		int count = 0;

		Connection con = null;
		
		try {
			con = DBManager.getConnection(connectionType);
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			throw new SQLException("Error while geting count SQL ["+sql+"]");
		}catch (Exception e) {
			throw new SQLException("Error while geting count SQL ["+sql+"]");
		}
		finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ex) {
				logger.error("Error while Connection closeing... ");
			}
		}
		return count;
	}

}
