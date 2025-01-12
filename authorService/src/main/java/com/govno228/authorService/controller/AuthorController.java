package com.govno228.authorService.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        Author authorFromDb = authorRepository.findById(id).orElse(null);
        if (authorFromDb == null) {
            return ResponseEntity.notFound().build();
        }
        authorFromDb.setName(author.getName());
        authorFromDb.setSurname(author.getSurname());
        authorFromDb.setDescription(author.getDescription());
        authorFromDb.setId(author.getId());
        authorRepository.deleteById(id);
        return ResponseEntity.ok(authorRepository.save(authorFromDb));
    }

    @PostMapping("/ids")
    public ResponseEntity<HashMap<Long, Author>> getAllAuthors(@RequestBody Set<Long> ids) {
        //authorRepository.findAllById(ids);
        HashMap<Long, Author> authors = new HashMap<>();
        for (Long id : ids) {
            Author author = authorRepository.findById(id).orElse(null);
            if (author != null) {
                authors.put(id, author);
            }
        }
        return ResponseEntity.ok(authors);
    }

}
