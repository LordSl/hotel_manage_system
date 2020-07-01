import {
    addRoomAPI,
    updateRoomAPI,
    deleteRoomAPI,
    addHotelAPI,
    updateHotelAPI,
    addHPicAPI,
    deleteHPicAPI
} from '@/api/hotelManager'
import {
    deleteHotelAPI,
    getHotelByIdAPI,
    getHotelByHMIdAPI,
    updateHotelImgAPI,
    updateRoomImgAPI,
    getOrderRoomAPI,

} from '@/api/hotel'
import {
    getAllOrdersAPI,
    deleteOrderAPI,
    getAppealMessageAPI,
    changeAppealStatusAPI,
    checkedInAPI,
    autoUpdateOrderStatusAPI,
    getOrderListOfCurrentHotelAPI
} from '@/api/order'
import {
    hotelAllCouponsAPI ,
    hotelTargetMoneyAPI ,
    hotelTargetRoomAPI,
    hotelTargetTimeAPI,
    getWebCouponListAPI,
    deleteCouponAPI
} from '@/api/coupon'
import {
    creditRechargeAPI
} from '@/api/user'
import {
    getVipListOfCurrentHotelAPI,
    deleteVIPHotelAPI
}from'@/api/vipHotel'
import { message } from 'ant-design-vue'

import {
    uploadImgAPI,
} from '@/api/oss'

