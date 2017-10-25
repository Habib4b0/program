package com.stpl.app.global.company.logic;

import com.stpl.app.global.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.global.abstractsearch.util.ConstantUtil;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.company.dto.CompanyCrtIdentifierDTO;
import com.stpl.app.global.company.dto.CompanyMasterDTO;
import com.stpl.app.global.company.dto.SearchCompanyForm;
import com.stpl.app.global.company.util.CommonUtils;
import com.stpl.app.global.company.util.FiledNameUtils;
import com.stpl.app.global.company.util.UIUtils;
import com.stpl.app.global.dao.impl.CompanySearchLogicDAOImpl;
import com.stpl.app.global.dao.impl.StplSecurityDAOImpl;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.model.CompanyIdentifier;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.CompanyParentDetails;
import com.stpl.app.model.CompanyQualifier;
import com.stpl.app.model.CompanyTradeClass;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.MasterDataFiles;
import com.stpl.app.model.Udcs;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.CompanyParentDetailsLocalServiceUtil;
import com.stpl.app.service.CompanyQualifierLocalServiceUtil;
import com.stpl.app.service.CompanyTradeClassLocalServiceUtil;
import com.stpl.app.service.MasterDataFilesLocalServiceUtil;
import com.stpl.app.service.UdcsLocalServiceUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.service.CompanyIdentifierLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.ui.UDCIncrementCheck;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.HelperDTO;
import com.stpl.app.util.HelperUtils;
import com.stpl.app.util.NotesTabLogic;
import com.stpl.app.util.ValidationUtils;
import com.stpl.domain.global.company.CompanyLogic;
import com.stpl.domain.global.company.CompanyMasterDAO;
import com.stpl.domain.global.security.StplSecurityDAO;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CommonUtil;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

// TODO: Auto-generated Javadoc

/**
 * The Class CompanySearchLogic holds all business logic for company.
 */
public class CompanySearchLogic extends BeanItemContainer<SearchCompanyForm> implements CompanyLogic {

    /**
     * The Constant LOGGER to track CompanySearchLogic.
     */
    private static final Logger LOGGER = Logger.getLogger(CompanySearchLogic.class);
    /**
     * The dao to communicate with DB.
     */
    private static CompanyMasterDAO dao = new CompanySearchLogicDAOImpl();
    /**
     * The company dynamic query.
     */
    public final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
    /**
     * trueFlag.
     */
    private int trueFlag = 1;
    /**
     * The false flag.
     */
    private int falseFlag;
    /**
     * The company qualifier name count.
     */
    private static int companyQualifierNameCount;
    /**
     * The Stpl Security related DAO.
     */
    private final  StplSecurityDAO securityDto = new StplSecurityDAOImpl();
    /**
     * The Notes tab logic related Object.
     */
    private NotesTabLogic notesLogic = new NotesTabLogic();
    HelperListUtil helperListUtil=HelperListUtil.getInstance();
    SessionDTO sessionDTO;

    /**
     * The Constructor.
     */
    public CompanySearchLogic(SessionDTO sessionDTO) {
        super(SearchCompanyForm.class);
        this.sessionDTO=sessionDTO;
    }
    public CompanySearchLogic() {
        super(SearchCompanyForm.class);
    }

