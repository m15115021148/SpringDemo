package com.geek.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.geek.dao.AccountDao;
import com.geek.po.AccountModel;
import com.geek.po.KindModel;
import com.geek.po.LocationModel;

@Service("accountService")
public class AccountService implements AccountDao{
	@Resource
	private AccountDao dao;

	public int upLoadAccount(AccountModel model) {
		return dao.upLoadAccount(model);
	}

	public List<AccountModel> getAccountList(String userID, String type,
			String kindID, String startTime, String endTime, String page) {
		return dao.getAccountList(userID, type, kindID, startTime, endTime, page);
	}

	public List<KindModel> getKind() {
		return dao.getKind();
	}

	public String findKindID(String kindID) {
		return dao.findKindID(kindID);
	}

	public String getStatisticsMoney(String userID, String type, String kind,
			String startTime, String endTime, String page) {
		return dao.getStatisticsMoney(userID, type, kind, startTime, endTime, page);
	}

	public List<AccountModel> getPieData(String userID, String type, String kind,
			String startTime, String endTime) {
		return dao.getPieData(userID, type, kind, startTime, endTime);
	}

	public int updateNote(String id , String userID, String note) {
		return dao.updateNote(id ,userID, note);
	}

	public int insertLocation(LocationModel model) {
		return dao.insertLocation(model);
	}

	public List<AccountModel> getLineData(String userID, String type,
			String kind, String startTime, String endTime) {
		return dao.getLineData(userID, type, kind, startTime, endTime);
	}

	public List<String> getKindAll(String userID, String type, String kind,
			String startTime, String endTime) {
		return dao.getKindAll(userID, type, kind, startTime, endTime);
	}

}
