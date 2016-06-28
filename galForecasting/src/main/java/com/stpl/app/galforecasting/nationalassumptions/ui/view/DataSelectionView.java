package com.stpl.app.galforecasting.nationalassumptions.ui.view;


import com.stpl.app.galforecasting.nationalassumptions.dto.DataSelectionDTO;
import com.stpl.app.galforecasting.nationalassumptions.ui.form.DataSelectionIndex;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 * @author manikanta
 */
public class DataSelectionView extends VerticalLayout implements View {

    private static final long serialVersionUID = 918856446098386044L;
    @SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(DataSelectionView.class);
    public static final String NAME = StringUtils.EMPTY;
    TextField name = new TextField();
    OptionGroup mode = new OptionGroup();
    DataSelectionDTO dtoValue = new DataSelectionDTO();
    CustomFieldGroup dataSelectionBinder = new CustomFieldGroup(new BeanItem(DataSelectionDTO.class)) ;

    public DataSelectionView() {
        super();
        setSpacing(true);
        addComponent(new DataSelectionIndex(dtoValue, dataSelectionBinder, mode));
    }

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        this.removeAllComponents();
        addComponent(new DataSelectionIndex(dtoValue, dataSelectionBinder,mode));
       

       

    }
}
