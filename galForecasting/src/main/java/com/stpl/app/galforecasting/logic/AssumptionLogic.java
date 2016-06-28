/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.logic;

import com.stpl.app.galforecasting.dao.AssumptionDAO;
import com.stpl.app.galforecasting.dao.impl.AssumptionDAOImpl;
import com.stpl.app.galforecasting.dto.AssumptionCIDTDTO;
import com.stpl.app.galforecasting.dto.AssumptionDiscountDTO;
import com.stpl.app.galforecasting.dto.AssumptionPPADTO;
import com.stpl.app.galforecasting.dto.AssumptionPVDTO;
import com.stpl.app.galforecasting.dto.AssumptionSalesDTO;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.DASH;
import com.stpl.app.galforecasting.utils.Converters;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItemContainer;

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
        return getCustomizedCIDT(assumptionDAO.getCIDTResults());
    }

    /**
     * To customize the Customer ID Transfer Results.
     *
     * @param result
     * @return
     */
    private List<AssumptionCIDTDTO> getCustomizedCIDT(List result) {
        return new ArrayList<AssumptionCIDTDTO>();
    }

    /**
     * To get the Projection Variance Snapshot Results.
     *
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    public void getPVResults(final SessionDTO session) throws PortalException, SystemException {
        List<Object> input = new ArrayList<Object>();
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
        List<AssumptionPVDTO> retList = new ArrayList<AssumptionPVDTO>();
        for (Object[] temp : (List<Object[]>) result) {
            AssumptionPVDTO tempDto = new AssumptionPVDTO();
            tempDto.setParent((Boolean) temp[2]);
            tempDto.setPvCheck(Integer.valueOf(String.valueOf(temp[0]))==0?Boolean.FALSE:Boolean.TRUE);
            if (tempDto.getParent()) {
                tempDto.setProjectionPeriodPV(String.valueOf(temp[1]));
                tempDto.setContractHolder(String.valueOf(temp[3]));
                tempDto.setCostomer(String.valueOf(temp[4]));
                tempDto.setSegment(Constant.NULL.equals(String.valueOf(temp[5])) ? StringUtils.EMPTY : temp[5].toString());
                tempDto.setSegmentId(Constant.NULL.equals(String.valueOf(temp[5])) ? StringUtils.EMPTY : temp[5].toString());
                tempDto.setMarketType(Constant.NULL.equals(String.valueOf(temp[6])) ? StringUtils.EMPTY : temp[6].toString());
                tempDto.setBrandPV(Constant.NULL.equals(String.valueOf(temp[7])) ? StringUtils.EMPTY : temp[7].toString());
                BigDecimal grossSalePrior = Constant.NULL.equals(String.valueOf(temp[8])) || StringUtils.EMPTY.equals(String.valueOf(temp[8])) ? new BigDecimal(0) : (BigDecimal) temp[8];
                BigDecimal grossSaleProjected = Constant.NULL.equals(String.valueOf(temp[9])) || StringUtils.EMPTY.equals(String.valueOf(temp[9])) ? new BigDecimal(0) : (BigDecimal) temp[9];
                Double grossSaleChange = 0d;
                if (grossSaleProjected.doubleValue() != 0d) {
                    grossSaleChange = ((grossSaleProjected.doubleValue() - grossSalePrior.doubleValue()) / grossSaleProjected.doubleValue()) * 100;
                } else if (grossSalePrior.doubleValue() != 0d) {
                    grossSaleChange = ((grossSaleProjected.doubleValue() - grossSalePrior.doubleValue()) / grossSalePrior.doubleValue()) * 100;
                }
                BigDecimal totalDiscountPrior1 = Constant.NULL.equals(String.valueOf(temp[11])) || StringUtils.EMPTY.equals(String.valueOf(temp[11])) ? new BigDecimal(0) : (BigDecimal) temp[11];
                BigDecimal totalDiscountProjected1 = Constant.NULL.equals(String.valueOf(temp[12])) || StringUtils.EMPTY.equals(String.valueOf(temp[12])) ? new BigDecimal(0) : (BigDecimal) temp[12];
                Double totalDiscountPrior = totalDiscountPrior1.doubleValue() * 100 / grossSalePrior.doubleValue();
                Double totalDiscountProjected = totalDiscountProjected1.doubleValue() * 100 / grossSaleProjected.doubleValue();
                Double totalDiscountChange = 0d;
                if (totalDiscountProjected1.doubleValue() != 0d) {
                    totalDiscountChange = ((totalDiscountProjected1.doubleValue() - totalDiscountPrior1.doubleValue()) / totalDiscountProjected1.doubleValue()) * 100;
                } else if (totalDiscountPrior1.doubleValue() != 0d) {
                    totalDiscountChange = ((totalDiscountProjected1.doubleValue() - totalDiscountPrior1.doubleValue()) / totalDiscountPrior1.doubleValue()) * 100;
                }
                Double netSalePrior = grossSalePrior.doubleValue() - totalDiscountPrior1.doubleValue();
                Double netSaleProjected = grossSaleProjected.doubleValue() - totalDiscountProjected1.doubleValue();
                Double netSaleChange = 0d;
                if (netSaleProjected.doubleValue() != 0d) {
                    netSaleChange = ((netSaleProjected.doubleValue() - netSalePrior.doubleValue()) / netSaleProjected.doubleValue()) * 100;
                } else if (netSalePrior.doubleValue() != 0d) {
                    netSaleChange = ((netSaleProjected.doubleValue() - netSalePrior.doubleValue()) / netSalePrior.doubleValue()) * 100;
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

                tempDto.setCamNamePV(Converters.getUserFLName(String.valueOf(temp[17])));
            } else {
                tempDto.setChildPeriod(String.valueOf(temp[1]));
            }
            tempDto.setChildCamId(String.valueOf(temp[17]));
            tempDto.setSegmentId(Constant.NULL.equals(String.valueOf(temp[5])) ? StringUtils.EMPTY : temp[5].toString());
            tempDto.setCommentaryPV(Constant.NULL.equals(String.valueOf(temp[18])) ? StringUtils.EMPTY : temp[18].toString());
            tempDto.setOldCommentary(String.valueOf(temp[18]));
            tempDto.setUserId(Integer.valueOf(String.valueOf(temp[19])));
            tempDto.setSessionId(Integer.valueOf(String.valueOf(temp[20])));
            tempDto.setReasonCodePV(Constant.NULL.equals(String.valueOf(temp[21])) ? StringUtils.EMPTY : temp[21].toString());
            tempDto.setOldReasonCode(String.valueOf(temp[21]));
            tempDto.setContractHolderSid(Integer.valueOf(Constant.NULL.equals(String.valueOf(temp[22]))?DASH:String.valueOf(temp[22])));
            tempDto.setCompanySid(Integer.valueOf(String.valueOf(temp[23])));
            tempDto.setMatketTypeSid(Integer.valueOf(String.valueOf(temp[24])));
            tempDto.setBrandSid(Integer.valueOf(String.valueOf(temp[25])));
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
        List<Object> input = new ArrayList<Object>();
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

        LOGGER.info("custom Logger Sales");
        List<AssumptionSalesDTO> retList = new ArrayList<AssumptionSalesDTO>();
        for (Object[] temp : (List<Object[]>) result) {
            AssumptionSalesDTO tempDto = new AssumptionSalesDTO();
            tempDto.setProjectionPeriod(String.valueOf(temp[0]));
            tempDto.setTradingPartner(Constant.NULL.equals(String.valueOf(temp[1])) ? StringUtils.EMPTY : temp[1].toString());
            tempDto.setCustomerID(Constant.NULL.equals(String.valueOf(temp[2])) ? StringUtils.EMPTY : temp[2].toString());
            tempDto.setGroup(Constant.NULL.equals(String.valueOf(temp[3])) ? StringUtils.EMPTY : temp[3].toString());
            tempDto.setBrand(Constant.NULL.equals(String.valueOf(temp[4])) ? StringUtils.EMPTY : temp[4].toString());
            BigDecimal accountGrouth = Constant.NULL.equals(String.valueOf(temp[5])) || StringUtils.EMPTY.equals(String.valueOf(temp[5])) ? new BigDecimal(0) : (BigDecimal) temp[5];
            BigDecimal productGrouth = Constant.NULL.equals(String.valueOf(temp[6])) || StringUtils.EMPTY.equals(String.valueOf(temp[6])) ? new BigDecimal(0) : (BigDecimal) temp[6];
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
        List<Object> input = new ArrayList<Object>();
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
        List<AssumptionDiscountDTO> retList = new ArrayList<AssumptionDiscountDTO>();
        for (Object[] temp : (List<Object[]>) result) {
            AssumptionDiscountDTO tempDto = new AssumptionDiscountDTO();
            tempDto.setProjectionPeriod(String.valueOf(temp[0]));
            tempDto.setTradingPartner(Constant.NULL.equals(String.valueOf(temp[1])) ? StringUtils.EMPTY : temp[1].toString());
            tempDto.setCustomerID(Constant.NULL.equals(String.valueOf(temp[2])) ? StringUtils.EMPTY : temp[2].toString());
            tempDto.setGroup(Constant.NULL.equals(String.valueOf(temp[3])) ? StringUtils.EMPTY : temp[3].toString());
            tempDto.setBrand(Constant.NULL.equals(String.valueOf(temp[4])) ? StringUtils.EMPTY : temp[4].toString());
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
        List<Object> input = new ArrayList<Object>();
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
        List<AssumptionPPADTO> retList = new ArrayList<AssumptionPPADTO>();
        for (Object[] temp : (List<Object[]>) result) {
            AssumptionPPADTO tempDto = new AssumptionPPADTO();
            tempDto.setProjectionPeriod(String.valueOf(temp[0]));
            tempDto.setTradingPartner(String.valueOf(temp[1]));
            tempDto.setCustomerID(String.valueOf(temp[2]));
            tempDto.setGroup(Constant.NULL.equals(String.valueOf(temp[3])) ? StringUtils.EMPTY : temp[3].toString());
            tempDto.setBrand(Constant.NULL.equals(String.valueOf(temp[4])) ? StringUtils.EMPTY : temp[4].toString());
            BigDecimal priceCap = Constant.NULL.equals(String.valueOf(temp[5])) || StringUtils.EMPTY.equals(String.valueOf(temp[5])) ? new BigDecimal(0) : (BigDecimal) temp[5];
            BigDecimal discountPer = Constant.NULL.equals(String.valueOf(temp[6])) || StringUtils.EMPTY.equals(String.valueOf(temp[6])) ? new BigDecimal(0) : (BigDecimal) temp[6];
            BigDecimal discountDollar = Constant.NULL.equals(String.valueOf(temp[7])) || StringUtils.EMPTY.equals(String.valueOf(temp[7])) ? new BigDecimal(0) : (BigDecimal) temp[7];
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
    public void removeLinePVS(final SessionDTO session, AssumptionPVDTO child) throws PortalException, SystemException {
            List<Object> input = new ArrayList<Object>();
            input.add(Constant.UPDATE);
            
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
        List<Object> input = new ArrayList<Object>();
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
    public void savePVS(final SessionDTO session, final BeanItemContainer<AssumptionPVDTO> pvsContainer) throws SystemException, PortalException {
        for (AssumptionPVDTO dto : pvsContainer.getItemIds()) {
            List<Object> input = new ArrayList<Object>();
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
        List<Object> input = new ArrayList<Object>();
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
        List<Object> input = new ArrayList<Object>();
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
        List<Object> input = new ArrayList<Object>();
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
        List<Object> input = new ArrayList<Object>();
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
    public Integer getLineCount(final SessionDTO session) throws SystemException {
        List<Object> input = new ArrayList<Object>();
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
        List<Object> input = new ArrayList<Object>();
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
        List<Object> input = new ArrayList<Object>();
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
        List<Object> input = new ArrayList<Object>();
        input.add(session.getUserId());
        input.add(session.getSessionId());
        return Integer.parseInt(String.valueOf(assumptionDAO.getAssumptionResults(input, "assumption.childCheckForAddLine").get(0)));
    }
}
