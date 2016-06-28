/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.discountprojectionresults.utils;

import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import static com.stpl.app.galforecasting.utils.Constant.ANNUALLY;
import static com.stpl.app.galforecasting.utils.Constant.MONTHLY;
import static com.stpl.app.galforecasting.utils.Constant.ProjectionConstants.FORECAST_END_DAY;
import static com.stpl.app.galforecasting.utils.Constant.ProjectionConstants.FORECAST_END_PERIOD;
import static com.stpl.app.galforecasting.utils.Constant.ProjectionConstants.FORECAST_START_DAY;
import static com.stpl.app.galforecasting.utils.Constant.ProjectionConstants.FORECAST_START_PERIOD;
import static com.stpl.app.galforecasting.utils.Constant.ProjectionConstants.FREQUENCY_DIVISION;
import static com.stpl.app.galforecasting.utils.Constant.ProjectionConstants.HISTORY_END_DAY;
import static com.stpl.app.galforecasting.utils.Constant.ProjectionConstants.HISTORY_END_MONTH;
import static com.stpl.app.galforecasting.utils.Constant.ProjectionConstants.HISTORY_END_PERIOD;
import static com.stpl.app.galforecasting.utils.Constant.ProjectionConstants.HISTORY_END_YEAR;
import static com.stpl.app.galforecasting.utils.Constant.ProjectionConstants.HISTORY_START_DAY;
import static com.stpl.app.galforecasting.utils.Constant.ProjectionConstants.HISTORY_START_MONTH;
import static com.stpl.app.galforecasting.utils.Constant.ProjectionConstants.HISTORY_START_MONTH_DDLB;
import static com.stpl.app.galforecasting.utils.Constant.ProjectionConstants.HISTORY_START_PERIOD;
import static com.stpl.app.galforecasting.utils.Constant.ProjectionConstants.HISTORY_START_PERIOD_DDLB;
import static com.stpl.app.galforecasting.utils.Constant.ProjectionConstants.HISTORY_START_YEAR;
import static com.stpl.app.galforecasting.utils.Constant.ProjectionConstants.HISTORY_START_YEAR_DDLB;
import static com.stpl.app.galforecasting.utils.Constant.ProjectionConstants.PROJECTION_END_DAY;
import static com.stpl.app.galforecasting.utils.Constant.ProjectionConstants.PROJECTION_END_PERIOD;
import static com.stpl.app.galforecasting.utils.Constant.ProjectionConstants.PROJECTION_START_DAY;
import static com.stpl.app.galforecasting.utils.Constant.ProjectionConstants.PROJECTION_START_MONTH;
import static com.stpl.app.galforecasting.utils.Constant.ProjectionConstants.PROJECTION_START_MONTH_DDLB;
import static com.stpl.app.galforecasting.utils.Constant.ProjectionConstants.PROJECTION_START_PERIOD;
import static com.stpl.app.galforecasting.utils.Constant.ProjectionConstants.PROJECTION_START_YEAR;
import static com.stpl.app.galforecasting.utils.Constant.ProjectionConstants.PROJECTION_START_YEAR_DDLB;
import static com.stpl.app.galforecasting.utils.Constant.QUARTERLY;
import static com.stpl.app.galforecasting.utils.Constant.SEMI_ANNUALLY;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author pvinoth
 */
public class CommonUtil {
    
