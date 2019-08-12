package com.spping.ath.common.constants;

/**
 * @Author: 石保平
 * @Description: ${description}
 * @Date: 2019/4/6 22:33
 * @Version: 1.0
 */
public class RspCodeEnum {
    public enum SUCCESS{
        SUCCESS("0000","处理成功");
        private String code;
        private String msg;
        SUCCESS(String code, String msg){
            this.code = code;
            this.msg = msg;
        }
        public String getCode() {
            return code;
        }
        public String getMsg() {
            return msgReplace(msg);
        }
    }
    public enum ERROR{

        FAIL("9999","失败,原因:%s"),

        //系统异常类 以9开头
        SYS_BUSY("9001","系统繁忙,请稍后再试!"),
        SYS_ERR("9002","系统异常!"),

        //校验类
        SOURCE_NOT_SUPPORT("1001","reqSource:%s 不支持"),
        CAN_NOT_EMPTY("1002","%s 不能为空"),
        PRODUCTCODE_NOT_SUPPORT("1003","projectCode:%s 不支持"),
        FRIEND_APPLY_TYPE_NOT_SUPPORT("1004","好友请求类型不支持"),

        //业务异常
        ORDER_NOT_EXIST("2001","订单不存在"),



        LOGIN_FAIL("U999","登录失败"),
        LOGIN_UN_REGIST("U001","此用户名未注册"),
        LOGIN_PSW_WRONG("U002","密码输入有误"),
        LOGIN_OLD_PSW_WRONG("U003","原密码输入有误"),
        IMGCODE_WRONG("U003","验证码输入有误"),
        CONTENT_CATAGORY_NOT_SUPPORT("U004","内容分类:%s 不存在"),
        ADMIN_NOT_LOGIN("U005","管理员未登录!"),
        LOGIN_NAME_OR_PWD_ERR("U006","登录名或密码错误！"),
        LOGIN_OUT_ERR("U007","退出异常！"),
        NICKNAME_REGISTED("U008","此昵称已被注册!"),
        MOBILE_REGISTED("U009","此手机号已被注册!"),
        PWD_MODIFY_ERR("U010","修改密码不成功，入参有问题!"),
        PLEASE_LOGIN_FIRST("U011","请先登录!"),
        FRIEND_INFO_NOT_EXIST("U012","好友信息不存在!"),

        LOGIN_FAIL_PHONE_ERROE("U300","登陆失败，原因：%s"),
        LOGIN_FAIL_SMS_VERIFY_ERROE("U301","手机验证码校验失败,原因：%s"),
        LOGIN_FAIL_MEMBER_STOP_USE("U302","用户已被禁用"),
        QUERY_REQ_FAIL_MEMBER("U303","查询请求失败，原因:%s"),
        MEMBER_INFO_NOT_EXIST("U304","会员信息不存在"),


        CONTENT_UPLOAD_FAIL("C999","上传文件失败，原因:%s"),
        CONFIG_READ_ERROR("C001","配置数据读取异常，原因:%s"),
        CONFIG_VAL_NOT_EXIST("C002","%s对应的val值不存在"),
        CONFIG_NOT_EXIST("C003","%s对应的配置不存在"),
        MAP_STR_2_MAP("C004","%s 格式不符"),
        CONTENT_ID_NOT_EXIST("C005","内容不存在"),
        ANSWER_UPDATE_NOT_MATCH("C006","答案更新条件不匹配"),
        MODIFY_CONTENT_ID_CANNOT_NULL("C007","更新内容,id不能为空"),
        THIS_ANSWER_NOT_EXIST("C007","此答案不存在!"),
        BEST_ANSWER_FORBID_MOD("C008","最佳答案不允许修改!"),
        ANSWER_USER_NOT_QA_USER("C009","回答者不是QA提出者，不允许此操作!"),
        BEST_ANSWER_IS_EXIST("C010","最佳答案已经存在!"),
        TAGLIST_ERR("C011","标签集合异常!"),
        TAG_REL_DEL_ERR("C012","标签关联删除异常!"),
        VISIT_URL_ERR("C013","页面%s访问异常!"),
        EXTRACT_CATEGORY_ERR("C014","提取分类列表异常!"),
        SELECT_CSS_ERR("C015","css选择:%s 提取节点异常!"),
        ADD_BOOK_CHAPTERLIST_IS_NULL("C016","章节列表不能为空!"),
        BOOKSELF_BOOK_IS_EXIST("C017","此书已存在书架"),
        BOOK_NOT_EXIST("C018","此书不存在"),
        BOOK_ID_IS_NULL("C018","bookIdb不能为空"),



        SMS_SEND_QUICKLY("S001","短信发送频繁，%s秒内只能发送一次"),
        SMS_SEND_ERROR("S002","短信发送异常:%s"),
        SMS_INFO_UN_AVAIL("S003","短信已失效，短信有效时间为:%s秒"),
        HEADER_SID_NOT_EXIST("S004","请求头中SID不存在，后台拒绝服务"),
        COOKIE_SID_NOT_EXIST("S005","cookie中SID不存在，后台拒绝服务"),
        REQUST_SID_NOT_EXIST("S006","请求中SID不存在，后台拒绝服务"),
        PLEASE_SEND_SMS_CODE("S007","请先发送短信"),
        SMS_CODE_NOT_CORRECT("S007","验证码不正确"),
        MAKEDIR_ERR("S008","创建文件夹异常"),
        SMS_MOBILE_NOT_CORRECT("S008","短信手机格式有问题"),
        APPLICATION_NOT_EXIST("S009","config中application不存在"),
        SMS_PARAM_IS_NULL("S010","请求参数%S值不能为空");
        private String code;
        private String msg;
        ERROR(String code, String msg){
            this.code = code;
            this.msg = msg;
        }
        public String getRspMsg(Object... args) {
            return String.format(this.msg, args);
        }
        public String getCode() {
            return code;
        }
        public String getMsg() {
            return msgReplace(msg);
        }
    }
    private String code;
    private String message;

    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public String getRspCodeAndMsg(){

        return code.concat("-").concat(getMsg());

    }
    public String getRspCodeAndMsg(Object... args){

        return code.concat("-").concat(getMsg(args));

    }
    public String getMsg(Object... args) {
        return String.format(this.message, args);
    }
    public RspCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public RspCodeEnum(SUCCESS succ) {
        this.code = succ.getCode();
        this.message = succ.getMsg();
    }
    public RspCodeEnum(ERROR error) {
        this.code = error.getCode();
        this.message = error.getMsg();
    }
    private static String msgReplace(String shortRspMsg){
        if(shortRspMsg.contains(":%s")){
            shortRspMsg = shortRspMsg.replaceAll(":%s","");
        }
        if(shortRspMsg.contains("%s")){
            shortRspMsg = shortRspMsg.replaceAll("%s","");
        }
        return shortRspMsg;
    }
}
