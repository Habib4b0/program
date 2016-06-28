/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.forecast.ui.view;

import com.stpl.app.adminconsole.forecast.dto.ForecastDTO;
import com.stpl.app.adminconsole.forecast.ui.form.ForecastSearchIndex;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class ForecastSearchView.
 *
 * @author mohamed
 */
public class ForecastSearchView extends VerticalLayout implements View {

    /**
     * String name.
     */
    public static final String NAME = ConstantsUtils.EMPTY;

    /**
     * Constructor reference for ForecastSearchIndex.
     */
    private ForecastSearchIndex forecastIndex;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ForecastSearchIndex.class); 

    /**
     * The forecast binder.
     */
    private ErrorfulFieldGroup forecastBinder;

    /**
     * The forecast dto.
     */
    private ForecastDTO forecastDTO;

    /**
     * Gets the forecast binder.
     *
     * @return the forecast binder
     */
    public ErrorfulFieldGroup getForecastBinder() {
        return forecastBinder;
    }

    /**
     * Sets the forecast binder.
     *
     * @param forecastBinder the new forecast binder
     */
    public void setForecastBinder(final ErrorfulFieldGroup forecastBinder) {
        this.forecastBinder = forecastBinder;
    }

    /**
     * Gets the forecast dto.
     *
     * @return the forecast dto
     */
    public ForecastDTO getForecastDTO() {
        return forecastDTO;
    }

    /**
     * Sets the forecast dto.
     *
     * @param forecastDTO the new forecast dto
     */
    public void setForecastDTO(final ForecastDTO forecastDTO) {
        this.forecastDTO = forecastDTO;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public static String getName() {
        return NAME;
    }

    /**
     * Gets the forecast index.
     *
     * @return the forecast index
     */
    public ForecastSearchIndex getForecastIndex() {
        return forecastIndex;
    }

    /**
     * Gets the logger.
     *
     * @return the logger
     */
    public static Logger getLogger() {
        return LOGGER;
    }

    /**
     * constructor ForecastSearchView.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public ForecastSearchView() throws SystemException, PortalException, Exception {

        super();
        LOGGER.info("ForecastSearchView constructor initiated ");
        forecastIndex = new ForecastSearchIndex(forecastBinder);
        setSpacing(true);
        addComponent(forecastIndex);
        setStyleName("bootstrap");
        LOGGER.info("ForecastSearchView constructor ended ");

    }

    /**
     * enter method.
     *
     * @param event the event
     */
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        try {
            LOGGER.info("Inside enter -- ?>>>> ");
            this.removeAllComponents();
            forecastIndex = new ForecastSearchIndex(forecastBinder);
            addComponent(forecastIndex);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
}
