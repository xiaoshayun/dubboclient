#### dubboclient的调用处理
```
一开始使用,需要在pom.xml中放入你的api内容,不然传参会找不到对应的类出错
1,调用参数
    包 方法 参数类型列表(用~进行分割) 参数列表(用~进行分割)
2,ip设置放在最后-->   --server.port=
3,参数类型列表含有对象类型,参数列表对应的数据是json类型
4,参数类型列表 string和int两种可以简写,其他都需要完整包名
5,实例-->
com.zte.ums.ztev.trade.api.ITradeRecordService
queryTradeRecordByTime
string~string~string~int~int
ztev.driver=440300201706161420580001~2017~07~0~10
int类型的数据,代码中含有int.class的类型


com.zte.ums.ztev.trade.api.IWalletService updateDriverWallet com.zte.ums.ztev.trade.entity.DriverWalletBO {\"driver_OID\":\"ztev.driver=440300201707140846580001\",\"balance\":\"222\"}
对象类型的数据需要把json字符进行反斜杠的处理
```