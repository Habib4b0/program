/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.logic;

import com.stpl.app.gtnforecasting.dao.AssumptionDAO;
import com.stpl.app.gtnforecasting.dao.impl.AssumptionDAOImpl;
import com.stpl.app.gtnforecasting.dto.AssumptionCIDTDTO;
import com.stpl.app.gtnforecasting.dto.AssumptionDiscountDTO;
import com.stpl.app.gtnforecasting.dto.AssumptionPPADTO;
import com.stpl.app.gtnforecasting.dto.AssumptionPVDTO;
import com.stpl.app.gtnforecasting.dto.AssumptionSalesDTO;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import com.stpl.app.gtnforecasting.utils.Converters;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author harlin
 */
public class AssumptionLogic {

    private final AssumptionDAO assumptionDAO = new AssumptionDAOImpl();
    private final DecimalFormat perOneDec = new DecimalFormat("###,##0.0");
    private final DecimalFormat dollZeroDec = new DecimalFormat("$###,##0");
    private final SimpleDateFormat fmt = new SimpleDateFormat(Constant.DATE_FORMAT);

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(AssumptionLogic.class);

    /**
     * To get the Customer ID Transfer Results.
     *
     * @return
     */
    public List<AssumptionCIDTDTO> getCIDTResults() {
        return getCustomizedCIDT();
    }

    /**
     * To customize the Customer ID Transfer Results.
     *
     * @param result
     * @return
     */
    private List<AssumptionCIDTDTO> getCustomizedCIDT() {
        return new ArrayList<>();
    }

