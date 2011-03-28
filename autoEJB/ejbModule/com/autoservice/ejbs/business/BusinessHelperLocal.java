package com.autoservice.ejbs.business;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.Query;

import com.autoservice.entities.Ciudad;
import com.autoservice.entities.Holiday;
import com.autoservice.entities.MailTemplate;
import com.autoservice.entities.MultipleType;
import com.autoservice.entities.Pais;
import com.autoservice.entities.Part;
import com.autoservice.entities.PartCategory;
import com.autoservice.entities.UserMailConfirmation;
import com.autoservice.entities.vehicles.Marca;
import com.autoservice.entities.vehicles.Modelo;
import com.autoservice.exceptions.AutoException;
import com.autoservice.exceptions.NotExistsException;
import com.autoservice.util.EjbConstants;

@Local
public interface BusinessHelperLocal {
    public MailTemplate getMailTemplate(String name) throws NotExistsException;
    public void createMailConfirmation(UserMailConfirmation mailConfirmation) throws AutoException;
    public UserMailConfirmation getMailConfirmation(String email, String confirmationCode) throws NotExistsException;
    public void updateMailConfirmation(UserMailConfirmation mailConfirmation) throws AutoException;
    public List<Pais> getCountries() throws NotExistsException;
    public Pais getCountry(int paisid) throws NotExistsException;
    public List<Ciudad> getCiudades(Pais pais) throws NotExistsException;
    public List<Marca> getMarcas() throws NotExistsException;
    public Marca getMarcas(int marcaid) throws NotExistsException;
    public List<Modelo> getModelos(Marca marca) throws NotExistsException;
    public List<Modelo> getModelos() throws NotExistsException;
    public Modelo getModelo(int marcaid, int modeloid) throws NotExistsException;
    public List<PartCategory> getPiezasCategories() throws NotExistsException;
    public List<Part> getParts(int categoryid) throws NotExistsException;
    public List<Part> getParts(int categoryid, int page, int rows, String orderedBy, String orderAs) throws NotExistsException;
    public Part getPart(int partid) throws NotExistsException;
    public PartCategory getPartCategory(int categoryid) throws NotExistsException;
    public void setCategoryToPart(int partid, int categoryid) throws AutoException;
   // public List<Part> getOrphanParts() throws NotExistsException;
    public List<Part> getAllParts() throws NotExistsException;
    public void updatePart(Part part) throws AutoException;
    public void deletePart(int partid) throws AutoException;
    public int addPart(String description, int available, int category) throws AutoException;
    public void deletePartCategory(int categoryid) throws AutoException;
    public int addPartCategory(String description, int status, int parentcategory) throws AutoException;
    public void updatePartCategory(PartCategory partCategory) throws AutoException;
    public Long getPartsCount(int categoryid) throws NotExistsException;
    public List<Holiday> getHolidays(int year) throws NotExistsException;
    public List<MultipleType> getMultipleType(String type) throws NotExistsException;
}
