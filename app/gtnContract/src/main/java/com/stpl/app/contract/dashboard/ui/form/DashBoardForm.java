package com.stpl.app.contract.dashboard.ui.form;

import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import org.vaadin.addons.lazycontainer.LazyBeanItemContainer;
import org.vaadin.addons.lazycontainer.LazyContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.contract.abstractsearch.util.ConstantUtil;
import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.common.util.CommonUtil;
import com.stpl.app.contract.contractheader.logic.ContractHeaderLogic;
import com.stpl.app.contract.contractheader.util.CommonUtils;
import com.stpl.app.contract.contractheader.util.UIUtils;
import com.stpl.app.contract.dashboard.dto.ContractMember;
import com.stpl.app.contract.dashboard.dto.DashBoardSearchDto;
import com.stpl.app.contract.dashboard.dto.DashboardCriteria;
import com.stpl.app.contract.dashboard.dto.DashboardSearchDAO;
import com.stpl.app.contract.dashboard.dto.DetailComponentFilterGenerator;
import com.stpl.app.contract.dashboard.dto.DetailSearchDTO;
import com.stpl.app.contract.dashboard.dto.DetailTableCriteria;
import com.stpl.app.contract.dashboard.dto.DetailTableDAO;
import com.stpl.app.contract.dashboard.dto.LeftSearchComponentDTO;
import com.stpl.app.contract.dashboard.dto.RightSearchComponentDTO;
import com.stpl.app.contract.dashboard.dto.SearchComponentFilterGenerator;
import com.stpl.app.contract.dashboard.logic.ContractDashboardLogic;
import com.stpl.app.contract.dashboard.logic.DashBoardLogic;
import com.stpl.app.contract.dashboard.logic.DashboardComponentSearchLogic;
import com.stpl.app.contract.dashboard.ui.view.DashboardEditView;
import com.stpl.app.contract.dashboard.util.ContractUtils;
import com.stpl.app.contract.dashboard.util.CustomTreeContainer;
import com.stpl.app.contract.dashboard.util.ExcelExportUtil;
import com.stpl.app.contract.global.lazyload.BrandNameCriteria;
import com.stpl.app.contract.global.lazyload.BrandNameDAO;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.CHFunctionNameUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;
import com.stpl.app.contract.util.ResponsiveUtils;
import com.stpl.app.model.ContractMaster;
import com.stpl.app.model.PsDetails;
import com.stpl.app.model.RsDetails;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.ContractMasterLocalServiceUtil;
import com.stpl.domain.contract.base.SearchBaseForm;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.UISecurityUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.data.util.converter.StringToIntegerConverter;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.DataBoundTransferable;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.event.dd.acceptcriteria.Not;
import com.vaadin.event.dd.acceptcriteria.Or;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.dd.VerticalDropLocation;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.AbstractSelect.VerticalLocationIs;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable.TableDragMode;
import com.vaadin.ui.ExtCustomTreeTable;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Tree.CollapseEvent;
import com.vaadin.ui.Tree.CollapseListener;
import com.vaadin.ui.Tree.ExpandEvent;
import com.vaadin.ui.Tree.ExpandListener;
import com.vaadin.ui.Tree.TreeDragMode;
import com.vaadin.ui.Tree.TreeTargetDetails;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.themes.Reindeer;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.jboss.logging.Logger;

/**
 * The Class DashBoardForm contains all the UI components and its configurations
 * in .
 */
public final class DashBoardForm extends CustomComponent implements SearchBaseForm {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(DashBoardForm.class);

    private static final String TREE_HEIGHT = "30px";

    /**
     * The error msg.
     */
    @UiField("errorLB")
    private ErrorLabel errorMsg;

    @UiField("mainLayout")
    private VerticalLayout mainLayout;

    @UiField("searchBtn")
    private Button btnSearch;

    @UiField("resetBtn")
    private Button resetbtn;

    /**
     * The table.
     */
    @UiField("componentSearchResultsTable")
    private ExtFilterTable table;

    @UiField("detailPanel")
    private Panel detailPanel;

    @UiField("componentSearchDetailsTable")
    private ExtFilterTable detailsTable;

    @UiField("addToTreeBtn")
    private Button addToTreeBtn;

    @UiField("resultsExport")
    private Button resultsExportBtn;

    @UiField("dashBoardTree")
    private Tree tree;

    @UiField("saveTreeBtn")
    private Button treeSaveBtn;

    @UiField("removeTreeBtn")
    private Button treeRemoveBtn;

    @UiField("processLayout")
    private VerticalLayout processedLayout;

    @UiField("processSearchBtn")
    private Button processedbtnSearch;

    @UiField("processResetBtn")
    private Button lresetbtn;

    @UiField("processTreeTable")
    private TreeTable processTreeTable;

    @UiField("processBtn")
    private Button processedbtn;

    @UiField("treeButtonLayout")
    private HorizontalLayout treeButtonLayout;

    @UiField("rebuildBtn")
    private Button editbtn;

    @UiField("processExportBtn")
    private Button excelExport;

    @UiField("processSearchLayout")
    private HorizontalLayout processSearchLayout;

    @UiField("componentSearchLayout")
    private HorizontalLayout componentSearchLayout;

    @UiField("processResultsLayout")
    private HorizontalLayout processResultsLayout;

    @UiField("verticalSplitPanel")
    private VerticalSplitPanel verticalSplitPanel;

    @UiField("paginationLayout")
    private HorizontalLayout paginationLayout;

    @UiField("leftSearchType")
    private OptionGroup leftSearchType;

    @UiField("leftComponent")
    private ComboBox leftComponent;

    @UiField("leftContractId")
    private TextField leftContractId;

    @UiField("leftContractNo")
    private TextField leftContractNo;

    @UiField("leftContractName")
    private TextField leftContractName;

    @UiField("leftContractType")
    private ComboBox leftContractType;

    @UiField("leftContractStartDate")
    private PopupDateField leftContractStartDate;

    @UiField("leftContractEndDate")
    private PopupDateField leftContractEndDate;

    @UiField("leftContractHolderNo")
    private TextField leftContractHolderNo;

    @UiField("leftContractHolderName")
    private TextField leftContractHolderName;

    @UiField("leftCfpId")
    private TextField leftCfpId;

    @UiField("leftCfpNo")
    private TextField leftCfpNo;

    @UiField("leftCfpName")
    private TextField leftCfpName;

    @UiField("leftCfpType")
    private ComboBox leftCfpType;

    @UiField("leftCompanyNo")
    private TextField leftCompanyNo;

    @UiField("leftCompanyName")
    private TextField leftCompanyName;

    @UiField("leftCompanyType")
    private ComboBox leftCompanyType;

    @UiField("leftCompanyCategory")
    private TextField leftCompanyCategory;

    @UiField("leftIfpId")
    private TextField leftIfpId;

    @UiField("leftIfpNo")
    private TextField leftIfpNo;

    @UiField("leftIfpName")
    private TextField leftIfpName;

    @UiField("leftIfpType")
    private ComboBox leftIfpType;

    @UiField("leftIfpItemNo")
    private TextField leftIfpItemNo;

    @UiField("leftIfpItemName")
    private TextField leftIfpItemName;

    @UiField("leftIfpTherapeuticClass")
    private ComboBox leftIfpTherapeuticClass;

    @UiField("leftIfpBrandName")
    private ComboBox leftIfpBrandName;

    @UiField("leftPsId")
    private TextField leftPsId;

    @UiField("leftPsNo")
    private TextField leftPsNo;

    @UiField("leftPsName")
    private TextField leftPsName;

    @UiField("leftPsType")
    private ComboBox leftPsType;

    @UiField("leftPsItemNo")
    private TextField leftPsItemNo;

    @UiField("leftPsItemName")
    private TextField leftPsItemName;

    @UiField("leftPsTherapeuticClass")
    private ComboBox leftPsTherapeuticClass;

    @UiField("leftPsBrandName")
    private ComboBox leftPsBrandName;

    @UiField("leftRsId")
    private TextField leftRsId;

    @UiField("leftRsNo")
    private TextField leftRsNo;

    @UiField("leftRsName")
    private TextField leftRsName;

    @UiField("leftRsType")
    private ComboBox leftRsType;

    @UiField("leftRsItemNo")
    private TextField leftRsItemNo;

    @UiField("leftRsItemName")
    private TextField leftRsItemName;

    @UiField("leftRsBrandName")
    private ComboBox leftRsBrandName;

    @UiField("leftRsProgramCategory")
    private ComboBox leftRsProgramCategory;

    @UiField("contractGridLayout")
    private GridLayout contractGridLayout;

    @UiField("cfpGridLayout")
    private GridLayout cfpGridLayout;

    @UiField("ifpGridLayout")
    private GridLayout ifpGridLayout;

    @UiField("psGridLayout")
    private GridLayout psGridLayout;

    @UiField("rsGridLayout")
    private GridLayout rsGridLayout;

    @UiField("contractDetailGridLayout")
    private GridLayout contractDetailGridLayout;

    @UiField("cfpDetailGridLayout")
    private GridLayout cfpDetailGridLayout;

    @UiField("ifpDetailGridLayout")
    private GridLayout ifpDetailGridLayout;

    @UiField("psDetailGridLayout")
    private GridLayout psDetailGridLayout;

    @UiField("rsDetailGridLayout")
    private GridLayout rsDetailGridLayout;

    @UiField("rightSearchType")
    private OptionGroup rightSearchType;

    @UiField("rightComponent")
    private ComboBox rightComponent;

    @UiField("rightContractId")
    private TextField rightContractId;

    @UiField("rightContractNo")
    private TextField rightContractNo;

    @UiField("rightContractName")
    private TextField rightContractName;

    @UiField("rightContractType")
    private ComboBox rightContractType;

    @UiField("rightContractStartDate")
    private PopupDateField rightContractStartDate;

    @UiField("rightContractEndDate")
    private PopupDateField rightContractEndDate;

    @UiField("rightContractHolderNoLabel")
    private Label rightContractHolderNoLabel;

    @UiField("rightContractHolderNo")
    private TextField rightContractHolderNo;

    @UiField("rightContractHolderNameLabel")
    private Label rightContractHolderNameLabel;

    @UiField("rightContractHolderName")
    private TextField rightContractHolderName;

    @UiField("rightCfpId")
    private TextField rightCfpId;

    @UiField("rightCfpNo")
    private TextField rightCfpNo;

    @UiField("rightCfpName")
    private TextField rightCfpName;

    @UiField("rightCfpTypeLabel")
    private Label rightCfpTypeLabel;

    @UiField("rightCfpType")
    private ComboBox rightCfpType;

    @UiField("rightCompanyIdLabel")
    private Label rightCompanyIdLabel;

    @UiField("rightCompanyId")
    private TextField rightCompanyId;

    @UiField("rightCompanyNo")
    private TextField rightCompanyNo;

    @UiField("rightCompanyName")
    private TextField rightCompanyName;

    @UiField("rightCompanyTypeLabel")
    private Label rightCompanyTypeLabel;

    @UiField("rightCompanyType")
    private ComboBox rightCompanyType;

    @UiField("rightCompanyCategoryLabel")
    private Label rightCompanyCategoryLabel;

    @UiField("rightCompanyCategory")
    private TextField rightCompanyCategory;

    @UiField("rightIfpId")
    private TextField rightIfpId;

    @UiField("rightIfpNo")
    private TextField rightIfpNo;

    @UiField("rightIfpName")
    private TextField rightIfpName;

    @UiField("rightIfpTypeLabel")
    private Label rightIfpTypeLabel;

    @UiField("rightIfpType")
    private ComboBox rightIfpType;

    @UiField("rightIfpItemIdLabel")
    private Label rightIfpItemIdLabel;

    @UiField("rightIfpItemId")
    private TextField rightIfpItemId;

    @UiField("rightIfpItemNo")
    private TextField rightIfpItemNo;

    @UiField("rightIfpItemName")
    private TextField rightIfpItemName;

    @UiField("rightIfpTherapeuticClassLabel")
    private Label rightIfpTherapeuticClassLabel;

    @UiField("rightIfpTherapeuticClass")
    private ComboBox rightIfpTherapeuticClass;

    @UiField("rightIfpBrandNameLabel")
    private Label rightIfpBrandNameLabel;

    @UiField("rightIfpBrandName")
    private ComboBox rightIfpBrandName;

    @UiField("rightPsId")
    private TextField rightPsId;

    @UiField("rightPsNo")
    private TextField rightPsNo;

    @UiField("rightPsName")
    private TextField rightPsName;

    @UiField("rightPsTypeLabel")
    private Label rightPsTypeLabel;

    @UiField("rightPsType")
    private ComboBox rightPsType;

    @UiField("rightPsItemNoLabel")
    private Label rightPsItemNoLabel;

    @UiField("rightPsItemNo")
    private TextField rightPsItemNo;

    @UiField("rightPsItemNameLabel")
    private Label rightPsItemNameLabel;

    @UiField("rightPsItemName")
    private TextField rightPsItemName;

    @UiField("rightPsTherapeuticClassLabel")
    private Label rightPsTherapeuticClassLabel;

    @UiField("rightPsTherapeuticClass")
    private ComboBox rightPsTherapeuticClass;

    @UiField("rightPsBrandNameLabel")
    private Label rightPsBrandNameLabel;

    @UiField("rightPsBrandName")
    private ComboBox rightPsBrandName;

    @UiField("rightRsId")
    private TextField rightRsId;

    @UiField("rightRsNo")
    private TextField rightRsNo;

    @UiField("rightRsName")
    private TextField rightRsName;

    @UiField("rightRsTypeLabel")
    private Label rightRsTypeLabel;

    @UiField("rightRsType")
    private ComboBox rightRsType;

    @UiField("rightRsItemNoLabel")
    private Label rightRsItemNoLabel;

    @UiField("rightRsItemNo")
    private TextField rightRsItemNo;

    @UiField("rightRsItemNameLabel")
    private Label rightRsItemNameLabel;

    @UiField("rightRsItemName")
    private TextField rightRsItemName;

    @UiField("rightRsBrandNameLabel")
    private Label rightRsBrandNameLabel;

    @UiField("rightRsBrandName")
    private ComboBox rightRsBrandName;

    @UiField("rightRsProgramCategoryLabel")
    private Label rightRsProgramCategoryLabel;

    @UiField("rightRsProgramCategory")
    private ComboBox rightRsProgramCategory;

    @UiField("contractRightGridLayout")
    private GridLayout contractRightGridLayout;

    @UiField("cfpRightGridLayout")
    private GridLayout cfpRightGridLayout;

    @UiField("ifpRightGridLayout")
    private GridLayout ifpRightGridLayout;

    @UiField("psRightGridLayout")
    private GridLayout psRightGridLayout;

    @UiField("rsRightGridLayout")
    private GridLayout rsRightGridLayout;

    @UiField("contractDetailRightGridLayout")
    private GridLayout contractDetailRightGridLayout;

    @UiField("cfpDetailRightGridLayout")
    private GridLayout cfpDetailRightGridLayout;

    @UiField("ifpDetailRightGridLayout")
    private GridLayout ifpDetailRightGridLayout;

    @UiField("psDetailRightGridLayout")
    private GridLayout psDetailRightGridLayout;

    @UiField("rsDetailRightGridLayout")
    private GridLayout rsDetailRightGridLayout;

    /**
     * The tree bean.
     */
    private ContractMember treeBean;
    /**
     * The table bean.
     */
    private ContractMember tableBean;
    /**
     * The src table bean.
     */
    private ContractMember srcTableBean;
    /**
     * The table bean id.
     */
    private Object tableBeanId;
    /**
     * The tree bean id.
     */
    private Object treeBeanId;
    /**
     * The src table bean id.
     */
    private Object srcTableBeanId;
    /**
     * The table container.
     */
    private final BeanItemContainer<ContractMember> tableContainer = new BeanItemContainer<>(ContractMember.class);

    private final BeanItemContainer<ContractMember> tContainer = new BeanItemContainer<>(ContractMember.class);

    private final BeanItemContainer<DetailSearchDTO> detailNormalContainer = new BeanItemContainer<>(DetailSearchDTO.class);

    /**
     * The contract.
     */
    private ContractMember contract = new ContractMember();

    /**
     * The contract.
     */
    private ContractMember contractMem = new ContractMember();
    /**
     * The cfp.
     */
    private ContractMember cfp = new ContractMember();
    /**
     * The ifp.
     */
    private ContractMember ifp = new ContractMember();
    /**
     * The priceSchedule.
     */
    private ContractMember priceSchedule = new ContractMember();
    /**
     * The rebateSchedule.
     */
    private ContractMember rebateSchedule = new ContractMember();
    /**
     * The hierarchical container.
     */
    private CustomTreeContainer<ContractMember> hierarchicalContainer = new CustomTreeContainer<>(ContractMember.class);

    private CustomTreeContainer<ContractMember> hContainer = new CustomTreeContainer<>(ContractMember.class);
    /**
     * The visible columns.
     */
    private static Object[] visibleColumns = {Constants.CATEGORY, Constants.MEMBER_ID, Constants.MEMBER_NO, Constants.NAME};

    /**
     * The expand listener.
     */
    private final StplExpandListener expandListener = new StplExpandListener();
    /**
     * The collapse listener.
     */
    private final StplCollapseListener collapseListener = new StplCollapseListener();
    /**
     * The dash board logic.
     */
    private final DashBoardLogic dashBoardLogic;
    /**
     * The contract member.
     */
    private ContractMember contractMember;
    /**
     * The Bean item Null
     */
    private static final BeanItem<?> NULL_OBJECT = null;
    /**
     * Empty Bean
     */
    private static final ContractMember NULL_BEAN = null;

    public static final HelperDTO NULL_ID = new HelperDTO(0, "-Select One-");

    /* Contract Dashboard Logic  */
    ContractDashboardLogic cdbLogic = new ContractDashboardLogic();

    /* Layout contains the Control Buttons. */
    HorizontalLayout buttonLayout = new HorizontalLayout();

    /* Items per page label */
    public Label itemsPerPageLabel = new Label("Items per page: ",
            ContentMode.HTML);

    /* Combo Box that displays the number of items to be displayed */
    public final ComboBox itemsPerPageSelect = new ComboBox();

    /* TextField that displays the current page number */
    public final TextField currentPageTextField = new TextField();

    /* First button */
    public final Button first = new Button("<<");

    /* Previous Button */
    public final Button previous = new Button("<");

    /* Next Button */
    public final Button next = new Button(">");

    /* Last Button */
    public final Button last = new Button(">>");

    /* Separator Label */
    public Label separatorLabel = new Label("&nbsp;/&nbsp;", ContentMode.HTML);

    /* Total Pages Label */
    public Label totalPagesLabel = new Label("1", ContentMode.HTML);

    /* Control Bar */
    public HorizontalLayout controlBar = new HorizontalLayout();

    /* Page Size Layout */
    public HorizontalLayout pageSize = new HorizontalLayout();

    /* Page Management Layout*/
    public HorizontalLayout pageManagement = new HorizontalLayout();

    /* Page Label */
    public Label pageLabel = new Label("Page:", ContentMode.HTML);

    /* List of Pagelengths*/
    public List<Integer> pageLengths = new ArrayList();

    /* Current Pagelength*/
    public int tempPageLength =NumericConstants.FIFTEEN;

    /* Current Level Value */
    public int levelValue;

    /* Contains the parent items of an item in the hierarchy */
    public List parentList = new ArrayList();

    /* Last Parent Item in the expanded Tree*/
    ContractMember lastParent = new ContractMember();

    public final ContractDashboardLogic logic = new ContractDashboardLogic();

    private CommonUtil util = new CommonUtil();

    DashBoardSearchDto searchDTO;
    LazyBeanItemContainer searchResults;
    LazyBeanItemContainer detailSearchResults;
    private CustomFieldGroup binder = new CustomFieldGroup(NULL_OBJECT);

    private CustomFieldGroup leftSearchBinder = new CustomFieldGroup(new BeanItem<>(new LeftSearchComponentDTO()));

    private CustomFieldGroup rightSearchBinder = new CustomFieldGroup(new BeanItem<>(new RightSearchComponentDTO()));

    private String leftComponentValue = StringUtils.EMPTY;

    private ExtFilterTreeTable exceltable;
    private CustomTreeContainer<ContractMember> excelContainer = new CustomTreeContainer<>(ContractMember.class);
    List<Object> childList = new ArrayList<>();
    /* Current Page Number */
    public int currentPageNo = 1;

    public DateFormat mmddyyyy = new SimpleDateFormat("MM/dd/yyyy");

    /**
     * Gets the tree bean.
     *
     * @return the tree bean
     */
    public ContractMember getTreeBean() {
        return treeBean;
    }

    /**
     * Sets the tree bean.
     *
     * @param treeBean the tree bean
     */
    public void setTreeBean(final ContractMember treeBean) {
        this.treeBean = treeBean;
    }

    /**
     * Gets the table bean.
     *
     * @return the table bean
     */
    public ContractMember getTableBean() {
        return tableBean;
    }

    /**
     * Sets the table bean.
     *
     * @param tableBean the table bean
     */
    public void setTableBean(final ContractMember tableBean) {
        this.tableBean = tableBean;
    }

    /**
     * Sets the src table bean.
     *
     * @param srcTableBean the src table bean
     */
    public void setSrcTableBean(final ContractMember srcTableBean) {
        this.srcTableBean = srcTableBean;
    }

    /**
     * Gets the table bean id.
     *
     * @return the table bean id
     */
    public Object getTableBeanId() {
        return tableBeanId;
    }

    /**
     * Sets the table bean id.
     *
     * @param tableBeanId the table bean id
     */
    public void setTableBeanId(final Object tableBeanId) {
        this.tableBeanId = tableBeanId;
    }

    /**
     * Gets the tree bean id.
     *
     * @return the tree bean id
     */
    public Object getTreeBeanId() {
        return treeBeanId;
    }

    /**
     * Sets the tree bean id.
     *
     * @param treeBeanId the tree bean id
     */
    public void setTreeBeanId(final Object treeBeanId) {
        this.treeBeanId = treeBeanId;
    }

    /**
     * Gets the src table bean id.
     *
     * @return the src table bean id
     */
    public Object getSrcTableBeanId() {
        return srcTableBeanId;
    }

    /**
     * Sets the src table bean id.
     *
     * @param srcTableBeanId the src table bean id
     */
    public void setSrcTableBeanId(final Object srcTableBeanId) {
        this.srcTableBeanId = srcTableBeanId;
    }

    /**
     * Gets the contract.
     *
     * @return the contract
     */
    public ContractMember getContract() {
        return contract;
    }

    /**
     * Sets the contract.
     *
     * @param contract the contract
     */
    public void setContract(final ContractMember contract) {
        this.contract = contract;
    }

    /**
     * Gets the cfp.
     *
     * @return the cfp
     */
    public ContractMember getCfp() {
        return cfp;
    }

    /**
     * Sets the cfp.
     *
     * @param cfp the cfp
     */
    public void setCfp(final ContractMember cfp) {
        this.cfp = cfp;
    }

    /**
     * Gets the ifp.
     *
     * @return the ifp
     */
    public ContractMember getIfp() {
        return ifp;
    }

    /**
     * Sets the ifp.
     *
     * @param ifp the ifp
     */
    public void setIfp(final ContractMember ifp) {
        this.ifp = ifp;
    }

    /**
     * Gets the price schedule.
     *
     * @return the price schedule
     */
    public ContractMember getPriceSchedule() {
        return priceSchedule;
    }

    /**
     * Sets the price schedule.
     *
     * @param priceSchedule the price schedule
     */
    public void setPriceSchedule(final ContractMember priceSchedule) {
        this.priceSchedule = priceSchedule;
    }

    /**
     * Gets the rebate schedule.
     *
     * @return the rebate schedule
     */
    public ContractMember getRebateSchedule() {
        return rebateSchedule;
    }

    /**
     * Sets the rebate schedule.
     *
     * @param rebateSchedule the rebate schedule
     */
    public void setRebateSchedule(final ContractMember rebateSchedule) {
        this.rebateSchedule = rebateSchedule;
    }

    /**
     * Gets the hierarchical container.
     *
     * @return the hierarchical container
     */
    public CustomTreeContainer<ContractMember> getHierarchicalContainer() {
        return hierarchicalContainer;
    }

    /**
     * Sets the hierarchical container.
     *
     * @param hierarchicalContainer the hierarchical container
     */
    public void setHierarchicalContainer(final CustomTreeContainer<ContractMember> hierarchicalContainer) {
        this.hierarchicalContainer = hierarchicalContainer;
    }

    /**
     * Gets the contract member.
     *
     * @return the contract member
     */
    public ContractMember getContractMember() {
        return contractMember;
    }

    /**
     * Sets the contract member.
     *
     * @param contractMember the contract member
     */
    public void setContractMember(final ContractMember contractMember) {
        this.contractMember = contractMember;
    }

