package com.eligere.services;

import java.util.List;

import com.eligere.dtos.BookDTO;
import com.eligere.entities.Book;
import com.eligere.exceptions.BookException;

public interface BookService {
	
	public Book getBookById(Integer bookId) throws BookException;
	
	public List<Book> getAllBooks();
	
	public Book createBook(BookDTO bookDTO) throws BookException;
	
	public Book updateBook(Integer bookId, BookDTO bookDTO) throws BookException;
	
	public Book deleteBook(Integer bookId) throws BookException;
}
