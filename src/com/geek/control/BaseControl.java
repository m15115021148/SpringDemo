package com.geek.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.geek.dao.AccountDao;
import com.geek.dao.UserDao;
import com.geek.po.AccountModel;
import com.geek.po.KindModel;
import com.geek.po.LocationModel;
import com.geek.po.ResultModel;
import com.geek.po.UserModel;
import com.geek.util.DateUtil;
import com.geek.util.FileUtil;
import com.geek.util.HostUtil;
import com.geek.util.ParserUtil;
import com.geek.util.StringUtil;

@Controller
@RequestMapping("")
public class BaseControl {
	@Resource
	private UserDao userDao;
	@Resource
	private AccountDao accountDao;
	// 头像路径
	private String userHeaderPath = "/upload/photo/";
	/**账单图片保存路径*/
//	private String accountImgPath = "/upload/account/";

	/**
	 * 注册
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/dbAction_register")
	public void register(HttpServletRequest request,
			HttpServletResponse response, Model model) throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String psw = request.getParameter("password");
		PrintWriter out = response.getWriter();

		UserModel user = new UserModel();
		user.setName(name);
		user.setPassword(psw);
		user.setCreateTime(DateUtil.getCurrentDate());

		ResultModel resultModel = new ResultModel();

		if (name == null || psw == null || "".equals(name) || "".equals(psw)) {
			resultModel.setResult("0");
			resultModel.setErrorMsg("非空用户无法注册");
		} else {
			UserModel result = userDao.findUserByUserName(name);
			if (result != null) {
				resultModel.setResult("0");
				resultModel.setErrorMsg("用户名已存在");
			} else {
				resultModel.setResult("1");
				userDao.registerUser(user);
			}
		}
		String str = ParserUtil.objectToJson(resultModel);

		// model.addAttribute("user", user);//传递数据 到jsp页面

		out.write(str);
		out.flush();
		out.close();
		//
		// return "success";//返回到success.jsp页面
	}

	/**
	 * 登录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/dbAction_login", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object login(String name, String password, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		UserModel model = userDao.findUserByUserName(name);
		if (model != null) {
			if (name.equals(model.getName())) {
				if (password.equals(model.getPassword())) {
					map.put("userID", model.getUserID());
					map.put("name", model.getName());
					map.put("age", model.getAge() == null ? "" : model.getAge());
					map.put("sex", model.getSex() == null ? "" : model.getSex().equals("1")?"男":"女");
					map.put("address",
							model.getAddress() == null ? "" : model
									.getAddress());
					map.put("telphone", model.getTelphone());
					map.put("photo",
							HostUtil.getHostIp(request) + model.getPhoto());
					map.put("result", "1");
				} else {
					map.put("result", "0");
					map.put("errorMsg", "密码错误");
				}
			} else {
				map.put("result", "0");
				map.put("errorMsg", "用户名不存在");
			}
		} else {
			map.put("result", "0");
			map.put("errorMsg", "用户名不存在");
		}
		return map;
	}

	/**
	 * 上传头像
	 */
	@RequestMapping(value = "/dbAction_uploadHeader", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object uploadHeader(String userID,
			@RequestParam(required = false) MultipartFile img,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(userID==null||"".equals(userID)){
			map.put("result", "0");
			map.put("errorMsg","用户id不能为空");
			return map;
		}
		try {
			UserModel userModel = userDao.findUserById(userID);
			if (userModel == null) {
				map.put("result", "0");
				map.put("errorMsg", "用户不存在");
				return map;
			}

			String fileName = StringUtil.getUUID();
			int result = FileUtil.uploadImage(img, userHeaderPath, fileName,
					request);
			System.out.println(result);
			if (result != 0) {
				map.put("result", "0");
				map.put("errorMsg", "图片上传失败");
				return map;
			}
			String type = img.getOriginalFilename().substring(
					img.getOriginalFilename().lastIndexOf("."));
			userModel.setPhoto(userHeaderPath + fileName + type);
			userDao.uploadHeader(userID, userModel.getPhoto());
			map.put("result", "1");
			map.put("msg", HostUtil.getHostIp(request) + userModel.getPhoto());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "0");
			map.put("errorMsg", "修改失败");
		}
		return map;
	}

