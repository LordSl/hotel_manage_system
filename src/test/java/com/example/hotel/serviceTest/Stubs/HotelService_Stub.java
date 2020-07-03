package com.example.hotel.serviceTest.Stubs;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;

import java.util.LinkedList;
import java.util.List;

public class HotelService_Stub  implements HotelService {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBizRegion() {
        return bizRegion;
    }

    public void setBizRegion(String bizRegion) {
        this.bizRegion = bizRegion;
    }

    public Integer getHotelStar() {
        return hotelStar;
    }

    public void setHotelStar(Integer hotelStar) {
        this.hotelStar = hotelStar;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getHotelImg() {
        return hotelImg;
    }

    public void setHotelImg(String hotelImg) {
        this.hotelImg = hotelImg;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    private Integer id;
    private String name;
    private String address;
    private String bizRegion;
    private Integer hotelStar;
    private Double rate;
    private String description;
    private String phoneNum;
    private Integer managerId;
    private String hotelImg;
    private Double longitude;
    private Double latitude;
    private String managerEmail;


    @Override
    public ResponseVO addHotel(HotelVO hotelVO) {
        System.out.println("addHotel");
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO updateHotelInfo(HotelVO hotelVO) throws ServiceException {
        System.out.println("updateHotelInfo");
        return ResponseVO.buildSuccess();
    }


    @Override
    public ResponseVO deleteHotel(Integer hotelId) {
        System.out.println("deleteHotel");
        return ResponseVO.buildSuccess();
    }

    @Override
    public List<HotelVO> getHotelListByName(String hotelName) {
        System.out.println("getHotelListByName");
        return new LinkedList<>();
    }

    @Override
    public List<HotelVO> retrieveHotels() {
        System.out.println("retrieveHotels");
        return new LinkedList<>();
    }

    @Override
    public List<HotelVO> retrieveHotelByBizRegion(String address, String bizRegion, Integer hotelStar, String checkInDate, String checkOutDate, Integer roomNum, String key, Boolean haveReserved, Integer userId) {
        System.out.println("retrieveHotelByBizRegion");
        return new LinkedList<>();
    }

    @Override
    public HotelVO retrieveHotelDetails(Integer hotelId) {
        System.out.println("retrieveHotelByBizRegion");
        return new HotelVO();
    }

    @Override
    public HotelVO getHotelByHMId(Integer HMId) {
        System.out.println("getHotelByHMId");
        return new HotelVO();
    }

    @Override
    public List<OrderVO> getHotelOrders(Integer hotelId) {
        System.out.println("getHotelOrders");
        return new LinkedList<>();
    }

    @Override
    public ResponseVO updateHotelImg(int id, String hotelImg) {
        System.out.println("updateHotelImg");
        return ResponseVO.buildSuccess();
    }

    @Override
    public HotelVO getHotelById(Integer id) {
        System.out.println("getHotelByHMId");
        return new HotelVO();
    }

    @Override
    public ResponseVO getHotelRoom(Integer hotelId, String checkInDate, String checkOutDate) {
        System.out.println("getHotelRoom");
        return ResponseVO.buildSuccess();
    }
}
