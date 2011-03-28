package com.autoservice.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.*;

import com.autoservice.util.EjbConstants;

@Entity
@Table(name = EjbConstants.TABLE_PIEZAS_CATEGORY)
@NamedQueries( {
		@NamedQuery(name = "PartCategory.findAll", query = "SELECT d FROM PartCategory d where d.status =1 order by d.category ASC"),
		@NamedQuery(name = "PartCategory.findById", query = "SELECT d FROM PartCategory d where d.categoryId=:categoryid")})
public class PartCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	public PartCategory() {
		super();
	}
	@Id
	@Column(name = "categoryid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;

	
	@Column(name = "category")
	private String category;
	
	@OneToOne
    @JoinColumn(name="parentcategory") 
	private PartCategory parentCategory;
	
	@Column(name = "status")
	private int status;
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName="categoryid", name="parentCategory")
	private List<PartCategory> subCategories;


	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public PartCategory getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(PartCategory parentCategory) {
		this.parentCategory = parentCategory;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<PartCategory> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<PartCategory> subCategories) {
		this.subCategories = subCategories;
	}
}
