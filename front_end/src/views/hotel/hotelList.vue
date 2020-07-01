<template>
<div>
    <div style="float: left;width: 18%">
        <a-form :form="form" style="margin-top: 30px;"  layout="inline"   >
            <a-form-item label="目的城市"   style="width:280px" :labelCol="{span: 6}" :wrapperCol="{span: 8}">
                <a-cascader
                        :options="provinceData"
                        :show-search="{ filter }"
                        placeholder="请选择"
                        v-model="selectProvince"
                        style="width:135px"
                />
            </a-form-item>

            <br/>
            <br/>
            <a-form-item label="房 间 数"  style="width:280px" :labelCol="{span:6}" :wrapperCol="{span: 8}">
                <a-select
                        style="width:135px"
                        v-decorator="[
                    'roomNum',
                    { rules: [{ required: false, message: '请选择房间数' }] }]"
                >
                    <a-select-option v-for="i in 10" :key="i">
                        {{ i }}间
                    </a-select-option>
                </a-select>
            </a-form-item>

            <br/>
            <br/>


            <a-form-item label="酒店星级"  style="width:280px" :labelCol="{span:6}" :wrapperCol="{span: 8}"  >
                <a-select
                        v-decorator="[
                    'hotelStar',
                    { rules: [{ required: false, message: '请选择酒店星级' }] }]"
                        style="width:135px"
                >
                    <a-select-option value="Three">三星级</a-select-option>
                    <a-select-option value="Four">四星级</a-select-option>
                    <a-select-option value="Five">五星级</a-select-option>
                </a-select>
            </a-form-item>

            <br/>
            <br/>
            <a-form-item label="入住时间" :labelCol="{span:4}" style="width: 400px" :wrapperCol="{span: 2}">
                <a-range-picker
                        :disabled-date="disabledDate"
                        show-time
                        mode="['month', 'month']"
                        format="YYYY-MM-DD HH:mm:ss"
                        v-decorator="[
                        'date',
                        {
                            rules: [{ required: false, message: '请选择适用时间' }]
                        }
                    ]"
                        :placeholder="['入住','退房']"
                        style="width: 140px"
                />
            </a-form-item>

            <br/>
            <br/>

            <a-form-item label="酒店名称"  style="width:340px">
                <a-select
                        show-search
                        :value="value"
                        placeholder="input search text"
                        style="width:135px"
                        :default-active-first-option="false"
                        :show-arrow="false"
                        :filter-option="false"
                        :not-found-content="null"
                        @search="handleSearch"
                        @change="handleChange"
                        @blur="handleBlur"
                >
                    <a-select-option v-for="d in searchHotelList" :key="d.name">
                        {{ d.name }}
                    </a-select-option>
                </a-select>
            </a-form-item>

            <br/>
            <br/>
            <a-form-item><a-checkbox  v-model="haveReserved" style="float: left;">
                住过
            </a-checkbox>
            </a-form-item>

            <a-form-item><a-button type="primary" icon="search" ghost style="width:126px" @click="searchHotel">搜索</a-button></a-form-item>

        </a-form>
    </div>

    <!--    广告-->
    <div style="width: 82%;float: left;margin-top: 20px">
        <a-carousel autoplay  v-if="advertisementList.length !== 0"
                    style="height: 350px;width: 100%; margin-top: 10px;">
            <img :style="((item.hotel == null)?'':'cursor:pointer;')+ 'height: 350px;width: 100%'" v-for="item in advertisementList" :key="item.adId" :src="item.adImgUrl" @click="jumpToADHotel(item)"/>
        </a-carousel>
    </div>
    <!--    广告-->



  <div class="hotelList" style="margin-top: 20px;clear: left">
    <a-layout>
        <a-layout-content id="htl">
                <div class="twoLists">
                    <a-list
                            :data-source="hotelList"
                            item-layout="horizontal"
                            :pagination="true"
                    >
                        <a-list-item slot="renderItem" slot-scope="item">
                            <HotelCard :hotel="item" v-bind:key="item.index"  @click.native="jumpToDetails(item.id)"></HotelCard>
                        </a-list-item>
                    </a-list>
                </div>
      </a-layout-content>
    </a-layout>
  </div>
