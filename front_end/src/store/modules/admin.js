import {
    getManagerListAPI,
    addManagerAPI,
    updateManagerAPI,
    deleteManagerAPI
} from '@/api/admin';
import {
    getUserOrdersAPI
}from '@/api/order'
import {
    getAdListAPI,
    deleteADAPI,
    addADImgAPI,
    bindADHotelAPI
} from "@/api/advertisement";
import {
    getHotelListByNameAPI
} from "@/api/hotel";
import {
    uploadImgAPI
} from "@/api/oss";
import {
    getWebVIPAPI,
    setWebVIPAPI
}from"@/api/user"
import {
    getWebCouponListAPI
}from"@/api/coupon"
import { message } from 'ant-design-vue'


const admin = {
    state: {
        managerList: [

        ],
        addManagerModalVisible: false,
        updateManagerModalVisible: false,
        bindADHotelModalVisible:false,
        addManagerParams: {
            userType:'',
            email:'',
            password:''
        },
        updateManagerParams: {
            id:null,
            email:'',
            userName:'',
            password:'',
            phoneNumber:'',
            credit:''
        },
        currentManagerId:'',
        adManageList:[],
        matchedHotelList:[],
        currentADId:'',
        webVIPList:[],
        currentUserId:'',
        currentUserParams:{
            userName:'',
            password:'',
            phoneNumber:'',
            credit:'',
        },
        webVIPcouponModalVisible:false,
        webVIPCouponList:[]
    },
    mutations: {
        set_managerList: function(state, data) {
            state.managerList = data
        },
        set_webVIPCouponList:function(state,data){
            state.webVIPCouponList = data
        },
        set_webVIPList:function(state,data){
           state.webVIPList = data
        },
        set_currentADId:function(state,data){
           state.currentADId = data
        },
        set_matchedHotelList: function(state, data) {
            state.matchedHotelList = data
        },
        set_updateManagerModalVisible: function(state, data) {
            state.updateManagerModalVisible = data
        },
        set_bindADHotelVisible: function(state, data) {
            state.bindADHotelModalVisible = data
        },
        set_addManagerModalVisible: function(state, data) {
            state.addManagerModalVisible = data
        },
        set_currentManagerId: function(state, data) {
            console.log(data)
            state.currentManagerId = data
            state.updateManagerParams.id = data
        },
        set_addManagerParams: function(state, data) {
            state.addManagerParams = {
                ...state.addManagerParams,
                ...data,
            }
        },
        set_webVIPcouponVisible:function(state,data){
                state.webVIPcouponModalVisible = data;
        },

        set_currentUserParams: function(state, data) {
            state.currentUserParams = {
                ...data,
            }
            console.log(state.currentUserParams)
        },
        set_currentUserId: function(state, data) {
            state.currentUserId = data
            console.log(data)
        },
        set_updateManagerParams: function(state, data) {
            state.updateManagerParams = {
                ...state.updateManagerParams,
                ...data,
            }
        },
        set_advertisementList(state,data){
            state.adManageList = data
        },
    },
    actions: {
        getManagerList: async({ state, commit }) => {
            const res = await getManagerListAPI()//这里有一个命名上的小问题，ManagerList代表的实际上是所有人的账户
            if(res){
                commit('set_managerList', res)
            }
        },

        deleteADById: async({ state, dispatch, commit },data) => {
            const res = await deleteADAPI(data)
            if(res){
                message.success('删除成功')
                dispatch('getAdvertisementList')
            }
        },
        getHotelListByName:async ({commit,state},name)=>{
            const param ={
                hotelName : name
            }
            const res = await getHotelListByNameAPI(param);
            if(res){
                commit('set_matchedHotelList',res)
            }
            if(res.length === 0){
                message.error("未找到 "+param.hotelName+" 的酒店记录")
            }
        },
        getAdvertisementList: async({commit, state}) => {
            const res = await getAdListAPI()
            if(res){
                commit('set_advertisementList', res)
            }
        },
        addManager: async({ state, commit, dispatch }) => {
            const res = await addManagerAPI(state.addManagerParams)
            if(res) {
                commit('set_addManagerModalVisible', false)
                message.success('添加成功')
                dispatch('getManagerList')
                commit('set_addManagerParams',{
                    email:'',
                    password:'',
                    userType:''
                })
            }else{
                message.error('添加失败')
            }
        },
        updateManager: async({ commit, dispatch },data) => {
            const res = await updateManagerAPI(data)
            if(res) {
                commit('set_updateManagerModalVisible', false)
                message.success('更新成功')
                dispatch('getManagerList')
                commit('set_updateManagerParams',{
                    id:null,
                    email:'',
                    name:'',
                    password:'',
                    phoneNum:'',
                    credit:''
                })
            }else{
                message.error('更新失败')
            }
        },

        uploadADImg: async ({state , dispatch} , data) => {
            const res = await uploadImgAPI(data) //res就是图片字符串
            if (res) {
                console.log(res)
                const params = {
                    imgUrl : res
                }
                const res1 = await addADImgAPI(params)
                if(res1){
                    message.success('上传成功')
                    dispatch('getAdvertisementList')
                }
            }
        } ,

        deleteManager: async({ state, dispatch, commit },data) => {
            const res = await deleteManagerAPI(data)
            if(res){
                message.success('删除成功')
                dispatch('getManagerList')
                dispatch('getWebVIP')
            }
            else{
              message.error('删除失败')
            }
        },
        getWebVip: async({ state, commit }) => {
            const res = await getWebVIPAPI()
            if(res){
              commit('set_webVIPList',res)
            }
        },
        getWebVIPCoupon:async ({state,commit})=>{
            const res = await getWebCouponListAPI()
            const webVIPcouponListGot = []
            for(let i = 0; i<res.length; i++){
                let item = res[i];
                if(item.forHotelVip === 1)
                    webVIPcouponListGot.push(item)
            }
            console.log(webVIPcouponListGot)
            commit('set_webVIPCouponList', webVIPcouponListGot)
        },

        bindADHotel: async({ state, dispatch, commit }, hId) => {
            const params = {
                adId:state.currentADId,
                hotelId : hId
            }
            const res = await bindADHotelAPI(params)
            if(res){
                message.success("绑定成功")
            }
            else{
                message.error("绑定失败")
            }
            state.matchedHotelList = []
            commit('set_bindADHotelVisible',false)
            dispatch('getAdvertisementList')
        },

        deleteWebVIP: async({ commit, dispatch }, data) => {
            const params = {
                ...data
            }
            const res = await setWebVIPAPI(params)
            if(res){
                dispatch('getWebVip')
                message.success("已取消该用户网站会员资格")
            }
        },
        getVIPOrders: async ({state , commit},userId) => {
            const data = {
                userId: userId
            }
            const res = await getUserOrdersAPI(data)
            if (res) {
                commit('set_historyOrderList' , res)
            }
        } ,
    }
}
export default admin