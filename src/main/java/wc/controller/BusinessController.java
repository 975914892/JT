package wc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;
import wc.entity.AddLeadVO;
import wc.entity.BusinessVO;
import wc.entity.LeadsVO;
import wc.entity.TbCrmBusiness;
import wc.entity.TbCrmLeads;
import wc.service.ITbCrmBusinessService;
import wc.service.ITbSystemUserService;

@RequestMapping("/Business")
@Controller
public class BusinessController {
	
	@Resource
	private ITbCrmBusinessService bService;
	
	@Resource
	private ITbSystemUserService userService;
	
	//创建一个全局json对象
	JSONObject json = new JSONObject();
	
	@RequestMapping("/business")
	public String Business() {
		
		return "Business/business";
	}
	//根据客户名查询对应的商机
	@RequestMapping("/findBusinessBy")
	public void getBusinessByCustomer() {
		
	}
	
	//对商机的分页模糊联合查询
	@RequestMapping("/leadBusiness")
	public void a(@Param("page")int page,@Param("rows")int rows,
			HttpServletRequest req,HttpServletResponse resp) throws IOException {
		
		HttpSession session = req.getSession();
		//获取session会话绑定的登录用户id
		int userId = (Integer) session.getAttribute("userId");
		String field = req.getParameter("field");
		String value = req.getParameter("value");
		System.out.println(field+"------------\t----------"+value);
		if(field == "") {
			field=null;
		}
		if(value == "") {
			value=null;
		}
		List<BusinessVO> listBusiness = bService.findAllBusinessBy(userId,(page-1)*rows,rows,field,value);
		int total = bService.getAllBusinessCount(userId,field,value);
		System.out.println(listBusiness);
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
			json.put("rows", listBusiness);
			json.put("total", total);
		
		out.print(json.toString());
		out.flush();
		out.close();
		
	}
	//添加商机
	@RequestMapping("/addB")
	public String addBusiness(HttpServletRequest req) {
		List<Map<String, Object>> list1 = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> list2 = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> list3 = new ArrayList<Map<String,Object>>();
		//查询所有负责人名
		list1 = bService.getAllUsername();
		System.out.println(list1);
		//查询所有客户名
		list2 = bService.getAllCustomerName();
		//查询所有联系人名
		list3 = bService.getAllContactsName();
		req.setAttribute("userListAtBusiness", list1);
		req.setAttribute("customerListAtBusiness", list2);
		req.setAttribute("contactsListAtBusiness", list3);
		return "Business/addBusiness";
	}
	
	//根据客户名查询对应的商机名和联系人名
	@RequestMapping("/littleSelect")
	public void littleSelect(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		String customerId = req.getParameter("customerId");
		System.out.println("垃圾毒素路口附近的萨洛克金佛秒的时间发----"+customerId);
		//查询商机名
		String name = bService.findBNameByCId(Integer.parseInt(customerId));
		//查询联系人名
		System.out.println(name);
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		json.put("msg", "查询成功");
		json.put("name", name);
		out.print(json.toString());
		out.flush();
		out.close();
	}
	
	//添加商机
	@RequestMapping("/addBusiness")
	public void addBusiness(BusinessVO BVO,HttpServletRequest req,HttpServletResponse resp) throws IOException {
		System.out.println("这是添加商机所创建的新实体类"+BVO);
		HttpSession session = req.getSession();
		Integer id = (Integer) session.getAttribute("userId");
		//创建一个商机表的实体类，用来把新实体类的值传过去
		TbCrmBusiness tcb = aaaa(BVO, 1, 0);
		tcb.setCreatotUserId(id);
		int a = bService.addBusiness(tcb);
		if(a>0) {
			json.put("msg", "添加成功");
		}else {
			json.put("msg", "添加失败");
		}
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
		
	}
	
