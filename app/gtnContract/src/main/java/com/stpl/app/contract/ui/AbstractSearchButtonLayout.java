/**
 *
 */
package com.stpl.app.contract.ui;

import java.util.Map;

import org.jboss.logging.Logger;

import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.util.UISecurityUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.vaadin.addons.lazycontainer.LazyBeanItemContainer;

/**
 * The Class AbstractSearchButtonLayout.
 *
 * @author arankumar
 */
public abstract class AbstractSearchButtonLayout extends HorizontalLayout {

    /**
     * The binder.
     */
    private CustomFieldGroup binder;
    /**
     * The search resultbeans.
     */
    private LazyBeanItemContainer searchResultbeans;
    /**
     * The ok label.
     */
    private String okLabel;
    /**
     * The reset label.
     */
    private String resetLabel;
    /**
     * The function hm.
     */
    private Map<String, AppPermission> functionHM;
    /**
     * The Table.
     */
    private ExtFilterTable table;
    /**
     * Constants LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(AbstractSearchButtonLayout.class);
    
    /** The export button. */
    public String exportButton;

    /**
     * Gets the ok label.
     *
     * @return the ok label
     */
    public String getOkLabel() {
        return okLabel;
    }

    /**
     * Sets the ok label.
     *
     * @param okLabel the new ok label
     */
    public void setOkLabel(final String okLabel) {
        this.okLabel = okLabel;
    }

    /**
     * Gets the reset label.
     *
     * @return the reset label
     */
    public String getResetLabel() {
        return resetLabel;
    }

    /**
     * Sets the reset label.
     *
     * @param resetLabel the new reset label
     */
    public void setResetLabel(final String resetLabel) {
        this.resetLabel = resetLabel;
    }

    /**
     * Gets the table.
     *
     * @return the table
     */
    public ExtFilterTable getTable() {
        return table;
    }

    /**
     * Sets the table.
     *
     * @param table the new table
     */
    public void setTable(final ExtFilterTable table) {
        this.table = table;
    }

