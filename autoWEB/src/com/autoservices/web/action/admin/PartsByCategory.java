package com.autoservices.web.action.admin;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.autoservice.ejbs.business.BusinessHelper;
import com.autoservice.ejbs.business.BusinessHelperLocal;
import com.autoservice.entities.Part;
import com.autoservice.entities.PartCategory;
import com.autoservices.web.util.DatalayerUtil;
import com.autoservices.web.util.objects.JQGridObject;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class PartsByCategory extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private List<Part> parts;
	private PartCategory category;
	private String categoryName;
	private int categoryId;
	
	public void parts() throws Exception {
		String catid= (request.getParameter("catid")+"").trim();
		String sord= (request.getParameter("sord")+"").trim();
		String pageStr= (request.getParameter("page")+"").trim();
		String sidx= (request.getParameter("sidx")+"").trim();
		String _search= (request.getParameter("_search")+"").trim();
		String rowsStr= (request.getParameter("rows")+"").trim();
		
		int catidint = Integer.parseInt(catid);
		if (catidint > 0) {
			int page = (pageStr != null ? Integer.parseInt(pageStr) : 0);
			int rows = (rowsStr != null ? Integer.parseInt(rowsStr) : 20);;
			setParts(DatalayerUtil.getPartsByCategory(catidint, page, rows, sidx, sord));
			Gson gson = new Gson();
			
			Long total = DatalayerUtil.getTotalPartsByCategory(catidint);
			JQGridObject object = new JQGridObject();
			
			object.setPage(page);
			object.setRecords(total);
			object.setHowManyRowsPerView(rows);
			object.setRows(getParts());
			String json = gson.toJson(object);
            response.setContentType("application/json");
    		response.setContentLength(json.length());
    		response.getOutputStream().print(json.toString());
    		response.getOutputStream().flush();
		}
	}
	public String execute() throws Exception {
		String catid= (request.getParameter("catid")+"").trim();
		int catidint = Integer.parseInt(catid);
		if (catidint > 0) {
			//PartCate
			setCategory(DatalayerUtil.getPartCategory(catidint));
			setCategoryName((getCategory() != null ? getCategory().getCategory() : "No Existe"));
			setCategoryId((getCategory() != null ? getCategory().getCategoryId() : 0));
	/*		setParts(DatalayerUtil.getPartsByCategory(catidint));
			if (getParts() != null && getParts().size() > 0) {
				setCategory(getParts().get(0).getCategory());
			} else {
	
			}*/
		} 
		return SUCCESS;
	}

	public List<Part> getParts() {
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	public PartCategory getCategory() {
		return category;
	}

	public void setCategory(PartCategory category) {
		this.category = category;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		response = arg0;
		
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
}
