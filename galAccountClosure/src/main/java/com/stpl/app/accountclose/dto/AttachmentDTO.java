/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author alok.v
 */
public class AttachmentDTO implements Serializable{
   
    private static final long serialVersionUID = -3123312853409349119L;

    
    private int docDetailsId;

  
    private String documentName;

    private Date dateAdded;

    
    private String userName;

  
    public int getDocDetailsId() {
        return docDetailsId;
    }

  
    public void setDocDetailsId(final int docDetailsId) {
        this.docDetailsId = docDetailsId;
    }

  
    public String getDocumentName() {
        return documentName;
    }

 
    public void setDocumentName(final String documentName) {
        this.documentName = documentName;
    }

  
    public Date getDateAdded() {
        return dateAdded;
    }

  
    public void setDateAdded(final Date dateAdded) {
        this.dateAdded = dateAdded;
    }

   
    public String getUserName() {
        return userName;
    }

  
    public void setUserName(final String userName) {
        this.userName = userName;
    }
}
