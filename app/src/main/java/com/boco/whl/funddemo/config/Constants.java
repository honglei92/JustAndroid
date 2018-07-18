package com.boco.whl.funddemo.config;

import android.os.Environment;

import com.boco.whl.funddemo.utils.UIUtils;

import java.io.File;

import okhttp3.MediaType;

import static android.os.Environment.DIRECTORY_DCIM;

/**
 * 项目常量值
 * Created by xxx on 2016/10/9.
 */

public interface Constants {

    /**
     * 上传视频所需参数
     */
    String UPLOAD_APP_KEY = "xnXcNtotNDIyMTA5NDI1MTIwNzEwNjU2";
    String UPLOAD_APP_SECRET = "YzkwNDc2ODFjMTE5MmQ5ODcyNWY2NGNiYWQyMzdjYWQ=";

    String BASE_IMAGE = "http://api.maomaoda.tv/uploads/";
    String BASE_SHARE = "http://app.maomaoda.tv/index.php";

    /**
     * 上传视频参数类型
     */
    String MULTIPART_FORM_DATA = "multipart/form-data";
    MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
    MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    /**
     * 阿里支付成功状态码
     */
    String PAY_ALI_SUCCESS = "9000";

    /**
     * 微信支付成功状态码
     */
    String WX_APP_ID = "wxd930ea5d5a258f4f";

    /**
     * 网络请求失败
     */
    int STATUS_DISCONNECT = -3;// 网络连接断开
    int STATUS_TIMEOUT = -2;// 网络超时
    int STATUS_ERROR = -1;// 网络错误
    int STATUS_SUCCESS = 200;
    int STATUS_NO_DATA = 201;
    int STATUS_NO_MORE = 202;
    int STATUS_FALSE = 203;
    int STATUS_TOKEN = 400;
    boolean DEBUG = true;

    /**
     * 用户状态
     */
    int USER_ON_LINE = 1;
    int USER_OFF_LINE = 2;
    int USER_BUSY = 3;
    int GENDER_MAN = 1;
    int GENDER_WOMAN = 2;

    /**
     * 通话状态
     */
    int AV_STATUS_0 = 0; // 未知或未挂断
    int AV_STATUS_1 = 1; // 网红不在线
    int AV_STATUS_2 = 2; // 网红通话中
    int AV_STATUS_3 = 3; // 未接通 用户挂断
    int AV_STATUS_4 = 4; // 未接通 网红拒接
    int AV_STATUS_5 = 5; // 未接通 超时挂断
    int AV_STATUS_6 = 0; // 通话中 用户挂断
    int AV_STATUS_7 = 0; // 通话中 网红挂断
    int AV_STATUS_8 = 0; // 通话中 余额不足挂断

    String COM_IS_FIRST = "com_is_first";
    String TOKEN = "20YldGdmJXRnZaR0U917";
    String MOMENT_POSITION = "moment_position";
    String MOMENT_SELECT = "moment_select";
    String MOMENT_PHOTO = "moment_photo";
    String SEARCH_HOT_ID = "search_hot_id";
    String SEARCH_CONTENT = "search_content";
    int SEARCH_DEFAULT_ID = -1;
    String GALLERY_PIC_BEAN = "gallery_pic_bean";
    String GALLERY_JUMP_POS = "gallery_jump_pos";
    String USER_ID = "user_id";
    String USER_LOGOUT = "user_logout";// 退出登录时发生错误
    String USER_REGISTER_DEVICE = "user_register_device";// 注册设备时发生错误
    String USER_BIND_DEVICE = "user_bind_device";// 绑定设备时发生错误
    int DEFAULT_USER_ID = 1;
    /**
     * 用户信息
     */
    String COM_USER_INFO = "user_info";
    String COM_LOGIN_INFO = "login_info";
    String COM_RE_CHOOSE = "re_choose";
    String COM_DEVICE_NUM = "device_num";
    String COM_TEMP_TICKET = "temp_ticket";
    String COM_SEARCH_INFO = "search_info";

    /**
     * key     通用位置索引
     */
    String COM_POSITION = "position";
    /**
     * key     通用图片集合数据
     */
    String COM_PHOTOS = "photos";
    /**
     * key     通用联系人集合数据
     */
    String COM_CONTACTS = "contacts";
    /**
     * key     通用图片数据
     */
    String COM_PHOTO = "photo";
    /**
     * key     通用视频数据
     */
    String COM_VIDEO = "video";
    /**
     * key     通用详细地址
     */
    String COM_ADDRESS = "address";

