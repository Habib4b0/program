/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.util;

import com.stpl.addons.tableexport.TemporaryFileDownloadResource;
import com.vaadin.v7.data.util.HierarchicalContainer;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import com.vaadin.ui.UI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.asi.ui.container.ExtTreeContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author Nandhakumar
 */
public abstract class HierarchyTreeExport  implements Serializable{
    

    private static final long serialVersionUID = -2972527330991334117L;
    private static final Logger LOGGER = LoggerFactory.getLogger(HierarchyTreeExport.class.getName());

    public static String EXCEL_MIME_TYPE = "application/vnd.ms-excel";
    public static String CSV_MIME_TYPE = "text/cvs";

    /** The Table to export. */
    private ExtCustomTable table;
    /** The Property ids of the Items in the Table. */
    private LinkedList<Object> propIds;

    /**
     * Whether the Container is a HierarchicalContainer or an extension thereof.
     */
    private boolean hierarchical;

    /** The window to send the export result */
    protected String exportWindow = "_self";

    protected String mimeType;

    public HierarchyTreeExport(final ExtCustomTable table) {
        this.setTable(table);
    }

    public ExtCustomTable getTable() {
        return table;
    }

    public List<Object> getPropIds() {
        return propIds;
    }

    public void setTable(final ExtCustomTable table) {
        this.table = table;
        this.propIds = new LinkedList<Object>(Arrays.asList(table.getVisibleColumns()));
        if (ExtTreeContainer.class.isAssignableFrom(table.getContainerDataSource().getClass())||HierarchicalContainer.class.isAssignableFrom(table.getContainerDataSource().getClass())) {
              setHierarchical(true);
        } else {
            setHierarchical(false);
        }
    }

    public boolean isHierarchical() {
        return hierarchical;
    }

    private void setHierarchical(final boolean hierarchical) {
        this.hierarchical = hierarchical;
    }

    public abstract void convertTable();
    public abstract boolean sendConverted();

    /**
     * Create and export the Table contents as some sort of file type. In the case of conversion to
     * Excel it would be an ".xls" file containing the contents as a report. Only the export()
     * method needs to be called. If the user wishes to manipulate the converted object to export,
     * then convertTable() should be called separately, and, after manipulation, sendConverted().
     */

    public void export() {
        convertTable();
        sendConverted();
    }

    /**
     * Utility method to send the converted object to the user, if it has been written to a
     * temporary File.
     * 
     * Code obtained from: http://vaadin.com/forum/-/message_boards/view_message/159583
     * 
     * @return true, if successful
     */
    public boolean sendConvertedFileToUser(final UI app, final File fileToExport,
            final String exportFileName, final String mimeType) {
        setMimeType(mimeType);
        return sendConvertedFileToUser(app, fileToExport, exportFileName);

    }

    protected boolean sendConvertedFileToUser(final UI app, final File fileToExport,
            final String exportFileName) {
        TemporaryFileDownloadResource resource;
        try {
            resource =
                    new TemporaryFileDownloadResource(app, exportFileName, mimeType, fileToExport);
            app.getPage().open(resource, null, false);
        } catch (final FileNotFoundException e) {
            LOGGER.warn("Sending file to user failed with FileNotFoundException " + e);
            return false;
        }
        return true;
    }

    public String getExportWindow() {
        return this.exportWindow;
    }

    public void setExportWindow(final String exportWindow) {
        this.exportWindow = exportWindow;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public void setMimeType(final String mimeType) {
        this.mimeType = mimeType;
    }

}
