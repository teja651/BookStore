package com.capgemini.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.capgemini.Exceptions.BookIdInvalid;
import com.capgemini.dao.BookDao;
import com.capgemini.entity.BookEntity;
import com.capgemini.entity.Category;
import com.capgemini.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@CrossOrigin("http://localhost:4200")
public class BookController {

	
	@Autowired
	BookService ser;
	
	@Autowired
	BookDao repo;

	
	@RequestMapping("/")

	public String add1() {

				
		return "index";
	
	}

	
	
	@RequestMapping("add")

	public String add(BookEntity book) {

		
		for(int i=0;i<book.getIcon().length;i++)
		System.out.println((char)book.getIcon()[i]);

		repo.save(book);

		System.out.println("book added");
		
		return "home";
	
	}
	
	@GetMapping("/getbook")
	
	public List<BookEntity> getBook() {
		
		/*
		 * Optional<BookEntity> book = ser.getBook(id);
		 * 
		 * System.out.println(book); return book;
		 */
		List<BookEntity> x= repo.findAll();
		System.out.println(x.get(0).getIcon().toString()+""+"is icon");
		return x;
		
		
	}
	
	@PostMapping("/addbook")
   public ResponseEntity<String> addbook(@RequestParam("imageFile") MultipartFile file,@RequestParam("book") String value ) throws IOException
   {
	System.out.println("Hai"+value);
		
	 Details details = new ObjectMapper().readValue(value, Details.class);	
		
	 System.out.println("Hai");
	 
		/* System.out.println(details.getISBN()); */
	
	 BookEntity book = new BookEntity();
	 
	 book.setIcon(compressBytes(file.getBytes()));
		
	 book.setFilename(file.getOriginalFilename());
	 
	 book.setType(file.getContentType());
	 
	 Category category = new Category();
	 
	 
	 
	 category.setCategoryName(details.getCategory());
	 
	 book.setCategory(category);
	 
	 book.setAuthor(details.getAuthor());
	 
	 book.setTitle(details.getTitle());
	 
	 book.setDescription(details.getDescription());
	 
		book.setISBN(/* details.getISBN() */10);
	 
	 book.setPrice(details.getPrice());
	 
	 
	 repo.save(book);
	 
	 
	 return new ResponseEntity<String>("Added to db",HttpStatus.OK);
	
		
		
		
   }
	@GetMapping(path = { "/get/{filename}" })
	@ResponseBody
	public BookEntity getImage(@PathVariable("filename") String filename) throws IOException {

		final BookEntity ret= repo.findByFilename(filename);
		
		System.out.println(ret.getType());

		ret.setIcon(decompressBytes(ret.getIcon()));
		
		return ret;
	}

	
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}

	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}
	
	
}

class Details
{
	private String category;

	private String title;

     private String author;

    private String description;

    //private int ISBN;

     private int price;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	/*
	 * public int getISBN() { return ISBN; }
	 * 
	 * public void setISBN(int iSBN) { this.ISBN = iSBN; }
	 */
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Details(String category, String title, String author, String description, int iSBN, int price) {
		super();
		this.category = category;
		this.title = title;
		this.author = author;
		this.description = description;
		//this.ISBN = iSBN;
		this.price = price;
	}

	public Details() {
		super();
	}
     
     

     


}
