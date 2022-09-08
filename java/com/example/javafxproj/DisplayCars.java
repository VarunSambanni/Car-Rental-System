package com.example.javafxproj;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

public class DisplayCars {

    private String currentCarEditId ; // Refers to the currentCarEditId to be edited
    @FXML
    private Label displayCarsHeading, addCarMessage, editCarMessage, deleteCarMessage, rentCarMessage, returnCarMessage;

    @FXML
    private Label totalCarsLabel, availableCarsLabel ;
    @FXML
    private TableView<Car> table ;
    @FXML
    private TableColumn<Car, String> id, model, brand , available, name, phoneNo, address, startTime;
    @FXML
    private TableColumn<Car, Integer> cost;

    @FXML
    private TextField inputId, inputModel, inputBrand, inputAvailable, inputName, inputPhoneNo, inputAddress, inputCost, inputStartTime;

    @FXML
    private TextField rentId, rentName, rentPhoneNo, rentAddress, rentStartTime;

    @FXML
    private TextField returnId, returnEndTime, returnName, returnStartTime, returnDuration, returnTotalCost ;
    @FXML
    private TextField editCarId, editCarModel, editCarBrand, editCarAvailable, editCarName, editCarPhoneNo, editCarAddress, editCarCost, editCarStartTime;
    @FXML
    private TextField deleteCarId ;
    public ObservableList<Car> cars = FXCollections.observableArrayList() ;

    public void initialize(){
        int totalCars = 0, availableCars = 0 ;
        try {   // For display cars scene
            Statement statement = App.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CAR") ;
            while(resultSet.next()){
                totalCars ++ ;
                if (resultSet.getString("available").equals("Yes")){
                    availableCars++ ;
                }
                cars.add(new Car(resultSet.getString("id"), resultSet.getString("model"), resultSet.getString("brand"), resultSet.getString("available").isEmpty() ? "Yes" : resultSet.getString("available"),
                        resultSet.getString("name").isEmpty() ? "NA" : resultSet.getString("name"), resultSet.getString("phoneNo").isEmpty() ? "NA" : resultSet.getString("phoneNo"), resultSet.getString("address").isEmpty() ? "NA" :resultSet.getString("address"), resultSet.getInt("cost"),
                        resultSet.getString("startTime").isEmpty() ? "NA" : resultSet.getString("startTime"))) ;
            }
            table.setItems(cars);
            id.setCellValueFactory(new PropertyValueFactory<Car, String>("id"));
            model.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
            brand.setCellValueFactory(new PropertyValueFactory<Car, String>("brand"));
            available.setCellValueFactory(new PropertyValueFactory<Car, String>("available"));
            name.setCellValueFactory(new PropertyValueFactory<Car, String>("name"));
            phoneNo.setCellValueFactory(new PropertyValueFactory<Car, String>("phoneNo"));
            address.setCellValueFactory(new PropertyValueFactory<Car, String>("address"));
            cost.setCellValueFactory(new PropertyValueFactory<Car, Integer>("cost"));
            startTime.setCellValueFactory(new PropertyValueFactory<Car, String>("startTime"));
            try{
                resultSet.close();
            }
            catch (SQLException e)  {
            }
        }
        catch (Exception e){
            System.out.println("Exception caught in initialize method " + e);
        }

        try{    // For stats scene
            totalCarsLabel.setText(String.valueOf(totalCars));
            availableCarsLabel.setText(String.valueOf(availableCars));
        }
        catch (Exception e){
            System.out.println("Exception caught in initialize method " + e);
        }
    }

    @FXML
    protected void loggedInScene(){
        App main = new App() ;
        try{
            main.changeScene("loggedIn.fxml");
        }
        catch (Exception e)
        {
            System.out.println("Exception while changing scene " + e.toString());
        }
    }

