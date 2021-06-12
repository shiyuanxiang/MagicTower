package mysql;

import session.Session;
import view.Config;

import javax.swing.*;
import java.sql.*;

public class DataConnection {
    private static Statement statement;

    public static void init() {
        try {
            //ע��MySQL��������
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("�������سɹ�");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("�������ش���");
        }
        //��ȡ���ݿ�����
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user", (String) Session.session.get("db_username"), (String) Session.session.get("db_password"));
            System.out.println("�������ݿ����ӳɹ�");
            statement = connection.createStatement();
        } catch (Exception e) {
            Config config = new Config();
            config.init();
            System.out.println("�������ݿ����Ӵ���");
        }
    }

    public static Statement getStatement() {
        if (statement == null) init();
        return statement;
    }

}
