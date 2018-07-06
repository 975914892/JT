package wc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import net.sf.json.JSONObject;
import wc.entity.MenuVO;
import wc.entity.TbCrmMessage;
import wc.entity.TbSystemFunction;
import wc.entity.TbSystemRole;
import wc.entity.TbSystemRoleFunction;
import wc.entity.TbSystemUser;
import wc.entity.TbSystemUserRole;
import wc.service.ITbCrmProductService;
import wc.service.ITbSystemFunctionService;
import wc.service.ITbSystemMessageService;
import wc.service.ITbSystemRoleFunctionService;
import wc.service.ITbSystemRoleService;
import wc.service.ITbSystemUserRoleService;
import wc.service.ITbSystemUserService;
import wc.service.impl.ITbSystemMessageServiceImpl;
import wc.util.EncryptUtil;


@SuppressWarnings("null")
@Controller
public class LoginController {
	
	@Resource
	public ITbSystemFunctionService functionService;
	
	@Resource
	public ITbSystemRoleService roleService;
	
	@Resource
	public ITbSystemUserRoleService urService;
	
	@Resource
	public ITbSystemUserService userService;
	
	@Resource
	public ITbSystemRoleFunctionService rfService;
	
	@Resource
	public ITbSystemMessageService MService;
	
	
	//ע���˳�
	@RequestMapping("/Exitok")
	public void Exit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		System.out.println("�˳�ϵͳ����");
		HttpSession session= request.getSession();
		TbSystemUser user=(TbSystemUser) session.getAttribute("user");
		System.out.println(user);
		session.invalidate();
		System.out.println("���ٺ�"+user);
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	
	//վ����
	@RequestMapping("/message")
	public String messagedo(HttpServletRequest request){
		HttpSession session= request.getSession();
		TbSystemUser user=(TbSystemUser) session.getAttribute("user");
		int userId=user.getUserId();
		session.setAttribute("userId", userId);
		return "messageList";
	}
	
	//�û�����
	@RequestMapping("/userList")
	public String userList(){
		return "userList";
	}
	
	//�û����
	@RequestMapping("/userAdd")
	public String userAdd() {
		return "userinsert";
	}
	
	//�û����� ��Ȩ
	@RequestMapping("/UserForRole")
	public String UserForRole(Integer userId,HttpServletRequest request){
		request.setAttribute("userId", userId);
		return "userForRole";
	}
	
	//��ɫ����
	@RequestMapping("/roleList")
	public String roleList(){
			
		return "roleList";
	}
	
	//��ɫ���
	@RequestMapping("/roleAdd")
	public String roleAdd() {
		return "roleinsert";
	}
	
	//��ɫ���� ��Ȩ
	@RequestMapping("/RoleForFunction")
	public String RoleForFunction(Integer roleId,HttpServletRequest request){
		request.setAttribute("roleId", roleId);
		return "roleForFunction";
	}
	
	//Ȩ�޹���
	@RequestMapping("/functionList")
	public String functionList(){

		return "functionList";
	}
	
	//��ɫ���
	@RequestMapping("/functionAdd")
	public String functionAdd() {
		return "functioninsert";
	}
	
	//��¼
	@RequestMapping("/login")
	public String login(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		String username = req.getParameter("username");
		String pw = req.getParameter("password");
		String password=EncryptUtil.encryptMD5(pw+"aaa");
		HttpSession session =  req.getSession();
		session.setAttribute("oldpassword", pw);
		if(username == null && username.equals("")) {
			req.setAttribute("message", "�û�������Ϊ��!");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
//			resp.sendRedirect(req.getContextPath()+"/login.jsp");
		}
		TbSystemUser user= userService.login(username, password);
		System.out.println(user);
		if(user == null) {
			req.setAttribute("message", "�˺Ż��������");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}else {
			int id = user.getUserId();
			session.setAttribute("user", user);
			session.setAttribute("userId", id);
			List<TbSystemUserRole> list = urService.findRoleByUserId(id);
			List<Integer> roleIds = new ArrayList<Integer>();
			for (TbSystemUserRole userRole : list) {
				roleIds.add(userRole.getRoleId());
			}
			
			if(roleIds.size()>0) {
				List<TbSystemRole> roles = roleService.findRolesByIds(roleIds);
				session.setAttribute("roles", roles);
				 for (int i = 0; i < roles.size(); i++) {
		                session.setAttribute("roleName", roles.get(i).getRoleName());
		            }
				 List<TbSystemFunction> list1 = functionService.findFunctionById(roleIds);
					if(list.size()>0) {
						session.setAttribute("functions", list1);
					}else {req.setAttribute("message", "û���û�Ȩ��");
					req.getRequestDispatcher("login.jsp").forward(req, resp);
				}
			}
			
			
			
		}

		return "menu";
		
		
	}
	
