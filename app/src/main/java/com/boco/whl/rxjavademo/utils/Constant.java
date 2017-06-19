package com.boco.whl.rxjavademo.utils;

import android.os.Environment;

/**
 * Created by zhang.w.x on 2017/3/13.
 */
public class Constant {

    public static class VALUE {
        public static final long BUTEL_ID_START = 10000000;
    }

    /**
     * 相关key值
     */
    public static class KEY {
        /*APPKEY*/
        public static final String APP_KEY = "911a477cb22f44b5bfccae98591c20d1";
        /*是否登录*/
        public static final String IS_LOGIN = "isLogin";
        /*用户配置信息*/
        public static final String KEY_USER_CONFIG = "userConfig";
        /*用户名*/
        public static final String APP_USER_NAME = "username";
        /*登录用户号码*/
        public static final String LOGIN_PHONE_NUMBER = "loginPhoneNumber";
        /*密码*/
        public static final String APP_PASSWORD = "password";
        /*事件ID*/
        public static final String EVENT_ID = "eventId";
        /*app数据*/
        public static final String APP_DATA = "appData";
        /*资源路径*/
        public static final String RESOURCE_URL = "resourceUrl";
        /*验证码*/
        public static final String SECURITY_CODE = "securityCode";
        /*电话号码*/
        public static final String PHONE_NUMBER = "phoneNumber";
        /*百度纬度*/
        public static final String LATITUDE = "bd_latitude";
        /*百度经度*/
        public static final String LONGITUDE = "bd_longitude";
        /*位置信息*/
        public static final String ADDRESS = "addressStr";
        /*区域ID*/
        public static final String REGION_ID = "regionId";
        /*是否第一次使用*/
        public static final String ISFIRST = "isfirst";
        /*视频路径*/
        public static final String VIDEO_APTH = "videoPath";
    }

    /**
     * 红云初始化返回结果
     */
    public static class InitResult {
        /*成功*/
        public static final int SUCCESS = 0;
        /*读取配置文件失败*/
        public static final int READ_CONFIG_FILE_FAIL = -1;
        /*已经初始化*/
        public static final int HAS_INITED = -2;
        /*调用太快*/
        public static final int CALL_SO_FAST = 2;
        /*无网络*/
        public static final int NO_NETWORK = 3;
    }

    /**
     * 路径
     */
    public static class Path {
        /*根目录*/
        public static final String ROOT = Environment.getExternalStorageDirectory().getPath() + "/fx110";
        /*图像存储路径*/
        public static final String UER_IMAGE = ROOT + "/image";
        /*视频存储路径*/
        public static final String UER_VIDEO = ROOT + "/video";
        /*日志路径*/
        public static final String CRASH = ROOT + "/crash";
        /*应用缓存*/
        public static final String TEMP = ROOT + "/temp";
        /*缓存*/
        public static final String CACHE = ROOT + "/cache";
    }

    /**
     * 请求命令
     */
    public static class REQUEST_COMMAND {
        /*注册*/
        public static final String REGISTER = "101";
        /*登录*/
        public static final String LOGIN = "102";
        /*报警*/
        public static final String UPLOAD_INFO = "103";
        /*更新SID*/
        public static final String BIND_SID = "107";
        /*增加事件文字信息、小视频*/
        public static final String ADD_EVENT_DESCRIBE = "105";
        /*获取时间类型*/
        public static final String GET_EVENT_TYPE = "109";
        /*检查更新*/
        public static final String CHECK_UPDATE = "405";
        /*发送验证码*/
        public static final String SEND_SECURITY_CODE = "110";
        /*重置密码*/
        public static final String RESET_PSWD = "111";
        /*获取我的报警*/
        public static final String GET_MY_REPORT = "112";
        /*获取区域列表*/
        public static final String GET_REGION_LIST = "407";
        /*设置修改密码*/
        public static final String SET_PASSWORD = "114";
    }

    public static class RESPONSE_CODE {
        /*成功*/
        public static final String SUCCESS = "000";
        /*session失效*/
        public static final String LOGIN_TIME_OUT = "900";
    }

    public static class ID {
        /*下载进度notification ID*/
        public static final int DOWNLOAD_NOTIFICATION_ID = 2;
    }
}
