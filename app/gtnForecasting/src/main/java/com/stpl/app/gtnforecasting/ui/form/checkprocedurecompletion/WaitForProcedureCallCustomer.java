/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui.form.checkprocedurecompletion;

import com.stpl.app.gtnforecasting.logic.DataSelectionLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.util.service.thread.ThreadPool;
import java.util.concurrent.Future;

/**
 *
 * @author mekalai.madhappa
 */
public class WaitForProcedureCallCustomer implements WaitForCustomerProcedureCompletion {

    private final SessionDTO session;
    public ThreadPool waitCustomerThreadPool = ThreadPool.getInstance();

    public WaitForProcedureCallCustomer(SessionDTO session) {
        this.session = session;
    }

    @Override
    public void waitforStatusTableCOmpletion() {
        session.addFutureMap(Constant.CUSTOMER_VIEW_DISCOUNT_POPULATION_CALL,
                new Future[]{waitCustomerThreadPool.submitRunnable(createRunnable(Constant.PRC_VIEWS_CALL,
                                    Constant.CUSTOMER_VIEW_DISCOUNT_POPULATION_CALL, session.getFunctionMode(), Constant.DISCOUNT3, "C", "null", "null", session))});
    }

    private Runnable createRunnable(final Object... inputs) {
        return new Runnable() {

            @Override
            public void run() {
                CommonUtil.getInstance().waitsForOtherThreadsToComplete(session.getFutureValue("Check_Customer"));
                Thread.currentThread().setName(inputs[1].toString());
                new DataSelectionLogic().callViewInsertProcedureForNm((SessionDTO) inputs[7], inputs[3].toString(), inputs[4].toString(), inputs[5].toString());
            }
        };

    }

}
