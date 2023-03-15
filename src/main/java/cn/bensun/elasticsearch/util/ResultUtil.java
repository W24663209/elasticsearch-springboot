//package cn.bensun.elasticsearch.util;
//
//import cn.bensun.elasticsearch.enums.ExceptionEnum;
//import cn.bensun.elasticsearch.model.dto.Result;
//
///**
// * @Description
// * @CreatedBy weizongtang
// * @CreateTime 2022/01/01 15:01:27
// */
//public class ResultUtil<T> {
//    private static String successMsg = "成功";
//
//    /**
//     * @Description 成功
//     * @CreatedBy weizongtang
//     * @CreateTime 2022/01/01 15:06:00
//     */
//    public static <T> Result<T> success(T t) {
//        Result<T> result = new Result<T>();
//        result.setData(t);
//        result.setSuccess(true);
//        result.setCode(200);
//        result.setMsg(successMsg);
//        return result;
//    }
//
//    /**
//     * @Description 成功
//     * @CreatedBy weizongtang
//     * @CreateTime 2022/01/01 15:09:39
//     */
//    public static <T> Result<T> success() {
//        Result<T> result = new Result<T>();
//        result.setSuccess(true);
//        result.setCode(200);
//        result.setMsg(successMsg);
//        return result;
//    }
//
//    /**
//     * @Description 失败
//     * @CreatedBy weizongtang
//     * @CreateTime 2022/01/01 15:06:06
//     */
//    public static <T> Result<T> error(T t, String msg) {
//        Result<T> result = new Result<T>();
//        result.setData(t);
//        result.setSuccess(false);
//        result.setMsg(msg);
//        result.setCode(500);
//        return result;
//    }
//
//    /**
//     * @Description 失败
//     * @CreatedBy weizongtang
//     * @CreateTime 2022/01/01 15:06:19
//     */
//    public static <T> Result<T> error(String msg) {
//        Result<T> result = new Result<T>();
//        result.setSuccess(false);
//        result.setMsg(msg);
//        result.setCode(500);
//        return result;
//    }
//
//    /**
//     * @Description 失败
//     * @CreatedBy weizongtang
//     * @CreateTime 2022/01/01 15:09:06
//     */
//    public static <T> Result<T> error(ExceptionEnum exceptionEnum) {
//        Result<T> result = new Result<T>();
//        result.setSuccess(false);
//        result.setMsg(exceptionEnum.getDesc());
//        result.setCode(exceptionEnum.getCode());
//        return result;
//    }
//
//
//}
