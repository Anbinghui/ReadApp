package com.jxkj.readapp.common;



/**
 * 请求地址
 *
 * @author Administrator
 */
public class UrlConfig {
    public static final String HEADHTTP = "http://";
    public static String NET = "192.168.1.144";//测试
    public static final String NET0 = "192.168.1.144";//测试,3.4域名
    public static final String NET1 = "pmall.jixiangkeji.com";// 预发
    public static final String NET2 = "mall.jixiangkeji.com";// 正式
    public static final String NET3 = "dmall.jixiangkeji.com";// shopNc服务端提供的
    public static final String PAY_NET0 = "tmall.jixiangkeji.com";//测试支付回调地址
    public static final String PAY_NET2 = "pmall.jixiangkeji.com";//预发支付回调地址
    public static final String PAY_NET1 = "mall.jixiangkeji.com";//正式支付回调地址
    public static String BASE_URL = HEADHTTP + NET0+"/";
    public static String BASE_PAY_URL = HEADHTTP + PAY_NET0+"/";//
    public static String BASE_URL_JAVA = HEADHTTP + NET0+":8080/";
    public static final String SERVER_IP_KEY = "SERVER_IP_KEY";
    public static final int DEFAULT_SERVER_FLAG = 0;

//    public static String resetServerUrl(int position) {
////        String IP = BuildConfig.IS_RELEASE ? NET2 : NET0;
////        String PAY = BuildConfig.IS_RELEASE ? NET2 : NET0;
//        switch (position) {
//            case 0:
//                IP = UrlConfig.NET0;
//                PAY = UrlConfig.PAY_NET0;
//                break;
//            case 1:
//                IP = UrlConfig.NET1;
//                PAY = UrlConfig.PAY_NET2;
//                break;
//            case 2:
//                IP = UrlConfig.NET2;
//                PAY = UrlConfig.PAY_NET1;
//                break;
//            case 3:
//                IP = UrlConfig.NET3;
//                PAY = UrlConfig.PAY_NET0;
//                break;
//            default:
//                break;
//        }
//
//        NET = IP;
//        BASE_URL = HEADHTTP + IP+"/";
//        BASE_URL_JAVA = HEADHTTP+IP+":8080/";
//        BASE_PAY_URL = HEADHTTP+PAY+"/";
//        return BASE_URL;
//    }

    //        public static final String notifyUrl = TestPay + "/order/alipay.php";// 支付宝测试
//    public static final String NOTIFY_URL = TestPay + "/order/wxpay_notify.php";// 微信支付测试

    // 微信支付预发正式
    public static String NOTIFY_URL() {
        return "http://mall.jixiangkeji.com/mobile/api/payment/alipay/notify_url.php";
    }

    //支付宝测试
    public static String TEST_URL() {
        return BASE_PAY_URL+"mobile/api/payment/alipay/notify_app_url.php";
    }

    //微信支付异步地址
    public static String WEIXIN_URL() {
        return  BASE_PAY_URL+"mobile/api/payment/wxpay/notify_app_url.php";
    }

    /**
     * 全部webview 添加版本号 2016年4月20日 14:17:06
     */
//     public static String ADDVERSION() {
//        return "&versions=" + BuildConfig.VERSION_NAME;
//    }

    // #添加坐标
    //废弃
    public static String addlbsUrl() {
        return BASE_URL + "/index.php?g=api&m=local&a=setselcoord";
    }


    //日笺接口
    public static String getHomeNotePaper() {
        return BASE_URL + "/index.php?g=api2&m=index&a=daypup";
    }


    // 支付宝
    // 商户PID
    public static final String ALI_PARENT = "2088021334955772";
    // 商户收款账号
    public static final String ALI_SELLER = "yunying@jixiangkeji.com";
    // 商户私钥，pkcs8格式
    public static final String ALI_RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAOhldjRMiBVWS2RYcyOUrkN+2llVTjLmv351t4O9uThVUDMUa4TZ9Xc/BwGWu8elZlIWpLJsuwPjHJ7Lq78ct3SABkGepuYj+i0uZM2CuIKl8TooxG+3ITnDFQmLV8mLUt3X+GEvV6tiZNiR3Jz980fgLibmQgr5m1drqDzCUaGDAgMBAAECgYEAjRbsSJweEjYt12ILpRJjKf8duRadPzHwGGqHW6gXhyfkZN6E2EcrS5YsPr6wimjUUgoFO33fnGCJw66LOp/Ij/3u3JntYcvIisLuljWHRLK8Qz20obw7H2QN5YLY/XcJ6idnXN4Rg4d1GKo+1RbgfCHwS5SxAFJZFfRYZp4mM2ECQQD0biyKkzVRYKGd5KimHn8Jk/MllTqJDt/A6g0rhlbnWhUN0qsoY/UcPkAgYZr6NmTZwLldu8T0I3IsH0KFGa9/AkEA82V34Bo0LHtkN4VpoO9kpl6g8tEeeZOnWgYR3AGFerho8FSTRhgECw5TRKAL/USTVGFZA9l/n6VlQHjfiLlP/QJAbRD4KQFUqjIfC3ArXKyA7QoqVZvH2b5cUx1csQ4pmuPUPy3Al646gda5EHndCEbbK1rWm5+cW3+xg0STjwaOCwJAbjpVv7geSMbY7AwPekLwYigY29643nZwI+T70ZcezCUX4T9sMyJNMnSaYUnaJCkaT+yGU6f8lGBjKn+TPGgwQQJABPcorpPaub1nZmtHPN8/Y8bqaoaCONxc+gl19e7d93RVqY/gAGiSmq6ny5UQs2nmI2UFrDXHIWglT8vqTbZFzQ==";
    // 支付宝公钥
    public static final String ALI_RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
    //批量补货
    public static final String PILIANG_BUHUO = BASE_URL+"index.php?m=order_new&a=order_list&status=2&bra_id=27&order=order&is_seller=0";
    //零散补货
    public static final String LISAN_BUHUO = BASE_URL+ "index.php?m=order_new&a=order_list&status=1&bra_id=27&order=scatter_order&is_seller=0";
    //预付款
    public static final String YUFUKUAN = BASE_URL+ "index.php?m=brand&a=index&bra_id=27";
    //进入他人店铺
    public static final String OTHER_SHOP = "wap/tmpl/store.html?";

