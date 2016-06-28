package com.stpl.app.global.item.logic;

import com.stpl.app.NoSuchItemPricingQualifierException;
import com.stpl.app.NoSuchItemQualifierException;
import com.stpl.app.global.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.global.abstractsearch.util.ConstantUtil;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.company.dto.SearchCompanyForm;
import com.stpl.app.global.company.util.FiledNameUtils;
import com.stpl.app.global.dao.impl.CompanySearchLogicDAOImpl;
import com.stpl.app.global.dao.impl.ItemSearchLogicDAOImpl;
import com.stpl.app.global.dao.impl.StplSecurityDAOImpl;
import com.stpl.app.global.item.dto.ItemIrtIdentifierDTO;
import com.stpl.app.global.item.dto.ItemMasterDTO;
import com.stpl.app.global.item.dto.ItemPricingDTO;
import com.stpl.app.global.item.dto.SearchItemForm;
import static com.stpl.app.global.item.logic.ItemSearchLogic.columnName;
import static com.stpl.app.global.item.logic.ItemSearchLogic.loadColumnName;
import com.stpl.app.global.item.util.CommonUtils;
import com.stpl.app.global.item.util.UIUtils;
import com.stpl.app.model.BrandMaster;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.IfpContractDetails;
import com.stpl.app.model.ItemIdentifier;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.model.ItemPricing;
import com.stpl.app.model.ItemPricingQualifier;
import com.stpl.app.model.ItemQualifier;
import com.stpl.app.model.MasterDataFiles;
import com.stpl.app.model.Udcs;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.IfpContractDetailsLocalServiceUtil;
import com.stpl.app.service.ItemIdentifierLocalServiceUtil;
import com.stpl.app.service.ItemMasterLocalServiceUtil;
import com.stpl.app.service.ItemPricingLocalServiceUtil;
import com.stpl.app.service.ItemPricingQualifierLocalServiceUtil;
import com.stpl.app.service.ItemQualifierLocalServiceUtil;
import com.stpl.app.service.MasterDataFilesLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.app.service.UdcsLocalServiceUtil;
import com.stpl.app.ui.UDCIncrementCheck;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.HelperDTO;
import com.stpl.app.util.HelperUtils;
import com.stpl.app.util.NotesTabLogic;
import com.stpl.domain.global.company.CompanyMasterDAO;
import com.stpl.domain.global.item.ItemDAO;
import com.stpl.domain.global.item.ItemLogic;
import com.stpl.domain.global.security.StplSecurityDAO;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
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
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NumberUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;
import com.stpl.app.model.IfpDetails;
import com.stpl.util.dao.orm.CustomSQLUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemSearchLogic.
 */
