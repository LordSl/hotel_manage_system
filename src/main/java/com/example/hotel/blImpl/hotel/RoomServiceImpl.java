package com.example.hotel.blImpl.hotel;

import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.po.Hotel;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.po.Order;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.RoomVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private OrderService orderService;
    @Override
    public List<RoomVO> retrieveHotelRoomInfo(Integer hotelId) {

        return roomMapper.selectRoomsByHotelId(hotelId).stream().map(r->{
            RoomVO roomVO=new RoomVO();
            BeanUtils.copyProperties(r,roomVO);
            return roomVO;
        }).collect(Collectors.toList());
    }

    @Override
    public void insertRoomInfo(RoomVO hotelRoom) {
        HotelRoom room=new HotelRoom();
        BeanUtils.copyProperties(hotelRoom,room);
        roomMapper.insertRoom(room);
    }

    @Override
    public void updateRoomInfo(Integer hotelId, String roomType, Integer rooms) {
        roomMapper.updateRoomInfo(hotelId,roomType,rooms);
    }

    public void updateRoom(RoomVO hotelRoom){
        HotelRoom room=new HotelRoom();
        BeanUtils.copyProperties(hotelRoom,room);
        roomMapper.updateRoom(room);
    }

    @Override
    public Integer addRoomNum(Integer roomId, Integer num) {
        return roomMapper.addRoomNum(roomId,num);
    }

    @Override
    public Integer getRoomNum(Integer roomId, String checkInDate, String checkOutDate) {
        List<OrderVO> orderList=orderService.getAllOrders();
        int total=roomMapper.getRoomCurNum(roomId);
        for(int i=0;i<orderList.size();i++){
            OrderVO order=orderList.get(i);
            if(roomId==order.getRoomId()&&(order.getCheckInDate().compareTo(checkInDate)>=0&&order.getCheckInDate().compareTo(checkOutDate)<0||
                    order.getCheckOutDate().compareTo(checkInDate)>0&&order.getCheckOutDate().compareTo(checkOutDate)<=0||
                        order.getCheckInDate().compareTo(checkInDate)<=0&&order.getCheckOutDate().compareTo(checkOutDate)>=0)){
                total-=order.getRoomNum();
            }
        }
        return total;
    }

    @Override
    public ResponseVO updateRoomImg(int id, String roomImg) {
        try {
            roomMapper.updateRoomImg(id,roomImg);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("上传图片失败");
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public RoomVO getOrderRoom(Integer id) {
        HotelRoom room=roomMapper.selectRoomByRoomId(id);
        RoomVO roomVO=new RoomVO();
        BeanUtils.copyProperties(room,roomVO);
        return roomVO;
    }

    public void deleteRoom(Integer roomId){
        roomMapper.deleteRoom(roomId);
    }
}
