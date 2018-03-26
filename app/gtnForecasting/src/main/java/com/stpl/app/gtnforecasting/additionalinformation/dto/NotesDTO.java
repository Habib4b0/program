/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.gtnforecasting.additionalinformation.dto;

import com.vaadin.ui.Component;
import java.io.Serializable;

/**
 *
 * @author gopinath
 */
public class NotesDTO implements Serializable {
 /**
  *
  */
    private static final long serialVersionUID = 1L;

    private int docDetailsId;
    private String documentFullPath;
    private Component documentName;
    private String dateAdded;
    private String userName;
    private int userId;

    public NotesDTO() {
        super();
    }

    public int getDocDetailsId() {
        return docDetailsId;
    }

    public String getDocumentFullPath() {
        return documentFullPath;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setDocDetailsId(int docDetailsId) {
        this.docDetailsId = docDetailsId;
    }

    public void setDocumentFullPath(String documentFullPath) {
        this.documentFullPath = documentFullPath;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Component getDocumentName() {
        return documentName;
    }

    public void setDocumentName(Component documentName) {
        this.documentName = documentName;
    }

	

}

