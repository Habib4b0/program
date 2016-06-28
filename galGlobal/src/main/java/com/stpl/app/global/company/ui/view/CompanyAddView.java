/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.company.ui.view;

import com.stpl.app.global.company.dto.CompanyCrtIdentifierDTO;
import com.stpl.app.global.company.dto.CompanyMasterDTO;
import com.stpl.app.global.company.logic.CompanySearchLogic;
import com.stpl.app.global.company.ui.form.CompanyAddForm;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.VerticalLayout;
import java.util.logging.Level;
import org.jboss.logging.Logger;

/**
 *
 * @author shyam.duraipandian
 */
public class CompanyAddView extends VerticalLayout implements View {

    private static final Logger LOGGER = Logger.getLogger(CompanyAddView.class);
    public static String NAME = "ADD";
    /**
     * The company master dto.
     */
    private static CompanyMasterDTO companyMasterDTO = new CompanyMasterDTO();
    /**
     * The binder.
     */
    private ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<CompanyMasterDTO>(companyMasterDTO));
    /**
     * The identifier results bean.
     */
    private final BeanItemContainer<CompanyCrtIdentifierDTO> identifierResultsBean = new BeanItemContainer<CompanyCrtIdentifierDTO>(CompanyCrtIdentifierDTO.class);
    /**
     * The trade class results bean.
     */
    private BeanItemContainer<CompanyMasterDTO> tradeClassResultsBean = new BeanItemContainer<CompanyMasterDTO>(CompanyMasterDTO.class);
    /**
     * the parent company results bean.
     */
    private BeanItemContainer<CompanyMasterDTO> parentCompanyResultsBean = new BeanItemContainer<CompanyMasterDTO>(CompanyMasterDTO.class);
    CompanyAddForm companyAddFormForm;
    SessionDTO sessionDTO;

    public CompanyAddView(final SessionDTO sessionDTO) throws PortalException, SystemException, Exception {
        super();
        setStyleName("bootstrap-company");
        setSpacing(true);
        this.sessionDTO=sessionDTO;
        LOGGER.info("inside Add View");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        //To change body of generated methods, choose Tools | Templates.
        String mode = sessionDTO.getMode();
        try {

            if ((ConstantsUtils.ADD).equals(mode)) {
                this.removeAllComponents();
                companyAddFormForm =new CompanyAddForm(sessionDTO);
                addComponent(companyAddFormForm);
                companyAddFormForm.enableDisableAndInformationTab(mode);

            } else if ((ConstantsUtils.EDIT).equals(mode)) {

                this.removeAllComponents();

                final CompanySearchLogic companyLogic = new CompanySearchLogic();
                final int systemId = sessionDTO.getSystemId();
                companyMasterDTO = companyLogic.getCompanyMasterById(Integer.valueOf(systemId));
                binder = new ErrorfulFieldGroup(new BeanItem<CompanyMasterDTO>(companyMasterDTO));

                identifierResultsBean.removeAllItems();
                identifierResultsBean.addAll(companyMasterDTO.getCompanyIdentifierList());
                tradeClassResultsBean.removeAllItems();
                tradeClassResultsBean.addAll(companyLogic.getTradeClassTable(systemId));
                parentCompanyResultsBean.removeAllItems();
                parentCompanyResultsBean.addAll(companyLogic.getParentCompanyTable(systemId));
                companyAddFormForm = new CompanyAddForm(companyMasterDTO, binder, identifierResultsBean, tradeClassResultsBean, parentCompanyResultsBean, sessionDTO);
                addComponent(companyAddFormForm);
                binder.setItemDataSource(new BeanItem<CompanyMasterDTO>(companyMasterDTO));
                companyAddFormForm.enableDisableAndInformationTab(mode);
                this.setDefaultFocus();
            } else if ((ConstantsUtils.VIEW).equals(mode)) {
                markAsDirty();
                this.removeAllComponents();
                binder = new ErrorfulFieldGroup(new BeanItem<CompanyMasterDTO>(new CompanyMasterDTO()));
                final CompanySearchLogic companyLogic = new CompanySearchLogic();
                final int systemId = sessionDTO.getSystemId();
                LOGGER.info("System ID:-->" + systemId);
                companyMasterDTO = companyLogic.getCompanyMasterById(Integer.valueOf(systemId));

                identifierResultsBean.removeAllItems();
                identifierResultsBean.addAll(companyMasterDTO.getCompanyIdentifierList());
                tradeClassResultsBean.removeAllItems();
                tradeClassResultsBean.addAll(companyLogic.getTradeClassTable(systemId));
                parentCompanyResultsBean.removeAllItems();
                parentCompanyResultsBean.addAll(companyLogic.getParentCompanyTable(systemId));
                CompanyAddForm companyAddForm = new CompanyAddForm(companyMasterDTO, binder, identifierResultsBean, tradeClassResultsBean, parentCompanyResultsBean, sessionDTO);
                addComponent(companyAddForm);
                binder.setItemDataSource(new BeanItem<CompanyMasterDTO>(companyMasterDTO));
                companyAddForm.enableDisableAndInformationTab(mode);
                binder.setReadOnly(true);
                companyAddForm.viewModeConfig();
            }

        } catch (PortalException ex) {
                    
            java.util.logging.Logger.getLogger(CompanyAddView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(CompanyAddView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(CompanyAddView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setDefaultFocus() {
        binder.getField("companyId").focus();
    }
}