    /**
     * To get the Projection Variance Snapshot Results.
     *
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    public void getPVResults(final SessionDTO session)  {
        List<Object> input = new ArrayList<>();
        input.add(Constant.UPDATE);
        input.add(session.getUserId());
        input.add(session.getUserId());
        input.add(session.getSessionId());

        input.add(session.getUserId());
        input.add(session.getSessionId());
        input.add(session.getProjectionId());
        input.add(fmt.format(session.getForecastDTO().getProjectionStartDate()));
        input.add(fmt.format(session.getForecastDTO().getForecastEndDate()));

        input.add(session.getUserId());
        input.add(session.getUserId());
        input.add(session.getSessionId());
        input.add(session.getUserId());
        input.add(session.getSessionId());
        input.add(session.getProjectionId());
        input.add(fmt.format(session.getForecastDTO().getProjectionStartDate()));
        input.add(fmt.format(session.getForecastDTO().getForecastEndDate()));

        input.add(session.getProjectionId());
        input.add(session.getCustRelationshipBuilderSid());
        input.add(session.getProjectionId());
        input.add(session.getProjectionId());
        input.add(session.getUserId());
        input.add(session.getSessionId());
        assumptionDAO.getAssumptionResults(input, "assumption.pvsTempInsert");
    }

    /**
     * To customize the Projection Variance Snapshot Results.
     *
     * @param result
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    private List<AssumptionPVDTO> getCustomizedPV(List result) throws PortalException, SystemException {
        List<AssumptionPVDTO> retList = new ArrayList<>();
        for (Object[] temp : (List<Object[]>) result) {
            AssumptionPVDTO tempDto = new AssumptionPVDTO();
            tempDto.setParent((Boolean) temp[NumericConstants.TWO]);
            tempDto.setPvCheck(Integer.valueOf(String.valueOf(temp[0]))==0?Boolean.FALSE:Boolean.TRUE);
            if (tempDto.getParent()) {
                tempDto.setProjectionPeriodPV(String.valueOf(temp[1]));
                tempDto.setContractHolder(String.valueOf(temp[NumericConstants.THREE]));
                tempDto.setCostomer(String.valueOf(temp[NumericConstants.FOUR]));
                tempDto.setSegment(Constant.NULL.equals(String.valueOf(temp[NumericConstants.FIVE])) ? StringUtils.EMPTY : temp[NumericConstants.FIVE].toString());
                tempDto.setSegmentId(Constant.NULL.equals(String.valueOf(temp[NumericConstants.FIVE])) ? StringUtils.EMPTY : temp[NumericConstants.FIVE].toString());
                tempDto.setMarketType(Constant.NULL.equals(String.valueOf(temp[NumericConstants.SIX])) ? StringUtils.EMPTY : temp[NumericConstants.SIX].toString());
                tempDto.setBrandPV(Constant.NULL.equals(String.valueOf(temp[NumericConstants.SEVEN])) ? StringUtils.EMPTY : temp[NumericConstants.SEVEN].toString());
                BigDecimal grossSalePrior = Constant.NULL.equals(String.valueOf(temp[NumericConstants.EIGHT])) || StringUtils.EMPTY.equals(String.valueOf(temp[NumericConstants.EIGHT])) ? new BigDecimal(0) : (BigDecimal) temp[NumericConstants.EIGHT];
                BigDecimal grossSaleProjected = Constant.NULL.equals(String.valueOf(temp[NumericConstants.NINE])) || StringUtils.EMPTY.equals(String.valueOf(temp[NumericConstants.NINE])) ? new BigDecimal(0) : (BigDecimal) temp[NumericConstants.NINE];
                Double grossSaleChange = 0d;
                if (grossSaleProjected.doubleValue() != 0d) {
                    grossSaleChange = ((grossSaleProjected.doubleValue() - grossSalePrior.doubleValue()) / grossSaleProjected.doubleValue()) * NumericConstants.HUNDRED;
                } else if (grossSalePrior.doubleValue() != 0d) {
                    grossSaleChange = ((grossSaleProjected.doubleValue() - grossSalePrior.doubleValue()) / grossSalePrior.doubleValue()) * NumericConstants.HUNDRED;
                }
                BigDecimal totalDiscountPrior1 = Constant.NULL.equals(String.valueOf(temp[NumericConstants.ELEVEN])) || StringUtils.EMPTY.equals(String.valueOf(temp[NumericConstants.ELEVEN])) ? new BigDecimal(0) : (BigDecimal) temp[NumericConstants.ELEVEN];
                BigDecimal totalDiscountProjected1 = Constant.NULL.equals(String.valueOf(temp[NumericConstants.TWELVE])) || StringUtils.EMPTY.equals(String.valueOf(temp[NumericConstants.TWELVE])) ? new BigDecimal(0) : (BigDecimal) temp[NumericConstants.TWELVE];
                Double totalDiscountPrior = totalDiscountPrior1.doubleValue() * NumericConstants.HUNDRED / grossSalePrior.doubleValue();
                Double totalDiscountProjected = totalDiscountProjected1.doubleValue() * NumericConstants.HUNDRED / grossSaleProjected.doubleValue();
                Double totalDiscountChange = 0d;
                if (totalDiscountProjected1.doubleValue() != 0d) {
                    totalDiscountChange = ((totalDiscountProjected1.doubleValue() - totalDiscountPrior1.doubleValue()) / totalDiscountProjected1.doubleValue()) * NumericConstants.HUNDRED;
                } else if (totalDiscountPrior1.doubleValue() != 0d) {
                    totalDiscountChange = ((totalDiscountProjected1.doubleValue() - totalDiscountPrior1.doubleValue()) / totalDiscountPrior1.doubleValue()) * NumericConstants.HUNDRED;
                }
                Double netSalePrior = grossSalePrior.doubleValue() - totalDiscountPrior1.doubleValue();
                Double netSaleProjected = grossSaleProjected.doubleValue() - totalDiscountProjected1.doubleValue();
                Double netSaleChange = 0d;
                if (netSaleProjected.doubleValue() != 0d) {
                    netSaleChange = ((netSaleProjected.doubleValue() - netSalePrior.doubleValue()) / netSaleProjected.doubleValue()) * NumericConstants.HUNDRED;
                } else if (netSalePrior.doubleValue() != 0d) {
                    netSaleChange = ((netSaleProjected.doubleValue() - netSalePrior.doubleValue()) / netSalePrior.doubleValue()) * NumericConstants.HUNDRED;
                }
                tempDto.setGrossSalePriorPV(dollZeroDec.format(grossSalePrior.doubleValue()));
                tempDto.setGrossSaleProjectedPV(dollZeroDec.format(grossSaleProjected.doubleValue()));
                tempDto.setGrossSaleChangePV(perOneDec.format(grossSaleChange.doubleValue()) + Constant.PERCENT);
                tempDto.setTotalDiscountPriorPV(perOneDec.format(totalDiscountPrior.isNaN() ? 0 : totalDiscountPrior.doubleValue()) + Constant.PERCENT);
                tempDto.setTotalDiscountProjectedPV(perOneDec.format(totalDiscountProjected.isNaN() ? 0 : totalDiscountProjected.doubleValue()) + Constant.PERCENT);
                tempDto.setTotalDiscountChangePV(perOneDec.format(totalDiscountChange.doubleValue()) + Constant.PERCENT);
                tempDto.setNetSalePriorPV(dollZeroDec.format(netSalePrior.doubleValue()));
                tempDto.setNetSaleProjectedPV(dollZeroDec.format(netSaleProjected.doubleValue()));
                tempDto.setNetSaleChangePV(perOneDec.format(netSaleChange.doubleValue()) + Constant.PERCENT);

                tempDto.setCamNamePV(Converters.getUserFLName(String.valueOf(temp[NumericConstants.SEVENTEEN])));
            } else {
                tempDto.setChildPeriod(String.valueOf(temp[1]));
            }
            tempDto.setChildCamId(String.valueOf(temp[NumericConstants.SEVENTEEN]));
            tempDto.setSegmentId(Constant.NULL.equals(String.valueOf(temp[NumericConstants.FIVE])) ? StringUtils.EMPTY : temp[NumericConstants.FIVE].toString());
            tempDto.setCommentaryPV(Constant.NULL.equals(String.valueOf(temp[NumericConstants.EIGHTEEN])) ? StringUtils.EMPTY : temp[NumericConstants.EIGHTEEN].toString());
            tempDto.setOldCommentary(String.valueOf(temp[NumericConstants.EIGHTEEN]));
            tempDto.setUserId(Integer.valueOf(String.valueOf(temp[NumericConstants.NINETEEN])));
            tempDto.setSessionId(Integer.valueOf(String.valueOf(temp[NumericConstants.TWENTY])));
            tempDto.setReasonCodePV(Constant.NULL.equals(String.valueOf(temp[NumericConstants.TWENTY_ONE])) ? StringUtils.EMPTY : temp[NumericConstants.TWENTY_ONE].toString());
            tempDto.setOldReasonCode(String.valueOf(temp[NumericConstants.TWENTY_ONE]));
            tempDto.setContractHolderSid(Integer.valueOf(Constant.NULL.equals(String.valueOf(temp[NumericConstants.TWENTY_TWO]))?DASH:String.valueOf(temp[NumericConstants.TWENTY_TWO])));
            tempDto.setCompanySid(Integer.valueOf(String.valueOf(temp[NumericConstants.TWENTY_THREE])));
            tempDto.setMatketTypeSid(Integer.valueOf(String.valueOf(temp[NumericConstants.TWENTY_FOUR])));
            tempDto.setBrandSid(Integer.valueOf(String.valueOf(temp[NumericConstants.TWENTY_FIVE])));
            retList.add(tempDto);
        }
        return retList;
    }

    /**
     * To get the Trading Partner Maintenance-Sales Results.
     *
     * @return
     */
    public List<AssumptionSalesDTO> getSalesResults(final SessionDTO session) {
        List<Object> input = new ArrayList<>();
        input.add(session.getForecastDTO().getProjectionStartMonth());
        input.add(session.getForecastDTO().getProjectionStartYear());
        input.add(session.getForecastDTO().getForecastEndMonth());
        input.add(session.getForecastDTO().getForecastEndYear());
        input.add(session.getProjectionId());
        input.add(session.getUserId());
        input.add(session.getSessionId());
        input.add(session.getUserId());
        input.add(session.getSessionId());
        return getCustomizedSales(assumptionDAO.getAssumptionResults(input, "assumption.sales"));
    }