</div>
</template>
<script>
import HotelCard from './components/hotelCard'
import { mapGetters, mapActions, mapMutations } from 'vuex'
import AFormItem from "ant-design-vue/es/form/FormItem";
const moment = require('moment')
const provinceData= [
        {
            value: '南京',
            label: '南京',
            children: [
                {
                    value: 'XiDan',
                    label: 'XiDan',
                },
                {
                    value: '新街口',
                    label: '新街口',
                },
                {
                    value: '夫子庙地区',
                    label: '夫子庙地区',
                },
                {
                    value: '禄口国际技场周边',
                    label: '禄口国际技场周边',
                },
                {
                    value: '雨花台南京南站地区',
                    label: '雨花台南京南站地区',
                },
                {
                    value: '明故宫',
                    label: '明故宫',
                },
                {
                    value: '火车站地区',
                    label: '火车站地区',
                },
                {
                    value: '万达广场',
                    label: '万达广场',
                },
                {
                    value: '江宁开发区',
                    label: '江宁开发区',
                },
                {
                    value: '龙江地区',
                    label: '龙江地区',
                },
                ],
        },
        {
            value: '北京',
            label: '北京',
            children: [
                {
                    value: '长城',
                    label: '长城',
                },
                {
                    value: '中关村',
                    label: '中关村',
                },
                {
                    value: '天安门',
                    label: '天安门',
                },
                {
                    value: '首都机场',
                    label: '首都机场',
                },
                {
                    value: '国贸地区',
                    label: '国贸地区',
                },
                {
                    value: '亚运村',
                    label: '亚运村',
                },
                {
                    value: '北京展览馆地区',
                    label: '北京展览馆地区',
                },
                {
                    value: '前门',
                    label: '前门',
                },

                ],
        },
    {
        value: '重庆',
        label: '重庆',
        children: [
            {
                value: '万达广场',
                label: '万达广场',
            },
            {
                value: '火车站',
                label: '火车站',
            },
            {
                value: '机场',
                label: '机场',
            },
            {
                value: '地铁站',
                label: '地铁站',
            },
        ],
    },
    {
        value: '大连',
        label: '大连',
        children: [
            {
                value: '万达广场',
                label: '万达广场',
            },
            {
                value: '火车站',
                label: '火车站',
            },
            {
                value: '机场',
                label: '机场',
            },
            {
                value: '地铁站',
                label: '地铁站',
            },
        ],
    },
    {
        value: '青岛',
        label: '青岛',
        children: [
            {
                value: '万达广场',
                label: '万达广场',
            },
            {
                value: '火车站',
                label: '火车站',
            },
            {
                value: '机场',
                label: '机场',
            },
            {
                value: '地铁站',
                label: '地铁站',
            },
        ],
    },
    {
        value: '西安',
        label: '西安',
        children: [
            {
                value: '万达广场',
                label: '万达广场',
            },
            {
                value: '火车站',
                label: '火车站',
            },
            {
                value: '机场',
                label: '机场',
            },
            {
                value: '地铁站',
                label: '地铁站',
            },
        ],
    },
    {
        value: '苏州',
        label: '苏州',
        children: [
            {
                value: '万达广场',
                label: '万达广场',
            },
            {
                value: '火车站',
                label: '火车站',
            },
            {
                value: '机场',
                label: '机场',
            },
            {
                value: '地铁站',
                label: '地铁站',
            },
        ],
    },
    {
        value: '香港',
        label: '香港',
        children: [
            {
                value: '万达广场',
                label: '万达广场',
            },
            {
                value: '火车站',
                label: '火车站',
            },
            {
                value: '机场',
                label: '机场',
            },
            {
                value: '地铁站',
                label: '地铁站',
            },
        ],
    },
    {
        value: '武汉',
        label: '武汉',
        children: [
            {
                value: '万达广场',
                label: '万达广场',
            },
            {
                value: '火车站',
                label: '火车站',
            },
            {
                value: '机场',
                label: '机场',
            },
            {
                value: '地铁站',
                label: '地铁站',
            },
        ],
    },
    {
        value: '郑州',
        label: '郑州',
        children: [
            {
                value: '万达广场',
                label: '万达广场',
            },
            {
                value: '火车站',
                label: '火车站',
            },
            {
                value: '机场',
                label: '机场',
            },
            {
                value: '地铁站',
                label: '地铁站',
            },
        ],
    },
    {
        value: '宁波',
        label: '宁波',
        children: [
            {
                value: '万达广场',
                label: '万达广场',
            },
            {
                value: '火车站',
                label: '火车站',
            },
            {
                value: '机场',
                label: '机场',
            },
            {
                value: '地铁站',
                label: '地铁站',
            },
        ],
    },{
        value: '台北',
        label: '台北',
        children: [
            {
                value: '万达广场',
                label: '万达广场',
            },
            {
                value: '火车站',
                label: '火车站',
            },
            {
                value: '机场',
                label: '机场',
            },
            {
                value: '地铁站',
                label: '地铁站',
            },
        ],
    },
    {
        value: '厦门',
        label: '厦门',
        children: [
            {
                value: '万达广场',
                label: '万达广场',
            },
            {
                value: '火车站',
                label: '火车站',
            },
            {
                value: '机场',
                label: '机场',
            },
            {
                value: '地铁站',
                label: '地铁站',
            },
        ],
    },
    {
        value: '成都',
        label: '成都',
        children: [
            {
                value: '万达广场',
                label: '万达广场',
            },
            {
                value: '火车站',
                label: '火车站',
            },
            {
                value: '机场',
                label: '机场',
            },
            {
                value: '地铁站',
                label: '地铁站',
            },
        ],
    },{
        value: '深圳',
        label: '深圳',
        children: [
            {
                value: '万达广场',
                label: '万达广场',
            },
            {
                value: '火车站',
                label: '火车站',
            },
            {
                value: '机场',
                label: '机场',
            },
            {
                value: '地铁站',
                label: '地铁站',
            },
        ],
    },{
        value: '广州',
        label: '广州',
        children: [
            {
                value: '万达广场',
                label: '万达广场',
            },
            {
                value: '火车站',
                label: '火车站',
            },
            {
                value: '机场',
                label: '机场',
            },
            {
                value: '地铁站',
                label: '地铁站',
            },
        ],
    },{
        value: '三亚',
        label: '三亚',
        children: [
            {
                value: '万达广场',
                label: '万达广场',
            },
            {
                value: '火车站',
                label: '火车站',
            },
            {
                value: '机场',
                label: '机场',
            },
            {
                value: '地铁站',
                label: '地铁站',
            },
        ],
    },

]
export default {
  name: 'home',
  components: {
      AFormItem ,
    HotelCard
  },
  data(){
    return{
      emptyBox: [{ name: 'box1' }, { name: 'box2'}, {name: 'box3'}],
      provinceData,
        selectProvince:[],
        value:'',
        reserveValue:'',
        checkInDate:'',
        checkOutDate:'',
        roomNum:'',
        hotelStar:'',
        size:'small',
        haveReserved:false
    }
  },
  async mounted() {
    await this.getHotelList()
    await this.getAdvertisementList()
  },
  computed: {
    ...mapGetters([
        'userInfo',
      'hotelList',
        'advertisementList',
      'hotelListLoading',
        'searchHotelList'
    ])
  },
    beforeCreate() {
        // 表单名默认为“form”
        this.form = this.$form.createForm(this, { name: 'searchModal' });
    },
  methods: {
    ...mapMutations([
      'set_hotelListParams',
      'set_hotelListLoading',
        'set_hotelList'
    ]),
    ...mapActions([
      'getHotelList',
        'getAdvertisementList',
        'retrieveHotel'
    ]),
      disabledDate(current) {
          // Can not select days before today and today
          return current && current < moment().startOf('day');
      },
      filter(inputValue, path) {
          return path.some(option => option.label.toLowerCase().indexOf(inputValue.toLowerCase()) > -1);
      },
    // pageChange(page, pageSize) {
    //   const data = {
    //     pageNo: page - 1
    //   }
    //   this.set_hotelListParams(data)
    //   this.set_hotelListLoading(true)
    //   this.getHotelList()
    // },
    jumpToDetails(id){
      this.$router.push({ name: 'hotelDetail', params: { hotelId: id }})
    },


    searchHotel(){
        console.log("search")
        if(this.form.getFieldValue('date')!=undefined){
            this.checkInDate=moment(this.form.getFieldValue('date')[0]).format('YYYY-MM-DD')
            this.checkOutDate=moment(this.form.getFieldValue('date')[1]).format('YYYY-MM-DD')
        }
        if(this.form.getFieldValue('roomNum')!=undefined){
            this.roomNum=Number(this.form.getFieldValue('roomNum'))
        }
        if(this.form.getFieldValue('hotelStar')!=undefined){
            this.hotelStar=this.form.getFieldValue('hotelStar')
        }
        if(this.selectProvince.length!=0){
            const params = {
                userId:this.userInfo.id,
                address:this.selectProvince[0],
                bizRegion:this.selectProvince[1],
                checkInDate: this.checkInDate,
                checkOutDate: this.checkOutDate,
                roomNum: this.roomNum,
                hotelStar: this.hotelStar,
                key:this.value,
                haveReserved: this.haveReserved,
                set:true
            }
            this.retrieveHotel(params)
        }
        this.checkInDate=''
        this.checkOutDate=''
    this.roomNum=''
    this.hotelStar=''
    },
      handleSearch(value){
          console.log(value)
          if(this.form.getFieldValue('date')!=undefined){
              this.checkInDate=moment(this.form.getFieldValue('date')[0]).format('YYYY-MM-DD')
              this.checkOutDate=moment(this.form.getFieldValue('date')[1]).format('YYYY-MM-DD')
          }
          if(this.form.getFieldValue('roomNum')!=undefined){
              this.roomNum=Number(this.form.getFieldValue('roomNum'))
          }
          if(this.form.getFieldValue('hotelStar')!=undefined){
              this.hotelStar=this.form.getFieldValue('hotelStar')
          }
          if(this.selectProvince.length!=0){
              const params = {
                  userId:this.userInfo.id,
                  address:this.selectProvince[0],
                  bizRegion:this.selectProvince[1],
                  checkInDate: this.checkInDate,
                  checkOutDate: this.checkOutDate,
                  roomNum: this.roomNum,
                  hotelStar: this.hotelStar,
                  key:value,
                  haveReserved: this.haveReserved,
                  set:false
              }
              this.retrieveHotel(params)
          }
          this.reserveValue=value
      },
      handleChange(value) {
          this.value = value
          this.reserveValue=''
          console.log("change")
      },
      handleBlur() {
          if(this.reserveValue!=''){
              this.value=this.reserveValue
              this.reserveValue=''
          }
          console.log("blur")
      },
      jumpToADHotel(item){
          if(Number(item.hotelId) !== -1){
              this.$router.push({name:'hotelDetail',params:{hotelId:item.hotelId}})
          }
      },
  },
  watch:{
      selectProvince(val){
          if(this.selectProvince.length==0){
              this.value=''
              this.form.setFieldsValue({
                  'date': '',
                  'roomNum': '',
                  'hotelStar':'',
              })
              this.getHotelList()
          }
      }
  }
}
</script>
<style scoped lang="less">


  .hotelList {
    text-align: center;
      padding: 50px 0;
    .emptyBox {
      height: 0;
      margin: 10px 10px
    }
    .card-wrapper{
      display: flex;
      justify-content: space-around;
      flex-wrap: wrap;
      flex-grow: 3;
      min-height: 800px
    }
    .card-wrapper .card-item {
      margin: 30px;
      position: relative;
      height: 188px;
    }
      .ant-calendar-picker-input {
          width: 180px;
      }
      .ant-input{
          width: 180px;
      }
  }
  #htl {
      background: #f8f7f7 url('../../assets/background.svg') repeat 100%;
  }
</style>