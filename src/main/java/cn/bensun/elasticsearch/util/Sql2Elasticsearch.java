package cn.bensun.elasticsearch.util;


import lombok.SneakyThrows;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sql2Elasticsearch {
    enum Type {
        VARCHAR("String", null, FieldType.Text),
        BIGINT("Long", null, FieldType.Long),
        DECIMAL("Double", null, FieldType.Double),
        INT("Integer", null, FieldType.Integer),
        TINYINT("Integer", null, FieldType.Integer),
        TIMESTAMP("Long", null, FieldType.Long),
        DATE("Long", null, FieldType.Long),
        //        TIMESTAMP("String", null),
        TEXT("String", null, FieldType.Text),
        DOUBLE("Double", null, FieldType.Double),
        MEDIUMTEXT("String", null, FieldType.Text),
        LONGTEXT("String", null, FieldType.Text);

        private String value;

        private String importPack;

        private FieldType type;

        Type(String value, String importPack, FieldType type) {
            this.value = value;
            this.importPack = importPack;
            this.type = type;
        }

        public static Type getTypeAndImport(String dataType) {
            return Arrays.stream(values()).filter(e -> e.name().equals(dataType.toUpperCase())).findAny().orElse(null);
        }

    }

    static class ElasticsearchPO {

        private static List<String> imports = new ArrayList<>(Arrays.asList("import lombok.Builder;", "import lombok.Data;",
                "import org.springframework.data.annotation.Id;", "import org.springframework.data.elasticsearch.annotations.Document;", "import org.springframework.data.elasticsearch.annotations" +
                        ".Field;", "import lombok.AllArgsConstructor;", "import lombok.NoArgsConstructor;","import org.springframework.data.elasticsearch.annotations.FieldType;","import com.fasterxml.jackson.annotation.JsonFormat;"));

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
                try {
                    if (dataType.importPack != null) {
                        if (!imports.contains(dataType.importPack)) {
                            imports.add(dataType.importPack);
                        }
                    }
                } catch (Exception e) {
                    System.out.println(rs.getString("DATA_TYPE"));
                }
                if ("PRI".equals(rs.getString("COLUMN_KEY"))) {
                    filedSb.append("\n@Id\n");
                } else {
                    filedSb.append(String.format("\n@Field(name = \"%s\",value = \"%s\",type=FieldType.%s)\n", rs.getString("COLUMN_NAME"), rs.getString("COLUMN_NAME"), dataType.type));
                    if (!rs.getString("COLUMN_NAME").equals(columnName)) {
//                        filedSb.append(String.format("@JsonProperty(\"%s\")\n", rs.getString("COLUMN_NAME")));
                    }
                }
                if (Type.DATE.equals(dataType) || Type.TIMESTAMP.equals(dataType)) {
                    filedSb.append("@JsonFormat(pattern = \"yyyy-mm-dd hh:mm:ss\")\n");
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
            sb.append(String.format("@Document(indexName = \"%s\",type=\"%s\")\n", tableName, tableName));
            sb.append("@Builder\n");
            sb.append("@AllArgsConstructor\n" +
                    "@NoArgsConstructor\n");
            StringBuffer poName = new StringBuffer();
            for (String t : tableName.replace("t_", "").split("_")) {
                poName.append(t.substring(0, 1).toUpperCase()).append(t.substring(1));
            }
            sb.append(String.format("public class %s {\n", poName));
            sb.append(filedSb).append("\n}");
            return sb;
        }
    }

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");//这是连接mysql数据库的驱动
        String url = "jdbc:mysql://127.0.0.1:3307/yd?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false";
        String username = "root";
        String password = "123456";
        Connection con = DriverManager.getConnection(url, username, password);
        Statement stmt = con.createStatement();
        String dbName = "yd";
        String tableName = "t_payment";
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
