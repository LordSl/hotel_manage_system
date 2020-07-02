package com.example.hotel.blImpl.hotel;

import com.example.hotel.bl.hotel.HPicService;
import com.example.hotel.data.hotel.HPicMapper;
import com.example.hotel.po.HPic;
import com.example.hotel.vo.HPicVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.Oneway;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HPIcServiceImpl  implements HPicService {

    @Autowired
    private HPicMapper hPicMapper;
    @Override
    public ResponseVO addHPic(HPicVO hPicVO) {
        HPic hPic=new HPic();
        BeanUtils.copyProperties(hPicVO,hPic);
        try {
            hPicMapper.insertHPic(hPic);
        }catch (Exception e){
            return ResponseVO.buildFailure("上传酒店图片失败");
        }
        return ResponseVO.buildSuccess("上传成功");
    }

    @Override
    public ResponseVO deleteHPic(Integer id) {
        try {
            hPicMapper.deleteHPic(id);
        }catch (Exception e){
            return ResponseVO.buildFailure("删除酒店失败");
        }
        return ResponseVO.buildSuccess("删除成功");
    }

    @Override
    public List<HPicVO> getHotelPicById(Integer hotelId) {
        List<HPicVO> picList=hPicMapper.getHotelPicById(hotelId).stream().map(p->{
            HPicVO hPicVO=new HPicVO();
            BeanUtils.copyProperties(p,hPicVO);
            return hPicVO;
        }).collect(Collectors.toList());
        Collections.sort(picList, new Comparator<HPicVO>() {
            @Override
            public int compare(HPicVO o1, HPicVO o2) {
                if(o1.isCustom()==true){
                    return 1;
                }else{
                    return -1;
                }
            }
        });
        return picList;
    }
}
