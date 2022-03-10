/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paiement.GUI;

import evenements.services.evenementsService;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import paiement.entities.paiement;
import paiement.service.PaiementService;
import publication.services.publicationService;

/**
 * FXML Controller class
 *
 * @author SBS
 */

public class TBFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
  /*  Connection cnx;
    public PreparedStatement pste;
    public ResultSet result;*/
   PaiementService PS=new PaiementService();
   
   ObservableList<paiement> obp=FXCollections.observableArrayList();
    @FXML
    private AnchorPane AnchroPane;

    @FXML
    private BorderPane BorderPane;

    @FXML
    private HBox HboxCenter;

    @FXML
    private HBox HboxCenterLeft;

    @FXML
    private AreaChart<?, ?> AreaChart;

    @FXML
    private VBox vboxtabv;

    @FXML
    private TableView<paiement> tabv;

    @FXML
    private TableColumn<paiement, String> colid;

    @FXML
    private TableColumn<paiement, String> coldate;

    @FXML
    private TableColumn<paiement, String> colmp;

    @FXML
    private TableColumn<paiement, String> colprix;

    @FXML
    private HBox HBoxBot;

    @FXML
    private HBox HboxBotLeft;

    @FXML
    private Label labelpay;

    @FXML
    private Text nbrpay;

    @FXML
    private HBox HboxBotCenter;

    @FXML
    private Label labelpub;

    @FXML
    private Text nbrpu;

    @FXML
    private HBox HboxBotRight;

    @FXML
    private Label labelev;

    @FXML
    private Text nbre;
            
        
            
    
            void RemplirTable(){
            List<paiement> p=PS.afficher();
            p.forEach(e->obp.add(e));
            colid.setCellValueFactory(new PropertyValueFactory<>("ID_Paiement"));
            coldate.setCellValueFactory(new PropertyValueFactory<>("Date_paiement"));
            colmp.setCellValueFactory(new PropertyValueFactory<>("Type_p"));
            colprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            tabv.setItems(obp);
            
            
            
            
            }
            
            @FXML
            public void remplirArea() {
             publicationService PUS=new publicationService();   
            PaiementService PS=new PaiementService();
            evenementsService ES=new evenementsService();
            
            List<paiement> paiements = new ArrayList<>(PS.remplirArea());
            // System.out.println("PAI : "+paiements);
             XYChart.Series series = new XYChart.Series();
             int i=0;
             for(paiement p:paiements)
                 
             {i++;
             String s=Integer.toString(i);
                 series.getData().add(new XYChart.Data(s,p.getPrix()));
                 //System.out.println("PRIX  /  PAY : "+p.getPrix()+" "+p.getDate_paiement());
             }
              
              AreaChart.getData().add(series);
              labelpay.setText(PS.countpay());
              labelpub.setText(PUS.countpub());
              labelev.setText(ES.countev());
              
    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        


//AreaChart.getData().add(series);
//ScatterChart.getData().add(series);
        remplirArea();
        RemplirTable();
        //countpay();
    }   
}
