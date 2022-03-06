/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import utilisateur.services.UtilisateurService;
import utilisateur.entities.utilisateur;

/**
 *
 * @author malek
 */
public class SessionUser {
    
    private static final UtilisateurService fs = new UtilisateurService();
    
    private static SessionUser instance = null;
    private static utilisateur user = null;
    
    private SessionUser(utilisateur userConnected) {
        super();
        SessionUser.user = userConnected;
        
    }
    
    private SessionUser(utilisateur userConnected, Boolean b) {
        super();
        SessionUser.user = userConnected;
    }
    
    public final static SessionUser getInstance(utilisateur userConnected) {

        if (SessionUser.instance == null) {
            SessionUser.instance = new SessionUser(userConnected);
        }
        return SessionUser.instance;
    }
    
    public final static SessionUser getFirstInstance(utilisateur userConnected) {

        if (SessionUser.instance == null) {

            SessionUser.instance = new SessionUser(userConnected,false);
      
        }
        return SessionUser.instance;
    }

    public static UtilisateurService getFs() {
        return fs;
    }

    public static SessionUser getInstance() {
        return instance;
    }

    public static utilisateur getUser() {
        return user;
    }



    public static void setUser(utilisateur user) {
        SessionUser.user = user;
    }
    
    

}
