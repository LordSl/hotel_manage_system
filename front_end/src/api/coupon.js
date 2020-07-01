import { axios } from '@/utils/request'
const api = {
    couponPre: '/api/coupon'
}
export function hotelTargetMoneyAPI(data) {
    return axios({
        url: `${api.couponPre}/hotelTargetMoney`,
        method: 'POST',
        data,
    })
}

export function hotelTargetRoomAPI(data) {
    return axios({
        url: `${api.couponPre}/hotelTargetRoom`,
        method: 'POST',
        data,
    })
}

export function hotelTargetTimeAPI(data) {
    return axios({
        url: `${api.couponPre}/hotelTargetTime`,
        method: 'POST',
        data,
    })
}

export function hotelAllCouponsAPI(params) {
    return axios({
        url: `${api.couponPre}/hotelAllCoupons`,
        method: 'GET',
        params,
    })
}

export function orderMatchCouponsAPI(params) {
    return axios({
        url: `${api.couponPre}/orderMatchCoupons`,
        method: 'GET',
        params,
    })
}

export function getWebCouponListAPI() {
    return axios({
        url: `${api.couponPre}/webCoupon`,
        method: 'GET',
    })
}

export function deleteCouponAPI(params) {
    return axios({
        url: `${api.couponPre}/delete`,
        method: 'GET',
        params
    })
}

export function getCouponForDisplayAPI(hotelId) {
    return axios({
        url: `${api.couponPre}/${hotelId}/getCouponForDisplay`,
        method: 'GET',
    })
}