package org.worldline.assignment.perfectnumbers.security;

import java.io.IOException;
import java.util.Base64;

public class BasicAuthAccess {

    /**
     * basic auth. This will be later changed to google auth2
     * @param auth
     * @return
     */
   public boolean isAuthenticated( String auth) {
       String decodeStr = "";
       String authInfo = auth.split("\\s+")[1];
       byte[] bytes = null;

       bytes = Base64.getDecoder().decode(authInfo);
       decodeStr = new String(bytes);
       String [] details = decodeStr.split(":");
       String username = details[0];
       String password = details[1];

       return authenticate(username, password);

   }


    /**
     * This method should get the details from database later. Values are hardcoded.
     * @param username
     * @param password
     * @return
     */
    public boolean authenticate( String username, String password ) {
       if (username.equals("username") && password.equals("password")) { // username and password should get from the db
          // return true;
       }
       return true; //
    }
}
