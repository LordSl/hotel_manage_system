import { axios } from '@/utils/request'
const api = {
    vipHotelPre: '/api/vipHotel'
}


export function isVIPCoupleAPI(params){
    return axios({
        url: `${api.vipHotelPre}/isVIPCouple`,
        method: 'GET',
        params
    })
}


export function registerAsHotelVIPAPI(params){
    return axios({
        url: `${api.vipHotelPre}/registerAsHotelVIP`,
        method: 'GET',
        params
    })
}


export function getHotelListOfCurrentVIPAPI(userId){
    return axios({
        url: `${api.vipHotelPre}/${userId}/getHotelListOfCurrentVIP`,
        method: 'GET',
    })
}


export function getVipListOfCurrentHotelAPI(hotelId){
    return axios({
        url: `${api.vipHotelPre}/${hotelId}/getVipListOfCurrentHotel`,
        method: 'GET',
    })
}


export function deleteVIPHotelAPI(params){
    return axios({
        url: `${api.vipHotelPre}/deleteVIPHotel`,
        method: 'GET',
        params
    })
}