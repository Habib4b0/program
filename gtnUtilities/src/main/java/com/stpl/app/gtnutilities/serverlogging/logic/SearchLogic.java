/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnutilities.serverlogging.logic;

import com.stpl.app.gtnutilities.serverlogging.dto.LoggingDto;
import com.stpl.app.gtnutilities.serverlogging.ui.LoggingUI;
import com.vaadin.server.VaadinService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Karthik.Raja
 */
public class SearchLogic {
  private static  SearchLogic searchLogic;
  //replacing file path  for both windows and Linux
    String filepath = VaadinService.getCurrent()
            .getBaseDirectory().getAbsolutePath().replace("deployments/gtnUtilities.war", "log/LogLists.txt").replace("deployments\\gtnUtilities.war", "log\\LogLists.txt");
    Properties prop = new Properties();
       /**
     * The Constant LOGGER.
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(LoggingUI.class);
    private SearchLogic(){
        
    }
     File file =null;
    public static SearchLogic getInstance() {
        if (searchLogic == null) {
            searchLogic = new SearchLogic();
        }
        return searchLogic;
    }

    public List searchResults(boolean isCount,int start,int end) {
        List list = new ArrayList<>();
        LoggingDto logDTO = new LoggingDto();
        FileInputStream oFile = null;
        end+=start;
        try {

            File fileList = new File(filepath);
            fileList.createNewFile(); // if file already exists will do nothing
            oFile = new FileInputStream(fileList);
            prop = new Properties();
            prop.load(oFile);
            if (isCount) {
                list.add(prop.size());
                return list;
            }
            for (int i = start; i < end; i++) {
                Object key = prop.keySet().toArray()[i];
                logDTO = new LoggingDto();
                logDTO.setLogDestination(String.valueOf(key));
                logDTO.setMessagesSelected(String.valueOf(prop.get(key)));
                file = new File(String.valueOf(key));
                logDTO.setActive(file.exists() && !file.isDirectory() ? "Yes" : "No");
                list.add(logDTO);
            }
            return list;
        } catch (Exception ex) {
            LOGGER.debug(ex);
            return list;
        } finally {
            try {
                oFile.close();
                return list;
            } catch (IOException ex) {
                LOGGER.debug(ex);
            }
        }

    }

    public String addLogFile(String logDestination,String MessagesSelected,boolean isRemove) {
        try {

            File fileList = new File(filepath);
            fileList.createNewFile(); // if file already exists will do nothing
            
            FileInputStream in =new FileInputStream(fileList);
            prop = new Properties();
            prop.load(in);
            if (isRemove ) {
                if (prop.containsKey(logDestination)) {
                    prop.remove(logDestination);
                } 
            } else {
                prop.setProperty(logDestination, MessagesSelected);
            }
            in.close();
            prop.store(new FileOutputStream(fileList), null);
          
            return StringUtils.EMPTY;
        } catch (Exception ex) {
            LOGGER.debug(ex);
            return ex.getMessage();
        }
    }
}
