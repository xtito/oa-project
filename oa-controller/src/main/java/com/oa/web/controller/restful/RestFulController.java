package com.oa.web.controller.restful;

import com.oa.bean.sys.SysUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by [张渊]
 * 2018/8/29 16:27
 */
@RestController
public class RestFulController {

    @GetMapping(value = "/user")
    public ResponseEntity<List<SysUser>> listAllUsers() {
        List<SysUser> users = new ArrayList<SysUser>();
        if(users.isEmpty()){
            return new ResponseEntity<List<SysUser>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<SysUser>>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SysUser> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        SysUser user = new SysUser();
        user.setCreateTime(new Date());
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<SysUser>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<SysUser>(user, HttpStatus.OK);
    }

}
