package wc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import net.sf.json.JSONObject;
import wc.entity.TbCrmProduct;
import wc.entity.TbCrmProductCategory;
import wc.entity.TbSystemUser;
import wc.service.ITbCrmProductCategoryService;
import wc.service.ITbCrmProductService;

@Controller
public class ProductController {

	@Resource
	public ITbCrmProductService proService;
	
	@Resource
	public ITbCrmProductCategoryService proCateService;
	
	//产品管理
	@RequestMapping("/product")
	public String product(){
		return "productList";
	}
	//产品分类
	@RequestMapping("/productCategory")
	public String productCategoryList(){
		return "productCategoryList";
	}
	
	//产品添加
	@RequestMapping("/productAdd")
	public String productAdd(HttpServletRequest request) {
		List<TbCrmProductCategory> proCateList=proCateService.selectAll();
		request.setAttribute("proCateList", proCateList);
		return "productinsert";
	}
	
	//产品分类添加
	@RequestMapping("/productCategoryAdd")
	public String productCategoryAdd() {
		return "productCategoryinsert";
	}
	
	//产品修改前查询
	@RequestMapping("/productUpdateList/{id}")
	public String productUpdateList(@PathVariable Integer id,HttpServletRequest request) {
		TbCrmProduct product=proService.listByid(id);
		request.setAttribute("product", product);
		List<TbCrmProductCategory> proCateList=proCateService.selectAll();
		request.setAttribute("proCateList", proCateList);
		return "productedit";
	}
	//产品修改
	@ResponseBody
	@RequestMapping("/productUpdate")
	public void productUpdatedo(TbCrmProduct product,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		product.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
		int ok=proService.updateByPrimaryKey(product);
		System.out.println("修改是否成功"+ok);
		if(ok>0) {
			json.put("message", "修改成功！");
		}else {
			json.put("message", "修改失败！");
		}
		out.print(json.toString());
		out.flush();
	} 
	
	//产品分类修改前查询
	@RequestMapping("/productcategoryUpdateList/{id}")
	public String productcategoryUpdateList(@PathVariable Integer id,HttpServletRequest request) {
		TbCrmProductCategory procate=proCateService.listByid(id);
		request.setAttribute("procate", procate);
		return "productcategoryedit";
	}
	//产品分类修改
	@ResponseBody
	@RequestMapping("/productcategoryUpdate")
	public void productcategoryUpdate(TbCrmProductCategory productcategory,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		int ok=proCateService.updateByPrimaryKey(productcategory);
		System.out.println("修改是否成功"+ok);
		if(ok>0) {
			json.put("message", "修改成功！");
		}else {
			json.put("message", "修改失败！");
		}
		out.print(json.toString());
		out.flush();
	} 
	
	//产品管理  分页查询
	@ResponseBody
	@RequestMapping("/selectProduct")
	public Map<String, Object> getProduct(HttpServletRequest request, HttpServletResponse response) {
		
		int total = proService.findTotal();
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
		List<TbCrmProduct> list = proService.findByPage(pageNo1, pageSize1);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", total);
		
		return map;

	}
	
	//添加产品
	@ResponseBody
	@RequestMapping("/insertProduct")
	public void productInsert(TbCrmProduct product,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		HttpSession session= request.getSession();
		TbSystemUser user=(TbSystemUser) session.getAttribute("user");
		int userId=user.getUserId();
		product.setCreatorUserId(userId);
		product.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
		product.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		int flag=proService.insert(product);
		if(flag>0) {
			json.put("message", "添加成功");
		}else {
			json.put("message", "添加失败！");
		}
		System.out.println(flag);
		out.print(json.toString());
		out.flush();
	}
	
    //删除产品
	@ResponseBody
	@RequestMapping("/productDelete/{id}")
	public boolean productDelete(@PathVariable Integer id) {
		boolean bl=proService.deleteById(id);
		return bl;
	}
	
	
	//产品分类管理  分页查询
	@ResponseBody
	@RequestMapping("/selectProductCategory")
	public Map<String, Object> getProductCategory(HttpServletRequest request, HttpServletResponse response) {
		
		int total = proCateService.findTotal();
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
		List<TbCrmProductCategory> list = proCateService.findByPage(pageNo1, pageSize1);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", total);
		
		return map;

	}
	
	//添加产品
	@ResponseBody
	@RequestMapping("/insertProductCategory")
	public void productCategoryInsert(TbCrmProductCategory productCategory,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		int flag=proCateService.insert(productCategory);
		if(flag>0) {
			json.put("message", "添加成功");
		}else {
			json.put("message", "添加失败！");
		}
		System.out.println(flag);
		out.print(json.toString());
		out.flush();
	}
	
	
    //删除产品分类
	@ResponseBody
	@RequestMapping("/productCategoryDelete/{id}")
	public boolean productCategoryDelete(@PathVariable Integer id) {
		boolean bl=proCateService.deleteById(id);
		return bl;
	}
	
	//产品表-模糊查询
	@ResponseBody
	@RequestMapping("/nameSerachSelect/{name}")
	public Map<String, Object> nameSerachSelect(@PathVariable String name,HttpServletRequest request, HttpServletResponse response) {
		int total = proService.findByNameTotal(name);
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
		List<TbCrmProduct> list = proService.findByNamePage(pageNo1, pageSize1, name);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", total);
		
		return map;
	}
	
}
