package cn.bensun.elasticsearch.util;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author weizongtang
 * @Description 分页插件
 * @CreateTime 2023/03/16 13:24:31
 */
public class PageableUtil {
    private PageableUtil() {
    }

    public static <T, R> Pageable getPageable(Integer pageNumber, Integer pageSize, ColumnUtil.SFunction<T, ?> fn) {
        return new Pageable() {
            @Override
            public int getPageNumber() {
                return pageNumber;
            }

            @Override
            public int getPageSize() {
                return pageSize;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return Sort.by(ColumnUtil.getFieldName(fn)).descending();
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
    }

    public static void main(String[] args) {
//        Pageable pageable = getPageable(10, 10, );
    }
}
