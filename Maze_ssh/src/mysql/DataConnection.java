package mysql;

import session.Session;
import view.Config;

import javax.swing.*;
import java.sql.*;

public class DataConnection {
    private static Statement statement;

    public static void init() {
        try {
            //注册MySQL驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("驱动加载成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("驱动加载错误");
        }
        //获取数据库连接
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user", (String) Session.session.get("db_username"), (String) Session.session.get("db_password"));
            System.out.println("建立数据库连接成功");
            statement = connection.createStatement();
        } catch (Exception e) {
            Config config = new Config();
            config.init();
            System.out.println("建立数据库连接错误");
        }
    }

    public static Statement getStatement() {
        if (statement == null) init();
        return statement;
    }

}
