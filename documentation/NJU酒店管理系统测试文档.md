# NJU酒店管理系统总测试文档

## 1 引言

### 1.1 文档标识

| 作者   | 日期       | 版本 |
| ------ | ---------- | ---- |
| 赵志翔 | 2020-06-25 | v1.0 |

### 1.2 范围

该文档涵盖了对NJU酒店管理系统进行的单元测试、集成测试、系统/功能测试的输入及预期结果，记录了测试中存在的缺陷与错误，并对项目整体进行了总结与建议。

### 1.3 参考资料

《软件工程与计算（卷二） 软件开发的技术基础》

## 2 详细情况

### 2.1 总测试结果概述

依次完成了对NJU酒店管理系统的单元测试、集成测试、功能测试，测试结果大部分符合预期，测试中出现的缺陷如下，产品总体完成基本预订需求并额外实现了对酒店评论，搜索酒店、预览广告等需求。

### 2.2 详细测试结果

#### 2.2.1 单元测试

##### 2.2.1.1 测试结果概述

对项目进行了单元测试，测试主要涵盖了dao层的接口，测试确保了dao层的接口规范及逻辑符合预期。

##### 2.2.1.2 单元测试详细结果


| 测试用例ID | 种类          | 条件                                                         | 期望结果                                                     | 测试结果                           | 测试对象ID             |
| ---------- | ------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---------------------------------- | ---------------------- |
| TUS1-1     | Admin         | User user =new User();adminMapper.addManager(user);          | 数据库中插入一条用户数据，返回1                              | 得到1                              | AddManager             |
| TUS1-2     | Admin         | User user=new User();adminMapper.updateManager(user);        | 数据库中更新user信息，返回1                                  | 得到1                              | UpdateManager          |
| TUS1-3     | Admin         | adminMapper.deleteManager(1);                                | 数据库中删除id为1的用户，返回1                               | 得到1                              | DeleteManager          |
| TUS1-4     | Admin         | List<User> users=adminMapper.getAllManagers();               | 得到数据库中所有的User对象，以列表形式返回，列表大小为5      | 得到一个大小为5的User列表          | GetAllManagers         |
| TUS2-1     | Advertisement | List<Advertisement> advertisementList=advertisementMapper.getAdvertisementList(); | 得到数据库中所有的Advertisement对象，以列表形式返回，列表大小为4 | 得到一个大小为4的Advertisement列表 | GetAdvertisementList() |
| TUS3-1     | Comment       | Comment comment=new Comment(); commentMapper.insertComment(comment); | 数据库中插入一条评论数据，返回1                              | 得到1                              | InsertComment          |
| TUS3-2     | Comment       | commentMapper.deleteComment(7);                              | 数据库中删除一条评论数据，返回1                              | 得到1                              | DeleteComment          |
| TUS3-3     | Reply         | Reply reply=new Reply();replyMapper.insertReply(reply);      | 数据库中插入一条回复数据，返回1                              | 得到1                              | InsertReply            |
| TUS3-4     | Reply         | replyMapper.deleteReply(7);                                  | 数据库中删除一条回复数据，返回1                              | 得到1                              | DeleteReply            |
| TUS3-5     | Status        | CommentStatus commentStatus=new CommentStatus(); statusMapper.insertCommentStatus(commentStatus); | 数据库中插入一条记录用户对评论的点赞状态数据，返回1          | 得到1                              | InsertCommentStatus    |
| TUS3-6     | Status        | List<CommentStatus> status=statusMapper.getCommentStatus();  | 得到数据库中所有的Stauts对象，以列表形式返回，列表大小为1    | 得到一个大小为1的Status列表        | GetCommentStatus       |
| TUS4-1     | Coupon        | Coupon coupon=new Coupon()；couponMapper.insertCoupon(coupon); | 数据库中插入一条优惠数据，返回1                              | 得到1                              | InsertCoupon           |
| TUS4-2     | Coupon        | couponMapper.deleteCoupon(2);                                | 数据库中删除一条优惠数据，返回1                              | 得到1                              | DeleteCoupon           |
| TUS5-1     | Hotel         | Hotel hotel=new Hotel();hotelMapper.insertHotel(hotel);      | 数据库中插入一条酒店数据，返回1                              | 得到1                              | InsertHotel            |
| TUS5-2     | Hotel         | List<Hotel>  hotelList=hotelMapper.selectAllHotel();         | 得到数据库中所有的hotel对象，以列表形式返回，列表大小为4     | 得到一个大小为4的Status列表        | SelectAllHotel         |
| TUS5-3     | Room          | HotelRoom room=new HotelRoom(); roomMapper.insertRoom(room); | 数据库中插入一条酒店房间数据，返回1                          | 得到1                              | InsertRoom             |
| TUS6-1     | Appeal        | Appeal appeal=appealMapper.getAppealById(0);                 | 根据orderID来获取用户申诉状态，返回null                      | 得到null                           | GetAppealById          |
| TUS6-2     | Order         | Order order=new Order();orderMapper.addOrder(order);         | 数据库中插入一条订单数据，返回1                              | 得到1                              | AddOrder               |
| TUS6-2     | Order         | List<Order> orderList=orderMapper.getUserOrders(1);          | 根据用户id来获取用户所有的order数据，以列表形式返回，列表大小为1 | 得到一个大小为1的Order列表         | GetUserOrders          |
| TUS7-1     | Account       | User user=new User();accountMapper.createNewAccount(user);   | 数据库中插入一条用户数据，返回1                              | 得到1                              | CreateNewAccount       |
| TUS7-2     | Account       | User user=accountMapper.getAccountById(2);                   | 根据用户id来获取用户信息                                     | 得到id为2的用户信息                | GetAccountById         |
| TUS8-1     | VIPHotel      | VIPHotel vipHotel=vipHotelMapper.retriveByUidHid(1,1);       | 根据用户id,酒店id来获取用户是否为该就酒店的会员              | 得到记录用户与酒店关系的信息       | RetriveByUidHid        |

