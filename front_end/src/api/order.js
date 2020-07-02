import { axios } from '@/utils/request'
const api = {
    orderPre: '/api/order'
}
export function reserveHotelAPI(data) {
    return axios({
        url: `${api.orderPre}/addOrder`,
        method: 'POST',
        data,
    })
}

//审核改变订单申诉状态
export  function changeAppealStatusAPI(data){
    return axios({
        url:`${api.orderPre}/changeAppealStatus`,
        method:'POST',
        data
    })
}

export function getAllOrdersAPI() {
    return axios({
        url: `${api.orderPre}/getAllOrders`,
        method: 'GET',
    })
}
export function getUserOrdersAPI(data) {
    return axios({
        url: `${api.orderPre}/${data.userId}/getUserOrders`,
        method: 'GET',
    })
}
export function cancelOrderAPI(orderId) {
    return axios({
        url: `${api.orderPre}/${orderId}/annulOrder`,
        method: 'GET',
    })
}

//订单撤销申诉
export function cancelAppealAPI(data) {
    return axios({
        url:`${api.orderPre}/cancelAppeal`,
        method:'POST',
        data
    })
}
//订单撤销申诉




//获取订单申诉理由
export function getAppealMessageAPI(orderId) {
    return axios({
        url: `${api.orderPre}/${orderId}/getAppealMessage`,
        method: 'GET'
    })
}



export function deleteOrderAPI(orderId) {
    return axios({
        url: `${api.orderPre}/${orderId}/deleteOrder`,
        method: 'GET',
    })
}

export function checkedInAPI(orderId) {
    return axios({
        url: `${api.orderPre}/${orderId}/checkedIn`,
        method: 'GET',
    })
}

export function autoUpdateOrderStatusAPI() {
    return axios({
        url: `${api.orderPre}/autoUpdateOrderStatus`,
        method: 'GET',
    })
}


export function getStateOfUHAPI(params){
    return axios({
        url: `${api.orderPre}/getStateOfUP`,
        method: 'GET',
        params
    })
}

export function getPrevOrderAPI(params){
    return axios({
        url: `${api.orderPre}/getPrevOrder`,
        method: 'GET',
        params
    })
}


export function getOrderListOfCurrentHotelAPI(hotelId) {
    return axios({
        url: `${api.orderPre}/${hotelId}/getOrderListByHotelId`,
        method: 'GET',
    })
}
