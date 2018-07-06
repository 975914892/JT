package wc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;
import wc.entity.PositionVO;
import wc.entity.TbCrmAnnouncement;
import wc.entity.TbCrmDepartment;
import wc.entity.TbCrmPosition;
import wc.service.ITbCrmDepartmentService;
import wc.service.ITbCrmPositionService;


@Controller
public class PositionController {
	/*----------------岗位控制--------*/
	@Resource
	public ITbCrmPositionService tbCrmPositionService;
	
	@Resource
	public ITbCrmDepartmentService tbCrmDepartmentService;
	/************************分页查询************************/
	@RequestMapping("/position")
	public String toPostition_jsp(){
		return "position";
		
	}
	@ResponseBody
	@RequestMapping("/positionInfo")
	public void initPostitionInfo(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String rows = request.getParameter("rows");
		String page = request.getParameter("page");
		
		//开始NO
		int startNo =(Integer.parseInt(page)-1)*Integer.parseInt(rows);
		//结束NO
		int endNo=Integer.parseInt(page)*Integer.parseInt(rows)+1;
		List<PositionVO> listA=tbCrmPositionService.findByPage(startNo,endNo);
		int total = tbCrmPositionService.findtotal();
		System.out.println(total);
		System.out.println(listA);
		//写出数据到页面
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		json.put("total", total);
		json.put("rows", listA);
		json.put("message", "初始化数据成功");
		out.println(json.toString());
		out.flush();
		
	}
	/************************添加岗位信息************************/
	
	@RequestMapping("addPositionBefore")
	public String findAddPosition_jsp(HttpServletRequest request) {
		List<TbCrmPosition> listA = tbCrmPositionService.findALL();
		List<TbCrmDepartment> listB = tbCrmDepartmentService.findAll();
		
		request.setAttribute("listA",listA);
		request.setAttribute("listB",listB );
		return "addPosition";
	}
	@RequestMapping("addPositionAfter")
	public void addPosition(TbCrmPosition tcp,HttpServletRequest request,HttpServletResponse response) throws Exception {
		//插入数据
		int row = tbCrmPositionService.insert(tcp);
		//向页面返回数据
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		if(row >0) {
			json.put("message", "添加岗位成功");
		}else {
			json.put("message", "添加岗位失败");
		}
		out.println(json.toString());
		out.flush();
	}
	/************************修改岗位信息************************/
	@RequestMapping("editPositionBefore")
	public String findEditPosition_jsp(int id,HttpServletRequest request) {
		List<TbCrmPosition> listA = tbCrmPositionService.findALL();
		List<TbCrmDepartment> listB = tbCrmDepartmentService.findAll();
		TbCrmPosition tcp = tbCrmPositionService.findById(id);
		request.setAttribute("listA",listA);
		request.setAttribute("listB",listB );
		request.setAttribute("tcp",tcp );
		return "editPosition";
	}
	@RequestMapping("editPositionAfter")
	public void editPosition(TbCrmPosition tcp,HttpServletRequest request,HttpServletResponse response) throws Exception {
		//插入数据
		int row = tbCrmPositionService.update(tcp);
		//向页面返回数据
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		if(row >0) {
			json.put("message", "修改岗位成功");
		}else {
			json.put("message", "修改岗位失败");
		}
		out.println(json.toString());
		out.flush();
	}
	/************************删除岗位信息************************/
	
	@ResponseBody
	@RequestMapping("/deletePosition")
	public void delUser(Integer id,HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println(id);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		int flag =tbCrmPositionService.deleteById(id);
		if(flag>0) {
			json.put("message", "删除岗位成功");
		}else {
			json.put("message", "删除岗位失败");
		}
		System.out.println(flag);
		out.print(json.toString());
		out.flush();
		
		
	}
	/************************岗位信息的模糊查询
	 * @throws Exception ************************/
	@ResponseBody
	@RequestMapping("/positionSerachSelect")
	public void positionSerachSelect(String name,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String rows = request.getParameter("rows");
		String page = request.getParameter("page");
		
		//开始NO
		int startNo =(Integer.parseInt(page)-1)*Integer.parseInt(rows);
		//结束NO
		int endNo=Integer.parseInt(page)*Integer.parseInt(rows)+1;
		
		//根据name查询的数据总条数
		int total = tbCrmPositionService.findByNameTotal(name);
		List<PositionVO> list = tbCrmPositionService.findByNamePage(startNo,endNo,name);
		
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
