/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.filemanagement.ui.form;

import java.util.List;
import java.util.Map;

import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author Nandhakumar
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.adminconsole.filemanagement.dto.FileMananagementResultDTO;
import com.stpl.app.adminconsole.filemanagement.dto.TrackingProcessDTO;
import com.stpl.app.adminconsole.filemanagement.logic.FileManagementLogic;
import com.stpl.app.adminconsole.filemanagement.logic.tablelogic.TrackingProgressTableLogic;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ResponsiveUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.TableResultCustom;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * The Class FileManagementLookup.
 *
 * @author Elangovan
 */
public class TrackingProgress extends Window {

    @UiField("fileType")
    private TextField fileType;
    
    @UiField("fileName")
    private TextField fileName;
    
    @UiField("country")
    private TextField country;
    
    @UiField("version")
    private TextField version;
    
    @UiField("effectiveDate")
    private TextField effectiveDate;
    
    @UiField("activeDateFrom")
    private TextField activeDateFrom;
    
    @UiField("activeDateTo")
    private TextField activeDateTo;
    
    @UiField("close")
    private Button close;
    
    @UiField("cssLayout")
    private CssLayout cssLayout;

    TrackingProgressTableLogic summaryTableLogic = new TrackingProgressTableLogic();
    private final ExtPagedTable summaryTable = new ExtPagedTable(summaryTableLogic);

    TrackingProgressTableLogic detailsTableLogic = new TrackingProgressTableLogic();
    private final ExtPagedTable detailsTable = new ExtPagedTable(detailsTableLogic);

    @UiField("summaryTableLayout")
    private VerticalLayout summaryTableLayout;

    @UiField("summaryControlLayout")
    private HorizontalLayout summaryControlLayout;
    
    @UiField("detailsTableLayout")
    private VerticalLayout detailsTableLayout;

    @UiField("detailsControlLayout")
    private HorizontalLayout detailsControlLayout;
    
    @UiField("businessUnit")
    private TextField businessUnit;

    private CommonUtil commonUtil = new CommonUtil();
    /**
     * The selected results excel export.
     */
    @UiField("excelExport")
    private Button excelExport;
    /**
     * The excel image.
     */
    private final ThemeResource excelImage = new ThemeResource("../../icons/excel.png");
    /**
     * The available results bean.
     */
    private BeanItemContainer<TrackingProcessDTO> summaryResultsBean = new BeanItemContainer<>(TrackingProcessDTO.class);
    private static final Logger LOGGER = Logger.getLogger(TrackingProgress.class);
    public final Object[] summaryColumn = new Object[]{"processIdentifier", "startTime", "endTime", "noOfProjections", "status"};
    /**
     * The Constant FIELD_LOOKUP_HEADER.
     */
    public final String[] summaryHeader = new String[]{"Process Identifier", "Start Time", "End Time", "# of Projections", "Status"};
    public final Object[] detailsColumn = new Object[]{"processType", "startTime", "endTime", "noOfProjections", "sequence", "status"};
    /**
     * The Constant FIELD_LOOKUP_HEADER.
     */
    public final String[] detailsHeader = new String[]{"Process Identifier", "Start Time", "End Time", "# of Projections", "Sequence", "Status"};
    private CommonSecurityLogic commonSecurity = new CommonSecurityLogic();
    private SessionDTO sessionDTO;
    
    public TrackingProgress(String name,final SessionDTO sessionDTO) {
        super(name);
        this.sessionDTO=sessionDTO;
    }

    public void init() {
      
            LOGGER.debug("Entering init method");
            setContent(Clara.create(getClass().getResourceAsStream("/clara/trackingProgressLookUp.xml"), this));
            setId("COMPANY");
            addStyleName("bootstrap-ui");
            addStyleName("bootstrap");
            addStyleName("bootstrap-forecast bootstrap-nm");
            center();
            setClosable(true);
            setModal(true);
            setWidth(NumericConstants.ONE_SEVEN_FIVE_ZERO, Sizeable.Unit.PIXELS);
            setHeight("800px");
            configureFields();
            LOGGER.debug("init method Ends");
        
    }

