package com.example.hotel.controller.user;

import com.example.hotel.bl.user.AccountService;
import com.example.hotel.po.User;
import com.example.hotel.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController()
@RequestMapping("/api/user")
public class AccountController {
    private final static String ACCOUNT_INFO_ERROR = "用户名或密码错误";
    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public ResponseVO login(@RequestBody UserForm userForm) {
        UserVO user = accountService.login(userForm);
        if (user == null) {
            return ResponseVO.buildFailure(ACCOUNT_INFO_ERROR);
        }
        return ResponseVO.buildSuccess(user);

    }

    @PostMapping("/register")
    public ResponseVO registerAccount(@RequestBody UserVO userVO) {
        return accountService.registerAccount(userVO);
    }


    @GetMapping("/{id}/getUserInfo")
    public ResponseVO getUserInfo(@PathVariable int id) {
        UserVO user = accountService.getUserInfo(id);
        if(user==null){
            return ResponseVO.buildFailure(ACCOUNT_INFO_ERROR);
        }
        return ResponseVO.buildSuccess(user);
    }

    @PostMapping("/{id}/userInfo/update")
    public ResponseVO updateInfo(@RequestBody UserInfoVO userInfoVO,@PathVariable int id){
        return accountService.updateUserInfo(id,userInfoVO.getPassword(),userInfoVO.getUserName(),userInfoVO.getPhoneNumber());

    }


    @PostMapping("/{id}/creditRecharge")
    public ResponseVO creditRecharge(@PathVariable int id,@RequestBody CreditRechargeVO creditRechargeVO){
        return accountService.creditRecharge(id,creditRechargeVO.getAmount());
    }

    @GetMapping("/userInfo/userImg")
    public ResponseVO updateUserAvatar(@RequestParam String userImg,@RequestParam Integer id){
        return accountService.updateUserAvatarUrl(id,userImg);
    }

    @GetMapping("/userInfo/setVIP")
    public ResponseVO setUserWebVIP(@RequestParam int userId,@RequestParam int isWebVIP){
        return accountService.setUserWebVIP(userId,isWebVIP);
    }

    @GetMapping("/userInfo/getWebVIP")
    public ResponseVO setUserWebVIP(){
        return accountService.getWebVIP();
    }

    @GetMapping("/{userId}/getCreditChange")
    public ResponseVO getCreditChange(@PathVariable int userId){
        return accountService.getCreditChangeListByUserId(userId);
    }

}
