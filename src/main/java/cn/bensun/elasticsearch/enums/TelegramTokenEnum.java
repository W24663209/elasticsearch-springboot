package cn.bensun.elasticsearch.enums;

/**
 * @Description
 * @author weizongtang
 * @CreateTime 2023/03/07 12:44:53
 */
public enum TelegramTokenEnum {
    日志错误机器人("6170859915:AAHDnTMOpqRs3W2Qx48llEqgxEBmkcClRhU"),
    订单同步机器人("5839852086:AAE0Ll9AEvZd2-6Xvfw3qFfiCrGfqeL59RQ");

    private String token;

    TelegramTokenEnum(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