    /**
     * Method is used to add the search criteria to the window
     *
     * @return
     */
    private void addSummaryTable() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(sessionDTO.getUserId());
            final Map<String, AppPermission> fieldIfpHM;

            fieldIfpHM = stplSecurity.getBusinessFieldPermission(userId, CommonUtil.FILE_MANAGEMENT+","+CommonUtil.PROGRAM_TRACKING_LIST_VIEW);

            List<Object> resultList = commonUtil.getFieldsForSecurity(CommonUtil.FILE_MANAGEMENT, CommonUtil.PROGRAM_TRACKING_LIST_VIEW);
            Object[] objColumn = summaryColumn;
            TableResultCustom tableResultCustom = commonSecurity.getTableColumnsPermission(resultList, objColumn, fieldIfpHM, "Add");

            summaryTableLayout.addComponent(summaryTable);
            ResponsiveUtils.getResponsiveControls(summaryTableLogic.createControls(), summaryControlLayout);
            summaryTableLayout.addComponent(summaryControlLayout);

            summaryTableLogic.setContainerDataSource(summaryResultsBean);
            summaryTableLogic.setPageLength(NumericConstants.TEN);
            summaryTableLogic.sinkItemPerPageWithPageLength(false);
            summaryTable.markAsDirty();
            summaryTable.setFilterBarVisible(true);
            summaryTable.setVisibleColumns(tableResultCustom.getObjResult());
            summaryTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
            summaryTable.setWidth("100%");
            summaryTable.setHeight("300px");
            summaryTable.setStyleName("filtertable");
            summaryTable.setImmediate(true);
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Method is used to add the search criteria to the window
     *
     * @return
     */
    private void addDetailsTable() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(sessionDTO.getUserId());
            final Map<String, AppPermission> fieldIfpHM;

            fieldIfpHM = stplSecurity.getBusinessFieldPermission(userId, CommonUtil.FILE_MANAGEMENT+","+CommonUtil.PROGRAM_TRACKING_LIST_VIEW);

            List<Object> resultList = commonUtil.getFieldsForSecurity(CommonUtil.FILE_MANAGEMENT, CommonUtil.PROGRAM_TRACKING_LIST_VIEW);
            Object[] objColumn = detailsColumn;
            TableResultCustom tableResultCustom = commonSecurity.getTableColumnsPermission(resultList, objColumn, fieldIfpHM, "Add");

            detailsTableLayout.addComponent(detailsTable);
            ResponsiveUtils.getResponsiveControls(detailsTableLogic.createControls(), detailsControlLayout);
            detailsTableLayout.addComponent(detailsControlLayout);

            detailsTableLogic.setContainerDataSource(summaryResultsBean);
            detailsTableLogic.setPageLength(NumericConstants.TEN);
            detailsTableLogic.sinkItemPerPageWithPageLength(false);
            detailsTable.markAsDirty();
            detailsTable.setFilterBarVisible(true);
            detailsTable.setVisibleColumns(tableResultCustom.getObjResult());
            detailsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
            detailsTable.setWidth("100%");
            detailsTable.setImmediate(true);
            detailsTable.setHeight("300px");
            detailsTable.setStyleName("filtertable");

        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Method is Used to close the window
     */
    private void configureFields() {
        addSummaryTable();
        addDetailsTable();
        excelExport.setDescription(ConstantsUtils.EXCEL_EXPORT);
        excelExport.setIcon(excelImage);
        fileType.setEnabled(false);
        fileName.setEnabled(false);
        country.setEnabled(false);
        businessUnit.setEnabled(false);
        version.setEnabled(false);
        effectiveDate.setEnabled(false);
        activeDateFrom.setEnabled(false);
        activeDateTo.setEnabled(false);

        close.addClickListener(new Button.ClickListener() {
            /**
             * button click listener
             */
            public void buttonClick(final Button.ClickEvent event) {
                close();
            }
        });
    }
}
