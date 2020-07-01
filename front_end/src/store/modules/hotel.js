import { message } from 'ant-design-vue'
import store from '@/store'
import {
    getHotelsAPI,
    getHotelByIdAPI,
    searchHotelListAPI
} from '@/api/hotel'
import {
    getAdListAPI
} from "@/api/advertisement"
import {
    reserveHotelAPI,
    getStateOfUHAPI,
    getPrevOrderAPI,
} from '@/api/order'
import {
    orderMatchCouponsAPI,
    getCouponForDisplayAPI
} from '@/api/coupon'
import {
    getHotelCommentListAPI,
    addCommentAPI,
    deleteCommentAPI,
    addReplyAPI,
    increaseReplyLikeAPI,
    addCommentStatusAPI,
    deleteReplyStatusAPI,
    deleteCommentStatusAPI,
    getCommentStatusAPI,
    increaseCommentLikeAPI,
    deleteReplyAPI,
}from '@/api/comment'
import {
    registerAsHotelVIPAPI,
    isVIPCoupleAPI
} from "@/api/vipHotel";
import {
    uploadImgAPI,
} from '@/api/oss'

const hotel = {
    state: {
        hotelList: [
            
        ],
        advertisementList : [

        ],
        hotelListParams: {
            pageNo: 0,
            pageSize: 12
        },
        hotelListLoading: true,
        currentHotelId: 0,
        currentHotelInfo: {

        },
        currentOrderRoom:{

        },
        currentHotelComments:[],
        currentCommentsStatus:[],
        orderModalVisible: false,
        orderMatchCouponList: [

        ],
        searchHotelList:[

        ],
        isVipOfCurrentHotel:false,
        hotelFlag:null,
        color:null,
        commentFlag:false,
        commentImgList:[

        ],
        atlasVisible:false,
        imgList:[

        ],
        curHotelImg:null,
        commentImgUrl:null,
        couponListForDisplay:[],
        hotelImgList: [],
        userImgList: [],
    },
    mutations: {
        set_userImgList: function(state,data){
            state.userImgList = data
        },
        set_hotelImgList: function(state,data){
            state.hotelImgList = data
        },
        set_curHotelImg: function(state , data ) {
            state.curHotelImg=data
        },
        set_atlasVisible: function(state , data ) {
            state.atlasVisible=data
        },
        set_imgList: function(state , data ) {
            state.imgList=data
        },
        set_commentImgList: function(state , data ) {
            state.commentImgList=data
        },
        set_couponListForDisplay:function(state,data){
            state.couponListForDisplay = data
        },
        set_commentFlag: function(state , data ) {
            state.commentFlag=data
        },
        set_color: function(state , data ) {
            state.color=data
        },
        set_hotelFlag: function(state,data){
            state.hotelFlag=data
        },
        set_isVipOfCurrentHotel: function(state,data){
           state.isVipOfCurrentHotel = data
        },
        set_hotelList: function(state, data) {
            state.hotelList = data
        },
        set_hotelListParams: function(state, data) {
            state.hotelListParams = {
                ...state.hotelListParams,
                ...data,
            }
        },
        set_hotelListLoading: function(state, data) {
            state.hotelListLoading = data
        },
        set_currentHotelId: function(state, data) {
            state.currentHotelId = data
        },
        set_currentHotelInfo: function(state, data) {
            state.currentHotelInfo = {
                ...state.currentHotelInfo,
                ...data,
            }
        },
        set_orderModalVisible: function(state, data) {
            state.orderModalVisible = data
        },
        set_currentOrderRoom: function(state, data) {
            state.currentOrderRoom = {
                ...state.currentOrderRoom,//原来的
                ...data,//新的
            }
            console.log("roomId:"+data.roomId)
        },
        set_orderMatchCouponList: function(state, data) {
            state.orderMatchCouponList = data
        },
        set_currentHotelComments:function (state,data) {
            state.currentHotelComments=data;
        },
        set_currentCommentStatus(state,data){
            state.currentCommentsStatus = data
        },
        set_searchHotelList(state,data){
            state.searchHotelList = data
        },
        set_advertisementList(state,data){
            state.advertisementList = data
        }
    },

    actions: {

        getHotelList: async({commit, state}) => {
            const res = await getHotelsAPI()
            if(res){
                commit('set_hotelList', res)
                commit('set_hotelListLoading', false)
            }
        },
        getAdvertisementList: async({commit, state}) => {
            const res = await getAdListAPI()
            if(res){
                commit('set_advertisementList', res)
            }
        },
        getHotelById: async({commit, state}) => {
            const res = await getHotelByIdAPI({
                hotelId: state.currentHotelId
            })
            if(res){
                console.log(res)
                commit('set_currentHotelInfo', res)
                commit('set_imgList',res.picList)
                const data1=state.imgList.filter(item => item.custom==true)
                const  data2=state.imgList.filter(item => item.custom==false)
                commit('set_userImgList',data1)
                commit('set_hotelImgList',data2)
                console.log(state.userImgList)
                if(state.imgList.length>0){
                    commit('set_curHotelImg', state.imgList[0].imgUrl)
                }else{
                    commit('set_curHotelImg',null)
                }
            }
        },
        getPrevOrder: async({commit, state}, data) =>{

            const res = await getPrevOrderAPI(data);
            if(res){
                message.info('您将在'+res.checkInDate+'入住')
            }
            return res;

        },
        getCommentFlag: async({commit, state}, data) =>{
            const res = await getStateOfUHAPI(data);

            if(res=="曾住过"){
                console.log("in")
                commit("set_commentFlag",true);
                console.log(state.commentFlag)
                return true;
            }else{
                commit("set_commentFlag",false);
                return  false;
            }

        },
        getHotelCommentsById: async({commit, state}) => {
            const res = await getHotelCommentListAPI({
                hotelId: state.currentHotelId
            })
            if(res){
                commit('set_currentHotelComments', res)
            }
        },
        addOrder: async({ state, commit }, data) => {
            const res = await reserveHotelAPI(data)
            if(res){
                message.success('预定成功')
                commit('set_orderModalVisible', false)
            }
        },
        uploadCommentImg: async({ state, commit,dispatch }, data) => {
            if(state.commentImgList.length>=6){
                message.error('最多只能上传6张图片')
            }else {
                const res1 = await uploadImgAPI(data)
                if (res1) {
                    const data=[
                        ...state.commentImgList,
                        res1
                    ]
                    console.log(data)
                    commit('set_commentImgList', data)
                    message.success('上传成功')
                }
            }

        },
        addComment: async({ state, commit,dispatch }, data) => {
            if(state.commentImgList.length>0){
                const data1={
                    picList:state.commentImgList,
                    ...data
                }
                console.log("data1")
                console.log(data1)
                const res = await addCommentAPI(data1)
                if(res){
                    message.success('评论成功')
                    dispatch('getHotelCommentsById')
                    dispatch('getHotelById')
                }else{
                    message.error('评论失败')
                }
                commit('set_commentImgList',[]);
            }else{
                const res = await addCommentAPI(data)
                if(res){
                    message.success('评论成功')
                    dispatch('getHotelCommentsById')
                }
            }
        },
        addReply: async({ state, commit ,dispatch}, data) => {
            const res = await addReplyAPI(data)
            console.log(res)
            if(res){
                message.success('回复成功')
                dispatch('getHotelCommentsById')
            }
        },
        increaseReplyLike: async({ state, commit }, data) => {
            const params = {
                ...data,
            }

            const res = await increaseReplyLikeAPI(params)
            console.log(res)
        },
        getOrderMatchCoupons: async({ state, commit }, data) => {
            const res = await orderMatchCouponsAPI(data)
            if(res){
                commit('set_orderMatchCouponList', res)
            }
        },
        addCommentStatus: async({ state, commit ,dispatch}, data) => {
            const res = await addCommentStatusAPI(data)
            console.log(res)
            if(res){
                dispatch('getCommentStatus')
            }
        },
        deleteCommentStatus: async({ state, commit,dispatch }, data) => {
            const res = await deleteCommentStatusAPI(data)
            console.log(res)
            dispatch('getCommentStatus')

        },
        deleteReplyStatus: async({ state, commit,dispatch }, data) => {
            const res = await deleteReplyStatusAPI(data)
            console.log(res)
            dispatch('getCommentStatus')
        },
        getCommentStatus: async({ state, commit }, data) => {
            const res = await getCommentStatusAPI(data)
            if(res){
                commit('set_currentCommentStatus',res)
            }
        },
        increaseCommentLike: async({ state, commit }, data) => {
            const params = {
                ...data,
            }
            const res = await increaseCommentLikeAPI(params)
        },
        retrieveHotel: async({ state, commit }, data) =>{
            console.log(data)
            const params = {
                ...data,
            }
            const res = await searchHotelListAPI(params)
            console.log(res)
            if(res){
                commit('set_searchHotelList',res)
            }
            if(data.set){
                commit('set_hotelList',res)
            }
        },
        judgeVipCouple: async({ state, commit }, data) =>{
            const res = await isVIPCoupleAPI(data);
            commit('set_isVipOfCurrentHotel',res)
        },
        judgeStateOfHotel: async({ state, commit }, data) =>{
            console.log(state.hotelFlag)
            const res = await getStateOfUHAPI(data);
            commit('set_hotelFlag',res)
        },

        registerAsHotelVIP: async({ state, commit,dispatch }, params) =>{
            const res =await registerAsHotelVIPAPI(params)
            if(res){
                message.success("恭喜您已成为该酒店的尊贵会员")
            }
            dispatch('judgeVipCouple', params);
        },
        deleteComment: async({ state, commit,dispatch }, data) =>{
            const res =await deleteCommentAPI(data)
            if(res){
                message.success("删除成功")
            }
            dispatch('getHotelCommentsById');
        },
        deleteReply: async({ state, commit,dispatch }, param) =>{
            const res =await deleteReplyAPI(param)
            if(res){
                message.success("删除成功")
            }
            dispatch('getHotelCommentsById');
        },
        getCouponListForDisplay: async({ state, commit }, hotelId) =>{
            const res =await getCouponForDisplayAPI(hotelId)
            console.log(res)
            if(res){
                commit('set_couponListForDisplay',res)
            }
        },
    }
}

export default hotel