    /**
     * 点击按钮默认间隔时长
     */
    long DEFAULT_CLICK_INTERVAL = 1000;
    int TIME_20 = 20;
    int TIME_10 = 10;
    /**
     * 默认列表索引
     */
    int DEFAULT_POSITION = 0;
    /**
     * 默认空值
     */
    String DEFAULT_NULL = "";
    /**
     * 默认软键盘高度
     */
    int DEFAULT_SOFTKEY_HEIGHT = (int) (267 * UIUtils.getResources().getDisplayMetrics().density);
    /**
     * 默认经度
     */
    double DEFAULT_LONGITUDE = 116.521321;
    /**
     * 默认纬度
     */
    double DEFAULT_LATITUDE = 39.911809;
    /**
     * 默认请求数据条目
     */
    int DEFAULT_PAGE_SIZE = 10;
    /**
     * 图片最大张数
     */
    int MAX_PHOTO_SIZE = 40;
    int MAX_SIGN_SIZE = 3;

    /**
     * 回掉值
     */
    int SELECT_AREA = 1;
    int EXIT_LOGIN = 1;
    int SELECT_CITY = 2;
    int GET_SELECT_AREA = 10;
    int GET_SELECT_COUNTRY = 1;
    int GET_SELECT_RESULT_COUNTRY = 2;
    int GET_SELECT_CITY = 20;
    int USER_AGREEMENT = 1;
    int USER_FORGET_AGREEMENT = 2;
    int NAME = 1;
    int REQUEST_NAME = 2;
    int REQUEST_REQUEST_EDIT = 1;
    int REQUEST_RESULT_EDIT = 2;


//======================================================================================

    /**
     * 拍照
     */
    String PIC_IMAGE = "pic_image";
    String PIC_OUTPUT = "pic_output";

    /**
     * 默认保存数据路径
     */
    String DEFAULT_SAVE_PATH = Environment.getExternalStorageDirectory()
            + File.separator + "MMD";
    String DEFAULT_IMAGE_PATH = DEFAULT_SAVE_PATH + "/image";
    String DEFAULT_THUMB_PATH = DEFAULT_SAVE_PATH + "/thumb";
    String DEFAULT_VIDEO_PATH = DEFAULT_SAVE_PATH + "/video";
    String DEFAULT_TEMP_PATH = DEFAULT_SAVE_PATH + "/temp";
    String DEFAULT_OBJECT_PATH = DEFAULT_SAVE_PATH + "/object";
    String DEFAULT_DOWNLOAD_PATH = DEFAULT_SAVE_PATH + "/download/";
    /**
     * 默认相册路径
     */
    String DEFAULT_ALBUM_PATH = Environment.getExternalStoragePublicDirectory(DIRECTORY_DCIM).getAbsolutePath() + "/MMD";

//======================================================================================

    interface EventType {
        String TAG_REFRESH_DATA = "refresh_data";
        String TAG_REFRESH_CHAT_ERROR = "refresh_chat_error";
        String TAG_REFRESH_LOGOUT_ERROR = "refresh_logout_error";
        String TAG_REFRESH_REGISTER_DEVICE_ERROR = "refresh_register_device_error";
        String TAG_REFRESH_BIND_DEVICE_ERROR = "refresh_bind_device_error";
        String TAG_REFRESH_MAIN = "refresh_main";
        String TAG_REFRESH_MAIN_ACTION = "refresh_main_action";
        String TAG_REFRESH_TIME = "refresh_time";
        String TAG_RELOGIN_ACTIVITY = "relogin_activity";
        String TAG_MOMENT_SEND_ACTIVITY = "moment_send_activity";
        String TAG_SELECT_PHOTO_ACTIVITY = "select_photo_activity";
        String TAG_RELOGIN_HELP = "relogin_help";
        String TAG_RELOGIN_COLLECT = "relogin_collect";
    }

    /**
     * 是否觉得热门问题有用
     */
    int HOT_QUESTION_HAVE_HELP = 0;
    int HOT_QUESTION_NO_HELP = 1;
    /**
     * 跳转主页(个人or主播)
     */
    int onlyPeople = 1;//个人
    int anchor = 2;//主播
}
