package com.autoservice.ejbs.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import com.autoservice.entities.Ciudad;
import com.autoservice.entities.Holiday;
import com.autoservice.entities.MailTemplate;
import com.autoservice.entities.MultipleType;
import com.autoservice.entities.Pais;
import com.autoservice.entities.Part;
import com.autoservice.entities.PartCategory;
import com.autoservice.entities.UserMailConfirmation;
import com.autoservice.entities.UserSession;
import com.autoservice.entities.vehicles.Marca;
import com.autoservice.entities.vehicles.Modelo;
import com.autoservice.exceptions.AutoException;
import com.autoservice.exceptions.NotExistsException;
import com.autoservice.util.EjbConstants;
import com.autoservice.util.JPAUtil;

/**
 * Session Bean implementation class UserBean
 * @author Garis M. Suero
 * 06/05/2010
 */
@Stateless(name="BussinesHelper", mappedName="ejb/BussinesHelperJNDI")
public class BusinessHelper implements BusinessHelperLocal {

	@PersistenceUnit(name="myAutoService")
	public EntityManager em;

    public BusinessHelper() {
    	em = new JPAUtil().getEMF().createEntityManager();
    }
    
    public MailTemplate getMailTemplate(String name) throws NotExistsException
    {
    	MailTemplate template = null;
		try {
			Query query = em.createNamedQuery("MailTemplate.findByName");
			query.setParameter("name", name);
			List<MailTemplate> templateList = query.getResultList();
			if (templateList.size() > 0) {
	    		template = templateList.get(0);
			} else {
				throw new Exception(" no existen ");
			}
		} catch (Exception ex) {
			throw new NotExistsException(MailTemplate.class.getName() + " " + EjbConstants.MESSAGE_NOT_EXISTS + " " + name + " " + ex.getMessage());
		}
    	return template;
    }
    
    public void createMailConfirmation(UserMailConfirmation mailConfirmation) throws AutoException {
    	try {
	        em.getTransaction().begin();
	        em.persist(mailConfirmation);
	        em.flush();
	        em.getTransaction().commit();
    	}  catch (NullPointerException ex) {
    		throw new AutoException ("Algo anda mal, no hay entity manager");   		
    	} catch (Exception ex) {
    		throw new AutoException ("Algo anda mal : " + ex.getMessage());
    	}
    }
    public UserMailConfirmation getMailConfirmation(String email, String confirmationCode) throws NotExistsException
    {
    	UserMailConfirmation confirmation = null;
		try {
			Query query = em.createNamedQuery("userMailConfirmation.findByEmailNConfirmationCode");
			query.setParameter("email", email);
			query.setParameter("confirmationcode", confirmationCode);
			List<UserMailConfirmation> confirmationList = query.getResultList();
			if (confirmationList.size() > 0) {
	    		confirmation = confirmationList.get(0);
			} else {
				throw new Exception(" no existen ");
			}
		} catch (Exception ex) {
			throw new NotExistsException(MailTemplate.class.getName() + " " + EjbConstants.MESSAGE_NOT_EXISTS + " " + email + " " + ex.getMessage());
		}
    	return confirmation;
    }
    
    public void updateMailConfirmation(UserMailConfirmation mailConfirmation) throws AutoException {
    	try {
	        em.getTransaction().begin();
	        em.persist(mailConfirmation);
	        em.flush();
	        em.merge(mailConfirmation);
	        em.getTransaction().commit();
    	}  catch (NullPointerException ex) {
    		throw new AutoException ("Algo anda mal, no hay entity manager");   		
    	} catch (Exception ex) {
    		throw new AutoException ("Algo anda mal : " + ex.getMessage());
    	}
    }
    
    public List<Pais> getCountries() throws NotExistsException
    {
		try {
			Query query = em.createNamedQuery("Pais.findAll");
			List<Pais> paisList = query.getResultList();
			if (paisList.size() > 0) {
	    		return paisList;
			} else {
				throw new Exception(" no existen ");
			}
		} catch (Exception ex) {
			throw new NotExistsException(Pais.class.getName() + " " + EjbConstants.MESSAGE_NOT_EXISTS);
		}
    }
    
    public Pais getCountry(int paisid) throws NotExistsException
    {
    	Pais pais = null;
		try {
			Query query = em.createNamedQuery("Pais.findByCode");
			query.setParameter("paisid", paisid);
			List<Pais> paisList = query.getResultList();
			if (paisList.size() > 0) {
	    		pais = paisList.get(0);
			} else {
				throw new Exception(" no existen ");
			}
		} catch (Exception ex) {
			throw new NotExistsException(Pais.class.getName() + " " + EjbConstants.MESSAGE_NOT_EXISTS + " " +ex.getMessage());
		}
		return pais;
    }
    
