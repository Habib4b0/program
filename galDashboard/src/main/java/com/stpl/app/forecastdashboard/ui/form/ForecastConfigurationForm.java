
package com.stpl.app.forecastdashboard.ui.form;

import com.stpl.app.forecastdashboard.logic.ForecastConfigurationLogic;
import com.stpl.app.model.ForecastConfig;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jboss.logging.Logger;

/**
 *
 * @author sriram
 */
public class ForecastConfigurationForm extends CustomComponent implements View {

    /* The Logic class */
    ForecastConfigurationLogic logic = new ForecastConfigurationLogic();

    /* The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(ForecastConfigurationForm.class);

    public ForecastConfigurationForm() throws SystemException, Exception {
        super();
        init();
        configureFields();
    }

    /**
     * Inits the.
     *
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public void init() throws SystemException, Exception {
        setCompositionRoot(addToContent());
    }

    /**
     * Adds the to content.
     *
     * @return the component
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    private Component addToContent() {
        LOGGER.info("Entering FC addToContent ");
        final HorizontalLayout content = new HorizontalLayout();
        content.addStyleName("forecastConfigLabelComponent");
//        content.setSpacing(true);
//        MarginInfo margininfo = new MarginInfo(false, true, false, true);
//        content.setMargin(margininfo);
        content.addComponent(addForecastImage());
        content.addComponent(addForecastLabel());
        content.addStyleName("mainLayout");
        LOGGER.info("Ending FC addToContent");
        return content;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    /**
     * Adds the Market Share Chart.
     *
     * @return the panel
     */
    private Component addForecastLabel() {
        LOGGER.info("Entering FC addForecastLabel");
        Date[] configurationDates = logic.getForecastConfigurationDates();
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//        ForecastConfig forecastConfig = logic.getForecastConfigurationTimePeriod();
//        
//        Date startDate = forecastConfig.getFromDate();
//        Date endDate = forecastConfig.getToDate();
//        
//        String startDateString = formatter.format(startDate);
//        String endDateString = formatter.format(endDate);
        
        String url = logic.getForecastConfigurationModuleURL();
        
        Label forecastLabel = new Label("", ContentMode.HTML);
        forecastLabel.setValue("<div style=\"/*background: dodgerblue;color: white;*/ font-weight: 500; text-align:left\"> Forecast Configuration Period : <marquee style=\"width:70%\">Commercial - " 
                + formatter.format(configurationDates[0]) + " to " + formatter.format(configurationDates[1]) + ", Government - "+ formatter.format(configurationDates[2]) + " to " 
                + formatter.format(configurationDates[3]) + ", Returns - "+ formatter.format(configurationDates[4]) + " to " + formatter.format(configurationDates[5]) 
                + ", National Assumptions - "+ formatter.format(configurationDates[6]) + " to " + formatter.format(configurationDates[7]) 
                + " </marquee>&nbsp;&nbsp; <br/><b><center><a href=\"" + url + "\" /*style=\"color: yellow;*/ \">Click Here to change </a></center></b>"
                + "</div>");
        LOGGER.info("Ending FC addForecastLabel");
        return forecastLabel;
    }  

    /**
     * Configure fields.
     *
     * @throws Exception
     * @throws SystemException
     */
    private void configureFields() throws SystemException, Exception {

    }

    private Component addForecastImage() {
        Button button = new Button();
        button.setStyleName("link");
        button.setIcon(new ThemeResource("../../icons/forecast-period-icon.png"));
        return button;
    }
    
}
