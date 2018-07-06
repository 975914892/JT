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
//import wc.service.ISmsService;
import wc.service.ITbCrmLeadsService;
import wc.service.ITbSystemUserService;

@RequestMapping("/Lead")
@Controller
public class LeadsController {
	
	@Resource
	private ITbCrmLeadsService leadsService;
	
	@Resource
	private ITbSystemUserService userService;
	
//	@Resource
//	private ISmsService ss;
	
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
		int userId = (Integer) session.getAttribute("userId");//��ȡsession�Ự�󶨵ĵ�¼�û�id
		String field = req.getParameter("field");
		String value = req.getParameter("value");
		System.out.println(field+"------------\t----------"+value);
		if(field == "") {
			field=null;
		}
		if(value == "") {
			value=null;
		}
		//�������ϵ���ʵ����
		List<LeadsVO> listLeads = leadsService.findLeadsAndUser(userId,value,field,(page-1)*rows,rows);
		
		//ԭһ���ѯ
//		List<TbCrmLeads> listLeads = leadsService.findAllLeads();
		int total = leadsService.getCount(userId,value,field,(page-1)*rows,rows);//������
		
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
	//�������
	@RequestMapping("/addLead2")
	public void addLead2(AddLeadVO addLeadVO, HttpServletRequest req,HttpServletResponse resp) throws Exception {
		String method = req.getParameter("method");
		
		//�Լ���װ�İ�ֵת�Ƶķ������Ա�ʹ��
		TbCrmLeads leads =aaaa(addLeadVO,1,0);
		HttpSession session =  req.getSession();
		session.setAttribute("leadVO", addLeadVO);
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		if(method.equals("add")) {
			PrintWriter out = resp.getWriter();
			//��ʼ�������
			int a = leadsService.addLead(leads);
			if(a>0) {
				json.put("flag", "��ӳɹ�");
				
			}else {
				json.put("flag", "���ʧ��");
				
			}
			out.print(json.toString());
			out.flush();
			out.close();
		}
		
		
		
	}
	
	
	//����ɾ��
	@RequestMapping("/deleteLeads")
	public void deleteLeads(String leadIds,Integer userId,HttpServletRequest req,HttpServletResponse resp) throws Exception {
		String method = req.getParameter("method");
		int a = 0;
		//��String����תΪinteger���飬��תΪList<Integer>����
		String[] ids = leadIds.split(",");
		Integer[] idss = new Integer[ids.length];
		for (int i = 0; i <ids.length; i++)
		{
		idss[i] = Integer.parseInt(ids[i]);
		}
		List<Integer> list= Arrays.asList(idss);
		if(method.equals("delete")) {
			//��������ɾ��״̬�ﵽɾ��Ч��
			a = leadsService.updateDelete(list);
			if(a>0) {
				json.put("flag", "ɾ���ɹ����ѷ������վ������ʱ�鿴");
			}else{
				json.put("flag", "ɾ��ʧ��");
			}
		}
		if(method.equals("re")) {
			//�����ָ���������
			a = leadsService.reLeads(list);
			if(a>0) {
				json.put("flag", "�ָ��ɹ����ѷ��������أ�����ʱ�鿴");
			}else{
				json.put("flag", "�ָ�ʧ��");
			}
		}
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
		
	}
	//ɾ��һ��
	@RequestMapping("/deleteLeadOne")
	public void deleteLeadOne(int id,HttpServletResponse resp) throws IOException {
		System.out.println();
		int a = leadsService.updateDeleteOne(id);
		
		if(a>0) {
			json.put("flag", "ɾ���ɹ����ѷ������վ������ʱ�鿴");
		}else{
			json.put("flag", "ɾ��ʧ��");
		}
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	
	//����վ��ҳģ����ѯ
	@RequestMapping("/leadRecycleBin")
	@ResponseBody
	public Map<String, Object> leadRecycleBin(@Param("page")int page,@Param("rows")int rows,
			HttpServletRequest req,HttpServletResponse resp){
		Map<String, Object> map = new HashedMap();
		//���ո����������ж�
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
		//��ҳģ����ѯ
		list = leadsService.findLeadRB(value,field,(page-1)*rows,rows,startTime,endTime);
		//��������վ������
		int total = leadsService.findLeadRBCount(value,field,(page-1)*rows,rows,startTime,endTime);
		if(list.size()>0) {
			map.put("rows", list);
			map.put("total", total);
		}
		
		return map;
	}
	//����ɾ��
	@RequestMapping("/actuallyDeleteLeadOne")
	public void actuallyDeleteLeadOne(int id,HttpServletResponse resp) throws IOException {
		System.out.println();
		int a = leadsService.actuallyDeleteOne(id);
		
		if(a>0) {
			json.put("flag", "����ɾ���ɹ�");
		}else{
			json.put("flag", "ɾ��ʧ��");
		}
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	
	
	//��������������
	@RequestMapping("/putLeads")
	public void putLeads(String leadIds,HttpServletResponse resp) throws IOException{
		//��String����תΪinteger���飬��תΪList<Integer>����
		String[] ids = leadIds.split(",");
		Integer[] idss = new Integer[ids.length];
		for (int i = 0; i <ids.length; i++)
		{
		idss[i] = Integer.parseInt(ids[i]);
		}
		List<Integer> list= Arrays.asList(idss);
		//��������ɾ��״̬�ﵽɾ��Ч��
		int a = leadsService.putPool(list);
		if(a>0) {
			json.put("flag", "����ɹ�������ʱ�鿴");
		}else{
			json.put("flag", "����ʧ��");
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
	//��ѯһ������
	@RequestMapping("/editLead")
	public String editLead(HttpServletRequest req) throws Exception {
		//��ȡ�󶨵Ĳ���
		String id = req.getParameter("id");
		String method = req.getParameter("method");
		//ת��Ϊlong���ͣ�����ת��ΪLong����
		long l = Long.parseLong(id);
		TbCrmLeads lead = leadsService.getOneById(l);	//��ѯһ������
		LeadsVO leadVO = leadsService.getOneLeadVO(l);
		
//		HttpSession session = req.getSession();
//		session.setAttribute("lead", lead);
		req.setAttribute("lead", lead);
		req.setAttribute("leadVO", leadVO);
		req.setAttribute("id", id);
		if(method.equals("list")) {
			req.setAttribute("msg", "�鿴����");
			return "Lead/editLead";
		}
		if(method.equals("update")) {
			req.setAttribute("msg", "�޸�����");
			return "Lead/updateLead";
		}
		if(method.equals("convert")) {
			req.setAttribute("msg", "ת���ͻ�");
			return "Lead/convertLead";
		}
		return "";
	}
	
	//����һ������
	@RequestMapping("/updateLead")
	public void updateLead(AddLeadVO addLeadVO,HttpServletRequest req,HttpServletResponse resp) throws Exception {
		System.out.println(addLeadVO);
		
		//��ȡid
		int id = Integer.parseInt(req.getParameter("id"));
		//�Լ���װ�İ�ֵת�Ƶķ������Ա�ʹ��
		TbCrmLeads leads =aaaa(addLeadVO,2,id);
		
		//��������
		int a = leadsService.updateOne(leads);
		
		if(a>0) {
			json.put("aa", "���³ɹ�");
		}else {
			json.put("aa","����ʧ��");
		}
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	//�����غͻ���վ����תվ������
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
	//�����صķ�ҳ
	@RequestMapping("/leadPool")
	@ResponseBody
	public Map<String, Object> leadPool(@Param("page")int page,@Param("rows")int rows,
			HttpServletRequest req,HttpServletResponse resp){
		Map<String, Object> map = new HashedMap();
		//���ո����������ж�
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
		//��ҳģ����ѯ
		list = leadsService.findLeadPool(value,field,(page-1)*rows,rows,startTime,endTime);
		//������������
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
		//��ѯ������־���÷���
		List<TbCrmLeadLog> logList = leadsService.getCCByIdAAAA(Integer.parseInt(id));
		//��ѯ��Ӧ��ӵ�������ֲ���
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
	
	
	//��ʱ��������־��ҳ��ѯ
	@RequestMapping("/leadLog")
	public void leadLog(@Param("page")int page,@Param("rows")int rows,
			HttpServletRequest req,HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		System.out.println("-----leadLog-------"+id);
		//��ҳ
		List<TbCrmLeadLog> logList = leadsService.getCCById(Integer.parseInt(id),(page-1)*rows,rows);
		System.out.println(logList);
		//������
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
	
	//���з�װ�ķ�����������ӻ��޸���������
	public TbCrmLeads aaaa(AddLeadVO addLeadVO,int a,int id) {
		TbCrmLeads leads = new TbCrmLeads();
		if(id>0) {
			leads = leadsService.getOneById(id);
		}
		//��ַƴ��
		String province = addLeadVO.getProvince();
		String city = addLeadVO.getCity();
		String area = addLeadVO.getArea();
		String street = addLeadVO.getStreet();
		String address = province+city+area+street;
		
		//���ݸ��������ֲ�ѯ��Ӧid
		String ownerName = addLeadVO.getOwnerName();
		int userId = userService.findUserId(ownerName);
		
		//��ֵ
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
	
//	！！！！！！！！！！！这个！！！！
//	//���Ͷ���
//	@RequestMapping("/send")
//	public void message(@Param("name")String name,@Param("phone")String phone,HttpServletRequest req,HttpServletResponse resp) throws IOException {
//		String[] n = name.split(",");
//		System.out.println(name+"-------------------"+n.toString());
//		HashMap<String, Object> map = ss.sendTemplateSMS(phone,"185313",n);
//		if(phone!=null && n!=null) {
//			json.put("msg", "���ͳɹ�");
//		}else {
//			json.put("msg", "����ʧ��");
//		}
//		resp.setCharacterEncoding("UTF-8");
//		resp.setContentType("application/json; charset=UTF-8");
//		PrintWriter out = resp.getWriter();
//		out.print(json.toString());
//		out.flush();
//		out.close();
//	}
	
}
