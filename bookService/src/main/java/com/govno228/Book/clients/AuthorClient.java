package com.govno228.Book.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.govno228.Book.model.Author;

@FeignClient(name = "author-service", url = "http://localhost:8082/api/v1")
public interface AuthorClient {
    @GetMapping("/authors/{id}")
    Author getAuthor(@PathVariable("id") Long id);
}
