package com.nearGroup.ui.server.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.nearGroup.db.DBManager;
import com.nearGroup.modal.Groups;
import com.nearGroup.modal.Topic;
import com.nearGroup.ui.server.HomeServices;
import com.nearGroup.util.Constants;
import com.nearGroup.util.NearGroupDaoUtil;

public class HomeServicesImpl implements HomeServices {

	private static final Logger logger = Logger.getLogger(HomeServicesImpl.class);

	@Override
	public int getGroupCount(String type, String value) {
		int count = 0;
		String sql = "select count(id) from groups";
		try {
			count = NearGroupDaoUtil.getTotalRecord(sql.toString(), Constants.ALPHA_DS);
		} catch (Exception e) {
			logger.error("Error while getting count of groups", e);
		}
		return count;
	}

	@Override
	public Map<String, Groups> getListOfGroups(String sql, int uid) {
		// List<Groups> list = new ArrayList<Groups>();
		List<String> joinList = getListJoinGroupUserList(uid);
		Map<String, Groups> map = new LinkedHashMap<>();
		try (Connection connection = DBManager.getConnection(Constants.ALPHA_DS); PreparedStatement ps = connection.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				map.put(rs.getString("ID"),
						new Groups(rs.getString("ID"), rs.getString("DISPLAY_NAME"), rs.getString("GROUP_CATEGORY"), rs.getString("GROUP_ICON_CATEGORY"), rs.getString("GROUP_STATUS"), rs
								.getString("NAME"), rs.getString("USERS_COUNT"), rs.getString("GROUP_CREATION_TIME"), rs.getString("MODIFIED_TIME")));
			}

			for (String s : joinList) {
				if(map.containsKey(s)){
				map.get(s).setJoinFlag(1);
				}
			}

		} catch (Exception e) {
			logger.error("Error while getting list of groups [" + sql + "]", e);
		}
		return map;
	}

	public List<String> getListJoinGroupUserList(int uid) {
		String sqlSelect = "select GID from GROUP_JOIN where U_ID =?";
		List<String> joinList = new ArrayList<>();
		try (Connection connection = DBManager.getConnection(Constants.NEAR_GROUP_DS); PreparedStatement ps = connection.prepareStatement(sqlSelect)) {
			ps.setInt(1, uid);
			ResultSet rsSelect = ps.executeQuery();
			while (rsSelect.next()) {
				joinList.add(rsSelect.getString("GID"));
			}
		} catch (Exception e) {
			logger.error("Error while getting list of join user into group , uid [" + uid + "]", e);
		}
		return joinList;
	}
	
	public List<String> getListJoinTopicUserList(int uid) {
		String sqlSelect = "select TID from TOPIC_JOIN where U_ID =?";
		List<String> joinList = new ArrayList<>();
		try (Connection connection = DBManager.getConnection(Constants.NEAR_GROUP_DS); PreparedStatement ps = connection.prepareStatement(sqlSelect)) {
			ps.setInt(1, uid);
			ResultSet rsSelect = ps.executeQuery();
			while (rsSelect.next()) {
				joinList.add(rsSelect.getString("TID"));
			}
		} catch (Exception e) {
			logger.error("Error while getting list of join user into topic , uid [" + uid + "]", e);
		}
		return joinList;
	}
	
	@Override
	public Map<String, Topic> getListOfTopic(String sql, int uid, String type, String value, String topicType, String ownerName, String gid) {
		List<String> joinList = getListJoinTopicUserList(uid);
		Map<String, Topic> map = new LinkedHashMap<>();
		try (Connection connection = DBManager.getConnection(Constants.ALPHA_DS); PreparedStatement ps = connection.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				map.put(rs.getString("ID"),
						new Topic(rs.getInt("ID"), rs.getString("NAME"), rs.getString("DESPCRIPTION"), rs.getString("TYPE"), rs.getString("GROUP_DISPLAY_NAME"), rs
								.getString("OWNER_FB_ID"), rs.getString("OWNER_USER_NAME"), rs.getString("TOPIC_LIKE_COUNT"), rs.getString("TOPIC_SPAM_COUNT"),
								rs.getString("CREATION_TIME"),rs.getString("MODIFIED_TIME"),rs.getString("GROUP_ID")));
			}

			for (String s : joinList) {
				if(map.containsKey(s)){
				map.get(s).setJoinFlag(1);
				}
			}

		} catch (Exception e) {
			logger.error("Error while getting list of topics [" + sql + "]", e);
		}
		return map;
	
	}
	@Override
	public int getTopicCount(String type, String value, String topicType, String ownerName, String gid) {
		int count = 0;
		StringBuilder sql = new StringBuilder("select count(id) from topics");
		if(!type.trim().equals(""))
	  		sql.append(" where ").append(type).append(" like '") .append(value.trim()).append("%' ");
	  		if(gid.length() > 0 && type.trim().equals("")){
	  			sql.append(" where GROUP_ID =").append("'").append(gid.trim()).append("'");
	  			}else if(gid.length() > 0){
	  			sql.append(" and GROUP_ID =").append("'").append(gid.trim()).append("'");
	  			}
		try {
			count = NearGroupDaoUtil.getTotalRecord(sql.toString(), Constants.ALPHA_DS);
		} catch (Exception e) {
			logger.error("Error while getting count of topic", e);
		}
		return count;
	}
}
