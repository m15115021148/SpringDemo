package com.geek.dao;

import java.util.List;

import com.geek.po.KindModel;

public interface KindDao {
	List<KindModel> getKind();
	String findKindID(String kindID);
}
