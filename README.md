
### 脱敏工具类

#### 背景
> 所有敏感信息都需要脱敏传输，这就需要对打日志时进行脱敏工作。
由于脚手架生成的工程中，在框架层面就对所有请求的入参及出参做了toString，并且包含了所有fields，就有可能导致把敏感信息直接打出来。

#### 目标

>开发人员能相对比较容易的对需要脱敏的字段进行脱敏，并且脱敏的格式可以自定义。如姓名的话只显示首字母、手机号只显示前5位。



#### gitee地址
> https://gitee.com/fyoutech/sensitive-data.git

> git clone git@gitlab.yeshj.com:foe/architecture.git

> cd sensitive-data

#### 实现方案
一. 导入依赖包

```
<dependency>
	<groupId>com.gitee.fyoutech</groupId>
	<artifactId>sensitive-data</artifactId>
	<version>1.0-SNAPSHOT</version>
</dependency>
```

二. override BaseRequest & BaseResponse的toString方法

```
@Override
public String toString() {
   return new MaskToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
}
```

三. 在需要脱敏的字段上增加注解
注意：

1. 目前仅支持对String对象脱敏；

2. 内嵌对象中的String也可被脱敏;

3. 被脱敏的字段的所属对象必须继承BaseRequest或BaseResponse，或override toString

```
/**
 * 绑卡申请
 * Created by fyoutech
 * Date : 2016-07-07
 */
public class BindBankCardReq extends TransAcctRequest {
    private static final long serialVersionUID = 1504331879625483383L;
    /*********四要素 end**********/
    /**
     * 真实姓名
     */
    @NotNull
    @Size(max=30)
    @Mask(prefixNoMaskLen = 1)
    private String name;
    /**
     * 身份证号
     */
    @NotNull
    @Size(max=18)
    @Mask
    private String idNo;
    /**
     * 银行卡号
     */
    @NotNull
    @Size(max=20)
    @Mask(suffixNoMaskLen = 4)
    private String bankCardNo;
    /**
     * 银行预留手机号
     */
    @Size(max=18)
    @Mask(prefixNoMaskLen = 3,suffixNoMaskLen = 3)
    private String mobile;
    /*********四要素 end**********/
```


脱敏后效果（打印出的对象）：
````
public static void main(String[] args) {
    BindBankCardReq req =  new BindBankCardReq();
    req.setIdNo("310110199999999999");
    req.setBankCardNo("6220622062206220");
    req.setMobile("18888888888");
    req.setCvv2("888");
    req.setMerchantCode("hj");
    req.setBankId("ICBC");
    req.setBankName("工商银行");
    req.setCardType("C");
    req.setEndDate("201909");
    req.setName("郭小明");
    req.setTransAcctType("z1");
    req.setUserId("123456");
    System.out.println(req);
}
````
> toString

````
BindBankCardReq[name=郭**,idNo=******************,bankCardNo=************6220,mobile=188*****888,bankId=ICBC,bankName=工商银行,cardType=C,cvv2=***,endDate=******,merchantCode=hj,userId=123456,transAcctType=z1]
````



四. Mask注解详解

````
/**
 * 用来打码
 * Created by fyoutech
 * Date : 2016-08-04
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Mask {
    /**
     * 前置不需要打码的长度
     * @return
     */
    int prefixNoMaskLen() default 0;

    /**
     * 后置不需要打码的长度
     * @return
     */
    int suffixNoMaskLen() default 0;

    /**
     * 用什么打码
     * @return
     */
    String maskStr() default "*";
}
````