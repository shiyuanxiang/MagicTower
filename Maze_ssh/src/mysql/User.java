package mysql;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //login
    public static boolean login(String username, String password) throws SQLException {
        User user = null;
        String sql = "select * from user where username='" + username + "' and password='" + password + "'";
        ResultSet resultSet = DataConnection.getStatement().executeQuery(sql);
        if (resultSet.next()) {
            user = new User(resultSet.getString(1), resultSet.getString(2));
        }
        if (user != null) {

        }
        return user != null;
    }

    //register
    public static int register(String username, String password) throws SQLException {
        User user = null;
        String sql = "select * from user where username='" + username + "'";
        ResultSet resultSet = DataConnection.getStatement().executeQuery(sql);
        if (resultSet.next()) {
            user = new User(resultSet.getString(1), resultSet.getString(2));
        }
        if (user == null) {
            sql = "insert into user(username,password) values('" + username + "','" + password + "')";
            DataConnection.getStatement().execute(sql);
            System.out.println("insert success");
            return 1;
        } else {
            return 2;
        }
    }

    //查询所有用户
    public static List<User> findAllUsers() throws SQLException {
        String sql = "select * from user ";
        ResultSet resultSet = DataConnection.getStatement().executeQuery(sql);
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add(new User(resultSet.getString(1), resultSet.getString(2)));
        }
        return users;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
