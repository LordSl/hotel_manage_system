import { axios } from '@/utils/request'
const api = {
    adminPre: '/api/admin'
}
export function getManagerListAPI(){
    return axios({
        url: `${api.adminPre}/getAllManagers`,
        method: 'POST'
    })
}
export function addManagerAPI(data) {
    return axios({
        url: `${api.adminPre}/addManager`,
        method: 'POST',
        data
    })
}
export function updateManagerAPI(data) {
    return axios({
        url: `${api.adminPre}/updateManager`,
        method: 'POST',
        data
    })
}



export function deleteManagerAPI(param) {
    return axios({
        url: `${api.adminPre}/${param.userId}/deleteManager` ,
        method: 'GET' ,
    })
}