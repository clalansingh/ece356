/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

/**
 *
 * @author Wojciech Golab
 */
public class Project {

    private int projID;
    private String title;
    private int budget;
    private int funds;

    public Project(int projID, String title, int budget, int funds) {
        this.projID = projID;
        this.title = title;
        this.budget = budget;
        this.funds = funds;
    }

    /**
     * Get the value of funds
     *
     * @return the value of funds
     */
    public int getFunds() {
        return funds;
    }

    /**
     * Set the value of funds
     *
     * @param funds new value of funds
     */
    public void setFunds(int funds) {
        this.funds = funds;
    }

    /**
     * Get the value of budget
     *
     * @return the value of budget
     */
    public int getLimit() {
        return budget;
    }

    /**
     * Set the value of budget
     *
     * @param budget new value of budget
     */
    public void setLimit(int limit) {
        this.budget = limit;
    }

    /**
     * Get the value of title
     *
     * @return the value of title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the value of title
     *
     * @param title new value of title
     */
    public void setTitle(String title) {
        this.title = title;
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
}