#### 2.2.2 集成测试

##### 2.2.2.1 测试结果概述

在对系统进行单元测试之后，对系统进行的集成测试，包含了对系统service层接口的正确性检验工作。

##### 2.2.2.2 集成测试详细结果

| 测试用例ID | 种类          | 条件                                                         | 期望结果                                                     | 测试结果                             | 测试对象ID           |
| ---------- | ------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------ | -------------------- |
| TUS1-1     | Admin         | UserForm userForm=new UserForm(); adminService.addManager(userForm); | 将前端传入的userform形式的vo转化为po并插入数据库，返回true   | 得到true                             | addManager           |
| TUS1-2     | Admin         | List<UserVO> userVOList=adminService.getAllManagers();       | 得到数据库中所有的User对象，并转化为vo，返回大小为5的列表    | 得到一个大小为5的UserVO列表          | getAllManagers       |
| TUS2-1     | Advertisement | List<AdvertisementVO> advertisements=advertisementService.getAdvertisementList(); | 得到数据库中所有的Advertisement对象，并转化为vo，以列表形式返回，列表大小为4 | 得到一个大小为4的AdvertisementVO列表 | GetAdvertisementList |
| TUS3-1     | Comment       | CommentVO commentVO=new CommentVO(); commentService.insertComment(commentVO); | 将前端传入的CommentVO形式的vo转化为po并插入数据库，返回true  | 得到true                             | InsertComment        |
| TUS3-2     | Comment       | ResponseVO responseVO=commentService.deleteComment(7);       | 数据库中删除一条评论数据，返回true                           | 得到true                             | DeleteComment        |
| TUS3-3     | Reply         | ReplyVO replyVO=new ReplyVO();replyService.insertReply(replyVO); | 将前端传入的replyVO形式的vo转化为po并插入数据库，返回true    | 得到true                             | InsertReply          |
| TUS3-4     | Reply         | ResponseVO responseVO=replyService.deleteReplyById(7);       | 数据库中删除一条回复数据，返回true                           | 得到true                             | DeleteReply          |
| TUS3-5     | Status        | CommentStatusVO commentStatusVO=new CommentStatusVO(); ResponseVO responseVO=commentStatusService.insertCommentStatus(commentStatusVO); | 将前端传入的CommentStatusVO形式的vo转化为po并插入数据库，返回true | 得到true                             | InsertCommentStatus  |
| TUS3-6     | Status        | ResponseVO responseVO=commentStatusService.getCommentStatus(); | 得到数据库中所有的Stauts对象，并转换成vo，以列表形式返回，返回true | 得到true                             | GetCommentStatus     |
| TUS4-1     | Coupon        | List<Coupon> coupons=couponService.getHotelAllCoupon(2);     | 根据hotelId来获取酒店所有的优惠策略，返回一个大小为1的列表   | 得到大小为1的一个列表                | getHotelAllCoupon    |
| TUS4-2     | Coupon        | int effectNum=couponService.deleteCoupon(2);                 | 数据库中删除一条优惠数据，返回1                              | 得到1                                | DeleteCoupon         |
| TUS5-1     | Hotel         | HotelVO hotelVO=new HotelVO(); hotelService.addHotel(hotelVO); | 将前端传入的hotelvo对象转换成po并存入数据库，返回true        | 得到true                             | InsertHotel          |
| TUS5-2     | Room          | RoomVO roomVO=roomService.getOrderRoom(2);                   | 根据房间id来获取房间信息并转换成vo                           | 得到特定id的房间信息                 | getOrderRoom         |
| TUS6-1     | Order         | OrderVO orderVO=new OrderVO(); orderService.addOrder(orderVO); | 将前端传入的orderVO转换成po并存入数据库，返回true            | 得到true                             | AddOrder             |
| TUS6-2     | Order         | orderService.deleteOrder(14);                                | 根据orderId删除特定的订单信息，得到true                      | 得到true                             | deleteOrder          |
| TUS7-1     | Account       | User user=new User();accountMapper.createNewAccount(user);   | 数据库中插入一条用户数据，返回1                              | 得到1                                | CreateNewAccount     |
| TUS7-2     | Account       | UserForm userForm=new UserForm(); UserVO userVO=accountService.login(userForm); | 根据用户账户邮箱及密码来获取用户信息                         | 得到特定邮箱的用户信息               | login                |
| TUS8-1     | VIPHotel      | vipHotelService.isVIPCouple(1,1);                            | 根据用户id,酒店id来获取用户是否为该就酒店的会员，返回false   | 得到false                            | isVIPCouple          |

