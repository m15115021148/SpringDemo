package com.geek.service;

import java.util.List;

import javax.annotation.Resource;

import com.geek.dao.KindDao;
import com.geek.po.KindModel;

public class KindService implements KindDao{
	@Resource
	private KindDao dao;

	public List<KindModel> getKind() {
		return dao.getKind();
	}

	public String findKindID(String kindID) {
		return dao.findKindID(kindID);
	}

}
