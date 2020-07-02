<template>
    <div class="header">
        <div class="label">
            <img src="@/assets/bg.png" class="logo" alt="logo" @click="jumpToHome">
            <span class="title">NJUSE 酒店管理系统</span>

        </div>

        <a-menu v-model="current" mode="horizontal" theme="light">

            <a-menu-item key="1" @click="selectMenu">
                <router-link to="/hotel/hotelList">
                    <a-icon type="home" />首页
                </router-link>
            </a-menu-item>

            <a-menu-item key="2" @click="jumpToUserInfo" v-if="userInfo.userType=='Client'">
                <a-icon type="user" />个人中心
            </a-menu-item>

            <a-menu-item key="3" @click="selectMenu" v-if="userInfo.userType=='HotelManager'||userInfo.userType=='Manager'">
                <router-link :to="{ name: 'manageHotel'}">
                     <a-icon type="switcher" />酒店管理
                </router-link>
            </a-menu-item>

            <a-menu-item key="4" @click="selectMenu" v-if="userInfo.userType=='Manager'">
            <!--当登录账户类型为网站管理人员时，显示账户管理界面-->
                <router-link :to="{ name: 'manageUser'}">
                     <a-icon type="user" />账户管理
                </router-link>
            </a-menu-item>

            <a-menu-item key="5" @click="selectMenu" v-if="userInfo.userType=='HotelManager'">
                <router-link :to="{ name: 'manageOrder'}">
                    <a-icon type="switcher" />订单管理
                </router-link>
            </a-menu-item>

            <a-menu-item key="6" @click="selectMenu" v-if="userInfo.userType=='Manager'">
                <!--当登录账户类型为网站管理人员时，显示广告管理界面-->
                <router-link :to="{ name: 'manageAdvertisement'}">
                    <a-icon type="account-book" />广告管理
                </router-link>
            </a-menu-item>

            <a-menu-item key="7" @click="selectMenu" v-if="userInfo.userType=='Manager'">
                <!--当登录账户类型为网站管理人员时，显示网站优惠政策管理界面-->
                <router-link :to="{ name: 'manageWebCoupon'}">
                    <a-icon type="account-book" />网站优惠政策管理
                </router-link>
            </a-menu-item>

            <a-menu-item key="8" @click="selectMenu" v-if="userInfo.userType=='HotelManager'">
                <!--当登录账户类型为酒店管理人员时，显示酒店会员管理界面-->
                <router-link :to="{ name: 'manageHotelVIP'}">
                    <a-icon type="account-book" />酒店会员管理
                </router-link>
            </a-menu-item>

        </a-menu>


            <div>
                <span style="margin-right: 10px">主题色调: </span>
                <a-input type="color" style="width:80px;" v-model="theme" @change="changeColor(false)">更换主题</a-input>
            </div>



        <div class="logout">
            <a-dropdown placement="bottomCenter">
                <div class="user">
                    <a-avatar :src=userInfo.avatarUrl></a-avatar>
                    <span style="font-size: 14px">{{ userInfo.userName }}</span>
                    <a-icon style="margin-left: 3px; font-size: 16px" type="down"></a-icon>
                </div>
                <a-menu slot="overlay">
                <a-menu-item  @click="jumpToHome()">
                    <a-icon type="home"></a-icon>
                    首页
                </a-menu-item>
                <a-menu-item @click="jumpToUserInfo()">
                    <a-icon type="profile"></a-icon>
                    我的信息
                </a-menu-item>
                <a-menu-item @click="quit()">
                    <a-icon type="poweroff"></a-icon>
                    退出登录
                </a-menu-item>
                </a-menu>
            </a-dropdown>
        </div>
        
    </div>
    
</template>
<script>
import { mapGetters, mapActions, mapMutations } from 'vuex'
export default {
    name: '',
    data() {
        return {
            current: ['1'],
            theme:''
        }
    },
    computed: {
        ...mapGetters([
            'userId',
            'userInfo',
            'color'
        ])
    },
    mounted() {
        if (this.$route.name == 'hotelList' || this.$route.name == 'hotelDetail') {
            this.current = ['1']
        }else if(this.$route.name == 'userInfo') {
            this.current = ['2']
        }else if(this.$route.name == 'manageHotel') {
            this.current = ['3']
        }else if(this.$route.name == 'manageUser'){
            this.current = ['4']
        }else if(this.$route.name == 'manageOrder'){
            this.current = ['5']
        }else if(this.$route.name == 'manageAdvertisement'){
            this.current = ['6']
        }else if(this.$route.name == 'manageWebCoupon'){
            this.current = ['7']
        }else if(this.$route.name == 'manageHotelVIP'){
            this.current = ['8']
        }
        console.log("color in store" + this.color)
        if(this.color===null){
            this.set_color('#69004b')
            this.theme='#69004b'
            this.changeColor()
            console.log(this.color)
        }else{
            this.theme=this.color
            this.changeColor(true)
        }
    },
    created(){
        this.getUserInfo()
    },
    methods: {
        ...mapMutations([
            'set_color'
        ]),
        ...mapActions([
            'logout',
            'getUserInfo'
        ]),
        selectMenu(v){
        },
        async quit() {
            await this.$store.dispatch('logout')
            this.$router.push(`/login?redirect=${this.$route.fullPath}`)
        },
        jumpToUserInfo() {
            this.$router.push({ name: 'userInfo', params: { userId: this.userId } })
        },
        jumpToHome() {

        },
        changeColor (judge) {
            console.log(judge)
            if(judge){
                window.less
                    .modifyVars({
                        '@primary-color': this.color,
                        '@link-color': this.color,
                        '@btn-primary-bg': this.color
                    })
                    .then(() => {
                        console.log('成功')
                    })
                    .catch(error => {
                        alert('失败')
                        console.log(error)
                    })
            }else{
                window.less
                    .modifyVars({
                        '@primary-color': this.theme,
                        '@link-color': this.theme,
                        '@btn-primary-bg': this.theme
                    })
                    .then(() => {
                        console.log('成功')
                    })
                    .catch(error => {
                        alert('失败')
                        console.log(error)
                    })
                this.set_color(this.theme)
            }

        }
    }
}
</script>
<style scoped lang="less">
    .header {
        display: flex;
        line-height: 44px;
        height: 44px;
        align-items: center;
        justify-content: space-between;
        min-width: 800px;
        .label{
            height: 44px;
            line-height: 44px;
            vertical-align: middle;
            min-width: 400px;

            .logo {
            height: 44px;
            vertical-align: top;
            margin-right: 16px;
            border-style: none;
            cursor: pointer;
          }

          .title {
            font-size: 33px;
            color: rgba(0, 0, 0, .85);
            font-family: Avenir, 'Helvetica Neue', Arial, Helvetica, sans-serif;
            font-weight: 600;
            position: relative;
            top: 2px;
          }

          .search {
              width: 300px; 
              margin-left: 30px
          }
        }
        .logout {
            margin-right: 40px;
            .user {
                cursor: pointer;
                display:flex;
                align-items: center;
                span {
                    margin-left: 5px
                }
            }
        }

    }
</style>
<style lang="less">
    .header {
        .ant-menu {
            background: none
        }
    }
</style>