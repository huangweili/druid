package com.alibaba.druid.sql.mysql.bvt.visitor;

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor.Column;

public class MySqlSchemaStatVisitorTest4 extends TestCase {

    public void test_0() throws Exception {
        String sql = "insert into users (id, name) values(?, ?)";

        MySqlStatementParser parser = new MySqlStatementParser(sql);
        List<SQLStatement> statementList = parser.parseStatementList();
        SQLStatement statemen = statementList.get(0);

        Assert.assertEquals(1, statementList.size());

        MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
        statemen.accept(visitor);

        System.out.println(sql);
        System.out.println("Tables : " + visitor.getTables());
        System.out.println("fields : " + visitor.getFields());

        Assert.assertEquals(1, visitor.getTables().size());
        Assert.assertEquals(true, visitor.getTables().containsKey("users"));

        Assert.assertEquals(2, visitor.getFields().size());
        Assert.assertEquals(true, visitor.getFields().contains(new Column("users", "id")));
        Assert.assertEquals(true, visitor.getFields().contains(new Column("users", "name")));

    }

}