package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Comment;

@Repository
public interface Commentrepo extends JpaRepository<Comment, Integer> {

}
