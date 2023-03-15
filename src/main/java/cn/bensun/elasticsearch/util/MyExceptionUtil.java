//package cn.bensun.elasticsearch.util;
//
//import cn.bensun.elasticsearch.enums.ExceptionEnum;
//import cn.hutool.core.util.ObjectUtil;
//
///**
// * @Description 抛出异常工具类
// * @CreatedBy weizongtang
// * @CreateTime 2022/12/31 20:33:22
// */
//public class MyExceptionUtil {
//    /**
//     * @Description 直接抛出异常
//     * @CreatedBy weizongtang
//     * @CreateTime 2022/12/31 20:39:32
//     */
//    public static void throwEx(ExceptionEnum exceptionEnum, Object... obj) {
//        if (obj.length > 0) {
//            throw new RuntimeException(String.format(exceptionEnum.getDesc(), obj));
//        } else {
//            throw new RuntimeException(exceptionEnum.getDesc());
//        }
//    }
//
//    /**
//     * @Description 为空时抛出异常
//     * @CreatedBy weizongtang
//     * @CreateTime 2022/12/31 20:39:43
//     */
//    public static void throwExIsNull(Object obj, ExceptionEnum exceptionEnum) {
//        if (ObjectUtil.isNull(obj)) {
//            throwEx(exceptionEnum);
//        }
//    }
//}