	//批量删除
		@RequestMapping("/deleteBusiness")
		public void deleteLeads(String businessIds,Integer userId,HttpServletRequest req,HttpServletResponse resp) throws Exception {
			String method = req.getParameter("method");
			int a = 0;
			//将String数组转为integer数组，再转为List<Integer>集合
			String[] ids = businessIds.split(",");
			Integer[] idss = new Integer[ids.length];
			for (int i = 0; i <ids.length; i++)
			{
			idss[i] = Integer.parseInt(ids[i]);
			}
			List<Integer> list= Arrays.asList(idss);
			if(method.equals("delete")) {
				//批量更新删除状态达到删除效果
				a = bService.updateDelete(list);
				if(a>0) {
					json.put("flag", "删除成功，已放入回收站，可随时查看");
				}else{
					json.put("flag", "删除失败");
				}
			}
//			if(method.equals("re")) {
//				//批量恢复到线索池
//				a = bService.reLeads(list);
//				if(a>0) {
//					json.put("flag", "恢复成功，已放入线索池，可随时查看");
//				}else{
//					json.put("flag", "恢复失败");
//				}
//			}
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.print(json.toString());
			out.flush();
			out.close();
			
		}
		//删除一条
		@RequestMapping("/deleteBusinessOne")
		public void deleteLeadOne(int id,HttpServletResponse resp) throws IOException {
			System.out.println();
			int a = bService.updateDeleteOne(id);
			
			if(a>0) {
				json.put("flag", "删除成功，已放入回收站，可随时查看");
			}else{
				json.put("flag", "删除失败");
			}
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.print(json.toString());
			out.flush();
			out.close();
		}
		
		//查询一条商机
		@RequestMapping("/editBusiness")
		public String editLead(HttpServletRequest req) throws Exception {
			//获取绑定的参数
			String id = req.getParameter("id");
			String method = req.getParameter("method");
			//转换为long类型，方便转换为Long类型
			long l = Long.parseLong(id);
			TbCrmBusiness tcb = bService.getOneById(l);	//查询一条数据
			BusinessVO BVO = bService.getOneBusinessVO(l);
			req.setAttribute("business", tcb);
			req.setAttribute("BVO", BVO);
			req.setAttribute("id", id);
			if(method.equals("list")) {
				req.setAttribute("msg", "查看商机");
				return "Business/editBusiness";
			}
			if(method.equals("update")) {
				req.setAttribute("msg", "修改商机");
				return "Business/updateBusiness";
			}
			if(method.equals("push")) {
				req.setAttribute("msg", "推进");
				return "Business/pushLead";
			}
			return "";
		}
		
		//更新一条线索
		@RequestMapping("/updateBusiness")
		public void updateLead(BusinessVO BVO,HttpServletRequest req,HttpServletResponse resp) throws Exception {
			System.out.println(BVO);
			
			//获取id
			int id = Integer.parseInt(req.getParameter("id"));
			//自己封装的把值转移的方法，以便使用
			TbCrmBusiness tcb =aaaa(BVO,2,id);
			
			//更新数据
			int a = bService.updateOne(tcb);
			
			if(a>0) {
				json.put("aa", "更新成功");
			}else {
				json.put("aa","更新失败");
			}
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.print(json.toString());
			out.flush();
			out.close();
		}
		
		//商机和回收站的中转站。。。
		@RequestMapping("/BP")
		public String LP(HttpServletRequest req) {
			String method = req.getParameter("method");
			if(method.equals("businessRecycleBin")) {
				return "Business/businessRecycleBin";
			}
			
			return "";
		}
		
