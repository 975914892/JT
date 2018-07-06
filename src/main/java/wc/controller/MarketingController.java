package wc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;
import wc.entity.TbCrmEmailRecord;
import wc.entity.TbCrmEmailTemplate;
import wc.entity.TbCrmSmsRecord;
import wc.entity.TbCrmSmsTemplate;
//import wc.service.ISmsService;
import wc.service.ITbCrmEmailRecordService;
import wc.service.ITbCrmEmailTemplateService;
import wc.service.ITbCrmSmsRecordService;
import wc.service.ITbCrmSmsTemplateService;

@Controller
public class MarketingController {
	@Resource 
	public ITbCrmSmsRecordService tbCrmSmsRecordService;
	@Resource 
	public ITbCrmSmsTemplateService tbCrmSmsTemplateService;
	@Resource 
	public ITbCrmEmailRecordService tbCrmEmailRecordService;
	@Resource 
	public ITbCrmEmailTemplateService tbCrmEmailTemplateService;
//	@Resource 
//	public ISmsService smsService;
	
	@RequestMapping("/market")
	public String toMarketing_jsp() {
		return "marketing";
	}	
	@ResponseBody
	@RequestMapping("/sendEmailSub")
	public void TestMessage(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		Long userId = tbCrmSmsRecordService.getUserIdByName(username);
		String toAddr = request.getParameter("toAddr");
		String subject = request.getParameter("subject");
		String content = "<html>"+request.getParameter("content")+"</html>";
		
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
		
		ImageHtmlEmail email=new ImageHtmlEmail();
		email.setHostName("smtp.zhidisoft.com"); //服务器名称
		email.setSslSmtpPort("465"); //端口号
		email.setAuthentication("test@zhidisoft.com", "zhidisoft@1200"); //登录服务器的用户，密码
		email.setSSLOnConnect(true); // 设置ssl登录
		email.setSubject(subject); // 邮件标题
		try {
			email.addTo(toAddr); //发送给谁
			email.setFrom("test@zhidisoft.com"); // 从哪个邮箱地址发送
			email.setHtmlMsg(new String(content.getBytes("UTF-8"),"ISO8859-1"));
			String  send = email.send();
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++"+send);
		} catch (EmailException e) {		
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		json.put("message", "发送邮件成功");
		
		out.println(json.toString());
		out.flush();
	}
	//!!!!!!!!!!!!是这个！！！！！！！！！！！！！
//	@ResponseBody
//	@RequestMapping("/sendMessageFrom")
//	public void sendMessageFrom(HttpServletRequest request,HttpServletResponse response) throws IOException {
//		String name1 = request.getParameter("name");
//		String telephone = request.getParameter("telephone");
//		String [] datas =  name1.split(",");
//		System.out.println(datas+"====================");
//		HashMap<String, Object> map = smsService.sendTemplateSMS(telephone, "185313", datas);
//		System.out.println(map+"====================");
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("application/json; charset=UTF-8");
//		PrintWriter out = response.getWriter();
//		JSONObject json = new JSONObject();
//		json.put("message", "发送短信成功");
//		
//		out.println(json.toString());
//		out.flush();
//	}
	/*@ResponseBody
	@RequestMapping("/sendMessageFrom")
	public void sendMessageFrom(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		String telephone = request.getParameter("telephone");
		String content = request.getParameter("content");
		Long userId = tbCrmSmsRecordService.getUserIdByName(username);
		TbCrmSmsRecord tcsr=new TbCrmSmsRecord();
		tcsr.setUserId(userId);
		tcsr.setTelephone(telephone);
		tcsr.setContent(content);
		tcsr.setSendtime(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
	
		
		
		System.err.println(tcsr);
		int row = tbCrmSmsRecordService.insert(tcsr);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		
		if(row >0 ) {
				json.put("message", "发送短信成功");
			}else {
				json.put("message", "发送短信失败");
			}
		out.println(json.toString());
		out.flush();
	}*/
	/*@ResponseBody
	@RequestMapping("/sendEmailSub")
	public void sendEmailSub(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		Long userId = tbCrmSmsRecordService.getUserIdByName(username);
		String toAddr = request.getParameter("toAddr");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		TbCrmEmailRecord tcsr=new TbCrmEmailRecord();
		tcsr.setUserId(userId);
		tcsr.setToAddr(toAddr);
		tcsr.setSubject(subject);
		tcsr.setContent(content);
		tcsr.setEndtime(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
		Short status = 1;
		tcsr.setStatus(status);
		
		System.err.println(tcsr);
		int row = tbCrmEmailRecordService.insert(tcsr);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		
		if(row >0 ) {
				json.put("message", "发送邮件成功");
			}else {
				json.put("message", "发送邮件失败");
			}
		out.println(json.toString());
		out.flush();
	}*/
	@ResponseBody
	@RequestMapping("/inintDgMessage")
	public void initDgMessage(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		String rows = request.getParameter("rows");
		String page = request.getParameter("page");
		
		//开始NO
		int startNo =(Integer.parseInt(page)-1)*Integer.parseInt(rows);
		//结束NO
		int endNo=Integer.parseInt(page)*Integer.parseInt(rows)+1;
		List<TbCrmSmsTemplate> list = tbCrmSmsTemplateService.findByPage(startNo,endNo);
		int total = tbCrmSmsTemplateService.findTotal();
		System.out.println(list);
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
	@ResponseBody
	@RequestMapping("/initDgEmail")
	public void initDgEmail(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		String rows = request.getParameter("rows");
		String page = request.getParameter("page");
		
		//开始NO
		int startNo =(Integer.parseInt(page)-1)*Integer.parseInt(rows);
		//结束NO
		int endNo=Integer.parseInt(page)*Integer.parseInt(rows)+1;
		List<TbCrmEmailTemplate> list = tbCrmEmailTemplateService.findByPage(startNo,endNo);
		int total = tbCrmEmailTemplateService.findTotal();
		System.out.println(list);
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