public class ItemSearchLogic extends BeanItemContainer<SearchItemForm> implements ItemLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ItemSearchLogic.class);
    /**
     * The format double.
     */
    private static final DecimalFormat FORMATDECIMAL = new DecimalFormat("###,##0.000000");
    /**
     * The dao.
     */
    private static final ItemDAO DAO = new ItemSearchLogicDAOImpl();

    private static CompanyMasterDAO dao = new CompanySearchLogicDAOImpl();
    /**
     *
     */
    private static final HelperDTO HELPERDTO1 = null;
    private static int brandCount;
    private static int ndc8Count;
    private static int itemQualifierNameCount;
    private static int itemPricingQualifierNameCount;
    static HashMap<String, String> columnName = new HashMap<String, String>();

    private CompanyMasterDAO companyDao = new CompanySearchLogicDAOImpl();
    private NotesTabLogic rsLogic = new NotesTabLogic();
    final private StplSecurityDAO securityDto = new StplSecurityDAOImpl();
    private static HelperListUtil helperListUtil = HelperListUtil.getInstance();
    SessionDTO sessionDTO;

    /**
     * The Constructor.
     */
    public ItemSearchLogic() {
        super(SearchItemForm.class);

    }

    /**
     * Gets the item qualifier for edit list.
     *
     * @return the item qualifier for edit list
     * @throws SystemException, Exception
     * @throws PortalException
     */
    public List<ItemIrtIdentifierDTO> getItemQualifierForEditList() throws PortalException, SystemException, Exception {

        LOGGER.info("Entering getItemQualifierForEditList");
        final List<ItemIrtIdentifierDTO> list = new ArrayList<ItemIrtIdentifierDTO>();

        final int count = DAO.getItemIrtQualifiersTotalCount();
        final List<ItemQualifier> qualifierList = DAO.getItemIrtQualifiersByLimit(0, count);
        for (int i = 0; i < qualifierList.size(); i++) {

            final ItemIrtIdentifierDTO itemIden = new ItemIrtIdentifierDTO();
            final ItemQualifier qualifier = (ItemQualifier) qualifierList
                    .get(i);
            itemIden.setIdentifierCodeQualifier(qualifier
                    .getItemQualifierValue());
            itemIden.setItemIrtQualifierId(qualifier.getItemQualifierSid());
            itemIden.setIdentifierCodeQualifierName(qualifier.getItemQualifierName());
            itemIden.setEffectiveDates(qualifier.getEffectiveDates());
            itemIden.setEntityCode(qualifier.getSpecificEntityCode());
            itemIden.setNotes(qualifier.getNotes());
            itemIden.setRecordLockStatus(qualifier.getRecordLockStatus());
            String createdBy = StplSecurity.userMap.get(qualifier.getCreatedBy());
            itemIden.setCreatedByValue(qualifier.getCreatedBy());
            itemIden.setCreatedBy(createdBy == null ? StringUtils.EMPTY : createdBy);
            itemIden.setCreatedDate(qualifier.getCreatedDate());
            String modifiedBy = StplSecurity.userMap.get(qualifier.getModifiedBy());
            itemIden.setModifiedBy(modifiedBy == null ? StringUtils.EMPTY : modifiedBy);
            itemIden.setModifiedByValue(qualifier.getModifiedBy());
            itemIden.setModifiedDate(qualifier.getModifiedDate());
            list.add(itemIden);
        }

        LOGGER.info("return list size :" + list.size());
        return list;

    }

    /**
     * Logic for Delete irt qualifer.
     *
     * @param qualifierId the qualifier id
     * @return the list< item irt identifier dt o>
     * @throws Exception
     */
    public List<ItemIrtIdentifierDTO> deleteIrtQualifer(final int qualifierId) throws SystemException, PortalException, Exception {

        LOGGER.info("Entering deleteIrtQualifer P1: " + qualifierId);
        List<ItemIrtIdentifierDTO> qualifierList = new ArrayList<ItemIrtIdentifierDTO>();

        DynamicQuery query = DynamicQueryFactoryUtil.forClass(ItemIdentifier.class);
        query.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_QUALIFIER_SID, qualifierId));
        final List<ItemIdentifier> itemIdentifierList = DAO.getItemIdentifierList(query);

        if (itemIdentifierList.isEmpty()) {
            DAO.deleteItemIrtQualifierByQualifierId(qualifierId);
            qualifierList = getItemQualifierForEditList();
        } else {
            final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Delete Failed", "Can not delete the selected Qualifier,Since it is associated with another Item", new MessageBoxListener() {
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
        LOGGER.info("Ending deleteIrtQualifer");
        return qualifierList;
    }

    /**
     * Logic for Save the irt qualifer.
     *
     * @param binder the binder
     * @return the list< item irt identifier dt o>
     * @throws SystemException, Exception
     * @throws PortalException
     */
    public List<ItemIrtIdentifierDTO> saveIrtQualifer(final ErrorfulFieldGroup binder) throws PortalException, SystemException, Exception {

        LOGGER.info("Entering saveIrtQualifer");

        final int itemIrtQualifierId = Integer.parseInt(binder.getField(ConstantsUtils.ITEM_IRT_QUALIFIFIERID).getValue().toString());
        final String itemIrtQualifierName = binder.getField("identifierCodeQualifierName").getValue().toString();
        final String itemIdentifier = binder.getField("identifierCodeQualifier").getValue().toString();
        final String effectiveDates = binder.getField("effectiveDates").getValue().toString();
        final String entityCode = binder.getField("entityCode").getValue().toString();

        if (itemIrtQualifierId <= 0) {

            // uniqueness check for item identifier name and id in look
            // up
            final ItemQualifier identifier = ItemQualifierLocalServiceUtil.createItemQualifier(0);
            identifier.setItemQualifierName(itemIrtQualifierName);
            identifier.setItemQualifierValue(itemIdentifier);
            identifier.setEffectiveDates(effectiveDates);
            identifier.setSpecificEntityCode(entityCode);
            identifier.setNotes(binder.getField(ConstantsUtils.NOTES_SMALL).getValue() == null ? StringUtils.EMPTY : binder.getField(ConstantsUtils.NOTES_SMALL).getValue().toString());
            identifier.setCreatedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
            identifier.setCreatedDate(new Date());
//            identifier.setModifiedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
            identifier.setModifiedDate(new Date());
            identifier.setSource(ConstantsUtils.GTN);
            final DynamicQuery itemIdDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemQualifier.class);
            itemIdDynamicQuery.add(RestrictionsFactoryUtil.eq("itemQualifierValue", itemIdentifier));
            final List<ItemQualifier> contractMaster = DAO.getItemIrtQualifierList(itemIdDynamicQuery);

            final DynamicQuery compamyNoDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemQualifier.class);
            compamyNoDynamicQuery.add(RestrictionsFactoryUtil.eq("itemQualifierName", identifier.getItemQualifierName()));
            final List<ItemQualifier> itemNoMaster = DAO.getItemIrtQualifierList(compamyNoDynamicQuery);
            if (contractMaster.size() < 1 && itemNoMaster.size() < 1) {
                DAO.saveItemIrtQualifier(identifier);
                LOGGER.info("entered contraId and ContractNo");

            } else if (contractMaster.size() == 1) {
                binder.getErrorDisplay().setError("Please enter different Qualifier, Since the entered qualifier already exists ");

            } else {
                binder.getErrorDisplay().setError("Please enter different Qualifier Name, Since the entered qualifier Name already exists ");
            }
        } else {
            // uniqueness check for item identifier name and id in look
            // up
            ItemQualifier itemQualifier = ItemQualifierLocalServiceUtil.getItemQualifier(itemIrtQualifierId);// to get created Date & created By
            itemQualifier.setCreatedBy(itemQualifier.getCreatedBy());
            itemQualifier.setCreatedDate(itemQualifier.getCreatedDate());
                itemQualifier.setModifiedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
                itemQualifier.setModifiedDate(new Date());
            itemQualifier.setItemQualifierName(itemIrtQualifierName);
            itemQualifier.setItemQualifierValue(itemIdentifier);
            itemQualifier.setEffectiveDates(effectiveDates);
            itemQualifier.setSpecificEntityCode(entityCode);
            itemQualifier.setNotes(binder.getField(ConstantsUtils.NOTES_SMALL).getValue() == null ? StringUtils.EMPTY : binder.getField(ConstantsUtils.NOTES_SMALL).getValue().toString());
            itemQualifier.setSource(ConstantsUtils.GTN);
            final DynamicQuery itemIdDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemQualifier.class);

            itemIdDynamicQuery.add(RestrictionsFactoryUtil.eq("itemQualifierValue", itemIdentifier));
            final List<ItemQualifier> conMaster = DAO.getItemIrtQualifierList(itemIdDynamicQuery);

            final DynamicQuery itemNoDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemQualifier.class);
            itemNoDynamicQuery.add(RestrictionsFactoryUtil.eq("itemQualifierName", itemQualifier.getItemQualifierName()));
            final List<ItemQualifier> conNoMaster = DAO.getItemIrtQualifierList(itemNoDynamicQuery);

            int count = 0;
            for (int i = 0; i < conMaster.size(); i++) {
                if (itemIrtQualifierId == conMaster.get(i).getItemQualifierSid()) {
                } else {

                    count++;
                }

            }
            int countNo = 0;

            for (int i = 0; i < conNoMaster.size(); i++) {
                if (itemIrtQualifierId == conNoMaster.get(i).getItemQualifierSid()) {
                } else {

                    countNo++;
                }

            }
            LOGGER.info("updated successfully count" + conMaster.size());
            LOGGER.info("updated successfully countNo" + conNoMaster.size());
            if (count < 1 && countNo < 1) {
                LOGGER.info("updated successfully");
                DAO.updateItemIrtQualifier(itemQualifier);
            } else if (conMaster.size() == 1) {
                binder.getErrorDisplay().setError("Please enter different Qualifier, Since the entered qualifier already exists ");

            } else {
                binder.getErrorDisplay().setError("Please enter different Qualifier Name, Since the entered qualifier Name already exists");
            }
        }
        LOGGER.info("Ending saveIrtQualifer");
        return getItemQualifierForEditList();

    }

    /**
     * Gets the pricing qualifier for edit list.
     *
     * @return the pricing qualifier for edit list
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws com.stpl.portal.kernel.exception.PortalException
     */
    public List<ItemIrtIdentifierDTO> getPricingQualifierForEditList() throws SystemException, PortalException {
        LOGGER.info("Entering getPricingQualifierForEditList");
        final List<ItemIrtIdentifierDTO> list = new ArrayList<ItemIrtIdentifierDTO>();

        final int count = DAO.getItemPricingQualifierTotalCount();
        final List<ItemPricingQualifier> qualifierList = DAO.getItemPricingQualifierByLimit(0, count);

        for (int i = 0; i < qualifierList.size(); i++) {
            final ItemIrtIdentifierDTO itemIden = new ItemIrtIdentifierDTO();
            final ItemPricingQualifier qualifier = (ItemPricingQualifier) qualifierList
                    .get(i);
            itemIden.setIdentifierCodeQualifierName(qualifier
                    .getItemPricingQualifierName());
            itemIden.setItemIrtQualifierId(qualifier.getItemPricingQualifierSid());
            itemIden.setIdentifierCodeQualifier(qualifier.getPricingQualifier());
            itemIden.setRecordLockStatus(qualifier.getRecordLockStatus());
            itemIden.setEntityCode(qualifier.getSpecificEntityCode());
            itemIden.setEffectiveDates(qualifier.getEffectiveDates());
            itemIden.setNotes(qualifier.getNotes());
            String createdBy = StplSecurity.userMap.get(qualifier.getCreatedBy());
            itemIden.setCreatedByValue(qualifier.getCreatedBy());
            itemIden.setCreatedBy(createdBy == null ? StringUtils.EMPTY : createdBy);
            itemIden.setCreatedDate(qualifier.getCreatedDate());
            String modifiedBy = StplSecurity.userMap.get(qualifier.getModifiedBy());
            itemIden.setModifiedBy(modifiedBy == null ? StringUtils.EMPTY : modifiedBy);
            itemIden.setModifiedByValue(qualifier.getModifiedBy());
            itemIden.setModifiedDate(qualifier.getModifiedDate());
            list.add(itemIden);
        }

        LOGGER.info("Ending ItemPricingQualifier size -" + list.size());
        return list;

    }

    /**
     * Logic for Delete the pricing qualifer.
     *
     * @param qualifierId the qualifier id
     * @return the list< item pricing dt o>
     */
    public List<ItemIrtIdentifierDTO> deletePricingQualifer(final int qualifierId) throws SystemException, PortalException {

        LOGGER.info("Entering deletePricingQualifer P1: " + qualifierId);
        DAO.deleteItemPricingQualifierByQualifierId(qualifierId);

        return getPricingQualifierForEditList();
    }

    /**
     * Logic for Save the pricing qualifer.
     *
     * @param binder the binder
     * @return the list< item pricing dt o>
     */
    public List<ItemIrtIdentifierDTO> savePricingQualifer(final ErrorfulFieldGroup binder) throws PortalException, SystemException {
        LOGGER.info("Entering savePricingQualifer");

        final int itemPricingQualifierId = Integer.valueOf(binder
                .getField(ConstantsUtils.ITEM_IRT_QUALIFIFIERID).getValue().toString());
        final String itemPricingQualifierName = binder.getField(ConstantsUtils.IDENTIFIER_CODE_QUALIFIER_NAME)
                .getValue().toString();
        final String itemQualifier = binder.getField(ConstantsUtils.IDENTIFIER_CODE_QUALIFIER).getValue()
                .toString();
        final String effectiveDates = binder.getField("effectiveDates").getValue().toString();
        final String entityCode = binder.getField("entityCode").getValue().toString();
        final ItemPricingQualifier identifier = ItemPricingQualifierLocalServiceUtil.createItemPricingQualifier(0);
        identifier.setItemPricingQualifierName(itemPricingQualifierName);
        identifier.setPricingQualifier(itemQualifier);
        identifier.setSpecificEntityCode(entityCode);
        identifier.setEffectiveDates(effectiveDates);
        identifier.setNotes(binder.getField(ConstantsUtils.NOTES_SMALL).getValue() == null ? StringUtils.EMPTY : binder.getField(ConstantsUtils.NOTES_SMALL).getValue().toString());

        if (itemPricingQualifierId <= 0) {

            // uniqueness check for pricing qualifier and id in look
            // up
            identifier.setCreatedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
            identifier.setCreatedDate(new Date());
//            identifier.setModifiedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
            identifier.setModifiedDate(new Date());
            identifier.setSource(ConstantsUtils.GTN);
            final DynamicQuery itemIdDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemPricingQualifier.class);
            itemIdDynamicQuery.add(RestrictionsFactoryUtil.eq("pricingQualifier", itemQualifier));
            final List<ItemPricingQualifier> contractMaster = DAO.getItemPricingQualifierList(itemIdDynamicQuery);

            final DynamicQuery compamyNoDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemPricingQualifier.class);
            compamyNoDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_PRICING_QUAL_NAME, identifier.getItemPricingQualifierName()));
            final List<ItemPricingQualifier> itemNoMaster = DAO.getItemPricingQualifierList(compamyNoDynamicQuery);
            LOGGER.info("contractMaster.size()" + contractMaster.size());
            LOGGER.info("contractNoMaster.size()" + itemNoMaster.size());

            if (contractMaster.size() < 1 && itemNoMaster.size() < 1) {
                DAO.saveItemPricingQualifier(identifier);
                LOGGER.info("entered contraId and ContractNo");

            } else if (contractMaster.size() == 1) {
                binder.getErrorDisplay().setError("Please enter different Qualifier, Since the entered qualifier already exists ");

            } else {
                binder.getErrorDisplay().setError("Please enter different Qualifier Name, Since the entered qualifier Name already exists ");
            }
        } else {
            // uniqueness check for pricing qualifier and id in look
            // up
            ItemPricingQualifier identifier1 = ItemPricingQualifierLocalServiceUtil.getItemPricingQualifier(itemPricingQualifierId);// to get created Date & created By
            identifier1.setModifiedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
            identifier1.setModifiedDate(new Date());
            identifier1.setItemPricingQualifierName(identifier.getItemPricingQualifierName());
            identifier1.setPricingQualifier(itemQualifier);
            identifier1.setSpecificEntityCode(identifier.getSpecificEntityCode());
            identifier1.setEffectiveDates(identifier.getEffectiveDates());
            identifier1.setNotes(binder.getField(ConstantsUtils.NOTES_SMALL).getValue() == null ? StringUtils.EMPTY : binder.getField(ConstantsUtils.NOTES_SMALL).getValue().toString());
            identifier1.setSource(ConstantsUtils.GTN);
            final DynamicQuery itemIdDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemPricingQualifier.class);
            itemIdDynamicQuery.add(RestrictionsFactoryUtil.eq("pricingQualifier", itemQualifier));
            final List<ItemPricingQualifier> conMaster = DAO.getItemPricingQualifierList(itemIdDynamicQuery);

            final DynamicQuery itemNoDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemPricingQualifier.class);
            itemNoDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_PRICING_QUAL_NAME, identifier1.getItemPricingQualifierName()));
            final List<ItemPricingQualifier> conNoMaster = DAO.getItemPricingQualifierList(itemNoDynamicQuery);

            int count = 0;
            for (int i = 0; i < conMaster.size(); i++) {
                if (itemPricingQualifierId == conMaster.get(i).getItemPricingQualifierSid()) {
                } else {

                    count++;
                }

            }
            int countNo = 0;

            for (int i = 0; i < conNoMaster.size(); i++) {
                if (itemPricingQualifierId == conNoMaster.get(i).getItemPricingQualifierSid()) {
                } else {

                    countNo++;
                }

            }
            LOGGER.info("updated successfully count" + conMaster.size());
            LOGGER.info("updated successfully countNo" + conNoMaster.size());
            if (count < 1 && countNo < 1) {
                LOGGER.info("updated successfully");
                DAO.updateItemPricingQualifier(identifier1);
            } else if (conMaster.size() == 1) {
                binder.getErrorDisplay().setError("Please enter different Qualifier, Since the entered qualifier already exists ");

            } else {
                binder.getErrorDisplay().setError("Please enter different Qualifier Name, Since the entered qualifier Name already exists");
            }
        }

        LOGGER.info("Ending savePricingQualifier operation");
        return getPricingQualifierForEditList();
    }

    /**
     * Gets the customized search form from object.
     *
     * @param list the list
     * @param identifierType the identifier type
     * @return the customized search form from object
     */
    public List<SearchResultsDTO> getCustomizedSearchFormFromObject(
            final List list, final String identifierType) throws PortalException, SystemException, Exception {

        LOGGER.info("Entering getCustomizedSearchFormFromObject p1: " + ((list == null) ? list : list.size()) + " p2: " + identifierType);
        final List<SearchResultsDTO> searchItemList = new ArrayList<SearchResultsDTO>();

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final SearchResultsDTO searchItemForm = new SearchResultsDTO();
                final Object[] obj = (Object[]) list.get(i);
                searchItemForm.setItemSystemID(HelperUtils.getString(obj[0]));
                searchItemForm.setSystemID(HelperUtils.getString(obj[0]));
                searchItemForm.setItemId(HelperUtils.getString(obj[1]));
                searchItemForm.setItemNo(HelperUtils.getString(obj[2]));
                searchItemForm.setItemName(HelperUtils.getString(obj[3]));
                searchItemForm.setItemDesc(HelperUtils.getString(obj[4]));

                searchItemForm.setItemType(CommonUtils.getDescription(HelperUtils.getInteger(obj[5])));
                searchItemForm.setItemStatus(CommonUtils.getDescription(HelperUtils.getInteger(obj[6])));

                /*  searchItemForm.setItemType(new HelperDTO(HelperUtils
                 .getString(obj[5])));
                 searchItemForm.setItemStatus(new HelperDTO(String
                 .valueOf(obj[6])));*/
                /**
                 * if(StringUtils.isNotBlank(HelperUtils.getString(obj[7]))){
                 * CompanyMaster master=
                 * companyDao.getCompanyMasterBySystemId(Integer.parseInt(HelperUtils.getString(obj[7])));
                 * searchItemForm.setManufacturerId(master.getCompanyId());
                 * }else{
                 * searchItemForm.setManufacturerId(HelperUtils.getString(obj[7]));
                 * }
                 */
                searchItemForm.setItemIrtQualifierName(new HelperDTO(
                        identifierType));
                if (obj.length == 29) {
                    searchItemForm.setItemIdentifier(obj[28] == null ? StringUtils.EMPTY : obj[28].toString());
                }
                searchItemForm.setRecordLockStatus(Boolean.parseBoolean(HelperUtils
                        .getString(obj[7])));
                searchItemForm.setItemCode(HelperUtils.getString(obj[8]));
                if (StringUtils.isNotBlank(HelperUtils.getString(obj[9]))) {
                    searchItemForm.setPackageSize(CommonUtils.getDescription(Integer.parseInt(HelperUtils.getString(obj[9]))));
                } else {
                    searchItemForm.setPackageSize(HelperUtils.getString(obj[9]));
                }

                if (obj[12] == null) {
                    searchItemForm.setUpps("0.000000");
                } else {
                    searchItemForm.setUpps(FORMATDECIMAL.format(Double.valueOf(HelperUtils.getString(obj[10]))));
                }

                searchItemForm.setItemStartDate(CommonUtils
                        .convertDateToString((Date) obj[11]));
                searchItemForm.setItemEndDate(CommonUtils
                        .convertDateToString((Date) obj[12]));

                searchItemForm.setLabelerCode(HelperUtils.getString(obj[13]));
                searchItemForm.setForm(obj[14] == null ? StringUtils.EMPTY : obj[14].toString());
                searchItemForm.setStrength(obj[15] == null ? StringUtils.EMPTY : obj[15].toString());
                searchItemForm.setPrimaryUom(CommonUtils.getDescription(HelperUtils.getInteger(obj[16])));
                searchItemForm.setSecondaryUom(CommonUtils.getDescription(HelperUtils.getInteger(obj[17])));
                searchItemForm.setItemClass(CommonUtils.getDescription(HelperUtils.getInteger(obj[18])));
                searchItemForm.setPediatricExclusiveStartDate(CommonUtils
                        .convertDateToString((Date) obj[19]));
                searchItemForm.setPediatricExclusiveEndDate(CommonUtils
                        .convertDateToString((Date) obj[20]));
                searchItemForm.setClottingFactorStartDate(CommonUtils
                        .convertDateToString((Date) obj[21]));
                searchItemForm.setClottingFactorEndDate(CommonUtils
                        .convertDateToString((Date) obj[22]));
                searchItemForm.setBrand(obj[23] == null ? StringUtils.EMPTY : obj[23].toString());
                HelperDTO ndc8 = new HelperDTO();
                ndc8.setDescription(HelperUtils.getString(obj[24]));
                searchItemForm.setNdc8(ndc8);
                HelperDTO ndc9 = new HelperDTO();
                ndc9.setDescription(HelperUtils.getString(obj[25]));
                searchItemForm.setNdc9(ndc9);
                searchItemForm.setTherapeuticClass(obj[26] != null && !StringUtils.isEmpty(obj[26].toString()) && !ConstantsUtils.ZERO.equals(obj[26]) && !ConstantsUtils.SELECT_ONE.equals(obj[26])
                        ? HelperUtils.getString(obj[26]) : StringUtils.EMPTY);
                searchItemForm.setItemBatchId(obj[27] != null && !StringUtils.isEmpty(obj[27].toString()) ? String.valueOf(obj[27]) : StringUtils.EMPTY);
                searchItemList.add(searchItemForm);

            }
        }

        LOGGER.info("returns searchItemList size " + searchItemList.size());
        return searchItemList;
    }

    /**
     * Gets the customized search form from model.
     *
     * @param list the list
     * @return the customized search form from model
     * @throws Exception
     */
    public List<SearchResultsDTO> getCustomizedSearchFormFromModel(
            final List<ItemMaster> list) throws Exception {

        final List<SearchResultsDTO> searchItemList = new ArrayList<SearchResultsDTO>();

        LOGGER.info("Entering getCustomizedSearchFormFromModel P1:  size " + ((list == null) ? list : list.size()));
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final SearchResultsDTO searchItemForm = new SearchResultsDTO();
                final ItemMaster obj = (ItemMaster) list.get(i);
                searchItemForm.setItemSystemID(String.valueOf(obj.getItemMasterSid()));
                searchItemForm.setSystemID(String.valueOf(obj.getItemMasterSid()));
                searchItemForm.setItemId(obj.getItemId());
                searchItemForm.setItemNo(obj.getItemNo());
                searchItemForm.setItemName(obj.getItemName());
                searchItemForm.setItemDesc(obj.getItemDesc());

                searchItemForm.setItemType(CommonUtils.getDescription(obj.getItemType()));
                searchItemForm.setItemStatus(CommonUtils.getDescription(obj.getItemStatus()));
                searchItemForm.setTherapeuticClass(CommonUtils.getDescription(obj.getTherapeuticClass()));
                searchItemForm.setForm(String.valueOf(obj.getForm()));
                searchItemForm.setStrength(StringUtils.EMPTY);

                searchItemForm.setItemCode(obj.getItemCode());
                searchItemForm.setPackageSize(!StringUtils.EMPTY.equals(obj.getPackageSize()) ? CommonUtils.getDescription(Integer.valueOf(obj.getPackageSize())) : StringUtils.EMPTY);
                searchItemForm.setUpps(FORMATDECIMAL.format(obj.getUpps()));
                searchItemForm.setItemStartDate(CommonUtils
                        .convertDateToString(obj.getItemStartDate()));
                searchItemForm.setItemEndDate(CommonUtils
                        .convertDateToString(obj.getItemEndDate()));
                searchItemForm.setItemMasterStartDate(obj.getItemStartDate());
                searchItemForm.setItemMasterEndDate(obj.getItemEndDate());

                searchItemForm.setLabelerCode(obj.getLabelerCode());
                searchItemForm.setForm(String.valueOf(obj.getForm()));
                final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
                cfpDynamicQuery.add(RestrictionsFactoryUtil.eq("companyMasterSid", !StringUtils.EMPTY.equals(obj.getManufacturerId()) ? Integer.parseInt(obj.getManufacturerId()) : 0));
                List<CompanyMaster> cmList = DAO.getCompanyMasterList(cfpDynamicQuery);
                String manufacturerId = StringUtils.EMPTY;
                for (CompanyMaster cm : cmList) {
                    manufacturerId = cm.getCompanyId();
                }
                searchItemForm.setManufacturerId(manufacturerId);
                searchItemForm.setStrength(StringUtils.EMPTY);
                searchItemForm.setPrimaryUom(CommonUtils.getDescription(obj.getPrimaryUom()));
                searchItemForm.setSecondaryUom(CommonUtils.getDescription(obj.getSecondaryUom()));
                searchItemForm.setItemClass(obj.getItemClass() != 0 ? CommonUtils.getDescription(obj.getItemClass()) : StringUtils.EMPTY);
                searchItemForm.setPediatricExclusiveStartDate(CommonUtils
                        .convertDateToString(obj
                                .getPediatricExclusiveStartDate()));
                searchItemForm.setPediatricExcStartDate(obj.getPediatricExclusiveStartDate());
                searchItemForm
                        .setPediatricExclusiveEndDate(CommonUtils
                                .convertDateToString(obj
                                        .getPediatricExclusiveEndDate()));
                searchItemForm.setPediatricExcEndDate(obj.getPediatricExclusiveEndDate());
                searchItemForm.setClottingFactorStartDate(CommonUtils
                        .convertDateToString(obj.getClottingFactorStartDate()));
                searchItemForm.setClottingFactStartDate(obj.getClottingFactorStartDate());
                searchItemForm.setClottingFactorEndDate(CommonUtils
                        .convertDateToString(obj.getClottingFactorEndDate()));
                searchItemForm.setClottingFactEndDate(obj.getClottingFactorEndDate());
                if (obj.getBrandMasterSid() != 0) {
                    BrandMaster brandM = BrandMasterLocalServiceUtil.getBrandMaster(obj.getBrandMasterSid());
                    searchItemForm.setBrand(brandM.getBrandName());
                }
                searchItemForm.setRecordLockStatus(obj.getRecordLockStatus());
                HelperDTO dto = new HelperDTO();
                dto.setDescription(obj.getNdc8());
                searchItemForm.setNdc8(dto);
                HelperDTO dto1 = new HelperDTO();
                dto1.setDescription(obj.getNdc9());
                searchItemForm.setNdc9(dto1);

                searchItemList.add(searchItemForm);
            }
        }
        LOGGER.info("return searchItemList size " + searchItemList.size());

        return searchItemList;
    }

    /**
     * Gets the item irt identifier.
     *
     * @param form the form
     * @return the item irt identifier
     * @throws com.stpl.portal.kernel.exception.SystemException
     */
    public ItemIdentifier getItemIrtIdentifier(final ItemIrtIdentifierDTO form) throws SystemException, NoSuchItemQualifierException {
        LOGGER.info("Enters getItemIrtIdentifier() P1: ItemIrtIdentifierDTO ");
        final ItemIdentifier identifier = ItemIdentifierLocalServiceUtil.createItemIdentifier(0);
        final ItemQualifier qualifier = DAO.getItemIrtQualifierByName(form
                .getItemIrtQualifierName());

        identifier.setItemIdentifierSid(qualifier.getItemQualifierSid());
        identifier.setEntityCode(form.getEntityCodeSid().trim());
        identifier.setIdentifierStatus(form.getIdentifierStatus().getId());
        identifier.setItemIdentifierValue(form.getItemIdentifier().trim());
        if (form.getIrtIdentifierSystemId() == CommonUtils.ZERO) {

            identifier.setCreatedDate(new Date());
            identifier.setCreatedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));

        } else {
            identifier.setCreatedBy(form.getCreatedByValue());
            identifier.setCreatedDate(form.getCreatedDate());
            identifier.setItemIdentifierSid(form.getIrtIdentifierSystemId());
            identifier.setModifiedDate(new Date());
            identifier.setModifiedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));

        }
        return identifier;
    }

    /**
     * Logic for Convert date to string.
     *
     * @param date the date
     * @return the string
     */
    public String convertDateToString(final Date date) throws Exception {

        final DateFormat outputFormat = new SimpleDateFormat(ConstantsUtils.DATE);
        final String outputString = outputFormat.format(date);

        return outputString;

    }

    /**
     * Gets the item pricing.
     *
     * @param form the form
     * @return the item pricing
     */
    public ItemPricing getItemPricing(final ItemPricingDTO form) throws SystemException, NoSuchItemPricingQualifierException, Exception {
        LOGGER.info("Enters getItemPricing() P1: ItemPricingDTO");
        final ItemPricing pricingForm = ItemPricingLocalServiceUtil.createItemPricing(0);

        if (form.getIdentifierCodeQualifierName() != null) {

            pricingForm.setItemPricingQualifierSid(form
                    .getItemPricingQualifierId());

        }

        String price = form.getItemPrice();
        price = price.replace(ConstantsUtils.DOLLAR, StringUtils.EMPTY);
        price = price.replace(ConstantsUtils.COMMA, StringUtils.EMPTY);
        pricingForm.setItemPrice(Double.valueOf(price));
        pricingForm.setPricingCodeStatus(form.getPricingCodeStatus().getId());
        pricingForm.setItemUom(form.getItemUom().getId());
        pricingForm.setEntityCode(form.getEntityCodeSid().trim());
        pricingForm.setSource(form.getSource());
        if (form.getItemPricingSystemId() == CommonUtils.ZERO) {

            pricingForm.setCreatedDate(new Date());
            pricingForm.setCreatedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
            pricingForm.setModifiedDate(new Date());
            pricingForm.setModifiedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));

        } else {
            pricingForm.setCreatedBy(form.getCreatedByValue());
            pricingForm.setCreatedDate(form.getCreatedDate());
            pricingForm.setItemPricingSid(form.getItemPricingSystemId());
            pricingForm.setModifiedDate(new Date());
            pricingForm.setModifiedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));

        }
        LOGGER.info("returns ItemPricing class ");
        return pricingForm;
    }

    /**
     * Gets the master.
     *
     * @param itemMasterForm the item master form
     */
    public void getMaster(final ErrorfulFieldGroup itemMasterForm) {
        LOGGER.info("Enters getMaster() P1: itemMasterForm binder");

        final ItemMaster item = ItemMasterLocalServiceUtil.createItemMaster(0);
        item.setItemId(itemMasterForm.getField(ConstantsUtils.ITEM_ID).getValue().toString());

        item.setItemNo(itemMasterForm.getField(ConstantsUtils.ITEM_NO).getValue().toString());
        item.setItemName(itemMasterForm.getField(ConstantsUtils.ITEM_NAME).getValue()
                .toString());
        item.setStrength(CommonUtils.getHelperTableSId(itemMasterForm.getField(ConstantsUtils.STRENGTH).getValue()
                .toString(), ConstantsUtils.STRENGTH_UPPERCASE));
        item.setPrimaryUom(Integer.parseInt(itemMasterForm.getField(ConstantsUtils.PRIMARY_UOM).getValue()
                .toString()));
        item.setSecondaryUom(Integer.parseInt(itemMasterForm.getField(ConstantsUtils.SECONDARY_UOM).getValue()
                .toString()));
        item.setForm(Integer.parseInt(itemMasterForm.getField(ConstantsUtils.FORM).getValue().toString()));
        item.setItemDesc(itemMasterForm.getField(ConstantsUtils.ITEM_DESC).getValue()
                .toString());
        item.setItemType(Integer.parseInt(itemMasterForm.getField(ConstantsUtils.ITEM_TYPE).getValue()
                .toString()));
        item.setItemStatus(Integer.parseInt(itemMasterForm.getField(ConstantsUtils.ITEM_STATUS).getValue()
                .toString()));
        item.setPackageSize(itemMasterForm.getField(ConstantsUtils.PACKAGE_SIZE).getValue()
                .toString());
        item.setItemClass(Integer.parseInt(itemMasterForm.getField(ConstantsUtils.ITEM_CLASS).getValue()
                .toString()));
        item.setItemCode(itemMasterForm.getField(ConstantsUtils.ITEM_CODE).getValue()
                .toString());
        item.setLabelerCode(itemMasterForm.getField(ConstantsUtils.LABELER_CODE).getValue()
                .toString());
        item.setUpps(Integer.parseInt(itemMasterForm.getField(ConstantsUtils.UPPS).getValue().toString()));
        item.setItemMasterSid((Integer) itemMasterForm.getField(ConstantsUtils.ITEM_SYSTEM_ID)
                .getValue());

    }

    /**
     * Save item master.
     *
     * @param itemMasterForm the item master form
     * @param identifierList the identifier list
     * @param priceList the price list
     * @return the string
     */
    public String saveItemMaster(final ErrorfulFieldGroup itemMasterForm, final List<ItemIrtIdentifierDTO> identifierList, final List<ItemPricingDTO> priceList, final List<NotesDTO> availableUploadedInformation, final String addedNotes, final List<NotesDTO> removedDetailsList, final SessionDTO sessionDTO, final List<ItemPricingDTO> removedItemPriceList) throws SystemException, PortalException, Exception {
        LOGGER.info("Entering saveItemMaster P2: size " + ((identifierList == null) ? identifierList : identifierList.size()) + " p3: size " + ((priceList == null) ? priceList : priceList.size()));
        String systemId = itemMasterForm.getField(ConstantsUtils.SYSTEM_ID).getValue() == null || itemMasterForm
                .getField(ConstantsUtils.SYSTEM_ID).getValue().equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : String
                        .valueOf(itemMasterForm.getField(ConstantsUtils.SYSTEM_ID).getValue());

        boolean flag = false;
        try {
            systemId = systemId.replace(ConstantsUtils.COMMA, StringUtils.EMPTY);
            Udcs udc = UdcsLocalServiceUtil.createUdcs(0);
            ItemMaster item;
            String itemId = String.valueOf(itemMasterForm.getField(ConstantsUtils.ITEM_ID).getValue()).trim();
            if (ConstantsUtils.NULL.equals(systemId) || StringUtils.EMPTY.equals(systemId) || ConstantsUtils.ZERO.equals(systemId)) {
                final DynamicQuery itemDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemMaster.class);
                itemDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_ID, itemId));
                itemDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
                final List<ItemMaster> itemMasterList = DAO.getItemMasterList(itemDynamicQuery);

                if (!itemMasterList.isEmpty()) {
                    for (int i = 0; i < itemMasterList.size(); i++) {
                        systemId = String.valueOf(itemMasterList.get(i).getItemMasterSid());
                    }
                    flag = true;
                }
            }
            if (systemId.equals(ConstantsUtils.NULL) || systemId.equals(CommonUtils.ZERO) || systemId.equals(StringUtils.EMPTY)) {
                item = ItemMasterLocalServiceUtil.createItemMaster(0);
                item.setInboundStatus(ConstantsUtils.INBOUND_STATUS_A);
                item.setSource(ConstantsUtils.GTN);
            } else {
                item = DAO.getItemMasterBySystemId(Integer.parseInt(systemId));
                item.setInboundStatus(ConstantsUtils.INBOUND_STATUS_C);
                item.setSource(ConstantsUtils.GTN);
                if (flag) {
                    item.setInboundStatus(ConstantsUtils.INBOUND_STATUS_A);
                    item.setSource(ConstantsUtils.GTN);
                    sessionDTO.setSystemId(Integer.valueOf(systemId));
                }
            }
            ItemMaster result = null;

            item.setItemId(itemMasterForm.getField(ConstantsUtils.ITEM_ID).getValue()
                    .toString().trim());

            item.setItemNo(itemMasterForm.getField(ConstantsUtils.ITEM_NO).getValue()
                    .toString().trim());
            item.setItemName(itemMasterForm.getField(ConstantsUtils.ITEM_NAME).getValue()
                    .toString().trim());
            if (itemMasterForm.getField(ConstantsUtils.STRENGTH).getValue() != null) {
                item.setStrength(((com.stpl.ifs.util.HelperDTO) (itemMasterForm.getField(ConstantsUtils.STRENGTH).getValue())).getId());
            } else {
                item.setStrength(0);
            }
            if (itemMasterForm.getField(ConstantsUtils.PRIMARY_UOM).getValue() != null) {
                item.setPrimaryUom(((com.stpl.ifs.util.HelperDTO) (itemMasterForm.getField(ConstantsUtils.PRIMARY_UOM)
                        .getValue())).getId());
            } else {
                item.setPrimaryUom(0);
            }
            if (itemMasterForm.getField(ConstantsUtils.SECONDARY_UOM).getValue() != null) {
                item.setSecondaryUom(((com.stpl.ifs.util.HelperDTO) (itemMasterForm.getField(ConstantsUtils.SECONDARY_UOM)
                        .getValue())).getId());
            } else {
                item.setSecondaryUom(0);
            }

            if (itemMasterForm.getField(ConstantsUtils.FORM).getValue() != null) {
                item.setForm(((com.stpl.ifs.util.HelperDTO) (itemMasterForm.getField(ConstantsUtils.FORM).getValue())).getId());
            } else {
                item.setForm(0);
            }
            item.setItemDesc(itemMasterForm.getField(ConstantsUtils.ITEM_DESC).getValue()
                    .toString().trim());
            if (itemMasterForm.getField(ConstantsUtils.ITEM_TYPE).getValue() != null) {
                item.setItemType(((com.stpl.ifs.util.HelperDTO) (itemMasterForm.getField(ConstantsUtils.ITEM_TYPE).getValue())).getId());
            } else {
                item.setItemType(0);
            }

            item.setItemStatus(((com.stpl.ifs.util.HelperDTO) (itemMasterForm.getField(ConstantsUtils.ITEM_STATUS).getValue())).getId());
            String packageSize = (itemMasterForm.getField(ConstantsUtils.PACKAGE_SIZE).getValue() == null ? ConstantUtil.ZERO : String.valueOf(((com.stpl.ifs.util.HelperDTO) (itemMasterForm.getField(ConstantsUtils.PACKAGE_SIZE).getValue())).getId()));
            if (!packageSize.equals(ConstantsUtils.NULL)) {
                item.setPackageSize(packageSize);
            } else {
                item.setPackageSize(ConstantsUtils.ZERO);
            }
            Object obj = itemMasterForm.getField(ConstantsUtils.PACKAGE_SIZE_CODE).getValue();

            item.setPackageSizeCode(obj == null || obj.toString().equals(StringUtils.EMPTY) ? ConstantsUtils.ZERO : obj.toString().trim());

            if (itemMasterForm.getField(ConstantsUtils.ITEM_CLASS).getValue() != null) {
                item.setItemClass(((com.stpl.ifs.util.HelperDTO) (itemMasterForm.getField(ConstantsUtils.ITEM_CLASS).getValue())).getId());
            } else {
                item.setItemClass(0);
            }
            item.setItemCode(itemMasterForm.getField(ConstantsUtils.ITEM_CODE).getValue()
                    .toString().trim());
            item.setNdc8(itemMasterForm.getField(ConstantsUtils.NDC8).getValue().toString());
            item.setNdc9(itemMasterForm.getField(ConstantsUtils.NDC9).getValue().toString());
            item.setBrandMasterSid(((HelperDTO) itemMasterForm.getField(ConstantsUtils.BRANDDDLB).getValue()).getId());
            item.setLabelerCode(itemMasterForm.getField(ConstantsUtils.LABELER_CODE)
                    .getValue().toString().trim());

            if (itemMasterForm.getField(ConstantsUtils.UPPS).getValue() != null && !StringUtils.EMPTY.equals(itemMasterForm.getField(ConstantsUtils.UPPS).getValue())) {

                item.setUpps(Double.valueOf(String.valueOf(itemMasterForm.getField(ConstantsUtils.UPPS).getValue()).replaceAll(ConstantsUtils.COMMA, StringUtils.EMPTY)));

            } else {
                item.setUpps(0.0);
            }

            if (itemMasterForm.getField(ConstantsUtils.CLOTTING_FACTOR_INDICATOR).getValue() != null
                    && !StringUtils.EMPTY.equals(itemMasterForm.getField(
                                    ConstantsUtils.CLOTTING_FACTOR_INDICATOR).getValue())) {
                item.setClottingFactorIndicator(String.valueOf(itemMasterForm
                        .getField(ConstantsUtils.CLOTTING_FACTOR_INDICATOR).getValue()));
            } else {
                item.setClottingFactorIndicator(StringUtils.EMPTY);
            }
            if (itemMasterForm.getField(ConstantsUtils.PEDIACTRIC_EXCLUSIVE_INDICATOR)
                    .getValue() != null
                    && !StringUtils.EMPTY.equals(itemMasterForm.getField(
                                    ConstantsUtils.PEDIACTRIC_EXCLUSIVE_INDICATOR).getValue())) {
                item.setPediatricExclusiveIndicator(String
                        .valueOf(itemMasterForm
                                .getField(ConstantsUtils.PEDIACTRIC_EXCLUSIVE_INDICATOR)
                                .getValue().toString()));

            } else {
                item.setPediatricExclusiveIndicator(StringUtils.EMPTY);
            }

            item.setItemStartDate((Date) itemMasterForm.getField(
                    ConstantsUtils.ITEM_START_DATE).getValue());
            item.setItemEndDate((Date) itemMasterForm.getField(ConstantsUtils.ITEM_END_DATE)
                    .getValue());
            item.setPackageSizeIntroDate((Date) itemMasterForm.getField(
                    ConstantsUtils.PACKAGE_SIZE_INTRO_DATE).getValue());
            item.setClottingFactorStartDate((Date) itemMasterForm.getField(
                    ConstantsUtils.CLOTTING_FACTOR_START_DATE).getValue());
            item.setClottingFactorEndDate((Date) itemMasterForm.getField(
                    ConstantsUtils.CLOTTING_FACTOR_END_DATE).getValue());
            item.setPediatricExclusiveStartDate((Date) itemMasterForm.getField(
                    ConstantsUtils.PEDIATRIC_EXCLUSIVE_START_DATE).getValue());
            item.setPediatricExclusiveEndDate((Date) itemMasterForm.getField(
                    ConstantsUtils.PEDIATRIC_EXCLUSIVE_END_DATE).getValue());

            if (itemMasterForm.getField(ConstantsUtils.THERAPEUTIC_CLASS)
                    .getValue() != null
                    && !StringUtils.EMPTY.equals(itemMasterForm.getField(
                                    ConstantsUtils.THERAPEUTIC_CLASS).getValue())) {

                item.setTherapeuticClass(((com.stpl.ifs.util.HelperDTO) (itemMasterForm.getField(ConstantsUtils.THERAPEUTIC_CLASS).getValue())).getId());

            } else {
                item.setTherapeuticClass(0);
            }

            if (itemMasterForm.getField(ConstantsUtils.FIRST_SALE_DATE)
                    .getValue() != null
                    && !StringUtils.EMPTY.equals(itemMasterForm.getField(
                                    ConstantsUtils.FIRST_SALE_DATE).getValue())) {

                item.setFirstSaleDate((Date) itemMasterForm.getField(ConstantsUtils.FIRST_SALE_DATE).getValue());
            } else {
                item.setFirstSaleDate(null);
            }

            if (itemMasterForm.getField(ConstantsUtils.BASE_CPI_PERIOD)
                    .getValue() != null
                    && !StringUtils.EMPTY.equals(itemMasterForm.getField(
                                    ConstantsUtils.BASE_CPI_PERIOD).getValue())) {

                item.setBaseCpiPeriod((Date) itemMasterForm.getField(ConstantsUtils.BASE_CPI_PERIOD).getValue());
            } else {
                item.setBaseCpiPeriod(null);
            }

            if (itemMasterForm.getField(ConstantsUtils.ITEM_CATEGORY).getValue() != null) {

                item.setItemCategory(((com.stpl.ifs.util.HelperDTO) (itemMasterForm.getField(ConstantsUtils.ITEM_CATEGORY).getValue())).getId());

            } else {
                item.setItemCategory(0);
            }

            if (itemMasterForm.getField(ConstantsUtils.MANUFACTURED_ID_DDLB).getValue() == null || ((HelperDTO) itemMasterForm.getField(ConstantsUtils.MANUFACTURED_ID_DDLB).getValue()).getId() == ConstantsUtils.ZERO_INT || StringUtils.EMPTY.equals(itemMasterForm.getField(ConstantsUtils.MANUFACTURED_ID_DDLB).getValue())) {
                item.setManufacturerId(StringUtils.EMPTY);

            } else {
                item.setManufacturerId(String.valueOf(((HelperDTO) itemMasterForm.getField(ConstantsUtils.MANUFACTURED_ID_DDLB).getValue()).getId()));
            }

            if (itemMasterForm.getField(ConstantsUtils.BASE_LINE_AMP).getValue() != null && !StringUtils.EMPTY.equals(itemMasterForm.getField(ConstantsUtils.BASE_LINE_AMP).getValue())) {

                item.setBaselineAmp(Double.valueOf(String.valueOf(itemMasterForm.getField(ConstantsUtils.BASE_LINE_AMP).getValue())));

            } else {
                item.setBaselineAmp(0.0);
            }

            if (itemMasterForm.getField(ConstantsUtils.BASE_CPI).getValue() != null && !StringUtils.EMPTY.equals(itemMasterForm.getField(ConstantsUtils.BASE_CPI).getValue())) {

                item.setBaseCpi(Double.valueOf(String.valueOf(itemMasterForm.getField(ConstantsUtils.BASE_CPI).getValue())));

            } else {
                item.setBaseCpi(0.0);
            }

            if (itemMasterForm.getField(ConstantsUtils.OBRA_BAMP).getValue() != null && !StringUtils.EMPTY.equals(itemMasterForm.getField(ConstantsUtils.OBRA_BAMP).getValue())) {

                item.setObraBamp(Double.valueOf(String.valueOf(itemMasterForm.getField(ConstantsUtils.OBRA_BAMP).getValue())));

            } else {
                item.setObraBamp(0.0);
            }

            if (itemMasterForm.getField(ConstantsUtils.ACQUIRE_AMP).getValue() != null && !StringUtils.EMPTY.equals(itemMasterForm.getField(ConstantsUtils.ACQUIRE_AMP).getValue())) {

                item.setAcquiredAmp(Double.valueOf(String.valueOf(itemMasterForm.getField(ConstantsUtils.ACQUIRE_AMP).getValue())));

            } else {
                item.setAcquiredAmp(0.0);
            }

            if (itemMasterForm.getField(ConstantsUtils.ACQUIRE_BAMP).getValue() != null && !StringUtils.EMPTY.equals(itemMasterForm.getField(ConstantsUtils.ACQUIRE_BAMP).getValue())) {

                item.setAcquiredBamp(Double.valueOf(String.valueOf(itemMasterForm.getField(ConstantsUtils.ACQUIRE_BAMP).getValue())));

            } else {
                item.setAcquiredBamp(0.0);
            }

            if (itemMasterForm.getField(ConstantsUtils.DRA).getValue() != null && !StringUtils.EMPTY.equals(itemMasterForm.getField(ConstantsUtils.DRA).getValue())) {

                item.setDra(Double.valueOf(String.valueOf(itemMasterForm.getField(ConstantsUtils.DRA).getValue())));

            } else {
                item.setDra(0.0);
            }

            if (itemMasterForm.getField(ConstantsUtils.AUTHORIZED_GENERIC).getValue() != null
                    && !StringUtils.EMPTY.equals(itemMasterForm.getField(ConstantsUtils.AUTHORIZED_GENERIC).getValue())) {
                item.setAuthorizedGeneric(String.valueOf(itemMasterForm.getField(ConstantsUtils.AUTHORIZED_GENERIC).getValue()));
            } else {
                item.setAuthorizedGeneric(StringUtils.EMPTY);
            }

            if (itemMasterForm.getField(ConstantsUtils.NEW_FORMULATION_INDICATOR).getValue() != null
                    && !StringUtils.EMPTY.equals(itemMasterForm.getField(ConstantsUtils.NEW_FORMULATION_INDICATOR).getValue())) {
                item.setNewFormulationIndicator(String.valueOf(itemMasterForm.getField(ConstantsUtils.NEW_FORMULATION_INDICATOR).getValue()));
            } else {
                item.setNewFormulationIndicator(StringUtils.EMPTY);
            }

            if (itemMasterForm.getField(ConstantsUtils.DUAL_PRICING_INDICATOR).getValue() != null
                    && !StringUtils.EMPTY.equals(itemMasterForm.getField(ConstantsUtils.DUAL_PRICING_INDICATOR).getValue())) {
                item.setDualPricingIndicator(String.valueOf(itemMasterForm.getField(ConstantsUtils.DUAL_PRICING_INDICATOR).getValue()).trim());
            } else {
                item.setDualPricingIndicator(StringUtils.EMPTY);
            }

            if (itemMasterForm.getField(ConstantsUtils.ACQUIRE_BAMP).getValue() != null
                    && !StringUtils.EMPTY.equals(itemMasterForm.getField(ConstantsUtils.ACQUIRE_BAMP).getValue())) {
            }

            item.setAcquisitionDate((Date) itemMasterForm.getField(
                    ConstantsUtils.ACQUISISTION_DATE).getValue());
            item.setNonFederalExpirationDate((Date) itemMasterForm.getField("nonFederalExpirationDate").getValue());

            item.setAuthorizedGenericStartDate((Date) itemMasterForm.getField(
                    ConstantsUtils.AUTHORIZED_GENERIC_START_DATE).getValue());

            item.setAuthorizedGenericEndDate((Date) itemMasterForm.getField(
                    ConstantsUtils.AUTHORIZED_GENERIC_END_DATE).getValue());

            item.setMarketTerminationDate((Date) itemMasterForm.getField(
                    ConstantsUtils.MARKER_TERMINATION_DATE).getValue());

            item.setNewFormulationStartDate((Date) itemMasterForm.getField(
                    ConstantsUtils.NEW_FORMULATION_START_DATE).getValue());

            item.setNewFormulationEndDate((Date) itemMasterForm.getField(
                    ConstantsUtils.NEW_FORMULATION_END_DATE).getValue());

            item.setDiscontinuationDate((Date) itemMasterForm.getField(
                    ConstantsUtils.DISCOUNT_DATE).getValue());

            item.setLastLotExpirationDate((Date) itemMasterForm.getField(
                    ConstantsUtils.LAST_LOT_EXP_DATE).getValue());

            item.setDivestitureDate((Date) itemMasterForm.getField(
                    ConstantsUtils.DIVESTITURE_DATE).getValue());

            item.setInternalNotes(addedNotes);

            if (itemMasterForm.getField(ConstantsUtils.ITEM_TYPE_INDICATION).getValue() != null
                    && !StringUtils.EMPTY.equals(itemMasterForm.getField(ConstantsUtils.ITEM_TYPE_INDICATION)
                            .getValue())) {
                item.setItemTypeIndication(String.valueOf(((com.stpl.ifs.util.HelperDTO) (itemMasterForm.getField(ConstantsUtils.ITEM_TYPE_INDICATION).getValue())).getId()));

            } else {
                item.setItemTypeIndication(StringUtils.EMPTY);
            }

            if (itemMasterForm.getField(ConstantsUtils.SHELF_LIFE_TYPE).getValue() != null
                    && !StringUtils.EMPTY.equals(itemMasterForm.getField(ConstantsUtils.SHELF_LIFE_TYPE)
                            .getValue())) {
                item.setShelfLifeType(((com.stpl.ifs.util.HelperDTO) (itemMasterForm.getField(ConstantsUtils.SHELF_LIFE_TYPE).getValue())).getId());

            } else {
                item.setShelfLifeType(0);
            }

            item.setShelfLife(itemMasterForm.getField(ConstantsUtils.SHELF_LIFE).getValue().toString().trim());
            item.setDosesPerUnit(itemMasterForm.getField(ConstantsUtils.DOSES_PER_UNIT).getValue().toString().trim());

            item.setRecordLockStatus(Boolean.valueOf(ConstantsUtils.UNLOCKED));
            final String user = VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString();

            final DynamicQuery itemDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemMaster.class);
            itemDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_ID, item.getItemId()));

            final DynamicQuery ndcDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemMaster.class);
            ndcDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.NDC8, item.getNdc8()));
            ndcDynamicQuery.add(RestrictionsFactoryUtil.ne("inboundStatus", "D"));

            final DynamicQuery itemNoQuery = DynamicQueryFactoryUtil.forClass(ItemMaster.class);
            itemNoQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_NO, item.getItemNo()));

            final List<ItemMaster> itemMaster = DAO.getItemMasterList(itemDynamicQuery);
            final List<ItemMaster> ndcList = DAO.getItemMasterList(ndcDynamicQuery);
            final List<ItemMaster> itemNoList = DAO.getItemMasterList(itemNoQuery);

            //vah    
            if (ConstantsUtils.NULL.equals(systemId) || StringUtils.EMPTY.equals(systemId) || ConstantsUtils.ZERO.equals(systemId)) {
                LOGGER.info("Entering Save operation");
                final Date createdDate = new Date();
                item.setCreatedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
                item.setCreatedDate(createdDate);
                item.setModifiedDate(createdDate);
                item.setModifiedDate(createdDate);
                item.setSource(ConstantsUtils.GTN);
                item.setInternalNotes(addedNotes);
                if (itemMaster.size() < 1 && ndcList.size() < 1 && itemNoList.size() < 1) {
                    result = DAO.saveItemMaster(item);
                    sessionDTO.setSystemId(result.getItemMasterSid());
                    if (item.getItemType() > 0) {
                        UDCIncrementCheck.increment(String.valueOf(item.getItemType()), UIUtils.ITEM_TYPE);
                    }
                    if (item.getItemStatus() > 0) {
                        UDCIncrementCheck.increment(String.valueOf(item.getItemStatus()), UIUtils.STATUS);
                    }
                    if (item.getTherapeuticClass() > 0) {
                        UDCIncrementCheck.increment(String.valueOf(item.getTherapeuticClass()), UIUtils.THERAPEUTIC_CLASS);
                    }
                    if (result.getItemMasterSid() != 0) {

                        udc.setMasterSid(result.getItemMasterSid());
                        udc.setMasterType("ITEM_MASTER");

                        String udc1 = (itemMasterForm.getField(ConstantsUtils.UDC1).getValue() == null ? ConstantUtil.ZERO : String.valueOf(((com.stpl.ifs.util.HelperDTO) (itemMasterForm.getField(ConstantsUtils.UDC1).getValue())).getId()));
                        String udc2 = (itemMasterForm.getField(ConstantsUtils.UDC2).getValue() == null ? ConstantUtil.ZERO : String.valueOf(((com.stpl.ifs.util.HelperDTO) (itemMasterForm.getField(ConstantsUtils.UDC2).getValue())).getId()));
                        String udc3 = (itemMasterForm.getField(ConstantsUtils.UDC3).getValue() == null ? ConstantUtil.ZERO : String.valueOf(((com.stpl.ifs.util.HelperDTO) (itemMasterForm.getField(ConstantsUtils.UDC3).getValue())).getId()));
                        String udc4 = (itemMasterForm.getField(ConstantsUtils.UDC4).getValue() == null ? ConstantUtil.ZERO : String.valueOf(((com.stpl.ifs.util.HelperDTO) (itemMasterForm.getField(ConstantsUtils.UDC4).getValue())).getId()));
                        String udc5 = (itemMasterForm.getField(ConstantsUtils.UDC5).getValue() == null ? ConstantUtil.ZERO : String.valueOf(((com.stpl.ifs.util.HelperDTO) (itemMasterForm.getField(ConstantsUtils.UDC5).getValue())).getId()));
                        String udc6 = (itemMasterForm.getField(ConstantsUtils.UDC6).getValue() == null ? ConstantUtil.ZERO : String.valueOf(((com.stpl.ifs.util.HelperDTO) (itemMasterForm.getField(ConstantsUtils.UDC6).getValue())).getId()));
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
                        if (!udc1.equals(ConstantUtil.ZERO) || !udc2.equals(ConstantUtil.ZERO) || !udc3.equals(ConstantUtil.ZERO)
                                || !udc4.equals(ConstantUtil.ZERO) || !udc5.equals(ConstantUtil.ZERO) || !udc6.equals(ConstantUtil.ZERO)) {
                            DAO.saveUdcs(udc);
                        }
                    }
                    if (item.getItemCategory() > 0) {
                        UDCIncrementCheck.increment(String.valueOf(item.getItemCategory()), UIUtils.ITEM_CATEGORY);
                    }
                    if (item.getItemTypeIndication() != null) {
                        UDCIncrementCheck.increment(item.getItemTypeIndication(), UIUtils.ITEM_TYPE_INDICATION);
                    }
                    if (item.getShelfLifeType() > 0) {
                        UDCIncrementCheck.increment(String.valueOf(item.getShelfLifeType()), UIUtils.SHELF_LIFE_TYPE);
                    }
                    if (item.getPrimaryUom() > 0) {
                        UDCIncrementCheck.increment(String.valueOf(item.getPrimaryUom()), UIUtils.PRIMARY_UOM);
                    }
                    if (item.getSecondaryUom() > 0) {
                        UDCIncrementCheck.increment(String.valueOf(item.getSecondaryUom()), UIUtils.SECONDARY_UOM);
                    }
                    if (item.getForm() > 0) {
                        UDCIncrementCheck.increment(String.valueOf(item.getForm()), UIUtils.FORM1);
                    }
                    if (item.getStrength() != 0) {
                        UDCIncrementCheck.increment(item.getStrength(), UIUtils.STRENGTH1);
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

                    saveIdentifiersList(identifierList, priceList, result, removedItemPriceList);
                    rsLogic.saveUploadedInformation(availableUploadedInformation, "ITEM_MASTER", result.getItemMasterSid());

                } else if (ndcList.size() > 0) {
                    LOGGER.info(ConstantsUtils.DUPLICATE_NDC);
                    return ConstantsUtils.DUPLICATE_NDC;
                    /*itemMasterForm.getErrorDisplay().setError(
                     "NDC8 already exists.");*/
                } else if (itemNoList.size() > 0) {
                    LOGGER.info(ConstantsUtils.DUPLICATE_ITEM_NO);
                    return ConstantsUtils.DUPLICATE_ITEM_NO;
                    /*itemMasterForm.getErrorDisplay().setError(
                     "NDC8 already exists.");*/
                } else {

                    /*itemMasterForm.getErrorDisplay().setError(
                     "Item ID already exists.");*/
                    LOGGER.info(ConstantsUtils.DUPLICATE);
                    return ConstantsUtils.DUPLICATE;
                }

            } else {
                LOGGER.info("Entering update operation");
                systemId = systemId.replace(ConstantsUtils.COMMA, StringUtils.EMPTY);
                int count = 0;
                for (int i = 0; i < itemMaster.size(); i++) {
                    if (Integer.parseInt(systemId.replace(ConstantsUtils.COMMA, StringUtils.EMPTY)) == itemMaster.get(i).getItemMasterSid()) {
                    } else {
                        count++;
                    }
                }
                int ndcCount = 0;
                for (int i = 0; i < ndcList.size(); i++) {
                    if (Integer.parseInt(systemId.replace(ConstantsUtils.COMMA, StringUtils.EMPTY)) == ndcList.get(i).getItemMasterSid()) {
                    } else {
                        ndcCount++;
                    }
                }
                int itemNoCount = 0;
                for (int i = 0; i < itemNoList.size(); i++) {
                    if (Integer.parseInt(systemId.replace(ConstantsUtils.COMMA, StringUtils.EMPTY)) == itemNoList.get(i).getItemMasterSid()) {
                    } else {
                        itemNoCount++;
                    }
                }

                if (count < 1 && ndcCount < 1 && itemNoCount < 1) {
                    item.setItemMasterSid(Integer.parseInt(systemId));
                    item.setModifiedDate(new Date());
                    item.setModifiedBy(Integer.parseInt(VaadinSession.getCurrent()
                            .getAttribute(ConstantsUtils.USER_ID).toString()));

                    final ItemMaster check = DAO.getItemMasterBySystemId(item.getItemMasterSid());

                    if (check.getItemType() > 0) {
                        UDCIncrementCheck.decrement(String.valueOf(check.getItemType()), UIUtils.ITEM_TYPE);
                    }
                    if (check.getItemStatus() > 0) {
                        UDCIncrementCheck.decrement(String.valueOf(check.getItemStatus()), UIUtils.STATUS);
                    }
                    if (check.getTherapeuticClass() > 0) {
                        UDCIncrementCheck.decrement(String.valueOf(check.getTherapeuticClass()), UIUtils.THERAPEUTIC_CLASS);
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

                    if (check.getItemCategory() > 0) {
                        UDCIncrementCheck.decrement(String.valueOf(check.getItemCategory()), UIUtils.ITEM_CATEGORY);
                    }
                    if (check.getItemTypeIndication() != null) {
                        UDCIncrementCheck.decrement(String.valueOf(check.getItemTypeIndication()), UIUtils.ITEM_TYPE_INDICATION);
                    }
                    if (check.getShelfLifeType() > 0) {
                        UDCIncrementCheck.decrement(String.valueOf(check.getShelfLifeType()), UIUtils.SHELF_LIFE_TYPE);
                    }
                    if (check.getPrimaryUom() > 0) {
                        UDCIncrementCheck.decrement(String.valueOf(check.getPrimaryUom()), UIUtils.PRIMARY_UOM);
                    }
                    if (check.getSecondaryUom() > 0) {
                        UDCIncrementCheck.decrement(String.valueOf(check.getSecondaryUom()), UIUtils.SECONDARY_UOM);
                    }
                    if (check.getForm() > 0) {
                        UDCIncrementCheck.decrement(String.valueOf(check.getForm()), UIUtils.FORM1);
                    }
                    if (check.getStrength() != 0) {
                        UDCIncrementCheck.decrement(String.valueOf(check.getStrength()), UIUtils.STRENGTH1);
                    }
                    if (flag) {
                        final Date createdDate = new Date();
                        item.setCreatedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
                        item.setCreatedDate(createdDate);
                        item.setModifiedDate(createdDate);
                    }

                    item.setSource(ConstantsUtils.GTN);
                    result = DAO.updateItemMaster(item);

                    Object id = itemMasterForm.getField(ConstantsUtils.UDC1).getValue();

                    DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Udcs.class);
                    dynamicQuery.add(RestrictionsFactoryUtil.eq("masterSid", result.getItemMasterSid()));
                    List<Udcs> list = UdcsLocalServiceUtil.dynamicQuery(dynamicQuery);
                    int udc1 = (itemMasterForm.getField(ConstantsUtils.UDC1).getValue()) == null ? 0 : ((com.stpl.ifs.util.HelperDTO) (itemMasterForm.getField(ConstantsUtils.UDC1).getValue())).getId();
                    int udc2 = (itemMasterForm.getField(ConstantsUtils.UDC2).getValue()) == null ? 0 : ((com.stpl.ifs.util.HelperDTO) (itemMasterForm.getField(ConstantsUtils.UDC2).getValue())).getId();
                    int udc3 = (itemMasterForm.getField(ConstantsUtils.UDC3).getValue()) == null ? 0 : ((com.stpl.ifs.util.HelperDTO) (itemMasterForm.getField(ConstantsUtils.UDC3).getValue())).getId();
                    int udc4 = (itemMasterForm.getField(ConstantsUtils.UDC4).getValue()) == null ? 0 : ((com.stpl.ifs.util.HelperDTO) (itemMasterForm.getField(ConstantsUtils.UDC4).getValue())).getId();
                    int udc5 = (itemMasterForm.getField(ConstantsUtils.UDC5).getValue()) == null ? 0 : ((com.stpl.ifs.util.HelperDTO) (itemMasterForm.getField(ConstantsUtils.UDC5).getValue())).getId();
                    int udc6 = (itemMasterForm.getField(ConstantsUtils.UDC6).getValue()) == null ? 0 : ((com.stpl.ifs.util.HelperDTO) (itemMasterForm.getField(ConstantsUtils.UDC6).getValue())).getId();
                    if (list == null || list.isEmpty()) {
                        Udcs udcup = UdcsLocalServiceUtil.createUdcs(0);
                        udcup.setMasterSid(result.getItemMasterSid());
                        udcup.setMasterType("ITEM_MASTER");
                        udcup.setUdc1(udc1);
                        udcup.setUdc2(udc2);
                        udcup.setUdc3(udc3);
                        udcup.setUdc4(udc4);
                        udcup.setUdc5(udc5);
                        udcup.setUdc6(udc6);
                        if (udc1 != 0 || udc2 != 0 || udc3 != 0 || udc4 != 0 || udc5 != 0 || udc6 != 0) {
                            DAO.saveUdcs(udcup);
                        }
                    } else {
                        for (int j = 0; j < list.size(); j++) {
                            Udcs obj1 = list.get(j);
                            obj1.setUdc1(udc1);
                            obj1.setUdc2(udc2);
                            obj1.setUdc3(udc3);
                            obj1.setUdc4(udc4);
                            obj1.setUdc5(udc5);
                            obj1.setUdc6(udc6);
                            DAO.updateUdcs(obj1);
                        }
                    }

                    if (item.getItemType() > 0) {
                        UDCIncrementCheck.increment(String.valueOf(item.getItemType()), UIUtils.ITEM_TYPE);
                    }
                    if (item.getItemStatus() > 0) {
                        UDCIncrementCheck.increment(String.valueOf(item.getItemStatus()), UIUtils.STATUS);
                    }
                    if (item.getTherapeuticClass() > 0) {
                        UDCIncrementCheck.increment(String.valueOf(item.getTherapeuticClass()), UIUtils.THERAPEUTIC_CLASS);
                    }
                    if (item.getItemCategory() > 0) {
                        UDCIncrementCheck.increment(String.valueOf(item.getItemCategory()), UIUtils.ITEM_CATEGORY);
                    }
                    if (item.getItemTypeIndication() != null) {
                        UDCIncrementCheck.increment(item.getItemTypeIndication(), UIUtils.ITEM_TYPE_INDICATION);
                    }
                    if (item.getShelfLifeType() > 0) {
                        UDCIncrementCheck.increment(String.valueOf(item.getShelfLifeType()), UIUtils.SHELF_LIFE_TYPE);
                    }
                    if (item.getPrimaryUom() > 0) {
                        UDCIncrementCheck.increment(String.valueOf(item.getPrimaryUom()), UIUtils.PRIMARY_UOM);
                    }
                    if (item.getSecondaryUom() > 0) {
                        UDCIncrementCheck.increment(String.valueOf(item.getSecondaryUom()), UIUtils.SECONDARY_UOM);
                    }
                    if (item.getForm() > 0) {
                        UDCIncrementCheck.increment(String.valueOf(item.getForm()), UIUtils.FORM1);
                    }
                    if (item.getStrength() != 0) {
                        UDCIncrementCheck.increment(item.getStrength(), UIUtils.STRENGTH1);
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

                    final List<ItemIdentifier> idenList = DAO.getItemIrtIdentifierByItemSystemId(result
                            .getItemMasterSid());
                    final List<ItemPricing> pricingList = DAO.getItemPricingByItemSystemId(result.getItemMasterSid());

                    for (int i = 0; i < idenList.size(); i++) {
                        final ItemIdentifier itemIrtIdentifier = (ItemIdentifier) idenList
                                .get(i);

                        itemIrtIdentifier.setInboundStatus(ConstantsUtils.INBOUND_STATUS_D);
                        DAO.updateItemIrtIdentifier(itemIrtIdentifier);

                        if (itemIrtIdentifier.getIdentifierStatus() > 0) {
                            UDCIncrementCheck.decrement(itemIrtIdentifier.getIdentifierStatus(), UIUtils.STATUS);
                        }
                    }
                    result.setInternalNotes(addedNotes);
                    saveIdentifiersList(identifierList, priceList, result, removedItemPriceList);

                    if (!removedDetailsList.isEmpty()) {

                        for (int i = 0; i < removedDetailsList.size(); i++) {
                            NotesDTO dtoValue = removedDetailsList.get(i);

                            if (dtoValue.getDocDetailsId() != 0) {

                                rsLogic.deleteUploadedFile(dtoValue.getDocDetailsId(), StringUtils.EMPTY, dtoValue.getDocumentFullPath());
                            }
                        }
                    }
                    rsLogic.saveUploadedInformation(availableUploadedInformation, "ITEM_MASTER", result.getItemMasterSid());
                } else if (ndcList.size() > 0) {
                    LOGGER.info(ConstantsUtils.DUPLICATE_NDC);
                    return ConstantsUtils.DUPLICATE_NDC;
                    /*itemMasterForm.getErrorDisplay().setError(
                     "NDC8 already exists.");*/
                } else if (itemNoList.size() > 0) {
                    LOGGER.info(ConstantsUtils.DUPLICATE_ITEM_NO);
                    return ConstantsUtils.DUPLICATE_ITEM_NO;
                    /*itemMasterForm.getErrorDisplay().setError(
                     "NDC8 already exists.");*/
                } else {

                    /*itemMasterForm.getErrorDisplay().setError(
                     "Item ID already exists.");*/
                    LOGGER.info(ConstantsUtils.DUPLICATE);
                    return ConstantsUtils.DUPLICATE;
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
            return ConstantsUtils.FAIL;
        }

        return ConstantsUtils.SUCCESS;

    }

    /**
     * Logic for Save identifiers list.
     *
     * @param identifierList the identifier list
     * @param priceList the price list
     * @param result the result
     */
    public void saveIdentifiersList(final List<ItemIrtIdentifierDTO> identifierList,
            final List<ItemPricingDTO> priceList, final ItemMaster result, final List<ItemPricingDTO> removedItemPriceList) throws SystemException, NoSuchItemPricingQualifierException, NoSuchItemQualifierException, Exception {

        LOGGER.info("Entering saveIdentifiersList P1: size " + ((identifierList == null) ? identifierList : identifierList.size()) + " P2: size" + ((priceList == null) ? priceList : priceList.size()));
        if (identifierList != null) {
            for (int i = 0; i < identifierList.size(); i++) {
                final ItemIrtIdentifierDTO identifierForm = (ItemIrtIdentifierDTO) identifierList
                        .get(i);
                final ItemIdentifier identifier = getItemIrtIdentifier(identifierForm);
                identifier.setItemMasterSid(result.getItemMasterSid());

                final ItemQualifier qualif = DAO.getItemIrtQualifierByName(identifierForm.getItemIrtQualifierName());
                identifier.setItemQualifierSid(qualif.getItemQualifierSid());

                if (identifierForm.getStartDate() != null) {
                    identifier.setStartDate(identifierForm.getStartDate());
                } else {
                    identifier.setStartDate(null);
                }
                if (identifierForm.getEndDate() != null) {
                    identifier.setEndDate(identifierForm.getEndDate());
                } else {
                    identifier.setEndDate(null);
                }
                identifier.setModifiedDate(new Date());
                final DynamicQuery itemIdentifierDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemIdentifier.class);
                itemIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_IDENTIFIER_VALUE, identifier.getItemIdentifierValue().trim()));
                itemIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.START_DATE, identifier.getStartDate()));
                itemIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_QUALIFIER_SID, identifier.getItemQualifierSid()));
                itemIdentifierDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));

                
                final List<ItemIdentifier> itemIdentiiferList = DAO.getItemIdentifierList(itemIdentifierDynamicQuery);
              
                if (itemIdentiiferList.isEmpty()) {
                     
                    if (identifierForm.getIrtIdentifierSystemId() == CommonUtils.ZERO) {
                        identifier.setInboundStatus(ConstantsUtils.INBOUND_STATUS_A);
                        identifier.setSource(ConstantsUtils.GTN);
                        final ItemIdentifier irt = DAO.saveItemIrtIdentifier(identifier);
                        if (irt.getIdentifierStatus() > 0) {
                            UDCIncrementCheck.increment(String.valueOf(irt.getIdentifierStatus()), UIUtils.STATUS);
                        }
                    } else {
                        identifier.setModifiedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
                        identifier.setInboundStatus(ConstantsUtils.INBOUND_STATUS_C);
                        identifier.setSource(ConstantsUtils.GTN);
                        final ItemIdentifier irt = DAO.updateItemIrtIdentifier(identifier);
                        if (irt.getIdentifierStatus() > 0) {
                            UDCIncrementCheck.increment(String.valueOf(irt.getIdentifierStatus()), UIUtils.STATUS);
                        }
                    }
                } else {
                   
                    ItemIdentifier itemIdentifier = itemIdentiiferList.get(0);
                    itemIdentifier.setInboundStatus(ConstantsUtils.INBOUND_STATUS_A);
                    itemIdentifier.setSource(ConstantsUtils.GTN);

                    itemIdentifier.setEntityCode(identifier.getEntityCode());
                    itemIdentifier.setIdentifierStatus(identifier.getIdentifierStatus());
                    itemIdentifier.setItemIdentifierValue(identifier.getItemIdentifierValue());
                    itemIdentifier.setCreatedDate(new Date());
                    itemIdentifier.setModifiedDate(new Date());
                    itemIdentifier.setCreatedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
                    itemIdentifier.setModifiedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
                    itemIdentifier.setItemMasterSid(result.getItemMasterSid());
                    final ItemIdentifier irt = DAO.updateItemIrtIdentifier(itemIdentifier);
                    if (irt.getIdentifierStatus() != 0) {
                        UDCIncrementCheck.increment(String.valueOf(irt.getIdentifierStatus()), UIUtils.STATUS);
                    }
                }
            }

            if (removedItemPriceList != null && !removedItemPriceList.isEmpty()) {
                StringBuilder itemPricingSystemIds = new StringBuilder(StringUtils.EMPTY);
                for (Object object : removedItemPriceList) {
                    Item item = (Item) object;
                    if (item.getItemProperty("itemPricingSystemId").getValue() != null) {
                        itemPricingSystemIds.append(String.valueOf(item.getItemProperty("itemPricingSystemId").getValue()));
                        itemPricingSystemIds.append(ConstantsUtils.COMMA);
                    }
                }
                if (itemPricingSystemIds.length() != 0) {
                    String updateQuery = "UPDATE ITEM_PRICING SET INBOUND_STATUS = 'D'  WHERE ITEM_PRICING_SID IN (" + itemPricingSystemIds.substring(0, itemPricingSystemIds.length() - 1) + ");";
                    RsModelLocalServiceUtil.executeUpdateQuery(updateQuery, null, null);
                }
            }

            if (priceList != null) {
                for (int i = 0; i < priceList.size(); i++) {
                    final ItemPricingDTO form = (ItemPricingDTO) priceList.get(i);
                    final ItemPricing priceForm = getItemPricing(form);
                    priceForm.setItemMasterSid(result.getItemMasterSid());
                    priceForm.setPricingCodeStatus(form.getPricingCodeStatus().getId());
                    priceForm.setItemUom(form.getItemUom().getId());
                    if (form.getPricingStartDate() != null) {
                        priceForm.setStartDate(form.getPricingStartDate());
                    } else {
                        priceForm.setStartDate(null);
                    }
                    if (form.getPricingEndDate() != null) {
                        priceForm.setEndDate(form.getPricingEndDate());
                    } else {
                        priceForm.setEndDate(null);
                    }
                    priceForm.setEntityCode(form.getEntityCodeSid());

                    final DynamicQuery itemPricingDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemPricing.class);
                    itemPricingDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.START_DATE, priceForm.getStartDate()));
                    itemPricingDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_PRICING_QUALIFIER_SID, priceForm.getItemPricingQualifierSid()));
                    itemPricingDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_UOM, priceForm.getItemUom()));
                    itemPricingDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_PRICE, priceForm.getItemPrice()));
                    itemPricingDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.PRICING_CODE_STATUS, priceForm.getPricingCodeStatus()));
                    itemPricingDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
                    final List<ItemPricing> itemPricingList = DAO.getItemPricingList(itemPricingDynamicQuery);
                    if (!itemPricingList.isEmpty()) {
                        ItemPricing itemPricing = itemPricingList.get(0);
                        itemPricing.setInboundStatus(ConstantsUtils.INBOUND_STATUS_A);
                        itemPricing.setItemPrice(priceForm.getItemPrice());
                        itemPricing.setPricingCodeStatus(priceForm.getPricingCodeStatus());
                        itemPricing.setItemUom(priceForm.getItemUom());
                        itemPricing.setEntityCode(priceForm.getEntityCode());
                        itemPricing.setSource(ConstantsUtils.GTN);
                        itemPricing.setCreatedDate(new Date());
                        itemPricing.setModifiedDate(new Date());
                        itemPricing.setItemPricingSid(itemPricing.getItemPricingSid());
                        itemPricing.setSource(itemPricing.getSource());
                        itemPricing.setCreatedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
                        itemPricing.setModifiedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
                        final ItemPricing pricing = DAO.updateItemPricing(itemPricing);
                        if (pricing.getPricingCodeStatus() > 0) {
                            UDCIncrementCheck.increment(String.valueOf(pricing.getPricingCodeStatus()), UIUtils.STATUS);
                        }
                        if (pricing.getItemUom() > 0) {
                            UDCIncrementCheck.increment(pricing.getItemUom(), UIUtils.UOM);
                        }
                    } else {
                        if (form.getItemPricingSystemId() == CommonUtils.ZERO) {
                            priceForm.setInboundStatus(ConstantsUtils.INBOUND_STATUS_A);
                            priceForm.setSource(ConstantsUtils.GTN);
                            final ItemPricing pricing = DAO.saveItemPricing(priceForm);
                            if (pricing.getPricingCodeStatus() > 0) {
                                UDCIncrementCheck.increment(String.valueOf(pricing.getPricingCodeStatus()), UIUtils.STATUS);
                            }
                            if (pricing.getItemUom() > 0) {
                                UDCIncrementCheck.increment(pricing.getItemUom(), UIUtils.UOM);
                            }
                        } else {
                            ItemPricing itemPrincingModel = ItemPricingLocalServiceUtil.getItemPricing(priceForm.getItemPricingSid());
                            itemPrincingModel.setItemMasterSid(priceForm.getItemMasterSid());
                            itemPrincingModel.setItemPricingQualifierSid(priceForm.getItemPricingQualifierSid());
                            itemPrincingModel.setItemUom(priceForm.getItemUom());
                            itemPrincingModel.setItemPrice(priceForm.getItemPrice());
                            itemPrincingModel.setPricingCodeStatus(priceForm.getPricingCodeStatus());
                            itemPrincingModel.setEntityCode(priceForm.getEntityCode());
                            itemPrincingModel.setStartDate(priceForm.getStartDate());
                            itemPrincingModel.setEndDate(priceForm.getEndDate());
                            itemPrincingModel.setInboundStatus(ConstantsUtils.INBOUND_STATUS_C);
                            itemPrincingModel.setSource(ConstantsUtils.GTN);
                            itemPrincingModel.setModifiedBy(Integer.parseInt(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
                            final ItemPricing pricing = DAO.updateItemPricing(itemPrincingModel);

                            if (pricing.getPricingCodeStatus() > 0) {
                                UDCIncrementCheck.increment(String.valueOf(pricing.getPricingCodeStatus()), UIUtils.STATUS);
                            }
                            if (pricing.getItemUom() > 0) {
                                UDCIncrementCheck.increment(pricing.getItemUom(), UIUtils.UOM);
                            }
                        }
                    }
                }
            }
        }

        LOGGER.info("Ending saveIdentifiersList");
    }

    /**
     * Gets the item master by id.
     *
     * @param identifier1 the id
     * @return the item master by id
     */
    public ItemMasterDTO getItemMasterById(final int identifier1) throws ParseException, SystemException, PortalException, Exception {

        final DecimalFormat format = new DecimalFormat("0.000000");
        final DecimalFormat formatDouble1 = new DecimalFormat("###,##0.0000");

        final DecimalFormat format1 = new DecimalFormat("0.0000");
        final DecimalFormat format2 = new DecimalFormat("#");
        final ItemMasterDTO itemMasterDTO = new ItemMasterDTO();

        final DecimalFormat formatDouble = new DecimalFormat("###,##0.000000");
        LOGGER.info("getItemMasterById() P1: id " + identifier1);
        final ItemMaster itemMaster = DAO.getItemMasterBySystemId(identifier1);
        itemMasterDTO.setItemSystemId(String.valueOf(itemMaster.getItemMasterSid()));
        itemMasterDTO.setItemId(itemMaster.getItemId());
        itemMasterDTO.setItemNo(itemMaster.getItemNo());
        itemMasterDTO.setItemName(itemMaster.getItemName());

        itemMasterDTO.setStrength(helperListUtil.getIdHelperDTOMap().get(itemMaster.getStrength()));
        itemMasterDTO.setPrimaryUom(helperListUtil.getIdHelperDTOMap().get(itemMaster.getPrimaryUom()));
        itemMasterDTO.setSecondaryUom(helperListUtil.getIdHelperDTOMap().get(itemMaster.getSecondaryUom()));
        itemMasterDTO.setForm(helperListUtil.getIdHelperDTOMap().get(itemMaster.getForm()));
        itemMasterDTO.setItemDesc(String.valueOf(itemMaster.getItemDesc()));
        itemMasterDTO.setItemType(helperListUtil.getIdHelperDTOMap().get(itemMaster.getItemType()));
        itemMasterDTO.setSystemId(String.valueOf(identifier1));
        itemMasterDTO.setItemStatus(helperListUtil.getIdHelperDTOMap().get(itemMaster.getItemStatus()));
        if (!String.valueOf(itemMaster.getPackageSize()).isEmpty() && !String.valueOf(itemMaster.getPackageSize()).equalsIgnoreCase(ConstantsUtils.NULL)) {
            itemMasterDTO.setPackageSize(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(itemMaster.getPackageSize())));
        }
        itemMasterDTO.setPackageSizeCode(itemMaster.getPackageSizeCode());
        itemMasterDTO.setItemClass(helperListUtil.getIdHelperDTOMap().get(itemMaster.getItemClass()));
        itemMasterDTO.setItemCode(itemMaster.getItemCode());
        itemMasterDTO.setLabelerCode(itemMaster.getLabelerCode());
        itemMasterDTO.setFormStr(itemMasterDTO.getFormStr());
        itemMasterDTO.setInternalNotes(itemMaster.getInternalNotes());
        if (itemMaster.getFirstSaleDate() != null) {
            itemMasterDTO.setFirstSaleDate(convertDateToDate(itemMaster
                    .getFirstSaleDate()));
        }

        if (itemMaster.getUpps() == CommonUtils.ZERO) {
            itemMasterDTO.setUpps(format2.format(itemMaster.getUpps()));

        } else {
            itemMasterDTO.setUpps(format2.format(itemMaster.getUpps()));

        }

        itemMasterDTO.setNdc8(itemMaster.getNdc8());
        itemMasterDTO.setNdc9(itemMaster.getNdc9());

        BrandMaster brandM = BrandMasterLocalServiceUtil.getBrandMaster(itemMaster.getBrandMasterSid());
        if (itemMaster.getBrandMasterSid() != 0) {

            itemMasterDTO.setBrandId(brandM.getBrandId());
            itemMasterDTO.setBrand(brandM.getBrandName());
            itemMasterDTO.setDisplayBrand(brandM.getDisplayBrand());

        }
        itemMasterDTO.setClottingFactorIndicator(itemMaster.getClottingFactorIndicator());
        itemMasterDTO.setPediatricExclusiveIndicator(itemMaster.getPediatricExclusiveIndicator());

        itemMasterDTO.setTherapeuticClass(helperListUtil.getIdHelperDTOMap().get(itemMaster.getTherapeuticClass()));
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Udcs.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq("masterSid", itemMaster.getItemMasterSid()));
        List<Udcs> list = UdcsLocalServiceUtil.dynamicQuery(dynamicQuery);
        for (int i = 0; i < list.size(); i++) {
            Udcs udcs = list.get(i);
            itemMasterDTO.setUdc1(helperListUtil.getIdHelperDTOMap().get(udcs.getUdc1()));
            itemMasterDTO.setUdc2(helperListUtil.getIdHelperDTOMap().get(udcs.getUdc2()));
            itemMasterDTO.setUdc3(helperListUtil.getIdHelperDTOMap().get(udcs.getUdc3()));
            itemMasterDTO.setUdc4(helperListUtil.getIdHelperDTOMap().get(udcs.getUdc4()));
            itemMasterDTO.setUdc5(helperListUtil.getIdHelperDTOMap().get(udcs.getUdc5()));
            itemMasterDTO.setUdc6(helperListUtil.getIdHelperDTOMap().get(udcs.getUdc6()));
        }

        if (itemMaster.getItemCategory() > 0) {

            itemMasterDTO.setItemCategory(helperListUtil.getIdHelperDTOMap().get(itemMaster.getItemCategory()));
        }

        if (itemMaster.getManufacturerId() != ConstantsUtils.ZERO && itemMaster.getManufacturerId() != null && !itemMaster.getManufacturerId().equals(ConstantsUtils.NULL) && !itemMaster.getManufacturerId().equals(StringUtils.EMPTY)) {
            final HelperDTO dto = new HelperDTO();
            final int companyId = Integer.parseInt(itemMaster.getManufacturerId().replace(ConstantsUtils.COMMA, StringUtils.EMPTY));
            dto.setId(companyId);
            dto.setDescription(getManufactureIdFromCompanyMaster(companyId));
            itemMasterDTO.setManufacturerIdDDLB(dto);
            itemMasterDTO.setManufacturerId(dto.getDescription());
            final CompanyMaster company = DAO.getCompanyMasterBySystemId(Integer.valueOf(itemMasterDTO.getManufacturerIdDDLB().getId()));

            itemMasterDTO.setManufacturerNo(company.getCompanyNo());
            itemMasterDTO.setManufacturerName(company.getCompanyName());

        } else {
            itemMasterDTO.setManufacturerIdDDLB(new HelperDTO(ConstantsUtils.SELECT_ONE));
        }

        final HelperDTO dto = new HelperDTO();
        dto.setId(itemMaster.getBrandMasterSid());
        dto.setDescription(brandM.getBrandName());
        itemMasterDTO.setBrandDdlb(dto);

        itemMasterDTO.setAuthorizedGeneric(itemMaster.getAuthorizedGeneric());
        itemMasterDTO.setNewFormulationIndicator(itemMaster.getNewFormulationIndicator());
        itemMasterDTO.setDualPricingIndicator(itemMaster.getDualPricingIndicator());
        itemMasterDTO.setItemTypeIndication(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf((StringUtils.isBlank(itemMaster.getItemTypeIndication())) ? ConstantsUtils.ZERO : itemMaster.getItemTypeIndication())));
        itemMasterDTO.setShelfLifeType(helperListUtil.getIdHelperDTOMap().get(itemMaster.getShelfLifeType()));
        itemMasterDTO.setShelfLife(itemMaster.getShelfLife());
        itemMasterDTO.setDosesPerUnit(itemMaster.getDosesPerUnit());

        if (itemMaster.getBaselineAmp() == CommonUtils.ZERO) {
            itemMasterDTO.setBaselineAmp(format2.format(itemMaster.getBaselineAmp()));

        } else {
            itemMasterDTO.setBaselineAmp(format2.format(itemMaster.getBaselineAmp()));

        }
        if (itemMaster.getBaseCpi() == CommonUtils.ZERO) {
            itemMasterDTO.setBaseCpi(format2.format(itemMaster.getBaseCpi()));
        } else {
            itemMasterDTO.setBaseCpi(format2.format(itemMaster.getBaseCpi()));
        }
        if (itemMaster.getObraBamp() == CommonUtils.ZERO) {
            itemMasterDTO.setObraBamp(format2.format(itemMaster
                    .getObraBamp()));

        } else {
            itemMasterDTO.setObraBamp(format2.format(itemMaster
                    .getObraBamp()));

        }

        if (itemMaster.getAcquiredAmp() == CommonUtils.ZERO) {
            itemMasterDTO.setAcquiredAmp(format2.format(itemMaster
                    .getAcquiredAmp()));
        } else {
            itemMasterDTO.setAcquiredAmp(format2.format(itemMaster.getAcquiredAmp()));

        }

        if (itemMaster.getAcquiredBamp() == CommonUtils.ZERO) {
            itemMasterDTO.setAcquiredBamp(format2.format(itemMaster
                    .getAcquiredBamp()));
        } else {
            itemMasterDTO.setAcquiredBamp(format2.format(itemMaster.getAcquiredBamp()));

        }

        itemMasterDTO.setOrganizationKey(itemMaster.getOrganizationKey());

        itemMasterDTO.setDra(format2.format(itemMaster.getDra()));

        itemMasterDTO.setItemSystemId(String.valueOf(itemMaster.getItemMasterSid()));
        if (itemMaster.getItemStartDate() != null) {
            itemMasterDTO.setItemStartDate(convertDateToDate(itemMaster
                    .getItemStartDate()));
        }
        if (itemMaster.getItemEndDate() != null) {
            itemMasterDTO.setItemEndDate(convertDateToDate(itemMaster
                    .getItemEndDate()));
        }

        if (itemMaster.getPackageSizeIntroDate() != null) {
            itemMasterDTO
                    .setPackageSizeIntroDate(convertDateToDate(itemMaster
                                    .getPackageSizeIntroDate()));
        }
        if (itemMaster.getClottingFactorStartDate() != null) {
            itemMasterDTO
                    .setClottingFactorStartDate(convertDateToDate(itemMaster
                                    .getClottingFactorStartDate()));
        }
        if (itemMaster.getClottingFactorEndDate() != null) {
            itemMasterDTO
                    .setClottingFactorEndDate(convertDateToDate(itemMaster
                                    .getClottingFactorEndDate()));
        }
        if (itemMaster.getPediatricExclusiveStartDate() != null) {
            itemMasterDTO
                    .setPediatricExclusiveStartDate(convertDateToDate(itemMaster
                                    .getPediatricExclusiveStartDate()));
        }
        if (itemMaster.getPediatricExclusiveEndDate() != null) {
            itemMasterDTO
                    .setPediatricExclusiveEndDate(convertDateToDate(itemMaster
                                    .getPediatricExclusiveEndDate()));
        }

        if (itemMaster.getNewFormulation() != null) {
            itemMasterDTO.setNewFormulation(itemMaster.getNewFormulation());
        }

        if (itemMaster.getAcquisitionDate() != null) {
            itemMasterDTO
                    .setAcquisitionDate(convertDateToDate(itemMaster
                                    .getAcquisitionDate()));
        }

        if (itemMaster.getNonFederalExpirationDate() != null) {
            itemMasterDTO.setNonFederalExpirationDate(convertDateToDate(itemMaster.getNonFederalExpirationDate()));
        }

        if (itemMaster.getAuthorizedGenericStartDate() != null) {
            itemMasterDTO
                    .setAuthorizedGenericStartDate(convertDateToDate(itemMaster
                                    .getAuthorizedGenericStartDate()));
        }

        if (itemMaster.getAuthorizedGenericEndDate() != null) {
            itemMasterDTO
                    .setAuthorizedGenericEndDate(convertDateToDate(itemMaster
                                    .getAuthorizedGenericEndDate()));
        }

        if (itemMaster.getMarketTerminationDate() != null) {
            itemMasterDTO
                    .setMarketTerminationDate(convertDateToDate(itemMaster
                                    .getMarketTerminationDate()));
        }

        if (itemMaster.getNewFormulationStartDate() != null) {
            itemMasterDTO
                    .setNewFormulationStartDate(convertDateToDate(itemMaster
                                    .getNewFormulationStartDate()));
        }

        if (itemMaster.getNewFormulationEndDate() != null) {
            itemMasterDTO
                    .setNewFormulationEndDate(convertDateToDate(itemMaster
                                    .getNewFormulationEndDate()));
        }

        if (itemMaster.getDiscontinuationDate() != null) {
            itemMasterDTO
                    .setDiscontinuationDate(convertDateToDate(itemMaster
                                    .getDiscontinuationDate()));
        }

        if (itemMaster.getLastLotExpirationDate() != null) {
            itemMasterDTO
                    .setLastLotExpirationDate(convertDateToDate(itemMaster
                                    .getLastLotExpirationDate()));
        }

        if (itemMaster.getDivestitureDate() != null) {
            itemMasterDTO
                    .setDivestitureDate(convertDateToDate(itemMaster
                                    .getDivestitureDate()));
        }

        if (itemMaster.getNonFederalExpirationDate() != null) {
            itemMasterDTO
                    .setNonFederalExpirationDate(convertDateToDate(itemMaster
                                    .getNonFederalExpirationDate()));
        }

        if (itemMaster.getBaseCpiPeriod() != null) {
            itemMasterDTO
                    .setBaseCpiPeriod(convertDateToDate(itemMaster.getBaseCpiPeriod()));
        }

        itemMasterDTO.setCreatedDate(itemMaster.getCreatedDate());
        itemMasterDTO.setModifiedDate(itemMaster.getModifiedDate());
        String createdBy = StplSecurity.userMap.get(itemMaster.getCreatedBy());
        itemMasterDTO.setCreatedBy(createdBy == null ? StringUtils.EMPTY : createdBy);

        try {
            User ModifiedUser = (itemMaster.getModifiedBy() == 0) ? null : ((User) securityDto.getUserByUserId(itemMaster.getModifiedBy()));
            itemMasterDTO.setModifiedBy(ModifiedUser == null ? StringUtils.EMPTY : ModifiedUser.getFullName());
        } catch (Exception e) {
            LOGGER.error(e);
        }

        final List<ItemIdentifier> irtList = DAO.getItemIrtIdentifierByItemSystemId(identifier1);

        final List<ItemPricing> pricingList = DAO.getItemPricingByItemSystemId(identifier1);

        final List<ItemIrtIdentifierDTO> itemIrtIdentifierList = new ArrayList<ItemIrtIdentifierDTO>();
        final List<ItemPricingDTO> itemPricingIdentifierList = new ArrayList<ItemPricingDTO>();
        if (!irtList.isEmpty()) {
            for (int i = 0; i < irtList.size(); i++) {
                final ItemIrtIdentifierDTO identifierDTO = new ItemIrtIdentifierDTO();
                final ItemIdentifier identifier = irtList.get(i);
                if (!ConstantsUtils.INBOUND_STATUS_D.equals(identifier.getInboundStatus())) {

                    identifierDTO.setItemSystemId(identifier.getItemMasterSid());

                    final ItemQualifier qualifier = DAO.getItemIrtQualifierByQualifierId(identifier
                            .getItemQualifierSid());
                    identifierDTO.setItemIrtQualifierName(qualifier
                            .getItemQualifierName());
                    identifierDTO.setEntityCodeSid(identifier.getEntityCode());
                    if (identifier.getEntityCode() != null && NumberUtils.isNumber(identifier.getEntityCode())) {
                        final CompanyMaster company = DAO.getCompanyMasterBySystemId(Integer.valueOf(identifier.getEntityCode()));
                        identifierDTO.setEntityCode(company.getCompanyId());
                        identifierDTO.setEntityCodeName(company.getCompanyName());
                    }
                    identifierDTO.setIdentifierStatus(helperListUtil.getIdHelperDTOMap().get(identifier.getIdentifierStatus()));
                    if (identifierDTO.getIdentifierStatus().getId() != 0) {
                        identifierDTO.setIdentifierStatusView(identifierDTO
                                .getIdentifierStatus().getDescription());
                    }

                    identifierDTO.setItemIdentifier(identifier
                            .getItemIdentifierValue());
                    identifierDTO.setItemIrtQualifierId(identifier
                            .getItemQualifierSid());
                    if (identifier.getStartDate() != null) {
                        identifierDTO
                                .setStartDate(
                                        convertDateToDateFormat(identifier
                                                .getStartDate()));
                    }
                    if (identifier.getEndDate() != null) {
                        identifierDTO.setEndDate(convertDateToDateFormat(identifier.getEndDate()));
                    }
                    if (identifier.getStartDate() != null) {
                        identifierDTO
                                .setViewStartDate(
                                        CommonUtils.convertDateToString(identifier
                                                .getStartDate()));
                    }
                    if (identifier.getEndDate() != null) {
                        identifierDTO.setViewEndDate(CommonUtils.convertDateToString(identifier.getEndDate()));
                    }
                    identifierDTO.setIrtIdentifierSystemId(identifier.getItemIdentifierSid());
                    identifierDTO.setCreatedDate(identifier.getCreatedDate());
                    String createdBy1 = StplSecurity.userMap.get(itemMaster.getCreatedBy());
                    identifierDTO.setCreatedByValue(itemMaster.getCreatedBy());
                    identifierDTO.setModifiedByValue(itemMaster.getModifiedBy());
                    identifierDTO.setCreatedBy(createdBy == null ? StringUtils.EMPTY : createdBy1);

                    try {
                        User ModifiedUser = (itemMaster.getModifiedBy() == 0) ? null : ((User) securityDto.getUserByUserId(itemMaster.getModifiedBy()));
                        identifierDTO.setModifiedBy(ModifiedUser == null ? StringUtils.EMPTY : ModifiedUser.getFullName());
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                    identifierDTO.setModifiedDate(identifier.getModifiedDate());

                    itemIrtIdentifierList.add(identifierDTO);
                }
            }

        }
        if (!pricingList.isEmpty()) {
            for (int i = 0; i < pricingList.size(); i++) {
                final ItemPricingDTO priceDTO = new ItemPricingDTO();
                final ItemPricing form = (ItemPricing) pricingList.get(i);
                if (!ConstantsUtils.INBOUND_STATUS_D.equals(form.getInboundStatus())) {
                    DecimalFormat priceFormat = new DecimalFormat("###,###,###.00##");
                    priceDTO.setItemPrice(ConstantsUtils.DOLLAR + String.valueOf(priceFormat.format(form.getItemPrice())));

                    priceDTO.setPricingCodeStatus(helperListUtil.getIdHelperDTOMap().get(form.getPricingCodeStatus()));
                    if (priceDTO.getPricingCodeStatus().getId() != 0) {
                        priceDTO.setPricingCodeStatusView(priceDTO.getPricingCodeStatus().getDescription());
                    }
                    priceDTO.setItemUom(helperListUtil.getIdHelperDTOMap().get(form.getItemUom()));
                    if (priceDTO.getItemUom().getId() != 0) {
                        priceDTO.setItemUomView(priceDTO.getItemUom().getDescription());

                    }
                    final ItemPricingQualifier qualifier = DAO.getItemPricingQualifierByQualifierId(form
                            .getItemPricingQualifierSid());
                    priceDTO.setIdentifierCodeQualifierName(qualifier
                            .getItemPricingQualifierName());
                    priceDTO.setItemPricingQualifierName(qualifier.getItemPricingQualifierName());
                    priceDTO.setItemPricingQualifierId(qualifier.getItemPricingQualifierSid());
                    priceDTO.setItemSystemId(form.getItemMasterSid());
                    priceDTO.setEntityCodeSid(form.getEntityCode());
                    if (form.getEntityCode() != null && NumberUtils.isNumber(form.getEntityCode())) {
                        final CompanyMaster company = DAO.getCompanyMasterBySystemId(Integer.valueOf(form.getEntityCode()));
                        priceDTO.setEntityCode(company.getCompanyId());
                    }
                    priceDTO.setSource(form.getSource());

                    if (form.getStartDate() != null) {
                        priceDTO.setStartDate(convertDate((Date) form.getStartDate()));
                    }
                    if (form.getEndDate() != null) {
                        priceDTO.setEndDate((Date) form.getEndDate());
                    }
                    if (form.getStartDate() != null) {
                        priceDTO.setPricingStartDate(form
                                .getStartDate());
                    }
                    if (form.getEndDate() != null) {
                        priceDTO.setPricingEndDate(form.getEndDate());
                    }
                    priceDTO.setSource(form.getSource());
                    priceDTO.setItemPricingSystemId(form.getItemPricingSid());
                    priceDTO.setCreatedDate(form.getCreatedDate());
                    String createdBy1 = StplSecurity.userMap.get(itemMaster.getCreatedBy());
                    priceDTO.setCreatedBy(createdBy == null ? StringUtils.EMPTY : createdBy1);
                    priceDTO.setCreatedByValue(itemMaster.getCreatedBy());
                    try {
                        User ModifiedUser = (itemMaster.getModifiedBy() == 0) ? null : ((User) securityDto.getUserByUserId(itemMaster.getModifiedBy()));
                        priceDTO.setModifiedBy(ModifiedUser == null ? StringUtils.EMPTY : ModifiedUser.getFullName());
                        priceDTO.setModifiedByValue(itemMaster.getModifiedBy());
                    } catch (Exception e) {
                        LOGGER.error(e);
                        e.printStackTrace();
                    }
                    priceDTO.setModifiedDate(form.getModifiedDate());
                    itemPricingIdentifierList.add(priceDTO);
                }
            }

        }
        itemMasterDTO.setItemIdentifierList(itemIrtIdentifierList);
        itemMasterDTO.setPricingIdentifierList(itemPricingIdentifierList);
        itemMasterDTO.setItemBatchId(itemMaster.getBatchId());

        LOGGER.info("returns ItemMasterDTO");
        return itemMasterDTO;
    }

    /**
     * Gets the manufacturer as list.
     *
     * @return the manufacturer
     */
    public List<HelperDTO> getManufacturer() throws PortalException, SystemException {
        LOGGER.info("getManufacturer() ");
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        HelperDTO helperTable;

        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(CompanyMaster.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.COMPANY_TYPE,
                ConstantsUtils.MANUFACTURE));

        // Added to check INBOUND_STATUS is A. ETL Soft delete data should not be included, where INBOUND_STATUS is D
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D)));

        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.COMPANY_TYPE));
        final List<CompanyMaster> list = DAO.getCompanyMasterList(cfpDynamicQuery);

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                helperTable = new HelperDTO(list.get(i).getCompanyMasterSid(),
                        list.get(i).getCompanyId());
                helperList.add(helperTable);
            }
        }

        LOGGER.info("returns helperList size" + helperList.size());

        return helperList;
    }

    /**
     * Gets the manufacturer details as company master.
     *
     * @param systemId the system id
     * @return the manufacturer details
     */
    public CompanyMaster getManufacturerDetails(final int systemId) throws SystemException, PortalException {
        LOGGER.info("Enters getManufacturerDetails() ");

        final CompanyMaster company = DAO.getCompanyMasterBySystemId(systemId);

        LOGGER.info("returns CompanyMaster");
        return company;

    }

    /**
     * Logic for Convert date to another required date format.
     *
     * @param startDate the start date
     * @return the date
     * @throws ParseException the parse exception
     */
    public Date convertDate(final Date startDate) throws ParseException {

        Date date;
        final DateFormat inputFormat = new SimpleDateFormat(ConstantsUtils.DATEFORMAT);

        date = inputFormat.parse(inputFormat.format(startDate));
        return date;
    }

    /**
     * Logic for Convert date to another required date format.
     *
     * @param date the date
     * @return the date
     * @throws ParseException the parse exception
     */
    public Date convertDateToDate(final Date date) throws ParseException {

        Date dateFormat;
        final DateFormat inputFormat = new SimpleDateFormat(ConstantsUtils.LONGDATEFORMAT);
        dateFormat = inputFormat.parse(inputFormat.format(date));
        return dateFormat;

    }

    /**
     * Logic for Delete item master by id.
     *
     * @param itemSystemId the item system id
     * @return the item master
     */
    public ItemMaster deleteItemMasterById(final int itemSystemId) throws SystemException, PortalException, Exception {

        LOGGER.info("Entering deleteItemMasterById P1: " + itemSystemId);
        final ItemMaster item = DAO.getItemMasterBySystemId(itemSystemId);
        item.setInboundStatus(ConstantsUtils.INBOUND_STATUS_D);
        DAO.updateItemMaster(item);
        final List<ItemIdentifier> identifierList = DAO.getItemIrtIdentifierByItemSystemId(itemSystemId);
        final List<ItemPricing> pricingList = DAO.getItemPricingByItemSystemId(itemSystemId);

        if (!identifierList.isEmpty()) {
            for (int i = 0; i < identifierList.size(); i++) {
                final ItemIdentifier iden = (ItemIdentifier) identifierList
                        .get(i);
                iden.setInboundStatus(ConstantsUtils.INBOUND_STATUS_D);
                DAO.updateItemIrtIdentifier(iden);

            }
        }
        if (!pricingList.isEmpty()) {
            for (int i = 0; i < pricingList.size(); i++) {
                final ItemPricing iden = (ItemPricing) pricingList.get(i);
                iden.setInboundStatus(ConstantsUtils.INBOUND_STATUS_D);
                DAO.updateItemPricing(iden);

            }
        }

        try {
            final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MasterDataFiles.class);
            dynamicQuery.add(RestrictionsFactoryUtil.eq("masterTableSid", itemSystemId));
            dynamicQuery.add(RestrictionsFactoryUtil.like("masterTableName", ConstantsUtils.ITEM_MASTER));
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

        LOGGER.info("return ItemMaster ");
        return item;
    }

    /**
     * Gets the item qualifier.
     *
     * @return the item qualifier
     */
    public List<HelperDTO> getItemQualifier() throws SystemException, PortalException {
        LOGGER.info("Entering getItemQualifier");

        final List<HelperDTO> list = new ArrayList<HelperDTO>();

        final DynamicQuery itemIrtDynamicQuery = DynamicQueryFactoryUtil
                .forClass(ItemQualifier.class);

        itemIrtDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.ITEM_QUALIFIER_NAME));
        final List<ItemQualifier> qualifierList = DAO.getItemIrtQualifierList(itemIrtDynamicQuery);

        for (int i = 0; i < qualifierList.size(); i++) {

            final ItemQualifier qualifier = (ItemQualifier) qualifierList
                    .get(i);
            list.add(new HelperDTO(qualifier.getItemQualifierSid(), qualifier
                    .getItemQualifierValue()));

        }
        LOGGER.info("return list size " + list.size());

        return list;

    }

    /**
     * Logic for Convert date to another required date format.
     *
     * @param date the date
     * @return the date
     * @throws ParseException the parse exception
     */
    public Date convertDateToDateFormat(final Date date) throws ParseException, Exception {
        Date formattedDate;
        final DateFormat inputFormat = new SimpleDateFormat(ConstantsUtils.DATEFORMAT);
        formattedDate = inputFormat.parse(inputFormat.format(date));
        return formattedDate;
    }

    /**
     * Gets the pricing qualifier.
     *
     * @return the pricing qualifier
     */
    public List<HelperDTO> getPricingQualifier() throws SystemException, Exception {
        LOGGER.info("Entering getPricingQualifier");

        final List<HelperDTO> list = new ArrayList<HelperDTO>();

        final DynamicQuery itemPricingDynamicQuery = DynamicQueryFactoryUtil
                .forClass(ItemPricingQualifier.class);

        itemPricingDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.ITEM_PRICING_QUAL_NAME));
        final List<ItemPricingQualifier> qualifierList = DAO.getItemPricingQualifierList(itemPricingDynamicQuery);

        for (int i = 0; i < qualifierList.size(); i++) {
            final ItemPricingQualifier qualifier = (ItemPricingQualifier) qualifierList
                    .get(i);
            list.add(new HelperDTO(qualifier.getItemPricingQualifierSid(),
                    qualifier.getItemPricingQualifierName()));
        }

        LOGGER.info("returns list size " + list.size());
        return list;

    }

    /**
     * Gets the item type.
     *
     * @param listType the list type
     * @return the item type
     */
    public List<HelperDTO> getItemType(final String listType) throws SystemException, Exception {

        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();

        LOGGER.info("Entering getItemType P1:" + listType);
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(HelperTable.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.LIST_NAME,
                listType));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.DESCRIPTION));
        final List<HelperTable> list = DAO.getHelperTableList(cfpDynamicQuery);

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable
                        .getDescription()));

            }
        }
        LOGGER.info("returns size " + helperList.size());

        return helperList;
    }

    public List<ItemMaster> getNdc() throws SystemException, Exception {
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property("ndc8"));
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(ItemMaster.class);
        query.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
        List<ItemMaster> list = HelperTableLocalServiceUtil.dynamicQuery(query);

        return list;
    }

    /**
     * Gets the drop down list.
     *
     * @param listName the list name
     * @return the drop down list
     */
    public List<HelperDTO> getDropDownList(final String listName) throws PortalException, SystemException {

        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();

        LOGGER.info("Entering getDropDownList P1: listName " + listName);
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(HelperTable.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.LIST_NAME,
                listName));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.DESCRIPTION));
        final List<HelperTable> list = DAO.getHelperTableList(cfpDynamicQuery);

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable
                        .getDescription()));

            }
        }
        LOGGER.info("returns helperList size " + helperList.size());

        return helperList;
    }

    /**
     * Gets the brands as list.
     *
     * @return the brands
     */
    public List<HelperDTO> getBrands() throws SystemException, Exception {
        LOGGER.info("Enters getBrands() ");
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        HelperDTO helperTable;

        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(BrandMaster.class);
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.BRAND_NAME));
        final List<BrandMaster> list = DAO.getBrandMasterList(cfpDynamicQuery);

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                helperTable = new HelperDTO(list.get(i).getBrandMasterSid(), list.get(i).getBrandName());
                helperList.add(helperTable);
            }
        }
        LOGGER.info("returns helperList size " + helperList.size());

        return helperList;
    }

    /**
     * Gets the brand id as string.
     *
     * @param brandName the brand name
     * @return the brand id
     */
    public String getBrandId(final String brandName) throws SystemException, Exception {

        final SearchItemForm searchItemForm = new SearchItemForm();

        LOGGER.info("Enters getBrandId() P1: brandName " + brandName);
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(BrandMaster.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.BRAND_NAME,
                brandName));
        final List<BrandMaster> list = DAO.getBrandMasterList(cfpDynamicQuery);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final BrandMaster brandMaster = (BrandMaster) list.get(i);
                searchItemForm.setBrandId(brandMaster.getBrandId());
            }
        }

        return searchItemForm.getBrandId();
    }

    /**
     * Gets the count.
     *
     * @param searchItemForm the search item form
     * @return the count
     */
    public int getCount(final ErrorfulFieldGroup searchItemForm, final BeanSearchCriteria criteria) throws PortalException, SystemException {

        Map<String, Object> parameters = getParametersValue(searchItemForm, criteria);

        return DAO.getItemMasterListByQualifierId(StringUtils.EMPTY, StringUtils.EMPTY,
                StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, null,
                0, StringUtils.EMPTY, StringUtils.EMPTY, null, null, parameters).size();
    }

    /**
     * Gets the total count.
     *
     * @return the total count
     * @throws PortalException
     * @throws SystemException
     */
    public int getTotalCount() throws PortalException, SystemException {
        LOGGER.info("Entering getTotalCount ");

        int count = 0;
        count = DAO.getItemMasterTotalCount();

        return count;

    }

    /**
     * Gets the item irt qualifier by name.
     *
     * @param qualifierName the qualifier name
     * @return the item irt qualifier by name
     */
    public ItemQualifier getItemIrtQualifierByName(final String qualifierName) throws SystemException, NoSuchItemQualifierException {
        ItemQualifier itemIrtQualifier = null;
        try {
            itemIrtQualifier = DAO.getItemIrtQualifierByName(qualifierName);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return itemIrtQualifier;
    }

    /**
     * Gets the item irt identifier list.
     *
     * @param query the query
     * @return the item irt identifier list
     */
    public List<ItemIdentifier> getItemIrtIdentifierList(final DynamicQuery query) throws PortalException, SystemException {
        return DAO.getItemIrtIdentifierList(query);
    }

    /**
     * Gets the item pricing qualifier by code qualifier name.
     *
     * @param codeQualifierName the code qualifier name
     * @return the item pricing qualifier by code qualifier name
     */
    public ItemPricingQualifier getItemPricingQualifierByCodeQualifierName(final String codeQualifierName) throws SystemException, NoSuchItemPricingQualifierException {
        return DAO.getItemPricingQualifierByCodeQualifierName(codeQualifierName);
    }

    public static int getLazyManufactureIdCount(String filter) throws PortalException, SystemException {
        filter = StringUtils.trimToEmpty(filter) + ConstantsUtils.PERCENCTAGE;
        LOGGER.info("Entering getLazyCompanyQualifierNameCount method with filterText :" + filter);
        List<Object[]> qualifierList;
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.COMPANY_ID, filter));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D)));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_TYPE, com.stpl.app.util.GeneralCommonUtils.getHelperCode("COMPANY_TYPE", ConstantsUtils.MANUFACTURE)));
        cfpDynamicQuery.setProjection(ProjectionFactoryUtil.countDistinct(ConstantsUtils.COMPANY_MASTER_ID));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.COMPANY_ID));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ConstantsUtils.COMPANY_ID, StringUtils.EMPTY)));
        qualifierList = DAO.getManufactureIdList(cfpDynamicQuery);
        LOGGER.info("Ending getLazyCompanyQualifierNameCount method with filterText with count :" + qualifierList.get(0));
        return Integer.parseInt(String.valueOf(qualifierList.get(0)));
    }

    public static List<HelperDTO> getLazyManufactureIdResults(final int startIndex, final int end, final String filter, final HelperDTO manufactureId, final boolean filerGeneraterFlag) throws PortalException, SystemException {
        final List<HelperDTO> list = new ArrayList<HelperDTO>();
        int startValue = startIndex;
        int endValue = end;
        if (startIndex == ConstantsUtils.ZERO_INT) {
            endValue = end - 1;
        } else {
            startValue = startIndex - 1;
            endValue = end - 1;
        }
        LOGGER.info("Entering getLazyManufactureIdResults method with filterText :" + filter);
        final String filterString = StringUtils.trimToEmpty(filter) + ConstantsUtils.PERCENCTAGE;
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
        cfpDynamicQuery.setLimit(startValue, endValue);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COMPANY_TYPE, com.stpl.app.util.GeneralCommonUtils.getHelperCode("COMPANY_TYPE", ConstantsUtils.MANUFACTURE)));
        if (manufactureId != null && manufactureId.getId() != 0) {
            cfpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.COMPANY_MASTER_ID, manufactureId.getId()));
        }
        // Added to check INBOUND_STATUS is A. ETL Soft delete data should not be included, where INBOUND_STATUS is D
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D)));
        final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.COMPANY_MASTER_ID));
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.COMPANY_ID));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ConstantsUtils.COMPANY_ID, StringUtils.EMPTY)));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.COMPANY_ID));
        if (filter != null) {
            cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.COMPANY_ID, filterString));
        }
        cfpDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projectionList));
        final List<Object[]> returnList = DAO.getManufactureIdList(cfpDynamicQuery);
        HelperDTO helperTable;
        if (startIndex == ConstantsUtils.ZERO_INT) {
            helperTable = new HelperDTO();
            if (filerGeneraterFlag) {
                helperTable.setDescription(ConstantsUtils.SHOW_ALL);
            } else {
                helperTable.setDescription(ConstantsUtils.SELECT_ONE);
            }
            list.add(helperTable);
            if (manufactureId != null && manufactureId.getId() != 0) {
                list.add(manufactureId);
            }
        }
        for (final Iterator<Object[]> iterator = returnList.iterator(); iterator.hasNext();) {
            final Object[] value = iterator.next();
            helperTable = new HelperDTO();
            helperTable.setId(value[0] != null ? Integer.parseInt(value[0].toString()) : 0);
            helperTable.setDescription(value[1] != null ? value[1].toString() : StringUtils.EMPTY);
            if (!StringUtils.EMPTY.equals(helperTable.getDescription())) {
                list.add(helperTable);
            }
        }
        LOGGER.info("Ending getLazyManufactureIdResults  return list size :" + +list.size());
        return list;
    }

    private String getManufactureIdFromCompanyMaster(final int companyMasterId) throws PortalException, SystemException {
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
        cfpDynamicQuery.setProjection(ProjectionFactoryUtil.property(ConstantsUtils.COMPANY_ID));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq("companyMasterSid", companyMasterId));
        final List<String> returnList = DAO.getManufactureIdList(cfpDynamicQuery);
        return returnList.get(0);
    }

    /**
     * getting count for Brand
     *
     * @param filterText
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public static int getLazyBrandCount(String filterText) throws PortalException, SystemException {
        filterText = StringUtils.trimToEmpty(filterText) + ConstantsUtils.PERCENCTAGE;
        LOGGER.info("Entering getLazyBrandCount method with filterText" + filterText);
        List<Object[]> qualifierList;
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(BrandMaster.class);
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.BRAND_NAME, filterText));
        ifpDynamicQuery.setProjection(ProjectionFactoryUtil.count(ConstantsUtils.BRAND_NAME));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
        qualifierList = DAO.getBrandList(ifpDynamicQuery);
        brandCount = Integer.parseInt(String.valueOf(qualifierList.get(0)));
        LOGGER.info("Ending getLazyBrandCount method : returning count :" + brandCount);
        return brandCount;
    }

    /**
     * getting results for Brand
     *
     * @param start
     * @param end
     * @param filterText
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public static List<HelperDTO> getLazyBrandResults(final int start, final int end, String filterText, final HelperDTO brand, boolean isFilter) throws PortalException, SystemException {
        filterText = StringUtils.trimToEmpty(filterText) + ConstantsUtils.PERCENCTAGE;
        LOGGER.info("Entering getLazyBrandCount method with filterText" + filterText);
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

        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(BrandMaster.class);
        ifpDynamicQuery.setLimit(startValue, endValue);
        final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.BRAND_MASTER_SID));
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.BRAND_NAME));
        ifpDynamicQuery.setProjection(projectionList);
        ifpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.BRAND_NAME));
        if (filterText != null) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.BRAND_NAME, filterText));
        }
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
        if (brand != null && brand.getId() != 0) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.BRAND_MASTER_SID, brand.getId()));
        }

        qualifierList = DAO.getBrandList(ifpDynamicQuery);

        HelperDTO dto;
        if (start == Constants.ZERO) {
            dto = new HelperDTO(0, isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE);
            list.add(dto);
            if (brand != null && brand.getId() != 0) {

                list.add(brand);
            }

        }
        for (final Iterator<Object[]> iterator = qualifierList.iterator(); iterator.hasNext();) {
            final Object[] value = iterator.next();
            dto = new HelperDTO(StringUtils.EMPTY);
            dto.setId(value[0] != null ? Integer.parseInt(value[0].toString()) : 0);
            dto.setDescription(value[1] != null ? value[1].toString() : StringUtils.EMPTY);
            list.add(dto);
        }
        LOGGER.info("return Brand size -" + list.size());
        return list;
    }

    /**
     * getting count for CompanyQualifierName
     *
     * @param filterText
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public static int getLazyItemQualifierNameCount(String filterText,boolean isEditList) throws PortalException, SystemException {
        filterText = StringUtils.trimToEmpty(filterText) + ConstantsUtils.PERCENCTAGE;
        LOGGER.info("Entering getLazyCompanyQualifierNameCount method with filterText" + filterText);
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemQualifier.class);
        ifpDynamicQuery.setProjection(ProjectionFactoryUtil.count(ConstantsUtils.ITEM_QUAL_NAME));
        final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_QUALIFIER_SID));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_QUAL_NAME, filterText));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ConstantsUtils.ITEM_QUAL_NAME, StringUtils.EMPTY)));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.ITEM_QUAL_NAME));
        final List<Object[]> qualifierList = DAO.itemIrtQualifierNameList(ifpDynamicQuery);
        itemQualifierNameCount = Integer.parseInt(String.valueOf(qualifierList.get(0)));
        if (itemQualifierNameCount == 0 && isEditList) {
            itemQualifierNameCount++;
        }
        LOGGER.info("Ending getLazyPriceTypeCount method : returning count :" + itemPricingQualifierNameCount);
        return itemQualifierNameCount;
    }

    /**
     * getting results for CompanyQualifierName
     *
     * @param start
     * @param end
     * @param filterText
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public static List<HelperDTO> getLazyItemQualifierNameResults(final int start, final int end, final String filteredText, final boolean editListFlag) throws PortalException, SystemException {
        String filterText = StringUtils.trimToEmpty(filteredText) + ConstantsUtils.PERCENCTAGE;
        LOGGER.info("Entering getLazyCompanyQualifierNameCount method with filterText" + filterText);
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

        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemQualifier.class);
        ifpDynamicQuery.setLimit(startValue, endValue);
        final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_QUALIFIER_SID));
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_QUAL_NAME));
        ifpDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projectionList));
        ifpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.ITEM_QUAL_NAME));
        if (filterText != null) {
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_QUAL_NAME, filterText));
        }
        ifpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ConstantsUtils.ITEM_QUAL_NAME, StringUtils.EMPTY)));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.ITEM_QUAL_NAME));
        final List<Object[]> qualifierList = DAO.itemIrtQualifierNameList(ifpDynamicQuery);

        HelperDTO dto;
        if (start == Constants.ZERO) {
            dto = new HelperDTO(ConstantsUtils.SELECT_ONE);
            list.add(dto);
        }
        for (final Iterator<Object[]> iterator = qualifierList.iterator(); iterator.hasNext();) {
            final Object[] value = iterator.next();
            dto = new HelperDTO(StringUtils.EMPTY);
            dto.setId(value[0] != null ? Integer.parseInt(value[0].toString()) : 0);
            dto.setDescription(value[1] != null ? value[1].toString() : StringUtils.EMPTY);
            if (!StringUtils.EMPTY.equals(dto.getDescription())) {
                list.add(dto);
            }
        }
        if (editListFlag) {
            filterText = filterText.replace("*", StringUtils.EMPTY);
            filterText = filterText.replace(ConstantsUtils.PERCENCTAGE, StringUtils.EMPTY);

            filterText = filterText.toUpperCase(Locale.ENGLISH);
            if (!StringUtils.EMPTY.equals(filterText) && Constants.EDIT_LIST.startsWith(filterText)) {
                dto = new HelperDTO(ConstantsUtils.EDITLIST);
                list.add(dto);
            }
            if (StringUtils.EMPTY.equals(filterText) && itemQualifierNameCount != 0 && itemQualifierNameCount == end - 2) {
                dto = new HelperDTO(ConstantsUtils.EDITLIST);
                list.add(dto);
            }
        }
        LOGGER.info("return CompanyQualifier size -" + list.size());
        return list;
    }

    private String getBrandIdFromBrandMaster(final String brand) throws PortalException, SystemException {
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(BrandMaster.class);
        cfpDynamicQuery.setProjection(ProjectionFactoryUtil.property(ConstantsUtils.BRAND_MASTER_SID));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.BRAND_NAME, brand));
        final List<Object> returnList = DAO.getBrandList(cfpDynamicQuery);
        return String.valueOf(returnList.get(0));
    }

    public List<?> getDisplayBrandFromBrandMaster() throws PortalException, SystemException {
        try {
            final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(BrandMaster.class);
            cfpDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(ConstantsUtils.DISPLAY_BRAND)));
            cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.DISPLAY_BRAND));
            cfpDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.DISPLAY_BRAND));
            cfpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.DISPLAY_BRAND, StringUtils.EMPTY));
            cfpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
            List list = DAO.getBrandList(cfpDynamicQuery);

            return list;
        } catch (Exception e) {
            LOGGER.error(e);
                        e.printStackTrace();
            return null;
        }
    }

    /**
     * To get price type count
     *
     * @param filterText
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public static int getLazyPriceTypeCount(final String filterText,boolean priceType) throws PortalException, SystemException {
        final String filter = StringUtils.trimToEmpty(filterText) + ConstantsUtils.PERCENCTAGE;
        LOGGER.debug("Entering getLazyPriceTypeCount method with filterText :" + filter);
        List<Object[]> qualifierList;
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemPricingQualifier.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_PRICING_QUAL_NAME, filter));
        cfpDynamicQuery.setProjection(ProjectionFactoryUtil.countDistinct(ConstantsUtils.ITEM_PRICING_QUALIFIER_SID));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.ITEM_PRICING_QUAL_NAME));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ConstantsUtils.ITEM_PRICING_QUAL_NAME, StringUtils.EMPTY)));
        qualifierList = DAO.getItemPricingTypeList(cfpDynamicQuery);
        LOGGER.debug("Ending getLazyPriceTypeCount method with filterText with count :" + qualifierList.get(0));
        itemPricingQualifierNameCount = Integer.parseInt(String.valueOf(qualifierList.get(0)));
        if(itemPricingQualifierNameCount==0 && priceType){
           itemPricingQualifierNameCount++; 
        }
        LOGGER.info("Ending getLazyPriceTypeCount method : returning count :" + itemPricingQualifierNameCount);
        return itemPricingQualifierNameCount;
    }

    public static List<HelperDTO> getLazyPriceTypeResults(final int startIndex, final int end, final String filter, final boolean editListFlag) throws PortalException, SystemException {
        final List<HelperDTO> list = new ArrayList<HelperDTO>();
        int startValue = startIndex;
        int endValue = end;
        if (startIndex == ConstantsUtils.ZERO_INT) {
            endValue = end - 1;
        } else {
            startValue = startIndex - 1;
            endValue = end - 1;
        }
        String filterText = StringUtils.trimToEmpty(filter) + ConstantsUtils.PERCENCTAGE;
        LOGGER.debug("Entering getLazyPriceTypeResults method with filterText :" + filterText);
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemPricingQualifier.class);
        cfpDynamicQuery.setLimit(startValue, endValue);
        final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_PRICING_QUALIFIER_SID));
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_PRICING_QUAL_NAME));
        if (filter != null) {
            cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_PRICING_QUAL_NAME, filterText));
        }
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ConstantsUtils.ITEM_PRICING_QUAL_NAME, StringUtils.EMPTY)));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.ITEM_PRICING_QUAL_NAME));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.ITEM_PRICING_QUAL_NAME));
        cfpDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projectionList));
        final List<Object[]> returnList = DAO.getItemPricingTypeList(cfpDynamicQuery);
        HelperDTO helperTable;
        if (startIndex == ConstantsUtils.ZERO_INT) {
            helperTable = new HelperDTO();
            helperTable.setDescription(ConstantsUtils.SELECT_ONE);
            list.add(helperTable);
        }
        for (final Iterator<Object[]> iterator = returnList.iterator(); iterator.hasNext();) {
            final Object[] value = iterator.next();
            helperTable = new HelperDTO();
            helperTable.setId(value[0] != null ? Integer.parseInt(value[0].toString()) : 0);
            helperTable.setDescription(value[1] != null ? value[1].toString() : StringUtils.EMPTY);
            if (!StringUtils.EMPTY.equals(helperTable.getDescription())) {
                list.add(helperTable);
            }
        }

        if (editListFlag) {
            filterText = filterText.replace("*", StringUtils.EMPTY);
            filterText = filterText.replace(ConstantsUtils.PERCENCTAGE, StringUtils.EMPTY);

            filterText = filterText.toUpperCase(Locale.ENGLISH);
            if (!StringUtils.EMPTY.equals(filterText) && Constants.EDIT_LIST.startsWith(filterText)) {
                helperTable = new HelperDTO(ConstantsUtils.EDIT_LIST);
                list.add(helperTable);
            }
            if (StringUtils.EMPTY.equals(filterText) && itemPricingQualifierNameCount != 0 && itemPricingQualifierNameCount == end - 2) {
                helperTable = new HelperDTO(ConstantsUtils.EDIT_LIST);
                list.add(helperTable);
            }
        }
        LOGGER.debug("Ending getLazyPriceTypeResults  return list size :" + +list.size());
        return list;
    }

    public static Map<Integer, String> getCodeDescription() throws PortalException, SystemException {
        Map<Integer, String> helperTableMap = new HashMap<Integer, String>();
        final List<HelperTable> list = DAO.getHelperTableDetailsByListName();
        for (HelperTable helperTable : list) {
            helperTableMap.put(helperTable.getHelperTableSid(), helperTable.getDescription());
        }
        return helperTableMap;
    }

    private HelperDTO getDescriptionFromHelperTable(int id) {
        HelperDTO hDTO = new HelperDTO();
        try {
            HelperTable helperTable = HelperTableLocalServiceUtil.getHelperTable(id);
            hDTO.setId(helperTable.getHelperTableSid());
            hDTO.setDescription(helperTable.getDescription());
            return hDTO;
        } catch (PortalException ex) {

            LOGGER.error(ex);
            return new HelperDTO(0, ConstantsUtils.SELECT_ONE);
        } catch (SystemException ex) {
            LOGGER.error(ex);
            return new HelperDTO(0, ConstantsUtils.SELECT_ONE);
        }

    }

    public static String getDBColumnName(String visibleColumnName) {
        return columnName.get(visibleColumnName);

    }

    public static HashMap<String, String> loadColumnName() {
        columnName.clear();

        columnName.put("itemMasterStartDate", "itemStartDate");
        columnName.put("itemMasterEndDate", "itemEndDate");
        columnName.put("pediatricExcStartDate", "pediatricExclusiveStartDate");
        columnName.put("pediatricExcEndDate", "pediatricExclusiveEndDate");
        columnName.put("clottingFactStartDate", "clottingFactorStartDate");
        columnName.put("clottingFactEndDate", "clottingFactorEndDate");

        return columnName;
    }

    public String getManufactureSid(final String id) throws PortalException, SystemException {

        String systemId = StringUtils.EMPTY;
        LOGGER.info("Entering getManufactureSid P1: id " + id);
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(CompanyMaster.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.like("companyId", id));
        final List<CompanyMaster> list = DAO.getCompanyMasterList(cfpDynamicQuery);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                systemId = String.valueOf(list.get(i).getCompanyMasterSid());
            }
        }

        return systemId;
    }

    public static int getLazyNdc8Count(String filterText) throws PortalException, SystemException {
        filterText = StringUtils.trimToEmpty(filterText) + ConstantsUtils.PERCENCTAGE;
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(ItemMaster.class);
        query.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.NDC8, filterText));
        Long ndc8Count1 = ItemMasterLocalServiceUtil.dynamicQueryCount(query);
        return ndc8Count1.intValue();
    }

    public static List<HelperDTO> getLazyNdc8Results(final int start, final int end, String filterText) throws PortalException, SystemException {
        filterText = StringUtils.trimToEmpty(filterText) + ConstantsUtils.PERCENCTAGE;
        LOGGER.info("Entering ndc8 method with filterText" + filterText);
        final List<HelperDTO> list1 = new ArrayList<HelperDTO>();
        int startValue = start;
        int endValue = end;

        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property("ndc8"));
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(ItemMaster.class);
        query.setLimit(startValue, endValue);
        query.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
        query.addOrder(OrderFactoryUtil.asc(ConstantsUtils.NDC8));
        query.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.NDC8, filterText));
        List<String> list = ItemMasterLocalServiceUtil.dynamicQuery(query);
        LOGGER.info("return ndc8 size -" + list.size());
        HelperDTO dto;
        if (start == Constants.ZERO) {
            dto = new HelperDTO(ConstantsUtils.SELECT_ONE);
            dto.setDescription(ConstantsUtils.SELECT_ONE);
            list1.add(dto);
        }

        for (final String item : list) {
            dto = new HelperDTO(StringUtils.EMPTY);
            dto.setDescription(item != null ? String.valueOf(item) : StringUtils.EMPTY);
            list1.add(dto);
        }

        return list1;
    }

    public static int getLazyNdc9Count(String filterText) throws PortalException, SystemException {
        filterText = StringUtils.trimToEmpty(filterText) + ConstantsUtils.PERCENCTAGE;
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(ItemMaster.class);
        query.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.NDC9, filterText));
        Long ndc9Count = ItemMasterLocalServiceUtil.dynamicQueryCount(query);
        return ndc9Count.intValue();
    }

    public static List<HelperDTO> getLazyNdc9Results(final int start, final int end, String filterText) throws PortalException, SystemException {
        filterText = StringUtils.trimToEmpty(filterText) + ConstantsUtils.PERCENCTAGE;
        LOGGER.info("Entering ndc8 method with filterText" + filterText);
        final List<HelperDTO> list1 = new ArrayList<HelperDTO>();
        int startValue = start;
        int endValue = end;

        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property("ndc9"));
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(ItemMaster.class);
        query.setLimit(startValue, endValue);
        query.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
        query.addOrder(OrderFactoryUtil.asc(ConstantsUtils.NDC9));
        query.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.NDC9, filterText));
        List<String> list = ItemMasterLocalServiceUtil.dynamicQuery(query);
        LOGGER.info("return ndc9 size -" + list.size());
        HelperDTO dto;
        if (start == Constants.ZERO) {
            dto = new HelperDTO(ConstantsUtils.SELECT_ONE);
            dto.setDescription(ConstantsUtils.SELECT_ONE);
            list1.add(dto);
        }

        for (final String item : list) {
            dto = new HelperDTO(StringUtils.EMPTY);
            dto.setDescription(item != null ? String.valueOf(item) : StringUtils.EMPTY);
            list1.add(dto);
        }

        return list1;
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
    public int getSearchCountParentNoLookup(final ErrorfulFieldGroup searchCompanyForm, final BeanSearchCriteria search) throws SystemException, PortalException, Exception {
        LOGGER.info("getSearchCount p1: SearchCompanyForm");
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
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        List itemMasterList = null;
        if (searchCompanyForm.getField(ConstantsUtils.COMPANY_ID).getValue().toString() != null) {
            companyId = searchCompanyForm.getField(ConstantsUtils.COMPANY_ID).getValue().toString().trim();
            companyId = companyId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        if (searchCompanyForm.getField(ConstantsUtils.COMPANY_NO).getValue().toString() != null) {

            companyNo = searchCompanyForm.getField(ConstantsUtils.COMPANY_NO).getValue().toString().trim();
            companyNo = companyNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        if (searchCompanyForm.getField(ConstantsUtils.COMPANY_NAME).getValue().toString() != null) {
            companyName = searchCompanyForm.getField(ConstantsUtils.COMPANY_NAME).getValue().toString().trim();
            companyName = companyName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        if (searchCompanyForm.getField("identifier") != null && !String.valueOf(searchCompanyForm.getField("identifier").getValue()).equals(ConstantsUtils.NULL)) {
            itemIdentifier = searchCompanyForm.getField(ConstantsUtils.IDENTIFIER).getValue().toString().trim();
        }
        if (searchCompanyForm.getField("identifierType") != null && !String.valueOf(searchCompanyForm.getField("identifierType").getValue()).equals(ConstantsUtils.NULL)) {
            final HelperDTO helperDTO = (HelperDTO) searchCompanyForm.getField("identifierType").getValue();
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
                    if ("companyStatus".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("companyStatus", Integer.valueOf(stringFilter.getFilterString()));

                    } else if ("companyType".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("companyType", Integer.valueOf(stringFilter.getFilterString()));
                    } else if ("tradeClass".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("tradeClass", Integer.valueOf(stringFilter.getFilterString()));
                    } else if ("companyGroup".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("companyGroup", Integer.valueOf(stringFilter.getFilterString()));
                    } else if ("companyCategory".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("companyCategory", Integer.valueOf(stringFilter.getFilterString()));
                    } else if ("organizationKey".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("orgKey", Integer.valueOf(stringFilter.getFilterString()));
                    } else if ("udc1".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("udc1", Integer.valueOf(stringFilter.getFilterString()));
                    } else if ("udc2".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("udc2", Integer.valueOf(stringFilter.getFilterString()));
                    } else if ("udc3".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("udc3", Integer.valueOf(stringFilter.getFilterString()));
                    } else if ("udc4".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("udc4", Integer.valueOf(stringFilter.getFilterString()));
                    } else if ("udc5".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("udc5", Integer.valueOf(stringFilter.getFilterString()));
                    } else if ("udc6".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("udc6", Integer.valueOf(stringFilter.getFilterString()));
                    } else if ("udc7".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("udc7", Integer.valueOf(stringFilter.getFilterString()));
                    } else if ("udc8".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("udc8", Integer.valueOf(stringFilter.getFilterString()));
                    } else if ("state".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("state", Integer.valueOf(stringFilter.getFilterString()));
                    } else if ("country".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("country", Integer.valueOf(stringFilter.getFilterString()));
                    } else {
                        String filterString = ConstantsUtils.PERCENCTAGE + stringFilter.getFilterString() + ConstantsUtils.PERCENCTAGE;
                        parameters.put("filter~" + stringFilter.getPropertyId(), filterString);
                    }
                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;
                    Date startValue = (Date) betweenFilter.getStartValue();
                    Date endValue = (Date) betweenFilter.getEndValue();
                    parameters.put(betweenFilter.getPropertyId() + "from", String.valueOf(dateFormat.format(startValue)));
                    parameters.put(betweenFilter.getPropertyId() + "to", String.valueOf(dateFormat.format(endValue)));
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    if (operation.EQUAL.toString().equals(operation.name())) {
                        if (((Integer) stringFilter.getValue()) == 0) {
                            parameters.put("filter~" + stringFilter.getPropertyId() + "~=", String.valueOf(stringFilter.getValue()) + "--" + ConstantsUtils.ZERO);
                        } else {
                            int val = (Integer) stringFilter.getValue();
                            parameters.put("filter~" + stringFilter.getPropertyId() + "~=", String.valueOf(val) + "--" + "=");
                        }
                    }
                    if (operation.GREATER.toString().equals(operation.name())) {
                        int val = (Integer) stringFilter.getValue();
                        if (val < 0) {
                            parameters.put("filter~" + stringFilter.getPropertyId() + "~>", String.valueOf(val) + "--" + ">0");
                        } else {
                            parameters.put("filter~" + stringFilter.getPropertyId() + "~>", String.valueOf(val) + "--" + ">");
                        }
                    }
                    if (operation.LESS.toString().equals(operation.name())) {
                        int val = (Integer) stringFilter.getValue();
                        if (val > 0) {
                            parameters.put("filter~" + stringFilter.getPropertyId() + "~<", String.valueOf(val) + "--" + "<0");
                        } else {
                            parameters.put("filter~" + stringFilter.getPropertyId() + "~<", String.valueOf(val) + "--" + "<");
                        }
                    }
                    if (stringFilter.getValue() instanceof Date) {
                        Date value = (Date) stringFilter.getValue();
                        if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            parameters.put(stringFilter.getPropertyId() + "from", String.valueOf(dateFormat.format(value)));
                        } else {
                            parameters.put(stringFilter.getPropertyId() + "to", String.valueOf(dateFormat.format(value)));
                        }
                    }
                } else if (filter instanceof And) {
                    And stringFilter = (And) filter;
                    Collection<Container.Filter> value = stringFilter.getFilters();
                    for (Container.Filter filter1 : value) {
                        Object propertyId = StringUtils.EMPTY;
                        if (filter1 instanceof Compare.Less) {

                            Compare.Less less = (Compare.Less) filter1;
                            propertyId = less.getPropertyId();
                            parameters.put("filter~" + less.getPropertyId() + "~<<", String.valueOf(less.getValue()) + "--" + "<<");
                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;
                            propertyId = greater.getPropertyId();
                            parameters.put("filter~" + greater.getPropertyId() + "~>>", String.valueOf(greater.getValue()) + "--" + ">>");
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
            itemMasterList = dao.findCompanyMasterV1(companyId, companyNo, companyName, companyStatus, companyType, companyCategory, companyGroup, tradeClass, 0, null, null, false, null, null, parameters);

            temp = itemMasterList.size();
        }
        if (flag == Constants.ONE) {
            itemMasterList = dao.findCompanyMasterV1(companyId, companyNo, companyName, companyStatus, companyType, companyCategory, companyGroup, tradeClass, qualifierId, itemIdentifier, null, false, null, null, parameters);

            temp = itemMasterList.size();
        }
        LOGGER.info("return:" + temp);

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
            PortalException, Exception {
        LOGGER.info("searchCompany p1: SearchCompanyForm p2:" + start + " p3:" + end + " p4: List<OrderByColumn> size" + orderByColumns.size());
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
        String filterBar = StringUtils.EMPTY;
        List itemMasterList = null;
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        List<SearchCompanyForm> searchList = new ArrayList<SearchCompanyForm>();
        if (searchCompanyForm.getField(ConstantsUtils.COMPANY_ID).getValue().toString() != null) {
            companyId = searchCompanyForm.getField(ConstantsUtils.COMPANY_ID).getValue().toString().trim();
            companyId = companyId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        if (searchCompanyForm.getField(ConstantsUtils.COMPANY_NO).getValue().toString() != null) {

            companyNo = searchCompanyForm.getField(ConstantsUtils.COMPANY_NO).getValue().toString().trim();
            companyNo = companyNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        if (searchCompanyForm.getField(ConstantsUtils.COMPANY_NAME).getValue().toString() != null) {
            companyName = searchCompanyForm.getField(ConstantsUtils.COMPANY_NAME).getValue().toString().trim();
            companyName = companyName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        if (searchCompanyForm.getField(ConstantsUtils.IDENTIFIER) != null) {
            if (searchCompanyForm.getField(ConstantsUtils.IDENTIFIER).getValue().toString() != null) {
                itemIdentifier = searchCompanyForm.getField(ConstantsUtils.IDENTIFIER).getValue().toString().trim();
            }
        }
        if (searchCompanyForm.getField("identifierType") != null) {
            final HelperDTO helperDTO = (HelperDTO) searchCompanyForm.getField("identifierType").getValue();
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
                    if ("companyStatus".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("companyStatus", Integer.valueOf(stringFilter.getFilterString()));

                    } else if ("companyType".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("companyType", Integer.valueOf(stringFilter.getFilterString()));
                    } else if ("tradeClass".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("tradeClass", Integer.valueOf(stringFilter.getFilterString()));
                    } else if ("companyGroup".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("companyGroup", Integer.valueOf(stringFilter.getFilterString()));
                    } else if ("companyCategory".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("companyCategory", Integer.valueOf(stringFilter.getFilterString()));
                    } else if ("organizationKey".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("orgKey", Integer.valueOf(stringFilter.getFilterString()));
                    } else if ("udc1".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("udc1", Integer.valueOf(stringFilter.getFilterString()));
                    } else if ("udc2".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("udc2", Integer.valueOf(stringFilter.getFilterString()));
                    } else if ("udc3".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("udc3", Integer.valueOf(stringFilter.getFilterString()));
                    } else if ("udc4".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("udc4", Integer.valueOf(stringFilter.getFilterString()));
                    } else if ("udc5".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("udc5", Integer.valueOf(stringFilter.getFilterString()));
                    } else if ("udc6".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("udc6", Integer.valueOf(stringFilter.getFilterString()));
                    } else if ("state".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("state", Integer.valueOf(stringFilter.getFilterString()));
                    } else if ("country".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("country", Integer.valueOf(stringFilter.getFilterString()));
                    } else if ("identifierStatus".equals(stringFilter.getPropertyId()) && Integer.valueOf(stringFilter.getFilterString()) != 0) {
                        parameters.put("identifierStatus", Integer.valueOf(stringFilter.getFilterString()));
                    } else {
                        String filterString = ConstantsUtils.PERCENCTAGE + stringFilter.getFilterString() + ConstantsUtils.PERCENCTAGE;
                        parameters.put("filter~" + stringFilter.getPropertyId(), filterString);
                    }
                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;
                    Date startValue = (Date) betweenFilter.getStartValue();
                    Date endValue = (Date) betweenFilter.getEndValue();
                    parameters.put(betweenFilter.getPropertyId() + "from", String.valueOf(dateFormat.format(startValue)));
                    parameters.put(betweenFilter.getPropertyId() + "to", String.valueOf(dateFormat.format(endValue)));
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    if (operation.EQUAL.toString().equals(operation.name())) {
                        if (((Integer) stringFilter.getValue()) == 0) {
                            parameters.put("filter~" + stringFilter.getPropertyId() + "~=", String.valueOf(stringFilter.getValue()) + "--" + ConstantsUtils.ZERO);
                        } else {
                            int val = (Integer) stringFilter.getValue();
                            parameters.put("filter~" + stringFilter.getPropertyId() + "~=", String.valueOf(val) + "--" + "=");
                        }
                    }
                    if (operation.GREATER.toString().equals(operation.name())) {
                        int val = (Integer) stringFilter.getValue();
                        if (val < 0) {
                            parameters.put("filter~" + stringFilter.getPropertyId() + "~>", String.valueOf(val) + "--" + ">0");
                        } else {
                            parameters.put("filter~" + stringFilter.getPropertyId() + "~>", String.valueOf(val) + "--" + ">");
                        }
                    }
                    if (operation.LESS.toString().equals(operation.name())) {
                        int val = (Integer) stringFilter.getValue();
                        if (val > 0) {
                            parameters.put("filter~" + stringFilter.getPropertyId() + "~<", String.valueOf(val) + "--" + "<0");
                        } else {
                            parameters.put("filter~" + stringFilter.getPropertyId() + "~<", String.valueOf(val) + "--" + "<");
                        }
                    }
                    if (stringFilter.getValue() instanceof Date) {
                        Date value = (Date) stringFilter.getValue();
                        if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            parameters.put(stringFilter.getPropertyId() + "from", String.valueOf(dateFormat.format(value)));
                        } else {
                            parameters.put(stringFilter.getPropertyId() + "to", String.valueOf(dateFormat.format(value)));
                        }
                    }
                } else if (filter instanceof And) {
                    And stringFilter = (And) filter;
                    Collection<Container.Filter> value = stringFilter.getFilters();
                    for (Container.Filter filter1 : value) {
                        Object propertyId = StringUtils.EMPTY;
                        if (filter1 instanceof Compare.Less) {

                            Compare.Less less = (Compare.Less) filter1;
                            propertyId = less.getPropertyId();
                            parameters.put("filter~" + less.getPropertyId() + "~<<", String.valueOf(less.getValue()) + "--" + "<<");
                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;
                            propertyId = greater.getPropertyId();
                            parameters.put("filter~" + greater.getPropertyId() + "~>>", String.valueOf(greater.getValue()) + "--" + ">>");
                        }
                    }
                }
            }
        }
        if (flag == Constants.ZERO) {
            itemMasterList = dao.findCompanyMasterV1(companyId, companyNo, companyName, companyStatus, companyType, companyCategory, companyGroup, tradeClass, 0, null, dbColumnName, asc, start, end, parameters);
            searchList = getCustomizedSearchFormFromObjectParentNoLookup(itemMasterList);
            VaadinSession.getCurrent().setAttribute(ConstantsUtils.IDENTIFIER, "N");
        }
        if (flag == Constants.ONE) {
            itemMasterList = dao.findCompanyMasterV1(companyId, companyNo, companyName, companyStatus, companyType, companyCategory, companyGroup, tradeClass, qualifierId, itemIdentifier, dbColumnName, asc, start, end, parameters);
            searchList = getCustomizedSearchFormFromObjectParentNoLookup(itemMasterList);
            VaadinSession.getCurrent().setAttribute(ConstantsUtils.IDENTIFIER, "Y");
        }

        LOGGER.info("return count :" + searchList.size());

        return searchList;
    }

    /**
     * Gets the customized list of SearchCompanyForm from list of CompanyMaster.
     *
     * @param list the list
     * @return the customized search form from object
     * @throws Exception the exception
     */
    public List<SearchCompanyForm> getCustomizedSearchFormFromObjectParentNoLookup(final List list) throws Exception {
        final List<SearchCompanyForm> searchItemList = new ArrayList<SearchCompanyForm>();
        LOGGER.info("Entering getCustomizedSearchFormFromObject p1:" + ((list == null) ? list : list.size()));

        if (list != null) {
            final Map<Integer, String> helperMap = com.stpl.app.util.GeneralCommonUtils.getCodeDescription();
            for (int i = 0; i < list.size(); i++) {
                final SearchCompanyForm searchCompanyForm = new SearchCompanyForm();
                final Object[] obj = (Object[]) list.get(i);
                final Integer systemId = (Integer) obj[0];
                searchCompanyForm.setSystemId(String.valueOf(systemId));
                searchCompanyForm.setCompanyId(HelperUtils.getString(obj[1]));
                searchCompanyForm.setCompanyNo(HelperUtils.getString(obj[2]));
                searchCompanyForm.setCompanyName(HelperUtils.getString(obj[3]));
                if (obj[4] != null && StringUtils.isNotBlank(obj[4].toString()) && (Integer) obj[4] != 0) {
                    searchCompanyForm.setCompanyType(helperMap.get((Integer) obj[4]));
                }
                if (obj[5] != null && StringUtils.isNotBlank(obj[5].toString()) && (Integer) obj[5] != 0) {
                    searchCompanyForm.setCompanyStatus(helperMap.get((Integer) obj[5]));
                }
                if (obj[6] != null) {
                    searchCompanyForm.setIdentifierTypeDesc(new HelperDTO(String.valueOf(obj[6])));
                    searchCompanyForm.setIdentifierType(new HelperDTO(String.valueOf(obj[6])));
                }
                if (obj[7] != null) {
                    searchCompanyForm.setIdentifier(HelperUtils.getString(obj[7]));
                }
                searchCompanyForm.setRecordLockStatus(HelperUtils.getString(obj[8]));
                if (obj[27] != null) {
                    Date startDate = (Date) obj[27];
                    searchCompanyForm.setCompStartDate(startDate);
                    searchCompanyForm.setCompanyStartDate(CommonUtils.convertDateToString(startDate));
                }
                if (obj[10] != null) {
                    Date EndDate = (Date) obj[10];
                    searchCompanyForm.setCompEndDate(EndDate);
                    searchCompanyForm.setCompanyEndDate(CommonUtils.convertDateToString(EndDate));
                }
                if (obj[12] != null && StringUtils.isNotBlank(obj[12].toString()) && Integer.valueOf(obj[12].toString()) != 0) {
                    searchCompanyForm.setCompanyGroup(helperMap.get(Integer.valueOf(obj[12].toString())));
                }
                if (obj[29] != null && StringUtils.isNotBlank(obj[29].toString()) && Integer.valueOf(obj[29].toString()) != 0) {
                    searchCompanyForm.setCompanyCategory(helperMap.get(Integer.valueOf(obj[29].toString())));
                }
                searchCompanyForm.setAddress1(HelperUtils.getString(obj[14]));
                searchCompanyForm.setAddress2(HelperUtils.getString(obj[15]));
                searchCompanyForm.setCity(HelperUtils.getString(obj[16]));
                searchCompanyForm.setZipCode(HelperUtils.getString(obj[18]));
                if (obj[17] != null && StringUtils.isNotBlank(obj[17].toString()) && Integer.valueOf(obj[17].toString()) != 0) {
                    searchCompanyForm.setState(helperMap.get((Integer) obj[17]));
                }
                if (obj[19] != null && StringUtils.isNotBlank(obj[19].toString()) && Integer.valueOf(obj[19].toString()) != 0) {
                    searchCompanyForm.setCountry(helperMap.get(Integer.valueOf(obj[19].toString())));
                }
                searchCompanyForm.setRegionCode(HelperUtils.getString(obj[20]));
                searchCompanyForm.setFinancialSystem(HelperUtils.getString(obj[13]));
                if (obj[30] != null && StringUtils.isNotBlank(obj[30].toString()) && Integer.valueOf(obj[30].toString()) != 0) {
                    searchCompanyForm.setOrganizationKey(helperMap.get(Integer.valueOf(obj[30].toString())));
                }
                if (obj[9] != null) {
                    searchCompanyForm.setLives(String.valueOf(obj[9]));
                }
                searchCompanyForm.setUdc1((obj[31] != null && (Integer) obj[31] != 0) ? helperMap.get((Integer) obj[31]) : StringUtils.EMPTY);
                searchCompanyForm.setUdc2((obj[32] != null && (Integer) obj[32] != 0) ? helperMap.get((Integer) obj[32]) : StringUtils.EMPTY);
                searchCompanyForm.setUdc3((obj[33] != null && (Integer) obj[33] != 0) ? helperMap.get((Integer) obj[33]) : StringUtils.EMPTY);
                searchCompanyForm.setUdc4((obj[34] != null && (Integer) obj[34] != 0) ? helperMap.get((Integer) obj[34]) : StringUtils.EMPTY);
                searchCompanyForm.setUdc5((obj[35] != null && (Integer) obj[35] != 0) ? helperMap.get((Integer) obj[35]) : StringUtils.EMPTY);
                searchCompanyForm.setUdc6((obj[36] != null && (Integer) obj[36] != 0) ? helperMap.get((Integer) obj[36]) : StringUtils.EMPTY);
                if (obj[37] != null && StringUtils.isNotBlank(obj[37].toString()) && (Integer) obj[37] != 0) {
                    searchCompanyForm.setTradeClass(helperMap.get((Integer) obj[37]));
                }
                if (obj[38] != null) {
                    Date startDate = (Date) obj[38];
                    searchCompanyForm.setTradeStartDate(startDate);
                    searchCompanyForm.setTradeClassStartDate(CommonUtils.convertDateToString(startDate));
                }
                if (obj[39] != null) {
                    Date EndDate = (Date) obj[39];
                    searchCompanyForm.setTradeEndDate(EndDate);
                    searchCompanyForm.setTradeClassEndDate(CommonUtils.convertDateToString(EndDate));
                }
                if (obj[43] != null && !ConstantsUtils.NULL.equalsIgnoreCase(String.valueOf(obj[43])) && StringUtils.isNotBlank(obj[43].toString())) {
                    searchCompanyForm.setParentCompanyNo(obj[43].toString());
                }
                if (obj[41] != null) {
                    Date startDate = (Date) obj[41];
                    searchCompanyForm.setParentSDate(startDate);
                    searchCompanyForm.setParentStartDate(CommonUtils.convertDateToString(startDate));
                }
                if (obj[42] != null) {
                    Date EndDate = (Date) obj[42];
                    searchCompanyForm.setParentEDate(EndDate);
                    searchCompanyForm.setParentEndDate(CommonUtils.convertDateToString(EndDate));
                }
                searchItemList.add(searchCompanyForm);
            }
        }
        LOGGER.info("return count -" + searchItemList.size());

        return searchItemList;
    }

    private Map<String, Object> getParametersValue(ErrorfulFieldGroup searchItemForm, BeanSearchCriteria criteria) throws PortalException, SystemException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        String itemIdentifier;
        int qualifierId;
        String itemId;
        String itemNo;
        String itemName;
        String itemType;
        String[] brandMasterSid = null;
        String itemStatus;
        String itemDesc;
        String brand;
        String form;
        String strength;
        String systemId;
        String therapyClass;
        String ndc8;
        String ndc9;
        HelperDTO helperDTO1;
        HelperDTO helperDTO2;

        HelperDTO helperDTO;
        if (StringUtils.isNotBlank(String.valueOf(searchItemForm.getField(ConstantsUtils.TEXT + 1).getValue()))) {
            systemId = searchItemForm.getField(ConstantsUtils.TEXT + 1).getValue().toString().trim();
            systemId = systemId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put(ConstantsUtils.SYSTEM_ID, systemId);
        }
        if (StringUtils.isNotBlank(String.valueOf(searchItemForm.getField(ConstantsUtils.TEXT + 2).getValue()))) {
            itemId = searchItemForm.getField(ConstantsUtils.TEXT + 2).getValue().toString().trim();
            itemId = itemId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put(ConstantsUtils.ITEM_ID, itemId);
        }
        if (StringUtils.isNotBlank(String.valueOf(searchItemForm.getField(ConstantsUtils.TEXT + 3).getValue()))) {
            itemNo = searchItemForm.getField(ConstantsUtils.TEXT + 3).getValue().toString().trim();
            itemNo = itemNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put(ConstantUtil.ITEM_NO_M, itemNo);
        }
        if (StringUtils.isNotBlank(String.valueOf(searchItemForm.getField(ConstantsUtils.TEXT + 4).getValue()))) {
            itemName = searchItemForm.getField(ConstantsUtils.TEXT + 4).getValue().toString().trim();
            itemName = itemName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put(ConstantUtil.ITEM_NAME_M, itemName);
        }

        if (StringUtils.isNotBlank(String.valueOf(searchItemForm.getField(ConstantsUtils.TEXT5).getValue()))) {
            itemDesc = searchItemForm.getField(ConstantsUtils.TEXT5).getValue().toString().trim();
            itemDesc = itemDesc.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put(ConstantUtil.ITEM_DESC_M, itemDesc);
        }

        if (StringUtils.isNotBlank(String.valueOf(searchItemForm.getField(ConstantsUtils.TEXT6).getValue()))) {
            therapyClass = searchItemForm.getField(ConstantsUtils.TEXT6).getValue().toString();
            therapyClass = therapyClass.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
            productProjectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.HELPER_TABLE_SID));
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(HelperTable.class);
            query.add(RestrictionsFactoryUtil.eq(ConstantsUtils.LIST_NAME, UIUtils.THERAPEUTIC_CLASS));
            query.add(RestrictionsFactoryUtil.like(ConstantsUtils.DESCRIPTION, therapyClass));
            query.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
            List list = HelperTableLocalServiceUtil.dynamicQuery(query);
            parameters.put("therapyClass", list);
        }

        if (StringUtils.isNotBlank(String.valueOf(searchItemForm.getField(ConstantsUtils.TEXT7).getValue()))) {
            itemIdentifier = searchItemForm.getField(ConstantsUtils.TEXT7).getValue().toString().trim();
            itemIdentifier = itemIdentifier.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("identiferId", itemIdentifier);
        }

        if (searchItemForm.getField(ConstantsUtils.COMBO1).getValue() == null) {
            itemStatus = StringUtils.EMPTY;
        } else {
            itemStatus = searchItemForm.getField(ConstantsUtils.COMBO1).getValue().toString();

            List<HelperDTO> list = getDropDownList(UIUtils.STATUS);
            for (HelperDTO list1 : list) {
                if (list1.getDescription().equals(itemStatus) && list1.getSystemId() != 0) {
                    parameters.put(ConstantUtil.ITEM_STATUS_M, list1.getSystemId());
                }
            }
        }

        if (searchItemForm.getField(ConstantsUtils.COMBO2).getValue() == null) {
            itemType = StringUtils.EMPTY;
        } else {
            itemType = searchItemForm.getField(ConstantsUtils.COMBO2).getValue().toString();
            List<HelperDTO> list = getDropDownList(UIUtils.ITEM_TYPE);
            for (HelperDTO list1 : list) {
                if (list1.getDescription().equals(itemType) && list1.getSystemId() != 0) {
                    parameters.put(ConstantUtil.ITEM_TYPE, list1.getSystemId());
                }
            }
        }

        if (searchItemForm.getField(ConstantsUtils.COMBO3).getValue() == null || searchItemForm.getField(ConstantsUtils.COMBO3).getValue().toString().equals(ConstantUtil.SELECT_ONE)) {
            helperDTO2 = HELPERDTO1;
        } else {
            helperDTO2 = (HelperDTO) searchItemForm.getField(ConstantsUtils.COMBO3).getValue();
            if (helperDTO2 != null && StringUtils.isNotBlank(helperDTO2.getDescription())) {
                parameters.put(ConstantUtil.NDC9, helperDTO2.getDescription());
            }
        }

        if (searchItemForm.getField(ConstantsUtils.COMBO4).getValue() == null) {
            form = StringUtils.EMPTY;
        } else {
            form = searchItemForm.getField(ConstantsUtils.COMBO4).getValue().toString();
            List<HelperDTO> list = getDropDownList(UIUtils.FORM1);
            for (HelperDTO list1 : list) {
                if (String.valueOf(list1.getDescription()).equals(form) && list1.getSystemId() != 0) {
                    parameters.put(ConstantUtil.FORM, list1.getSystemId());
                }
            }
        }
        if (searchItemForm.getField(ConstantsUtils.COMBO5) == null || searchItemForm.getField(ConstantsUtils.COMBO5).getValue() == null) {
            helperDTO = HELPERDTO1;
        } else {
            helperDTO = (HelperDTO) searchItemForm.getField(ConstantsUtils.COMBO5).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {
                if (helperDTO.getId() != 0) {
                    parameters.put("itemQualifier", helperDTO.getId());
                }
            }
        }

        if (searchItemForm.getField(ConstantsUtils.COMBO6) == null || searchItemForm.getField(ConstantsUtils.COMBO6).getValue() == null || Constants.SELECT_ONE.equals(searchItemForm.getField(ConstantsUtils.COMBO6).getValue().toString())) {
            brand = StringUtils.EMPTY;
        } else {
            brand = searchItemForm.getField(ConstantsUtils.COMBO6).getValue().toString();
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(BrandMaster.class);
            query.add(RestrictionsFactoryUtil.eq(ConstantsUtils.BRAND_NAME, brand));
            List<BrandMaster> list = BrandMasterLocalServiceUtil.dynamicQuery(query);
            brandMasterSid = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                brandMasterSid[i] = String.valueOf(list.get(i).getBrandMasterSid());
            }
            parameters.put(ConstantsUtils.BRAND_MASTER_SID, Arrays.toString(brandMasterSid).substring(1, Arrays.toString(brandMasterSid).length() - 1));
        }
        if (searchItemForm.getField(ConstantsUtils.COMBO7).getValue() == null || searchItemForm.getField(ConstantsUtils.COMBO7).getValue().toString().equals(ConstantUtil.SELECT_ONE)) {
            helperDTO1 = HELPERDTO1;
        } else {
            helperDTO1 = (HelperDTO) searchItemForm.getField(ConstantsUtils.COMBO7).getValue();
            if (helperDTO1 != null && StringUtils.isNotBlank(helperDTO1.getDescription())) {
                parameters.put("ndc8", helperDTO1.getDescription());
            }
        }

        if (searchItemForm.getField(ConstantsUtils.COMBO8).getValue() == null) {
            strength = StringUtils.EMPTY;

        } else {
            strength = searchItemForm.getField(ConstantsUtils.COMBO8).getValue().toString();
            List<HelperDTO> list = getDropDownList(UIUtils.STRENGTH1);
            for (HelperDTO list1 : list) {
                if (String.valueOf(list1.getDescription()).equals(strength) && list1.getSystemId() != 0) {
                    parameters.put(ConstantUtil.STRENGTH, list1.getSystemId());
                }
            }
        }

        if (criteria != null && criteria.getFilters() != null) {
            loadColumnName();
            for (Container.Filter filter : criteria.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = ConstantsUtils.PERCENCTAGE + stringFilter.getFilterString() + ConstantsUtils.PERCENCTAGE;

                    int sysId = 0;
                    List<HelperDTO> list = null;
                    if (ConstantUtil.ITEM_STATUS_M.equals(stringFilter.getPropertyId())) {
                        list = getDropDownList(UIUtils.STATUS);
                        for (HelperDTO list1 : list) {
                            if (list1.getDescription().equals(stringFilter.getFilterString()) && list1.getSystemId() != 0) {
                                parameters.put(ConstantUtil.ITEM_STATUS_M + "~", list1.getSystemId());
                            }
                        }
                    } else if (ConstantUtil.ITEM_TYPE.equals(stringFilter.getPropertyId())) {
                        if (!stringFilter.getFilterString().equals(ConstantUtil.SHOW_ALL)) {
                            parameters.put(ConstantUtil.ITEM_TYPE + "~", Integer.valueOf(stringFilter.getFilterString()));
                        }
                    } else if (ConstantsUtils.THERAPEUTIC_CLASS.equals(stringFilter.getPropertyId())) {
                        parameters.put(ConstantsUtils.THERAPEUTIC_CLASS + "~", Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.BRAND.equals(stringFilter.getPropertyId()) && !ConstantsUtils.SELECT_ONE.equals(stringFilter.getFilterString())) {
                        DynamicQuery query = DynamicQueryFactoryUtil.forClass(BrandMaster.class);
                        query.add(RestrictionsFactoryUtil.eq(ConstantsUtils.BRAND_NAME, stringFilter.getFilterString()));
                        List<BrandMaster> listBrand = BrandMasterLocalServiceUtil.dynamicQuery(query);
                        brandMasterSid = new String[listBrand.size()];
                        for (int i = 0; i < listBrand.size(); i++) {
                            brandMasterSid[i] = String.valueOf(listBrand.get(i).getBrandMasterSid());
                        }
                        parameters.put(ConstantsUtils.BRAND_MASTER_SID, Arrays.toString(brandMasterSid).substring(1, Arrays.toString(brandMasterSid).length() - 1));
                    } else if ("form".equals(stringFilter.getPropertyId())) {
                        parameters.put("form" + "~", Integer.valueOf(stringFilter.getFilterString()));
                    } else if ("strength".equals(stringFilter.getPropertyId())) {
                        parameters.put("strength" + "~", Integer.valueOf(stringFilter.getFilterString()));
                    } else {
                        parameters.put(StringUtils.EMPTY + stringFilter.getPropertyId() + "~", filterString);
                    }
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;

                    if (ConstantsUtils.ITEM_SID.equals(stringFilter.getPropertyId())) {
                        if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER)) {
                            parameters.put("systemId-greater", stringFilter.getValue());
                        } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS)) {
                            parameters.put("systemId-less", stringFilter.getValue());
                        } else if (stringFilter.getOperation().equals(stringFilter.getOperation().EQUAL)) {
                            parameters.put("systemId-equal", stringFilter.getValue());
                        }
                    }
                }
            }
        }
        return parameters;
    }

    @Override
    public List<SearchResultsDTO> searchItems(ErrorfulFieldGroup searchItemForm, int start, int end, List<OrderByColumn> orderByColumns, BeanSearchCriteria criteria) throws SystemException, PortalException, Exception {
        Map<String, Object> parameters = getParametersValue(searchItemForm, criteria);
        List<SearchResultsDTO> searchList;
        HelperDTO helperDTO;
        String orderByCol = StringUtils.EMPTY;
        Boolean sortOrder = false;
        for (final Iterator<OrderByColumn> iterator = orderByColumns.iterator(); iterator.hasNext();) {
            final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();
            if (ConstantsUtils.ITEM_SID.equals(orderByColumn.getName())) {
                orderByCol = ConstantsUtils.ITEM_MASTER_SYSTEM_ID;
            } else if (ConstantsUtils.BRAND.equals(orderByColumn.getName())) {
                orderByCol = ConstantsUtils.BRAND_MASTER_SID;
            } else {
                orderByCol = orderByColumn.getName();
            }

            if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
                sortOrder = true;
            } else {
                sortOrder = false;
            }
        }
        if (searchItemForm.getField(ConstantsUtils.COMBO5) == null || searchItemForm.getField(ConstantsUtils.COMBO5).getValue() == null) {
            helperDTO = HELPERDTO1;
        } else {
            helperDTO = (HelperDTO) searchItemForm.getField(ConstantsUtils.COMBO5).getValue();
        }
        List itemMasterList;
        itemMasterList = DAO.getItemMasterListByQualifierId(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY,
                StringUtils.EMPTY, StringUtils.EMPTY, null, 0, String.valueOf(start), String.valueOf(end), orderByCol, sortOrder, parameters);

        searchList = getCustomizedSearchFormFromObject(itemMasterList, helperDTO == null ? StringUtils.EMPTY : helperDTO.getDescription());
        VaadinSession.getCurrent().setAttribute(ConstantUtil.ITEM_IDENTIFIER, ConstantUtil.YES_INDICATOR);
        return searchList;
    }

    /**
     * Gets the count.
     *
     * @param searchItemForm the search item form
     * @return the count
     */
    public int getCountForSearch(final ErrorfulFieldGroup searchItemForm, final Set<Container.Filter> filterSet) throws PortalException, SystemException {

        Map<String, Object> parameters = getParametersValueForSearch(searchItemForm, filterSet);

        return DAO.getItemMasterListByQualifierId(StringUtils.EMPTY, StringUtils.EMPTY,
                StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, null,
                0, StringUtils.EMPTY, StringUtils.EMPTY, null, null, parameters).size();

    }

    public List<SearchResultsDTO> getResultsForSearch(ErrorfulFieldGroup searchItemForm, int start, int end, List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet) throws SystemException, PortalException, Exception {
        Map<String, Object> parameters = getParametersValueForSearch(searchItemForm, filterSet);
        List<SearchResultsDTO> searchList;
        HelperDTO helperDTO;
        String orderByCol = StringUtils.EMPTY;
        Boolean sortOrder = false;
        for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
            final SortByColumn sortByColumn = (SortByColumn) iterator.next();
            if (ConstantsUtils.ITEM_SID.equals(sortByColumn.getName())) {
                orderByCol = ConstantsUtils.ITEM_MASTER_SYSTEM_ID;
            } else if (ConstantsUtils.BRAND.equals(sortByColumn.getName())) {
                orderByCol = ConstantsUtils.BRAND_MASTER_SID;
            } else {
                orderByCol = sortByColumn.getName();
            }

            if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                sortOrder = true;
            } else {
                sortOrder = false;
            }
        }
        if (searchItemForm.getField(ConstantsUtils.COMBO5) == null || searchItemForm.getField(ConstantsUtils.COMBO5).getValue() == null) {
            helperDTO = HELPERDTO1;
        } else {
            helperDTO = (HelperDTO) searchItemForm.getField(ConstantsUtils.COMBO5).getValue();
        }
        List itemMasterList;
        itemMasterList = DAO.getItemMasterListByQualifierId(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY,
                StringUtils.EMPTY, StringUtils.EMPTY, null, 0, String.valueOf(start), String.valueOf(end), orderByCol, sortOrder, parameters);

        searchList = getCustomizedSearchFormFromObject(itemMasterList, helperDTO == null ? StringUtils.EMPTY : helperDTO.getDescription());
        VaadinSession.getCurrent().setAttribute(ConstantUtil.ITEM_IDENTIFIER, ConstantUtil.YES_INDICATOR);
        return searchList;
    }

    private Map<String, Object> getParametersValueForSearch(ErrorfulFieldGroup searchItemForm, final Set<Container.Filter> filterSet) throws PortalException, SystemException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        String itemIdentifier;
        int qualifierId;
        String itemId;
        String itemNo;
        String itemName;
         String batchId;
        String itemType;
        String[] brandMasterSid = null;
        String itemStatus;
        String itemDesc;
        String brand;
        String form;
        String strength;
        String systemId;
        String therapyClass;
        String ndc8;
        String ndc9;
        HelperDTO helperDTO1;
        HelperDTO helperDTO2;

        HelperDTO helperDTO;
        if (StringUtils.isNotBlank(String.valueOf(searchItemForm.getField(ConstantsUtils.TEXT + 1).getValue()))) {
            systemId = searchItemForm.getField(ConstantsUtils.TEXT + 1).getValue().toString().trim();
            systemId = systemId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put(ConstantsUtils.SYSTEM_ID, systemId);
        }
        if (StringUtils.isNotBlank(String.valueOf(searchItemForm.getField(ConstantsUtils.TEXT + 2).getValue()))) {
            itemId = searchItemForm.getField(ConstantsUtils.TEXT + 2).getValue().toString().trim();
            parameters.put(ConstantsUtils.ITEM_ID, itemId);
        }
        if (StringUtils.isNotBlank(String.valueOf(searchItemForm.getField(ConstantsUtils.TEXT + 3).getValue()))) {
            itemNo = searchItemForm.getField(ConstantsUtils.TEXT + 3).getValue().toString().trim();
            parameters.put(ConstantUtil.ITEM_NO_M, itemNo);
        }
        if (StringUtils.isNotBlank(String.valueOf(searchItemForm.getField(ConstantsUtils.TEXT + 4).getValue()))) {
            itemName = searchItemForm.getField(ConstantsUtils.TEXT + 4).getValue().toString().trim();
            parameters.put(ConstantUtil.ITEM_NAME_M, itemName);
        }
        
         if (StringUtils.isNotBlank(String.valueOf(searchItemForm.getField(ConstantsUtils.TEXT + 8).getValue()))) {
            batchId = searchItemForm.getField(ConstantsUtils.TEXT + 8).getValue().toString().trim();
            parameters.put(ConstantUtil.ITEM_BATCH_ID, batchId);
        }
        if (StringUtils.isNotBlank(String.valueOf(searchItemForm.getField(ConstantsUtils.TEXT5).getValue()))) {
            itemDesc = searchItemForm.getField(ConstantsUtils.TEXT5).getValue().toString().trim();
            parameters.put(ConstantUtil.ITEM_DESC_M, itemDesc);
        }

        if (StringUtils.isNotBlank(String.valueOf(searchItemForm.getField(ConstantsUtils.TEXT6).getValue()))) {
            therapyClass = searchItemForm.getField(ConstantsUtils.TEXT6).getValue().toString();
            therapyClass = therapyClass.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
            productProjectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.HELPER_TABLE_SID));
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(HelperTable.class);
            query.add(RestrictionsFactoryUtil.eq(ConstantsUtils.LIST_NAME, UIUtils.THERAPEUTIC_CLASS));
            query.add(RestrictionsFactoryUtil.like(ConstantsUtils.DESCRIPTION, therapyClass));
            query.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
            List list = HelperTableLocalServiceUtil.dynamicQuery(query);
            parameters.put("therapyClass", list);
        }

        if (StringUtils.isNotBlank(String.valueOf(searchItemForm.getField(ConstantsUtils.TEXT7).getValue()))) {
            itemIdentifier = searchItemForm.getField(ConstantsUtils.TEXT7).getValue().toString().trim();
            parameters.put("identiferId", itemIdentifier);
        }

        if (searchItemForm.getField(ConstantsUtils.COMBO1).getValue() == null) {
            itemStatus = StringUtils.EMPTY;
        } else {
            itemStatus = searchItemForm.getField(ConstantsUtils.COMBO1).getValue().toString();

            List<HelperDTO> list = getDropDownList(UIUtils.STATUS);
            for (HelperDTO list1 : list) {
                if (list1.getDescription().equals(itemStatus) && list1.getSystemId() != 0) {
                    parameters.put(ConstantUtil.ITEM_STATUS_M, list1.getSystemId());
                }
            }
        }

        if (searchItemForm.getField(ConstantsUtils.COMBO2).getValue() == null) {
            itemType = StringUtils.EMPTY;
        } else {
            itemType = searchItemForm.getField(ConstantsUtils.COMBO2).getValue().toString();
            List<HelperDTO> list = getDropDownList(UIUtils.ITEM_TYPE);
            for (HelperDTO list1 : list) {
                if (list1.getDescription().equals(itemType) && list1.getSystemId() != 0) {
                    parameters.put(ConstantUtil.ITEM_TYPE, list1.getSystemId());
                }
            }
        }

        if (searchItemForm.getField(ConstantsUtils.COMBO3).getValue() == null || searchItemForm.getField(ConstantsUtils.COMBO3).getValue().toString().equals(ConstantUtil.SELECT_ONE)) {
            helperDTO2 = HELPERDTO1;
        } else {
            helperDTO2 = (HelperDTO) searchItemForm.getField(ConstantsUtils.COMBO3).getValue();
            if (helperDTO2 != null && StringUtils.isNotBlank(helperDTO2.getDescription())) {
                parameters.put(ConstantUtil.NDC9, helperDTO2.getDescription());
            }
        }

        if (searchItemForm.getField(ConstantsUtils.COMBO4).getValue() == null) {
            form = StringUtils.EMPTY;
        } else {
            form = searchItemForm.getField(ConstantsUtils.COMBO4).getValue().toString();

            List<HelperDTO> list = getDropDownList(UIUtils.FORM1);
            for (HelperDTO list1 : list) {
                if (String.valueOf(list1.getDescription()).equals(form) && list1.getSystemId() != 0) {
                    parameters.put(ConstantUtil.FORM, list1.getSystemId());
                }
            }
        }
        if (searchItemForm.getField(ConstantsUtils.COMBO5) == null || searchItemForm.getField(ConstantsUtils.COMBO5).getValue() == null) {
            helperDTO = HELPERDTO1;
        } else {
            helperDTO = (HelperDTO) searchItemForm.getField(ConstantsUtils.COMBO5).getValue();
            if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {
                if (helperDTO.getId() != 0) {
                    parameters.put("itemQualifier", helperDTO.getId());
                }
            }
        }

        if (searchItemForm.getField(ConstantsUtils.COMBO6) == null || searchItemForm.getField(ConstantsUtils.COMBO6).getValue() == null || Constants.SELECT_ONE.equals(searchItemForm.getField(ConstantsUtils.COMBO6).getValue().toString())) {
            brand = StringUtils.EMPTY;
        } else {
            brand = searchItemForm.getField(ConstantsUtils.COMBO6).getValue().toString();
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(BrandMaster.class);
            query.add(RestrictionsFactoryUtil.eq(ConstantsUtils.BRAND_NAME, brand));
            List<BrandMaster> list = BrandMasterLocalServiceUtil.dynamicQuery(query);
            brandMasterSid = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                brandMasterSid[i] = String.valueOf(list.get(i).getBrandMasterSid());
            }
            parameters.put(ConstantsUtils.BRAND_MASTER_SID, Arrays.toString(brandMasterSid).substring(1, Arrays.toString(brandMasterSid).length() - 1));
        }
        if (searchItemForm.getField(ConstantsUtils.COMBO7).getValue() == null || searchItemForm.getField(ConstantsUtils.COMBO7).getValue().toString().equals(ConstantUtil.SELECT_ONE)) {
            helperDTO1 = HELPERDTO1;
        } else {
            helperDTO1 = (HelperDTO) searchItemForm.getField(ConstantsUtils.COMBO7).getValue();
            if (helperDTO1 != null && StringUtils.isNotBlank(helperDTO1.getDescription())) {
                parameters.put("ndc8", helperDTO1.getDescription());
            }
        }

        if (searchItemForm.getField(ConstantsUtils.COMBO8).getValue() == null) {
            strength = StringUtils.EMPTY;

        } else {
            strength = searchItemForm.getField(ConstantsUtils.COMBO8).getValue().toString();
            List<HelperDTO> list = getDropDownList(UIUtils.STRENGTH1);
            for (HelperDTO list1 : list) {
                if (String.valueOf(list1.getDescription()).equals(strength) && list1.getSystemId() != 0) {
                    parameters.put(ConstantUtil.STRENGTH, list1.getSystemId());
                }
            }
        }

        if (filterSet != null) {
            loadColumnName();
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = ConstantsUtils.PERCENCTAGE + stringFilter.getFilterString() + ConstantsUtils.PERCENCTAGE;

                    int sysId = 0;
                    List<HelperDTO> list = null;
                    if (ConstantUtil.ITEM_STATUS_M.equals(stringFilter.getPropertyId())) {
                        parameters.put(ConstantUtil.ITEM_STATUS_M + "~", Integer.valueOf(stringFilter.getFilterString()));

                    } else if (ConstantUtil.ITEM_TYPE.equals(stringFilter.getPropertyId())) {
                        if (!stringFilter.getFilterString().equals(ConstantUtil.SHOW_ALL)) {
                            parameters.put(ConstantUtil.ITEM_TYPE + "~", Integer.valueOf(stringFilter.getFilterString()));
                        }
                    } else if (ConstantsUtils.THERAPEUTIC_CLASS.equals(stringFilter.getPropertyId())) {
                        parameters.put(ConstantsUtils.THERAPEUTIC_CLASS + "~", Integer.valueOf(stringFilter.getFilterString()));
                    } else if (ConstantsUtils.BRAND.equals(stringFilter.getPropertyId()) && !ConstantsUtils.SELECT_ONE.equals(stringFilter.getFilterString())) {
                        DynamicQuery query = DynamicQueryFactoryUtil.forClass(BrandMaster.class);
                        query.add(RestrictionsFactoryUtil.eq(ConstantsUtils.BRAND_NAME, stringFilter.getFilterString()));
                        List<BrandMaster> listBrand = BrandMasterLocalServiceUtil.dynamicQuery(query);
                        brandMasterSid = new String[listBrand.size()];
                        for (int i = 0; i < listBrand.size(); i++) {
                            brandMasterSid[i] = String.valueOf(listBrand.get(i).getBrandMasterSid());
                        }
                        if (!stringFilter.getFilterString().equals("Show all")) {
                            parameters.put("brandMasterSid~", Arrays.toString(brandMasterSid).substring(1, Arrays.toString(brandMasterSid).length() - 1));
                        }
                    } else if ("form".equals(stringFilter.getPropertyId())) {
                        parameters.put("form" + "~", Integer.valueOf(stringFilter.getFilterString()));
                    } else if ("strength".equals(stringFilter.getPropertyId())) {
                        parameters.put("strength" + "~", Integer.valueOf(stringFilter.getFilterString()));
                    } else {
                        parameters.put(StringUtils.EMPTY + stringFilter.getPropertyId() + "~", filterString);
                    }
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    if (ConstantsUtils.ITEM_SID.equals(stringFilter.getPropertyId())) {
                        if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER)) {
                            parameters.put("systemId-greater", stringFilter.getValue());
                        } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS)) {
                            parameters.put("systemId-less", stringFilter.getValue());
                        } else if (stringFilter.getOperation().equals(stringFilter.getOperation().EQUAL)) {
                            parameters.put("systemId-equal", stringFilter.getValue());
                        }
                    }