    /**
     * Gets the search count.
     *
     * @param searchCompanyForm the search company form
     * @return the search count
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public int getSearchCountParentNoLookup(final ErrorfulFieldGroup searchCompanyForm, final BeanSearchCriteria search) throws SystemException, PortalException {
        LOGGER.debug("getSearchCount p1: SearchCompanyForm");
        int temp = 0;
        final SearchCompanyForm searchForm = new SearchCompanyForm();
        String itemIdentifier = StringUtils.EMPTY;
        Map<String, Object> parameters = new HashMap<String, Object>();
        int qualifierId = 0;
        String companyId = StringUtils.EMPTY;
        String companyNo = StringUtils.EMPTY;
        String companyName = StringUtils.EMPTY;
        String companyType = StringUtils.EMPTY;
        String companyStatus = StringUtils.EMPTY;
        String companyCategory = StringUtils.EMPTY;
        String companyGroup = StringUtils.EMPTY;
        String tradeClass = StringUtils.EMPTY;
        final SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);

        List itemMasterList = null;
        if (searchCompanyForm.getField(ConstantsUtils.COMPANY_ID).getValue().toString() != null) {
            companyId = searchCompanyForm.getField(ConstantsUtils.COMPANY_ID).getValue().toString().trim();
            companyId = CommonUtil.buildSearchCriteria(companyId);
        }
        if (searchCompanyForm.getField(ConstantsUtils.COMPANY_NO).getValue().toString() != null) {
            companyNo = searchCompanyForm.getField(ConstantsUtils.COMPANY_NO).getValue().toString().trim();
            companyNo = CommonUtil.buildSearchCriteria(companyNo);
        }
        if (searchCompanyForm.getField(ConstantsUtils.COMPANY_NAME).getValue().toString() != null) {
            companyName = searchCompanyForm.getField(ConstantsUtils.COMPANY_NAME).getValue().toString().trim();
            companyName = CommonUtil.buildSearchCriteria(companyName);
        }
        
        if (searchCompanyForm.getField(ConstantsUtils.IDENTIFIER) != null && !String.valueOf(searchCompanyForm.getField(ConstantsUtils.IDENTIFIER).getValue()).equals(ConstantsUtils.NULL)) {
            itemIdentifier = searchCompanyForm.getField(ConstantsUtils.IDENTIFIER).getValue().toString().trim();
        }
        if (searchCompanyForm.getField(ConstantsUtils.IDENTIFIER_TYPE_DESC) != null && !String.valueOf(searchCompanyForm.getField(ConstantsUtils.IDENTIFIER_TYPE_DESC).getValue()).equals(ConstantsUtils.NULL)) {
            final HelperDTO helperDTO = (HelperDTO) searchCompanyForm.getField(ConstantsUtils.IDENTIFIER_TYPE_DESC).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

                qualifierId = helperDTO.getId();

            }
        }
        if (searchCompanyForm.getField(ConstantsUtils.COMPANY_TYPE).getValue() != null) {
            companyType = searchCompanyForm.getField(ConstantsUtils.COMPANY_TYPE).getValue().toString();
        }
        if (searchCompanyForm.getField(ConstantsUtils.COMPANY_STATUS).getValue() != null) {
            companyStatus = searchCompanyForm.getField(ConstantsUtils.COMPANY_STATUS).getValue().toString();
        }


        if (searchCompanyForm.getField(ConstantsUtils.COMPANY_CATEGORY) != null && searchCompanyForm.getField(ConstantsUtils.COMPANY_CATEGORY).getValue() != null) {
            companyCategory = searchCompanyForm.getField(ConstantsUtils.COMPANY_CATEGORY).getValue().toString();
        }

        if (searchCompanyForm.getField(ConstantsUtils.COMPANY_GROUP) != null && searchCompanyForm.getField(ConstantsUtils.COMPANY_GROUP).getValue() != null) {
            companyGroup = searchCompanyForm.getField(ConstantsUtils.COMPANY_GROUP).getValue().toString();
        }
        if (searchCompanyForm.getField(ConstantsUtils.TRADE_CLASS) != null && searchCompanyForm.getField(ConstantsUtils.TRADE_CLASS).getValue() != null) {
            tradeClass = searchCompanyForm.getField(ConstantsUtils.TRADE_CLASS).getValue().toString();
        }

        int flag = 0;
        if (search != null && search.getFilters() != null) {

            for (Container.Filter filter : search.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    if (ConstantsUtils.COMPANY_STATUS.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.COMPANY_STATUS, Integer.valueOf(stringFilter.getFilterString()));
                        
                    } else if (ConstantsUtils.COM_TYPE.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.COM_TYPE, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.TRADE_CLASS.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.TRADE_CLASS, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.COMPANY_GROUP.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.COMPANY_GROUP, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.COMPANY_CATEGORY.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.COMPANY_CATEGORY, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.ORGANIZATION_KEY.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.ORG_KEY, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC1.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC1, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC2.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC2, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC3.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC3, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC4.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC4, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC5.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC5, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC6.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC6, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC7.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC7, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC8.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC8, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.STATE.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.STATE, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.COUNTRY.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.COUNTRY, Integer.valueOf(stringFilter.getFilterString()));
                    } else {
                        String filterString = CommonUtil.buildFilterCriteria(stringFilter.getFilterString());
                        parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId(), filterString);
                    }
                } else if (filter instanceof Between) { 
                    Between betweenFilter = (Between) filter; 
                    Date startValue = (Date) betweenFilter.getStartValue(); 
                    Date endValue = (Date) betweenFilter.getEndValue(); 
                    parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.FROM, String.valueOf(dateFormat.format(startValue)));
                    parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.TO, String.valueOf(dateFormat.format(endValue))); 
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    if (operation.EQUAL.toString().equals(operation.name())) {
                        if (((Integer) stringFilter.getValue()) == 0) {
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~=", String.valueOf(stringFilter.getValue()) + "--" + "0");
                        } else {
                            int val = (Integer) stringFilter.getValue();
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~=", String.valueOf(val) + "--" + "=");
                        }
                    }
                    if (operation.GREATER.toString().equals(operation.name())) {
                        int val = (Integer) stringFilter.getValue();
                        if (val < 0) {
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~>", String.valueOf(val) + "--" + ">0");
                        } else {
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~>", String.valueOf(val) + "--" + ">");
                        }
                    }
                    if (operation.LESS.toString().equals(operation.name())) {
                        int val = (Integer) stringFilter.getValue();
                        if (val > 0) {
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~<", String.valueOf(val) + "--" + "<0");
                        } else {
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~<", String.valueOf(val) + "--" + "<");
                        }
                    }
                     if (stringFilter.getValue() instanceof Date) {
                    Date value = (Date) stringFilter.getValue();
                    if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                        parameters.put(stringFilter.getPropertyId() + ConstantsUtils.FROM, String.valueOf(dateFormat.format(value)));
                    } else {
                        parameters.put(stringFilter.getPropertyId() + ConstantsUtils.TO, String.valueOf(dateFormat.format(value)));
                    }
                     }
                } else if (filter instanceof And) {
                    And stringFilter = (And) filter;
                    Collection<Filter> value = stringFilter.getFilters();
                    for (Filter filter1 : value) {
                        if (filter1 instanceof Compare.Less) {

                            Compare.Less less = (Compare.Less) filter1;
                            parameters.put(ConstantsUtils.FILTER + less.getPropertyId() + "~<<", String.valueOf(less.getValue()) + "--" + "<<");
                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;
                            parameters.put(ConstantsUtils.FILTER + greater.getPropertyId() + "~>>", String.valueOf(greater.getValue()) + "--" + ">>");
                        }
                    }
                }
            }
        }

        if (qualifierId != Constants.ZERO) {
            searchForm.setQualifierFlag(true);
            if (StringUtils.isNotBlank(itemIdentifier)) {
                searchForm.setIdentifierFlag(true);
                itemIdentifier = itemIdentifier.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            }
            flag = 1;
        }
        if (flag == Constants.ZERO) {
            itemMasterList = dao.findCompanyMasterV1(companyId, companyNo, companyName, companyStatus, companyType, companyCategory, companyGroup, tradeClass, 0, null, null, false, null, null,parameters);

            temp = itemMasterList.size();
        }
        if (flag == Constants.ONE) {
            itemMasterList = dao.findCompanyMasterV1(companyId, companyNo, companyName, companyStatus, companyType, companyCategory, companyGroup, tradeClass, qualifierId, itemIdentifier, null, false, null, null,parameters);

            temp = itemMasterList.size();
        }
        LOGGER.debug("return:" + temp);

        return temp;

    }

    /**
     * Gets the records from Company Master table based on user selection with
     * start and end index.
     *
     * @param searchCompanyForm the search company form
     * @param start the start
     * @param end the end
     * @param orderByColumns the order by columns
     * @return the list< search company form>
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public List<SearchCompanyForm> searchCompanyParentNoLookup(final ErrorfulFieldGroup searchCompanyForm, final int start, final int end, final List<OrderByColumn> orderByColumns, final BeanSearchCriteria criteria) throws SystemException,
            PortalException {
        LOGGER.debug("searchCompany p1: SearchCompanyForm p2:" + start + " p3:" + end + " p4: List<OrderByColumn> size" + orderByColumns.size());
        FiledNameUtils.loadDbColumnName();
        final SearchCompanyForm searchForm = new SearchCompanyForm();
        Map<String, Object> parameters = new HashMap<String, Object>();
        String itemIdentifier = StringUtils.EMPTY;
        int qualifierId = 0;
        String companyId = StringUtils.EMPTY;
        String companyNo = StringUtils.EMPTY;
        String companyName = StringUtils.EMPTY;
        String companyType = StringUtils.EMPTY;
        String companyCategory = StringUtils.EMPTY;
        String companyGroup = StringUtils.EMPTY;
        String tradeClass = StringUtils.EMPTY;
        String companyStatus = StringUtils.EMPTY;
        String columnName = StringUtils.EMPTY;
        String dbColumnName = StringUtils.EMPTY;
        List itemMasterList = null;
        final SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
        List<SearchCompanyForm> searchList = new ArrayList<SearchCompanyForm>();
        if (searchCompanyForm.getField(ConstantsUtils.COMPANY_ID).getValue().toString() != null) {
            companyId = searchCompanyForm.getField(ConstantsUtils.COMPANY_ID).getValue().toString().trim();
            companyId = companyId.replace(GlobalConstants.getPercent(), GlobalConstants.getPercentForEscape());
            companyId = companyId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        if (searchCompanyForm.getField(ConstantsUtils.COMPANY_NO).getValue().toString() != null) {

            companyNo = searchCompanyForm.getField(ConstantsUtils.COMPANY_NO).getValue().toString().trim();
            companyNo = companyNo.replace(GlobalConstants.getPercent(), GlobalConstants.getPercentForEscape());
            companyNo = companyNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        if (searchCompanyForm.getField(ConstantsUtils.COMPANY_NAME).getValue().toString() != null) {
            companyName = searchCompanyForm.getField(ConstantsUtils.COMPANY_NAME).getValue().toString().trim();
            companyName = companyName.replace(GlobalConstants.getPercent(), GlobalConstants.getPercentForEscape());
            companyName = companyName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        if (searchCompanyForm.getField(ConstantsUtils.IDENTIFIER) != null && searchCompanyForm.getField(ConstantsUtils.IDENTIFIER).getValue().toString() != null) {
                itemIdentifier = searchCompanyForm.getField(ConstantsUtils.IDENTIFIER).getValue().toString().trim();
        }
        if (searchCompanyForm.getField(ConstantsUtils.IDENTIFIER_TYPE_DESC) != null) {
            final HelperDTO helperDTO = (HelperDTO) searchCompanyForm.getField(ConstantsUtils.IDENTIFIER_TYPE_DESC).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

                qualifierId = helperDTO.getId();

            }
        }
        if (searchCompanyForm.getField(ConstantsUtils.COMPANY_TYPE).getValue() != null) {
            companyType = searchCompanyForm.getField(ConstantsUtils.COMPANY_TYPE).getValue().toString().trim();
        }

        if (searchCompanyForm.getField(ConstantsUtils.COMPANY_STATUS).getValue() != null) {
            companyStatus = searchCompanyForm.getField(ConstantsUtils.COMPANY_STATUS).getValue().toString().trim();
        }
        if (searchCompanyForm.getField(ConstantsUtils.COMPANY_CATEGORY) != null && searchCompanyForm.getField(ConstantsUtils.COMPANY_CATEGORY).getValue() != null) {
            companyCategory = searchCompanyForm.getField(ConstantsUtils.COMPANY_CATEGORY).getValue().toString().trim();
        }
        if (searchCompanyForm.getField(ConstantsUtils.COMPANY_GROUP) != null && searchCompanyForm.getField(ConstantsUtils.COMPANY_GROUP).getValue() != null) {
            companyGroup = searchCompanyForm.getField(ConstantsUtils.COMPANY_GROUP).getValue().toString().trim();
        }
        if (searchCompanyForm.getField(ConstantsUtils.TRADE_CLASS) != null && searchCompanyForm.getField(ConstantsUtils.TRADE_CLASS).getValue() != null) {
            tradeClass = searchCompanyForm.getField(ConstantsUtils.TRADE_CLASS).getValue().toString().trim();
        }



        int flag = 0;

        if (qualifierId != Constants.ZERO) {
            searchForm.setQualifierFlag(true);
            if (StringUtils.isNotBlank(itemIdentifier)) {
                searchForm.setIdentifierFlag(true);
                itemIdentifier = itemIdentifier.replace(GlobalConstants.getPercent(), GlobalConstants.getPercentForEscape());
                itemIdentifier = itemIdentifier.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            }
            flag = 1;
        }
        boolean asc = false;
        for (final Iterator<OrderByColumn> iterator = orderByColumns.iterator(); iterator.hasNext();) {
            final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();

            columnName = orderByColumn.getName();
            dbColumnName = FiledNameUtils.getDBColumnName(columnName);
            if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
                asc = false;
            } else {
                asc = true;
            }
        }

        if (criteria != null && criteria.getFilters() != null) {

            for (Container.Filter filter : criteria.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    if (ConstantsUtils.COMPANY_STATUS.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.COMPANY_STATUS, Integer.valueOf(stringFilter.getFilterString()));
                        
                    } else if (ConstantsUtils.COM_TYPE.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.COM_TYPE, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.TRADE_CLASS.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.TRADE_CLASS, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.COMPANY_GROUP.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.COMPANY_GROUP, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.COMPANY_CATEGORY.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.COMPANY_CATEGORY, Integer.valueOf(stringFilter.getFilterString()));
                    }else if (ConstantsUtils.ORGANIZATION_KEY.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.ORG_KEY, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC1.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC1, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC2.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC2, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC3.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC3, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC4.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC4, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC5.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC5, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC6.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC6, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.STATE.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.STATE, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.COUNTRY.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.COUNTRY, Integer.valueOf(stringFilter.getFilterString()));
                    }  else if (ConstantsUtils.IDENTIFIER_STATUS.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.IDENTIFIER_STATUS, Integer.valueOf(stringFilter.getFilterString()));
                    } else {
                        String filterString = CommonUtil.buildFilterCriteria(stringFilter.getFilterString());
                        parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId(), filterString);
                    }
                } 
                else if (filter instanceof Between) { 
                    Between betweenFilter = (Between) filter; 
                    Date startValue = (Date) betweenFilter.getStartValue(); 
                    Date endValue = (Date) betweenFilter.getEndValue(); 
                    parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.FROM, String.valueOf(dateFormat.format(startValue)));
                    parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.TO, String.valueOf(dateFormat.format(endValue))); 
                }
                else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    if (operation.EQUAL.toString().equals(operation.name())) {
                        if (((Integer) stringFilter.getValue()) == 0) {
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~=", String.valueOf(stringFilter.getValue()) + "--" + "0");
                        } else {
                            int val = (Integer) stringFilter.getValue();
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~=", String.valueOf(val) + "--" + "=");
                        }
                    }
                    if (operation.GREATER.toString().equals(operation.name())) {
                        int val = (Integer) stringFilter.getValue();
                        if (val < 0) {
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~>", String.valueOf(val) + "--" + ">0");
                        } else {
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~>", String.valueOf(val) + "--" + ">");
                        }
                    }
                    if (operation.LESS.toString().equals(operation.name())) {
                        int val = (Integer) stringFilter.getValue();
                        if (val > 0) {
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~<", String.valueOf(val) + "--" + "<0");
                        } else {
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~<", String.valueOf(val) + "--" + "<");
                        }
                    }
                     if (stringFilter.getValue() instanceof Date) {
                    Date value = (Date) stringFilter.getValue();
                    if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                        parameters.put(stringFilter.getPropertyId() + ConstantsUtils.FROM, String.valueOf(dateFormat.format(value)));
                    } else {
                        parameters.put(stringFilter.getPropertyId() + ConstantsUtils.TO, String.valueOf(dateFormat.format(value)));
                    }
                     }
                } else if (filter instanceof And) {
                    And stringFilter = (And) filter;
                    Collection<Filter> value = stringFilter.getFilters();
                    for (Filter filter1 : value) {
                        if (filter1 instanceof Compare.Less) {

                            Compare.Less less = (Compare.Less) filter1;
                            parameters.put(ConstantsUtils.FILTER + less.getPropertyId() + "~<<", String.valueOf(less.getValue()) + "--" + "<<");
                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;
                            parameters.put(ConstantsUtils.FILTER + greater.getPropertyId() + "~>>", String.valueOf(greater.getValue()) + "--" + ">>");
                        }
                    }
                }
            }
        }
        if (flag == Constants.ZERO) {
            itemMasterList = dao.findCompanyMasterV1(companyId, companyNo, companyName, companyStatus, companyType, companyCategory, companyGroup, tradeClass, 0, null, dbColumnName, asc, start, end,parameters);
            searchList = getCustomizedSearchFormFromObjectParentNoLookup(itemMasterList);
        }
        if (flag == Constants.ONE) {
            itemMasterList = dao.findCompanyMasterV1(companyId, companyNo, companyName, companyStatus, companyType, companyCategory, companyGroup, tradeClass, qualifierId, itemIdentifier, dbColumnName, asc, start, end,parameters);
            searchList = getCustomizedSearchFormFromObjectParentNoLookup(itemMasterList);
        }

        LOGGER.debug("return count :" + searchList.size());

        return searchList;
    }
    /**
     * Gets the customized list of SearchCompanyForm from list of CompanyMaster.
     *
     * @param list the list
     * @return the customized search form from object
     * @throws Exception the exception
     */
    public List<SearchCompanyForm> getCustomizedSearchFormFromObjectParentNoLookup(final List list) throws PortalException, SystemException {
        final List<SearchCompanyForm> searchItemList = new ArrayList<SearchCompanyForm>();
        LOGGER.debug("Entering getCustomizedSearchFormFromObject p1:" + ((list == null) ? list : list.size()));

        if (list != null) {
            final Map<Integer, String> helperMap = com.stpl.app.util.GeneralCommonUtils.getCodeDescription();
            for (int i = 0; i < list.size(); i++) {
                final SearchCompanyForm searchCompanyForm = new SearchCompanyForm();
                final Object[] obj = (Object[]) list.get(i);
                final Integer systemId = (Integer) obj[0];
                searchCompanyForm.setSystemId(String.valueOf(systemId));
                searchCompanyForm.setCompanyId(HelperUtils.getString(obj[1]));
                searchCompanyForm.setCompanyNo(HelperUtils.getString(obj[NumericConstants.TWO]));
                searchCompanyForm.setCompanyName(HelperUtils.getString(obj[NumericConstants.THREE]));
                if (obj[NumericConstants.FOUR] != null && StringUtils.isNotBlank(obj[NumericConstants.FOUR].toString())) { //In query fetching description, instead of ID.
                    searchCompanyForm.setCompanyType(String.valueOf(obj[NumericConstants.FOUR]));
                }
                if (obj[NumericConstants.FIVE] != null && StringUtils.isNotBlank(obj[NumericConstants.FIVE].toString()) && (Integer) obj[NumericConstants.FIVE] != 0) {
                    searchCompanyForm.setCompanyStatus(helperMap.get((Integer) obj[NumericConstants.FIVE]));
                }
                if (obj[NumericConstants.SIX] != null) {
                    searchCompanyForm.setIdentifierTypeDesc(new HelperDTO(String.valueOf(obj[NumericConstants.SIX])));
                }
                if (obj[NumericConstants.SEVEN] != null) {
                    searchCompanyForm.setIdentifier(HelperUtils.getString(obj[NumericConstants.SEVEN]));
                }
                searchCompanyForm.setRecordLockStatus(HelperUtils.getString(obj[NumericConstants.EIGHT]));
                if (obj[NumericConstants.TWENTY_SEVEN] != null) {
                    Date startDate = (Date) obj[NumericConstants.TWENTY_SEVEN];
                    searchCompanyForm.setCompStartDate(startDate);
                    searchCompanyForm.setCompanyStartDate(CommonUtils.convertDateToString(startDate));
                }
                if (obj[NumericConstants.TEN] != null) {
                    Date EndDate = (Date) obj[NumericConstants.TEN];
                    searchCompanyForm.setCompEndDate(EndDate);
                    searchCompanyForm.setCompanyEndDate(CommonUtils.convertDateToString(EndDate));
                }
                if (obj[NumericConstants.TWELVE] != null && StringUtils.isNotBlank(obj[NumericConstants.TWELVE].toString())) { //In query fetching description, instead of ID.
                    searchCompanyForm.setCompanyGroup(String.valueOf(obj[NumericConstants.TWELVE]));
                }
                if (obj[NumericConstants.TWENTY_NINE] != null && StringUtils.isNotBlank(obj[NumericConstants.TWENTY_NINE].toString())) { //In query fetching description, instead of ID.
                    searchCompanyForm.setCompanyCategory(String.valueOf(obj[NumericConstants.TWENTY_NINE]));
                }
                searchCompanyForm.setAddress1(HelperUtils.getString(obj[NumericConstants.FOURTEEN]));
                searchCompanyForm.setAddress2(HelperUtils.getString(obj[NumericConstants.FIFTEEN]));
                searchCompanyForm.setCity(HelperUtils.getString(obj[NumericConstants.SIXTEEN]));
                searchCompanyForm.setZipCode(HelperUtils.getString(obj[NumericConstants.EIGHTEEN]));
                if (obj[NumericConstants.SEVENTEEN] != null && StringUtils.isNotBlank(obj[NumericConstants.SEVENTEEN].toString()) && Integer.valueOf(obj[NumericConstants.SEVENTEEN].toString()) != 0) {
                    searchCompanyForm.setState(helperMap.get((Integer) obj[NumericConstants.SEVENTEEN]));
                }
                if (obj[NumericConstants.NINETEEN] != null && StringUtils.isNotBlank(obj[NumericConstants.NINETEEN].toString()) && Integer.valueOf(obj[NumericConstants.NINETEEN].toString()) != 0) {
                    searchCompanyForm.setCountry(helperMap.get(Integer.valueOf(obj[NumericConstants.NINETEEN].toString())));
                }
                searchCompanyForm.setRegionCode(HelperUtils.getString(obj[NumericConstants.TWENTY]));
                searchCompanyForm.setFinancialSystem(HelperUtils.getString(obj[NumericConstants.THIRTEEN]));
                if (obj[NumericConstants.THIRTY] != null && StringUtils.isNotBlank(obj[NumericConstants.THIRTY].toString())) { //In query fetching description, instead of ID.
                    searchCompanyForm.setOrganizationKey(String.valueOf(obj[NumericConstants.THIRTY]));
                }
                if (obj[NumericConstants.NINE] != null) {
                    searchCompanyForm.setLives(String.valueOf(obj[NumericConstants.NINE]));
                }
                searchCompanyForm.setUdc1((obj[NumericConstants.THIRTY_ONE] != null && (Integer) obj[NumericConstants.THIRTY_ONE] != 0) ? helperMap.get((Integer) obj[NumericConstants.THIRTY_ONE]) : StringUtils.EMPTY);
                searchCompanyForm.setUdc2((obj[NumericConstants.THIRTY_TWO] != null && (Integer) obj[NumericConstants.THIRTY_TWO] != 0) ? helperMap.get((Integer) obj[NumericConstants.THIRTY_TWO]) : StringUtils.EMPTY);
                searchCompanyForm.setUdc3((obj[NumericConstants.THIRTY_THREE] != null && (Integer) obj[NumericConstants.THIRTY_THREE] != 0) ? helperMap.get((Integer) obj[NumericConstants.THIRTY_THREE]) : StringUtils.EMPTY);
                searchCompanyForm.setUdc4((obj[NumericConstants.THIRTY_FOUR] != null && (Integer) obj[NumericConstants.THIRTY_FOUR] != 0) ? helperMap.get((Integer) obj[NumericConstants.THIRTY_FOUR]) : StringUtils.EMPTY);
                searchCompanyForm.setUdc5((obj[NumericConstants.THIRTY_FIVE] != null && (Integer) obj[NumericConstants.THIRTY_FIVE] != 0) ? helperMap.get((Integer) obj[NumericConstants.THIRTY_FIVE]) : StringUtils.EMPTY);
                searchCompanyForm.setUdc6((obj[NumericConstants.THIRTY_SIX] != null && (Integer) obj[NumericConstants.THIRTY_SIX] != 0) ? helperMap.get((Integer) obj[NumericConstants.THIRTY_SIX]) : StringUtils.EMPTY);
                if (obj[NumericConstants.THIRTY_SEVEN] != null && StringUtils.isNotBlank(obj[NumericConstants.THIRTY_SEVEN].toString())) { //In query fetching description, instead of ID.
                    searchCompanyForm.setTradeClass(String.valueOf(obj[NumericConstants.THIRTY_SEVEN]));
                }
                if (obj[NumericConstants.THIRTY_EIGHT] != null) {
                    Date startDate = (Date) obj[NumericConstants.THIRTY_EIGHT];
                    searchCompanyForm.setTradeStartDate(startDate);
                    searchCompanyForm.setTradeClassStartDate(CommonUtils.convertDateToString(startDate));
                }
                if (obj[NumericConstants.THIRTY_NINE] != null) {
                    Date EndDate = (Date) obj[NumericConstants.THIRTY_NINE];
                    searchCompanyForm.setTradeEndDate(EndDate);
                    searchCompanyForm.setTradeClassEndDate(CommonUtils.convertDateToString(EndDate));
                }
                if (obj[NumericConstants.FORTY_THREE] != null && !ConstantsUtils.NULL.equalsIgnoreCase(String.valueOf(obj[NumericConstants.FORTY_THREE]))&& StringUtils.isNotBlank(obj[NumericConstants.FORTY_THREE].toString()) ) {
                    searchCompanyForm.setParentCompanyNo(obj[NumericConstants.FORTY_THREE].toString());
                }
                if (obj[NumericConstants.FORTY_ONE]!= null) {
                    Date startDate = (Date) obj[NumericConstants.FORTY_ONE];
                    searchCompanyForm.setParentSDate(startDate);
                    searchCompanyForm.setParentStartDate(CommonUtils.convertDateToString(startDate));
                }
                if (obj[NumericConstants.FORTY_TWO] != null) {
                    Date EndDate = (Date) obj[NumericConstants.FORTY_TWO];
                    searchCompanyForm.setParentEDate(EndDate);
                    searchCompanyForm.setParentEndDate(CommonUtils.convertDateToString(EndDate));
                }
                searchItemList.add(searchCompanyForm);
            }
        }
        LOGGER.debug("return count -" + searchItemList.size());

        return searchItemList;
    }
    /**
     * Gets the total count of Company Master.
     *
     * @return the total count
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public int getTotalCount() throws SystemException, PortalException {
        LOGGER.debug("Entering CompanySearchLogic getTotalCount");
        final int temp = dao.getCompanyMasterTotalCount();
        LOGGER.debug("return Company Count - " + temp);
        return temp;
    }

    /**
     * Gets the search count.
     *
     * @param searchCompanyForm the search company form
     * @return the search count
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public int getSearchCount(final ErrorfulFieldGroup searchCompanyForm, final BeanSearchCriteria search) throws SystemException, PortalException {
 
        
        LOGGER.debug("getSearchCount p1: SearchCompanyForm");
        int temp = 0;
        final SearchResultsDTO searchForm = new SearchResultsDTO();
        String itemIdentifier = StringUtils.EMPTY;
        Map<String, Object> parameters = new HashMap<String, Object>();
        int qualifierId = 0;
        String companyId = StringUtils.EMPTY;
        String companyNo = StringUtils.EMPTY;
        String companyName = StringUtils.EMPTY;
        String companyType = StringUtils.EMPTY;
        String companyStatus = StringUtils.EMPTY;
        String companyCategory = StringUtils.EMPTY;
        String companyGroup = StringUtils.EMPTY;
        String tradeClass = StringUtils.EMPTY;
        final SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
     try{
        List itemMasterList = null;
        if (searchCompanyForm.getField(ConstantsUtils.TEXT1).getValue().toString() != null) {
            companyId = searchCompanyForm.getField(ConstantsUtils.TEXT1).getValue().toString().trim();
            companyId = companyId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        if (searchCompanyForm.getField(ConstantsUtils.TEXT2).getValue().toString() != null) {

            companyNo = searchCompanyForm.getField(ConstantsUtils.TEXT2).getValue().toString().trim();
            companyNo = companyNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        if (searchCompanyForm.getField(ConstantsUtils.TEXT3).getValue().toString() != null) {
            companyName = searchCompanyForm.getField(ConstantsUtils.TEXT3).getValue().toString().trim();
            companyName = companyName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        if (searchCompanyForm.getField(ConstantsUtils.TEXT8) != null && !String.valueOf(searchCompanyForm.getField(ConstantsUtils.TEXT8).getValue()).equals(ConstantsUtils.NULL)) {
            itemIdentifier = searchCompanyForm.getField(ConstantsUtils.TEXT8).getValue().toString().trim();
        }
        if (searchCompanyForm.getField(ConstantsUtils.COMBO6) != null && !String.valueOf(searchCompanyForm.getField(ConstantsUtils.COMBO6).getValue()).equals(ConstantsUtils.NULL)&&!ConstantsUtils.SELECT_ONE.equals(String.valueOf(searchCompanyForm.getField(ConstantsUtils.COMBO6).getValue()))) {
            final HelperDTO helperDTO = (HelperDTO) searchCompanyForm.getField(ConstantsUtils.COMBO6).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

                qualifierId = helperDTO.getId();
                

            }
        }
        if (searchCompanyForm.getField(ConstantsUtils.COMBO2).getValue() != null&&!ConstantsUtils.SELECT_ONE.equals(String.valueOf(searchCompanyForm.getField(ConstantsUtils.COMBO2).getValue()))) {
            
               final HelperDTO helperDTO = (HelperDTO) searchCompanyForm.getField(ConstantsUtils.COMBO2).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

                companyType = String.valueOf(helperDTO.getId()) ;
        
            }
        }
        if (searchCompanyForm.getField(ConstantsUtils.COMBO1).getValue() != null&&!ConstantsUtils.SELECT_ONE.equals(String.valueOf(searchCompanyForm.getField(ConstantsUtils.COMBO1).getValue()))) {
            
                     final HelperDTO helperDTO = (HelperDTO) searchCompanyForm.getField(ConstantsUtils.COMBO1).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

                companyStatus =String.valueOf(helperDTO.getId());
        
            }
            
            
        }


        if (searchCompanyForm.getField(ConstantsUtils.COMBO3) != null  && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(searchCompanyForm.getField(ConstantsUtils.COMBO3).getValue()))) {
            
            
                     final HelperDTO helperDTO = (HelperDTO) searchCompanyForm.getField(ConstantsUtils.COMBO3).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

                companyCategory = String.valueOf(helperDTO.getId());
         
            }
            
        }

        if (searchCompanyForm.getField(ConstantsUtils.COMBO4) != null  && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(searchCompanyForm.getField(ConstantsUtils.COMBO4).getValue()))) {
           
                     final HelperDTO helperDTO = (HelperDTO) searchCompanyForm.getField(ConstantsUtils.COMBO4).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

                companyGroup =String.valueOf(helperDTO.getId());

            }
            
            
        }
        if (searchCompanyForm.getField(ConstantsUtils.COMBO5) != null &&!ConstantsUtils.SELECT_ONE.equals(String.valueOf(searchCompanyForm.getField(ConstantsUtils.COMBO5).getValue()))) {
           
                     final HelperDTO helperDTO = (HelperDTO) searchCompanyForm.getField(ConstantsUtils.COMBO5).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

                tradeClass = String.valueOf(helperDTO.getId());
            }
            
            
            
        }

        int flag = 0;
        if (search != null && search.getFilters() != null) {

            for (Container.Filter filter : search.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    if (ConstantsUtils.COMPANY_STATUS.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.COMPANY_STATUS, Integer.valueOf(stringFilter.getFilterString()));
                        
                    } else if (ConstantsUtils.COM_TYPE.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.COM_TYPE, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.TRADE_CLASS.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.TRADE_CLASS, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.COMPANY_GROUP.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.COMPANY_GROUP, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.COMPANY_CATEGORY.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.COMPANY_CATEGORY, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.ORGANIZATION_KEY.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.ORG_KEY, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC1.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC1, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC2.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC2, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC3.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC3, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC4.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC4, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC5.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC5, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC6.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC6, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC7.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC7, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC8.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC8, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.STATE.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.STATE, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.COUNTRY.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.COUNTRY, Integer.valueOf(stringFilter.getFilterString()));
                    } else {
                        String filterString = CommonUtil.buildFilterCriteria(stringFilter.getFilterString());
                        parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId(), filterString);
                    }
                } else if (filter instanceof Between) { 
                    Between betweenFilter = (Between) filter; 
                    Date startValue = (Date) betweenFilter.getStartValue(); 
                    Date endValue = (Date) betweenFilter.getEndValue(); 
                    parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.FROM, String.valueOf(dateFormat.format(startValue)));
                    parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.TO, String.valueOf(dateFormat.format(endValue))); 
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    if (operation.EQUAL.toString().equals(operation.name())) {
                        if (((Integer) stringFilter.getValue()) == 0) {
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~=", String.valueOf(stringFilter.getValue()) + "--" + "0");
                        } else {
                            int val = (Integer) stringFilter.getValue();
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~=", String.valueOf(val) + "--" + "=");
                        }
                    }
                    if (operation.GREATER.toString().equals(operation.name())) {
                        int val = (Integer) stringFilter.getValue();
                        if (val < 0) {
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~>", String.valueOf(val) + "--" + ">0");
                        } else {
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~>", String.valueOf(val) + "--" + ">");
                        }
                    }
                    if (operation.LESS.toString().equals(operation.name())) {
                        int val = (Integer) stringFilter.getValue();
                        if (val > 0) {
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~<", String.valueOf(val) + "--" + "<0");
                        } else {
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~<", String.valueOf(val) + "--" + "<");
                        }
                    }
                     if (stringFilter.getValue() instanceof Date) {
                    Date value = (Date) stringFilter.getValue();
                    if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                        parameters.put(stringFilter.getPropertyId() + ConstantsUtils.FROM, String.valueOf(dateFormat.format(value)));
                    } else {
                        parameters.put(stringFilter.getPropertyId() + ConstantsUtils.TO, String.valueOf(dateFormat.format(value)));
                    }
                     }
                } else if (filter instanceof And) {
                    And stringFilter = (And) filter;
                    Collection<Filter> value = stringFilter.getFilters();
                    for (Filter filter1 : value) {
                        if (filter1 instanceof Compare.Less) {

                            Compare.Less less = (Compare.Less) filter1;
                            parameters.put(ConstantsUtils.FILTER + less.getPropertyId() + "~<<", String.valueOf(less.getValue()) + "--" + "<<");
                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;
                            parameters.put(ConstantsUtils.FILTER + greater.getPropertyId() + "~>>", String.valueOf(greater.getValue()) + "--" + ">>");
                        }
                    }
                }
            }
        }
        if (qualifierId != Constants.ZERO) {
            searchForm.setQualifierFlag(true);
            if (StringUtils.isNotBlank(itemIdentifier)) {
                searchForm.setIdentifierFlag(true);
                itemIdentifier = itemIdentifier.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            }
            flag = 1;
        }
        if (flag == Constants.ZERO) {
            itemMasterList = dao.findCompanyMasterV1(companyId, companyNo, companyName, companyStatus, companyType, companyCategory, companyGroup, tradeClass, 0, null, null, false, null, null,parameters);

            temp = itemMasterList.size();
        }
        if (flag == Constants.ONE) {
            itemMasterList = dao.findCompanyMasterV1(companyId, companyNo, companyName, companyStatus, companyType, companyCategory, companyGroup, tradeClass, qualifierId, itemIdentifier, null, false, null, null,parameters);

            temp = itemMasterList.size();
        }
        LOGGER.debug("return:" + temp);
       
        
      } catch(Exception e)
      {
      LOGGER.error(e);
      }

         return temp;

    }
    
    /**
     * Gets the records from Company Master table based on user selection with
     * start and end index.
     *
     * @param searchCompanyForm the search company form
     * @param start the start
     * @param end the end
     * @param orderByColumns the order by columns
     * @return the list< search company form>
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public List<SearchResultsDTO> searchCompany(final ErrorfulFieldGroup searchCompanyForm, final int start, final int end, final List<OrderByColumn> orderByColumns, final BeanSearchCriteria criteria) throws SystemException, ParseException,
            PortalException {
        LOGGER.debug("searchCompany p1: SearchCompanyForm p2:" + start + " p3:" + end + " p4: List<OrderByColumn> size" + orderByColumns.size());
        FiledNameUtils.loadDbColumnName();
        final SearchResultsDTO searchForm = new SearchResultsDTO();
        Map<String, Object> parameters = new HashMap<String, Object>();
        String itemIdentifier = StringUtils.EMPTY;
        int qualifierId = 0;
        String companyId = StringUtils.EMPTY;
        String companyNo = StringUtils.EMPTY;
        String companyName = StringUtils.EMPTY;
        String companyType = StringUtils.EMPTY;
        String companyCategory = StringUtils.EMPTY;
        String companyGroup = StringUtils.EMPTY;
        String tradeClass = StringUtils.EMPTY;
        String companyStatus = StringUtils.EMPTY;
        String columnName = StringUtils.EMPTY;
        String dbColumnName = StringUtils.EMPTY;
        List itemMasterList = null;
        final SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
        List<SearchResultsDTO> searchList = new ArrayList<SearchResultsDTO>();
        
        
        
        
        if (searchCompanyForm.getField(ConstantsUtils.TEXT1).getValue().toString() != null) {
            companyId = searchCompanyForm.getField(ConstantsUtils.TEXT1).getValue().toString().trim();
            companyId = companyId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        
        if (searchCompanyForm.getField(ConstantsUtils.TEXT2).getValue().toString() != null) {

            companyNo = searchCompanyForm.getField(ConstantsUtils.TEXT2).getValue().toString().trim();
            companyNo = companyNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        if (searchCompanyForm.getField(ConstantsUtils.TEXT3).getValue().toString() != null) {
            companyName = searchCompanyForm.getField(ConstantsUtils.TEXT3).getValue().toString().trim();
            companyName = companyName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        if (searchCompanyForm.getField(ConstantsUtils.TEXT8) != null && !String.valueOf(searchCompanyForm.getField(ConstantsUtils.TEXT8).getValue()).equals(ConstantsUtils.NULL)) {
            itemIdentifier = searchCompanyForm.getField(ConstantsUtils.TEXT8).getValue().toString().trim();
        }
        if (searchCompanyForm.getField(ConstantsUtils.COMBO6) != null && !String.valueOf(searchCompanyForm.getField(ConstantsUtils.COMBO6).getValue()).equals(ConstantsUtils.NULL)&&!ConstantsUtils.SELECT_ONE.equals(String.valueOf(searchCompanyForm.getField(ConstantsUtils.COMBO6).getValue()))) {
            final HelperDTO helperDTO = (HelperDTO) searchCompanyForm.getField(ConstantsUtils.COMBO6).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

                qualifierId = helperDTO.getId();

            }
         }
        if (searchCompanyForm.getField(ConstantsUtils.COMBO2).getValue() != null&&!ConstantsUtils.SELECT_ONE.equals(String.valueOf(searchCompanyForm.getField(ConstantsUtils.COMBO2).getValue()))) {
            
               final HelperDTO helperDTO = (HelperDTO) searchCompanyForm.getField(ConstantsUtils.COMBO2).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

                companyType = String.valueOf(helperDTO.getId()) ;
            }
        }
        if (searchCompanyForm.getField(ConstantsUtils.COMBO1).getValue() != null&&!ConstantsUtils.SELECT_ONE.equals(String.valueOf(searchCompanyForm.getField(ConstantsUtils.COMBO1).getValue()))) {
            
                     final HelperDTO helperDTO = (HelperDTO) searchCompanyForm.getField(ConstantsUtils.COMBO1).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

                companyStatus =String.valueOf(helperDTO.getId());
            }
            
            
        }


        if (searchCompanyForm.getField(ConstantsUtils.COMBO3) != null  && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(searchCompanyForm.getField(ConstantsUtils.COMBO3).getValue()))) {
            
            
                     final HelperDTO helperDTO = (HelperDTO) searchCompanyForm.getField(ConstantsUtils.COMBO3).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

                companyCategory = String.valueOf(helperDTO.getId());
            }
            
        }

        if (searchCompanyForm.getField(ConstantsUtils.COMBO4) != null  && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(searchCompanyForm.getField(ConstantsUtils.COMBO4).getValue()))) {
           
                     final HelperDTO helperDTO = (HelperDTO) searchCompanyForm.getField(ConstantsUtils.COMBO4).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

                companyGroup =String.valueOf(helperDTO.getId());
            }
            
            
        }
        if (searchCompanyForm.getField(ConstantsUtils.COMBO5) != null &&!ConstantsUtils.SELECT_ONE.equals(String.valueOf(searchCompanyForm.getField(ConstantsUtils.COMBO5).getValue()))) {
           
                     final HelperDTO helperDTO = (HelperDTO) searchCompanyForm.getField(ConstantsUtils.COMBO5).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

                tradeClass = String.valueOf(helperDTO.getId());
            }
        
        }



        int flag = 0;

        if (qualifierId != Constants.ZERO) {
            searchForm.setQualifierFlag(true);
            if (StringUtils.isNotBlank(itemIdentifier)) {
                searchForm.setIdentifierFlag(true);
                itemIdentifier = itemIdentifier.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            }
            flag = 1;
        }
        boolean asc = false;
        for (final Iterator<OrderByColumn> iterator = orderByColumns.iterator(); iterator.hasNext();) {
            final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();

            columnName = orderByColumn.getName();
            dbColumnName = FiledNameUtils.getDBColumnName(columnName);
            if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
                asc = false;
            } else {
                asc = true;
            }
        }

        if (criteria != null && criteria.getFilters() != null) {

            for (Container.Filter filter : criteria.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    if (ConstantsUtils.COMPANY_STATUS.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.COMPANY_STATUS, Integer.valueOf(stringFilter.getFilterString()));
                        
                    } else if (ConstantsUtils.COM_TYPE.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.COM_TYPE, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.TRADE_CLASS.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.TRADE_CLASS, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.COMPANY_GROUP.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.COMPANY_GROUP, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.COMPANY_CATEGORY.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.COMPANY_CATEGORY, Integer.valueOf(stringFilter.getFilterString()));
                    }else if (ConstantsUtils.ORGANIZATION_KEY.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.ORG_KEY, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC1.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC1, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC2.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC2, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC3.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC3, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC4.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC4, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC5.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC5, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC6.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC6, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.STATE.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.STATE, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.COUNTRY.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.COUNTRY, Integer.valueOf(stringFilter.getFilterString()));
                    }  else if (ConstantsUtils.IDENTIFIER_STATUS.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.IDENTIFIER_STATUS, Integer.valueOf(stringFilter.getFilterString()));
                    } else {
                        parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId(), CommonUtil.buildFilterCriteria(stringFilter.getFilterString()));
                    }
                } 
                else if (filter instanceof Between) { 
                    Between betweenFilter = (Between) filter; 
                    Date startValue = (Date) betweenFilter.getStartValue(); 
                    Date endValue = (Date) betweenFilter.getEndValue(); 
                    parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.FROM, String.valueOf(dateFormat.format(startValue)));
                    parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.TO, String.valueOf(dateFormat.format(endValue))); 
                }
                else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    if (operation.EQUAL.toString().equals(operation.name())) {
                        if (((Integer) stringFilter.getValue()) == 0) {
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~=", String.valueOf(stringFilter.getValue()) + "--" + "0");
                        } else {
                            int val = (Integer) stringFilter.getValue();
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~=", String.valueOf(val) + "--" + "=");
                        }
                    }
                    if (operation.GREATER.toString().equals(operation.name())) {
                        int val = (Integer) stringFilter.getValue();
                        if (val < 0) {
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~>", String.valueOf(val) + "--" + ">0");
                        } else {
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~>", String.valueOf(val) + "--" + ">");
                        }
                    }
                    if (operation.LESS.toString().equals(operation.name())) {
                        int val = (Integer) stringFilter.getValue();
                        if (val > 0) {
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~<", String.valueOf(val) + "--" + "<0");
                        } else {
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~<", String.valueOf(val) + "--" + "<");
                        }
                    }
                     if (stringFilter.getValue() instanceof Date) {
                    Date value = (Date) stringFilter.getValue();
                    if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                        parameters.put(stringFilter.getPropertyId() + ConstantsUtils.FROM, String.valueOf(dateFormat.format(value)));
                    } else {
                        parameters.put(stringFilter.getPropertyId() + ConstantsUtils.TO, String.valueOf(dateFormat.format(value)));
                    }
                     }
                } else if (filter instanceof And) {
                    And stringFilter = (And) filter;
                    Collection<Container.Filter> value = stringFilter.getFilters();
                    for (Container.Filter filter1 : value) {
                        if (filter1 instanceof Compare.Less) {

                            Compare.Less less = (Compare.Less) filter1;
                            parameters.put(ConstantsUtils.FILTER + less.getPropertyId() + "~<<", String.valueOf(less.getValue()) + "--" + "<<");
                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;
                            parameters.put(ConstantsUtils.FILTER + greater.getPropertyId() + "~>>", String.valueOf(greater.getValue()) + "--" + ">>");
                        }
                    }
                }
            }
        }
        if (flag == Constants.ZERO) {
            itemMasterList = dao.findCompanyMasterV1(companyId, companyNo, companyName, companyStatus, companyType, companyCategory, companyGroup, tradeClass, 0, null, dbColumnName, asc, start, end,parameters);
            searchList = getCustomizedSearchFormFromObject(itemMasterList);
        }
        if (flag == Constants.ONE) {
            itemMasterList = dao.findCompanyMasterV1(companyId, companyNo, companyName, companyStatus, companyType, companyCategory, companyGroup, tradeClass, qualifierId, itemIdentifier, dbColumnName, asc, start, end,parameters);
            searchList = getCustomizedSearchFormFromObject(itemMasterList);
        }
        
        LOGGER.debug("return count :" + searchList.size());

        return searchList;
    }

    /**
     * Gets the customized list of SearchCompanyForm from list of CompanyMaster.
     *
     * @param list the list
     * @return the customized search form from object
     * @throws Exception the exception
     */
    public List<SearchResultsDTO> getCustomizedSearchFormFromObject(final List list) throws ParseException {
        final List<SearchResultsDTO> searchItemList = new ArrayList<SearchResultsDTO>();
        LOGGER.debug("Entering getCustomizedSearchFormFromObject p1:" + ((list == null) ? list : list.size()));
  
        if (list != null) {
            HelperListUtil helperUtil = HelperListUtil.getInstance();
            Map<Integer,String> helperMap = helperUtil.getIdDescMap();
            for (int i = 0; i < list.size(); i++) {
                final SearchResultsDTO searchCompanyForm = new SearchResultsDTO();
                final Object[] obj = (Object[]) list.get(i);
                final Integer systemId = (Integer) obj[0];
                searchCompanyForm.setCompanySystemId(String.valueOf(systemId));
                searchCompanyForm.setSystemID(searchCompanyForm.getCompanySystemId());
                searchCompanyForm.setCompanyId(HelperUtils.getString(obj[1]));
                searchCompanyForm.setCompanyNo(HelperUtils.getString(obj[NumericConstants.TWO]));
                searchCompanyForm.setCompanyName(HelperUtils.getString(obj[NumericConstants.THREE]));
                    searchCompanyForm.setCompanyType(HelperUtils.getString(obj[NumericConstants.FOUR]));
                if (obj[NumericConstants.FIVE] != null && StringUtils.isNotBlank(obj[NumericConstants.FIVE].toString()) && (Integer) obj[NumericConstants.FIVE] != 0) {
                    searchCompanyForm.setCompanyStatus(helperMap.get((Integer) obj[NumericConstants.FIVE]));
                }
                if (obj[NumericConstants.SIX] != null) {
                    searchCompanyForm.setIdentifierTypeDesc(new HelperDTO(String.valueOf(obj[NumericConstants.SIX])));
                }
                if (obj[NumericConstants.SEVEN] != null) {
                    searchCompanyForm.setIdentifier(HelperUtils.getString(obj[NumericConstants.SEVEN]));
                }
                searchCompanyForm.setRecordLockStatus(Boolean.parseBoolean(HelperUtils.getString(obj[NumericConstants.EIGHT])));
                if (obj[NumericConstants.TWENTY_SEVEN] != null) {
                    Date startDate = (Date) obj[NumericConstants.TWENTY_SEVEN];
                    searchCompanyForm.setComStartDate(startDate);
                    searchCompanyForm.setCompanyStartDate(CommonUtils.convertDateToString(startDate));
                    DateFormat df = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
                    startDate = df.parse(searchCompanyForm.getCompanyStartDate());
                    searchCompanyForm.setComStartDate(startDate);
                }
                if (obj[NumericConstants.TEN] != null) {
                    Date EndDate = (Date) obj[NumericConstants.TEN];
                    searchCompanyForm.setComEndDate(EndDate);
                    searchCompanyForm.setCompanyEndDate(CommonUtils.convertDateToString(EndDate));
                    DateFormat df = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
                    EndDate = df.parse(searchCompanyForm.getCompanyEndDate());
                    searchCompanyForm.setComEndDate(EndDate);
                }
                    searchCompanyForm.setCompanyGroup(HelperUtils.getString(obj[NumericConstants.TWELVE]));
                    searchCompanyForm.setCompanyCategory(HelperUtils.getString(obj[NumericConstants.TWENTY_NINE]));
                searchCompanyForm.setAddress1(HelperUtils.getString(obj[NumericConstants.FOURTEEN]));
                searchCompanyForm.setAddress2(HelperUtils.getString(obj[NumericConstants.FIFTEEN]));
                searchCompanyForm.setCity(HelperUtils.getString(obj[NumericConstants.SIXTEEN]));
                searchCompanyForm.setZipCode(HelperUtils.getString(obj[NumericConstants.EIGHTEEN]));
                if (obj[NumericConstants.SEVENTEEN] != null && StringUtils.isNotBlank(obj[NumericConstants.SEVENTEEN].toString()) && Integer.valueOf(obj[NumericConstants.SEVENTEEN].toString()) != 0) {
                    searchCompanyForm.setState(helperMap.get((Integer) obj[NumericConstants.SEVENTEEN]));
                }
                if (obj[NumericConstants.NINETEEN] != null && StringUtils.isNotBlank(obj[NumericConstants.NINETEEN].toString()) && Integer.valueOf(obj[NumericConstants.NINETEEN].toString()) != 0) {
                    searchCompanyForm.setCountry(helperMap.get(Integer.valueOf(obj[NumericConstants.NINETEEN].toString())));
                }
                searchCompanyForm.setRegionCode(HelperUtils.getString(obj[NumericConstants.TWENTY]));
                searchCompanyForm.setFinancialSystem(HelperUtils.getString(obj[NumericConstants.THIRTEEN]));
                    searchCompanyForm.setOrganizationKey(HelperUtils.getString(obj[NumericConstants.THIRTY]));
                if (obj[NumericConstants.NINE] != null) {
                    searchCompanyForm.setLives(String.valueOf(obj[NumericConstants.NINE]));
                }
                searchCompanyForm.setUdc1((obj[NumericConstants.THIRTY_ONE] != null && (Integer) obj[NumericConstants.THIRTY_ONE] != 0) ? helperMap.get((Integer) obj[NumericConstants.THIRTY_ONE]) : StringUtils.EMPTY);
                searchCompanyForm.setUdc2((obj[NumericConstants.THIRTY_TWO] != null && (Integer) obj[NumericConstants.THIRTY_TWO] != 0) ? helperMap.get((Integer) obj[NumericConstants.THIRTY_TWO]) : StringUtils.EMPTY);
                searchCompanyForm.setUdc3((obj[NumericConstants.THIRTY_THREE] != null && (Integer) obj[NumericConstants.THIRTY_THREE] != 0) ? helperMap.get((Integer) obj[NumericConstants.THIRTY_THREE]) : StringUtils.EMPTY);
                searchCompanyForm.setUdc4((obj[NumericConstants.THIRTY_FOUR] != null && (Integer) obj[NumericConstants.THIRTY_FOUR] != 0) ? helperMap.get((Integer) obj[NumericConstants.THIRTY_FOUR]) : StringUtils.EMPTY);
                searchCompanyForm.setUdc5((obj[NumericConstants.THIRTY_FIVE] != null && (Integer) obj[NumericConstants.THIRTY_FIVE] != 0) ? helperMap.get((Integer) obj[NumericConstants.THIRTY_FIVE]) : StringUtils.EMPTY);
                searchCompanyForm.setUdc6((obj[NumericConstants.THIRTY_SIX] != null && (Integer) obj[NumericConstants.THIRTY_SIX] != 0) ? helperMap.get((Integer) obj[NumericConstants.THIRTY_SIX]) : StringUtils.EMPTY);
                    searchCompanyForm.setTradeClass(HelperUtils.getString(obj[NumericConstants.THIRTY_SEVEN]));
                if (obj[NumericConstants.THIRTY_EIGHT] != null) {
                    Date startDate = (Date) obj[NumericConstants.THIRTY_EIGHT];
                    searchCompanyForm.setComTradeStartDate(startDate);
                    DateFormat df = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
                    startDate = df.parse(CommonUtils.convertDateToString(startDate));
                    searchCompanyForm.setTradeStartDate(startDate);
                    searchCompanyForm.setComTradeStartDate(startDate);
                }
                if (obj[NumericConstants.THIRTY_NINE] != null) {
                   Date EndDate = (Date) obj[NumericConstants.THIRTY_NINE];
                   searchCompanyForm.setComTradeEndDate(EndDate);
                   DateFormat df = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
                   EndDate = df.parse(CommonUtils.convertDateToString(EndDate));
                   searchCompanyForm.setTradeEndDate(EndDate);
                   searchCompanyForm.setComTradeEndDate(EndDate);
                }
                if (obj[NumericConstants.FORTY_THREE] != null && !ConstantsUtils.NULL.equalsIgnoreCase(String.valueOf(obj[NumericConstants.FORTY_THREE]))&& StringUtils.isNotBlank(obj[NumericConstants.FORTY_THREE].toString()) ) {
                    searchCompanyForm.setParentCompanyNo(obj[NumericConstants.FORTY_THREE].toString());
                }
                if (obj[NumericConstants.FORTY_ONE] != null) {
                    Date startDate = (Date) obj[NumericConstants.FORTY_ONE];
                    searchCompanyForm.setComParentSDate(startDate);
                    searchCompanyForm.setParentSDate(startDate);
                    searchCompanyForm.setParentStartDate(CommonUtils.convertDateToString(startDate));
                    DateFormat df = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
                    startDate = df.parse(searchCompanyForm.getParentStartDate());
                    searchCompanyForm.setComParentSDate(startDate);
                    searchCompanyForm.setParentSDate(startDate);
                }
                if (obj[NumericConstants.FORTY_TWO] != null) {
                    Date EndDate = (Date) obj[NumericConstants.FORTY_TWO];
                    searchCompanyForm.setComParentEDate(EndDate);
                    searchCompanyForm.setParentEDate(EndDate);
                    searchCompanyForm.setParentEndDate(CommonUtils.convertDateToString(EndDate));
                    DateFormat df = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
                    EndDate = df.parse(searchCompanyForm.getParentEndDate());
                    searchCompanyForm.setComParentEDate(EndDate);
                    searchCompanyForm.setParentEDate(EndDate);
                }
                if (obj[NumericConstants.FORTY_FOUR] != null && !ConstantsUtils.NULL.equalsIgnoreCase(String.valueOf(obj[NumericConstants.FORTY_FOUR]))&& StringUtils.isNotBlank(obj[NumericConstants.FORTY_FOUR].toString()) ) {
                    searchCompanyForm.setPriorParentCompanyNo(obj[NumericConstants.FORTY_FOUR].toString());
                }
                if (obj[NumericConstants.FORTY_FIVE] != null) {
                    Date priorSndDate = (Date) obj[NumericConstants.FORTY_FIVE];
                    searchCompanyForm.setPriorParentSDate(priorSndDate);
                    searchCompanyForm.setPriorParentStartDate(priorSndDate);
                }
                searchItemList.add(searchCompanyForm);
            }
        }
        LOGGER.debug("return count -" + searchItemList.size());

        return searchItemList;
    }

