package cn.bensun.elasticsearch.enums;

/**
 * @Description RocketMQ主题常量
 * @CreatedBy weizongtang
 * @CreateTime 2022/10/22 22:07:51
 */
public class RocketMQTopicConstants {
    private RocketMQTopicConstants() {
    }

    public static final String 同步es主题 = "sync_es_topic";
    public static final String 同步es订阅组 = "group_sync_es_topic";
    public static final String 日志主题 = "log_topic";
    public static final String 日志主题订阅组 = "group_log_topic";

}