		//回收站分页模糊查询
		@RequestMapping("/businessRecycleBin")
		@ResponseBody
		public Map<String, Object> leadRecycleBin(@Param("page")int page,@Param("rows")int rows,
				HttpServletRequest req,HttpServletResponse resp){
			Map<String, Object> map = new HashedMap();
			
			//接收各个参数并判断
			String field = req.getParameter("field");
			String value = req.getParameter("value");
			String startTime = req.getParameter("startTime");
			String endTime = req.getParameter("endTime");
			if(field == "") {
				field=null;
			}
			if(value == "") {
				value=null;
			}
			if(startTime == "") {
				startTime=null;
			}
			if(endTime == "") {
				endTime=null;
			}
			List<BusinessVO> list = new ArrayList<BusinessVO>();
			//分页模糊查询
			list = bService.findBusinessRB(value,field,(page-1)*rows,rows,startTime,endTime);
			//线索回收站总行数
			int total = bService.findBusinessRBCount(value,field,(page-1)*rows,rows,startTime,endTime);
			if(list.size()>0) {
				map.put("rows", list);
				map.put("total", total);
			}
			
			return map;
		}
		
		//商机的日志
		@RequestMapping("/businessA")
		public String businessLog(HttpServletRequest req) {
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			int id = Integer.parseInt(req.getParameter("id"));
			list = bService.findBusinessLogById(id);
			req.setAttribute("businessLogList", list);
			if(req.getParameter("method").equals("goutong")) {
				
				return "Business/businessLog";
			}
			if(req.getParameter("method").equals("fuze")) {
				
				return "Business/businessLog2";
			}
			if(req.getParameter("method").equals("hetong")) {
				Map<String, Object> map = bService.findContractById(id);
				req.setAttribute("Contract", map);
				return "Business/businessContract";
			}
			if(req.getParameter("method").equals("tuijin")) {
				
				return "Business/businessLog2";
			}
			return "";
		}
		
		@RequestMapping("/putB")
		public String putB(HttpServletRequest req) {
			int id = Integer.parseInt(req.getParameter("id"));
			int statusId = bService.getStatusIdBy(id);
			req.setAttribute("id", id);
			req.setAttribute("statusId", statusId);
			return "Business/putBusiness";
		}
		
		@RequestMapping("putBusiness")
		public void putBusiness(TbCrmBusiness b,HttpServletRequest req,HttpServletResponse resp) throws IOException {
			int id = Integer.parseInt(req.getParameter("id"));
			b.setId((long) id);
			int a = bService.updateStatusBy(b);
			if(a>0) {
				json.put("msg", "推进成功");
			}else {
				json.put("msg", "推进成功");
			}
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.print(json.toString());
			out.flush();
			out.close();
			
		}
		
	
		
		
		
		
		
		
		
		
		//自行封装的方法，用于添加或修改线索数据
		public TbCrmBusiness aaaa(BusinessVO BVO,int a,int id) {
			//获取负责人id
			int ownerId = userService.findUserId(BVO.getOwnerName());
			//获取联系人id
			int contactsId = bService.getContactsId(BVO.getContactsName());
			//获取客户id
			int customerId = bService.getCustomerId(BVO.getCustomerName());
			TbCrmBusiness tcb = new TbCrmBusiness();
			if(id>0) {
				tcb = bService.getOneById(id);
			}
			
			//根据负责人名字查询对应id
			String ownerName = BVO.getOwnerName();
			int userId = userService.findUserId(ownerName);
			
			tcb.setUpdateUserId((long) userId);
			tcb.setOwnerUserId((long) ownerId);
			tcb.setName(BVO.getName());
			tcb.setContactsId((long) contactsId);
			tcb.setOrigin(BVO.getOrigin());
			tcb.setCustomerId((long) customerId);
			tcb.setTotalPrice(BVO.getTotalPrice());
			tcb.setContractAddress(BVO.getContractAddress());
			tcb.setType(BVO.getType());
			tcb.setStatusId((long) BVO.getStatusId());
			tcb.setEstimatePrice((long) BVO.getEstimatePrice());
			tcb.setGainRate((short) BVO.getGainRate());
			tcb.setNextstepTime(BVO.getNextstepTime());
			tcb.setNextstep(BVO.getNextstep());
			tcb.setDescrption(BVO.getDescription());
			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			if(a==1) {
				tcb.setCreateTime(date);
			}
			tcb.setUpdateTime(date);
			
			System.out.println("利达解放路撒旦教佛我绝对是放松"+tcb);
			return tcb;
		}

}
