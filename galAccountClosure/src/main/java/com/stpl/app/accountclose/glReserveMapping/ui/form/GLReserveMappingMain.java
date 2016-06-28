/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.glReserveMapping.ui.form;

import com.stpl.app.accountclose.dto.GLReserveMappingDTO;
import com.stpl.app.accountclose.dto.GLReserveTreeDTO;
import com.stpl.app.accountclose.logic.GLReserveMappingLogic;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.utils.AbstractNotificationUtils;
import com.stpl.app.accountclose.utils.Constants;
import com.stpl.app.accountclose.utils.ErrorCodeUtil;
import com.stpl.app.accountclose.utils.ErrorCodes;
import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author kasiammal.m
 */
public class GLReserveMappingMain extends CustomComponent implements View {

    private static final Logger LOGGER = Logger.getLogger(GLReserveMappingMain.class);
    @UiField("componentitems")
    OptionGroup componentitems;
    @UiField("businessUnit")
    ComboBox businessUnit;
    @UiField("programType")
    ComboBox programType;
    @UiField("programsubType")
    ComboBox programsubType;
    @UiField("frequency")
    ComboBox frequency;
    @UiField("resetbtnmain")
    Button resetbtnmain;
    @UiField("generatebtn")
    Button generatebtn;
    GLReserveMappingLogic logic = new GLReserveMappingLogic();
    public TabSheet tabsheet = new TabSheet();
    @UiField("main")
    public VerticalLayout layout;
    @UiField("resultLayout")
    VerticalLayout resultLayout;
    Tree selectiontable = new Tree();
    String mode = StringUtils.EMPTY;
    private Boolean expandFlag = Boolean.TRUE;
    private CustomTreeContainer<GLReserveTreeDTO> treeContainer = new CustomTreeContainer<GLReserveTreeDTO>(GLReserveTreeDTO.class);
    private final SessionDTO session;
    private boolean saveFlag;
    ReserveAccount reserveAccount;
    RulesTab rulesTab;
    Businessprocess businessProcess;
    public static final ResourceBundle alertMsg = ResourceBundle.getBundle("errorcodes.errorcode");
    GLReserveMappingDTO binderDto = new GLReserveMappingDTO();
    private CustomFieldGroup binder = new CustomFieldGroup(new BeanItem<GLReserveMappingDTO>(binderDto));

    public GLReserveMappingMain(SessionDTO session, String mode) throws SystemException, Exception {
        this.mode = mode;
        this.session = session;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/GLReserveMapiingmainform.xml"), this));
        getBinder();
        init();
    }

