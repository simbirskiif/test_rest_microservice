package com.govno228.authorService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.govno228.authorService.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    
}
