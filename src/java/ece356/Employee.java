/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

/**
 *
 * @author Wojciech Golab
 */
public class Employee {  
    private int empID;
    private String empName;
    private String job;
    private int deptID;
    private int salary;

    public Employee(int empID, String empName, String job, int deptID, int salary) {
        this.empID = empID;
        this.empName = empName;
        this.job = job;
        this.deptID = deptID;
        this.salary = salary;
    }

    /**
     * Get the value of salary
     *
     * @return the value of salary
     */
    public int getSalary() {
        return salary;
    }

    /**
     * Set the value of salary
     *
     * @param salary new value of salary
     */
    public void setSalary(int salary) {
        this.salary = salary;
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


    /**
     * Get the value of job
     *
     * @return the value of job
     */
    public String getJob() {
        return job;
    }

    /**
     * Set the value of job
     *
     * @param job new value of job
     */
    public void setJob(String job) {
        this.job = job;
    }


    /**
     * Get the value of empName
     *
     * @return the value of empName
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * Set the value of empName
     *
     * @param empName new value of empName
     */
    public void setEmpName(String empName) {
        this.empName = empName;
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
