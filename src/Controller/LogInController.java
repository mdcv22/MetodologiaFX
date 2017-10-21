/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * FXML Controller class
 *
 * @author jonathancastro
 */
public class LogInController implements Initializable {

    Model.Conection conn = new Model.Conection();
    CallableStatement stmt;
    ResultSet rs;
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
            Connection cn = conn.conexion();
            int user = Integer.parseInt(txt_User.getText());
            try {
                stmt = cn.prepareCall("call login(?,?)");
                stmt.setInt(1,user);
                stmt.setString(2,txt_Password.getText());     
                //ERROR
                rs = stmt.executeQuery();
                while(rs.next()) {
                int rol =rs.getInt(8);
}
            } catch (Exception e) {
            }
            
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
