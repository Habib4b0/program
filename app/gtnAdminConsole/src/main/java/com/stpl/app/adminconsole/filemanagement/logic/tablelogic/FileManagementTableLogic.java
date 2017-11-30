/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.filemanagement.logic.tablelogic;

import com.stpl.app.adminconsole.filemanagement.dto.FileMananagementResultDTO;
import com.stpl.app.adminconsole.filemanagement.logic.FileManagementLogic;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author vinodhini
 */
public class FileManagementTableLogic extends PageTableLogic {

    private static final Logger LOGGER = Logger.getLogger(FileManagementTableLogic.class);
    private final FileManagementLogic searchLogic = new FileManagementLogic();
    private HelperDTO fileType;
    private String country;
    private String businessUnit;
    private Object companyId;
    private boolean isFirstLoad = false;
    private boolean isResultsEmpty;
    private boolean isReset = false;

    @Override
    public int getCount() {
        int count = 0;
        try {
            if (isFirstLoad) {
                count = (Integer) searchLogic.getFileHistoryResults(fileType, country,businessUnit,companyId ,0, 0, this.getSortByColumns(), this.getFilters(), true);
            }
            isResultsEmpty = count == 0;
            count = isReset ? 0 : count;
        } catch (Exception ex) {
            LOGGER.error(ex);
          
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List list = new ArrayList();
        if (isFirstLoad) {
            try {
                list = (List) searchLogic.getFileHistoryResults(fileType, country,businessUnit,companyId, start, offset, this.getSortByColumns(), this.getFilters(), false);
            } catch (Exception ex) {
                LOGGER.error(ex);
                
            }
        }
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        FileMananagementResultDTO dto = (FileMananagementResultDTO) object;
        ((BeanItemContainer<FileMananagementResultDTO>) container).addBean(dto);
        return dto;
    }

    public void configureSearchData(HelperDTO fileType, String country,String businessUnit,final Object companyId) {
        isFirstLoad = true; 
        isReset = false;
        this.clearAll();
        this.getFilters().clear();
        this.fileType= fileType;
        this.country = country;
        this.businessUnit=businessUnit;
        this.companyId = companyId;
        this.setRequiredCount(true);
        this.setCurrentPage(1);
    }

    public boolean isResultsEmpty() {
        return isResultsEmpty;
    }

    public void setReset(boolean isReset) {
        this.isReset = isReset;
    }

}
