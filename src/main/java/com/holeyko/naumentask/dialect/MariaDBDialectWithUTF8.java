package com.holeyko.naumentask.dialect;

import org.hibernate.dialect.MariaDBDialect;

public class MariaDBDialectWithUTF8 extends MariaDBDialect {
    @Override
    public String getTableTypeString() {
        return "ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
