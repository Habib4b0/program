/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui.form.checkprocedurecompletion;

import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.serviceutils.Constants;
import com.stpl.app.util.service.thread.ThreadPool;
import java.util.concurrent.Future;

/**
 *
 * @author mekalai.madhappa
 */
public class CheckCustomCompletion implements CompletionCheckOnTabChange {
    private ThreadPool threadPool = ThreadPool.getInstance();
    private final SessionDTO session;

    public CheckCustomCompletion(SessionDTO session) {
        this.session = session;
    }

    @Override
    public void checkProcedureCompletion() {
        session.addFutureMap("Check_Custom", new Future[]{threadPool.submitRunnable(createRunnableCustom())});
    }

    private Runnable createRunnableCustom() {
        return new Runnable() {

            @Override
            public void run() {
                Thread.currentThread().setName("Check_Custom");
                CommonUtil.getInstance().isProcedureCompleted("Discount", Constants.CUSTOM, session);
            }
        };
    }

}
