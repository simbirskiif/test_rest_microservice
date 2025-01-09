package com.govno228.Book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.govno228.Book.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    
}
