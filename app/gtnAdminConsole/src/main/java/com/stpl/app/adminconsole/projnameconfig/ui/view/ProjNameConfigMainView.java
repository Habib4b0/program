/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.projnameconfig.ui.view;

import com.stpl.app.adminconsole.projnameconfig.dto.ProjectionNameDTO;
import com.stpl.app.adminconsole.projnameconfig.ui.form.ProjNameConfigIndex;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import org.jboss.logging.Logger;

/**
 * The Class ProjNameConfigMainView.
 *
 * @author santanukumar
 */
public class ProjNameConfigMainView extends VerticalLayout implements View {

    /**
     * String name.
     */
    public static final String NAME = ConstantsUtils.EMPTY;

    /**
     * Constructor reference for ForecastSearchIndex.
     */
    private final ProjNameConfigIndex nameIndex;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ProjNameConfigMainView.class);

    /**
     * The forecast binder.
     */
    private CustomFieldGroup forecastBinder;

    /**
     * The forecast dto.
     */
    private ProjectionNameDTO projectionNameDTO;

    /**
     * Gets the forecast binder.
     *
     * @return the forecast binder
     */
    public CustomFieldGroup getForecastBinder() {
        return forecastBinder;
    }

    /**
     * Sets the forecast binder.
     *
     * @param forecastBinder the new forecast binder
     */
    public void setForecastBinder(final CustomFieldGroup forecastBinder) {
        this.forecastBinder = forecastBinder;
    }

    /**
     * Gets the forecast dto.
     *
     * @return the forecast dto
     */
    public ProjectionNameDTO getForecastDTO() {
        return projectionNameDTO;
    }

    /**
     * Sets the forecast dto.
     *
     * @param forecastDTO the new forecast dto
     */
    public void setForecastDTO(final ProjectionNameDTO forecastDTO) {
        this.projectionNameDTO = forecastDTO;
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
    public ProjNameConfigIndex getForecastIndex() {
        return nameIndex;
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
    public ProjNameConfigMainView() throws SystemException, PortalException {

        super();
        LOGGER.debug("ProjNameConfigMainView constructor initiated ");
        nameIndex = new ProjNameConfigIndex();
        setSpacing(true);
        addComponent(nameIndex);
        LOGGER.debug("ProjNameConfigMainView constructor ended ");

    }

    /**
     * enter method.
     *
     * @param event the event
     */
    public void enter(final ViewChangeListener.ViewChangeEvent event) {

    }

}
