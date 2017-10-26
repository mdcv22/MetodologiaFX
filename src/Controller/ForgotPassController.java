/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author jonathancastro
 */
public class ForgotPassController implements Initializable {

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
    private ComboBox cmb_Question;
    @FXML
    private TextField txt_answer;
    @FXML
    private TextField txt_User;
    @FXML
    private PasswordField txt_Pass1;
    @FXML
    private PasswordField txt_Pass2;
    @FXML
    private Label lbl_newP;
    @FXML
    private Label lbl_confirmP;
    @FXML
    private Button btn_save;
    
    public void CheckSecQ(){
        if (txt_answer.getText().isEmpty() && !txt_User.getText().isEmpty()){
            //Metodo log in
            Connection cn = conn.conexion();
            try {
                stmt = cn.prepareCall("{select `login`(?, ?, ?)}");
                stmt.setInt(1, Integer.parseInt(txt_User.getText()));
                stmt.setInt(2,cmb_Question.getSelectionModel().getSelectedIndex() + 1);     
                stmt.setString(3, txt_answer.getText());
                //ERROR
                int result;
                rs = stmt.executeQuery();
                while(rs.next()) {
                    result =rs.getInt(1);
                }
                
                        
            } catch (Exception e) {
            }
            
        }
    }
    
    public void Enable(int result){
        if (result == 1){
            lbl_newP.setDisable(false);
            lbl_confirmP.setDisable(false);
            txt_Pass1.setDisable(false);
            txt_Pass2.setDisable(false);
            btn_save.setDisable(false);
        }
        else {
            //Mensaje pregunta y respuesta no coincide
        }
    }
    
    public void Save(){
        
    }
}
