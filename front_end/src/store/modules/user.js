import Vue from 'vue'
import router from '@/router'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'
import { message } from 'ant-design-vue'
import {
    loginAPI,
    registerAPI,
    getUserInfoAPI,
    updateUserInfoAPI,
    creditRechargeAPI,
    updateUserImgAPI,
    setWebVIPAPI
} from '@/api/user'

import {
    getUserOrdersAPI,
    cancelOrderAPI,
    cancelAppealAPI
} from '@/api/order'

import {
    uploadImgAPI,
} from '@/api/oss'
import { getUserReplyAPI } from "@/api/comment";
import {
    getHotelListOfCurrentVIPAPI
}from '@/api/vipHotel'

const getDefaultState = () => {
    return {
        userId: '',
        appealOrderId:'',
        userInfo: {

        },
        userOrderList: [

        ],
        cancelOrderAppealModalVisible:false,
        creditRechargeModalVisible:false,
        userReplyList:[

        ],
        orderDetailCardVisible:false,
        currentOrderOfUser:{

        },
        hotelListOfCurrentVIP:[],
        historyOrderModalVisible:false,
        historyOrderList:[]
    }
}

const user = {//定义对象user
    state: getDefaultState() ,//user中定义的state对象（符合模板中module的定义）

    mutations: {//参见index中的module
        reset_state: function (state) {
            state.infoCredit = '',
                state.token = '',
                state.userId = '',
                state.appealOrderId = '',
                state.userInfo = {},
                state.userOrderList = [],
                state.cancelOrderAppealModalVisible = false,
                state.userReplyList = [],
                state.creditRechargeModalVisible = false ,
                state.orderDetailCardVisible = false,
                state.currentOrderOfUser = {}
        } ,
        set_historyOrderList:function(state,data){
            state.historyOrderList = data
            //查看历史订单
        },
        set_historyOrderModalVisible:function(state,data){
            state.historyOrderModalVisible = data
            //查看历史订单
        },
        set_orderDetailCardVisible:function(state,data){
           state.orderDetailCardVisible = data
        },
        set_currentOrderOfUser:function(state,data){
           state.currentOrderOfUser = data
        },
        set_hotelListOfCurrentVIP:function(state,data){
            state.hotelListOfCurrentVIP = data
        },
        set_token: function (state , token) {
            state.token = token
        } ,
        set_email: (state , data) => {
            state.email = data
        } ,
        set_userId: (state , data) => {
            state.userId = data
        } ,
        set_appealOrderId: (state , data) => {
            state.appealOrderId = data
        } ,
        set_userInfo: (state , data) => {
            state.userInfo = {
                ...state.userInfo ,
                ...data
            }
        } ,
        set_userOrderList: (state , data) => {
            state.userOrderList = data
        } ,
        set_creditRechargeModalVisible: (state , data) => {
            state.creditRechargeModalVisible = data
        } ,
        set_cancelOrderAppealModalVisible: (state , data) => {
            state.cancelOrderAppealModalVisible = data


        } ,
        set_userReplyList: (state , data) => {
            state.userReplyList = data;
        } ,
    },

        actions: {
            login: async ({dispatch , commit} , userData) => {
                const res = await loginAPI(userData)
                if (res) {
                    setToken(res.id)//从'@/utils/auth'中导入的方法
                    commit('set_userId' , res.id)
                    dispatch('getUserInfo')
                    router.push('/hotel/hotelList')//mutations的方法用commit，actions的方法用dispatch
                }
            } ,
            register: async ({commit} , data) => {
                const res = await registerAPI(data)
                if (res) {
                    message.success('注册成功')
                }
            } ,
            getUserInfo({state , commit}) {
                return new Promise((resolve , reject) => {
                    getUserInfoAPI(state.userId).then(response => {
                        const data = response
                        if (!data) {
                            reject('登录已过期，请重新登录')
                        }
                        commit('set_userInfo' , data)
                        commit('set_userId' , data.id)
                        console.log('getUserInfo')
                        resolve(data)
                    }).catch(error => {
                        reject(error)
                    })
                })
            } ,
            updateUserInfo: async ({state , dispatch} , data) => {
                const params = {
                    id: state.userId ,
                    ...data ,
                }
                const res = await updateUserInfoAPI(params)
                if (res) {
                    message.success('修改成功')
                    dispatch('getUserInfo')
                }
            } ,
            creditRecharge: async ({state , commit , dispatch} , data) => {
                const params = {
                    id: state.userId ,
                    ...data ,
                }
                const res = await creditRechargeAPI(params)
                if (res) {
                    message.success('充值成功')
                    dispatch('getUserInfo')
                    commit('set_creditRechargeModalVisible' , false)
                }
            } ,


            getUserOrders: async ({state , commit}) => {
                const data = {
                    userId: Number(state.userId)
                }
                const res = await getUserOrdersAPI(data)
                if (res) {
                    commit('set_userOrderList' , res)
                }
            } ,
            getUserReplies: async ({state , commit}) => {
                const params = {
                    userId: Number(state.userId)
                }
                const res = await getUserReplyAPI(params)
                console.log(res)
                if (res) {
                    commit('set_userReplyList' , res)
                    console.log(state.userReplyList)
                }
            } ,


            //申诉
            cancelAppeal: async ({state , commit , dispatch} , data) => {
                const params = {
                    orderId: state.appealOrderId ,
                    ...data ,
                }
                const res = await cancelAppealAPI(params)
                if (res) {
                    commit('set_cancelOrderAppealModalVisible' , false)
                    message.success("申诉成功，等待审核")
                    dispatch('getUserOrders')
                    //上一行的dispathch是为了更新前台数据显示，因为列表是根据orderList显示的，重新向后台获取一下
                }
            } ,
            //申诉

            //注册成为网站会员
                registerWebVIP: async ({state , commit , dispatch} , data) => {
                const params = {
                    ...data
                }
                const res = await setWebVIPAPI(params)
                if (res) {
                    dispatch('getUserInfo')
                    //上一行的dispathch是为了更新前台数据显示，因为列表是根据orderList显示的，重新向后台获取一下
                    message.success("恭喜您已成为网站会员")
                }
            } ,

            cancelOrder: async ({state , dispatch} , orderId) => {
                const res = await cancelOrderAPI(orderId)
                if (res) {
                    dispatch('getUserOrders')
                    dispatch('getUserInfo')
                    message.success('撤销成功')
                } else {
                    message.error('撤销失败')
                }
            } ,
            updateUserImg: async ({state , dispatch} , data) => {

                const res = await uploadImgAPI(data)
                if (res) {
                    const params = {
                        id: state.userId ,
                        userImg: res ,
                    }
                    const res1 = await updateUserImgAPI(params)
                    if (res1) {
                        message.success('上传成功')
                        dispatch('getUserInfo')
                    }

                }
            } ,
            logout: async ({commit}) => {
                removeToken()
                resetRouter()
                commit('reset_state')
            } ,
            // remove token
            resetToken({commit}) {
                return new Promise(resolve => {
                    removeToken() // must remove  token  first
                    commit('reset_state')
                    resolve()
                })
            } ,

            getHotelListOfCurrentVIP:async ({state , commit }) => {
                //获取当前用户的会员酒店
                console.log(state.userId)
                const res = await getHotelListOfCurrentVIPAPI(state.userId)
                if(res){
                    commit('set_hotelListOfCurrentVIP',res)
                }
            }
        }
    }


export default user