package com.example.javafxproj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.IOException;


public class App extends Application {

    private static Stage stg ;
    public static Connection connection ;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setTitle("Car Rental System");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        stg = stage ;
    }
    public void changeScene(String fxml) throws IOException{
        System.out.println("Scene change " + fxml);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stg.setScene(scene);
    }

    @Override
    public void stop(){
        try {
            App.connection.close();
            System.out.println("Connection closed ");
        }
        catch (Exception e){
            System.out.println("Exception closing connection to database " + e.toString());
        }
    }

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String user = "user";
            String password = "password";
            String url = "jdbc:mysql://127.0.0.1:3306/car?user=root";
            connection = DriverManager.getConnection(url, user, password);
        }
        catch (Exception e){
            System.out.println("Exception connecting to database " + e.toString());
        }
       launch();
    }
}