import { axios } from '@/utils/request'
const api = {
    hotelPre: '/api/hotel'
}
export function getHotelsAPI() {
    return axios({
        url: `${api.hotelPre}/all`,
        method: 'GET',
    })
}
export function getHotelByIdAPI(param) {
    return axios({
        url: `${api.hotelPre}/${param.hotelId}/detail`,
        method: 'GET',
    })
}
export function getHotelByHMIdAPI(param) {
    return axios({
        url: `${api.hotelPre}/${param.HMId}/getHotelByHMId`,
        method: 'GET',
    })
}
export function getHotelListByNameAPI(params) {
    return axios({
        url: `${api.hotelPre}/getHotelListByName`,
        method: 'GET',
        params
    })
}

export function deleteHotelAPI(param) {
    return axios({
        url: `${api.hotelPre}/${param}/deleteHotel` ,
        method: 'GET' ,
    })
}
export function searchHotelListAPI(params) {
    return axios({
        url: `${api.hotelPre}/search`,
        method: 'GET',
        params
    })
}

export function updateHotelImgAPI(params) {
    return axios({
        url: `${api.hotelPre}/hotelImg`,
        method: 'GET' ,
        params,
    })
}

export function updateRoomImgAPI(params) {
    return axios({
        url: `${api.hotelPre}/roomImg`,
        method: 'GET' ,
        params,
    })
}

export function getOrderRoomAPI(params) {
    return axios({
        url: `${api.hotelPre}/getRoom`,
        method: 'GET' ,
        params,
    })
}

export function getHotelRoomAPI(params) {
    return axios({
        url: `${api.hotelPre}/getHotelRoom`,
        method: 'GET' ,
        params,
    })
}


