package com.stpl.app.cff.ui.table;

import com.vaadin.ui.JavaScript;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterTable;

/**
 *
 * @author Sriram.Vaitheeswaran
 */
public class CFFPagedFilterTable extends ExtPagedFilterTable{
    
    @Override
    public void beforeClientResponse(boolean initial) {
        super.beforeClientResponse(initial); 
        JavaScript.getCurrent().execute("addHyperLink();");
    }
    
    
}
