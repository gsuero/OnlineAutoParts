package com.autoservices.web.action.admin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.impl.Log4jFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.autoservice.commons.Constants;
import com.autoservice.ejbs.business.BusinessHelper;
import com.autoservice.ejbs.business.BusinessHelperLocal;
import com.autoservice.ejbs.user.UserBean;
import com.autoservice.ejbs.user.UserBeanLocal;
import com.autoservice.entities.Part;
import com.autoservice.entities.PartCategory;
import com.autoservice.entities.Vehicle;
import com.autoservice.exceptions.InvalidOperationException;
import com.autoservices.web.session.UserSession;
import com.autoservices.web.util.DatalayerUtil;
import com.autoservices.web.util.Util;
import com.opensymphony.xwork2.ActionSupport;

public class PartCategories extends ActionSupport implements
		ServletResponseAware, ServletRequestAware, ServletContextAware {
//	private static Log logger = LogFactory.getLog(PartCategories.class);
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private HttpServletResponse response;
	private ServletContext context;

	private List<Part> parts;
	private List<Part> orphanParts;
	private List<PartCategory> categories;
	
	public String saveParts() throws Exception {
		
		try {
			//"operation=update&piezaid="+piezaid+"&desc="+desc+"&categoria="+categoria+"&disponible="+disponible
			String operation = request.getParameter("operation");
			if (operation== null || operation.length() < 1)
				operation = request.getParameter("oper") + "";
			
			String piezaid = (request.getParameter("piezaId") + "").trim();
			String piezasid = request.getParameter("piezasId");
			String desc = (request.getParameter("descripcion") + "").trim();
			String categoria = (request.getParameter("categoria") + "").trim();
			String disponible = (request.getParameter("disponible") + "").trim();

			// mnodificando pieza...
			if ("edit".equalsIgnoreCase(operation) || "update".equalsIgnoreCase(operation)) {
				BusinessHelperLocal helper = new BusinessHelper();
				Part pieza = new Part();
				pieza.setPiezaId(Integer.parseInt(piezaid));
				pieza.setCategory(helper.getPartCategory(Integer.parseInt(categoria)));
				pieza.setDescripcion(desc);
				pieza.setDisponible(Integer.parseInt(disponible));
				helper.updatePart(pieza);
			}  else if ("delete".equalsIgnoreCase(operation)) {
				String[] piezas = null;
				if (piezasid != null) {
					piezas = piezasid.trim().split("-"); 
				} else if (piezaid != null) {
					piezas = new String[1];
					piezas[0] = piezaid;
				}
				//logger.info("Borrando : " + piezas.toString());
				
				DatalayerUtil.deleteParts(Util.convertStringArraytoIntArray(piezas));
			} else if ("add".equalsIgnoreCase(operation)) {
				DatalayerUtil.addPart(desc, Integer.parseInt(disponible), Integer.parseInt(categoria));
			} else {
				throw new InvalidOperationException("No se especifico ninguna operacion...");
			}
		} catch (NumberFormatException ex) {
			addActionError("Informacion invalida...");
			return ERROR;
		} catch (NullPointerException ex) {
			addActionError("Un error ha ocurrido..." + ex.getMessage());
			return ERROR;
		} catch (Exception ex) {
			addActionError("Un error ha ocurrido..." + ex.getMessage());
			return ERROR;
		}

		return SUCCESS;
	}
	
	public String saveCategory() throws Exception {
		
		try {
			//"operation=update&piezaid="+piezaid+"&desc="+desc+"&categoria="+categoria+"&disponible="+disponible
			String operation = request.getParameter("operation") + "";
			String categoryid = (request.getParameter("categoryid") + "").trim();
			String categoriesid = request.getParameter("categoriesid");
			String desc = (request.getParameter("desc") + "").trim();
			String parentcategory = (request.getParameter("parentcategoria") + "").trim();
			String status = (request.getParameter("status") + "").trim();

			// mnodificando pieza...
			if ("update".equalsIgnoreCase(operation)) {
				DatalayerUtil.updatePartCategory(Integer.parseInt(categoryid), desc, Integer.parseInt(status), Integer.parseInt(parentcategory));
			}  else if ("delete".equalsIgnoreCase(operation)) {
				String[] categories = null;
				if (categoriesid != null) {
					categories = categoriesid.trim().split("-"); 
				} else if (categoryid != null) {
					categories = new String[1];
					categories[0] = categoryid;
				}
				DatalayerUtil.deletePartCategories(Util.convertStringArraytoIntArray(categories));
			} else if ("add".equalsIgnoreCase(operation)) {
				DatalayerUtil.addPartCategory(desc, Integer.parseInt(status), Integer.parseInt(parentcategory));
			}
		} catch (NumberFormatException ex) {
			addActionError("Informacion invalida...");
			return ERROR;
		} catch (NullPointerException ex) {
			addActionError("Un error ha ocurrido..." + ex.getMessage());
			return ERROR;
		} catch (Exception ex) {
			addActionError("Un error ha ocurrido..." + ex.getMessage());
			return ERROR;
		}

		return SUCCESS;
	}
	
	public String execute() throws Exception {
		BusinessHelper helper = new BusinessHelper();

		setCategories(helper.getPiezasCategories());
		List<Part> piezas = helper.getAllParts();
		
		setOrphanParts(new ArrayList<Part>());
		setParts(new ArrayList<Part>());
		
		Iterator<Part> it = piezas.iterator();
		while(it.hasNext()) {
			Part parte = it.next();
			PartCategory category = parte.getCategory();
			if(category != null && category.getStatus() == Constants.GLOBAL_STATUS_ACTIVATED) {
				parts.add(parte);
			} else {
				orphanParts.add(parte);
			}
		}
		return SUCCESS;
	}

	public List<Part> getParts() {
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;
	}

	public List<PartCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<PartCategory> categories) {
		this.categories = categories;
	}

	public List<Part> getOrphanParts() {
		return orphanParts;
	}

	public void setOrphanParts(List<Part> orphanParts) {
		this.orphanParts = orphanParts;
	}
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
		
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		
	}
	@Override
	public void setServletContext(ServletContext arg0) {
		this.context = arg0;
	}
}
