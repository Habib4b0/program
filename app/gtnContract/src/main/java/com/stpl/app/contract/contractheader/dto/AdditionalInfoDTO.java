/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.contractheader.dto;

import java.io.Serializable;

/**
 *
 * @author sooriya.lakshmanan
 */
public class AdditionalInfoDTO implements Serializable {

    private int docDetailsId;
    private String documentName;
    private String dateAdded;
    private String userName;
    private String documentFullPath;
    private int userId;

    public int getDocDetailsId() {
        return docDetailsId;
    }

    public void setDocDetailsId(int docDetailsId) {
        this.docDetailsId = docDetailsId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDocumentFullPath() {
        return documentFullPath;
    }

    public void setDocumentFullPath(String documentFullPath) {
        this.documentFullPath = documentFullPath;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }    
}