    private CustomFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<>(binderDto));
        binder.setBuffered(true);
        return binder;
    }

    private void init() throws SystemException, Exception {
        reserveAccount = new ReserveAccount(session, mode, this);
        rulesTab = new RulesTab(session, mode, reserveAccount);
        businessProcess = new Businessprocess(session, mode, reserveAccount, tabsheet);
        businessUnit.focus();
        componentitems.addItem("Collapsed");
        componentitems.addItem("Expand");
        componentitems.select("Collapsed");
        componentitems.setImmediate(true);
        resultLayout.addComponent(selectiontable);
        selectiontable.setSizeFull();
        selectiontable.setWidth("100%");
        selectiontable.setHeight("100%");
        selectiontable.setImmediate(true);
        selectiontable.setContainerDataSource(treeContainer);
        selectiontable.setItemCaptionPropertyId("levelValue");
        List<GLReserveTreeDTO> tempList = logic.loadCompany();
        for (GLReserveTreeDTO tempList1 : tempList) {
            treeContainer.addItem(tempList1);
            treeContainer.setChildrenAllowed(tempList1, true);
        }
        selectiontable.addExpandListener(new Tree.ExpandListener() {
            public void nodeExpand(Tree.ExpandEvent event) {
                if (event != null && expandFlag) {
                    GLReserveTreeDTO temp = (GLReserveTreeDTO) event.getItemId();
                    if (temp.getLevelNo() == 1) {
                        List<GLReserveTreeDTO> tempList = logic.loadDT(String.valueOf(temp.getLevelId()));
                        for (GLReserveTreeDTO tempList1 : tempList) {
                            treeContainer.addItem(tempList1);
                            treeContainer.setChildrenAllowed(tempList1, true);
                            treeContainer.setParent(tempList1, event.getItemId());
                        }
                    } else if (temp.getLevelNo() == 2) {
                        GLReserveTreeDTO parent = (GLReserveTreeDTO) treeContainer.getParent(temp);
                        List<GLReserveTreeDTO> tempList = logic.loadDST(String.valueOf(parent.getLevelId()), String.valueOf(temp.getLevelId()));
                        for (GLReserveTreeDTO tempList1 : tempList) {
                            treeContainer.addItem(tempList1);
                            treeContainer.setChildrenAllowed(tempList1, false);
                            treeContainer.setParent(tempList1, event.getItemId());
                        }
                    }
                }

            }
        });

        selectiontable.addCollapseListener(new Tree.CollapseListener() {
            public void nodeCollapse(Tree.CollapseEvent event) {
                Collection<?> parent = (Collection<?>) treeContainer.getChildren(event.getItemId());
                List<Object> temp = new ArrayList<Object>();
                for (Object next : parent) {
                    temp.add(next);
                }
                for (int i = temp.size() - 1; i >= 0; i--) {
                    treeContainer.removeItemRecursively(temp.get(i));
                }

            }
        });

        selectiontable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            public void itemClick(ItemClickEvent event) {
                GLReserveTreeDTO temp = (GLReserveTreeDTO) event.getItemId();
                if (!saveFlag) {
                    treeItemClickLogic(temp);

                } else {
                    new AbstractNotificationUtils() {
                        @Override
                        public void noMethod() {

                        }

                        @Override
                        public void yesMethod() {
                            try {
                                new ReserveAccount().saveButtonLogic(StringUtils.EMPTY);
                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }
                        }
                    }.getConfirmationMessage(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_002), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_007) + (ErrorCodes.PERIOD) + (ErrorCodes.ERROR_CODE_008) + (ErrorCodes.QUESTION));

                }
            }
        });
        frequency = logic.loadFrequency(frequency, Boolean.FALSE);
        businessUnit = logic.comboBoxAddItems(businessUnit, "gl.getCompany", Boolean.FALSE, StringUtils.EMPTY, StringUtils.EMPTY);
        programType = logic.comboBoxAddItems(programType, "gl.loadDType", Boolean.FALSE, StringUtils.EMPTY, StringUtils.EMPTY);
        programsubType = logic.comboBoxAddItems(programsubType, "gl.loadDSType", Boolean.FALSE, StringUtils.EMPTY, StringUtils.EMPTY);
        selectiontable.select(this);
        if (Constants.VIEW_MODE.equals(mode) || Constants.EDIT_MODE.equals(mode)) {
            String frequency1 = session.getGlReserveMapping().getFrequency();
            String businessUnit1 = session.getGlReserveMapping().getBusinessUnit().toString();
            String programType1 = session.getGlReserveMapping().getProgramType().toString();
            String programSubType1 = String.valueOf(session.getGlReserveMapping().getProgramsubType());

            for (Object itemId : businessUnit.getItemIds()) {
                if (businessUnit1.equals(itemId.toString())) {
                    businessUnit.select(itemId);
                    break;
                }
            }
            for (Object itemId : programType.getItemIds()) {
                if (programType1.equals(itemId.toString())) {
                    programType.select(itemId);
                    break;
                }
            }
            for (Object itemId : programsubType.getItemIds()) {
                if (String.valueOf(itemId).equals(programSubType1)) {
                    programsubType.select(itemId);
                    break;
                }
            }
            frequency.select(frequency1);
            frequency.setEnabled(false);
            businessUnit.setEnabled(false);
            programType.setEnabled(false);
            programsubType.setEnabled(false);
            selectiontable.setEnabled(false);
            resetbtnmain.setEnabled(false);
        }

        tabsheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabsheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        tabsheet.setImmediate(true);
        tabsheet.markAsDirty();
        tabsheet.markAsDirtyRecursive();
        if (Constants.VIEW_MODE.equals(mode) || Constants.EDIT_MODE.equals(mode) || Constants.COPY_MODE.equals(mode)) {
            tabsheet.addTab(reserveAccount, "Reserve Accounts", null, 0);
            tabsheet.addTab(businessProcess, "Business Process", null, 1);
            tabsheet.addTab(rulesTab, "Rules", null, 2);
            if ("Adjustment".equals(businessProcess.businessProcess.getValue())) {
                tabsheet.getTab(2).setVisible(false);
            } else {
                tabsheet.getTab(2).setVisible(true);
            }
        } else {
            tabsheet.addTab(reserveAccount, "Reserve Accounts", null, 0);
            tabsheet.addTab(businessProcess, "Business Process", null, 1);
            tabsheet.addTab(rulesTab, "Rules", null, 2);
        }
        tabsheet.setSelectedTab(0);
        attachTabChangeListener();
        layout.addComponent(tabsheet);
    }

    @UiHandler("businessUnit")
    public void businessUnitDdlbChange(Property.ValueChangeEvent event) throws SystemException, PortalException, Exception {
        if (businessUnit.getValue() != null) {
            HelperDTO businessUnitVal = (HelperDTO) businessUnit.getValue();
            programType.removeAllItems();
            programType = logic.comboBoxAddItems(programType, "gl.loadDType", Boolean.FALSE, String.valueOf(businessUnitVal.getId()), StringUtils.EMPTY);
        } else {
            programType.setValue(null);
        }
    }

    @UiHandler("programType")
    public void deductionTypeDdlbChange(Property.ValueChangeEvent event) throws SystemException, PortalException, Exception {
        if (programType.getValue() != null) {
            HelperDTO programTypeVal = (HelperDTO) programType.getValue();
            String compId = StringUtils.EMPTY;
            programsubType.removeAllItems();
            if (businessUnit.getValue() != null) {
                compId = String.valueOf(((HelperDTO) businessUnit.getValue()).getId());
            }
            programsubType = logic.comboBoxAddItems(programsubType, "gl.loadDSType", Boolean.FALSE, compId, String.valueOf(programTypeVal.getId()));
        } else {
            programsubType.setValue(null);
        }
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    @UiHandler("resetbtnmain")
    public void ResetbtnmainClick(Button.ClickEvent event) throws Exception {

        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {

            }

            @Override
            public void yesMethod() {
                try {
                    businessUnit.select(Constants.IndicatorConstants.SELECT_ONE.getConstant());
                    programType.select(Constants.IndicatorConstants.SELECT_ONE.getConstant());
                    programsubType.select(Constants.IndicatorConstants.SELECT_ONE.getConstant());
                    saveFlag = false;
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        }.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_006) + (ErrorCodes.QUESTION));

    }

    @UiHandler("generatebtn")
    public void generatebtnClick(Button.ClickEvent event) throws Exception {
        logic.loadGLRSVMasterTable();
        expandFlag = Boolean.FALSE;
        if ("Collapsed".equals(componentitems.getValue())) {
            generateBtnClick();
        } else {
            treeContainer.removeAllItems();
            List<GLReserveTreeDTO> tempList = logic.loadCompany();
            for (GLReserveTreeDTO tempList1 : tempList) {
                treeContainer.addItem(tempList1);
                treeContainer.setChildrenAllowed(tempList1, true);
                expandLogic(tempList1);
                selectiontable.expandItemsRecursively(tempList1);
            }
        }
        expandFlag = Boolean.TRUE;
    }

    private void expandLogic(GLReserveTreeDTO parent) {
        if (parent.getLevelNo() == 1) {
            List<GLReserveTreeDTO> tempList = logic.loadDT(String.valueOf(parent.getLevelId()));
            for (GLReserveTreeDTO tempList1 : tempList) {
                treeContainer.addItem(tempList1);
                treeContainer.setChildrenAllowed(tempList1, true);
                treeContainer.setParent(tempList1, parent);
                LOGGER.info("Level 1 : " + parent.equals(treeContainer.getParent(tempList1)));

                expandLogic(tempList1);
            }
        } else if (parent.getLevelNo() == 2) {

            GLReserveTreeDTO preParent = (GLReserveTreeDTO) treeContainer.getParent(parent);
            LOGGER.info("Level 2 : " + preParent == treeContainer.getParent(parent));
            List<GLReserveTreeDTO> tempList = logic.loadDST(String.valueOf(preParent.getLevelId()), String.valueOf(parent.getLevelId()));
            for (GLReserveTreeDTO tempList1 : tempList) {
                treeContainer.addItem(tempList1);
                treeContainer.setChildrenAllowed(tempList1, false);
                treeContainer.setParent(tempList1, parent);
            }
        }
    }

    public void generateBtnClick() {
        logic.loadGLRSVMasterTable();
        treeContainer.removeAllItems();
        List<GLReserveTreeDTO> tempList = logic.loadCompany();
        for (GLReserveTreeDTO tempList1 : tempList) {
            treeContainer.addItem(tempList1);
            treeContainer.setChildrenAllowed(tempList1, true);
        }
    }

    public void setSaveFlag(boolean saveFlag) {
        this.saveFlag = saveFlag;
    }

    public void treeItemClickLogic(GLReserveTreeDTO temp) {
        if (temp.getLevelNo() == 1) {
            for (Object itemId : businessUnit.getItemIds()) {
                if (temp.getLevelValue().equals(itemId.toString())) {
                    break;
                }
            }
            programType.select(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            programsubType.select(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        } else if (temp.getLevelNo() == 2) {
            GLReserveTreeDTO parent1 = (GLReserveTreeDTO) treeContainer.getParent(temp);
            for (Object itemId : businessUnit.getItemIds()) {
                if (parent1.getLevelValue().equals(itemId.toString())) {
                    break;
                }
            }
            for (Object itemId : programType.getItemIds()) {
                if (temp.getLevelValue().equals(itemId.toString())) {
                    break;
                }
            }
            programsubType.select(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        } else {
            GLReserveTreeDTO parent1 = (GLReserveTreeDTO) treeContainer.getParent(temp);
            GLReserveTreeDTO parent2 = (GLReserveTreeDTO) treeContainer.getParent(parent1);
            for (Object itemId : businessUnit.getItemIds()) {
                if (parent2.getLevelValue().equals(itemId.toString())) {
                    businessUnit.select(itemId);
                    break;
                }
            }
            for (Object itemId : programType.getItemIds()) {
                if (parent1.getLevelValue().equals(itemId.toString())) {
                    programType.select(itemId);
                    break;
                }
            }
            for (Object itemId : programsubType.getItemIds()) {
                if (temp.getLevelValue().equals(itemId.toString())) {
                    programsubType.select(itemId);
                    break;
                }
            }
            saveFlag = true;
        }
    }

    private void attachTabChangeListener() {
        tabsheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            @Override
            public void selectedTabChange(final TabSheet.SelectedTabChangeEvent event) {
                final TabSheet.Tab tab = (TabSheet.Tab) event.getTabSheet().getTab(event.getTabSheet().getSelectedTab());
                int tabPosition = event.getTabSheet().getTabPosition(tab);
                try {
                    onTabChange(tabPosition);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }

            private void onTabChange(int tabPosition) {
                if (tabPosition == 1) {
                    detachListeners(tabsheet);
                    tabsheet.replaceComponent(businessProcess, businessProcess);
                    attachTabChangeListener();
                }
                if (tabPosition == 2) {
                    detachListeners(tabsheet);
                    tabsheet.replaceComponent(rulesTab, rulesTab);
                    rulesTab.loadSelectedRules(session);
                    attachTabChangeListener();
                }
            }
        });

    }

    public void detachListeners(TabSheet tabsheet) {
        List<TabSheet.SelectedTabChangeListener> listeners = new ArrayList<TabSheet.SelectedTabChangeListener>();

        listeners = (List<TabSheet.SelectedTabChangeListener>) tabsheet
                .getListeners(TabSheet.SelectedTabChangeEvent.class);

        for (TabSheet.SelectedTabChangeListener object : listeners) {
            tabsheet.removeSelectedTabChangeListener(object);

        }

    }
}
