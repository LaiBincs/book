package com.bin.test;

import com.bin.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.test
 * @ClassName: JdbcUtilsTest
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/06 23:55
 */
public class JdbcUtilsTest {
    @Test
    public void testJdbcUtils() {
        for (int i = 0; i < 100; i++) {
            Connection connection = JdbcUtils.getConnection();
            System.out.println(connection + " " + (i+1));
//            JdbcUtils.close(connection);

        }
    }
}
