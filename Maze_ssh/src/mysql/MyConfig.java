package mysql;

import java.io.Serializable;

public class MyConfig implements Serializable {
    public String db_username;
    public String db_password;
    public String ip;

    public MyConfig(String db_username, String db_password, String ip) {
        this.db_username = db_username;
        this.db_password = db_password;
        this.ip = ip;
    }
}