package com.stpl.app.utils;

import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.NATIONAL_ASSUMPTIONS;
import static com.stpl.app.utils.Constants.LabelConstants.SALES_PROJ;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;

import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.gtn.gtn2o.ws.bean.bcp.GtnWsBcpServiceBean;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.ifs.ui.util.GtnUiBcpServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.StandaloneParser;
import com.stpl.ifs.util.constants.ForecastingConstants;

public class CumulativeCalculationUtils {

	private final Object[] procedureInputList;
	public static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger
			.getLogger(CumulativeCalculationUtils.class);
	private String tabName = StringUtils.EMPTY;
	boolean salesFlag = false;
	private final StandaloneParser credentials = StandaloneParser.getInstance();
	private final String folderName = System.getProperty(Constant.CUMULATIVE_FILE_PATH);
	private String methodology = StringUtils.EMPTY;
	private String userId = StringUtils.EMPTY;
	private String sessionId = StringUtils.EMPTY;
	private final String tableName;

	public CumulativeCalculationUtils(Object[] procedureInputList, final String userId, final String sessionId,
			final String methodology, final String tabName, final String tableName) {
		this.procedureInputList = procedureInputList == null ? procedureInputList : procedureInputList.clone();
		this.tabName = tabName;
		this.tableName = tableName;
		this.methodology = methodology;
		this.userId = userId;
		this.sessionId = sessionId;
		init();
	}

	private void init() {
		salesFlag = SALES_PROJ.getConstant().equals(tabName) || NATIONAL_ASSUMPTIONS.getConstant().equals(tabName)
				|| "Returns".equals(tabName);
		calculate();

	}

	private void calculate() {
		try {
		GtnWsBcpServiceBean gtnWsBcpServiceBean = new GtnWsBcpServiceBean();
		gtnWsBcpServiceBean.setUserId(userId);
		gtnWsBcpServiceBean.setSessionId(sessionId);
		gtnWsBcpServiceBean.setCurrentDateInString(UiUtils.getDate());
		gtnWsBcpServiceBean.setTableName(tableName);
		gtnWsBcpServiceBean.setFolderName(folderName);

		gtnWsBcpServiceBean.setServerName(credentials.getServer());
		gtnWsBcpServiceBean.setSchemaName(credentials.getSchema());
		gtnWsBcpServiceBean.setUserName(credentials.getUser());

			gtnWsBcpServiceBean
					.setPassword(credentials.isIsPasswordEncrypted() ? getDecryptedPassword(credentials.getPassword())
							: credentials.getPassword());


			gtnWsBcpServiceBean.setProcedureInputs(procedureInputList);
		gtnWsBcpServiceBean.setCumulativeFilePath(System.getProperty(Constant.CUMULATIVE_FILE_PATH));
		gtnWsBcpServiceBean.setSalesFlag(salesFlag);
		gtnWsBcpServiceBean.setMethodology(methodology);
		gtnWsBcpServiceBean.setTabName(tabName);

			GtnUiBcpServiceUtil.callBcpService(gtnWsBcpServiceBean,
					GtnWebServiceUrlConstants.GTN_BCP_SERVICE + GtnWebServiceUrlConstants.CALCULATE);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException e) {
			LOGGER.error(e);
		}
	}
	private static String getDecryptedPassword(String secret) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		byte[] kbytes = System.getProperty("KEY").getBytes();
		SecretKeySpec key = new SecretKeySpec(kbytes, ForecastingConstants.getPassword());
		BigInteger n = new BigInteger(secret, NumericConstants.SIXTEEN);
		byte[] encoding = n.toByteArray();
		if (encoding.length % NumericConstants.EIGHT != 0) {
			int length = encoding.length;
			int newLength = ((length / NumericConstants.EIGHT) + 1) * NumericConstants.EIGHT;
			int pad = newLength - length; // number of leading zero values
			byte[] old = encoding;
			encoding = new byte[newLength];
			for (int i = old.length - 1; i >= 0; i--) {
				encoding[i + pad] = old[i];
			}
			if (n.signum() == -1) {
				for (int i = 0; i < newLength - length; i++) {
					encoding[i] = (byte) -1;
				}
			}
		}
		Cipher cipher = Cipher.getInstance(ForecastingConstants.getPassword());
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decode = cipher.doFinal(encoding);
		return new String(decode);
	}

}
