package com.autoservices.web.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autoservice.ejbs.business.BusinessHelper;
import com.autoservice.ejbs.business.BusinessHelperLocal;
import com.autoservice.ejbs.cart.ShoppingCart;
import com.autoservice.ejbs.cart.ShoppingCartLocal;
import com.autoservice.ejbs.user.UserBean;
import com.autoservice.ejbs.user.UserBeanLocal;
import com.autoservice.entities.Part;
import com.autoservice.entities.PartCategory;
import com.autoservice.entities.User;
import com.autoservice.entities.Vehicle;
import com.autoservice.entities.vehicles.Marca;
import com.autoservice.entities.vehicles.Modelo;
import com.autoservice.exceptions.AutoException;
import com.autoservice.exceptions.NotExistsException;
import com.autoservice.exceptions.NotExistsUserException;
import com.autoservices.web.action.admin.PartCategories;
import com.autoservices.web.util.lists.Marcas;
import com.autoservices.web.util.objects.CartItem;

public class DatalayerUtil {
	private static HashMap<String,String> MARCAS;
	private static HashMap<String,String> MODELOS = new HashMap<String, String>();
	//private static Log logger = LogFactory.getLog(DatalayerUtil.class);
	public static void deletePart(int piezaid) throws AutoException {
		//logger.info("deleteParts(): deleting with " + piezaid);
		BusinessHelperLocal helper = new BusinessHelper();
		helper.deletePart(piezaid);
	}
	public static boolean deleteParts(int piezaid[]) {
	//	logger.info("deleteParts(): entering with " + piezaid.toString());
		boolean retorno = true;
		for (int a=0; a < piezaid.length; a++) {
			try {
				deletePart(piezaid[a]);
			} catch (AutoException e) {
				e.printStackTrace();
			}
		}
		
		return retorno;
	}
	
	public static int addPart(String description, int available, int category) {
		int retorno = 0;
		BusinessHelperLocal helper = new BusinessHelper();
		try {
			retorno = helper.addPart(description, available, category);
		} catch (AutoException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public static int addPartCategory(String category, int status, int parentCategory) {
		int retorno = 0;
		BusinessHelperLocal helper = new BusinessHelper();
		try {
			retorno = helper.addPartCategory(category, status, parentCategory);
		} catch (AutoException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public static void deletePartCategory(int categoryid) throws AutoException {
		//logger.info("deleteParts(): deleting with " + piezaid);
		BusinessHelperLocal helper = new BusinessHelper();
		helper.deletePartCategory(categoryid);
	}
	public static boolean deletePartCategories(int categoriesid[]) {
	//	logger.info("deleteParts(): entering with " + piezaid.toString());
		boolean retorno = true;
		for (int a=0; a < categoriesid.length; a++) {
			try {
				if (categoriesid[a] > 0)
					deletePartCategory(categoriesid[a]);
			} catch (AutoException e) {
				e.printStackTrace();
			}
		}
		
		return retorno;
	}
	
	public static int updatePartCategory(int categoryid, String category, int status, int parentCategory) {
		int retorno = 0;
		BusinessHelperLocal helper = new BusinessHelper();
		try {
			PartCategory partCategory = new PartCategory();
			partCategory.setCategoryId(categoryid);
			partCategory.setCategory(category);
			if (parentCategory > 0)
				partCategory.setParentCategory(helper.getPartCategory(parentCategory));
			
			partCategory.setStatus(status);
			helper.updatePartCategory(partCategory);
		} catch (AutoException e) {
			e.printStackTrace();
		} catch (NotExistsException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public static List<Part> getPartsByCategory(int categoryid, int page, int rows, String orderBy, String orderAs) {
		List<Part> retorno = null;
		BusinessHelperLocal helper = new BusinessHelper();
		try {
			retorno = helper.getParts(categoryid, page, rows, orderBy, orderAs);
		} catch (NotExistsException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	public static PartCategory getPartCategory(int categoryid) {
		PartCategory category = null;
		
		try {
			BusinessHelperLocal helper = new BusinessHelper();
			category = helper.getPartCategory(categoryid);
		} catch (NotExistsException e) {
			e.printStackTrace();
		}
		
		return category;
	}

	public static Vehicle getVehicle(int vehicleid) {
		Vehicle vehicle = null;
		try {
			if (vehicleid > 0) {
				UserBeanLocal bean = new UserBean();
				vehicle = bean.getVehicle(vehicleid);
			}
		} catch (NotExistsException e) {
			e.printStackTrace();
		} catch (AutoException e) {
			e.printStackTrace();
		}
		
		return vehicle;
	}
	
	public static Long getTotalPartsByCategory(int categoryid) {
		Long retorno = new Long(0);
		BusinessHelperLocal helper = new BusinessHelper();
		try {
			retorno = helper.getPartsCount(categoryid);
		} catch (NotExistsException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	public static User getUser(String login) {
		UserBeanLocal userBean = new UserBean();
		User user= null;
		try {
			user = userBean.getUser(login);
		} catch (NotExistsUserException e) {
			e.printStackTrace();
		}
		return user;
		
	}
	
	public static String getMarcaDescription(String marcaid) throws NotExistsException {
		if (MARCAS == null || MARCAS.size() < 1) {
			BusinessHelperLocal helper = new BusinessHelper();
			List<Marca> marcasList = helper.getMarcas();
			MARCAS = new HashMap<String,String>();
			if (marcasList != null) {
				Iterator<Marca> it = marcasList.iterator();
				while(it.hasNext()) {
					Marca marca = it.next();
					MARCAS.put(marca.getMarcaid()+"",marca.getMarca());
				}
			}
		}

		String marca = MARCAS.get(marcaid);
		return (marca != null ? marca : "unresolved");
	}
	public static String getModeloDescription(int marcaid, int modeloid) {
		BusinessHelperLocal helper = new BusinessHelper();
		try {
			Modelo model = helper.getModelo(marcaid, modeloid);
			return model.getModelo();
		} catch (NotExistsException ex) {
			ex.printStackTrace();
		}
		return "Invalido";
	}
	
}
