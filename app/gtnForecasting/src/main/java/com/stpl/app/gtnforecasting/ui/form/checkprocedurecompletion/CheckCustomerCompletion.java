/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui.form.checkprocedurecompletion;

import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.serviceUtils.Constants;
import com.stpl.app.util.service.thread.ThreadPool;
import java.util.concurrent.Future;

/**
 *
 * @author mekalai.madhappa
 */
public class CheckCustomerCompletion implements CompletionCheckOnTabChange {

    public ThreadPool customerThreadPool = ThreadPool.getInstance();
    private final SessionDTO session;

    public CheckCustomerCompletion(SessionDTO session) {
        this.session = session;
    }

    @Override
    public void checkProcedureCompletion() {
        session.addFutureMap("Check_Customer", new Future[]{customerThreadPool.submitRunnable(
            createRunnableCustomer())});
    }

    private Runnable createRunnableCustomer() {
        return new Runnable() {

            @Override
            public void run() {
                Thread.currentThread().setName("Check_Customer");
                CommonUtil.getInstance().isProcedureCompleted("Discount", Constants.CUSTOMER, session);
            }
        };

    }

}