    /**
     * To customize the Trading Partner Maintenance-Sales Results.
     *
     * @param result
     * @return
     */
    private List<AssumptionSalesDTO> getCustomizedSales(List result) {

        LOGGER.debug("custom Logger Sales");
        List<AssumptionSalesDTO> retList = new ArrayList<>();
        for (Object[] temp : (List<Object[]>) result) {
            AssumptionSalesDTO tempDto = new AssumptionSalesDTO();
            tempDto.setProjectionPeriod(String.valueOf(temp[0]));
            tempDto.setTradingPartner(Constant.NULL.equals(String.valueOf(temp[1])) ? StringUtils.EMPTY : temp[1].toString());
            tempDto.setCustomerID(Constant.NULL.equals(String.valueOf(temp[NumericConstants.TWO])) ? StringUtils.EMPTY : temp[NumericConstants.TWO].toString());
            tempDto.setGroup(Constant.NULL.equals(String.valueOf(temp[NumericConstants.THREE])) ? StringUtils.EMPTY : temp[NumericConstants.THREE].toString());
            tempDto.setBrand(Constant.NULL.equals(String.valueOf(temp[NumericConstants.FOUR])) ? StringUtils.EMPTY : temp[NumericConstants.FOUR].toString());
            BigDecimal accountGrouth = Constant.NULL.equals(String.valueOf(temp[NumericConstants.FIVE])) || StringUtils.EMPTY.equals(String.valueOf(temp[NumericConstants.FIVE])) ? new BigDecimal(0) : (BigDecimal) temp[NumericConstants.FIVE];
            BigDecimal productGrouth = Constant.NULL.equals(String.valueOf(temp[NumericConstants.SIX])) || StringUtils.EMPTY.equals(String.valueOf(temp[NumericConstants.SIX])) ? new BigDecimal(0) : (BigDecimal) temp[NumericConstants.SIX];
            tempDto.setAccountGrowth(perOneDec.format(accountGrouth.doubleValue()) + Constant.PERCENT);
            tempDto.setProductGrowth(perOneDec.format(productGrouth.doubleValue()) + Constant.PERCENT);
            retList.add(tempDto);
        }
        return retList;
    }

