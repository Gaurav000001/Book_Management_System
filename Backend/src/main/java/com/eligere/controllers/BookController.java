package com.eligere.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eligere.dtos.BookDTO;
import com.eligere.entities.Book;
import com.eligere.exceptions.BookException;
import com.eligere.services.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/eligere/books")
@CrossOrigin("*")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/{bookId}")
	public ResponseEntity<Book> getBookByISBNHandler(@PathVariable Integer bookId) throws BookException{
		Book book = bookService.getBookById(bookId);
		
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Book>> getAllBooksHandler(){
		List<Book> books = bookService.getAllBooks();
		
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Book> createBookHandler(@Valid @RequestBody BookDTO bookDTO) throws BookException{
		Book createdBook = bookService.createBook(bookDTO);
		
		return new ResponseEntity<Book>(createdBook, HttpStatus.OK);
	}
	
	@PutMapping("/{bookId}")
	public ResponseEntity<Book> updateBookHandler(@PathVariable Integer bookId, @Valid @RequestBody BookDTO bookDTO) throws BookException{
		Book updatedBook = bookService.updateBook(bookId, bookDTO);
		
		return new ResponseEntity<Book>(updatedBook, HttpStatus.OK);
	}
	
	@DeleteMapping("/{bookId}")
	public ResponseEntity<Book> deleteBookHandler(@PathVariable Integer bookId) throws BookException{
		Book deletedBook = bookService.deleteBook(bookId);
		
		return new ResponseEntity<Book>(deletedBook, HttpStatus.OK);
	}
}
