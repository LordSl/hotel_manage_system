import { axios } from '@/utils/request'

const api = {
    userPre: '/api/user'
}
export function loginAPI(data){
    return axios({
        url:`${api.userPre}/login`,
        method: 'POST',
        data
    })
}
export function registerAPI(data){
    return axios({
        url: `${api.userPre}/register`,
        method: 'POST',
        data
    })
}
export function getUserInfoAPI(id){
    return axios({
        url: `${api.userPre}/${id}/getUserInfo`,
        method: 'GET'
    })
}
export function updateUserInfoAPI(data) {
    return axios({
        url: `${api.userPre}/${data.id}/userInfo/update`,
        method: 'POST',
        data
    })
}

export function creditRechargeAPI(data) {
    return axios({
        url: `${api.userPre}/${data.id}/creditRecharge`,
        method: 'POST',
        data
    })
}

export function updateUserImgAPI(params) {
    return axios({
        url: `${api.userPre}/userInfo/userImg`,
        method: 'GET' ,
        params,
    })
}

export function setWebVIPAPI(params) {
    return axios({
        url: `${api.userPre}/userInfo/setVIP`,
        method: 'GET' ,
        params,
    })
}

export function getWebVIPAPI() {
    return axios({
        url: `${api.userPre}/userInfo/getWebVIP`,
        method: 'GET'
    })
}

export function getCreditChangeAPI(userId) {
    return axios({
        url: `${api.userPre}/${userId}/getCreditChange`,
        method: 'GET'
    })
}
