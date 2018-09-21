package com.stpl.app.adminconsole.discount.ui.view;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.discount.dto.DiscountSearchDTO;
import com.stpl.app.adminconsole.discount.logic.DiscountLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.stpl.app.adminconsole.discount.ui.form.DiscountAddForm;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.List;


/**
 * The Class DiscountAddView.
 */
public class DiscountAddView extends VerticalLayout implements View {

    /**
     * The Constant NAME.
     */
    public static final String NAME = "Add";
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscountAddView.class);

    /**
     * The selected results bean.
     */
    private BeanItemContainer<DiscountSearchDTO> resultsBean = new BeanItemContainer<>(DiscountSearchDTO.class);

    /**
     * The available availableRebate bean.
     */
    private BeanItemContainer<DiscountSearchDTO> availableResultsBean = new BeanItemContainer<>(DiscountSearchDTO.class);
    
    

    private SessionDTO sessionDTO;

    /**
     * Instantiates a new discount add view.
     */
    public DiscountAddView(SessionDTO sessionDTO) {
        super();
        LOGGER.debug("enter DiscountAddView constructor");
        this.sessionDTO = sessionDTO;
        setSpacing(true);
        setStyleName("bootstrap");
        LOGGER.debug("DiscountAddView constructor Ended");
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
     */
    @Override
    public void enter(final ViewChangeEvent event) {
        try {
            final DiscountLogic logic = new DiscountLogic();

            final int deductionGroupSId = sessionDTO.getSystemId();
            this.removeAllComponents();
            resultsBean.removeAllItems();
            availableResultsBean.removeAllItems();

            DiscountAddForm discountAddForm = new DiscountAddForm(resultsBean, availableResultsBean, sessionDTO);
            addComponent(discountAddForm);

             String pageName = sessionDTO.getLogic();
            if (pageName.equals("edit")) {
                List<DiscountSearchDTO> selectedRebate;
                selectedRebate = logic.getDeductionGroupDetails(deductionGroupSId);
                if (!selectedRebate.isEmpty()) {
                    resultsBean.addAll(selectedRebate);
                    DiscountSearchDTO deductionGroupDTO = logic.getDeductionGroupInfo(deductionGroupSId);
                    discountAddForm.groupInfo(deductionGroupDTO, BooleanConstant.getFalseFlag());
                }
            } else if (pageName.equals("view")) {
                List<DiscountSearchDTO> selectedRebate;
                selectedRebate = logic.getDeductionGroupDetails(deductionGroupSId);
                if (!selectedRebate.isEmpty()) {
                    resultsBean.addAll(selectedRebate);
                }
               DiscountSearchDTO deductionGroupDTO = logic.getDeductionGroupInfo(deductionGroupSId);
                discountAddForm.groupInfo(deductionGroupDTO, BooleanConstant.getFalseFlag());
            } else {
                discountAddForm.groupInfo(new DiscountSearchDTO(), BooleanConstant.getTrueFlag());
            }
        } catch (Exception ex) {
           LOGGER.error(ex.getMessage());
        }
    }

}
