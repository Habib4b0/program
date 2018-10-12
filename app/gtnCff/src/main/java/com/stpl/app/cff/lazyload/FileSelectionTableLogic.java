/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.lazyload;

import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.logic.CFFLogic;
import com.stpl.app.cff.ui.fileselection.dto.FileSelectionDTO;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.Collections;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mohamed.hameed
 */
public class FileSelectionTableLogic extends PageTableLogic {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileSelectionTableLogic.class);
    private final CFFLogic logic=new CFFLogic();
    private boolean iscount=false;
    private SessionDTO sessionDTO;
    private String businessUnit;

    public FileSelectionTableLogic() {
        super();
    }
    
    @Override
    public int getCount() {
        int count =0;
        if(iscount){
        count = Integer.parseInt(String.valueOf(logic.getFileName(true,sessionDTO, getFilters(),businessUnit)));
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<FileSelectionDTO> list = (List<FileSelectionDTO>) logic.getFileName(false, sessionDTO, getFilters(), businessUnit);
        LOGGER.debug("getCount= {}", list.size());
        return Collections.unmodifiableList(list);
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        FileSelectionDTO dto = (FileSelectionDTO) object;
        ((BeanItemContainer<FileSelectionDTO>) container).addBean(dto);
        return dto;
    }
    public void setSearchData(SessionDTO sessionDTO,String businessUnit){   
        this.iscount=true;
        this.sessionDTO=sessionDTO;
        this.businessUnit=businessUnit;
        this.clearAll();
        this.setRequiredCount(true);
        this.setCurrentPage(1);
    }
}