    public List<Ciudad> getCiudades(Pais pais) throws NotExistsException
    {
		try {
			Query query = em.createNamedQuery("ciudad.findByCountry");
			query.setParameter("pais", pais);
			List<Ciudad> ciudadList = query.getResultList();
			if (ciudadList.size() > 0) {
				return ciudadList;
			} else {
				throw new Exception(" no existen ");
			}
		} catch (Exception ex) {
			throw new NotExistsException(Ciudad.class.getName() + " " + EjbConstants.MESSAGE_NOT_EXISTS);
		}
    }
    
    public List<Marca> getMarcas() throws NotExistsException
    {
		try {
			Query query = em.createNamedQuery("Marca.findAll");
			List<Marca> marcaList = query.getResultList();
			if (marcaList.size() > 0) {
	    		return marcaList;
			} else {
				throw new Exception(" no existen ");
			}
		} catch (Exception ex) {
			throw new NotExistsException(Marca.class.getName() + " " + EjbConstants.MESSAGE_NOT_EXISTS);
		}
    }
    
    public Marca getMarcas(int marcaid) throws NotExistsException
    {
    	Marca marca = null;
		try {
			Query query = em.createNamedQuery("Marca.findByCode");
			query.setParameter("marcaid", marcaid);
			List<Marca> marcaList = query.getResultList();
			if (marcaList.size() > 0) {
	    		marca = marcaList.get(0);
			} else {
				throw new Exception(" no existen ");
			}
		} catch (Exception ex) {
			throw new NotExistsException(Marca.class.getName() + " " + EjbConstants.MESSAGE_NOT_EXISTS + " " +ex.getMessage());
		}
		return marca;
    }

    
    public List<Modelo> getModelos(Marca marca) throws NotExistsException
    {
		try {
			Query query = em.createNamedQuery("modelo.findByMarca");
			query.setParameter("marca", marca);
			List<Modelo> modeloList = query.getResultList();
			if (modeloList.size() > 0) {
				return modeloList;
			} else {
				throw new Exception(" no existen ");
			}
		} catch (Exception ex) {
			throw new NotExistsException(Modelo.class.getName() + " " + EjbConstants.MESSAGE_NOT_EXISTS);
		}
    }
    
    public Modelo getModelo(int marcaid, int modeloid) throws NotExistsException
    {
		try {
			Query query = em.createNamedQuery("modelo.findByModelAndBrand");
			query.setParameter("marca", this.getMarcas(marcaid));
			query.setParameter("modeloid", modeloid);
			Modelo modelo = (Modelo) query.getSingleResult();
			if (modelo == null)
				throw new Exception();
			
			return modelo;
		} catch (Exception ex) {
			throw new NotExistsException(Modelo.class.getName() + " " + EjbConstants.MESSAGE_NOT_EXISTS);
		}
    }
    
    public List<Modelo> getModelos() throws NotExistsException
    {
		try {
			Query query = em.createNamedQuery("modelo.findAll");
			List<Modelo> modeloList = query.getResultList();
			if (modeloList.size() > 0) {
				return modeloList;
			} else {
				throw new Exception(" no existen ");
			}
		} catch (Exception ex) {
			throw new NotExistsException(Modelo.class.getName() + " " + EjbConstants.MESSAGE_NOT_EXISTS);
		}
    }
    
