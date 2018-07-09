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
	
	/**********************分页查询**************************/
	@RequestMapping("announcement")
	public String findNoticeInfo_jsp() {
		
		return "announcement";
	}
	@ResponseBody
	@RequestMapping("getAnnouncementInfo")
	public void getNoticeInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String rows = request.getParameter("rows");
		String page = request.getParameter("page");
		
		//开始NO
		int startNo =(Integer.parseInt(page)-1)*Integer.parseInt(rows);
		//结束NO
		int endNo=Integer.parseInt(page)*Integer.parseInt(rows)+1;
		List<TbCrmAnnouncement> listA =tbCrmAnnouncementService.findByPage(startNo,endNo);
		int total =tbCrmAnnouncementService.findtotal();
		
		//id查询_系统用户名
		
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
		//数据写出 到页面
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		json.put("total", total);
		json.put("rows", list);
		json.put("message", "初始化数据成功");
		out.println(json.toString());
		out.flush();
	}
	/**********************添加公告**************************/
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
			//获得创建时间
			tca.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
			
			tca.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
			tca.setColor(avo.getColor());
			tca.setDepartment(avo.getDepartment());
			tca.setStatus(avo.getStatus());
			tca.setIsshow(avo.getIsshow());
			tca.setContent(avo.getContent());
			//插入数据
			int row = tbCrmAnnouncementService.insert(tca);
			//向页面返回数据
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			JSONObject json = new JSONObject();
			if(row >0) {
				json.put("message", "添加公告成功");
			}else {
				json.put("message", "添加公告失败");
			}
			out.println(json.toString());
			out.flush();
			
		}
		/**********************修改公告**************************/
		@RequestMapping("editAnnouncementBefore")
		public String findEditAnnouncement_jsp(int id,HttpServletRequest request) {
			
			//根据id查询公告信息
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
			//获得修改时间
			tca.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
			tca.setColor(avo.getColor());
			tca.setDepartment(avo.getDepartment());
			tca.setStatus(avo.getStatus());
			tca.setIsshow(avo.getIsshow());
			tca.setContent(avo.getContent());
			
			//修改数据
			int row = tbCrmAnnouncementService.update(tca);
			//向页面返回数据
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			JSONObject json = new JSONObject();
			if(row >0) {
				json.put("message", "修改公告成功");
			}else {
				json.put("message", "修改公告失败");
			}
			out.println(json.toString());
			out.flush();
		}
		/**********************删除公告**************************/
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
				json.put("message", "删除公告成功");
			}else {
				json.put("message", "删除公告失败");
			}
			System.out.println(flag);
			out.print(json.toString());
			out.flush();
			
			
		}
		
		/**********************模糊查询公告
		 ***************************/
		@RequestMapping("/titleSerachSelect")
		public void titleSerachSelect(String title,HttpServletRequest request,HttpServletResponse response) throws Exception {
			String rows = request.getParameter("rows");
			String page = request.getParameter("page");
			
			//开始NO
			int startNo =(Integer.parseInt(page)-1)*Integer.parseInt(rows);
			//结束NO
			int endNo=Integer.parseInt(page)*Integer.parseInt(rows)+1;
		
			//根据title查询的数据总条数
			int total = tbCrmAnnouncementService.findByTitleTotal(title);
			//根据title 的分页查询
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
			//数据写出 到页面
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			JSONObject json = new JSONObject();
			json.put("total", total);
			json.put("rows", list);
			json.put("message", "初始化数据成功");
			out.println(json.toString());
			out.flush();
			
		}
}
