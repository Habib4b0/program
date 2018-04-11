package com.stpl.addons.grid.paged.component;

import java.util.List;

import com.stpl.addons.grid.paged.bean.DataSet;
import com.stpl.addons.grid.paged.bean.Row;
import com.stpl.addons.grid.paged.config.PagedTableConfig;
import com.stpl.addons.grid.paged.service.FetchData;
import com.vaadin.ui.Button;

import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PagedGrid {
        private static final Logger logger = LogManager.getLogger(PagedGrid.class);
	PagedTableConfig tableConfig;
	int count;
	private int pageLength = 10;
	private int pageNumber = 0;
	private DataSet dataSet;
	Grid<Row> grid;
        HorizontalLayout controlLayout;
        private TextField pageNoField;

	public PagedGrid(PagedTableConfig tableConfig) {
		this.tableConfig = tableConfig;
		grid = new Grid<>();
                grid.removeAllColumns();
                int i = 0;
                for (String column : tableConfig.getVisibleColumns()) {
			grid.addColumn(row -> row.getPropertyValue(column)).setCaption(tableConfig.getColumnHeaders().get(i++));
		}
	}

	public void refreshGrid() {
            	count = getTotalCount();
		dataSet = loadData((pageNumber * pageLength), pageLength);
		int i = 0;
		grid.removeAllColumns();
                for (String column :tableConfig.getVisibleColumns()) {
			grid.addColumn(row -> row.getPropertyValue(column)).setCaption(tableConfig.getColumnHeaders().get(i++));
		}
                if(dataSet.getRows()!=null)
		grid.setItems(dataSet.getRows());
	}

	private int getTotalCount() {

		if (tableConfig.getQueryBean().getCountQuery() != null) {
			List<Object[]> result = FetchData.fetchResult(tableConfig.getQueryBean().getCountQuery(), tableConfig.getQueryBean().getCountQueryInputs());
			return result == null || result.isEmpty() ? 0 : Integer.parseInt(String.valueOf(result.get(0)[0]));
		}
		return 0;

	}

	private DataSet loadData(int offset, int limit) {
               List<Object> input= PagedTreeGrid.addRangeInInput(tableConfig.getQueryBean().getDataQueryInputs(), offset, limit);
	       List<Row> rows = FetchData.fetchResultAsRow(tableConfig,tableConfig.getQueryBean().getDataQuery(),input.toArray());
	       return new DataSet(tableConfig.getVisibleColumns().stream().collect(Collectors.toList()), rows);
	}

	public void nextPage() {
		System.out.println("next page->" + (pageNumber + 1));
		if (pageNumber + 1 < getPageCount()) {
                        setPageNoFieldValue( ++pageNumber);

			refreshGrid();
		}
	}

	/**
	 * Moves to previous page, if previous page exists.
	 */
	public void previousPage() {
		if ((pageNumber - 1) >= 0) {
                        setPageNoFieldValue(--pageNumber);
			refreshGrid();
		}
	}

	/**
	 * Sets the page length.
	 */
	public void setPageLength(int newPageLength) {
		if (newPageLength <= 0) {
			throw new IllegalArgumentException("Illegal page length.");
		}
		if (pageLength != newPageLength) {
			pageNumber = 0;
                        setPageNoFieldValue(pageNumber);
			pageLength = newPageLength;
			refreshGrid();
		}
	}

	/**
	 * Sets the current page number.
	 * 
	 * @param newPageNumber
	 *            the desired page
	 */
	public void setPageNumber(int newPageNumber) {
		if (newPageNumber >= 0 && newPageNumber < getPageCount()) {
			pageNumber = newPageNumber;
                        setPageNoFieldValue(pageNumber);
			refreshGrid();
		} else {
			throw new IllegalArgumentException("Illegal page number.");
		}
	}

	int getPageCount() {
		int lastPage = count / pageLength;
		return count % pageLength == 0 ? lastPage : lastPage + 1;
	}

	/**
	 * Gets the current page number.
	 * 
	 * @return current page number
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * Gets the current page length.
	 * 
	 * @return current page length
	 */
	public int getPageLength() {
		return pageLength;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public Grid<Row> getGrid() {
		return grid;
	}

	public void setGrid(Grid<Row> grid) {
		this.grid = grid;
	}
   public HorizontalLayout getControlLayout() {
        if (controlLayout == null) {
            controlLayout = new HorizontalLayout();
            pageNoField = new TextField();
            pageNoField.setWidth("50px");
            setPageNoFieldValue(0);
            controlLayout.addComponent(new Label("Page No:"));
            controlLayout.addComponent(pageNoField);
            controlLayout.addComponent(new Button("<<", e -> this.setPageNumber(0)));
            controlLayout.addComponent(new Button("<", e -> this.previousPage()));
            controlLayout.addComponent(new Button(">", e -> this.nextPage()));
            controlLayout.addComponent(new Button(">>",
                    e -> this.setPageNumber(this.getPageCount() - 1)));
            pageNoField.addBlurListener(e -> setPageNumber((Integer.parseInt(pageNoField.getValue())) -1));
        }
        return controlLayout;
    }
   void setPageNoFieldValue(int pageNo){
        pageNoField.setValue(String.valueOf(pageNo+1));
    }
   public void setData(Object data){
       grid.setData(data);
   }

    public PagedTableConfig getTableConfig() {
        return tableConfig;
    }

    public void setTableConfig(PagedTableConfig tableConfig) {
        this.tableConfig = tableConfig;
    }
    public Set<Row> getValue(){
       return grid.getSelectedItems();
    }
}
