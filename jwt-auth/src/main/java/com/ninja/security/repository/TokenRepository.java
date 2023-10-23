package com.ninja.security.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ninja.security.token.Token;

public interface TokenRepository extends JpaRepository<Token, Integer> {
	
	@Query(value = """
			select t from Token t join User u
			on t.user.id = u.id
			where u.id = :id and (t.expired = false and t.revoked = false)
			""")
	List<Token> findAllVaildTokenByUser(Integer id);
	
	Optional<Token> findByToken(String Token);
}
