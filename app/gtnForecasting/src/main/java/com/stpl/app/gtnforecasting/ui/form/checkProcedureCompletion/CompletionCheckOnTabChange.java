/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui.form.checkProcedureCompletion;

import com.stpl.app.util.service.thread.ThreadPool;
import java.util.concurrent.ExecutorService;

/**
 *
 * @author mekalai.madhappa
 */
public interface CompletionCheckOnTabChange {
    public ExecutorService service = ThreadPool.getInstance().getService();

    public void checkProcedureCompletion();
    
}