#### 2.2.3 系统/功能测试

##### 2.2.3.1 测试结果概述

系统的功能测试确认观察到的软件的行为遵从软件需求规格说明，并从需求用例的角度来验证功能测试的正确性。

##### 2.2.3.2 功能测试详细结果


| 测试用例ID | 种类    | 条件                                                         | 期望结果                                                     | 测试结果               | 测试对象ID        |
| ---------- | ------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---------------------- | ----------------- |
| TUS1-1     | Admin   | UserForm userForm=new UserForm();accountController.login(userForm); | 将前端传入的userform形式的vo转化为po并插入数据库，返回true   | 得到true               | addManager        |
| TUS3-1     | Comment | CommentVO commentVO=new CommentVO(); ResponseVO effect=commentController.addComment(commentVO); | 将前端传入的CommentVO形式的vo转化为po并插入数据库，返回true  | 得到true               | InsertComment     |
| TUS3-6     | Status  | ResponseVO responseVO=commentStatusService.getCommentStatus(); | 得到数据库中所有的Stauts对象，并转换成vo，以列表形式返回，返回true | 得到true               | GetCommentStatus  |
| TUS4-1     | Coupon  | ResponseVO responseVO =couponController.getHotelAllCoupons(2); | 根据hotelId来获取酒店所有的优惠策略，返回一个大小为1的列表   | 得到大小为1的一个列表  | getHotelAllCoupon |
| TUS5-1     | Hotel   | HotelVO hotelVO=new HotelVO(); responseVO=hotelController.createHotel(hotelVO); | 将前端传入的hotelvo对象转换成po并存入数据库，返回true        | 得到true               | createHotel       |
| TUS6-1     | Order   | OrderVO orderVO=new OrderVO();  ResponseVO responseVO=orderController.reserveHotel(orderVO); | 将前端传入的orderVO转换成po并存入数据库，返回true            | 得到true               | reserveHotel      |
| TUS7-1     | Account | UserForm userForm=new UserForm(); ResponseVO responseVO =accountController.login(userForm); | 根据用户账户邮箱及密码来获取用户信息                         | 得到特定邮箱的用户信息 | login             |

#### 2.2.4 缺陷报告

| 缺陷ID | 发现日期   | 测试脚本                                                     | 测试用例                                                     | 期望结果                       | 实际结果        | 状态   | 严重性 | 优先级 | 缺陷类型 | 备注                                     |
| ------ | ---------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------ | --------------- | ------ | ------ | ------ | -------- | ---------------------------------------- |
| ERR1   | 2020-06-25 | HotelVO hotelVO=new HotelVO(); hotelVO.setName("ht"); ResponseVO responseVO=hotelController.createHotel(hotelVO); | HotelVO hotelVO=new HotelVO(); hotelVO.setName("ht"); ResponseVO responseVO=hotelController.createHotel(hotelVO); | 数据库中插入酒店信息，返回true | 报错，返回false | 已解决 | 中     | 中     | Hotel    | 酒店管理员必须提前创建好。才能创建酒店。 |
| ERR2   | 2020-06-23 | HotelVO hotelVO=new HotelVO(); hotelVO.setName("ht"); ResponseVO responseVO=hotelController.createHotel(hotelVO); | HotelVO hotelVO=new HotelVO(); hotelVO.setName("ht"); ResponseVO responseVO=hotelController.createHotel(hotelVO); | 数据库中插入酒店信息，返回true | 报错返回false   | 已解决 | 中     | 中     | Hotel    | 上传的图片大小有限制，不能超过10M        |



### 2.3 决策理由 

有条件通过，系统的真实支付有待实现，登录方式单一，以及在数据量剧增时可能出现卡顿。

### 2.4 总结与建议

· 该产品总体功能较为完善，但投入生产还需要考虑实际环境并完善许多功能。


## 3 其他

· 测试用例代码参照 hotel\src\test\java\com\example\hotel目录下的文件。