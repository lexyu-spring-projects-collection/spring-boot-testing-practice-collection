package com.lex.practice.repository;

import com.lex.practice.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author : Lex Yu
 */
public interface UserRepository extends CrudRepository<User, UUID> {
}
