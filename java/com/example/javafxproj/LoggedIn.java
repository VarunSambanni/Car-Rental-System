package com.example.javafxproj;

import javafx.fxml.FXML;
public class LoggedIn {

    @FXML
    protected void displayCarsHandler() {
        App main = new App() ;
        try{
            main.changeScene("displayCars.fxml");
        }
        catch (Exception e)
        {
            System.out.println("Exception while changing scene " + e.toString());
        }
    }
    @FXML
    protected void addCarHandler() {
        App main = new App() ;
        try{
            main.changeScene("addNewCar.fxml");
        }
        catch (Exception e)
        {
            System.out.println("Exception while changing scene " + e.toString());
        }
    }

    @FXML
    protected void editHandler() {
        App main = new App() ;
        try{
            main.changeScene("editCar.fxml");
        }
        catch (Exception e)
        {
            System.out.println("Exception while changing scene " + e.toString());
        }
    }
    @FXML
    protected void statsHandler(){
        App main = new App() ;
        try{
            main.changeScene("stats.fxml");
        }
        catch (Exception e)
        {
            System.out.println("Exception while changing scene " + e.toString());
        }
    }

    @FXML
    protected void deleteHandler(){
        App main = new App() ;
        try{
            main.changeScene("deleteCar.fxml");
        }
        catch (Exception e)
        {
            System.out.println("Exception while changing scene " + e.toString());
        }
    }

    @FXML
    protected void rentHandler(){
        App main = new App() ;
        try{
            main.changeScene("rent.fxml");
        }
        catch (Exception e)
        {
            System.out.println("Exception while changing scene " + e.toString());
        }
    }
    @FXML
    protected void returnHandler(){
        App main = new App() ;
        try{
            main.changeScene("return.fxml");
        }
        catch (Exception e)
        {
            System.out.println("Exception while changing scene " + e.toString());
        }
    }
    @FXML
    protected void loginScene(){
        App main = new App() ;
        try{
            main.changeScene("login.fxml");
        }
        catch (Exception e)
        {
            System.out.println("Exception while changing scene " + e.toString());
        }
    }
}