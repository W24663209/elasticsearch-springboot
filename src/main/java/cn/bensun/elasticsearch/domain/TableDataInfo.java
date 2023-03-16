package cn.bensun.elasticsearch.domain;

import cn.hutool.core.collection.CollectionUtil;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

/**
 * 表格分页数据对象
 *
 * @author ruoyi
 */
public class TableDataInfo implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 总记录数
     */
    private long total;

    /**
     * 列表数据
     */
    private List<?> rows;

    /**
     * 消息状态码
     */
    private int code;

    /**
     * 消息内容
     */
    private String msg;

    private boolean isAdmin;

    /**
     * 表格数据对象
     */
    public TableDataInfo() {
    }

    /**
     * 分页
     *
     * @param list  列表数据
     * @param total 总记录数
     */
    public TableDataInfo(List<?> list, long total) {
        this.rows = list;
        this.total = total;
    }

    public static TableDataInfo getInstance(List<?> list, long total) {
        if (CollectionUtil.isEmpty(list)){
            return new TableDataInfo(list, 0);
        }
        Class clazz = list.get(0).getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(JsonFormat.class)) {
                for (Object o : list) {
                    try {
                        Object o1 = field.get(o);
                        if (o1==null){
                            continue;
                        }
                        String s = String.valueOf(o1);
                        if (s.length() == 10) {
                            field.set(o, Long.parseLong(s) * 1000);
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }

        return new TableDataInfo(list, total);
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
