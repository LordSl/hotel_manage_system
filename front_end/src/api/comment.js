import { axios } from '@/utils/request'
const api = {
    commentPre: '/api/comment'
}
export function getHotelCommentListAPI(data){
    return axios({
        url: `${api.commentPre}/hotel/${data.hotelId}`,
        method: 'GET'
    })
}
export function increaseCommentLikeAPI(params){
    return axios({
        url: `${api.commentPre}/like`,
        method: 'GET',
        params
    })
}
export function increaseReplyLikeAPI(params){
    return axios({
        url: `${api.commentPre}/reply/like`,
        method: 'GET',
        params
    })
}


export function getUserCommentListAPI(data){
    return axios({
        url: `${api.commentPre}/User/${data.userId}`,
        method: 'GET'
    })
}
export function deleteCommentAPI(data){
    return axios({
        url: `${api.commentPre}/delete/${data.id}`,
        method: 'GET'
    })
}

export function deleteReplyAPI(params){
    return axios({
        url: `${api.commentPre}/deleteReply`,
        method: 'GET',
        params
    })
}

export function addCommentAPI(data) {
    return axios({
        url: `${api.commentPre}/addComment`,
        method: 'POST',
        data
    })
}

export function addReplyAPI(data) {
    return axios({
        url: `${api.commentPre}/addReply`,
        method: 'POST',
        data
    })
}

export function addCommentStatusAPI(data) {
    return axios({
        url: `${api.commentPre}/addStatus`,
        method: 'POST',
        data
    })
}

export function deleteCommentStatusAPI(params) {
    return axios({
        url: `${api.commentPre}/deleteCommentStatus`,
        method: 'GET',
        params
    })
}

export function deleteReplyStatusAPI(params) {
    return axios({
        url: `${api.commentPre}/deleteReplyStatus`,
        method: 'GET',
        params
    })
}

export function getCommentStatusAPI(data) {
    return axios({
        url: `${api.commentPre}/getCommentStatus`,
        method: 'GET',
        data
    })
}

export function getUserReplyAPI(params) {
    return axios({
        url: `${api.commentPre}/replyToUser`,
        method: 'GET',
        params
    })
}