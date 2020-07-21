package com.capgemini.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.BookDao;
import com.capgemini.entity.BookEntity;


@Service
public class BookService {

	@Autowired
	BookDao repo;
	
	public Optional<BookEntity> getBook(int id)
	{
		return repo.findById(id);
		
		
	}
	
	
	
	
	
	
	
	
	
}