    static void getHistoryAndProjectionDetailsDPR(ProjectionSelectionDTO projSelDTO) {
        int frequencyDivision = 1;
        int historyStartPeriod = 1;
        int historyEndPeriod = 1;
        int historyEndMonth = 1;
        int historyEndYear = 1;
        int forecastStartPeriod = 1;
        int forecastEndPeriod = 1;
        int projectionStartPeriod = 1;
        int projectionEndPeriod = 1;
        String frequency = projSelDTO.getFrequency();
        if (frequency.equals(QUARTERLY)) {
            frequencyDivision = 4;
            historyStartPeriod = getPeriod(projSelDTO.getForecastDTO().getHistoryStartMonth(), 3);
            historyEndPeriod = getPeriod(projSelDTO.getForecastDTO().getHistoryEndMonth(), 3);
            forecastStartPeriod =getPeriod(projSelDTO.getForecastDTO().getForecastStartMonth(), 3);
            forecastEndPeriod = getPeriod(projSelDTO.getForecastDTO().getForecastEndMonth(), 3);
            projectionStartPeriod = getPeriod(projSelDTO.getForecastDTO().getProjectionStartMonth() + 1, 3);
            projectionEndPeriod =getPeriod(projSelDTO.getForecastDTO().getProjectionEndMonth(), 3);
        } else if (frequency.equals(SEMI_ANNUALLY)) {
            frequencyDivision = 2;
            historyStartPeriod =getPeriod(projSelDTO.getForecastDTO().getHistoryStartMonth(), 6);
            historyEndPeriod = getPeriod(projSelDTO.getForecastDTO().getHistoryEndMonth(), 6);
            forecastStartPeriod = getPeriod(projSelDTO.getForecastDTO().getForecastStartMonth(), 6);
            forecastEndPeriod = getPeriod(projSelDTO.getForecastDTO().getForecastEndMonth(), 6);
            projectionStartPeriod = getPeriod(projSelDTO.getForecastDTO().getProjectionStartMonth(), 6);
            projectionEndPeriod = getPeriod(projSelDTO.getForecastDTO().getProjectionEndMonth(), 6);
        } else if (frequency.equals(MONTHLY)) {
            frequencyDivision = 12;
            historyStartPeriod = projSelDTO.getForecastDTO().getHistoryStartMonth();
            historyEndPeriod = projSelDTO.getForecastDTO().getHistoryEndMonth();
            forecastStartPeriod = projSelDTO.getForecastDTO().getForecastStartMonth();
            forecastEndPeriod = projSelDTO.getForecastDTO().getForecastEndMonth();
            projectionStartPeriod = projSelDTO.getForecastDTO().getProjectionStartMonth();
            projectionEndPeriod = projSelDTO.getForecastDTO().getProjectionEndMonth();
        } else if (frequency.equals(ANNUALLY)) {
            frequencyDivision = 1;
            historyStartPeriod = projSelDTO.getForecastDTO().getHistoryStartYear();
            historyEndPeriod = projSelDTO.getForecastDTO().getHistoryEndYear();
            forecastStartPeriod = projSelDTO.getForecastDTO().getForecastStartYear();
            forecastEndPeriod = projSelDTO.getForecastDTO().getForecastEndYear();
            projectionStartPeriod = projSelDTO.getForecastDTO().getProjectionStartYear();
            projectionEndPeriod = projSelDTO.getForecastDTO().getProjectionEndYear();
        }
        historyEndMonth = projSelDTO.getForecastDTO().getHistoryEndMonth();
        historyEndYear = projSelDTO.getForecastDTO().getHistoryEndYear();
        if(historyEndPeriod==forecastStartPeriod&&historyEndYear==projSelDTO.getForecastDTO().getForecastStartYear()){
            historyEndPeriod--;
            if(frequencyDivision==1){
            historyEndMonth = 12;
            historyEndYear = historyEndYear-1;
        }else if(historyEndPeriod<1){
            historyEndMonth = 12;
            historyEndYear = historyEndYear-1;
            historyEndPeriod=frequencyDivision;
        }else  historyEndMonth = (12 / frequencyDivision) * historyEndPeriod;
        }
       
        projSelDTO.setFrequencyDivision(frequencyDivision);   
        projSelDTO.addProjectionDetails(FREQUENCY_DIVISION.getConstant(), frequencyDivision);
        projSelDTO.addProjectionDetails(HISTORY_END_YEAR.getConstant(), historyEndYear);
        projSelDTO.addProjectionDetails(HISTORY_END_MONTH.getConstant(), historyEndMonth);
        projSelDTO.addProjectionDetails(HISTORY_START_YEAR.getConstant(), projSelDTO.getForecastDTO().getHistoryStartYear());
        projSelDTO.addProjectionDetails(HISTORY_START_MONTH.getConstant(), projSelDTO.getForecastDTO().getHistoryStartMonth());
        projSelDTO.addProjectionDetails(HISTORY_START_PERIOD.getConstant(), historyStartPeriod);
        projSelDTO.addProjectionDetails(HISTORY_END_PERIOD.getConstant(), historyEndPeriod);
        projSelDTO.addProjectionDetails(FORECAST_START_PERIOD.getConstant(), forecastStartPeriod);
        projSelDTO.addProjectionDetails(FORECAST_END_PERIOD.getConstant(), forecastEndPeriod);
        projSelDTO.addProjectionDetails(PROJECTION_START_PERIOD.getConstant(), projectionStartPeriod);
        projSelDTO.addProjectionDetails(PROJECTION_END_PERIOD.getConstant(), projectionEndPeriod);
        projSelDTO.addProjectionDetails(PROJECTION_START_YEAR.getConstant(), projSelDTO.getForecastDTO().getProjectionStartYear());
        projSelDTO.addProjectionDetails(PROJECTION_START_MONTH.getConstant(), projSelDTO.getForecastDTO().getProjectionStartMonth());
        projSelDTO.addProjectionDetails(PROJECTION_START_YEAR_DDLB.getConstant(), projSelDTO.getForecastDTO().getProjectionStartYear());
        projSelDTO.addProjectionDetails(PROJECTION_START_MONTH_DDLB.getConstant(), projSelDTO.getForecastDTO().getProjectionStartMonth());
        projSelDTO.addProjectionDetails(HISTORY_START_DAY.getConstant(), 1);
        projSelDTO.addProjectionDetails(FORECAST_START_DAY.getConstant(), 1);
        projSelDTO.addProjectionDetails(PROJECTION_START_DAY.getConstant(), 1);
        projSelDTO.addProjectionDetails(HISTORY_END_DAY.getConstant(), getEndDay(historyEndMonth, historyEndYear));
        projSelDTO.addProjectionDetails(FORECAST_END_DAY.getConstant(), getEndDay(projSelDTO.getForecastDTO().getForecastEndMonth(), projSelDTO.getForecastDTO().getForecastEndYear()));
        projSelDTO.addProjectionDetails(PROJECTION_END_DAY.getConstant(), getEndDay(projSelDTO.getForecastDTO().getProjectionEndMonth(), projSelDTO.getForecastDTO().getProjectionEndYear()));
        
        projSelDTO.setProjectionStartYear(projSelDTO.getForecastDTO().getProjectionStartYear());
        projSelDTO.setProjectionStartMonth(projSelDTO.getForecastDTO().getProjectionStartMonth());
        projSelDTO.setHistoryEndYear(historyEndYear);
        projSelDTO.setHistoryEndMonth(historyEndMonth);
        projSelDTO.setHistoryStartPeriod(historyStartPeriod);
        projSelDTO.setHistoryEndPeriod(historyEndPeriod);
        projSelDTO.setForecastStartPeriod(forecastStartPeriod);
        projSelDTO.setForecastEndPeriod(forecastEndPeriod);
        projSelDTO.setProjectionStartPeriod(projectionStartPeriod);
        projSelDTO.setProjectionEndPeriod(projectionEndPeriod);
        projSelDTO.setHistoryEndDay(getEndDay(projSelDTO.getForecastDTO().getHistoryEndMonth(), projSelDTO.getForecastDTO().getHistoryEndYear()));
        projSelDTO.setForecastStartDay(1);
        projSelDTO.setForecastEndDay(getEndDay(projSelDTO.getForecastDTO().getForecastEndMonth(), projSelDTO.getForecastDTO().getForecastEndYear()));
        projSelDTO.setProjectionStartDay(1);
        projSelDTO.setProjectionEndDay(getEndDay(projSelDTO.getForecastDTO().getProjectionEndMonth(), projSelDTO.getForecastDTO().getProjectionEndYear()));
        
        
        getHistoryDetailDPR(projSelDTO);
    }
    
