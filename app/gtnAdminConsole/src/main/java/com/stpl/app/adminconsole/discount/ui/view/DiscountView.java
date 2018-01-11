package com.stpl.app.adminconsole.discount.ui.view;

import com.stpl.app.adminconsole.discount.dto.DiscountSearchDTO;
import com.stpl.app.adminconsole.discount.logic.DiscountLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.stpl.app.adminconsole.discount.ui.form.DiscountViewForm;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class DiscountView.
 */
public class DiscountView extends VerticalLayout implements View{
	/**
     * The Constant NAME.
     */
    public static final String NAME = "View";
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscountView.class);
    
      /**
     * The selected results bean.
     */
    private BeanItemContainer<DiscountSearchDTO> resultsBean = new BeanItemContainer<>(DiscountSearchDTO.class);
    
     /**
     * The DiscountLogic.
     */
    DiscountLogic logic=new DiscountLogic();
    
        /**
     * The itemGroupDTO.
     */
    private DiscountSearchDTO deductionGroupDTO = new DiscountSearchDTO(); 
    
        /**
     * The Discount Add form.
     */
    private DiscountViewForm discountViewForm=new DiscountViewForm();
    
    /**
     * Instantiates a new discount view.
     */
    public DiscountView() {
    	 super();
    	 LOGGER.debug("enter DiscountView constructor");
         setSpacing(true);
         this.discountViewForm=new DiscountViewForm(resultsBean);
         addComponent(discountViewForm);
         setStyleName("bootstrap");
         LOGGER.debug("DiscountView constructor Ended");
	}

	/** (non-Javadoc)
	 * @see com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
	 */
    @Override
    public void enter(final ViewChangeEvent event) {
        // TODO Auto-generated method stub
        try {
            final DiscountLogic logic = new DiscountLogic();
            final int deductionGroupSId = (Integer) VaadinSession.getCurrent().getAttribute(ConstantsUtils.SYS_ID);
            resultsBean.removeAllItems();
            String pageName = String.valueOf(VaadinSession.getCurrent().getAttribute("logic"));
            if (pageName.equals("view")) {
                List<DiscountSearchDTO> selectedRebate;
                selectedRebate = logic.getDeductionGroupDetails(deductionGroupSId);
                if (!selectedRebate.isEmpty()) {
                    resultsBean.addAll(selectedRebate);
                }
                deductionGroupDTO = logic.getDeductionGroupInfo(deductionGroupSId);
                discountViewForm.groupInfo(deductionGroupDTO);
            }
        } catch (SystemException ex) {
           LOGGER.error(ex.getMessage());
        } catch (PortalException ex) {
           LOGGER.error(ex.getMessage());
        } catch (Exception ex) {
           LOGGER.error(ex.getMessage());
        }
    }
}