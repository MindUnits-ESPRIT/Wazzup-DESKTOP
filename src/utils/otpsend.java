/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import static utilisateur.test.ACCOUNT_SID;
import static utilisateur.test.AUTH_TOKEN;

/**
 *
 * @author malek
 */
public class otpsend {

public static final String ACCOUNT_SID = "ACa1c3f6d59e0c9f3d76e39dfec69e7c91"; 
    public static final String AUTH_TOKEN = "bd7a82b880d1e886d52059b1d5026ae4"; 

    public static void sendSms(String msg) {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

                 Message message = Message.creator( 
                new com.twilio.type.PhoneNumber("+21624664880"),  
                "MG9b59c60b5a9dd634c795ca5686f72d6b", 
                msg)      
            .create(); 
    }
}

