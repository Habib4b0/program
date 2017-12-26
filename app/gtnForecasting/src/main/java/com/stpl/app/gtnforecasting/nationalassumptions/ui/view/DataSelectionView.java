package com.stpl.app.gtnforecasting.nationalassumptions.ui.view;


import com.stpl.app.gtnforecasting.nationalassumptions.dto.DataSelectionDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.ui.form.DataSelectionIndex;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.apache.commons.lang.StringUtils;


/**
 * @author manikanta
 */
public class DataSelectionView extends VerticalLayout implements View {

    private static final long serialVersionUID = 918856446098386044L;
    public static final String NAME = StringUtils.EMPTY;
    protected OptionGroup mode = new OptionGroup();
    protected DataSelectionDTO dtoValue = new DataSelectionDTO();
    protected CustomFieldGroup dataSelectionBinder = new CustomFieldGroup(new BeanItem(DataSelectionDTO.class)) ;
    protected SessionDTO session;

    public DataSelectionView(SessionDTO session) {
        super();
        setSpacing(true);
        this.session=session;
        addComponent(new DataSelectionIndex(dtoValue, dataSelectionBinder, session));
    }

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        this.removeAllComponents();
        addComponent(new DataSelectionIndex(dtoValue, dataSelectionBinder,session));
       

       

    }
}
