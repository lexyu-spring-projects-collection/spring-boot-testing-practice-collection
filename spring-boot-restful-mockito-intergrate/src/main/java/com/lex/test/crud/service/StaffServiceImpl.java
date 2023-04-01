package com.lex.test.crud.service;

import com.lex.test.crud.dao.StaffRepository;
import com.lex.test.crud.entity.UserInfo;
import com.lex.test.crud.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService{

    private final StaffRepository staffRepository;

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public Optional<UserInfo> findByFirstnameAndLastname(String firstName, String lastName) {
        return Optional.ofNullable(staffRepository.findByFirstnameAndLastname(firstName, lastName));
    }

    @Override
    public List<UserInfo> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public UserInfo save(UserInfo userInfo) {
        Optional<UserInfo> saveUserInfo =  staffRepository.findByUsername(userInfo.getUsername());
        if (saveUserInfo.isPresent()){
            throw new ResourceNotFoundException("UserInfo already exist with given username" + userInfo.getUsername());
        }
        return staffRepository.save(userInfo);
    }

    @Override
    public Optional<UserInfo> getUserInfoById(int id) {
        return staffRepository.findById(id);
    }

    @Override
    public UserInfo updateUserInfo(UserInfo updateUserInfo) {
        return staffRepository.save(updateUserInfo);
    }

    @Override
    public void deleteUserInfo(int id) {
        staffRepository.deleteById(id);
    }
}
