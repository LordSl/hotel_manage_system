package com.example.hotel.serviceTest.drivers;

import com.example.hotel.bl.admin.AdminService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.vo.CreditChangeVO;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.UserInfoVO;

public class AccountService_Stub {
    public void drive(AccountService accountService){
        if(accountService.addCreditChangeRecord(new CreditChangeVO()).getSuccess()!=true){
            System.out.println("Error");
        }
        if(accountService.getCreditChangeListByUserId(1).getSuccess()!=true){
            System.out.println("Error");
        }
        if(accountService.getWebVIP().getSuccess()!=true){
            System.out.println("Error");
        }

    }
}