    static int getEndDay(int monthNo, int year) {
        Calendar ob = Calendar.getInstance();
        ob.set(year, monthNo - 1, 1);
        int daysInMonth = ob.getActualMaximum(Calendar.DAY_OF_MONTH);
        return daysInMonth;
    }
    
    static int getPeriod(int monthNo, int division) {
        return ((monthNo - 1) / division) + 1;
    } 
    
    static void getHistoryDetailDPR(ProjectionSelectionDTO projSelDTO) {

        int historyNum = projSelDTO.getHistoryNum();
        int frequencyDivision = projSelDTO.getProjectionDetails(FREQUENCY_DIVISION.getConstant());
              
        
        int historyStartYear = projSelDTO.getProjectionDetails(HISTORY_END_YEAR.getConstant());
        int historyEndPeriod = projSelDTO.getProjectionDetails(HISTORY_END_PERIOD.getConstant());
        int historyStartFreq = historyEndPeriod + 1;
        if (frequencyDivision == 1) {
            historyStartYear = historyStartYear - historyNum + 1;
            historyStartFreq = historyStartYear;
        } else {
            int historyTempFreq = historyNum - historyEndPeriod;
            if (historyTempFreq > 0) {
                historyStartYear = historyStartYear - historyTempFreq / frequencyDivision;
                historyStartFreq = 1;
                if (historyTempFreq % frequencyDivision > 0) {
                    historyStartYear = historyStartYear - 1;
                    historyStartFreq = frequencyDivision - (historyTempFreq % frequencyDivision) + 1;
                }
            } else {
                historyStartFreq = historyStartFreq - historyNum;
            }
        }
        if (frequencyDivision == 1) {
            projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), frequencyDivision);
        } else if (frequencyDivision == 2) {
            if (historyStartFreq == 1) {
                projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), 1);
            } else if (historyStartFreq == 2) {
                projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), 7);
            }
        } else if (frequencyDivision == 4) {
            if (historyStartFreq == 1) {
                projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), 1);
            } else if (historyStartFreq == 2) {
                projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), 4);
            } else if (historyStartFreq == 3) {
                projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), 7);
            } else if (historyStartFreq == 4) {
                projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), 10);
            }
        } else if (frequencyDivision == 12) {
            projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), 12);
        }
        if (projSelDTO.getProjectionDetails(HISTORY_START_PERIOD.getConstant()) == historyStartFreq && projSelDTO.getProjectionDetails(HISTORY_START_YEAR.getConstant()) == historyStartYear) {
            projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), projSelDTO.getProjectionDetails(HISTORY_START_MONTH.getConstant()));
        }
        projSelDTO.addProjectionDetails(HISTORY_START_PERIOD_DDLB.getConstant(), historyStartFreq);
        projSelDTO.addProjectionDetails(HISTORY_START_YEAR_DDLB.getConstant(), historyStartYear);
        projSelDTO.setHistoryStartPeriod(historyStartFreq);
        projSelDTO.setHistoryStartYear(historyStartYear);
        projSelDTO.setHistoryStartMonth(projSelDTO.getProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant()));
        projSelDTO.setHistoryStartDay(1);
        boolean modified=false;
        if (projSelDTO.getProjectionDetails(PROJECTION_START_YEAR.getConstant())< historyStartYear){
            projSelDTO.setProjectionStartYear(projSelDTO.getHistoryStartYear());
            projSelDTO.addProjectionDetails(PROJECTION_START_YEAR_DDLB.getConstant(), projSelDTO.getHistoryStartYear());
            modified=true;
        }
        if(projSelDTO.getProjectionStartYear()==projSelDTO.getHistoryStartYear()){
            if(projSelDTO.getProjectionDetails(PROJECTION_START_MONTH_DDLB.getConstant()) < projSelDTO.getHistoryStartMonth()||(modified&&projSelDTO.getProjectionDetails(PROJECTION_START_MONTH_DDLB.getConstant()) > projSelDTO.getHistoryStartMonth())) {
            projSelDTO.setProjectionStartMonth(projSelDTO.getHistoryStartMonth());
            projSelDTO.addProjectionDetails(PROJECTION_START_MONTH_DDLB.getConstant(), projSelDTO.getHistoryStartMonth());
            projSelDTO.setProjectionStartPeriod(historyStartFreq);
            projSelDTO.addProjectionDetails(PROJECTION_START_PERIOD.getConstant(), historyStartFreq);
        }
        }
        projSelDTO.setEndYear(projSelDTO.getForecastDTO().getForecastEndYear());
        projSelDTO.setEndPeriod(projSelDTO.getForecastEndPeriod());
        projSelDTO.setEndMonth(projSelDTO.getForecastDTO().getForecastEndMonth());
        projSelDTO.setEndDay(projSelDTO.getForecastEndDay());
        projSelDTO.setStartYear(projSelDTO.getHistoryStartYear());
        projSelDTO.setStartPeriod(projSelDTO.getHistoryStartPeriod());
        projSelDTO.setStartMonth(projSelDTO.getHistoryStartMonth());
        projSelDTO.setStartDay(projSelDTO.getHistoryStartDay());

    }
    
    
}
