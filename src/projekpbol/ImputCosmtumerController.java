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
public class ImputCosmtumerController implements Initializable {
  boolean editdata=false;
    @FXML
    private TextField labelpelanggnan;
    @FXML
    private TextField labelpesanan;
    @FXML
    private TextField labelmeja;
    @FXML
    private Button deletes;
    @FXML
    private Button exit;
    @FXML
    private Button saves;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        }
      public void execute(modelcustomer d){
        if(!d.getIdpelangganan().isEmpty()){
          editdata=true;
          labelpelanggnan.setText(d.getIdpelangganan());
          labelpesanan.setText(d.getNomeja());
          labelmeja.setText(d.getPesanan());         
          labelpelanggnan.setEditable(false);
          labelpesanan.requestFocus();  
          
        }
    }    
      
    @FXML
    private void deleteclick(ActionEvent event) {
        labelpelanggnan.setText("");        
        labelpesanan.setText("");
        labelmeja.setText("");       
    }

    @FXML
    private void exitclick(ActionEvent event) {
          exit.getScene().getWindow().hide();
    }

    @FXML
    private void saveclick(ActionEvent event) {
          modelcustomer n=new modelcustomer();        
        n.setIdpelangganan(labelpelanggnan.getText());
        n.setPesanan(labelpesanan.getText());     
        n.setNomeja(labelmeja.getText());  
      
        
        FXMLDocumentController.datacustomer.setmodelcustomer(n);
        if(editdata){
            if(FXMLDocumentController.datacustomer.update()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   
               labelpelanggnan.setEditable(true);        
               deleteclick(event);                
            } else {               
                Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
                a.showAndWait(); 
            }
            }else if(FXMLDocumentController.datacustomer.validasi(n.getIdpelangganan())<=0){
            if(FXMLDocumentController.datacustomer.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            
               deleteclick(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            labelpelanggnan.requestFocus();
        }
    }
    
}
