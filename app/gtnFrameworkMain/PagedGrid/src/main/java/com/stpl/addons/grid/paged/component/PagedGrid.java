package com.stpl.addons.grid.paged.component;

import java.util.List;

import com.stpl.addons.grid.paged.bean.DataSet;
import com.stpl.addons.grid.paged.bean.Row;
import com.stpl.addons.grid.paged.config.PagedTableConfig;
import com.stpl.addons.grid.paged.service.FetchData;

import com.vaadin.ui.Grid;

public class PagedGrid {
	PagedTableConfig tableConfig;
	int count;
	private int pageLength = 10;
	private int pageNumber = 0;
	private DataSet dataSet;
	Grid<Row> grid;

	public PagedGrid(PagedTableConfig tableConfig) {
		this.tableConfig = tableConfig;
		count = getTotalCount();
		grid = new Grid<>();
		refreshGrid();
	}

	public void refreshGrid() {
		dataSet = loadData((pageNumber * pageLength), pageLength);
		grid.removeAllColumns();
		int i = 0;
		// Set<String> headers = tableConfig.getColumnHeaders() == null ?
		// tableConfig.getVisibleColumns()
		// : tableConfig.getColumnHeaders();
		for (String column : dataSet.getColumns()) {
			grid.addColumn(row -> row.getValue(column)).setCaption(column);
		}

		grid.setItems(dataSet.getRows());
	}

	private int getTotalCount() {

		if (tableConfig.getQueryBean().getCountQuery() != null) {
			List<Object[]> result = FetchData.fetchResult(tableConfig.getQueryBean().getCountQuery());
			return result == null || result.isEmpty() ? 0 : Integer.parseInt(String.valueOf(result.get(0)[0]));
		}
		return 0;

	}

	private DataSet loadData(int offset, int limit) {

		List<Row> rows = FetchData.fetchResultAsRow(tableConfig.getQueryBean().getDataQuery(), offset, limit);

		return new DataSet(tableConfig.getVisibleColumns(), rows);
	}

	public void nextPage() {
		System.out.println("next page->" + (pageNumber + 1));
		if (pageNumber + 1 < getPageCount()) {
			++pageNumber;

			refreshGrid();
		}
	}

	/**
	 * Moves to previous page, if previous page exists.
	 */
	public void previousPage() {
		if ((pageNumber - 1) >= 0) {
			--pageNumber;
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

}
