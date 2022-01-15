package cn.bensun.elasticsearch.model.vo.requestLog;

import lombok.Builder;
import lombok.Data;

/**
 * @Description: OceanPayInVO
 * @Author: lucky
 * @Version: 1.0
 * @Date: 2021/7/5 6:24 下午
 * @Modify 修改了本类的需要在这里加上修改者的名字
 */
@Builder
@Data
public class OceanPayInVO {

    private String name;

    private String mobile;

    private String email;

    private String amount;

    private String paycode;

    private String code;

    private String merordercode;

    private String notifyurl;

    private String callbackurl;

    private String starttime;

    private String signs;

}
