package cn.bensun.elasticsearch.util;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weizongtang
 * @Description tg机器人工具类
 * @CreateTime 2023/03/06 02:19:05
 */
@Slf4j
public class TelegramLogUtils {
    /**
     * @Description
     * @author weizongtang
     * @CreateTime 2023/03/06 17:02:35
     */
    public static boolean sendText(String token, String chatId, String text) {
//        https://api.telegram.org/bot5839852086:AAE0Ll9AEvZd2-6Xvfw3qFfiCrGfqeL59RQ/sendMessage
//        https://api.telegram.org/bot6170859915:AAHDnTMOpqRs3W2Qx48llEqgxEBmkcClRhU
        Map<String, Object> map = new HashMap<>();
        map.put("chat_id", chatId);
        map.put("text", text);
        log.info("发送消息:{}", JSON.toJSONString(map));
        String post = HttpUtil.post(String.format("https://api.telegram.org/bot%s/sendMessage", token), map);
        log.info("发送消息响应:{}", post);
        return true;
    }

    public static void sendTextList(String token, String chatId, List<String> list) {
        log.info("准备发送消息:{}条", list.size());
        BigDecimal size = BigDecimal.valueOf(list.size() * 1.0 / 10).setScale(0, RoundingMode.UP);
        for (int i = 0; i < size.intValue(); i++) {
            int start = i * 10;
            int end = (i + 1) * 10;
            end = Math.min(end, list.size());
            sendText(token, chatId, String.join("\n", list.subList(start, end)));
        }
    }


    public static void main(String[] args) {
//        String response = HttpUtil.post("https://api.telegram.org/bot5839852086:AAE0Ll9AEvZd2-6Xvfw3qFfiCrGfqeL59RQ/setWebhook",new HashMap<String,Object>(){{
//            put("url","https://bot.funnypay.in/telegram/funnyPayBot/webhookInfo");
//        }});

//        System.out.println(response);
//        sendText(TelegramTokenEnum.订单同步机器人.getToken(), "-1001793453714", "test");
        BigDecimal size = BigDecimal.valueOf(2 * 1.0 / 10).setScale(0, RoundingMode.UP);
        for (int i = 0; i < size.intValue(); i++) {
            int start = i * 10;
            int end = (i + 1) * 10;
            end = Math.min(end, 2);
            System.out.println(start+"===="+end);
        }

    }
}
