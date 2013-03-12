/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

/**
 *
 * @author Wojciech Golab
 */
public class Assigned {

    private int empID;
    private int projID;
    private String role;

    public Assigned(int empID, int projID, String role) {
        this.empID = empID;
        this.projID = projID;
        this.role = role;
    }

    /**
     * Get the value of role
     *
     * @return the value of role
     */
    public String getRole() {
        return role;
    }

    /**
     * Set the value of role
     *
     * @param role new value of role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Get the value of projID
     *
     * @return the value of projID
     */
    public int getProjID() {
        return projID;
    }

    /**
     * Set the value of projID
     *
     * @param projID new value of projID
     */
    public void setProjID(int projID) {
        this.projID = projID;
    }

    /**
     * Get the value of empID
     *
     * @return the value of empID
     */
    public int getEmpID() {
        return empID;
    }

    /**
     * Set the value of empID
     *
     * @param empID new value of empID
     */
    public void setEmpID(int empID) {
        this.empID = empID;
    }
}
