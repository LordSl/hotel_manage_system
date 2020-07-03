package com.example.hotel.serviceTest.drivers;

import com.example.hotel.bl.admin.AdminService;
import com.example.hotel.vo.CreditChangeVO;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.UserInfoVO;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

public class adminService_Driver {
    public void drive(AdminService adminService){
        if(adminService.addManager(new UserForm()).getSuccess()!=true){
            System.out.println("Error");
        }
        if(adminService.getAllManagers().getSuccess()!=true){
            System.out.println("Error");
        }
        if(adminService.deleteManager(1).getSuccess()!=true){
            System.out.println("Error");
        }
        if(adminService.updateManager(new UserInfoVO()).getSuccess()!=true){
            System.out.println("Error");
        }
        if(adminService.uploadAD("abc").getSuccess()!=true){
            System.out.println("Error");
        }
    }
}

