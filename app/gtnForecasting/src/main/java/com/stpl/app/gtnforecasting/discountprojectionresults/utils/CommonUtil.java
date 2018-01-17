/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.discountprojectionresults.utils;

import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import static com.stpl.app.gtnforecasting.utils.Constant.ANNUALLY;
import static com.stpl.app.gtnforecasting.utils.Constant.MONTHLY;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.FORECAST_END_DAY;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.FORECAST_END_PERIOD;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.FORECAST_START_DAY;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.FORECAST_START_PERIOD;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.FREQUENCY_DIVISION;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.HISTORY_END_DAY;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.HISTORY_END_MONTH;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.HISTORY_END_PERIOD;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.HISTORY_END_YEAR;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.HISTORY_START_DAY;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.HISTORY_START_MONTH;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.HISTORY_START_MONTH_DDLB;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.HISTORY_START_PERIOD;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.HISTORY_START_PERIOD_DDLB;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.HISTORY_START_YEAR;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.HISTORY_START_YEAR_DDLB;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.PROJECTION_END_DAY;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.PROJECTION_END_PERIOD;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.PROJECTION_START_DAY;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.PROJECTION_START_MONTH;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.PROJECTION_START_MONTH_DDLB;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.PROJECTION_START_PERIOD;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.PROJECTION_START_YEAR;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.PROJECTION_START_YEAR_DDLB;
import static com.stpl.app.gtnforecasting.utils.Constant.QUARTERLY;
import static com.stpl.app.gtnforecasting.utils.Constant.SEMI_ANNUALLY;
import com.stpl.ifs.ui.util.NumericConstants;
import java.util.Calendar;

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
            frequencyDivision = NumericConstants.FOUR;
            historyStartPeriod = getPeriod(projSelDTO.getForecastDTO().getHistoryStartMonth(), NumericConstants.THREE);
            historyEndPeriod = getPeriod(projSelDTO.getForecastDTO().getHistoryEndMonth(), NumericConstants.THREE);
            forecastStartPeriod =getPeriod(projSelDTO.getForecastDTO().getForecastStartMonth(), NumericConstants.THREE);
            forecastEndPeriod = getPeriod(projSelDTO.getForecastDTO().getForecastEndMonth(), NumericConstants.THREE);
            projectionStartPeriod = getPeriod(projSelDTO.getForecastDTO().getProjectionStartMonth() + 1, NumericConstants.THREE);
            projectionEndPeriod =getPeriod(projSelDTO.getForecastDTO().getProjectionEndMonth(), NumericConstants.THREE);
        } else if (frequency.equals(SEMI_ANNUALLY)) {
            frequencyDivision = NumericConstants.TWO;
            historyStartPeriod =getPeriod(projSelDTO.getForecastDTO().getHistoryStartMonth(), NumericConstants.SIX);
            historyEndPeriod = getPeriod(projSelDTO.getForecastDTO().getHistoryEndMonth(), NumericConstants.SIX);
            forecastStartPeriod = getPeriod(projSelDTO.getForecastDTO().getForecastStartMonth(), NumericConstants.SIX);
            forecastEndPeriod = getPeriod(projSelDTO.getForecastDTO().getForecastEndMonth(), NumericConstants.SIX);
            projectionStartPeriod = getPeriod(projSelDTO.getForecastDTO().getProjectionStartMonth(), NumericConstants.SIX);
            projectionEndPeriod = getPeriod(projSelDTO.getForecastDTO().getProjectionEndMonth(), NumericConstants.SIX);
        } else if (frequency.equals(MONTHLY)) {
            frequencyDivision = NumericConstants.TWELVE;
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
            historyEndMonth = NumericConstants.TWELVE;
            historyEndYear = historyEndYear-1;
        }else if(historyEndPeriod<1){
            historyEndMonth = NumericConstants.TWELVE;
            historyEndYear = historyEndYear-1;
            historyEndPeriod=frequencyDivision;
        }else  historyEndMonth = (NumericConstants.TWELVE / frequencyDivision) * historyEndPeriod;
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
        } else if (frequencyDivision == NumericConstants.TWO) {
            if (historyStartFreq == 1) {
                projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), 1);
            } else if (historyStartFreq == NumericConstants.TWO) {
                projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), NumericConstants.SEVEN);
            }
        } else if (frequencyDivision == NumericConstants.FOUR) {
            if (historyStartFreq == 1) {
                projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), 1);
            } else if (historyStartFreq == NumericConstants.TWO) {
                projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), NumericConstants.FOUR);
            } else if (historyStartFreq == NumericConstants.THREE) {
                projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), NumericConstants.SEVEN);
            } else if (historyStartFreq == NumericConstants.FOUR) {
                projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), NumericConstants.TEN);
            }
        } else if (frequencyDivision == NumericConstants.TWELVE) {
            projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), NumericConstants.TWELVE);
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
            if ((projSelDTO.getProjectionStartYear()==projSelDTO.getHistoryStartYear()) && (projSelDTO.getProjectionDetails(PROJECTION_START_MONTH_DDLB.getConstant()) < projSelDTO.getHistoryStartMonth()||(modified&&projSelDTO.getProjectionDetails(PROJECTION_START_MONTH_DDLB.getConstant()) > projSelDTO.getHistoryStartMonth()))) {
            projSelDTO.setProjectionStartMonth(projSelDTO.getHistoryStartMonth());
            projSelDTO.addProjectionDetails(PROJECTION_START_MONTH_DDLB.getConstant(), projSelDTO.getHistoryStartMonth());
            projSelDTO.setProjectionStartPeriod(historyStartFreq);
            projSelDTO.addProjectionDetails(PROJECTION_START_PERIOD.getConstant(), historyStartFreq);
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
