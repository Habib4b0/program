/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.common;

import com.stpl.app.accountclose.dto.FixedDollarDTO;
import com.stpl.app.accountclose.dto.TreeDTO;
import com.stpl.app.accountclose.utils.QueryConstants;
import com.stpl.app.accountclose.utils.QueryUtils;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author alok.v
 */
public class FixedDollarQuery {

    private static final Logger LOGGER = Logger.getLogger(FixedDollarQuery.class);

    public String getLoadFdDataQuery(TreeDTO dto) {
        String query = StringUtils.EMPTY;
        LOGGER.info("Entered getLoadDataQuery" + dto.getContractSid() + "-" + dto.getItemSid());
        Map<String, String> input = new HashMap(); 
        if (dto.getLevelNo() == 1) { 
            if (dto.getAccSidFromworkflow().equalsIgnoreCase("0")) {
                input.put(QueryConstants.IMID, dto.getItemSid());
                input.put(QueryConstants.CMID, dto.getContractSid());
                 query = QueryUtils.replacedQuery(input, "selectLevel0Query");
            } else {
                String Masterid = dto.getAccSidFromworkflowvalue();
                input.put(QueryConstants.AC_MASTER_SID, Masterid);
                input.put(QueryConstants.IMID, dto.getItemSid());
                input.put(QueryConstants.CMID, dto.getContractSid());
                query = QueryUtils.replacedQuery(input, "selectLevel1Query");
            }
        }
        if (dto.getLevelNo() == 2) {
           
            input.put(QueryConstants.IMID, dto.getItemSid());
            input.put(QueryConstants.CMID, dto.getContractSid());
            input.put(QueryConstants.CCPID, dto.getCcpSid());
            query = QueryUtils.replacedQuery(input, "selectLevel2Query");
        }
        if (dto.getLevelNo() == 3) {
            input.put(QueryConstants.IMID, dto.getItemSid());
            input.put(QueryConstants.CMID, dto.getContractSid());
            input.put(QueryConstants.CCPID, dto.getCcpSid());
            query = QueryUtils.replacedQuery(input, "selectLevel3Query");
        }
        if (dto.getLevelNo() == 4) {
            input.put(QueryConstants.IMID, dto.getItemSid());
            input.put(QueryConstants.CMID, dto.getContractSid());
            input.put(QueryConstants.CCPID, dto.getCcpSid());
            query = QueryUtils.replacedQuery(input, "selectLevel4Query");
        }
        if (dto.getLevelNo() == 5) {
            input.put(QueryConstants.IMID, dto.getItemSid());
            input.put(QueryConstants.CMID, dto.getContractSid());
            input.put(QueryConstants.CCPID, dto.getCcpSid());
            query = QueryUtils.replacedQuery(input, "selectLevel5Query");
        }
        return query;

    }

   
    public String getLoadFdResultsDataQuery(FixedDollarDTO dto) {
        Map<String, String> input = new HashMap();
        String query = StringUtils.EMPTY;
        LOGGER.info("dto.getLevelNo()" + dto.getLevelNo());
        String calculate = dto.getCalculate();
        LOGGER.info("Entered getLoadFdResultsDataQuery" + dto.getContractSid() + "-" + dto.getItemSid());
        input.put(QueryConstants.IMID, dto.getItemSid());
        input.put(QueryConstants.CMID, dto.getContractSid());
        if (calculate.equalsIgnoreCase("0")) {
            if (dto.getLevelNo() == 1) {

                input.put(QueryConstants.CCPID, dto.getSelectedCCplist());
                query = QueryUtils.replacedQuery(input, "resultsLevel1Query");
            }
            if (dto.getLevelNo() == 2) {
                input.put(QueryConstants.CCPID, dto.getCcpSid());
                query = QueryUtils.replacedQuery(input, "resultsLevel2Query");
            }
            if (dto.getLevelNo() == 3) {
                input.put(QueryConstants.CCPID, dto.getCcpSid());
                query = QueryUtils.replacedQuery(input, "resultsLevel3Query");
            }
            if (dto.getLevelNo() == 4) {
                input.put(QueryConstants.CCPID, dto.getCcpSid());
                query = QueryUtils.replacedQuery(input, "resultsLevel4Query");
            }
            if (dto.getLevelNo() == 5) {
                input.put(QueryConstants.CCPID, dto.getCcpSid());
                query = QueryUtils.replacedQuery(input, "resultsLevel5Query");
            }
        }
        if (calculate.equalsIgnoreCase("1")) {
            input.put(QueryConstants.AC_MASTER_SID, dto.getAccountClosureMasterSid());

            if (dto.getLevelNo() == 1) {
                query = QueryUtils.replacedQuery(input, "calculateLevel1Query");
            }
            if (dto.getLevelNo() == 2) {
                input.put(QueryConstants.CCPID, dto.getCcpSid());
                query = QueryUtils.replacedQuery(input, "calculateLevel2Query");

            }
            if (dto.getLevelNo() == 3) {
                input.put(QueryConstants.CCPID, dto.getCcpSid());
                query = QueryUtils.replacedQuery(input, "calculateLevel3Query");
            }
            if (dto.getLevelNo() == 4) {
                input.put(QueryConstants.CCPID, dto.getCcpSid());
                query = QueryUtils.replacedQuery(input, "calculateLevel4Query");
            }
            if (dto.getLevelNo() == 5) {
                input.put(QueryConstants.CCPID, dto.getCcpSid());
                query = QueryUtils.replacedQuery(input, "calculateLevel5Query");
            }

        }
        return query;

    }
}
