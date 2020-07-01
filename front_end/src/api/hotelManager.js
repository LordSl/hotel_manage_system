import { axios } from '@/utils/request'
const api = {
    hotelPre: '/api/hotel'
}
export function addRoomAPI(data) {
    return axios({
        url: `${api.hotelPre}/roomInfo`,
        method: 'POST',
        data,
    })
}
export function updateRoomAPI(data) {
    return axios({
        url: `${api.hotelPre}/updateRoom`,
        method: 'POST',
        data,
    })
}
export function addHotelAPI(data) {
    return axios({
        url: `${api.hotelPre}/addHotel`,
        method: 'POST',
        data,
    })
}
export function updateHotelAPI(data) {
    return axios({
        url: `${api.hotelPre}/updateHotel`,
        method: 'POST',
        data,
    })
}
export function deleteRoomAPI(param) {
    return axios({
        url: `${api.hotelPre}/${param}/deleteRoom` ,
        method: 'GET' ,
    })
}

export function addHPicAPI(data) {
    return axios({
        url: `${api.hotelPre}/addHPic`,
        method: 'POST',
        data,
    })
}

export function deleteHPicAPI(params) {
    return axios({
        url: `${api.hotelPre}/deleteHPic`,
        method: 'GET',
        params,
    })
}
