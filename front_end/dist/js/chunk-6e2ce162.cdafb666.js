(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-6e2ce162"],{a1cc:function(e,t,n){"use strict";n("d15e")},d15e:function(e,t,n){},d58e:function(e,t,n){},ed3a:function(e,t,n){"use strict";n.r(t);var o=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"layout"}},[n("Header"),n("transition",{attrs:{name:"fade-transform",mode:"out-in"}},[n("router-view")],1)],1)},r=[],a=function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",{staticClass:"header"},[o("div",{staticClass:"label"},[o("img",{staticClass:"logo",attrs:{src:n("d68e"),alt:"logo"},on:{click:e.jumpToHome}}),o("span",{staticClass:"title"},[e._v("NJUSE 酒店管理系统")])]),o("a-menu",{attrs:{mode:"horizontal",theme:"light"},model:{value:e.current,callback:function(t){e.current=t},expression:"current"}},[o("a-menu-item",{key:"1",on:{click:e.selectMenu}},[o("router-link",{attrs:{to:"/hotel/hotelList"}},[o("a-icon",{attrs:{type:"home"}}),e._v("首页 ")],1)],1),"Client"==e.userInfo.userType?o("a-menu-item",{key:"2",on:{click:e.jumpToUserInfo}},[o("a-icon",{attrs:{type:"user"}}),e._v("个人中心 ")],1):e._e(),"HotelManager"==e.userInfo.userType||"Manager"==e.userInfo.userType?o("a-menu-item",{key:"3",on:{click:e.selectMenu}},[o("router-link",{attrs:{to:{name:"manageHotel"}}},[o("a-icon",{attrs:{type:"switcher"}}),e._v("酒店管理 ")],1)],1):e._e(),"Manager"==e.userInfo.userType?o("a-menu-item",{key:"4",on:{click:e.selectMenu}},[o("router-link",{attrs:{to:{name:"manageUser"}}},[o("a-icon",{attrs:{type:"user"}}),e._v("账户管理 ")],1)],1):e._e(),"HotelManager"==e.userInfo.userType?o("a-menu-item",{key:"5",on:{click:e.selectMenu}},[o("router-link",{attrs:{to:{name:"manageOrder"}}},[o("a-icon",{attrs:{type:"switcher"}}),e._v("订单管理 ")],1)],1):e._e(),"Manager"==e.userInfo.userType?o("a-menu-item",{key:"6",on:{click:e.selectMenu}},[o("router-link",{attrs:{to:{name:"manageAdvertisement"}}},[o("a-icon",{attrs:{type:"account-book"}}),e._v("广告管理 ")],1)],1):e._e(),"Manager"==e.userInfo.userType?o("a-menu-item",{key:"7",on:{click:e.selectMenu}},[o("router-link",{attrs:{to:{name:"manageWebCoupon"}}},[o("a-icon",{attrs:{type:"account-book"}}),e._v("网站优惠政策管理 ")],1)],1):e._e(),"HotelManager"==e.userInfo.userType?o("a-menu-item",{key:"8",on:{click:e.selectMenu}},[o("router-link",{attrs:{to:{name:"manageHotelVIP"}}},[o("a-icon",{attrs:{type:"account-book"}}),e._v("酒店会员管理 ")],1)],1):e._e()],1),o("div",[o("span",{staticStyle:{"margin-right":"10px"}},[e._v("主题色调: ")]),o("a-input",{staticStyle:{width:"80px"},attrs:{type:"color"},on:{change:function(t){return e.changeColor(!1)}},model:{value:e.theme,callback:function(t){e.theme=t},expression:"theme"}},[e._v("更换主题")])],1),o("div",{staticClass:"logout"},[o("a-dropdown",{attrs:{placement:"bottomCenter"}},[o("div",{staticClass:"user"},[o("a-avatar",{attrs:{src:e.userInfo.avatarUrl}}),o("span",{staticStyle:{"font-size":"14px"}},[e._v(e._s(e.userInfo.userName))]),o("a-icon",{staticStyle:{"margin-left":"3px","font-size":"16px"},attrs:{type:"down"}})],1),o("a-menu",{attrs:{slot:"overlay"},slot:"overlay"},[o("a-menu-item",{on:{click:function(t){return e.jumpToHome()}}},[o("a-icon",{attrs:{type:"home"}}),e._v(" 首页 ")],1),o("a-menu-item",{on:{click:function(t){return e.jumpToUserInfo()}}},[o("a-icon",{attrs:{type:"profile"}}),e._v(" 我的信息 ")],1),o("a-menu-item",{on:{click:function(t){return e.quit()}}},[o("a-icon",{attrs:{type:"poweroff"}}),e._v(" 退出登录 ")],1)],1)],1)],1)],1)},s=[],c=(n("1d7a"),n("6a61"),n("2e91")),i=n("d211"),u=n("cf6b"),l={name:"",data:function(){return{current:["1"],theme:""}},computed:Object(i["a"])({},Object(u["c"])(["userId","userInfo","color"])),mounted:function(){"hotelList"==this.$route.name||"hotelDetail"==this.$route.name?this.current=["1"]:"userInfo"==this.$route.name?this.current=["2"]:"manageHotel"==this.$route.name?this.current=["3"]:"manageUser"==this.$route.name?this.current=["4"]:"manageOrder"==this.$route.name?this.current=["5"]:"manageAdvertisement"==this.$route.name?this.current=["6"]:"manageWebCoupon"==this.$route.name?this.current=["7"]:"manageHotelVIP"==this.$route.name&&(this.current=["8"]),console.log("color in store"+this.color),null===this.color?(this.set_color("#69004b"),this.theme="#69004b",this.changeColor(),console.log(this.color)):(this.theme=this.color,this.changeColor(!0))},created:function(){this.getUserInfo()},methods:Object(i["a"])(Object(i["a"])(Object(i["a"])({},Object(u["d"])(["set_color"])),Object(u["b"])(["logout","getUserInfo"])),{},{selectMenu:function(e){},quit:function(){var e=this;return Object(c["a"])(regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return t.next=2,e.$store.dispatch("logout");case 2:e.$router.push("/login?redirect=".concat(e.$route.fullPath));case 3:case"end":return t.stop()}}),t)})))()},jumpToUserInfo:function(){this.$router.push({name:"userInfo",params:{userId:this.userId}})},jumpToHome:function(){},changeColor:function(e){console.log(e),e?window.less.modifyVars({"@primary-color":this.color,"@link-color":this.color,"@btn-primary-bg":this.color}).then((function(){console.log("成功")})).catch((function(e){alert("失败"),console.log(e)})):(window.less.modifyVars({"@primary-color":this.theme,"@link-color":this.theme,"@btn-primary-bg":this.theme}).then((function(){console.log("成功")})).catch((function(e){alert("失败"),console.log(e)})),this.set_color(this.theme))}})},m=l,h=(n("fe26"),n("a1cc"),n("c701")),p=Object(h["a"])(m,a,s,!1,null,"703538df",null),f=p.exports,d={name:"layout",components:{Header:f}},g=d,y=Object(h["a"])(g,o,r,!1,null,null,null);t["default"]=y.exports},fe26:function(e,t,n){"use strict";n("d58e")}}]);
//# sourceMappingURL=chunk-6e2ce162.cdafb666.js.map