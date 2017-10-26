
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.util.constants;

import com.stpl.app.util.service.PropertiesReader;

/**
 *
 * @author sathyaseelan.v
 */
public class ARMMessages {

    private static final PropertiesReader.ExtProperties properties = PropertiesReader.getInstance().getConfirmationMessages();

    public static String getResetMessageName() {
        return properties.getProperty("RESET_MESSAGE_NAME");
    }

    public static String getResetMessage() {
        return properties.getProperty("RESET_MESSAGE");
    }

    public static String getResetMessageID001() {
        return properties.getProperty("RESET_MESSAGE_MSGID_001");
    }

    public static String getResetMessageID002() {
        return properties.getProperty("RESET_MESSAGE_MSGID_002");

    }

    public static String getResetMessageID003() {
        return properties.getProperty("RESET_MESSAGE_MSGID_003");

    }

    public static String getResetMessageID005() {
        return properties.getProperty("RESET_MESSAGE_MSGID_005");

    }

    public static String getResetConfirmationMessage() {
        return properties.getProperty("CW_SUBMIT_1");
    }

    public static String getPropertyMessageName() {
        return properties.getProperty("POPULATE_MESSAGE_NAME");
    }

    public static String getPropertyMessage001() {
        return properties.getProperty("POPULATE_MESSAGE_MSGID_001");
    }

    public static String getPropertyMessage002() {
        return properties.getProperty("POPULATE_MESSAGE_MSGID_002");
    }

    public static String getPropertyMessage003() {
        return properties.getProperty("GENERATE_MESSAGE_MSGID_001");
    }

    public static String getRemoveLineMessageName() {
        return properties.getProperty("REMOVE_LINE_MESSAGE_NAME");
    }

    public static String getRemoveLineMessageID001() {
        return properties.getProperty("REMOVE_LINE_MSGID_001");
    }

    public static String getRemoveLineMessageID002() {
        return properties.getProperty("REMOVE_LINE_MSGID_002");
    }

    public static String getCopyLineMessageID001() {
        return properties.getProperty("COPY_LINE_MSGID_001");
    }

    public static String getCopyLineMessageID002() {
        return properties.getProperty("COPY_LINE_MSGID_002");
    }

    public static String getSaveMessageName() {
        return properties.getProperty("SAVE_MESSAGE_NAME");
    }

    public static String getSaveMessageID001() {
        return properties.getProperty("SAVE_MESSAGE_MSGID_001");
    }

    public static String getSaveMessageID002() {
        return properties.getProperty("SAVE_MESSAGE_MSGID_002");
    }

    public static String getSaveMessageID003() {
        return properties.getProperty("SAVE_MESSAGE_MSGID_003");
    }

    public static String getSaveMessageID004() {
        return properties.getProperty("SAVE_MESSAGE_MSGID_004");
    }

    public static String getSaveMessageID005() {
        return properties.getProperty("SAVE_MESSAGE_MSGID_005");
    }

    public static String getSaveMessageID006() {
        return properties.getProperty("SAVE_MESSAGE_MSGID_006");
    }

    public static String getSaveMessageID007() {
        return properties.getProperty("SAVE_MESSAGE_MSGID_007");
    }

    public static String getSaveMessageID0010() {
        return properties.getProperty("SAVE_MESSAGE_MSGID_008");
    }

    public static String getCloseMessageName() {
        return properties.getProperty("CLOSE_MESSAGE_NAME");
    }

    public static String getCloseMessageID001() {
        return properties.getProperty("CLOSE_MESSAGE_MSGID_001");
    }

    public static String getGenerateMessageName() {
        return properties.getProperty("GENERATE_MESSAGE_NAME");
    }

    public static String getGenerateMessageID001() {
        return properties.getProperty("GENERATE_MESSAGE_MSGID_001");
    }

    public static String getGenerateErrorHeaderMessage() {
        return properties.getProperty("CW_SUBMIT_3");
    }

    public static String getResetMessage_views() {
        return properties.getProperty("RESET_MSG_001");
    }

    public static String getResetMessage_exclusion() {
        return properties.getProperty("RESET_MSG_002");
    }

    public static String getDeleteMessage_exclusion() {
        return properties.getProperty("DELETE_MSG_001");
    }

    public static String getDeleteErrorMessage_exclusion() {
        return properties.getProperty("DELETE_MSG_002");
    }

    public static String getDeleteErrorMessage_exclusion_003() {
        return properties.getProperty("DELETE_MSG_003");
    }

    public static String getSubmitMessage_exclusion() {
        return properties.getProperty("SUBMIT_MSG_001");
    }

    public static String getSelect_Msg_002() {
        return properties.getProperty("SELECT_MSG_002");
    }

    public static String getMoveLeftRightMessage_exclusion() {
        return properties.getProperty("MOVE_LEFT_RIGHT_MSG");
    }

