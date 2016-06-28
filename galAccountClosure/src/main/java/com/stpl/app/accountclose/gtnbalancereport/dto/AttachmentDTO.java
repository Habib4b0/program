/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.dto;

import java.io.Serializable;

/**
 *
 * @author santanukumar
 */
public class AttachmentDTO implements Serializable {

    private static final long serialVersionUID = -3123312853409349119L;

    private int docDetailsId;

    private String documentName;

    private String dateAdded;

    private String userName;
    private String documentFullPath;

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

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(final String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getUserName() {
        return userName;
    }

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
