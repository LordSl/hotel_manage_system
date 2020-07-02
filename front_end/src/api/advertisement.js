import { axios } from '@/utils/request'
const api = {
    advertisementPre: '/api/advertisement'
}
export function getAdListAPI(){
    return axios({
        url: `${api.advertisementPre}/getAdList`,
        method: 'GET'
    })
}

export function deleteADAPI(id){
    return axios({
        url: `${api.advertisementPre}/${id}/deleteAD`,
        method: 'GET'
    })
}

export function addADImgAPI(params){
    return axios({
        url: `${api.advertisementPre}/addADImg`,
        method: 'GET',
        params
    })
}

export function bindADHotelAPI(params){
    return axios({
        url: `${api.advertisementPre}/bindADHotel`,
        method: 'GET',
        params
    })
}