    // 品牌管理 地址
    public static String orderJoinSaleManage() {
        return BASE_URL + "/index.php?m=user&a=mybrand";
    }

    //我的店铺地址
    public static final String MINESHOP_URL = "index.php?m=shop&a=shopshows&bra_id=33";
    //货架地址
    public static final String HUOJIA_URL = "/index.php?m=shop&a=shelves";
    //查看物流地址
    public static final String WULIU_URL = "http://m.kuaidi100.com/index_all.html?";
    //商品详情
    public static final String GOODDETAIL_URL = "wap/tmpl/product_detail.html?";
    //订单详情
    public static final String ORDERDETAIL_URL = "wap/tmpl/member/order_detail.html?";

    //版权信息
    public static final String BANQUANINFO_URL = "wap/banquanxinxi.html";
    //帮助中心
    public static final String HELPCENTER_URL = "wap/helpcenter.html";
    //吉象币规则
    public static final String JXCOIN_URL = "wap/jixiangbi.html";
    //使用协议
    public static final String USER_URL = "wap/usexieyi.html";

    //注册协议
    public static final String ZHUCEXIEYI_URL  = "wap/zhucexieyi.html?";


    /*之前app1的域名,接口地址*/

    //获取selid
    public static final String GETSELID_URl = "/mobile/index.php?g=api&m=public&a=personalinformation";
    //我的钱包
    public static final String MYWALLET_URL = "/mobile/index.php?g=api&m=seller&a=wallet1";
    //店铺信息
    public static final String SHOPINFO_URL = "/mobile/index.php?g=api&m=shop&a=get_shop";
    //绑定支付宝
    public static final String BINDPAY_URL = "/mobile/index.php?g=api&m=seller&a=bind";
    //店铺提现密码
    public static final String TIXIAN_URL = "/mobile/index.php?g=api&m=seller&a=checkpwd";
    //修改支付宝账号
    public static final String CHANGEPAY_URL = "/mobile/index.php?g=api&m=seller&a=update";
    //获取支护宝信息
    public static final String GETPAYINFO_URL = "/mobile/index.php?g=api&m=seller&a=getcardinfo";
    //提现手续费
    public static final String TIXIANFREE_URL = "/mobile/index.php?g=api&m=seller&a=get_money_fee";
    //校验支付密码
    public static final String CHECKPAYPWD_URL = "/mobile/index.php?g=api&m=seller&a=setpwd";
    //店铺提现
    public static final String GOTOTIXIAN_URL = "/mobile/index.php?g=api&m=seller&a=withdraw";
    //未读u消息
    public static final String MESSAGENUM_URL = "/mobile/index.php?g=api2&m=pushnews&a=get_info";
    //系统消息
    public static final String SYSMSG_URL = "/mobile/index.php?g=api2&m=pushnews&a=get_list";
    //消息删除
    public static final String DELETEMSG_URL = "/mobile/index.php?g=api2&m=pushnews&a=del";
    //清空消息
    public static final String DELETEALLMSG_URL = "/mobile/index.php?g=api2&m=pushnews&a=del_all";

    //极客店铺分销商品领取页面
    public static final String GEEK_SHOP_RD_GOODS_URL = BASE_URL + "/wap/tmpl/distri_list.html?a=a";
    //    极客订单管理
    public static final String GEEK_ORDER_MANAGE_URL = BASE_URL + "wap/tmpl/appstore/store_order_list.html?a=a";

    //发现
    public static final String BASE_FOUND_URL = HEADHTTP + "tmall.jixiangkeji.com/mobile/index.php?";
    public static final String HOU_URL = "&op=index&con=app";
    //店铺认领H5
    public static final String SHOP_CLAIM_URL="/mobile/index.php?act=store_shop_joinin_apply&op=store_joinin";

}