    /**
     * To get the Trading Partner Maintenance-Discount Results.
     *
     * @return
     */
    public List<AssumptionDiscountDTO> getDiscountResults(final SessionDTO session) {
        List<Object> input = new ArrayList<>();
        input.add(session.getForecastDTO().getProjectionStartMonth());
        input.add(session.getForecastDTO().getProjectionStartYear());
        input.add(session.getForecastDTO().getForecastEndMonth());
        input.add(session.getForecastDTO().getForecastEndYear());
        input.add(session.getProjectionId());
        input.add(session.getUserId());
        input.add(session.getSessionId());
        input.add(session.getUserId());
        input.add(session.getSessionId());
        return getCustomizedDiscount(assumptionDAO.getAssumptionResults(input, "assumption.discount"));
    }

    /**
     * To customize the Trading Partner Maintenance-Discount Results.
     *
     * @param result
     * @return
     */
    private List<AssumptionDiscountDTO> getCustomizedDiscount(List result) {
        List<AssumptionDiscountDTO> retList = new ArrayList<>();
        for (Object[] temp : (List<Object[]>) result) {
            AssumptionDiscountDTO tempDto = new AssumptionDiscountDTO();
            tempDto.setProjectionPeriod(String.valueOf(temp[0]));
            tempDto.setTradingPartner(Constant.NULL.equals(String.valueOf(temp[1])) ? StringUtils.EMPTY : temp[1].toString());
            tempDto.setCustomerID(Constant.NULL.equals(String.valueOf(temp[NumericConstants.TWO])) ? StringUtils.EMPTY : temp[NumericConstants.TWO].toString());
            tempDto.setGroup(Constant.NULL.equals(String.valueOf(temp[NumericConstants.THREE])) ? StringUtils.EMPTY : temp[NumericConstants.THREE].toString());
            tempDto.setBrand(Constant.NULL.equals(String.valueOf(temp[NumericConstants.FOUR])) ? StringUtils.EMPTY : temp[NumericConstants.FOUR].toString());
            retList.add(tempDto);
        }
        return retList;
    }