	@ResponseBody
	@RequestMapping("/menu")
	public void Index_menu(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		List<TbSystemFunction> functions = (List<TbSystemFunction>)session.getAttribute("functions");
		
		List<MenuVO> menus = new ArrayList<MenuVO>();
		if(functions != null && functions.size() > 0){
			for(TbSystemFunction fun : functions){
				MenuVO mv = new MenuVO();
				mv.setId(fun.getFuncId().toString());
				mv.setName(fun.getFuncName());
				mv.setUrl(fun.getFuncUrl());
				if(fun.getParentId() != null){
					mv.setpId(fun.getParentId().toString());
					mv.setOpen(false);
				}else{
					mv.setOpen(true);
				}
				mv.setChecked(false);
				menus.add(mv);
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		json.put("data", menus);
		json.put("success", true);
		json.put("message", "���ز˵��ɹ���");
		out.print(json.toString());
		out.flush();
	}
	
	//�û�����  user��ҳ��ѯ
	@ResponseBody
	@RequestMapping("/selectUser")
	public Map<String, Object> getUser(HttpServletRequest request, HttpServletResponse response) {
		
		int total = userService.findTotal();
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		if(pageNo==null) {
			pageNo="1";
		}
		if(pageSize==null) {
			pageSize="10";
		}
		int pageNo1 = Integer.parseInt(pageNo);
		int pageSize1 = Integer.parseInt(pageSize);
		List<TbSystemUser> list = userService.findByPage(pageNo1, pageSize1);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", total);
		
		return map;

	}
	

	//��ɫ����  role��ҳ��ѯ
	@ResponseBody
	@RequestMapping("/selectRole")
	public Map<String, Object> getRole(HttpServletRequest request, HttpServletResponse response) {
		
		int total = roleService.findTotal();
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		if(pageNo==null) {
			pageNo="1";
		}
		if(pageSize==null) {
			pageSize="10";
		}
		int pageNo1 = Integer.parseInt(pageNo);
		int pageSize1 = Integer.parseInt(pageSize);
		List<TbSystemRole> list = roleService.findByPage(pageNo1, pageSize1);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", total);
		
		return map;

	}

	//Ȩ�޹���  function��ҳ��ѯ
	@ResponseBody
	@RequestMapping("/selectFunction")
	public Map<String, Object> getFunction(HttpServletRequest request, HttpServletResponse response) {
		
		int total = functionService.findTotal();
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		if(pageNo==null) {
			pageNo="1";
		}
		if(pageSize==null) {
			pageSize="10";
		}
		int pageNo1 = Integer.parseInt(pageNo);
		int pageSize1 = Integer.parseInt(pageSize);
		List<TbSystemFunction> list = functionService.findByPage(pageNo1, pageSize1);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", total);
		
		return map;

	}
	

	//�û����� user�޸�ǰ��ѯ
	@RequestMapping("/userUpdateList/{userId}")
	public String UserUpdateList(@PathVariable Integer userId,HttpServletRequest request) {
		TbSystemUser list=userService.listByid(userId);
		request.setAttribute("user", list);
		return "useredit";
	}
	

	 //��ɫ���� role�޸�ǰ��ѯ
	@RequestMapping("/roleUpdateList/{roleId}")
	public String RoleUpdateList(@PathVariable Integer roleId,HttpServletRequest request) {
		TbSystemRole list=roleService.listByid(roleId);
		request.setAttribute("role", list);
		return "roleedit";
	}
	
 	//Ȩ�޹��� function�޸�ǰ��ѯ
	@RequestMapping("/functionUpdateList/{functionId}")
	public String FunctionUpdateList(@PathVariable Integer functionId,HttpServletRequest request) {
		TbSystemFunction list=functionService.listByid(functionId);
		request.setAttribute("function", list);
		return "functionedit";
	}
	
	
	//�û����� user�޸�
	@ResponseBody
	@RequestMapping("/userUpdateok")
	public void UserUpdatedo(TbSystemUser user,HttpServletRequest request,HttpServletResponse response) throws IOException {
		String pw = request.getParameter("password");
		String password=EncryptUtil.encryptMD5(pw+"aaa");
		user.setPassword(password);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		user.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
		int ok=userService.updateByPrimaryKey(user);
		System.out.println("�޸��Ƿ�ɹ�"+ok);
		if(ok>0) {
			json.put("message", "�޸ĳɹ���");
		}else {
			json.put("message", "�޸�ʧ�ܣ�");
		}
		out.print(json.toString());
		out.flush();
	} 
	

	//��ɫ���� role�޸�
	@ResponseBody
	@RequestMapping("/roleUpdateok")
	public void ROleUpdatedo(TbSystemRole role,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		role.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
		int ok=roleService.updateByPrimaryKey(role);
		System.out.println("�޸��Ƿ�ɹ�"+ok);
		if(ok>0) {
			json.put("message", "�޸ĳɹ���");
		}else {
			json.put("message", "�޸�ʧ�ܣ�");
		}
		out.print(json.toString());
		out.flush();
	} 

	//Ȩ�޹��� function�޸�
	@ResponseBody
	@RequestMapping("/functionUpdateok")
	public void FunctionUpdatedo(TbSystemFunction function,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		int ok=functionService.updateByPrimaryKey(function);
		System.out.println("�޸��Ƿ�ɹ�"+ok);
		if(ok>0) {
			json.put("message", "�޸ĳɹ���");
		}else {
			json.put("message", "�޸�ʧ�ܣ�");
		}
		out.print(json.toString());
		out.flush();
	}
	
	
	//�û�����  userɾ��
	@ResponseBody
	@RequestMapping("/userDelete/{userId}")
	public boolean userDelete(@PathVariable Integer userId) {
		boolean bl=userService.deleteById(userId);
		System.out.println(userId+"            userɾ������       "+bl);
		return bl;
	}
	

    //��ɫ����  roleɾ��
	@ResponseBody
	@RequestMapping("/roleDelete/{roleId}")
	public boolean roleDelete(@PathVariable Integer roleId) {
		boolean bl=roleService.deleteById(roleId);
		return bl;
	}
	
    //Ȩ�޹���  functionɾ��
	@ResponseBody
	@RequestMapping("/functionDelete/{functionId}")
	public boolean functionDelete(@PathVariable Integer functionId) {
		boolean bl=functionService.deleteById(functionId);
		return bl;
	}
	
	//��Ȩ--�û���Ȩ user-role
	@ResponseBody
	@RequestMapping("/roleFindByPage")
	public void roleFindByPage(Integer userId,Integer page,Integer rows,HttpServletResponse response) throws IOException{
		if(page == null){
			page = 1;
		}
		if(rows == null){
			rows = 10;
		}
		//��ҳ��ѯ
		List<TbSystemRole> list = roleService.findByPage(page,rows);
		//�ܼ�¼��
		int total = roleService.findTotal();
		//�����û�id��ѯ���û����������Ľ�ɫ
		List<TbSystemRole> roles = new ArrayList<TbSystemRole>();
		List<TbSystemUserRole> urs= urService.findByUserId(userId);
		for(TbSystemRole r : list){
			for(TbSystemUserRole ur : urs){
				int roleId = ur.getRoleId();
				if(roleId == r.getRoleId()){
					r.setFlag(true);
				}
			}
			roles.add(r);
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		json.put("totalRows", total);
		json.put("result", roles);
		json.put("success", true);
		json.put("message", "���ز˵��ɹ���");
		out.print(json.toString());
		out.flush();
	}

	@ResponseBody
	@RequestMapping("/editUserRole")
	public void editUserRole(String roleIds,Integer userId,HttpServletResponse response) throws IOException{
		int result = 0;
		if(roleIds!=null && !"".equals(roleIds)){
			String[] ids = roleIds.split(",");
			//�ȸ���userIdɾ��userRole������������
			urService.deleteByUserId(userId);
			for(String id : ids){
				if(id != null && !"".equals(id)){
					TbSystemUserRole ur = new TbSystemUserRole();
					ur.setUserId(userId);
					ur.setRoleId(Integer.parseInt(id));
					result += urService.insert(ur);
				}
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		if(result > 0){
			json.put("success", true);
			json.put("message", "��Ȩ�ɹ���");
		}else{
			json.put("success", false);
			json.put("message", "��Ȩʧ�ܣ�");
		}
		out.print(json.toString());
		out.flush();
	}
	
	//��Ȩ--��ɫ��Ȩ role-function
	@ResponseBody
	@RequestMapping("/functionFindByPage")
	public void functionFindByPage(Integer roleId,Integer page,Integer rows,HttpServletResponse response) throws IOException{
		if(page == null){
			page = 1;
		}
		if(rows == null){
			rows = 10;
		}
		//��ҳ��ѯ
		List<TbSystemFunction> list = functionService.findByPage(page,rows);
		//�ܼ�¼��
		int total = functionService.findTotal();
		//�����û�id��ѯ���û����������Ľ�ɫ
		List<TbSystemFunction> functions = new ArrayList<TbSystemFunction>();
		System.out.println("2222222222222222222");
		List<TbSystemRoleFunction> rfs= rfService.findByRoleId(roleId);
		for(TbSystemFunction f : list){
			for(TbSystemRoleFunction rf : rfs){
				int funcId = rf.getFuncId();
				if(funcId == f.getFuncId()){
					f.setFlag(true);
				}
			}
			functions.add(f);
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		json.put("totalRows", total);
		json.put("result", list);
		json.put("success", true);
		json.put("message", "���ز˵��ɹ���");
		out.print(json.toString());
		out.flush();
	}

	@ResponseBody
	@RequestMapping("/editRoleFunction")
	public void editRoleFunction(String funcIds,Integer roleId,HttpServletResponse response) throws IOException{
		int result = 0;
		if(funcIds!=null && !"".equals(funcIds)){
			String[] ids = funcIds.split(",");
			//�ȸ���userIdɾ��userRole������������
			rfService.deleteByRoleId(roleId);
			for(String id : ids){
				if(id != null && !"".equals(id)){
					TbSystemRoleFunction rf = new TbSystemRoleFunction();
					rf.setRoleId(roleId);
					rf.setFuncId(Integer.parseInt(id));
					result += rfService.insert(rf);
				}
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		if(result > 0){
			json.put("success", true);
			json.put("message", "��Ȩ�ɹ���");
		}else{
			json.put("success", false);
			json.put("message", "��Ȩʧ�ܣ�");
		}
		out.print(json.toString());
		out.flush();
	}
	
	//�û�����  User���
	@ResponseBody
	@RequestMapping("/insertUser")
	public void UserInsert(TbSystemUser user,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		user.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
		String pw=user.getPassword();
		String password=EncryptUtil.encryptMD5(pw+"aaa");
		user.setPassword(password);
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		int flag=userService.insert(user);
		if(flag>0) {
			json.put("message", "��ӳɹ�");
		}else {
			json.put("message", "���ʧ�ܣ��û��������ظ�");
		}
		System.out.println(flag);
		out.print(json.toString());
		out.flush();
	}
	
	//��ɫ����  Role���
	@ResponseBody
	@RequestMapping("/insertRole")
	public void RoleInsert(TbSystemRole role,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		role.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
		short status=1;
		role.setStatus(status);
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		int flag=roleService.insert(role);
		if(flag>0) {
			json.put("message", "��ӳɹ�");
		}else {
			json.put("message", "���ʧ�ܣ�");
		}
		System.out.println(flag);
		out.print(json.toString());
		out.flush();
	}
	
	//Ȩ�޹���  Function���
	@ResponseBody
	@RequestMapping("/insertFunction")
	public void FunctionInsert(TbSystemFunction function,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		int flag=functionService.insert(function);
		if(flag>0) {
			json.put("message", "��ӳɹ�");
		}else {
			json.put("message", "���ʧ�ܣ�");
		}
		System.out.println(flag);
		out.print(json.toString());
		out.flush();
	}
	
	//վ����
	
	//д����ת
	@RequestMapping("/messageAdd")
	public String messageAdd() {
		return "messageinsert";
	}
	
	//message���
	@ResponseBody
	@RequestMapping("/insertMessage")
	public void MessageInsert(TbCrmMessage message,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		HttpSession session= request.getSession();
		int userId=(Integer) session.getAttribute("userId");
		message.setFromUserId(userId);
		message.setSendTime(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
		message.setReadTime(null);
		short status=1;
		message.setStatus(status);
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		int flag=MService.insert(message);
		if(flag>0) {
			json.put("message", "���ͳɹ�");
		}else {
			json.put("message", "����ʧ�ܣ�");
		}
		System.out.println(flag);
		out.print(json.toString());
		out.flush();
	}
	
	//��ѯ�Լ�д�����˵���
	@ResponseBody
	@RequestMapping("/getMessageBM")
	public Map<String, Object> getMessageBM(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session= request.getSession();
		int userId=(Integer) session.getAttribute("userId");
		int total = MService.findBmTotal(userId);
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		if(pageNo==null) {
			pageNo="1";
		}
		if(pageSize==null) {
			pageSize="10";
		}
		int pageNo1 = Integer.parseInt(pageNo);
		int pageSize1 = Integer.parseInt(pageSize);
		List<TbCrmMessage> list = MService.selectBymyself(userId, pageNo1, pageSize1);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", total);
		
		return map;

	}
	//��ѯ����д���Լ�����
	@ResponseBody
	@RequestMapping("/getMessageBO")
	public Map<String, Object> getMessageBO(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session= request.getSession();
		int userId=(Integer) session.getAttribute("userId");
		int total = MService.findBoTotal(userId);
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		if(pageNo==null) {
			pageNo="1";
		}
		if(pageSize==null) {
			pageSize="10";
		}
		int pageNo1 = Integer.parseInt(pageNo);
		int pageSize1 = Integer.parseInt(pageSize);
		List<TbCrmMessage> list = MService.selectByother(userId, pageNo1, pageSize1);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", total);
		
		return map;

	}
	 //�鿴�Լ�д����
	@RequestMapping("/messagefromlook/{id}")
	public String messagefromlook(@PathVariable Integer id,HttpServletRequest request) {
		TbCrmMessage message=MService.selectByid(id);
		request.setAttribute("message", message);
		int toUserId=message.getToUserId();
		TbSystemUser list=userService.listByid(toUserId);
		String toUserName=list.getUsername();
		request.setAttribute("toUserName", toUserName);
		return "messagefromuserview";
	}
	
	 //�鿴����д����
	@RequestMapping("/messagetolook/{id}")
	public String messagetolook(@PathVariable Integer id,HttpServletRequest request) {
		TbCrmMessage message=MService.selectByid(id);
		request.setAttribute("message", message);
		int fromUserId=message.getFromUserId();
		TbSystemUser list=userService.listByid(fromUserId);
		String fromUserName=list.getUsername();
		request.setAttribute("fromUserName", fromUserName);
		return "messagetoUserview";
	}
	
	//message�����Լ�д����
	@ResponseBody
	@RequestMapping("/messagelookmyok")
	public void messagelookok(TbCrmMessage message,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
//		message.setReadTime(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
		int ok=MService.updateByPrimaryKey(message);
		if(ok>0) {
			json.put("message1", "�Ķ���ϣ�");
		}else {
			json.put("message1", "�Ķ�δ�꣡");
		}
		out.print(json.toString());
		out.flush();
	}
	
	//message�����յ�����
	@ResponseBody
	@RequestMapping("/messagelookotherok")
	public void messagelookotherok(TbCrmMessage message,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		message.setReadTime(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
		int ok=MService.updateByPrimaryKey(message);
		if(ok>0) {
			json.put("message1", "�Ķ���ϣ�");
		}else {
			json.put("message1", "�Ķ�δ�꣡");
		}
		out.print(json.toString());
		out.flush();
	}
	
    //messageɾ��
	@ResponseBody
	@RequestMapping("/messageDelete/{id}")
	public boolean messageDelete(@PathVariable Integer id) {
		boolean bl=MService.deleteById(id);
		return bl;
	}
	
	
}
