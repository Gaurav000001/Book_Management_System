package com.eligere.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eligere.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	
	public Optional<Book> findByIsbn(String isbn);
}
