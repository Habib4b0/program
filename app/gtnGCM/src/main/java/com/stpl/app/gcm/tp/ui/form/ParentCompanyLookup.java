/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.ui.form;

import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.gcm.tp.dto.TradingPartnerDTO;
import com.stpl.app.gcm.tp.tablelogic.CompanySearchTableLogic;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyBeanItemContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author lokeshwari
 */
public class ParentCompanyLookup extends Window {

    @UiField("companyId")
    public TextField companyId;
    @UiField("companyNo")
    public TextField companyNo;
    @UiField("companyName")
    public TextField companyName;
    @UiField("companyStatus")
    public ComboBox companyStatus;
    @UiField("companyType")
    public ComboBox companyType;
    @UiField("searchBtn")
    public Button searchBtn;
    @UiField("resetBtn")
    public Button resetBtn;
    @UiField("selectBtn")
    public Button selectBtn;
    @UiField("companySearchTableLayout")
    public VerticalLayout companySearchTableLayout;
    private TextField parentno;
    private TextField parentName;

    private TradingPartnerDTO tpDTO;
    private int parentCompanySid;
    private TradingPartnerDTO tradingPartnerDto = new TradingPartnerDTO();
    private BeanItemContainer<TradingPartnerDTO> companyResultsContainer = new BeanItemContainer<>(TradingPartnerDTO.class);
    private LazyBeanItemContainer<TradingPartnerDTO> resultsLazyContainer;
    private CommonUtil commonUtil=CommonUtil.getInstance();
    /**
     * The data selection binder.
     */
    private CustomFieldGroup dataSelectionBinder = new CustomFieldGroup(new BeanItem<>(tradingPartnerDto));
    private final ErrorLabel errorMsg = new ErrorLabel();

    /**
     * The Constant LOGGER.
     */
    private final static Logger LOGGER = Logger.getLogger(ParentCompanyLookup.class);
    private CompanySearchTableLogic companyLogic = new CompanySearchTableLogic();
    private ExtPagedTable resultTable = new ExtPagedTable(companyLogic);