    /**
     * Gets the record based on system ID.
     *
     * @param companySystemId the company system id
     * @return the trade class table
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public List<CompanyMasterDTO> getTradeClassTable(final int companySystemId) throws SystemException, PortalException {
        LOGGER.debug("Entering getTradeClassTable p1:" + companySystemId);
        final List<CompanyMasterDTO> companyTrade = getCustomisedTradeClassList(dao.getCompanyTradeClassBySystemId(companySystemId));
        LOGGER.debug("return Trade Class count -" + ((companyTrade == null) ? companyTrade : companyTrade.size()));
        return companyTrade;

    }

    public static String getCompanyNo(int sysId) {
        CompanyMaster master = null;
        try {
            master = CompanyMasterLocalServiceUtil.getCompanyMaster(sysId);
        } catch (PortalException ex) {
            java.util.logging.Logger.getLogger(CompanySearchLogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(CompanySearchLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return master.getCompanyNo();
    }

    /**
     * Gets the customized list of CompanyMasterDTO .
     *
     * @param companyTradeClass the company trade class
     * @return the list of CompanyMasterDTO
     * @throws Exception the exception
     */
    public List<CompanyMasterDTO> getCustomisedTradeClassList(final List companyTradeClass) {
        LOGGER.debug("Entering getCustomisedTradeClassList p1: " + ((companyTradeClass == null) ? companyTradeClass : companyTradeClass.size()));
        final List<CompanyMasterDTO> companyTrade = new ArrayList<CompanyMasterDTO>();
        try {
        Map<Integer, String> map = getCodeDescription(UIUtils.TRADE_CLASS);
            CompanyMasterDTO dto;
            if(companyTradeClass !=null && !companyTradeClass.isEmpty() ){
            for (int i = 0; i < companyTradeClass.size(); i++) {
                dto = new CompanyMasterDTO();
                final Object[] obj = (Object[]) companyTradeClass.get(i);
                if (!ConstantsUtils.INBOUND_STATUS_D.equalsIgnoreCase(String.valueOf(obj[NumericConstants.NINE]))) {
                    if (obj[0] == null) {
                        dto.setTradeClass1(helperListUtil.getIdHelperDTOMap().get(0));
                    } else {
                        dto.setTradeClass1(helperListUtil.getIdHelperDTOMap().get((Integer) obj[0]));
                    }                 
                    dto.setTradeClass(map.get(Integer.parseInt(obj[0].toString())));
                    dto.setOldTC(String.valueOf(obj[0]));
                    try {

                        dto.setTradeClassSDate(dateToDateConvert((Date) obj[1]));
                        dto.setOldTCDate(dateToDateConvert((Date) obj[1]));
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }

                    if (obj[NumericConstants.TWO] != null) {
                        dto.setTradeClassEDate(dateToDateConvert((Date) obj[NumericConstants.TWO]));

                    }

                    dto.setCompanySystemId(String.valueOf(obj[NumericConstants.THREE]));
                    if (obj[NumericConstants.FOUR] != null) {
                        String tradeClass = !(String.valueOf(obj[NumericConstants.FOUR])).equals("0") ? String.valueOf(obj[NumericConstants.FOUR]) : StringUtils.EMPTY;
                        dto.setPriorTradeClass(tradeClass);
                    }
                    if (obj[NumericConstants.FIVE] != null) {
                        dto.setCreatedBy(String.valueOf(obj[NumericConstants.FIVE]));
                        try {
                            User createdUser = (dto.getCreatedBy() == "0") ? null : ((User) securityDto.getUserByUserId(Integer.parseInt(dto.getCreatedBy())));
                            dto.setCreatedBy(createdUser == null ? StringUtils.EMPTY : createdUser.getFullName());
                            dto.setCreatedUserId(Integer.valueOf(String.valueOf(obj[NumericConstants.FIVE])));
                            
                        } catch (Exception e) {
                            LOGGER.error(e);
                        }
                    }
                    if (obj[NumericConstants.SIX] != null) {
                        dto.setCreatedDate(dateToDateConvert((Date) (obj[NumericConstants.SIX])));
                    }
                    if (obj[NumericConstants.SEVEN] != null) {
                        dto.setTradeClassSysId(Integer.valueOf(obj[NumericConstants.SEVEN].toString()));
                    }
                    if (obj[NumericConstants.EIGHT] != null) {
                        dto.setPriorTradeClassStartDate((Date) (obj[NumericConstants.EIGHT]));
                    }
                    if (obj[NumericConstants.TEN] != null) {
                        dto.setModifiedBy(String.valueOf(obj[NumericConstants.TEN]));
                        try {
                            if (Integer.parseInt(dto.getModifiedBy()) != 0) {
                                User ModifiedUser = (Integer.parseInt(dto.getModifiedBy()) == 0) ? null : ((User) securityDto.getUserByUserId(Integer.parseInt(dto.getModifiedBy())));
                                dto.setModifiedBy(ModifiedUser == null ? StringUtils.EMPTY : ModifiedUser.getFullName());
                            } else {
                                dto.setModifiedBy(StringUtils.EMPTY);
                            }
                        } catch (Exception e) {
                            LOGGER.error(e);
                        }
                    }
                    if (obj[NumericConstants.ELEVEN] != null) {
                        dto.setModifiedDate(dateToDateConvert((Date) (obj[NumericConstants.ELEVEN])));
                    }
                    companyTrade.add(dto);
                }
            }
        }
        } catch (Exception e) {
            LOGGER.error(e);
        }

        LOGGER.debug("return Customized Trade Class count -" + companyTrade.size());

        return companyTrade;
    }

