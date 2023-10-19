package com.ninja.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ninja.security.entity.Post;
import com.ninja.security.entity.User;

public interface PostRepository extends JpaRepository<Post, Integer>{
	List<Post> findByUserId(Integer userId);
}
