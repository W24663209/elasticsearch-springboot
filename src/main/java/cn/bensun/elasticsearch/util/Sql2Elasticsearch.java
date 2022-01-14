package cn.bensun.elasticsearch.util;


import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sql2Elasticsearch {
    enum Type {
        VARCHAR("String", null),
        BIGINT("Long", null),
        DECIMAL("BigDecimal", "import java.math.BigDecimal;"),
        INT("Integer", null),
        TINYINT("Integer", null),
        TIMESTAMP("Timestamp", "import java.sql.Timestamp;"),
        DATE("Date", "import java.sql.Date;"),
        //        TIMESTAMP("String", null),
        TEXT("String", null),
        LONGTEXT("String", null);

        private String value;

        private String importPack;

        Type(String value, String importPack) {
            this.value = value;
            this.importPack = importPack;
        }

        public static Type getTypeAndImport(String dataType) {
            return Arrays.stream(values()).filter(e -> e.name().equals(dataType.toUpperCase())).findAny().orElse(null);
        }

    }

    static class ElasticsearchPO {

        private static List<String> imports = new ArrayList<>(Arrays.asList("import lombok.Builder;", "import lombok.Data;",
                "import org.springframework.data.annotation.Id;", "import org.springframework.data.elasticsearch.annotations.Document;", "import org.springframework.data.elasticsearch.annotations.Field;"));

        private static StringBuffer filedSb = new StringBuffer();

        private static String getFieldSql2Java(String name) {
            StringBuffer sb = new StringBuffer();
            for (String n : name.split("_")) {
                if (sb.length() == 0) {
                    sb.append(n);
                } else {
                    sb.append(n.substring(0, 1).toUpperCase()).append(n.substring(1));
                }
            }
            return sb.toString();
        }

        @SneakyThrows
        private static void getFieldText(ResultSet rs) {
            while (rs.next()) {
                String columnName = getFieldSql2Java(rs.getString("COLUMN_NAME"));
                Type dataType = Type.getTypeAndImport(rs.getString("DATA_TYPE"));
                if (dataType.importPack != null) {
                    if (!imports.contains(dataType.importPack)) {
                        imports.add(dataType.importPack);
                    }
                }
                if ("PRI".equals(rs.getString("COLUMN_KEY"))) {
                    filedSb.append("\n@Id\n");
                } else {
                    filedSb.append(String.format("\n@Field(name = \"%s\",value = \"%s\")\n", rs.getString("COLUMN_NAME"), rs.getString("COLUMN_NAME")));
                    if (!rs.getString("COLUMN_NAME").equals(columnName)) {
//                        filedSb.append(String.format("@JsonProperty(\"%s\")\n", rs.getString("COLUMN_NAME")));
                    }
                }
                filedSb.append("private ").append(dataType.value).append(" ").append(columnName).append(";");
                filedSb.append("//").append(rs.getString("COLUMN_COMMENT")).append("\n");
            }
        }

        public static CharSequence getClassText(String tableName, ResultSet rs) {
            getFieldText(rs);
            StringBuffer sb = new StringBuffer();
            for (String anImport : imports) {
                sb.append(anImport).append("\n");
            }
            sb.append("@Data\n");
            sb.append(String.format("@Document(indexName = \"%s\")\n", tableName));
            sb.append("@Builder\n");
            StringBuffer poName = new StringBuffer();
            for (String t : tableName.replace("t_", "").split("_")) {
                poName.append(t.substring(0, 1).toUpperCase()).append(t.substring(1));
            }
            sb.append(String.format("public class %sPO {\n", poName));
            sb.append(filedSb).append("\n}");
            return sb;
        }
    }

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");//这是连接mysql数据库的驱动
        String url = "jdbc:mysql://127.0.0.1:3306/otc100?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false";
        String username = "root";
        String password = "123456";
        Connection con = DriverManager.getConnection(url, username, password);
        Statement stmt = con.createStatement();
        String dbName = "otc100";
        String tableName = "t_polymer_payment_order";
        String sql = "select COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT, COLUMN_KEY\n" +
                "from information_schema.COLUMNS\n" +
                "where TABLE_SCHEMA = '%s'\n" +
                "  and TABLE_NAME = '%s';";
        ResultSet rs = stmt.executeQuery(String.format(sql, dbName, tableName));
        System.out.println(ElasticsearchPO.getClassText(tableName, rs));
//        while (rs.next()) {
//            System.out.println(rs.getString("COLUMN_NAME"));
//            System.out.println(rs.getString("DATA_TYPE"));
//            System.out.println(rs.getString("COLUMN_COMMENT"));
//            System.out.println(rs.getString("COLUMN_KEY"));
//        }
    }
}
