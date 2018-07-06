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
	/*----------------��λ����--------*/
	@Resource
	public ITbCrmPositionService tbCrmPositionService;
	
	@Resource
	public ITbCrmDepartmentService tbCrmDepartmentService;
	/************************��ҳ��ѯ************************/
	@RequestMapping("/position")
	public String toPostition_jsp(){
		return "position";
		
	}
	@ResponseBody
	@RequestMapping("/positionInfo")
	public void initPostitionInfo(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String rows = request.getParameter("rows");
		String page = request.getParameter("page");
		
		//��ʼNO
		int startNo =(Integer.parseInt(page)-1)*Integer.parseInt(rows);
		//����NO
		int endNo=Integer.parseInt(page)*Integer.parseInt(rows)+1;
		List<PositionVO> listA=tbCrmPositionService.findByPage(startNo,endNo);
		int total = tbCrmPositionService.findtotal();
		System.out.println(total);
		System.out.println(listA);
		//д�����ݵ�ҳ��
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		json.put("total", total);
		json.put("rows", listA);
		json.put("message", "��ʼ�����ݳɹ�");
		out.println(json.toString());
		out.flush();
		
	}
	/************************��Ӹ�λ��Ϣ************************/
	
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
		//��������
		int row = tbCrmPositionService.insert(tcp);
		//��ҳ�淵������
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		if(row >0) {
			json.put("message", "��Ӹ�λ�ɹ�");
		}else {
			json.put("message", "��Ӹ�λʧ��");
		}
		out.println(json.toString());
		out.flush();
	}
	/************************�޸ĸ�λ��Ϣ************************/
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
		//��������
		int row = tbCrmPositionService.update(tcp);
		//��ҳ�淵������
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		if(row >0) {
			json.put("message", "�޸ĸ�λ�ɹ�");
		}else {
			json.put("message", "�޸ĸ�λʧ��");
		}
		out.println(json.toString());
		out.flush();
	}
	/************************ɾ����λ��Ϣ************************/
	
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
			json.put("message", "ɾ����λ�ɹ�");
		}else {
			json.put("message", "ɾ����λʧ��");
		}
		System.out.println(flag);
		out.print(json.toString());
		out.flush();
		
		
	}
	/************************��λ��Ϣ��ģ����ѯ
	 * @throws Exception ************************/
	@ResponseBody
	@RequestMapping("/positionSerachSelect")
	public void positionSerachSelect(String name,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String rows = request.getParameter("rows");
		String page = request.getParameter("page");
		
		//��ʼNO
		int startNo =(Integer.parseInt(page)-1)*Integer.parseInt(rows);
		//����NO
		int endNo=Integer.parseInt(page)*Integer.parseInt(rows)+1;
		
		//����name��ѯ������������
		int total = tbCrmPositionService.findByNameTotal(name);
		List<PositionVO> list = tbCrmPositionService.findByNamePage(startNo,endNo,name);
		
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
