package com.stpl.app.adminconsole.archive.dto;

import java.io.Serializable;

import com.stpl.app.adminconsole.util.ConstantsUtils;

/**
 * The Class ArchiveColumnDTO.
 */
public class ArchiveColumnDTO implements Serializable {

    private static final long serialVersionUID = 6186494746765637961L;

    private String table = ConstantsUtils.EMPTY;

    private String column = ConstantsUtils.EMPTY;

    public String getTable() {
        return table;
    }

    public void setTable(final String table) {
        this.table = table;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(final String column) {
        this.column = column;
    }

}