    public List<PartCategory> getPiezasCategories() throws NotExistsException
    {
		try {
			Query query = em.createNamedQuery("PartCategory.findAll");
		List<PartCategory> categorieList = query.getResultList();
			if (categorieList.size() > 0) {
				return categorieList;
			} else {
				throw new Exception(" no existen ");
			}
		} catch (Exception ex) {
			throw new NotExistsException(PartCategory.class.getName() + " " + EjbConstants.MESSAGE_NOT_EXISTS);
		}
    }
    public List<Part> getParts(int categoryid) throws NotExistsException {
    	return getParts(categoryid, 0, 0, null, null);
	}
    public List<Part> getParts(int categoryid, int page, int rows, String orderedBy, String orderAs) throws NotExistsException
    {
		try {
			Query query = em.createNamedQuery("Part.findByCategory");
			
			if (page != 0 && rows != 0) {
				int firstResult = (page*rows)-rows; // 1* 20 --> 20 -20=0 ///// 3 * 20 --> 60 - 20 -= 40
				query.setFirstResult(firstResult);
				query.setMaxResults(rows);
			}
			orderedBy = (orderedBy == null || orderedBy.length() == 0 ? "piezaid" : orderedBy);
			orderedBy = (orderAs == null || orderAs.length() == 0 ? "DESC" : orderAs);
			
		//	query.setParameter("orderby", orderedBy);
		//	query.setParameter("orderas", orderAs);
			query.setParameter("categoryid", getPartCategory(categoryid));
			List<Part> partsList = query.getResultList();
			if (partsList.size() > 0) {
				return partsList;
			} else {
				throw new Exception(" no existen ");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new NotExistsException(Part.class.getName() + " " + EjbConstants.MESSAGE_NOT_EXISTS);
		}
    }
    //Part.getCount
    public Long getPartsCount(int categoryid) throws NotExistsException
    {
		try {
			Query query = em.createNamedQuery("Part.getCount");
			query.setParameter("categoryid", getPartCategory(categoryid));
			Number totals = (Number) query.getSingleResult();
			Long total = totals.longValue();
			return total;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new NotExistsException(Part.class.getName() + " " + EjbConstants.MESSAGE_NOT_EXISTS);
		}
    }
    public Part getPart(int partid) throws NotExistsException
    {
		try {
			Query query = em.createNamedQuery("Part.finById");
			query.setParameter("partid", partid);
			List<Part> partsList = query.getResultList();
			if (partsList.size() > 0) {
				Part part = partsList.get(0);
				return part;
			} else {
				throw new Exception(" no existen ");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new NotExistsException(Part.class.getName() + " " + EjbConstants.MESSAGE_NOT_EXISTS);
		}
    }

    public List<Part> getAllParts() throws NotExistsException
    {
		try {
			Query query = em.createNamedQuery("Part.findAll");
			List<Part> partsList = query.getResultList();
			if (partsList.size() > 0) {
				return partsList;
			} else {
				throw new Exception(" no existen ");
			}
		} catch (Exception ex) {
			throw new NotExistsException(Part.class.getName() + " " + EjbConstants.MESSAGE_NOT_EXISTS);
		}
    }
    
   /* public List<Part> getOrphanParts() throws NotExistsException
    {
		try {
			Query query = em.createNamedQuery("Part.withNoCategory");
			List<Part> partsList = query.getResultList();
			if (partsList.size() > 0) {
				return partsList;
			} else {
				throw new Exception(" no existen ");
			}
		} catch (Exception ex) {
			throw new NotExistsException(Ciudad.class.getName() + " " + EjbConstants.MESSAGE_NOT_EXISTS);
		}
    }*/
    
    
    public PartCategory getPartCategory(int categoryid) throws NotExistsException
    {
		try {
			Query query = em.createNamedQuery("PartCategory.findById");
			query.setParameter("categoryid", categoryid);
			List<PartCategory> partsList = query.getResultList();
			if (partsList.size() > 0) {
				PartCategory category = partsList.get(0);
				return category;
			} else {
				throw new Exception(" no existen ");
			}
		} catch (Exception ex) {
			throw new NotExistsException(PartCategory.class.getName() + " " + EjbConstants.MESSAGE_NOT_EXISTS);
		}
    }
    
    public void setCategoryToPart(int partid, int categoryid) throws AutoException {
    	try {
	        
	        if (partid > 0 && categoryid > 0) {
	        	em.getTransaction().begin();
	        	Part part = this.getPart(partid);
	        	PartCategory category = this.getPartCategory(categoryid);
	        	part.setCategory(category);
	        	em.flush();
	        	em.getTransaction().commit();
	        }
    	}  catch (NullPointerException ex) {
    		throw new AutoException ("Algo anda mal, no hay entity manager");   		
    	} catch (Exception ex) {
    		throw new AutoException ("Algo anda mal : " + ex.getMessage());
    	}
    }
    
    public void updatePart(Part part) throws AutoException {
    	try {
	        em.getTransaction().begin();
	        if (part.getPiezaId() > 0) {
	        	Part dbPart = this.getPart(part.getPiezaId());
	        	if (part.getCategory() != null && 
	        			dbPart.getCategory() != null && 
	        			dbPart.getCategory().getCategoryId() != part.getCategory().getCategoryId() || 
	        				part.getCategory() != null && dbPart.getCategory() == null) {
	        		
	        		dbPart.setCategory(part.getCategory());
	        		
	        	}
	        	dbPart.setDescripcion(part.getDescripcion());
	        	dbPart.setDisponible(part.getDisponible());
	        } else {
	        	em.persist(part);
	        }
	        
	        em.flush();
	        em.getTransaction().commit();
	        //this.setCategoryToPart(part.getPiezaId(), categoryid);
    	}  catch (NullPointerException ex) {
    		ex.printStackTrace();
    		throw new AutoException ("Algo anda mal, no hay entity manager");   		
    	} catch (Exception ex) {
    		ex.printStackTrace();
    		throw new AutoException ("Algo anda mal : " + ex.getMessage());
    	}
    }
    
    public void deletePart(int partid) throws AutoException {
    	try {
	        em.getTransaction().begin();
	        Part dbPart = this.getPart(partid);
	        em.remove(dbPart);
	        em.flush();
	        em.getTransaction().commit();
    	}  catch (NullPointerException ex) {
    		throw new AutoException ("Algo anda mal, no hay entity manager");   		
    	} catch (Exception ex) {
    		throw new AutoException ("Algo anda mal : " + ex.getMessage());
    	}
    }
    
    public int addPart(String description, int available, int category) throws AutoException {
    	int retorno = -1;
    	try {
	        em.getTransaction().begin();
	        Part part = new Part();
	        if (category != 0)
	        	part.setCategory(getPartCategory(category));
	        else 
	        	part.setCategory(null);
	        
	        part.setDescripcion(description);
	        part.setDisponible(available);
	        em.persist(part);
	        em.flush();
	        retorno = part.getPiezaId();
	        em.getTransaction().commit();
    	}  catch (NullPointerException ex) {
    		throw new AutoException ("Algo anda mal, no hay entity manager");   		
    	} catch (Exception ex) {
    		throw new AutoException ("Algo anda mal : " + ex.getMessage());
    	}
    	return retorno;
    }
    
    public void deletePartCategory(int categoryid) throws AutoException {
    	try {
	        em.getTransaction().begin();
	        PartCategory dbPartCategory = this.getPartCategory(categoryid);
	        em.remove(dbPartCategory);
	        em.flush();
	        em.getTransaction().commit();
    	}  catch (NullPointerException ex) {
    		throw new AutoException ("Algo anda mal, no hay entity manager");   		
    	} catch (Exception ex) {
    		throw new AutoException ("Algo anda mal : " + ex.getMessage());
    	}
    }

    public int addPartCategory(String description, int status, int parentcategory) throws AutoException {
    	int retorno = -1;
    	try {
	        em.getTransaction().begin();
	        PartCategory part = new PartCategory();
	        if (parentcategory != 0)
	        	part.setParentCategory(getPartCategory(parentcategory));
	        else 
	        	part.setCategory(null);
	        
	        part.setCategory(description);
	        part.setStatus(status);
	        em.persist(part);
	        em.flush();
	        retorno = part.getCategoryId();
	        em.getTransaction().commit();
    	}  catch (NullPointerException ex) {
    		throw new AutoException ("Algo anda mal, no hay entity manager");   		
    	} catch (Exception ex) {
    		throw new AutoException ("Algo anda mal : " + ex.getMessage());
    	}
    	return retorno;
    }
    
    public void updatePartCategory(PartCategory partCategory) throws AutoException {
    	try {
	        em.getTransaction().begin();
	        if (partCategory.getCategoryId() > 0) {
	        	PartCategory dbPart = this.getPartCategory(partCategory.getCategoryId());
	        	if (partCategory.getParentCategory() != null && 
	        			dbPart.getParentCategory() != null && 
	        			dbPart.getParentCategory().getCategoryId() != partCategory.getParentCategory().getCategoryId() || 
	        				partCategory.getCategory() != null && dbPart.getCategory() == null) {
	        		
	        		dbPart.setParentCategory(partCategory.getParentCategory());
	        		
	        	}
	        	dbPart.setCategory(partCategory.getCategory());
	        	dbPart.setStatus(partCategory.getStatus());
	        } else {
	        	em.persist(partCategory);
	        }
	        
	        em.flush();
	        em.getTransaction().commit();
    	}  catch (NullPointerException ex) {
    		ex.printStackTrace();
    		throw new AutoException ("Algo anda mal, no hay entity manager");   		
    	} catch (Exception ex) {
    		ex.printStackTrace();
    		throw new AutoException ("Algo anda mal : " + ex.getMessage());
    	}
    }
    
    public List<Holiday> getHolidays(int year) throws NotExistsException
    {
    	List<Holiday> holidays = null;
		try {
			Query query = em.createNamedQuery("Holiday.findByYear");
			query.setParameter("year", year);
			List<Holiday> holidaysList = query.getResultList();
			if (holidaysList.size() > 0) {
				holidays = holidaysList;
			} else {
				throw new Exception(" no existen ");
			}
		} catch (Exception ex) {
			throw new NotExistsException(Holiday.class.getName() + " " + EjbConstants.MESSAGE_NOT_EXISTS);
		}
    	return holidays;
    }
    
    public List<MultipleType> getMultipleType(String type) throws NotExistsException
    {
    	List<MultipleType> types = null;
		try {
			Query query = em.createNamedQuery("MultipleType.findByType");
			query.setParameter("type", type);
			List<MultipleType> holidaysList = query.getResultList();
			if (holidaysList.size() > 0) {
				types = holidaysList;
			} else {
				throw new Exception(" no existen ");
			}
		} catch (Exception ex) {
			throw new NotExistsException(MultipleType.class.getName() + " " + EjbConstants.MESSAGE_NOT_EXISTS);
		}
    	return types;
    }
}
