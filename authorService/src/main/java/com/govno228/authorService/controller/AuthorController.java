package com.govno228.authorService.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.govno228.authorService.models.Author;
import com.govno228.authorService.repositories.AuthorRepository;

import feign.Response;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;

    @GetMapping
    public ResponseEntity<List<Author>> getAuthors() {
        return ResponseEntity.ok(authorRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        return author == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(author);
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        return ResponseEntity.ok(authorRepository.save(author));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteAuthor(@PathVariable Long id) {
        authorRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        Author authorFromDb = authorRepository.findById(id).orElse(null);
        if (authorFromDb == null) {
            return ResponseEntity.notFound().build();
        }
        authorFromDb.setName(author.getName());
        authorFromDb.setSurname(author.getSurname());
        authorFromDb.setDescription(author.getDescription());
        return ResponseEntity.ok(authorRepository.save(authorFromDb));
    }

}