    /**
     * To get the Trading Partner Maintenance-PPA Results.
     *
     * @return
     */
    public List<AssumptionPPADTO> getPPAResults(final SessionDTO session) {
        List<Object> input = new ArrayList<>();
        input.add(session.getForecastDTO().getProjectionStartMonth());
        input.add(session.getForecastDTO().getProjectionStartYear());
        input.add(session.getForecastDTO().getForecastEndMonth());
        input.add(session.getForecastDTO().getForecastEndYear());
        input.add(session.getProjectionId());
        input.add(session.getUserId());
        input.add(session.getSessionId());
        input.add(session.getUserId());
        input.add(session.getSessionId());
        return getCustomizedPPA(assumptionDAO.getAssumptionResults(input, "assumption.ppa"));
    }

    /**
     * To customize the Trading Partner Maintenance-PPA Results.
     *
     * @param result
     * @return
     */
    private List<AssumptionPPADTO> getCustomizedPPA(List result) {
        List<AssumptionPPADTO> retList = new ArrayList<>();
        for (Object[] temp : (List<Object[]>) result) {
            AssumptionPPADTO tempDto = new AssumptionPPADTO();
            tempDto.setProjectionPeriod(String.valueOf(temp[0]));
            tempDto.setTradingPartner(String.valueOf(temp[1]));
            tempDto.setCustomerID(String.valueOf(temp[NumericConstants.TWO]));
            tempDto.setGroup(Constant.NULL.equals(String.valueOf(temp[NumericConstants.THREE])) ? StringUtils.EMPTY : temp[NumericConstants.THREE].toString());
            tempDto.setBrand(Constant.NULL.equals(String.valueOf(temp[NumericConstants.FOUR])) ? StringUtils.EMPTY : temp[NumericConstants.FOUR].toString());
            BigDecimal priceCap = Constant.NULL.equals(String.valueOf(temp[NumericConstants.FIVE])) || StringUtils.EMPTY.equals(String.valueOf(temp[NumericConstants.FIVE])) ? new BigDecimal(0) : (BigDecimal) temp[NumericConstants.FIVE];
            BigDecimal discountPer = Constant.NULL.equals(String.valueOf(temp[NumericConstants.SIX])) || StringUtils.EMPTY.equals(String.valueOf(temp[NumericConstants.SIX])) ? new BigDecimal(0) : (BigDecimal) temp[NumericConstants.SIX];
            BigDecimal discountDollar = Constant.NULL.equals(String.valueOf(temp[NumericConstants.SEVEN])) || StringUtils.EMPTY.equals(String.valueOf(temp[NumericConstants.SEVEN])) ? new BigDecimal(0) : (BigDecimal) temp[NumericConstants.SEVEN];
            tempDto.setPpaCap(perOneDec.format(priceCap.doubleValue()) + Constant.PERCENT);
            tempDto.setPpaDiscountPer(perOneDec.format(discountPer.doubleValue()) + Constant.PERCENT);
            tempDto.setPpaDiscountDollar(dollZeroDec.format(discountDollar.doubleValue()));
            retList.add(tempDto);
        }
        return retList;
    }

