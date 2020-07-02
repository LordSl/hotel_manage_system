package com.example.hotel.blImpl.user;

import com.example.hotel.bl.user.AccountService;
import com.example.hotel.blImpl.hotel.RoomServiceImpl;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.data.user.CreditChangeMapper;
import com.example.hotel.po.CreditChange;
import com.example.hotel.po.User;
import com.example.hotel.vo.CreditChangeVO;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Service
public class AccountServiceImpl implements AccountService {
    private final static String ACCOUNT_EXIST = "账号已存在";
    private final static String UPDATE_ERROR = "修改失败";
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private CreditChangeMapper creditChangeMapper;

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

            //信用记录增加

            CreditChange creditChange = new CreditChange();
            creditChange.setUserId(id);
            creditChange.setReason("用户通过申诉或完成充值， 信用值增加");
            creditChange.setValue(amount);
            this.addCreditChangeRecord(creditChange);
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

    @Override
    public ResponseVO getCreditChangeListByUserId(int userId) {
        List<CreditChange> got = creditChangeMapper.getCreditChangeByUserId(userId);
        List<CreditChangeVO> result = new ArrayList<>();
        for(CreditChange creditChange : got){
            CreditChangeVO creditChangeVO = new CreditChangeVO();
            BeanUtils.copyProperties(creditChange, creditChangeVO);
            result.add(creditChangeVO);
        }
        return ResponseVO.buildSuccess(result);
    }


    @Override
    public ResponseVO addCreditChangeRecord(CreditChangeVO creditChangeVO) {
        CreditChange creditChange = new CreditChange();
        creditChange.setUserId(creditChangeVO.getUserId());
        creditChange.setReason(creditChangeVO.getReason());
        creditChange.setValue(creditChangeVO.getValue());
        this.addCreditChangeRecord(creditChange);
        return ResponseVO.buildSuccess(true);
    }

    public void addCreditChangeRecord(CreditChange creditChange) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss ");
        Date date = new Date(System.currentTimeMillis());
        creditChange.setTime(formatter.format(date));
        creditChangeMapper.addCreditChangeRecord(creditChange);
        ResponseVO.buildSuccess(true);
    }
}
