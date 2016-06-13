/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytestsoft;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author mijan
 */
public class MyTestSoft extends Application {
    private static Stage mainStage;

    public static Stage getMainStage() {
        return mainStage;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        mainStage=stage;
        Parent root = FXMLLoader.load(getClass().getResource("MainUi.fxml"));
        
        Scene scene = new Scene(root);
        
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
