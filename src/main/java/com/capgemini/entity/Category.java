package com.capgemini.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.oracle.jrockit.jfr.ValueDefinition;

@Entity
public class Category {
	
	@Id
	@Column(length=12)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int categoryId;
	

	@Column(length=50)
	private String categoryName;
	
	
	@OneToMany(mappedBy="category")
	private List<BookEntity> books = new ArrayList<BookEntity>();


	
	public Category() {
		super();
	}


	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public List<BookEntity> getBooks() {
		return books;
	}


	public void setBooks(List<BookEntity> books) {
		this.books = books;
	}


	public Category(int categoryId, String categoryName, List<BookEntity> books) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.books = books;
	}
	
	
	
	

}
