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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;
import wc.entity.AddLeadVO;
import wc.entity.LeadsFieldVO;
import wc.entity.LeadsVO;
import wc.entity.TbCrmLeadLog;
import wc.entity.TbCrmLeads;
import wc.service.ITbCrmLeadsService;
import wc.service.ITbSystemUserService;

@RequestMapping("/Lead")
@Controller
public class LeadsController {
	
	@Resource
	private ITbCrmLeadsService leadsService;
	
	@Resource
	private ITbSystemUserService userService;
	
	
	JSONObject json = new JSONObject();
	
	@RequestMapping("/leads")
	public String listLeads1(HttpServletRequest req) {
		List<LeadsFieldVO> fieldlist = leadsService.getFields();
		System.out.println(fieldlist);
		HttpSession session = req.getSession();
		session.setAttribute("leadsFields", fieldlist);
		return "Lead/leads";
	}
	
	@RequestMapping("/leads2")
	@ResponseBody
	public void listLeads2(@Param("page")int page,@Param("rows")int rows,
			HttpServletRequest req,HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();
		int userId = (Integer) session.getAttribute("userId");//获取session会话绑定的登录用户id
		String field = req.getParameter("field");
		String value = req.getParameter("value");
		System.out.println(field+"------------\t----------"+value);
		if(field == "") {
			field=null;
		}
		if(value == "") {
			value=null;
		}
		//两表联合的新实体类
		List<LeadsVO> listLeads = leadsService.findLeadsAndUser(userId,value,field,(page-1)*rows,rows);
		
		//原一表查询
//		List<TbCrmLeads> listLeads = leadsService.findAllLeads();
		int total = leadsService.getCount(userId,value,field,(page-1)*rows,rows);//总行数
		
		System.out.println(listLeads);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (LeadsVO l : listLeads) {
			String ht = l.getHaveTime();
			Date d1 = df.parse(ht); 
			long a = new Date().getTime()-d1.getTime();
			l.setDay(30-a/ (1000 * 60 * 60 * 24));
		}
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
			json.put("rows", listLeads);
			json.put("total", total);
		
		out.print(json.toString());
		out.flush();
		out.close();
	}
	
	
	@RequestMapping("/addLead")
	public String addLead() {
		
		return "Lead/addLead";
	}
	/**
	 * 添加线索
	 * @author 王聪
	 * @param addLeadVO
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	@RequestMapping("/addLead2")
	public void addLead2(AddLeadVO addLeadVO, HttpServletRequest req,HttpServletResponse resp) throws Exception {
		String method = req.getParameter("method");
		
		//自己封装的把值转移的方法，以便使用
		TbCrmLeads leads =aaaa(addLeadVO,1,0);
		HttpSession session =  req.getSession();
		session.setAttribute("leadVO", addLeadVO);
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		if(method.equals("add")) {
			PrintWriter out = resp.getWriter();
			//开始添加数据
			int a = leadsService.addLead(leads);
			if(a>0) {
				json.put("flag", "添加成功");
				
			}else {
				json.put("flag", "添加失败");
				
			}
			out.print(json.toString());
			out.flush();
			out.close();
		}
		
		
		
	}
	
	
	/**
	 * 批量删除
	 * @author 王聪
	 * @param leadIds
	 * @param userId
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	@RequestMapping("/deleteLeads")
	public void deleteLeads(String leadIds,Integer userId,HttpServletRequest req,HttpServletResponse resp) throws Exception {
		String method = req.getParameter("method");
		int a = 0;
		//将String数组转为integer数组，再转为List<Integer>集合
		String[] ids = leadIds.split(",");
		Integer[] idss = new Integer[ids.length];
		for (int i = 0; i <ids.length; i++)
		{
		idss[i] = Integer.parseInt(ids[i]);
		}
		List<Integer> list= Arrays.asList(idss);
		if(method.equals("delete")) {
			//批量更新删除状态达到删除效果
			a = leadsService.updateDelete(list);
			if(a>0) {
				json.put("flag", "删除成功，已放入回收站，可随时查看");
			}else{
				json.put("flag", "删除失败");
			}
		}
		if(method.equals("re")) {
			//批量恢复到线索池
			a = leadsService.reLeads(list);
			if(a>0) {
				json.put("flag", "恢复成功，已放入线索池，可随时查看");
			}else{
				json.put("flag", "恢复失败");
			}
		}
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
		
	}
	//删除一条
	@RequestMapping("/deleteLeadOne")
	public void deleteLeadOne(int id,HttpServletResponse resp) throws IOException {
		System.out.println();
		int a = leadsService.updateDeleteOne(id);
		
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
	
	//回收站分页模糊查询
	@RequestMapping("/leadRecycleBin")
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
		System.out.println(field+"--------========="+value);
		System.out.println(startTime+"--------========="+endTime);
		List<AddLeadVO> list = new ArrayList<AddLeadVO>();
		//分页模糊查询
		list = leadsService.findLeadRB(value,field,(page-1)*rows,rows,startTime,endTime);
		//线索回收站总行数
		int total = leadsService.findLeadRBCount(value,field,(page-1)*rows,rows,startTime,endTime);
		if(list.size()>0) {
			map.put("rows", list);
			map.put("total", total);
		}
		
		return map;
	}
	//彻底删除
	@RequestMapping("/actuallyDeleteLeadOne")
	public void actuallyDeleteLeadOne(int id,HttpServletResponse resp) throws IOException {
		System.out.println();
		int a = leadsService.actuallyDeleteOne(id);
		
		if(a>0) {
			json.put("flag", "彻底删除成功");
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
	
	
	//批量放入线索池
	@RequestMapping("/putLeads")
	public void putLeads(String leadIds,HttpServletResponse resp) throws IOException{
		//将String数组转为integer数组，再转为List<Integer>集合
		String[] ids = leadIds.split(",");
		Integer[] idss = new Integer[ids.length];
		for (int i = 0; i <ids.length; i++)
		{
		idss[i] = Integer.parseInt(ids[i]);
		}
		List<Integer> list= Arrays.asList(idss);
		//批量更新删除状态达到删除效果
		int a = leadsService.putPool(list);
		if(a>0) {
			json.put("flag", "放入成功，可随时查看");
		}else{
			json.put("flag", "放入失败");
		}
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	
	@RequestMapping("/a")
	public String a() {
		return "Lead/editLead";
	}
	//查询一条线索
	@RequestMapping("/editLead")
	public String editLead(HttpServletRequest req) throws Exception {
		//获取绑定的参数
		String id = req.getParameter("id");
		String method = req.getParameter("method");
		//转换为long类型，方便转换为Long类型
		long l = Long.parseLong(id);
		TbCrmLeads lead = leadsService.getOneById(l);	//查询一条数据
		LeadsVO leadVO = leadsService.getOneLeadVO(l);
		
//		HttpSession session = req.getSession();
//		session.setAttribute("lead", lead);
		req.setAttribute("lead", lead);
		req.setAttribute("leadVO", leadVO);
		req.setAttribute("id", id);
		if(method.equals("list")) {
			req.setAttribute("msg", "查看线索");
			return "Lead/editLead";
		}
		if(method.equals("update")) {
			req.setAttribute("msg", "修改线索");
			return "Lead/updateLead";
		}
		if(method.equals("convert")) {
			req.setAttribute("msg", "转换客户");
			return "Lead/convertLead";
		}
		return "";
	}
	
	//更新一条线索
	@RequestMapping("/updateLead")
	public void updateLead(AddLeadVO addLeadVO,HttpServletRequest req,HttpServletResponse resp) throws Exception {
		System.out.println(addLeadVO);
		
		//获取id
		int id = Integer.parseInt(req.getParameter("id"));
		//自己封装的把值转移的方法，以便使用
		TbCrmLeads leads =aaaa(addLeadVO,2,id);
		
		//更新数据
		int a = leadsService.updateOne(leads);
		
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
	//线索池和回收站的中转站。。。
	@RequestMapping("/LP")
	public String LP(HttpServletRequest req) {
		String method = req.getParameter("method");
		if(method.equals("open")) {
			return "Lead/leadPool";
		}if(method.equals("leadRecycleBin")) {
			return "Lead/leadRecycleBin";
		}
		
		return "";
	}
	//线索池的分页
	@RequestMapping("/leadPool")
	@ResponseBody
	public Map<String, Object> leadPool(@Param("page")int page,@Param("rows")int rows,
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
		System.out.println(field+"--------========="+value);
		System.out.println(startTime+"--------========="+endTime);
		List<AddLeadVO> list = new ArrayList<AddLeadVO>();
		//分页模糊查询
		list = leadsService.findLeadPool(value,field,(page-1)*rows,rows,startTime,endTime);
		//线索池总行数
		int total = leadsService.getLeadPoolCount(value,field,(page-1)*rows,rows,startTime,endTime);
		if(list.size()>0) {
			map.put("rows", list);
			map.put("total", total);
		}
		
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("/leadA")
	public String leadA(HttpServletRequest req) {
		String method = req.getParameter("method");
		String id = req.getParameter("id");
		System.out.println("leadA--------------:"+id);
		req.setAttribute("id", id);
		//查询所有日志备用方法
		List<TbCrmLeadLog> logList = leadsService.getCCByIdAAAA(Integer.parseInt(id));
		//查询对应的拥有者名字并绑定
		LeadsVO l = leadsService.getOneLeadVO(Integer.parseInt(id));
		System.out.println(l);
		
		for (TbCrmLeadLog ll : logList) {
			ll.setOwnerName(l.getOwnerName());
		}
		req.setAttribute("leadLogList", logList);
		if(method.equals("goutong")) {
			return "Lead/leadLog11";
		}
		if(method.equals("fuze")) {
			return "Lead/leadLog22";
		}
		return "";
		
	}
	@RequestMapping("/leadB")
	public String leadB(HttpServletRequest req) {
		String method = req.getParameter("method");
		int id = Integer.parseInt(req.getParameter("id"));
		System.out.println("leadB--------------:"+id);
		if(method.equals("update")) {
			return "";
		}
		
		return "";
	}
	
	
	//暂时废弃的日志分页查询
	@RequestMapping("/leadLog")
	public void leadLog(@Param("page")int page,@Param("rows")int rows,
			HttpServletRequest req,HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		System.out.println("-----leadLog-------"+id);
		//分页
		List<TbCrmLeadLog> logList = leadsService.getCCById(Integer.parseInt(id),(page-1)*rows,rows);
		System.out.println(logList);
		//总行数
		int total = leadsService.getLeadLogCountById(Integer.parseInt(id));
		LeadsVO l = leadsService.getOneLeadVO(Integer.parseInt(id));
		System.out.println(l);
		
		for (TbCrmLeadLog ll : logList) {
			ll.setOwnerName(l.getOwnerName());
		}
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		json.put("rows", logList);
		json.put("total",total);
		out.print(json.toString());
		out.flush();
		out.close();
	}
	
	/**
	 * 自行封装的方法，用于添加或修改线索数据
	 * @param addLeadVO
	 * @param a
	 * @param id
	 * @return
	 */
	public TbCrmLeads aaaa(AddLeadVO addLeadVO,int a,int id) {
		TbCrmLeads leads = new TbCrmLeads();
		if(id>0) {
			leads = leadsService.getOneById(id);
		}
		//地址拼接
		String province = addLeadVO.getProvince();
		String city = addLeadVO.getCity();
		String area = addLeadVO.getArea();
		String street = addLeadVO.getStreet();
		String address = province+city+area+street;
		
		//根据负责人名字查询对应id
		String ownerName = addLeadVO.getOwnerName();
		int userId = userService.findUserId(ownerName);
		
		//传值
		leads.setOwnerUserId((long) userId);	
		leads.setCreatorUserId((long) userId);
		leads.setName(addLeadVO.getName());
		leads.setSource(addLeadVO.getSource());
		leads.setContactsName(addLeadVO.getContactsName());
		leads.setPosition(addLeadVO.getPosition());
		leads.setSaltname(addLeadVO.getSaltname());
		leads.setMobile(addLeadVO.getMobile());
		leads.setEmail(addLeadVO.getEmail());
		leads.setAddress(address);
		leads.setNextstepTime(addLeadVO.getNextstepTime());
		leads.setNextstep(addLeadVO.getNextstep());
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		if(a==1) {
			leads.setCreateTime(date);
			leads.setHaveTime(date);
		}
		leads.setUpdateTime(date);
		
		System.out.println(leads);
		return leads;
	}
	
	@RequestMapping("/s")
	public String s() {
		return "Lead/message";
	}
	
	//发送短信
//	@RequestMapping("/send")
//	public void message(@Param("name")String name,@Param("phone")String phone,HttpServletRequest req,HttpServletResponse resp) throws IOException {
//		String[] n = name.split(",");
//		System.out.println(name+"-------------------"+n.toString());
//		HashMap<String, Object> map = ss.sendTemplateSMS(phone,"185313",n);
//		if(phone!=null && n!=null) {
//			json.put("msg", "发送成功");
//		}else {
//			json.put("msg", "发送失败");
//		}
//		resp.setCharacterEncoding("UTF-8");
//		resp.setContentType("application/json; charset=UTF-8");
//		PrintWriter out = resp.getWriter();
//		out.print(json.toString());
//		out.flush();
//		out.close();
//	}
	
}
