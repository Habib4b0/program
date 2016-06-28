/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.filemanagement.ui.form;

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
import com.stpl.app.adminconsole.itemgroup.util.UISecurityUtil;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ResponsiveUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

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

    FileManagementLogic logic = new FileManagementLogic();
    TextField itemNumber;
    TextField itemLookupName;
    CommonUtil commonUtil = new CommonUtil();
    FileMananagementResultDTO fileMgtDTO;
    /**
     * The customer group binder.
     */
    private ErrorfulFieldGroup binder;

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
    private BeanItemContainer<TrackingProcessDTO> summaryResultsBean = new BeanItemContainer<TrackingProcessDTO>(TrackingProcessDTO.class);
    /**
     * The selected results bean.
     */
    private BeanItemContainer<TrackingProcessDTO> detailsResultsBean = new BeanItemContainer<TrackingProcessDTO>(TrackingProcessDTO.class);
    private static final Logger LOGGER = Logger.getLogger(TrackingProgress.class);
    public static final Object[] SUMMARY_COLUMN = new Object[]{"processIdentifier", "startTime", "endTime", "noOfProjections", "status"};
    /**
     * The Constant FIELD_LOOKUP_HEADER.
     */
    public static final String[] SUMMARY_HEADER = new String[]{"Process Identifier", "Start Time", "End Time", "# of Projections", "Status"};
    public static final Object[] DETAILS_COLUMN = new Object[]{"processType", "startTime", "endTime", "noOfProjections", "sequence", "status"};
    /**
     * The Constant FIELD_LOOKUP_HEADER.
     */
    public static final String[] DETAILS_HEADER = new String[]{"Process Identifier", "Start Time", "End Time", "# of Projections", "Sequence", "Status"};
    CommonSecurityLogic commonSecurity = new CommonSecurityLogic();
    SessionDTO sessionDTO;
    public TrackingProgress(String name,final SessionDTO sessionDTO) {
        super(name);
        this.sessionDTO=sessionDTO;
    }

    public void init() {
      
            LOGGER.info("Entering init method");
            setContent(Clara.create(getClass().getResourceAsStream("/clara/trackingProgressLookUp.xml"), this));
            setId("COMPANY");
            addStyleName("bootstrap-ui");
            addStyleName("bootstrap");
            addStyleName("bootstrap-forecast bootstrap-nm");
            center();
            setClosable(true);
            setModal(true);
            setWidth(1750, Sizeable.Unit.PIXELS);
            setHeight("800px");
            configureFields();
            LOGGER.info("init method Ends");
        
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

            fieldIfpHM = stplSecurity.getBusinessFieldPermission(userId, UISecurityUtil.FILE_MANAGEMENT+","+UISecurityUtil.PROGRAM_TRACKING_LIST_VIEW);

            List<Object> resultList = commonUtil.getFieldsForSecurity(UISecurityUtil.FILE_MANAGEMENT, UISecurityUtil.PROGRAM_TRACKING_LIST_VIEW);
            Object[] objColumn = SUMMARY_COLUMN;
            TableResultCustom tableResultCustom = commonSecurity.getTableColumnsPermission(resultList, objColumn, fieldIfpHM, "Add");

            summaryTableLayout.addComponent(summaryTable);
            ResponsiveUtils.getResponsiveControls(summaryTableLogic.createControls(), summaryControlLayout);
            summaryTableLayout.addComponent(summaryControlLayout);

            summaryTableLogic.setContainerDataSource(summaryResultsBean);
            summaryTableLogic.setPageLength(10);
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

            fieldIfpHM = stplSecurity.getBusinessFieldPermission(userId, UISecurityUtil.FILE_MANAGEMENT+","+UISecurityUtil.PROGRAM_TRACKING_LIST_VIEW);

            List<Object> resultList = commonUtil.getFieldsForSecurity(UISecurityUtil.FILE_MANAGEMENT, UISecurityUtil.PROGRAM_TRACKING_LIST_VIEW);
            Object[] objColumn = DETAILS_COLUMN;
            TableResultCustom tableResultCustom = commonSecurity.getTableColumnsPermission(resultList, objColumn, fieldIfpHM, "Add");

            detailsTableLayout.addComponent(detailsTable);
            ResponsiveUtils.getResponsiveControls(detailsTableLogic.createControls(), detailsControlLayout);
            detailsTableLayout.addComponent(detailsControlLayout);

            detailsTableLogic.setContainerDataSource(summaryResultsBean);
            detailsTableLogic.setPageLength(10);
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
