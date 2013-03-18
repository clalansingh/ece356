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
    private String userid;
    
    public void createUser (String user, String user_type, String id) {
        username = user;
        type = user_type;
        userid = id;
    }
    
    public String getName() {
        return username;
    }
    
    public String getType() {
        return type;
    }
    
    public String getID() {
        return userid;
    }
}
