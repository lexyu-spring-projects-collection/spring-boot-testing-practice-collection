package com.lex.test.crud.controller;

import com.lex.test.crud.entity.UserInfo;
import com.lex.test.crud.service.StaffService;
import com.lex.test.crud.service.StaffServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staffs")
public class StaffController {

    private StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    public List<UserInfo> getAllUserInfo(){
        return staffService.findAll();
    }

    @GetMapping("/{firstname}/{lastname}")
    public ResponseEntity<UserInfo> findByFirstnameAndLastname
            (@PathVariable String firstname, @PathVariable String lastname){
        return staffService.findByFirstnameAndLastname(firstname, lastname).
                map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{theId}")
    public ResponseEntity<UserInfo> getUserInfoById(@PathVariable int theId) {
        return staffService.getUserInfoById(theId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserInfo createUserInfo(@RequestBody UserInfo theUser){
        staffService.save(theUser);
        return theUser;
    }

    @PutMapping("{theId}")
    public ResponseEntity<UserInfo> updateUserInfo(@PathVariable Integer theId ,@RequestBody UserInfo updateUser) {
        ResponseEntity<UserInfo> responseEntity =  staffService.getUserInfoById(theId)
                .map(user -> {
                    user.setUsername(updateUser.getUsername());
                    user.setPassword(updateUser.getPassword());
                    user.setFirstname(updateUser.getFirstname());
                    user.setLastname(updateUser.getLastname());
                    UserInfo newUserInfo = staffService.updateUserInfo(user);
                    return new ResponseEntity<>(newUserInfo, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());

        return responseEntity;
    }

    @DeleteMapping("{theId}")
    public ResponseEntity<String> deleteUserInfo(@PathVariable int theId) {
        staffService.deleteUserInfo(theId);
        return new ResponseEntity<String>("UserInfo deleted successfully!", HttpStatus.OK);
    }
}