    /**
     * To remove the line for the Projection Variance Snapshot
     *
     * @param session
     * @param child
     * @throws SystemException
     * @throws PortalException
     */
    public void removeLinePVS(final SessionDTO session, AssumptionPVDTO child)  {
            List<Object> input = new ArrayList<>();
            input.add(Constant.UPDATE);
            
            input.add(child.getChildPeriod());
            input.add(session.getProjectionId());
            input.add(session.getUserId());
            input.add(session.getSessionId());
            input.add(child.getChildCamId());
            input.add(child.getSegmentId());
            input.add(Constant.NULL.equals(String.valueOf(child.getOldReasonCode()))?"AND NMA.REASON_CODES is null ":"AND NMA.REASON_CODES = '"+child.getOldReasonCode()+"'");
            input.add(Constant.NULL.equals(String.valueOf(child.getOldReasonCode()))?"AND NMA.COMMENTARY is null ":"AND NMA.COMMENTARY = '"+child.getOldCommentary()+"'");
            input.add(child.getBrandSid());
            input.add(child.getCompanySid());
            input.add(child.getMatketTypeSid());
            input.add(child.getContractHolderSid()==0?"AND CTM.CONT_HOLD_COMPANY_MASTER_SID is null ":"AND CTM.CONT_HOLD_COMPANY_MASTER_SID = '"+child.getContractHolderSid()+"'");
            
            
            input.add(child.getChildPeriod());
            input.add(session.getProjectionId());
            input.add(session.getUserId());
            input.add(session.getSessionId());
            input.add(child.getChildCamId());
            input.add(child.getSegmentId());
            input.add(Constant.NULL.equals(String.valueOf(child.getOldReasonCode()))?"AND NMA.REASON_CODES is null":"AND NMA.REASON_CODES='"+child.getOldReasonCode()+"'");
            input.add(Constant.NULL.equals(String.valueOf(child.getOldReasonCode()))?"AND NMA.COMMENTARY is null":"AND NMA.COMMENTARY='"+child.getOldCommentary()+"'");
            input.add(child.getBrandSid());
            input.add(child.getCompanySid());
            input.add(child.getMatketTypeSid());
            input.add(child.getContractHolderSid()==0?"AND CTM.CONT_HOLD_COMPANY_MASTER_SID is null":"AND CTM.CONT_HOLD_COMPANY_MASTER_SID ='"+child.getContractHolderSid()+"'");
            
            assumptionDAO.getAssumptionResults(input, "assumption.deleteRecord");

    }

    /**
     * To delete the added lines from the Main table.
     *
     * @return
     */
    public void deleteChilds(final SessionDTO session) {
        List<Object> input = new ArrayList<>();
        input.add(Constant.UPDATE);
        input.add(session.getUserId());
        input.add(session.getSessionId());
        assumptionDAO.getAssumptionResults(input, "assumption.childDelete");
    }

    /**
     * To delete the added lines from the Main table.
     *
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    public void savePVS(final SessionDTO session, final BeanItemContainer<AssumptionPVDTO> pvsContainer)  {
        for (AssumptionPVDTO dto : pvsContainer.getItemIds()) {
            List<Object> input = new ArrayList<>();
            input.add(Constant.UPDATE);
            input.add(dto.getPvCheck() ? 1 : 0);
            input.add(dto.getReasonCodePV());
            input.add(dto.getCommentaryPV());
            input.add(dto.getSegmentId());
            if (dto.getParent()) {
                input.add(dto.getProjectionPeriodPV());
            } else {
                input.add(dto.getChildPeriod());
            }
            input.add(dto.getParent() ? 1 : 0);
            input.add(session.getProjectionId());
            input.add(session.getUserId());
            input.add(session.getSessionId());
            input.add(dto.getChildCamId());
            input.add(dto.getSegmentId());
            input.add(Constant.NULL.equals(String.valueOf(dto.getOldReasonCode()))?"AND NMA.REASON_CODES is null":"AND NMA.REASON_CODES='"+dto.getOldReasonCode()+"'");
            input.add(Constant.NULL.equals(String.valueOf(dto.getOldReasonCode()))?"AND NMA.COMMENTARY is null":"AND NMA.COMMENTARY='"+dto.getOldCommentary()+"'");
            input.add(dto.getBrandSid());
            input.add(dto.getCompanySid());
            input.add(dto.getMatketTypeSid());
            input.add(dto.getContractHolderSid()==0?"AND CTM.CONT_HOLD_COMPANY_MASTER_SID is null":"AND CTM.CONT_HOLD_COMPANY_MASTER_SID ='"+dto.getContractHolderSid()+"'");
            assumptionDAO.getAssumptionResults(input, "assumption.updateRecord");
        }
    }

    /**
     * To Check All.
     *
     * @return
     */
    public void checkAll(final SessionDTO session, final Boolean check) {
        List<Object> input = new ArrayList<>();
        input.add(Constant.UPDATE);
        input.add(check ? 1 : 0);
        input.add(session.getUserId());
        input.add(session.getSessionId());
        assumptionDAO.getAssumptionResults(input, "assumption.checkAll");
    }

