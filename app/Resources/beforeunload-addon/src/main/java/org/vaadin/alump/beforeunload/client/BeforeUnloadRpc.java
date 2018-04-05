/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.vaadin.alump.beforeunload.client;

import com.vaadin.shared.communication.ServerRpc;

/**
 *
 * @author shyam.d
 */
public interface BeforeUnloadRpc extends ServerRpc{
    void unload();
}
