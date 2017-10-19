/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author jonathancastro
 */
public class LogInController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private TextField txt_User;
    @FXML
    private PasswordField txt_Password;
    @FXML
    private Button btn_Exit;
    
    public void LogIn(){
        if (txt_User.getText().length() != 0 && !txt_Password.getText().isEmpty()){
            //Metodo log in
        }
        else {
            //Mensaje llenar campos
        }
    }
    
    public void FYP (){
        View.Main main = new View.Main();
    }
    
    public void Language (){
        
    }
    
    public void Exit() {
        Stage stage = (Stage) btn_Exit.getScene().getWindow();
        stage.close();
    }
}
