/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.lazyLoad;

import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.logic.CFFLogic;
import com.stpl.app.cff.ui.fileSelection.dto.FileSelectionDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author mohamed.hameed
 */
public class FileSelectionTableLogic extends PageTableLogic {
    private static final Logger LOGGER = Logger.getLogger(FileSelectionTableLogic.class);
    private CFFLogic logic=new CFFLogic();
    private List<FileSelectionDTO> list;
    private boolean iscount=false;
    private SessionDTO sessionDTO;
    private String businessUnit;
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
        list=(List<FileSelectionDTO>)logic.getFileName(false,sessionDTO, getFilters(),businessUnit);
        LOGGER.debug("getCount"+list.size());
        return list;
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