    /**
     * Gets the company master by id.
     *
     * @param systemId the system id
     * @return the company master by id
     * @throws ParseException the parse exception
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    
    public CompanyMasterDTO getCompanyMasterById(final int systemId) throws ParseException, SystemException, PortalException {
        LOGGER.debug("Entering getCompanyMasterById p1:" + systemId);
        DecimalFormat formatLives = new DecimalFormat("###,###,###");
        final CompanyMasterDTO companyMasterDTO = new CompanyMasterDTO();
        final CompanyMaster companyMaster = dao.getCompanyMasterBySystemId(systemId);
        companyMasterDTO.setCompanySystemId(String.valueOf(companyMaster.getCompanyMasterSid()));
        companyMasterDTO.setCompanyId(companyMaster.getCompanyId());
        companyMasterDTO.setCompanyNo(companyMaster.getCompanyNo());
        companyMasterDTO.setCompanyName(companyMaster.getCompanyName());

        companyMasterDTO.setCompanyStartDate(convertDateToDate(companyMaster.getCompanyStartDate()));

        if (companyMaster.getCompanyEndDate() != null) {

            companyMasterDTO.setCompanyEndDate(convertDateToDate(companyMaster.getCompanyEndDate()));

        }
        companyMasterDTO.setCompanyStatus(helperListUtil.getIdHelperDTOMap().get(companyMaster.getCompanyStatus()));
        companyMasterDTO.setCompanyType(helperListUtil.getIdHelperDTOMap().get(companyMaster.getCompanyType()));
        companyMasterDTO.setCompanyGroup(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(StringUtils.isEmpty(companyMaster.getCompanyGroup()) ? "0" : companyMaster.getCompanyGroup())));
        companyMasterDTO.setCompanyCategory(helperListUtil.getIdHelperDTOMap().get(companyMaster.getCompanyCategory()));
        companyMasterDTO.setAddress1(companyMaster.getAddress1());
        companyMasterDTO.setAddress2(companyMaster.getAddress2());
        companyMasterDTO.setCity(companyMaster.getCity());
        companyMasterDTO.setState(helperListUtil.getIdHelperDTOMap().get(companyMaster.getState()));
        companyMasterDTO.setCountry(helperListUtil.getIdHelperDTOMap().get(companyMaster.getCountry()));
        companyMasterDTO.setZipCode(companyMaster.getZipCode());
        companyMasterDTO.setSource(companyMaster.getSource());
        companyMasterDTO.setSystemId(String.valueOf(systemId));
        String orgKey = companyMaster.getOrgKey();
        companyMasterDTO.setOrganizationKey(helperListUtil.getIdHelperDTOMap().get(StringUtils.isEmpty(orgKey) ? 0 : Integer.valueOf(companyMaster.getOrgKey())));
        companyMasterDTO.setRegionCode(companyMaster.getRegionCode());
        if(companyMaster.getLives()!=0 && StringUtils.isNotBlank(String.valueOf(companyMaster.getLives()))){
        companyMasterDTO.setLives(formatLives.format(Double.valueOf(String.valueOf(companyMaster.getLives()))));
        }
        companyMasterDTO.setFinancialSystem(companyMaster.getFinancialSystem());

        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Udcs.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.MASTER_SID, systemId));
        dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.MASTER_TYPE, ConstantsUtils.COMPANY_MASTER));
        List<Udcs> list = UdcsLocalServiceUtil.dynamicQuery(dynamicQuery);
        int udcId1 = 0;
        int udcId2 = 0;
        int udcId3 = 0;
        int udcId4 = 0;
        int udcId5 = 0;
        int udcId6 = 0;
        for (int j = 0; j < list.size(); j++) {
            Udcs obj = list.get(j);
            udcId1 = obj.getUdc1();
            udcId2 = obj.getUdc2();
            udcId3 = obj.getUdc3();
            udcId4 = obj.getUdc4();
            udcId5 = obj.getUdc5();
            udcId6 = obj.getUdc6();

            companyMasterDTO.setUdc1(helperListUtil.getIdHelperDTOMap().get(udcId1));
            companyMasterDTO.setUdc2(helperListUtil.getIdHelperDTOMap().get(udcId2));
            companyMasterDTO.setUdc3(helperListUtil.getIdHelperDTOMap().get(udcId3));
            companyMasterDTO.setUdc4(helperListUtil.getIdHelperDTOMap().get(udcId4));
            companyMasterDTO.setUdc5(helperListUtil.getIdHelperDTOMap().get(udcId5));
            companyMasterDTO.setUdc6(helperListUtil.getIdHelperDTOMap().get(udcId6));
        }
        final String user = VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString();
        LOGGER.debug("user id" + user);
        companyMasterDTO.setCreatedDate(companyMaster.getCreatedDate());
        companyMasterDTO.setModifiedDate(companyMaster.getModifiedDate());

        Map<Integer,String> userMap = StplSecurity.userMap;
        
        companyMasterDTO.setCreatedBy(userMap.get(companyMaster.getCreatedBy()) ==null ? StringUtils.EMPTY : userMap.get(companyMaster.getCreatedBy()));
        companyMasterDTO.setModifiedBy(userMap.get(companyMaster.getModifiedBy()) ==null ? StringUtils.EMPTY : userMap.get(companyMaster.getModifiedBy()));
       
        companyMasterDTO.setInternalNotes(companyMaster.getInternalNotes());

        final List<CompanyIdentifier> crtList = dao.getCompanyIdentifierByCompanySystemId(systemId);
        final List<CompanyCrtIdentifierDTO> companyCrtIdentifierList = new ArrayList<>();
        for (int i = 0; i < crtList.size(); i++) {
            final CompanyCrtIdentifierDTO identifierDTO = new CompanyCrtIdentifierDTO();
            final CompanyIdentifier identifier = crtList.get(i);
            if (!ConstantsUtils.INBOUND_STATUS_D.equals(identifier.getInboundStatus())) {
                identifierDTO.setCompanySystemId(identifier.getCompanyMasterSid());
                final CompanyQualifier qualifier = dao.getCompanyCrtQualifierByQualifierId(identifier.getCompanyQualifierSid());
                identifierDTO.setCompanyCrtQualifierSid(qualifier.getCompanyQualifierSid());
                identifierDTO.setCompanyCrtQualifierName(qualifier.getCompanyQualifierName());
                identifierDTO.setEntityCode(identifier.getEntityCode());
                identifierDTO.setIdentifierStatus(helperListUtil.getIdHelperDTOMap().get(identifier.getIdentifierStatus()));
                identifierDTO.setIdentifierStatusValue(getDescription(Integer.parseInt(String.valueOf(identifier.getIdentifierStatus()))));
                identifierDTO.setCompanyIdentifier(String.valueOf(identifier.getCompanyIdentifierValue()));
                identifierDTO.setCompanyCrtQualifierId(identifier.getCompanyQualifierSid());
                if (identifier.getStartDate() != null) {
                    identifierDTO.setStartDate(dateToDateConvert(identifier.getStartDate()));
                }
                if (identifier.getEndDate() != null) {
                    identifierDTO.setEndDate(dateToDateConvert(identifier.getEndDate()));
                }
                if (identifier.getStartDate() != null) {
                    identifierDTO.setViewStartDate(CommonUtils.convertDateToString(identifier.getStartDate()));
                }
                if (identifier.getEndDate() != null) {
                    identifierDTO.setViewEndDate(CommonUtils.convertDateToString(identifier.getEndDate()));
                }
                identifierDTO.setCrtIdentifierSystemId(identifier.getCompanyIdentifierSid());
                identifierDTO.setCreatedDate(dateToDateConvert(identifier.getCreatedDate()));
                identifierDTO.setModifiedDate(dateToDateConvert(identifier.getModifiedDate()));
                if(identifier.getCreatedDate()!=null){
                identifierDTO.setViewCreatedDate(CommonUtils.convertDateToString(identifier.getCreatedDate()));
            }else{
                    identifierDTO.setViewCreatedDate(StringUtils.EMPTY);
                    }
                if(identifier.getModifiedDate()!=null){
                identifierDTO.setViewModifiedDate(CommonUtils.convertDateToString(identifier.getModifiedDate()));
                }else{
                    identifierDTO.setViewModifiedDate(StringUtils.EMPTY);
                }
               
                identifierDTO.setCreatedBy((userMap.get(identifier.getCreatedBy()) ==null) ? StringUtils.EMPTY : userMap.get(identifier.getCreatedBy()));
               
                identifierDTO.setModifiedBy((userMap.get(identifier.getModifiedBy()) ==null) ? StringUtils.EMPTY : userMap.get(identifier.getModifiedBy()));

                companyCrtIdentifierList.add(identifierDTO);
            }
        }
        companyMasterDTO.setCompanyIdentifierList(companyCrtIdentifierList);
        LOGGER.debug("return CompanyMaster data");
        return companyMasterDTO;
    }

    /**
     * Convert date to string.
     *
     * @param date the date
     * @return the string
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public String convertStringDateToString(final String date) throws SystemException, PortalException, ParseException {
        LOGGER.debug("Entering ConvertStringDateToString p1:" + date);
        final DateFormat formatter = new SimpleDateFormat(ConstantsUtils.SIMPLE_DATE_FORMAT);

        Date sDate;
        sDate = (Date) formatter.parse(date);

        final Calendar cal = Calendar.getInstance();
        cal.setTime(sDate);
        final String formatedDate = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + "/" + cal.get(Calendar.YEAR);
        LOGGER.debug("return formatedDate -" + formatedDate);
        return formatedDate;
    }

    /**
     * Format the date to yyyy-MM-dd hh:mm:ss.S.
     *
     * @param date the date
     * @return the date
     * @throws ParseException the parse exception
     */
    public Date convertDateToDate(final Date date) throws ParseException {

        LOGGER.debug("convertDateToDate p1:" + date);
        Date formatedDate = null;
        try {
            final DateFormat inputFormat = new SimpleDateFormat(ConstantsUtils.LONGDATEFORMAT);
            formatedDate = inputFormat.parse(inputFormat.format(date));
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return formatedDate;

    }

    /**
     * Format the date to MM/dd/yyyy.
     *
     * @param date the date
     * @return the date
     * @throws ParseException the parse exception
     */
    public Date dateToDateConvert(final Date date) throws ParseException {

        LOGGER.debug("dateToDateConvert p1:" + date);
        Date formatedDate = null;
        try {
            final DateFormat inputFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
            formatedDate = inputFormat.parse(inputFormat.format(date));
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return formatedDate;

    }

    /**
     * format the date to E MMM dd HH:mm:ss Z yyyy.
     *
     * @param date the date
     * @return the string
     * @throws Exception the exception
     */
    public String convertDateToString(final String date) throws ParseException {
        LOGGER.debug("Entering ConvertDateToString p1:" + date);
        final DateFormat formatter = new SimpleDateFormat(ConstantsUtils.SIMPLE_DATE_FORMAT);

        Date sDate;
        sDate = (Date) formatter.parse(date);
        final Calendar cal = Calendar.getInstance();
        cal.setTime(sDate);
        return cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
    }

    /**
     * Convert String to date as E MMM dd HH:mm:ss Z yyyy format.
     *
     * @param dateString the date string
     * @return the date
     * @throws Exception the exception
     */
    public Date stringToDate(final String dateString) throws ParseException  {
        LOGGER.debug("Entering StringToDate p1:" + dateString);
        final DateFormat inputFormat = new SimpleDateFormat(ConstantsUtils.SIMPLE_DATE_FORMAT);
        Date date;
        date = inputFormat.parse(dateString);
        LOGGER.debug("return date : " + date);
        return date;

    }

    /**
     * convert string to date as MM/dd/yyyy format.
     *
     * @param dateString the date string
     * @return the date
     * @throws Exception the exception
     */
    public Date stringToDateForIden(final String dateString) throws ParseException {
        LOGGER.debug("Entering StringToDateForIden p1:" + dateString);
        final DateFormat inputFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
        Date date;
        date = inputFormat.parse(dateString);
        LOGGER.debug("return date : " + date);
        return date;
    }

    /**
     * To Save company master.
     *
     * @param companyMasterForm the company master form
     * @param identifierList the identifier list
     * @param companyTradeList the company trade list
     * @param parentCompanyList the parent company list
     * @return the string
     */
    public String saveCompanyMaster(final ErrorfulFieldGroup companyMasterForm, final List<CompanyCrtIdentifierDTO> identifierList, final List<CompanyMasterDTO> companyTradeList,
            final List<CompanyMasterDTO> parentCompanyList, final List<NotesDTO> availableUploadedInformation, final String addedNotes, final List<NotesDTO> removeDetailsList) {
        LOGGER.debug("SaveCompanyMaster p1: ErrorfulFieldGroup p2: List<CompanyCrtIdentifierDTO> size " + identifierList.size() + " p3:List<CompanyMasterDTO> size " + companyTradeList.size());
        CompanyMaster company ;
        List<CompanyIdentifier> idenList = null;
        boolean flag = false;
        try {
            String systemId = companyMasterForm.getField(ConstantsUtils.COMPANY_SYSTEM_ID).getValue() == null || companyMasterForm.getField(ConstantsUtils.COMPANY_SYSTEM_ID).getValue().equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : String.valueOf(companyMasterForm.getField(ConstantsUtils.COMPANY_SYSTEM_ID)
                    .getValue().toString());
            LOGGER.debug("company----------------" + systemId);
            systemId = systemId.replace(ConstantsUtils.COMMA, StringUtils.EMPTY);
            Udcs udc = UdcsLocalServiceUtil.createUdcs(0);
            //Added to check the softdelete 
            String companyId = String.valueOf(companyMasterForm.getField(ConstantsUtils.COMPANY_ID).getValue()).trim();
            if (ConstantsUtils.NULL.equals(systemId) || StringUtils.EMPTY.equals(systemId) || ConstantsUtils.ZERO.equals(systemId)) {
                final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
                companyDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_ID, companyId));
                companyDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
                final List<CompanyMaster> companymasterList = dao.getCompanyMasterList(companyDynamicQuery);
                if (!companymasterList.isEmpty()) {
                    for (int i = 0; i < companymasterList.size(); i++) {
                        systemId = String.valueOf(companymasterList.get(i).getCompanyMasterSid());
                    }
                    flag = true;
                }
            }
            if (systemId.equals(ConstantsUtils.NULL) || systemId.equals(ConstantsUtils.ZERO) || systemId.equals(StringUtils.EMPTY)) {
                company = CompanyMasterLocalServiceUtil.createCompanyMaster(0);
                company.setInboundStatus(ConstantsUtils.INBOUND_STATUS_A);
                company.setSource(ConstantsUtils.GTN);
            } else {
                company = dao.getCompanyMasterBySystemId(Integer.parseInt(systemId));
                company.setInboundStatus(ConstantsUtils.INBOUND_STATUS_C);
                company.setSource(ConstantsUtils.GTN);
                if (flag) {
                    company.setInboundStatus(ConstantsUtils.INBOUND_STATUS_A);
                    sessionDTO.setSystemId(Integer.valueOf(systemId));
                    company.setSource("GTN");
                }
            }
            LOGGER.debug("cfp----------------" + company.getCompanyMasterSid());
            CompanyMaster result = null;
            company.setCompanyId(companyMasterForm.getField(ConstantsUtils.COMPANY_ID).getValue().toString().trim());
            company.setCompanyNo(companyMasterForm.getField(ConstantsUtils.COMPANY_NO).getValue().toString().trim());
            company.setCompanyName(companyMasterForm.getField(ConstantsUtils.COMPANY_NAME).getValue().toString().trim());
            company.setCompanyType(((com.stpl.ifs.util.HelperDTO) (companyMasterForm.getField(ConstantsUtils.COMPANY_TYPE)).getValue()).getId());
            company.setCompanyStatus(((com.stpl.ifs.util.HelperDTO) (companyMasterForm.getField(ConstantsUtils.COMPANY_STATUS)).getValue()).getId());
            company.setCompanyCategory((companyMasterForm.getField(ConstantsUtils.COMPANY_CATEGORY).getValue() == null || StringUtils.isBlank(companyMasterForm.getField(ConstantsUtils.COMPANY_CATEGORY).getValue().toString())) ? 0 : ((com.stpl.ifs.util.HelperDTO) companyMasterForm.getField(ConstantsUtils.COMPANY_CATEGORY).getValue()).getId());
            company.setCompanyGroup(companyMasterForm.getField(ConstantsUtils.COMPANY_GROUP).getValue() == null ? StringUtils.EMPTY : String.valueOf(((com.stpl.ifs.util.HelperDTO) (companyMasterForm.getField(ConstantsUtils.COMPANY_GROUP).getValue())).getId()));
           

            // Added by Beena
            company.setCompanyStartDate(stringToDate(String.valueOf(companyMasterForm.getField(ConstantsUtils.COMPANY_START_DATE).getValue())));
            if (companyMasterForm.getField(ConstantsUtils.COMPANY_END_DATE).getValue() != null && !StringUtils.EMPTY.equals(companyMasterForm.getField(ConstantsUtils.COMPANY_END_DATE).getValue())) {
                company.setCompanyEndDate(stringToDate(String.valueOf(companyMasterForm.getField(ConstantsUtils.COMPANY_END_DATE).getValue())));
            } else {
                company.setCompanyEndDate(null);
            }

            company.setFinancialSystem(companyMasterForm.getField(ConstantsUtils.FINANCIAL_SYSTEM).getValue() == null ? StringUtils.EMPTY : companyMasterForm.getField(ConstantsUtils.FINANCIAL_SYSTEM).getValue()
                    .toString().trim());
            company.setSource(companyMasterForm.getField(ConstantsUtils.SOURCE).getValue() == null ? StringUtils.EMPTY : companyMasterForm.getField(ConstantsUtils.SOURCE).getValue()
                    .toString().trim());
            company.setOrgKey(companyMasterForm.getField(ConstantsUtils.ORGANIZATION_KEY).getValue() == null ? StringUtils.EMPTY : String.valueOf(((com.stpl.ifs.util.HelperDTO) (companyMasterForm.getField(ConstantsUtils.ORGANIZATION_KEY).getValue()
                    )).getId()));
            if (!StringUtils.EMPTY.equals(companyMasterForm.getField(ConstantsUtils.LIVES).getValue())) {
                String value=companyMasterForm.getField(ConstantsUtils.LIVES).getValue().toString().trim();
                value=value.replace(",", StringUtils.EMPTY);
                company.setLives(Integer.parseInt(value));
            } else {
                company.setLives(0);
            }
            company.setRegionCode(companyMasterForm.getField(ConstantsUtils.REGION_CODE).getValue().toString().trim());

            company.setAddress1(companyMasterForm.getField(ConstantsUtils.ADDRESS1).getValue().toString().trim());
            company.setAddress2(companyMasterForm.getField(ConstantsUtils.ADDRESS2).getValue().toString().trim());
            company.setCity(companyMasterForm.getField(ConstantsUtils.CITY).getValue().toString().trim());
            if (companyMasterForm.getField(ConstantsUtils.STATE).getValue() != null) {
                company.setState(((com.stpl.ifs.util.HelperDTO) (companyMasterForm.getField(ConstantsUtils.STATE).getValue())).getId());
            } else {
                company.setState(0);
            }
            company.setZipCode(companyMasterForm.getField(ConstantsUtils.ZIPCODE).getValue().toString().trim());
            if (companyMasterForm.getField(ConstantsUtils.COUNTRY).getValue() != null) {
                company.setCountry(((com.stpl.ifs.util.HelperDTO) (companyMasterForm.getField(ConstantsUtils.COUNTRY).getValue())).getId());
            } else {
                company.setCountry(0);
            }

            company.setInternalNotes(addedNotes);
            company.setRecordLockStatus(false);//unlocked
            final DynamicQuery companyNoDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
            companyNoDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_NO, company.getCompanyNo()));
            final List<CompanyMaster> companymasterNoList = dao.getCompanyMasterList(companyNoDynamicQuery);

            if (ConstantsUtils.NULL.equals(systemId) || StringUtils.EMPTY.equals(systemId) || ConstantsUtils.ZERO.equals(systemId)) {

                final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);

                companyDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_ID, company.getCompanyId()));

                final List<CompanyMaster> companymasterList = dao.getCompanyMasterList(companyDynamicQuery);
                try {
                    if (companymasterList.size() < 1 && companymasterNoList.size() < 1) {
                        final Date createdDate = new Date();
                        company.setCreatedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
                        company.setCreatedDate(createdDate);
                        company.setModifiedDate(createdDate);

                        result = dao.saveCompanyMaster(company);
                        sessionDTO.setSystemId(result.getCompanyMasterSid());
                        if (result.getCompanyStatus() != 0) {
                            UDCIncrementCheck.increment(String.valueOf(company.getCompanyStatus()), UIUtils.STATUS);
                        }
                        if (result.getCompanyMasterSid() != 0) {

                            udc.setMasterSid(result.getCompanyMasterSid());
                            udc.setMasterType(ConstantsUtils.COMPANY_MASTER);
                    String udc1 = companyMasterForm.getField(ConstantsUtils.UDC1).getValue() == null ? ConstantUtil.ZERO :String.valueOf(((com.stpl.ifs.util.HelperDTO) (companyMasterForm.getField(ConstantsUtils.UDC1).getValue())).getId());
                    String udc2 = companyMasterForm.getField(ConstantsUtils.UDC2).getValue() == null ? ConstantUtil.ZERO :String.valueOf(((com.stpl.ifs.util.HelperDTO) (companyMasterForm.getField(ConstantsUtils.UDC2).getValue())).getId());
                    String udc3 = companyMasterForm.getField(ConstantsUtils.UDC3).getValue() == null ? ConstantUtil.ZERO :String.valueOf(((com.stpl.ifs.util.HelperDTO) (companyMasterForm.getField(ConstantsUtils.UDC3).getValue())).getId());
                    String udc4 = companyMasterForm.getField(ConstantsUtils.UDC4).getValue() == null ? ConstantUtil.ZERO :String.valueOf(((com.stpl.ifs.util.HelperDTO) (companyMasterForm.getField(ConstantsUtils.UDC4).getValue())).getId());
                    String udc5 = companyMasterForm.getField(ConstantsUtils.UDC5).getValue() == null ? ConstantUtil.ZERO :String.valueOf(((com.stpl.ifs.util.HelperDTO) (companyMasterForm.getField(ConstantsUtils.UDC5).getValue())).getId());
                    String udc6 = companyMasterForm.getField(ConstantsUtils.UDC6).getValue() == null ? ConstantUtil.ZERO :String.valueOf(((com.stpl.ifs.util.HelperDTO) (companyMasterForm.getField(ConstantsUtils.UDC6).getValue())).getId());
                            if (!udc1.equals(ConstantsUtils.NULL)) {
                                udc.setUdc1(Integer.valueOf(udc1));
                            }
                            if (!udc2.equals(ConstantsUtils.NULL)) {
                                udc.setUdc2(Integer.valueOf(udc2));
                            }
                            if (!udc3.equals(ConstantsUtils.NULL)) {
                                udc.setUdc3(Integer.valueOf(udc3));
                            }
                            if (!udc4.equals(ConstantsUtils.NULL)) {
                                udc.setUdc4(Integer.valueOf(udc4));
                            }
                            if (!udc5.equals(ConstantsUtils.NULL)) {
                                udc.setUdc5(Integer.valueOf(udc5));
                            }
                            if (!udc6.equals(ConstantsUtils.NULL)) {
                                udc.setUdc6(Integer.valueOf(udc6));
                            }
                            dao.saveUdcs(udc);
                        }
                        if (result.getCompanyType() != 0) {
                            UDCIncrementCheck.increment(String.valueOf(company.getCompanyType()), UIUtils.COMPANY_TYPE);
                        }
                        if (result.getCompanyGroup() != null) {
                            UDCIncrementCheck.increment(company.getCompanyGroup(), UIUtils.COMPANY_GROUP);
                        }
                        if (result.getCompanyCategory() != 0) {
                            UDCIncrementCheck.increment(String.valueOf(company.getCompanyCategory()), UIUtils.COMPANY_CATEGORY);
                        }
                        if (result.getState() != 0) {
                            UDCIncrementCheck.increment(String.valueOf(company.getState()), UIUtils.STATE);
                        }
                        if (result.getCountry() != 0) {
                            UDCIncrementCheck.increment(String.valueOf(company.getCountry()), UIUtils.COUNTRY);
                        }
                        if (result.getOrgKey() != null) {
                            UDCIncrementCheck.increment(company.getOrgKey(), UIUtils.ORGANIZATION_KEY);
                        }


                        if (udc.getUdc1() != 0) {
                            UDCIncrementCheck.increment(String.valueOf(udc.getUdc1()), UIUtils.UDC1);
                        }
                        if (udc.getUdc2() != 0) {
                            UDCIncrementCheck.increment(String.valueOf(udc.getUdc2()), UIUtils.UDC2);
                        }
                        if (udc.getUdc3() != 0) {
                            UDCIncrementCheck.increment(String.valueOf(udc.getUdc3()), UIUtils.UDC3);
                        }
                        if (udc.getUdc4() != 0) {
                            UDCIncrementCheck.increment(String.valueOf(udc.getUdc4()), UIUtils.UDC4);
                        }
                        if (udc.getUdc5() != 0) {
                            UDCIncrementCheck.increment(String.valueOf(udc.getUdc5()), UIUtils.UDC5);
                        }
                        if (udc.getUdc6() != 0) {
                            UDCIncrementCheck.increment(String.valueOf(udc.getUdc6()), UIUtils.UDC6);
                        }
                        saveIdentifiersList(identifierList, result);
                        saveCompanyTradeClass(companyTradeList, result);
                        saveParentCompanyDetails(parentCompanyList, result);
                        notesLogic.saveUploadedInformation(availableUploadedInformation, ConstantsUtils.COMPANY_MASTER, result.getCompanyMasterSid());

                    } else if (companymasterNoList == null || companymasterNoList.isEmpty()) {
                        LOGGER.debug("return duplicate");
                        return ConstantsUtils.DUPLICATE;
                    } else {
                        LOGGER.debug("return duplicateNo");
                        return "duplicateNo";
                    }
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            } else {
                final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);

                companyDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_ID, company.getCompanyId()));

                final List<CompanyMaster> companymasterList = dao.getCompanyMasterList(companyDynamicQuery);
                int count = 0;
                for (int i = 0; i < companymasterList.size(); i++) {
                    if (Integer.parseInt(systemId) == companymasterList.get(i).getCompanyMasterSid()) {
                    } else {
                        count++;
                    }
                }
                int countNo = 0;
                for (int i = 0; i < companymasterNoList.size(); i++) {
                    if (Integer.parseInt(systemId) == companymasterNoList.get(i).getCompanyMasterSid()) {
                    } else {
                        countNo++;
                    }
                }
                if (count < 1 && countNo < 1) {

                    company.setCompanyMasterSid(Integer.parseInt(systemId));
                    company.setModifiedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
                    company.setModifiedDate(new Date());
                    final CompanyMaster checkUDC = dao.getCompanyMasterBySystemId(company.getCompanyMasterSid());
                    if (checkUDC.getCompanyStatus() != 0) {
                        UDCIncrementCheck.decrement(String.valueOf(checkUDC.getCompanyStatus()), UIUtils.STATUS);
                    }

                    if (checkUDC.getCompanyType() != 0) {
                        UDCIncrementCheck.decrement(String.valueOf(checkUDC.getCompanyType()), UIUtils.COMPANY_TYPE);
                    }
                    if (checkUDC.getCompanyGroup() != null) {
                        UDCIncrementCheck.decrement(checkUDC.getCompanyGroup(), UIUtils.COMPANY_GROUP);
                    }
                    if (checkUDC.getCompanyCategory() != 0) {
                        UDCIncrementCheck.decrement(String.valueOf(checkUDC.getCompanyCategory()), UIUtils.COMPANY_CATEGORY);
                    }
                    if (checkUDC.getState() != 0) {
                        UDCIncrementCheck.decrement(String.valueOf(checkUDC.getState()), UIUtils.STATE);
                    }
                    if (checkUDC.getCountry() != 0) {
                        UDCIncrementCheck.decrement(String.valueOf(checkUDC.getCountry()), UIUtils.COUNTRY);
                    }
                    if (checkUDC.getOrgKey() != null) {
                        UDCIncrementCheck.decrement(checkUDC.getOrgKey(), UIUtils.ORGANIZATION_KEY);
                    }
                    if (udc.getUdc1() != 0) {
                        UDCIncrementCheck.decrement(String.valueOf(udc.getUdc1()), UIUtils.UDC1);
                    }
                    if (udc.getUdc2() != 0) {
                        UDCIncrementCheck.decrement(String.valueOf(udc.getUdc2()), UIUtils.UDC2);
                    }
                    if (udc.getUdc3() != 0) {
                        UDCIncrementCheck.decrement(String.valueOf(udc.getUdc3()), UIUtils.UDC3);
                    }
                    if (udc.getUdc4() != 0) {
                        UDCIncrementCheck.decrement(String.valueOf(udc.getUdc4()), UIUtils.UDC4);
                    }
                    if (udc.getUdc5() != 0) {
                        UDCIncrementCheck.decrement(String.valueOf(udc.getUdc5()), UIUtils.UDC5);
                    }
                    if (udc.getUdc6() != 0) {
                        UDCIncrementCheck.decrement(String.valueOf(udc.getUdc6()), UIUtils.UDC6);
                    }
                    if (flag) {
                        final Date createdDate = new Date();
                        company.setCreatedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
                        company.setCreatedDate(createdDate);
                        company.setModifiedDate(createdDate);
                        company.setModifiedDate(createdDate);
                    }
                    result = dao.updateCompanyMaster(company);


                    DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Udcs.class);
                    dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.MASTER_SID, result.getCompanyMasterSid()));
                    dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.MASTER_TYPE, ConstantsUtils.COMPANY_MASTER));
                    List<Udcs> list = UdcsLocalServiceUtil.dynamicQuery(dynamicQuery);
                    String udc1 = companyMasterForm.getField(ConstantsUtils.UDC1).getValue() == null ? ConstantUtil.ZERO :String.valueOf(((com.stpl.ifs.util.HelperDTO) (companyMasterForm.getField(ConstantsUtils.UDC1).getValue())).getId());
                    String udc2 = companyMasterForm.getField(ConstantsUtils.UDC2).getValue() == null ? ConstantUtil.ZERO :String.valueOf(((com.stpl.ifs.util.HelperDTO) (companyMasterForm.getField(ConstantsUtils.UDC2).getValue())).getId());
                    String udc3 = companyMasterForm.getField(ConstantsUtils.UDC3).getValue() == null ? ConstantUtil.ZERO :String.valueOf(((com.stpl.ifs.util.HelperDTO) (companyMasterForm.getField(ConstantsUtils.UDC3).getValue())).getId());
                    String udc4 = companyMasterForm.getField(ConstantsUtils.UDC4).getValue() == null ? ConstantUtil.ZERO :String.valueOf(((com.stpl.ifs.util.HelperDTO) (companyMasterForm.getField(ConstantsUtils.UDC4).getValue())).getId());
                    String udc5 = companyMasterForm.getField(ConstantsUtils.UDC5).getValue() == null ? ConstantUtil.ZERO :String.valueOf(((com.stpl.ifs.util.HelperDTO) (companyMasterForm.getField(ConstantsUtils.UDC5).getValue())).getId());
                    String udc6 = companyMasterForm.getField(ConstantsUtils.UDC6).getValue() == null ? ConstantUtil.ZERO :String.valueOf(((com.stpl.ifs.util.HelperDTO) (companyMasterForm.getField(ConstantsUtils.UDC6).getValue())).getId());
                    if (!list.isEmpty()) { // Added for GAL-7698
                        for (int j = 0; j < list.size(); j++) {
                            Udcs obj1 = list.get(j);
                            obj1.setUdc1(Integer.valueOf(udc1));
                            obj1.setUdc2(Integer.valueOf(udc2));
                            obj1.setUdc3(Integer.valueOf(udc3));
                            obj1.setUdc4(Integer.valueOf(udc4));
                            obj1.setUdc5(Integer.valueOf(udc5));
                            obj1.setUdc6(Integer.valueOf(udc6));
                            dao.updateUdcs(obj1);
                        }
                    } else {// Added for GAL-7698
                        udc.setMasterSid(result.getCompanyMasterSid());
                        udc.setMasterType(ConstantsUtils.COMPANY_MASTER);
                        if (!udc1.equals(ConstantsUtils.NULL)) {
                            udc.setUdc1(Integer.valueOf(udc1));
                        }
                        if (!udc2.equals(ConstantsUtils.NULL)) {
                            udc.setUdc2(Integer.valueOf(udc2));
                        }
                        if (!udc3.equals(ConstantsUtils.NULL)) {
                            udc.setUdc3(Integer.valueOf(udc3));
                        }
                        if (!udc4.equals(ConstantsUtils.NULL)) {
                            udc.setUdc4(Integer.valueOf(udc4));
                        }
                        if (!udc5.equals(ConstantsUtils.NULL)) {
                            udc.setUdc5(Integer.valueOf(udc5));
                        }
                        if (!udc6.equals(ConstantsUtils.NULL)) {
                            udc.setUdc6(Integer.valueOf(udc6));
                        }
                        dao.saveUdcs(udc);
                    }
                    if (result.getCompanyStatus() != 0) {
                        UDCIncrementCheck.increment(String.valueOf(company.getCompanyStatus()), UIUtils.STATUS);
                    }

                    if (result.getCompanyType() != 0) {
                        UDCIncrementCheck.increment(String.valueOf(company.getCompanyType()), UIUtils.COMPANY_TYPE);
                    }
                    if (result.getCompanyGroup() != null) {
                        UDCIncrementCheck.increment(company.getCompanyGroup(), UIUtils.COMPANY_GROUP);
                    }
                    if (result.getCompanyCategory() != 0) {
                        UDCIncrementCheck.increment(String.valueOf(company.getCompanyCategory()), UIUtils.COMPANY_CATEGORY);
                    }
                    if (result.getState() != 0) {
                        UDCIncrementCheck.increment(String.valueOf(company.getState()), UIUtils.STATE);
                    }
                    if (result.getCountry() != 0) {
                        UDCIncrementCheck.increment(String.valueOf(company.getCountry()), UIUtils.COUNTRY);
                    }
                    if (result.getOrgKey() != null) {
                        UDCIncrementCheck.increment(company.getOrgKey(), UIUtils.ORGANIZATION_KEY);
                    }
                    if (udc.getUdc1() != 0) {
                        UDCIncrementCheck.increment(String.valueOf(udc.getUdc1()), UIUtils.UDC1);
                    }
                    if (udc.getUdc2() != 0) {
                        UDCIncrementCheck.increment(String.valueOf(udc.getUdc2()), UIUtils.UDC2);
                    }
                    if (udc.getUdc3() != 0) {
                        UDCIncrementCheck.increment(String.valueOf(udc.getUdc3()), UIUtils.UDC3);
                    }
                    if (udc.getUdc4() != 0) {
                        UDCIncrementCheck.increment(String.valueOf(udc.getUdc4()), UIUtils.UDC4);
                    }
                    if (udc.getUdc5() != 0) {
                        UDCIncrementCheck.increment(String.valueOf(udc.getUdc5()), UIUtils.UDC5);
                    }
                    if (udc.getUdc6() != 0) {
                        UDCIncrementCheck.increment(String.valueOf(udc.getUdc6()), UIUtils.UDC6);
                    }
                    idenList = dao.getCompanyIdentifierByCompanySystemId(result.getCompanyMasterSid());

                    for (int i = 0; i < idenList.size(); i++) {
                        final CompanyIdentifier identifier1 = idenList.get(i);
                        identifier1.setInboundStatus(ConstantsUtils.INBOUND_STATUS_D);
                        dao.updateCompanyCrtIdentifier(identifier1);
                        if (identifier1.getIdentifierStatus() != 0) {
                            UDCIncrementCheck.decrement(String.valueOf(identifier1.getIdentifierStatus()), UIUtils.IDENTIFIER_STATUS);
                        }
                    }
                    deleteCompanyTradeClassForUpdate(Integer.parseInt(systemId));
                    deleteParentCompanyForUpdate(Integer.parseInt(systemId));
                    saveIdentifiersList(identifierList, result);
                    saveCompanyTradeClass(companyTradeList, result);
                    saveParentCompanyDetails(parentCompanyList, result);
                    if (removeDetailsList.size() != 0) {
                        for (int i = 0; i < removeDetailsList.size(); i++) {

                        	NotesDTO dtoValue = removeDetailsList.get(i);

                            if (dtoValue.getDocDetailsId() != 0) {

                                notesLogic.deleteUploadedFile(dtoValue.getDocDetailsId(), dtoValue.getDocumentFullPath());

                            }
                        }
                    }
                    notesLogic.saveUploadedInformation(availableUploadedInformation, ConstantsUtils.COMPANY_MASTER, company.getCompanyMasterSid());
                } else if (countNo > Constants.ZERO) {
                    LOGGER.debug("return duplicateNo");
                    return "duplicateNo";
                } else {
                    LOGGER.debug("return duplicate");
                    return ConstantsUtils.DUPLICATE;
                }
            }
            LOGGER.debug("return success");
            return ConstantsUtils.SUCCESS;

        } catch (Exception e) {
            LOGGER.error(e);
            return ConstantsUtils.FAIL;
        }

    }

    /**
     * To Save identifiers list.
     *
     * @param identifierList the identifier list
     * @param result the result
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public void saveIdentifiersList(final List<CompanyCrtIdentifierDTO> identifierList, final CompanyMaster result) throws SystemException, PortalException {
        LOGGER.debug("Entering saveIdentifiersList");
        if (identifierList != null) {
            for (int i = 0; i < identifierList.size(); i++) {
                final CompanyCrtIdentifierDTO identifierForm = (CompanyCrtIdentifierDTO) identifierList.get(i);

                final CompanyIdentifier identifier = getCompanyCrtIdentifier(identifierForm);

                identifier.setCompanyMasterSid(result.getCompanyMasterSid());

                if (identifierForm.getStartDate() != null && !StringUtils.EMPTY.equals(identifierForm.getStartDate())) {
                    identifier.setStartDate(identifierForm.getStartDate());
                } else {
                    identifier.setStartDate(null);
                }
                if (identifierForm.getEndDate() != null && !StringUtils.EMPTY.equals(identifierForm.getEndDate())) {
                    identifier.setEndDate(identifierForm.getEndDate());
                } else {
                    identifier.setEndDate(null);
                }
                identifier.setModifiedDate(new Date());
                final DynamicQuery companyIdentifierDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyIdentifier.class);
                companyIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq("companyIdentifierValue", identifier.getCompanyIdentifierValue().trim()));
                companyIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.START_DATE, identifier.getStartDate()));
                companyIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_QUALIFIER_SID, identifier.getCompanyQualifierSid()));
                companyIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
                final List<CompanyIdentifier> companyIdentiiferList = getCompanyCrtIdentifierlist(companyIdentifierDynamicQuery);

                if (!companyIdentiiferList.isEmpty()) {
                    CompanyIdentifier companyIdentifier = companyIdentiiferList.get(0);
                    companyIdentifier.setEntityCode(identifier.getEntityCode());
                    companyIdentifier.setStartDate(identifier.getStartDate());
                    companyIdentifier.setEndDate(identifier.getEndDate());
                    companyIdentifier.setCompanyQualifierSid(identifier.getCompanyQualifierSid());
                    companyIdentifier.setIdentifierStatus(identifier.getIdentifierStatus());
                    companyIdentifier.setCompanyIdentifierValue(identifier.getCompanyIdentifierValue());
                    companyIdentifier.setInboundStatus(ConstantsUtils.INBOUND_STATUS_A);
                    identifier.setSource(ConstantsUtils.GTN);                    
                    companyIdentifier.setModifiedDate(new Date());                    
                    companyIdentifier.setModifiedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));

                    final CompanyIdentifier crt = dao.updateCompanyCrtIdentifier(companyIdentifier);
                    if (crt.getIdentifierStatus() != 0) {
                        UDCIncrementCheck.increment(String.valueOf(crt.getIdentifierStatus()), UIUtils.IDENTIFIER_STATUS);
                    }
                } else {
                    if (identifierForm.getCrtIdentifierSystemId() == falseFlag) {
                        identifier.setInboundStatus(ConstantsUtils.INBOUND_STATUS_A);
                        identifier.setSource(ConstantsUtils.GTN);
                        identifier.setCreatedDate(new Date());
                        identifier.setCreatedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
                        final CompanyIdentifier crt = dao.saveCompanyCrtIdentifier(identifier);

                        if (crt.getIdentifierStatus() != 0) {
                            UDCIncrementCheck.increment(String.valueOf(crt.getIdentifierStatus()), UIUtils.IDENTIFIER_STATUS);
                        }
                    } else {
                        identifier.setModifiedBy(Integer.parseInt((String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID)));
                        identifier.setInboundStatus(ConstantsUtils.INBOUND_STATUS_C);
                        identifier.setSource(ConstantsUtils.GTN);
                        final CompanyIdentifier crt = dao.saveCompanyCrtIdentifier(identifier);

                        if (crt.getIdentifierStatus() != 0) {
                            UDCIncrementCheck.increment(String.valueOf(crt.getIdentifierStatus()), UIUtils.IDENTIFIER_STATUS);
                        }
                    }
                }

            }

        }
        LOGGER.debug("Ending saveIdentifiersList");
    }

    /**
     * Save company trade class.
     *
     * @param companyTradeList the company trade list
     * @param result the result
     */
    public void saveCompanyTradeClass(final List<CompanyMasterDTO> companyTradeList, final CompanyMaster result) {
        try {
            LOGGER.debug("Entering saveCompanyTradeClass");
            for (int i = 0; i < companyTradeList.size(); i++) {
                final CompanyTradeClass tradeClass = CompanyTradeClassLocalServiceUtil.createCompanyTradeClass(0);
                tradeClass.setRecordLockStatus(false);//unlocked
                tradeClass.setCompanyMasterSid(result.getCompanyMasterSid());
                tradeClass.setCompanyTradeClass(companyTradeList.get(i).getTradeClass1().getId());


                if (companyTradeList.get(i).getTradeClassSDate() != null && !StringUtils.EMPTY.equals(companyTradeList.get(i).getTradeClassSDate())) {
                    tradeClass.setTradeClassStartDate(companyTradeList.get(i).getTradeClassSDate());
                }
                if (companyTradeList.get(i).getTradeClassEDate() != null && !StringUtils.EMPTY.equals(companyTradeList.get(i).getTradeClassEDate())) {
                    tradeClass.setTradeClassEndDate(companyTradeList.get(i).getTradeClassEDate());
                } else {
                    tradeClass.setTradeClassEndDate(null);
                }
                final DynamicQuery tradeClassDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyTradeClass.class);
                tradeClassDynamicQuery.add(RestrictionsFactoryUtil.eq("companyTradeClass", tradeClass.getCompanyTradeClass()));
                tradeClassDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.TRADE_CLASS_START_DATE, tradeClass.getTradeClassStartDate()));
                tradeClassDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
                final List<CompanyTradeClass> list = dao.getCompanyTradeClassList(tradeClassDynamicQuery);
                if (!list.isEmpty()) {
                    CompanyTradeClass companyTradeClass = list.get(0);
                    companyTradeClass.setModifiedBy(Integer.parseInt((String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID)));
                    companyTradeClass.setModifiedDate(new Date());
                    companyTradeClass.setInboundStatus(ConstantsUtils.INBOUND_STATUS_A);
                    companyTradeClass.setSource(ConstantsUtils.GTN);
                    companyTradeClass.setRecordLockStatus(tradeClass.getRecordLockStatus());
                    companyTradeClass.setCompanyTradeClass(tradeClass.getCompanyTradeClass());
                    companyTradeClass.setTradeClassStartDate(tradeClass.getTradeClassStartDate());
                    companyTradeClass.setTradeClassEndDate(tradeClass.getTradeClassEndDate());
                    CompanyTradeClassLocalServiceUtil.updateCompanyTradeClass(companyTradeClass);
                } else {
                    if (companyTradeList.get(i).getTradeClassSysId() == Constants.ZERO) {
                        tradeClass.setCreatedBy(Integer.parseInt((String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID)));
                        tradeClass.setCreatedDate(new Date());
                        tradeClass.setModifiedDate(new Date());
                        tradeClass.setInboundStatus(ConstantsUtils.INBOUND_STATUS_A);
                        tradeClass.setSource(ConstantsUtils.GTN);
                        CompanyTradeClassLocalServiceUtil.addCompanyTradeClass(tradeClass);
                    } else {
                        tradeClass.setCompanyTradeClassSid(companyTradeList.get(i).getTradeClassSysId());
                        if (String.valueOf(companyTradeList.get(i).getTradeClass1()).equals(companyTradeList.get(i).getOldTC())) {
                            if(!StringUtils.EMPTY.equals(companyTradeList.get(i).getPriorTradeClass()))
                                    tradeClass.setPriorTradeClass(Integer.parseInt(companyTradeList.get(i).getPriorTradeClass()));
                            else
                                tradeClass.setPriorTradeClass(Integer.parseInt("0"));
                        } else {
                            tradeClass.setPriorTradeClass(Integer.parseInt(companyTradeList.get(i).getOldTC()));
                        }
                        if (companyTradeList.get(i).getTradeClassSDate() == companyTradeList.get(i).getOldTCDate()) {
                            tradeClass.setPriorTradeClassStartDate(companyTradeList.get(i).getPriorTradeClassStartDate());
                        } else {
                            tradeClass.setPriorTradeClassStartDate(companyTradeList.get(i).getOldTCDate());
                        }
                        tradeClass.setModifiedBy(Integer.parseInt((String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID)));
                        tradeClass.setModifiedDate(new Date());
                        tradeClass.setInboundStatus(ConstantsUtils.INBOUND_STATUS_C);
                        tradeClass.setSource(ConstantsUtils.GTN);
                        CompanyTradeClassLocalServiceUtil.updateCompanyTradeClass(tradeClass);
                    }
                }

            }
            LOGGER.debug("Ending saveCompanyTradeClass");

        } catch (SystemException ex) {
            LOGGER.error(ex);
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            LOGGER.error(errorMsg);
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

    }

    /**
     * Save parent company details.
     *
     * @param parentCompanyList the parent company list
     * @param result the result
     */
    public void saveParentCompanyDetails(final List<CompanyMasterDTO> parentCompanyList, final CompanyMaster result) {

        for (int i = 0; i < parentCompanyList.size(); i++) {
            final CompanyParentDetails parentCompany = CompanyParentDetailsLocalServiceUtil.createCompanyParentDetails(0);
            parentCompany.setCompanyMasterSid(result.getCompanyMasterSid());
            parentCompany.setParentCompanyMasterSid(parentCompanyList.get(i).getParentCompanySysId());
            parentCompany.setRecordLockStatus(false);//unlocked
            if (parentCompanyList.get(i).getParentStartDate() != null) {
                parentCompany.setParentStartDate(parentCompanyList.get(i).getParentStartDate());
            }
            if (parentCompanyList.get(i).getParentEndDate() != null) {
                parentCompany.setParentEndDate(parentCompanyList.get(i).getParentEndDate());
            }
            if (parentCompanyList.get(i).getPriorParentCompanySysId() != 0) {
                parentCompany.setPriorParentCmpyMasterSid(String.valueOf(parentCompanyList.get(i).getPriorParentCompanySysId()));
            } else {
                parentCompany.setPriorParentCmpyMasterSid(null);
            }
            try {
                final DynamicQuery parentDetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyParentDetails.class);
                parentDetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.PARENT_COMPANY_IDENTIFIER_SID, parentCompany.getParentCompanyMasterSid()));
                parentDetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.PARENT_START_DATE, parentCompany.getParentStartDate()));
                parentDetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
                final List<CompanyParentDetails> parentDetails = dao.getCompanyParentDetails(parentDetailsDynamicQuery);
                if (!parentDetails.isEmpty()) {
                    CompanyParentDetails companyParentDetails = parentDetails.get(0);
                    companyParentDetails.setModifiedBy(Integer.parseInt((String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID)));
                    companyParentDetails.setModifiedDate(new Date());
                    companyParentDetails.setInboundStatus(ConstantsUtils.INBOUND_STATUS_A);
                    companyParentDetails.setSource(ConstantsUtils.GTN);
                    companyParentDetails.setParentCompanyMasterSid(parentCompany.getParentCompanyMasterSid());
                    companyParentDetails.setRecordLockStatus(parentCompany.getRecordLockStatus());
                    companyParentDetails.setParentStartDate(parentCompany.getParentStartDate());
                    companyParentDetails.setParentEndDate(parentCompany.getParentEndDate());
                    companyParentDetails.setPriorParentCmpyMasterSid(parentCompany.getPriorParentCmpyMasterSid());
                    CompanyParentDetailsLocalServiceUtil.updateCompanyParentDetails(companyParentDetails);
                } else {


                    if (parentCompanyList.get(i).getParentCompanyDetailsSysId() == Constants.ZERO) {
                        parentCompany.setCreatedBy(Integer.parseInt((String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID)));
                        parentCompany.setCreatedDate(new Date());
                        parentCompany.setModifiedDate(new Date());
                        parentCompany.setInboundStatus(ConstantsUtils.INBOUND_STATUS_A);
                        parentCompany.setSource(ConstantsUtils.GTN);
                        CompanyParentDetailsLocalServiceUtil.addCompanyParentDetails(parentCompany);
                    } else {
                        if (parentCompanyList.get(i).getPriorParentCompanySysId() != parentCompany.getParentCompanyMasterSid()) {
                            parentCompany.setPriorParentCmpyMasterSid(parentCompanyList.get(i).getPriorParentCompanySysId() == 0 ? null : String.valueOf(parentCompanyList.get(i).getPriorParentCompanySysId()));
                            if (parentCompanyList.get(i).getParentStartDate() == parentCompanyList.get(i).getOldParentStartDate()) {
                                parentCompany.setPriorParentStartDate(parentCompanyList.get(i).getPriorParentStartDate());
                            } else {
                                parentCompany.setPriorParentStartDate(parentCompanyList.get(i).getOldParentStartDate());
                            }
                        }
                        parentCompany.setInboundStatus(ConstantsUtils.INBOUND_STATUS_C);
                        parentCompany.setSource(ConstantsUtils.GTN);
                        parentCompany.setModifiedBy(Integer.parseInt((String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID)));
                        parentCompany.setModifiedDate(new Date());
                        parentCompany.setCompanyParentDetailsSid(parentCompanyList.get(i).getParentCompanyDetailsSysId());
                        CompanyParentDetailsLocalServiceUtil.updateCompanyParentDetails(parentCompany);
                    }
                }
            } catch (SystemException ex) {
                LOGGER.error(ex);
                final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                LOGGER.error(errorMsg);
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
        }

    }

    /**
     * Gets the CompanyCrtIdentifier model from CompanyCrtIdentifierDTO.
     *
     * @param form the form
     * @return the company crt identifier
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public CompanyIdentifier getCompanyCrtIdentifier(final CompanyCrtIdentifierDTO form) throws SystemException, PortalException {
        LOGGER.debug("Entering getCompanyCrtIdentifier");
        final CompanyIdentifier identifier = CompanyIdentifierLocalServiceUtil.createCompanyIdentifier(0);

        final CompanyQualifier qualifier = dao.getCompanyCrtQualifierByQualifierName(form.getCompanyCrtQualifierName());
        identifier.setCompanyQualifierSid(qualifier.getCompanyQualifierSid());
        if (form.getEntityCode() != null && !StringUtils.EMPTY.equals(form.getEntityCode().trim())) {
            identifier.setEntityCode(form.getEntityCode().trim());
        }
        identifier.setIdentifierStatus(((com.stpl.ifs.util.HelperDTO) form.getIdentifierStatus()).getId());
        identifier.setCompanyIdentifierValue(form.getCompanyIdentifier().trim());
        if (form.getCrtIdentifierSystemId() == falseFlag) {
            LOGGER.debug("----getCompanyCrtQualifierId-------" + form.getCrtIdentifierSystemId());

            identifier.setCreatedDate(new Date());
            identifier.setModifiedDate(new Date());
            identifier.setCreatedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));

        } else {
            LOGGER.debug("---update----getCompanyCrtQualifierId----------" + form.getCrtIdentifierSystemId());
            if (StringUtils.isNotBlank(form.getCreatedBy()) && !(form.getCreatedBy().equals(ConstantsUtils.NULL)) && form.getCreatedBy().matches(ValidationUtils.NUM_VALIDATION)) {
                identifier.setCreatedBy(Integer.parseInt(form.getCreatedBy()));
            } else {
                identifier.setCreatedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
            }
            identifier.setCreatedDate(form.getCreatedDate());
            identifier.setCompanyIdentifierSid(form.getCrtIdentifierSystemId());
            identifier.setModifiedDate(new Date());
            identifier.setModifiedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));

        }
        LOGGER.debug("return CompanyCrtIdentifier");
        return identifier;
    }

    /**
     * Delete company master by id.
     *
     * @param companySystemId the company system id
     * @return the company master
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public CompanyMaster deleteCompanyMasterById(final int companySystemId) throws SystemException, PortalException {
        LOGGER.debug("Entering deleteCompanyMasterById p1:" + companySystemId);
        final CompanyMaster company = dao.getCompanyMasterBySystemId(companySystemId);
        company.setInboundStatus(ConstantsUtils.INBOUND_STATUS_D);
        dao.updateCompanyMaster(company);
        if (company.getCompanyStatus() != 0) {
            UDCIncrementCheck.decrement(String.valueOf(company.getCompanyStatus()), UIUtils.STATUS);
        }

        if (company.getCompanyType() != 0) {
            UDCIncrementCheck.decrement(String.valueOf(company.getCompanyType()), UIUtils.COMPANY_TYPE);
        }
        if (company.getCompanyGroup() != null) {
            UDCIncrementCheck.decrement(company.getCompanyGroup(), UIUtils.COMPANY_GROUP);
        }
        if (company.getCompanyCategory() != 0) {
            UDCIncrementCheck.decrement(String.valueOf(company.getCompanyCategory()), UIUtils.COMPANY_CATEGORY);
        }
        if (company.getState() != 0) {
            UDCIncrementCheck.decrement(String.valueOf(company.getState()), UIUtils.STATE);
        }
        if (company.getCountry() != 0) {
            UDCIncrementCheck.decrement(String.valueOf(company.getCountry()), UIUtils.COUNTRY);
        }
        if (company.getOrgKey() != null) {
            UDCIncrementCheck.decrement(company.getOrgKey(), UIUtils.ORGANIZATION_KEY);
        }


        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(Udcs.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.MASTER_SID, companySystemId));
        List<Udcs> udc = UdcsLocalServiceUtil.dynamicQuery(cfpDynamicQuery);
        if (udc.size() > 0) {
            Udcs udcs = udc.get(0);
            int id = udcs.getUdc1();
            String descr = getDescription(id);
            UDCIncrementCheck.decrement(descr, UIUtils.UDC1);
        }

        final List<CompanyIdentifier> crtList = dao.getCompanyIdentifierByCompanySystemId(companySystemId);
        for (int i = 0; i < crtList.size(); i++) {
            final CompanyIdentifier identifier1 = crtList.get(i);
            identifier1.setInboundStatus(ConstantsUtils.INBOUND_STATUS_D);
            dao.updateCompanyCrtIdentifier(identifier1);
            if (identifier1.getIdentifierStatus() != 0) {
                UDCIncrementCheck.decrement(String.valueOf(identifier1.getIdentifierStatus()), UIUtils.IDENTIFIER_STATUS);
            }
        }

        final DynamicQuery tradeClassDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyTradeClass.class);
        tradeClassDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.COMPANY_MASTER_ID, companySystemId));
        final List<CompanyTradeClass> list = dao.getCompanyTradeClassList(tradeClassDynamicQuery);
        for (int i = 0; i < list.size(); i++) {
            final CompanyTradeClass compTradeClass = list.get(i);
            compTradeClass.setInboundStatus(ConstantsUtils.INBOUND_STATUS_D);
            CompanyTradeClassLocalServiceUtil.updateCompanyTradeClass(compTradeClass);
        }
        final DynamicQuery parentDetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyParentDetails.class);
        parentDetailsDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.COMPANY_MASTER_ID, companySystemId));
        final List<CompanyParentDetails> parentDetails = dao.getCompanyParentDetails(parentDetailsDynamicQuery);
        for (int i = 0; i < parentDetails.size(); i++) {
            final CompanyParentDetails compParentDetails = parentDetails.get(i);
            compParentDetails.setInboundStatus(ConstantsUtils.INBOUND_STATUS_D);
            CompanyParentDetailsLocalServiceUtil.updateCompanyParentDetails(compParentDetails);
        }
        LOGGER.debug("return CompanyMaster");
        return company;

    }

    /**
     * Gets the company qualifier.
     *
     * @return the company qualifier
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public List<HelperDTO> getCompanyQualifier() throws PortalException, SystemException {
        LOGGER.debug("Entering getCompanyQualifier");
        List<CompanyQualifier> qualifierList;
        final List<HelperDTO> list = new ArrayList<HelperDTO>();

        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyQualifier.class);
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.COMPANY_QUALIFIER_NAME));
        qualifierList = dao.getCompanyCrtQualifierList(cfpDynamicQuery);

        for (int i = 0; i < qualifierList.size(); i++) {

            final CompanyQualifier qualifier = (CompanyQualifier) qualifierList.get(i);
            list.add(new HelperDTO(qualifier.getCompanyQualifierSid(), qualifier.getCompanyQualifierName()));

        }
        LOGGER.debug("return CompanyQualifier size -" + list.size());
        return list;

    }

    /**
     * Gets the drop down list based on list name.
     *
     * @param listName the list name
     * @return the drop down list
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public List<HelperDTO> getDropDownList(final String listName) throws SystemException, PortalException {
        LOGGER.debug("Entering getDropDownList p1:" + listName);
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        final List<HelperTable> list = dao.getHelperTableDetailsByListName(listName);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));

            }
        }
        Collections.sort(helperList);
        LOGGER.debug("return DropDownList :" + helperList.size());
        return helperList;
    }

    /**
     * Gets the company type.
     *
     * @param listType the list type
     * @return the company type
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public List<HelperDTO> getCompanyType(final String listType) throws SystemException, PortalException {
        LOGGER.debug("Entering getDropDownList p1:" + listType);
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);

        cfpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.LIST_NAME, listType));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.DESCRIPTION));
        final List<HelperTable> list = dao.getHelperTableDetailsList(cfpDynamicQuery);

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));

            }
        }
        LOGGER.debug("return CompanyType :" + helperList.size());
        return helperList;
    }

    /**
     * Gets the company group.
     *
     * @return the company group
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public List<HelperDTO> getCompanyGroup() throws SystemException, PortalException {
        LOGGER.debug("Entering getCompanyGroup");
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        final List<HelperTable> list = dao.getHelperTableDetailsByListName(UIUtils.COMPANY_GROUP);

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
            }
        }
        Collections.sort(helperList);
        return helperList;
    }

    /**
     * Gets the company category.
     *
     * @return the company category
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public List<HelperDTO> getCompanyCategory() throws SystemException, PortalException {
        LOGGER.debug("Entering getCompanyCategory");
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        final List<HelperTable> list = dao.getHelperTableDetailsByListName(UIUtils.COMPANY_CATEGORY);

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
            }
        }
        Collections.sort(helperList);
        return helperList;
    }

    /**
     * Gets the organization.
     *
     * @return the organization
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public List<HelperDTO> getOrganization() throws SystemException, PortalException {
        LOGGER.debug("Entering getOrganization");
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        final List<HelperTable> list = dao.getHelperTableDetailsByListName(UIUtils.ORGANIZATION_KEY);

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
            }
        }
        Collections.sort(helperList);
        return helperList;
    }

    /**
     * Gets the UDCS by udcNo.
     *
     * @param udcNo the udc no
     * @return the udcs
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public List<HelperDTO> getUDCS(final String udcNo) throws SystemException, PortalException {
        LOGGER.debug("Entering getUDCS");
        List<HelperTable> list;
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        list = dao.getHelperTableDetailsByListName(udcNo);

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
            }
        }
        Collections.sort(helperList);
        return helperList;
    }

    /**
     * Gets the state.
     *
     * @return the state
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public List<HelperDTO> getState() throws SystemException, PortalException {
        LOGGER.debug("Entering getState");
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        final List<HelperTable> list = dao.getHelperTableDetailsByListName(UIUtils.STATE);

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
            }
        }
        Collections.sort(helperList);
        return helperList;
    }

    /**
     * Gets the country.
     *
     * @return the country
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public List<HelperDTO> getCountry() throws SystemException, PortalException {
        LOGGER.debug("Entering getCountry");
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        final List<HelperTable> list = dao.getHelperTableDetailsByListName(UIUtils.COUNTRY);

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
            }
        }
        Collections.sort(helperList);
        return helperList;
    }

    /**
     * Delete crt identifier.
     *
     * @param identifierSystemId the identifier system id
     * @return the list< company crt identifier dt o>
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public List<CompanyCrtIdentifierDTO> deleteCrtIdentifier(final int identifierSystemId) throws PortalException, SystemException {
        LOGGER.debug("Entering deleteCrtIdentifier p1:" + identifierSystemId);
        dao.deleteCompanyCrtIdentifierByCrtIdentifierSystemId(identifierSystemId);
        return getCompanyQualifierForEditList();
    }

    /**
     * To get the qualifier for edit list.
     *
     * @return the company qualifier for edit list
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public List<CompanyCrtIdentifierDTO> getCompanyQualifierForEditList() throws PortalException, SystemException {
        LOGGER.debug("Entering getCompanyQualifierForEditList");
        final List<CompanyCrtIdentifierDTO> list = new ArrayList<CompanyCrtIdentifierDTO>();
        final List<CompanyQualifier> qualifierList = dao.getCompanyCrtQualifiersByLimit(0, dao.getCompanyCrtQualifierTotalCount());
        final Map<Integer,String> userMap = StplSecurity.userMap;
        for (int i = 0; i < qualifierList.size(); i++) {

            final CompanyCrtIdentifierDTO companyIden = new CompanyCrtIdentifierDTO();
            final CompanyQualifier qualifier = (CompanyQualifier) qualifierList.get(i);
            companyIden.setCompanyCrtQualifierName(qualifier.getCompanyQualifierName());
            companyIden.setCompanyCrtQualifierId(qualifier.getCompanyQualifierSid());
            companyIden.setCompanyQualifier(qualifier.getCompanyQualifierValue());
            companyIden.setEffectiveDates(qualifier.getEffectiveDates());
            companyIden.setNotes(qualifier.getNotes());
            companyIden.setCreatedBy(userMap.get(qualifier.getCreatedBy()));
            companyIden.setCreatedDate(qualifier.getCreatedDate());
            companyIden.setModifiedBy(userMap.get(qualifier.getModifiedBy()));
            companyIden.setModifiedDate(qualifier.getModifiedDate());
            companyIden.setRecordLockStatus(qualifier.getRecordLockStatus());
            list.add(companyIden);
        }
        LOGGER.debug("return CompanyCrtQualifier list -" + list.size());
        return list;

    }

    /**
     * Delete crt qualifer by qualifier ID.
     *
     * @param qualifierId the qualifier id
     * @return the list< company crt identifier dt o>
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public List<CompanyCrtIdentifierDTO> deleteCrtQualifer(final int qualifierId) throws SystemException, PortalException {
        LOGGER.debug("Entering deleteCrtIdentifier p1:" + qualifierId);
        List<CompanyCrtIdentifierDTO> object = new ArrayList<CompanyCrtIdentifierDTO>();
        try {

            DynamicQuery query = DynamicQueryFactoryUtil.forClass(CompanyIdentifier.class);
            query.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_QUALIFIER_SID, qualifierId));
            final List<CompanyIdentifier> compIdentifierList = dao.getCompanyIdentifierList(query);
            if (compIdentifierList.isEmpty()) {
                dao.deleteCompanyCrtQualifierByQualifierId(qualifierId);
                object = getCompanyQualifierForEditList();
            } else {
                final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Delete Failed", "Can not delete the selected Qualifier,Since it is associated with another Company", new MessageBoxListener() {
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

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return object;

    }

    /**
     * Save crt qualifier.
     *
     * @param binder the binder
     * @return the list of CompanyCrtIdentifierDTO
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public List<CompanyCrtIdentifierDTO> saveCrtQualifer(final ErrorfulFieldGroup binder) throws PortalException, SystemException {
        LOGGER.debug("Entering deleteCrtIdentifier");
        final int companyCrtQualifierId = Integer.valueOf(binder.getField("companyCrtQualifierId").getValue().toString());
        final String companyCrtQualifierName = binder.getField("companyCrtQualifierName").getValue().toString().trim();
        final String companyIdentifier = binder.getField("companyQualifier").getValue().toString().trim();
        final String effectiveDates=binder.getField("effectiveDates").getValue().toString().trim();
        final String notes=binder.getField("notes").getValue().toString().trim();
        final CompanyQualifier identifier = CompanyQualifierLocalServiceUtil.createCompanyQualifier(0);
        identifier.setCompanyQualifierSid(companyCrtQualifierId);
        identifier.setCompanyQualifierName(companyCrtQualifierName);
        identifier.setCompanyQualifierValue(companyIdentifier);
        identifier.setEffectiveDates(effectiveDates);
        identifier.setNotes(notes);
        if (companyCrtQualifierId <= falseFlag) {

            // uniqueness check for company crt identifier name and id in lookup
            identifier.setCreatedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
            identifier.setCreatedDate(new Date());
            identifier.setModifiedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
            identifier.setModifiedDate(new Date());
            final DynamicQuery compamyIdDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyQualifier.class);
            compamyIdDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_QUALIFIER_VALUE, identifier.getCompanyQualifierValue()));
            final List<CompanyQualifier> contractMaster = dao.getCompanyCrtQualifierList(compamyIdDynamicQuery);

            final DynamicQuery compamyNoDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyQualifier.class);
            compamyNoDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_QUALIFIER_NAME, identifier.getCompanyQualifierName()));
            final List<CompanyQualifier> contractNoMaster = dao.getCompanyCrtQualifierList(compamyNoDynamicQuery);
            LOGGER.debug("contractMaster.size()" + contractMaster.size());
            LOGGER.debug("contractNoMaster.size()" + contractNoMaster.size());

            if (contractMaster.size() < 1 && contractNoMaster.size() < 1) {
                dao.saveCompanyCrtQualifier(identifier);
                LOGGER.debug("entered contraId and ContractNo");

            } else if (contractMaster.size() == trueFlag) {
                binder.getErrorDisplay().setError("Please enter different Qualifier, Since the entered qualifier already exists ");

            } else {
                binder.getErrorDisplay().setError("Please enter different Qualifier Name, Since the entered qualifier Name already exists ");
            }
        } else {
            // uniqueness check for company crt identifier name and id in lookup
            CompanyQualifier companyQualifier = CompanyQualifierLocalServiceUtil.getCompanyQualifier(companyCrtQualifierId);// to get created Date & created By
            companyQualifier.setCompanyQualifierSid(companyCrtQualifierId);
            companyQualifier.setCompanyQualifierName(companyCrtQualifierName);
            companyQualifier.setCompanyQualifierValue(companyIdentifier);
            companyQualifier.setEffectiveDates(effectiveDates);
            companyQualifier.setNotes(notes);
            companyQualifier.setCreatedBy(companyQualifier.getCreatedBy());
            companyQualifier.setCreatedDate(companyQualifier.getCreatedDate());
            companyQualifier.setModifiedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
            companyQualifier.setModifiedDate(new Date());
            final DynamicQuery compamyIdDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyQualifier.class);
            compamyIdDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_QUALIFIER_VALUE, companyQualifier.getCompanyQualifierValue()));
            final List<CompanyQualifier> conMaster = dao.getCompanyCrtQualifierList(compamyIdDynamicQuery);

            final DynamicQuery compamyNoDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyQualifier.class);
            compamyNoDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_QUALIFIER_NAME, companyQualifier.getCompanyQualifierName()));
            final List<CompanyQualifier> conNoMaster = dao.getCompanyCrtQualifierList(compamyNoDynamicQuery);

            int count = 0;
            for (int i = 0; i < conMaster.size(); i++) {
                if (companyCrtQualifierId == conMaster.get(i).getCompanyQualifierSid()) {
                } else {

                    count++;
                }

            }
            int countNo = 0;

            for (int i = 0; i < conNoMaster.size(); i++) {
                if (companyCrtQualifierId == conNoMaster.get(i).getCompanyQualifierSid()) {
                } else {

                    countNo++;
                }

            }
            LOGGER.debug("updated successfully count" + conMaster.size());
            LOGGER.debug("updated successfully countNo" + conNoMaster.size());
            if (count < 1 && countNo < 1) {
                LOGGER.debug("updated successfully");
                dao.updateCompanyCrtQualifier(companyQualifier);
            } else if (conMaster.size() == trueFlag) {
                binder.getErrorDisplay().setError("Please enter different Qualifier, Since the entered qualifier already exists ");

            } else {
                binder.getErrorDisplay().setError("Please enter different Qualifier Name, Since the entered qualifier Name already exists");
            }
        }
        LOGGER.debug("Ending deleteCrtIdentifier");
        return getCompanyQualifierForEditList();
    }

    /**
     * Gets the company crt qualifier by qualifier name.
     *
     * @param companyCrtQualifierName the company crt qualifier name
     * @return the company crt qualifier by qualifier name
     */
    public CompanyQualifier getCompanyCrtQualifierByQualifierName(final String companyCrtQualifierName) {
        CompanyQualifier companyCrtQualifier = null ;
        try {
            companyCrtQualifier = dao.getCompanyCrtQualifierByQualifierName(companyCrtQualifierName.trim());

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return companyCrtQualifier;

    }

    /**
     * Gets the list of CompanyCrtIdentifier from dynamic query.
     *
     * @param query the query
     * @return the company crt identifierlist
     */
    public List<CompanyIdentifier> getCompanyCrtIdentifierlist(final DynamicQuery query) {
        try {
            List<CompanyIdentifier> companyCrtQualifier = dao.getCompanyIdentifierlist(query);
            return companyCrtQualifier;
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }

    /**
     * Gets the company master by system id.
     *
     * @param companySystemId the company system id
     * @return the company master by system id
     */
    public CompanyMaster getCompanyMasterBySystemId(final int companySystemId) {

          CompanyMaster companyMaster = CompanyMasterLocalServiceUtil.createCompanyMaster(0);
        try {
            companyMaster = dao.getCompanyMasterBySystemId(companySystemId);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return companyMaster;

    }

    /**
     * Gets the company master list.
     *
     * @param query the query
     * @return the company master list
     */
    public List<CompanyMaster> getCompanyMasterList(final DynamicQuery query) {

        List<CompanyMaster> companyMaster = null;
        try {

            companyMaster = dao.getCompanyMasterList(query);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return companyMaster;

    }

    /**
     * Gets the parent company table.
     *
     * @param companySystemId the company system id
     * @return the parent company table
     */
    public List<CompanyMasterDTO> getParentCompanyTable(final int companySystemId) {
        List<CompanyMasterDTO> parentCompany = new ArrayList<CompanyMasterDTO>();
        List<CompanyParentDetails> companyParentDetails = null;
        try {
            final DynamicQuery parentDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyParentDetails.class);
            parentDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_MASTER_ID, companySystemId));
            companyParentDetails = CompanyParentDetailsLocalServiceUtil.dynamicQuery(parentDynamicQuery);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        parentCompany = getCustomisedParentCompanyList(companyParentDetails);
        return parentCompany;

    }

    /**
     * Gets the customised parent company list.
     *
     * @param parentCompanyDetails the parent company details
     * @return the customised parent company list
     */
    public List<CompanyMasterDTO> getCustomisedParentCompanyList(final List<CompanyParentDetails> parentCompanyDetails) {
        final List<CompanyMasterDTO> parentCompany = new ArrayList<CompanyMasterDTO>();
        CompanyMasterDTO dto;
        try {

            for (final CompanyParentDetails parentInfo : parentCompanyDetails) {
                dto = new CompanyMasterDTO();
                if (!ConstantsUtils.INBOUND_STATUS_D.equals(parentInfo.getInboundStatus())) {
                    dto.setParentCompanyDetailsSysId(parentInfo.getCompanyParentDetailsSid());
                    dto.setParentCompanySysId(parentInfo.getParentCompanyMasterSid());
                    dto.setOldParentId(parentInfo.getParentCompanyMasterSid());
                    dto.setCompanySystemId(String.valueOf(parentInfo.getCompanyMasterSid()));
                    CompanyMaster companyInfo = CompanyMasterLocalServiceUtil.getCompanyMaster(parentInfo.getParentCompanyMasterSid());
                    dto.setParentCompanyNo(companyInfo.getCompanyNo());
                    dto.setOldCompanyNo(companyInfo.getCompanyId());
                    dto.setParentCompanyName(companyInfo.getCompanyName());
                    dto.setParentStartDate(dateToDateConvert((Date) parentInfo.getParentStartDate()));
                    dto.setOldParentStartDate(dto.getParentStartDate());
                    if (parentInfo.getParentEndDate() != null) {
                        dto.setParentEndDate(dateToDateConvert((Date) parentInfo.getParentEndDate()));
                    }
                    dto.setPriorParentCompanySysId(Integer.valueOf(parentInfo.getPriorParentCmpyMasterSid() == null ? 0 : Integer.valueOf(parentInfo.getPriorParentCmpyMasterSid())));
                    Object priorParentCmpyMasterSid = parentInfo.getPriorParentCmpyMasterSid();
                    if (priorParentCmpyMasterSid != null) {
                        companyInfo = CompanyMasterLocalServiceUtil.getCompanyMaster(Integer.valueOf(parentInfo.getPriorParentCmpyMasterSid()));
                        dto.setPriorParentCompanyNo(companyInfo.getCompanyNo());
                    }
                    if (parentInfo.getPriorParentStartDate() != null) {
                        dto.setPriorParentStartDate(dateToDateConvert((Date) parentInfo.getPriorParentStartDate()));
                    }
                    dto.setCreatedDate(dateToDateConvert(parentInfo.getCreatedDate()));
                    dto.setModifiedDate(dateToDateConvert(parentInfo.getModifiedDate()));
                    try {
                        User createdUser = (parentInfo.getCreatedBy() == 0) ? null : ((User) securityDto.getUserByUserId(parentInfo.getCreatedBy()));
                        dto.setCreatedBy(createdUser != null ? createdUser.getFullName() : StringUtils.EMPTY);
                        dto.setCreatedUserId(parentInfo.getCreatedBy());
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                    try {
                        User ModifiedUser = (parentInfo.getModifiedBy() == 0) ? null : ((User) securityDto.getUserByUserId(parentInfo.getModifiedBy()));
                        dto.setModifiedBy(ModifiedUser != null ? ModifiedUser.getFullName() : StringUtils.EMPTY);
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                    parentCompany.add(dto);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return parentCompany;
    }

    /**
     * Gets the customized trade class list.
     *
     * @param companyTradeClass the company trade class
     * @return the customized trade class list
     */
    public List<CompanyMasterDTO> getCustomizedTradeClassList(final List companyTradeClass) {

        final List<CompanyMasterDTO> companyTrade = new ArrayList<CompanyMasterDTO>();
        final CompanyMasterDTO dto = new CompanyMasterDTO();
        for (int i = 0; i < companyTradeClass.size(); i++) {
            final Object[] obj = (Object[]) companyTradeClass.get(i);
             dto.setTradeClass1(obj[0] == null ? helperListUtil.getIdHelperDTOMap().get(0) : helperListUtil.getIdHelperDTOMap().get((Integer) obj[0]));

            try {
                dto.setTradeClassSDate(dateToDateConvert((Date) obj[1]));
            } catch (ParseException e1) {
                LOGGER.error(e1);
            }
            if (obj[NumericConstants.TWO] != null) {
                try {
                    dto.setTradeClassEDate(dateToDateConvert((Date) obj[NumericConstants.TWO]));
                } catch (ParseException e) {
                    LOGGER.error(e);
                }
            }
            dto.setCompanySystemId(String.valueOf(obj[NumericConstants.THREE]));
            companyTrade.add(dto);
        }
        return companyTrade;
    }

    /**
     * Delete company trade class for update.
     *
     * @param systemId the system id
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public void deleteCompanyTradeClassForUpdate(final int systemId) throws SystemException, PortalException {

        final DynamicQuery tradeClassDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyTradeClass.class);
        tradeClassDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.COMPANY_MASTER_ID, systemId));
        final List<CompanyTradeClass> list = dao.getCompanyTradeClassList(tradeClassDynamicQuery);
        for (int i = 0; i < list.size(); i++) {
            final CompanyTradeClass compTradeClass = list.get(i);
            compTradeClass.setInboundStatus(ConstantsUtils.INBOUND_STATUS_D);
            CompanyTradeClassLocalServiceUtil.updateCompanyTradeClass(compTradeClass);
        }
    }

    /**
     * Delete parent company for update.
     *
     * @param systemId the system id
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public void deleteParentCompanyForUpdate(final int systemId) throws SystemException, PortalException {
        final DynamicQuery parentDetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyParentDetails.class);
        parentDetailsDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.COMPANY_MASTER_ID, systemId));
        final List<CompanyParentDetails> parentDetails = dao.getCompanyParentDetails(parentDetailsDynamicQuery);
        for (int i = 0; i < parentDetails.size(); i++) {
            final CompanyParentDetails compParentDetails = parentDetails.get(i);
            compParentDetails.setInboundStatus(ConstantsUtils.INBOUND_STATUS_D);
            CompanyParentDetailsLocalServiceUtil.updateCompanyParentDetails(compParentDetails);
        }
    }

    /**
     * Gets the true flag.
     *
     * @return the true flag
     */
    public int getTrueFlag() {
        return trueFlag;
    }

    /**
     * Sets the true flag.
     *
     * @param trueFlag the true flag
     */
    public void setTrueFlag(final int trueFlag) {
        this.trueFlag = trueFlag;
    }

    /**
     * Gets the false flag.
     *
     * @return the false flag
     */
    public int getFalseFlag() {
        return falseFlag;
    }

    /**
     * Sets the false flag.
     *
     * @param falseFlag the false flag
     */
    public void setFalseFlag(final int falseFlag) {
        this.falseFlag = falseFlag;
    }

    /**
     * Gets the company dynamic query.
     *
     * @return the company dynamic query
     */
    public DynamicQuery getCompanyDynamicQuery() {
        return companyDynamicQuery;
    }

    /**
     * Gets the company parent details list.
     *
     * @param query the query
     * @return the company parent details list
     */
    public List<CompanyParentDetails> getCompanyParentDetailsList(final DynamicQuery query) {

        List<CompanyParentDetails> companyParentDetailsList = null;
        try {

            companyParentDetailsList = dao.getCompanyParentDetails(query);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return companyParentDetailsList;

    }

    /**
     * getting count for CompanyQualifierName.
     *
     * @param filterText the filter text
     * @return the lazy company qualifier name count
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public static int getLazyCompanyQualifierNameCount(String filterText,boolean isEditList) throws PortalException, SystemException {
        filterText = StringUtils.trimToEmpty(filterText) + ConstantsUtils.PERCENCTAGE;
        LOGGER.debug("Entering getLazyCompanyQualifierNameCount method with filterText" + filterText);
        List<Object[]> qualifierList;
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyQualifier.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.COMPANY_QUALIFIER_NAME, filterText));
        cfpDynamicQuery.setProjection(ProjectionFactoryUtil.countDistinct(ConstantsUtils.COMPANY_QUALIFIER_SID));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.COMPANY_QUALIFIER_NAME));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ConstantsUtils.COMPANY_QUALIFIER_NAME, StringUtils.EMPTY)));
        qualifierList = dao.getCompanyCrtQualifierNameList(cfpDynamicQuery);
        companyQualifierNameCount = Integer.parseInt(String.valueOf(qualifierList.get(0)));
        if(companyQualifierNameCount==0 && isEditList){
           companyQualifierNameCount++; 
        }
        LOGGER.debug("Ending getLazyCompanyQualifierNameCount method : returning count :" + companyQualifierNameCount);
        return companyQualifierNameCount;
    }

    /**
     * getting results for CompanyQualifierName.
     *
     * @param start the start
     * @param end the end
     * @param filterText the filter text
     * @param editListFlag the edit list flag
     * @return the lazy company qualifier name results
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public static List<HelperDTO> getLazyCompanyQualifierNameResults(final int start, final int end, final String filter, final boolean editListFlag) throws PortalException, SystemException {
        String filterText = StringUtils.trimToEmpty(filter) + ConstantsUtils.PERCENCTAGE;
        LOGGER.debug("Entering getLazyCompanyQualifierNameCount method with filterText" + filter);
        List<Object[]> qualifierList;
        final List<HelperDTO> list = new ArrayList<HelperDTO>();
        int startValue = start;
        int endValue = end;
        if (start == Constants.ZERO) {
            startValue = start;
            endValue = end - 1;
        } else {
            startValue = start - 1;
            endValue = end - 1;
        }
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyQualifier.class);
        cfpDynamicQuery.setLimit(startValue, endValue);
        final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.COMPANY_QUALIFIER_SID));
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.COMPANY_QUALIFIER_NAME));
        cfpDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projectionList));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.COMPANY_QUALIFIER_NAME));
        if(filter != null){
        cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.COMPANY_QUALIFIER_NAME, filterText));
        }
        qualifierList = dao.getCompanyCrtQualifierNameList(cfpDynamicQuery);
        
        HelperDTO dto;
        if (start == Constants.ZERO) {
            dto = new HelperDTO();
            dto.setDescription(ConstantsUtils.SELECT_ONE);
            list.add(dto);
        }
        for (final Iterator<Object[]> iterator = qualifierList.iterator(); iterator.hasNext();) {
            final Object[] value = iterator.next();
            dto = new HelperDTO();
            dto.setId(value[0] == null ? 0 : Integer.valueOf(value[0].toString()));
            dto.setDescription(value[1] == null ? StringUtils.EMPTY : value[1].toString());
            list.add(dto);
        }
        if (editListFlag) {
            filterText = filterText.replace("*", StringUtils.EMPTY);
            filterText = filterText.replace(ConstantsUtils.PERCENCTAGE, StringUtils.EMPTY);
            filterText = filterText.toUpperCase(Locale.ENGLISH);
            if (!StringUtils.EMPTY.equals(filterText) && Constants.EDIT_LIST.startsWith(filterText)) {
                dto = new HelperDTO();
                dto.setDescription(UIUtils.EDIT_LIST);
                list.add(dto);
            }
            if (StringUtils.EMPTY.equals(filterText) && companyQualifierNameCount != 0 && companyQualifierNameCount == end - NumericConstants.TWO) {
                dto = new HelperDTO();
                dto.setDescription(UIUtils.EDIT_LIST);
                list.add(dto);
            }
        }
        LOGGER.debug("Ending getLazyCompanyQualifierNameResults method : returning CompanyQualifier size :" + list.size());
        return list;
    }

    public Udcs setUdc(ErrorfulFieldGroup companyMasterForm, int companyUdc) throws SystemException, PortalException {
        Udcs companyUdcs;
        if (companyUdc == 0) {
            companyUdcs = UdcsLocalServiceUtil.createUdcs(0);
        } else {
            companyUdcs = dao.getUDCS(companyUdc);
        }
        companyUdcs.setUdc1(Integer.parseInt(companyMasterForm.getField(ConstantsUtils.UDC1).getValue() == null ? StringUtils.EMPTY : companyMasterForm.getField(ConstantsUtils.UDC1).getValue().toString()));
        companyUdcs.setUdc2(Integer.parseInt(companyMasterForm.getField(ConstantsUtils.UDC2).getValue() == null ? StringUtils.EMPTY : companyMasterForm.getField(ConstantsUtils.UDC2).getValue().toString()));
        companyUdcs.setUdc3(Integer.parseInt(companyMasterForm.getField(ConstantsUtils.UDC3).getValue() == null ? StringUtils.EMPTY : companyMasterForm.getField(ConstantsUtils.UDC3).getValue().toString()));
        companyUdcs.setUdc4(Integer.parseInt(companyMasterForm.getField(ConstantsUtils.UDC4).getValue() == null ? StringUtils.EMPTY : companyMasterForm.getField(ConstantsUtils.UDC4).getValue().toString()));
        companyUdcs.setUdc5(Integer.parseInt(companyMasterForm.getField(ConstantsUtils.UDC5).getValue() == null ? StringUtils.EMPTY : companyMasterForm.getField(ConstantsUtils.UDC5).getValue().toString()));
        companyUdcs.setUdc6(Integer.parseInt(companyMasterForm.getField(ConstantsUtils.UDC6).getValue() == null ? StringUtils.EMPTY : companyMasterForm.getField(ConstantsUtils.UDC6).getValue().toString()));
        if (companyUdc == 0) {
            return dao.saveUdcs(companyUdcs);
        } else {
            return dao.updateUdcs(companyUdcs);
        }
    }

    public Map<Integer, String> getCodeDescription(final String listName) throws PortalException, SystemException {
        Map<Integer, String> helperTableMap = new HashMap<Integer, String>();
        final List<HelperTable> list = dao.getHelperTableDetailsByListName(listName);
        for (HelperTable helperTable : list) {
            helperTableMap.put(helperTable.getHelperTableSid(), helperTable.getDescription());
        }
        return helperTableMap;
    }

    public String getDescription(int sysId) {
        try {
            final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
            dynamicQuery.add(RestrictionsFactoryUtil.eq("helperTableSid", sysId));
            String descr = StringUtils.EMPTY;
            List<HelperTable> list1 = UdcsLocalServiceUtil.dynamicQuery(dynamicQuery);
            for (int j = 0; j < list1.size(); j++) {
                descr = list1.get(0).getDescription();
                return descr;
            }
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(CompanySearchLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void sortBy(final String columnName, final boolean asc, final List<SearchCompanyForm> searchList) {
        if (asc) {
            if (ConstantsUtils.COMPANY_ID.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyIdAsc);
            } else if (ConstantsUtils.COMPANY_NO.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyNoAsc);
            } else if (ConstantsUtils.COMPANY_NAME.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyNameAsc);
            } else if (ConstantsUtils.COMPANY_STATUS.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyStatusAsc);
            } else if (ConstantsUtils.COM_TYPE.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyTypeAsc);
            } else if (ConstantsUtils.IDENTIFIER_TYPE_DESC.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyQualifierNameAsc);
            } else if (ConstantsUtils.IDENTIFIER.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyIdentifierAsc);
            } else if (ConstantsUtils.COMPANY_START_DATE.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyStartDateAsc);
            } else if (ConstantsUtils.COMPANY_END_DATE.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyEndDateAsc);
            } else if (ConstantsUtils.TRADE_CLASS.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyTradeClassAsc);
            } else if (ConstantsUtils.TRADE_CLASS_START_DATE.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyTcStartDateAsc);
            } else if (ConstantsUtils.TRADE_CLASS_END_DATE.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyTcEndDateAsc);
            } else if (ConstantsUtils.LIVES.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyLivesAsc);
            } else if (ConstantsUtils.COMPANY_GROUP.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyGroupAsc);
            } else if (ConstantsUtils.COMPANY_CATEGORY.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyCategoryAsc);
            } else if (ConstantsUtils.ORGANIZATION_KEY.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.organisationAsc);
            } else if (ConstantsUtils.FINANCIAL_SYSTEM.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.financialAsc);
            } else if (ConstantsUtils.PARENT_COMPANY_NO.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.parentNoAsc);
            } else if (ConstantsUtils.PARENT_START_DATE.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.parentStartDateAsc);
            } else if (ConstantsUtils.PARENT_END_DATE.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.parentEndDateAsc);
            } else if (ConstantsUtils.PRIOR_PARENT_COMPANY_NO.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.priorParentNoAsc);
            } else if (ConstantsUtils.PRIOR_PARENT_START_DATE.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.priorParentStartDateAsc);
            } else if (ConstantsUtils.REGION_CODE.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.regionCodeAsc);
            } else if (ConstantsUtils.UDC1.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.udc1Asc);
            } else if (ConstantsUtils.UDC2.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.udc2Asc);
            } else if (ConstantsUtils.UDC3.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.udc3Asc);
            } else if (ConstantsUtils.UDC4.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.udc4Asc);
            } else if (ConstantsUtils.UDC5.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.udc5Asc);
            } else if (ConstantsUtils.UDC6.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.udc6Asc);
            } else if (ConstantsUtils.ADDRESS1.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyAddess1Asc);
            } else if (ConstantsUtils.ADDRESS2.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyAddess2Asc);
            } else if (ConstantsUtils.ZIPCODE.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.zipCodeAsc);
            } else if (ConstantsUtils.CITY.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.cityAsc);
            } else if (ConstantsUtils.STATE.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.stateAsc);
            } else if (ConstantsUtils.COUNTRY.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.countryAsc);
            }
        } else {
            if (ConstantsUtils.COMPANY_ID.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyIdDsc);
            } else if (ConstantsUtils.COMPANY_NO.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyNoDsc);
            } else if (ConstantsUtils.COMPANY_NAME.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyNameDsc);
            } else if (ConstantsUtils.COMPANY_STATUS.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyStatusDsc);
            } else if (ConstantsUtils.COM_TYPE.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyTypeDsc);
            } else if (ConstantsUtils.IDENTIFIER_TYPE_DESC.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyQualifierNameDsc);
            } else if (ConstantsUtils.IDENTIFIER.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyIdentiferDsc);
            } else if (ConstantsUtils.COMPANY_START_DATE.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyStartDateDsc);
            } else if (ConstantsUtils.COMPANY_END_DATE.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyEndDateDsc);
            } else if (ConstantsUtils.TRADE_CLASS.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyTradeClassDsc);
            } else if (ConstantsUtils.TRADE_CLASS_START_DATE.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyTcStartDateDsc);
            } else if (ConstantsUtils.TRADE_CLASS_END_DATE.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyTcEndDateDsc);
            } else if (ConstantsUtils.LIVES.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyLivesDsc);
            } else if (ConstantsUtils.COMPANY_GROUP.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyGroupDsc);
            } else if (ConstantsUtils.COMPANY_CATEGORY.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyCategoryDsc);
            } else if (ConstantsUtils.ORGANIZATION_KEY.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.organisationDsc);
            } else if (ConstantsUtils.FINANCIAL_SYSTEM.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.financialDsc);
            } else if (ConstantsUtils.PARENT_COMPANY_NO.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.parentNoDsc);
            } else if (ConstantsUtils.PARENT_START_DATE.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.parentStartDateDsc);
            } else if (ConstantsUtils.PARENT_END_DATE.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.parentEndDateDsc);
            } else if (ConstantsUtils.PRIOR_PARENT_COMPANY_NO.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.priorParentNoDsc);
            } else if (ConstantsUtils.PRIOR_PARENT_START_DATE.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.priorParentStartDateDsc);
            } else if (ConstantsUtils.REGION_CODE.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.regionCodeDsc);
            } else if (ConstantsUtils.UDC1.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.udc1Dsc);
            } else if (ConstantsUtils.UDC2.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.udc2Dsc);
            } else if (ConstantsUtils.UDC3.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.udc3Dsc);
            } else if (ConstantsUtils.UDC4.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.udc4Dsc);
            } else if (ConstantsUtils.UDC5.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.udc5Dsc);
            } else if (ConstantsUtils.UDC6.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.udc6Dsc);
            } else if (ConstantsUtils.ADDRESS1.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyAddess1Dsc);
            } else if (ConstantsUtils.ADDRESS2.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.companyAddess2Dsc);
            } else if (ConstantsUtils.ZIPCODE.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.zipCodeDsc);
            } else if (ConstantsUtils.CITY.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.cityDsc);
            } else if (ConstantsUtils.STATE.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.stateDsc);
            } else if (ConstantsUtils.COUNTRY.equals(columnName)) {
                Collections.sort(searchList, SearchCompanyForm.countryDsc);
            }
        }
    }

    public static Map<Integer, String> getCompanyQualifiers() throws PortalException, SystemException {

        Map<Integer, String> companyQualifierMap = new HashMap<Integer, String>();

        final DynamicQuery qualifierDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyQualifier.class);
        final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.COMPANY_QUALIFIER_SID));
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.COMPANY_QUALIFIER_NAME));
        qualifierDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projectionList));
        qualifierDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.COMPANY_QUALIFIER_NAME));

        List<Object[]> qualifierArray = dao.getCompanyCrtQualifierNameList(qualifierDynamicQuery);



        for (Object[] qualifier : qualifierArray) {
            companyQualifierMap.put((Integer) qualifier[0], String.valueOf(qualifier[1]));
        }
        return companyQualifierMap;
    }

    public void deleteNotesTabAttachment(final int systemId) {
        try {
            final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MasterDataFiles.class);
            dynamicQuery.add(RestrictionsFactoryUtil.eq("masterTableSid", systemId));
            dynamicQuery.add(RestrictionsFactoryUtil.like("masterTableName", ConstantsUtils.COMPANY_MASTER));
            final List<MasterDataFiles> masterDataFiles = MasterDataFilesLocalServiceUtil.dynamicQuery(dynamicQuery);
            if (!masterDataFiles.isEmpty()) {
                for (MasterDataFiles masterDataFile : masterDataFiles) {
                    MasterDataFilesLocalServiceUtil.deleteMasterDataFiles(masterDataFile.getMasterDataFilesSid());
                }
            }
        } catch (SystemException e) {
            LOGGER.error(e);
        } catch (PortalException e) {
            LOGGER.error(e);
        }
    }
    
    /**
     * 
     * @param searchCompanyForm
     * @param start
     * @param end
     * @param sortByColumns
     * @param filterSet
     * @return 
     * @throws SystemException
     * @throws PortalException
     * @throws Exception 
     */
    public Object getResultsForCompany(final ErrorfulFieldGroup searchCompanyForm, final int start, final int end, final List<SortByColumn> sortByColumns, final Set<Filter> filterSet,boolean isCount) throws SystemException,
            PortalException,
            ParseException {
        LOGGER.debug("searchCompany p1 : SearchCompanyForm p2:" + start + " p3:" + end );
        FiledNameUtils.loadDbColumnName();
        final SearchResultsDTO searchForm = new SearchResultsDTO();
        Map<String, Object> parameters = new HashMap<String, Object>();
        String itemIdentifier = StringUtils.EMPTY;
        int qualifierId = 0;
        String companyId = StringUtils.EMPTY;
        String companyNo = StringUtils.EMPTY;
        String companyName = StringUtils.EMPTY;
        String companyType = StringUtils.EMPTY;
        String companyCategory = StringUtils.EMPTY;
        String companyGroup = StringUtils.EMPTY;
        String tradeClass = StringUtils.EMPTY;
        String companyStatus = StringUtils.EMPTY;
        String columnName = StringUtils.EMPTY;
        String dbColumnName = StringUtils.EMPTY;
        
        final SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
        
        
        
        
        
        if (searchCompanyForm.getField(ConstantsUtils.TEXT1).getValue().toString() != null) {
            companyId = searchCompanyForm.getField(ConstantsUtils.TEXT1).getValue().toString().trim();
            companyId = companyId.replace(CommonUtils.PERCENT, CommonUtils.SLASH_PERCENT);
            companyId = companyId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        
        if (searchCompanyForm.getField(ConstantsUtils.TEXT2).getValue().toString() != null) {

            companyNo = searchCompanyForm.getField(ConstantsUtils.TEXT2).getValue().toString().trim();
            companyNo = companyNo.replace(CommonUtils.PERCENT, CommonUtils.SLASH_PERCENT);
            companyNo = companyNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        if (searchCompanyForm.getField(ConstantsUtils.TEXT3).getValue().toString() != null) {
            companyName = searchCompanyForm.getField(ConstantsUtils.TEXT3).getValue().toString().trim();
            companyName = companyName.replace(CommonUtils.PERCENT, CommonUtils.SLASH_PERCENT);
            companyName = companyName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        if (searchCompanyForm.getField(ConstantsUtils.TEXT8) != null && !String.valueOf(searchCompanyForm.getField(ConstantsUtils.TEXT8).getValue()).equals(ConstantsUtils.NULL)) {
            itemIdentifier = searchCompanyForm.getField(ConstantsUtils.TEXT8).getValue().toString().trim();
        }
        if (searchCompanyForm.getField(ConstantsUtils.COMBO6) != null && !String.valueOf(searchCompanyForm.getField(ConstantsUtils.COMBO6).getValue()).equals(ConstantsUtils.NULL)&&!ConstantsUtils.SELECT_ONE.equals(String.valueOf(searchCompanyForm.getField(ConstantsUtils.COMBO6).getValue()))) {
            final HelperDTO helperDTO = (HelperDTO) searchCompanyForm.getField(ConstantsUtils.COMBO6).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

                qualifierId = helperDTO.getId();

            }
         }
        if (searchCompanyForm.getField(ConstantsUtils.COMBO2).getValue() != null&&!ConstantsUtils.SELECT_ONE.equals(String.valueOf(searchCompanyForm.getField(ConstantsUtils.COMBO2).getValue()))) {
            
               final HelperDTO helperDTO = (HelperDTO) searchCompanyForm.getField(ConstantsUtils.COMBO2).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

                companyType = String.valueOf(helperDTO.getId()) ;
            }
        }
        if (searchCompanyForm.getField(ConstantsUtils.COMBO1).getValue() != null&&!ConstantsUtils.SELECT_ONE.equals(String.valueOf(searchCompanyForm.getField(ConstantsUtils.COMBO1).getValue()))) {
            
                     final HelperDTO helperDTO = (HelperDTO) searchCompanyForm.getField(ConstantsUtils.COMBO1).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

                companyStatus =String.valueOf(helperDTO.getId());
            }
            
            
        }


        if (searchCompanyForm.getField(ConstantsUtils.COMBO3) != null  && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(searchCompanyForm.getField(ConstantsUtils.COMBO3).getValue()))) {
            
            
                     final HelperDTO helperDTO = (HelperDTO) searchCompanyForm.getField(ConstantsUtils.COMBO3).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

                companyCategory = String.valueOf(helperDTO.getId());
            }
            
        }

        if (searchCompanyForm.getField(ConstantsUtils.COMBO4) != null  && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(searchCompanyForm.getField(ConstantsUtils.COMBO4).getValue()))) {
           
                     final HelperDTO helperDTO = (HelperDTO) searchCompanyForm.getField(ConstantsUtils.COMBO4).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

                companyGroup =String.valueOf(helperDTO.getId());
            }
            
            
        }
        if (searchCompanyForm.getField(ConstantsUtils.COMBO5) != null &&!ConstantsUtils.SELECT_ONE.equals(String.valueOf(searchCompanyForm.getField(ConstantsUtils.COMBO5).getValue()))) {
           
                     final HelperDTO helperDTO = (HelperDTO) searchCompanyForm.getField(ConstantsUtils.COMBO5).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

                tradeClass = String.valueOf(helperDTO.getId());
            }
        
        }



        int flag = 0;

        if (qualifierId != Constants.ZERO) {
            searchForm.setQualifierFlag(true);
            if (StringUtils.isNotBlank(itemIdentifier)) {
                searchForm.setIdentifierFlag(true);
                itemIdentifier = itemIdentifier.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            }
            flag = 1;
        }
        boolean asc = false;
     if(sortByColumns != null){
        for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
            final SortByColumn sortByColumn = (SortByColumn) iterator.next();

            columnName = sortByColumn.getName();
            dbColumnName = FiledNameUtils.getDBColumnName(columnName);
            if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                asc = false;
            } else {
                asc = true;
            }
        }
    }

        if (filterSet != null) {

            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    if (ConstantsUtils.COMPANY_STATUS.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.COMPANY_STATUS, Integer.valueOf(stringFilter.getFilterString()));
                        
                    } else if (ConstantsUtils.COM_TYPE.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.COM_TYPE, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.TRADE_CLASS.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.TRADE_CLASS, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.COMPANY_GROUP.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.COMPANY_GROUP, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.COMPANY_CATEGORY.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.COMPANY_CATEGORY, Integer.valueOf(stringFilter.getFilterString()));
                    }else if (ConstantsUtils.ORGANIZATION_KEY.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.ORG_KEY, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC1.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC1, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC2.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC2, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC3.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC3, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC4.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC4, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC5.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC5, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.UDC6.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.UDC6, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.STATE.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.STATE, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.COUNTRY.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.COUNTRY, Integer.valueOf(stringFilter.getFilterString()));
                    }  else if (ConstantsUtils.IDENTIFIER_STATUS.equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put(ConstantsUtils.IDENTIFIER_STATUS, Integer.valueOf(stringFilter.getFilterString()));
                    } else {
                        String filterString = CommonUtil.buildFilterCriteria(stringFilter.getFilterString());
                        parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId(), filterString);
                    }
                } 
                else if (filter instanceof Between) { 
                    Between betweenFilter = (Between) filter; 
                    Date startValue = (Date) betweenFilter.getStartValue(); 
                    Date endValue = (Date) betweenFilter.getEndValue(); 
                    parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.FROM, String.valueOf(dateFormat.format(startValue)));
                    parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.TO, String.valueOf(dateFormat.format(endValue))); 
                }
                else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    if (operation.EQUAL.toString().equals(operation.name())) {
                        if (((Integer) stringFilter.getValue()) == 0) {
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~=", String.valueOf(stringFilter.getValue()) + "--" + "0");
                        } else {
                            int val = (Integer) stringFilter.getValue();
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~=", String.valueOf(val) + "--" + "=");
                        }
                    }
                    if (operation.GREATER.toString().equals(operation.name())) {
                        int val = (Integer) stringFilter.getValue();
                        if (val < 0) {
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~>", String.valueOf(val) + "--" + ">0");
                        } else {
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~>", String.valueOf(val) + "--" + ">");
                        }
                    }
                    if (operation.LESS.toString().equals(operation.name())) {
                        int val = (Integer) stringFilter.getValue();
                        if (val > 0) {
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~<", String.valueOf(val) + "--" + "<0");
                        } else {
                            parameters.put(ConstantsUtils.FILTER + stringFilter.getPropertyId() + "~<", String.valueOf(val) + "--" + "<");
                        }
                    }
                     if (stringFilter.getValue() instanceof Date) {
                    Date value = (Date) stringFilter.getValue();
                    if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                        parameters.put(stringFilter.getPropertyId() + ConstantsUtils.FROM, String.valueOf(dateFormat.format(value)));
                    } else {
                        parameters.put(stringFilter.getPropertyId() + ConstantsUtils.TO, String.valueOf(dateFormat.format(value)));
                    }
                     }
                } else if (filter instanceof And) {
                    And stringFilter = (And) filter;
                    Collection<Container.Filter> value = stringFilter.getFilters();
                    for (Container.Filter filter1 : value) {
                        if (filter1 instanceof Compare.Less) {

                            Compare.Less less = (Compare.Less) filter1;
                            parameters.put(ConstantsUtils.FILTER + less.getPropertyId() + "~<<", String.valueOf(less.getValue()) + "--" + "<<");
                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;
                            parameters.put(ConstantsUtils.FILTER + greater.getPropertyId() + "~>>", String.valueOf(greater.getValue()) + "--" + ">>");
                        }
                    }
                }
            }
        }
        
        
        Object object = new Object();
        if (flag == Constants.ZERO) {
            String sql = getQueryForCompanyMaster(companyId, companyNo, companyName, companyStatus, companyType, companyCategory, companyGroup, tradeClass, 0, null, dbColumnName, asc, start, end,parameters,isCount);                    
            List resultList = (List) CompanyMasterLocalServiceUtil.executeSelectQuery(sql, null, null);                    
            if (isCount) {
                object = (Integer) resultList.get(0);                
            } else {
                object = getCustomizedSearchFormFromObject(resultList);
            }            
        }
        if (flag == Constants.ONE) {
            String sql = getQueryForCompanyMaster(companyId, companyNo, companyName, companyStatus, companyType, companyCategory, companyGroup, tradeClass, qualifierId, itemIdentifier, dbColumnName, asc, start, end,parameters,isCount);                        
            List resultList = (List) CompanyMasterLocalServiceUtil.executeSelectQuery(sql, null, null);                    
            if (isCount) {
                object = (Integer) resultList.get(0);                
            } else {
                object = getCustomizedSearchFormFromObject(resultList);
            }
        }

        return object;
    }
    
     public String getQueryForCompanyMaster(String companyId, String companyNo,
            String companyName, String companyStatus, String companyType, String companyCategory, String companyGroup,
            String tradeClass, int identifierType, String identifier, String orderByColumn, Boolean sortOrder, Object index, Object next,Map<String, Object> parameters,boolean isCount) {
            StringBuilder sql;
            String andOperator = StringUtils.EMPTY;
            if (identifierType == 0 && identifier == null) {
                if(isCount){
                    sql = new StringBuilder(CustomSQLUtil.get("findCompanyMasterWithoutIdentifierCount"));
                }else{
                sql = new StringBuilder(CustomSQLUtil.get("com.companymaster.service.persistence.CompanyMasterFinder.findCompanyMasterWithoutIdentifier"));
                }
                sql.append(" where ");
            } else {
                if(isCount){
                    sql = new StringBuilder(CustomSQLUtil.get("findCompanyMasterWithIdentifierCount"));
                }else{
                sql = new StringBuilder(CustomSQLUtil.get("com.companymaster.service.persistence.CompanyMasterFinder.findCompanyMasterWithIdentifier"));
                }
                sql.append(" where ");
                if (identifierType != 0) {
                    sql.append(" crti.COMPANY_QUALIFIER_SID=").append(identifierType).append(" ");
                    andOperator = ConstantsUtils.AND;
                }
                if (identifier.length() != 0) {
                    identifier = identifier.replace('*', '%');
                    sql.append(andOperator).append(" crti.COMPANY_IDENTIFIER_VALUE like '").append(identifier).append("' ");
                    andOperator = ConstantsUtils.AND;
                }

            }

            if (companyId.length() != 0) {
                if (companyId.contains(CommonUtils.SLASH_PERCENT)) {
                    sql.append(andOperator).append(ConstantsUtils.QUERY_COMP_ID).append(companyId).append("' ").append(CommonUtils.ESCAPE_SLASH);
                    andOperator = ConstantsUtils.AND;
                } else {
                    sql.append(andOperator).append(ConstantsUtils.QUERY_COMP_ID).append(companyId).append("' ");
                    andOperator = ConstantsUtils.AND;
                }
            }

            if (companyNo.length() != 0) {
                if (companyNo.contains(CommonUtils.SLASH_PERCENT)) {
                    sql.append(andOperator).append(ConstantsUtils.QUERY_COMPANY_NO).append(companyNo).append("' ").append(CommonUtils.ESCAPE_SLASH);
                    andOperator = ConstantsUtils.AND;
                } else {
                    sql.append(andOperator).append(ConstantsUtils.QUERY_COMPANY_NO).append(companyNo).append("' ");
                    andOperator = ConstantsUtils.AND;

                }
            }

            if (companyName.length() != 0) {
                if (companyName.contains(CommonUtils.SLASH_PERCENT)) {
                    sql.append(andOperator).append(ConstantsUtils.QUERY_COMPANY_NAME).append(companyName).append("' ").append(CommonUtils.ESCAPE_SLASH);
                    andOperator = ConstantsUtils.AND;
                } else {
                    sql.append(andOperator).append(ConstantsUtils.QUERY_COMPANY_NAME).append(companyName).append("' ");
                    andOperator = ConstantsUtils.AND;
                }
            }

            if (!"0".equals(companyStatus) && StringUtils.isNotBlank(companyStatus)) {
                sql.append(andOperator).append(" cm.COMPANY_STATUS='").append(companyStatus).append("' ");
                andOperator = ConstantsUtils.AND;

            }

            if (!"0".equals(companyType) && StringUtils.isNotBlank(companyType)) {
                sql.append(andOperator).append(" cm.COMPANY_TYPE='").append(companyType).append("' ");
                andOperator = ConstantsUtils.AND;

            }
            if (!"0".equals(companyCategory) && StringUtils.isNotBlank(companyCategory)) {
                sql.append(andOperator).append(" cm.COMPANY_CATEGORY='").append(companyCategory).append("' ");
                andOperator = ConstantsUtils.AND;

            }
            if (!"0".equals(companyGroup) && StringUtils.isNotBlank(companyGroup)) {
                sql.append(andOperator).append(" cm.COMPANY_GROUP='").append(companyGroup).append("' ");
                andOperator = ConstantsUtils.AND;

            }
            if (!"0".equals(tradeClass) && StringUtils.isNotBlank(tradeClass)) {
                sql.append(andOperator).append(" trade.COMPANY_TRADE_CLASS='").append(tradeClass).append("' ");
                andOperator = ConstantsUtils.AND;

            }

            sql.append(andOperator).append(" cm.INBOUND_STATUS <> 'D' ");
            andOperator = ConstantsUtils.AND;
            
            if (parameters.get(ConstantsUtils.QUERY_START_DATE_FORM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.QUERY_START_DATE_FORM)))) { 
                String startDate = parameters.get(ConstantsUtils.QUERY_START_DATE_FORM).toString();
                sql.append(andOperator).append(" cm.COMPANY_START_DATE >= '").append(startDate).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.QUERY_START_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.QUERY_START_DATE_TO)))) {
                String endDate = parameters.get(ConstantsUtils.QUERY_START_DATE_TO).toString();
                sql.append(andOperator).append(" cm.COMPANY_START_DATE <= '").append(endDate).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.CREATED_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.CREATED_DATE_FROM)))) { 
                String startDate = parameters.get(ConstantsUtils.CREATED_DATE_FROM).toString();
                sql.append(andOperator).append(" crti.CREATED_DATE >= '").append(startDate).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.CREATED_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.CREATED_DATE_TO)))) {
                String endDate = parameters.get(ConstantsUtils.CREATED_DATE_TO).toString();
                sql.append(andOperator).append(" crti.CREATED_DATE <= '").append(endDate).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.QUERY_END_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.QUERY_END_DATE_FROM)))) { 
                String startDate = parameters.get(ConstantsUtils.QUERY_END_DATE_FROM).toString();
                sql.append(andOperator).append(" cm.COMPANY_END_DATE >= '").append(startDate).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.QUERY_END_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.QUERY_END_DATE_TO)))) {
                String endDate = parameters.get(ConstantsUtils.QUERY_END_DATE_TO).toString();
                sql.append(andOperator).append(" cm.COMPANY_END_DATE <= '").append(endDate).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.QUERY_TRADE_START_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.QUERY_TRADE_START_DATE_FROM)))) { 
                String startDate = parameters.get(ConstantsUtils.QUERY_TRADE_START_DATE_FROM).toString();
                sql.append(andOperator).append(" trade.TRADE_CLASS_START_DATE >= '").append(startDate).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.QUERY_TRADE_START_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.QUERY_TRADE_START_DATE_TO)))) {
                String endDate = parameters.get(ConstantsUtils.QUERY_TRADE_START_DATE_TO).toString();
                sql.append(andOperator).append(" trade.TRADE_CLASS_START_DATE <= '").append(endDate).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.QUERY_TRADE_END_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.QUERY_TRADE_END_DATE_FROM)))) { 
                String startDate = parameters.get(ConstantsUtils.QUERY_TRADE_END_DATE_FROM).toString();
                sql.append(andOperator).append(" trade.TRADE_CLASS_END_DATE >= '").append(startDate).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.QUERY_TRADE_END_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.QUERY_TRADE_END_DATE_TO)))) {
                String endDate = parameters.get(ConstantsUtils.QUERY_TRADE_END_DATE_TO).toString();
                sql.append(andOperator).append(" trade.TRADE_CLASS_END_DATE <= '").append(endDate).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.QUERY_S_DATE_FORM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.QUERY_S_DATE_FORM)))) { 
                String startDate = parameters.get(ConstantsUtils.QUERY_S_DATE_FORM).toString();
                sql.append(andOperator).append(" parent.PARENT_START_DATE >= '").append(startDate).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.QUERY_S_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.QUERY_S_DATE_TO)))) {
                String endDate = parameters.get(ConstantsUtils.QUERY_S_DATE_TO).toString();
                sql.append(andOperator).append(" parent.PARENT_START_DATE <= '").append(endDate).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.QUERY_PARENT_E_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.QUERY_PARENT_E_DATE_FROM)))) { 
                String startDate = parameters.get(ConstantsUtils.QUERY_PARENT_E_DATE_FROM).toString();
                sql.append(andOperator).append(" parent.PARENT_END_DATE >= '").append(startDate).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.QUERY_PARENT_E_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.QUERY_PARENT_E_DATE_TO)))) {
                String endDate = parameters.get(ConstantsUtils.QUERY_PARENT_E_DATE_TO).toString();
                sql.append(andOperator).append(" parent.PARENT_END_DATE <= '").append(endDate).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.QUERY_PRIOR_PARENT_S_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.QUERY_PRIOR_PARENT_S_DATE_FROM)))) { 
                String startDate = parameters.get(ConstantsUtils.QUERY_PRIOR_PARENT_S_DATE_FROM).toString();
                sql.append(andOperator).append(" parent.PRIOR_PARENT_START_DATE >= '").append(startDate).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.QUERY_PRIOR_PARENT_S_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.QUERY_PRIOR_PARENT_S_DATE_TO)))) {
                String endDate = parameters.get(ConstantsUtils.QUERY_PRIOR_PARENT_S_DATE_TO).toString();
                sql.append(andOperator).append(" parent.PRIOR_PARENT_START_DATE <= '").append(endDate).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get("filter~companyId") != null) {
                String company= parameters.get("filter~companyId").toString();
                sql.append(andOperator).append(ConstantsUtils.QUERY_COMP_ID).append(company).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get("filter~parentCompanyNo") != null) {
                String compNo= parameters.get("filter~parentCompanyNo").toString();
                sql.append(andOperator).append(" comp.COMPANY_NO like '").append(compNo).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get("filter~companyNo") != null) {
                String compNo= parameters.get("filter~companyNo").toString();
                sql.append(andOperator).append(ConstantsUtils.QUERY_COMPANY_NO).append(compNo).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get("filter~companyName") != null) {
                String compName= parameters.get("filter~companyName").toString();
                sql.append(andOperator).append(ConstantsUtils.QUERY_COMPANY_NAME).append(compName).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get("filter~companySystemId") != null) {
                String sysId = parameters.get("filter~companySystemId").toString();
                sql.append(andOperator).append(" cm.COMPANY_MASTER_SID like '").append(sysId).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get("filter~priorParentCompanyNo") != null) {
                String priorParentCompanyNo = parameters.get("filter~priorParentCompanyNo").toString();
                sql.append(andOperator).append(" comp1.COMPANY_NO like '").append(priorParentCompanyNo).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.COMPANY_STATUS) != null) {
                String compStatus= parameters.get(ConstantsUtils.COMPANY_STATUS).toString();
                sql.append(andOperator).append(" cm.COMPANY_STATUS = '").append(compStatus).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.COM_TYPE) != null) {
                String compType= parameters.get(ConstantsUtils.COM_TYPE).toString();
                sql.append(andOperator).append(" cm.COMPANY_TYPE like '").append(compType).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get("filter~companyStartDate") != null) {
                String compStart= parameters.get("filter~companyStartDate").toString();
                sql.append(andOperator).append(" cm.COMPANY_START_DATE like '").append(compStart).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.TRADE_CLASS) != null) {
                String trade= parameters.get(ConstantsUtils.TRADE_CLASS).toString();
                sql.append(andOperator).append(" trade.COMPANY_TRADE_CLASS like '").append(trade).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get("filter~lives") != null) {
                String lives= parameters.get("filter~lives").toString();
                sql.append(andOperator).append(" cm.LIVES like '").append(lives).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get("filter~identifier") != null) {
                String iden = parameters.get("filter~identifier").toString();
                sql.append(andOperator).append(" crti.COMPANY_IDENTIFIER_VALUE like '").append(iden).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.COMPANY_GROUP) != null) {
                String group= parameters.get(ConstantsUtils.COMPANY_GROUP).toString();
                sql.append(andOperator).append(" cm.COMPANY_GROUP like '").append(group).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.COMPANY_CATEGORY) != null) {
                String category= parameters.get(ConstantsUtils.COMPANY_CATEGORY).toString();
                sql.append(andOperator).append(" cm.COMPANY_CATEGORY like '").append(category).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.ORG_KEY) != null) {
                String orgKey= parameters.get(ConstantsUtils.ORG_KEY).toString();
                sql.append(andOperator).append(" cm.ORGANIZATION_KEY like '").append(orgKey).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get("filter~identifierTypeDesc") != null) {
                String qualifier= parameters.get("filter~identifierTypeDesc").toString();
                sql.append(andOperator).append(ConstantsUtils.QUERY_COMP_QUALIFIER).append(qualifier).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            
            if (parameters.get("filter~identifierType") != null) {
                String qualifier= parameters.get("filter~identifierType").toString();
                sql.append(andOperator).append(ConstantsUtils.QUERY_COMP_QUALIFIER).append(qualifier).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get("filter~companyQualifierName") != null) {
                String qualifier= parameters.get("filter~companyQualifierName").toString();
                sql.append(andOperator).append(ConstantsUtils.QUERY_COMP_QUALIFIER).append(qualifier).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            
            if (parameters.get("filter~financialSystem") != null) {
                String company= parameters.get("filter~financialSystem").toString();
                sql.append(andOperator).append(" cm.FINANCIAL_SYSTEM like '").append(company).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get("filter~regionCode") != null) {
                String code= parameters.get("filter~regionCode").toString();
                sql.append(andOperator).append(" cm.REGION_CODE like '").append(code).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.UDC1) != null) {
                String udc1= parameters.get(ConstantsUtils.UDC1).toString();
                sql.append(andOperator).append(" udc.UDC1 like '").append(udc1).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.UDC2) != null) {
                String udc2= parameters.get(ConstantsUtils.UDC2).toString();
                sql.append(andOperator).append(" udc.UDC2 like '").append(udc2).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.UDC3) != null) {
                String udc3= parameters.get(ConstantsUtils.UDC3).toString();
                sql.append(andOperator).append(" udc.UDC3 like '").append(udc3).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.UDC4) != null) {
                String udc4= parameters.get(ConstantsUtils.UDC4).toString();
                sql.append(andOperator).append(" udc.UDC4 like '").append(udc4).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.UDC5) != null) {
                String udc5= parameters.get(ConstantsUtils.UDC5).toString();
                sql.append(andOperator).append(" udc.UDC5 like '").append(udc5).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.UDC6) != null) {
                String udc6= parameters.get(ConstantsUtils.UDC6).toString();
                sql.append(andOperator).append(" udc.UDC6 like '").append(udc6).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get("filter~address1") != null) {
                String address1= parameters.get("filter~address1").toString();
                sql.append(andOperator).append(" cm.ADDRESS1 like '").append(address1).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get("filter~address2") != null) {
                String address2= parameters.get("filter~address2").toString();
                sql.append(andOperator).append(" cm.ADDRESS2 like '").append(address2).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get("filter~zipCode") != null) {
                String zipCode= parameters.get("filter~zipCode").toString();
                sql.append(andOperator).append(" cm.ZIP_CODE like '").append(zipCode).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get("filter~city") != null) {
                String city= parameters.get("filter~city").toString();
                sql.append(andOperator).append(" cm.CITY like '").append(city).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.STATE) != null) {
                String state= parameters.get(ConstantsUtils.STATE).toString();
                sql.append(andOperator).append(" cm.STATE like '").append(state).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.COUNTRY) != null) {
                String country= parameters.get(ConstantsUtils.COUNTRY).toString();
                sql.append(andOperator).append(" cm.COUNTRY like '").append(country).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get(ConstantsUtils.IDENTIFIER_STATUS) != null) {
                String identifierStatus = parameters.get(ConstantsUtils.IDENTIFIER_STATUS).toString();
                sql.append(andOperator).append(" crti.IDENTIFIER_STATUS like '").append(identifierStatus).append("' ");
                andOperator = ConstantsUtils.AND;
            }
            if (parameters.get("filter~systemId~>") != null) {
                String value = parameters.get("filter~systemId~>").toString();
                String[] value1 = value.split("--");
                String companySid = value1[0];
                String operator = value1[1];
                if (operator.equals(">0")) {
                    sql.append(" AND (cm.COMPANY_MASTER_SID >'").append(companySid).append("' ").append(" or");
                    sql.append(" cm.COMPANY_MASTER_SID ='").append("0").append("' )");
                }
                if (operator.equals(">")) {
                    sql.append(" AND cm.COMPANY_MASTER_SID >'").append(companySid).append("' ");
                }
            }
            if (parameters.get("filter~systemId~<") != null) {
                String value = parameters.get("filter~systemId~<").toString();
                String[] value1 = value.split("--");
                String companySid = value1[0];
                String operator = value1[1];
                if (operator.equals("<0")) {
                    sql.append(" AND (cm.COMPANY_MASTER_SID <'").append(companySid).append("' ").append(" or");
                    sql.append(" cm.COMPANY_MASTER_SID ='").append("0").append("') ");
                }
                if (operator.equals("<")) {
                    sql.append(" AND cm.COMPANY_MASTER_SID <'").append(companySid).append("' ");
                }
            }
            if (parameters.get("filter~systemId~=") != null) {
                String value = parameters.get("filter~systemId~=").toString();
                String[] value1 = value.split("--");
                String companySid = value1[0];
                String operator = value1[1];
                if (operator.equals("0") || operator.equals("=")) {
                    sql.append(" AND cm.COMPANY_MASTER_SID ='").append(companySid).append("' ");
                }
            }
            if (parameters.get("filter~systemId~<<") != null || parameters.get("filter~systemId~>>") != null) {
                String lesser = parameters.get("filter~systemId~<<").toString();
                String greater = parameters.get("filter~systemId~>>").toString();
                String[] lesser1 = lesser.split("--");
                String[] greater1 = greater.split("--");
                String companySidLesser = lesser1[0];
                String companySidGreater = greater1[0];
                sql.append(" AND CM.COMPANY_MASTER_SID > '");
                sql.append(companySidGreater).append("'");
                sql.append(" AND CM.COMPANY_MASTER_SID < '");
                sql.append(companySidLesser).append("'");
            }
          



            if (!isCount) {

                if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
                    sql.append(" ORDER BY COMPANY_MASTER_SID").append((!sortOrder) ? " ASC " : " DESC ");
                } else {
                    sql.append("ORDER BY ").append(orderByColumn).append((!sortOrder) ? " ASC " : " DESC ");
                }

                sql.append(" " + "OFFSET ");
                sql.append(index);
                sql.append(" ROWS FETCH NEXT ");
                sql.append((Integer) next - (Integer) index);
                sql.append(" ROWS ONLY;");
            }
            
           return sql.toString();
        }

    public void insertToCPDetails(int systemId) {
        String query= CustomSQLUtil.get("company-CP").replace("?SID", String.valueOf(systemId));
        CompanyMasterLocalServiceUtil.executeUpdateQuery(query);  
    }
    
    public boolean checkDifferentQualifier(int companyIdentifierId, String companyIdentifier) {
        String query = "SELECT COMPANY_QUALIFIER_VALUE FROM DBO.COMPANY_QUALIFIER WHERE COMPANY_QUALIFIER_SID =" + companyIdentifierId;
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        String val = list == null || list.isEmpty() ? StringUtils.EMPTY : list.get(0).toString().trim();
        return val.isEmpty() ? false : !val.equals(companyIdentifier);        
    }
     
     
}