//                }
                }
//                
            }
        }
        return parameters;
    }

    public List getItemIfpList(int systemId) {
        List itemContractList = new ArrayList<>();
        try {
            final DynamicQuery contractDynamicQuery = DynamicQueryFactoryUtil.forClass(IfpDetails.class);
            contractDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_MASTER_SYSTEM_ID, systemId));
            contractDynamicQuery.add(RestrictionsFactoryUtil.ne("inboundStatus", "D"));
            itemContractList = IfpContractDetailsLocalServiceUtil.dynamicQuery(contractDynamicQuery);
        } catch (SystemException ex) {
            LOGGER.error(ex);
                        ex.printStackTrace();

        }
        return itemContractList;
    }

    /**
     * Gets the Display Brand as string.
     *
     * @param brandName the brand name
     * @return the brand id
     */
    public String getDisplayBrand(final String brandName) throws SystemException, Exception {

        final SearchItemForm searchItemForm = new SearchItemForm();

        LOGGER.info("Enters getBrandId() P1: brandName " + brandName);
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(BrandMaster.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.BRAND_NAME,
                brandName));
        final List<BrandMaster> list = DAO.getBrandMasterList(cfpDynamicQuery);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final BrandMaster brandMaster = (BrandMaster) list.get(i);
                searchItemForm.setDisplayBrand(brandMaster.getDisplayBrand());
            }
        }
        return searchItemForm.getDisplayBrand();
    }

    public List getItemContractList(int systemId) {
        List itemContractList = new ArrayList<>();
        try {
            final DynamicQuery contractDynamicQuery = DynamicQueryFactoryUtil.forClass(IfpContractDetails.class);
            contractDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_MASTER_SYSTEM_ID, systemId));
            contractDynamicQuery.add(RestrictionsFactoryUtil.ne("inboundStatus", "D"));
            itemContractList = IfpContractDetailsLocalServiceUtil.dynamicQuery(contractDynamicQuery);
        } catch (SystemException e) {
            LOGGER.error(e);
                        e.printStackTrace();
        }
        return itemContractList;
    }

    public void insertToCPDetails(int systemId) {
        String query = CustomSQLUtil.get("item-CP").replace("?SID", String.valueOf(systemId));
        RsModelLocalServiceUtil.executeUpdateQuery(query, null, null);
    }
}
