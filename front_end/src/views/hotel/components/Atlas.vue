<template>
    <a-modal
            :visible="atlasVisible"
            :width="1200"
            :footer="null"
            @cancel="handleCancel"
    >
        <div class="atlas" :visible="atlasVisible">
            <div class="currentImg">
                <img alt="example"
                     :src=curHotelImg
                     slot="cover"
                     style="height: 420px;width: 640px"
                     referrerPolicy="no-referrer"
                     v-if="curHotelImg!=null"
                />
            </div>
            <div class="brief">
                <a-tabs >
                    <a-tab-pane tab="酒店官方" key="1">
                        <div class="hotelDetailImgs">
                            <template v-for="item in imgList">
                                <img :src="item.imgUrl" class="imgWindow"  :key="item.id" v-if="item.custom===false" @click="handleClick(item.imgUrl)">
                            </template>
                        </div>
                    </a-tab-pane>
                    <a-tab-pane tab="用户上传" key="2">
                        <div class="hotelDetailImgs">
                            <template v-for="item in imgList">
                                <img :src="item.imgUrl" class="imgWindow"  :key="item.id" v-if="item.custom===true" @click="handleClick(item.imgUrl)">
                            </template>  </div>
                    </a-tab-pane>
                </a-tabs>
            </div>
        </div>

    </a-modal>
</template>

<script>
    import { mapActions , mapGetters , mapMutations } from "vuex";
    export default {
        name: "Atlas.vue",
        data(){
            return {
                // imgList:[
                //     {
                //         imgUrl:"https://tse3-mm.cn.bing.net/th/id/OIP.Ar22_tqDQJOlRdiERR8BSgHaE_?w=257&h=180&c=7&o=5&dpr=1.25&pid=1.7",
                //         id:1,
                //         isCustom:false,
                //         hotelId:1
                //     },
                //     {
                //         imgUrl:"https://tse3-mm.cn.bing.net/th/id/OIP.Ar22_tqDQJOlRdiERR8BSgHaE_?w=257&h=180&c=7&o=5&dpr=1.25&pid=1.7",
                //         id:1,
                //         isCustom:false,
                //         hotelId:1
                //     },{
                //         imgUrl:"https://tse3-mm.cn.bing.net/th/id/OIP.Ar22_tqDQJOlRdiERR8BSgHaE_?w=257&h=180&c=7&o=5&dpr=1.25&pid=1.7",
                //         id:1,
                //         isCustom:false,
                //         hotelId:1
                //     },{
                //         imgUrl:"https://tse3-mm.cn.bing.net/th/id/OIP.Ar22_tqDQJOlRdiERR8BSgHaE_?w=257&h=180&c=7&o=5&dpr=1.25&pid=1.7",
                //         id:1,
                //         isCustom:false,
                //         hotelId:1
                //     },{
                //         imgUrl:"https://tse3-mm.cn.bing.net/th/id/OIP.Ar22_tqDQJOlRdiERR8BSgHaE_?w=257&h=180&c=7&o=5&dpr=1.25&pid=1.7",
                //         id:1,
                //         isCustom:false,
                //         hotelId:1
                //     },
                //     {
                //         imgUrl:"https://tse3-mm.cn.bing.net/th/id/OIP.Ar22_tqDQJOlRdiERR8BSgHaE_?w=257&h=180&c=7&o=5&dpr=1.25&pid=1.7",
                //         id:1,
                //         isCustom:true,
                //         hotelId:1
                //     },
                //     {
                //         imgUrl:"https://tse3-mm.cn.bing.net/th/id/OIP.Ar22_tqDQJOlRdiERR8BSgHaE_?w=257&h=180&c=7&o=5&dpr=1.25&pid=1.7",
                //         id:1,
                //         isCustom:true,
                //         hotelId:1
                //     },
                //     {
                //         imgUrl:"https://tse3-mm.cn.bing.net/th/id/OIP.Ar22_tqDQJOlRdiERR8BSgHaE_?w=257&h=180&c=7&o=5&dpr=1.25&pid=1.7",
                //         id:1,
                //         isCustom:true,
                //         hotelId:1
                //     },
                //     {
                //         imgUrl:"https://tse3-mm.cn.bing.net/th/id/OIP.Ar22_tqDQJOlRdiERR8BSgHaE_?w=257&h=180&c=7&o=5&dpr=1.25&pid=1.7",
                //         id:1,
                //         isCustom:true,
                //         hotelId:1
                //     },{
                //         imgUrl:"https://tse3-mm.cn.bing.net/th/id/OIP.Ar22_tqDQJOlRdiERR8BSgHaE_?w=257&h=180&c=7&o=5&dpr=1.25&pid=1.7",
                //         id:1,
                //         isCustom:true,
                //         hotelId:1
                //     },
                //
                // ],
                curImgUrl:"https://tse1-mm.cn.bing.net/th/id/OIP.gEDe1XAAlRGW6GAkKQwQGAHaEG?w=280&h=180&c=7&o=5&dpr=1.25&pid=1.7",
            }

        },
        computed: {
            ...mapGetters([
                'currentHotelInfo',
                'userId',
                'atlasVisible',
                'imgList',
                'curHotelImg'
            ])
        },
        mounted() {
        },
        methods: {
            ...mapMutations([
                'set_currentHotelId' ,
                'set_atlasVisible',
                'set_curHotelImg',
                'set_curHotelImg'
            ]) ,
            ...mapActions([
                'getHotelById',
            ]) ,
            handleCancel(){
                this.set_atlasVisible(false)
            },
            handleClick(text){
                this.set_curHotelImg(text)
            },
        }
    }
</script>

<style scoped lang="less">

    .atlas{
        display: flex;
        justify-content: space-between;
        .currentImg{
            height: 100%;
            width: 60%;
        }
        .brief{
            display: flex;
            flex-wrap: wrap;
            width: 40%;

            .hotelDetailImgs{
                display: flex;
                flex-wrap: wrap;
                overflow-y: scroll;
                height: 380px;
                justify-content: flex-start;
                align-content: flex-start;
                .imgWindow{
                    height: 80px;
                    width: 100px;
                    margin-right: 5px;
                    margin-bottom: 5px;
                }
            }
        }

    }

</style>