    /**
     * To Populate.
     *
     * @return
     */
    public void populateCommentry(final SessionDTO session, final Object value) {
        List<Object> input = new ArrayList<>();
        input.add(Constant.UPDATE);
        input.add(value);
        input.add(session.getUserId());
        input.add(session.getSessionId());
        assumptionDAO.getAssumptionResults(input, "assumption.populateCommentry");
    }

    /**
     * To Check All.
     *
     * @return
     */
    public void populateReasonCode(final SessionDTO session, final Object value) {
        List<Object> input = new ArrayList<>();
        input.add(Constant.UPDATE);
        input.add(value);
        input.add(session.getUserId());
        input.add(session.getSessionId());
        assumptionDAO.getAssumptionResults(input, "assumption.populateReasonCode");
    }

    /**
     * To Add the line.
     *
     * @return
     */
    public void addLine(final SessionDTO session, final Boolean value) {
        List<Object> input = new ArrayList<>();
        input.add(Constant.UPDATE);
        input.add(value ? 1 : 0);
        input.add(session.getUserId());
        input.add(session.getSessionId());
        assumptionDAO.getAssumptionResults(input, "assumption.addLine");
    }

    /**
     * To get the count.
     *
     * @return
     * @throws SystemException
     * @throws NumberFormatException
     */
    public Integer getLineCount(final SessionDTO session)  {
        List<Object> input = new ArrayList<>();
        input.add(session.getProjectionId());
        input.add(session.getUserId());
        input.add(session.getSessionId());
        return Integer.parseInt(String.valueOf(assumptionDAO.getAssumptionResults(input, "assumption.totalCount").get(0)));
    }

    /**
     * To get the count.
     *
     * @return
     * @throws SystemException
     * @throws NumberFormatException
     * @throws PortalException
     */
    public List<AssumptionPVDTO> getPVSResult(final SessionDTO session, final Integer startIndex, final Integer offset) throws SystemException, PortalException {
        List<Object> input = new ArrayList<>();
        input.add(session.getProjectionId());
        input.add(session.getUserId());
        input.add(session.getSessionId());
		input.add(session.getForecastDTO().getProjectionStartYear());
		input.add(session.getForecastDTO().getForecastEndYear());
        input.add(startIndex);
        input.add(offset);
        return getCustomizedPV(assumptionDAO.getAssumptionResults(input, "assumption.pvsTempFetch"));
    }

    /**
     * To Add the line.
     *
     * @return
     */
    public Integer checkedCount(final SessionDTO session) {
        List<Object> input = new ArrayList<>();
        input.add(session.getUserId());
        input.add(session.getSessionId());
        return Integer.parseInt(String.valueOf(assumptionDAO.getAssumptionResults(input, "assumption.checkedCount").get(0)));
    }

    /**
     * To Add the line.
     *
     * @return
     */
    public Integer childCheckedCount(final SessionDTO session) {
        List<Object> input = new ArrayList<>();
        input.add(session.getUserId());
        input.add(session.getSessionId());
        return Integer.parseInt(String.valueOf(assumptionDAO.getAssumptionResults(input, "assumption.childCheckForAddLine").get(0)));
    }
}
