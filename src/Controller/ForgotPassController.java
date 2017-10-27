/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Conection;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author jonathancastro
 */
public class ForgotPassController implements Initializable {

    Model.Conection conn = new Model.Conection();
    CallableStatement stmt;
    ResultSet rs;
    Connection cn = conn.conexion();
    
    @FXML
    public ComboBox<String> cmb_Question;
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
    @FXML
    public ChoiceBox<String> cmb_preguntas;
    @FXML
    public Pane pane_2;
     
    
//    ArrayList<String> items = new ArrayList<>();
      ObservableList<String> items = FXCollections.observableArrayList();
   
//    @FXML
//    public void initialize() {
//        cmb_Question.getItems().removeAll(cmb_Question.getItems());
//        cmb_Question.getItems().addAll("Option A", "Option B", "Option C");
//        cmb_Question.getSelectionModel().select("Option B");
//    }

     @Override
    public void initialize(URL url, ResourceBundle rb) {
            Preguntas();
//            cmb_Question.setItems(items);
//            
//          cmb_Question.getItems().removeAll(cmb_Question.getItems());
//          cmb_Question.getItems().addAll("Option A", "Option B", "Option C");
//          cmb_Question.getSelectionModel().select("Option B");

            cmb_preguntas.setItems(items);
    }
    
    public void Preguntas(){
        try {
            stmt = cn.prepareCall("{CALL listar_pre()}");
            rs = stmt.executeQuery();
            while (rs.next()){
                items.add(rs.getString(1));
            }
        } catch (Exception e) {
        }
    }
    
    public void CheckSecQ(){
        if (txt_User.getText().length() != 0 && !txt_answer.getText().isEmpty()){
            try {
                stmt = cn.prepareCall("{CALL Sec_Ques(?,?,?)}");
                stmt.setInt(1, Integer.parseInt(txt_User.getText()));
                stmt.setInt(2,cmb_preguntas.getSelectionModel().getSelectedIndex() + 1);     
                stmt.setString(3, txt_answer.getText());
                int result = 0;
                rs = stmt.executeQuery();
                while(rs.next()) {
                    result = rs.getInt(1);
                }
                Enable(result);
            } catch (Exception e) {
            }
        }
    }
    
    public void Enable(int result){
        if (result == 1){
//            txt_Pass1.setDisable(false);
//            txt_Pass2.setDisable(false);
//            btn_save.setDisable(false);
//            lbl_newP.setDisable(false);
//            lbl_confirmP.setDisable(false);
              pane_2.setDisable(false);
        }
        else {
           JOptionPane.showMessageDialog(null, "ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void Save(){
        if(!txt_Pass1.getText().isEmpty() && !txt_Pass2.getText().isEmpty()){
            try {
                stmt = cn.prepareCall("{CALL newPass(?,?)}");
                stmt.setString(1, txt_Pass1.getText());
                stmt.setString(2, txt_Pass2.getText());   
                stmt.execute();
            } catch (Exception e) {
            }
        }
    }
}