    /**
     * Gets the tree.
     *
     * @return the tree
     */
    public Tree getTree() {
        return tree;
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
     * Gets the process tree table.
     *
     * @return the process tree table
     */
    public TreeTable getProcessTreeTable() {
        return processTreeTable;
    }

    /**
     * Gets the table container.
     *
     * @return the table container
     */
    public BeanItemContainer<ContractMember> getTableContainer() {
        return tableContainer;
    }

    /**
     * Gets the processed layout.
     *
     * @return the processed layout
     */
    public VerticalLayout getProcessedLayout() {
        return processedLayout;
    }

    /**
     * Gets the expand listener.
     *
     * @return the expand listener
     */
    public StplExpandListener getExpandListener() {
        return expandListener;
    }

    /**
     * Gets the collapse listener.
     *
     * @return the collapse listener
     */
    public StplCollapseListener getCollapseListener() {
        return collapseListener;
    }

    /**
     * Gets the dash board logic.
     *
     * @return the dash board logic
     */
    public DashBoardLogic getDashBoardLogic() {
        return dashBoardLogic;
    }

    ContractHeaderLogic headerLogic = new ContractHeaderLogic();

    LazyContainer ifpBrandContainer;

    LazyContainer psBrandContainer;

    LazyContainer rsBrandContainer;

    LazyContainer rightIfpBrandContainer;

    LazyContainer rightPsBrandContainer;

    LazyContainer rightRsBrandContainer;
    DashboardComponentSearchLogic dashboardSearchLogic = new DashboardComponentSearchLogic();
    HorizontalLayout hLayout = new HorizontalLayout();
    SessionDTO sessionDTO;

    /**
     * DashBoardForm Constructor.
     */
    public DashBoardForm(final SessionDTO sessionDTO) throws SystemException, PortalException {
        super();
        LOGGER.debug("Entering DashBoardForm");
        this.sessionDTO = sessionDTO;
        dashBoardLogic = new DashBoardLogic(this.sessionDTO);
        init();
        LOGGER.debug("End of DashBoardForm");
    }

    /**
     * Inits Method load while calling constructor method.
     */
    public void init() throws SystemException, PortalException {
        LOGGER.debug("Entering init method");

        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarative-ui/contract-dashboard/contract-dashboard-landing-screen.xml"), this));
        getLeftBinder();
        getRightBinder();
        basic();
        configure();
        mainLayout.addComponent(hLayout);
        hLayout.setVisible(false);
        loadTableValues(null);
        loadDetailTable();
        getDroppableTree();
        binder.setErrorDisplay(errorMsg);
        LOGGER.debug("End of init method");

    }

    /**
     * Binds the CustomFieldGroup to LeftSearchComponent.
     *
     * @return CustomFieldGroup.
     */
    private CustomFieldGroup getLeftBinder() {
        LOGGER.debug("Entering getLeftBinder method ");
        leftSearchBinder.setItemDataSource(new BeanItem<>(new LeftSearchComponentDTO()));
        leftSearchBinder.bindMemberFields(this);
        leftSearchBinder.setBuffered(true);
        leftSearchBinder.setErrorDisplay(errorMsg);
        leftSearchBinder.getErrorDisplay().clearError();
        LOGGER.debug(" getLeftBinder method ends");
        return leftSearchBinder;
    }

    /**
     * Binds the CustomFieldGroup to RightSearchComponent.
     *
     * @return CustomFieldGroup.
     */
    private CustomFieldGroup getRightBinder() {
        LOGGER.debug("Entering getRightBinder method ");
        rightSearchBinder.setItemDataSource(new BeanItem<>(new RightSearchComponentDTO()));
        rightSearchBinder.bindMemberFields(this);
        rightSearchBinder.setBuffered(true);
        rightSearchBinder.setErrorDisplay(errorMsg);
        rightSearchBinder.getErrorDisplay().clearError();
        LOGGER.debug(" getRightBinder method ends");
        return rightSearchBinder;
    }

    /**
     * This method Addspace between the compinents.
     *
     * @return the grid layout
     */
    public GridLayout addspace() {
        final GridLayout grid = new GridLayout(1, 1);
        grid.addComponent(new Label(" "));
        return grid;
    }

    private void basic() throws SystemException, PortalException {
        LOGGER.debug("Entering basic method");
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final Map<String, AppPermission> functionContractDashboardHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + Constants.COMMA + "Contract Dashboard Search");

        verticalSplitPanel.setSplitPosition(30f);

        btnSearch.setWidth("75");
        btnSearch.addStyleName(ConstantUtil.BUTTONPADDING);
        btnSearch.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug(" buttonClick (ClickEvent event) name=" + event.getButton().getCaption());
                try {
                    leftSearchBinder.getErrorDisplay().clearError();
                    leftSearchBinder.commit();

                    if (ContractUtils.CONTRACT_DETAILS_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
                        if (Constants.SUMMARY.equals(String.valueOf(leftSearchType.getValue()))) {
                            if ((StringUtils.isBlank(String.valueOf(leftContractId.getValue())) || Constants.NULL.equals(String.valueOf(leftContractId.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(leftContractNo.getValue())) || Constants.NULL.equals(String.valueOf(leftContractNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(leftContractName.getValue())) || Constants.NULL.equals(String.valueOf(leftContractName.getValue())))
                                    && (leftContractType.getValue() == null || ((HelperDTO) leftContractType.getValue()).getId() == 0)) {

                                MessageBox.showPlain(Icon.ERROR, Constants.SEARCH_ERROR, ConstantUtil.PLEASE_ENTER_SEARCH_CRITERIA, ButtonId.OK);
                            } else {
                                loadTableValues(leftSearchBinder);

                            }

                        } else if (Constants.DETAILS.equals(String.valueOf(leftSearchType.getValue()))) {
                            if ((StringUtils.isBlank(String.valueOf(leftContractId.getValue())) || Constants.NULL.equals(String.valueOf(leftContractId.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(leftContractNo.getValue())) || Constants.NULL.equals(String.valueOf(leftContractNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(leftContractName.getValue())) || Constants.NULL.equals(String.valueOf(leftContractName.getValue())))
                                    && (leftContractType.getValue() == null || ((HelperDTO) leftContractType.getValue()).getId() == 0)
                                    && leftContractStartDate.getValue() == null && leftContractEndDate.getValue() == null
                                    && (StringUtils.isBlank(String.valueOf(leftContractHolderNo.getValue())) || Constants.NULL.equals(String.valueOf(leftContractHolderNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(leftContractHolderName.getValue())) || Constants.NULL.equals(String.valueOf(leftContractHolderName.getValue())))) {

                                MessageBox.showPlain(Icon.ERROR, Constants.SEARCH_ERROR, ConstantUtil.PLEASE_ENTER_SEARCH_CRITERIA, ButtonId.OK);

                            } else {

                                loadTableValues(leftSearchBinder);

                            }
                        }
                    } else if (ContractUtils.CFP_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
                        if (Constants.SUMMARY.equals(String.valueOf(leftSearchType.getValue()))) {
                            if ((StringUtils.isBlank(String.valueOf(leftCfpId.getValue())) || Constants.NULL.equals(String.valueOf(leftCfpId.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(leftCfpNo.getValue())) || Constants.NULL.equals(String.valueOf(leftCfpNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(leftCfpName.getValue())) || Constants.NULL.equals(String.valueOf(leftCfpName.getValue())))
                                    && (leftCfpType.getValue() == null || ((HelperDTO) leftCfpType.getValue()).getId() == 0)) {

                                MessageBox.showPlain(Icon.ERROR, Constants.SEARCH_ERROR, ConstantUtil.PLEASE_ENTER_SEARCH_CRITERIA, ButtonId.OK);
                            } else {

                                loadTableValues(leftSearchBinder);
                            }

                        } else if (Constants.DETAILS.equals(String.valueOf(leftSearchType.getValue()))) {
                            if ((StringUtils.isBlank(String.valueOf(leftCfpId.getValue())) || Constants.NULL.equals(String.valueOf(leftCfpId.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(leftCfpNo.getValue())) || Constants.NULL.equals(String.valueOf(leftCfpNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(leftCfpName.getValue())) || Constants.NULL.equals(String.valueOf(leftCfpName.getValue())))
                                    && (leftCfpType.getValue() == null || ((HelperDTO) leftCfpType.getValue()).getId() == 0)
                                    && (StringUtils.isBlank(String.valueOf(leftCompanyNo.getValue())) || Constants.NULL.equals(String.valueOf(leftCompanyNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(leftCompanyName.getValue())) || Constants.NULL.equals(String.valueOf(leftCompanyName.getValue())))
                                    && (leftCompanyType.getValue() == null || ((HelperDTO) leftCompanyType.getValue()).getId() == 0)
                                    && (StringUtils.isBlank(String.valueOf(leftCompanyCategory.getValue())) || Constants.NULL.equals(String.valueOf(leftCompanyCategory.getValue())))) {

                                MessageBox.showPlain(Icon.ERROR, Constants.SEARCH_ERROR, ConstantUtil.PLEASE_ENTER_SEARCH_CRITERIA, ButtonId.OK);
                            } else {
                                loadTableValues(leftSearchBinder);

                            }
                        }
                    } else if (ContractUtils.IFP_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
                        if (Constants.SUMMARY.equals(String.valueOf(leftSearchType.getValue()))) {
                            if ((StringUtils.isBlank(String.valueOf(leftIfpId.getValue())) || Constants.NULL.equals(String.valueOf(leftIfpId.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(leftIfpNo.getValue())) || Constants.NULL.equals(String.valueOf(leftIfpNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(leftIfpName.getValue())) || Constants.NULL.equals(String.valueOf(leftIfpName.getValue())))
                                    && (leftIfpType.getValue() == null || ((HelperDTO) leftIfpType.getValue()).getId() == 0)) {

                                MessageBox.showPlain(Icon.ERROR, Constants.SEARCH_ERROR, ConstantUtil.PLEASE_ENTER_SEARCH_CRITERIA, ButtonId.OK);
                            } else {
                                loadTableValues(leftSearchBinder);

                            }

                        } else if (Constants.DETAILS.equals(String.valueOf(leftSearchType.getValue()))) {
                            if ((StringUtils.isBlank(String.valueOf(leftIfpId.getValue())) || Constants.NULL.equals(String.valueOf(leftIfpId.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(leftIfpNo.getValue())) || Constants.NULL.equals(String.valueOf(leftIfpNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(leftIfpName.getValue())) || Constants.NULL.equals(String.valueOf(leftIfpName.getValue())))
                                    && (leftIfpType.getValue() == null || ((HelperDTO) leftIfpType.getValue()).getId() == 0)
                                    && (StringUtils.isBlank(String.valueOf(leftIfpItemNo.getValue())) || Constants.NULL.equals(String.valueOf(leftIfpItemNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(leftIfpItemName.getValue())) || Constants.NULL.equals(String.valueOf(leftIfpItemName.getValue())))
                                    && (leftIfpTherapeuticClass.getValue() == null || ((HelperDTO) leftIfpTherapeuticClass.getValue()).getId() == 0)
                                    && (leftIfpBrandName.getValue() == null || ((HelperDTO) leftIfpBrandName.getValue()).getId() == 0)) {

                                MessageBox.showPlain(Icon.ERROR, Constants.SEARCH_ERROR, ConstantUtil.PLEASE_ENTER_SEARCH_CRITERIA, ButtonId.OK);
                            } else {

                                loadTableValues(leftSearchBinder);
                            }
                        }
                    } else if (ContractUtils.PS_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
                        if (Constants.SUMMARY.equals(String.valueOf(leftSearchType.getValue()))) {
                            if ((StringUtils.isBlank(String.valueOf(leftPsId.getValue())) || Constants.NULL.equals(String.valueOf(leftPsId.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(leftPsNo.getValue())) || Constants.NULL.equals(String.valueOf(leftPsNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(leftPsName.getValue())) || Constants.NULL.equals(String.valueOf(leftPsName.getValue())))
                                    && (leftPsType.getValue() == null || ((HelperDTO) leftPsType.getValue()).getId() == 0)) {

                                MessageBox.showPlain(Icon.ERROR, Constants.SEARCH_ERROR, ConstantUtil.PLEASE_ENTER_SEARCH_CRITERIA, ButtonId.OK);
                            } else {
                                loadTableValues(leftSearchBinder);

                            }
                        } else if (Constants.DETAILS.equals(String.valueOf(leftSearchType.getValue()))) {
                            if ((StringUtils.isBlank(String.valueOf(leftPsId.getValue())) || Constants.NULL.equals(String.valueOf(leftPsId.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(leftPsNo.getValue())) || Constants.NULL.equals(String.valueOf(leftPsNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(leftPsName.getValue())) || Constants.NULL.equals(String.valueOf(leftPsName.getValue())))
                                    && (leftPsType.getValue() == null || ((HelperDTO) leftPsType.getValue()).getId() == 0)
                                    && (StringUtils.isBlank(String.valueOf(leftPsItemNo.getValue())) || Constants.NULL.equals(String.valueOf(leftPsItemNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(leftPsItemName.getValue())) || Constants.NULL.equals(String.valueOf(leftPsItemName.getValue())))
                                    && (leftPsTherapeuticClass.getValue() == null || ((HelperDTO) leftPsTherapeuticClass.getValue()).getId() == 0)
                                    && (leftPsBrandName.getValue() == null || ((HelperDTO) leftPsBrandName.getValue()).getId() == 0)) {

                                MessageBox.showPlain(Icon.ERROR, Constants.SEARCH_ERROR, ConstantUtil.PLEASE_ENTER_SEARCH_CRITERIA, ButtonId.OK);
                            } else {

                                loadTableValues(leftSearchBinder);
                            }
                        }
                    } else if (ContractUtils.RS_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
                        if (Constants.SUMMARY.equals(String.valueOf(leftSearchType.getValue()))) {
                            if ((StringUtils.isBlank(String.valueOf(leftRsId.getValue())) || Constants.NULL.equals(String.valueOf(leftRsId.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(leftRsNo.getValue())) || Constants.NULL.equals(String.valueOf(leftRsNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(leftRsName.getValue())) || Constants.NULL.equals(String.valueOf(leftRsName.getValue())))
                                    && (leftRsType.getValue() == null || ((HelperDTO) leftRsType.getValue()).getId() == 0)) {

                                MessageBox.showPlain(Icon.ERROR, Constants.SEARCH_ERROR, ConstantUtil.PLEASE_ENTER_SEARCH_CRITERIA, ButtonId.OK);
                            } else {

                                loadTableValues(leftSearchBinder);
                            }
                        } else if (Constants.DETAILS.equals(String.valueOf(leftSearchType.getValue()))) {
                            if ((StringUtils.isBlank(String.valueOf(leftRsId.getValue())) || Constants.NULL.equals(String.valueOf(leftRsId.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(leftRsNo.getValue())) || Constants.NULL.equals(String.valueOf(leftRsNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(leftRsName.getValue())) || Constants.NULL.equals(String.valueOf(leftRsName.getValue())))
                                    && (leftRsType.getValue() == null || ((HelperDTO) leftRsType.getValue()).getId() == 0)
                                    && (StringUtils.isBlank(String.valueOf(leftRsItemNo.getValue())) || Constants.NULL.equals(String.valueOf(leftRsItemNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(leftRsItemName.getValue())) || Constants.NULL.equals(String.valueOf(leftRsItemName.getValue())))
                                    && (leftRsProgramCategory.getValue() == null || ((HelperDTO) leftRsProgramCategory.getValue()).getId() == 0)
                                    && (leftRsBrandName.getValue() == null || ((HelperDTO) leftRsBrandName.getValue()).getId() == 0)) {

                                MessageBox.showPlain(Icon.ERROR, Constants.SEARCH_ERROR, ConstantUtil.PLEASE_ENTER_SEARCH_CRITERIA, ButtonId.OK);
                            } else {

                                loadTableValues(leftSearchBinder);
                            }
                        }
                    }

                } catch (Exception ex) {
                    LOGGER.error(ex);

                }
            }
        });
        btnSearch.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });

        addToTreeBtn.setWidth("100");
        addToTreeBtn.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            @SuppressWarnings("unchecked")
            public void buttonClick(final Button.ClickEvent event) {
                tree.setHeight(99f, Sizeable.Unit.PERCENTAGE);
                try {
                    Boolean flag = addToTreeMethod();
                    if (flag) {
                        table.getContainerDataSource().removeAllItems();
                        detailsTable.getContainerDataSource().removeAllItems();
                    }
                } catch (SystemException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    LOGGER.error(errorMsg);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                } catch (Exception ex) {
                    LOGGER.error("Error While Adding to right side tree node :" + ex);
                }
            }
        });
        addToTreeBtn.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });

        resultsExportBtn.setIcon(new ThemeResource(ExcelExportUtil.EXCEL_EXPORT_IMAGE));
        resultsExportBtn.setStyleName("link");
        resultsExportBtn.setDescription(Constants.EXCEL_EXPORT);
        resultsExportBtn.setIconAlternateText(Constants.EXCEL_EXPORT);
        resultsExportBtn.setErrorHandler(new ErrorHandler() {

            @Override
            public void error(com.vaadin.server.ErrorEvent event) {
                return;
            }
        });
        resultsExportBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    LOGGER.debug("Entering EXCEL Export Button Click :::: ");
                    excelExportLogic();
                    LOGGER.debug(" Ends  EXCEL Export Button Click ::::  ");

                } catch (Exception exception) {
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1011));
                    LOGGER.error(exception);
                }
            }
        });

        if ((functionContractDashboardHM.get(CHFunctionNameUtils.RESET_BUTTON) != null) && !((AppPermission) functionContractDashboardHM.get(CHFunctionNameUtils.RESET_BUTTON)).isFunctionFlag()) {
            resetbtn.setVisible(false);
        }
        if ((functionContractDashboardHM.get(CHFunctionNameUtils.SEARCH_RESET_BTN) != null) && !((AppPermission) functionContractDashboardHM.get(CHFunctionNameUtils.SEARCH_RESET_BTN)).isFunctionFlag()) {
            lresetbtn.setVisible(false);
        }
        if ((functionContractDashboardHM.get(CHFunctionNameUtils.PROCESS) != null) && !((AppPermission) functionContractDashboardHM.get(CHFunctionNameUtils.PROCESS)).isFunctionFlag()) {
            processedbtn.setVisible(false);
        }
        if ((functionContractDashboardHM.get(CHFunctionNameUtils.REBUILD_BTN) != null) && !((AppPermission) functionContractDashboardHM.get(CHFunctionNameUtils.REBUILD_BTN)).isFunctionFlag()) {
            editbtn.setVisible(false);
        }
        if ((functionContractDashboardHM.get(CHFunctionNameUtils.TREE_SAVE) != null) && !((AppPermission) functionContractDashboardHM.get(CHFunctionNameUtils.TREE_SAVE)).isFunctionFlag()) {
            treeSaveBtn.setVisible(false);
        }
        if ((functionContractDashboardHM.get(CHFunctionNameUtils.TREE_REMOVE_BTN) != null) && !((AppPermission) functionContractDashboardHM.get(CHFunctionNameUtils.TREE_REMOVE_BTN)).isFunctionFlag()) {
            treeRemoveBtn.setVisible(false);
        }
        if ((functionContractDashboardHM.get(CHFunctionNameUtils.ADD_TO_TREE) != null) && !((AppPermission) functionContractDashboardHM.get(CHFunctionNameUtils.ADD_TO_TREE)).isFunctionFlag()) {
            addToTreeBtn.setVisible(false);
        }

        resetbtn.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                MessageBox.showPlain(Icon.QUESTION, util.getHeaderMessage(), util.getResetMessage(), new MessageBoxListener() {

                    @Override
                    public void buttonClicked(ButtonId buttonId) {
                        if (buttonId.name().equals(Constants.YES)) {
                            leftComponent.setValue(ContractUtils.CONTRACT_DETAILS_COMPONENT);
                            getLeftBinder();
                            table.setContainerDataSource(tContainer);
                            table.setVisibleColumns(new Object[]{Constants.MEMBER_ID, Constants.MEMBER_NO, Constants.NAME, Constants.MEMBER_TYPE});
                            table.setColumnHeaders(Constants.CONTRACT_ID1, Constants.CONTRACT_NO_SP, Constants.CONTRACT_NAME1, Constants.CONTRACT_TYPE_HEADER);
                            table.setFilterGenerator(new SearchComponentFilterGenerator(String.valueOf(leftComponent.getValue())));
                            detailsTable.setContainerDataSource(detailNormalContainer);
                            detailsTable.setVisible(false);
                            detailPanel.setVisible(false);
                            leftSearchType.select(Constants.SUMMARY);
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
            }
        });

        treeSaveBtn.setWidth("75");
        treeSaveBtn.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });
        treeSaveBtn.addClickListener(new Button.ClickListener() {
            String contractIden = StringUtils.EMPTY;
            String contractNam = StringUtils.EMPTY;

            /**
             * Called when a Button has been clicked.
             */
            @SuppressWarnings("rawtypes")
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    LOGGER.debug("buttonClick(ClickEvent event) name=" + event.getButton().getCaption());
                    final Collection idList = tree.rootItemIds();

                    Iterator iterator = idList.iterator();
                    final Object idValue = iterator.next();
                    final ContractMember temp = getBeanFromId(idValue);
                    if (temp.getCategory().equals(Constants.CONTRACT)) {
                        contractMem = temp;
                        DynamicQuery query = DynamicQueryFactoryUtil.forClass(ContractMaster.class);
                        query.add(RestrictionsFactoryUtil.eq("contractMasterSid", contractMem.getInternalId()));
                        List<ContractMaster> list = ContractMasterLocalServiceUtil.dynamicQuery(query);
                        for (ContractMaster cm : list) {
                            contractIden = cm.getContractId();
                            contractNam = cm.getContractName();
                        }
                    }

                    if (idList.isEmpty()) {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "No new contract created. Create new contract and save it.");
                    } else {
                        MessageBox.showPlain(Icon.QUESTION, util.getHeaderMessage(), util.getSaveMessage(contractNam), new MessageBoxListener() {
                            /**
                             * Invoked when yes is clicked.
                             *
                             * @param event - ClickEvent
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                if (buttonId.name().equals(Constants.YES)) {
                                    try {
                                        saveTree(idList, StringUtils.EMPTY);
                                        tree.removeAllItems();
                                        final Notification notif = new Notification(util.getSavedSuccessfulMessage(contractIden, contractNam), Notification.Type.HUMANIZED_MESSAGE);
                                        notif.setDelayMsec(Constants.NOTIF_DEPALY_MSEC);
                                        notif.setPosition(Position.MIDDLE_CENTER);
                                        notif.setStyleName("mystyle");
                                        notif.show(Page.getCurrent());
                                    } catch (PortalException ex) {
                                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_2002));
                                        LOGGER.error(ex);
                                    } catch (Exception ex) {
                                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_2002));
                                        LOGGER.error(ex);
                                    }
                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);

                    }
                } catch (Exception ex) {
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_2002));
                    LOGGER.error(ex);
                }
            }
        });

        if (!(functionContractDashboardHM.get(CHFunctionNameUtils.TREE_REMOVE_BTN) != null) && !((AppPermission) functionContractDashboardHM.get(CHFunctionNameUtils.TREE_REMOVE_BTN)).isFunctionFlag()) {
            treeButtonLayout.removeComponent(treeRemoveBtn);
        }

        treeRemoveBtn.setWidth("75");
        treeRemoveBtn.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });
        treeRemoveBtn.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            @SuppressWarnings("unchecked")
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug(" buttonClick ( ClickEvent event ) name=" + event.getButton().getCaption());
                if (tree.getItemIds().size() > Constants.ZERO) {
                    if (treeBean == null) {
                        AbstractNotificationUtils.getWarningNotification("No Node Selected", "Please select a node to remove ");
                    } else {
                        final Collection<Object> collection = (Collection<Object>) tree.getChildren(treeBeanId);
                        if (collection == null) {
                            MessageBox.showPlain(Icon.QUESTION, util.getHeaderMessage(), util.getRemoveComponentMessage(), new MessageBoxListener() {
                                /**
                                 * Invoked when yes is clicked.
                                 *
                                 * @param event - ClickEvent
                                 */
                                @SuppressWarnings("PMD")
                                public void buttonClicked(final ButtonId buttonId) {
                                    if (buttonId.name().equals(Constants.YES)) {
                                        tree.removeItem(treeBeanId);
                                        if (tree.getItemIds().size() == Constants.ZERO) {
                                            tree.setHeight(TREE_HEIGHT);
                                        }
                                    }
                                }
                            }, ButtonId.YES, ButtonId.NO);

                        } else {
                            final String message = "Please remove the child nodes of  " + treeBean.getName();
                            AbstractNotificationUtils.getWarningNotification("Remove Criteria", message);
                        }
                    }
                } else {
                    AbstractNotificationUtils.getWarningNotification("Remove Criteria", "No data to remove");
                }
                treeBean = tree.getValue() == null ? null : getBeanFromID(tree.getValue());
            }
        });

        controlBar.setVisible(false);
        final Label contractLable = new Label("Contract No");
        /*added this style to make contract no and textbox closer*/
        contractLable.addStyleName("contract-dashboard-contract-no");

        processedbtnSearch.setWidth("75");

        processedbtnSearch.addClickListener(new Button.ClickListener() {
            /**
             * Method used to process button search logic and its listener
             */
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    LOGGER.debug(" buttonClick(Click Event event) name=" + event.getButton().getCaption());
                    rightSearchBinder.getErrorDisplay().clearError();
                    rightSearchBinder.commit();

                    if (ContractUtils.CONTRACT_DETAILS_COMPONENT.equals(String.valueOf(rightComponent.getValue()))) {
                        if (Constants.SUMMARY.equals(String.valueOf(rightSearchType.getValue()))) {
                            if ((StringUtils.isBlank(String.valueOf(rightContractId.getValue())) || Constants.NULL.equals(String.valueOf(rightContractId.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightContractNo.getValue())) || Constants.NULL.equals(String.valueOf(rightContractNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightContractName.getValue())) || Constants.NULL.equals(String.valueOf(rightContractName.getValue())))
                                    && (rightContractType.getValue() == null || ((HelperDTO) rightContractType.getValue()).getId() == 0)) {

                                MessageBox.showPlain(Icon.ERROR, Constants.SEARCH_ERROR, ConstantUtil.PLEASE_ENTER_SEARCH_CRITERIA, ButtonId.OK);
                            } else {
                                getProcessedTree(rightSearchBinder);
                                if (hierarchicalContainer.size() > Constants.ZERO) {
                                    CommonUIUtils.successNotification(ConstantUtil.SEARCH_COMPLETED);
                                } else {
                                    CommonUIUtils.successNotification(ConstantUtil.NO_RESULTS_FOUND);
                                }
                            }

                        } else if (Constants.DETAILS.equals(String.valueOf(rightSearchType.getValue()))) {
                            if ((StringUtils.isBlank(String.valueOf(rightContractId.getValue())) || Constants.NULL.equals(String.valueOf(rightContractId.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightContractNo.getValue())) || Constants.NULL.equals(String.valueOf(rightContractNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightContractName.getValue())) || Constants.NULL.equals(String.valueOf(rightContractName.getValue())))
                                    && (rightContractType.getValue() == null || ((HelperDTO) rightContractType.getValue()).getId() == 0)
                                    && rightContractStartDate.getValue() == null && rightContractEndDate.getValue() == null
                                    && (StringUtils.isBlank(String.valueOf(rightContractHolderNo.getValue())) || Constants.NULL.equals(String.valueOf(rightContractHolderNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightContractHolderName.getValue())) || Constants.NULL.equals(String.valueOf(rightContractHolderName.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightCfpId.getValue())) || Constants.NULL.equals(String.valueOf(rightCfpId.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightCfpNo.getValue())) || Constants.NULL.equals(String.valueOf(rightCfpNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightCfpName.getValue())) || Constants.NULL.equals(String.valueOf(rightCfpName.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightCompanyId.getValue())) || Constants.NULL.equals(String.valueOf(rightCompanyId.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightCompanyNo.getValue())) || Constants.NULL.equals(String.valueOf(rightCompanyNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightCompanyName.getValue())) || Constants.NULL.equals(String.valueOf(rightCompanyName.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightIfpId.getValue())) || Constants.NULL.equals(String.valueOf(rightIfpId.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightIfpNo.getValue())) || Constants.NULL.equals(String.valueOf(rightIfpNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightIfpName.getValue())) || Constants.NULL.equals(String.valueOf(rightIfpName.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightIfpItemId.getValue())) || Constants.NULL.equals(String.valueOf(rightIfpItemId.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightIfpItemNo.getValue())) || Constants.NULL.equals(String.valueOf(rightIfpItemNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightIfpItemName.getValue())) || Constants.NULL.equals(String.valueOf(rightIfpItemName.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightPsId.getValue())) || Constants.NULL.equals(String.valueOf(rightPsId.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightPsNo.getValue())) || Constants.NULL.equals(String.valueOf(rightPsNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightPsName.getValue())) || Constants.NULL.equals(String.valueOf(rightPsName.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightRsId.getValue())) || Constants.NULL.equals(String.valueOf(rightRsId.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightRsNo.getValue())) || Constants.NULL.equals(String.valueOf(rightRsNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightRsName.getValue())) || Constants.NULL.equals(String.valueOf(rightRsName.getValue())))) {

                                MessageBox.showPlain(Icon.ERROR, Constants.SEARCH_ERROR, ConstantUtil.PLEASE_ENTER_SEARCH_CRITERIA, ButtonId.OK);

                            } else {
                                getProcessedTree(rightSearchBinder);
                                if (hierarchicalContainer.size() > Constants.ZERO) {
                                    CommonUIUtils.successNotification(ConstantUtil.SEARCH_COMPLETED);
                                } else {
                                    CommonUIUtils.successNotification(ConstantUtil.NO_RESULTS_FOUND);
                                }
                            }
                        }
                    } else if (ContractUtils.CFP_COMPONENT.equals(String.valueOf(rightComponent.getValue()))) {
                        if (Constants.SUMMARY.equals(String.valueOf(rightSearchType.getValue()))) {
                            if ((StringUtils.isBlank(String.valueOf(rightCfpId.getValue())) || Constants.NULL.equals(String.valueOf(rightCfpId.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightCfpNo.getValue())) || Constants.NULL.equals(String.valueOf(rightCfpNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightCfpName.getValue())) || Constants.NULL.equals(String.valueOf(rightCfpName.getValue())))
                                    && (leftCfpType.getValue() == null || ((HelperDTO) leftCfpType.getValue()).getId() == 0)) {

                                MessageBox.showPlain(Icon.ERROR, Constants.SEARCH_ERROR, ConstantUtil.PLEASE_ENTER_SEARCH_CRITERIA, ButtonId.OK);
                            } else {
                                getProcessedTree(rightSearchBinder);
                                if (hierarchicalContainer.size() > Constants.ZERO) {
                                    CommonUIUtils.successNotification(ConstantUtil.SEARCH_COMPLETED);
                                } else {
                                    CommonUIUtils.successNotification(ConstantUtil.NO_RESULTS_FOUND);
                                }
                            }

                        } else if (Constants.DETAILS.equals(String.valueOf(rightSearchType.getValue()))) {
                            if ((StringUtils.isBlank(String.valueOf(rightCfpId.getValue())) || Constants.NULL.equals(String.valueOf(rightCfpId.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightCfpNo.getValue())) || Constants.NULL.equals(String.valueOf(rightCfpNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightCfpName.getValue())) || Constants.NULL.equals(String.valueOf(rightCfpName.getValue())))
                                    && (rightCfpType.getValue() == null || ((HelperDTO) rightCfpType.getValue()).getId() == 0)
                                    && (StringUtils.isBlank(String.valueOf(rightCompanyNo.getValue())) || Constants.NULL.equals(String.valueOf(rightCompanyNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightCompanyName.getValue())) || Constants.NULL.equals(String.valueOf(rightCompanyName.getValue())))
                                    && (rightCompanyType.getValue() == null || ((HelperDTO) rightCompanyType.getValue()).getId() == 0)
                                    && (StringUtils.isBlank(String.valueOf(rightCompanyCategory.getValue())) || Constants.NULL.equals(String.valueOf(rightCompanyCategory.getValue())))) {

                                MessageBox.showPlain(Icon.ERROR, Constants.SEARCH_ERROR, ConstantUtil.PLEASE_ENTER_SEARCH_CRITERIA, ButtonId.OK);
                            } else {
                                getProcessedTree(rightSearchBinder);
                                if (hierarchicalContainer.size() > Constants.ZERO) {
                                    CommonUIUtils.successNotification(ConstantUtil.SEARCH_COMPLETED);
                                } else {
                                    CommonUIUtils.successNotification(ConstantUtil.NO_RESULTS_FOUND);
                                }
                            }
                        }
                    } else if (ContractUtils.IFP_COMPONENT.equals(String.valueOf(rightComponent.getValue()))) {
                        if (Constants.SUMMARY.equals(String.valueOf(rightSearchType.getValue()))) {
                            if ((StringUtils.isBlank(String.valueOf(rightIfpId.getValue())) || Constants.NULL.equals(String.valueOf(rightIfpId.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightIfpNo.getValue())) || Constants.NULL.equals(String.valueOf(rightIfpNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightIfpName.getValue())) || Constants.NULL.equals(String.valueOf(rightIfpName.getValue())))
                                    && (rightIfpType.getValue() == null || ((HelperDTO) rightIfpType.getValue()).getId() == 0)) {

                                MessageBox.showPlain(Icon.ERROR, Constants.SEARCH_ERROR, ConstantUtil.PLEASE_ENTER_SEARCH_CRITERIA, ButtonId.OK);
                            } else {
                                getProcessedTree(rightSearchBinder);
                                if (hierarchicalContainer.size() > Constants.ZERO) {
                                    CommonUIUtils.successNotification(ConstantUtil.SEARCH_COMPLETED);
                                } else {
                                    CommonUIUtils.successNotification(ConstantUtil.NO_RESULTS_FOUND);
                                }
                            }

                        } else if (Constants.DETAILS.equals(String.valueOf(rightSearchType.getValue()))) {
                            if ((StringUtils.isBlank(String.valueOf(rightIfpId.getValue())) || Constants.NULL.equals(String.valueOf(rightIfpId.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightIfpNo.getValue())) || Constants.NULL.equals(String.valueOf(rightIfpNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightIfpName.getValue())) || Constants.NULL.equals(String.valueOf(rightIfpName.getValue())))
                                    && (rightIfpType.getValue() == null || ((HelperDTO) rightIfpType.getValue()).getId() == 0)
                                    && (StringUtils.isBlank(String.valueOf(rightIfpItemNo.getValue())) || Constants.NULL.equals(String.valueOf(rightIfpItemNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightIfpItemName.getValue())) || Constants.NULL.equals(String.valueOf(rightIfpItemName.getValue())))
                                    && (rightIfpTherapeuticClass.getValue() == null || ((HelperDTO) rightIfpTherapeuticClass.getValue()).getId() == 0)
                                    && (rightIfpBrandName.getValue() == null || ((HelperDTO) rightIfpBrandName.getValue()).getId() == 0)) {

                                MessageBox.showPlain(Icon.ERROR, Constants.SEARCH_ERROR, ConstantUtil.PLEASE_ENTER_SEARCH_CRITERIA, ButtonId.OK);
                            } else {
                                getProcessedTree(rightSearchBinder);
                                if (hierarchicalContainer.size() > Constants.ZERO) {
                                    CommonUIUtils.successNotification(ConstantUtil.SEARCH_COMPLETED);
                                } else {
                                    CommonUIUtils.successNotification(ConstantUtil.NO_RESULTS_FOUND);
                                }
                            }
                        }
                    } else if (ContractUtils.PS_COMPONENT.equals(String.valueOf(rightComponent.getValue()))) {
                        if (Constants.SUMMARY.equals(String.valueOf(rightSearchType.getValue()))) {
                            if ((StringUtils.isBlank(String.valueOf(rightPsId.getValue())) || Constants.NULL.equals(String.valueOf(rightPsId.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightPsNo.getValue())) || Constants.NULL.equals(String.valueOf(rightPsNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightPsName.getValue())) || Constants.NULL.equals(String.valueOf(rightPsName.getValue())))
                                    && (rightPsType.getValue() == null || ((HelperDTO) rightPsType.getValue()).getId() == 0)) {

                                MessageBox.showPlain(Icon.ERROR, Constants.SEARCH_ERROR, ConstantUtil.PLEASE_ENTER_SEARCH_CRITERIA, ButtonId.OK);
                            } else {
                                getProcessedTree(rightSearchBinder);
                                if (hierarchicalContainer.size() > Constants.ZERO) {
                                    CommonUIUtils.successNotification(ConstantUtil.SEARCH_COMPLETED);
                                } else {
                                    CommonUIUtils.successNotification(ConstantUtil.NO_RESULTS_FOUND);
                                }
                            }
                        } else if (Constants.DETAILS.equals(String.valueOf(rightSearchType.getValue()))) {
                            if ((StringUtils.isBlank(String.valueOf(rightPsId.getValue())) || Constants.NULL.equals(String.valueOf(rightPsId.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightPsNo.getValue())) || Constants.NULL.equals(String.valueOf(rightPsNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightPsName.getValue())) || Constants.NULL.equals(String.valueOf(rightPsName.getValue())))
                                    && (rightPsType.getValue() == null || ((HelperDTO) rightPsType.getValue()).getId() == 0)
                                    && (StringUtils.isBlank(String.valueOf(rightPsItemNo.getValue())) || Constants.NULL.equals(String.valueOf(rightPsItemNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightPsItemName.getValue())) || Constants.NULL.equals(String.valueOf(rightPsItemName.getValue())))
                                    && (rightPsTherapeuticClass.getValue() == null || ((HelperDTO) rightPsTherapeuticClass.getValue()).getId() == 0)
                                    && (rightPsBrandName.getValue() == null || ((HelperDTO) rightPsBrandName.getValue()).getId() == 0)) {

                                MessageBox.showPlain(Icon.ERROR, Constants.SEARCH_ERROR, ConstantUtil.PLEASE_ENTER_SEARCH_CRITERIA, ButtonId.OK);
                            } else {
                                getProcessedTree(rightSearchBinder);
                                if (hierarchicalContainer.size() > Constants.ZERO) {
                                    CommonUIUtils.successNotification(ConstantUtil.SEARCH_COMPLETED);
                                } else {
                                    CommonUIUtils.successNotification(ConstantUtil.NO_RESULTS_FOUND);
                                }
                            }
                        }
                    } else if (ContractUtils.RS_COMPONENT.equals(String.valueOf(rightComponent.getValue()))) {
                        if (Constants.SUMMARY.equals(String.valueOf(rightSearchType.getValue()))) {
                            if ((StringUtils.isBlank(String.valueOf(rightRsId.getValue())) || Constants.NULL.equals(String.valueOf(rightRsId.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightRsNo.getValue())) || Constants.NULL.equals(String.valueOf(rightRsNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightRsName.getValue())) || Constants.NULL.equals(String.valueOf(rightRsName.getValue())))
                                    && (rightRsType.getValue() == null || ((HelperDTO) rightRsType.getValue()).getId() == 0)) {

                                MessageBox.showPlain(Icon.ERROR, Constants.SEARCH_ERROR, ConstantUtil.PLEASE_ENTER_SEARCH_CRITERIA, ButtonId.OK);
                            } else {
                                getProcessedTree(rightSearchBinder);
                                if (hierarchicalContainer.size() > Constants.ZERO) {
                                    CommonUIUtils.successNotification(ConstantUtil.SEARCH_COMPLETED);
                                } else {
                                    CommonUIUtils.successNotification(ConstantUtil.NO_RESULTS_FOUND);
                                }
                            }
                        } else if (Constants.DETAILS.equals(String.valueOf(rightSearchType.getValue()))) {
                            if ((StringUtils.isBlank(String.valueOf(rightRsId.getValue())) || Constants.NULL.equals(String.valueOf(rightRsId.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightRsNo.getValue())) || Constants.NULL.equals(String.valueOf(rightRsNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightRsName.getValue())) || Constants.NULL.equals(String.valueOf(rightRsName.getValue())))
                                    && (rightRsType.getValue() == null || ((HelperDTO) rightRsType.getValue()).getId() == 0)
                                    && (StringUtils.isBlank(String.valueOf(rightRsItemNo.getValue())) || Constants.NULL.equals(String.valueOf(rightRsItemNo.getValue())))
                                    && (StringUtils.isBlank(String.valueOf(rightRsItemName.getValue())) || Constants.NULL.equals(String.valueOf(rightRsItemName.getValue())))
                                    && (rightRsProgramCategory.getValue() == null || ((HelperDTO) rightRsProgramCategory.getValue()).getId() == 0)
                                    && (rightRsBrandName.getValue() == null || ((HelperDTO) rightRsBrandName.getValue()).getId() == 0)) {

                                MessageBox.showPlain(Icon.ERROR, Constants.SEARCH_ERROR, ConstantUtil.PLEASE_ENTER_SEARCH_CRITERIA, ButtonId.OK);
                            } else {
                                getProcessedTree(rightSearchBinder);
                                if (hierarchicalContainer.size() > Constants.ZERO) {
                                    CommonUIUtils.successNotification(ConstantUtil.SEARCH_COMPLETED);
                                } else {
                                    CommonUIUtils.successNotification(ConstantUtil.NO_RESULTS_FOUND);
                                }
                            }
                        }
                    }

                } catch (FieldGroup.CommitException ex) {
                    LOGGER.error(ex);
                } catch (Exception ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    LOGGER.error(errorMsg);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                }
            }
        });

        processedbtn.setWidth("75");
        processedbtn.setImmediate(true);
        processedbtn.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });
        processedbtn.addClickListener(new Button.ClickListener() {
            @SuppressWarnings("unchecked")
            /**
             * Method used to process button logic and its listener
             */
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    LOGGER.debug(" buttonClick(ClickEvent event) name =" + event.getButton().getCaption());
                    Object value = processTreeTable.getValue();
                    boolean flag = processTreeTable.isSelected(value);
                    if (!flag) {
                        MessageBox.showPlain(Icon.ERROR, "Process Error", "Please select a Contract", ButtonId.OK);
                    } else {
                        navigateToNextView();
                    }
                } catch (SystemException ex) {

                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    LOGGER.error(errorMsg);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                } catch (PortalException ex) {

                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_2011));
                    LOGGER.error(ex);
                }

            }
        });
        final Button disassemblebtn = new Button("Disassemble");
        disassemblebtn.setWidth("100");
        disassemblebtn.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });
        disassemblebtn.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    LOGGER.debug(" buttonClick( ClickEvent event ) name=" + event.getButton().getCaption());
                    if (tableBean == null) {
                        AbstractNotificationUtils.getWarningNotification("Disassemble Criteria", "Select a contract to disassemble");
                    } else {
                        if (tableBean.getCategory().equals(Constants.CONTRACT)) {
                            if (new ContractDashboardLogic().disassemple(tableBean.getSystemId())) {
                                final String successMessage = ContractUtils.DISASSEMBLED_SUCCESS_MSG;
                                Notification.show(successMessage, Notification.Type.HUMANIZED_MESSAGE);
                                ((HierarchicalContainer) processTreeTable.getContainerDataSource()).removeItemRecursively(tableBeanId);
                            } else {
                                final String message = ConstantUtil.THE_SELECTED + tableBean.getCategory() + " - " + tableBean.getMemberId() + " can not be disassembled";
                                AbstractNotificationUtils.getWarningNotification("Cannot Disassemble", message);
                            }
                        } else {
                            final String message = "The selected node is " + tableBean.getCategory() + ". Please select a contract to disassemble";
                            AbstractNotificationUtils.getWarningNotification("Disassemble Criteria", message);
                        }
                    }
                } catch (SystemException ex) {
                    LOGGER.error(ex.getMessage());
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    LOGGER.error(errorMsg);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                } catch (PortalException e) {
                    LOGGER.error(e);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_2006));
                }
            }
        });

        if (!(functionContractDashboardHM.get(CHFunctionNameUtils.REBUILD_BTN) != null) && !((AppPermission) functionContractDashboardHM.get(CHFunctionNameUtils.REBUILD_BTN)).isFunctionFlag()) {
            processResultsLayout.removeComponent(editbtn);
        }

        editbtn.setWidth("75");
        editbtn.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });
        editbtn.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug(" buttonClick(ClickEvent event) name=" + event.getButton().getCaption());
                try {
                    Object value = processTreeTable.getValue();
                    boolean flag = processTreeTable.isSelected(value);
                    if (!flag) {
                        AbstractNotificationUtils.getWarningNotification("Rebuild Criteria", "Select a contract to rebuild");
                    } else {
                        if (tableBean.getCategory().equals(Constants.CONTRACT)) {
                            ContractMaster contract = dashBoardLogic.getContractMaster(tableBean.getInternalId());
                            if (dashBoardLogic.validateContractToProcess(contract)) {
                                MessageBox.showPlain(Icon.QUESTION, Constants.CONFORMATION, ConstantUtil.CONTRACT_SELECTED_HAS_ACTUALS
                                        + ConstantUtil.FORECASTED_SALES_OR_FORECASTED_DISCOUNTS_PROCEED, new MessageBoxListener() {
                                    /**
                                     * Invoked when yes is clicked.
                                     *
                                     * @param event - ClickEvent
                                     */
                                    @SuppressWarnings("PMD")
                                    public void buttonClicked(final ButtonId buttonId) {
                                        if (buttonId.name().equals(Constants.YES)) {
                                            try {
                                                addFrmTableToTree();
                                                if (new ContractDashboardLogic().disassemple(tableBean.getSystemId())) {
                                                    final String successMessage = ContractUtils.REBUILD_SUCCESS_MSG;
                                                    Notification.show(successMessage, Notification.Type.HUMANIZED_MESSAGE);
                                                } else {
                                                    tree.removeAllItems();
                                                    final String message = ConstantUtil.THE_SELECTED + tableBean.getCategory() + " - " + tableBean.getMemberId() + " can not be rebuilt";
                                                    AbstractNotificationUtils.getWarningNotification("Cannot Rebuild", message);
                                                }
                                            } catch (SystemException ex) {
                                                final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                                                LOGGER.error(errorMsg);
                                                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                                            } catch (PortalException ex) {
                                                LOGGER.error(ex);
                                                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_2007));
                                            }
                                        }
                                    }
                                }, ButtonId.YES, ButtonId.NO);
                            } else {
                                MessageBox.showPlain(Icon.QUESTION, util.getHeaderMessage(), util.getRebuildMessage(), new MessageBoxListener() {
                                    /**
                                     * Invoked when yes is clicked.
                                     *
                                     * @param event - ClickEvent
                                     */
                                    @SuppressWarnings("PMD")
                                    public void buttonClicked(final ButtonId buttonId) {
                                        if (buttonId.name().equals(Constants.YES)) {
                                            try {
                                                addFrmTableToTree();
                                                if (new ContractDashboardLogic().disassemple(tableBean.getSystemId())) {
                                                    final String successMessage = ContractUtils.REBUILD_SUCCESS_MSG;
                                                    Notification.show(successMessage, Notification.Type.HUMANIZED_MESSAGE);
                                                } else {
                                                    tree.removeAllItems();
                                                    final String message = ConstantUtil.THE_SELECTED + tableBean.getCategory() + " - " + tableBean.getMemberId() + " can not be rebuilt";
                                                    AbstractNotificationUtils.getWarningNotification("Cannot Rebuild", message);
                                                }
                                            } catch (SystemException ex) {
                                                final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                                                LOGGER.error(errorMsg);
                                                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                                            } catch (PortalException ex) {
                                                LOGGER.error(ex);
                                                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_2007));
                                            }
                                        }
                                    }
                                }, ButtonId.YES, ButtonId.NO);
                            }

                        } else {
                            final String message = "The selected node is " + tableBean.getCategory() + ". Please select a contract to Rebuild";
                            AbstractNotificationUtils.getWarningNotification("Rebuild Criteria", message);
                        }
                    }
                } catch (Exception e) {
                    LOGGER.error(e);
                }

            }
        });

        excelExport.setIcon(new ThemeResource(ExcelExportUtil.EXCEL_EXPORT_IMAGE));
        excelExport.setStyleName("link");
        excelExport.setDescription(Constants.EXCEL_EXPORT);
        excelExport.setIconAlternateText(Constants.EXCEL_EXPORT);
        excelExport.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                try {

                    configureExcelResultTable();
                    if (hierarchicalContainer.size() > Constants.ZERO) {
                        loadExcelResultTable();
                    }
                    VaadinSession.getCurrent().setAttribute(Constants.EXCEL_CLOSE, "true");
                    ExcelExport exp = new ExcelExport(new ExtCustomTableHolder(exceltable), "Contract Dashboard", "Contract Dashboard", "Contract_Dashboard.xls", false);
                    exp.export();
                    hLayout.removeComponent(exceltable);
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
        });
        if (!(functionContractDashboardHM.get(CHFunctionNameUtils.CONTRACT_PROCESSED_SEARCH) != null)
                && !((AppPermission) functionContractDashboardHM.get(CHFunctionNameUtils.CONTRACT_PROCESSED_SEARCH)).isFunctionFlag()) {
            /*added this style to make buttons spaced out*/
            processedbtnSearch.addStyleName(ConstantUtil.BUTTONPADDING);
            processSearchLayout.removeComponent(processedbtnSearch);
        }

        if (!(functionContractDashboardHM.get(CHFunctionNameUtils.SEARCH_RESET_BTN) != null)
                && !((AppPermission) functionContractDashboardHM.get(CHFunctionNameUtils.SEARCH_RESET_BTN)).isFunctionFlag()) {
            /*added this style to make buttons spaced out*/
            lresetbtn.addStyleName(ConstantUtil.BUTTONPADDING);
            processSearchLayout.removeComponent(lresetbtn);
        }

        lresetbtn.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                MessageBox.showPlain(Icon.QUESTION, util.getHeaderMessage(), util.getResetMessage(), new MessageBoxListener() {

                    @Override
                    public void buttonClicked(ButtonId buttonId) {
                        if (buttonId.name().equals(Constants.YES)) {
                            rightComponent.select(ContractUtils.CONTRACT_DETAILS_COMPONENT);
                            getRightBinder();
                            processTreeTable.removeAllItems();
                            hierarchicalContainer.removeAllItems();
                            processTreeTable.setContainerDataSource(hContainer);
                            processTreeTable.setVisibleColumns(visibleColumns);
                            processTreeTable.setColumnHeaders(new String[]{Constants.CATEGORY_HEADER, "ID", "No", "Name"});
                            paginationLayout.removeAllComponents();
                            HorizontalLayout tempLayout = ResponsiveUtils.getResponsiveControls(configureControls());
                            paginationLayout.addComponent(tempLayout);
                            rightSearchType.select(Constants.SUMMARY);
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
            }
        });

        processTreeTable.setSizeFull();
        processTreeTable.setContainerDataSource(hierarchicalContainer);
        processTreeTable.setVisibleColumns(visibleColumns);
        processTreeTable.setColumnHeaders(new String[]{Constants.CATEGORY_HEADER, "ID", "No", "Name"});
        HorizontalLayout tempLayout = ResponsiveUtils.getResponsiveControls(configureControls());
        paginationLayout.addComponent(tempLayout);
        if (!(functionContractDashboardHM.get(CHFunctionNameUtils.PROCESS) != null) && !((AppPermission) functionContractDashboardHM.get(CHFunctionNameUtils.PROCESS)).isFunctionFlag()) {
            processedbtn.addStyleName(ConstantUtil.BUTTONPADDING);
            processResultsLayout.removeComponent(processedbtn);
        }
        LOGGER.debug("End of basic method");
    }

    public void excelExportLogic() throws SystemException, PortalException {
        LOGGER.debug("Entering excelExportLogic operation");

        createWorkSheet();

        LOGGER.debug("Ending excelExportLogic operation");
    }

    private void createWorkSheet() throws SystemException, PortalException {
        try {
            LOGGER.debug("Entering createWorkSheet operation");
            if (ContractUtils.CONTRACT_DETAILS_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
                final long recordCount = table.getContainerDataSource().size();
                ExcelExportforBB.createWorkSheet(table.getColumnHeaders(), recordCount, this, getUI(), "Component_Search");
            } else {
                final long recordCount = detailsTable.getContainerDataSource().size();
                ExcelExportforBB.createWorkSheet(detailsTable.getColumnHeaders(), recordCount, this, getUI(), "Component_Search");
            }
            LOGGER.debug("Ending createWorkSheet operation");
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Creates the work sheet content.
     *
     * @param start the start
     * @param end the end
     * @param printWriter the pw
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter)  {

        DetailSearchDTO searchItemForm;
        ContractMember searchItemFormcon;

        DashboardCriteria criteria = new DashboardCriteria();

        if (ContractUtils.CONTRACT_DETAILS_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
            final List<ContractMember> reultListCon = dashboardSearchLogic.getComponentSearch(leftSearchBinder, String.valueOf(leftComponent.getValue()), criteria, start, end);
            for (int rowCount = 0; rowCount < reultListCon.size(); rowCount++) {

                searchItemFormcon = (ContractMember) reultListCon.get(rowCount);

                printWriter.print(UIUtils.QUOTE + searchItemFormcon.getMemberId() + UIUtils.QUOTE + UIUtils.COMMA);

                printWriter.print(UIUtils.QUOTE + searchItemFormcon.getMemberNo() + UIUtils.QUOTE + UIUtils.COMMA);

                printWriter.print(UIUtils.QUOTE + searchItemFormcon.getName() + UIUtils.QUOTE + UIUtils.COMMA);

                printWriter.println(UIUtils.QUOTE + searchItemFormcon.getMemberType() + UIUtils.QUOTE + UIUtils.COMMA);
            }
        } else {
            DashboardComponentSearchLogic logic = new DashboardComponentSearchLogic();
            final List<DetailSearchDTO> reultList = logic.getComponentDetailsSearch(srcTableBean.getInternalId(), String.valueOf(leftComponent.getValue()), new DetailTableCriteria(), start, end);

            if (ContractUtils.CFP_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
                for (int rowCount = 0; rowCount < reultList.size(); rowCount++) {

                    searchItemForm = (DetailSearchDTO) reultList.get(rowCount);

                    printWriter.print(UIUtils.QUOTE + searchItemForm.getNo() + UIUtils.QUOTE + UIUtils.COMMA);

                    printWriter.print(UIUtils.QUOTE + searchItemForm.getName() + UIUtils.QUOTE + UIUtils.COMMA);

                    printWriter.print(UIUtils.QUOTE + searchItemForm.getType() + UIUtils.QUOTE + UIUtils.COMMA);

                    printWriter.print(UIUtils.QUOTE + searchItemForm.getCategory() + UIUtils.QUOTE + UIUtils.COMMA);

                    printWriter.println(UIUtils.QUOTE + searchItemForm.getStatus() + UIUtils.QUOTE + UIUtils.COMMA);
                }
            } else if (ContractUtils.IFP_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
                for (int rowCount = 0; rowCount < reultList.size(); rowCount++) {

                    searchItemForm = (DetailSearchDTO) reultList.get(rowCount);

                    printWriter.print(UIUtils.QUOTE + searchItemForm.getNo() + UIUtils.QUOTE + UIUtils.COMMA);

                    printWriter.print(UIUtils.QUOTE + searchItemForm.getName() + UIUtils.QUOTE + UIUtils.COMMA);

                    printWriter.print(UIUtils.QUOTE + searchItemForm.getStatus() + UIUtils.QUOTE + UIUtils.COMMA);

                    printWriter.print(UIUtils.QUOTE + (searchItemForm.getStartDate() == null ? StringUtils.EMPTY : mmddyyyy.format(searchItemForm.getStartDate())) + UIUtils.QUOTE + UIUtils.COMMA);

                    printWriter.println(UIUtils.QUOTE + (searchItemForm.getEndDate() == null ? StringUtils.EMPTY : mmddyyyy.format(searchItemForm.getEndDate())) + UIUtils.QUOTE + UIUtils.COMMA);
                }
            } else if (ContractUtils.PS_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
                for (int rowCount = 0; rowCount < reultList.size(); rowCount++) {

                    searchItemForm = (DetailSearchDTO) reultList.get(rowCount);

                    printWriter.print(UIUtils.QUOTE + searchItemForm.getNo() + UIUtils.QUOTE + UIUtils.COMMA);

                    printWriter.print(UIUtils.QUOTE + searchItemForm.getName() + UIUtils.QUOTE + UIUtils.COMMA);

                    printWriter.print(UIUtils.QUOTE + searchItemForm.getStatus() + UIUtils.QUOTE + UIUtils.COMMA);

                    printWriter.print(UIUtils.QUOTE + (searchItemForm.getStartDate() == null ? StringUtils.EMPTY : mmddyyyy.format(searchItemForm.getStartDate())) + UIUtils.QUOTE + UIUtils.COMMA);

                    printWriter.println(UIUtils.QUOTE + (searchItemForm.getEndDate() == null ? StringUtils.EMPTY : mmddyyyy.format(searchItemForm.getEndDate())) + UIUtils.QUOTE + UIUtils.COMMA);
                }
            } else if (ContractUtils.RS_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
                for (int rowCount = 0; rowCount < reultList.size(); rowCount++) {

                    searchItemForm = (DetailSearchDTO) reultList.get(rowCount);

                    printWriter.print(UIUtils.QUOTE + searchItemForm.getNo() + UIUtils.QUOTE + UIUtils.COMMA);

                    printWriter.print(UIUtils.QUOTE + searchItemForm.getName() + UIUtils.QUOTE + UIUtils.COMMA);

                    printWriter.print(UIUtils.QUOTE + searchItemForm.getStatus() + UIUtils.QUOTE + UIUtils.COMMA);

                    printWriter.print(UIUtils.QUOTE + (searchItemForm.getStartDate() == null ? StringUtils.EMPTY : mmddyyyy.format(searchItemForm.getStartDate())) + UIUtils.QUOTE + UIUtils.COMMA);

                    printWriter.println(UIUtils.QUOTE + (searchItemForm.getEndDate() == null ? StringUtils.EMPTY : mmddyyyy.format(searchItemForm.getEndDate())) + UIUtils.QUOTE + UIUtils.COMMA);
                }
            }
        }

    }

    private ExtCustomTreeTable configureExcelResultTable() {
        if (excelContainer != null) {
            excelContainer.removeAllItems();
        }
        exceltable = new ExtFilterTreeTable();
        hLayout.addComponent(exceltable);
        exceltable.setVisible(false);
        exceltable.setContainerDataSource(excelContainer);
        exceltable.setVisibleColumns(visibleColumns);
        exceltable.setColumnHeaders(new String[]{Constants.CATEGORY_HEADER, "ID", "No", "Name"});
        return exceltable;
    }

    /**
     * Loads the Excel Results.
     */
    @SuppressWarnings("serial")
    private void loadExcelResultTable() throws SystemException, PortalException {
        List<ContractMember> resultList = null;
        excelContainer.removeAllItems();
        resultList = cdbLogic.getContractList(rightSearchBinder, ContractMember.LEVEL1, 0, 0);
        loadDataToContainer(resultList, null);

    }

    public void loadDataToContainer(List<ContractMember> resultList, Object parentId) throws SystemException, PortalException {
        for (ContractMember dto : resultList) {
            excelContainer.addBean(dto);
            if (parentId != null) {
                excelContainer.setParent(dto, parentId);
            }
            int levelNo = dto.getLevel();
            if (!Constants.RS_VALUE.equals(dto.getCategory()) && ((levelNo == 1 && cdbLogic.isLevel2ListAvlbl(dto.getSystemId(), dto.getCategory()))
                    || (levelNo == NumericConstants.TWO && cdbLogic.isLevel3ListAvlbl(dto.getSystemId(), dto.getCategory()))
                    || (levelNo == NumericConstants.THREE && cdbLogic.isLevel4ListAvlbl(dto.getSystemId(), dto.getCategory()))
                    || (levelNo == NumericConstants.FOUR && cdbLogic.isLevel5ListAvlbl(dto.getSystemId())))) {
                excelContainer.setChildrenAllowed(dto, true);
                addLowerLevelsForExport(dto, levelNo + 1);
            } else {
                excelContainer.setChildrenAllowed(dto, false);
            }
        }
    }

    /**
     *
     * @param resultList
     * @param parentId
     * @throws SystemException
     * @throws PortalException
     */
    public void loadDataToTree(List<ContractMember> resultList, Object parentId) throws SystemException, PortalException {
        for (ContractMember dto : resultList) {
            tree.addItem(dto);
            tree.setItemCaption(dto, dto.getCategory() + Constants.DASH + dto.getMemberId() + Constants.DASH + dto.getMemberNo() + Constants.DASH + dto.getName());
            if (parentId != null) {
                tree.setParent(dto, parentId);
                ContractMember object = getBeanFromId(parentId);
                dto.setContractChild(Constants.CONTRACT.equals(object.getCategory()));

            }
            int levelNo = dto.getLevel();
            if (!Constants.RS_VALUE.equals(dto.getCategory())) {
                tree.setChildrenAllowed(dto, true);
                addLowerLevelsForTree(dto, levelNo + 1);
            } else {
                tree.setChildrenAllowed(dto, false);
            }
        }
    }

    /**
     * Adding lower Levels to excel
     *
     * @param id
     * @param tab
     */
    public void addLowerLevelsForExport(Object id, int levelNo) throws SystemException, PortalException {
        List<ContractMember> resultList = null;
        ContractMember dto = (ContractMember) id;
        if (levelNo == NumericConstants.TWO) {
            resultList = cdbLogic.getLevel2List(dto, 0, 0, false);
        } else if (levelNo == NumericConstants.THREE) {
            resultList = cdbLogic.getLevel3List(dto.getParent1(), dto, 0, 0, false);
        } else if (levelNo == NumericConstants.FOUR) {
            resultList = cdbLogic.getLevel4List(dto.getParent1(), dto.getParent2(), dto, 0, 0, false);
        } else if (levelNo == NumericConstants.FIVE) {
            resultList = cdbLogic.getLevel5List(dto.getParent1(), dto.getParent2(), dto.getParent3(), dto, 0, 0, false);
        }
        loadDataToContainer(resultList, id);
    }

    /**
     *
     * @param id
     * @param levelNo
     * @throws SystemException
     * @throws PortalException
     */
    public void addLowerLevelsForTree(Object id, int levelNo) throws SystemException, PortalException {
        List<ContractMember> resultList = null;
        ContractMember dto = (ContractMember) id;
        if (levelNo == NumericConstants.TWO) {
            resultList = cdbLogic.getLevel2List(dto, 0, 0, false);
        } else if (levelNo == NumericConstants.THREE) {
            resultList = cdbLogic.getLevel3List(dto.getParent1(), dto, 0, 0, false);
        } else if (levelNo == NumericConstants.FOUR) {
            resultList = cdbLogic.getLevel4List(dto.getParent1(), dto.getParent2() == null ? dto.getParent3() : dto.getParent2(), dto, 0, 0, false);
        } else if (levelNo == NumericConstants.FIVE) {
            resultList = cdbLogic.getLevel5List(dto.getParent1(), dto.getParent2(), dto.getParent3(), dto, 0, 0, false);
        }
        loadDataToTree(resultList, id);
    }

    /**
     * Load table values.
     *
     * @param flagId the flag id
     * @param flag the flag
     */
    protected void loadTableValues(CustomFieldGroup leftSearchBinder) {
        LOGGER.debug("inside DashBoardForm loadTableValues");
        if (leftSearchBinder != null) {
            searchResults = new LazyBeanItemContainer(ContractMember.class, new DashboardSearchDAO(leftSearchBinder, String.valueOf(leftComponent.getValue())), new DashboardCriteria());
            table.setContainerDataSource(searchResults);
            if (searchResults.size() > Constants.ZERO) {
                CommonUIUtils.successNotification(ConstantUtil.SEARCH_COMPLETED);

            } else {
                CommonUIUtils.successNotification(ConstantUtil.NO_RESULTS_FOUND);

            }
        } else {
            table.setContainerDataSource(tContainer);

        }
        table.setPageLength(NumericConstants.SIX);
        table.setImmediate(true);
        table.setVisibleColumns(new Object[]{Constants.MEMBER_ID, Constants.MEMBER_NO, Constants.NAME, Constants.MEMBER_TYPE});
        table.setColumnHeaders(Constants.CONTRACT_ID1, Constants.CONTRACT_NO_SP, Constants.CONTRACT_NAME1, Constants.CONTRACT_TYPE_HEADER);
        changeTableHeader(String.valueOf(leftComponent.getValue()));
        table.setSelectable(true);
        table.setDragMode(TableDragMode.ROW);
        table.setFilterBarVisible(true);
        table.addStyleName("valo-theme-extfiltertable");
        table.addStyleName("filterbar");
        table.setFilterDecorator(new ExtDemoFilterDecorator());
        table.setSizeFull();
        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            @SuppressWarnings("PMD")
            public void itemClick(final ItemClickEvent event) {

                srcTableBeanId = event.getItemId();
                BeanItem<?> targetItem;
                if (srcTableBeanId instanceof BeanItem<?>) {
                    targetItem = (BeanItem<?>) srcTableBeanId;
                } else if (srcTableBeanId instanceof ContractMember) {
                    targetItem = new BeanItem<>((ContractMember) srcTableBeanId);
                } else {
                    targetItem = NULL_OBJECT;
                }
                srcTableBean = (ContractMember) targetItem.getBean();
                if (srcTableBean != null) {
                    if (ContractUtils.CFP_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
                        detailSearchResults = new LazyBeanItemContainer(DetailSearchDTO.class, new DetailTableDAO(srcTableBean.getInternalId(), String.valueOf(leftComponent.getValue())), new DetailTableCriteria());
                        detailsTable.setContainerDataSource(detailSearchResults);
                        detailsTable.setVisibleColumns(new Object[]{"no", "name", "type", "category", ConstantUtil.STATUS_PROPERTY});
                        detailsTable.setColumnHeaders(new String[]{"Company No", "Company Name", "Company Type", "Company Category", "CFP Status"});
                    } else if (ContractUtils.IFP_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
                        detailSearchResults = new LazyBeanItemContainer(DetailSearchDTO.class, new DetailTableDAO(srcTableBean.getInternalId(), String.valueOf(leftComponent.getValue())), new DetailTableCriteria());
                        detailsTable.setContainerDataSource(detailSearchResults);
                        detailsTable.setVisibleColumns(new Object[]{"no", "name", ConstantUtil.STATUS_PROPERTY, Constants.START_DATE, Constants.END_DATE});
                        detailsTable.setColumnHeaders(new String[]{Constants.ITEM_NO2, Constants.ITEM_NAME2, "IFP Status", "IFP Start Date", "IFP End Date"});
                    } else if (ContractUtils.PS_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
                        detailSearchResults = new LazyBeanItemContainer(DetailSearchDTO.class, new DetailTableDAO(srcTableBean.getInternalId(), String.valueOf(leftComponent.getValue())), new DetailTableCriteria());
                        detailsTable.setContainerDataSource(detailSearchResults);
                        detailsTable.setVisibleColumns(new Object[]{"no", "name", ConstantUtil.STATUS_PROPERTY, Constants.START_DATE, Constants.END_DATE});
                        detailsTable.setColumnHeaders(new String[]{Constants.ITEM_NO2, Constants.ITEM_NAME2, "PS Status", "PS Start Date", "PS End Date"});
                    } else if (ContractUtils.RS_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
                        detailSearchResults = new LazyBeanItemContainer(DetailSearchDTO.class, new DetailTableDAO(srcTableBean.getInternalId(), String.valueOf(leftComponent.getValue())), new DetailTableCriteria());
                        detailsTable.setContainerDataSource(detailSearchResults);
                        detailsTable.setVisibleColumns(new Object[]{"no", "name", ConstantUtil.STATUS_PROPERTY, Constants.START_DATE, Constants.END_DATE});
                        detailsTable.setColumnHeaders(new String[]{Constants.ITEM_NO2, Constants.ITEM_NAME2, "RS Status", "RS Start Date", "RS End Date"});

                    }
                    detailsTable.setFilterGenerator(new DetailComponentFilterGenerator(String.valueOf(leftComponent.getValue())));
                }
            }
        });

    }

    private void loadDetailTable() {
        detailPanel.setImmediate(true);
        detailPanel.setVisible(false);
        detailsTable.setContainerDataSource(detailNormalContainer);
        detailsTable.setVisible(false);
        detailsTable.setPageLength(NumericConstants.SIX);
        detailsTable.setSelectable(false);
        detailsTable.setFilterBarVisible(true);
        detailsTable.addStyleName("valo-theme-extfiltertable");
        detailsTable.addStyleName("filterbar");
        detailsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        detailsTable.setSizeFull();
    }

    /**
     * Gets the droppable tree.
     *
     * @return the droppable tree
     */
    public void getDroppableTree() {
        LOGGER.debug("Entering getDroppableTree");
        tree.markAsDirty();
        tree.setImmediate(true);
        tree.setSizeFull();
        tree.setHeight(TREE_HEIGHT);
        tree.setWidth(99f, Sizeable.Unit.PERCENTAGE);
        tree.setContainerDataSource(createTreeContent());
        tree.setItemCaptionPropertyId(Constants.NAME);
        tree.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
        tree.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            @SuppressWarnings("PMD")
            public void itemClick(final ItemClickEvent event) {
                treeBeanId = event.getItemId();
                BeanItem<?> targetItem;
                if (treeBeanId instanceof BeanItem<?>) {
                    targetItem = (BeanItem<?>) treeBeanId;
                } else if (treeBeanId instanceof ContractMember) {
                    targetItem = new BeanItem<>((ContractMember) treeBeanId);
                } else {

                    targetItem = NULL_OBJECT;
                }
                treeBean = (ContractMember) targetItem.getBean();
                LOGGER.debug("Start date = = =  =>" + treeBean.getStartDate());
                LOGGER.debug("Start date = = =  =>" + treeBean.getEndDate());
                LOGGER.debug("category date = = =  =>" + treeBean.getCategory());
                LOGGER.debug("Name date = = =  =>" + treeBean.getName());
            }
        });
        final Object[] array = tree.getItemIds().toArray();
        for (int i = 0; i < array.length; i++) {
            final Object item = array[i];
            tree.setItemCaption(item, (String) ((BeanItem<?>) item).getItemProperty(Constants.NAME).getValue());
        }

        // Expand all items
        for (final Iterator<?> it = tree.rootItemIds().iterator(); it.hasNext();) {
            tree.expandItemsRecursively(it.next());
        }

        // Set the tree in drag source mode
        tree.setDragMode(TreeDragMode.NODE);

        // Allow the tree to receive drag drops and handle them
        tree.setDropHandler(new DropHandler() {
            /**
             * Invoked in tree to receive drag drops.
             */
            public AcceptCriterion getAcceptCriterion() {
                // Accept drops in the middle of container items
                // and below and above all items.
                return new Or(Tree.TargetItemAllowsChildren.get(), new Not(VerticalLocationIs.MIDDLE));
            }

            /**
             * This method is to called drop
             */
            public void drop(final DragAndDropEvent event) {
                // Wrapper for the object that is dragged
                final DataBoundTransferable tran = (DataBoundTransferable) event.getTransferable();

                final TreeTargetDetails target = (TreeTargetDetails) event.getTargetDetails();

                // Get ids of the dragged item and the target item
                final Object sourceItemId = tran.getData(Constants.ITEM_ID);
                final Object targetItemId = target.getItemIdOver();

                // On which side of the target the item was dropped
                final VerticalDropLocation location = target.getDropLocation();

                BeanItem<?> beanItem = null;
                BeanItem<?> targetItem = null;

                if (sourceItemId instanceof BeanItem<?>) {
                    beanItem = (BeanItem<?>) sourceItemId;
                } else if (sourceItemId instanceof ContractMember) {
                    beanItem = new BeanItem<>((ContractMember) sourceItemId);
                }

                if (targetItemId instanceof BeanItem<?>) {
                    targetItem = (BeanItem<?>) targetItemId;
                } else if (targetItemId instanceof ContractMember) {
                    targetItem = new BeanItem<>((ContractMember) targetItemId);
                }

                final ContractMember bean = (ContractMember) beanItem.getBean();
                try {
                    if (!validateEndStart(sourceItemId, targetItemId)) {
                        return;
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
                if (targetItem == null) {
                    if (bean.getCategory().equals(Constants.CONTRACT)) {
                        setTreeNode(beanItem, bean, VerticalDropLocation.MIDDLE, targetItemId);
                    } else {
                        final String message = Constants.CANNOT_MAKE + bean.getCategory() + " as contracts header";
                        AbstractNotificationUtils.getWarningNotification(ConstantUtil.CRITERIA_MISMATCH, message);
                    }
                } else {
                    tree.setHeight(99f, Sizeable.Unit.PERCENTAGE);
                    if (bean.getCategory().equals(Constants.CONTRACT)) {
                        final String message = Constants.CANNOT_MAKE + bean.getCategory() + " as child node";
                        AbstractNotificationUtils.getWarningNotification(ConstantUtil.CRITERIA_MISMATCH, message);
                    } else {
                        final ContractMember targetBean = (ContractMember) targetItem.getBean();
                        if (bean.getCategory().equals(targetBean.getCategory())) {
                            final String message = bean.getCategory() + " cannot be added to  " + targetBean.getCategory();
                            AbstractNotificationUtils.getWarningNotification(ConstantUtil.CRITERIA_MISMATCH, message);
                        } else {
                            try {
                                if (bean.getCategory().equals(Constants.PS_VALUE) && targetBean.getCategory().equals(Constants.IFP) && nodeDuplicateCheck()) {
                                    final DynamicQuery psDynamicQuery = DynamicQueryFactoryUtil.forClass(PsDetails.class);

                                    psDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.PS_MODEL_SID, bean.getInternalId()));
                                    psDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantUtil.INBOUND_STATUS, "D"));
                                    psDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(Constants.IFP_SYSTEM_ID)));
                                    final List<PsDetails> priceScheduleDetailsList = dashBoardLogic.priceScheduleDetailsDynamicQuery(psDynamicQuery);
                                    if (priceScheduleDetailsList.isEmpty()) {
                                        AbstractNotificationUtils.getWarningNotification(ConstantUtil.NO_ITEMS, "No items Exists in PS");
                                    } else {
                                        final String psSystem = String.valueOf(priceScheduleDetailsList.get(0)).trim();
                                        if (psSystem.equals(String.valueOf(targetBean.getModelSysId()).trim())) {

                                            if (Constants.CONTRACT.equals(getBeanFromID(targetItemId).getCategory())) {
                                                getBeanFromID(targetItem).setContractId(getBeanFromID(targetItemId).getInternalId());
                                                getBeanFromID(beanItem).setContractId(getBeanFromID(targetItemId).getInternalId()); // Added newly to get contractId to avoid hierarchy duplication
                                                getBeanFromID(beanItem).setContractChild(true);
                                            } else {
                                                getBeanFromID(beanItem).setContractId(getBeanFromID(targetItemId).getContractId());
                                            }
                                            setTreeNode(beanItem, bean, location, targetItemId);
                                        } else {
                                            final String message = bean.getCategory() + ConstantUtil.DOES_NOT_ASSOCIATE_WITH + targetBean.getCategory();
                                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, message);
                                        }
                                    }
                                } else if (bean.getCategory().equals(Constants.RS_VALUE) && targetBean.getCategory().equals(Constants.IFP) && nodeDuplicateCheck()) {
                                    LOGGER.debug("Inside Expected Code");
                                    final DynamicQuery rsDynamicQuery = DynamicQueryFactoryUtil.forClass(RsDetails.class);
                                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.RS_MODEL_SID, bean.getInternalId()));
                                    rsDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantUtil.INBOUND_STATUS, "D"));
                                    rsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(Constants.IFP_SYSTEM_ID)));
                                    final List<RsDetails> rebateScheduleDetailsList = dashBoardLogic.priceScheduleDetailsDynamicQuery(rsDynamicQuery);
                                    if (rebateScheduleDetailsList.isEmpty()) {

                                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, ConstantUtil.NO_ITEMS_EXISTS_IN_RS);
                                    } else {
                                        final String rsSystem = String.valueOf(rebateScheduleDetailsList.get(0)).trim();
                                        if (rsSystem.equals(String.valueOf(targetBean.getModelSysId()).trim())) {
                                            if (Constants.CONTRACT.equals(getBeanFromID(targetItemId).getCategory())) {
                                                getBeanFromID(targetItem).setContractId(getBeanFromID(targetItemId).getInternalId());
                                                getBeanFromID(beanItem).setContractId(getBeanFromID(targetItemId).getInternalId()); // Added newly to get contractId to avoid hierarchy duplication
                                                getBeanFromID(beanItem).setContractChild(true);
                                            } else {
                                                getBeanFromID(beanItem).setContractId(getBeanFromID(targetItemId).getContractId());
                                            }
                                            setTreeNode(beanItem, bean, location, targetItemId);
                                        } else {
                                            final String message = bean.getCategory() + ConstantUtil.DOES_NOT_ASSOCIATE_WITH + targetBean.getCategory();
                                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, message);
                                        }
                                    }
                                } else if (bean.getCategory().equals(Constants.RS_VALUE) && targetBean.getCategory().equals(Constants.PS_VALUE) && nodeDuplicateCheck()) {

                                    final DynamicQuery rsDynamicQuery = DynamicQueryFactoryUtil.forClass(RsDetails.class);

                                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.RS_MODEL_SID, bean.getInternalId()));
                                    rsDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantUtil.INBOUND_STATUS, "D"));
                                    rsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(Constants.IFP_SYSTEM_ID)));
                                    final List<RsDetails> rebateScheduleDetailsList = dashBoardLogic.priceScheduleDetailsDynamicQuery(rsDynamicQuery);
                                    if (rebateScheduleDetailsList.isEmpty()) {

                                        AbstractNotificationUtils.getWarningNotification(ConstantUtil.NO_ITEMS, ConstantUtil.NO_ITEMS_EXISTS_IN_RS);
                                    } else {
                                        final String rsSystem = String.valueOf(rebateScheduleDetailsList.get(0)).trim();

                                        final DynamicQuery psDynamicQuery = DynamicQueryFactoryUtil.forClass(PsDetails.class);
                                        psDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.PS_MODEL_SID, targetBean.getModelSysId()));
                                        psDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantUtil.INBOUND_STATUS, "D"));
                                        psDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(Constants.IFP_SYSTEM_ID)));
                                        final List<PsDetails> priceScheduleDetailsList = dashBoardLogic.priceScheduleDetailsDynamicQuery(psDynamicQuery);

                                        if (rsSystem.equals(String.valueOf(priceScheduleDetailsList.get(0)).trim())) {
                                            if (Constants.CONTRACT.equals(getBeanFromID(targetItemId).getCategory())) {
                                                getBeanFromID(targetItem).setContractId(getBeanFromID(targetItemId).getInternalId());
                                                getBeanFromID(beanItem).setContractId(getBeanFromID(targetItemId).getInternalId()); // Added newly to get contractId to avoid hierarchy duplication
                                                getBeanFromID(beanItem).setContractChild(true);
                                            } else {
                                                getBeanFromID(beanItem).setContractId(getBeanFromID(targetItemId).getContractId());
                                            }
                                            setTreeNode(beanItem, bean, location, targetItemId);
                                        } else {
                                            final String message = bean.getCategory() + ConstantUtil.DOES_NOT_ASSOCIATE_WITH + targetBean.getCategory();
                                            AbstractNotificationUtils.getWarningNotification(Constants.ERROR, message);
                                        }
                                    }
                                } else {
                                    Object rootItemID = getRootContract(targetItemId);
                                    if (tree.hasChildren(rootItemID == null ? treeBeanId : rootItemID)) {
                        childList.clear();
                        final Collection<?> collection = tree.getChildren(treeBeanId );
                        if (collection != null) {
                            for (final Object id : collection) {
                                final ContractMember object = getBeanFromId(id);
                                if (srcTableBean.getInternalId() == object.getModelSysId() && srcTableBean.getCategory().equals(object.getCategory())) {
                                    if ("CFP".equals(srcTableBean.getCategory())) {
                                        final String message = srcTableBean.getCategory() + ConstantUtil.ALREADY_ADDED;
                                        AbstractNotificationUtils.getWarningNotification(ConstantUtil.DUPLICATE_CRITERIA, message);
                                        return ;
                                    } else {
                                        final String message = srcTableBean.getCategory() + ConstantUtil.ALREADY_ADDED_IFP + treeBean.getCategory();
                                        AbstractNotificationUtils.getWarningNotification(ConstantUtil.DUPLICATE_CRITERIA, message);
                                        return ;
                                    }
                                }
                            }
                        }
                    }
                                    if (Constants.CONTRACT.equals(getBeanFromID(targetItemId).getCategory())) {
                                        getBeanFromID(targetItem).setContractId(getBeanFromID(targetItemId).getInternalId());
                                        getBeanFromID(beanItem).setContractId(getBeanFromID(targetItemId).getInternalId()); // Added newly to get contractId to avoid hierarchy duplication
                                        getBeanFromID(beanItem).setContractChild(true);
                                    } else {
                                        getBeanFromID(beanItem).setContractId(getBeanFromID(targetItemId).getContractId());
                                    }
                                    setTreeNode(beanItem, bean, location, targetItemId);

                                }
                            } catch (Exception e) {
                                LOGGER.error(e);
                            }
                        }

                    }
                }
            }
        });
        LOGGER.debug("End of getDropableTable method");
    }

    /**
     * Creates the tree content.
     *
     * @return the hierarchical container
     */
    public HierarchicalContainer createTreeContent() {
        final HierarchicalContainer container = new HierarchicalContainer();
        LOGGER.debug("Entering createTreeContent method");
        container.addContainerProperty(Constants.NAME, String.class, null);
        container.addContainerProperty(Constants.CATEGORY, String.class, null);
        LOGGER.debug("End of createTreeContent method");
        return container;
    }

    /**
     * Sets the tree node.
     *
     * @param sourceItemId the source item id
     * @param beanItem the bean item
     * @param bean the bean
     * @param location the location
     * @param targetItemId the target item id
     * @param sourceContainer the source container
     */
    private void setTreeNode(final Object beanItem, final ContractMember bean, final VerticalDropLocation location, final Object targetItemId) {
        try {
            LOGGER.debug("Entering setTreeNode method");

            if (location == VerticalDropLocation.MIDDLE) {

                final String dommyId = bean.getCategory() + Constants.DASH + bean.getMemberId() + Constants.DASH + bean.getMemberNo() + Constants.DASH + bean.getName();
                final Collection list = tree.rootItemIds();
                boolean flag = false;
                for (final Iterator iterator = list.iterator(); iterator.hasNext();) {
                    final Object idValue = iterator.next();
                    final ContractMember availableContract = getBeanFromId(idValue);
                    final String treeCaption = availableContract.getCategory() + Constants.DASH + availableContract.getMemberId() + Constants.DASH + availableContract.getMemberNo() + Constants.DASH + availableContract.getName();
                    if (treeCaption.equals(dommyId)) {
                        flag = true;
                    }
                }
                if (flag) {
                    AbstractNotificationUtils.getWarningNotification("Duplicate Contract ID", "Selected Contract ID is already exist");
                } else {
                    tree.addItem(beanItem);
                    tree.setChildrenAllowed(beanItem, bean.isChildrenAllowed());
                    tree.setParent(beanItem, targetItemId);
                    tree.setItemCaption(beanItem, bean.getCategory() + Constants.DASH + bean.getMemberId() + Constants.DASH + bean.getMemberNo() + Constants.DASH + bean.getName());
                    tree.expandItem(targetItemId);
                }

            } // Drop at the top of a subtree -> make it previous
            else if (location == VerticalDropLocation.TOP) {
                AbstractNotificationUtils.getWarningNotification("Drop Criteria", "Drop the child node on the parent node");
                return;
            } // Drop below another item -> make it next
            else if (location == VerticalDropLocation.BOTTOM) {
                AbstractNotificationUtils.getWarningNotification("Drop Criteria", "Drop the child node on the parent node");
                return;
            }
            LOGGER.debug("End of setTreeNode method");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Sets the processed table header.
     */
    private void setProcessedTableHeader() {
        LOGGER.debug("Entering setProcessedTableHeader method");
        processTreeTable.setVisibleColumns(visibleColumns);
        processTreeTable.setColumnHeaders(new String[]{Constants.CATEGORY_HEADER, "ID", "No", "Name"});
        LOGGER.debug("End of setProcessedTableHeader method");
    }

    private Collection<Object> getAllChildren(Object rootItemID) {
        if (tree.hasChildren(rootItemID)) {
            final Collection<?> collection = tree.getChildren(rootItemID);
            for (Object collection1 : collection) {
                childList.add(collection1);
                getAllChildren(collection1);
            }
        }
        return childList;
    }

    private Object getRootContract(Object treeBeanId) {
        Object rootItemID = null;
        final Collection list = tree.rootItemIds();
        for (final Iterator iterator = list.iterator(); iterator.hasNext();) {
            final Object idValue = iterator.next();
            final ContractMember availableContract = getBeanFromId(idValue);
            if (availableContract.getModelSysId() == getBeanFromID(treeBeanId).getContractId()) {
                rootItemID = idValue;
                break;
            }
        }
        return rootItemID;
    }

    /**
     * The Class StplCollapseListener.
     *
     * @see StplCollapseEvent
     */
    class StplCollapseListener implements CollapseListener {

        /**
         * The Constant serialVersionUID.
         */
        private static final long serialVersionUID = 1L;
        /**
         * The contract dashboard logic.
         */
        private final ContractDashboardLogic contractDashboardLogic = new ContractDashboardLogic();

        /**
         * Gets the contract dashboard logic.
         *
         * @return the contract dashboard logic
         */
        public ContractDashboardLogic getContractDashboardLogic() {
            return contractDashboardLogic;
        }

        /**
         * Method used to node collapse and its event.
         *
         * @param event the event
         */
        public void nodeCollapse(final CollapseEvent event) {
            try {
                LOGGER.debug("Entering StplCollapseListener nodeCollapse method");

                contractMember = (ContractMember) event.getItemId();
                switch (contractMember.getLevel()) {
                    case ContractMember.LEVEL1:
                        levelValue = 0;
                        setTotalAmountOfPages(cdbLogic.getRightQueriedCount(rightSearchBinder));
                        hierarchicalContainer = contractDashboardLogic.getLevel1Hierarchy(rightSearchBinder, hierarchicalContainer, 0, NumericConstants.FIFTEEN);
                        setProcessedTableHeader();
                        break;
                    case ContractMember.LEVEL2:
                        levelValue = 1;
                        setTotalAmountOfPages(cdbLogic.getCFPQueriedCount(contractMember.getSystemId()));
                        processTreeTable.setContainerDataSource(contractDashboardLogic.getLevel2Hierarchy(contractMember.getParent1(), hierarchicalContainer, 0, tempPageLength));
                        processTreeTable.removeExpandListener(expandListener);
                        setProcessedTableHeader();
                        processTreeTable.setCollapsed(contractMember.getParent1(), false);
                        processTreeTable.addExpandListener(expandListener);
                        break;
                    case ContractMember.LEVEL3:
                        levelValue = NumericConstants.TWO;
                        setTotalAmountOfPages(cdbLogic.getIFPCount(contractMember.getSystemId(), contractMember.getParent2().getInternalId()));
                        processTreeTable.setContainerDataSource(contractDashboardLogic.getLevel3Hierarchy(contractMember.getParent2(), hierarchicalContainer, 0, tempPageLength - NumericConstants.TWO));
                        processTreeTable.removeExpandListener(expandListener);
                        setProcessedTableHeader();
                        processTreeTable.setCollapsed(contractMember.getParent1(), false);
                        processTreeTable.setCollapsed(contractMember.getParent2(), false);
                        processTreeTable.addExpandListener(expandListener);
                        break;
                    case ContractMember.LEVEL4:
                        levelValue = NumericConstants.THREE;
                        setTotalAmountOfPages(cdbLogic.getPSCount(contractMember.getSystemId(), contractMember.getParent2().getInternalId(), contractMember.getParent3().getInternalId()));
                        processTreeTable.setContainerDataSource(contractDashboardLogic.getLevel4Hierarchy(contractMember.getParent3(), hierarchicalContainer, 0, tempPageLength - NumericConstants.THREE));
                        processTreeTable.removeExpandListener(expandListener);
                        setProcessedTableHeader();
                        processTreeTable.setCollapsed(contractMember.getParent1(), false);
                        processTreeTable.setCollapsed(contractMember.getParent2(), false);
                        processTreeTable.setCollapsed(contractMember.getParent3(), false);
                        processTreeTable.addExpandListener(expandListener);
                        break;
                    default:
                        break;
                }
                int temp = parentList.indexOf(event.getItemId());
                if (temp != -1) {
                    for (int i = temp; i < parentList.size();) {
                        parentList.remove(i);
                    }
                }
                currentPageTextField.setValue("1");
                LOGGER.debug("End of StplCollapseListener nodeCollapse method");
            } catch (SystemException ex) {
                final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                LOGGER.error(errorMsg);
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
            } catch (PortalException ex) {
                LOGGER.error(ex);
            }

        }
    }

    /**
     * The Class StplExpandListener.
     *
     * @see StplExpandEvent
     */
    class StplExpandListener implements ExpandListener {

        /**
         * The Constant serialVersionUID.
         */
        private static final long serialVersionUID = 1L;
        /**
         * The contract dashboard logic.
         */
        private final ContractDashboardLogic contractDashboardLogic = new ContractDashboardLogic();

        /**
         * Gets the contract dashboard logic.
         *
         * @return the contract dashboard logic
         */
        public ContractDashboardLogic getContractDashboardLogic() {
            return contractDashboardLogic;
        }

        /**
         * Node Expand Event
         *
         */
        public void nodeExpand(final ExpandEvent event) {
            try {
                LOGGER.debug("Entering StplExpandListener nodeExpand method");

                contractMember = (ContractMember) event.getItemId();
                switch (contractMember.getLevel()) {
                    case ContractMember.LEVEL1:
                        configureLevel(event.getItemId());
                        int i = cdbLogic.getCFPQueriedCount(contractMember.getSystemId());

                        setTotalAmountOfPages(i);
                        hierarchicalContainer = contractDashboardLogic.getLevel2Hierarchy(contractMember, hierarchicalContainer, 0, tempPageLength);
                        setProcessedTableHeader();

                        break;
                    case ContractMember.LEVEL2:
                        configureLevel(event.getItemId());
                        setTotalAmountOfPages(cdbLogic.getIFPCount(contractMember.getSystemId(), contractMember.getInternalId()));
                        processTreeTable.setContainerDataSource(contractDashboardLogic.getLevel3Hierarchy(contractMember, hierarchicalContainer, 0, tempPageLength - NumericConstants.TWO));
                        processTreeTable.removeExpandListener(expandListener);
                        processTreeTable.setCollapsed(contractMember.getParent1(), false);
                        processTreeTable.setCollapsed(contractMember, false);
                        processTreeTable.addExpandListener(expandListener);
                        setProcessedTableHeader();
                        break;
                    case ContractMember.LEVEL3:
                        configureLevel(event.getItemId());
                        setTotalAmountOfPages(cdbLogic.getPSCount(contractMember.getSystemId(), contractMember.getParent2().getInternalId(), contractMember.getInternalId()));
                        processTreeTable.setContainerDataSource(contractDashboardLogic.getLevel4Hierarchy(contractMember, hierarchicalContainer, 0, tempPageLength - NumericConstants.THREE));
                        processTreeTable.removeExpandListener(expandListener);
                        processTreeTable.setCollapsed(contractMember.getParent1(), false);
                        processTreeTable.setCollapsed(contractMember.getParent2(), false);
                        processTreeTable.setCollapsed(contractMember, false);
                        processTreeTable.addExpandListener(expandListener);
                        setProcessedTableHeader();
                        break;
                    case ContractMember.LEVEL4:
                        configureLevel(event.getItemId());
                        setTotalAmountOfPages(cdbLogic.getRSCount(contractMember.getSystemId(), contractMember.getParent2().getInternalId(), contractMember.getParent3().getInternalId(), contractMember.getInternalId()));
                        processTreeTable.setContainerDataSource(contractDashboardLogic.getLevel5Hierarchy(contractMember, hierarchicalContainer, 0, tempPageLength - NumericConstants.FOUR));
                        processTreeTable.removeExpandListener(expandListener);
                        processTreeTable.setCollapsed(contractMember.getParent1(), false);
                        processTreeTable.setCollapsed(contractMember.getParent2(), false);
                        processTreeTable.setCollapsed(contractMember.getParent3(), false);
                        processTreeTable.setCollapsed(contractMember, false);
                        processTreeTable.addExpandListener(expandListener);
                        setProcessedTableHeader();
                        break;
                    default:
                        break;
                }
                currentPageTextField.setValue("1");
                LOGGER.debug("End of StplExpandListener nodeExpand method");
            } catch (SystemException ex) {
                final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                LOGGER.error(errorMsg);
                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
            } catch (PortalException ex) {
                LOGGER.error(ex);
            }

        }
    }

    /**
     * Gets the processed tree.
     *
     * @return the processed tree
     */
    private void getProcessedTree(CustomFieldGroup rightSearchBinder) throws SystemException {
        LOGGER.debug("Entering getProcessedTree method");
        final ContractDashboardLogic contractDashboardLogic = new ContractDashboardLogic();
        processTreeTable.markAsDirty();
        processTreeTable.setImmediate(true);
        processTreeTable.setSizeFull();
        processTreeTable.setPageLength(NumericConstants.TEN);
        processTreeTable.removeAllItems();
        controlBar.setVisible(true);
        parentList.clear();
        levelValue = 0;
        totalPagesLabel.setValue(String.valueOf(getTotalAmountOfPages()));
        hierarchicalContainer = contractDashboardLogic.getLevel1Hierarchy(rightSearchBinder, hierarchicalContainer, 0, Integer.valueOf(String.valueOf(itemsPerPageSelect.getValue())));
        processTreeTable.setContainerDataSource(hierarchicalContainer);

        setProcessedTableHeader();

        processTreeTable.addExpandListener(expandListener);
        processTreeTable.addCollapseListener(collapseListener);
        processTreeTable.setSelectable(true);
        processTreeTable.setId("ProcessTreeTable");
        processTreeTable.setColumnHeaders(new String[]{Constants.CATEGORY_HEADER, "ID", "No", "Name"});

        processTreeTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            @SuppressWarnings("PMD")
            public void itemClick(final ItemClickEvent event) {
                tableBeanId = event.getItemId();
                BeanItem<?> targetItem;
                if (tableBeanId instanceof BeanItem<?>) {
                    targetItem = (BeanItem<?>) tableBeanId;
                } else if (tableBeanId instanceof ContractMember) {
                    targetItem = new BeanItem<>((ContractMember) tableBeanId);
                } else {
                    targetItem = NULL_OBJECT;
                }
                tableBean = (ContractMember) targetItem.getBean();
            }
        });
        LOGGER.debug("End of getProcessedTree method");
    }

    /**
     * to save Tree Structure Data
     *
     * @param list
     * @throws SystemException
     * @throws PortalException
     */
    @SuppressWarnings("rawtypes")
    public void saveTree(final Collection list, String parentCategory) throws SystemException, PortalException {
        LOGGER.debug("Entering saveTree method");
        try {
            final ContractDashboardLogic contractDashboardLogic = new ContractDashboardLogic();
            for (final Iterator iterator = list.iterator(); iterator.hasNext();) {
                final Object idValue = iterator.next();
                final ContractMember temp = getBeanFromId(idValue);
                if (temp.getCategory().equals(Constants.CONTRACT)) {
                    contract = temp;
                    cfp = new ContractMember();
                    ifp = new ContractMember();
                    priceSchedule = new ContractMember();
                    rebateSchedule = new ContractMember();
                    contractDashboardLogic.updateContract(contract.getInternalId());
                }
                if (temp.getCategory().equals(Constants.CFP)) {

                    cfp = temp;
                    contractDashboardLogic.saveCFp(contract.getInternalId(), cfp);
                }
                if (temp.getCategory().equals(Constants.IFP)) {
                    ifp = temp;
                    contractDashboardLogic.saveIFP(contract.getInternalId(), temp.isContractChild() ? 0 : cfp.getCfpContractId(), ifp);

                }
                if (temp.getCategory().equals(Constants.PRICE_SCHEDULE)) {
                    priceSchedule = temp;
                    contractDashboardLogic.savePS(contract.getInternalId(), temp.isContractChild() ? 0 : cfp.getCfpContractId(), temp.isContractChild() ? 0 : ifp.getIfpContractId(), priceSchedule);
                }
                if (temp.getCategory().equals(Constants.REBATE_SCHEDULE)) {
                    rebateSchedule = temp;
                    contractDashboardLogic.saveRS(contract.getInternalId(), temp.isContractChild() ? 0 : cfp.getCfpContractId(), temp.isContractChild() ? 0 : ifp.getIfpContractId(), temp.isContractChild() ? 0 : priceSchedule.getPsContractId(), rebateSchedule);
                }

                final Collection childlist = tree.getChildren(idValue);
                if (childlist == null || childlist.isEmpty()) {
                } else {
                    saveTree(childlist, temp.getCategory());
                    if (parentCategory.equals(Constants.CFP)) {
                        ifp = new ContractMember();
                        priceSchedule = new ContractMember();
                        rebateSchedule = new ContractMember();
                    }
                    if (parentCategory.equals(Constants.IFP)) {
                        priceSchedule = new ContractMember();
                        rebateSchedule = new ContractMember();

                    }
                    if (parentCategory.equals(Constants.PRICE_SCHEDULE)) {
                        rebateSchedule = new ContractMember();
                    }
                    if (parentCategory.equals(Constants.REBATE_SCHEDULE)) {
                        rebateSchedule = temp;
                    }
                }
                contractDashboardLogic.callCcpProcedure();
                contractDashboardLogic.callActualsDetailsProcedure();
                getProcessedTree(rightSearchBinder);
                tree.setHeight(TREE_HEIGHT);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("End of saveTree method");
    }

    /**
     * to navigate next view
     *
     * @throws SystemException
     * @throws PortalException
     */
    public void navigateToNextView() throws SystemException, PortalException {
        LOGGER.debug("Entering navigateToNextView method");
        try {
            sessionDTO.setRsSystemId(0);
            sessionDTO.setPsSystemId(0);
            sessionDTO.setIfpSystemId(0);
            sessionDTO.setCfpSystemId(0);
            sessionDTO.setContractSystemId(0);
            sessionDTO.setHasPermission(null);
            sessionDTO.setProcessIntanceId(0);
            sessionDTO.setWorkflow(null);
            if (tableBean.getCategory().equals(Constants.REBATE_SCHEDULE)) {
                sessionDTO.setRsSystemId(tableBean.getInternalId());
                LOGGER.debug(" rsSystemId=" + tableBean.getInternalId());
                final Object systemId = processTreeTable.getParent(tableBeanId);
                final ContractMember rsparent = getBeanFromId(systemId);
                if (rsparent.getCategory().equals(Constants.PRICE_SCHEDULE)) {
                    sessionDTO.setPsSystemId(rsparent.getInternalId());
                    LOGGER.debug(" psSystemId=" + rsparent.getInternalId());
                    final Object psParentId = processTreeTable.getParent(systemId);
                    LOGGER.debug(" psParentId=" + psParentId);
                    final ContractMember psParent = getBeanFromId(psParentId);
                    if (psParent.getCategory().equals(Constants.IFP)) {
                        sessionDTO.setIfpSystemId(psParent.getInternalId());
                        LOGGER.debug(" ifpSystemId=" + psParent.getInternalId());
                        final Object ifpParentId = processTreeTable.getParent(psParentId);
                        final ContractMember ifpParent = getBeanFromId(ifpParentId);
                        if (ifpParent.getCategory().equals(Constants.CFP)) {
                            sessionDTO.setCfpSystemId(ifpParent.getInternalId());
                            final Object cfpParentId = processTreeTable.getParent(ifpParentId);
                            final ContractMember cfpParent = getBeanFromId(cfpParentId);
                            if (cfpParent.getCategory().equals(Constants.CONTRACT)) {
                                sessionDTO.setContractSystemId(cfpParent.getInternalId());
                                LOGGER.debug(" contractSystemId=" + cfpParent.getInternalId());
                                final ContractMaster contract = dashBoardLogic.getContractMaster(cfpParent.getInternalId());
                                if (!contract.getRecordLockStatus()) {
                                    sessionDTO.setEdit(Constants.Y);
                                } else {
                                    sessionDTO.setEdit(Constants.N);
                                }

                                if (dashBoardLogic.validateContractToProcess(contract)) {
                                    MessageBox.showPlain(Icon.QUESTION, Constants.CONFORMATION, ConstantUtil.CONTRACT_SELECTED_HAS_ACTUALS
                                            + ConstantUtil.FORECASTED_SALES_OR_FORECASTED_DISCOUNTS_PROCEED, new MessageBoxListener() {
                                        /**
                                         * Called when a Button has been clicked
                                         * .
                                         */
                                        @SuppressWarnings("PMD")
                                        public void buttonClicked(final ButtonId buttonId) {
                                            if (buttonId.name().equals(Constants.YES)) {
                                                getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                                            }
                                        }
                                    }, ButtonId.YES, ButtonId.NO);
                                } else {
                                    getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                                }

                            }
                        } else if (ifpParent.getCategory().equals(Constants.CONTRACT)) {
                            sessionDTO.setContractSystemId(ifpParent.getInternalId());
                            final ContractMaster contract = dashBoardLogic.getContractMaster(ifpParent.getInternalId());
                            if (!contract.getRecordLockStatus()) {
                                sessionDTO.setEdit(Constants.Y);
                            } else {
                                sessionDTO.setEdit(Constants.N);
                            }
                            if (dashBoardLogic.validateContractToProcess(contract)) {
                                MessageBox.showPlain(Icon.QUESTION, Constants.CONFORMATION, ConstantUtil.CONTRACT_SELECTED_HAS_ACTUALS
                                        + ConstantUtil.FORECASTED_SALES_OR_FORECASTED_DISCOUNTS_PROCEED, new MessageBoxListener() {
                                    /**
                                     * Called when a Button has been clicked .
                                     */
                                    @SuppressWarnings("PMD")
                                    public void buttonClicked(final ButtonId buttonId) {
                                        if (buttonId.name().equals(Constants.YES)) {
                                            getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                                        }
                                    }
                                }, ButtonId.YES, ButtonId.NO);
                            } else {
                                getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                            }

                        }
                    } else if (psParent.getCategory().equals(Constants.CFP)) {
                        sessionDTO.setCfpSystemId(psParent.getInternalId());
                        final Object cfpParentId = processTreeTable.getParent(psParentId);
                        final ContractMember cfpParent = getBeanFromId(cfpParentId);
                        if (cfpParent.getCategory().equals(Constants.CONTRACT)) {
                            sessionDTO.setContractSystemId(cfpParent.getInternalId());
                            final ContractMaster contract = dashBoardLogic.getContractMaster(cfpParent.getInternalId());

                            if (!contract.getRecordLockStatus()) {
                                sessionDTO.setEdit(Constants.Y);
                            } else {
                                sessionDTO.setEdit(Constants.N);
                            }
                            if (dashBoardLogic.validateContractToProcess(contract)) {
                                MessageBox.showPlain(Icon.QUESTION, Constants.CONFORMATION, ConstantUtil.CONTRACT_SELECTED_HAS_ACTUALS
                                        + ConstantUtil.FORECASTED_SALES_OR_FORECASTED_DISCOUNTS_PROCEED, new MessageBoxListener() {
                                    /**
                                     * Called when a Button has been clicked .
                                     */
                                    @SuppressWarnings("PMD")
                                    public void buttonClicked(final ButtonId buttonId) {
                                        if (buttonId.name().equals(Constants.YES)) {
                                            getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                                        }
                                    }
                                }, ButtonId.YES, ButtonId.NO);
                            } else {
                                getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                            }

                        }
                    } else if (psParent.getCategory().equals(Constants.CONTRACT)) {
                        sessionDTO.setContractSystemId(psParent.getInternalId());
                        ContractMaster contract = null;
                        contract = dashBoardLogic.getContractMaster(psParent.getInternalId());

                        if (!contract.getRecordLockStatus()) {
                            sessionDTO.setEdit(Constants.Y);
                        } else {
                            sessionDTO.setEdit(Constants.N);
                        }
                        if (dashBoardLogic.validateContractToProcess(contract)) {
                            MessageBox.showPlain(Icon.QUESTION, Constants.CONFORMATION, ConstantUtil.CONTRACT_SELECTED_HAS_ACTUALS
                                    + ConstantUtil.FORECASTED_SALES_OR_FORECASTED_DISCOUNTS_PROCEED, new MessageBoxListener() {
                                /**
                                 * Called when a Button has been clicked .
                                 */
                                @SuppressWarnings("PMD")
                                public void buttonClicked(final ButtonId buttonId) {
                                    if (buttonId.name().equals(Constants.YES)) {
                                        getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                                    }
                                }
                            }, ButtonId.YES, ButtonId.NO);
                        } else {
                            getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                        }
                    }
                } else if (rsparent.getCategory().equals(Constants.IFP)) {
                    sessionDTO.setIfpSystemId(rsparent.getInternalId());
                    final Object ifpParentId = processTreeTable.getParent(systemId);
                    final ContractMember ifpParent = getBeanFromId(ifpParentId);
                    if (ifpParent.getCategory().equals(Constants.CFP)) {
                        sessionDTO.setCfpSystemId(ifpParent.getInternalId());
                        final Object cfpParentId = processTreeTable.getParent(ifpParentId);
                        final ContractMember cfpParent = getBeanFromId(cfpParentId);
                        if (cfpParent.getCategory().equals(Constants.CONTRACT)) {
                            sessionDTO.setContractSystemId(cfpParent.getInternalId());
                            ContractMaster contract = null;
                            contract = dashBoardLogic.getContractMaster(cfpParent.getInternalId());
                            if (!contract.getRecordLockStatus()) {
                                sessionDTO.setEdit(Constants.Y);
                            } else {
                                sessionDTO.setEdit(Constants.N);
                            }
                            if (dashBoardLogic.validateContractToProcess(contract)) {
                                MessageBox.showPlain(Icon.QUESTION, Constants.CONFORMATION, ConstantUtil.CONTRACT_SELECTED_HAS_ACTUALS
                                        + ConstantUtil.FORECASTED_SALES_OR_FORECASTED_DISCOUNTS_PROCEED, new MessageBoxListener() {
                                    /**
                                     * Called when a Button has been clicked .
                                     */
                                    @SuppressWarnings("PMD")
                                    public void buttonClicked(final ButtonId buttonId) {
                                        if (buttonId.name().equals(Constants.YES)) {
                                            getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                                        }
                                    }
                                }, ButtonId.YES, ButtonId.NO);
                            } else {
                                getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                            }
                        }
                    } else if (ifpParent.getCategory().equals(Constants.CONTRACT)) {
                        sessionDTO.setContractSystemId(ifpParent.getInternalId());
                        ContractMaster contract = null;
                        contract = dashBoardLogic.getContractMaster(ifpParent.getInternalId());

                        if (!contract.getRecordLockStatus()) {
                            sessionDTO.setEdit(Constants.Y);
                        } else {
                            sessionDTO.setEdit(Constants.N);
                        }
                        if (dashBoardLogic.validateContractToProcess(contract)) {
                            MessageBox.showPlain(Icon.QUESTION, Constants.CONFORMATION, ConstantUtil.CONTRACT_SELECTED_HAS_ACTUALS
                                    + ConstantUtil.FORECASTED_SALES_OR_FORECASTED_DISCOUNTS_PROCEED, new MessageBoxListener() {
                                /**
                                 * Called when a Button has been clicked .
                                 */
                                @SuppressWarnings("PMD")
                                public void buttonClicked(final ButtonId buttonId) {
                                    if (buttonId.name().equals(Constants.YES)) {
                                        getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                                    }
                                }
                            }, ButtonId.YES, ButtonId.NO);
                        } else {
                            getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                        }
                    }
                } else if (rsparent.getCategory().equals(Constants.CFP)) {
                    sessionDTO.setCfpSystemId(rsparent.getInternalId());
                    final Object cfpParentId = processTreeTable.getParent(systemId);
                    final ContractMember cfpParent = getBeanFromId(cfpParentId);
                    if (cfpParent.getCategory().equals(Constants.CONTRACT)) {
                        sessionDTO.setContractSystemId(cfpParent.getInternalId());
                        ContractMaster contract = null;
                        contract = dashBoardLogic.getContractMaster(cfpParent.getInternalId());

                        if (!contract.getRecordLockStatus()) {
                            sessionDTO.setEdit(Constants.Y);
                        } else {
                            sessionDTO.setEdit(Constants.N);
                        }
                        if (dashBoardLogic.validateContractToProcess(contract)) {
                            MessageBox.showPlain(Icon.QUESTION, Constants.CONFORMATION, ConstantUtil.CONTRACT_SELECTED_HAS_ACTUALS
                                    + ConstantUtil.FORECASTED_SALES_OR_FORECASTED_DISCOUNTS_PROCEED, new MessageBoxListener() {
                                /**
                                 * Called when a Button has been clicked .
                                 */
                                @SuppressWarnings("PMD")
                                public void buttonClicked(final ButtonId buttonId) {
                                    if (buttonId.name().equals(Constants.YES)) {
                                        getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                                    }
                                }
                            }, ButtonId.YES, ButtonId.NO);
                        } else {
                            getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                        }
                    }
                } else if (rsparent.getCategory().equals(Constants.CONTRACT)) {
                    sessionDTO.setContractSystemId(rsparent.getInternalId());
                    ContractMaster contract = null;
                    contract = dashBoardLogic.getContractMaster(rsparent.getInternalId());

                    if (!contract.getRecordLockStatus()) {
                        sessionDTO.setEdit(Constants.Y);
                    } else {
                        sessionDTO.setEdit(Constants.N);
                    }
                    if (dashBoardLogic.validateContractToProcess(contract)) {
                        MessageBox.showPlain(Icon.QUESTION, Constants.CONFORMATION, ConstantUtil.CONTRACT_SELECTED_HAS_ACTUALS
                                + ConstantUtil.FORECASTED_SALES_OR_FORECASTED_DISCOUNTS_PROCEED, new MessageBoxListener() {
                            /**
                             * Called when a Button has been clicked .
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                if (buttonId.name().equals(Constants.YES)) {
                                    getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);
                    } else {
                        getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                    }
                }
            }
            if (tableBean.getCategory().equals(Constants.PRICE_SCHEDULE)) {
                sessionDTO.setPsSystemId(tableBean.getInternalId());

                final Object psParentId = processTreeTable.getParent(tableBeanId);
                final ContractMember psParent = getBeanFromId(psParentId);
                if (psParent.getCategory().equals(Constants.IFP)) {
                    sessionDTO.setIfpSystemId(psParent.getInternalId());
                    final Object ifpParentId = processTreeTable.getParent(psParentId);
                    final ContractMember ifpParent = getBeanFromId(ifpParentId);
                    if (ifpParent.getCategory().equals(Constants.CFP)) {
                        sessionDTO.setCfpSystemId(ifpParent.getInternalId());
                        final Object cfpParentId = processTreeTable.getParent(ifpParentId);
                        final ContractMember cfpParent = getBeanFromId(cfpParentId);
                        if (cfpParent.getCategory().equals(Constants.CONTRACT)) {
                            sessionDTO.setContractSystemId(cfpParent.getInternalId());
                            ContractMaster contract = null;
                            contract = dashBoardLogic.getContractMaster(cfpParent.getInternalId());

                            if (!contract.getRecordLockStatus()) {
                                sessionDTO.setEdit(Constants.Y);
                            } else {
                                sessionDTO.setEdit(Constants.N);
                            }
                            if (dashBoardLogic.validateContractToProcess(contract)) {
                                MessageBox.showPlain(Icon.QUESTION, Constants.CONFORMATION, ConstantUtil.CONTRACT_SELECTED_HAS_ACTUALS
                                        + ConstantUtil.FORECASTED_SALES_OR_FORECASTED_DISCOUNTS_PROCEED, new MessageBoxListener() {
                                    /**
                                     * Called when a Button has been clicked .
                                     */
                                    @SuppressWarnings("PMD")
                                    public void buttonClicked(final ButtonId buttonId) {
                                        if (buttonId.name().equals(Constants.YES)) {
                                            getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                                        }
                                    }
                                }, ButtonId.YES, ButtonId.NO);
                            } else {
                                getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                            }
                        }
                    } else if (ifpParent.getCategory().equals(Constants.CONTRACT)) {
                        sessionDTO.setContractSystemId(ifpParent.getInternalId());
                        ContractMaster contract = null;
                        contract = dashBoardLogic.getContractMaster(ifpParent.getInternalId());

                        if (!contract.getRecordLockStatus()) {
                            sessionDTO.setEdit(Constants.Y);
                        } else {
                            sessionDTO.setEdit(Constants.N);

                        }
                        if (dashBoardLogic.validateContractToProcess(contract)) {
                            MessageBox.showPlain(Icon.QUESTION, Constants.CONFORMATION, ConstantUtil.CONTRACT_SELECTED_HAS_ACTUALS
                                    + ConstantUtil.FORECASTED_SALES_OR_FORECASTED_DISCOUNTS_PROCEED, new MessageBoxListener() {
                                /**
                                 * Called when a Button has been clicked .
                                 */
                                @SuppressWarnings("PMD")
                                public void buttonClicked(final ButtonId buttonId) {
                                    if (buttonId.name().equals(Constants.YES)) {
                                        getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                                    }
                                }
                            }, ButtonId.YES, ButtonId.NO);
                        } else {
                            getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                        }
                    }
                } else if (psParent.getCategory().equals(Constants.CFP)) {
                    sessionDTO.setCfpSystemId(psParent.getInternalId());
                    final Object cfpParentId = processTreeTable.getParent(psParentId);
                    final ContractMember cfpParent = getBeanFromId(cfpParentId);
                    if (cfpParent.getCategory().equals(Constants.CONTRACT)) {
                        sessionDTO.setContractSystemId(cfpParent.getInternalId());
                        ContractMaster contract = null;
                        contract = dashBoardLogic.getContractMaster(cfpParent.getInternalId());

                        if (!contract.getRecordLockStatus()) {
                            sessionDTO.setEdit(Constants.Y);
                        } else {
                            sessionDTO.setEdit(Constants.N);
                        }
                        if (dashBoardLogic.validateContractToProcess(contract)) {
                            MessageBox.showPlain(Icon.QUESTION, Constants.CONFORMATION, ConstantUtil.CONTRACT_SELECTED_HAS_ACTUALS
                                    + ConstantUtil.FORECASTED_SALES_OR_FORECASTED_DISCOUNTS_PROCEED, new MessageBoxListener() {
                                /**
                                 * Called when a Button has been clicked .
                                 */
                                @SuppressWarnings("PMD")
                                public void buttonClicked(final ButtonId buttonId) {
                                    if (buttonId.name().equals(Constants.YES)) {
                                        getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                                    }
                                }
                            }, ButtonId.YES, ButtonId.NO);
                        } else {
                            getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                        }
                    }
                } else if (psParent.getCategory().equals(Constants.CONTRACT)) {
                    sessionDTO.setContractSystemId(psParent.getInternalId());
                    ContractMaster contract = null;
                    contract = dashBoardLogic.getContractMaster(psParent.getInternalId());

                    if (!contract.getRecordLockStatus()) {
                        sessionDTO.setEdit(Constants.Y);
                    } else {
                        sessionDTO.setEdit(Constants.N);
                    }
                    if (dashBoardLogic.validateContractToProcess(contract)) {
                        MessageBox.showPlain(Icon.QUESTION, Constants.CONFORMATION, ConstantUtil.CONTRACT_SELECTED_HAS_ACTUALS
                                + ConstantUtil.FORECASTED_SALES_OR_FORECASTED_DISCOUNTS_PROCEED, new MessageBoxListener() {
                            /**
                             * Called when a Button has been clicked .
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                if (buttonId.name().equals(Constants.YES)) {
                                    getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);
                    } else {
                        getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                    }
                }
            } else if (tableBean.getCategory().equals(Constants.IFP)) {

                sessionDTO.setIfpSystemId(tableBean.getInternalId());

                final Object ifpParentId = processTreeTable.getParent(tableBeanId);
                final ContractMember ifpParent = getBeanFromId(ifpParentId);
                if (ifpParent.getCategory().equals(Constants.CFP)) {
                    sessionDTO.setCfpSystemId(ifpParent.getInternalId());
                    final Object cfpParentId = processTreeTable.getParent(ifpParentId);
                    final ContractMember cfpParent = getBeanFromId(cfpParentId);
                    if (cfpParent.getCategory().equals(Constants.CONTRACT)) {
                        sessionDTO.setContractSystemId(cfpParent.getInternalId());
                        ContractMaster contract = null;
                        contract = dashBoardLogic.getContractMaster(cfpParent.getInternalId());

                        if (!contract.getRecordLockStatus()) {
                            sessionDTO.setEdit(Constants.Y);
                        } else {
                            sessionDTO.setEdit(Constants.N);
                        }
                        if (dashBoardLogic.validateContractToProcess(contract)) {
                            MessageBox.showPlain(Icon.QUESTION, Constants.CONFORMATION, ConstantUtil.CONTRACT_SELECTED_HAS_ACTUALS
                                    + ConstantUtil.FORECASTED_SALES_OR_FORECASTED_DISCOUNTS_PROCEED, new MessageBoxListener() {
                                /**
                                 * Called when a Button has been clicked .
                                 */
                                @SuppressWarnings("PMD")
                                public void buttonClicked(final ButtonId buttonId) {
                                    if (buttonId.name().equals(Constants.YES)) {
                                        getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                                    }
                                }
                            }, ButtonId.YES, ButtonId.NO);
                        } else {
                            getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                        }
                    }
                } else if (ifpParent.getCategory().equals(Constants.CONTRACT)) {
                    sessionDTO.setContractSystemId(ifpParent.getInternalId());
                    ContractMaster contract = null;
                    contract = dashBoardLogic.getContractMaster(ifpParent.getInternalId());

                    if (!contract.getRecordLockStatus()) {
                        sessionDTO.setEdit(Constants.Y);
                    } else {
                        sessionDTO.setEdit(Constants.N);
                    }
                    if (dashBoardLogic.validateContractToProcess(contract)) {
                        MessageBox.showPlain(Icon.QUESTION, Constants.CONFORMATION, ConstantUtil.CONTRACT_SELECTED_HAS_ACTUALS
                                + ConstantUtil.FORECASTED_SALES_OR_FORECASTED_DISCOUNTS_PROCEED, new MessageBoxListener() {
                            /**
                             * Called when a Button has been clicked .
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                if (buttonId.name().equals(Constants.YES)) {
                                    getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);
                    } else {
                        getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                    }
                }
            }
            if (tableBean.getCategory().equals(Constants.CFP)) {

                sessionDTO.setCfpSystemId(tableBean.getInternalId());
                final Object cfpParentId = processTreeTable.getParent(tableBeanId);
                final ContractMember cfpParent = getBeanFromId(cfpParentId);
                if (cfpParent.getCategory().equals(Constants.CONTRACT)) {
                    sessionDTO.setContractSystemId(cfpParent.getInternalId());
                    ContractMaster contract = null;
                    contract = dashBoardLogic.getContractMaster(cfpParent.getInternalId());

                    if (!contract.getRecordLockStatus()) {
                        sessionDTO.setEdit(Constants.Y);
                    } else {
                        sessionDTO.setEdit(Constants.N);
                    }
                    if (dashBoardLogic.validateContractToProcess(contract)) {
                        MessageBox.showPlain(Icon.QUESTION, Constants.CONFORMATION, ConstantUtil.CONTRACT_SELECTED_HAS_ACTUALS
                                + ConstantUtil.FORECASTED_SALES_OR_FORECASTED_DISCOUNTS_PROCEED, new MessageBoxListener() {
                            /**
                             * Called when a Button has been clicked .
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                if (buttonId.name().equals(Constants.YES)) {
                                    getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);
                    } else {
                        getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                    }
                }
            } else if (tableBean.getCategory().equals(Constants.CONTRACT)) {
                sessionDTO.setContractSystemId(tableBean.getInternalId());
                ContractMaster contract = null;
                contract = dashBoardLogic.getContractMaster(tableBean.getInternalId());

                if (!contract.getRecordLockStatus()) {
                    sessionDTO.setEdit(Constants.Y);
                } else {
                    sessionDTO.setEdit(Constants.N);
                }
                if (dashBoardLogic.validateContractToProcess(contract)) {
                    MessageBox.showPlain(Icon.QUESTION, Constants.CONFORMATION, ConstantUtil.CONTRACT_SELECTED_HAS_ACTUALS
                            + ConstantUtil.FORECASTED_SALES_OR_FORECASTED_DISCOUNTS_PROCEED, new MessageBoxListener() {
                        /**
                         * Called when a Button has been clicked .
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            if (buttonId.name().equals(Constants.YES)) {
                                getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);
                } else {
                    getUI().getNavigator().navigateTo(DashboardEditView.NAME);
                }
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("End of navigateToNextView method");
    }

    /**
     * Gets the bean from id.
     *
     * @param systemId the system id
     * @return the bean from id
     */
    public ContractMember getBeanFromId(final Object systemId) {
        LOGGER.debug("Entering getBeanFromId method");

        BeanItem<?> targetItem;
        if (systemId instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) systemId;
        } else if (systemId instanceof ContractMember) {
            targetItem = new BeanItem<>((ContractMember) systemId);
        } else {

            targetItem = NULL_OBJECT;
        }
        LOGGER.debug("End of getBeanFromId method");
        return (ContractMember) targetItem.getBean();
    }

    /**
     * This method is always called before the view is shown on screen.
     */
    public void enter(final ViewChangeEvent event) {
        LOGGER.debug("Entering enter method in DashboardForm");
    }

    /**
     * Adds the from table to tree.
     */
    private void addFrmTableToTree() {
        try {
            LOGGER.debug("Entering addFrmTableToTree method");
            tree.setHeight(99f, Sizeable.Unit.PERCENTAGE);
            tree.addItem(tableBeanId);
            List<ContractMember> list = new ArrayList<>();
            list.add(getBeanFromId(tableBeanId));
            loadDataToTree(list, null);
            ((CustomTreeContainer) processTreeTable.getContainerDataSource()).removeItemRecursively(tableBeanId);
            LOGGER.debug("End of addFrmTableToTree method");
        } catch (SystemException ex) {
            LOGGER.error(ex);
        } catch (PortalException ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * This method Configures the UI components.
     */
    public void configure() {
        LOGGER.debug("Entering configure method");
        try {

            leftComponent.setImmediate(true);
            leftComponent.focus();
            loadComponentDropdown();
            loadSearchType();
            if (ContractUtils.CONTRACT_DETAILS_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
                table.setContainerDataSource(tableContainer);
                table.setVisibleColumns(new Object[]{Constants.MEMBER_ID, Constants.MEMBER_NO, Constants.NAME, Constants.MEMBER_TYPE});
                table.setColumnHeaders(Constants.CONTRACT_ID1, Constants.CONTRACT_NO_SP, Constants.CONTRACT_NAME1, Constants.CONTRACT_TYPE_HEADER);
                table.setFilterGenerator(new SearchComponentFilterGenerator(String.valueOf(leftComponent.getValue())));
                detailsTable.setContainerDataSource(detailNormalContainer);
                detailsTable.setVisible(false);
                detailPanel.setVisible(false);
            }
            contractGridLayout.setVisible(true);
            cfpGridLayout.setVisible(false);
            ifpGridLayout.setVisible(false);
            psGridLayout.setVisible(false);
            rsGridLayout.setVisible(false);
            contractDetailGridLayout.setVisible(false);
            cfpDetailGridLayout.setVisible(false);
            ifpDetailGridLayout.setVisible(false);
            psDetailGridLayout.setVisible(false);
            rsDetailGridLayout.setVisible(false);

            rightCompanyIdLabel.setVisible(false);
            rightCompanyId.setVisible(false);
            rightIfpItemIdLabel.setVisible(false);
            rightIfpItemId.setVisible(false);

            leftContractId.addValidator(new StringLengthValidator("Contract ID length should be less than 38 characters", 0, NumericConstants.THIRTY_EIGHT, true));
            leftContractId.setImmediate(true);
            leftContractId.setValidationVisible(true);
            leftContractId.setDescription(leftContractId.getValue());
            leftContractId.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * sets the value for contractId Textfield on Value change.
                 *
                 * @param event - ValueChangeEvent
                 */
                @SuppressWarnings("PMD")
                public void valueChange(final ValueChangeEvent event) {
                    leftContractId.setDescription(leftContractId.getValue());
                }
            });

            leftContractNo.addValidator(new StringLengthValidator("Contract No should be less than 50 characters", 0, NumericConstants.FIFTY, true));
            leftContractNo.setImmediate(true);
            leftContractNo.setValidationVisible(true);
            leftContractNo.setDescription(leftContractNo.getValue());
            leftContractNo.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Sets the description for Contract Number.
                 *
                 * @param event - ValueChangeEvent
                 */
                public void valueChange(final ValueChangeEvent event) {
                    leftContractNo.setDescription(leftContractNo.getValue());
                }
            });

            leftContractName.addValidator(new StringLengthValidator("Contract Name should be less than 100 characters", 0, NumericConstants.HUNDRED, true));
            leftContractName.setImmediate(true);
            leftContractName.setValidationVisible(true);
            leftContractName.setDescription(leftContractName.getValue());
            leftContractName.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * sets the value for contractName Textfield on Value change.
                 *
                 * @param event - ValueChangeEvent
                 */
                @SuppressWarnings("PMD")
                public void valueChange(final ValueChangeEvent event) {
                    leftContractName.setDescription(leftContractName.getValue());
                }
            });

            leftContractType.setImmediate(true);
            leftContractType.setPageLength(NumericConstants.SEVEN);
            leftContractType.addItem(Constants.SELECT_ONE);
            loadComboBox(leftContractType, headerLogic.getDropDownListWithoutNull(UIUtils.CONTRACT_TYPE));
            leftContractType.setNullSelectionAllowed(true);
            leftContractType.setNullSelectionItemId(Constants.SELECT_ONE);
            leftContractType.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(ValueChangeEvent event) {
                    CommonUtils.setNullValue(leftContractType);
                }
            });

            leftContractStartDate.setValidationVisible(true);
            leftContractStartDate.setImmediate(true);
            leftContractStartDate.setDateFormat(Constants.MM_DD_YYYY);
            leftContractStartDate.setId("ContractStartDate");
            leftContractStartDate.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Changes the Date format for PopupdateField.
                 *
                 * @param event - ValueChangeEvent
                 */
                public void valueChange(final ValueChangeEvent event) {
                    leftContractStartDate.setDescription(com.stpl.app.contract.util.CommonUIUtils.convert2DigitTo4DigitYear(leftContractStartDate.getValue()));
                }
            });

            leftContractEndDate.setImmediate(true);
            leftContractEndDate.setValidationVisible(true);
            leftContractEndDate.addValidator(new DateValidatorContract("Contract End date should be greater than Contract Start Date"));
            leftContractEndDate.setDateFormat(Constants.MM_DD_YYYY);

            leftCfpId.setImmediate(true);
            leftCfpId.setValidationVisible(true);
            leftCfpId.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            leftCfpId.addValidator(new StringLengthValidator(" CFP ID Should be less than 38 characters", 0, NumericConstants.THIRTY_EIGHT, true));
            leftCfpId.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * This method listens to data source value changes and passes
                 * the changes forwards.
                 */
                public void valueChange(final ValueChangeEvent event) {

                    leftCfpId.setDescription(leftCfpId.getValue());

                }
            });

            leftCfpNo.setImmediate(true);
            leftCfpNo.setValidationVisible(true);
            leftCfpNo.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            leftCfpNo.addValidator(new StringLengthValidator(" CFP No Should be less than 50 characters", 0, NumericConstants.FIFTY, true));
            leftCfpNo.setDescription(leftCfpNo.getValue());
            leftCfpNo.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * This method listens to data source value changes and passes
                 * the changes forwards.
                 */
                public void valueChange(final ValueChangeEvent event) {
                    leftCfpNo.setDescription(leftCfpNo.getValue());

                }
            });

            leftCfpName.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            leftCfpName.setImmediate(true);
            leftCfpName.setValidationVisible(true);
            leftCfpName.addValidator(new StringLengthValidator(" CFP Name Should be less than 100 characters", 0, NumericConstants.HUNDRED, true));
            leftCfpName.setDescription(leftCfpName.getValue());
            leftCfpName.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * This method listens to data source value changes and passes
                 * the changes forwards.
                 */
                @SuppressWarnings("PMD")
                public void valueChange(final ValueChangeEvent event) {

                    leftCfpName.setDescription(leftCfpName.getValue());

                }
            });

            leftCfpType.setImmediate(true);
            leftCfpType.setPageLength(NumericConstants.SEVEN);
            leftCfpType.addItem(Constants.SELECT_ONE);
            loadComboBox(leftCfpType, headerLogic.getDropDownListWithoutNull(UIUtils.CFP_TYPE));
            leftCfpType.setNullSelectionAllowed(true);
            leftCfpType.setNullSelectionItemId(Constants.SELECT_ONE);
            leftCfpType.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(ValueChangeEvent event) {
                    CommonUtils.setNullValue(leftCfpType);
                }
            });

            leftCompanyNo.setImmediate(true);
            leftCompanyNo.setDescription(leftCompanyNo.getValue());
            leftCompanyNo.setValidationVisible(true);
            leftCompanyNo.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            leftCompanyNo.addValidator(new StringLengthValidator(" Company No Should be less than 50 characters", 0, NumericConstants.FIFTY, true));
            leftCompanyNo.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Logic for Value change event.
                 *
                 * @param event
                 */
                public void valueChange(final ValueChangeEvent event) {
                    leftCompanyNo.setDescription(leftCompanyNo.getValue());
                }
            });

            leftCompanyName.setDescription(leftCompanyName.getValue());
            leftCompanyName.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            leftCompanyName.setImmediate(true);
            leftCompanyName.setValidationVisible(true);
            leftCompanyName.addValidator(new StringLengthValidator(" Company Name Should be less than 100 characters", 0, NumericConstants.HUNDRED, true));
            leftCompanyName.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Logic for Value change event.
                 *
                 * @param event
                 */
                @SuppressWarnings("PMD")
                public void valueChange(final ValueChangeEvent event) {
                    leftCompanyName.setDescription(leftCompanyName.getValue());
                }
            });

            leftCompanyType.setImmediate(true);
            leftCompanyType.setPageLength(NumericConstants.SEVEN);
            leftCompanyType.addItem(Constants.SELECT_ONE);
            loadComboBox(leftCompanyType, headerLogic.getDropDownListWithoutNull(UIUtils.COMP_TYPE));
            leftCompanyType.setNullSelectionAllowed(true);
            leftCompanyType.setNullSelectionItemId(Constants.SELECT_ONE);
            leftCompanyType.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(ValueChangeEvent event) {
                    CommonUtils.setNullValue(leftCompanyType);
                }
            });

            leftCompanyCategory.setDescription(leftCompanyCategory.getValue());
            leftCompanyCategory.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            leftCompanyCategory.setImmediate(true);
            leftCompanyCategory.setValidationVisible(true);
            leftCompanyCategory.addValidator(new StringLengthValidator(" Company Category Should be less than 100 characters", 0, NumericConstants.HUNDRED, true));
            leftCompanyCategory.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Logic for Value change event.
                 *
                 * @param event
                 */
                @SuppressWarnings("PMD")
                public void valueChange(final ValueChangeEvent event) {
                    leftCompanyCategory.setDescription(leftCompanyCategory.getValue());
                }
            });

            leftIfpId.setImmediate(true);
            leftIfpId.addValidator(new StringLengthValidator("IFP ID length should be less than 50 characters", 0, NumericConstants.FIFTY, true));
            leftIfpId.addValidator(new RegexpValidator(ContractUtils.SEARCH_SP_CHAR, "IFP ID " + ContractUtils.SEARCH_SPCHAR_MSG));
            leftIfpId.setValidationVisible(true);
            leftIfpId.setDescription(leftIfpId.getValue());
            leftIfpId.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * This method listens to data source value changes and passes
                 * the changes forwards.
                 */
                @SuppressWarnings("PMD")
                public void valueChange(final ValueChangeEvent event) {
                    leftIfpId.setDescription(leftIfpId.getValue());
                }
            });

            leftIfpNo.addValidator(new StringLengthValidator("IFP No length should be less than 50 characters", 0, NumericConstants.FIFTY, true));
            leftIfpNo.addValidator(new RegexpValidator(ContractUtils.SEARCH_SP_CHAR, "IFP No " + ContractUtils.SEARCH_SPCHAR_MSG));
            leftIfpNo.setImmediate(true);
            leftIfpNo.setValidationVisible(true);
            leftIfpNo.setDescription(leftIfpNo.getValue());
            leftIfpNo.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * This method listens to data source value changes and passes
                 * the changes forwards.
                 */
                public void valueChange(final ValueChangeEvent event) {
                    leftIfpNo.setDescription(leftIfpNo.getValue());
                }
            });

            leftIfpName.addValidator(new StringLengthValidator("IFP Name length should be less than 100 characters", 0, NumericConstants.HUNDRED, true));
            leftIfpName.addValidator(new RegexpValidator(ContractUtils.SEARCH_SP_CHAR, "IFP Name " + ContractUtils.SEARCH_SPCHAR_MSG));
            leftIfpName.setImmediate(true);
            leftIfpName.setValidationVisible(true);
            leftIfpName.setDescription(leftIfpName.getValue());
            leftIfpName.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * This method listens to data source value changes and passes
                 * the changes forwards.
                 */
                public void valueChange(final ValueChangeEvent event) {
                    leftIfpName.setDescription(leftIfpName.getValue());
                }
            });

            leftIfpItemNo.setImmediate(true);
            leftIfpItemNo.setValidationVisible(true);
            leftIfpItemNo.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            leftIfpItemNo.addValidator(new StringLengthValidator(ConstantUtil.ITEM_NO_SHOULD_BE_LESS_THAN_50_CHAR, 0, NumericConstants.FIFTY, true));
            leftIfpItemNo.setDescription(leftIfpItemNo.getValue());
            leftIfpItemNo.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in itemNo, function will be
                 * executed.
                 *
                 * @param event
                 */
                public void valueChange(final ValueChangeEvent event) {
                    leftIfpItemNo.setDescription(leftIfpItemNo.getValue());
                }
            });

            leftIfpItemName.addValidator(new RegexpValidator(ContractUtils.APOSTROPHE_SP_CHAR, ContractUtils.SPECIAL_CHAR_MSGS));
            leftIfpItemName.setImmediate(true);
            leftIfpItemName.setValidationVisible(true);
            leftIfpItemName.addValidator(new StringLengthValidator(ConstantUtil.ITEM_NAME_SHOULD_BE_LESS_THAN_100_CHAR, 0, NumericConstants.HUNDRED, true));
            leftIfpItemName.setDescription(leftIfpItemName.getValue());
            leftIfpItemName.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in itemName, function will be
                 * executed.
                 *
                 * @param event
                 */
                public void valueChange(final ValueChangeEvent event) {
                    leftIfpItemName.setDescription(leftIfpItemName.getValue());
                }
            });

            leftIfpType.setImmediate(true);
            leftIfpType.setPageLength(NumericConstants.SEVEN);
            leftIfpType.addItem(Constants.SELECT_ONE);
            loadComboBox(leftIfpType, headerLogic.getDropDownListWithoutNull(UIUtils.IFP_TYPE));
            leftIfpType.setNullSelectionAllowed(true);
            leftIfpType.setNullSelectionItemId(Constants.SELECT_ONE);
            leftIfpType.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(ValueChangeEvent event) {
                    CommonUtils.setNullValue(leftIfpType);
                }
            });

            leftIfpBrandName.setImmediate(true);
            ifpBrandContainer = new LazyContainer(HelperDTO.class, new BrandNameDAO(), new BrandNameCriteria());
            leftIfpBrandName.setContainerDataSource(ifpBrandContainer);
            leftIfpBrandName.setItemCaptionPropertyId(Constants.DESCRIPTION);
            leftIfpBrandName.setNullSelectionAllowed(true);
            leftIfpBrandName.setNullSelectionItemId(NULL_ID);

            leftIfpTherapeuticClass.setImmediate(true);
            leftIfpTherapeuticClass.setPageLength(NumericConstants.SEVEN);
            leftIfpTherapeuticClass.addItem(Constants.SELECT_ONE);
            loadComboBox(leftIfpTherapeuticClass, headerLogic.getDropDownListWithoutNull(UIUtils.ITEM_THERP_CLASS));
            leftIfpTherapeuticClass.setNullSelectionAllowed(true);
            leftIfpTherapeuticClass.setNullSelectionItemId(Constants.SELECT_ONE);
            leftIfpTherapeuticClass.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(ValueChangeEvent event) {
                    CommonUtils.setNullValue(leftIfpTherapeuticClass);
                }
            });

            leftPsId.setImmediate(true);
            leftPsId.setValidationVisible(true);
            leftPsId.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            leftPsId.addValidator(new StringLengthValidator("Price Schedule Id should be less than 38 characters", 0, NumericConstants.THIRTY_EIGHT, true));
            leftPsId.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Notifies this listener that the Property's value has changed.
                 */
                public void valueChange(final ValueChangeEvent event) {

                    leftPsId.setDescription((String) leftPsId.getValue());
                }
            });

            leftPsNo.setImmediate(true);
            leftPsNo.setValidationVisible(true);
            leftPsNo.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            leftPsNo.addValidator(new StringLengthValidator(" Price Schedule No should be less than 50 characters", 0, NumericConstants.FIFTY, true));
            leftPsNo.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Value Change listener
                 */
                public void valueChange(final ValueChangeEvent event) {

                    leftPsNo.setDescription((String) leftPsNo.getValue());
                }
            });

            leftPsName.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            leftPsName.setImmediate(true);
            leftPsName.setValidationVisible(true);
            leftPsName.addValidator(new StringLengthValidator(" Price Schedule Name Should be less than 100 characters", 0, NumericConstants.HUNDRED, true));
            leftPsName.setDescription(leftPsName.getValue());
            leftPsName.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Value change for PS Name.
                 *
                 * @param event the event
                 */
                public void valueChange(final ValueChangeEvent event) {

                    leftPsName.setDescription(leftPsName.getValue());

                }
            });

            leftPsType.setImmediate(true);
            leftPsType.setPageLength(NumericConstants.SEVEN);
            leftPsType.addItem(Constants.SELECT_ONE);
            loadComboBox(leftPsType, headerLogic.getDropDownListWithoutNull(UIUtils.PS_TYPE));
            leftPsType.setNullSelectionAllowed(true);
            leftPsType.setNullSelectionItemId(Constants.SELECT_ONE);
            leftPsType.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(ValueChangeEvent event) {
                    CommonUtils.setNullValue(leftPsType);
                }
            });

            leftPsItemNo.setImmediate(true);
            leftPsItemNo.setValidationVisible(true);
            leftPsItemNo.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            leftPsItemNo.addValidator(new StringLengthValidator(ConstantUtil.ITEM_NO_SHOULD_BE_LESS_THAN_50_CHAR, 0, NumericConstants.FIFTY, true));
            leftPsItemNo.setDescription(leftIfpItemNo.getValue());
            leftPsItemNo.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in itemNo, function will be
                 * executed.
                 *
                 * @param event
                 */
                public void valueChange(final ValueChangeEvent event) {
                    leftPsItemNo.setDescription(leftPsItemNo.getValue());
                }
            });

            leftPsItemName.addValidator(new RegexpValidator(ContractUtils.APOSTROPHE_SP_CHAR, ContractUtils.SPECIAL_CHAR_MSGS));
            leftPsItemName.setImmediate(true);
            leftPsItemName.setValidationVisible(true);
            leftPsItemName.addValidator(new StringLengthValidator(ConstantUtil.ITEM_NAME_SHOULD_BE_LESS_THAN_100_CHAR, 0, NumericConstants.HUNDRED, true));
            leftPsItemName.setDescription(leftPsItemName.getValue());
            leftPsItemName.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in itemName, function will be
                 * executed.
                 *
                 * @param event
                 */
                public void valueChange(final ValueChangeEvent event) {
                    leftPsItemName.setDescription(leftPsItemName.getValue());
                }
            });

            leftPsTherapeuticClass.setImmediate(true);
            leftPsTherapeuticClass.setPageLength(NumericConstants.SEVEN);
            leftPsTherapeuticClass.addItem(Constants.SELECT_ONE);
            loadComboBox(leftPsTherapeuticClass, headerLogic.getDropDownListWithoutNull(UIUtils.ITEM_THERP_CLASS));
            leftPsTherapeuticClass.setNullSelectionAllowed(true);
            leftPsTherapeuticClass.setNullSelectionItemId(Constants.SELECT_ONE);
            leftPsTherapeuticClass.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(ValueChangeEvent event) {
                    CommonUtils.setNullValue(leftPsTherapeuticClass);
                }
            });

            leftPsBrandName.setImmediate(true);
            psBrandContainer = new LazyContainer(HelperDTO.class, new BrandNameDAO(), new BrandNameCriteria());
            leftPsBrandName.setContainerDataSource(psBrandContainer);
            leftPsBrandName.setItemCaptionPropertyId(Constants.DESCRIPTION);
            leftPsBrandName.setNullSelectionAllowed(true);
            leftPsBrandName.setNullSelectionItemId(NULL_ID);

            leftRsId.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            leftRsId.setImmediate(true);
            leftRsId.setValidationVisible(true);
            leftRsId.addValidator(new StringLengthValidator(" Rebate Schedule ID should be less than 38 Characters", 0, NumericConstants.THIRTY_EIGHT, true));
            leftRsId.setDescription(leftRsId.getValue());
            /**
             * Value Change Listener for Rebate Schedule ID
             */
            leftRsId.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used for Rebate Schedule Information tab
                 */
                @SuppressWarnings("PMD")
                public void valueChange(final ValueChangeEvent event) {
                    leftRsId.setDescription(leftRsId.getValue());
                }
            });

            leftRsNo.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            leftRsNo.setImmediate(true);
            leftRsNo.setValidationVisible(true);
            leftRsNo.addValidator(new StringLengthValidator(" Rebate Schedule No should be less than or equal to 50 Characters", 0, NumericConstants.FIFTY, true));
            leftRsNo.setDescription(leftRsNo.getValue());
            leftRsNo.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used for Rebate Schedule Information
                 */
                public void valueChange(final ValueChangeEvent event) {
                    leftRsNo.setDescription(leftRsNo.getValue());
                }
            });

            leftRsName.setImmediate(true);
            leftRsName.setValidationVisible(true);
            leftRsName.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            leftRsName.addValidator(new StringLengthValidator("Rebate Schedule Name should be less than 100 Characters", 0, NumericConstants.HUNDRED, true));
            leftRsName.setDescription(leftRsName.getValue());
            leftRsName.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used for Rebate Schedule Name Value Change Listener
                 */
                public void valueChange(final ValueChangeEvent event) {
                    leftRsName.setDescription(leftRsName.getValue());
                }
            });

            leftRsType.setImmediate(true);
            leftRsType.setPageLength(NumericConstants.SEVEN);
            leftRsType.addItem(Constants.SELECT_ONE);
            loadComboBox(leftRsType, headerLogic.getDropDownListWithoutNull(UIUtils.RS_TYPE));
            leftRsType.setNullSelectionAllowed(true);
            leftRsType.setNullSelectionItemId(Constants.SELECT_ONE);
            leftRsType.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(ValueChangeEvent event) {
                    CommonUtils.setNullValue(leftRsType);
                }
            });

            leftRsItemNo.setImmediate(true);
            leftRsItemNo.setValidationVisible(true);
            leftRsItemNo.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            leftRsItemNo.addValidator(new StringLengthValidator(ConstantUtil.ITEM_NO_SHOULD_BE_LESS_THAN_50_CHAR, 0, NumericConstants.FIFTY, true));
            leftRsItemNo.setDescription(leftIfpItemNo.getValue());
            leftRsItemNo.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in itemNo, function will be
                 * executed.
                 *
                 * @param event
                 */
                public void valueChange(final ValueChangeEvent event) {
                    leftRsItemNo.setDescription(leftRsItemNo.getValue());
                }
            });

            leftRsItemName.addValidator(new RegexpValidator(ContractUtils.APOSTROPHE_SP_CHAR, ContractUtils.SPECIAL_CHAR_MSGS));
            leftRsItemName.setImmediate(true);
            leftRsItemName.setValidationVisible(true);
            leftRsItemName.addValidator(new StringLengthValidator(ConstantUtil.ITEM_NAME_SHOULD_BE_LESS_THAN_100_CHAR, 0, NumericConstants.HUNDRED, true));
            leftRsItemName.setDescription(leftRsItemName.getValue());
            leftRsItemName.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in itemName, function will be
                 * executed.
                 *
                 * @param event
                 */
                public void valueChange(final ValueChangeEvent event) {
                    leftRsItemName.setDescription(leftRsItemName.getValue());
                }
            });

            leftRsBrandName.setImmediate(true);
            rsBrandContainer = new LazyContainer(HelperDTO.class, new BrandNameDAO(), new BrandNameCriteria());
            leftRsBrandName.setContainerDataSource(rsBrandContainer);
            leftRsBrandName.setItemCaptionPropertyId(Constants.DESCRIPTION);
            leftRsBrandName.setNullSelectionAllowed(true);
            leftRsBrandName.setNullSelectionItemId(NULL_ID);

            leftRsProgramCategory.setImmediate(true);
            leftRsProgramCategory.setPageLength(NumericConstants.SEVEN);
            leftRsProgramCategory.addItem(Constants.SELECT_ONE);
            loadComboBox(leftRsProgramCategory, headerLogic.getDropDownListWithoutNull(UIUtils.RS_UDC_2));
            leftRsProgramCategory.setNullSelectionAllowed(true);
            leftRsProgramCategory.setNullSelectionItemId(Constants.SELECT_ONE);
            leftRsProgramCategory.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(ValueChangeEvent event) {
                    CommonUtils.setNullValue(leftRsProgramCategory);
                }
            });

            rightComponent.setImmediate(true);
            loadRightComponentDropdown();
            loadRightSearchType();

            contractRightGridLayout.setVisible(true);
            cfpRightGridLayout.setVisible(false);
            ifpRightGridLayout.setVisible(false);
            psRightGridLayout.setVisible(false);
            rsRightGridLayout.setVisible(false);
            contractDetailRightGridLayout.setVisible(false);
            cfpDetailRightGridLayout.setVisible(false);
            ifpDetailRightGridLayout.setVisible(false);
            psDetailRightGridLayout.setVisible(false);
            rsDetailRightGridLayout.setVisible(false);

            rightContractId.addValidator(new StringLengthValidator("Contract ID length should be less than 38 characters", 0, NumericConstants.THIRTY_EIGHT, true));
            rightContractId.setImmediate(true);
            rightContractId.setValidationVisible(true);
            rightContractId.setDescription(rightContractId.getValue());
            rightContractId.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * sets the value for contractId Textfield on Value change.
                 *
                 * @param event - ValueChangeEvent
                 */
                @SuppressWarnings("PMD")
                public void valueChange(final ValueChangeEvent event) {
                    rightContractId.setDescription(rightContractId.getValue());
                }
            });

            rightContractNo.addValidator(new StringLengthValidator("Contract No should be less than 50 characters", 0, NumericConstants.FIFTY, true));
            rightContractNo.setImmediate(true);
            rightContractNo.setValidationVisible(true);
            rightContractNo.setDescription(rightContractNo.getValue());
            rightContractNo.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Sets the description for Contract Number.
                 *
                 * @param event - ValueChangeEvent
                 */
                public void valueChange(final ValueChangeEvent event) {
                    rightContractNo.setDescription(rightContractNo.getValue());
                }
            });

            rightContractName.addValidator(new StringLengthValidator("Contract Name should be less than 100 characters", 0, NumericConstants.HUNDRED, true));
            rightContractName.setImmediate(true);
            rightContractName.setValidationVisible(true);
            rightContractName.setDescription(rightContractName.getValue());
            rightContractName.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * sets the value for contractName Textfield on Value change.
                 *
                 * @param event - ValueChangeEvent
                 */
                @SuppressWarnings("PMD")
                public void valueChange(final ValueChangeEvent event) {
                    rightContractName.setDescription(rightContractName.getValue());
                }
            });

            rightContractType.setImmediate(true);
            rightContractType.setPageLength(NumericConstants.SEVEN);
            rightContractType.addItem(Constants.SELECT_ONE);
            loadComboBox(rightContractType, headerLogic.getDropDownListWithoutNull(UIUtils.CONTRACT_TYPE));
            rightContractType.setNullSelectionAllowed(true);
            rightContractType.setNullSelectionItemId(Constants.SELECT_ONE);
            rightContractType.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(ValueChangeEvent event) {
                    CommonUtils.setNullValue(rightContractType);
                }
            });

            rightContractStartDate.setValidationVisible(true);
            rightContractStartDate.setImmediate(true);
            rightContractStartDate.setDateFormat(Constants.MM_DD_YYYY);
            rightContractStartDate.setId("ContractStartDate");
            rightContractStartDate.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Changes the Date format for PopupdateField.
                 *
                 * @param event - ValueChangeEvent
                 */
                public void valueChange(final ValueChangeEvent event) {
                    rightContractStartDate.setDescription(com.stpl.app.contract.util.CommonUIUtils.convert2DigitTo4DigitYear(rightContractStartDate.getValue()));
                }
            });

            rightContractEndDate.setImmediate(true);
            rightContractEndDate.setValidationVisible(true);
            rightContractEndDate.addValidator(new DateValidatorContractRight("Contract End date should be greater than Contract Start Date"));
            rightContractEndDate.setDateFormat(Constants.MM_DD_YYYY);

            rightCfpId.setImmediate(true);
            rightCfpId.setValidationVisible(true);
            rightCfpId.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            rightCfpId.addValidator(new StringLengthValidator(" CFP ID Should be less than 38 characters", 0, NumericConstants.THIRTY_EIGHT, true));
            rightCfpId.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * This method listens to data source value changes and passes
                 * the changes forwards.
                 */
                public void valueChange(final ValueChangeEvent event) {

                    rightCfpId.setDescription(rightCfpId.getValue());

                }
            });

            rightCfpNo.setImmediate(true);
            rightCfpNo.setValidationVisible(true);
            rightCfpNo.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            rightCfpNo.addValidator(new StringLengthValidator(" CFP No Should be less than 50 characters", 0, NumericConstants.FIFTY, true));
            rightCfpNo.setDescription(rightCfpNo.getValue());
            rightCfpNo.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * This method listens to data source value changes and passes
                 * the changes forwards.
                 */
                public void valueChange(final ValueChangeEvent event) {
                    rightCfpNo.setDescription(rightCfpNo.getValue());

                }
            });

            rightCfpName.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            rightCfpName.setImmediate(true);
            rightCfpName.setValidationVisible(true);
            rightCfpName.addValidator(new StringLengthValidator(" CFP Name Should be less than 100 characters", 0, NumericConstants.HUNDRED, true));
            rightCfpName.setDescription(rightCfpName.getValue());
            rightCfpName.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * This method listens to data source value changes and passes
                 * the changes forwards.
                 */
                @SuppressWarnings("PMD")
                public void valueChange(final ValueChangeEvent event) {

                    rightCfpName.setDescription(rightCfpName.getValue());

                }
            });

            rightCfpType.setImmediate(true);
            rightCfpType.setPageLength(NumericConstants.SEVEN);
            rightCfpType.addItem(Constants.SELECT_ONE);
            loadComboBox(rightCfpType, headerLogic.getDropDownListWithoutNull(UIUtils.CFP_TYPE));
            rightCfpType.setNullSelectionAllowed(true);
            rightCfpType.setNullSelectionItemId(Constants.SELECT_ONE);
            rightCfpType.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(ValueChangeEvent event) {
                    CommonUtils.setNullValue(rightCfpType);
                }
            });

            rightCompanyNo.setImmediate(true);
            rightCompanyNo.setDescription(rightCompanyNo.getValue());
            rightCompanyNo.setValidationVisible(true);
            rightCompanyNo.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            rightCompanyNo.addValidator(new StringLengthValidator(" Company No Should be less than 50 characters", 0, NumericConstants.FIFTY, true));
            rightCompanyNo.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Logic for Value change event.
                 *
                 * @param event
                 */
                public void valueChange(final ValueChangeEvent event) {
                    rightCompanyNo.setDescription(rightCompanyNo.getValue());
                }
            });

            rightCompanyName.setDescription(rightCompanyName.getValue());
            rightCompanyName.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            rightCompanyName.setImmediate(true);
            rightCompanyName.setValidationVisible(true);
            rightCompanyName.addValidator(new StringLengthValidator(" Company Name Should be less than 100 characters", 0, NumericConstants.HUNDRED, true));
            rightCompanyName.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Logic for Value change event.
                 *
                 * @param event
                 */
                @SuppressWarnings("PMD")
                public void valueChange(final ValueChangeEvent event) {
                    rightCompanyName.setDescription(rightCompanyName.getValue());
                }
            });

            rightCompanyType.setImmediate(true);
            rightCompanyType.setPageLength(NumericConstants.SEVEN);
            rightCompanyType.addItem(Constants.SELECT_ONE);
            loadComboBox(rightCompanyType, headerLogic.getDropDownListWithoutNull(UIUtils.COMP_TYPE));
            rightCompanyType.setNullSelectionAllowed(true);
            rightCompanyType.setNullSelectionItemId(Constants.SELECT_ONE);
            rightCompanyType.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(ValueChangeEvent event) {
                    CommonUtils.setNullValue(rightCompanyType);
                }
            });

            rightCompanyCategory.setDescription(rightCompanyCategory.getValue());
            rightCompanyCategory.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            rightCompanyCategory.setImmediate(true);
            rightCompanyCategory.setValidationVisible(true);
            rightCompanyCategory.addValidator(new StringLengthValidator(" Company Category Should be less than 100 characters", 0, NumericConstants.HUNDRED, true));
            rightCompanyCategory.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Logic for Value change event.
                 *
                 * @param event
                 */
                @SuppressWarnings("PMD")
                public void valueChange(final ValueChangeEvent event) {
                    rightCompanyCategory.setDescription(rightCompanyCategory.getValue());
                }
            });

            rightIfpId.setImmediate(true);
            rightIfpId.addValidator(new StringLengthValidator("IFP ID length should be less than 50 characters", 0, NumericConstants.FIFTY, true));
            rightIfpId.addValidator(new RegexpValidator(ContractUtils.SEARCH_SP_CHAR, "IFP ID " + ContractUtils.SEARCH_SPCHAR_MSG));
            rightIfpId.setValidationVisible(true);
            rightIfpId.setDescription(rightIfpId.getValue());
            rightIfpId.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * This method listens to data source value changes and passes
                 * the changes forwards.
                 */
                @SuppressWarnings("PMD")
                public void valueChange(final ValueChangeEvent event) {
                    rightIfpId.setDescription(rightIfpId.getValue());
                }
            });

            rightIfpNo.addValidator(new StringLengthValidator("IFP No length should be less than 50 characters", 0, NumericConstants.FIFTY, true));
            rightIfpNo.addValidator(new RegexpValidator(ContractUtils.SEARCH_SP_CHAR, "IFP No " + ContractUtils.SEARCH_SPCHAR_MSG));
            rightIfpNo.setImmediate(true);
            rightIfpNo.setValidationVisible(true);
            rightIfpNo.setDescription(rightIfpNo.getValue());
            rightIfpNo.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * This method listens to data source value changes and passes
                 * the changes forwards.
                 */
                public void valueChange(final ValueChangeEvent event) {
                    rightIfpNo.setDescription(rightIfpNo.getValue());
                }
            });

            rightIfpName.addValidator(new StringLengthValidator("IFP Name length should be less than 100 characters", 0, NumericConstants.HUNDRED, true));
            rightIfpName.addValidator(new RegexpValidator(ContractUtils.SEARCH_SP_CHAR, "IFP Name " + ContractUtils.SEARCH_SPCHAR_MSG));
            rightIfpName.setImmediate(true);
            rightIfpName.setValidationVisible(true);
            rightIfpName.setDescription(rightIfpName.getValue());
            rightIfpName.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * This method listens to data source value changes and passes
                 * the changes forwards.
                 */
                public void valueChange(final ValueChangeEvent event) {
                    rightIfpName.setDescription(rightIfpName.getValue());
                }
            });

            rightIfpItemNo.setImmediate(true);
            rightIfpItemNo.setValidationVisible(true);
            rightIfpItemNo.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            rightIfpItemNo.addValidator(new StringLengthValidator(ConstantUtil.ITEM_NO_SHOULD_BE_LESS_THAN_50_CHAR, 0, NumericConstants.FIFTY, true));
            rightIfpItemNo.setDescription(rightIfpItemNo.getValue());
            rightIfpItemNo.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in itemNo, function will be
                 * executed.
                 *
                 * @param event
                 */
                public void valueChange(final ValueChangeEvent event) {
                    rightIfpItemNo.setDescription(rightIfpItemNo.getValue());
                }
            });

            rightIfpItemName.addValidator(new RegexpValidator(ContractUtils.APOSTROPHE_SP_CHAR, ContractUtils.SPECIAL_CHAR_MSGS));
            rightIfpItemName.setImmediate(true);
            rightIfpItemName.setValidationVisible(true);
            rightIfpItemName.addValidator(new StringLengthValidator(ConstantUtil.ITEM_NAME_SHOULD_BE_LESS_THAN_100_CHAR, 0, NumericConstants.HUNDRED, true));
            rightIfpItemName.setDescription(rightIfpItemName.getValue());
            rightIfpItemName.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in itemName, function will be
                 * executed.
                 *
                 * @param event
                 */
                public void valueChange(final ValueChangeEvent event) {
                    rightIfpItemName.setDescription(rightIfpItemName.getValue());
                }
            });

            rightIfpType.setImmediate(true);
            rightIfpType.setPageLength(NumericConstants.SEVEN);
            rightIfpType.addItem(Constants.SELECT_ONE);
            loadComboBox(rightIfpType, headerLogic.getDropDownListWithoutNull(UIUtils.IFP_TYPE));
            rightIfpType.setNullSelectionAllowed(true);
            rightIfpType.setNullSelectionItemId(Constants.SELECT_ONE);
            rightIfpType.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(ValueChangeEvent event) {
                    CommonUtils.setNullValue(rightIfpType);
                }
            });

            rightIfpBrandName.setImmediate(true);
            rightIfpBrandContainer = new LazyContainer(HelperDTO.class, new BrandNameDAO(), new BrandNameCriteria());
            rightIfpBrandName.setContainerDataSource(rightIfpBrandContainer);
            rightIfpBrandName.setItemCaptionPropertyId(Constants.DESCRIPTION);
            rightIfpBrandName.setNullSelectionAllowed(true);
            rightIfpBrandName.setNullSelectionItemId(NULL_ID);

            rightIfpTherapeuticClass.setImmediate(true);
            rightIfpTherapeuticClass.setPageLength(NumericConstants.SEVEN);
            rightIfpTherapeuticClass.addItem(Constants.SELECT_ONE);
            loadComboBox(rightIfpTherapeuticClass, headerLogic.getDropDownListWithoutNull(UIUtils.ITEM_THERP_CLASS));
            rightIfpTherapeuticClass.setNullSelectionAllowed(true);
            rightIfpTherapeuticClass.setNullSelectionItemId(Constants.SELECT_ONE);
            rightIfpTherapeuticClass.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(ValueChangeEvent event) {
                    CommonUtils.setNullValue(rightIfpTherapeuticClass);
                }
            });

            rightPsId.setImmediate(true);
            rightPsId.setValidationVisible(true);
            rightPsId.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            rightPsId.addValidator(new StringLengthValidator("Price Schedule Id should be less than 38 characters", 0, NumericConstants.THIRTY_EIGHT, true));
            rightPsId.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Notifies this listener that the Property's value has changed.
                 */
                public void valueChange(final ValueChangeEvent event) {

                    rightPsId.setDescription((String) rightPsId.getValue());
                }
            });

            rightPsNo.setImmediate(true);
            rightPsNo.setValidationVisible(true);
            rightPsNo.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            rightPsNo.addValidator(new StringLengthValidator(" Price Schedule No should be less than 50 characters", 0, NumericConstants.FIFTY, true));
            rightPsNo.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Value Change listener
                 */
                public void valueChange(final ValueChangeEvent event) {

                    rightPsNo.setDescription((String) rightPsNo.getValue());
                }
            });

            rightPsName.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            rightPsName.setImmediate(true);
            rightPsName.setValidationVisible(true);
            rightPsName.addValidator(new StringLengthValidator(" Price Schedule Name Should be less than 100 characters", 0, NumericConstants.HUNDRED, true));
            rightPsName.setDescription(rightPsName.getValue());
            rightPsName.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Value change for PS Name.
                 *
                 * @param event the event
                 */
                public void valueChange(final ValueChangeEvent event) {

                    rightPsName.setDescription(rightPsName.getValue());

                }
            });

            rightPsType.setImmediate(true);
            rightPsType.setPageLength(NumericConstants.SEVEN);
            rightPsType.addItem(Constants.SELECT_ONE);
            loadComboBox(rightPsType, headerLogic.getDropDownListWithoutNull(UIUtils.PS_TYPE));
            rightPsType.setNullSelectionAllowed(true);
            rightPsType.setNullSelectionItemId(Constants.SELECT_ONE);
            rightPsType.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(ValueChangeEvent event) {
                    CommonUtils.setNullValue(rightPsType);
                }
            });

            rightPsItemNo.setImmediate(true);
            rightPsItemNo.setValidationVisible(true);
            rightPsItemNo.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            rightPsItemNo.addValidator(new StringLengthValidator(ConstantUtil.ITEM_NO_SHOULD_BE_LESS_THAN_50_CHAR, 0, NumericConstants.FIFTY, true));
            rightPsItemNo.setDescription(rightIfpItemNo.getValue());
            rightPsItemNo.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in itemNo, function will be
                 * executed.
                 *
                 * @param event
                 */
                public void valueChange(final ValueChangeEvent event) {
                    rightPsItemNo.setDescription(rightPsItemNo.getValue());
                }
            });

            rightPsItemName.addValidator(new RegexpValidator(ContractUtils.APOSTROPHE_SP_CHAR, ContractUtils.SPECIAL_CHAR_MSGS));
            rightPsItemName.setImmediate(true);
            rightPsItemName.setValidationVisible(true);
            rightPsItemName.addValidator(new StringLengthValidator(ConstantUtil.ITEM_NAME_SHOULD_BE_LESS_THAN_100_CHAR, 0, NumericConstants.HUNDRED, true));
            rightPsItemName.setDescription(rightPsItemName.getValue());
            rightPsItemName.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in itemName, function will be
                 * executed.
                 *
                 * @param event
                 */
                public void valueChange(final ValueChangeEvent event) {
                    rightPsItemName.setDescription(rightPsItemName.getValue());
                }
            });

            rightPsTherapeuticClass.setImmediate(true);
            rightPsTherapeuticClass.setPageLength(NumericConstants.SEVEN);
            rightPsTherapeuticClass.addItem(Constants.SELECT_ONE);
            loadComboBox(rightPsTherapeuticClass, headerLogic.getDropDownListWithoutNull(UIUtils.ITEM_THERP_CLASS));
            rightPsTherapeuticClass.setNullSelectionAllowed(true);
            rightPsTherapeuticClass.setNullSelectionItemId(Constants.SELECT_ONE);
            rightPsTherapeuticClass.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(ValueChangeEvent event) {
                    CommonUtils.setNullValue(rightPsTherapeuticClass);
                }
            });

            rightPsBrandName.setImmediate(true);
            rightPsBrandContainer = new LazyContainer(HelperDTO.class, new BrandNameDAO(), new BrandNameCriteria());
            rightPsBrandName.setContainerDataSource(psBrandContainer);
            rightPsBrandName.setItemCaptionPropertyId(Constants.DESCRIPTION);
            rightPsBrandName.setNullSelectionAllowed(true);
            rightPsBrandName.setNullSelectionItemId(NULL_ID);

            rightRsId.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            rightRsId.setImmediate(true);
            rightRsId.setValidationVisible(true);
            rightRsId.addValidator(new StringLengthValidator(" Rebate Schedule ID should be less than 38 Characters", 0, NumericConstants.THIRTY_EIGHT, true));
            rightRsId.setDescription(rightRsId.getValue());
            /**
             * Value Change Listener for Rebate Schedule ID
             */
            rightRsId.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used for Rebate Schedule Information tab
                 */
                @SuppressWarnings("PMD")
                public void valueChange(final ValueChangeEvent event) {
                    rightRsId.setDescription(rightRsId.getValue());
                }
            });

            rightRsNo.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            rightRsNo.setImmediate(true);
            rightRsNo.setValidationVisible(true);
            rightRsNo.addValidator(new StringLengthValidator(" Rebate Schedule No should be less than or equal to 50 Characters", 0, NumericConstants.FIFTY, true));
            rightRsNo.setDescription(rightRsNo.getValue());
            rightRsNo.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used for Rebate Schedule Information
                 */
                public void valueChange(final ValueChangeEvent event) {
                    rightRsNo.setDescription(rightRsNo.getValue());
                }
            });

            rightRsName.setImmediate(true);
            rightRsName.setValidationVisible(true);
            rightRsName.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            rightRsName.addValidator(new StringLengthValidator("Rebate Schedule Name should be less than 100 Characters", 0, NumericConstants.HUNDRED, true));
            rightRsName.setDescription(rightRsName.getValue());
            rightRsName.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used for Rebate Schedule Name Value Change Listener
                 */
                public void valueChange(final ValueChangeEvent event) {
                    rightRsName.setDescription(rightRsName.getValue());
                }
            });

            rightRsType.setImmediate(true);
            rightRsType.setPageLength(NumericConstants.SEVEN);
            rightRsType.addItem(Constants.SELECT_ONE);
            loadComboBox(rightRsType, headerLogic.getDropDownListWithoutNull(UIUtils.RS_TYPE));
            rightRsType.setNullSelectionAllowed(true);
            rightRsType.setNullSelectionItemId(Constants.SELECT_ONE);
            rightRsType.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(ValueChangeEvent event) {
                    CommonUtils.setNullValue(rightRsType);
                }
            });

            rightRsItemNo.setImmediate(true);
            rightRsItemNo.setValidationVisible(true);
            rightRsItemNo.addValidator(new RegexpValidator(ContractUtils.SPECIAL_CHAR, ContractUtils.SPECIAL_CHAR_MSG));
            rightRsItemNo.addValidator(new StringLengthValidator(" Item No should be less than NumericConstants.FIFTY characters", 0, NumericConstants.FIFTY, true));
            rightRsItemNo.setDescription(rightIfpItemNo.getValue());
            rightRsItemNo.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in itemNo, function will be
                 * executed.
                 *
                 * @param event
                 */
                public void valueChange(final ValueChangeEvent event) {
                    rightRsItemNo.setDescription(rightRsItemNo.getValue());
                }
            });

            rightRsItemName.addValidator(new RegexpValidator(ContractUtils.APOSTROPHE_SP_CHAR, ContractUtils.SPECIAL_CHAR_MSGS));
            rightRsItemName.setImmediate(true);
            rightRsItemName.setValidationVisible(true);
            rightRsItemName.addValidator(new StringLengthValidator(ConstantUtil.ITEM_NAME_SHOULD_BE_LESS_THAN_100_CHAR, 0, NumericConstants.HUNDRED, true));
            rightRsItemName.setDescription(rightRsItemName.getValue());
            rightRsItemName.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in itemName, function will be
                 * executed.
                 *
                 * @param event
                 */
                public void valueChange(final ValueChangeEvent event) {
                    rightRsItemName.setDescription(rightRsItemName.getValue());
                }
            });

            rightRsBrandName.setImmediate(true);
            rsBrandContainer = new LazyContainer(HelperDTO.class, new BrandNameDAO(), new BrandNameCriteria());
            rightRsBrandName.setContainerDataSource(rsBrandContainer);
            rightRsBrandName.setItemCaptionPropertyId(Constants.DESCRIPTION);
            rightRsBrandName.setNullSelectionAllowed(true);
            rightRsBrandName.setNullSelectionItemId(NULL_ID);

            rightRsProgramCategory.setImmediate(true);
            rightRsProgramCategory.setPageLength(NumericConstants.SEVEN);
            rightRsProgramCategory.addItem(Constants.SELECT_ONE);
            loadComboBox(rightRsProgramCategory, headerLogic.getDropDownListWithoutNull(UIUtils.RS_UDC_2));
            rightRsProgramCategory.setNullSelectionAllowed(true);
            rightRsProgramCategory.setNullSelectionItemId(Constants.SELECT_ONE);
            rightRsProgramCategory.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(ValueChangeEvent event) {
                    CommonUtils.setNullValue(rightRsProgramCategory);
                }
            });

        } catch (SystemException e) {
            LOGGER.error(e);
        }
        LOGGER.debug("End of configure method");
    }

    private void loadSearchType() {
        leftSearchType.setImmediate(true);
        leftSearchType.addItem(Constants.SUMMARY);
        leftSearchType.addItem(Constants.DETAILS);
        leftSearchType.select(Constants.SUMMARY);

        leftSearchType.addValueChangeListener(new ValueChangeListener() {
            private static final long serialVersionUID = -2255853716069800092L;

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (leftSearchType.getValue() != null) {
                    if (Constants.SUMMARY.equals(String.valueOf(leftSearchType.getValue()))) {
                        if (ContractUtils.CONTRACT_DETAILS_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
                            contractGridLayout.setVisible(true);
                            cfpGridLayout.setVisible(false);
                            ifpGridLayout.setVisible(false);
                            psGridLayout.setVisible(false);
                            rsGridLayout.setVisible(false);
                            contractDetailGridLayout.setVisible(false);
                            cfpDetailGridLayout.setVisible(false);
                            ifpDetailGridLayout.setVisible(false);
                            psDetailGridLayout.setVisible(false);
                            rsDetailGridLayout.setVisible(false);
                            getLeftBinder();

                        } else if (ContractUtils.CFP_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
                            contractGridLayout.setVisible(false);
                            cfpGridLayout.setVisible(true);
                            ifpGridLayout.setVisible(false);
                            psGridLayout.setVisible(false);
                            rsGridLayout.setVisible(false);
                            contractDetailGridLayout.setVisible(false);
                            cfpDetailGridLayout.setVisible(false);
                            ifpDetailGridLayout.setVisible(false);
                            psDetailGridLayout.setVisible(false);
                            rsDetailGridLayout.setVisible(false);
                            getLeftBinder();

                        } else if (ContractUtils.IFP_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
                            contractGridLayout.setVisible(false);
                            cfpGridLayout.setVisible(false);
                            ifpGridLayout.setVisible(true);
                            psGridLayout.setVisible(false);
                            rsGridLayout.setVisible(false);
                            contractDetailGridLayout.setVisible(false);
                            cfpDetailGridLayout.setVisible(false);
                            ifpDetailGridLayout.setVisible(false);
                            psDetailGridLayout.setVisible(false);
                            rsDetailGridLayout.setVisible(false);
                            getLeftBinder();

                        } else if (ContractUtils.PS_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
                            contractGridLayout.setVisible(false);
                            cfpGridLayout.setVisible(false);
                            ifpGridLayout.setVisible(false);
                            psGridLayout.setVisible(true);
                            rsGridLayout.setVisible(false);
                            contractDetailGridLayout.setVisible(false);
                            cfpDetailGridLayout.setVisible(false);
                            ifpDetailGridLayout.setVisible(false);
                            psDetailGridLayout.setVisible(false);
                            rsDetailGridLayout.setVisible(false);
                            getLeftBinder();

                        } else if (ContractUtils.RS_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
                            contractGridLayout.setVisible(false);
                            cfpGridLayout.setVisible(false);
                            ifpGridLayout.setVisible(false);
                            psGridLayout.setVisible(false);
                            rsGridLayout.setVisible(true);
                            contractDetailGridLayout.setVisible(false);
                            cfpDetailGridLayout.setVisible(false);
                            ifpDetailGridLayout.setVisible(false);
                            psDetailGridLayout.setVisible(false);
                            rsDetailGridLayout.setVisible(false);
                            getLeftBinder();

                        }
                    } else if (Constants.DETAILS.equals(String.valueOf(leftSearchType.getValue()))) {
                        if (ContractUtils.CONTRACT_DETAILS_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
                            contractGridLayout.setVisible(true);
                            cfpGridLayout.setVisible(false);
                            ifpGridLayout.setVisible(false);
                            psGridLayout.setVisible(false);
                            rsGridLayout.setVisible(false);
                            contractDetailGridLayout.setVisible(true);
                            cfpDetailGridLayout.setVisible(false);
                            ifpDetailGridLayout.setVisible(false);
                            psDetailGridLayout.setVisible(false);
                            rsDetailGridLayout.setVisible(false);
                            getLeftBinder();

                        } else if (ContractUtils.CFP_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
                            contractGridLayout.setVisible(false);
                            cfpGridLayout.setVisible(true);
                            ifpGridLayout.setVisible(false);
                            psGridLayout.setVisible(false);
                            rsGridLayout.setVisible(false);
                            contractDetailGridLayout.setVisible(false);
                            cfpDetailGridLayout.setVisible(true);
                            ifpDetailGridLayout.setVisible(false);
                            psDetailGridLayout.setVisible(false);
                            rsDetailGridLayout.setVisible(false);
                            getLeftBinder();

                        } else if (ContractUtils.IFP_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
                            contractGridLayout.setVisible(false);
                            cfpGridLayout.setVisible(false);
                            ifpGridLayout.setVisible(true);
                            psGridLayout.setVisible(false);
                            rsGridLayout.setVisible(false);
                            contractDetailGridLayout.setVisible(false);
                            cfpDetailGridLayout.setVisible(false);
                            ifpDetailGridLayout.setVisible(true);
                            psDetailGridLayout.setVisible(false);
                            rsDetailGridLayout.setVisible(false);
                            getLeftBinder();

                        } else if (ContractUtils.PS_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
                            contractGridLayout.setVisible(false);
                            cfpGridLayout.setVisible(false);
                            ifpGridLayout.setVisible(false);
                            psGridLayout.setVisible(true);
                            rsGridLayout.setVisible(false);
                            contractDetailGridLayout.setVisible(false);
                            cfpDetailGridLayout.setVisible(false);
                            ifpDetailGridLayout.setVisible(false);
                            psDetailGridLayout.setVisible(true);
                            rsDetailGridLayout.setVisible(false);
                            getLeftBinder();

                        } else if (ContractUtils.RS_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
                            contractGridLayout.setVisible(false);
                            cfpGridLayout.setVisible(false);
                            ifpGridLayout.setVisible(false);
                            psGridLayout.setVisible(false);
                            rsGridLayout.setVisible(true);
                            contractDetailGridLayout.setVisible(false);
                            cfpDetailGridLayout.setVisible(false);
                            ifpDetailGridLayout.setVisible(false);
                            psDetailGridLayout.setVisible(false);
                            rsDetailGridLayout.setVisible(true);
                            getLeftBinder();

                        }
                    }
                }
            }
        });
    }

    private void loadRightSearchType() {
        rightSearchType.setImmediate(true);
        rightSearchType.addItem(Constants.SUMMARY);
        rightSearchType.addItem(Constants.DETAILS);
        rightSearchType.select(Constants.SUMMARY);

        rightSearchType.addValueChangeListener(new ValueChangeListener() {
            private static final long serialVersionUID = -2255853716069800092L;

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (rightSearchType.getValue() != null) {
                    rightCfpTypeLabel.setVisible(true);
                    rightIfpTypeLabel.setVisible(true);
                    rightPsTypeLabel.setVisible(true);
                    rightRsTypeLabel.setVisible(true);
                    rightContractHolderNameLabel.setVisible(true);
                    rightContractHolderNoLabel.setVisible(true);
                    rightCompanyTypeLabel.setVisible(true);
                    rightCompanyCategoryLabel.setVisible(true);
                    rightIfpTherapeuticClassLabel.setVisible(true);
                    rightIfpBrandNameLabel.setVisible(true);
                    rightCompanyIdLabel.setVisible(false);
                    rightCompanyId.setVisible(false);
                    rightIfpItemIdLabel.setVisible(false);
                    rightIfpItemId.setVisible(false);

                    rightCfpType.setVisible(true);
                    rightIfpType.setVisible(true);
                    rightPsType.setVisible(true);
                    rightRsType.setVisible(true);
                    rightContractHolderName.setVisible(true);
                    rightContractHolderNo.setVisible(true);
                    rightCompanyType.setVisible(true);
                    rightCompanyCategory.setVisible(true);
                    rightIfpTherapeuticClass.setVisible(true);
                    rightIfpBrandName.setVisible(true);

                    if (Constants.SUMMARY.equals(String.valueOf(rightSearchType.getValue()))) {
                        if (ContractUtils.CONTRACT_DETAILS_COMPONENT.equals(String.valueOf(rightComponent.getValue()))) {
                            contractRightGridLayout.setVisible(true);
                            cfpRightGridLayout.setVisible(false);
                            ifpRightGridLayout.setVisible(false);
                            psRightGridLayout.setVisible(false);
                            rsRightGridLayout.setVisible(false);
                            contractDetailRightGridLayout.setVisible(false);
                            cfpDetailRightGridLayout.setVisible(false);
                            ifpDetailRightGridLayout.setVisible(false);
                            psDetailRightGridLayout.setVisible(false);
                            rsDetailRightGridLayout.setVisible(false);
                            getRightBinder();

                        } else if (ContractUtils.CFP_COMPONENT.equals(String.valueOf(rightComponent.getValue()))) {
                            contractRightGridLayout.setVisible(false);
                            cfpRightGridLayout.setVisible(true);
                            ifpRightGridLayout.setVisible(false);
                            psRightGridLayout.setVisible(false);
                            rsRightGridLayout.setVisible(false);
                            contractDetailRightGridLayout.setVisible(false);
                            cfpDetailRightGridLayout.setVisible(false);
                            ifpDetailRightGridLayout.setVisible(false);
                            psDetailRightGridLayout.setVisible(false);
                            rsDetailRightGridLayout.setVisible(false);
                            getRightBinder();

                        } else if (ContractUtils.IFP_COMPONENT.equals(String.valueOf(rightComponent.getValue()))) {
                            contractRightGridLayout.setVisible(false);
                            cfpRightGridLayout.setVisible(false);
                            ifpRightGridLayout.setVisible(true);
                            psRightGridLayout.setVisible(false);
                            rsRightGridLayout.setVisible(false);
                            contractDetailRightGridLayout.setVisible(false);
                            cfpDetailRightGridLayout.setVisible(false);
                            ifpDetailRightGridLayout.setVisible(false);
                            psDetailRightGridLayout.setVisible(false);
                            rsDetailRightGridLayout.setVisible(false);
                            getRightBinder();

                        } else if (ContractUtils.PS_COMPONENT.equals(String.valueOf(rightComponent.getValue()))) {
                            contractRightGridLayout.setVisible(false);
                            cfpRightGridLayout.setVisible(false);
                            ifpRightGridLayout.setVisible(false);
                            psRightGridLayout.setVisible(true);
                            rsRightGridLayout.setVisible(false);
                            contractDetailRightGridLayout.setVisible(false);
                            cfpDetailRightGridLayout.setVisible(false);
                            ifpDetailRightGridLayout.setVisible(false);
                            psDetailRightGridLayout.setVisible(false);
                            rsDetailRightGridLayout.setVisible(false);
                            getRightBinder();

                        } else if (ContractUtils.RS_COMPONENT.equals(String.valueOf(rightComponent.getValue()))) {
                            contractGridLayout.setVisible(false);
                            cfpRightGridLayout.setVisible(false);
                            ifpRightGridLayout.setVisible(false);
                            psRightGridLayout.setVisible(false);
                            rsRightGridLayout.setVisible(true);
                            contractDetailRightGridLayout.setVisible(false);
                            cfpDetailRightGridLayout.setVisible(false);
                            ifpDetailRightGridLayout.setVisible(false);
                            psDetailRightGridLayout.setVisible(false);
                            rsDetailRightGridLayout.setVisible(false);
                            getRightBinder();

                        }
                    } else if (Constants.DETAILS.equals(String.valueOf(rightSearchType.getValue()))) {
                        if (ContractUtils.CONTRACT_DETAILS_COMPONENT.equals(String.valueOf(rightComponent.getValue()))) {
                            rightCfpTypeLabel.setVisible(false);
                            rightIfpTypeLabel.setVisible(false);
                            rightPsTypeLabel.setVisible(false);
                            rightRsTypeLabel.setVisible(false);
                            rightContractHolderNameLabel.setVisible(false);
                            rightContractHolderNoLabel.setVisible(false);
                            rightCompanyTypeLabel.setVisible(false);
                            rightCompanyCategoryLabel.setVisible(false);
                            rightIfpTherapeuticClassLabel.setVisible(false);
                            rightIfpBrandNameLabel.setVisible(false);
                            rightCompanyIdLabel.setVisible(true);
                            rightCompanyId.setVisible(true);
                            rightIfpItemIdLabel.setVisible(true);
                            rightIfpItemId.setVisible(true);

                            contractRightGridLayout.setVisible(true);
                            cfpRightGridLayout.setVisible(true);
                            rightCfpType.setVisible(false);
                            ifpRightGridLayout.setVisible(true);
                            rightIfpType.setVisible(false);
                            psRightGridLayout.setVisible(true);
                            rightPsType.setVisible(false);
                            rsRightGridLayout.setVisible(true);
                            rightRsType.setVisible(false);

                            contractDetailRightGridLayout.setVisible(true);
                            rightContractHolderName.setVisible(false);
                            rightContractHolderNo.setVisible(false);
                            cfpDetailRightGridLayout.setVisible(true);
                            rightCompanyType.setVisible(false);
                            rightCompanyCategory.setVisible(false);
                            ifpDetailRightGridLayout.setVisible(true);
                            rightIfpTherapeuticClass.setVisible(false);
                            rightIfpBrandName.setVisible(false);
                            psDetailRightGridLayout.setVisible(false);
                            rsDetailRightGridLayout.setVisible(false);

                            getRightBinder();

                        } else if (ContractUtils.CFP_COMPONENT.equals(String.valueOf(rightComponent.getValue()))) {
                            contractRightGridLayout.setVisible(false);
                            cfpRightGridLayout.setVisible(true);
                            ifpRightGridLayout.setVisible(false);
                            psRightGridLayout.setVisible(false);
                            rsRightGridLayout.setVisible(false);
                            contractDetailRightGridLayout.setVisible(false);
                            cfpDetailRightGridLayout.setVisible(true);
                            ifpDetailRightGridLayout.setVisible(false);
                            psDetailRightGridLayout.setVisible(false);
                            rsDetailRightGridLayout.setVisible(false);
                            getRightBinder();

                        } else if (ContractUtils.IFP_COMPONENT.equals(String.valueOf(rightComponent.getValue()))) {
                            contractRightGridLayout.setVisible(false);
                            cfpRightGridLayout.setVisible(false);
                            ifpRightGridLayout.setVisible(true);
                            psRightGridLayout.setVisible(false);
                            rsRightGridLayout.setVisible(false);
                            contractDetailRightGridLayout.setVisible(false);
                            cfpDetailRightGridLayout.setVisible(false);
                            ifpDetailRightGridLayout.setVisible(true);
                            psDetailRightGridLayout.setVisible(false);
                            rsDetailRightGridLayout.setVisible(false);
                            getRightBinder();

                        } else if (ContractUtils.PS_COMPONENT.equals(String.valueOf(rightComponent.getValue()))) {
                            contractRightGridLayout.setVisible(false);
                            cfpRightGridLayout.setVisible(false);
                            ifpRightGridLayout.setVisible(false);
                            psRightGridLayout.setVisible(true);
                            rsRightGridLayout.setVisible(false);
                            contractDetailRightGridLayout.setVisible(false);
                            cfpDetailRightGridLayout.setVisible(false);
                            ifpDetailRightGridLayout.setVisible(false);
                            psDetailRightGridLayout.setVisible(true);
                            rsDetailRightGridLayout.setVisible(false);
                            getRightBinder();

                        } else if (ContractUtils.RS_COMPONENT.equals(String.valueOf(rightComponent.getValue()))) {
                            contractRightGridLayout.setVisible(false);
                            cfpRightGridLayout.setVisible(false);
                            ifpRightGridLayout.setVisible(false);
                            psRightGridLayout.setVisible(false);
                            rsRightGridLayout.setVisible(true);
                            contractDetailRightGridLayout.setVisible(false);
                            cfpDetailRightGridLayout.setVisible(false);
                            ifpDetailRightGridLayout.setVisible(false);
                            psDetailRightGridLayout.setVisible(false);
                            rsDetailRightGridLayout.setVisible(true);
                            getRightBinder();

                        }
                    }
                }
            }
        });
    }

    private void loadComponentDropdown() {
        leftComponent.setImmediate(true);
        leftComponent.addItem(ContractUtils.CONTRACT_DETAILS_COMPONENT);
        leftComponent.addItem(ContractUtils.CFP_COMPONENT);
        leftComponent.addItem(ContractUtils.IFP_COMPONENT);
        leftComponent.addItem(ContractUtils.PS_COMPONENT);
        leftComponent.addItem(ContractUtils.RS_COMPONENT);
        leftComponent.setNullSelectionAllowed(false);
        leftComponent.setValue(ContractUtils.CONTRACT_DETAILS_COMPONENT);
        leftComponent.select(ContractUtils.CONTRACT_DETAILS_COMPONENT);

        leftComponent.addValueChangeListener(new ValueChangeListener() {
            private static final long serialVersionUID = -2255853716069800092L;

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty().getValue() != null) {
                    table.setSelectable(true);
                    LOGGER.debug("loadTableValues()");
                    table.setContainerDataSource(tableContainer);
                    detailsTable.setContainerDataSource(detailNormalContainer);
                    leftComponentValue = String.valueOf(event.getProperty().getValue());
                    if (ContractUtils.CONTRACT_DETAILS_COMPONENT.equals(leftComponentValue)) {
                        detailsTable.setVisible(false);
                        detailPanel.setVisible(false);
                        if (Constants.SUMMARY.equals(String.valueOf(leftSearchType.getValue()))) {
                            contractGridLayout.setVisible(true);
                            cfpGridLayout.setVisible(false);
                            ifpGridLayout.setVisible(false);
                            psGridLayout.setVisible(false);
                            rsGridLayout.setVisible(false);
                            contractDetailGridLayout.setVisible(false);
                            cfpDetailGridLayout.setVisible(false);
                            ifpDetailGridLayout.setVisible(false);
                            psDetailGridLayout.setVisible(false);
                            rsDetailGridLayout.setVisible(false);
                            getLeftBinder();
                        } else if (Constants.DETAILS.equals(String.valueOf(leftSearchType.getValue()))) {
                            contractGridLayout.setVisible(true);
                            cfpGridLayout.setVisible(false);
                            ifpGridLayout.setVisible(false);
                            psGridLayout.setVisible(false);
                            rsGridLayout.setVisible(false);
                            contractDetailGridLayout.setVisible(true);
                            cfpDetailGridLayout.setVisible(false);
                            ifpDetailGridLayout.setVisible(false);
                            psDetailGridLayout.setVisible(false);
                            rsDetailGridLayout.setVisible(false);
                            getLeftBinder();

                        }

                    } else if (ContractUtils.CFP_COMPONENT.equals(leftComponentValue)) {
                        detailPanel.setVisible(true);
                        detailsTable.setVisible(true);
                        detailsTable.setVisibleColumns(new Object[]{"no", "name", "type", "category", ConstantUtil.STATUS_PROPERTY});
                        detailsTable.setColumnHeaders(new String[]{"Company No", "Company Name", "Company Type", "Company Category", "CFP Status"});
                        if (Constants.SUMMARY.equals(String.valueOf(leftSearchType.getValue()))) {
                            contractGridLayout.setVisible(false);
                            cfpGridLayout.setVisible(true);
                            ifpGridLayout.setVisible(false);
                            psGridLayout.setVisible(false);
                            rsGridLayout.setVisible(false);
                            contractDetailGridLayout.setVisible(false);
                            cfpDetailGridLayout.setVisible(false);
                            ifpDetailGridLayout.setVisible(false);
                            psDetailGridLayout.setVisible(false);
                            rsDetailGridLayout.setVisible(false);
                            getLeftBinder();
                        } else if (Constants.DETAILS.equals(String.valueOf(leftSearchType.getValue()))) {
                            contractGridLayout.setVisible(false);
                            cfpGridLayout.setVisible(true);
                            ifpGridLayout.setVisible(false);
                            psGridLayout.setVisible(false);
                            rsGridLayout.setVisible(false);
                            contractDetailGridLayout.setVisible(false);
                            cfpDetailGridLayout.setVisible(true);
                            ifpDetailGridLayout.setVisible(false);
                            psDetailGridLayout.setVisible(false);
                            rsDetailGridLayout.setVisible(false);
                            getLeftBinder();
                        }

                    } else if (ContractUtils.IFP_COMPONENT.equals(leftComponentValue)) {
                        detailPanel.setVisible(true);
                        detailsTable.setVisible(true);
                        detailsTable.setVisibleColumns(new Object[]{"no", "name", ConstantUtil.STATUS_PROPERTY, Constants.START_DATE, Constants.END_DATE});
                        detailsTable.setColumnHeaders(new String[]{Constants.ITEM_NO2, Constants.ITEM_NAME2, "IFP Status", "IFP Start Date", "IFP End Date"});
                        if (Constants.SUMMARY.equals(String.valueOf(leftSearchType.getValue()))) {
                            contractGridLayout.setVisible(false);
                            cfpGridLayout.setVisible(false);
                            ifpGridLayout.setVisible(true);
                            psGridLayout.setVisible(false);
                            rsGridLayout.setVisible(false);
                            contractDetailGridLayout.setVisible(false);
                            cfpDetailGridLayout.setVisible(false);
                            ifpDetailGridLayout.setVisible(false);
                            psDetailGridLayout.setVisible(false);
                            rsDetailGridLayout.setVisible(false);
                            getLeftBinder();
                        } else if (Constants.DETAILS.equals(String.valueOf(leftSearchType.getValue()))) {
                            contractGridLayout.setVisible(false);
                            cfpGridLayout.setVisible(false);
                            ifpGridLayout.setVisible(true);
                            psGridLayout.setVisible(false);
                            rsGridLayout.setVisible(false);
                            contractDetailGridLayout.setVisible(false);
                            cfpDetailGridLayout.setVisible(false);
                            ifpDetailGridLayout.setVisible(true);
                            psDetailGridLayout.setVisible(false);
                            rsDetailGridLayout.setVisible(false);
                            getLeftBinder();
                        }

                    } else if (ContractUtils.PS_COMPONENT.equals(leftComponentValue)) {
                        detailPanel.setVisible(true);
                        detailsTable.setVisible(true);
                        detailsTable.setVisibleColumns(new Object[]{"no", "name", ConstantUtil.STATUS_PROPERTY, Constants.START_DATE, Constants.END_DATE});
                        detailsTable.setColumnHeaders(new String[]{Constants.ITEM_NO2, Constants.ITEM_NAME2, "PS Status", "PS Start Date", "PS End Date"});

                        if (Constants.SUMMARY.equals(String.valueOf(leftSearchType.getValue()))) {
                            contractGridLayout.setVisible(false);
                            cfpGridLayout.setVisible(false);
                            ifpGridLayout.setVisible(false);
                            psGridLayout.setVisible(true);
                            rsGridLayout.setVisible(false);
                            contractDetailGridLayout.setVisible(false);
                            cfpDetailGridLayout.setVisible(false);
                            ifpDetailGridLayout.setVisible(false);
                            psDetailGridLayout.setVisible(false);
                            rsDetailGridLayout.setVisible(false);
                            getLeftBinder();
                        } else if (Constants.DETAILS.equals(String.valueOf(leftSearchType.getValue()))) {
                            contractGridLayout.setVisible(false);
                            cfpGridLayout.setVisible(false);
                            ifpGridLayout.setVisible(false);
                            psGridLayout.setVisible(true);
                            rsGridLayout.setVisible(false);
                            contractDetailGridLayout.setVisible(false);
                            cfpDetailGridLayout.setVisible(false);
                            ifpDetailGridLayout.setVisible(false);
                            psDetailGridLayout.setVisible(true);
                            rsDetailGridLayout.setVisible(false);
                            getLeftBinder();
                        }

                    } else if (ContractUtils.RS_COMPONENT.equals(leftComponentValue)) {
                        detailPanel.setVisible(true);
                        detailsTable.setVisible(true);
                        detailsTable.setVisibleColumns(new Object[]{"no", "name", ConstantUtil.STATUS_PROPERTY, Constants.START_DATE, Constants.END_DATE});
                        detailsTable.setColumnHeaders(new String[]{Constants.ITEM_NO2, Constants.ITEM_NAME2, "RS Status", "RS Start Date", "RS End Date"});

                        if (Constants.SUMMARY.equals(String.valueOf(leftSearchType.getValue()))) {
                            contractGridLayout.setVisible(false);
                            cfpGridLayout.setVisible(false);
                            ifpGridLayout.setVisible(false);
                            psGridLayout.setVisible(false);
                            rsGridLayout.setVisible(true);
                            contractDetailGridLayout.setVisible(false);
                            cfpDetailGridLayout.setVisible(false);
                            ifpDetailGridLayout.setVisible(false);
                            psDetailGridLayout.setVisible(false);
                            rsDetailGridLayout.setVisible(false);
                            getLeftBinder();
                        } else if (Constants.DETAILS.equals(String.valueOf(leftSearchType.getValue()))) {
                            contractGridLayout.setVisible(false);
                            cfpGridLayout.setVisible(false);
                            ifpGridLayout.setVisible(false);
                            psGridLayout.setVisible(false);
                            rsGridLayout.setVisible(true);
                            contractDetailGridLayout.setVisible(false);
                            cfpDetailGridLayout.setVisible(false);
                            ifpDetailGridLayout.setVisible(false);
                            psDetailGridLayout.setVisible(false);
                            rsDetailGridLayout.setVisible(true);
                            getLeftBinder();
                        }

                    }
                    changeTableHeader(leftComponentValue);
                }
            }
        });
    }

    private void loadRightComponentDropdown() {
        rightComponent.setImmediate(true);
        rightComponent.addItem(ContractUtils.CONTRACT_DETAILS_COMPONENT);
        rightComponent.addItem(ContractUtils.CFP_COMPONENT);
        rightComponent.addItem(ContractUtils.IFP_COMPONENT);
        rightComponent.addItem(ContractUtils.PS_COMPONENT);
        rightComponent.addItem(ContractUtils.RS_COMPONENT);
        rightComponent.setNullSelectionAllowed(false);
        rightComponent.setValue(ContractUtils.CONTRACT_DETAILS_COMPONENT);
        rightComponent.select(ContractUtils.CONTRACT_DETAILS_COMPONENT);

        rightComponent.addValueChangeListener(new ValueChangeListener() {
            private static final long serialVersionUID = -2255853716069800092L;

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty().getValue() != null) {
                    LOGGER.debug("loadTableValues()");
                    rightCfpTypeLabel.setVisible(true);
                    rightIfpTypeLabel.setVisible(true);
                    rightPsTypeLabel.setVisible(true);
                    rightRsTypeLabel.setVisible(true);
                    rightContractHolderNameLabel.setVisible(true);
                    rightContractHolderNoLabel.setVisible(true);
                    rightCompanyTypeLabel.setVisible(true);
                    rightCompanyCategoryLabel.setVisible(true);
                    rightIfpTherapeuticClassLabel.setVisible(true);
                    rightIfpBrandNameLabel.setVisible(true);
                    rightCompanyIdLabel.setVisible(false);
                    rightCompanyId.setVisible(false);
                    rightIfpItemIdLabel.setVisible(false);
                    rightIfpItemId.setVisible(false);

                    rightCfpType.setVisible(true);
                    rightIfpType.setVisible(true);
                    rightPsType.setVisible(true);
                    rightRsType.setVisible(true);
                    rightContractHolderName.setVisible(true);
                    rightContractHolderNo.setVisible(true);
                    rightCompanyType.setVisible(true);
                    rightCompanyCategory.setVisible(true);
                    rightIfpTherapeuticClass.setVisible(true);
                    rightIfpBrandName.setVisible(true);

                    if (ContractUtils.CONTRACT_DETAILS_COMPONENT.equals(String.valueOf(event.getProperty().getValue()))) {

                        if (Constants.SUMMARY.equals(String.valueOf(rightSearchType.getValue()))) {
                            contractRightGridLayout.setVisible(true);
                            cfpRightGridLayout.setVisible(false);
                            ifpRightGridLayout.setVisible(false);
                            psRightGridLayout.setVisible(false);
                            rsRightGridLayout.setVisible(false);
                            contractDetailRightGridLayout.setVisible(false);
                            cfpDetailRightGridLayout.setVisible(false);
                            ifpDetailRightGridLayout.setVisible(false);
                            psDetailRightGridLayout.setVisible(false);
                            rsDetailRightGridLayout.setVisible(false);
                            getRightBinder();
                        } else if (Constants.DETAILS.equals(String.valueOf(rightSearchType.getValue()))) {
                            rightCfpTypeLabel.setVisible(false);
                            rightIfpTypeLabel.setVisible(false);
                            rightPsTypeLabel.setVisible(false);
                            rightRsTypeLabel.setVisible(false);
                            rightContractHolderNameLabel.setVisible(false);
                            rightContractHolderNoLabel.setVisible(false);
                            rightCompanyTypeLabel.setVisible(false);
                            rightCompanyCategoryLabel.setVisible(false);
                            rightIfpTherapeuticClassLabel.setVisible(false);
                            rightIfpBrandNameLabel.setVisible(false);
                            rightCompanyIdLabel.setVisible(true);
                            rightCompanyId.setVisible(true);
                            rightIfpItemIdLabel.setVisible(true);
                            rightIfpItemId.setVisible(true);

                            contractRightGridLayout.setVisible(true);
                            cfpRightGridLayout.setVisible(true);
                            rightCfpType.setVisible(false);
                            ifpRightGridLayout.setVisible(true);
                            rightIfpType.setVisible(false);
                            psRightGridLayout.setVisible(true);
                            rightPsType.setVisible(false);
                            rsRightGridLayout.setVisible(true);
                            rightRsType.setVisible(false);

                            contractDetailRightGridLayout.setVisible(true);
                            rightContractHolderName.setVisible(false);
                            rightContractHolderNo.setVisible(false);
                            cfpDetailRightGridLayout.setVisible(true);
                            rightCompanyType.setVisible(false);
                            rightCompanyCategory.setVisible(false);
                            ifpDetailRightGridLayout.setVisible(true);
                            rightIfpTherapeuticClass.setVisible(false);
                            rightIfpBrandName.setVisible(false);
                            psDetailRightGridLayout.setVisible(false);
                            rsDetailRightGridLayout.setVisible(false);
                            getRightBinder();

                        }

                    } else if (ContractUtils.CFP_COMPONENT.equals(String.valueOf(event.getProperty().getValue()))) {
                        if (Constants.SUMMARY.equals(String.valueOf(rightSearchType.getValue()))) {
                            contractRightGridLayout.setVisible(false);
                            cfpRightGridLayout.setVisible(true);
                            ifpRightGridLayout.setVisible(false);
                            psRightGridLayout.setVisible(false);
                            rsRightGridLayout.setVisible(false);
                            contractDetailRightGridLayout.setVisible(false);
                            cfpDetailRightGridLayout.setVisible(false);
                            ifpDetailRightGridLayout.setVisible(false);
                            psDetailRightGridLayout.setVisible(false);
                            rsDetailRightGridLayout.setVisible(false);
                            getRightBinder();
                        } else if (Constants.DETAILS.equals(String.valueOf(rightSearchType.getValue()))) {
                            contractRightGridLayout.setVisible(false);
                            cfpRightGridLayout.setVisible(true);
                            ifpRightGridLayout.setVisible(false);
                            psRightGridLayout.setVisible(false);
                            rsRightGridLayout.setVisible(false);
                            contractDetailRightGridLayout.setVisible(false);
                            cfpDetailRightGridLayout.setVisible(true);
                            ifpDetailRightGridLayout.setVisible(false);
                            psDetailRightGridLayout.setVisible(false);
                            rsDetailRightGridLayout.setVisible(false);
                            getRightBinder();
                        }

                    } else if (ContractUtils.IFP_COMPONENT.equals(String.valueOf(event.getProperty().getValue()))) {
                        if (Constants.SUMMARY.equals(String.valueOf(rightSearchType.getValue()))) {
                            contractRightGridLayout.setVisible(false);
                            cfpRightGridLayout.setVisible(false);
                            ifpRightGridLayout.setVisible(true);
                            psRightGridLayout.setVisible(false);
                            rsRightGridLayout.setVisible(false);
                            contractDetailRightGridLayout.setVisible(false);
                            cfpDetailRightGridLayout.setVisible(false);
                            ifpDetailRightGridLayout.setVisible(false);
                            psDetailRightGridLayout.setVisible(false);
                            rsDetailRightGridLayout.setVisible(false);
                            getRightBinder();
                        } else if (Constants.DETAILS.equals(String.valueOf(rightSearchType.getValue()))) {
                            contractRightGridLayout.setVisible(false);
                            cfpRightGridLayout.setVisible(false);
                            ifpRightGridLayout.setVisible(true);
                            psRightGridLayout.setVisible(false);
                            rsRightGridLayout.setVisible(false);
                            contractDetailRightGridLayout.setVisible(false);
                            cfpDetailRightGridLayout.setVisible(false);
                            ifpDetailRightGridLayout.setVisible(true);
                            psDetailRightGridLayout.setVisible(false);
                            rsDetailRightGridLayout.setVisible(false);
                            getRightBinder();
                        }

                    } else if (ContractUtils.PS_COMPONENT.equals(String.valueOf(event.getProperty().getValue()))) {
                        if (Constants.SUMMARY.equals(String.valueOf(rightSearchType.getValue()))) {
                            contractRightGridLayout.setVisible(false);
                            cfpRightGridLayout.setVisible(false);
                            ifpRightGridLayout.setVisible(false);
                            psRightGridLayout.setVisible(true);
                            rsRightGridLayout.setVisible(false);
                            contractDetailRightGridLayout.setVisible(false);
                            cfpDetailRightGridLayout.setVisible(false);
                            ifpDetailRightGridLayout.setVisible(false);
                            psDetailRightGridLayout.setVisible(false);
                            rsDetailRightGridLayout.setVisible(false);
                            getRightBinder();
                        } else if (Constants.DETAILS.equals(String.valueOf(rightSearchType.getValue()))) {
                            contractRightGridLayout.setVisible(false);
                            cfpRightGridLayout.setVisible(false);
                            ifpRightGridLayout.setVisible(false);
                            psRightGridLayout.setVisible(true);
                            rsRightGridLayout.setVisible(false);
                            contractDetailRightGridLayout.setVisible(false);
                            cfpDetailRightGridLayout.setVisible(false);
                            ifpDetailRightGridLayout.setVisible(false);
                            psDetailRightGridLayout.setVisible(true);
                            rsDetailRightGridLayout.setVisible(false);
                            getRightBinder();
                        }

                    } else if (ContractUtils.RS_COMPONENT.equals(String.valueOf(event.getProperty().getValue()))) {
                        if (Constants.SUMMARY.equals(String.valueOf(rightSearchType.getValue()))) {
                            contractRightGridLayout.setVisible(false);
                            cfpRightGridLayout.setVisible(false);
                            ifpRightGridLayout.setVisible(false);
                            psRightGridLayout.setVisible(false);
                            rsRightGridLayout.setVisible(true);
                            contractDetailRightGridLayout.setVisible(false);
                            cfpDetailRightGridLayout.setVisible(false);
                            ifpDetailRightGridLayout.setVisible(false);
                            psDetailRightGridLayout.setVisible(false);
                            rsDetailRightGridLayout.setVisible(false);
                            getRightBinder();
                        } else if (Constants.DETAILS.equals(String.valueOf(rightSearchType.getValue()))) {
                            contractRightGridLayout.setVisible(false);
                            cfpRightGridLayout.setVisible(false);
                            ifpRightGridLayout.setVisible(false);
                            psRightGridLayout.setVisible(false);
                            rsRightGridLayout.setVisible(true);
                            contractDetailRightGridLayout.setVisible(false);
                            cfpDetailRightGridLayout.setVisible(false);
                            ifpDetailRightGridLayout.setVisible(false);
                            psDetailRightGridLayout.setVisible(false);
                            rsDetailRightGridLayout.setVisible(true);
                            getRightBinder();
                        }

                    }
                }
            }
        });
    }

    /**
     * Change table header.
     */
    private void changeTableHeader(String componentValue) {
        LOGGER.debug("Entering changeTableHeader method");
        if (ContractUtils.CONTRACT_DETAILS_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
            table.setVisibleColumns(new Object[]{Constants.MEMBER_ID, Constants.MEMBER_NO, Constants.NAME, Constants.MEMBER_TYPE});
            table.setColumnHeaders(Constants.CONTRACT_ID1, Constants.CONTRACT_NO_SP, Constants.CONTRACT_NAME1, Constants.CONTRACT_TYPE_HEADER);
        } else if (ContractUtils.CFP_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
            table.setVisibleColumns(new Object[]{Constants.MEMBER_ID, Constants.MEMBER_NO, Constants.NAME, Constants.MEMBER_TYPE});
            table.setColumnHeaders("CFP ID", "CFP No", "CFP Name", "CFP Type");
        } else if (ContractUtils.IFP_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
            table.setVisibleColumns(new Object[]{Constants.MEMBER_ID, Constants.MEMBER_NO, Constants.NAME, Constants.MEMBER_TYPE});
            table.setColumnHeaders("IFP ID", "IFP No", "IFP Name", "IFP Type");
        } else if (ContractUtils.PS_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
            table.setVisibleColumns(new Object[]{Constants.MEMBER_ID, Constants.MEMBER_NO, Constants.NAME, Constants.MEMBER_TYPE});
            table.setColumnHeaders("PS ID", "PS No", "PS Name", "PS Type");
        } else if (ContractUtils.RS_COMPONENT.equals(String.valueOf(leftComponent.getValue()))) {
            table.setVisibleColumns(new Object[]{Constants.MEMBER_ID, Constants.MEMBER_NO, Constants.NAME, Constants.MEMBER_TYPE});
            table.setColumnHeaders("RS ID", "RS No", "RS Name", "RS Type");
        }
        table.setFilterBarVisible(true);
        table.setFilterDecorator(new ExtDemoFilterDecorator());
        table.setFilterGenerator(new SearchComponentFilterGenerator(componentValue));
        LOGGER.debug("End of changeTableHeader method");
    }

    private Boolean addToTreeMethod() throws SystemException {

        final ContractDashboardLogic logic = new ContractDashboardLogic();
        srcTableBean = table.getValue() == null ? null : getBeanFromID(table.getValue());
        srcTableBeanId = table.getValue() == null ? null : table.getValue();

        if (srcTableBean == null) {
            MessageBox.showPlain(Icon.ERROR, Constants.ERROR, "Please select Search Result", ButtonId.OK);
        } else {

            final BeanItem<?> targetItem = new BeanItem<>((ContractMember) srcTableBeanId);
            treeBean = tree.getValue() == null ? null : getBeanFromID(tree.getValue());
            treeBeanId = tree.getValue() == null ? null : tree.getValue();

            if (treeBean == null) {
                if (srcTableBean.getCategory().equals(Constants.CONTRACT)) {

                    setTreeNode(targetItem, srcTableBean, VerticalDropLocation.MIDDLE, treeBeanId);
                } else {
                    final String message = "Cannot make a " + srcTableBean.getCategory() + " as contracts header";
                    AbstractNotificationUtils.getWarningNotification(ConstantUtil.CRITERIA_MISMATCH, message);
                    return Boolean.FALSE;
                }
            } else {

                //If start date of lower level is greater than upper level
                if (treeBean.getEndDate() != null && srcTableBean.getStartDate().after(treeBean.getEndDate())) {
                    AbstractNotificationUtils.getWarningNotification("Info", "The " + srcTableBean.getCategory() + " Start and End Date must be within the " + treeBean.getCategory() + " Start and End Date.");
                    return Boolean.FALSE;
                } else if (srcTableBean.getEndDate() != null && srcTableBean.getEndDate().before(treeBean.getStartDate())) {
                    AbstractNotificationUtils.getWarningNotification("Info", "The " + srcTableBean.getCategory() + " End Date is less than " + treeBean.getCategory() + " Start Date.");
                    return Boolean.FALSE;
                }

                if (srcTableBean.getStartDate().before(treeBean.getStartDate())) {
                    srcTableBean.setStartDate(treeBean.getStartDate());
                }

                //End date case
                if (treeBean.getEndDate() == null && srcTableBean.getEndDate() == null) {
                    validation(logic.addToTreeValidation(srcTableBean), targetItem, srcTableBean.getCategory());
                } else if (srcTableBean.getEndDate() == null && treeBean.getEndDate() != null) {
                    srcTableBean.setEndDate(treeBean.getEndDate());
                    validation(logic.addToTreeValidation(srcTableBean), targetItem, srcTableBean.getCategory());
                } else if (srcTableBean.getEndDate() != null && treeBean.getEndDate() == null) {
                    validation(logic.addToTreeValidation(srcTableBean), targetItem, srcTableBean.getCategory());
                } else if (srcTableBean.getEndDate().after(treeBean.getEndDate())) {
                    MessageBox.showPlain(Icon.QUESTION, "Info", srcTableBean.getCategory() + " end date is greater.", new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message
                         * box is pressed .
                         *
                         * @param buttonId The buttonId of the pressed button.
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(ButtonId buttonId) {
                            if (buttonId.name().equals("OK")) {
                                try {
                                    srcTableBean.setEndDate(treeBean.getEndDate());
                                    validation(logic.addToTreeValidation(srcTableBean), targetItem, srcTableBean.getCategory());
                                } catch (Exception ex) {
                                    LOGGER.error(ex);
                                }
                            }
                        }
                    }, ButtonId.OK, ButtonId.CANCEL);
                } else if (srcTableBean.getEndDate() != null && treeBean.getEndDate() != null) {
                    validation(logic.addToTreeValidation(srcTableBean), targetItem, srcTableBean.getCategory());
                }
            }
        }
        return Boolean.TRUE;
    }

    /**
     *
     * @param values
     * @param targetItem
     * @throws Exception
     */
    private void validation(List<Object> values, final BeanItem<?> targetItem, String category) throws SystemException {
        String ids = StringUtils.EMPTY;
        for (int i = 0; i < values.size(); i++) {
            if (i == 0 && values.size() != 1) {
                ids = values.get(i) + Constants.COMMA;
            } else if (values.size() == 1) {
                ids = String.valueOf(values.get(i));
            } else {
                ids += String.valueOf(values.get(i));
            }
        }
        if (values.size() > 0) {
            MessageBox.showPlain(Icon.QUESTION, "Info", ids + " will be removed from " + category, new MessageBoxListener() {
                @SuppressWarnings("PMD")
                public void buttonClicked(ButtonId buttonId) {
                    if (buttonId.name().equals("OK")) {
                        try {
                            loadTree(targetItem);
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                    }
                }
            }, ButtonId.OK, ButtonId.CANCEL);

        } else {
            loadTree(targetItem);
        }
        return;
    }

    /**
     * Method for attaching a node to a tree
     *
     * @param targetItem
     * @throws SystemException
     */
    private Boolean loadTree(BeanItem<?> targetItem) throws SystemException {
        if (srcTableBean.getCategory().equals(Constants.CONTRACT)) {
            final String message = "Cannot make a " + srcTableBean.getCategory() + " as child node";
            AbstractNotificationUtils.getWarningNotification(ConstantUtil.CRITERIA_MISMATCH, message);
        } else {
            if (srcTableBean.getCategory().equals(treeBean.getCategory())) {
                final String message = srcTableBean.getCategory() + " cannot be added to  " + treeBean.getCategory();
                AbstractNotificationUtils.getWarningNotification(ConstantUtil.CRITERIA_MISMATCH, message);
            } else {
                if (srcTableBean.getCategory().equals(Constants.PS_VALUE) && treeBean.getCategory().equals(Constants.IFP) && nodeDuplicateCheck()) {
                    final DynamicQuery psDynamicQuery = DynamicQueryFactoryUtil.forClass(PsDetails.class);

                    psDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.PS_MODEL_SID, srcTableBean.getInternalId()));
                    psDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantUtil.INBOUND_STATUS, "D"));
                    psDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(Constants.IFP_SYSTEM_ID)));
                    final List<PsDetails> priceScheduleDetailsList = dashBoardLogic.priceScheduleDetailsDynamicQuery(psDynamicQuery);
                    if (priceScheduleDetailsList.isEmpty()) {
                        AbstractNotificationUtils.getWarningNotification(ConstantUtil.NO_ITEMS, "No items Exists in PS");
                    } else {
                        final String psSystem = String.valueOf(priceScheduleDetailsList.get(0)).trim();
                        if (psSystem.equals(String.valueOf(treeBean.getModelSysId()).trim())) {

                            Object rootItemID = getRootContract(treeBeanId);

                            if (Constants.CONTRACT.equals(getBeanFromID(treeBeanId).getCategory())) {
                                getBeanFromID(targetItem).setContractId(getBeanFromID(treeBeanId).getInternalId());
                                getBeanFromID(targetItem).setContractChild(true);
                            } else {
                                getBeanFromID(targetItem).setContractId(getBeanFromID(treeBeanId).getContractId());
                            }
                            setTreeNode(targetItem, srcTableBean, VerticalDropLocation.MIDDLE, treeBeanId);
                        } else {
                            final String message = srcTableBean.getCategory() + ConstantUtil.DOES_NOT_ASSOCIATE_WITH + treeBean.getCategory();
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, message);
                        }
                    }
                } else if (srcTableBean.getCategory().equals(Constants.RS_VALUE) && treeBean.getCategory().equals(Constants.IFP) && nodeDuplicateCheck()) {
                    LOGGER.debug("Inside Expected Code");
                    final DynamicQuery rsDynamicQuery = DynamicQueryFactoryUtil.forClass(RsDetails.class);
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.RS_MODEL_SID, srcTableBean.getInternalId()));
                    rsDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantUtil.INBOUND_STATUS, "D"));
                    rsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(Constants.IFP_SYSTEM_ID)));
                    final List<RsDetails> rebateScheduleDetailsList = dashBoardLogic.priceScheduleDetailsDynamicQuery(rsDynamicQuery);
                    if (rebateScheduleDetailsList.isEmpty()) {

                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, ConstantUtil.NO_ITEMS_EXISTS_IN_RS);
                    } else {
                        final String rsSystem = String.valueOf(rebateScheduleDetailsList.get(0)).trim();
                        if (rsSystem.equals(String.valueOf(treeBean.getModelSysId()).trim())) {
                            Object rootItemID = getRootContract(treeBeanId);
                            if (Constants.CONTRACT.equals(getBeanFromID(treeBeanId).getCategory())) {
                                getBeanFromID(targetItem).setContractId(getBeanFromID(treeBeanId).getInternalId());
                                getBeanFromID(targetItem).setContractChild(true);
                            } else {
                                getBeanFromID(targetItem).setContractId(getBeanFromID(treeBeanId).getContractId());
                            }
                            setTreeNode(targetItem, srcTableBean, VerticalDropLocation.MIDDLE, treeBeanId);
                        } else {
                            final String message = srcTableBean.getCategory() + ConstantUtil.DOES_NOT_ASSOCIATE_WITH + treeBean.getCategory();
                            AbstractNotificationUtils.getErrorNotification(Constants.ERROR, message);
                        }
                    }
                } else if (srcTableBean.getCategory().equals(Constants.RS_VALUE) && treeBean.getCategory().equals(Constants.PS_VALUE) && nodeDuplicateCheck()) {

                    final DynamicQuery rsDynamicQuery = DynamicQueryFactoryUtil.forClass(RsDetails.class);

                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.RS_MODEL_SID, srcTableBean.getInternalId()));
                    rsDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantUtil.INBOUND_STATUS, "D"));
                    rsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(Constants.IFP_SYSTEM_ID)));
                    final List<RsDetails> rebateScheduleDetailsList = dashBoardLogic.priceScheduleDetailsDynamicQuery(rsDynamicQuery);
                    if (rebateScheduleDetailsList.isEmpty()) {

                        AbstractNotificationUtils.getWarningNotification(ConstantUtil.NO_ITEMS, ConstantUtil.NO_ITEMS_EXISTS_IN_RS);
                    } else {
                        final String rsSystem = String.valueOf(rebateScheduleDetailsList.get(0)).trim();

                        final DynamicQuery psDynamicQuery = DynamicQueryFactoryUtil.forClass(PsDetails.class);
                        psDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.PS_MODEL_SID, treeBean.getModelSysId()));
                        psDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantUtil.INBOUND_STATUS, "D"));
                        psDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(Constants.IFP_SYSTEM_ID)));
                        final List<PsDetails> priceScheduleDetailsList = dashBoardLogic.priceScheduleDetailsDynamicQuery(psDynamicQuery);

                        if (rsSystem.equals(String.valueOf(priceScheduleDetailsList.get(0)).trim())) {
                            if (Constants.CONTRACT.equals(getBeanFromID(treeBeanId).getCategory())) {
                                getBeanFromID(targetItem).setContractId(getBeanFromID(treeBeanId).getInternalId());
                                getBeanFromID(targetItem).setContractChild(true);
                            } else {
                                getBeanFromID(targetItem).setContractId(getBeanFromID(treeBeanId).getContractId());
                            }
                            setTreeNode(targetItem, srcTableBean, VerticalDropLocation.MIDDLE, treeBeanId);
                        } else {
                            final String message = srcTableBean.getCategory() + ConstantUtil.DOES_NOT_ASSOCIATE_WITH + treeBean.getCategory();
                            AbstractNotificationUtils.getWarningNotification(Constants.ERROR, message);
                        }
                    }
                } else {
                    Object rootItemID = getRootContract(treeBeanId);
                    if (tree.hasChildren(rootItemID == null ? treeBeanId : rootItemID)) {
                        childList.clear();
                        final Collection<?> collection = tree.getChildren(treeBeanId );
                        if (collection != null) {
                            for (final Object id : collection) {
                                final ContractMember object = getBeanFromId(id);
                                if (srcTableBean.getInternalId() == object.getModelSysId() && srcTableBean.getCategory().equals(object.getCategory())) {
                                    if ("CFP".equals(srcTableBean.getCategory())) {
                                        final String message = srcTableBean.getCategory() + ConstantUtil.ALREADY_ADDED;
                                        AbstractNotificationUtils.getWarningNotification(ConstantUtil.DUPLICATE_CRITERIA, message);
                                        return Boolean.FALSE;
                                    } else {
                                        final String message = srcTableBean.getCategory() + ConstantUtil.ALREADY_ADDED_IFP + treeBean.getCategory();
                                        AbstractNotificationUtils.getWarningNotification(ConstantUtil.DUPLICATE_CRITERIA, message);
                                        return Boolean.FALSE;
                                    }
                                }
                            }
                        }
                    }
                    if (Constants.CONTRACT.equals(getBeanFromID(treeBeanId).getCategory())) {
                        getBeanFromID(targetItem).setContractId(getBeanFromID(treeBeanId).getInternalId());
                        getBeanFromID(targetItem).setContractChild(true);
                    } else {
                        getBeanFromID(targetItem).setContractId(getBeanFromID(treeBeanId).getContractId());
                    }
                    setTreeNode(targetItem, srcTableBean, VerticalDropLocation.MIDDLE, treeBeanId);

                }
            }
        }
        srcTableBean = NULL_BEAN;
        srcTableBeanId = Constants.NULL_OBJECT;
        return Boolean.TRUE;
    }
    
    
    private boolean nodeDuplicateCheck() {
        Object rootItemID = getRootContract(treeBeanId);
        if (tree.hasChildren(rootItemID == null ? treeBeanId : rootItemID)) {
            childList.clear();
            final Collection<?> collection = tree.getChildren(treeBeanId);
            if (collection != null) {
                for (final Object id : collection) {
                    final ContractMember object = getBeanFromId(id);
                    if (srcTableBean.getInternalId() == object.getModelSysId() && srcTableBean.getCategory().equals(object.getCategory())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Gets the null bean item
     *
     * @return Null bean item
     */
    public BeanItem<?> getNULLOBJECT() {
        return NULL_OBJECT;
    }

    private ContractMember getBeanFromID(final Object tableID) {
        BeanItem<?> targetItem;
        if (tableID instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) tableID;
        } else if (tableID instanceof ContractMember) {
            targetItem = new BeanItem<>((ContractMember) tableID);
        } else {
            targetItem = NULL_OBJECT;
        }
        return (ContractMember) targetItem.getBean();
    }

    /**
     * Configures the elements used for the pagination.
     *
     * @return HorizontalLayout - Contains the configured elements used for
     * pagination.
     */
    public HorizontalLayout configureControls() {

        itemsPerPageLabel.setSizeUndefined();
        itemsPerPageSelect.setImmediate(true);
        itemsPerPageSelect.addItem(NumericConstants.FIFTEEN);
        itemsPerPageSelect.addItem(NumericConstants.TWENTY_FIVE);
        itemsPerPageSelect.addItem(NumericConstants.FIFTY);
        itemsPerPageSelect.select(NumericConstants.FIFTEEN);
        itemsPerPageSelect.setNullSelectionAllowed(false);
        itemsPerPageSelect.setWidth(null);
        itemsPerPageSelect.setSizeUndefined();
        itemsPerPageSelect.addValueChangeListener(new ValueChangeListener() {
            private static final long serialVersionUID = -2255853716069800092L;

            @Override
            public void valueChange(
                    com.vaadin.data.Property.ValueChangeEvent event) {
                try {
                    tempPageLength = (Integer) event.getProperty().getValue();
                    processTreeTable.setPageLength(tempPageLength);
                    getProcessedTree(rightSearchBinder);
                } catch (SystemException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    LOGGER.error(errorMsg);
                }
            }
        });

        currentPageTextField.setConverter(new StringToIntegerConverter() {
            @Override
            protected NumberFormat getFormat(Locale locale) {
                NumberFormat result = super.getFormat(locale);
                result.setGroupingUsed(false);
                return result;
            }
        });

        first.setEnabled(false);
        previous.setEnabled(false);
        next.setEnabled(true);
        last.setEnabled(true);

        currentPageTextField.setValue("1");
        currentPageTextField.setStyleName(Reindeer.TEXTFIELD_SMALL);
        currentPageTextField.setImmediate(true);
        currentPageTextField.setNullRepresentation("1");
        currentPageTextField.addValueChangeListener(new ValueChangeListener() {
            private static final long serialVersionUID = -2255853716069800092L;

            @Override
            public void valueChange(
                    com.vaadin.data.Property.ValueChangeEvent event) {
                try {
                    int lastPageNo = currentPageNo;
                    if (event.getProperty().getValue() != null && !"null".equals(event.getProperty().getValue())) { // Added for GAL-8160
                        currentPageNo = Integer.valueOf(event.getProperty().getValue() + StringUtils.EMPTY);
                        if (currentPageNo <= Integer.valueOf(totalPagesLabel.getValue()) && currentPageNo > 0) {
                            if (levelValue == 0) {
                                int start = (currentPageNo - 1) * tempPageLength;
                                int end = currentPageNo * tempPageLength;
                                cdbLogic.getLevel1Hierarchy(rightSearchBinder, hierarchicalContainer, start, end);
                            } else {
                                configureParentHierarchy();
                                populateTableOnButtonClick();
                            }
                        }
                    } else {
//                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Page does not exist"); // In All Modules there is no Notification 
                        currentPageTextField.setValue(String.valueOf(lastPageNo));
                    }
                    controlButtons();
                } catch (SystemException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    LOGGER.error(errorMsg);
                } catch (PortalException ex) {
                    LOGGER.error(ex);
                }
            }
        });
        pageLabel.setWidth(null);
        currentPageTextField.setColumns(NumericConstants.THREE);
        separatorLabel.setWidth(null);
        totalPagesLabel.setWidth(null);

        first.addClickListener(new ClickListener() {
            private static final long serialVersionUID = -355520120491283992L;

            @Override
            public void buttonClick(ClickEvent event) {
                currentPageTextField.setValue("1");
            }
        });
        previous.addClickListener(new ClickListener() {
            private static final long serialVersionUID = -355520120491283992L;

            @Override
            public void buttonClick(ClickEvent event) {
                currentPageTextField.setValue((Integer.valueOf(currentPageTextField.getValue() + StringUtils.EMPTY) - 1) + StringUtils.EMPTY);
            }
        });
        next.addClickListener(new ClickListener() {
            private static final long serialVersionUID = -1927138212640638452L;

            @Override
            public void buttonClick(ClickEvent event) {
                currentPageTextField.setValue((Integer.valueOf(currentPageTextField.getValue() + StringUtils.EMPTY) + 1) + StringUtils.EMPTY);
            }
        });
        last.addClickListener(new ClickListener() {
            private static final long serialVersionUID = -355520120491283992L;

            @Override
            public void buttonClick(ClickEvent event) {
                currentPageTextField.setValue(totalPagesLabel.getValue());
            }
        });
        first.setStyleName(Reindeer.BUTTON_LINK);
        previous.setStyleName(Reindeer.BUTTON_LINK);
        next.setStyleName(Reindeer.BUTTON_LINK);
        last.setStyleName(Reindeer.BUTTON_LINK);

        itemsPerPageLabel.addStyleName("pagedtable-itemsperpagecaption");
        itemsPerPageSelect.addStyleName("pagedtable-itemsperpagecombobox");
        pageLabel.addStyleName("pagedtable-pagecaption");
        currentPageTextField.addStyleName("pagedtable-pagefield");
        separatorLabel.addStyleName("pagedtable-separator");
        totalPagesLabel.addStyleName("pagedtable-total");
        first.addStyleName("pagedtable-first");
        previous.addStyleName("pagedtable-previous");
        next.addStyleName("pagedtable-next");
        last.addStyleName("pagedtable-last");

        itemsPerPageLabel.addStyleName(ConstantUtil.PAGEDTABLELABEL);
        itemsPerPageSelect.addStyleName("pagedtable-combobox");
        pageLabel.addStyleName(ConstantUtil.PAGEDTABLELABEL);
        currentPageTextField.addStyleName(ConstantUtil.PAGEDTABLELABEL);
        separatorLabel.addStyleName(ConstantUtil.PAGEDTABLELABEL);
        totalPagesLabel.addStyleName(ConstantUtil.PAGEDTABLELABEL);
        first.addStyleName(ConstantUtil.PAGEDTABLEBUTTON);
        previous.addStyleName(ConstantUtil.PAGEDTABLEBUTTON);
        next.addStyleName(ConstantUtil.PAGEDTABLEBUTTON);
        last.addStyleName(ConstantUtil.PAGEDTABLEBUTTON);

        pageSize.addComponent(itemsPerPageLabel);
        pageSize.addComponent(itemsPerPageSelect);
        pageSize.setComponentAlignment(itemsPerPageLabel, Alignment.MIDDLE_LEFT);
        pageSize.setComponentAlignment(itemsPerPageSelect,
                Alignment.MIDDLE_LEFT);
        pageSize.setSpacing(true);
        pageManagement.addComponent(first);
        pageManagement.addComponent(previous);
        pageManagement.addComponent(pageLabel);
        pageManagement.addComponent(currentPageTextField);
        pageManagement.addComponent(separatorLabel);
        pageManagement.addComponent(totalPagesLabel);
        pageManagement.addComponent(next);
        pageManagement.addComponent(last);
        pageManagement.setComponentAlignment(first, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(previous, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(pageLabel, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(currentPageTextField,
                Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(separatorLabel,
                Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(totalPagesLabel,
                Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(next, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(last, Alignment.MIDDLE_LEFT);
        pageManagement.setWidth(null);
        pageManagement.setSpacing(true);
        controlBar.addComponent(pageSize);
        controlBar.addComponent(pageManagement);
        controlBar.setComponentAlignment(pageManagement,
                Alignment.MIDDLE_CENTER);
        controlBar.setSpacing(true);
        currentPageTextField.setSizeUndefined();
        return controlBar;
    }

    /**
     * Enables / Disables the pagination control buttons based on the current
     * page number.
     */
    public void controlButtons() {
        if (currentPageTextField.getValue().equals("1")) {
            first.setEnabled(false);
            previous.setEnabled(false);
            next.setEnabled(true);
            last.setEnabled(true);
        } else if (currentPageTextField.getValue().equals(totalPagesLabel.getValue())) {
            first.setEnabled(true);
            previous.setEnabled(true);
            next.setEnabled(false);
            last.setEnabled(false);
        } else {
            first.setEnabled(true);
            previous.setEnabled(true);
            next.setEnabled(true);
            last.setEnabled(true);
        }
    }

    /**
     * Gets the total amount of pages for the first level.
     *
     * @return int - total amount of pages.
     * @throws SystemException
     */
    public int getTotalAmountOfPages() {
        int recordCount = cdbLogic.getRightQueriedCount(rightSearchBinder);
        double pageLength = tempPageLength;
        int pageCount = (int) Math.ceil(recordCount / pageLength);
        if (pageCount < 1) {
            pageCount = 1;
        }
        return pageCount;
    }

    /**
     * Method calculates the hierarchy level of the table and adds the item of
     * particular level to a list. After calling this method only levelValue and
     * levelList fields can be used.
     *
     * @param object Item Id passed as argument on expand event.
     * @return int - Hierarchy Level in the table.
     */
    private int configureLevel(Object item) {
        levelValue = 1;
        parentList.clear();
        while (!processTreeTable.getContainerDataSource().isRoot(item)) {
            parentList.add(item);
            item = processTreeTable.getContainerDataSource().getParent(item);
            levelValue++;
        }
        parentList.add(item);
        Collections.reverse(parentList);
        return levelValue;
    }

    /**
     *
     * Clears the container and adds the parent hierarchy for the pagination
     * purpose .
     */
    private void configureParentHierarchy() {
        processTreeTable.getContainerDataSource().removeAllItems();
        for (int i = 0; i < parentList.size(); i++) {
            processTreeTable.addItem(parentList.get(i));
            processTreeTable.setChildrenAllowed(parentList.get(i), true);
            if (i != 0) {
                processTreeTable.setParent(parentList.get(i), parentList.get(i - 1));
            }
            lastParent = (ContractMember) parentList.get(parentList.size() - 1);
            processTreeTable.getContainerDataSource().setChildrenAllowed(lastParent, true);
        }
    }

    /**
     * Paginates and populates the table based on the Pagination control
     * buttons.
     */
    private void populateTableOnButtonClick() throws SystemException, PortalException {

        int start = ((currentPageNo - 1) * tempPageLength) - ((currentPageNo - 1) * levelValue);
        int end = start + (tempPageLength - levelValue);

        List<ContractMember> contractList;
        if (levelValue == 1) {
            contractList = cdbLogic.getLevel2List(lastParent, start, end, true);
            for (final Iterator<ContractMember> iterator = contractList.iterator(); iterator.hasNext();) {
                contractMember = iterator.next();
                processTreeTable.addItem(contractMember);
                if (cdbLogic.isLevel3ListAvlbl(contractMember.getSystemId(), contractMember.getCategory())) {
                    processTreeTable.getContainerDataSource().setChildrenAllowed(contractMember, true);
                } else {
                    processTreeTable.getContainerDataSource().setChildrenAllowed(contractMember, false);
                }
                processTreeTable.getContainerDataSource().setParent(contractMember, lastParent);
            }
        } else if (levelValue == NumericConstants.TWO) {
            contractList = cdbLogic.getLevel3List(lastParent.getParent1(), lastParent, start, end, true);
            for (final Iterator<ContractMember> iterator = contractList.iterator(); iterator.hasNext();) {
                contractMember = iterator.next();
                processTreeTable.addItem(contractMember);
                if (cdbLogic.isLevel4ListAvlbl(contractMember.getSystemId(), contractMember.getCategory())) {
                    processTreeTable.getContainerDataSource().setChildrenAllowed(contractMember, true);
                } else {
                    processTreeTable.getContainerDataSource().setChildrenAllowed(contractMember, false);
                }
                processTreeTable.getContainerDataSource().setParent(contractMember, lastParent);
            }
        } else if (levelValue == NumericConstants.THREE) {
            contractList = cdbLogic.getLevel4List(lastParent.getParent1(), lastParent.getParent2(), lastParent, start, end, true);
            for (final Iterator<ContractMember> iterator = contractList.iterator(); iterator.hasNext();) {
                contractMember = iterator.next();
                processTreeTable.addItem(contractMember);
                if (cdbLogic.isLevel4ListAvlbl(contractMember.getSystemId(), contractMember.getCategory())) {
                    processTreeTable.getContainerDataSource().setChildrenAllowed(contractMember, true);
                } else {
                    processTreeTable.getContainerDataSource().setChildrenAllowed(contractMember, false);
                }
                processTreeTable.getContainerDataSource().setParent(contractMember, lastParent);
            }
        } else if (levelValue == NumericConstants.FOUR) {
            contractList = cdbLogic.getLevel5List(lastParent.getParent1(), lastParent.getParent2(), lastParent.getParent3(), lastParent, start, end, true);
            for (final Iterator<ContractMember> iterator = contractList.iterator(); iterator.hasNext();) {
                contractMember = iterator.next();
                processTreeTable.addItem(contractMember);
                processTreeTable.getContainerDataSource().setChildrenAllowed(contractMember, false);
                processTreeTable.getContainerDataSource().setParent(contractMember, lastParent);
            }
        }
    }

    /**
     * Sets the total number of pages on expand/collapse events based on the
     * number of child available for a parent.
     *
     * @param count - record count to calculate the number of pages.
     */
    public void setTotalAmountOfPages(final int count) {
        double pagelength = tempPageLength;
        int pageCount = (int) Math.ceil((((count / pagelength) * levelValue) + count) / pagelength);
        if (pageCount <= 0) {
            pageCount = 1;
        }
        totalPagesLabel.setValue(String.valueOf(pageCount));
    }

    @Override
    public void configureFields() throws SystemException, PortalException {
        return;
    }

    @Override
    public void addToContent() throws SystemException, PortalException {
        return;
    }

    @Override
    public void searchLogic() {
        return;
    }

    private void loadComboBox(ComboBox comboBox, List<HelperDTO> dropDownList) {
        for (int i = 0; dropDownList.size() > i; i++) {
            comboBox.addItem(dropDownList.get(i));

        }
    }

    /**
     * Inner Class DateValidatorContract that extends AbstractValidator to
     * validate the DateFields.
     */
    public class DateValidatorContract extends AbstractValidator {

        /**
         * Default Constructor.
         */
        public DateValidatorContract() {
            super(StringUtils.EMPTY);
        }

        /**
         * Parameterized Constructor with parameters error message.
         *
         * @param errorMessage the error message
         */
        public DateValidatorContract(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * Validates the startDate and EndDate field for equals dates or
         * mismatching startDate and EndDate.
         *
         * @param value - Object
         * @throws InvalidValueException the invalid value exception
         */
        public void validate(final Object value) {
            LOGGER.debug("Entering validate method for start date and end date");
            if (leftContractStartDate.getValue() != null && leftContractEndDate.getValue() != null) {
                if (leftContractStartDate.getValue().after(leftContractEndDate.getValue())) {
                    throw new Validator.InvalidValueException("Contract End date should be after Contract Start Date");
                } else if (leftContractStartDate.getValue().getTime() == leftContractEndDate.getValue().getTime()) {
                    throw new Validator.InvalidValueException("Contract Start date and Contract End date are equal");
                }
            }
            LOGGER.debug("validate method ends ");
        }

        /**
         * compares the date field and returns the boolean value.
         *
         * @param value - Object
         * @return true, if checks if is valid value
         */
        @Override
        protected boolean isValidValue(final Object value) {
            LOGGER.debug("Entering validValue method ");
            if (leftContractStartDate.getValue() != null && leftContractEndDate.getValue() != null) {
                final boolean isValid = leftContractStartDate.getValue().compareTo(leftContractEndDate.getValue()) <= 0;
                LOGGER.debug("isValidValue method ends after returning isValid= " + isValid);
                return isValid;
            }
            LOGGER.debug("isValidValue method ends after returning isValid = true");
            return true;
        }

        /**
         * Abstract method Overridded.
         *
         * @return Class object.
         */
        @Override
        public Class getType() {
            return null;
        }
    }

    /**
     * Inner Class DateValidatorContract that extends AbstractValidator to
     * validate the DateFields.
     */
    public class DateValidatorContractRight extends AbstractValidator {

        /**
         * Default Constructor.
         */
        public DateValidatorContractRight() {
            super(StringUtils.EMPTY);
        }

        /**
         * Parameterized Constructor with parameters error message.
         *
         * @param errorMessage the error message
         */
        public DateValidatorContractRight(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * Validates the startDate and EndDate field for equals dates or
         * mismatching startDate and EndDate.
         *
         * @param value - Object
         * @throws InvalidValueException the invalid value exception
         */
        public void validate(final Object value) {
            LOGGER.debug("Entering validate method for start date and end date");
            if (rightContractStartDate.getValue() != null && rightContractEndDate.getValue() != null) {
                if (rightContractStartDate.getValue().after(rightContractEndDate.getValue())) {
                    throw new Validator.InvalidValueException("Contract End date should be after Contract Start Date");
                } else if (rightContractStartDate.getValue().getTime() == rightContractEndDate.getValue().getTime()) {
                    throw new Validator.InvalidValueException("Contract Start date and Contract End date are equal");
                }
            }
            LOGGER.debug("validate method ends ");
        }

        /**
         * compares the date field and returns the boolean value.
         *
         * @param value - Object
         * @return true, if checks if is valid value
         */
        @Override
        protected boolean isValidValue(final Object value) {
            LOGGER.debug("Entering validValue method ");
            if (rightContractStartDate.getValue() != null && rightContractEndDate.getValue() != null) {
                final boolean isValid = rightContractStartDate.getValue().compareTo(rightContractEndDate.getValue()) <= 0;
                LOGGER.debug("isValidValue method ends after returning isValid= " + isValid);
                return isValid;
            }
            LOGGER.debug("isValidValue method ends after returning isValid = true");
            return true;
        }

        /**
         * Abstract method Overridded.
         *
         * @return Class object.
         */
        @Override
        public Class getType() {
            return null;
        }
    }

    private Boolean validateEndStart(Object sourceBean, Object targetBean) {

        srcTableBean = sourceBean == null ? null : getBeanFromID(sourceBean);
        srcTableBeanId = sourceBean == null ? null : sourceBean;
        treeBean = targetBean == null ? null : getBeanFromID(targetBean);
        treeBeanId = targetBean == null ? null : targetBean;

        if (treeBean == null) {
        } else {

            //If start date of lower level is greater than upper level
            if (treeBean.getEndDate() != null && srcTableBean.getStartDate().after(treeBean.getEndDate())) {
                AbstractNotificationUtils.getWarningNotification("Info", "The " + srcTableBean.getCategory() + " Start and End Date must be within the " + treeBean.getCategory() + " Start and End Date.");
                return Boolean.FALSE;
            } else if (srcTableBean.getEndDate() != null && srcTableBean.getEndDate().before(treeBean.getStartDate())) {
                AbstractNotificationUtils.getWarningNotification("Info", "The " + srcTableBean.getCategory() + " End Date is less than " + treeBean.getCategory() + " Start Date.");
                return Boolean.FALSE;
            }
            if (srcTableBean.getStartDate().before(treeBean.getStartDate())) {
                srcTableBean.setStartDate(treeBean.getStartDate());
            }
            //End date case
            if (treeBean.getEndDate() == null && srcTableBean.getEndDate() == null) {
            } else if (srcTableBean.getEndDate() == null && treeBean.getEndDate() != null) {
                srcTableBean.setEndDate(treeBean.getEndDate());
            } else if (srcTableBean.getEndDate() != null && treeBean.getEndDate() == null) {
            } else if (srcTableBean.getEndDate().after(treeBean.getEndDate())) {
                MessageBox.showPlain(Icon.QUESTION, "Info", srcTableBean.getCategory() + " end date is greater.", new MessageBoxListener() {
                    /**
                     * The method is triggered when a button of the message box
                     * is pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(ButtonId buttonId) {
                        if (buttonId.name().equals("OK")) {
                            try {
                                srcTableBean.setEndDate(treeBean.getEndDate());
                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }
                        }
                    }
                }, ButtonId.OK, ButtonId.CANCEL);
            }
        }
        return Boolean.TRUE;
    }
}
