package com.example.restaurantmanagement.config;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



@Getter
@Setter
@NoArgsConstructor
@Configuration
public class ConnectDatabase {
    public static String url = System.getenv("URL");
    public static String user = System.getenv("USER");
    public static String password = System.getenv("PASSWORD");

    private static ConnectDatabase instance;
    public static ConnectDatabase getInstance() {
        if (instance == null) {
            instance = new ConnectDatabase();
        }
        return instance;
    }
    public Connection getConnection(){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connection successful !");
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}