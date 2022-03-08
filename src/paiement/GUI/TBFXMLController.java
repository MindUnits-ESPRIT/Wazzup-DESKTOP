/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paiement.GUI;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import paiement.entities.paiement;
import paiement.service.PaiementService;

/**
 * FXML Controller class
 *
 * @author SBS
 */

public class TBFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection cnx;
    public PreparedStatement pste;
    public ResultSet result;
     @FXML
    private AnchorPane AnchroPane;

    @FXML
    private BorderPane BorderPane;

    @FXML
    private HBox HboxTop;

    @FXML
    private HBox HboxCenter;

    @FXML
    private HBox HboxCenterLeft;

    @FXML
    private AreaChart<?, ?> AreaChart;

    @FXML
    private HBox HboxCenterRight;

    @FXML
    private ScatterChart<?, ?> ScatterChart;

    @FXML
    private HBox HBoxBot;

    @FXML
    private HBox HboxBotLeft;

    @FXML
    private Text nbrpay;

    @FXML
    private TextField txtnbrpay;

    @FXML
    private HBox HboxBotCenter;

    @FXML
    private TextField txtnbrpu;

    @FXML
    private Text nbrpu;

    @FXML
    private HBox HboxBotRight;

    @FXML
    private TextField txtpe;

    @FXML
    private Text nbre;

    
    
   /* @FXML
    public List<paiement>remplirArea() {
          List<paiement> paiements = new ArrayList<>();
          List<String> date = new ArrayList<>();
          List<String> prix = new ArrayList<>();
          XYChart.Series series = new XYChart.Series();
          
        String req = "SELECT prix,Date_paiement FROM paiement";       
        try{
        pste=cnx.prepareStatement(req);
        result=pste.executeQuery();
            while(result.next()){ 
                System.out.println("aaaaaaaaa");
                paiement p = new paiement();              
                p.setDate_paiement(result.getString(1));
                p.setPrix(result.getFloat(2));
                paiements.add(p);
               //series.getData().add(new XYChart.Data(result.getString("prix"),result.getString("Date_paiement")));
            }
            } catch (SQLException ex) {
            Logger.getLogger(PaiementService.class.getName()).log(Level.SEVERE, null, ex);
        }
     return paiements;
      //AreaChart.getData().add(series);
    }
*/
            @FXML
            public void remplirArea() {
            PaiementService PS=new PaiementService();
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
              ScatterChart.getData().add(series);
              AreaChart.getData().add(series);
    }
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
XYChart.Series series = new XYChart.Series();
series.getData().add(new XYChart.Data("1",21));
series.getData().add(new XYChart.Data("2",22));
series.getData().add(new XYChart.Data("3",23));
series.getData().add(new XYChart.Data("4",24));
series.getData().add(new XYChart.Data("5",25));
//AreaChart.getData().add(series);
//ScatterChart.getData().add(series);
        remplirArea();
        
    }   
}
