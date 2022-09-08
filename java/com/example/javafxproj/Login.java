package com.example.javafxproj;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login {

    @FXML
    private Label welcomeText, loginText;
    @FXML
    private Button loginButton;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    protected void userLogin() {
        App main = new App() ;

        if (username.getText().toString().equals("varun") && password.getText().toString().equals("12345")){
            try {
                main.changeScene("loggedIn.fxml");
            }
            catch (Exception e){
                System.out.println("Exception while changing scene " + e.toString());
            }
        }
        else if (username.getText().isEmpty() && password.getText().isEmpty()){
            loginText.setText("ENTER THE REQUIRED DATA");
        }
        else {
            loginText.setText("INVALID CREDENTIALS");
        }
    }
}