    public ParentCompanyLookup(TradingPartnerDTO tpDTO) {
        super("Parent Company Lookup");
        try {
            this.setTpDTO(tpDTO);
            init();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public ParentCompanyLookup(TextField parentno, TextField parentname, int parentCompanySid) {
        super("Parent Company Lookup");
        try {
            this.parentno = parentno;
            this.parentName = parentname;
            this.parentCompanySid = parentCompanySid;
            init();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Initialize the the form.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void init() {
        center();
        setClosable(true);
        setModal(true);
        setResizable(false);
        setWidth(NumericConstants.ONE_ZERO_FIVE_ZERO, Unit.PIXELS);
        setHeight("750px");
        setContent(Clara.create(getClass().getResourceAsStream("/TradingPartner/parentCompanyLookup.xml"), this));
        getBinder();
        configureFields();
    }

    protected void configureFields() {
        try {
            addStyleName("bootstrap-ui");
            addStyleName(Constants.BOOTSTRAP);
            addStyleName("bootstrap-forecast");
            addStyleName("bootstrap-nm");
            addStyleName("bootstrap-fe");
            companyId.focus();
            commonUtil.loadComboBox(companyType, UiUtils.COMPANY_TYPE, false);
            commonUtil.loadComboBox(companyStatus, UiUtils.STATUS, false);

            
            configureTable();
            companySearchTableLayout.addComponent(resultTable);
            HorizontalLayout hLayout;
            hLayout = companyLogic.createControls();
            companySearchTableLayout.addComponent(hLayout);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    public void configureTable() {
        resultTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        resultTable.setWidth(NumericConstants.NINTY_NINE, Unit.PERCENTAGE);
        resultTable.setHeight(NumericConstants.FOUR_HUNDRED, Unit.PIXELS);
        resultTable.setPageLength(NumericConstants.FIVE);
        companyLogic.setContainerDataSource(companyResultsContainer);
        resultTable.setVisibleColumns(Constants.getInstance().parentCompanySearchColumns);
        resultTable.setColumnHeaders(Constants.getInstance().parentCompanySearchHeaders);
        resultTable.setSelectable(true);
        resultTable.addStyleName("filterbar");
        resultTable.setFilterBarVisible(true);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultTable.setFilterGenerator(new ExtFilterGenerator() {

            @Override
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            @Override
            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                if (originatingField instanceof ComboBox) {
                    if (originatingField.getValue() != null) {
                        return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
                    } else {
                        return null;
                    }
                }
                return null;
            }

            @Override
            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                 try {
                if ("companyStatus".equals(propertyId)) {
                    ComboBox companyStatus = new ComboBox();
                    commonUtil.loadComboBox(companyStatus, UiUtils.STATUS, true);

                    return companyStatus;
                }
                if ("companyType".equals(propertyId)) {
                   
                        ComboBox companyType = new ComboBox();
                        commonUtil.loadComboBox(companyType, UiUtils.COMPANY_TYPE, true);

                        return companyType;
                    }
                }
                  catch (Exception ex) {
                       LOGGER.error(ex);
                    }
                return null;
            }

            @Override
            public void filterRemoved(Object propertyId) {
                return;
            }

            @Override
            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
                return;
            }

            @Override
            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }
        });
    }

    @UiHandler("searchBtn")
    public void searchBtnLogic(Button.ClickEvent event) {
        companyResultsContainer.removeAllItems();
        resultTable.setContainerDataSource(companyResultsContainer);
        resultTable.setVisibleColumns(Constants.getInstance().parentCompanySearchColumns);
        resultTable.setColumnHeaders(Constants.getInstance().parentCompanySearchHeaders);
        if (resultsLazyContainer != null) {
            resultsLazyContainer.removeAllItems();
        }
        resultTable.removeAllItems();
        if (StringUtils.isBlank(companyId.getValue()) && StringUtils.isBlank(companyNo.getValue()) && StringUtils.isBlank(companyName.getValue())
                && Constants.NULL.equalsIgnoreCase(String.valueOf(companyStatus.getValue())) && Constants.NULL.equalsIgnoreCase(String.valueOf(companyType.getValue()))) {
            AbstractNotificationUtils.getAlertNotification(Constants.MessageConstants.NO_SEARCH_CRITERIA_TITLE.getConstant(),
                    Constants.MessageConstants.NO_SEARCH_CRITERIA_BODY.getConstant());
        } else {
            try {
                dataSelectionBinder.commit();
                companyLogic.loadSetData(tradingPartnerDto, StringUtils.EMPTY,StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY);
                if (!companyLogic.isRecordPresent()) {
                    AbstractNotificationUtils.getErrorNotification("No Results Found",
                            "There are no results that match the search criteria. Please try again.");
                } else {
                    CommonUIUtils.getMessageNotification("Search Completed");
                }
            } catch (FieldGroup.CommitException commit) {
                LOGGER.error(commit);
            }
        }
    }

    private CustomFieldGroup getBinder() {
        dataSelectionBinder.bindMemberFields(this);
        dataSelectionBinder.setItemDataSource(new BeanItem<>(tradingPartnerDto));
        dataSelectionBinder.setBuffered(true);
        dataSelectionBinder.setErrorDisplay(errorMsg);
        return dataSelectionBinder;
    }

    @UiHandler("resetBtn")
    public void resetBtnLogic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to reset the page to default/previous values " + " ?", new MessageBoxListener() {
            /**
             * Called when a Button has been clicked .
             *
             */
            @SuppressWarnings("PMD")
            @Override
            public void buttonClicked(final ButtonId buttonId) {
                if (buttonId.name().equals("YES")) {
                    companyId.setValue(StringUtils.EMPTY);
                    companyNo.setValue(StringUtils.EMPTY);
                    companyName.setValue(StringUtils.EMPTY);
                    companyStatus.setValue(Constants.SELECT_ONE);
                    companyType.setValue(Constants.SELECT_ONE);
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    @UiHandler("selectBtn")
    public void selectBtnLogic(Button.ClickEvent event) {
        if (resultTable.getValue() != null) {
            tradingPartnerDto = (TradingPartnerDTO) resultTable.getValue();
            parentno.setValue(tradingPartnerDto.getCompanyNo());
            parentName.setValue(tradingPartnerDto.getCompanyName());
            parentCompanySid = !StringUtils.EMPTY.equals(tradingPartnerDto.getCompanySystemId()) ? Integer.parseInt(tradingPartnerDto.getCompanySystemId()) : 0;

            close();
        } else {
            AbstractNotificationUtils.getErrorNotification("No record selected", "Please select a Company. Then try again.");
        }

    }

    public int getParentCompanySid() {
        return parentCompanySid;
    }

    public void setParentCompanySid(int parentCompanySid) {
        this.parentCompanySid = parentCompanySid;
    }

	public TradingPartnerDTO getTpDTO() {
		return tpDTO;
	}

	public void setTpDTO(TradingPartnerDTO tpDTO) {
		this.tpDTO = tpDTO;
	}

}
