package com.example.hotel.controller.ossUtil;

import com.example.hotel.util.OssClientUtil;
import com.example.hotel.vo.ResponseVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController()
@RequestMapping("/api/oss")
public class ossController {

    @RequestMapping("/upload")
    public ResponseVO uploadImage(@RequestBody MultipartFile file){
        OssClientUtil ossClientUtil=new OssClientUtil();
        try {
            String imgUrl=ossClientUtil.updateHomeImage(file);
            return ResponseVO.buildSuccess(imgUrl);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(e.getMessage());
        }

    }
}