    public static String getResetMessageName_001() {
        return properties.getProperty("RESET_MESSAGE_NAME_001");
    }

    public static String getGenerateMessageName_001() {
        return properties.getProperty("GENERATE_MESSAGE_NAME_001");
    }

    public static String getGenerateMessageMsgId_004() {
        return properties.getProperty("GENERATE_MESSAGE_MSGID_004");
    }

    public static String getGenerateMessage_MsgId_002() {
        return properties.getProperty("GENERATE_MESSAGE_MSGID_003");
    }

    public static String getExpandMessageName() {
        return properties.getProperty("EXPAND_MESSAGE_NAME");
    }

    public static String getExpandMessageMsgId_001() {
        return properties.getProperty("EXPAND_MESSAGE_MSGID_001");
    }

    public static String getResetMessageID004() {
        return properties.getProperty("RESET_MESSAGE_MSGID_004");

    }

    public static String getGenerateMessageID002() {
        return properties.getProperty("GENERATE_MESSAGE_NAME_002");
    }

    public static String getCloseMessageName_001() {
        return properties.getProperty("CLOSE_MESSAGE_NAME_001");
    }

    public static String getCloseMessageID002() {
        return properties.getProperty("CLOSE_MESSAGE_MSGID_002");
    }

    public static String getCloseMessageID003() {
        return properties.getProperty("CLOSE_MESSAGE_MSGID_003");
    }

    public static String getCloseMessageID004() {
        return properties.getProperty("CLOSE_MESSAGE_MSGID_004");
    }

    public static String getCloseMessageID005() {
        return properties.getProperty("CLOSE_MESSAGE_MSGID_005");
    }

    public static String getCloseMessageID006() {
        return properties.getProperty("CLOSE_MESSAGE_MSGID_006");
    }

    public static String getCloseMessageID007() {
        return properties.getProperty("CLOSE_MESSAGE_MSGID_007");
    }

    public static String getCloseMessageID008() {
        return properties.getProperty("CLOSE_MESSAGE_MSGID_008");
    }

    public static String getCloseMessageID012() {
        return properties.getProperty("CLOSE_MESSAGE_MSGID_012");
    }
    
    public static String getSearchMsg_001() {
        return properties.getProperty("SEARCH_MSG_001");
    }

    public static String getSuccessful_msg_001() {
        return properties.getProperty("SUCCESSFUL_MSG_001");
    }

    public static String getGenerateMessage_MsgId_003() {
        return properties.getProperty("GENERATE_MESSAGE_MSGID_003");
    }

    public static String getSaveSuccessfulMessage() {
        return properties.getProperty("SUCCESSFUL_MSG_002");
    }

    public static String getCloseMessageID009() {
        return properties.getProperty("CLOSE_MESSAGE_MSGID_009");
    }

    public static String getDeleteConfirmationMessage() {
        return properties.getProperty("CONFIRMATION_DELETE");
    }

    public static String getDeleteErrorMessage() {
        return properties.getProperty("CW_SUBMIT_3");
    }

    public static String getGLImpactMessageId001() {
        return properties.getProperty("GLIMPACT_MESSAGE_ID_001");
    }

    public static String getNoResultsMessage() {
        return properties.getProperty("SEARCH_MSG_002");
    }

    public static String getPropertyMessage004() {
        return properties.getProperty("GENERATE_MESSAGE_MSGID_005");
    }

    public static String getdDelete_Conform_1() {
        return properties.getProperty("DELETE_CONFORM_1");
    }

    public static String getdReset_Conform_1() {
        return properties.getProperty("RESET_CONFORM_1");
    }

    public static String getdDelete_Conform_2() {
        return properties.getProperty("DELETE_CONFORM_2");
    }

    public static String getdDelete_Conform_3() {
        return properties.getProperty("DELETE_CONFORM_3");
    }

    public static String getSave_Msg1() {
        return properties.getProperty("SAVE_MSG1");
    }

    public static String getCloseMessage_Summary() {
        return properties.getProperty("CLOSE_MESSAGE_MSGID_010");
    }

    public static String getGenerateMessage_Demand() {
        return properties.getProperty("GENERATE_MESSAGE_MSGID_002");
    }

    public static String getSaveMessageID008() {
        return properties.getProperty("SAVE_MESSAGE_MSGID_009");
    }

    public static String getNoResultsFoundMessage() {
        return properties.getProperty("SEARCH_MSG_003");
    }
    
    public static String getSubmitErrorTransaction1() {
        return properties.getProperty("SUMBIT_ERROR_TRANSACTION_1");
    }

    public static String getSubmitErrorTransactionDemand() {
        return properties.getProperty("SUMBIT_ERROR_TRANSACTION_2");
    }

    public static String getSubmitErrorTransaction3() {
        return properties.getProperty("SUMBIT_ERROR_TRANSACTION_3");
    }
    
