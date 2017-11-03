/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.asi.container;

import org.asi.container.ExtMapDTO;
import org.asi.container.ExtTreeContainer;
import org.asi.container.ExtContainer.DataStructureMode;
import java.io.Serializable;

/**
 *
 * @author manikanta
 */
public class Example {

    public void sampleDemo() {
        ExtTreeContainer<TableDTO> customContainer = new ExtTreeContainer<TableDTO>(TableDTO.class, DataStructureMode.MAP);
        TableDTO parent = getHardcodedData("parent");
        TableDTO child = getHardcodedData("child");
        customContainer.addBean(parent);
        customContainer.addBean(child);
        customContainer.setChildrenAllowed(parent, true);
        customContainer.setChildrenAllowed(child, false);
        customContainer.setParent(child, parent);
    }

    private TableDTO getHardcodedData(String type) {
        TableDTO dto = new TableDTO();
        dto.addBooleanProperties("checkRecord", false);
        if ("parent".equals(type)) {
            dto.setCustomerId("1");
            dto.setCustomerName("Customer 1");
            dto.addStringProperties("2012Sales", "50");
            dto.addStringProperties("2013Sales", "100");
            dto.addStringProperties("2014Sales", "25");
        } else {
            dto.setCustomerId("2");
            dto.setCustomerName("Customer 2");
            dto.addStringProperties("2010Sales", "12");
            dto.addStringProperties("2011Sales", "16");
            dto.addStringProperties("2012Sales", "18");
        }
        return dto;
    }

    public class TableDTO extends ExtMapDTO implements Serializable {

        private String customerId;
        private String customerName;

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

    }
}
