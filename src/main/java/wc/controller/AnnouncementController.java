package wc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mchange.v2.c3p0.impl.NewProxyDatabaseMetaData;

import net.sf.json.JSONObject;
import wc.entity.AnnoouncementVO;
import wc.entity.TbCrmAnnouncement;
import wc.service.ITbCrmAnnouncementService;

@Controller
public class AnnouncementController {
	@Resource
	public ITbCrmAnnouncementService tbCrmAnnouncementService;
	
	/**********************��ҳ��ѯ**************************/
	@RequestMapping("announcement")
	public String findNoticeInfo_jsp() {
		
		return "announcement";
	}
	@ResponseBody
	@RequestMapping("getAnnouncementInfo")
	public void getNoticeInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String rows = request.getParameter("rows");
		String page = request.getParameter("page");
		
		//��ʼNO
		int startNo =(Integer.parseInt(page)-1)*Integer.parseInt(rows);
		//����NO
		int endNo=Integer.parseInt(page)*Integer.parseInt(rows)+1;
		List<TbCrmAnnouncement> listA =tbCrmAnnouncementService.findByPage(startNo,endNo);
		int total =tbCrmAnnouncementService.findtotal();
		
		//id��ѯ_ϵͳ�û���
		
		List<AnnoouncementVO> list=new ArrayList<AnnoouncementVO>();
		if(listA !=null && listA.size()>0) {
			for (TbCrmAnnouncement tca : listA) {
				AnnoouncementVO avo = new AnnoouncementVO();
				String username = tbCrmAnnouncementService.findUsernameById(tca.getUserId());
				System.out.println(username);
				avo.setId(tca.getId());
				avo.setOrderId(tca.getOrderId());
				avo.setUsername(username);
				avo.setTitle(tca.getTitle());
				avo.setCreateTime(tca.getCreateTime());
				avo.setUpdateTime(tca.getUpdateTime());
				avo.setColor(tca.getColor());
				avo.setDepartment(tca.getDepartment());
				avo.setStatus(tca.getStatus());
				avo.setIsshow(tca.getIsshow());
				avo.setContent(tca.getContent());
				list.add(avo);
			}	
			
		}
		//����д�� ��ҳ��
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		json.put("total", total);
		json.put("rows", list);
		json.put("message", "��ʼ�����ݳɹ�");
		out.println(json.toString());
		out.flush();
	}
	/**********************��ӹ���**************************/
		@RequestMapping("addAnnouncementBefore")
		public String findAddAnnouncement_jsp() {
		
			return "addAnnouncement";
		}
		@ResponseBody
		@RequestMapping("addAnnouncementAfter")
		public void AddAnnouncementAfter(AnnoouncementVO avo,HttpServletRequest request,HttpServletResponse response) throws Exception {
			
			
			Long userId = tbCrmAnnouncementService.findByName(avo.getUsername());
			TbCrmAnnouncement tca = new TbCrmAnnouncement();
			tca.setId(avo.getId());
			tca.setOrderId(avo.getOrderId());
			tca.setUserId(userId);
			tca.setTitle(avo.getTitle());
			//��ô���ʱ��
			tca.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
			
			tca.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
			tca.setColor(avo.getColor());
			tca.setDepartment(avo.getDepartment());
			tca.setStatus(avo.getStatus());
			tca.setIsshow(avo.getIsshow());
			tca.setContent(avo.getContent());
			//��������
			int row = tbCrmAnnouncementService.insert(tca);
			//��ҳ�淵������
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			JSONObject json = new JSONObject();
			if(row >0) {
				json.put("message", "��ӹ���ɹ�");
			}else {
				json.put("message", "��ӹ���ʧ��");
			}
			out.println(json.toString());
			out.flush();
			
		}
		/**********************�޸Ĺ���**************************/
		@RequestMapping("editAnnouncementBefore")
		public String findEditAnnouncement_jsp(int id,HttpServletRequest request) {
			
			//����id��ѯ������Ϣ
			TbCrmAnnouncement tca = tbCrmAnnouncementService.findOneById(id);
			String username = tbCrmAnnouncementService.findUsernameById(tca.getUserId());
			
			AnnoouncementVO avo = new AnnoouncementVO();
			avo.setId(tca.getId());
			avo.setOrderId(tca.getOrderId());
			avo.setUsername(username);
			avo.setTitle(tca.getTitle());
			avo.setCreateTime(tca.getCreateTime());
			avo.setUpdateTime(tca.getUpdateTime());
			avo.setColor(tca.getColor());
			avo.setDepartment(tca.getDepartment());
			avo.setStatus(tca.getStatus());
			avo.setIsshow(tca.getIsshow());
			avo.setContent(tca.getContent());
			request.setAttribute("avo", avo);
			return "editAnnouncement";
		}
		@ResponseBody
		@RequestMapping("editAnnouncementAfter")
		public void EditAnnouncement(AnnoouncementVO avo,HttpServletRequest request,HttpServletResponse response) throws Exception {
			Long userId = tbCrmAnnouncementService.findByName(avo.getUsername());
			System.out.println(avo);
			TbCrmAnnouncement tca = new TbCrmAnnouncement();
			tca.setId(avo.getId());
			tca.setOrderId(avo.getOrderId());
			tca.setUserId(userId);
			tca.setTitle(avo.getTitle());
			tca.setCreateTime(avo.getCreateTime());
			//����޸�ʱ��
			tca.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
			tca.setColor(avo.getColor());
			tca.setDepartment(avo.getDepartment());
			tca.setStatus(avo.getStatus());
			tca.setIsshow(avo.getIsshow());
			tca.setContent(avo.getContent());
			
			//�޸�����
			int row = tbCrmAnnouncementService.update(tca);
			//��ҳ�淵������
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			JSONObject json = new JSONObject();
			if(row >0) {
				json.put("message", "�޸Ĺ���ɹ�");
			}else {
				json.put("message", "�޸Ĺ���ʧ��");
			}
			out.println(json.toString());
			out.flush();
		}
		/**********************ɾ������**************************/
		@ResponseBody
		@RequestMapping("/deleteAnnouncement")
		public void delUser(Integer id,HttpServletRequest request,HttpServletResponse response) throws IOException {
			System.out.println(id);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			JSONObject json = new JSONObject();
			int flag =tbCrmAnnouncementService.deleteById(id);
			if(flag>0) {
				json.put("message", "ɾ������ɹ�");
			}else {
				json.put("message", "ɾ������ʧ��");
			}
			System.out.println(flag);
			out.print(json.toString());
			out.flush();
			
			
		}
		
		/**********************ģ����ѯ����
		 ***************************/
		@RequestMapping("/titleSerachSelect")
		public void titleSerachSelect(String title,HttpServletRequest request,HttpServletResponse response) throws Exception {
			String rows = request.getParameter("rows");
			String page = request.getParameter("page");
			
			//��ʼNO
			int startNo =(Integer.parseInt(page)-1)*Integer.parseInt(rows);
			//����NO
			int endNo=Integer.parseInt(page)*Integer.parseInt(rows)+1;
		
			//����title��ѯ������������
			int total = tbCrmAnnouncementService.findByTitleTotal(title);
			//����title �ķ�ҳ��ѯ
			List<TbCrmAnnouncement> listA = tbCrmAnnouncementService.findByTitlePage(startNo,endNo,title);
			
			List<AnnoouncementVO> list=new ArrayList<AnnoouncementVO>();
			if(listA !=null && listA.size()>0) {
				for (TbCrmAnnouncement tca : listA) {
					AnnoouncementVO avo = new AnnoouncementVO();
					String username = tbCrmAnnouncementService.findUsernameById(tca.getUserId());
					System.out.println(username);
					avo.setId(tca.getId());
					avo.setOrderId(tca.getOrderId());
					avo.setUsername(username);
					avo.setTitle(tca.getTitle());
					avo.setCreateTime(tca.getCreateTime());
					avo.setUpdateTime(tca.getUpdateTime());
					avo.setColor(tca.getColor());
					avo.setDepartment(tca.getDepartment());
					avo.setStatus(tca.getStatus());
					avo.setIsshow(tca.getIsshow());
					avo.setContent(tca.getContent());
					list.add(avo);
				}	
				
			}
			//����д�� ��ҳ��
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			JSONObject json = new JSONObject();
			json.put("total", total);
			json.put("rows", list);
			json.put("message", "��ʼ�����ݳɹ�");
			out.println(json.toString());
			out.flush();
			
		}
}
