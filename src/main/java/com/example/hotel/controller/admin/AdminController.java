package com.example.hotel.controller.admin;

import com.example.hotel.bl.admin.AdminService;
import com.example.hotel.blImpl.admin.AdminServiceImpl;
import com.example.hotel.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@RestController()
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/addManager")
    public ResponseVO addManager(@RequestBody UserForm userForm){

        return adminService.addManager(userForm);
    }

    @PostMapping("/updateManager")
    public ResponseVO updateManager(@RequestBody UserInfoVO userInfoVO){

        return adminService.updateManager(userInfoVO);
    }

    @GetMapping("/{userId}/deleteManager")
    public ResponseVO deleteManager(@PathVariable Integer userId) {
        adminService.deleteManager(userId);
        return ResponseVO.buildSuccess(true);
    }

    @PostMapping("/getAllManagers")
    public ResponseVO getAllManagers(){
        return ResponseVO.buildSuccess(adminService.getAllManagers());
    }

    @PostMapping("/uploadADAPI")
    public ResponseVO iploadAD(@RequestParam String imgUrl){
        adminService.uploadAD(imgUrl);
        return ResponseVO.buildSuccess(true);
    }

}
