package com.capgemini.entity;



import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "book")
public class BookEntity {
	
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="categoryId")
	@JsonIgnore
	private Category category;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(length = 10)
	private int bookId;
	
	public BookEntity() {
		super();
	}

	@Column(length=128)
	private String title;
	
	@Column(length=64)
	private String author;
	
	@Column(length=200)
	private String description;
	
	@Column(length=10)
	private int ISBN;
	
	@Column(length=10)
	private float price;
	
	private String filename;
	
	private String type;
	
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Lob
	private byte[] icon;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getISBN() {
		return ISBN;
	}

	public void setISBN(int iSBN) {
		this.ISBN = iSBN;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	
	public byte[] getIcon() {
		return icon;
	}

	public void setIcon(byte[] icon) {
		this.icon = icon;
	}

	public BookEntity(Category category, int bookId, String title, String author, String description, int iSBN,
			float price, String filename, String type, byte[] icon) {
		super();
		this.category = category;
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.description = description;
		this.ISBN = iSBN;
		this.price = price;
		this.filename = filename;
		this.type = type;
		this.icon = icon;
	}

		
	
	
	
	
	

}
