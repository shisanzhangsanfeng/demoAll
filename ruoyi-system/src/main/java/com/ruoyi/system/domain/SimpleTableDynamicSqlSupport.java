package com.ruoyi.system.domain;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;
import java.util.Date;

public class SimpleTableDynamicSqlSupport {
    public static final SimpleTable simpleTable = new SimpleTable();
    public static final SqlColumn<Integer> id = simpleTable.id;
    public static final SqlColumn<String> firstName = simpleTable.firstName;
    public static final SqlColumn<String> lastName = simpleTable.lastName;
    public static final SqlColumn<Date> birthDate = simpleTable.birthDate;
    public static final SqlColumn<Boolean> employed = simpleTable.employed;
    public static final SqlColumn<String> occupation = simpleTable.occupation;

    public static final class SimpleTable extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);
        public final SqlColumn<String> firstName = column("first_name", JDBCType.VARCHAR);
        public final SqlColumn<String> lastName = column("last_name", JDBCType.VARCHAR);
        public final SqlColumn<Date> birthDate = column("birth_date", JDBCType.DATE);
        public final SqlColumn<Boolean> employed = column("employed", JDBCType.VARCHAR, "examples.simple.YesNoTypeHandler");
        public final SqlColumn<String> occupation = column("occupation", JDBCType.VARCHAR);

        public SimpleTable() {
            super("SimpleTable");
        }
    }

}
