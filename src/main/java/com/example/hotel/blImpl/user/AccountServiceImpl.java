package com.example.hotel.blImpl.user;

import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.po.User;
import com.example.hotel.util.OssClientUtil;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Random;


@Service
public class AccountServiceImpl implements AccountService {
    private final static String ACCOUNT_EXIST = "账号已存在";
    private final static String UPDATE_ERROR = "修改失败";
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public ResponseVO registerAccount(UserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO,user);
        user.setAvatarUrl("http://hotelreservesystem.oss-cn-beijing.aliyuncs.com/userAvatar/1590771992934.png?Expires=1622307985&OSSAccessKeyId=LTAI4G7424cm4dhbzubVuhk8&Signature=k8%2BrIG4JHbxoYvk%2Bdu8VLlhFj%2F4%3D");
        Random random=new Random();
        user.setUserName("用户"+random.nextInt(10000)+"号");
        try {
            accountMapper.createNewAccount(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
        return ResponseVO.buildSuccess();
    }

    @Override
    public UserVO login(UserForm userForm) {
        User user = accountMapper.getAccountByName(userForm.getEmail());
        if (null == user || !user.getPassword().equals(userForm.getPassword())) {
            return null;
        }
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        UserVO userVO=new UserVO();
        BeanUtils.copyProperties(user,userVO);
        return userVO;
    }

    @Override
    public UserVO getUserInfo(int id) {
        User user = accountMapper.getAccountById(id);
        if (user == null) {
            return null;
        }
        UserVO userVO=new UserVO();
        BeanUtils.copyProperties(user,userVO);
        return userVO;
    }

    @Override
    public ResponseVO updateUserInfo(int id, String password, String username, String phonenumber) {
        try {
            accountMapper.updateAccount(id, password, username, phonenumber);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(UPDATE_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO creditRecharge(int id, Double amount) {
        try {
            accountMapper.creditRecharge(id,amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(UPDATE_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO updateUserCredit(int id, double credit) {
        accountMapper.updateAccountCredit(id,credit);
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO updateUserAvatarUrl(int id, String userImg) {
        try {
            accountMapper.updateUserImg(id,userImg);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("上传图片失败");
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO setUserWebVIP(int userId, int isWebVIP) {
        accountMapper.setUserWebVIP(userId,isWebVIP);
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO getWebVIP() {
        List<User>vip = accountMapper.getWebVIP();
        return ResponseVO.buildSuccess(vip);
    }
}
