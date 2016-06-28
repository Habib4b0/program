package com.stpl.app.galforecasting.nationalassumptions.ui.view;

import com.stpl.app.galforecasting.nationalassumptions.dto.DataSelectionDTO;
import com.stpl.app.galforecasting.nationalassumptions.ui.form.NationalAssumptions;
import com.stpl.app.galforecasting.nationalassumptions.ui.form.NationalAssumptionsForm;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.CommonConstants.SESSION_ID;
import com.stpl.app.galforecasting.utils.Constant;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.VerticalLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jboss.logging.Logger;

/**
 * @author manikanta
 */
public class NationalAssumptionsView extends VerticalLayout implements View {

    private static final long serialVersionUID = -1L;
    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(NationalAssumptionsView.class);

    public static final String NAME = "nationalAssumptions";
    DataSelectionDTO dtoValue = new DataSelectionDTO();
    OptionGroup mode = new OptionGroup();

    public NationalAssumptionsView() throws Exception {
        super();
        setSpacing(true);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        try {
            this.removeAllComponents();
            final SimpleDateFormat fmtID = new SimpleDateFormat("hhmmssms");
            NationalAssumptions nationalAssumptions = new NationalAssumptions();
            VaadinSession.getCurrent().setAttribute(SESSION_ID.getConstant(),
                    Integer.valueOf(fmtID.format(new Date())));
            VaadinSession.getCurrent().setAttribute("isCreated", "N");
            VaadinSession.getCurrent().setAttribute("ndcCreated", null);
            addComponent(new NationalAssumptionsForm(dtoValue, mode));
            int projectionId = (Integer) VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID);
            if (projectionId != 0) {
                nationalAssumptions.reloadTable(projectionId);
            }

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

    }
}
