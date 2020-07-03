

<template>
    <a-layout>
        <a-layout-content id="htl">
            <div class="hotelDetailCard">


                <div class="hotel-info">
                    <div class="originInfo">
                        <a-card style="width: 240px">
                            <img
                                    alt="example"
                                    :src="currentHotelInfo.hotelImg"
                                    slot="cover"
                                    referrerPolicy="no-referrer"
                                    style="height: 340px"
                            />
                        </a-card>
                        <div class="info">
                            <div class="items" v-if="currentHotelInfo.name">
                                <span class="label">酒店名称：</span>
                                <span class="value">{{ currentHotelInfo.name }}</span>
                            </div>
                            <div class="items" v-if="currentHotelInfo.address">
                                <span class="label">地址</span>
                                <span class="value">{{ currentHotelInfo.address }}</span>
                            </div>
                            <div class="items" v-if="currentHotelInfo.rate">
                                <span class="label">评分:</span>
                                <span class="value">{{ currentHotelInfo.rate }}</span>
                            </div>
                            <div class="items" v-if="currentHotelInfo.hotelStar">
                                <span class="label">星级:</span>
                                <a-rate style="font-size: 15px" :value=currentHotelInfo.hotelStar disabled allowHalf/>
                            </div>
                            <div class="items" v-if="currentHotelInfo.description">
                                <span class="label">酒店简介:</span>
                                <span class="value">{{ currentHotelInfo.description }}</span>
                            </div>
                            <div class="items">
                                <a-button type="danger" icon="fire" style="font-weight: bold" v-if="!isVipOfCurrentHotel" size="large" ghost @click="registerAsHotelVIPBtn">注册酒店会员, 享受最大优惠</a-button>
                                <a-button type="primary" size="large" style="font-weight: bold;color: #42b983" icon="sketch" v-if="isVipOfCurrentHotel" ghost >您已成为该酒店的尊贵会员</a-button>
                            </div>
                            <div class="items">
                                <a-icon type="check-circle" theme="twoTone" two-tone-color="#52c41a" style="font-size: 24px;" class="label" v-if="hotelFlag=='曾住过'||hotelFlag=='已预订'"></a-icon>
                                <span class="value" style="color: #52c41a" v-if="hotelFlag=='曾住过'||hotelFlag=='已预订'">{{hotelFlag}}</span>
                                <a-icon type="check-circle" theme="twoTone" two-tone-color="#e72b48" style="font-size: 24px;" class="label" v-if="hotelFlag=='曾撤销订单'"></a-icon>
                                <span class="value" style="color: #e72b48" v-if="hotelFlag=='曾撤销订单'">{{hotelFlag}}</span>
                            </div>
<!--                            <div class="items">-->
<!--                                <a-button type="primary" size="large" icon="search" @click="showAtlas(null)" ghost >查看酒店图册</a-button>-->
<!--                            </div>-->
                        </div>
                    </div>

                    <div class="imgsAndTags">

                        <div class="hotelDetailImgs">
                            <template v-for="(item, index) in imgList">
                                <img :src="item.imgUrl" class="imgWindow"  :key="item.id" v-if="index<=7" @click="showAtlas(item.imgUrl)">
                            </template>
                        </div>

                        <div class="couponTags">
                            <a-tag v-for="item in couponListForDisplay" :color="(item.hotelId===0)?'yellow':'green'"  :key="item.id">{{item.name}}    {{item.description}}</a-tag>

                        </div>

                    </div>
                    </div>

                </div>
                <a-divider></a-divider>
                <a-tabs >
                    <a-tab-pane tab="房间信息" key="1">
                        <RoomList :rooms="currentHotelInfo.rooms"></RoomList>
                    </a-tab-pane>
                    <a-tab-pane tab="酒店评论" key="2">
                        <CommentList></CommentList>
                    </a-tab-pane>
                    <a-tab-pane tab="酒店地图" key="3">
                        <HotelMap :key="currentHotelInfo.id"></HotelMap>
                    </a-tab-pane>
                </a-tabs>

            <Atlas></Atlas>
        </a-layout-content>
    </a-layout>
</template>
<script>
import { mapGetters, mapActions, mapMutations } from 'vuex'
import RoomList from './components/roomList'
import CommentList from './components/commentList'
import HotelMap from './components/Hmap'
import Atlas from './components/Atlas'
export default {
    name: 'hotelDetail',
    components: {
        RoomList,
        CommentList,
        HotelMap,
        Atlas
    },
    data() {
        return {
            codes:'',
            curImgList:[]
        }
    },
    computed: {
        ...mapGetters([
            'currentHotelInfo',
            'currentHotelComments',
            'userId',
            'isVipOfCurrentHotel',
            'hotelFlag',
            'imgList',
            'couponListForDisplay'
        ])
    },
    mounted() {
        this.set_currentHotelId(Number(this.$route.params.hotelId))
        this.getHotelById()
        this.getHotelCommentsById()
        this.getCommentStatus()
        this.getHotelList()
        this.judgeVipCouple({userId:this.userId, hotelId:this.$route.params.hotelId})
        this.judgeStateOfHotel({userId:this.userId, hotelId:this.$route.params.hotelId})
        this.getPrevOrder({userId:this.userId, hotelId:this.$route.params.hotelId})
        this.getCouponListForDisplay(this.$route.params.hotelId)
    },
    beforeRouteUpdate(to, from, next) {
        this.set_currentHotelId(Number(to.params.hotelId))
        this.getHotelById()
        this.getHotelCommentsById()
        this.getCommentStatus()
        next()

    },
    methods: {
        ...mapMutations([
            'set_currentHotelId' ,
            'set_atlasVisible',
            'set_curHotelImg'
        ]) ,
        ...mapActions([
            'getHotelById' ,
            'getHotelCommentsById' ,
            'getCommentStatus' ,
            'registerAsHotelVIP' ,
            'getHotelList' ,
            'judgeVipCouple' ,
            'judgeStateOfHotel' ,
            "getPrevOrder",
            'getCouponListForDisplay'
        ]) ,
        registerAsHotelVIPBtn() {
            const param = {
                userId: this.userId ,
                hotelId: this.currentHotelInfo.id
            }
            this.registerAsHotelVIP(param)
        },
        showAtlas(text){
            if(text!=null){
                console.log("in")
                this.set_curHotelImg(text)
            }
            this.set_atlasVisible(true)

        },

    }

}
</script>


<style scoped lang="less">
    .hotelDetailCard {
        padding: 50px 50px;
    }

    .hotel-info {
        display: flex;
        justify-content: space-between;

        .originInfo{
            display: flex;
            align-items: stretch;
            justify-content: flex-start;
            width: 50%;
            .info{
                padding: 10px 0;
                display: flex;
                flex-direction: column;
                justify-content: space-evenly;

                margin-left: 20px;
                .items {
                    display: flex;
                    align-items: center;
                    margin-bottom: 10px;
                    .label{
                        margin-right: 10px;
                        font-size: 18px;
                    }
                    .value {
                        margin-right: 15px
                    }
                }
            }


        }



        .imgsAndTags{
            width: 50%;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            flex-wrap: wrap;
            .hotelDetailImgs{
                display: flex;
                flex-wrap: wrap;

                .imgWindow{
                    height: 100px;
                    margin-right: 5px;
                    margin-bottom: 5px;
                }
            }
            .couponTags{
                display: flex;
                flex-direction: row-reverse;
                flex-wrap: wrap-reverse;
            }
        }
    }
    #htl {
        background: #f8f7f7 url('../../assets/background.svg') repeat 100%;
    }
</style>
