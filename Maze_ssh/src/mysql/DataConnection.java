package mysql;

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
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user", "root", "Syxkxlm666.");
            System.out.println("�������ݿ����ӳɹ�");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("�������ݿ����Ӵ���");
        }
    }

    public static Statement getStatement() {
        if (statement == null) init();
        return statement;
    }

}
