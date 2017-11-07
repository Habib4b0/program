package com.stpl.app.util;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

/**
 * The Class HelperUtils.
 */
public final class HelperUtils {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(HelperUtils.class);
	/** The Constant EMPTY. */
	public static final String EMPTY = "";

	/** The Constant ITEM_TYPE. */
	public static final String ITEM_TYPE = "ItemType";

	/** The Constant RP_TYPE. */
	public static final String RP_TYPE = "RebatePlanType";

	/** The Constant COMPANY_TYPE. */
	public static final String COMPANY_TYPE = "CompanyType";

	/** The Constant MEMBER_TYPE. */
	public static final String MEMBER_TYPE = "MemberType";

	/** The Constant CFP_TYPE. */
	public static final String CFP_TYPE = "CompanyFamilyPlanType";

	/** The Constant MFP_TYPE. */
	public static final String MFP_TYPE = "MemberFamilyPlanType";

	/** The Constant IFP_TYPE. */
	public static final String IFP_TYPE = "ItemFamilyPlanType";

	/** The Constant RS_TYPE. */
	public static final String RS_TYPE = "RebateScheduleType";

	/** The Constant PS_TYPE. */
	public static final String PS_TYPE = "PriceScheduleType";

	/** The Constant TRADE_CLASS. */
	public static final String TRADE_CLASS = "TradeClass";

	/** The Constant MODULE_NAME. */
	public static final String MODULE_NAME = "module";

	/** The Constant COMPANY_CRT_QUALIFIER_NAME. */
	public static final String COMPANY_CRT_QUALIFIER_NAME = "COMPANY_CRT_QUALIFIER_NAME";

	/** The Constant MEMBER_DRT_QUALIFIER_NAME. */
	public static final String MEMBER_DRT_QUALIFIER_NAME = "MEMBER_DRT_QUALIFIER_NAME";

	/** The Constant ITEM_IRT_QUALIFIER_NAME. */
	public static final String ITEM_IRT_QUALIFIER_NAME = "ITEM_IRT_QUALIFIER_NAME";

	/** The Constant REBATE_PROGRAM_TYPE. */
	public static final String REBATE_PROGRAM_TYPE = "RebateProgramType";

	/** The Constant PAYMENT_TERMS. */
	public static final String PAYMENT_TERMS = "PaymentTerms";

	/** The Constant REBATE_FREQUENCY. */
	public static final String REBATE_FREQUENCY = "RebateFrequency";

	/** The Constant PAYMENT_FREQUENCY. */
	public static final String PAYMENT_FREQUENCY = "PaymentFrequency";

	/** The Constant REBATE_PLAN_LEVEL. */
	public static final String REBATE_PLAN_LEVEL = "RebatePlanLevel";

	/** The Constant CALENDER. */
	public static final String CALENDER = "Calendar";

	/** The Constant VALIDATION_PROFILE. */
	public static final String VALIDATION_PROFILE = "ValidationProfile";

	/** The Constant REBATE_STRUCTURE. */
	public static final String REBATE_STRUCTURE = "RebateStructure";

	/** The Constant PAYMENT_METHOD. */
	public static final String PAYMENT_METHOD = "PaymentMethod";

	/** The Constant REBATE_BASED_ON. */
	public static final String REBATE_BASED_ON = "RebateBasedOn";

	/** The Constant REBATE_RANGE_BASED_ON. */
	public static final String REBATE_RANGE_BASED_ON = "RebateRangeBasedOn";

	/** The Constant IS_ACTIVE. */
	public static final String IS_ACTIVE = "STATUS";

	/** The Constant END_DATE. */
	public static final String END_DATE = "EndDate";

	/** The Constant START_DATE. */
	public static final String START_DATE = "StartDate";

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public static List<String> getStatus() {
		LOGGER.debug("Entering getStatus");
		final List<String> status = new ArrayList<>();
		try {
			status.add("Active");
			status.add("Inactive");
			LOGGER.debug("Ends getStatus with status size  :::: " + status.size());
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return status;
	}

	/**
	 * Gets the mfp populate list.
	 *
	 * @return the mfp populate list
	 */
	public static List<String> getMfpPopulateList() {
		LOGGER.debug("Entering getMfpPopulateList");
		final List<String> status = new ArrayList<>();
		try {
			status.add(START_DATE);
			status.add(END_DATE);
			status.add("TradeClass StartDate");
			status.add("TradeClass EndDate");
			status.add("Status");
			status.add(ConstantUtil.ATTACHED_STATUS);
			status.add("Attached EndDate");
			LOGGER.debug("Ends getMfpPopulateList with status size  :::: " + status.size());
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return status;
	}

	/**
	 * Gets the ifp populate list.
	 *
	 * @return the ifp populate list
	 */
	public static List<String> getIfpPopulateList() {
		LOGGER.debug("Entering getIfpPopulateList");
		final List<String> status = new ArrayList<>();
		try {
			status.add(START_DATE);
			status.add(END_DATE);
			status.add("Item StartDate");
			status.add("Item EndDate");
			status.add("Attached Status");
			LOGGER.debug("Ends getIfpPopulateList with status size  :::: " + status.size());
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return status;
	}

	/**
	 * Gets the cfp populate list.
	 *
	 * @return the cfp populate list
	 */
	public static List<String> getCfpPopulateList() {
		LOGGER.debug("Entering getCfpPopulateList");
		final List<String> status = new ArrayList<>();
		try {
			status.add(START_DATE);
			status.add(END_DATE);
			status.add("TradeClass StartDate");
			status.add("TradeClass EndDate");
			status.add("Attached Status");
			LOGGER.debug("Ends getCfpPopulateList with status size  :::: " + status.size());
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return status;
	}

	/**
	 * Gets the ps populate list.
	 *
	 * @return the ps populate list
	 */
	public static List<String> getPsPopulateList() {
		LOGGER.debug("Entering getPsPopulateList");
		final List<String> status = new ArrayList<>();
		try {
			status.add(START_DATE);
			status.add(END_DATE);
			status.add("RevisionDate");
			LOGGER.debug("Ends getPsPopulateList with status size  :::: " + status.size());
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return status;
	}

	/**
	 * Ge rs populate list.
	 *
	 * @return the list< string>
	 */
	public static List<String> geRsPopulateList() {
		LOGGER.debug("Entering geRsPopulateList");
		final List<String> status = new ArrayList<>();
		try {
			status.add("Rebate Plan Name");
			status.add("Rebate Amount");
			status.add(START_DATE);
			status.add(END_DATE);
			LOGGER.debug("Ends geRsPopulateList with status size  :::: " + status.size());
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return status;
	}

	/**
	 * The Constructor.
	 */
	private HelperUtils() {

	}
}
