package com.lex.test.crud.service;

import com.lex.test.crud.entity.UserInfo;

import java.util.List;
import java.util.Optional;

public interface StaffService {

    Optional<UserInfo> findByFirstnameAndLastname(String firstName, String lastName);

    List<UserInfo> findAll();

    UserInfo save(UserInfo userInfo);

    Optional<UserInfo> getUserInfoById(int id);
    UserInfo updateUserInfo(UserInfo updateUserInfo);
    void deleteUserInfo(int id);

}