    /**
     * The Constructor for AbstractSearchButtonLayout .
     *
     * @param binder the binder
     * @param searchResultbeans the search resultbeans
     * @param table the table
     * @param okLabel the ok label
     * @param resetLabel the reset label
     * @param moduleName the module name
     */
    public AbstractSearchButtonLayout(final CustomFieldGroup binder, final LazyBeanItemContainer searchResultbeans, final ExtFilterTable table, final String okLabel, final String resetLabel,
            final String exportButton, final String moduleName) throws SystemException, PortalException {
        super();
        LOGGER.debug("Enters AbstractSearchButtonLayout ");
        this.binder = binder;
        this.searchResultbeans = searchResultbeans;
        this.okLabel = okLabel;
        this.resetLabel = resetLabel;
        this.exportButton = exportButton;
        this.functionHM = setHashMap(moduleName);
        this.table = table;
        init();
        LOGGER.debug("End of AbstractSearchButtonLayout ");
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    public CustomFieldGroup getBinder() {
        return binder;
    }

    /**
     * Sets the binder.
     *
     * @param binder the new binder
     */
    public void setBinder(final CustomFieldGroup binder) {
        this.binder = binder;
    }

    /**
     * Gets the search resultbeans.
     *
     * @return the search resultbeans
     */
    public LazyBeanItemContainer getSearchResultbeans() {
        return searchResultbeans;
    }

    /**
     * Sets the search resultbeans.
     *
     * @param searchResultbeans the new search resultbeans
     */
    public void setSearchResultbeans(final LazyBeanItemContainer searchResultbeans) {
        this.searchResultbeans = searchResultbeans;
    }

    /**
     * Gets the function hm.
     *
     * @return the function hm
     */
    public Map<String, AppPermission> getFunctionHM() {
        return functionHM;
    }

    /**
     * Sets the function hm.
     *
     * @param functionHM the function hm
     */
    public void setFunctionHM(final Map<String, AppPermission> functionHM) {
        this.functionHM = functionHM;
    }

    public String getExportButton() {
        return exportButton;
    }

    public void setExportButton(String exportButton) {
        this.exportButton = exportButton;
    }
    
    /**
     * Inits Method loads while calling constructor.
     */
    private void init() {
        LOGGER.debug("Enters init method ");
        this.setSpacing(true);
        if (((AppPermission) functionHM.get(okLabel)) != null && ((AppPermission) functionHM.get(okLabel)).isFunctionFlag()) {
            searchButton();
        }
        if (((AppPermission) functionHM.get(resetLabel)) != null && ((AppPermission) functionHM.get(resetLabel)).isFunctionFlag()) {
            resetButton();
        }
        LOGGER.debug("End of init method ");

    }

    /**
     * Sets the hash map.
     *
     * @param moduleName the hash map
     * @return the hash map< string, app permission>
     */
    public Map<String, AppPermission> setHashMap(final String moduleName) throws SystemException, PortalException {

        LOGGER.debug("Enters setHashMap method ");

        if (moduleName.equals(UISecurityUtil.CONTRACT_HEADER)) {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
            final Map<String, AppPermission> hashMap = stplSecurity.getBusinessFunctionPermission(userId, moduleName);
            LOGGER.debug("End of setHashMap method with hashMap size=" + hashMap.size());
            return hashMap;
        }
        if (moduleName.equals(UISecurityUtil.CONTRACT_DASHBOARD)) {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
            final Map<String, AppPermission> hashMap = stplSecurity.getBusinessFunctionPermission(userId, moduleName);
            LOGGER.debug("End of setHashMap method with hashMap size=" + hashMap.size());
            return hashMap;
        }
        return null;
    }

    /**
     * Search button.
     */
    private void searchButton() {
        LOGGER.debug("Enters searchButton method ");
        final Button btnSearch = new Button(okLabel);
        btnSearch.setErrorHandler(new ErrorHandler() {
            /**
             * Method used to Invoked when an error occurs.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });
        btnSearch.addClickListener(new ClickListener() {
            /**
             * Method used to search button logic and its listener.
             */
            public void buttonClick(final ClickEvent event) {
                try {
                    LOGGER.debug("Enters btnSearch click listener");
                    binder.getFields();
                    binder.commit();

                    searchResultbeans.removeAllItems();
                    LOGGER.debug("btnSearchLogic()");
                    btnSearchLogic();
                    LOGGER.debug("ends btnSearch click listener");
                } catch (CommitException e) {
                    LOGGER.error(e);
                } catch (PortalException ex) {
                    LOGGER.error(ex);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_2011));
                } catch (SystemException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    LOGGER.error(errorMsg);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                } 
            }
        });
        this.addComponent(btnSearch);
        LOGGER.debug(" searchButton method ends");
    }

    /**
     * Reset button.
     */
    private void resetButton() {
        LOGGER.debug("Entering resetButton()");
        final Button btnReset = new Button(resetLabel);
        btnReset.setErrorHandler(new ErrorHandler() {
            /**
             * Method used to Invoked when an error occurs.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());

            }
        });
        btnReset.addClickListener(new ClickListener() {
            /**
             * Method used to reset button logic and its listener.
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("Enters btnReset buttonClick()");
                MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to reset the page to default/previous values " + " ?", new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals("YES")) {
                            btnResetLogic();
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
                LOGGER.debug("Ends of btnReset buttonClick()");
            }
        });
        this.addComponent(btnReset);
        LOGGER.debug(" resetButton method ends");
    }

    /**
     * Call the Search logic here and searchResultbeans.addAll(<result List>);
     * Also implement the identifier , identifier type column hide unhide based
     * on the search type here
     *
     * @throws CommitException the commit exception
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    protected abstract void btnSearchLogic() throws CommitException, PortalException, SystemException;

    /**
     * To empty the Search contents provide searchResultbeans.removeAllItems();
     * Call either 1. binder.discard(); - to revert back to last valid values
     * (or) 2. binder.setItemDataSource(new BeanItem<SearchDTO>(new
     * SearchDTO())); - to empty the form
     */
    protected abstract void btnResetLogic();
    
    /**
	 * Abstract method to implement excel export logic.
	 *
	 * @throws StplR2Exception
	 *             the stpl r2 exception
	 */
    protected abstract void excelExportLogic() throws SystemException, PortalException;
}
