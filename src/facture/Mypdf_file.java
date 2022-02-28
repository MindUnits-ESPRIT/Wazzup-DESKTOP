/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facture;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;
import database.db;
import com.*;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;

import com.sun.scenario.effect.ImageData;
import utilisateur.entities.utilisateur;
import utilisateur.services.UtilisateurService;
import facture.entities.facture;
import facture.services.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime; 
import offre_publicitaire.entities.*;
import offre_publicitaire.service.OffreService;
import paiement.entities.paiement;
import paiement.service.PaiementService;
/**
 *
 * @author Ahmed Guedri
 */
public class Mypdf_file {
    
    public static void main(String[] args) throws FileNotFoundException, DocumentException, BadElementException, IOException{
    
    Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream("Facture.pdf"));
    document.open();
    db cnx = db.getInstance();
    System.out.println(cnx.hashCode());
    UtilisateurService us= new UtilisateurService();
    factureService fs = new factureService();
    LocalDateTime now = LocalDateTime.now();
    OffreService os = new OffreService();
    PaiementService ps = new PaiementService();
        paiement p = new paiement("Stripe",10);
        offre_publicitaire o = new offre_publicitaire("java","test",40);
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    utilisateur u = new utilisateur("Ahmed","Guedri",22,95990559,"guedri.ahmed@esprit.tn","test","User",4,"Male");
              Image img = Image.getInstance("C:\\Users\\Ahmed Guedri\\Wazzup-DESKTOP\\src\\facture\\wazzup.png");
              img.scaleToFit(100f, 100f);
        // Creating Image object from the imagedata
        document.add(img);
    document.add(new Paragraph("Bonjour "+u.getNom()+" "+u.getPrenom()+"\n Merci d'avoir effectué vos achats sur WAZZUP le "+dtf.format(now)));
    document.add(new Paragraph("Voici les détails de la commande : "+o.getNom_offre()+" "+o.getContenu_offre()+" "+o.getNbr_max_offre()));
    document.add(new Paragraph("Adresse de livraison : "+u.getNum_tel()));
    document.add(new Paragraph("La methode de paiement : "+p.getType_p()));
    document.add(new Paragraph("Montant Total = : "+p.getPrix()));

        
    document.close();
    
    
    
    }
}
