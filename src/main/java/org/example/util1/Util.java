package org.example.util1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db",
                    "jdbc_user", "123456"); // <2>
            return con;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable() {
        try {
            Connection con = Util.getConnection();
            Statement statement = con.createStatement(); // <3>
            String sql = "   create table if not exists task(" +
                    "        id serial  primary key," +
                    "        title varchar not null,   " +
                    "        content text not null," +
                    "        status varchar(10) not null," +
                    "        created_date timestamp default now()," +
                    "        finished_date timestamp" +
                    "      );";
            statement.executeUpdate(sql); // <4>
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
