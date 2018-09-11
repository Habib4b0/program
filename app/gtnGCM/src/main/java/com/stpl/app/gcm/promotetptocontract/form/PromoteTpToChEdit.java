/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.form;

import com.stpl.app.gcm.promotetptocontract.dto.PromoteTpToChDto;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.ui.VerticalLayout;
import org.asi.ui.extfilteringtable.ExtFilterTable;

/**
 *
 * @author alok.v
 */
public class PromoteTpToChEdit extends VerticalLayout {

    /**
     * View name for navigation.
     */
    public static final String NAME = "PROMOTETP";
    /**
     * DTO object for DataSelection.
     */
    private PromoteTpToChDto promoteTpToChDto = new PromoteTpToChDto();
    /**
     * Binder for DataSelection.
     */
    private final ErrorfulFieldGroup promoteTpToChBinder = new ErrorfulFieldGroup(new BeanItem<>(promoteTpToChDto));

    /**
     *
     * @param session
     * @param promoteTpToChDto
     * @param editWindow
     * @param resultTable
     */
    public PromoteTpToChEdit(final SessionDTO session, final PromoteTpToChDto promoteTpToChDto, final PromoteTpToChWindow editWindow, final ExtFilterTable resultTable) {
        this.promoteTpToChDto = promoteTpToChDto;
        PromoteTPToChForm promoteTPToChForm = new PromoteTPToChForm(session, editWindow, resultTable);
        addComponent(promoteTPToChForm);
        enter();
    }

    /**
     * Enter Method
     *
     */
    public final void enter() {
        promoteTpToChBinder.setItemDataSource(new BeanItem<>(promoteTpToChDto));
 
    }

}