const hotelManager = {
    state: {
        // orderList: [],
        orderListOfCurrentHotel:[],
        updateRoomModalVisible:false,
        currentRoomId:0,
        addHotelModalVisible: false,
        updateHotelModalVisible: false,
        addRoomParams: {
            roomType: '',
            hotelId: '',
            price: '',
            total: 0,
            curNum: 0,
        },
        updateRoomParams: {
            id:0,
            roomType: '',
            hotelId: '',
            price: '',
            total: 0,
            curNum: 0,
        },
        appealReviewModalVisible : false,
        addRoomModalVisible: false,
        couponVisible: false,
        addCouponVisible: false,
        couponList: [],
        appealMessageReview:'',
        appealReviewOrderId:'',
        appealCreditRechargeimfo:{},
        orderDetailCardVisibleHM:false,
        currentOrderOfHM:{
        },
        webCoupon:false,
        webCouponList:[],
        hotelImgUrl:null,
        roomImgUrl:null,
        vipListOfCurrentHotel:[],
    },
        mutations: {

            set_updateRoomModalVisible:function(state,data){
                state.updateRoomModalVisible = data
            },
            set_updateRoomParams:function(state,data){
                state.updateRoomParams = {
                    ...state.updateRoomParams,
                    ...data,
                }
                state.updateRoomParams.id = state.currentRoomId
            },
            set_currentRoomId:function(state,data){
                state.currentRoomId = data
                console.log("RoomId:"+data)
            },
            set_orderDetailCardVisibleHM:function(state,data){
                state.orderDetailCardVisibleHM = data
                //查看订单详情，选中列表中的一项时，设置当前order的信息
            },
            set_currentOrderOfHM:function(state,data){
                state.currentOrderOfHM = data
            },
            set_webCoupon:function(state,data){
                state.webCoupon = data
            },
            set_webCouponList:function(state,data){
                state.webCouponList = data
            },
         set_appealCreditRechargeimfo:function(state, data){
            state.appealCreditRechargeimfo = data
         },
         set_appealReviewOrderId: function (state, data) {
             state.appealReviewOrderId = data
             console.log(state.appealReviewOrderId)
         },
        set_currentHotelId: function(state, data) {
            state.currentHotelId = data
        },

        // set_orderList: function(state, data) {
        //     state.orderList = data
        // },
        set_orderListOfCurrentHotel:function(state,data){
                state.orderListOfCurrentHotel = data
        },
        set_addHotelModalVisible: function(state, data) {
            state.addHotelModalVisible = data
        },
         set_appealReviewModalVisible:function(state,data){
            state.appealReviewModalVisible = data
         },
        set_updateHotelModalVisible: function(state, data) {
            state.updateHotelModalVisible = data
        },
        set_addRoomModalVisible: function(state, data) {
            state.addRoomModalVisible = data
        },
        set_addRoomParams: function(state, data) {
            state.addRoomParams = {
                ...state.addRoomParams,
                ...data,
            }
        },
        set_couponVisible: function(state, data) {
            state.couponVisible = data
        },
        set_couponList: function(state, data) {
            state.couponList = data
        },
        set_addCouponVisible: function(state, data) {
            state.addCouponVisible =data
        },
            set_hotelImgUrl: function (state, data) {
                state.hotelImgUrl=data
            },
            set_roomImgUrl: function (state, data) {
                state.roomImgUrl=data
            },
            set_vipListOfCurrentHotel:function(state,data){
                state.vipListOfCurrentHotel = data
            },
    },
    actions: {
        getVipListOfCurrentHotel: async({commit, state}) => {
            const res = await getVipListOfCurrentHotelAPI(state.currentHotelId)
            if(res){
                commit('set_vipListOfCurrentHotel',res)
                console.log(state.vipListOfCurrentHotel)
            }
        },
        getHotelById: async({commit, state}) => {
            const res = await getHotelByIdAPI({
                hotelId: state.currentHotelId
            })
            if(res){
                commit('set_currentHotelInfo', res)
            }
        },
        getHotelByHMId: async({commit, state},data) => {
            const res = await getHotelByHMIdAPI({
                HMId: data
             })
            if(res){
                commit('set_currentHotelInfo', res)
                commit('set_currentHotelId',Number(res.id))
            }
        },//根据传入的HM的id设定酒店信息
        deleteCoupon: async({ state, commit,dispatch },data) => {
            const res = await deleteCouponAPI(data)
            if(res){
                message.success('删除成功')
                dispatch('getWebCouponList', res)
            }
            else{
                message.error('删除失败')
            }
        },
        deleteHPic: async({ state, commit,dispatch },params) => {
            const res = await deleteHPicAPI(params)
            if(res){
                message.success('删除成功')
                dispatch('getHotelById')
            }
            else{
                message.error('删除失败')
            }
        },
        deleteHotelCoupon: async({ state, commit,dispatch },data) => {
            const res = await deleteCouponAPI(data)
            if(res){
                message.success('删除成功')
                dispatch('getHotelCoupon',res)
            }
            else{
                message.error('删除失败')
            }
        },
        getWebCouponList: async({ state, commit }) => {
            const res = await getWebCouponListAPI()
            if(res){
                commit('set_webCouponList', res)
                console.log("WebCouponList:"+res)
            }
        },
        // getAllOrders: async({ state, commit }) => {
        //     const res = await getAllOrdersAPI()
        //     if(res){
        //         commit('set_orderList', res)
        //         console.log("orderList:"+res)
        //     }
        // },

        getOrderListOfCurrentHotel:async ({state,commit})=>{
            let hotelId = state.currentHotelId
            console.log(hotelId)
            const res = await getOrderListOfCurrentHotelAPI(hotelId)
            if(res){
                commit('set_orderListOfCurrentHotel',res)
            }
        },
        deleteHotel: async({ state, dispatch, commit }) => {
            const res = await deleteHotelAPI(state.currentHotelId)
            if(res){
                message.success('删除成功')
                dispatch('getHotelList', res)
            }
            else{
                message.error('删除失败')
            }
        },
        deleteRoom: async({ state, dispatch, commit }) => {
            const res = await deleteRoomAPI(state.currentRoomId)
            if(res){
                message.success('删除成功')
                dispatch("getHotelById",state.currentHotelId)
            }
            else{
                message.error('删除失败')
            }
        },
        addHotel: async({state,dispatch, commit },data) => {
            if(state.hotelImgUrl!=null){
                const data1={
                    hotelImg:state.hotelImgUrl,
                    ...data
                }
                const res = await addHotelAPI(data1)
                if(res){
                    dispatch('getHotelList')//添加酒店后HotelList改变，所以执行getHotelList
                    commit('set_addHotelModalVisible', false)//添加酒店后关闭模态框
                    message.success('添加成功')
                }else{
                    message.error('添 加失败')
                }
                commit('set_hotelImgUrl',null);
            }else{
                message.error('未上传酒店图片')
            }

        },
        uploadHotelImg: async ({state ,commit, dispatch} , data) => {
            const res1 = await uploadImgAPI(data)
            if (res1) {
                commit('set_hotelImgUrl', res1)
                message.success('上传成功')
            }
        },
        uploadRoomImg: async ({state ,commit, dispatch} , data) => {
            const res1 = await uploadImgAPI(data)
            if (res1) {
                commit('set_roomImgUrl', res1)
                message.success('上传成功')
            }
        },
        updateHotelImg: async ({state , dispatch} , data) => {
                const res1 = await uploadImgAPI(data)
                if (res1) {
                    const params = {
                        id: state.currentHotelId ,
                        hotelImg: res1,
                    }
                    const res2 = await updateHotelImgAPI(params)
                    if (res2) {
                        message.success('上传成功')
                        dispatch("getHotelById",state.currentHotelId)
                    }
                }
            },
        uploadHPic: async({state , dispatch} , data) => {
            const res1 = await uploadImgAPI(data)
            if (res1) {
                const data = {
                    imgUrl: res1,
                    custom:false,
                    hotelId: state.currentHotelId
                }
                const res2 = await addHPicAPI(data)
                if (res2) {
                    message.success('上传成功')
                    dispatch("getHotelById",state.currentHotelId)
                }
            }
            },
        updateRoomImg: async ({state , dispatch} , data) => {
                const res1 = await uploadImgAPI(data)
                if (res1) {
                    const params = {
                        id: state.currentRoomId ,
                        roomImg: res1,
                    }
                    const res2 = await updateRoomImgAPI(params)
                    if (res2) {
                        message.success('上传成功')
                        dispatch("getHotelById",state.currentHotelId)
                    }
                }
            },
        updateHotel: async({ state, dispatch, commit },data) => {
            const res = await updateHotelAPI(data)
            dispatch('getHotelList')//添加酒店后HotelList改变，所以执行getHotelList
            dispatch('getHotelById',state.currentHotelId)//按理说这个应该放到vue里面因为HM和C只需要执行这两个中的一个
            commit('set_updateHotelModalVisible', false)//编辑后关闭模态框
            message.success('更新成功')
        },
        addRoom: async({ state, dispatch, commit },data) => {
            if(state.roomImgUrl!=null){
                const data1={
                    roomImg:state.roomImgUrl,
                    ...data
                }
                const res = await addRoomAPI(data1)
                if(res){
                    commit('set_addRoomModalVisible', false)
                    message.success('添加成功')
                    dispatch("getHotelById",state.currentHotelId)
                }else{
                    message.error('添加失败')
                }
                commit('set_roomImgUrl',null);
            }else{
                message.error('未上传酒店图片')
            }
        },
        updateRoom: async({ state, dispatch, commit }) => {
            const res = await updateRoomAPI(state.updateRoomParams)
            if(res){
                commit('set_updateRoomModalVisible', false)
                message.success('更新成功')
                dispatch("getHotelById",state.currentHotelId)
            }else{
                message.error('更新失败')
            }
        },
        getHotelCoupon: async({ state, commit }) => {
            console.log("载入优惠:"+state.currentHotelId)
            const res = await hotelAllCouponsAPI({hotelId:state.currentHotelId})
            if(res) {
                // 获取到酒店策略之后的操作（将获取到的数组赋值给couponList）
                commit('set_couponList', res)
            }
        },
        getAppealMessageReviewByOID:async ({commit, state})=>{
            console.log("appealReviewOrderId:"+state.appealReviewOrderId)
            const res = await getAppealMessageAPI(state.appealReviewOrderId)
            if(res){
                console.log(res)
                state.appealMessageReview = res
            }
        },
        addHotelTargetMoneyCoupon: async({ commit, dispatch }, data) => {
            const res = await hotelTargetMoneyAPI(data)
            console.log(data)
            if(res){
                // 添加成功后的操作（提示文案、modal框显示与关闭，调用优惠列表策略等）

                dispatch('getHotelCoupon')
                dispatch('getWebCouponList')

                commit('set_addCouponVisible',false)
                commit('set_couponVisible',false)
                message.success('添加成功')
            }else{
                // 添加失败后的操作
                message.error('添加失败')
            }
        },

        addHotelTargetRoomCoupon: async({ commit, dispatch }, data) => {
            const res = await hotelTargetRoomAPI(data)
            if(res){
                // 添加成功后的操作（提示文案、modal框显示与关闭，调用优惠列表策略等）
                dispatch('getHotelCoupon')
                dispatch('getWebCouponList')

                commit('set_addCouponVisible',false)
                commit('set_couponVisible',false)
                message.success('添加成功')
            }else{
                // 添加失败后的操作
                message.error('添加失败')
            }
        },

        addHotelTargetTimeCoupon: async({ commit, dispatch }, data) => {
            const res = await hotelTargetTimeAPI(data)
            if(res){
                // 添加成功后的操作（提示文案、modal框显示与关闭，调用优惠列表策略等）
                dispatch('getHotelCoupon')
                dispatch('getWebCouponList')
                commit('set_addCouponVisible',false)
                commit('set_couponVisible',false)
                message.success('添加成功')
            }else{
                // 添加失败后的操作
                message.error('添加失败')
            }
        },

        deleteOrder: async({ state, dispatch }, orderId) => {
            const  res = await deleteOrderAPI(orderId)
            if(res){
                dispatch('getOrderListOfCurrentHotel')
                message.success('删除成功')
            }else{
                message.success("删除失败")
            }
        },
        checkedIn:async({ state, dispatch }, record) => {
            //入住成功增加信用值
            await creditRechargeAPI({
                id:record.userId,
                amount : record.price
            })
           const res = await checkedInAPI(record.id)
            if(res){
                dispatch('getOrderListOfCurrentHotel')
                message.success('入住登记成功')
            }else{
                message.success("入住登记失败")
            }
        },
        changeAppealStatus: async ({state,dispatch}, data)=>{
            //传进来的data只是一个字符串
            if(data == '审核通过'){
                //恢复信用值
                await creditRechargeAPI(state.appealCreditRechargeimfo)
            }
            data = {
                appealStatus:data,
                orderId: state.appealReviewOrderId
            }
            const res = await  changeAppealStatusAPI(data)
            if(res){
                dispatch('getOrderListOfCurrentHotel')//前台刷新显示
            }
        },
        autoUpdateOrderStatus:async ({state,dispatch}, data)=>{
            await autoUpdateOrderStatusAPI()
        },
        deleteVIPHotel:async ({state,dispatch}, data)=>{
            let params ={
                ...data
            }
            const res = await deleteVIPHotelAPI(params)
            console.log(params,6666666666666666666666666666666666)
            if(res){
                dispatch('getVipListOfCurrentHotel')
                message.success('成功取消该用户会员资格')
            }else{
                message.error("取消该用户会员资格失败")
            }
        },
    }
}
export default hotelManager