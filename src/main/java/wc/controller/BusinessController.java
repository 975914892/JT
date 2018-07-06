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
	
	//����һ��ȫ��json����
	JSONObject json = new JSONObject();
	
	@RequestMapping("/business")
	public String Business() {
		
		return "Business/business";
	}
	//���ݿͻ�����ѯ��Ӧ���̻�
	@RequestMapping("/findBusinessBy")
	public void getBusinessByCustomer() {
		
	}
	
	//���̻��ķ�ҳģ�����ϲ�ѯ
	@RequestMapping("/leadBusiness")
	public void a(@Param("page")int page,@Param("rows")int rows,
			HttpServletRequest req,HttpServletResponse resp) throws IOException {
		
		HttpSession session = req.getSession();
		//��ȡsession�Ự�󶨵ĵ�¼�û�id
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
	//����̻�
	@RequestMapping("/addB")
	public String addBusiness(HttpServletRequest req) {
		List<Map<String, Object>> list1 = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> list2 = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> list3 = new ArrayList<Map<String,Object>>();
		//��ѯ���и�������
		list1 = bService.getAllUsername();
		System.out.println(list1);
		//��ѯ���пͻ���
		list2 = bService.getAllCustomerName();
		//��ѯ������ϵ����
		list3 = bService.getAllContactsName();
		req.setAttribute("userListAtBusiness", list1);
		req.setAttribute("customerListAtBusiness", list2);
		req.setAttribute("contactsListAtBusiness", list3);
		return "Business/addBusiness";
	}
	
	//���ݿͻ�����ѯ��Ӧ���̻�������ϵ����
	@RequestMapping("/littleSelect")
	public void littleSelect(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		String customerId = req.getParameter("customerId");
		System.out.println("��������·�ڸ���������˽�����ʱ�䷢----"+customerId);
		//��ѯ�̻���
		String name = bService.findBNameByCId(Integer.parseInt(customerId));
		//��ѯ��ϵ����
		System.out.println(name);
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		json.put("msg", "��ѯ�ɹ�");
		json.put("name", name);
		out.print(json.toString());
		out.flush();
		out.close();
	}
	
	//����̻�
	@RequestMapping("/addBusiness")
	public void addBusiness(BusinessVO BVO,HttpServletRequest req,HttpServletResponse resp) throws IOException {
		System.out.println("��������̻�����������ʵ����"+BVO);
		HttpSession session = req.getSession();
		Integer id = (Integer) session.getAttribute("userId");
		//����һ���̻����ʵ���࣬��������ʵ�����ֵ����ȥ
		TbCrmBusiness tcb = aaaa(BVO, 1, 0);
		tcb.setCreatotUserId(id);
		int a = bService.addBusiness(tcb);
		if(a>0) {
			json.put("msg", "��ӳɹ�");
		}else {
			json.put("msg", "���ʧ��");
		}
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
		
	}
	
	//����ɾ��
		@RequestMapping("/deleteBusiness")
		public void deleteLeads(String businessIds,Integer userId,HttpServletRequest req,HttpServletResponse resp) throws Exception {
			String method = req.getParameter("method");
			int a = 0;
			//��String����תΪinteger���飬��תΪList<Integer>����
			String[] ids = businessIds.split(",");
			Integer[] idss = new Integer[ids.length];
			for (int i = 0; i <ids.length; i++)
			{
			idss[i] = Integer.parseInt(ids[i]);
			}
			List<Integer> list= Arrays.asList(idss);
			if(method.equals("delete")) {
				//��������ɾ��״̬�ﵽɾ��Ч��
				a = bService.updateDelete(list);
				if(a>0) {
					json.put("flag", "ɾ���ɹ����ѷ������վ������ʱ�鿴");
				}else{
					json.put("flag", "ɾ��ʧ��");
				}
			}
//			if(method.equals("re")) {
//				//�����ָ���������
//				a = bService.reLeads(list);
//				if(a>0) {
//					json.put("flag", "�ָ��ɹ����ѷ��������أ�����ʱ�鿴");
//				}else{
//					json.put("flag", "�ָ�ʧ��");
//				}
//			}
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.print(json.toString());
			out.flush();
			out.close();
			
		}
		//ɾ��һ��
		@RequestMapping("/deleteBusinessOne")
		public void deleteLeadOne(int id,HttpServletResponse resp) throws IOException {
			System.out.println();
			int a = bService.updateDeleteOne(id);
			
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
		
		//��ѯһ���̻�
		@RequestMapping("/editBusiness")
		public String editLead(HttpServletRequest req) throws Exception {
			//��ȡ�󶨵Ĳ���
			String id = req.getParameter("id");
			String method = req.getParameter("method");
			//ת��Ϊlong���ͣ�����ת��ΪLong����
			long l = Long.parseLong(id);
			TbCrmBusiness tcb = bService.getOneById(l);	//��ѯһ������
			BusinessVO BVO = bService.getOneBusinessVO(l);
			req.setAttribute("business", tcb);
			req.setAttribute("BVO", BVO);
			req.setAttribute("id", id);
			if(method.equals("list")) {
				req.setAttribute("msg", "�鿴�̻�");
				return "Business/editBusiness";
			}
			if(method.equals("update")) {
				req.setAttribute("msg", "�޸��̻�");
				return "Business/updateBusiness";
			}
			if(method.equals("push")) {
				req.setAttribute("msg", "�ƽ�");
				return "Business/pushLead";
			}
			return "";
		}
		
		//����һ������
		@RequestMapping("/updateBusiness")
		public void updateLead(BusinessVO BVO,HttpServletRequest req,HttpServletResponse resp) throws Exception {
			System.out.println(BVO);
			
			//��ȡid
			int id = Integer.parseInt(req.getParameter("id"));
			//�Լ���װ�İ�ֵת�Ƶķ������Ա�ʹ��
			TbCrmBusiness tcb =aaaa(BVO,2,id);
			
			//��������
			int a = bService.updateOne(tcb);
			
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
		
		//�̻��ͻ���վ����תվ������
		@RequestMapping("/BP")
		public String LP(HttpServletRequest req) {
			String method = req.getParameter("method");
			if(method.equals("businessRecycleBin")) {
				return "Business/businessRecycleBin";
			}
			
			return "";
		}
		
		//����վ��ҳģ����ѯ
		@RequestMapping("/businessRecycleBin")
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
			List<BusinessVO> list = new ArrayList<BusinessVO>();
			//��ҳģ����ѯ
			list = bService.findBusinessRB(value,field,(page-1)*rows,rows,startTime,endTime);
			//��������վ������
			int total = bService.findBusinessRBCount(value,field,(page-1)*rows,rows,startTime,endTime);
			if(list.size()>0) {
				map.put("rows", list);
				map.put("total", total);
			}
			
			return map;
		}
		
		//�̻�����־
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
				json.put("msg", "�ƽ��ɹ�");
			}else {
				json.put("msg", "�ƽ��ɹ�");
			}
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.print(json.toString());
			out.flush();
			out.close();
			
		}
		
	
		
		
		
		
		
		
		
		
		//���з�װ�ķ�����������ӻ��޸���������
		public TbCrmBusiness aaaa(BusinessVO BVO,int a,int id) {
			//��ȡ������id
			int ownerId = userService.findUserId(BVO.getOwnerName());
			//��ȡ��ϵ��id
			int contactsId = bService.getContactsId(BVO.getContactsName());
			//��ȡ�ͻ�id
			int customerId = bService.getCustomerId(BVO.getCustomerName());
			TbCrmBusiness tcb = new TbCrmBusiness();
			if(id>0) {
				tcb = bService.getOneById(id);
			}
			
			//���ݸ��������ֲ�ѯ��Ӧid
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
			
			System.out.println("������·�����̷��Ҿ����Ƿ���"+tcb);
			return tcb;
		}

}
