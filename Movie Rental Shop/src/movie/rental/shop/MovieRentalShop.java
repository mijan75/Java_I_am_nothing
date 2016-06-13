/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.rental.shop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author mijan
 */
public class MovieRentalShop extends Application {
    private static  Stage mainStage;
    private static Scene MainUi;
    private static Scene SuperVisorUi;
    private static Scene EmployeUI;
    private static Scene superVisor;

    public static Scene getSuperViosrUiScene() {
        return SuperVisorUi;
    }

    public static Scene getMainUIScene() {
        return MainUi;
    }
    public static Stage getMainStage() {
        return mainStage;
    }

    public static Scene getEmployeUI() {
        return EmployeUI;
    }

    public static Scene getSuperVisor() {
        return superVisor;
    }
    
    
    
    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("MainUI.fxml"));
        Parent root1 = FXMLLoader.load(getClass().getResource("SupervisorUI.fxml"));
        Parent root2 = FXMLLoader.load(getClass().getResource("EmployeeSignUpUI.fxml"));
        Parent root3 = FXMLLoader.load(getClass().getResource("SuperVisorUI.fxml"));
        
        Scene scene = new Scene(root);
        Scene scene1 = new Scene(root1);
        Scene scene2 = new Scene(root2);
        Scene scene3 = new Scene(root3);
        
        
        
        MainUi=scene;
        SuperVisorUi=scene1;
        EmployeUI = scene2;
        superVisor = scene3;
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
