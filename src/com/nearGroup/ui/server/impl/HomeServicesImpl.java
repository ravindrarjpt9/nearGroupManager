package com.nearGroup.ui.server.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.nearGroup.db.DBManager;
import com.nearGroup.modal.Groups;
import com.nearGroup.ui.server.HomeServices;
import com.nearGroup.util.Constants;
import com.nearGroup.util.NearGroupDaoUtil;

public class HomeServicesImpl implements HomeServices {

	private static final Logger logger = Logger.getLogger(HomeServicesImpl.class);

	
	@Override
	public int getGroupCount(String type, String value) {
		int count =0;
		String sql = "select count(id) from groups";
		try{
			count = NearGroupDaoUtil.getTotalRecord(sql.toString(), Constants.ALPHA_DS);
		}catch(Exception e)
		{
			logger.error("Error while getting count of groups",e);
		}
		return count;
	}
	
	@Override
	public List<Groups> getListOfGroups(String sql) {
		List<Groups> list = new ArrayList<Groups>();
		try(Connection connection = DBManager.getConnection(Constants.ALPHA_DS);
				PreparedStatement ps = connection.prepareStatement(sql))
				{
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				list.add(new Groups(rs.getString("ID"), rs.getString("DISPLAY_NAME"), rs.getString("GROUP_CATEGORY"),
						rs.getString("GROUP_ICON_CATEGORY"), rs.getString("GROUP_STATUS"), 
						rs.getString("NAME"), rs.getString("USERS_COUNT"), rs.getString("GROUP_CREATION_TIME"), rs.getString("MODIFIED_TIME")));
			}
			
				}catch (Exception e) {
					logger.error("Error while getting list of groups ["+sql+"]", e);
				}
		return list;
	}
}
