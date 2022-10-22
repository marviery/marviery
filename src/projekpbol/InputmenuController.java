/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekpbol;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import model.*;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class InputmenuController implements Initializable {
boolean editdata=false;
    @FXML
    private TextField tcode;
    @FXML
    private TextField tnama;
    @FXML
    private TextField tharga;
    @FXML
    private Button tsave;
    @FXML
    private Button tdelete;
    @FXML
    private Button texit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void execute(modelmenu d){
        if(!d.getCode().isEmpty()){
          editdata=true;
          tcode.setText(d.getCode());
          tharga.setText(d.getHarga());
          tnama.setText(d.getNamamakanan());         
          tcode.setEditable(false);
          tnama.requestFocus();  
          
        }
    }    

    @FXML
    private void saveclick(ActionEvent event) {
        modelmenu n=new modelmenu();        
        n.setCode(tcode.getText());
        n.setHarga(tharga.getText());     
        n.setNamamakanan(tnama.getText());  
          
        FXMLDocumentController.datamenu.setmodelmenu(n);
        if(editdata){
            if(FXMLDocumentController.datamenu.update()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   
               tcode.setEditable(true);        
               deleteclick(event);                
            } else {               
                Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
                a.showAndWait(); 
            }
            }else if(FXMLDocumentController.datamenu.validasi(n.getCode())<=0){
            if(FXMLDocumentController.datamenu.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            
               deleteclick(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            tcode.requestFocus();
        }
    }

    @FXML
    private void deleteclick(ActionEvent event) {
            tcode.setText("");        
        tharga.setText("");
        tnama.setText("");       
    }

    @FXML
    private void exitclick(ActionEvent event) {
          texit.getScene().getWindow().hide();
    }
    
}
