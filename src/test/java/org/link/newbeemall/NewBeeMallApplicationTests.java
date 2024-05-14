package org.link.newbeemall;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class NewBeeMallApplicationTests {

    @Autowired
    private DataSource dataSource;
    @Test
    void datasourceTest() throws SQLException {

        Connection connection = dataSource.getConnection();
        System.out.println("获取链接");
        System.out.println(connection != null );

        connection.close();
    }

    @Test
    void datasourceClassTest() throws SQLException {

        Connection connection = dataSource.getConnection();
        System.out.println("默认数据源是：" + dataSource.getClass());

    }

}