    public static String getSubmitErrorTransaction6() {
        return properties.getProperty("SUMBIT_ERROR_TRANSACTION_7");
    }

     public static String getSubmitErrorTransaction7() {
        return properties.getProperty("SUMBIT_ERROR_TRANSACTION7");
    }
    

    public static String getAdjConfigSaveErrorMsg() {
        return properties.getProperty("ADJ_CONFIG_SAVE_ERROR_MSG");
    }

    public static String getAdjConfigSaveTranNameErrorMsg() {
        return properties.getProperty("ADJ_CONFIG_SAVE_TRANSACTION_NAME_ERROR_MSG");
    }

    public static String getInventoryCustomerGroupRemoveALLErrorMsg1() {
        return properties.getProperty("REMOVEALL_BUTTON_CUSTOMER_GROUP_MESSAGE_NAME");
    }

    public static String getInventoryCustomerGroupRemoveALLErrorMsg2() {
        return properties.getProperty("REMOVEALL_BUTTON_CUSTOMER_GROUP_MESSAGE_BODY");
    }

    public static String getInventoryCustomerGroupADDALLErrorMsg1() {
        return properties.getProperty("ADDALL_BUTTON_CUSTOMER_GROUP_MESSAGE_NAME");
    }

    public static String getInventoryCustomerGroupADDALLErrorMsg2() {
        return properties.getProperty("ADDALL_BUTTON_CUSTOMER_GROUP_MESSAGE_BODY");
    }

    public static String getLookUpSubmitErrorTransaction3() {
        return properties.getProperty("SUMBIT_ERROR_MESSAGE_LOOKUP_TRANSACTION_3");
    }

    public static String getCGLookUpSubmitConfirmTransaction3() {
        return properties.getProperty("SUMBIT_CONFIRMATION_CUSTOMER_GROUP_LOOKUP_TRANSACTION_3");
    }

    public static String getCLookUpSubmitConfirmTransaction3() {
        return properties.getProperty("SUMBIT_CONFIRMATION_CUSTOMER_LOOKUP_TRANSACTION_3");
    }

    public static String getRemoveButtonMessageId001() {
        return properties.getProperty("REMOVE_BUTTON_MSG_ID_001");
    }
    public static String getResetMessageId006() {
        return properties.getProperty("RESET_MESSAGE_MSGID_006");
    }
    public static String getSalesLeaveConfirmHeaderTransaction1() {
        return properties.getProperty("SALES_LEAVE_CONFORM_HEADER_1");
    }
    public static String getSalesLeaveConfirmMessageTransaction1() {
        return properties.getProperty("SALES_LEAVE_CONFORM_MESSAGE_1");
    }
    
    public static String getRatesLeaveConfirmHeaderTransaction7() {
        return properties.getProperty("RATES_LEAVE_CONFORM_HEADER_7");
    }
    public static String getRatesLeaveConfirmMessageTransaction7() {
        return properties.getProperty("RATES_LEAVE_CONFORM_MESSAGE_7");
    }
    
    public static String getInventoryLeaveConfirmHeaderTransaction3() {
        return properties.getProperty("INVENTORY_LEAVE_CONFORM_HEADER_3");
    }
    public static String getInventoryLeaveConfirmMessageTransaction3() {
        return properties.getProperty("INVENTORY_LEAVE_CONFORM_MESSAGE_3");
    }
    public static String getInventoryLeaveConfirmMessageTransaction6() {
        return properties.getProperty("INVENTORY_LEAVE_CONFORM_MESSAGE_6");
    }
    public static String getCloseMessageID010() {
        return properties.getProperty("CLOSE_MESSAGE_MSGID_010");
    }
    public static String getResetMessage_view_CG() {
        return properties.getProperty("RESET_MSG_003");
    }
    public static String getSubmitConfirmationTransaction7() {
        return properties.getProperty("SUMBIT_CONFIRMATION_TRANSACTION_7");
}
    public static String getCloseMessageID011() {
        return properties.getProperty("CLOSE_MESSAGE_MSGID_011");
    }
     public static String getSubmitErrorTransaction8() {
        return properties.getProperty("SUMBIT_ERROR_TRANSACTION_8");
    }

        public static String getCalculateMessageName() {
        return properties.getProperty("CALCULATE_MESSAGE_NAME");
    }

    public static String getCalculateMessageMsgId_001() {
        return properties.getProperty("CALCULATE_MESSAGE_MSGID_001");
    }
    public static String getDeleteMessageId_004() {
        return properties.getProperty("DELETE_MSG_004");
    }
   
    public static String getCloseMessageID013() {
        return properties.getProperty("CLOSE_MESSAGE_MSGID_013");
    }

    public static String getCloseMessageID014() {
        return properties.getProperty("CLOSE_MESSAGE_MSGID_014");
    }
   
}
