package com.lex.test.crud.dao;

import com.lex.test.crud.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<UserInfo, Integer> {

    // select u from UserInfo u where x.firstname = ?1 and x.lastname = ?2
    UserInfo findByFirstnameAndLastname(String firstName, String lastName);

    Optional<UserInfo> findByUsername(String username);
}
