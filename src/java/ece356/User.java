/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

/**
 *
 * @author chrislalansingh
 */
public class User {
    private String username;
    private String type;
    
    public void createUser (String user, String user_type) {
        username = user;
        type = user_type;
    }
    
    public String getName() {
        return username;
    }
    
    public String getType() {
        return type;
    }
}
