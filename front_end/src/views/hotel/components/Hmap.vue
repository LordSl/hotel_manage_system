<template>
    <div style="height: 540px">
        <div class="amap-wrapper">
            <el-amap vid="amapDemo"
                     :zoom="zoom"
                     :center="center"
                     :plugin="plugins"
            >
                <el-amap-marker
                        v-for="item in markers"
                        :position="item.position"
                        :key="item.id"
                        :events="item.event"
                ></el-amap-marker>

                <el-amap-info-window v-if="window"
                                     :content="window.content"
                                     :visible="window.visible"
                                     :position="window.position"
                                     :events="window.events"
                >
                </el-amap-info-window>
                <div style="position: absolute;right:0px;bottom:0px;height: 40px" v-if="buttonVisible">
                    <a-button @click="jumpToDetails(jumpId)">查看酒店详细信息</a-button>
                </div>

            </el-amap>

        </div>
        <div style="background: white;height: 30px">
        <span style="display: flex">
          当前酒店： 经度 = {{ center[0] }} 纬度 = {{ center[1] }}
        </span>

        </div>
    </div>


</template>
<!-- 地图结束-->

<script>
    import { mapGetters ,mapActions, mapMutations } from "vuex";
    import hotelList from "../hotelList";

    export default {
        data() {
            let self =this
            return {
                zoom: 18,
                center: [121.59996, 31.197646],
                markers: [],
                windows:[],
                window:'',
                lng: 0,
                lat: 0,
                loaded: false,
                plugins:[
                    "AMap.Scale",
                    "AMap.ToolBar",
                    "AMap.MapType",
                    {
                        pName: 'Geolocation' ,
                        events: {
                            click(o) {
                                // o 是高德地图定位插件实例
                                o.getCurrentPosition((status , result) => {
                                    if (result && result.position) {
                                        self.lng = result.position.lng;
                                        self.lat = result.position.lat;
                                        // self.center=[self.lng,self.lat]
                                        self.loaded = true;
                                        console.log(self.loaded)
                                        // self.$nextTick();
                                    }
                                });
                            }

                        }
                    },
                ],
                buttonVisible:false,
                jumpId:''
            };
        },
        inject:['reload'],
        computed: {
            ...mapGetters([
                'hotelList',
                'currentHotelInfo'
            ])
        },
        mounted() {
            console.log(this.currentHotelInfo)
            let center=[this.currentHotelInfo.longitude,this.currentHotelInfo.latitude]
            this.center=center
            let markers=[]
            let windows=[]
            let self=this
            let num=this.hotelList.length
            for(let i=0;i<num ; i ++){
                markers.push({
                    position:  [self.hotelList[i].longitude, self.hotelList[i].latitude],
                    event : {
                        click() {
                            self.windows.forEach(window => {
                                window.visible=false;
                                });
                            self.window = self.windows[i]
                            self.$nextTick(()=>{
                                self.window.visible=true
                                self.buttonVisible=true;
                                self.jumpId=self.hotelList[i].id
                            })
                        },
                    },
                    id:Number(i)
                    })
                let title = '<div style="text-align: center;height: 20%;color: #333333; font-size: 15px"> '+self.hotelList[i].name+'</div>',
                    content = [];
                content.push(title);
                content.push("<div style='float: left;'><img src="+self.hotelList[i].hotelImg+" style='width: 100px;height: 75px'></div><span style=''>地址："+self.hotelList[i].address+"</span>");
                content.push("<span>电话："+self.hotelList[i].phoneNum+"</span>");
                content.push("<span>评分："+self.hotelList[i].rate+"</span>");
                    windows.push({
                        id:self.hotelList[i].id,
                        position: [self.hotelList[i].longitude, self.hotelList[i].latitude],
                        content: `<div class="prompt">${ content.join("<br/>") }</div>`,
                        visible: false,
                        events: {
                            close() {
                                self.buttonVisible=false;
                            }
                        }
                    });
            }
            windows.filter(item => ((item.id == self.currentHotelInfo.id))).forEach(function (item) {
                item.visible=true;
                self.window=item;
            })
            this.markers = markers;
            this.windows = windows;
        },

        methods:{
            ...mapMutations([
                'set_currentHotelId',
            ]),
            ...mapActions([
                'getHotelById',
                'getHotelList',
                'getCouponListForDisplay'
            ]),
            jumpToDetails (id) {
                this.set_currentHotelId(id)
                this.getCouponListForDisplay(id)
                this.$router.push({ name: 'hotelDetail', params: { hotelId: id }})
                this.getHotelById()
            },
            beforeRouteUpdate(to, from, next) {

                next()
            },

        }

    };
</script>

<style>
    .amap-wrapper {
        width: 2000px;
        height: 500px;
    }
    .prompt {
        background: #ffffff;
        width: 300px;
        height: 90px;
    }
    .tip {
        background: white;
        width: 200px;
        height: 100px;
    }
    .toolbar{
        width: 2000px;
        height: 40px;
    }
</style>
