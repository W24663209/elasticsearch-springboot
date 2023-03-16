package cn.bensun.elasticsearch.domain;

import lombok.Data;

import java.util.List;

@Data
public class SyncEsTable<T> {
    private String database;
    private Long es;
    private Integer id;
    private Boolean isDdl;
    private String table;
    private Long ts;
    private String type;
    private List<T> date;
}