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
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.scene.Parent;
import javafx.stage.Modality;
/**
 * FXML Controller class
 *
 * @author jonathancastro
 */
public class LogInController implements Initializable {

    private Stage primaryStage;
    private AnchorPane rootLayout;
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
            int rol = 0;
            int user = Integer.parseInt(txt_User.getText());
            try {
                stmt = cn.prepareCall("{CALL `login`(?, ?)}");
                stmt.setInt(1,user);
                stmt.setString(2,txt_Password.getText());     
                //ERROR
                rs = stmt.executeQuery();
                while(rs.next()) {
                    rol =rs.getInt(1);
                }
                switch (rol)
                {
                    case 0:break; //Nada'
                    case 1:
                        //JOptionPane.showMessageDialog(null, "Rework");
                        break;
                    case 2:
                        //JOptionPane.showMessageDialog(null, "Tecnico");
                        break;
                    case 3:
                        //JOptionPane.showMessageDialog(null, "Supervisor");
                        break;
                }
                        
            } catch (Exception e) {
            }
            
        }
    }
    
    public void FYP () throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/ForgotPass.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle("App");
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.setScene(scene);
        
        stage.showAndWait();
    }
    
    public void Language (){
        
    }
    
    public void Exit() {
        Stage stage = (Stage) btn_Exit.getScene().getWindow();
        stage.close();
    }
}
