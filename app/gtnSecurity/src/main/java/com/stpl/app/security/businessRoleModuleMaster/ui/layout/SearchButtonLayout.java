package com.stpl.app.security.businessRoleModuleMaster.ui.layout;

import java.util.List;
import com.stpl.app.security.businessRoleModuleMaster.dto.SearchBusinessRoleModuleForm;
import com.stpl.app.security.businessRoleModuleMaster.dto.SearchDTO;
import com.stpl.app.security.businessRoleModuleMaster.logic.BusinessRoleModuleSearchLogic;
import com.stpl.app.security.businessRoleModuleMaster.util.CommonUtils;
import com.stpl.app.security.businessRoleModuleMaster.util.ModuleNameCheckingUtil;
import com.stpl.app.security.businessRoleModuleMaster.util.UIUtils;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.HelperUtils;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.LogFactory;
import com.vaadin.v7.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.server.ErrorHandler;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.v7.ui.CheckBox;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.HorizontalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchButtonLayout extends HorizontalLayout {

	private static final long serialVersionUID = 1L;

	
	private ErrorfulFieldGroup binder;
	private BusinessRoleModuleSearchLogic businessRoleModuleLogic=new BusinessRoleModuleSearchLogic();
	
	private BeanItemContainer<SearchBusinessRoleModuleForm> searchResultbeans;
	private BeanItemContainer<SearchBusinessRoleModuleForm> searchFieldResult;
	private ExtFilterTable table;
	private ExtFilterTable tableField;
	private ComboBox subModuleName;
	private ComboBox moduleName;
	private ComboBox businessRoleName;
	private CheckBox add;
        private CheckBox edit;
        private CheckBox view;
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchButtonLayout.class
			.getName());
	private final String Select=CommonUtils.SELECT_ONE;
	public SearchButtonLayout(ErrorfulFieldGroup binder,BeanItemContainer<SearchBusinessRoleModuleForm> searchResultbeans,BeanItemContainer<SearchBusinessRoleModuleForm> searchFieldResult,ExtFilterTable table,ExtFilterTable tableField,ComboBox subModuleName,ComboBox moduleName,ComboBox businessRoleName
        ,CheckBox add,CheckBox edit,CheckBox view) {
		super();
		this.table = table;
		this.tableField = tableField;
		this.binder = binder;
		this.searchResultbeans =searchResultbeans;
		this.searchFieldResult =searchFieldResult;
		this.subModuleName=subModuleName;
		this.moduleName=moduleName;
		this.businessRoleName=businessRoleName;
		this.add=add;
		this.edit=edit;
		this.view=view;
                init();
	}

	private void init(){
		this.setSpacing(true);
		SearchButton();
		ResetButton();
	}

	private void SearchButton() {
		// Commit button
           LOGGER.info("In search button----------------");
		Button btnSearch = new Button("Search");
		btnSearch.setWidth("75");
		btnSearch.setErrorHandler(new ErrorHandler() {

            private static final long serialVersionUID = 1L;

            public void error(com.vaadin.server.ErrorEvent event) {
                   
            
                return;
            }
    });
		
		btnSearch.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			
			public void buttonClick(ClickEvent event) {
				try {
					binder.getFields();
					binder.commit();
					
					try {
					    if(businessRoleName.getValue()!=Select || subModuleName.getValue()!=Select || moduleName.getValue() !=Select){	
					    if(subModuleName.getValue()!=Select || moduleName.getValue() !=Select){	
					    if( moduleName.getValue() !=Select){	
						List<SearchBusinessRoleModuleForm> searchResults=businessRoleModuleLogic.searchmoduleAccessDetails(binder);
						List<SearchBusinessRoleModuleForm> searchFieldResults=businessRoleModuleLogic.searchFieldAccessDetails(binder);
						searchFieldResult.removeAllItems();
                                                for (SearchBusinessRoleModuleForm searchFieldResult1 : searchFieldResults) {
                                                    searchFieldResult.addBean(searchFieldResult1);
                                                }
						searchResultbeans.removeAllItems();
						searchResultbeans.addAll(searchResults);
						if(ModuleNameCheckingUtil.moduleNameCheckingFun(subModuleName.getValue().toString())){
							tableField.setVisibleColumns(UIUtils.getInstance().businessRoleModuleMasterFieldsView);
							tableField.setColumnHeaders(UIUtils.getInstance().businessRoleModuleMasterHeaderView);
						}else{
							tableField.setVisibleColumns(UIUtils.getInstance().businessRoleModuleMasterFields);
						        tableField.setColumnHeaders(UIUtils.getInstance().businessRoleModuleMasterFieldsHeader);
						}
                                            }else{
                                  tableField.removeAllItems();
                                  table.removeAllItems();
                                  MessageBox.showPlain(Icon.ERROR, "No Search Criteria", "Please enter ModuleName", ButtonId.OK);
        
        }
                                            }else{
                                  tableField.removeAllItems();
                                  table.removeAllItems();
                                  MessageBox.showPlain(Icon.ERROR, "Search Criteria", "Please enter SubModuleName", ButtonId.OK);
        
        }
                                            }else{
                                  tableField.removeAllItems();
                                  table.removeAllItems();
                                  MessageBox.showPlain(Icon.ERROR, "Search Criteria", "Please enter Search Criteria", ButtonId.OK);
        
        }
                                            } catch (SystemException e) {
						LOGGER.error(e.getMessage());
                                                
						
					} catch (PortalException e) {
						LOGGER.error(e.getMessage());
                                               					}
					
				} catch (CommitException e) {
					LOGGER.error(e.getMessage());
                                       
				}
                                catch (Exception e) {
					LOGGER.error(e.getMessage());
                                 
				}
			}
		});
		this.addComponent(btnSearch);
	}

	private void ResetButton() {
		Button btnReset = new Button("Reset");
		btnReset.setWidth("75");
		btnReset.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;
			public void buttonClick(ClickEvent event) {
				binder.discard();
				binder.setItemDataSource(new BeanItem<SearchDTO>(new SearchDTO()));
				searchResultbeans.removeAllItems();
				searchFieldResult.removeAllItems();
				subModuleName.removeAllItems();
                                moduleName.removeAllItems();
				subModuleName.addItem(CommonUtils.SELECT_ONE);
                               moduleName.addItem(CommonUtils.SELECT_ONE);
				subModuleName.select(CommonUtils.SELECT_ONE);
                               moduleName.select(CommonUtils.SELECT_ONE);
                                businessRoleName.removeAllItems();
                               new HelperUtils().getNativeSelect(businessRoleName, businessRoleModuleLogic.getBusinessRoleNames());
                                 businessRoleName.select(CommonUtils.SELECT_ONE);
                               businessRoleName.select(CommonUtils.SELECT_ONE);
			       add.setValue(Boolean.FALSE);
                               edit.setValue(Boolean.FALSE);
                               view.setValue(Boolean.FALSE);
                        }
		});
		this.addComponent(btnReset);
	}

}
