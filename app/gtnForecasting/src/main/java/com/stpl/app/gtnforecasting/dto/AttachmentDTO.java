/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dto;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class AttachmentDTO.
 *
 * @author lokeshwari
 */
public class AttachmentDTO implements Serializable {

    public AttachmentDTO() {
    }

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
    private String documentName;

    /**
     * The date added.
     */
    private Date dateAdded;

    /**
     * The user name.
     */
    private String userName;

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
    public String getDocumentName() {
        return documentName;
    }

    /**
     * setter method for documentName field .
     *
     * @param documentName .
     */
    public void setDocumentName(final String documentName) {
        this.documentName = documentName;
    }

    /**
     * getter for dateAdded field .
     *
     * @return dateAdded
     */
    public Date getDateAdded() {
        return dateAdded == null ? null : (Date) dateAdded.clone();
    }

    /**
     * setter method for dateAdded field .
     *
     * @param dateAdded the date added
     */
    public void setDateAdded(final Date dateAdded) {
        this.dateAdded = dateAdded == null ? null : (Date) dateAdded.clone();
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
}