    @FXML
    protected void addCar(){
        System.out.println("Adding " + inputId.getText());
        try {
            Statement statement = App.connection.createStatement();
            PreparedStatement stmt = App.connection.prepareStatement("INSERT INTO CAR VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, inputId.getText());
            stmt.setString(2, inputModel.getText());
            stmt.setString(3, inputBrand.getText());
            stmt.setString(4, inputAvailable.getText());
            stmt.setString(5, inputName.getText());
            stmt.setString(6, inputPhoneNo.getText());
            stmt.setString(7, inputAddress.getText()) ;
            stmt.setInt(8, Integer.parseInt(inputCost.getText()));
            stmt.setString(9, inputStartTime.getText());
            stmt.executeUpdate();
        }
        catch (java.sql.SQLException e){
            System.out.println("Exception while adding car " + e.toString());
        }

        addCarMessage.setText("CAR '" + inputId.getText() + "' ADDED");
        inputId.setText("");
        inputModel.setText("");
        inputBrand.setText("");
        inputAvailable.setText("");
        inputName.setText("");
        inputPhoneNo.setText("");
        inputAddress.setText("");
    }

    @FXML
    protected void loadDetails(){
        String carId = editCarId.getText();
        boolean flag = false;
        String[] loadedDetails = new String[9] ;
        try {
            Statement statement = App.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM CAR WHERE ID='%s'", carId));
            while (resultSet.next()) {
                flag = true ;
                loadedDetails[0] = resultSet.getString("id") ;
                loadedDetails[1] = resultSet.getString("model") ;
                loadedDetails[2] = resultSet.getString("brand") ;
                loadedDetails[3] = resultSet.getString("available").isEmpty() ? "NA" : resultSet.getString("available");
                loadedDetails[4] = resultSet.getString("name").isEmpty() ? "NA" : resultSet.getString("name") ;
                loadedDetails[5] = resultSet.getString("phoneNo").isEmpty() ? "NA" : resultSet.getString("phoneNo");
                loadedDetails[6] = resultSet.getString("address").isEmpty() ? "NA" : resultSet.getString("address");
                loadedDetails[7] = String.valueOf(resultSet.getInt("cost"))  ;
                loadedDetails[8] = resultSet.getString("startTime").isEmpty() ? "NA" : resultSet.getString("startTime");
            }
        }
        catch (java.sql.SQLException e){
            System.out.println("Exception while loading details " + e.toString());
        }

        if (flag == false){
            editCarMessage.setText("NO SUCH CAR ID FOUND");
            return ;
        }
        currentCarEditId = loadedDetails[0];
        editCarModel.setText(loadedDetails[1]);
        editCarBrand.setText(loadedDetails[2]);
        editCarAvailable.setText(loadedDetails[3]);
        editCarName.setText(loadedDetails[4]);
        editCarPhoneNo.setText(loadedDetails[5]);
        editCarAddress.setText(loadedDetails[6]);
        editCarCost.setText(loadedDetails[7]);
        editCarStartTime.setText(loadedDetails[8]);
    }

    @FXML
    protected void editCar(){
        try{
            Statement statement = App.connection.createStatement();
            String sqlUpdateQuery = String.format("UPDATE CAR SET model = '%s', brand = '%s', available = '%s', name = '%s', phoneNo = '%s', address = '%s', cost = %s, startTime = '%s' WHERE id = '%s'",
                    editCarModel.getText(), editCarBrand.getText(), editCarAvailable.getText(), editCarName.getText(), editCarPhoneNo.getText(), editCarAddress.getText(), Integer.parseInt(editCarCost.getText()), editCarStartTime.getText(), currentCarEditId);
            statement.executeUpdate(sqlUpdateQuery) ;
            editCarMessage.setText("CAR '" + editCarId.getText() + "' DETAILS EDITED");
            editCarId.setText("");
            editCarModel.setText("");
            editCarBrand.setText("");
            editCarAvailable.setText("");
            editCarName.setText("");
            editCarPhoneNo.setText("");
            editCarAddress.setText("");
            editCarCost.setText("");
            editCarStartTime.setText("");
        }
        catch (java.sql.SQLException e) {
            System.out.println("Exception while loading details " + e.toString());
        }

    }

    @FXML
    protected void deleteCar(){
        String carId = deleteCarId.getText();
        boolean flag = false;
        try {
            Statement statement = App.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM CAR WHERE ID='%s'", carId)) ;
            while (resultSet.next()) {
                flag = true ;
            }
            if (flag == true){
                statement.executeUpdate(String.format("DELETE FROM CAR WHERE ID = '%s'", carId)) ;
            }
        }
        catch (java.sql.SQLException e){
            System.out.println("Exception while loading details " + e.toString());
        }

        if (flag == false) {
            deleteCarMessage.setText("NO SUCH CAR ID FOUND");
            return;
        }
        deleteCarMessage.setText("CAR '" + carId + "' DELETED");
        deleteCarId.setText("");
    }

    @FXML
    protected  void rentCar(){
        String carId = rentId.getText() ;
        String avail = "" ;
        boolean flag = false ;
        try {
            Statement statement = App.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM CAR WHERE ID='%s'", carId)) ;
            while (resultSet.next()) {
                flag = true ;
                avail = resultSet.getString("available");
            }
            if (flag == false) {
                rentCarMessage.setText("NO SUCH CAR ID FOUND");
                return;
            }
            if (flag == true && avail.equals("No") == true){
                rentCarMessage.setText("CAR UNAVAILABLE");
                return ;
            }
            if (flag == true){
                String sqlUpdateQuery = String.format("UPDATE CAR SET available='%s', name = '%s', phoneNo = '%s', address = '%s', startTime = '%s' WHERE id = '%s'",
                        "No", rentName.getText(), rentPhoneNo.getText(), rentAddress.getText(), rentStartTime.getText(), carId);
                System.out.println("Sqplupdatequery: " + sqlUpdateQuery);
                statement.executeUpdate(sqlUpdateQuery) ;
                rentCarMessage.setText("CAR '" + rentId.getText() + "' RENTED");
                rentName.setText("");
                rentPhoneNo.setText("");
                rentAddress.setText("");
                rentStartTime.setText("");
            }
        }
        catch (java.sql.SQLException e){
            System.out.println("Exception while loading details " + e.toString());
        }
    }

    @FXML
    protected void returnCar() {
        String carId = returnId.getText();
        String startTime = "", name = "" ;
        double cost = 0 ;
        boolean flag = false;
        try {
            Statement statement = App.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM CAR WHERE ID='%s'", carId)) ;
            while (resultSet.next()) {
                flag = true ;
                cost = Integer.parseInt(resultSet.getString("cost")) ;
                startTime = resultSet.getString("startTime") ;
                name = resultSet.getString("name") ;
            }
            if (flag == false){
                returnCarMessage.setText("NO SUCH CAR ID FOUND");
                return;
            }
            if (flag == true){
                String sqlUpdateQuery = String.format("UPDATE CAR SET available = '%s', name = '%s', phoneNo = '%s', address = '%s', startTime = '%s' WHERE id = '%s'",
                        "Yes", "NA", "NA",  "NA", "NA", carId);
                statement.executeUpdate(sqlUpdateQuery) ;
                returnName.setText(name);
                returnStartTime.setText(startTime);
                long[] timeDiff= Car.calcDiffTimes(startTime, returnEndTime.getText()) ;
                returnDuration.setText(timeDiff[0] + " hours " + timeDiff[1] + " minutes");
                double totalCost = timeDiff[0]*cost + ((double)timeDiff[1]/(double)60)*(double)cost;
                returnTotalCost.setText(String.valueOf(totalCost));
            }
        }
        catch (java.sql.SQLException e){
            System.out.println("Exception while loading details " + e.toString());
        }
    }
}
