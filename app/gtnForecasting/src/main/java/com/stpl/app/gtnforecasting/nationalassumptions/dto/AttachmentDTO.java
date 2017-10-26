/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.dto;

import com.vaadin.ui.Component;
import java.io.Serializable;


/**
 * The Class AttachmentDTO.
 *
 * @author Vinodhini
 */
public class AttachmentDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -3123312853409349119L;
    /**
     * The doc details id.
     */
    private int docDetailsId;
    /**
     * The document name.
     */
    private Component documentName;
    /**
     * The date added.
     */
    private String dateAdded;
    /**
     * The user name.
     */
    private String userName;
    private String documentFullPath;
    /**
     * getter for docDetailsId field .
     *
     * @return docDetailsId .
     */
    public int getDocDetailsId() {
        return docDetailsId;
    }

    /**
     * setter method for docDetailsId field .
     *
     * @param docDetailsId .
     */
    public void setDocDetailsId(final int docDetailsId) {
        this.docDetailsId = docDetailsId;
    }

    /**
     * getter for documentName field .
     *
     * @return documentName
     */
    public Component getDocumentName() {
        return documentName;
    }

    /**
     * setter method for documentName field .
     *
     * @param documentName .
     */
    public void setDocumentName(final Component documentName) {
        this.documentName = documentName;
    }

    /**
     * getter for dateAdded field .
     *
     * @return dateAdded
     */
    public String getDateAdded() {
        return dateAdded;
    }

    /**
     * setter method for dateAdded field .
     *
     * @param dateAdded the date added
     */
    public void setDateAdded(final String dateAdded) {
        this.dateAdded = dateAdded;
    }

    /**
     * getter for userName field .
     *
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * setter method for UserName field .
     *
     * @param userName the user name
     */
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getDocumentFullPath() {
        return documentFullPath;
    }

    public void setDocumentFullPath(String documentFullPath) {
        this.documentFullPath = documentFullPath;
    }
    
}