	/**
	 * 得到类型
	 */
	@RequestMapping(value = "/dbAction_getKinds", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Map<String, Object>> getKind() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<KindModel> u = accountDao.getKind();

		for (KindModel model : u) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("kindID", model.getKindID());
			map.put("kind", model.getKind());
			list.add(map);
		}
		return list;
	}

	/**
	 * 上传账单信息
	 */
	@RequestMapping(value = "/dbAction_uploadAccount", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object upLoadAccount(String userID, String type, String kind,
			String money, String note, String time,String lat,String lng,String address) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		LocationModel l = new LocationModel();
		l.setAddress(address);
		l.setLat(lat);
		l.setLng(lng);
		int lRow = accountDao.insertLocation(l);//上传位置信息
		if(lRow ==1){
			AccountModel model = new AccountModel();
			model.setUserID(userID);
			model.setTime(time);
			model.setType(type);
			model.setKind(kind);
			model.setMoney(money);
			model.setNote(note);
			model.setLocationID(l.getLocationID());//得到上传位置的id号  保存起来
			
			int raw = accountDao.upLoadAccount(model);
			if (raw == 1) {
				map.put("result", "1");
				map.put("errorMsg", "");
			} else {
				map.put("result", "0");
				map.put("errorMsg", "上传失败");
			}
		}else {
			map.put("result", "0");
			map.put("errorMsg", "上传失败");
		}
		
		return map;
	}

	/**
	 * 得到账单信息
	 */
	@RequestMapping(value = "/dbAction_getAccountList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Map<String, Object>> getAccountsListData(String userID,
			String type, String kind, String startTime, String endTime,
			String page, HttpServletRequest request) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<AccountModel> u = accountDao.getAccountList(userID, type, kind,
				startTime, endTime, page);
		for (AccountModel model : u) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userID", model.getUserID());
			map.put("type", model.getType());
			map.put("money", model.getMoney());
			map.put("kind", model.getKind());
			map.put("note", model.getNote());
			map.put("time", model.getTime());
			map.put("accountID", model.getAccountID());
			map.put("lat", model.getLat());
			map.put("lng", model.getLng());
			map.put("address", model.getAddress());
			if (model.getImg() == null) {
				map.put("img", "");
			} else {
				String img = "";
				String[] split = model.getImg().split(";");
				for (String str : split) {
					img = img + HostUtil.getHostIp(request) + str + ";";
				}
				map.put("img", img);
			}

			list.add(map);
		}
		return list;
	}
	
	/**
	 * 修改账单备注信息
	 */
	@RequestMapping(value = "/dbAction_updateAccountNote", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object updateAccountNote(String accountID, String userID, String note) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(userID==null||"".equals(userID)){
			map.put("result", "0");
			map.put("errorMsg","用户id不能为空");
			return map;
		}
		if("".equals(note)||"null".equals(note)){
			map.put("result", "0");
			map.put("errorMsg", "修改失败");
			return map;
		}
		int row = accountDao.updateNote(accountID, userID, note);
		if(row == 1){
			map.put("result", "1");
			map.put("errorMsg", "");
		}else{
			map.put("result", "0");
			map.put("errorMsg", "修改失败");
		}
		
		return map;
	}

	/**
	 * 饼形图
	 */
	@RequestMapping(value = "/dbAction_getPieData", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Map<String, Object>> getPieData(String userID, String type, String kind,
			String startTime, String endTime) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<AccountModel> m = accountDao.getPieData(userID, type, kind, startTime, endTime);
		for(AccountModel model:m){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("type", model.getType());
			map.put("kind", model.getKind());
			map.put("money",model.getMoney());
			list.add(map);
		}
		return list;
	}
	
	/**
	 * 线形图
	 */
	@RequestMapping(value = "/dbAction_getLineData", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Map<String, Object>> getlineData(String userID, String type, String kind,String startTime, String endTime) {				
		List<Map<String, Object>> bList = new ArrayList<Map<String, Object>>();		
		List<AccountModel> accountList = new ArrayList<AccountModel>();
		
		if(kind==null||"".equals(kind)){
			List<String> kindAll = accountDao.getKindAll(userID, type, kind, startTime, endTime);			
			for(String k : kindAll){
				accountList.clear();
				List<Map<String, Object>> sList = new ArrayList<Map<String, Object>>();
				Map<String, Object> map = new HashMap<String, Object>();	
				accountList = accountDao.getLineData(userID, type, k, startTime, endTime);
				for(AccountModel model:accountList){
					Map<String, Object> m = new HashMap<String, Object>();
					m.put("time", model.getTime().split(" ")[0]);
					m.put("money", model.getMoney());
					sList.add(m);
				}
				map.put("kind", k);
				map.put("value", sList);
				bList.add(map);
			}
					
		}else{
			accountList.clear();
			List<Map<String, Object>> sList = new ArrayList<Map<String, Object>>();			
			Map<String, Object> map = new HashMap<String, Object>();
			accountList = accountDao.getLineData(userID, type, kind, startTime, endTime);
			for(AccountModel model:accountList){
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("time", model.getTime().split(" ")[0]);
				m.put("money", model.getMoney());
				sList.add(m);
			}
			map.put("kind", kind);
			map.put("value", sList);		
			bList.add(map);
		}	
		
		return bList;
	}
	
	/**
	 * 修改个人信息
	 */
	@RequestMapping(value = "/dbAction_updateUserInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object updateUserInfo(String userID, String sex, String age,String telphone,String email) {
		Map<String, Object> map = new HashMap<String, Object>();		
		if(userID==null||"".equals(userID)){
			map.put("result", "0");
			map.put("errorMsg","用户id不能为空");
			return map;
		}
		int row = userDao.updateUserInfo(userID, sex, age, telphone, email);
		if(row ==1){
			map.put("result", "1");
			map.put("errorMsg","");
		}else{
			map.put("result", "0");
			map.put("errorMsg","修改失败");
		}
		
		return map;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value = "/", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object sendSMS(String values) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result","1");
		return map;
	}

}
