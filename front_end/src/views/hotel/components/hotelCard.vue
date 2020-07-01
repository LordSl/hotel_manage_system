<template>

  <div class="hotelDetailCard">
    <div class="hotel-info">

      <div class="originHotelInfo">
        <a-card>
          <img
                  alt="example"
                  :src="hotel.hotelImg"
                  slot="cover"
                  referrerPolicy="no-referrer"
                  style="height:200px;float: left;width: 150px"
          />
        </a-card>
        <div class="info">
          <div class="items">
            <span class="label">酒店名称：</span>
            <span class="value">{{ hotel.name }}</span>
          </div>
          <div class="items" >
            <span class="label">地址</span>
            <span class="value">{{ hotel.address }}</span>
          </div>
          <div class="items" >
            <span class="label">评分:</span>
            <span class="value">{{ hotel.rate }}</span>
          </div>
          <div class="items" >
            <span class="label">星级:</span>
            <a-rate style="font-size: 15px" :value="4" disabled allowHalf v-if="hotel.hotelStar==='Four'"/>
            <a-rate style="font-size: 15px" :value="3" disabled allowHalf v-if="hotel.hotelStar==='Three'"/>
            <a-rate style="font-size: 15px" :value="5" disabled allowHalf v-if="hotel.hotelStar==='Five'"/>
          </div>
        </div>
      </div>

      <div class="leastMoneyText">
<!--          <a-tag   color="orange" style="font-size: 16px" v-if="this.leastMoney !== 0"> 原价  {{this.leastMoney}} 元起-->
<!--          </a-tag>-->
                  <a-tag   color="orange" style="font-size: 12px;margin-bottom: 20px" v-if="this.leastMoney !== 0">￥
                  </a-tag>

        <span style="font-size: 60px;color: #FF6600" v-if="this.leastMoney !== 0"> {{this.leastMoney}}</span>
        <span style="font-size: 12px;color: #FF6600;margin-top: 20px;margin-left: 10px" v-if="this.leastMoney !== 0">起</span>
      </div>
    </div>
  </div>
</template>
<script>
  import {mapActions, mapMutations} from "vuex";

export default {
  name:'',
  props: {
    hotel: {}
  },

  async mounted() {

    // console.log(this.hotel)
    this.getLeastMoney()
  },
  data() {
    return{
      leastMoney:0
    }
  },
  methods:{
    ...mapMutations([
    ]) ,
    ...mapActions([
    ]) ,
    getLeastMoney(){
      let roomList = this.hotel.rooms
      if(roomList.length === 0)return
      let minMoney = roomList[0].price
      for(let i = 1; i<roomList.length;i++){
        if(roomList[i].price < minMoney)minMoney = roomList[i].price
      }
      this.leastMoney = minMoney
    }
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
    .originHotelInfo{
      display: flex;
      align-items: stretch;
      justify-content: flex-start;
      .info{
        padding: 10px 0;
        display: flex;
        flex-direction: column;
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
    .leastMoneyText{
      display: flex;
      align-items: center;
      margin-left: 700px;
    }
  }
</style>

