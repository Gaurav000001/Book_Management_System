package com.eligere.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eligere.dtos.BookDTO;
import com.eligere.entities.Book;
import com.eligere.exceptions.BookException;
import com.eligere.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book getBookById(Integer bookId) throws BookException {
		Optional<Book> bookOpt = bookRepository.findById(bookId);
		
		if(bookOpt.isEmpty()) throw new BookException("Book not found");
		return bookOpt.get();
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> bookList = bookRepository.findAll();
		return bookList;
	}

	@Override
	public Book createBook(BookDTO bookDTO) throws BookException{
		// replacing the isbn hiphans '-' and spaces
		bookDTO.setIsbn(bookDTO.getIsbn().replaceAll("-", "").replaceAll(" ", ""));
		
		Optional<Book> existingBookOpt = bookRepository.findByIsbn(bookDTO.getIsbn());
		if(existingBookOpt.isPresent()) throw new BookException("Book already exists with same ISBN!");
		
		Book book = Book.builder()
				.isbn(bookDTO.getIsbn())
				.author(bookDTO.getAuthor())
				.title(bookDTO.getTitle())
				.publicationDate(bookDTO.getPublicationDate())
				.build();
		
		Book createdBook = bookRepository.save(book);
		return createdBook;
	}

	@Override
	public Book updateBook(Integer bookId, BookDTO bookDTO) throws BookException {
		// replacing the isbn hiphans '-' and spaces
		bookDTO.setIsbn(bookDTO.getIsbn().replaceAll("-", "").replaceAll(" ", ""));
		
		Optional<Book> bookByIdOpt = bookRepository.findById(bookId);
		if(bookByIdOpt.isEmpty()) throw new BookException("Book not found");
		
		// if isbn is changed by user while updating the book information
		if(bookByIdOpt.get().getIsbn().equals(bookDTO.getIsbn()) == false) {
			
			// if can find another book with same isbn in the database then throw error
			Optional<Book> bookByIsbnOpt = bookRepository.findByIsbn(bookDTO.getIsbn());
			if(bookByIsbnOpt.isPresent()) throw new BookException("Book with same ISBN already present!");
		}
			
		
		Book existingBook = bookByIdOpt.get();
		existingBook.setIsbn(bookDTO.getIsbn());
		existingBook.setAuthor(bookDTO.getAuthor());
		existingBook.setTitle(bookDTO.getTitle());
		existingBook.setPublicationDate(bookDTO.getPublicationDate());
		
		Book updatedBook = bookRepository.save(existingBook);
		return updatedBook;
	}

	@Override
	public Book deleteBook(Integer bookId) throws BookException {
		Optional<Book> bookOpt = bookRepository.findById(bookId);
		if(bookOpt.isEmpty()) throw new BookException("Book not found");
		
		bookRepository.deleteById(bookId);
		Book deletedBook = bookOpt.get();
		
		return deletedBook;
	}

}
