/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.exception;

/**
 *
 * @author Satheesh.Nagendran
 */
public class AccessDeniedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

   public  AccessDeniedException(String cause) {
        super(cause);
    }
}