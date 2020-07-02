package com.example.hotel.blImpl.hotel;

import com.example.hotel.bl.hotel.HPicService;
import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.comment.CommentMapper;
import com.example.hotel.data.hotel.HotelMapper;
import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.enums.BizRegion;
import com.example.hotel.enums.HotelStar;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.*;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.Oneway;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {


    @Autowired HotelService hotelService;
    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private OrderService orderService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private HPicService hPicService;

    @Autowired
    private CommentMapper commentMapper;
    @Override
    public ResponseVO addHotel(HotelVO hotelVO) {

        User manager=accountMapper.getAccountByName(hotelVO.getManagerEmail());
        List<Hotel> hotelList=hotelMapper.selectAllHotel();
        if(manager==null||!manager.getUserType().equals(UserType.HotelManager)){
            return ResponseVO.buildFailure("管理员不存在或者无权限！创建酒店失败！");
        }
        for(Hotel hotel: hotelList){
            if(hotel.getManagerId()==manager.getId()){
                return ResponseVO.buildFailure("单一管理员只能管理一个酒店");
            }
        }
        Hotel hotel = new Hotel();
        BeanUtils.copyProperties(hotelVO,hotel);
        hotel.setHotelName(hotelVO.getName());
        hotel.setManagerId(manager.getId());
        hotelMapper.insertHotel(hotel);
        return ResponseVO.buildSuccess(true);
    }
    @Override
    public void updateHotelInfo(HotelVO hotelVO) throws ServiceException {
        Hotel hotel = new Hotel();
        hotel.setId(hotelVO.getId());
        hotel.setDescription(hotelVO.getDescription());
        hotel.setAddress(hotelVO.getAddress());
        hotel.setHotelName(hotelVO.getName());
        hotel.setPhoneNum(hotelVO.getPhoneNum());
        hotel.setManagerId(hotelVO.getManagerId());
        hotel.setRate(hotelVO.getRate());
        hotel.setBizRegion(hotelVO.getBizRegion());
        hotel.setHotelStar(hotelVO.getHotelStar());
        hotel.setLongitude(hotelVO.getLongitude());
        hotel.setLatitude(hotelVO.getLatitude());
        hotelMapper.updateHotelInfo(hotel);
    }

    @Override
    public void updateRoomInfo(Integer hotelId, String roomType, Integer rooms) {
        roomService.updateRoomInfo(hotelId,roomType,rooms);
    }

    @Override
    public void deleteHotel(Integer hotelId){
        hotelMapper.deleteHotel(hotelId);
    }

    @Override
    public List<HotelVO> getHotelListByName(String hotelName) {
        List<HotelVO>allHotel = retrieveHotels();
        List<HotelVO>matchedList = new ArrayList<>();
        hotelName = hotelName.trim();
        for (HotelVO hotelVO:allHotel){
            if(hotelVO.getName().contains(hotelName)){
                matchedList.add(hotelVO);
            }
        }
        return matchedList;
    }

    @Override
    public List<HotelVO> retrieveHotels() {

        return hotelMapper.selectAllHotel().stream().map(h->{
            HotelVO hotelVO=new HotelVO();
            BeanUtils.copyProperties(h,hotelVO);
            hotelVO.setName(h.getHotelName());
            hotelVO.setRooms(retrieveHotelDetails(hotelVO.getId()).getRooms());
            return hotelVO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<HotelVO> retrieveHotelByBizRegion(String address, String  bizRegion, Integer hotelStar, String checkInDate, String checkOutDate, Integer roomNum, String key,Boolean haveReserved,
                                                  Integer userId) {
        List<Hotel> hotels=hotelMapper.selectAllHotel();
        if(haveReserved&&userId>0){
            List<OrderVO> orders=orderService.getUserOrders(userId);
            for(int i=0;i<hotels.size();i++){
                Hotel hotel=hotels.get(i);
                if(!orders.stream().anyMatch(order -> order.getOrderState().equals("已入住")&&order.getHotelId().equals(hotel.getId()))){
                    hotels.remove(i);
                    i--;
                }
            }
        }
        List<Hotel> res=new LinkedList<>();
        for(Hotel hotel:hotels){
            if(hotel.getAddress().contains(address)&&hotel.getBizRegion().equals(bizRegion)){
                res.add(hotel);
            }
        }
        if(checkInDate!=null&&checkOutDate!=null&&checkInDate.length()>0&&checkOutDate.length()>0&&roomNum!=null&&roomNum>0){
            for(int i=0;i<res.size();i++){
                HotelVO hotelVO=retrieveHotelDetails(res.get(i).getId());
                boolean enough=false;
                for(RoomVO roomVO:hotelVO.getRooms()){
                    if(roomService.getRoomNum(roomVO.getId(),checkInDate,checkOutDate)>=roomNum){
                        enough=true;
                        break;
                    }
                }
                if(!enough){
                    res.remove(i);
                    i--;
                }
            }
        }
        if(hotelStar!=null&&hotelStar>0){
            for(int i=0;i<res.size();i++){
                if(res.get(i).getHotelStar()<hotelStar){
                    res.remove(i);
                    i--;
                }
            }
        }
        if(key!=null){
            for(int i=0;i<res.size();i++){
                if(!res.get(i).getHotelName().contains(key)){
                    res.remove(i);
                    i--;
                }
            }
        }

        return res.stream().map(r->{
            HotelVO hotelVO=new HotelVO();
            BeanUtils.copyProperties(r,hotelVO);
            hotelVO.setName(r.getHotelName());
            hotelVO.setRooms(retrieveHotelDetails(hotelVO.getId()).getRooms());
            return hotelVO;
        }).collect(Collectors.toList());
    }

    @Override
    public HotelVO retrieveHotelDetails(Integer hotelId) {
        Hotel hotel = hotelMapper.selectById(hotelId);
        if(hotel==null){
            return null;
        }
        List<RoomVO> rooms = roomService.retrieveHotelRoomInfo(hotelId);
        List<HPicVO> picList=hPicService.getHotelPicById(hotelId);
        HotelVO hotelVO=new HotelVO();
        BeanUtils.copyProperties(hotel,hotelVO);
        hotelVO.setName(hotel.getHotelName());
        hotelVO.setRooms(rooms);
        hotelVO.setPicList(picList);
        return hotelVO;
    }

    @Override
    public HotelVO getHotelByHMId(Integer HMId) {
        Hotel hotel = hotelMapper.selectByHMId(HMId);
        List<RoomVO> rooms = roomService.retrieveHotelRoomInfo(hotel.getId());
        List<RoomVO> roomVOS = rooms.stream().map(r -> {
            RoomVO roomVO = new RoomVO();
            BeanUtils.copyProperties(r,roomVO);
            return roomVO;
        }).collect(Collectors.toList());
        HotelVO hotelVO=new HotelVO();
        if(hotel==null){
            return null;
        }
        List<HPicVO> picList=hPicService.getHotelPicById(hotel.getId());
        BeanUtils.copyProperties(hotel,hotelVO);
        hotelVO.setName(hotel.getHotelName());
        hotelVO.setRooms(roomVOS);
        hotelVO.setPicList(picList);
        return hotelVO;
    }
    
    /**
     * @param hotelId
     * @return
     */
    @Override
    public List<OrderVO> getHotelOrders(Integer hotelId) {
        List<OrderVO> orders = orderService.getAllOrders();
        return orders.stream().filter(order -> order.getHotelId().equals(hotelId)).collect(Collectors.toList());
    }

    @Override
    public ResponseVO updateHotelImg(int id, String hotelImg) {
        try {
            hotelMapper.updateHotelImg(id,hotelImg);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("上传图片失败");
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public HotelVO getHotelById(Integer id) {
        Hotel hotel=hotelMapper.selectById(id);
        if(hotel==null){
            return null;
        }
        HotelVO hotelVO=new HotelVO();
        BeanUtils.copyProperties(hotel,hotelVO);
        hotelVO.setName(hotel.getHotelName());
        return hotelVO;
    }

    @Override
    public ResponseVO getHotelRoom(Integer hotelId, String checkInDate, String checkOutDate) {
        List<RoomVO> roomVOS=roomService.retrieveHotelRoomInfo(hotelId);
        for(RoomVO roomVO:roomVOS){
            roomVO.setCurNum(roomService.getRoomNum(roomVO.getId(),checkInDate,checkOutDate));
        }
        return ResponseVO.buildSuccess(roomVOS);
    }
}
