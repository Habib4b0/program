/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.cfp.ui.lazyload;

import com.stpl.app.global.cfp.dto.CFPCompanyDTO;
import com.stpl.app.global.cfp.logic.CFPSearchLogic;
import com.stpl.app.util.CommonLazyUtilDTO;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.ifs.ui.CustomePagedFilterTable;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItemContainer;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author KarthikeyanS
 */
public class CompanyDetailsContainer implements BeanDAO<CFPCompanyDTO> {

    public boolean editListFlag;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CompanyDetailsContainer.class);
    int pageLength;
    int pageNo;
    int nextPageStartIndex;
    int nextPageEndIndex;
    CustomePagedFilterTable table;
    BeanItemContainer<CFPCompanyDTO> companyDetailsResultSaveBean;
    final boolean viewModeFlag;
    String cfpMasterSystemId;
    String [] cfpDetails;
    String tabName;
    String record = StringUtils.EMPTY;
    
    
    /**
     * Method used for get Count.
     */
    public CompanyDetailsContainer(final CustomePagedFilterTable table, final Boolean viewModeFlag,String cfpMasterSystemId,String [] cfpDetails,String tab) {
        this.table = table;
        this.viewModeFlag = viewModeFlag;
        this.cfpMasterSystemId=cfpMasterSystemId;
        this.cfpDetails=cfpDetails;
        this.tabName=tab;
    }

    public CompanyDetailsContainer(CustomePagedFilterTable table, BeanItemContainer<CFPCompanyDTO> companyDetailsResultSaveBean) {
        this.table = table;
        this.companyDetailsResultSaveBean = companyDetailsResultSaveBean;
        this.viewModeFlag = false;
    }

    public int count(final BeanSearchCriteria searchCriteria) {
        try {
            if (viewModeFlag) {
               
                    return CFPSearchLogic.getLazyTempCfpDetailsCount(cfpMasterSystemId,searchCriteria,getRecord());
                } else {
            if (table.getData() == null) {
                    return 0;
            } else {
                CommonLazyUtilDTO dto = (CommonLazyUtilDTO) table.getData();
                List<Object[]> list = CFPSearchLogic.searchCompanyHelperTableSort(0, 0, dto,new ArrayList<OrderByColumn>(),searchCriteria,getRecord(),true);
                return Integer.valueOf(String.valueOf(!list.isEmpty() ? list.get(0) : 0));
            } 
            }

        } catch (PortalException ex) {
            LOGGER.error(ex);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004), new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        } catch (SystemException ex) {
            LOGGER.error(ex);
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {
                    /**
                     * The method is triggered when a button of the message box is
                     * pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        // Do Nothing
                    }
                }, ButtonId.OK);
                msg.getButton(ButtonId.OK).focus();
        } catch (Exception ex) {
            LOGGER.error(ex);
          final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {
                    /**
                     * The method is triggered when a button of the message box is
                     * pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        // Do Nothing
                    }
                }, ButtonId.OK);
                msg.getButton(ButtonId.OK).focus();
        }
        return 0;
    }

    /**
     * Method used for get the results.
     */
    public List<CFPCompanyDTO> find(final BeanSearchCriteria searchCriteria, final int startIndex, final int offset, final List<OrderByColumn> list) {
        try {
            LOGGER.debug("Entering AvailableCompanyAddition find method for third tab:");
            pageNo = table.getCurrentPage();
            pageLength = table.getPageLength();
            nextPageStartIndex = (pageNo - 1) * pageLength;
            nextPageEndIndex = ((pageNo - 1) * pageLength) + pageLength;
            if (viewModeFlag) {
                  if("Companies".equals(tabName)){
                 return CFPSearchLogic.getLazyTempCfpDetailsResults(startIndex , offset,cfpMasterSystemId,cfpDetails,list,searchCriteria,getRecord());
                  }
                  else{
                         return CFPSearchLogic.getLazyTempCfpDetailsResults( startIndex,offset,cfpMasterSystemId,cfpDetails,list,searchCriteria,getRecord());
                  }
            } else {
                if (companyDetailsResultSaveBean.size() > Constants.ZERO && CFPSearchLogic.saveToTempTable(companyDetailsResultSaveBean.getItemIds())) {
                    companyDetailsResultSaveBean.removeAllItems();
                }
                if (table.getData() == null) {
                    return null;
                } else {
                    CommonLazyUtilDTO dto = (CommonLazyUtilDTO) table.getData();
                   List<Object[]> list1 = CFPSearchLogic.searchCompanyHelperTableSort(startIndex, offset, dto,list,searchCriteria,getRecord(),false);
                   List<CFPCompanyDTO> finalList = new ArrayList<CFPCompanyDTO>();

                   return CFPSearchLogic.getCustomizedTempCFPCompanyDTO(list1, finalList, dto);
                }
            }
        } catch (PortalException ex) {
            LOGGER.error(ex);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004), new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        } catch (SystemException ex) {
            LOGGER.error(ex);
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {
                    /**
                     * The method is triggered when a button of the message box is
                     * pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        // Do Nothing
                    }
                }, ButtonId.OK);
                msg.getButton(ButtonId.OK).focus();
                }
        return null;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
    
    
}
