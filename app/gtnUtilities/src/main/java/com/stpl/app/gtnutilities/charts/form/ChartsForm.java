/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnutilities.charts.form;

//import com.stpl.app.gtnutilities.common.util.CommonUtil;
import com.stpl.app.gtnutilities.charts.logic.SearchLogic;
import com.stpl.app.gtnutilities.util.Constants;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author Karthik.Raja
 */
public class ChartsForm extends CustomComponent implements View {

    private static final Logger LOGGER = Logger.getLogger(ChartsForm.class);
    @UiField("mainLayout")
    private VerticalLayout mainLayout;

    /**
     * Instantiates a new LoggingSearchIndex
     */
    public ChartsForm()  {

        super();
        try {
            init();
            configureFields();
        } catch (Exception e) {
            LOGGER.debug(e);
        }
    }

    /**
     * Inits the.
     */
    private void init() {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/ChartsTabSheetForm.xml"), this));
    }

    /**
     * (non-Javadoc).
     *
     * @param event the event
     * @see
     * com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
     */
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        return;
    }

    /**
     * Configure fields.
     */
    private void configureFields()  {
        TabSheet tabsheet = new TabSheet();
        mainLayout.addComponent(tabsheet);
        CpuIoChart CpuIoChart = new CpuIoChart();
        CpuIoChart.setCaption(Constants.CPU_IO_STATISTIC);
        tabsheet.addTab(CpuIoChart, Constants.CPU_IO_STATISTIC);
        final QueryStatistics queryStatistics = new QueryStatistics();
        queryStatistics.setCaption(Constants.QUERY_STATISTICS);
        tabsheet.addTab(queryStatistics, Constants.QUERY_STATISTICS);
        final DisplayJobs scheduleJobs = new DisplayJobs();
        scheduleJobs.setCaption(Constants.SCHEDULE_JOB);
        tabsheet.addTab(scheduleJobs, Constants.SCHEDULE_JOB);
        tabsheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
             private static final long serialVersionUID = 1L;
            @Override
            public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                TabSheet tabsheet = event.getTabSheet();
                if (tabsheet.getSelectedTab().getCaption().equals(Constants.QUERY_STATISTICS)) {
                    queryStatistics.selecteDate.setValue(SearchLogic.getInstance().selectedDate);
                    queryStatistics.refreshTable();
                }
            }
        });
        
    }

}
