/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.asi.ui.extfilteringtable.paged.logic;

/**
 * The Class SortByColumn.
 *
 * @author Abhiram
 */
public class SortByColumn {
    
    /**
     * The Enum Type.
     */
    public enum Type {
        
        /** The asc. */
        ASC, 
 /** The desc. */
 DESC
    }

    /** The name. */
    private String name;
    
    /** The type. */
    private Type type;

    /**
     * Instantiates a new sort by column.
     *
     * @param name the name
     * @param type the type
     */
    public SortByColumn(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(Type type) {
        this.type = type;
    }
}
