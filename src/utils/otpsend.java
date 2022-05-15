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

public static final String ACCOUNT_SID = "AC5e973cfeb8e1a9c3bdea6396f3bbfae5"; 
    public static final String AUTH_TOKEN = "fd26bae679d3799eb208b1779a1e36e1"; 

    public static void sendSms(String msg,String num) {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = null;

              message = Message.creator(
                    new com.twilio.type.PhoneNumber(num),//To
                    new com.twilio.type.PhoneNumber("+19704786402"), //From
                    msg)
                    .create();   
                 
                 
                 
                 
                 
    }
}

