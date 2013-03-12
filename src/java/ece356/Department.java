/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

/**
 *
 * @author Wojciech Golab
 */
public class Department {

    private int deptID;
    private String deptName;
    private String location;

    public Department(int deptID, String deptName, String location) {
        this.deptID = deptID;
        this.deptName = deptName;
        this.location = location;
    }

    /**
     * Get the value of location
     *
     * @return the value of location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Set the value of location
     *
     * @param location new value of location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Get the value of deptName
     *
     * @return the value of deptName
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * Set the value of deptName
     *
     * @param deptName new value of deptName
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * Get the value of deptID
     *
     * @return the value of deptID
     */
    public int getDeptID() {
        return deptID;
    }

    /**
     * Set the value of deptID
     *
     * @param deptID new value of deptID
     */
    public void setDeptID(int deptID) {
        this.deptID = deptID;
    }
}
