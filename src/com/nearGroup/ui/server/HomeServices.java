package com.nearGroup.ui.server;

import java.util.List;

import com.nearGroup.modal.Groups;

public interface HomeServices {

	public int getGroupCount(String type,String value);
	public List<Groups> getListOfGroups(String sql);
}
