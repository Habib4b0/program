package com.stpl.ifs.util;

import java.io.Serializable;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 * Mainly to load values in Drop Downs
 * @author Shrihariharan
 */
public class HelperDTO implements Serializable, Comparable<HelperDTO> {

    private int id;

    private int refCount;

    private String description = HelperUtils.EMPTY;

    private static final Logger LOGGER = Logger.getLogger(HelperDTO.class);
    
    public HelperDTO() {

    }

    /**
     * Initializing ID and Description
     * @param id
     * @param description 
     */
    public HelperDTO(int id, String description) {
        this.id = id;
        this.description = description;

    }

    /**
     * Initializing Description
     * @param description 
     */
    public HelperDTO(String description) {
        this.id = 0;
        this.description = description;
    }

    /**
     * To make default selected value as -Select One- 
     * @return description
     */
    @Override
    public String toString() {

      //  if ("-Select One-".equals(description)) {
        //    return null;
       // }
        return StringUtils.trimToEmpty(description);
    }

    public String getDescription() {
        return StringUtils.trimToEmpty(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRefCount() {
        return refCount;
    }

    public void setRefCount(int refCount) {
        this.refCount = refCount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;        
        int result = 1;
        try{
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        result = prime * result + id;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
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
            HelperDTO other = (HelperDTO) obj;
            if (description == null) {
                if (other.description != null) {
                    return false;
                }
            } else if (!description.equals(other.description)) {
                return false;
            }
            if (id != other.id) {
                return false;
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return true;
    }
    
    @Override
    public int compareTo(HelperDTO object) {
        return this.description.compareTo(object.getDescription());
    }
}
