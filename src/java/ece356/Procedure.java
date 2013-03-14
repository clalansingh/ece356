/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

import java.sql.Date;

/**
 *
 * @author Brandon
 */
public class Procedure {
    int procedureID;
    String operation;
    Date date;
    
    public Procedure (int procedureID, String operation, Date date)
    {
        this.procedureID = procedureID;
        this.operation = operation;
        this.date = date;
    }
    
    public int getProcedureID() {
        return procedureID;
    }
    
    public void setProcedureID(int procedureID)
    {
        this.procedureID = procedureID;
    }
    
    public String getOperation()
    {
        return operation;
    }
    
    public void setOperation(String operation)
    {
        this.operation = operation;
    }
    
    public Date getDate()
    {
        return date;
    }
    
    public void setDate(Date date)
    {
        this.date = date;
        
    }
}
