package com.stpl.app.util;

import com.stpl.ifs.ui.util.NumericConstants;
import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * DTO class holds the properties of Helper Table.
 *
 * @author
 */
public class HelperDTO implements Serializable, Comparable<HelperDTO> {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -65702198255983161L;
    
    /** The system id. */
    private int systemId;
    
    /** The description. */
    private String description = HelperUtils.EMPTY;
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(GeneralCommonUtils.class);
    
    /**
     * Empty Constructor
     */
    public HelperDTO() {
    	 //Empty Constructor
    }
/**
 * 
 *getter for SystemID
 */
    public int getSystemId() {
        return systemId;
    }
/**
 * 
 * setter for systemId
 */
    public void setSystemId(final int systemId) {
        this.systemId = systemId;
    }

    /**
     * Parameterized constructor to initialize systemId and description.
     *
     * @param systemId the system id
     * @param description the description
     */
    public HelperDTO(final int systemId, final String description) {
        
            this.systemId = systemId;
            this.description = description;
        
    }

    /**
     * Parameterized constructor to initialize description.
     *
     * @param description the description
     */
    public HelperDTO(final String description) {
        this.systemId = 0;
        this.description = description;
    }

    /**
     * Getter for systemId.
     *
     * @return the systemId
     */
    public int getId() {
        return systemId;
    }

    /**
     * Setter for systemId.
     *
     * @param systemId the systemId to set
     */
    public void setId(final int systemId) {
        this.systemId = systemId;
    }

    /**
     * Setter for description field.
     *
     * @param description the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Getter for description.
     *
     * @return the description.
     */
    public String getDescription() {
        return StringUtils.trimToEmpty(description);
    }

    /**
     * method is over ridded to return the description field of the object.
     *
     * @return String description
     */
    @Override
    public String toString() {
        return StringUtils.trimToEmpty(description);
    }

    /**
     * hashCode method is over ridded to calculate Hash Code based on
     * description.
     *
     * @return int - hashcode value
     */
    @Override
    public int hashCode() {
        int result = 1;
        try {
          

            result = NumericConstants.THIRTY_ONE * result
                    + ((description == null) ? 0 : description.hashCode());
            result = NumericConstants.THIRTY_ONE * result + systemId;
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result;
    }

    /**
     * method is over ridded to compare the object based on the description.
     *
     * @param obj object that should be compared.
     * @return boolean based on comparison.
     */
    @Override
    public boolean equals(final Object obj) {
        try {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
          final  HelperDTO other = (HelperDTO) obj;
            if (description == null) {
                if (other.description != null) {
                    return false;
                }
            } else if (!description.equals(other.description)) {
                return false;
            }
            if (systemId != other.systemId) {
                return false;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return true;
    }

    @Override
    public int compareTo(HelperDTO object) {
        return this.description.compareTo(object.getDescription());
    }
    

}
