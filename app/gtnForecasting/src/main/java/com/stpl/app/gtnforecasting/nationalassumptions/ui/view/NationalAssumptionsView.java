package com.stpl.app.gtnforecasting.nationalassumptions.ui.view;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.DataSelectionDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.ui.form.NationalAssumptions;
import com.stpl.app.gtnforecasting.nationalassumptions.ui.form.NationalAssumptionsForm;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.CommonConstants.SESSION_ID;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.v7.ui.VerticalLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author manikanta
 */
public class NationalAssumptionsView extends VerticalLayout implements View {

    private static final long serialVersionUID = -1L;
    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(NationalAssumptionsView.class);

    public static final String NAME = "nationalAssumptions";
    protected DataSelectionDTO dtoValue = new DataSelectionDTO();
    protected OptionGroup mode = new OptionGroup();
    protected SessionDTO sessionDto;

    public NationalAssumptionsView(SessionDTO sessionDto) {
        super();
        this.sessionDto=sessionDto;
        setSpacing(true);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        try {
            this.removeAllComponents();
            final SimpleDateFormat fmtID = new SimpleDateFormat("hhmmssms");
            NationalAssumptions nationalAssumptions = new NationalAssumptions(sessionDto);
            VaadinSession.getCurrent().setAttribute(SESSION_ID.getConstant(),
                    Integer.valueOf(fmtID.format(new Date())));
            VaadinSession.getCurrent().setAttribute("isCreated", "N");
            VaadinSession.getCurrent().setAttribute("ndcCreated", null);
            addComponent(new NationalAssumptionsForm(dtoValue, mode,sessionDto));
            int projectionId = (Integer) VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID);
            
            if (projectionId != 0) {
                nationalAssumptions.reloadTable();
            }

        } catch (PortalException | SystemException | NumberFormatException ex) {
            LOGGER.error(ex.getMessage());
        }

    }
}
