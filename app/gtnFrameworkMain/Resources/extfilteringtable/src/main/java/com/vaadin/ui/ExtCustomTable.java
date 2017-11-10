/*
 * Copyright 2014 Abhiram.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.ui;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.ContainerOrderedWrapper;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.util.converter.ConverterUtil;
import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.event.DataBoundTransferable;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.ItemClickEvent.ItemClickNotifier;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DragSource;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.DropTarget;
import com.vaadin.event.dd.acceptcriteria.ServerSideCriterion;
import com.vaadin.server.KeyMapper;
import com.vaadin.server.LegacyCommunicationManager;
import com.vaadin.server.LegacyPaint;
import com.vaadin.server.PaintException;
import com.vaadin.server.PaintTarget;
import com.vaadin.server.Resource;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.ui.MultiSelectMode;
import com.vaadin.shared.ui.table.TableConstants;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.DOUBLE_HEADER;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.SINGLE_HEADER;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.TRIPLE_HEADER;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.COLUMN_EXPAND_ICON_STYLE_ARROW_CIRCLE;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.COLUMN_EXPAND_ICON_STYLE_PLUS_MINUS_SQUARE;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.IMAGE_AS_COLUMN_EXPAND_ICON_PLUS_MINUS;

/**
 * <p>
 * <code>CustomTable</code> is used for representing data or components in a
 * pageable and selectable CustomTable.
 * </p>
 *
 * <p>
 * Scalability of the CustomTable is largely dictated by the container. A table
 * does not have a limit for the number of items and is just as fast with
 * hundreds of thousands of items as with just a few. The current GWT
 * implementation with scrolling however limits the number of rows to around
 * 500000, depending on the browser and the pixel height of rows.
 * </p>
 *
 * <p>
 * Components in a CustomTable will not have their caption nor icon rendered.
 * </p>
 *
 * @author Abhiram.
 *
 */
@SuppressWarnings({ "deprecation" })
public class ExtCustomTable extends AbstractSelect implements Action.Container, Container.Ordered, Container.Sortable,
		ItemClickNotifier, DragSource, DropTarget, HasComponents {

	/**
	 * The logger.
	 */
	private transient Logger logger = null;
	private ColumnExpandIconStyle defaultColumnExpandIconStyle = ColumnExpandIconStyle.ICON_IMAGE_PLUS_MINUS;
	private ColumnExpandIconStyle columnExpandIconStyle = defaultColumnExpandIconStyle;

	/**
	 * Modes that CustomTable support as drag sourse.
	 */
	public enum TableDragMode {

		/**
		 * CustomTable does not start drag and drop events. HTM5 style events
		 * started by browser may still happen.
		 */
		NONE,
		/**
		 * CustomTable starts drag with a one row only.
		 */
		ROW,
		/**
		 * CustomTable drags selected rows, if drag starts on a selected rows.
		 * Else it starts like in ROW mode. Note, that in Transferable there
		 * will still be only the row on which the drag started, other dragged
		 * rows need to be checked from the source CustomTable.
		 */
		MULTIROW
	}

	/**
	 * The Constant CELL_KEY.
	 */
	protected static final int CELL_KEY = 0;
	/**
	 * The Constant CELL_HEADER.
	 */
	protected static final int CELL_HEADER = 1;
	/**
	 * The Constant CELL_ICON.
	 */
	protected static final int CELL_ICON = 2;
	/**
	 * The Constant CELL_ITEMID.
	 */
	protected static final int CELL_ITEMID = 3;
	/**
	 * The Constant CELL_GENERATED_ROW.
	 */
	protected static final int CELL_GENERATED_ROW = 4;
	/**
	 * The Constant CELL_FIRSTCOL.
	 */
	protected static final int CELL_FIRSTCOL = 5;

	/**
	 * The Enum Align.
	 */
	public enum Align {

		/**
		 * Left column alignment. <b>This is the default behaviour. </b>
		 */
		LEFT("b"),
		/**
		 * Center column alignment.
		 */
		CENTER("c"),
		/**
		 * Right column alignment.
		 */
		RIGHT("e");
		/**
		 * The alignment.
		 */
		private String alignment;

		/**
		 * Instantiates a new align.
		 *
		 * @param alignment
		 *            the alignment
		 */
		private Align(String alignment) {
			this.alignment = alignment;
		}

		/**
		 * To string.
		 *
		 * @return the string
		 */
		@Override
		public String toString() {
			return alignment;
		}

		/**
		 * Convert string to align.
		 *
		 * @param string
		 *            the string
		 * @return the align
		 */
		public Align convertStringToAlign(String string) {
			if (string == null) {
				return null;
			}
			if (string.equals("b")) {
				return Align.LEFT;
			} else if (string.equals("c")) {
				return Align.CENTER;
			} else if (string.equals("e")) {
				return Align.RIGHT;
			} else {
				return null;
			}
		}
	}

	/**
	 * The Constant ALIGN_LEFT.
	 *
	 * @deprecated As of 7.0, use {@link Align#LEFT} instead
	 */
	@Deprecated
	public static final Align ALIGN_LEFT = Align.LEFT;
	/**
	 * The Constant ALIGN_CENTER.
	 *
	 * @deprecated As of 7.0, use {@link Align#CENTER} instead
	 */
	@Deprecated
	public static final Align ALIGN_CENTER = Align.CENTER;
	/**
	 * The Constant ALIGN_RIGHT.
	 *
	 * @deprecated As of 7.0, use {@link Align#RIGHT} instead
	 */
	@Deprecated
	public static final Align ALIGN_RIGHT = Align.RIGHT;

	/**
	 * The Enum ColumnHeaderMode.
	 */
	public enum ColumnHeaderMode {

		/**
		 * Column headers are hidden.
		 */
		HIDDEN,
		/**
		 * Property ID:s are used as column headers.
		 */
		ID,
		/**
		 * Column headers are explicitly specified with
		 * {@link #setColumnHeaders(String[])}.
		 */
		EXPLICIT,
		/**
		 * Column headers are explicitly specified with
		 * {@link #setColumnHeaders(String[])}. If a header is not specified for
		 * a given property, its property id is used instead.
		 * <p>
		 * <b>This is the default behavior. </b>
		 */
		EXPLICIT_DEFAULTS_ID
	}

	/**
	 * The Constant COLUMN_HEADER_MODE_HIDDEN.
	 *
	 * @deprecated As of 7.0, use {@link ColumnHeaderMode#HIDDEN} instead
	 */
	@Deprecated
	public static final ColumnHeaderMode COLUMN_HEADER_MODE_HIDDEN = ColumnHeaderMode.HIDDEN;
	/**
	 * The Constant COLUMN_HEADER_MODE_ID.
	 *
	 * @deprecated As of 7.0, use {@link ColumnHeaderMode#ID} instead
	 */
	@Deprecated
	public static final ColumnHeaderMode COLUMN_HEADER_MODE_ID = ColumnHeaderMode.ID;
	/**
	 * The Constant COLUMN_HEADER_MODE_EXPLICIT.
	 *
	 * @deprecated As of 7.0, use {@link ColumnHeaderMode#EXPLICIT} instead
	 */
	@Deprecated
	public static final ColumnHeaderMode COLUMN_HEADER_MODE_EXPLICIT = ColumnHeaderMode.EXPLICIT;
	/**
	 * The Constant COLUMN_HEADER_MODE_EXPLICIT_DEFAULTS_ID.
	 *
	 * @deprecated As of 7.0, use {@link ColumnHeaderMode#EXPLICIT_DEFAULTS_ID}
	 *             instead
	 */
	@Deprecated
	public static final ColumnHeaderMode COLUMN_HEADER_MODE_EXPLICIT_DEFAULTS_ID = ColumnHeaderMode.EXPLICIT_DEFAULTS_ID;

	/**
	 * The Enum RowHeaderMode.
	 */
	public enum RowHeaderMode {

		/**
		 * Row caption mode: The row headers are hidden. <b>This is the default
		 * mode. </b>
		 */
		HIDDEN(null),
		/**
		 * Row caption mode: Items Id-objects toString is used as row caption.
		 */
		ID(ItemCaptionMode.ID),
		/**
		 * Row caption mode: Item-objects toString is used as row caption.
		 */
		ITEM(ItemCaptionMode.ITEM),
		/**
		 * Row caption mode: Index of the item is used as item caption. The
		 * index mode can only be used with the containers implementing the
		 * {@link com.vaadin.data.Container.Indexed} interface.
		 */
		INDEX(ItemCaptionMode.INDEX),
		/**
		 * Row caption mode: Item captions are explicitly specified, but if the
		 * caption is missing, the item id objects <code>toString()</code> is
		 * used instead.
		 */
		EXPLICIT_DEFAULTS_ID(ItemCaptionMode.EXPLICIT_DEFAULTS_ID),
		/**
		 * Row caption mode: Item captions are explicitly specified.
		 */
		EXPLICIT(ItemCaptionMode.EXPLICIT),
		/**
		 * Row caption mode: Only icons are shown, the captions are hidden.
		 */
		ICON_ONLY(ItemCaptionMode.ICON_ONLY),
		/**
		 * Row caption mode: Item captions are read from property specified with
		 * {@link #setItemCaptionPropertyId(Object)}.
		 */
		PROPERTY(ItemCaptionMode.PROPERTY);
		/**
		 * The mode.
		 */
		ItemCaptionMode mode;

		/**
		 * Instantiates a new row header mode.
		 *
		 * @param mode
		 *            the mode
		 */
		private RowHeaderMode(ItemCaptionMode mode) {
			this.mode = mode;
		}

		/**
		 * Gets the item caption mode.
		 *
		 * @return the item caption mode
		 */
		public ItemCaptionMode getItemCaptionMode() {
			return mode;
		}
	}

	/**
	 * The Constant ROW_HEADER_MODE_HIDDEN.
	 *
	 * @deprecated As of 7.0, use {@link RowHeaderMode#HIDDEN} instead
	 */
	@Deprecated
	public static final RowHeaderMode ROW_HEADER_MODE_HIDDEN = RowHeaderMode.HIDDEN;
	/**
	 * The Constant ROW_HEADER_MODE_ID.
	 *
	 * @deprecated As of 7.0, use {@link RowHeaderMode#ID} instead
	 */
	@Deprecated
	public static final RowHeaderMode ROW_HEADER_MODE_ID = RowHeaderMode.ID;
	/**
	 * The Constant ROW_HEADER_MODE_ITEM.
	 *
	 * @deprecated As of 7.0, use {@link RowHeaderMode#ITEM} instead
	 */
	@Deprecated
	public static final RowHeaderMode ROW_HEADER_MODE_ITEM = RowHeaderMode.ITEM;
	/**
	 * The Constant ROW_HEADER_MODE_INDEX.
	 *
	 * @deprecated As of 7.0, use {@link RowHeaderMode#INDEX} instead
	 */
	@Deprecated
	public static final RowHeaderMode ROW_HEADER_MODE_INDEX = RowHeaderMode.INDEX;
	/**
	 * The Constant ROW_HEADER_MODE_EXPLICIT_DEFAULTS_ID.
	 *
	 * @deprecated As of 7.0, use {@link RowHeaderMode#EXPLICIT_DEFAULTS_ID}
	 *             instead
	 */
	@Deprecated
	public static final RowHeaderMode ROW_HEADER_MODE_EXPLICIT_DEFAULTS_ID = RowHeaderMode.EXPLICIT_DEFAULTS_ID;
	/**
	 * The Constant ROW_HEADER_MODE_EXPLICIT.
	 *
	 * @deprecated As of 7.0, use {@link RowHeaderMode#EXPLICIT} instead
	 */
	@Deprecated
	public static final RowHeaderMode ROW_HEADER_MODE_EXPLICIT = RowHeaderMode.EXPLICIT;
	/**
	 * The Constant ROW_HEADER_MODE_ICON_ONLY.
	 *
	 * @deprecated As of 7.0, use {@link RowHeaderMode#ICON_ONLY} instead
	 */
	@Deprecated
	public static final RowHeaderMode ROW_HEADER_MODE_ICON_ONLY = RowHeaderMode.ICON_ONLY;
	/**
	 * The Constant ROW_HEADER_MODE_PROPERTY.
	 *
	 * @deprecated As of 7.0, use {@link RowHeaderMode#PROPERTY} instead
	 */
	@Deprecated
	public static final RowHeaderMode ROW_HEADER_MODE_PROPERTY = RowHeaderMode.PROPERTY;
	/**
	 * The default rate that table caches rows for smooth scrolling.
	 */
	private static final double CACHE_RATE_DEFAULT = 2;
	/**
	 * The Constant ROW_HEADER_COLUMN_KEY.
	 */
	private static final String ROW_HEADER_COLUMN_KEY = "0";
	/**
	 * The Constant ROW_HEADER_FAKE_PROPERTY_ID.
	 */
	private static final Object ROW_HEADER_FAKE_PROPERTY_ID = new UniqueSerializable() {
	};

	/* Private table extensions to Select */
	/**
	 * True if column collapsing is allowed.
	 */
	private boolean columnCollapsingAllowed = false;
	/**
	 * True if reordering of columns is allowed on the client side.
	 */
	private boolean columnReorderingAllowed = false;
	/**
	 * Keymapper for column ids.
	 */
	protected final KeyMapper<Object> columnIdMap = new KeyMapper<Object>();
	/**
	 * Holds visible column propertyIds - in order.
	 */
	private LinkedList<Object> visibleColumns = new LinkedList<Object>();
	/**
	 * Holds noncollapsible columns.
	 */
	private HashSet<Object> noncollapsibleColumns = new HashSet<Object>();
	/**
	 * Holds propertyIds of currently collapsed columns.
	 */
	private final HashSet<Object> collapsedColumns = new HashSet<Object>();
	/**
	 * Holds headers for visible columns (by propertyId).
	 */
	private final HashMap<Object, String> columnHeaders = new HashMap<Object, String>();
	/**
	 * Holds footers for visible columns (by propertyId).
	 */
	private final HashMap<Object, String> columnFooters = new HashMap<Object, String>();
	/**
	 * Holds icons for visible columns (by propertyId).
	 */
	private final HashMap<Object, Resource> columnIcons = new HashMap<Object, Resource>();
	/**
	 * Holds alignments for visible columns (by propertyId).
	 */
	private HashMap<Object, Align> columnAlignments = new HashMap<Object, Align>();
	/**
	 * Holds column widths in pixels for visible columns (by propertyId).
	 */
	private final HashMap<Object, Integer> columnWidths = new HashMap<Object, Integer>();
	/**
	 * Holds column expand rations for visible columns (by propertyId).
	 */
	private final HashMap<Object, Float> columnExpandRatios = new HashMap<Object, Float>();
	/**
	 * Holds column generators.
	 */
	private final HashMap<Object, ColumnGenerator> columnGenerators = new LinkedHashMap<Object, ColumnGenerator>();
	/**
	 * Holds value of property pageLength. 0 disables paging.
	 */
	private int pageLength = 15;
	/**
	 * Id the first item on the current page.
	 */
	private Object currentPageFirstItemId = null;
	/**
	 * Index of the first item on the current page.
	 */
	private int currentPageFirstItemIndex = 0;
	/**
	 * Index of the "first" item on the last page if a user has used
	 * setCurrentPageFirstItemIndex to scroll down. -1 if not set.
	 */
	private int currentPageFirstItemIndexOnLastPage = -1;
	/**
	 * Holds value of property selectable.
	 */
	private boolean selectable = false;
	/**
	 * Holds value of property columnHeaderMode.
	 */
	private ColumnHeaderMode columnHeaderMode = ColumnHeaderMode.EXPLICIT_DEFAULTS_ID;
	/**
	 * Holds value of property rowHeaderMode.
	 */
	private RowHeaderMode rowHeaderMode = RowHeaderMode.EXPLICIT_DEFAULTS_ID;
	/**
	 * Should the CustomTable footer be visible?.
	 */
	private boolean columnFootersVisible = false;
	/**
	 * Page contents buffer used in buffered mode.
	 */
	private Object[][] pageBuffer = null;
	/**
	 * Set of properties listened - the list is kept to release the listeners
	 * later.
	 */
	private HashSet<Property<?>> listenedProperties = null;
	/**
	 * Set of visible components - the is used for needsRepaint calculation.
	 */
	protected HashSet<Component> visibleComponents = null;
	/**
	 * List of action handlers.
	 */
	private LinkedList<Handler> actionHandlers = null;
	/**
	 * Action mapper.
	 */
	private KeyMapper<Action> actionMapper = null;
	/**
	 * CustomTable cell editor factory.
	 */
	private TableFieldFactory fieldFactory = DefaultFieldFactory.get();
	/**
	 * Is table editable.
	 */
	private boolean editable = false;
	/**
	 * Current sorting direction.
	 */
	private boolean sortAscending = true;
	/**
	 * Currently table is sorted on this propertyId.
	 */
	private Object sortContainerPropertyId = null;
	/**
	 * Is table sorting by the user enabled.
	 */
	private boolean sortEnabled = true;
	/**
	 * Number of rows explicitly requested by the client to be painted on next
	 * paint. This is -1 if no request by the client is made. Painting the
	 * component will automatically reset this to -1.
	 */
	private int reqRowsToPaint = -1;
	/**
	 * Index of the first rows explicitly requested by the client to be painted.
	 * This is -1 if no request by the client is made. Painting the component
	 * will automatically reset this to -1.
	 */
	private int reqFirstRowToPaint = -1;
	/**
	 * The first to be rendered in client.
	 */
	private int firstToBeRenderedInClient = -1;
	/**
	 * The last to be rendered in client.
	 */
	private int lastToBeRenderedInClient = -1;
	/**
	 * The is content refreshes enabled.
	 */
	private boolean isContentRefreshesEnabled = true;
	/**
	 * The page buffer first index.
	 */
	private int pageBufferFirstIndex;
	/**
	 * The container change to be rendered.
	 */
	private boolean containerChangeToBeRendered = false;
	/**
	 * CustomTable cell specific style generator.
	 */
	private CellStyleGenerator cellStyleGenerator = null;
	/**
	 * CustomTable cell specific tooltip generator.
	 */
	private ItemDescriptionGenerator itemDescriptionGenerator;

	/*
	 * EXPERIMENTAL feature: will tell the client to re-calculate column widths
	 * if set to true. Currently no setter: extend to enable.
	 */
	/**
	 * The always recalculate column widths.
	 */
	protected boolean alwaysRecalculateColumnWidths = false;
	/**
	 * The cache rate.
	 */
	private double cacheRate = CACHE_RATE_DEFAULT;
	/**
	 * The drag mode.
	 */
	private TableDragMode dragMode = TableDragMode.NONE;
	/**
	 * The drop handler.
	 */
	private DropHandler dropHandler;
	/**
	 * The multi select mode.
	 */
	private MultiSelectMode multiSelectMode = MultiSelectMode.DEFAULT;
	/**
	 * The row cache invalidated.
	 */
	private boolean rowCacheInvalidated;
	/**
	 * The row generator.
	 */
	private RowGenerator rowGenerator = null;
	/**
	 * The associated properties.
	 */
	private final Map<Field<?>, Property<?>> associatedProperties = new HashMap<Field<?>, Property<?>>();
	/**
	 * The painted.
	 */
	private boolean painted = false;
	/**
	 * The property value converters.
	 */
	private HashMap<Object, Converter<String, Object>> propertyValueConverters = new HashMap<Object, Converter<String, Object>>();
	/**
	 * Set to true if the client-side should be informed that the key mapper has
	 * been reset so it can avoid sending back references to keys that are no
	 * longer present.
	 */
	private boolean keyMapperReset;
	/**
	 * The exceptions during cache population.
	 */
	private List<Throwable> exceptionsDuringCachePopulation = new ArrayList<Throwable>();
	/**
	 * The is being painted.
	 */
	private boolean isBeingPainted;
	/**
	 * The is construct.
	 */
	private boolean isConstruct = false;
	/**
	 * The refresh.
	 */
	boolean refresh = true;

	/* Added for double Header */
	/**
	 * True if column collapsing is allowed.
	 */
	private boolean doubleColumnCollapsingAllowed = false;
	/**
	 * True if reordering of columns is allowed on the client side.
	 */
	private boolean doubleColumnReorderingAllowed = false;
	/**
	 * Keymapper for column ids.
	 */
	protected final KeyMapper<Object> doubleColumnIdMap = new KeyMapper<Object>();
	/**
	 * Holds double header visible column propertyIds - in order.
	 */
	private LinkedList<Object> doubleVisibleColumns = new LinkedList<Object>();
	/**
	 * Holds noncollapsible columns.
	 */
	private HashSet<Object> doubleNonCollapsibleColumns = new HashSet<Object>();
	/**
	 * Holds propertyIds of currently collapsed columns.
	 */
	private final HashSet<Object> doubleCollapsedColumns = new HashSet<Object>();
	/**
	 * Holds headers for visible columns (by propertyId).
	 */
	private final HashMap<Object, String> doubleColumnHeaders = new HashMap<Object, String>();
	/**
	 * The double map vis col.
	 */
	private final HashMap<String, String[]> doubleMapVisCol = new HashMap<String, String[]>();
	/**
	 * Should the double header be visible?.
	 */
	private boolean doubleHeadersVisible = false;
	/**
	 * Holds icons for visible columns (by propertyId).
	 */
	private final HashMap<Object, Resource> doubleColumnIcons = new HashMap<Object, Resource>();
	/**
	 * Holds alignments for visible columns (by propertyId).
	 */
	private HashMap<Object, Align> doubleColumnAlignments = new HashMap<Object, Align>();
	/**
	 * Holds column widths in pixels for visible columns (by propertyId).
	 */
	private final HashMap<Object, Integer> doubleColumnWidths = new HashMap<Object, Integer>();
	/**
	 * Holds column expand rations for visible columns (by propertyId).
	 */
	private final HashMap<Object, Float> doubleColumnExpandRatios = new HashMap<Object, Float>();
	// Added for checkbox in Double Header
	/**
	 * The doublecolumncheckboxes.
	 */
	private final HashMap<Object, Boolean> doublecolumncheckboxes = new HashMap<Object, Boolean>();
	/**
	 * The doublecolumncheckboxesdisable.
	 */
	private final HashSet<Object> doublecolumncheckboxesdisable = new HashSet<Object>();
	// Added for checkbox in Single Header
	/**
	 * The columncheckboxes.
	 */
	private final HashMap<Object, Boolean> columncheckboxes = new HashMap<Object, Boolean>();
	/**
	 * The columncheckboxesdisable.
	 */
	private final HashSet<Object> columncheckboxesdisable = new HashSet<Object>();
	// Added for radio buttons in Single Header
	// Map<String, RadioButton> radioVal = new HashMap<String, RadioButton>();
	/**
	 * The map single radio.
	 */
	Map<String, String[]> mapSingleRadio = new HashMap<String, String[]>();
	/**
	 * The single radio val.
	 */
	Map<String, String> singleRadioVal = new HashMap<String, String>();
	/**
	 * The singlecolumnradioes.
	 */
	private final HashMap<String, String> singlecolumnradioes = new HashMap<String, String>();
	/**
	 * The singlecolumnradiodisable.
	 */
	private final HashSet<Object> singlecolumnradiodisable = new HashSet<Object>();
	/**
	 * The singlecolumnradionames.
	 */
	private final HashSet<String> singlecolumnradionames = new HashSet<String>();
	// Added for radio buttons in Double Header
	/**
	 * The map double radio.
	 */
	Map<String, String[]> mapDoubleRadio = new HashMap<String, String[]>();
	/**
	 * The double radio val.
	 */
	Map<String, String> doubleRadioVal = new HashMap<String, String>();
	/**
	 * The doublecolumnradioes.
	 */
	private final HashMap<String, String> doublecolumnradioes = new HashMap<String, String>();
	/**
	 * The doublecolumnradiodisable.
	 */
	private final HashSet<Object> doublecolumnradiodisable = new HashSet<Object>();
	/**
	 * The doublecolumnradionames.
	 */
	private final HashSet<String> doublecolumnradionames = new HashSet<String>();
	// Added for expand icons in Double Header
	/**
	 * The doublecolumnexpandicons.
	 */
	private final HashMap<String, Boolean> doublecolumnexpandicons = new HashMap<String, Boolean>();
	// Added for expand icons in Single Header
	/**
	 * The columnexpandicons.
	 */
	private final HashMap<String, Boolean> columnexpandicons = new HashMap<String, Boolean>();

	/* Added for Column Freezing */
	/**
	 * The left table.
	 */
	com.vaadin.ui.ExtCustomTable leftTable;
	/**
	 * The right table.
	 */
	com.vaadin.ui.ExtCustomTable rightTable;
	// Added for TripleHeader
	/**
	 * True if column collapsing is allowed.
	 */
	private boolean tripleColumnCollapsingAllowed = false;
	/**
	 * True if reordering of columns is allowed on the client side.
	 */
	private boolean tripleColumnReorderingAllowed = false;
	/**
	 * Keymapper for column ids.
	 */
	protected final KeyMapper<Object> tripleColumnIdMap = new KeyMapper<Object>();
	/**
	 * Holds double header visible column propertyIds - in order.
	 */
	private LinkedList<Object> tripleVisibleColumns = new LinkedList<Object>();
	/**
	 * Holds noncollapsible columns.
	 */
	private HashSet<Object> tripleNonCollapsibleColumns = new HashSet<Object>();
	/**
	 * Holds propertyIds of currently collapsed columns.
	 */
	private final HashSet<Object> tripleCollapsedColumns = new HashSet<Object>();
	/**
	 * Holds headers for visible columns (by propertyId).
	 */
	private final HashMap<Object, String> tripleColumnHeaders = new HashMap<Object, String>();
	/**
	 * The triple map vis col.
	 */
	private final HashMap<String, String[]> tripleMapVisCol = new HashMap<String, String[]>();
	/**
	 * Should the double header be visible?.
	 */
	private boolean tripleHeadersVisible = false;
	/**
	 * Holds icons for visible columns (by propertyId).
	 */
	private final HashMap<Object, Resource> tripleColumnIcons = new HashMap<Object, Resource>();
	/**
	 * Holds alignments for visible columns (by propertyId).
	 */
	private HashMap<Object, Align> tripleColumnAlignments = new HashMap<Object, Align>();
	/**
	 * Holds column widths in pixels for visible columns (by propertyId).
	 */
	private final HashMap<Object, Integer> tripleColumnWidths = new HashMap<Object, Integer>();
	/**
	 * Holds column expand rations for visible columns (by propertyId).
	 */
	private final HashMap<Object, Float> tripleColumnExpandRatios = new HashMap<Object, Float>();
	// Added for checkbox in Triple Header
	/**
	 * The triple column checkboxes.
	 */
	private final HashMap<Object, Boolean> tripleColumnCheckboxes = new HashMap<Object, Boolean>();
	/**
	 * The triple column checkboxes disable.
	 */
	private final HashSet<Object> tripleColumnCheckboxesDisable = new HashSet<Object>();
	// Added for radio buttons in Triple Header
	/**
	 * The map triple radio.
	 */
	Map<String, String[]> mapTripleRadio = new HashMap<String, String[]>();
	/**
	 * The triple radio val.
	 */
	Map<String, String> tripleRadioVal = new HashMap<String, String>();
	/**
	 * The triple column radioes.
	 */
	private final HashMap<String, String> tripleColumnRadioes = new HashMap<String, String>();
	/**
	 * The triple column radio disable.
	 */
	private final HashSet<Object> tripleColumnRadioDisable = new HashSet<Object>();
	/**
	 * The triple column radio names.
	 */
	private final HashSet<String> tripleColumnRadioNames = new HashSet<String>();
	// Added for expand icons in Triple Header
	/**
	 * The triple column expand icons.
	 */
	private final HashMap<String, Boolean> tripleColumnExpandIcons = new HashMap<String, Boolean>();
	/**
	 * The radio button sinks.
	 */
	private boolean radioButtonSinks = false;

	/* ExtCustomTable constructors */
	/**
	 * Creates a new empty table.
	 */
	public ExtCustomTable() {
		setRowHeaderMode(ROW_HEADER_MODE_HIDDEN);
		setColumnExpandIconStyle(defaultColumnExpandIconStyle);
	}

	/**
	 * Creates a new empty table with caption.
	 *
	 * @param caption
	 *            the caption
	 */
	public ExtCustomTable(String caption) {
		this();
		setCaption(caption);
	}

	/**
	 * Creates a new table with caption and connect it to a Container.
	 *
	 * @param caption
	 *            the caption
	 * @param dataSource
	 *            the data source
	 */
	public ExtCustomTable(String caption, Container dataSource) {
		this();
		setCaption(caption);
		setContainerDataSource(dataSource);
	}

	/* CustomTable functionality */
	/**
	 * Gets the array of visible column id:s, including generated columns.
	 *
	 * <p>
	 * The columns are show in the order of their appearance in this array.
	 * </p>
	 *
	 * @return an array of currently visible propertyIds and generated column
	 *         ids.
	 */
	public Object[] getVisibleColumns() {
		if (visibleColumns == null) {
			return null;
		}
		return visibleColumns.toArray();
	}

	/**
	 * Sets the array of visible column property id:s.
	 *
	 * <p>
	 * The columns are show in the order of their appearance in this array.
	 * </p>
	 *
	 * @param visibleColumns
	 *            the Array of shown property id:s.
	 */
	public void setVisibleColumns(Object... visibleColumns) {

		// Visible columns must exist
		if (visibleColumns == null) {
			throw new NullPointerException("Can not set visible columns to null value");
		}

		final LinkedList<Object> newVC = new LinkedList<Object>();

		// Checks that the new visible columns contains no nulls, properties
		// exist and that there are no duplicates before adding them to newVC.
		final Collection<?> properties = getContainerPropertyIds();
		for (int i = 0; i < visibleColumns.length; i++) {
			if (visibleColumns[i] == null) {
				throw new NullPointerException("Ids must be non-nulls");
			} else if (!properties.contains(visibleColumns[i]) && !columnGenerators.containsKey(visibleColumns[i])) {
				throw new IllegalArgumentException(
						"Ids must exist in the Container or as a generated column, missing id: " + visibleColumns[i]);
			} else if (newVC.contains(visibleColumns[i])) {
				throw new IllegalArgumentException("Ids must be unique, duplicate id: " + visibleColumns[i]);
			} else {
				newVC.add(visibleColumns[i]);
			}
		}

		// Removes alignments, icons and headers from hidden columns
		if (this.visibleColumns != null) {
			boolean disabledHere = disableContentRefreshing();
			try {
				for (final Iterator<Object> i = this.visibleColumns.iterator(); i.hasNext();) {
					final Object col = i.next();
					if (!newVC.contains(col)) {
						setColumnHeader(col, null);
						setColumnAlignment(col, (Align) null);
						setColumnIcon(col, null);
					}
				}
			} finally {
				if (disabledHere) {
					enableContentRefreshing(false);
				}
			}
		}

		this.visibleColumns = newVC;

		// Assures visual refresh
		refreshRowCache();
	}

	/**
	 * Gets the headers of the columns.
	 *
	 * <p>
	 * The headers match the property id:s given my the set visible column
	 * headers. The table must be set in either
	 * {@link #COLUMN_HEADER_MODE_EXPLICIT} or
	 * {@link #COLUMN_HEADER_MODE_EXPLICIT_DEFAULTS_ID} mode to show the
	 * headers. In the defaults mode any nulls in the headers array are replaced
	 * with id.toString().
	 * </p>
	 *
	 * @return the Array of column headers.
	 */
	public String[] getColumnHeaders() {
		if (columnHeaders == null) {
			return null;
		}
		final String[] headers = new String[visibleColumns.size()];
		int i = 0;
		for (final Iterator<Object> it = visibleColumns.iterator(); it.hasNext(); i++) {
			headers[i] = getColumnHeader(it.next());
		}
		return headers;
	}

	/**
	 * Sets the headers of the columns.
	 *
	 * <p>
	 * The headers match the property id:s given my the set visible column
	 * headers. The table must be set in either
	 * {@link #COLUMN_HEADER_MODE_EXPLICIT} or
	 * {@link #COLUMN_HEADER_MODE_EXPLICIT_DEFAULTS_ID} mode to show the
	 * headers. In the defaults mode any nulls in the headers array are replaced
	 * with id.toString() outputs when rendering.
	 * </p>
	 *
	 * @param columnHeaders
	 *            the Array of column headers that match the
	 *            {@link #getVisibleColumns()} method.
	 */
	public void setColumnHeaders(String... columnHeaders) {
		setColumnHeaders(columnHeaders, true);
	}

	/**
	 * Sets the headers of the columns.
	 *
	 * <p>
	 * The headers match the property id:s given my the set visible column
	 * headers. The table must be set in either
	 * {@link #COLUMN_HEADER_MODE_EXPLICIT} or
	 * {@link #COLUMN_HEADER_MODE_EXPLICIT_DEFAULTS_ID} mode to show the
	 * headers. In the defaults mode any nulls in the headers array are replaced
	 * with id.toString() outputs when rendering.
	 * </p>
	 *
	 * @param columnHeaders
	 *            the Array of column headers that match the
	 *            {@link #getVisibleColumns()} method.
	 */
	public void setColumnHeaders(String[] columnHeaders, boolean refresh) {

		if (columnHeaders.length != visibleColumns.size()) {
			throw new IllegalArgumentException(
					"The length of the headers array must match the number of visible columns");
		}

		this.columnHeaders.clear();
		int i = 0;
		for (final Iterator<Object> it = visibleColumns.iterator(); it.hasNext() && i < columnHeaders.length; i++) {
			this.columnHeaders.put(it.next(), columnHeaders[i]);
		}
		if (refresh) {
			markAsDirty();
		}

	}

	/**
	 * Gets the icons of the columns.
	 *
	 * <p>
	 * The icons in headers match the property id:s given my the set visible
	 * column headers. The table must be set in either
	 * {@link #COLUMN_HEADER_MODE_EXPLICIT} or
	 * {@link #COLUMN_HEADER_MODE_EXPLICIT_DEFAULTS_ID} mode to show the headers
	 * with icons.
	 * </p>
	 *
	 * @return the Array of icons that match the {@link #getVisibleColumns()}.
	 */
	public Resource[] getColumnIcons() {
		if (columnIcons == null) {
			return null;
		}
		final Resource[] icons = new Resource[visibleColumns.size()];
		int i = 0;
		for (final Iterator<Object> it = visibleColumns.iterator(); it.hasNext(); i++) {
			icons[i] = columnIcons.get(it.next());
		}

		return icons;
	}

	/**
	 * Sets the icons of the columns.
	 *
	 * <p>
	 * The icons in headers match the property id:s given my the set visible
	 * column headers. The table must be set in either
	 * {@link #COLUMN_HEADER_MODE_EXPLICIT} or
	 * {@link #COLUMN_HEADER_MODE_EXPLICIT_DEFAULTS_ID} mode to show the headers
	 * with icons.
	 * </p>
	 *
	 * @param columnIcons
	 *            the Array of icons that match the {@link #getVisibleColumns()}
	 *            .
	 */
	public void setColumnIcons(Resource... columnIcons) {

		if (columnIcons.length != visibleColumns.size()) {
			throw new IllegalArgumentException(
					"The length of the icons array must match the number of visible columns");
		}

		this.columnIcons.clear();
		int i = 0;
		for (final Iterator<Object> it = visibleColumns.iterator(); it.hasNext() && i < columnIcons.length; i++) {
			this.columnIcons.put(it.next(), columnIcons[i]);
		}

		markAsDirty();
	}

	/**
	 * Gets the array of column alignments.
	 *
	 * <p>
	 * The items in the array must match the properties identified by
	 * {@link #getVisibleColumns()}. The possible values for the alignments
	 * include:
	 * <ul>
	 * <li>{@link Align#LEFT}: Left alignment</li>
	 * <li>{@link Align#CENTER}: Centered</li>
	 * <li>{@link Align#RIGHT}: Right alignment</li>
	 * </ul>
	 * The alignments default to {@link Align#LEFT}: any null values are
	 * rendered as align lefts.
	 * </p>
	 *
	 * @return the Column alignments array.
	 */
	public Align[] getColumnAlignments() {
		if (columnAlignments == null) {
			return null;
		}
		final Align[] alignments = new Align[visibleColumns.size()];
		int i = 0;
		for (final Iterator<Object> it = visibleColumns.iterator(); it.hasNext(); i++) {
			alignments[i] = getColumnAlignment(it.next());
		}

		return alignments;
	}

	/**
	 * Sets the column alignments.
	 *
	 * <p>
	 * The amount of items in the array must match the amount of properties
	 * identified by {@link #getVisibleColumns()}. The possible values for the
	 * alignments include:
	 * <ul>
	 * <li>{@link Align#LEFT}: Left alignment</li>
	 * <li>{@link Align#CENTER}: Centered</li>
	 * <li>{@link Align#RIGHT}: Right alignment</li>
	 * </ul>
	 * The alignments default to {@link Align#LEFT}
	 * </p>
	 *
	 * @param columnAlignments
	 *            the Column alignments array.
	 */
	public void setColumnAlignments(Align... columnAlignments) {

		if (columnAlignments.length != visibleColumns.size()) {
			throw new IllegalArgumentException(
					"The length of the alignments array must match the number of visible columns");
		}

		// Resets the alignments
		final HashMap<Object, Align> newCA = new HashMap<Object, Align>();
		int i = 0;
		for (final Iterator<Object> it = visibleColumns.iterator(); it.hasNext() && i < columnAlignments.length; i++) {
			newCA.put(it.next(), columnAlignments[i]);
		}
		this.columnAlignments = newCA;

		// Assures the visual refresh. No need to reset the page buffer before
		// as the content has not changed, only the alignments.
		refreshRenderedCells();
	}

	/**
	 * Sets columns width (in pixels). Theme may not necessary respect very
	 * small or very big values. Setting width to -1 (default) means that theme
	 * will make decision of width.
	 *
	 * <p>
	 * Column can either have a fixed width or expand ratio. The latter one set
	 * is used. See @link {@link #setColumnExpandRatio(Object, float)}.
	 *
	 * @param propertyId
	 *            colunmns property id
	 * @param width
	 *            width to be reserved for colunmns content @since 4.0.3
	 */
	public void setColumnWidth(Object propertyId, int width) {
		setColumnWidth(propertyId, width, true);
	}

	/**
	 * Sets columns width (in pixels). Theme may not necessary respect very
	 * small or very big values. Setting width to -1 (default) means that theme
	 * will make decision of width.
	 *
	 * <p>
	 * Column can either have a fixed width or expand ratio. The latter one set
	 * is used. See @link {@link #setColumnExpandRatio(Object, float)}.
	 *
	 * @param propertyId
	 *            colunmns property id
	 * @param width
	 *            width to be reserved for colunmns content @since 4.0.3
	 */
	public void setColumnWidth(Object propertyId, int width, boolean refresh) {
		if (propertyId == null) {
			// Since propertyId is null, this is the row header. Use the magic
			// id to store the width of the row header.
			propertyId = ROW_HEADER_FAKE_PROPERTY_ID;
		}

		// Setting column width should remove any expand ratios as well
		columnExpandRatios.remove(propertyId);

		if (width < 0) {
			columnWidths.remove(propertyId);
		} else {
			columnWidths.put(propertyId, width);
		}
		if (refresh) {
			markAsDirty();
		}
	}

	/**
	 * Sets the column expand ratio for given column.
	 * <p>
	 * Expand ratios can be defined to customize the way how excess space is
	 * divided among columns. CustomTable can have excess space if it has its
	 * width defined and there is horizontally more space than columns consume
	 * naturally. Excess space is the space that is not used by columns with
	 * explicit width (see {@link #setColumnWidth(Object, int)}) or with natural
	 * width (no width nor expand ratio).
	 *
	 * <p>
	 * By default (without expand ratios) the excess space is divided
	 * proportionally to columns natural widths.
	 *
	 * <p>
	 * Only expand ratios of visible columns are used in final calculations.
	 *
	 * <p>
	 * Column can either have a fixed width or expand ratio. The latter one set
	 * is used.
	 *
	 * <p>
	 * A column with expand ratio is considered to be minimum width by default
	 * (if no excess space exists). The minimum width is defined by terminal
	 * implementation.
	 *
	 * <p>
	 * If terminal implementation supports re-sizable columns the column becomes
	 * fixed width column if users resizes the column.
	 *
	 * @param propertyId
	 *            columns property id
	 * @param expandRatio
	 *            the expandRatio used to divide excess space for this column
	 */
	public void setColumnExpandRatio(Object propertyId, float expandRatio) {
		if (propertyId == null) {
			// Since propertyId is null, this is the row header. Use the magic
			// id to store the width of the row header.
			propertyId = ROW_HEADER_FAKE_PROPERTY_ID;
		}

		// Setting the column expand ratio should remove and defined column
		// width
		columnWidths.remove(propertyId);

		if (expandRatio < 0) {
			columnExpandRatios.remove(propertyId);
		} else {
			columnExpandRatios.put(propertyId, expandRatio);
		}

		requestRepaint();
	}

	/**
	 * Gets the column expand ratio for a columnd. See
	 * {@link #setColumnExpandRatio(Object, float)}
	 *
	 * @param propertyId
	 *            columns property id
	 * @return the expandRatio used to divide excess space for this column
	 */
	public float getColumnExpandRatio(Object propertyId) {
		final Float width = columnExpandRatios.get(propertyId);
		if (width == null) {
			return -1;
		}
		return width.floatValue();
	}

	/**
	 * Gets the pixel width of column.
	 *
	 * @param propertyId
	 *            the property id
	 * @return width of column or -1 when value not set
	 */
	public int getColumnWidth(Object propertyId) {
		if (propertyId == null) {
			// Since propertyId is null, this is the row header. Use the magic
			// id to retrieve the width of the row header.
			propertyId = ROW_HEADER_FAKE_PROPERTY_ID;
		}
		final Integer width = columnWidths.get(propertyId);
		if (width == null) {
			return -1;
		}
		return width.intValue();
	}

	/**
	 * Gets the page length.
	 *
	 * <p>
	 * Setting page length 0 disables paging.
	 * </p>
	 *
	 * @return the Length of one page.
	 */
	public int getPageLength() {
		return pageLength;
	}

	/**
	 * Sets the page length.
	 *
	 * <p>
	 * Setting page length 0 disables paging. The page length defaults to 15.
	 * </p>
	 *
	 * <p>
	 * If CustomTable has width set ({@link #setWidth(float, int)} ) the client
	 * side may update the page length automatically the correct value.
	 * </p>
	 *
	 * @param pageLength
	 *            the length of one page.
	 */
	public void setPageLength(int pageLength) {
		if (pageLength >= 0 && this.pageLength != pageLength) {
			this.pageLength = pageLength;
			// Assures the visual refresh
			refreshRowCache();
		}
	}

	/**
	 * This method adjusts a possible caching mechanism of table implementation.
	 *
	 * <p>
	 * CustomTable component may fetch and render some rows outside visible
	 * area. With complex tables (for example containing layouts and
	 * components), the client side may become unresponsive. Setting the value
	 * lower, UI will become more responsive. With higher values scrolling in
	 * client will hit server less frequently.
	 *
	 * <p>
	 * The amount of cached rows will be cacheRate multiplied with pageLength (
	 * {@link #setPageLength(int)} both below and above visible area..
	 *
	 * @param cacheRate
	 *            a value over 0 (fastest rendering time). Higher value will
	 *            cache more rows on server (smoother scrolling). Default value
	 *            is 2.
	 */
	public void setCacheRate(double cacheRate) {
		if (cacheRate < 0) {
			throw new IllegalArgumentException("cacheRate cannot be less than zero");
		}
		if (this.cacheRate != cacheRate) {
			this.cacheRate = cacheRate;
			markAsDirty();
		}
	}

	/**
	 * Gets the cache rate.
	 *
	 * @return the current cache rate value
	 * @see #setCacheRate(double)
	 */
	public double getCacheRate() {
		return cacheRate;
	}

	/**
	 * Getter for property currentPageFirstItem.
	 *
	 * @return the Value of property currentPageFirstItem.
	 */
	public Object getCurrentPageFirstItemId() {

		// Priorise index over id if indexes are supported
		if (items instanceof Container.Indexed) {
			final int index = getCurrentPageFirstItemIndex();
			Object id = null;
			if (index >= 0 && index < size()) {
				id = getIdByIndex(index);
			}
			if (id != null && !id.equals(currentPageFirstItemId)) {
				currentPageFirstItemId = id;
			}
		}

		// If there is no item id at all, use the first one
		if (currentPageFirstItemId == null) {
			currentPageFirstItemId = firstItemId();
		}

		return currentPageFirstItemId;
	}

	/**
	 * Returns the item ID for the item represented by the index given. Assumes
	 * that the current container implements {@link Container.Indexed}.
	 *
	 * See {@link Container.Indexed#getIdByIndex(int)} for more information
	 * about the exceptions that can be thrown.
	 *
	 * @param index
	 *            the index for which the item ID should be fetched
	 * @return the item ID for the given index
	 */
	protected Object getIdByIndex(int index) {
		return ((Container.Indexed) items).getIdByIndex(index);
	}

	/**
	 * Setter for property currentPageFirstItemId.
	 *
	 * @param currentPageFirstItemId
	 *            the New value of property currentPageFirstItemId.
	 */
	public void setCurrentPageFirstItemId(Object currentPageFirstItemId) {

		// Gets the corresponding index
		int index = -1;
		if (items instanceof Container.Indexed) {
			index = indexOfId(currentPageFirstItemId);
		} else {
			// If the table item container does not have index, we have to
			// calculates the index by hand
			Object id = firstItemId();
			while (id != null && !id.equals(currentPageFirstItemId)) {
				index++;
				id = nextItemId(id);
			}
			if (id == null) {
				index = -1;
			}
		}

		// If the search for item index was successful
		if (index >= 0) {
			/*
			 * The table is not capable of displaying an item in the container
			 * as the first if there are not enough items following the selected
			 * item so the whole table (pagelength) is filled.
			 */
			int maxIndex = size() - pageLength;
			if (maxIndex < 0) {
				maxIndex = 0;
			}

			if (index > maxIndex) {
				// Note that we pass index, not maxIndex, letting
				// setCurrentPageFirstItemIndex handle the situation.
				setCurrentPageFirstItemIndex(index);
				return;
			}

			this.currentPageFirstItemId = currentPageFirstItemId;
			currentPageFirstItemIndex = index;
		}

		// Assures the visual refresh
		refreshRowCache();

	}

	/**
	 * Index of id.
	 *
	 * @param itemId
	 *            the item id
	 * @return the int
	 */
	protected int indexOfId(Object itemId) {
		return ((Container.Indexed) items).indexOfId(itemId);
	}

	/**
	 * Gets the icon Resource for the specified column.
	 *
	 * @param propertyId
	 *            the propertyId indentifying the column.
	 * @return the icon for the specified column; null if the column has no icon
	 *         set, or if the column is not visible.
	 */
	public Resource getColumnIcon(Object propertyId) {
		return columnIcons.get(propertyId);
	}

	/**
	 * Sets the icon Resource for the specified column.
	 * <p>
	 * Throws IllegalArgumentException if the specified column is not visible.
	 * </p>
	 *
	 * @param propertyId
	 *            the propertyId identifying the column.
	 * @param icon
	 *            the icon Resource to set.
	 */
	public void setColumnIcon(Object propertyId, Resource icon) {

		if (icon == null) {
			columnIcons.remove(propertyId);
		} else {
			columnIcons.put(propertyId, icon);
		}

		markAsDirty();
	}

	/**
	 * Gets the header for the specified column.
	 *
	 * @param propertyId
	 *            the propertyId identifying the column.
	 * @return the header for the specified column if it has one.
	 */
	public String getColumnHeader(Object propertyId) {
		if (getColumnHeaderMode() == ColumnHeaderMode.HIDDEN) {
			return null;
		}

		String header = columnHeaders.get(propertyId);
		if ((header == null && getColumnHeaderMode() == ColumnHeaderMode.EXPLICIT_DEFAULTS_ID)
				|| getColumnHeaderMode() == ColumnHeaderMode.ID) {
			header = propertyId.toString();
		}

		return header;
	}

	/**
	 * Sets the column header for the specified column;.
	 *
	 * @param propertyId
	 *            the propertyId identifying the column.
	 * @param header
	 *            the header to set.
	 */
	public void setColumnHeader(Object propertyId, String header) {

		if (header == null) {
			columnHeaders.remove(propertyId);
		} else {
			columnHeaders.put(propertyId, header);
		}

		markAsDirty();
	}

	/**
	 * Gets the specified column's alignment.
	 *
	 * @param propertyId
	 *            the propertyID identifying the column.
	 * @return the specified column's alignment if it as one; {@link Align#LEFT}
	 *         otherwise.
	 */
	public Align getColumnAlignment(Object propertyId) {
		final Align a = columnAlignments.get(propertyId);
		return a == null ? Align.LEFT : a;
	}

	/**
	 * Sets the specified column's alignment.
	 *
	 * <p>
	 * Throws IllegalArgumentException if the alignment is not one of the
	 * following: {@link Align#LEFT}, {@link Align#CENTER} or
	 * {@link Align#RIGHT}
	 * </p>
	 *
	 * @param propertyId
	 *            the propertyID identifying the column.
	 * @param alignment
	 *            the desired alignment.
	 */
	public void setColumnAlignment(Object propertyId, Align alignment) {
		if (alignment == null || alignment == Align.LEFT) {
			columnAlignments.remove(propertyId);
		} else {
			columnAlignments.put(propertyId, alignment);
		}

		// Assures the visual refresh. No need to reset the page buffer before
		// as the content has not changed, only the alignments.
		refreshRenderedCells();
	}

	/**
	 * Checks if the specified column is collapsed.
	 *
	 * @param propertyId
	 *            the propertyID identifying the column.
	 * @return true if the column is collapsed; false otherwise;
	 */
	public boolean isColumnCollapsed(Object propertyId) {
		return collapsedColumns != null && collapsedColumns.contains(propertyId);
	}

	/**
	 * Sets whether the specified column is collapsed or not.
	 *
	 *
	 * @param propertyId
	 *            the propertyID identifying the column.
	 * @param collapsed
	 *            the desired collapsedness.
	 * @throws IllegalStateException
	 *             if column collapsing is not allowed
	 */
	public void setColumnCollapsed(Object propertyId, boolean collapsed) throws IllegalStateException {
		setColumnCollapsed(propertyId, collapsed, true);
	}

	/**
	 * Sets whether the specified column is collapsed or not.
	 *
	 *
	 * @param propertyId
	 *            the propertyID identifying the column.
	 * @param collapsed
	 *            the desired collapsedness.
	 * @throws IllegalStateException
	 *             if column collapsing is not allowed
	 */
	public void setColumnCollapsed(Object propertyId, boolean collapsed, boolean refresh) throws IllegalStateException {
		if (!isColumnCollapsingAllowed()) {
			throw new IllegalStateException("Column collapsing not allowed!");
		}
		if (collapsed && noncollapsibleColumns.contains(propertyId)) {
			throw new IllegalStateException("The column is noncollapsible!");
		}

		if (collapsed) {
			collapsedColumns.add(propertyId);
		} else {
			collapsedColumns.remove(propertyId);
		}
		if (refresh) {
			// Assures the visual refresh
			refreshRowCache();
		}
	}

	/**
	 * Checks if column collapsing is allowed.
	 *
	 * @return true if columns can be collapsed; false otherwise.
	 */
	public boolean isColumnCollapsingAllowed() {
		return columnCollapsingAllowed;
	}

	/**
	 * Sets whether column collapsing is allowed or not.
	 *
	 * @param collapsingAllowed
	 *            specifies whether column collapsing is allowed.
	 */
	public void setColumnCollapsingAllowed(boolean collapsingAllowed) {
		columnCollapsingAllowed = collapsingAllowed;
		doubleColumnCollapsingAllowed = collapsingAllowed;
		tripleColumnCollapsingAllowed = collapsingAllowed;
		if (!collapsingAllowed) {
			collapsedColumns.clear();
			doubleCollapsedColumns.clear();
			tripleCollapsedColumns.clear();
		}

		// Assures the visual refresh. No need to reset the page buffer before
		// as the content has not changed, only the alignments.
		refreshRenderedCells();
	}

	/**
	 * Sets whether the given column is collapsible. Note that collapsible
	 * columns can only be actually collapsed (via UI or with
	 * {@link #setColumnCollapsed(Object, boolean) setColumnCollapsed()}) if
	 * {@link #isColumnCollapsingAllowed()} is true. By default all columns are
	 * collapsible.
	 *
	 * @param propertyId
	 *            the propertyID identifying the column.
	 * @param collapsible
	 *            true if the column should be collapsible, false otherwise.
	 */
	public void setColumnCollapsible(Object propertyId, boolean collapsible) {
		if (collapsible) {
			noncollapsibleColumns.remove(propertyId);
		} else {
			noncollapsibleColumns.add(propertyId);
			collapsedColumns.remove(propertyId);
		}
		refreshRowCache();
	}

	/**
	 * Checks if the given column is collapsible. Note that even if this method
	 * returns <code>true</code>, the column can only be actually collapsed (via
	 * UI or with {@link #setColumnCollapsed(Object, boolean)
	 * setColumnCollapsed()}) if {@link #isColumnCollapsingAllowed()} is also
	 * true.
	 *
	 * @param propertyId
	 *            the property id
	 * @return true if the column can be collapsed; false otherwise.
	 */
	public boolean isColumnCollapsible(Object propertyId) {
		return !noncollapsibleColumns.contains(propertyId);
	}

	/**
	 * Checks if column reordering is allowed.
	 *
	 * @return true if columns can be reordered; false otherwise.
	 */
	public boolean isColumnReorderingAllowed() {
		return columnReorderingAllowed;
	}

	/**
	 * Sets whether column reordering is allowed or not.
	 *
	 * @param columnReorderingAllowed
	 *            specifies whether column reordering is allowed.
	 */
	public void setColumnReorderingAllowed(boolean columnReorderingAllowed) {
		if (columnReorderingAllowed != this.columnReorderingAllowed) {
			this.columnReorderingAllowed = columnReorderingAllowed;
			markAsDirty();
		}
	}

	/*
	 * Arranges visible columns according to given columnOrder. Silently ignores
	 * colimnId:s that are not visible columns, and keeps the internal order of
	 * visible columns left out of the ordering (trailing). Silently does
	 * nothing if columnReordering is not allowed.
	 */
	/**
	 * Sets the column order.
	 *
	 * @param columnOrder
	 *            the new column order
	 */
	private void setColumnOrder(Object[] columnOrder) {
		setColumnOrder(columnOrder, true);
	}

	/*
	 * Arranges visible columns according to given columnOrder. Silently ignores
	 * colimnId:s that are not visible columns, and keeps the internal order of
	 * visible columns left out of the ordering (trailing). Silently does
	 * nothing if columnReordering is not allowed.
	 */
	/**
	 * Sets the column order.
	 *
	 * @param columnOrder
	 *            the new column order
	 */
	public void setColumnOrder(Object[] columnOrder, boolean refresh) {
		if (columnOrder == null || !isColumnReorderingAllowed()) {
			return;
		}
		final LinkedList<Object> newOrder = new LinkedList<Object>();
		for (int i = 0; i < columnOrder.length; i++) {
			if (columnOrder[i] != null && visibleColumns.contains(columnOrder[i])) {
				visibleColumns.remove(columnOrder[i]);
				newOrder.add(columnOrder[i]);
			}
		}
		for (final Iterator<Object> it = visibleColumns.iterator(); it.hasNext();) {
			final Object columnId = it.next();
			if (!newOrder.contains(columnId)) {
				newOrder.add(columnId);
			}
		}
		visibleColumns = newOrder;

		// Assure visual refresh
		if (refresh) {
			refreshRowCache();
		}
	}

	/**
	 * Getter for property currentPageFirstItem.
	 *
	 * @return the Value of property currentPageFirstItem.
	 */
	public int getCurrentPageFirstItemIndex() {
		return currentPageFirstItemIndex;
	}

	/**
	 * Sets the current page first item index.
	 *
	 * @param newIndex
	 *            the new index
	 * @param needsPageBufferReset
	 *            the needs page buffer reset
	 */
	protected void setCurrentPageFirstItemIndex(int newIndex, boolean needsPageBufferReset) {
		if (newIndex < 0) {
			newIndex = 0;
		}

		/*
		 * minimize Container.size() calls which may be expensive. For example
		 * it may cause sql query.
		 */
		final int size = size();
		/*
		 * The table is not capable of displaying an item in the container as
		 * the first if there are not enough items following the selected item
		 * so the whole table (pagelength) is filled.
		 */
		int maxIndex = size - pageLength;
		if (maxIndex < 0) {
			maxIndex = 0;
		}

		/*
		 * If the new index is on the last page we set the index to be the first
		 * item on that last page and make a note of the real index for the
		 * client side to be able to move the scroll position to the correct
		 * position.
		 */
		int indexOnLastPage = -1;
		if (newIndex > maxIndex) {
			indexOnLastPage = newIndex;
			newIndex = maxIndex;
		}

		// Refresh first item id
		if (items instanceof Container.Indexed) {
			try {
				currentPageFirstItemId = getIdByIndex(newIndex);
			} catch (final IndexOutOfBoundsException e) {
				currentPageFirstItemId = null;
			}
			currentPageFirstItemIndex = newIndex;

			if (needsPageBufferReset) {
				/*
				 * The flag currentPageFirstItemIndexOnLastPage denotes a user
				 * set scrolling position on the last page via
				 * setCurrentPageFirstItemIndex() and shouldn't be changed by
				 * the table component internally changing the firstvisible item
				 * on lazy row fetching. Doing so would make the scrolling
				 * position not be updated correctly when the lazy rows are
				 * finally rendered.
				 */

				boolean isLastRowPossiblyPartiallyVisible = true;
				if (indexOnLastPage != -1) {
					/*
					 * If the requested row was greater than maxIndex, the last
					 * row should be fully visible (See
					 * TestCurrentPageFirstItem).
					 */
					isLastRowPossiblyPartiallyVisible = false;
				}

				int extraRows = isLastRowPossiblyPartiallyVisible ? 0 : 1;
				currentPageFirstItemIndexOnLastPage = currentPageFirstItemIndex + extraRows;
			} else {
				currentPageFirstItemIndexOnLastPage = -1;
			}

		} else {
			// For containers not supporting indexes, we must iterate the
			// container forwards / backwards
			// next available item forward or backward
			currentPageFirstItemId = firstItemId();

			// Go forwards in the middle of the list (respect borders)
			while (currentPageFirstItemIndex < newIndex && !isLastId(currentPageFirstItemId)) {
				currentPageFirstItemIndex++;
				currentPageFirstItemId = nextItemId(currentPageFirstItemId);
			}

			// If we did hit the border
			if (isLastId(currentPageFirstItemId)) {
				currentPageFirstItemIndex = size - 1;
			}

			// Go backwards in the middle of the list (respect borders)
			while (currentPageFirstItemIndex > newIndex && !isFirstId(currentPageFirstItemId)) {
				currentPageFirstItemIndex--;
				currentPageFirstItemId = prevItemId(currentPageFirstItemId);
			}

			// If we did hit the border
			if (isFirstId(currentPageFirstItemId)) {
				currentPageFirstItemIndex = 0;
			}

			// Go forwards once more
			while (currentPageFirstItemIndex < newIndex && !isLastId(currentPageFirstItemId)) {
				currentPageFirstItemIndex++;
				currentPageFirstItemId = nextItemId(currentPageFirstItemId);
			}

			// If for some reason we do hit border again, override
			// the user index request
			if (isLastId(currentPageFirstItemId)) {
				newIndex = currentPageFirstItemIndex = size - 1;
			}
		}
		if (needsPageBufferReset) {
			// Assures the visual refresh
			refreshRowCache();
		}
	}

	/**
	 * Setter for property currentPageFirstItem.
	 *
	 * @param newIndex
	 *            the New value of property currentPageFirstItem.
	 */
	public void setCurrentPageFirstItemIndex(int newIndex) {
		setCurrentPageFirstItemIndex(newIndex, true);
	}

	/**
	 * Getter for property selectable.
	 *
	 * <p>
	 * The table is not selectable by default.
	 * </p>
	 *
	 * @return the Value of property selectable.
	 */
	public boolean isSelectable() {
		return selectable;
	}

	/**
	 * Setter for property selectable.
	 *
	 * <p>
	 * The table is not selectable by default.
	 * </p>
	 *
	 * @param selectable
	 *            the New value of property selectable.
	 */
	public void setSelectable(boolean selectable) {
		if (this.selectable != selectable) {
			this.selectable = selectable;
			markAsDirty();
		}
	}

	/**
	 * Getter for property columnHeaderMode.
	 *
	 * @return the Value of property columnHeaderMode.
	 */
	public ColumnHeaderMode getColumnHeaderMode() {
		return columnHeaderMode;
	}

	/**
	 * Setter for property columnHeaderMode.
	 *
	 * @param columnHeaderMode
	 *            the New value of property columnHeaderMode.
	 */
	public void setColumnHeaderMode(ColumnHeaderMode columnHeaderMode) {
		if (columnHeaderMode == null) {
			throw new IllegalArgumentException("Column header mode can not be null");
		}
		if (columnHeaderMode != this.columnHeaderMode) {
			this.columnHeaderMode = columnHeaderMode;
			markAsDirty();
		}

	}

	/**
	 * Refreshes the rows in the internal cache. Only if
	 * {@link #resetPageBuffer()} is called before this then all values are
	 * guaranteed to be recreated.
	 */
	protected void refreshRenderedCells() {
		if (!isAttached()) {
			return;
		}

		if (!isContentRefreshesEnabled) {
			return;
		}

		// Collects the basic facts about the table page
		// final int pagelen = getPageLength();
		final int pagelen = getManualPageLength();
		int rows, totalRows;
		rows = totalRows = size();
		int firstIndex = Math.min(getCurrentPageFirstItemIndex(), totalRows - 1);
		if (rows > 0 && firstIndex >= 0) {
			rows -= firstIndex;
		}
		if (pagelen > 0 && pagelen < rows) {
			rows = pagelen;
		}

		// If "to be painted next" variables are set, use them
		if (lastToBeRenderedInClient - firstToBeRenderedInClient > 0) {
			rows = lastToBeRenderedInClient - firstToBeRenderedInClient + 1;
		}
		if (firstToBeRenderedInClient >= 0) {
			if (firstToBeRenderedInClient < totalRows) {
				firstIndex = firstToBeRenderedInClient;
			} else {
				firstIndex = totalRows - 1;
			}
		} else {
			// initial load

			// #8805 send one extra row in the beginning in case a partial
			// row is shown on the UI
			if (firstIndex > 0) {
				firstIndex = firstIndex - 1;
				rows = rows + 1;
			}
			firstToBeRenderedInClient = firstIndex;
		}
		if (totalRows > 0) {
			if (rows + firstIndex > totalRows) {
				rows = totalRows - firstIndex;
			}
		} else {
			rows = 0;
		}

		// Saves the results to internal buffer
		pageBuffer = getVisibleCellsNoCache(firstIndex, rows, true);

		if (rows > 0) {
			pageBufferFirstIndex = firstIndex;
		}
		// if (getPageLength() != 0) {
		if (getManualPageLength() != 0) {
			removeUnnecessaryRows();
		}

		setRowCacheInvalidated(true);
		markAsDirty();
		maybeThrowCacheUpdateExceptions();

	}

	/**
	 * Maybe throw cache update exceptions.
	 */
	private void maybeThrowCacheUpdateExceptions() {
		if (!exceptionsDuringCachePopulation.isEmpty()) {
			Throwable[] causes = new Throwable[exceptionsDuringCachePopulation.size()];
			exceptionsDuringCachePopulation.toArray(causes);

			exceptionsDuringCachePopulation.clear();
			throw new CacheUpdateException(this, "Error during CustomTable cache update.", causes);
		}

	}

	/**
	 * Exception thrown when one or more exceptions occurred during updating of
	 * the CustomTable cache.
	 * <p>
	 * Contains all exceptions which occurred during the cache update. The first
	 * occurred exception is set as the cause of this exception. All occurred
	 * exceptions can be accessed using {@link #getCauses()}.
	 * </p>
	 *
	 */
	public static class CacheUpdateException extends RuntimeException {

		/**
		 * The causes.
		 */
		private Throwable[] causes;
		/**
		 * The table.
		 */
		private ExtCustomTable table;

		/**
		 * Instantiates a new cache update exception.
		 *
		 * @param table
		 *            the table
		 * @param message
		 *            the message
		 * @param causes
		 *            the causes
		 */
		public CacheUpdateException(ExtCustomTable table, String message, Throwable[] causes) {
			super(maybeSupplementMessage(message, causes.length), causes[0]);
			this.table = table;
			this.causes = causes;
		}

		/**
		 * Maybe supplement message.
		 *
		 * @param message
		 *            the message
		 * @param causeCount
		 *            the cause count
		 * @return the string
		 */
		private static String maybeSupplementMessage(String message, int causeCount) {
			if (causeCount > 1) {
				return message + " Additional causes not shown.";
			} else {
				return message;
			}
		}

		/**
		 * Returns the cause(s) for this exception.
		 *
		 * @return the exception(s) which caused this exception
		 */
		public Throwable[] getCauses() {
			return causes;
		}

		/**
		 * Gets the table.
		 *
		 * @return the table
		 */
		public ExtCustomTable getTable() {
			return table;
		}
	}

	/**
	 * Removes rows that fall outside the required cache.
	 */
	private void removeUnnecessaryRows() {
		int minPageBufferIndex = getMinPageBufferIndex();
		int maxPageBufferIndex = getMaxPageBufferIndex();

		int maxBufferSize = maxPageBufferIndex - minPageBufferIndex + 1;

		/*
		 * Number of rows that were previously cached. This is not necessarily
		 * the same as pageLength if we do not have enough rows in the
		 * container.
		 */
		int currentlyCachedRowCount = pageBuffer[CELL_ITEMID].length;

		if (currentlyCachedRowCount <= maxBufferSize) {
			// removal unnecessary
			return;
		}

		/* Figure out which rows to get rid of. */
		int firstCacheRowToRemoveInPageBuffer = -1;
		if (minPageBufferIndex > pageBufferFirstIndex) {
			firstCacheRowToRemoveInPageBuffer = pageBufferFirstIndex;
		} else if (maxPageBufferIndex < pageBufferFirstIndex + currentlyCachedRowCount) {
			firstCacheRowToRemoveInPageBuffer = maxPageBufferIndex + 1;
		}

		if (firstCacheRowToRemoveInPageBuffer - pageBufferFirstIndex < currentlyCachedRowCount) {
			/*
			 * Unregister all components that fall beyond the cache limits after
			 * inserting the new rows.
			 */
			unregisterComponentsAndPropertiesInRows(firstCacheRowToRemoveInPageBuffer,
					currentlyCachedRowCount - firstCacheRowToRemoveInPageBuffer);
		}
	}

	/**
	 * Requests that the CustomTable should be repainted as soon as possible.
	 *
	 * Note that a {@code CustomTable} does not necessarily repaint its contents
	 * when this method has been called. See {@link #refreshRowCache()} for
	 * forcing an update of the contents.
	 *
	 * @deprecated As of 7.0, use {@link #markAsDirty()} instead
	 */
	@Deprecated
	@Override
	public void requestRepaint() {
		markAsDirty();
	}

	/**
	 * Requests that the CustomTable should be repainted as soon as possible.
	 *
	 * Note that a {@code CustomTable} does not necessarily repaint its contents
	 * when this method has been called. See {@link #refreshRowCache()} for
	 * forcing an update of the contents.
	 */
	@Override
	public void markAsDirty() {
		// Overridden only for javadoc
		super.markAsDirty();
	}

	/**
	 * Mark as dirty recursive.
	 */
	@Override
	public void markAsDirtyRecursive() {
		super.markAsDirtyRecursive();

		// Avoid sending a partial repaint (#8714)
		refreshRowCache();
	}

	/**
	 * Removes the rows from cache and fill bottom.
	 *
	 * @param firstIndex
	 *            the first index
	 * @param rows
	 *            the rows
	 */
	private void removeRowsFromCacheAndFillBottom(int firstIndex, int rows) {
		int totalCachedRows = pageBuffer[CELL_ITEMID].length;
		int totalRows = size();
		int firstIndexInPageBuffer = firstIndex - pageBufferFirstIndex;

		/*
		 * firstIndexInPageBuffer is the first row to be removed. "rows" rows
		 * after that should be removed. If the page buffer does not contain
		 * that many rows, we only remove the rows that actually are in the page
		 * buffer.
		 */
		if (firstIndexInPageBuffer + rows > totalCachedRows) {
			rows = totalCachedRows - firstIndexInPageBuffer;
		}

		/*
		 * Unregister components that will no longer be in the page buffer to
		 * make sure that no components leak.
		 */
		unregisterComponentsAndPropertiesInRows(firstIndex, rows);

		/*
		 * The number of rows that should be in the cache after this operation
		 * is done (pageBuffer currently contains the expanded items).
		 */
		int newCachedRowCount = totalCachedRows;
		if (newCachedRowCount + pageBufferFirstIndex > totalRows) {
			newCachedRowCount = totalRows - pageBufferFirstIndex;
		}

		/*
		 * The index at which we should render the first row that does not come
		 * from the previous page buffer.
		 */
		int firstAppendedRowInPageBuffer = totalCachedRows - rows;
		int firstAppendedRow = firstAppendedRowInPageBuffer + pageBufferFirstIndex;

		/*
		 * Calculate the maximum number of new rows that we can add to the page
		 * buffer. Less than the rows we removed if the container does not
		 * contain that many items afterwards.
		 */
		int maxRowsToRender = (totalRows - firstAppendedRow);
		int rowsToAdd = rows;
		if (rowsToAdd > maxRowsToRender) {
			rowsToAdd = maxRowsToRender;
		}

		Object[][] cells = null;
		if (rowsToAdd > 0) {
			cells = getVisibleCellsNoCache(firstAppendedRow, rowsToAdd, false);
		}
		/*
		 * Create the new cache buffer by copying the first rows from the old
		 * buffer, moving the following rows upwards and appending more rows if
		 * applicable.
		 */
		Object[][] newPageBuffer = new Object[pageBuffer.length][newCachedRowCount];

		for (int i = 0; i < pageBuffer.length; i++) {
			for (int row = 0; row < firstIndexInPageBuffer; row++) {
				// Copy the first rows
				newPageBuffer[i][row] = pageBuffer[i][row];
			}
			for (int row = firstIndexInPageBuffer; row < firstAppendedRowInPageBuffer; row++) {
				// Move the rows that were after the expanded rows
				newPageBuffer[i][row] = pageBuffer[i][row + rows];
			}
			for (int row = firstAppendedRowInPageBuffer; row < newCachedRowCount; row++) {
				// Add the newly rendered rows. Only used if rowsToAdd > 0
				// (cells != null)
				newPageBuffer[i][row] = cells[i][row - firstAppendedRowInPageBuffer];
			}
		}
		pageBuffer = newPageBuffer;
	}

	/**
	 * Gets the visible cells update cache rows.
	 *
	 * @param firstIndex
	 *            the first index
	 * @param rows
	 *            the rows
	 * @return the visible cells update cache rows
	 */
	private Object[][] getVisibleCellsUpdateCacheRows(int firstIndex, int rows) {
		Object[][] cells = getVisibleCellsNoCache(firstIndex, rows, false);
		int cacheIx = firstIndex - pageBufferFirstIndex;
		// update the new rows in the cache.
		int totalCachedRows = pageBuffer[CELL_ITEMID].length;
		int end = Math.min(cacheIx + rows, totalCachedRows);
		for (int ix = cacheIx; ix < end; ix++) {
			for (int i = 0; i < pageBuffer.length; i++) {
				pageBuffer[i][ix] = cells[i][ix - cacheIx];
			}
		}
		return cells;
	}

	/**
	 * Gets the visible cells insert into cache.
	 *
	 * @param firstIndex
	 *            The position where new rows should be inserted
	 * @param rows
	 *            The maximum number of rows that should be inserted at position
	 *            firstIndex. Less rows will be inserted if the page buffer is
	 *            too small.
	 * @return the visible cells insert into cache
	 */
	private Object[][] getVisibleCellsInsertIntoCache(int firstIndex, int rows) {
		getLogger().log(Level.FINEST, "Insert {0} rows at index {1} to existing page buffer requested",
				new Object[] { rows, firstIndex });

		int minPageBufferIndex = getMinPageBufferIndex();
		int maxPageBufferIndex = getMaxPageBufferIndex();

		int maxBufferSize = maxPageBufferIndex - minPageBufferIndex + 1;

		if (getPageLength() == 0) {
			// If pageLength == 0 then all rows should be rendered
			maxBufferSize = pageBuffer[CELL_ITEMID].length + rows;
		}
		/*
		 * Number of rows that were previously cached. This is not necessarily
		 * the same as maxBufferSize.
		 */
		int currentlyCachedRowCount = pageBuffer[CELL_ITEMID].length;

		/* If rows > size available in page buffer */
		if (firstIndex + rows - 1 > maxPageBufferIndex) {
			rows = maxPageBufferIndex - firstIndex + 1;
		}

		/*
		 * "rows" rows will be inserted at firstIndex. Find out how many old
		 * rows fall outside the new buffer so we can unregister components in
		 * the cache.
		 */

		/*
		 * if there are rows before the new pageBuffer limits they must be
		 * removed
		 */
		int lastCacheRowToRemove = minPageBufferIndex - 1;
		int rowsFromBeginning = lastCacheRowToRemove - pageBufferFirstIndex + 1;
		if (lastCacheRowToRemove >= pageBufferFirstIndex) {
			unregisterComponentsAndPropertiesInRows(pageBufferFirstIndex, rowsFromBeginning);
		} else {
			rowsFromBeginning = 0;
		}

		/*
		 * the rows that fall outside of the new pageBuffer limits after the new
		 * rows are inserted must also be removed
		 */
		int firstCacheRowToRemove = firstIndex;
		/*
		 * IF there is space remaining in the buffer after the rows have been
		 * inserted, we can keep more rows.
		 */
		int numberOfOldRowsAfterInsertedRows = Math.min(pageBufferFirstIndex + currentlyCachedRowCount + rows,
				maxPageBufferIndex + 1) - (firstIndex + rows - 1);
		if (numberOfOldRowsAfterInsertedRows > 0) {
			firstCacheRowToRemove += numberOfOldRowsAfterInsertedRows;
		}
		int rowsFromAfter = currentlyCachedRowCount - (firstCacheRowToRemove - pageBufferFirstIndex);

		if (rowsFromAfter > 0) {
			/*
			 * Unregister all components that fall beyond the cache limits after
			 * inserting the new rows.
			 */
			unregisterComponentsAndPropertiesInRows(firstCacheRowToRemove, rowsFromAfter);
		}

		// Calculate the new cache size
		int newCachedRowCount = maxBufferSize;
		if (pageBufferFirstIndex + currentlyCachedRowCount + rows - 1 < maxPageBufferIndex) {
			// there aren't enough rows to fill the whole potential -> use what
			// there is
			newCachedRowCount -= maxPageBufferIndex - (pageBufferFirstIndex + currentlyCachedRowCount + rows - 1);
		} else if (minPageBufferIndex < pageBufferFirstIndex) {
			newCachedRowCount -= pageBufferFirstIndex - minPageBufferIndex;
		}
		/*
		 * calculate the internal location of the new rows within the new cache
		 */
		int firstIndexInNewPageBuffer = firstIndex - pageBufferFirstIndex - rowsFromBeginning;

		/* Paint the new rows into a separate buffer */
		Object[][] cells = getVisibleCellsNoCache(firstIndex, rows, false);

		/*
		 * Create the new cache buffer and fill it with the data from the old
		 * buffer as well as the inserted rows.
		 */
		Object[][] newPageBuffer = new Object[pageBuffer.length][newCachedRowCount];

		for (int i = 0; i < pageBuffer.length; i++) {
			for (int row = 0; row < firstIndexInNewPageBuffer; row++) {
				// Copy the first rows
				newPageBuffer[i][row] = pageBuffer[i][rowsFromBeginning + row];
			}
			for (int row = firstIndexInNewPageBuffer; row < firstIndexInNewPageBuffer + rows; row++) {
				// Copy the newly created rows
				newPageBuffer[i][row] = cells[i][row - firstIndexInNewPageBuffer];
			}
			for (int row = firstIndexInNewPageBuffer + rows; row < newCachedRowCount; row++) {
				// Move the old rows down below the newly inserted rows
				newPageBuffer[i][row] = pageBuffer[i][rowsFromBeginning + row - rows];
			}
		}
		pageBuffer = newPageBuffer;
		pageBufferFirstIndex = Math.max(pageBufferFirstIndex + rowsFromBeginning, minPageBufferIndex);
		if (getLogger().isLoggable(Level.FINEST)) {
			getLogger().log(Level.FINEST, "Page Buffer now contains {0} rows ({1}-{2})",
					new Object[] { pageBuffer[CELL_ITEMID].length, pageBufferFirstIndex,
							(pageBufferFirstIndex + pageBuffer[CELL_ITEMID].length - 1) });
		}
		return cells;
	}

	/**
	 * Gets the max page buffer index.
	 *
	 * @return the max page buffer index
	 */
	private int getMaxPageBufferIndex() {
		int total = size();
		// if (getPageLength() == 0) {
		if (getManualPageLength() == 0) {
			// everything is shown at once, no caching
			return total - 1;
		}
		// Page buffer must not become larger than pageLength*cacheRate after
		// the current page
		int maxPageBufferIndex = getCurrentPageFirstItemIndex() + (int) (getManualPageLength() * (1 + getCacheRate()));
		if (shouldHideNullSelectionItem()) {
			--total;
		}
		if (maxPageBufferIndex >= total) {
			maxPageBufferIndex = total - 1;
		}
		return maxPageBufferIndex;
	}

	/**
	 * Gets the min page buffer index.
	 *
	 * @return the min page buffer index
	 */
	private int getMinPageBufferIndex() {
		// if (getPageLength() == 0) {
		if (getManualPageLength() == 0) {
			// everything is shown at once, no caching
			return 0;
		}
		// Page buffer must not become larger than pageLength*cacheRate before
		// the current page
		int minPageBufferIndex = getCurrentPageFirstItemIndex() - (int) (getManualPageLength() * getCacheRate());
		if (minPageBufferIndex < 0) {
			minPageBufferIndex = 0;
		}
		return minPageBufferIndex;
	}

	/**
	 * Render rows with index "firstIndex" to "firstIndex+rows-1" to a new
	 * buffer.
	 *
	 * Reuses values from the current page buffer if the rows are found there.
	 *
	 * @param firstIndex
	 *            the first index
	 * @param rows
	 *            the rows
	 * @param replaceListeners
	 *            the replace listeners
	 * @return the visible cells no cache
	 */
	private Object[][] getVisibleCellsNoCache(int firstIndex, int rows, boolean replaceListeners) {
		if (getLogger().isLoggable(Level.FINEST)) {
			getLogger().log(Level.FINEST, "Render visible cells for rows {0}-{1}",
					new Object[] { firstIndex, (firstIndex + rows - 1) });
		}
		final Object[] colids = getVisibleColumns();
		final int cols = colids.length;

		HashSet<Property<?>> oldListenedProperties = listenedProperties;
		HashSet<Component> oldVisibleComponents = visibleComponents;

		if (replaceListeners) {
			// initialize the listener collections, this should only be done if
			// the entire cache is refreshed (through refreshRenderedCells)
			listenedProperties = new HashSet<Property<?>>();
			visibleComponents = new HashSet<Component>();
		}

		Object[][] cells = new Object[cols + CELL_FIRSTCOL][rows];
		if (rows == 0) {
			unregisterPropertiesAndComponents(oldListenedProperties, oldVisibleComponents);
			return cells;
		}

		final RowHeaderMode headmode = getRowHeaderMode();
		final boolean[] iscomponent = new boolean[cols];
		for (int i = 0; i < cols; i++) {
			iscomponent[i] = columnGenerators.containsKey(colids[i])
					|| Component.class.isAssignableFrom(getType(colids[i]));
		}
		int firstIndexNotInCache;
		if (pageBuffer != null && pageBuffer[CELL_ITEMID].length > 0) {
			firstIndexNotInCache = pageBufferFirstIndex + pageBuffer[CELL_ITEMID].length;
		} else {
			firstIndexNotInCache = -1;
		}

		// Creates the page contents
		int filledRows = 0;
		if (items instanceof Container.Indexed) {
			// more efficient implementation for containers supporting access by
			// index
			List<?> itemIds = getItemIds(firstIndex, rows);
			for (int i = 0; i < rows && i < itemIds.size(); i++) {
				Object id = itemIds.get(i);
				// Start by parsing the values, id should already be set
				parseItemIdToCells(cells, id, i, firstIndex, headmode, cols, colids, firstIndexNotInCache, iscomponent,
						oldListenedProperties);

				filledRows++;
			}
		} else {
			// slow back-up implementation for cases where the container does
			// not support access by index

			// Gets the first item id
			Object id = firstItemId();
			for (int i = 0; i < firstIndex; i++) {
				id = nextItemId(id);
			}
			for (int i = 0; i < rows && id != null; i++) {
				// Start by parsing the values, id should already be set
				parseItemIdToCells(cells, id, i, firstIndex, headmode, cols, colids, firstIndexNotInCache, iscomponent,
						oldListenedProperties);

				// Gets the next item id for non indexed container
				id = nextItemId(id);

				filledRows++;
			}
		}

		// Assures that all the rows of the cell-buffer are valid
		if (filledRows != cells[0].length) {
			final Object[][] temp = new Object[cells.length][filledRows];
			for (int i = 0; i < cells.length; i++) {
				for (int j = 0; j < filledRows; j++) {
					temp[i][j] = cells[i][j];
				}
			}
			cells = temp;
		}

		unregisterPropertiesAndComponents(oldListenedProperties, oldVisibleComponents);

		return cells;
	}

	/**
	 * Gets the item ids.
	 *
	 * @param firstIndex
	 *            the first index
	 * @param rows
	 *            the rows
	 * @return the item ids
	 */
	protected List<Object> getItemIds(int firstIndex, int rows) {
		return (List<Object>) ((Container.Indexed) items).getItemIds(firstIndex, rows);
	}

	/**
	 * Update a cache array for a row, register any relevant listeners etc.
	 *
	 * This is an internal method extracted from
	 * {@link #getVisibleCellsNoCache(int, int, boolean)} and should be removed
	 * when the CustomTable is rewritten.
	 *
	 * @param cells
	 *            the cells
	 * @param id
	 *            the id
	 * @param i
	 *            the i
	 * @param firstIndex
	 *            the first index
	 * @param headmode
	 *            the headmode
	 * @param cols
	 *            the cols
	 * @param colids
	 *            the colids
	 * @param firstIndexNotInCache
	 *            the first index not in cache
	 * @param iscomponent
	 *            the iscomponent
	 * @param oldListenedProperties
	 *            the old listened properties
	 */
	private void parseItemIdToCells(Object[][] cells, Object id, int i, int firstIndex, RowHeaderMode headmode,
			int cols, Object[] colids, int firstIndexNotInCache, boolean[] iscomponent,
			HashSet<Property<?>> oldListenedProperties) {

		cells[CELL_ITEMID][i] = id;
		cells[CELL_KEY][i] = itemIdMapper.key(id);
		if (headmode != ROW_HEADER_MODE_HIDDEN) {
			switch (headmode) {
			case INDEX:
				cells[CELL_HEADER][i] = String.valueOf(i + firstIndex + 1);
				break;
			default:
				try {
					cells[CELL_HEADER][i] = getItemCaption(id);
				} catch (Exception e) {
					exceptionsDuringCachePopulation.add(e);
					cells[CELL_HEADER][i] = "";
				}
			}
			try {
				cells[CELL_ICON][i] = getItemIcon(id);
			} catch (Exception e) {
				exceptionsDuringCachePopulation.add(e);
				cells[CELL_ICON][i] = null;
			}
		}

		GeneratedRow generatedRow = rowGenerator != null ? rowGenerator.generateRow(this, id) : null;
		cells[CELL_GENERATED_ROW][i] = generatedRow;

		for (int j = 0; j < cols; j++) {
			if (isColumnCollapsed(colids[j])) {
				continue;
			}
			Property<?> p = null;
			Object value = "";
			boolean isGeneratedRow = generatedRow != null;
			boolean isGeneratedColumn = columnGenerators.containsKey(colids[j]);
			boolean isGenerated = isGeneratedRow || isGeneratedColumn;

			if (!isGenerated) {
				try {
					p = getContainerProperty(id, colids[j]);
				} catch (Exception e) {
					exceptionsDuringCachePopulation.add(e);
					value = null;
				}
			}

			if (isGeneratedRow) {
				if (generatedRow.isSpanColumns() && j > 0) {
					value = null;
				} else if (generatedRow.isSpanColumns() && j == 0 && generatedRow.getValue() instanceof Component) {
					value = generatedRow.getValue();
				} else if (generatedRow.getText().length > j) {
					value = generatedRow.getText()[j];
				}
			} else {
				// check if current pageBuffer already has row
				int index = firstIndex + i;
				if (p != null || isGenerated) {
					int indexInOldBuffer = index - pageBufferFirstIndex;
					if (index < firstIndexNotInCache && index >= pageBufferFirstIndex
							&& pageBuffer[CELL_GENERATED_ROW][indexInOldBuffer] == null
							&& id.equals(pageBuffer[CELL_ITEMID][indexInOldBuffer])) {
						// we already have data in our cache,
						// recycle it instead of fetching it via
						// getValue/getPropertyValue
						value = pageBuffer[CELL_FIRSTCOL + j][indexInOldBuffer];
						if (!isGeneratedColumn && iscomponent[j] || !(value instanceof Component)) {
							listenProperty(p, oldListenedProperties);
						}
					} else {
						if (isGeneratedColumn) {
							ColumnGenerator cg = columnGenerators.get(colids[j]);
							try {
								value = cg.generateCell(this, id, colids[j]);
							} catch (Exception e) {
								exceptionsDuringCachePopulation.add(e);
								value = null;
							}
							if (value != null && !(value instanceof Component) && !(value instanceof String)) {
								// Avoid errors if a generator returns
								// something
								// other than a Component or a String
								value = value.toString();
							}
						} else if (iscomponent[j]) {
							try {
								value = p.getValue();
							} catch (Exception e) {
								exceptionsDuringCachePopulation.add(e);
								value = null;
							}
							listenProperty(p, oldListenedProperties);
						} else if (p != null && !p.getType().equals(Map.class)) {
							try {
								value = getPropertyValue(id, colids[j], p);
							} catch (Exception e) {
								exceptionsDuringCachePopulation.add(e);
								value = null;
							}
							/*
							 * If returned value is Component (via fieldfactory
							 * or overridden getPropertyValue) we expect it to
							 * listen property value changes. Otherwise if
							 * property emits value change events, table will
							 * start to listen them and refresh content when
							 * needed.
							 */
							if (!(value instanceof Component)) {
								listenProperty(p, oldListenedProperties);
							}
						} else {
							try {
								value = getPropertyValue(id, colids[j], null);
							} catch (Exception e) {
								exceptionsDuringCachePopulation.add(e);
								value = null;
							}
						}
					}
				}
			}

			if (value instanceof Component) {
				registerComponent((Component) value);
			}
			cells[CELL_FIRSTCOL + j][i] = value;
		}
	}

	/**
	 * Register component.
	 *
	 * @param component
	 *            the component
	 */
	protected void registerComponent(Component component) {
		getLogger().log(Level.FINEST, "Registered {0}: {1}",
				new Object[] { component.getClass().getSimpleName(), component.getCaption() });
		if (!equals(component.getParent())) {
			component.setParent(this);
		}
		visibleComponents.add(component);
	}

	/**
	 * Listen property.
	 *
	 * @param p
	 *            the p
	 * @param oldListenedProperties
	 *            the old listened properties
	 */
	private void listenProperty(Property<?> p, HashSet<Property<?>> oldListenedProperties) {
		if (p instanceof Property.ValueChangeNotifier) {
			if (oldListenedProperties == null || !oldListenedProperties.contains(p)) {
				((Property.ValueChangeNotifier) p).addListener(this);
			}
			/*
			 * register listened properties, so we can do proper cleanup to free
			 * memory. Essential if table has loads of data and it is used for a
			 * long time.
			 */
			listenedProperties.add(p);

		}
	}

	/**
	 * Unregister components and properties in rows.
	 *
	 * @param firstIx
	 *            Index of the first row to process. Global index, not relative
	 *            to page buffer.
	 * @param count
	 *            the count
	 */
	private void unregisterComponentsAndPropertiesInRows(int firstIx, int count) {
		if (getLogger().isLoggable(Level.FINEST)) {
			getLogger().log(Level.FINEST, "Unregistering components in rows {0}-{1}",
					new Object[] { firstIx, (firstIx + count - 1) });
		}
		Object[] colids = getVisibleColumns();
		if (pageBuffer != null && pageBuffer[CELL_ITEMID].length > 0) {
			int bufSize = pageBuffer[CELL_ITEMID].length;
			int ix = firstIx - pageBufferFirstIndex;
			ix = ix < 0 ? 0 : ix;
			if (ix < bufSize) {
				count = count > bufSize - ix ? bufSize - ix : count;
				for (int i = 0; i < count; i++) {
					for (int c = 0; c < colids.length; c++) {
						Object cellVal = pageBuffer[CELL_FIRSTCOL + c][i + ix];
						if (cellVal instanceof Component && visibleComponents.contains(cellVal)) {
							visibleComponents.remove(cellVal);
							unregisterComponent((Component) cellVal);
						} else {
							Property<?> p = getContainerProperty(pageBuffer[CELL_ITEMID][i + ix], colids[c]);
							if (p instanceof ValueChangeNotifier && listenedProperties.contains(p)) {
								listenedProperties.remove(p);
								((ValueChangeNotifier) p).removeListener(this);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Helper method to remove listeners and maintain correct component
	 * hierarchy. Detaches properties and components if those are no more
	 * rendered in client.
	 *
	 * @param oldListenedProperties
	 *            set of properties that where listened in last render
	 * @param oldVisibleComponents
	 *            set of components that where attached in last render
	 */
	private void unregisterPropertiesAndComponents(HashSet<Property<?>> oldListenedProperties,
			HashSet<Component> oldVisibleComponents) {
		if (oldVisibleComponents != null) {
			for (final Iterator<Component> i = oldVisibleComponents.iterator(); i.hasNext();) {
				Component c = i.next();
				if (!visibleComponents.contains(c)) {
					unregisterComponent(c);
				}
			}
		}

		if (oldListenedProperties != null) {
			for (final Iterator<Property<?>> i = oldListenedProperties.iterator(); i.hasNext();) {
				Property.ValueChangeNotifier o = (ValueChangeNotifier) i.next();
				if (!listenedProperties.contains(o)) {
					o.removeListener(this);
				}
			}
		}
	}

	/**
	 * This method cleans up a Component that has been generated when
	 * CustomTable is in editable mode. The component needs to be detached from
	 * its parent and if it is a field, it needs to be detached from its
	 * property data source in order to allow garbage collection to take care of
	 * removing the unused component from memory.
	 *
	 * Override this method and getPropertyValue(Object, Object, Property) with
	 * custom logic if you need to deal with buffered fields.
	 *
	 * @param component
	 *            the component
	 * @see #getPropertyValue(Object, Object, Property)
	 */
	protected void unregisterComponent(Component component) {
		getLogger().log(Level.FINEST, "Unregistered {0}: {1}",
				new Object[] { component.getClass().getSimpleName(), component.getCaption() });
		component.setParent(null);
		/*
		 * Also remove property data sources to unregister listeners keeping the
		 * fields in memory.
		 */
		if (component instanceof Field) {
			Field<?> field = (Field<?>) component;
			Property<?> associatedProperty = associatedProperties.remove(component);
			if (associatedProperty != null && field.getPropertyDataSource() == associatedProperty) {
				// Remove the property data source only if it's the one we
				// added in getPropertyValue
				field.setPropertyDataSource(null);
			}
		}
	}

	/**
	 * Sets the row header mode.
	 * <p>
	 * The mode can be one of the following ones:
	 * <ul>
	 * <li>{@link #ROW_HEADER_MODE_HIDDEN}: The row captions are hidden.</li>
	 * <li>{@link #ROW_HEADER_MODE_ID}: Items Id-objects <code>toString()</code>
	 * is used as row caption.
	 * <li>{@link #ROW_HEADER_MODE_ITEM}: Item-objects <code>toString()</code>
	 * is used as row caption.
	 * <li>{@link #ROW_HEADER_MODE_PROPERTY}: Property set with
	 * {@link #setItemCaptionPropertyId(Object)} is used as row header.
	 * <li>{@link #ROW_HEADER_MODE_EXPLICIT_DEFAULTS_ID}: Items Id-objects
	 * <code>toString()</code> is used as row header. If caption is explicitly
	 * specified, it overrides the id-caption.
	 * <li>{@link #ROW_HEADER_MODE_EXPLICIT}: The row headers must be explicitly
	 * specified.</li>
	 * <li>{@link #ROW_HEADER_MODE_INDEX}: The index of the item is used as row
	 * caption. The index mode can only be used with the containers implementing
	 * <code>Container.Indexed</code> interface.</li>
	 * </ul>
	 * The default value is {@link #ROW_HEADER_MODE_HIDDEN}
	 * </p>
	 *
	 * @param mode
	 *            the One of the modes listed above.
	 */
	public void setRowHeaderMode(RowHeaderMode mode) {
		if (mode != null) {
			rowHeaderMode = mode;
			if (mode != RowHeaderMode.HIDDEN) {
				setItemCaptionMode(mode.getItemCaptionMode());
			}
			// Assures the visual refresh. No need to reset the page buffer
			// before
			// as the content has not changed, only the alignments.
			refreshRenderedCells();
		}
	}

	/**
	 * Gets the row header mode.
	 *
	 * @return the Row header mode.
	 * @see #setRowHeaderMode(int)
	 */
	public RowHeaderMode getRowHeaderMode() {

		return rowHeaderMode;
	}

	/**
	 * Adds the new row to table and fill the visible cells (except generated
	 * columns) with given values.
	 *
	 * @param cells
	 *            the Object array that is used for filling the visible cells
	 *            new row. The types must be settable to visible column property
	 *            types.
	 * @param itemId
	 *            the Id the new row. If null, a new id is automatically
	 *            assigned. If given, the table cant already have a item with
	 *            given id.
	 * @return Returns item id for the new row. Returns null if operation fails.
	 * @throws UnsupportedOperationException
	 *             the unsupported operation exception
	 */
	public Object addItem(Object[] cells, Object itemId) throws UnsupportedOperationException {

		// remove generated columns from the list of columns being assigned
		final LinkedList<Object> availableCols = new LinkedList<Object>();
		for (Iterator<Object> it = visibleColumns.iterator(); it.hasNext();) {
			Object id = it.next();
			if (!columnGenerators.containsKey(id)) {
				availableCols.add(id);
			}
		}
		// Checks that a correct number of cells are given
		if (cells.length != availableCols.size()) {
			return null;
		}

		// Creates new item
		Item item;
		if (itemId == null) {
			itemId = items.addItem();
			if (itemId == null) {
				return null;
			}
			item = items.getItem(itemId);
		} else {
			item = items.addItem(itemId);
		}
		if (item == null) {
			return null;
		}

		// Fills the item properties
		for (int i = 0; i < availableCols.size(); i++) {
			item.getItemProperty(availableCols.get(i)).setValue(cells[i]);
		}

		if (!(items instanceof Container.ItemSetChangeNotifier)) {
			refreshRowCache();
		}

		return itemId;
	}

	/**
	 * Discards and recreates the internal row cache. Call this if you make
	 * changes that affect the rows but the information about the changes are
	 * not automatically propagated to the CustomTable.
	 * <p>
	 * Do not call this e.g. if you have updated the data model through a
	 * Property. These types of changes are automatically propagated to the
	 * CustomTable.
	 * <p>
	 * A typical case when this is needed is if you update a generator (e.g.
	 * CellStyleGenerator) and want to ensure that the rows are redrawn with new
	 * styles.
	 * <p>
	 * <i>Note that calling this method is not cheap so avoid calling it
	 * unnecessarily.</i>
	 *
	 * @since 6.7.2
	 */
	public void refreshRowCache() {
		if (isRefresh()) {
			resetPageBuffer();
			refreshRenderedCells();
		}
	}

	/**
	 * Sets the Container that serves as the data source of the viewer. As a
	 * side-effect the table's selection value is set to null as the old
	 * selection might not exist in new Container.<br>
	 * <br>
	 * All rows and columns are generated as visible using this method. If the
	 * new container contains properties that are not meant to be shown you
	 * should use
	 * {@link CustomTable#setContainerDataSource(Container, Collection)}
	 * instead, especially if the table is editable.
	 * <p>
	 * Keeps propertyValueConverters if the corresponding id exists in the new
	 * data source and is of a compatible type.
	 * </p>
	 *
	 * @param newDataSource
	 *            the new data source.
	 */
	@Override
	public void setContainerDataSource(Container newDataSource) {
		if (newDataSource == null) {
			newDataSource = new IndexedContainer();
		}
		Collection<Object> generated;
		if (columnGenerators != null) {
			generated = columnGenerators.keySet();
		} else {
			generated = Collections.emptyList();
		}
		List<Object> visibleIds = new ArrayList<Object>();
		if (generated.isEmpty()) {
			visibleIds.addAll(newDataSource.getContainerPropertyIds());
		} else {
			for (Object id : newDataSource.getContainerPropertyIds()) {
				// don't add duplicates
				if (!generated.contains(id)) {
					visibleIds.add(id);
				}
			}
			// generated columns to the end
			visibleIds.addAll(generated);
		}
		setContainerDataSource(newDataSource, visibleIds);
	}

	/**
	 * Sets the container data source and the columns that will be visible.
	 * Columns are shown in the collection's iteration order.
	 * <p>
	 * Keeps propertyValueConverters if the corresponding id exists in the new
	 * data source and is of a compatible type.
	 * </p>
	 *
	 * @param newDataSource
	 *            the new data source.
	 * @param visibleIds
	 *            IDs of the visible columns
	 * @see ExtCustomTable#setContainerDataSource(Container)
	 * @see ExtCustomTable#setVisibleColumns(Object[])
	 * @see ExtCustomTable#setConverter(Object, Converter<String, ?>)
	 */
	public void setContainerDataSource(Container newDataSource, Collection<?> visibleIds) {

		disableContentRefreshing();

		if (newDataSource == null) {
			newDataSource = new IndexedContainer();
		}
		if (visibleIds == null) {
			visibleIds = new ArrayList<Object>();
		}

		// Retain propertyValueConverters if their corresponding ids are
		// properties of the new
		// data source and are of a compatible type
		if (propertyValueConverters != null) {
			Collection<?> newPropertyIds = newDataSource.getContainerPropertyIds();
			LinkedList<Object> retainableValueConverters = new LinkedList<Object>();
			for (Object propertyId : newPropertyIds) {
				Converter<String, ?> converter = getConverter(propertyId);
				if (converter != null) {
					if (typeIsCompatible(converter.getModelType(), newDataSource.getType(propertyId))) {
						retainableValueConverters.add(propertyId);
					}
				}
			}
			propertyValueConverters.keySet().retainAll(retainableValueConverters);
		}

		// Assures that the data source is ordered by making unordered
		// containers ordered by wrapping them
		if (newDataSource instanceof Container.Ordered) {
			super.setContainerDataSource(newDataSource);
		} else {
			super.setContainerDataSource(new ContainerOrderedWrapper(newDataSource));
		}

		// Resets page position
		currentPageFirstItemId = null;
		currentPageFirstItemIndex = 0;

		// Resets column properties
		if (collapsedColumns != null) {
			collapsedColumns.clear();
		}

		// don't add the same id twice
		Collection<Object> col = new LinkedList<Object>();
		for (Iterator<?> it = visibleIds.iterator(); it.hasNext();) {
			Object id = it.next();
			if (!col.contains(id)) {
				col.add(id);
			}
		}

		setVisibleColumns(col.toArray());

		// Assure visual refresh
		resetPageBuffer();

		enableContentRefreshing(true);
	}

	/**
	 * Checks if class b can be safely assigned to class a.
	 *
	 * @param a
	 *            the a
	 * @param b
	 *            the b
	 * @return true, if successful
	 */
	private boolean typeIsCompatible(Class<?> a, Class<?> b) {
		// TODO Implement this check properly
		// Basically we need to do a a.isAssignableFrom(b)
		// with special considerations for primitive types.
		return true;
	}

	/**
	 * Gets items ids from a range of key values.
	 *
	 * @param itemId
	 *            the item id
	 * @param length
	 *            the length
	 * @return the item ids in range
	 */
	private LinkedHashSet<Object> getItemIdsInRange(Object itemId, final int length) {
		LinkedHashSet<Object> ids = new LinkedHashSet<Object>();
		for (int i = 0; i < length; i++) {
			assert itemId != null; // should not be null unless client-server
			// are out of sync
			ids.add(itemId);
			itemId = nextItemId(itemId);
		}
		return ids;
	}

	/**
	 * Handles selection if selection is a multiselection.
	 *
	 * @param variables
	 *            The variables
	 */
	private void handleSelectedItems(Map<String, Object> variables) {
		final String[] ka = (String[]) variables.get("selected");
		final String[] ranges = (String[]) variables.get("selectedRanges");

		Set<Object> renderedButNotSelectedItemIds = getCurrentlyRenderedItemIds();

		@SuppressWarnings("unchecked")
		HashSet<Object> newValue = new LinkedHashSet<Object>((Collection<Object>) getValue());

		if (variables.containsKey("clearSelections")) {
			// the client side has instructed to swipe all previous selections
			newValue.clear();
		}

		/*
		 * Then add (possibly some of them back) rows that are currently
		 * selected on the client side (the ones that the client side is aware
		 * of).
		 */
		for (int i = 0; i < ka.length; i++) {
			// key to id
			final Object id = itemIdMapper.get(ka[i]);
			if (!isNullSelectionAllowed() && (id == null || id == getNullSelectionItemId())) {
				// skip empty selection if nullselection is not allowed
				markAsDirty();
			} else if (id != null && containsId(id)) {
				newValue.add(id);
				renderedButNotSelectedItemIds.remove(id);
			}
		}

		/* Add range items aka shift clicked multiselection areas */
		if (ranges != null) {
			for (String range : ranges) {
				String[] split = range.split("-");
				Object startItemId = itemIdMapper.get(split[0]);
				int length = Integer.valueOf(split[1]);
				LinkedHashSet<Object> itemIdsInRange = getItemIdsInRange(startItemId, length);
				newValue.addAll(itemIdsInRange);
				renderedButNotSelectedItemIds.removeAll(itemIdsInRange);
			}
		}
		/*
		 * finally clear all currently rendered rows (the ones that the client
		 * side counterpart is aware of) that the client didn't send as selected
		 */
		newValue.removeAll(renderedButNotSelectedItemIds);

		if (!isNullSelectionAllowed() && newValue.isEmpty()) {
			// empty selection not allowed, keep old value
			markAsDirty();
			return;
		}

		setValue(newValue, true);

	}

	/**
	 * Gets the currently rendered item ids.
	 *
	 * @return the currently rendered item ids
	 */
	private Set<Object> getCurrentlyRenderedItemIds() {
		HashSet<Object> ids = new HashSet<Object>();
		if (pageBuffer != null) {
			for (int i = 0; i < pageBuffer[CELL_ITEMID].length; i++) {
				ids.add(pageBuffer[CELL_ITEMID][i]);
			}
		}
		return ids;
	}

	/* Component basics */
	/**
	 * Invoked when the value of a variable has changed.
	 *
	 * @param source
	 *            the source
	 * @param variables
	 *            the variables
	 * @see com.vaadin.ui.Select#changeVariables(java.lang.Object,
	 *      java.util.Map)
	 */
	@Override
	public void changeVariables(Object source, Map<String, Object> variables) {
		if (variables.containsKey("isReConstruct")) {
			Object curr = variables.get("isReConstruct");
			if (curr != null) {
				reConstruct(Boolean.valueOf(curr.toString()));
			}
		}
		boolean clientNeedsContentRefresh = false;

		changeSingleHeaderVariables(source, variables);
		if (isDoubleHeaderVisible()) {
			changeDoubleHeaderVariables(source, variables);
		}
		if (isTripleHeaderVisible()) {
			changeTripleHeaderVariables(source, variables);
		}
		disableContentRefreshing();

		if (!isSelectable() && variables.containsKey("selected")) {
			// Not-selectable is a special case, AbstractSelect does not support
			// TODO could be optimized.
			variables = new HashMap<String, Object>(variables);
			variables.remove("selected");
		} /*
			 * The AbstractSelect cannot handle the multiselection properly,
			 * instead we handle it ourself
			 */ else if (isSelectable() && isMultiSelect() && variables.containsKey("selected")
				&& multiSelectMode == MultiSelectMode.DEFAULT) {
			handleSelectedItems(variables);
			variables = new HashMap<String, Object>(variables);
			variables.remove("selected");
		}

		super.changeVariables(source, variables);

		// Client might update the pagelength if CustomTable height is fixed
		if (variables.containsKey("pagelength")) {
			// Sets pageLength directly to avoid repaint that setter causes
			pageLength = (Integer) variables.get("pagelength");
		}

		// Page start index
		if (variables.containsKey("firstvisible")) {
			final Integer value = (Integer) variables.get("firstvisible");
			if (value != null) {
				setCurrentPageFirstItemIndex(value.intValue(), false);
			}
		}

		// Sets requested firstrow and rows for the next paint
		if (variables.containsKey("reqfirstrow") || variables.containsKey("reqrows")) {

			try {
				firstToBeRenderedInClient = ((Integer) variables.get("firstToBeRendered")).intValue();
				lastToBeRenderedInClient = ((Integer) variables.get("lastToBeRendered")).intValue();
			} catch (Exception e) {
				// FIXME: Handle exception
				getLogger().log(Level.FINER, "Could not parse the first and/or last rows.", e);
			}

			// respect suggested rows only if table is not otherwise updated
			// (row caches emptied by other event)
			if (!containerChangeToBeRendered) {
				Integer value = (Integer) variables.get("reqfirstrow");
				if (value != null) {
					reqFirstRowToPaint = value.intValue();
				}

				value = (Integer) variables.get("reqrows");
				if (value != null) {
					reqRowsToPaint = value.intValue();
					int size = size();
					// sanity check

					if (reqFirstRowToPaint >= size) {
						reqFirstRowToPaint = size;
					}

					if (reqFirstRowToPaint + reqRowsToPaint > size) {
						reqRowsToPaint = size - reqFirstRowToPaint;
					}
				}
			}
			if (getLogger().isLoggable(Level.FINEST)) {
				getLogger().log(Level.FINEST, "Client wants rows {0}-{1}",
						new Object[] { reqFirstRowToPaint, (reqFirstRowToPaint + reqRowsToPaint - 1) });
			}
			clientNeedsContentRefresh = true;
		}

		if (isSortEnabled()) {
			// Sorting
			boolean doSort = false;
			if (variables.containsKey("sortcolumn")) {
				final String colId = (String) variables.get("sortcolumn");
				if (colId != null && !"".equals(colId) && !"null".equals(colId)) {
					final Object id = columnIdMap.get(colId);
					setSortContainerPropertyId(id, false);
					doSort = true;
				}
			}
			if (variables.containsKey("sortascending")) {
				final boolean state = ((Boolean) variables.get("sortascending")).booleanValue();
				if (state != sortAscending) {
					setSortAscending(state, false);
					doSort = true;
				}
			}
			if (doSort) {
				this.sort();
				resetPageBuffer();
			}
		}

		// Dynamic column hide/show and order
		// Update visible columns
		if (isColumnCollapsingAllowed()) {
			if (changeSingleHeaderCollapsingVariables(variables)) {
				clientNeedsContentRefresh = true;
			}
			if (isDoubleHeaderVisible()) {
				if (changeDoubleHeaderCollapsingVariables(variables)) {
					clientNeedsContentRefresh = true;
				}
				if (isTripleHeaderVisible()) {
					if (changeTripleHeaderCollapsingVariables(variables)) {
						clientNeedsContentRefresh = true;
					}
				}
			}
		}
		if (isColumnReorderingAllowed()) {
			if (variables.containsKey("columnorder")) {
				try {
					final Object[] ids = (Object[]) variables.get("columnorder");
					// need a real Object[], ids can be a String[]
					final Object[] idsTemp = new Object[ids.length];
					for (int i = 0; i < ids.length; i++) {
						idsTemp[i] = columnIdMap.get(ids[i].toString());
					}
					setColumnOrder(idsTemp, true);
					if (hasListeners(ColumnReorderEvent.class)) {
						fireEvent(new ColumnReorderEvent(this));
					}
				} catch (final Exception e) {
					// FIXME: Handle exception
					getLogger().log(Level.FINER, "Could not determine column reordering state", e);
				}
				clientNeedsContentRefresh = true;
			}
		}

		enableContentRefreshing(clientNeedsContentRefresh);

		// Actions
		if (variables.containsKey("action")) {
			final StringTokenizer st = new StringTokenizer((String) variables.get("action"), ",");
			if (st.countTokens() == 2) {
				final Object itemId = itemIdMapper.get(st.nextToken());
				final Action action = actionMapper.get(st.nextToken());

				if (action != null && (itemId == null || containsId(itemId)) && actionHandlers != null) {
					for (Handler ah : actionHandlers) {
						ah.handleAction(action, this, itemId);
					}
				}
			}
		}

	}

	public boolean changeSingleHeaderCollapsingVariables(Map<String, Object> variables) {
		boolean contentRefresh = false;
		if (variables.containsKey("collapsedcolumns")) {
			try {
				final Object[] ids = (Object[]) variables.get("collapsedcolumns");
				Set<Object> idSet = new HashSet<Object>();
				for (Object id : ids) {
					idSet.add(columnIdMap.get(id.toString()));
				}
				for (final Iterator<Object> it = visibleColumns.iterator(); it.hasNext();) {
					Object propertyId = it.next();
					if (isColumnCollapsed(propertyId)) {
						if (!idSet.contains(propertyId)) {
							setColumnCollapsed(propertyId, false, true);
						}
					} else if (idSet.contains(propertyId)) {
						setColumnCollapsed(propertyId, true, true);
					}
				}
			} catch (final Exception e) {
				// FIXME: Handle exception
				getLogger().log(Level.FINER, "Could not determine column collapsing state", e);
			}
			contentRefresh = true;
		}
		return contentRefresh;
	}

	/**
	 * Change double header variables.
	 *
	 * @param source
	 *            the source
	 * @param variables
	 *            the variables
	 */
	public void changeSingleHeaderVariables(Object source, Map<String, Object> variables) {
		handleClickEvent(variables);
		handleColumnResizeEvent(variables);
		handleColumnWidthUpdates(variables);
		handleColumnCheckEvent(variables);
		handleCheckBoxUpdates(variables);
		handleColumnRadioCheckEvent(variables);
		handleColumnRadioUpdates(variables);
		handleColumnExpandIconEvent(variables);
		handleExpandIconUpdates(variables);
	}

	/**
	 * Handles click event.
	 *
	 * @param variables
	 *            the variables
	 */
	private void handleClickEvent(Map<String, Object> variables) {

		// Item click event
		if (variables.containsKey("clickEvent")) {
			String key = (String) variables.get("clickedKey");
			Object itemId = itemIdMapper.get(key);
			Object propertyId = null;
			String colkey = (String) variables.get("clickedColKey");
			// click is not necessary on a property
			if (colkey != null) {
				propertyId = columnIdMap.get(colkey);
			}
			MouseEventDetails evt = MouseEventDetails.deSerialize((String) variables.get("clickEvent"));
			Item item = getItem(itemId);
			if (item != null) {
				fireEvent(new ItemClickEvent(this, item, itemId, propertyId, evt));
			}
		} // Header click event
		else if (variables.containsKey("headerClickEvent")) {

			MouseEventDetails details = MouseEventDetails.deSerialize((String) variables.get("headerClickEvent"));

			Object cid = variables.get("headerClickCID");
			Object propertyId = null;
			if (cid != null) {
				propertyId = columnIdMap.get(cid.toString());
			}
			fireEvent(new HeaderClickEvent(this, propertyId, details));
		} // Footer click event
		else if (variables.containsKey("footerClickEvent")) {
			MouseEventDetails details = MouseEventDetails.deSerialize((String) variables.get("footerClickEvent"));

			Object cid = variables.get("footerClickCID");
			Object propertyId = null;
			if (cid != null) {
				propertyId = columnIdMap.get(cid.toString());
			}
			fireEvent(new FooterClickEvent(this, propertyId, details));
		}
	}

	/**
	 * Handles the column resize event sent by the client.
	 *
	 * @param variables
	 *            the variables
	 */
	private void handleColumnResizeEvent(Map<String, Object> variables) {
		if (variables.containsKey("columnResizeEventColumn")) {
			Object cid = variables.get("columnResizeEventColumn");
			Object propertyId = null;
			if (cid != null) {
				propertyId = columnIdMap.get(cid.toString());
				Object prev = variables.get("columnResizeEventPrev");
				int previousWidth = -1;
				if (prev != null) {
					previousWidth = Integer.valueOf(prev.toString());
				}

				Object curr = variables.get("columnResizeEventCurr");
				int currentWidth = -1;
				if (curr != null) {
					currentWidth = Integer.valueOf(curr.toString());
				}

				fireColumnResizeEvent(propertyId, previousWidth, currentWidth);
			}
		}
	}

	/**
	 * Fire column resize event.
	 *
	 * @param propertyId
	 *            the property id
	 * @param previousWidth
	 *            the previous width
	 * @param currentWidth
	 *            the current width
	 */
	private void fireColumnResizeEvent(Object propertyId, int previousWidth, int currentWidth) {
		/*
		 * Update the sizes on the server side. If a column previously had a
		 * expand ratio and the user resized the column then the expand ratio
		 * will be turned into a static pixel size.
		 */
		setColumnWidth(propertyId, currentWidth, true);

		fireEvent(new ColumnResizeEvent(this, propertyId, previousWidth, currentWidth));
	}

	/**
	 * Handle column width updates.
	 *
	 * @param variables
	 *            the variables
	 */
	private void handleColumnWidthUpdates(Map<String, Object> variables) {
		if (variables.containsKey("columnWidthUpdates")) {
			String[] events = (String[]) variables.get("columnWidthUpdates");
			for (String str : events) {
				String[] eventDetails = str.split(":");
				Object propertyId = columnIdMap.get(eventDetails[0]);
				if (propertyId == null) {
					propertyId = ROW_HEADER_FAKE_PROPERTY_ID;
				}
				int width = Integer.valueOf(eventDetails[1]);
				setColumnWidth(propertyId, width, true);
			}
		}
	}

	/**
	 * Go to mode where content updates are not done. This is due we want to
	 * bypass expensive content for some reason (like when we know we may have
	 * other content changes on their way).
	 *
	 * @return true if content refresh flag was enabled prior this call
	 */
	protected boolean disableContentRefreshing() {
		boolean wasDisabled = isContentRefreshesEnabled;
		isContentRefreshesEnabled = false;
		return wasDisabled;
	}

	/**
	 * Go to mode where content content refreshing has effect.
	 *
	 * @param refreshContent
	 *            true if content refresh needs to be done
	 */
	protected void enableContentRefreshing(boolean refreshContent) {
		isContentRefreshesEnabled = true;
		if (refreshContent) {
			refreshRenderedCells();
			// Ensure that client gets a response
			markAsDirty();
		}
	}

	/**
	 * Before client response.
	 *
	 * @param initial
	 *            the initial
	 */
	@Override
	public void beforeClientResponse(boolean initial) {
		super.beforeClientResponse(initial);

		// Ensure pageBuffer is filled before sending the response to avoid
		// calls to markAsDirty during paint
		getVisibleCells();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.ui.AbstractSelect#paintContent(com.vaadin.
	 * terminal.PaintTarget)
	 */
	/**
	 * Paint content.
	 *
	 * @param target
	 *            the target
	 * @throws PaintException
	 *             the paint exception
	 */
	@Override
	public void paintContent(PaintTarget target) throws PaintException {
		isBeingPainted = true;
		try {
			doPaintContent(target);
		} finally {
			isBeingPainted = false;
		}
	}

	/**
	 * Do paint content.
	 *
	 * @param target
	 *            the target
	 * @throws PaintException
	 *             the paint exception
	 */
	private void doPaintContent(PaintTarget target) throws PaintException {
		target.addAttribute("isReConstruct", isReConstruct());
		target.addAttribute("radioButtonSinks", isSinkHeadersRadioButtons());
		target.addAttribute("scrollPane", this);
		if (leftTable != null) {

			target.addAttribute("leftDependentTable", leftTable);
		}
		if (rightTable != null) {
			target.addAttribute("rightDependentTable", rightTable);
		}
		/*
		 * Body actions - Actions which has the target null and can be invoked
		 * by right clicking on the table body.
		 */
		final Set<Action> actionSet = findAndPaintBodyActions(target);

		final Object[][] cells = getVisibleCells();
		int rows = findNumRowsToPaint(target, cells);

		int total = size();
		if (shouldHideNullSelectionItem()) {
			total--;
			rows--;
		}

		// CustomTable attributes
		paintTableAttributes(target, rows, total);

		paintVisibleColumnOrder(target);

		// Rows
		if (isPartialRowUpdate() && painted && !target.isFullRepaint()) {
			paintPartialRowUpdate(target, actionSet);
		} else if (target.isFullRepaint() || isRowCacheInvalidated()) {
			paintRows(target, cells, actionSet);
			setRowCacheInvalidated(false);
		}

		/*
		 * Send the page buffer indexes to ensure that the client side stays in
		 * sync. Otherwise we _might_ have the situation where the client side
		 * discards too few or too many rows, causing out of sync issues.
		 */
		int pageBufferLastIndex = pageBufferFirstIndex + pageBuffer[CELL_ITEMID].length - 1;
		target.addAttribute(TableConstants.ATTRIBUTE_PAGEBUFFER_FIRST, pageBufferFirstIndex);
		target.addAttribute(TableConstants.ATTRIBUTE_PAGEBUFFER_LAST, pageBufferLastIndex);

		paintSorting(target);

		resetVariablesAndPageBuffer(target);

		// Actions
		paintActions(target, actionSet);

		paintColumnOrder(target);

		// Available columns
		paintAvailableColumns(target);
		paintColumnRadioButtons(target);
		paintVisibleColumns(target);

		/* Add DoubleHeader to UI Component */
		if (isDoubleHeaderVisible()) {
			paintDoubleHeaderVisibleColumnOrder(target);

			paintDoubleHeaderColumnOrder(target);
			paintDoubleHeaderAvailableColumns(target);
			paintDoubleHeaderColumnRadioButtons(target);
			paintDoubleHeaderVisibleColumns(target);
			paintDoubleHeaderVisibleColumnMap(target);
			if (isTripleHeaderVisible()) {

				paintTripleHeaderVisibleColumnOrder(target);
				paintTripleHeaderColumnOrder(target);
				paintTripleHeaderAvailableColumns(target);
				paintTripleHeaderColumnRadioButtons(target);
				paintTripleHeaderVisibleColumns(target);
				paintTripleHeaderVisibleColumnMap(target);
			}
		}

		if (keyMapperReset) {
			keyMapperReset = false;
			target.addAttribute(TableConstants.ATTRIBUTE_KEY_MAPPER_RESET, true);
		}

		if (dropHandler != null) {
			dropHandler.getAcceptCriterion().paint(target);
		}

		painted = true;
	}

	/**
	 * Sets the row cache invalidated.
	 *
	 * @param invalidated
	 *            the new row cache invalidated
	 */
	private void setRowCacheInvalidated(boolean invalidated) {
		rowCacheInvalidated = invalidated;
	}

	/**
	 * Checks if is row cache invalidated.
	 *
	 * @return true, if is row cache invalidated
	 */
	protected boolean isRowCacheInvalidated() {
		return rowCacheInvalidated;
	}

	/**
	 * Paint partial row update.
	 *
	 * @param target
	 *            the target
	 * @param actionSet
	 *            the action set
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintPartialRowUpdate(PaintTarget target, Set<Action> actionSet) throws PaintException {
		paintPartialRowUpdates(target, actionSet);
		paintPartialRowAdditions(target, actionSet);
	}

	/**
	 * Paint partial row updates.
	 *
	 * @param target
	 *            the target
	 * @param actionSet
	 *            the action set
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintPartialRowUpdates(PaintTarget target, Set<Action> actionSet) throws PaintException {
		final boolean[] iscomponent = findCellsWithComponents();

		int firstIx = getFirstUpdatedItemIndex();
		int count = getUpdatedRowCount();

		target.startTag("urows");
		target.addAttribute("firsturowix", firstIx);
		target.addAttribute("numurows", count);

		// Partial row updates bypass the normal caching mechanism.
		Object[][] cells = getVisibleCellsUpdateCacheRows(firstIx, count);
		for (int indexInRowbuffer = 0; indexInRowbuffer < count; indexInRowbuffer++) {
			final Object itemId = cells[CELL_ITEMID][indexInRowbuffer];

			if (shouldHideNullSelectionItem()) {
				// Remove null selection item if null selection is not allowed
				continue;
			}

			paintRow(target, cells, isEditable(), actionSet, iscomponent, indexInRowbuffer, itemId);
		}
		target.endTag("urows");
		maybeThrowCacheUpdateExceptions();
	}

	/**
	 * Paint partial row additions.
	 *
	 * @param target
	 *            the target
	 * @param actionSet
	 *            the action set
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintPartialRowAdditions(PaintTarget target, Set<Action> actionSet) throws PaintException {
		final boolean[] iscomponent = findCellsWithComponents();

		int firstIx = getFirstAddedItemIndex();
		int count = getAddedRowCount();

		target.startTag("prows");

		if (!shouldHideAddedRows()) {
			getLogger().log(Level.FINEST, "Paint rows for add. Index: {0}, count: {1}.",
					new Object[] { firstIx, count });

			// Partial row additions bypass the normal caching mechanism.
			Object[][] cells = getVisibleCellsInsertIntoCache(firstIx, count);
			if (cells[0].length < count) {
				// delete the rows below, since they will fall beyond the cache
				// page.
				target.addAttribute("delbelow", true);
				count = cells[0].length;
			}

			for (int indexInRowbuffer = 0; indexInRowbuffer < count; indexInRowbuffer++) {
				final Object itemId = cells[CELL_ITEMID][indexInRowbuffer];
				if (shouldHideNullSelectionItem()) {
					// Remove null selection item if null selection is not
					// allowed
					continue;
				}

				paintRow(target, cells, isEditable(), actionSet, iscomponent, indexInRowbuffer, itemId);
			}
		} else {
			getLogger().log(Level.FINEST, "Paint rows for remove. Index: {0}, count: {1}.",
					new Object[] { firstIx, count });
			removeRowsFromCacheAndFillBottom(firstIx, count);
			target.addAttribute("hide", true);
		}

		target.addAttribute("firstprowix", firstIx);
		target.addAttribute("numprows", count);
		target.endTag("prows");
		maybeThrowCacheUpdateExceptions();
	}

	/**
	 * Subclass and override this to enable partial row updates and additions,
	 * which bypass the normal caching mechanism. This is useful for e.g.
	 * TreeTable.
	 *
	 * @return true if this update is a partial row update, false if not. For
	 *         plain CustomTable it is always false.
	 */
	protected boolean isPartialRowUpdate() {
		return false;
	}

	/**
	 * Subclass and override this to enable partial row additions, bypassing the
	 * normal caching mechanism. This is useful for e.g. TreeTable, where
	 * expanding a node should only fetch and add the items inside of that node.
	 *
	 * @return The index of the first added item. For plain CustomTable it is
	 *         always 0.
	 */
	protected int getFirstAddedItemIndex() {
		return 0;
	}

	/**
	 * Subclass and override this to enable partial row additions, bypassing the
	 * normal caching mechanism. This is useful for e.g. TreeTable, where
	 * expanding a node should only fetch and add the items inside of that node.
	 *
	 * @return the number of rows to be added, starting at the index returned by
	 *         {@link #getFirstAddedItemIndex()}. For plain CustomTable it is
	 *         always 0.
	 */
	protected int getAddedRowCount() {
		return 0;
	}

	/**
	 * Subclass and override this to enable removing of rows, bypassing the
	 * normal caching and lazy loading mechanism. This is useful for e.g.
	 * TreeTable, when you need to hide certain rows as a node is collapsed.
	 *
	 * This should return true if the rows pointed to by
	 * {@link #getFirstAddedItemIndex()} and {@link #getAddedRowCount()} should
	 * be hidden instead of added.
	 *
	 * @return whether the rows to add (see {@link #getFirstAddedItemIndex()}
	 *         and {@link #getAddedRowCount()}) should be added or hidden. For
	 *         plain CustomTable it is always false.
	 */
	protected boolean shouldHideAddedRows() {
		return false;
	}

	/**
	 * Subclass and override this to enable partial row updates, bypassing the
	 * normal caching and lazy loading mechanism. This is useful for updating
	 * the state of certain rows, e.g. in the TreeTable the collapsed state of a
	 * single node is updated using this mechanism.
	 *
	 * @return the index of the first item to be updated. For plain CustomTable
	 *         it is always 0.
	 */
	protected int getFirstUpdatedItemIndex() {
		return 0;
	}

	/**
	 * Subclass and override this to enable partial row updates, bypassing the
	 * normal caching and lazy loading mechanism. This is useful for updating
	 * the state of certain rows, e.g. in the TreeTable the collapsed state of a
	 * single node is updated using this mechanism.
	 *
	 * @return the number of rows to update, starting at the index returned by
	 *         {@link #getFirstUpdatedItemIndex()}. For plain table it is always
	 *         0.
	 */
	protected int getUpdatedRowCount() {
		return 0;
	}

	/**
	 * Paint table attributes.
	 *
	 * @param target
	 *            the target
	 * @param rows
	 *            the rows
	 * @param total
	 *            the total
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintTableAttributes(PaintTarget target, int rows, int total) throws PaintException {
		paintTabIndex(target);
		paintDragMode(target);
		paintSelectMode(target);

		if (cacheRate != CACHE_RATE_DEFAULT) {
			target.addAttribute("cr", cacheRate);
		}

		target.addAttribute("cols", getVisibleColumns().length);
		target.addAttribute("rows", rows);

		target.addAttribute("firstrow", (reqFirstRowToPaint >= 0 ? reqFirstRowToPaint : firstToBeRenderedInClient));
		target.addAttribute("totalrows", total);
		if (getPageLength() != 0) {
			target.addAttribute("pagelength", getPageLength());
		}
		if (areColumnHeadersEnabled()) {
			target.addAttribute("colheaders", true);
		}
		if (rowHeadersAreEnabled()) {
			target.addAttribute("rowheaders", true);
		}

		target.addAttribute("colfooters", columnFootersVisible);

		// The cursors are only shown on pageable table
		// if (getCurrentPageFirstItemIndex() != 0 || getPageLength() > 0) {
		if (getCurrentPageFirstItemIndex() != 0 || getManualPageLength() > 0) {
			target.addVariable(this, "firstvisible", getCurrentPageFirstItemIndex());
			target.addVariable(this, "firstvisibleonlastpage", currentPageFirstItemIndexOnLastPage);
		}
	}

	/**
	 * Resets and paints "to be painted next" variables. Also reset pageBuffer
	 *
	 * @param target
	 *            the target
	 * @throws PaintException
	 *             the paint exception
	 */
	private void resetVariablesAndPageBuffer(PaintTarget target) throws PaintException {
		reqFirstRowToPaint = -1;
		reqRowsToPaint = -1;
		containerChangeToBeRendered = false;
		target.addVariable(this, "reqrows", reqRowsToPaint);
		target.addVariable(this, "reqfirstrow", reqFirstRowToPaint);
	}

	/**
	 * Are column headers enabled.
	 *
	 * @return true, if successful
	 */
	private boolean areColumnHeadersEnabled() {
		return getColumnHeaderMode() != ColumnHeaderMode.HIDDEN;
	}

	/**
	 * Paint visible columns.
	 *
	 * @param target
	 *            the target
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintVisibleColumns(PaintTarget target) throws PaintException {
		target.startTag("visiblecolumns");
		if (rowHeadersAreEnabled()) {
			target.startTag("column");
			target.addAttribute("cid", ROW_HEADER_COLUMN_KEY);
			paintColumnWidth(target, ROW_HEADER_FAKE_PROPERTY_ID);
			paintColumnExpandRatio(target, ROW_HEADER_FAKE_PROPERTY_ID);
			target.endTag("column");
		}
		final Collection<?> sortables = getSortableContainerPropertyIds();
		for (Object colId : visibleColumns) {
			if (colId != null) {
				target.startTag("column");
				target.addAttribute("cid", columnIdMap.key(colId));
				final String head = getColumnHeader(colId);
				target.addAttribute("caption", (head != null ? head : ""));
				final String foot = getColumnFooter(colId);
				target.addAttribute("fcaption", (foot != null ? foot : ""));
				if (isColumnCollapsed(colId)) {
					target.addAttribute("collapsed", true);
				}

				if (areColumnHeadersEnabled()) {
					if (getColumnIcon(colId) != null) {
						target.addAttribute("icon", getColumnIcon(colId));
					}
					if (sortables.contains(colId)) {
						target.addAttribute("sortable", true);
					}
				}
				if (isColumnCheckBoxVisible(colId)) {
					target.addAttribute("checkbox", getColumnCheckBox(colId));
					target.addAttribute("checkboxdisabled", getColumnCheckBoxDisable(colId));
				}
				if (getColumnRadioButtonName(colId) != null) {
					target.addAttribute("radio", getColumnRadioButtonName(colId));
					target.addAttribute("radiochecked",
							colId.toString().equals(getColumnRadioButtonValue(getColumnRadioButtonName(colId))));
					target.addAttribute("radiodisabled", getColumnRadioButtonDisable(colId));
				}
				if (isColumnExpandIcon(colId)) {
					target.addAttribute("eicon", getColumnExpandIcon(colId));
				}
				if (!Align.LEFT.equals(getColumnAlignment(colId))) {
					target.addAttribute("align", getColumnAlignment(colId).toString());
				}
				paintColumnWidth(target, colId);
				paintColumnExpandRatio(target, colId);
				target.endTag("column");
			}
		}
		target.endTag("visiblecolumns");
	}

	/**
	 * Paint available columns.
	 *
	 * @param target
	 *            the target
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintAvailableColumns(PaintTarget target) throws PaintException {
		if (columnCollapsingAllowed) {
			final HashSet<Object> collapsedCols = new HashSet<Object>();
			for (Object colId : visibleColumns) {
				if (isColumnCollapsed(colId)) {
					collapsedCols.add(colId);
				}
			}
			final String[] collapsedKeys = new String[collapsedCols.size()];
			int nextColumn = 0;
			for (Object colId : visibleColumns) {
				if (isColumnCollapsed(colId)) {
					collapsedKeys[nextColumn++] = columnIdMap.key(colId);
				}
			}
			target.addVariable(this, "collapsedcolumns", collapsedKeys);

			final String[] noncollapsibleKeys = new String[noncollapsibleColumns.size()];
			nextColumn = 0;
			for (Object colId : noncollapsibleColumns) {
				noncollapsibleKeys[nextColumn++] = columnIdMap.key(colId);
			}
			target.addVariable(this, "noncollapsiblecolumns", noncollapsibleKeys);
		}

	}

	/**
	 * Paint actions.
	 *
	 * @param target
	 *            the target
	 * @param actionSet
	 *            the action set
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintActions(PaintTarget target, final Set<Action> actionSet) throws PaintException {
		if (!actionSet.isEmpty()) {
			target.addVariable(this, "action", "");
			target.startTag("actions");
			for (Action a : actionSet) {
				target.startTag("action");
				if (a.getCaption() != null) {
					target.addAttribute("caption", a.getCaption());
				}
				if (a.getIcon() != null) {
					target.addAttribute("icon", a.getIcon());
				}
				target.addAttribute("key", actionMapper.key(a));
				target.endTag("action");
			}
			target.endTag("actions");
		}
	}

	/**
	 * Paint column order.
	 *
	 * @param target
	 *            the target
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintColumnOrder(PaintTarget target) throws PaintException {

		if (columnReorderingAllowed && !doubleHeadersVisible && !tripleHeadersVisible) {
			final String[] colorder = new String[visibleColumns.size()];
			int i = 0;
			for (Object colId : visibleColumns) {
				colorder[i++] = columnIdMap.key(colId);
			}
			target.addVariable(this, "columnorder", colorder);
		}
	}

	/**
	 * Paint sorting.
	 *
	 * @param target
	 *            the target
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintSorting(PaintTarget target) throws PaintException {
		// Sorting
		if (getContainerDataSource() instanceof Container.Sortable) {
			target.addVariable(this, "sortcolumn", columnIdMap.key(sortContainerPropertyId));
			target.addVariable(this, "sortascending", sortAscending);
		}
	}

	/**
	 * Paint rows.
	 *
	 * @param target
	 *            the target
	 * @param cells
	 *            the cells
	 * @param actionSet
	 *            the action set
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintRows(PaintTarget target, final Object[][] cells, final Set<Action> actionSet)
			throws PaintException {
		final boolean[] iscomponent = findCellsWithComponents();

		target.startTag("rows");
		// cells array contains all that are supposed to be visible on client,
		// but we'll start from the one requested by client
		int start = 0;
		if (reqFirstRowToPaint != -1 && firstToBeRenderedInClient != -1) {
			start = reqFirstRowToPaint - firstToBeRenderedInClient;
		}
		int end = cells[0].length;
		if (reqRowsToPaint != -1) {
			end = start + reqRowsToPaint;
		}
		// sanity check
		if (lastToBeRenderedInClient != -1 && lastToBeRenderedInClient < end) {
			end = lastToBeRenderedInClient + 1;
		}
		if (start > cells[CELL_ITEMID].length || start < 0) {
			start = 0;
		}
		if (end > cells[CELL_ITEMID].length) {
			end = cells[CELL_ITEMID].length;
		}

		for (int indexInRowbuffer = start; indexInRowbuffer < end; indexInRowbuffer++) {
			final Object itemId = cells[CELL_ITEMID][indexInRowbuffer];

			if (shouldHideNullSelectionItem()) {
				// Remove null selection item if null selection is not allowed
				continue;
			}

			paintRow(target, cells, isEditable(), actionSet, iscomponent, indexInRowbuffer, itemId);
		}
		target.endTag("rows");
	}

	/**
	 * Find cells with components.
	 *
	 * @return the boolean[]
	 */
	private boolean[] findCellsWithComponents() {
		final boolean[] isComponent = new boolean[visibleColumns.size()];
		int ix = 0;
		for (Object columnId : visibleColumns) {
			if (columnGenerators.containsKey(columnId)) {
				isComponent[ix++] = true;
			} else {
				final Class<?> colType = getType(columnId);
				isComponent[ix++] = colType != null && Component.class.isAssignableFrom(colType);
			}
		}
		return isComponent;
	}

	/**
	 * Paint visible column order.
	 *
	 * @param target
	 *            the target
	 */
	private void paintVisibleColumnOrder(PaintTarget target) {
		// Visible column order
		final ArrayList<String> visibleColOrder = new ArrayList<String>();
		for (Object columnId : visibleColumns) {
			if (!isColumnCollapsed(columnId)) {
				String s = columnIdMap.key(columnId);
				visibleColOrder.add(s);
			}
		}
		target.addAttribute("vcolorder", visibleColOrder.toArray());
	}

	/**
	 * Find and paint body actions.
	 *
	 * @param target
	 *            the target
	 * @return the sets the
	 */
	private Set<Action> findAndPaintBodyActions(PaintTarget target) {
		Set<Action> actionSet = new LinkedHashSet<Action>();
		if (actionHandlers != null) {
			final ArrayList<String> keys = new ArrayList<String>();
			for (Handler ah : actionHandlers) {
				// Getting actions for the null item, which in this case means
				// the body item
				final Action[] actions = ah.getActions(null, this);
				if (actions != null) {
					for (Action action : actions) {
						actionSet.add(action);
						keys.add(actionMapper.key(action));
					}
				}
			}
			target.addAttribute("alb", keys.toArray());
		}
		return actionSet;
	}

	/**
	 * Should hide null selection item.
	 *
	 * @return true, if successful
	 */
	private boolean shouldHideNullSelectionItem() {
		return !isNullSelectionAllowed() && getNullSelectionItemId() != null && containsId(getNullSelectionItemId());
	}

	/**
	 * Find num rows to paint.
	 *
	 * @param target
	 *            the target
	 * @param cells
	 *            the cells
	 * @return the int
	 * @throws PaintException
	 *             the paint exception
	 */
	private int findNumRowsToPaint(PaintTarget target, final Object[][] cells) throws PaintException {
		int rows;
		if (reqRowsToPaint >= 0) {
			rows = reqRowsToPaint;
		} else {
			rows = cells[0].length;
			if (alwaysRecalculateColumnWidths) {
				// TODO experimental feature for now: tell the client to
				// recalculate column widths.
				// We'll only do this for paints that do not originate from
				// table scroll/cache requests (i.e when reqRowsToPaint<0)
				target.addAttribute("recalcWidths", true);
			}
		}
		return rows;
	}

	/**
	 * Paint select mode.
	 *
	 * @param target
	 *            the target
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintSelectMode(PaintTarget target) throws PaintException {
		if (multiSelectMode != MultiSelectMode.DEFAULT) {
			target.addAttribute("multiselectmode", multiSelectMode.ordinal());
		}
		if (isSelectable()) {
			target.addAttribute("selectmode", (isMultiSelect() ? "multi" : "single"));
		} else {
			target.addAttribute("selectmode", "none");
		}
		if (!isNullSelectionAllowed()) {
			target.addAttribute("nsa", false);
		}

		// selection support
		// The select variable is only enabled if selectable
		if (isSelectable()) {
			target.addVariable(this, "selected", findSelectedKeys());
		}
	}

	/**
	 * Find selected keys.
	 *
	 * @return the string[]
	 */
	private String[] findSelectedKeys() {
		LinkedList<String> selectedKeys = new LinkedList<String>();
		if (isMultiSelect()) {
			HashSet<?> sel = new HashSet<Object>((Set<?>) getValue());
			Collection<?> vids = getVisibleItemIds();
			for (Iterator<?> it = vids.iterator(); it.hasNext();) {
				Object id = it.next();
				if (sel.contains(id)) {
					selectedKeys.add(itemIdMapper.key(id));
				}
			}
		} else {
			Object value = getValue();
			if (value == null) {
				value = getNullSelectionItemId();
			}
			if (value != null) {
				selectedKeys.add(itemIdMapper.key(value));
			}
		}
		return selectedKeys.toArray(new String[selectedKeys.size()]);
	}

	/**
	 * Paint drag mode.
	 *
	 * @param target
	 *            the target
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintDragMode(PaintTarget target) throws PaintException {
		if (dragMode != TableDragMode.NONE) {
			target.addAttribute("dragmode", dragMode.ordinal());
		}
	}

	/**
	 * Paint tab index.
	 *
	 * @param target
	 *            the target
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintTabIndex(PaintTarget target) throws PaintException {
		// The tab ordering number
		if (getTabIndex() > 0) {
			target.addAttribute("tabindex", getTabIndex());
		}
	}

	/**
	 * Paint column width.
	 *
	 * @param target
	 *            the target
	 * @param columnId
	 *            the column id
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintColumnWidth(PaintTarget target, final Object columnId) throws PaintException {
		if (columnWidths.containsKey(columnId)) {
			target.addAttribute("width", getColumnWidth(columnId));
		}
	}

	/**
	 * Paint column expand ratio.
	 *
	 * @param target
	 *            the target
	 * @param columnId
	 *            the column id
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintColumnExpandRatio(PaintTarget target, final Object columnId) throws PaintException {
		if (columnExpandRatios.containsKey(columnId)) {
			target.addAttribute("er", getColumnExpandRatio(columnId));
		}
	}

	/**
	 * Row headers are enabled.
	 *
	 * @return true, if successful
	 */
	private boolean rowHeadersAreEnabled() {
		return getRowHeaderMode() != ROW_HEADER_MODE_HIDDEN;
	}

	/**
	 * Paint row.
	 *
	 * @param target
	 *            the target
	 * @param cells
	 *            the cells
	 * @param iseditable
	 *            the iseditable
	 * @param actionSet
	 *            the action set
	 * @param iscomponent
	 *            the iscomponent
	 * @param indexInRowbuffer
	 *            the index in rowbuffer
	 * @param itemId
	 *            the item id
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintRow(PaintTarget target, final Object[][] cells, final boolean iseditable,
			final Set<Action> actionSet, final boolean[] iscomponent, int indexInRowbuffer, final Object itemId)
			throws PaintException {
		target.startTag("tr");

		paintRowAttributes(target, cells, actionSet, indexInRowbuffer, itemId);

		// cells
		int currentColumn = 0;
		for (final Iterator<Object> it = visibleColumns.iterator(); it.hasNext(); currentColumn++) {
			final Object columnId = it.next();
			if (columnId == null || isColumnCollapsed(columnId)) {
				continue;
			}
			/*
			 * For each cell, if a cellStyleGenerator is specified, get the
			 * specific style for the cell. If there is any, add it to the
			 * target.
			 */
			if (cellStyleGenerator != null) {
				String cellStyle = cellStyleGenerator.getStyle(this, itemId, columnId);
				if (cellStyle != null && !cellStyle.equals("")) {
					target.addAttribute("style-" + columnIdMap.key(columnId), cellStyle);
				}
			}

			if ((iscomponent[currentColumn] || iseditable || cells[CELL_GENERATED_ROW][indexInRowbuffer] != null)
					&& Component.class.isInstance(cells[CELL_FIRSTCOL + currentColumn][indexInRowbuffer])) {
				final Component c = (Component) cells[CELL_FIRSTCOL + currentColumn][indexInRowbuffer];
				if (c == null || !LegacyCommunicationManager.isComponentVisibleToClient(c)) {
					target.addText("");
				} else {
					LegacyPaint.paint(c, target);
				}
			} else {
				target.addText((String) cells[CELL_FIRSTCOL + currentColumn][indexInRowbuffer]);
			}
			paintCellTooltips(target, itemId, columnId);
		}

		target.endTag("tr");
	}

	/**
	 * Paint cell tooltips.
	 *
	 * @param target
	 *            the target
	 * @param itemId
	 *            the item id
	 * @param columnId
	 *            the column id
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintCellTooltips(PaintTarget target, Object itemId, Object columnId) throws PaintException {
		if (itemDescriptionGenerator != null) {
			String itemDescription = itemDescriptionGenerator.generateDescription(this, itemId, columnId);
			if (itemDescription != null && !itemDescription.equals("")) {
				target.addAttribute("descr-" + columnIdMap.key(columnId), itemDescription);
			}
		}
	}

	/**
	 * Paint row tooltips.
	 *
	 * @param target
	 *            the target
	 * @param itemId
	 *            the item id
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintRowTooltips(PaintTarget target, Object itemId) throws PaintException {
		if (itemDescriptionGenerator != null) {
			String rowDescription = itemDescriptionGenerator.generateDescription(this, itemId, null);
			if (rowDescription != null && !rowDescription.equals("")) {
				target.addAttribute("rowdescr", rowDescription);
			}
		}
	}

	/**
	 * Paint row attributes.
	 *
	 * @param target
	 *            the target
	 * @param cells
	 *            the cells
	 * @param actionSet
	 *            the action set
	 * @param indexInRowbuffer
	 *            the index in rowbuffer
	 * @param itemId
	 *            the item id
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintRowAttributes(PaintTarget target, final Object[][] cells, final Set<Action> actionSet,
			int indexInRowbuffer, final Object itemId) throws PaintException {
		// tr attributes

		paintRowIcon(target, cells, indexInRowbuffer);
		paintRowHeader(target, cells, indexInRowbuffer);
		paintGeneratedRowInfo(target, cells, indexInRowbuffer);
		target.addAttribute("key", Integer.parseInt(cells[CELL_KEY][indexInRowbuffer].toString()));

		if (isSelected(itemId)) {
			target.addAttribute("selected", true);
		}

		// Actions
		if (actionHandlers != null) {
			final ArrayList<String> keys = new ArrayList<String>();
			for (Handler ah : actionHandlers) {
				final Action[] aa = ah.getActions(itemId, this);
				if (aa != null) {
					for (int ai = 0; ai < aa.length; ai++) {
						final String key = actionMapper.key(aa[ai]);
						actionSet.add(aa[ai]);
						keys.add(key);
					}
				}
			}
			target.addAttribute("al", keys.toArray());
		}

		/*
		 * For each row, if a cellStyleGenerator is specified, get the specific
		 * style for the cell, using null as propertyId. If there is any, add it
		 * to the target.
		 */
		if (cellStyleGenerator != null) {
			String rowStyle = cellStyleGenerator.getStyle(this, itemId, null);
			if (rowStyle != null && !rowStyle.equals("")) {
				target.addAttribute("rowstyle", rowStyle);
			}
		}

		paintRowTooltips(target, itemId);

		paintRowAttributes(target, itemId);
	}

	/**
	 * Paint generated row info.
	 *
	 * @param target
	 *            the target
	 * @param cells
	 *            the cells
	 * @param indexInRowBuffer
	 *            the index in row buffer
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintGeneratedRowInfo(PaintTarget target, Object[][] cells, int indexInRowBuffer)
			throws PaintException {
		GeneratedRow generatedRow = (GeneratedRow) cells[CELL_GENERATED_ROW][indexInRowBuffer];
		if (generatedRow != null) {
			target.addAttribute("gen_html", generatedRow.isHtmlContentAllowed());
			target.addAttribute("gen_span", generatedRow.isSpanColumns());
			target.addAttribute("gen_widget", generatedRow.getValue() instanceof Component);
		}
	}

	/**
	 * Paint row header.
	 *
	 * @param target
	 *            the target
	 * @param cells
	 *            the cells
	 * @param indexInRowbuffer
	 *            the index in rowbuffer
	 * @throws PaintException
	 *             the paint exception
	 */
	protected void paintRowHeader(PaintTarget target, Object[][] cells, int indexInRowbuffer) throws PaintException {
		if (rowHeadersAreEnabled()) {
			if (cells[CELL_HEADER][indexInRowbuffer] != null) {
				target.addAttribute("caption", (String) cells[CELL_HEADER][indexInRowbuffer]);
			}
		}

	}

	/**
	 * Paint row icon.
	 *
	 * @param target
	 *            the target
	 * @param cells
	 *            the cells
	 * @param indexInRowbuffer
	 *            the index in rowbuffer
	 * @throws PaintException
	 *             the paint exception
	 */
	protected void paintRowIcon(PaintTarget target, final Object[][] cells, int indexInRowbuffer)
			throws PaintException {
		if (rowHeadersAreEnabled() && cells[CELL_ICON][indexInRowbuffer] != null) {
			// target.addAttribute("icon",
			// (Resource) cells[CELL_ICON][indexInRowbuffer]);
		}
	}

	/**
	 * A method where extended CustomTable implementations may add their custom
	 * attributes for rows.
	 *
	 * @param target
	 *            the target
	 * @param itemId
	 *            the item id
	 * @throws PaintException
	 *             the paint exception
	 */
	protected void paintRowAttributes(PaintTarget target, Object itemId) throws PaintException {
	}

	/**
	 * Gets the cached visible table contents.
	 *
	 * @return the cached visible table contents.
	 */
	private Object[][] getVisibleCells() {
		if (pageBuffer == null) {
			refreshRenderedCells();
		}
		return pageBuffer;
	}

	/**
	 * Gets the value of property.
	 *
	 * By default if the table is editable the fieldFactory is used to create
	 * editors for table cells. Otherwise formatPropertyValue is used to format
	 * the value representation.
	 *
	 * @param rowId
	 *            the Id of the row (same as item Id).
	 * @param colId
	 *            the Id of the column.
	 * @param property
	 *            the Property to be presented.
	 * @return Object Either formatted value or Component for field.
	 * @see #setTableFieldFactory(TableFieldFactory)
	 */
	protected Object getPropertyValue(Object rowId, Object colId, Property property) {
		if (isEditable() && fieldFactory != null) {
			final Field<?> f = fieldFactory.createField(getContainerDataSource(), rowId, colId, this);
			if (f != null) {
				// Remember that we have made this association so we can remove
				// it when the component is removed
				associatedProperties.put(f, property);
				bindPropertyToField(rowId, colId, property, f);
				return f;
			}
		}

		return formatPropertyValue(rowId, colId, property);
	}

	/**
	 * Binds an item property to a field generated by TableFieldFactory. The
	 * default behavior is to bind property straight to Field. If
	 * Property.Viewer type property (e.g. PropertyFormatter) is already set for
	 * field, the property is bound to that Property.Viewer.
	 *
	 * @param rowId
	 *            the row id
	 * @param colId
	 *            the col id
	 * @param property
	 *            the property
	 * @param field
	 *            the field
	 * @since 6.7.3
	 */
	protected void bindPropertyToField(Object rowId, Object colId, Property property, Field field) {
		// check if field has a property that is Viewer set. In that case we
		// expect developer has e.g. PropertyFormatter that he wishes to use and
		// assign the property to the Viewer instead.
		boolean hasFilterProperty = field.getPropertyDataSource() != null
				&& (field.getPropertyDataSource() instanceof Property.Viewer);
		if (hasFilterProperty) {
			((Property.Viewer) field.getPropertyDataSource()).setPropertyDataSource(property);
		} else {
			field.setPropertyDataSource(property);
		}
	}

	/**
	 * Formats table cell property values. By default the property.toString()
	 * and return a empty string for null properties.
	 *
	 * @param rowId
	 *            the Id of the row (same as item Id).
	 * @param colId
	 *            the Id of the column.
	 * @param property
	 *            the Property to be formatted.
	 * @return the String representation of property and its value.
	 * @since 3.1
	 */
	protected String formatPropertyValue(Object rowId, Object colId, Property<?> property) {
		if (property == null) {
			return "";
		}
		Converter<String, Object> converter = null;

		if (hasConverter(colId)) {
			converter = getConverter(colId);
		} else {
			converter = (Converter) ConverterUtil.getConverter(String.class, property.getType(), getSession());
		}
		Object value = property.getValue();
		if (converter != null) {
			return converter.convertToPresentation(value, String.class, getLocale());
		}
		return (null != value) ? value.toString() : "";
	}

	/* Action container */
	/**
	 * Registers a new action handler for this container.
	 *
	 * @param actionHandler
	 *            the action handler
	 * @see com.vaadin.event.Action.Container#addActionHandler(Action.Handler)
	 */
	@Override
	public void addActionHandler(Action.Handler actionHandler) {

		if (actionHandler != null) {

			if (actionHandlers == null) {
				actionHandlers = new LinkedList<Handler>();
				actionMapper = new KeyMapper<Action>();
			}

			if (!actionHandlers.contains(actionHandler)) {
				actionHandlers.add(actionHandler);
				// Assures the visual refresh. No need to reset the page buffer
				// before as the content has not changed, only the action
				// handlers.
				refreshRenderedCells();
			}

		}
	}

	/**
	 * Removes a previously registered action handler for the contents of this
	 * container.
	 *
	 * @param actionHandler
	 *            the action handler
	 * @see com.vaadin.event.Action.Container#removeActionHandler(Action.Handler)
	 */
	@Override
	public void removeActionHandler(Action.Handler actionHandler) {

		if (actionHandlers != null && actionHandlers.contains(actionHandler)) {

			actionHandlers.remove(actionHandler);

			if (actionHandlers.isEmpty()) {
				actionHandlers = null;
				actionMapper = null;
			}

			// Assures the visual refresh. No need to reset the page buffer
			// before as the content has not changed, only the action
			// handlers.
			refreshRenderedCells();
		}
	}

	/**
	 * Removes all action handlers.
	 */
	public void removeAllActionHandlers() {
		actionHandlers = null;
		actionMapper = null;
		// Assures the visual refresh. No need to reset the page buffer
		// before as the content has not changed, only the action
		// handlers.
		refreshRenderedCells();
	}

	/* Property value change listening support */
	/**
	 * Notifies this listener that the Property's value has changed.
	 *
	 * Also listens changes in rendered items to refresh content area.
	 *
	 * @param event
	 *            the event
	 * @see com.vaadin.data.Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 */
	@Override
	public void valueChange(Property.ValueChangeEvent event) {
		if (equals(event.getProperty()) || event.getProperty() == getPropertyDataSource()) {
			super.valueChange(event);
		} else {
			refreshRowCache();
			containerChangeToBeRendered = true;
		}
		markAsDirty();
	}

	/**
	 * Clears the current page buffer. Call this before
	 * {@link #refreshRenderedCells()} to ensure that all content is updated
	 * from the properties.
	 */
	protected void resetPageBuffer() {
		firstToBeRenderedInClient = -1;
		lastToBeRenderedInClient = -1;
		reqFirstRowToPaint = -1;
		reqRowsToPaint = -1;
		pageBuffer = null;
	}

	/**
	 * Notifies the component that it is connected to an application.
	 *
	 * @see com.vaadin.ui.Component#attach()
	 */
	@Override
	public void attach() {
		super.attach();

		refreshRenderedCells();
	}

	/**
	 * Notifies the component that it is detached from the application.
	 *
	 * @see com.vaadin.ui.Component#detach()
	 */
	@Override
	public void detach() {
		super.detach();
	}

	/**
	 * Removes all Items from the Container.
	 *
	 * @return true, if successful
	 * @see com.vaadin.data.Container#removeAllItems()
	 */
	@Override
	public boolean removeAllItems() {
		currentPageFirstItemId = null;
		currentPageFirstItemIndex = 0;
		return super.removeAllItems();
	}

	/**
	 * Removes the Item identified by <code>ItemId</code> from the Container.
	 *
	 * @param itemId
	 *            the item id
	 * @return true, if successful
	 * @see com.vaadin.data.Container#removeItem(Object)
	 */
	@Override
	public boolean removeItem(Object itemId) {
		final Object nextItemId = nextItemId(itemId);
		final boolean ret = super.removeItem(itemId);
		if (ret && (itemId != null) && (itemId.equals(currentPageFirstItemId))) {
			currentPageFirstItemId = nextItemId;
		}
		if (!(items instanceof Container.ItemSetChangeNotifier)) {
			refreshRowCache();
		}
		return ret;
	}

	/**
	 * Removes a Property specified by the given Property ID from the Container.
	 *
	 * @param propertyId
	 *            the property id
	 * @return true, if successful
	 * @throws UnsupportedOperationException
	 *             the unsupported operation exception
	 * @see com.vaadin.data.Container#removeContainerProperty(Object)
	 */
	@Override
	public boolean removeContainerProperty(Object propertyId) throws UnsupportedOperationException {

		// If a visible property is removed, remove the corresponding column
		visibleColumns.remove(propertyId);
		columnAlignments.remove(propertyId);
		columnIcons.remove(propertyId);
		columnHeaders.remove(propertyId);
		columnFooters.remove(propertyId);
		// If a propertyValueConverter was defined for the property, remove it.
		propertyValueConverters.remove(propertyId);

		return super.removeContainerProperty(propertyId);
	}

	/**
	 * Adds a new property to the table and show it as a visible column.
	 *
	 * @param propertyId
	 *            the Id of the proprty.
	 * @param type
	 *            the class of the property.
	 * @param defaultValue
	 *            the default value given for all existing items.
	 * @return true, if successful
	 * @throws UnsupportedOperationException
	 *             the unsupported operation exception
	 * @see com.vaadin.data.Container#addContainerProperty(Object, Class,
	 *      Object)
	 */
	@Override
	public boolean addContainerProperty(Object propertyId, Class<?> type, Object defaultValue)
			throws UnsupportedOperationException {

		boolean visibleColAdded = false;
		if (!visibleColumns.contains(propertyId)) {
			visibleColumns.add(propertyId);
			visibleColAdded = true;
		}

		if (!super.addContainerProperty(propertyId, type, defaultValue)) {
			if (visibleColAdded) {
				visibleColumns.remove(propertyId);
			}
			return false;
		}
		if (!(items instanceof Container.PropertySetChangeNotifier)) {
			refreshRowCache();
		}
		return true;
	}

	/**
	 * Adds a new property to the table and show it as a visible column.
	 *
	 * @param propertyId
	 *            the Id of the proprty
	 * @param type
	 *            the class of the property
	 * @param defaultValue
	 *            the default value given for all existing items
	 * @param columnHeader
	 *            the Explicit header of the column. If explicit header is not
	 *            needed, this should be set null.
	 * @param columnIcon
	 *            the Icon of the column. If icon is not needed, this should be
	 *            set null.
	 * @param columnAlignment
	 *            the Alignment of the column. Null implies align left.
	 * @return true, if successful
	 * @throws UnsupportedOperationException
	 *             if the operation is not supported.
	 * @see com.vaadin.data.Container#addContainerProperty(Object, Class,
	 *      Object)
	 */
	public boolean addContainerProperty(Object propertyId, Class<?> type, Object defaultValue, String columnHeader,
			Resource columnIcon, Align columnAlignment) throws UnsupportedOperationException {
		if (!this.addContainerProperty(propertyId, type, defaultValue)) {
			return false;
		}
		setColumnAlignment(propertyId, columnAlignment);
		setColumnHeader(propertyId, columnHeader);
		setColumnIcon(propertyId, columnIcon);
		return true;
	}

	/**
	 * Adds a generated column to the CustomTable.
	 * <p>
	 * A generated column is a column that exists only in the CustomTable, not
	 * as a property in the underlying Container. It shows up just as a regular
	 * column.
	 * </p>
	 * <p>
	 * A generated column will override a property with the same id, so that the
	 * generated column is shown instead of the column representing the
	 * property. Note that getContainerProperty() will still get the real
	 * property.
	 * </p>
	 * <p>
	 * CustomTable will not listen to value change events from properties
	 * overridden by generated columns. If the content of your generated column
	 * depends on properties that are not directly visible in the table, attach
	 * value change listener to update the content on all depended properties.
	 * Otherwise your UI might not get updated as expected.
	 * </p>
	 * <p>
	 * Also note that getVisibleColumns() will return the generated columns,
	 * while getContainerPropertyIds() will not.
	 * </p>
	 *
	 * @param id
	 *            the id of the column to be added
	 * @param generatedColumn
	 *            the {@link ColumnGenerator} to use for this column
	 */
	public void addGeneratedColumn(Object id, ColumnGenerator generatedColumn) {
		if (generatedColumn == null) {
			throw new IllegalArgumentException("Can not add null as a GeneratedColumn");
		}
		if (columnGenerators.containsKey(id)) {
			throw new IllegalArgumentException("Can not add the same GeneratedColumn twice, id:" + id);
		} else {
			columnGenerators.put(id, generatedColumn);
			/*
			 * add to visible column list unless already there (overriding
			 * column from DS)
			 */
			if (!visibleColumns.contains(id)) {
				visibleColumns.add(id);
			}
			refreshRowCache();
		}
	}

	/**
	 * Returns the ColumnGenerator used to generate the given column.
	 *
	 * @param columnId
	 *            The id of the generated column
	 * @return The ColumnGenerator used for the given columnId or null.
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 */
	public ColumnGenerator getColumnGenerator(Object columnId) throws IllegalArgumentException {
		return columnGenerators.get(columnId);
	}

	/**
	 * Removes a generated column previously added with addGeneratedColumn.
	 *
	 * @param columnId
	 *            id of the generated column to remove
	 * @return true if the column could be removed (existed in the CustomTable)
	 */
	public boolean removeGeneratedColumn(Object columnId) {
		if (columnGenerators.containsKey(columnId)) {
			columnGenerators.remove(columnId);
			// remove column from visibleColumns list unless it exists in
			// container (generator previously overrode this column)
			if (!items.getContainerPropertyIds().contains(columnId)) {
				visibleColumns.remove(columnId);
			}
			refreshRowCache();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns item identifiers of the items which are currently rendered on the
	 * client.
	 * <p>
	 * Note, that some due to historical reasons the name of the method is bit
	 * misleading. Some items may be partly or totally out of the viewport of
	 * the table's scrollable area. Actually detecting rows which can be
	 * actually seen by the end user may be problematic due to the client server
	 * architecture. Using {@link #getCurrentPageFirstItemId()} combined with
	 * {@link #getPageLength()} may produce good enough estimates in some
	 * situations.
	 *
	 * @return the visible item ids
	 * @see com.vaadin.ui.Select#getVisibleItemIds()
	 */
	@Override
	public Collection<?> getVisibleItemIds() {

		final LinkedList<Object> visible = new LinkedList<Object>();

		final Object[][] cells = getVisibleCells();
		// may be null if the table has not been rendered yet (e.g. not attached
		// to a layout)
		if (null != cells) {
			for (int i = 0; i < cells[CELL_ITEMID].length; i++) {
				visible.add(cells[CELL_ITEMID][i]);
			}
		}

		return visible;
	}

	/**
	 * Container datasource item set change. CustomTable must flush its buffers
	 * on change.
	 *
	 * @param event
	 *            the event
	 * @see com.vaadin.data.Container.ItemSetChangeListener#containerItemSetChange(com.vaadin.data.Container.ItemSetChangeEvent)
	 */
	@Override
	public void containerItemSetChange(Container.ItemSetChangeEvent event) {
		if (isBeingPainted) {
			return;
		}

		super.containerItemSetChange(event);

		// super method clears the key map, must inform client about this to
		// avoid getting invalid keys back (#8584)
		keyMapperReset = true;

		// ensure that page still has first item in page, ignore buffer refresh
		// (forced in this method)
		setCurrentPageFirstItemIndex(getCurrentPageFirstItemIndex(), false);
		refreshRowCache();
	}

	/**
	 * Container datasource property set change. CustomTable must flush its
	 * buffers on change.
	 *
	 * @param event
	 *            the event
	 * @see com.vaadin.data.Container.PropertySetChangeListener#containerPropertySetChange(com.vaadin.data.Container.PropertySetChangeEvent)
	 */
	@Override
	public void containerPropertySetChange(Container.PropertySetChangeEvent event) {
		if (isBeingPainted) {
			return;
		}

		disableContentRefreshing();
		super.containerPropertySetChange(event);

		// sanitetize visibleColumns. note that we are not adding previously
		// non-existing properties as columns
		Collection<?> containerPropertyIds = getContainerDataSource().getContainerPropertyIds();

		LinkedList<Object> newVisibleColumns = new LinkedList<Object>(visibleColumns);
		for (Iterator<Object> iterator = newVisibleColumns.iterator(); iterator.hasNext();) {
			Object id = iterator.next();
			if (!(containerPropertyIds.contains(id) || columnGenerators.containsKey(id))) {
				iterator.remove();
			}
		}
		setVisibleColumns(newVisibleColumns.toArray());
		// same for collapsed columns
		for (Iterator<Object> iterator = collapsedColumns.iterator(); iterator.hasNext();) {
			Object id = iterator.next();
			if (!(containerPropertyIds.contains(id) || columnGenerators.containsKey(id))) {
				iterator.remove();
			}
		}

		resetPageBuffer();
		enableContentRefreshing(true);
	}

	/**
	 * Adding new items is not supported.
	 *
	 * @param allowNewOptions
	 *            the new new items allowed
	 * @throws UnsupportedOperationException
	 *             if set to true.
	 * @see com.vaadin.ui.Select#setNewItemsAllowed(boolean)
	 */
	@Override
	public void setNewItemsAllowed(boolean allowNewOptions) throws UnsupportedOperationException {
		if (allowNewOptions) {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * Gets the ID of the Item following the Item that corresponds to itemId.
	 *
	 * @param itemId
	 *            the item id
	 * @return the object
	 * @see com.vaadin.data.Container.Ordered#nextItemId(java.lang.Object)
	 */
	@Override
	public Object nextItemId(Object itemId) {
		return ((Container.Ordered) items).nextItemId(itemId);
	}

	/**
	 * Gets the ID of the Item preceding the Item that corresponds to the
	 * itemId.
	 *
	 * @param itemId
	 *            the item id
	 * @return the object
	 * @see com.vaadin.data.Container.Ordered#prevItemId(java.lang.Object)
	 */
	@Override
	public Object prevItemId(Object itemId) {
		return ((Container.Ordered) items).prevItemId(itemId);
	}

	/**
	 * Gets the ID of the first Item in the Container.
	 *
	 * @return the object
	 * @see com.vaadin.data.Container.Ordered#firstItemId()
	 */
	@Override
	public Object firstItemId() {
		return ((Container.Ordered) items).firstItemId();
	}

	/**
	 * Gets the ID of the last Item in the Container.
	 *
	 * @return the object
	 * @see com.vaadin.data.Container.Ordered#lastItemId()
	 */
	@Override
	public Object lastItemId() {
		return ((Container.Ordered) items).lastItemId();
	}

	/**
	 * Tests if the Item corresponding to the given Item ID is the first Item in
	 * the Container.
	 *
	 * @param itemId
	 *            the item id
	 * @return true, if is first id
	 * @see com.vaadin.data.Container.Ordered#isFirstId(java.lang.Object)
	 */
	@Override
	public boolean isFirstId(Object itemId) {
		return ((Container.Ordered) items).isFirstId(itemId);
	}

	/**
	 * Tests if the Item corresponding to the given Item ID is the last Item in
	 * the Container.
	 *
	 * @param itemId
	 *            the item id
	 * @return true, if is last id
	 * @see com.vaadin.data.Container.Ordered#isLastId(java.lang.Object)
	 */
	@Override
	public boolean isLastId(Object itemId) {
		return ((Container.Ordered) items).isLastId(itemId);
	}

	/**
	 * Adds new item after the given item.
	 *
	 * @param previousItemId
	 *            the previous item id
	 * @return the object
	 * @throws UnsupportedOperationException
	 *             the unsupported operation exception
	 * @see com.vaadin.data.Container.Ordered#addItemAfter(java.lang.Object)
	 */
	@Override
	public Object addItemAfter(Object previousItemId) throws UnsupportedOperationException {
		Object itemId = ((Container.Ordered) items).addItemAfter(previousItemId);
		if (!(items instanceof Container.ItemSetChangeNotifier)) {
			refreshRowCache();
		}
		return itemId;
	}

	/**
	 * Adds new item after the given item.
	 *
	 * @param previousItemId
	 *            the previous item id
	 * @param newItemId
	 *            the new item id
	 * @return the item
	 * @throws UnsupportedOperationException
	 *             the unsupported operation exception
	 * @see com.vaadin.data.Container.Ordered#addItemAfter(java.lang.Object,
	 *      java.lang.Object)
	 */
	@Override
	public Item addItemAfter(Object previousItemId, Object newItemId) throws UnsupportedOperationException {
		Item item = ((Container.Ordered) items).addItemAfter(previousItemId, newItemId);
		if (!(items instanceof Container.ItemSetChangeNotifier)) {
			refreshRowCache();
		}
		return item;
	}

	/**
	 * Sets the TableFieldFactory that is used to create editor for table cells.
	 *
	 * The TableFieldFactory is only used if the CustomTable is editable. By
	 * default the DefaultFieldFactory is used.
	 *
	 * @param fieldFactory
	 *            the field factory to set.
	 * @see #isEditable
	 * @see DefaultFieldFactory
	 */
	public void setTableFieldFactory(TableFieldFactory fieldFactory) {
		this.fieldFactory = fieldFactory;

		// Assure visual refresh
		refreshRowCache();
	}

	/**
	 * Gets the TableFieldFactory that is used to create editor for table cells.
	 *
	 * The FieldFactory is only used if the CustomTable is editable.
	 *
	 * @return TableFieldFactory used to create the Field instances.
	 * @see #isEditable
	 */
	public TableFieldFactory getTableFieldFactory() {
		return fieldFactory;
	}

	/**
	 * Is table editable.
	 *
	 * If table is editable a editor of type Field is created for each table
	 * cell. The assigned FieldFactory is used to create the instances.
	 *
	 * To provide custom editors for table cells create a class implementins the
	 * FieldFactory interface, and assign it to table, and set the editable
	 * property to true.
	 *
	 * @return true if table is editable, false oterwise.
	 * @see Field
	 * @see FieldFactory
	 *
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * Sets the editable property.
	 *
	 * If table is editable a editor of type Field is created for each table
	 * cell. The assigned FieldFactory is used to create the instances.
	 *
	 * To provide custom editors for table cells create a class implementins the
	 * FieldFactory interface, and assign it to table, and set the editable
	 * property to true.
	 *
	 * @param editable
	 *            true if table should be editable by user.
	 * @see Field
	 * @see FieldFactory
	 *
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;

		// Assure visual refresh
		refreshRowCache();
	}

	/**
	 * Sorts the table.
	 *
	 * @param propertyId
	 *            the property id
	 * @param ascending
	 *            the ascending
	 * @throws UnsupportedOperationException
	 *             if the container data source does not implement
	 *             Container.Sortable
	 * @see com.vaadin.data.Container.Sortable#sort(java.lang.Object[],
	 *      boolean[])
	 */
	@Override
	public void sort(Object[] propertyId, boolean[] ascending) throws UnsupportedOperationException {
		final Container c = getContainerDataSource();
		if (c instanceof Container.Sortable) {
			if (!doSortOperation(propertyId, ascending)) {
				final int pageIndex = getCurrentPageFirstItemIndex();
				boolean refreshingPreviouslyEnabled = disableContentRefreshing();
				((Container.Sortable) c).sort(propertyId, ascending);
				setCurrentPageFirstItemIndex(pageIndex);
				if (refreshingPreviouslyEnabled) {
					enableContentRefreshing(true);
				}
			}

			if (propertyId.length > 0 && ascending.length > 0) {
				// The first propertyId is the primary sorting criterion,
				// therefore the sort indicator should be there
				sortAscending = ascending[0];
				sortContainerPropertyId = propertyId[0];
			} else {
				sortAscending = true;
				sortContainerPropertyId = null;
			}
		} else if (c != null) {
			throw new UnsupportedOperationException("Underlying Data does not allow sorting");
		}
	}

	/**
	 * Sorts the table by currently selected sorting column.
	 */
	public void sort() {
		if (getSortContainerPropertyId() == null) {
			return;
		}
		sort(new Object[] { sortContainerPropertyId }, new boolean[] { sortAscending });
	}

	/**
	 * Gets the container property IDs, which can be used to sort the item.
	 * <p>
	 * Note that the {@link #isSortEnabled()} state affects what this method
	 * returns. Disabling sorting causes this method to always return an empty
	 * collection.
	 * </p>
	 *
	 * @return the sortable container property ids
	 * @see com.vaadin.data.Container.Sortable#getSortableContainerPropertyIds()
	 */
	@Override
	public Collection<?> getSortableContainerPropertyIds() {
		final Container c = getContainerDataSource();
		if (c instanceof Container.Sortable && isSortEnabled()) {
			return ((Container.Sortable) c).getSortableContainerPropertyIds();
		} else {
			return Collections.EMPTY_LIST;
		}
	}

	/**
	 * Gets the currently sorted column property ID.
	 *
	 * @return the Container property id of the currently sorted column.
	 */
	public Object getSortContainerPropertyId() {
		return sortContainerPropertyId;
	}

	/**
	 * Sets the currently sorted column property id.
	 *
	 * @param propertyId
	 *            the Container property id of the currently sorted column.
	 */
	public void setSortContainerPropertyId(Object propertyId) {
		setSortContainerPropertyId(propertyId, true);
	}

	/**
	 * Internal method to set currently sorted column property id. With doSort
	 * flag actual sorting may be bypassed.
	 *
	 * @param propertyId
	 *            the property id
	 * @param doSort
	 *            the do sort
	 */
	private void setSortContainerPropertyId(Object propertyId, boolean doSort) {
		if ((sortContainerPropertyId != null && !sortContainerPropertyId.equals(propertyId))
				|| (sortContainerPropertyId == null && propertyId != null)) {
			sortContainerPropertyId = propertyId;

			if (doSort) {
				sort();
				// Assures the visual refresh. This should not be necessary as
				// sort() calls refreshRowCache
				refreshRenderedCells();
			}
		}
	}

	/**
	 * Is the table currently sorted in ascending order.
	 *
	 * @return <code>true</code> if ascending, <code>false</code> if descending.
	 */
	public boolean isSortAscending() {
		return sortAscending;
	}

	/**
	 * Sets the table in ascending order.
	 *
	 * @param ascending
	 *            <code>true</code> if ascending, <code>false</code> if
	 *            descending.
	 */
	public void setSortAscending(boolean ascending) {
		setSortAscending(ascending, true);
	}

	/**
	 * Internal method to set sort ascending. With doSort flag actual sort can
	 * be bypassed.
	 *
	 * @param ascending
	 *            the ascending
	 * @param doSort
	 *            the do sort
	 */
	private void setSortAscending(boolean ascending, boolean doSort) {
		if (sortAscending != ascending) {
			sortAscending = ascending;
			if (doSort) {
				sort();
				// Assures the visual refresh. This should not be necessary as
				// sort() calls refreshRowCache
				refreshRenderedCells();
			}
		}
	}

	/**
	 * Is sorting disabled altogether.
	 *
	 * True iff no sortable columns are given even in the case where data source
	 * would support this.
	 *
	 * @return True iff sorting is disabled.
	 * @deprecated As of 7.0, use {@link #isSortEnabled()} instead
	 */
	@Deprecated
	public boolean isSortDisabled() {
		return !isSortEnabled();
	}

	/**
	 * Checks if sorting is enabled.
	 *
	 * @return true if sorting by the user is allowed, false otherwise
	 */
	public boolean isSortEnabled() {
		return sortEnabled;
	}

	/**
	 * Disables the sorting by the user altogether.
	 *
	 * @param sortDisabled
	 *            True iff sorting is disabled.
	 * @deprecated As of 7.0, use {@link #setSortEnabled(boolean)} instead
	 */
	@Deprecated
	public void setSortDisabled(boolean sortDisabled) {
		setSortEnabled(!sortDisabled);
	}

	/**
	 * Enables or disables sorting.
	 * <p>
	 * Setting this to false disallows sorting by the user. It is still possible
	 * to call {@link #sort()}.
	 * </p>
	 *
	 * @param sortEnabled
	 *            true to allow the user to sort the table, false to disallow it
	 */
	public void setSortEnabled(boolean sortEnabled) {
		if (this.sortEnabled != sortEnabled) {
			this.sortEnabled = sortEnabled;
			markAsDirty();
		}
	}

	/**
	 * Used to create "generated columns"; columns that exist only in the
	 * CustomTable, not in the underlying Container. Implement this interface
	 * and pass it to CustomTable.addGeneratedColumn along with an id for the
	 * column to be generated.
	 *
	 */
	public interface ColumnGenerator extends Serializable {

		/**
		 * Called by CustomTable when a cell in a generated column needs to be
		 * generated.
		 *
		 * @param source
		 *            the source CustomTable
		 * @param itemId
		 *            the itemId (aka rowId) for the of the cell to be generated
		 * @param columnId
		 *            the id for the generated column (as specified in
		 *            addGeneratedColumn)
		 * @return A {@link Component} that should be rendered in the cell or a
		 *         {@link String} that should be displayed in the cell. Other
		 *         return values are not supported.
		 */
		public abstract Object generateCell(ExtCustomTable source, Object itemId, Object columnId);
	}

	/**
	 * Set cell style generator for CustomTable.
	 *
	 * @param cellStyleGenerator
	 *            New cell style generator or null to remove generator.
	 */
	public void setCellStyleGenerator(CellStyleGenerator cellStyleGenerator) {
		this.cellStyleGenerator = cellStyleGenerator;
		// Assures the visual refresh. No need to reset the page buffer
		// before as the content has not changed, only the style generators
		refreshRenderedCells();

	}

	/**
	 * Get the current cell style generator.
	 *
	 * @return the cell style generator
	 */
	public CellStyleGenerator getCellStyleGenerator() {
		return cellStyleGenerator;
	}

	/**
	 * Allow to define specific style on cells (and rows) contents. Implements
	 * this interface and pass it to CustomTable.setCellStyleGenerator. Row
	 * styles are generated when porpertyId is null. The CSS class name that
	 * will be added to the cell content is
	 * <tt>v-table-cell-content-[style name]</tt>, and the row style will be
	 * <tt>v-table-row-[style name]</tt>.
	 */
	public interface CellStyleGenerator extends Serializable {

		/**
		 * Called by CustomTable when a cell (and row) is painted.
		 *
		 * @param source
		 *            the source CustomTable
		 * @param itemId
		 *            The itemId of the painted cell
		 * @param propertyId
		 *            The propertyId of the cell, null when getting row style
		 * @return The style name to add to this cell or row. (the CSS class
		 *         name will be v-table-cell-content-[style name], or
		 *         v-table-row-[style name] for rows)
		 */
		public abstract String getStyle(ExtCustomTable source, Object itemId, Object propertyId);
	}

	/**
	 * Adds the item click listener.
	 *
	 * @param listener
	 *            the listener
	 */
	@Override
	public void addItemClickListener(ItemClickListener listener) {
		addListener(TableConstants.ITEM_CLICK_EVENT_ID, ItemClickEvent.class, listener,
				ItemClickEvent.ITEM_CLICK_METHOD);
	}

	/**
	 * Adds the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #addItemClickListener(ItemClickListener)}
	 */
	@Override
	@Deprecated
	public void addListener(ItemClickListener listener) {
		addItemClickListener(listener);
	}

	/**
	 * Removes the item click listener.
	 *
	 * @param listener
	 *            the listener
	 */
	@Override
	public void removeItemClickListener(ItemClickListener listener) {
		removeListener(TableConstants.ITEM_CLICK_EVENT_ID, ItemClickEvent.class, listener);
	}

	/**
	 * Removes the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #removeItemClickListener(ItemClickListener)}
	 */
	@Override
	@Deprecated
	public void removeListener(ItemClickListener listener) {
		removeItemClickListener(listener);
	}

	// Identical to AbstractCompoenentContainer.setEnabled();
	/**
	 * Sets the enabled.
	 *
	 * @param enabled
	 *            the new enabled
	 */
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		if (getParent() != null && !getParent().isEnabled()) {
			// some ancestor still disabled, don't update children
			return;
		} else {
			markAsDirtyRecursive();
		}
	}

	/**
	 * Sets the drag start mode of the CustomTable. Drag start mode controls how
	 * CustomTable behaves as a drag source.
	 *
	 * @param newDragMode
	 *            the new drag mode
	 */
	public void setDragMode(TableDragMode newDragMode) {
		dragMode = newDragMode;
		markAsDirty();
	}

	/**
	 * Gets the drag mode.
	 *
	 * @return the current start mode of the CustomTable. Drag start mode
	 *         controls how CustomTable behaves as a drag source.
	 */
	public TableDragMode getDragMode() {
		return dragMode;
	}

	/**
	 * Concrete implementation of {@link DataBoundTransferable} for data
	 * transferred from a table.
	 *
	 * @see {@link DataBoundTransferable}.
	 *
	 * @since 6.3
	 */
	public class TableTransferable extends DataBoundTransferable {

		/**
		 * Instantiates a new table transferable.
		 *
		 * @param rawVariables
		 *            the raw variables
		 */
		protected TableTransferable(Map<String, Object> rawVariables) {
			super(ExtCustomTable.this, rawVariables);
			Object object = rawVariables.get("itemId");
			if (object != null) {
				setData("itemId", itemIdMapper.get((String) object));
			}
			object = rawVariables.get("propertyId");
			if (object != null) {
				setData("propertyId", columnIdMap.get((String) object));
			}
		}

		/**
		 * Gets the item id.
		 *
		 * @return the item id
		 */
		@Override
		public Object getItemId() {
			return getData("itemId");
		}

		/**
		 * Gets the property id.
		 *
		 * @return the property id
		 */
		@Override
		public Object getPropertyId() {
			return getData("propertyId");
		}

		/**
		 * Gets the source component.
		 *
		 * @return the source component
		 */
		@Override
		public ExtCustomTable getSourceComponent() {
			return (ExtCustomTable) super.getSourceComponent();
		}
	}

	/**
	 * Gets the transferable.
	 *
	 * @param rawVariables
	 *            the raw variables
	 * @return the transferable
	 */
	@Override
	public TableTransferable getTransferable(Map<String, Object> rawVariables) {
		TableTransferable transferable = new TableTransferable(rawVariables);
		return transferable;
	}

	/**
	 * Gets the drop handler.
	 *
	 * @return the drop handler
	 */
	@Override
	public DropHandler getDropHandler() {
		return dropHandler;
	}

	/**
	 * Sets the drop handler.
	 *
	 * @param dropHandler
	 *            the new drop handler
	 */
	public void setDropHandler(DropHandler dropHandler) {
		this.dropHandler = dropHandler;
	}

	/**
	 * Translate drop target details.
	 *
	 * @param clientVariables
	 *            the client variables
	 * @return the abstract select target details
	 */
	@Override
	public AbstractSelectTargetDetails translateDropTargetDetails(Map<String, Object> clientVariables) {
		return new AbstractSelectTargetDetails(clientVariables);
	}

	/**
	 * Sets the behavior of how the multi-select mode should behave when the
	 * table is both selectable and in multi-select mode.
	 * <p>
	 * Note, that on some clients the mode may not be respected. E.g. on touch
	 * based devices CTRL/SHIFT base selection method is invalid, so touch based
	 * browsers always use the {@link MultiSelectMode#SIMPLE}.
	 *
	 * @param mode
	 *            The select mode of the table
	 */
	public void setMultiSelectMode(MultiSelectMode mode) {
		multiSelectMode = mode;
		markAsDirty();
	}

	/**
	 * Returns the select mode in which multi-select is used.
	 *
	 * @return The multi select mode
	 */
	public MultiSelectMode getMultiSelectMode() {
		return multiSelectMode;
	}

	/**
	 * Lazy loading accept criterion for CustomTable. Accepted target rows are
	 * loaded from server once per drag and drop operation. Developer must
	 * override one method that decides on which rows the currently dragged data
	 * can be dropped.
	 *
	 * <p>
	 * Initially pretty much no data is sent to client. On first required
	 * criterion check (per drag request) the client side data structure is
	 * initialized from server and no subsequent requests requests are needed
	 * during that drag and drop operation.
	 */
	public static abstract class TableDropCriterion extends ServerSideCriterion {

		/**
		 * The table.
		 */
		private ExtCustomTable table;
		/**
		 * The allowed item ids.
		 */
		private Set<Object> allowedItemIds;

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.vaadin.event.dd.acceptcriteria.ServerSideCriterion#getIdentifier
		 * ()
		 */
		/**
		 * Gets the identifier.
		 *
		 * @return the identifier
		 */
		@Override
		protected String getIdentifier() {
			return TableDropCriterion.class.getCanonicalName();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.vaadin.event.dd.acceptcriteria.AcceptCriterion#accepts(com.vaadin
		 * .event.dd.DragAndDropEvent)
		 */
		/**
		 * Accept.
		 *
		 * @param dragEvent
		 *            the drag event
		 * @return true, if successful
		 */
		@Override
		@SuppressWarnings("unchecked")
		public boolean accept(DragAndDropEvent dragEvent) {
			AbstractSelectTargetDetails dropTargetData = (AbstractSelectTargetDetails) dragEvent.getTargetDetails();
			table = (ExtCustomTable) dragEvent.getTargetDetails().getTarget();
			Collection<?> visibleItemIds = table.getVisibleItemIds();
			allowedItemIds = getAllowedItemIds(dragEvent, table, (Collection<Object>) visibleItemIds);

			return allowedItemIds.contains(dropTargetData.getItemIdOver());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.vaadin.event.dd.acceptcriteria.AcceptCriterion#paintResponse(
		 * com.vaadin.server.PaintTarget)
		 */
		/**
		 * Paint response.
		 *
		 * @param target
		 *            the target
		 * @throws PaintException
		 *             the paint exception
		 */
		@Override
		public void paintResponse(PaintTarget target) throws PaintException {
			/*
			 * send allowed nodes to client so subsequent requests can be
			 * avoided
			 */
			Object[] array = allowedItemIds.toArray();
			for (int i = 0; i < array.length; i++) {
				String key = table.itemIdMapper.key(array[i]);
				array[i] = key;
			}
			target.addAttribute("allowedIds", array);
		}

		/**
		 * Gets the allowed item ids.
		 *
		 * @param dragEvent
		 *            the drag event
		 * @param table
		 *            the table for which the allowed item identifiers are
		 *            defined
		 * @param visibleItemIds
		 *            the list of currently rendered item identifiers, accepted
		 *            item id's need to be detected only for these visible items
		 * @return the set of identifiers for items on which the dragEvent will
		 *         be accepted
		 */
		protected abstract Set<Object> getAllowedItemIds(DragAndDropEvent dragEvent, ExtCustomTable table,
				Collection<Object> visibleItemIds);
	}

	/**
	 * Click event fired when clicking on the CustomTable headers. The event
	 * includes a reference the the CustomTable the event originated from, the
	 * property id of the column which header was pressed and details about the
	 * mouse event itself.
	 */
	public static class HeaderClickEvent extends ClickEvent {

		/**
		 * The Constant HEADER_CLICK_METHOD.
		 */
		public static final Method HEADER_CLICK_METHOD;

		static {
			try {
				// Set the header click method
				HEADER_CLICK_METHOD = HeaderClickListener.class.getDeclaredMethod("headerClick",
						new Class[] { HeaderClickEvent.class });
			} catch (final java.lang.NoSuchMethodException e) {
				// This should never happen
				throw new java.lang.RuntimeException(e);
			}
		}
		// The property id of the column which header was pressed
		/**
		 * The column property id.
		 */
		private final Object columnPropertyId;

		/**
		 * Instantiates a new header click event.
		 *
		 * @param source
		 *            the source
		 * @param propertyId
		 *            the property id
		 * @param details
		 *            the details
		 */
		public HeaderClickEvent(Component source, Object propertyId, MouseEventDetails details) {
			super(source, details);
			columnPropertyId = propertyId;
		}

		/**
		 * Gets the property id of the column which header was pressed.
		 *
		 * @return The column propety id
		 */
		public Object getPropertyId() {
			return columnPropertyId;
		}
	}

	/**
	 * Click event fired when clicking on the CustomTable footers. The event
	 * includes a reference the the CustomTable the event originated from, the
	 * property id of the column which header was pressed and details about the
	 * mouse event itself.
	 */
	public static class FooterClickEvent extends ClickEvent {

		/**
		 * The Constant FOOTER_CLICK_METHOD.
		 */
		public static final Method FOOTER_CLICK_METHOD;

		static {
			try {
				// Set the header click method
				FOOTER_CLICK_METHOD = FooterClickListener.class.getDeclaredMethod("footerClick",
						new Class[] { FooterClickEvent.class });
			} catch (final java.lang.NoSuchMethodException e) {
				// This should never happen
				throw new java.lang.RuntimeException(e);
			}
		}
		// The property id of the column which header was pressed
		/**
		 * The column property id.
		 */
		private final Object columnPropertyId;

		/**
		 * Constructor.
		 *
		 * @param source
		 *            The source of the component
		 * @param propertyId
		 *            The propertyId of the column
		 * @param details
		 *            The mouse details of the click
		 */
		public FooterClickEvent(Component source, Object propertyId, MouseEventDetails details) {
			super(source, details);
			columnPropertyId = propertyId;
		}

		/**
		 * Gets the property id of the column which header was pressed.
		 *
		 * @return The column propety id
		 */
		public Object getPropertyId() {
			return columnPropertyId;
		}
	}

	/**
	 * Interface for the listener for column header mouse click events. The
	 * headerClick method is called when the user presses a header column cell.
	 *
	 * @see HeaderClickEvent
	 */
	public interface HeaderClickListener extends Serializable {

		/**
		 * Called when a user clicks a header column cell.
		 *
		 * @param event
		 *            The event which contains information about the column and
		 *            the mouse click event
		 */
		public void headerClick(HeaderClickEvent event);
	}

	/**
	 * Interface for the listener for column footer mouse click events. The
	 * footerClick method is called when the user presses a footer column cell.
	 *
	 * @see FooterClickEvent
	 */
	public interface FooterClickListener extends Serializable {

		/**
		 * Called when a user clicks a footer column cell.
		 *
		 * @param event
		 *            The event which contains information about the column and
		 *            the mouse click event
		 */
		public void footerClick(FooterClickEvent event);
	}

	/**
	 * Adds a header click listener which handles the click events when the user
	 * clicks on a column header cell in the CustomTable.
	 * <p>
	 * The listener will receive events which contain information about which
	 * column was clicked and some details about the mouse event.
	 * </p>
	 *
	 * @param listener
	 *            The handler which should handle the header click events.
	 */
	public void addHeaderClickListener(HeaderClickListener listener) {
		addListener(TableConstants.HEADER_CLICK_EVENT_ID, HeaderClickEvent.class, listener,
				HeaderClickEvent.HEADER_CLICK_METHOD);
	}

	/**
	 * Adds the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #addHeaderClickListener(HeaderClickListener)}
	 */
	@Deprecated
	public void addListener(HeaderClickListener listener) {
		addHeaderClickListener(listener);
	}

	/**
	 * Removes a header click listener.
	 *
	 * @param listener
	 *            The listener to remove.
	 */
	public void removeHeaderClickListener(HeaderClickListener listener) {
		removeListener(TableConstants.HEADER_CLICK_EVENT_ID, HeaderClickEvent.class, listener);
	}

	/**
	 * Removes the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #removeHeaderClickListener(HeaderClickListener)}
	 */
	@Deprecated
	public void removeListener(HeaderClickListener listener) {
		removeHeaderClickListener(listener);
	}

	/**
	 * Adds a footer click listener which handles the click events when the user
	 * clicks on a column footer cell in the CustomTable.
	 * <p>
	 * The listener will receive events which contain information about which
	 * column was clicked and some details about the mouse event.
	 * </p>
	 *
	 * @param listener
	 *            The handler which should handle the footer click events.
	 */
	public void addFooterClickListener(FooterClickListener listener) {
		addListener(TableConstants.FOOTER_CLICK_EVENT_ID, FooterClickEvent.class, listener,
				FooterClickEvent.FOOTER_CLICK_METHOD);
	}

	/**
	 * Adds the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #addFooterClickListener(FooterClickListener)}
	 */
	@Deprecated
	public void addListener(FooterClickListener listener) {
		addFooterClickListener(listener);
	}

	/**
	 * Removes a footer click listener.
	 *
	 * @param listener
	 *            The listener to remove.
	 */
	public void removeFooterClickListener(FooterClickListener listener) {
		removeListener(TableConstants.FOOTER_CLICK_EVENT_ID, FooterClickEvent.class, listener);
	}

	/**
	 * Removes the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #removeFooterClickListener(FooterClickListener)}
	 */
	@Deprecated
	public void removeListener(FooterClickListener listener) {
		removeFooterClickListener(listener);
	}

	/**
	 * Gets the footer caption beneath the rows.
	 *
	 * @param propertyId
	 *            The propertyId of the column
	 * @return The caption of the footer or NULL if not set
	 */
	public String getColumnFooter(Object propertyId) {
		return columnFooters.get(propertyId);
	}

	/**
	 * Sets the column footer caption. The column footer caption is the text
	 * displayed beneath the column if footers have been set visible.
	 *
	 * @param propertyId
	 *            The properyId of the column
	 *
	 * @param footer
	 *            The caption of the footer
	 */
	public void setColumnFooter(Object propertyId, String footer) {
		if (footer == null) {
			columnFooters.remove(propertyId);
		} else {
			columnFooters.put(propertyId, footer);
		}

		markAsDirty();
	}

	/**
	 * Sets the footer visible in the bottom of the table.
	 * <p>
	 * The footer can be used to add column related data like sums to the bottom
	 * of the CustomTable using setColumnFooter(Object propertyId, String
	 * footer).
	 * </p>
	 *
	 * @param visible
	 *            Should the footer be visible
	 */
	public void setFooterVisible(boolean visible) {
		if (visible != columnFootersVisible) {
			columnFootersVisible = visible;
			markAsDirty();
		}
	}

	/**
	 * Is the footer currently visible?.
	 *
	 * @return Returns true if visible else false
	 */
	public boolean isFooterVisible() {
		return columnFootersVisible;
	}

	/**
	 * This event is fired when a column is resized. The event contains the
	 * columns property id which was fired, the previous width of the column and
	 * the width of the column after the resize.
	 */
	public static class ColumnResizeEvent extends Component.Event {

		/**
		 * The Constant COLUMN_RESIZE_METHOD.
		 */
		public static final Method COLUMN_RESIZE_METHOD;

		static {
			try {
				COLUMN_RESIZE_METHOD = ColumnResizeListener.class.getDeclaredMethod("columnResize",
						new Class[] { ColumnResizeEvent.class });
			} catch (final java.lang.NoSuchMethodException e) {
				// This should never happen
				throw new java.lang.RuntimeException(e);
			}
		}
		/**
		 * The previous width.
		 */
		private final int previousWidth;
		/**
		 * The current width.
		 */
		private final int currentWidth;
		/**
		 * The column property id.
		 */
		private final Object columnPropertyId;

		/**
		 * Constructor.
		 *
		 * @param source
		 *            The source of the event
		 * @param propertyId
		 *            The columns property id
		 * @param previous
		 *            The width in pixels of the column before the resize event
		 * @param current
		 *            The width in pixels of the column after the resize event
		 */
		public ColumnResizeEvent(Component source, Object propertyId, int previous, int current) {
			super(source);
			previousWidth = previous;
			currentWidth = current;
			columnPropertyId = propertyId;
		}

		/**
		 * Get the column property id of the column that was resized.
		 *
		 * @return The column property id
		 */
		public Object getPropertyId() {
			return columnPropertyId;
		}

		/**
		 * Get the width in pixels of the column before the resize event.
		 *
		 * @return Width in pixels
		 */
		public int getPreviousWidth() {
			return previousWidth;
		}

		/**
		 * Get the width in pixels of the column after the resize event.
		 *
		 * @return Width in pixels
		 */
		public int getCurrentWidth() {
			return currentWidth;
		}
	}

	/**
	 * Interface for listening to column resize events.
	 *
	 * @see ColumnResizeEvent
	 */
	public interface ColumnResizeListener extends Serializable {

		/**
		 * This method is triggered when the column has been resized.
		 *
		 * @param event
		 *            The event which contains the column property id, the
		 *            previous width of the column and the current width of the
		 *            column
		 */
		public void columnResize(ColumnResizeEvent event);
	}

	/**
	 * Adds a column resize listener to the CustomTable. A column resize
	 * listener is called when a user resizes a columns width.
	 *
	 * @param listener
	 *            The listener to attach to the CustomTable
	 */
	public void addColumnResizeListener(ColumnResizeListener listener) {
		addListener(TableConstants.COLUMN_RESIZE_EVENT_ID, ColumnResizeEvent.class, listener,
				ColumnResizeEvent.COLUMN_RESIZE_METHOD);
	}

	/**
	 * Adds the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #addColumnResizeListener(ColumnResizeListener)}
	 */
	@Deprecated
	public void addListener(ColumnResizeListener listener) {
		addColumnResizeListener(listener);
	}

	/**
	 * Removes a column resize listener from the CustomTable.
	 *
	 * @param listener
	 *            The listener to remove
	 */
	public void removeColumnResizeListener(ColumnResizeListener listener) {
		removeListener(TableConstants.COLUMN_RESIZE_EVENT_ID, ColumnResizeEvent.class, listener);
	}

	/**
	 * Removes the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #removeColumnResizeListener(ColumnResizeListener)}
	 */
	@Deprecated
	public void removeListener(ColumnResizeListener listener) {
		removeColumnResizeListener(listener);
	}

	/**
	 * This event is fired when a columns are reordered by the end user user.
	 */
	public static class ColumnReorderEvent extends Component.Event {

		/**
		 * The Constant METHOD.
		 */
		public static final Method METHOD;

		static {
			try {
				METHOD = ColumnReorderListener.class.getDeclaredMethod("columnReorder",
						new Class[] { ColumnReorderEvent.class });
			} catch (final java.lang.NoSuchMethodException e) {
				// This should never happen
				throw new java.lang.RuntimeException(e);
			}
		}

		/**
		 * Constructor.
		 *
		 * @param source
		 *            The source of the event
		 */
		public ColumnReorderEvent(Component source) {
			super(source);
		}
	}

	/**
	 * Interface for listening to column reorder events.
	 *
	 * @see ColumnReorderEvent
	 */
	public interface ColumnReorderListener extends Serializable {

		/**
		 * This method is triggered when the column has been reordered.
		 *
		 * @param event
		 *            the event
		 */
		public void columnReorder(ColumnReorderEvent event);
	}

	/**
	 * Adds a column reorder listener to the CustomTable. A column reorder
	 * listener is called when a user reorders columns.
	 *
	 * @param listener
	 *            The listener to attach to the CustomTable
	 */
	public void addColumnReorderListener(ColumnReorderListener listener) {
		addListener(TableConstants.COLUMN_REORDER_EVENT_ID, ColumnReorderEvent.class, listener,
				ColumnReorderEvent.METHOD);
	}

	/**
	 * Adds the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #addColumnReorderListener(ColumnReorderListener)}
	 */
	@Deprecated
	public void addListener(ColumnReorderListener listener) {
		addColumnReorderListener(listener);
	}

	/**
	 * Removes a column reorder listener from the CustomTable.
	 *
	 * @param listener
	 *            The listener to remove
	 */
	public void removeColumnReorderListener(ColumnReorderListener listener) {
		removeListener(TableConstants.COLUMN_REORDER_EVENT_ID, ColumnReorderEvent.class, listener);
	}

	/**
	 * Removes the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #removeColumnReorderListener(ColumnReorderListener)}
	 */
	@Deprecated
	public void removeListener(ColumnReorderListener listener) {
		removeColumnReorderListener(listener);
	}

	/**
	 * Set the item description generator which generates tooltips for cells and
	 * rows in the CustomTable.
	 *
	 * @param generator
	 *            The generator to use or null to disable
	 */
	public void setItemDescriptionGenerator(ItemDescriptionGenerator generator) {
		if (generator != itemDescriptionGenerator) {
			itemDescriptionGenerator = generator;
			// Assures the visual refresh. No need to reset the page buffer
			// before as the content has not changed, only the descriptions
			refreshRenderedCells();
		}
	}

	/**
	 * Get the item description generator which generates tooltips for cells and
	 * rows in the CustomTable.
	 *
	 * @return the item description generator
	 */
	public ItemDescriptionGenerator getItemDescriptionGenerator() {
		return itemDescriptionGenerator;
	}

	/**
	 * Row generators can be used to replace certain items in a table with a
	 * generated string. The generator is called each time the table is
	 * rendered, which means that new strings can be generated each time.
	 *
	 * Row generators can be used for e.g. summary rows or grouping of items.
	 */
	public interface RowGenerator extends Serializable {

		/**
		 * Called for every row that is painted in the CustomTable. Returning a
		 * GeneratedRow object will cause the row to be painted based on the
		 * contents of the GeneratedRow. A generated row is by default styled
		 * similarly to a header or footer row.
		 * <p>
		 * The GeneratedRow data object contains the text that should be
		 * rendered in the row. The itemId in the container thus works only as a
		 * placeholder.
		 * <p>
		 * If GeneratedRow.setSpanColumns(true) is used, there will be one
		 * String spanning all columns (use setText("Spanning text")). Otherwise
		 * you can define one String per visible column.
		 * <p>
		 * If GeneratedRow.setRenderAsHtml(true) is used, the strings can
		 * contain HTML markup, otherwise all strings will be rendered as text
		 * (the default).
		 * <p>
		 * A "v-table-generated-row" CSS class is added to all generated rows.
		 * For custom styling of a generated row you can combine a RowGenerator
		 * with a CellStyleGenerator.
		 * <p>
		 *
		 * @param table
		 *            The CustomTable that is being painted
		 * @param itemId
		 *            The itemId for the row
		 * @return A GeneratedRow describing how the row should be painted or
		 *         null to paint the row with the contents from the container
		 */
		public GeneratedRow generateRow(ExtCustomTable table, Object itemId);
	}

	/**
	 * The Class GeneratedRow.
	 */
	public static class GeneratedRow implements Serializable {

		/**
		 * The html content allowed.
		 */
		private boolean htmlContentAllowed = false;
		/**
		 * The span columns.
		 */
		private boolean spanColumns = false;
		/**
		 * The text.
		 */
		private String[] text = null;

		/**
		 * Creates a new generated row. If only one string is passed in, columns
		 * are automatically spanned.
		 *
		 * @param text
		 *            the text
		 */
		public GeneratedRow(String... text) {
			setHtmlContentAllowed(false);
			setSpanColumns(text == null || text.length == 1);
			setText(text);
		}

		/**
		 * Pass one String if spanColumns is used, one String for each visible
		 * column otherwise.
		 *
		 * @param text
		 *            the new text
		 */
		public void setText(String... text) {
			if (text == null || (text.length == 1 && text[0] == null)) {
				text = new String[] { "" };
			}
			this.text = text;
		}

		/**
		 * Gets the text.
		 *
		 * @return the text
		 */
		protected String[] getText() {
			return text;
		}

		/**
		 * Gets the value.
		 *
		 * @return the value
		 */
		protected Object getValue() {
			return getText();
		}

		/**
		 * Checks if is html content allowed.
		 *
		 * @return true, if is html content allowed
		 */
		protected boolean isHtmlContentAllowed() {
			return htmlContentAllowed;
		}

		/**
		 * If set to true, all strings passed to {@link #setText(String...)}
		 * will be rendered as HTML.
		 *
		 * @param htmlContentAllowed
		 *            the new html content allowed
		 */
		public void setHtmlContentAllowed(boolean htmlContentAllowed) {
			this.htmlContentAllowed = htmlContentAllowed;
		}

		/**
		 * Checks if is span columns.
		 *
		 * @return true, if is span columns
		 */
		protected boolean isSpanColumns() {
			return spanColumns;
		}

		/**
		 * If set to true, only one string will be rendered, spanning the entire
		 * row.
		 *
		 * @param spanColumns
		 *            the new span columns
		 */
		public void setSpanColumns(boolean spanColumns) {
			this.spanColumns = spanColumns;
		}
	}

	/**
	 * Assigns a row generator to the table. The row generator will be able to
	 * replace rows in the table when it is rendered.
	 *
	 * @param generator
	 *            the new row generator
	 */
	public void setRowGenerator(RowGenerator generator) {
		rowGenerator = generator;
		refreshRowCache();
	}

	/**
	 * Gets the row generator.
	 *
	 * @return the current row generator
	 */
	public RowGenerator getRowGenerator() {
		return rowGenerator;
	}

	/**
	 * Sets a converter for a property id.
	 * <p>
	 * The converter is used to format the the data for the given property id
	 * before displaying it in the table.
	 * </p>
	 *
	 * @param propertyId
	 *            The propertyId to format using the converter
	 * @param converter
	 *            The converter to use for the property id
	 */
	public void setConverter(Object propertyId, Converter<String, ?> converter) {
		setConverter(propertyId, converter, true);
	}

	/**
	 * Sets a converter for a property id.
	 * <p>
	 * The converter is used to format the the data for the given property id
	 * before displaying it in the table.
	 * </p>
	 *
	 * @param propertyId
	 *            The propertyId to format using the converter
	 * @param converter
	 *            The converter to use for the property id
	 * @param refreshRowCache
	 *            the refresh row cache
	 */
	public void setConverter(Object propertyId, Converter<String, ?> converter, boolean refreshRowCache) {
		if (!getContainerPropertyIds().contains(propertyId)) {
			throw new IllegalArgumentException("PropertyId " + propertyId + " must be in the container");
		}

		if (!typeIsCompatible(converter.getModelType(), getType(propertyId))) {
			throw new IllegalArgumentException("Property type (" + getType(propertyId)
					+ ") must match converter source type (" + converter.getModelType() + ")");
		}
		propertyValueConverters.put(propertyId, (Converter<String, Object>) converter);
		if (refreshRowCache) {
			refreshRowCache();
		}
	}

	/**
	 * Checks if there is a converter set explicitly for the given property id.
	 *
	 * @param propertyId
	 *            The propertyId to check
	 * @return true if a converter has been set for the property id, false
	 *         otherwise
	 */
	protected boolean hasConverter(Object propertyId) {
		return propertyValueConverters.containsKey(propertyId);
	}

	/**
	 * Returns the converter used to format the given propertyId.
	 *
	 * @param propertyId
	 *            The propertyId to check
	 * @return The converter used to format the propertyId or null if no
	 *         converter has been set
	 */
	public Converter<String, Object> getConverter(Object propertyId) {
		return propertyValueConverters.get(propertyId);
	}

	/**
	 * Sets the visible.
	 *
	 * @param visible
	 *            the new visible
	 */
	@Override
	public void setVisible(boolean visible) {
		if (visible) {
			// We need to ensure that the rows are sent to the client when the
			// CustomTable is made visible if it has been rendered as invisible.
			setRowCacheInvalidated(true);
		}
		super.setVisible(visible);
	}

	/**
	 * Iterator.
	 *
	 * @return the iterator
	 */
	@Override
	public Iterator<Component> iterator() {
		if (visibleComponents == null) {
			Collection<Component> empty = Collections.emptyList();
			return empty.iterator();
		}
		return visibleComponents.iterator();
	}

	/**
	 * Gets the component iterator.
	 *
	 * @return the component iterator
	 * @deprecated As of 7.0, use {@link #iterator()} instead.
	 */
	@Deprecated
	public Iterator<Component> getComponentIterator() {
		return iterator();
	}

	/**
	 * Gets the logger.
	 *
	 * @return the logger
	 */
	private final Logger getLogger() {
		if (logger == null) {
			logger = Logger.getLogger(ExtCustomTable.class.getName());
		}
		return logger;
	}

	/* Added for Double Header */
	/**
	 * Sets the double header map.
	 *
	 * @param mapVisibleCols
	 *            the map visible cols
	 */
	public void setDoubleHeaderMap(Map<Object, Object[]> mapVisibleCols) {
		if (mapVisibleCols == null || doubleVisibleColumns.size() != mapVisibleCols.size()) {
			throw new NullPointerException("Can not set visible columns map to null value");
		}

		this.doubleMapVisCol.clear();

		for (int i = 0; i < doubleVisibleColumns.size(); i++) {
			if (doubleVisibleColumns.get(i) == null) {
				throw new NullPointerException("Ids must be non-nulls");
			} else {
				String tmp[] = Arrays.copyOf(mapVisibleCols.get(doubleVisibleColumns.get(i)),
						mapVisibleCols.get(doubleVisibleColumns.get(i)).length, String[].class);
				this.doubleMapVisCol.put(((doubleVisibleColumns.get(i)).toString()), tmp);
			}
		}

	}

	/**
	 * Gets the double header map.
	 *
	 * @return the double header map
	 */
	public Map<String, String[]> getDoubleHeaderMap() {
		return doubleMapVisCol;
	}

	/**
	 * Gets the triple header map.
	 *
	 * @return the triple header map
	 */
	public Map<String, String[]> getTripleHeaderMap() {
		return tripleMapVisCol;
	}

	/**
	 * Gets the header map from double header.
	 *
	 * @param doubleVisibleColumn
	 *            the double visible column
	 * @return the header map from double header
	 */
	public String[] getHeaderMapFromDoubleHeader(String doubleVisibleColumn) {
		if (doubleMapVisCol.containsKey(doubleVisibleColumn)) {
			return doubleMapVisCol.get(doubleVisibleColumn);
		}
		return new String[0];
	}

	/**
	 * Gets the maped header size from double header.
	 *
	 * @param doubleVisibleColumn
	 *            the double visible column
	 * @return the maped header size from double header
	 */
	public int getMapedHeaderSizeFromDoubleHeader(String doubleVisibleColumn) {
		if (doubleMapVisCol.containsKey(doubleVisibleColumn)) {
			return doubleMapVisCol.get(doubleVisibleColumn).length;
		}
		return 0;
	}

	/**
	 * Gets the maped double header size from triple header.
	 *
	 * @param tripleVisibleColumn
	 *            the triple visible column
	 * @return the maped double header size from triple header
	 */
	public int getMapedDoubleHeaderSizeFromTripleHeader(String tripleVisibleColumn) {
		if (tripleMapVisCol.containsKey(tripleVisibleColumn)) {
			return tripleMapVisCol.get(tripleVisibleColumn).length;
		}
		return 0;
	}

	/**
	 * Gets the maped header size from triple header.
	 *
	 * @param tripleVisibleColumn
	 *            the triple visible column
	 * @return the maped header size from triple header
	 */
	public int getMapedHeaderSizeFromTripleHeader(String tripleVisibleColumn) {
		String[] doubleVisibleColumn = getDoubleHeaderMapFromTripleHeader(tripleVisibleColumn);
		int size = 0;
		if (doubleVisibleColumn != null) {
			for (String column : doubleVisibleColumn) {
				if (doubleMapVisCol.containsKey(column)) {
					size += doubleMapVisCol.get(column).length;
				}
			}
		}
		return size;
	}

	/**
	 * Gets the double header map from triple header.
	 *
	 * @param tripleVisibleColumn
	 *            the triple visible column
	 * @return the double header map from triple header
	 */
	public String[] getDoubleHeaderMapFromTripleHeader(String tripleVisibleColumn) {
		if (tripleMapVisCol.containsKey(tripleVisibleColumn)) {
			return tripleMapVisCol.get(tripleVisibleColumn);
		}
		return new String[0];
	}

	/**
	 * Gets the header map from triple header.
	 *
	 * @param tripleVisibleColumn
	 *            the triple visible column
	 * @return the header map from triple header
	 */
	public String[] getHeaderMapFromTripleHeader(String tripleVisibleColumn) {
		String[] doubleVisibleColumn = getDoubleHeaderMapFromTripleHeader(tripleVisibleColumn);
		List<String> singleCols = new ArrayList<String>();
		if (doubleVisibleColumn != null) {
			for (String column : doubleVisibleColumn) {
				if (doubleMapVisCol.containsKey(column)) {
					List<String> doubleCols = Arrays.asList(doubleMapVisCol.get(column));
					singleCols.addAll(doubleCols);
				}
			}
		}
		String[] stringArray = new String[singleCols.size()];
		stringArray = singleCols.toArray(stringArray);
		return stringArray;
	}

	/**
	 * Gets the double header for single header.
	 *
	 * @param visibleColumn
	 *            the visible column
	 * @return the double header for single header
	 */
	public String getDoubleHeaderForSingleHeader(String visibleColumn) {

		for (String column : doubleMapVisCol.keySet()) {
			if (Arrays.asList(doubleMapVisCol.get(column)).contains(visibleColumn)) {
				return column;
			}
		}
		return null;
	}

	/**
	 * Gets the triple header for double header.
	 *
	 * @param doubleVisibleColumn
	 *            the double visible column
	 * @return the triple header for double header
	 */
	public String getTripleHeaderForDoubleHeader(String doubleVisibleColumn) {
		for (String column : tripleMapVisCol.keySet()) {
			if (Arrays.asList(tripleMapVisCol.get(column)).contains(doubleVisibleColumn)) {
				return column;
			}
		}
		return null;
	}

	/**
	 * Gets the triple header for single header.
	 *
	 * @param visibleColumn
	 *            the visible column
	 * @return the triple header for single header
	 */
	public String getTripleHeaderForSingleHeader(String visibleColumn) {
		String doubleVisibleColumn = getDoubleHeaderForSingleHeader(visibleColumn);
		if (doubleVisibleColumn != null) {
			for (String column : tripleMapVisCol.keySet()) {
				if (Arrays.asList(tripleMapVisCol.get(column)).contains(doubleVisibleColumn)) {
					return column;
				}
			}

		}
		return null;
	}

	/**
	 * Sets columns width (in pixels). Theme may not necessary respect very
	 * small or very big values. Setting width to -1 (default) means that theme
	 * will make decision of width.
	 *
	 * <p>
	 * Column can either have a fixed width or expand ratio. The latter one set
	 * is used. See @link {@link #setColumnExpandRatio(Object, float)}.
	 *
	 * @param propertyId
	 *            colunmns property id
	 * @param width
	 *            width to be reserved for colunmns content @since 4.0.3
	 */
	public void setDoubleHeaderColumnWidth(Object propertyId, int width) {
		setDoubleHeaderColumnWidth(propertyId, width, true);
	}

	/**
	 * Sets columns width (in pixels). Theme may not necessary respect very
	 * small or very big values. Setting width to -1 (default) means that theme
	 * will make decision of width.
	 *
	 * <p>
	 * Column can either have a fixed width or expand ratio. The latter one set
	 * is used. See @link {@link #setColumnExpandRatio(Object, float)}.
	 *
	 * @param propertyId
	 *            colunmns property id
	 * @param width
	 *            width to be reserved for colunmns content @since 4.0.3
	 */
	public void setDoubleHeaderColumnWidth(Object propertyId, int width, boolean refresh) {
		if (propertyId == null || "0".equals(propertyId)) {
			// Since propertyId is null, this is the row header. Use the magic
			// id to store the width of the row header.
			propertyId = ROW_HEADER_FAKE_PROPERTY_ID;
		}

		// Setting column width should remove any expand ratios as well
		doubleColumnExpandRatios.remove(propertyId);

		if (width < 0) {
			doubleColumnWidths.remove(propertyId);
		} else {
			doubleColumnWidths.put(propertyId, width);

		}
		if (refresh) {
			markAsDirty();
		}
	}

	/**
	 * Handle double header column width updates.
	 *
	 * @param variables
	 *            the variables
	 */
	private void handleDoubleHeaderColumnWidthUpdates(Map<String, Object> variables) {
		if (variables.containsKey("doublecolumnWidthUpdates")) {
			String[] events = (String[]) variables.get("doublecolumnWidthUpdates");
			for (String str : events) {
				String[] eventDetails = str.split(":");
				Object propertyId = eventDetails[0];
				if (propertyId == null) {
					propertyId = ROW_HEADER_FAKE_PROPERTY_ID;
				}
				int width = Integer.valueOf(eventDetails[1]);
				setDoubleHeaderColumnWidth(propertyId, width, false);
			}
		}
	}

	/**
	 * Handle column check event.
	 *
	 * @param variables
	 *            the variables
	 */
	private void handleColumnCheckEvent(Map<String, Object> variables) {
		if (variables.containsKey("columnCheckEventColumn")) {
			Object cid = variables.get("columnCheckEventColumn");
			Object propertyId = null;
			if (cid != null) {
				propertyId = columnIdMap.get(cid.toString());

				Object curr = variables.get("columnCheckEventCurr");
				boolean currentValue = false;
				if (curr != null) {
					currentValue = Boolean.valueOf(curr.toString());
				}

				fireColumnCheckEvent(propertyId, currentValue);
			}
		}
	}

	/**
	 * Fire column check event.
	 *
	 * @param propertyId
	 *            the property id
	 * @param currentValue
	 *            the current value
	 */
	private void fireColumnCheckEvent(Object propertyId, boolean currentValue) {
		/*
		 * Update the sizes on the server side. If a column previously had a
		 * expand ratio and the user resized the column then the expand ratio
		 * will be turned into a static pixel size.
		 */
		setColumnCheckBox(propertyId, true, currentValue, true);

		fireEvent(new ColumnCheckEvent(this, propertyId, currentValue));
	}

	/**
	 * Handle check box updates.
	 *
	 * @param variables
	 *            the variables
	 */
	private void handleCheckBoxUpdates(Map<String, Object> variables) {
		if (variables.containsKey("columnCheckBoxUpdates")) {
			String[] events = (String[]) variables.get("columnCheckBoxUpdates");
			for (String str : events) {
				String[] eventDetails = str.split(":");
				Object propertyId = columnIdMap.get(eventDetails[0]);
				if (propertyId == null) {
					propertyId = ROW_HEADER_FAKE_PROPERTY_ID;
				}

				if (isColumnCheckBoxVisible(propertyId)) {
					boolean check = Boolean.valueOf(eventDetails[1]);
					setColumnCheckBox(propertyId, true, check, true);
				}

			}
		}
	}

	/**
	 * Handle double header column check event.
	 *
	 * @param variables
	 *            the variables
	 */
	private void handleDoubleHeaderColumnCheckEvent(Map<String, Object> variables) {
		if (variables.containsKey("doublecolumnCheckEventColumn")) {
			Object cid = variables.get("doublecolumnCheckEventColumn");
			Object propertyId = null;
			if (cid != null) {
				propertyId = cid.toString();

				Object curr = variables.get("doublecolumnCheckEventCurr");
				boolean currentValue = false;
				if (curr != null) {
					currentValue = Boolean.valueOf(curr.toString());
				}

				fireDoubleHeaderColumnCheckEvent(propertyId, currentValue);
			}
		}
	}

	/**
	 * Fire double header column check event.
	 *
	 * @param propertyId
	 *            the property id
	 * @param currentValue
	 *            the current value
	 */
	private void fireDoubleHeaderColumnCheckEvent(Object propertyId, boolean currentValue) {
		/*
		 * Update the sizes on the server side. If a column previously had a
		 * expand ratio and the user resized the column then the expand ratio
		 * will be turned into a static pixel size.
		 */
		setDoubleHeaderColumnCheckBox(propertyId, true, currentValue, true);

		fireEvent(new DoubleHeaderColumnCheckEvent(this, propertyId, currentValue));
	}

	/**
	 * Handle double header check box updates.
	 *
	 * @param variables
	 *            the variables
	 */
	private void handleDoubleHeaderCheckBoxUpdates(Map<String, Object> variables) {
		if (variables.containsKey("doublecolumnCheckBoxUpdates")) {
			String[] events = (String[]) variables.get("doublecolumnCheckBoxUpdates");
			for (String str : events) {
				String[] eventDetails = str.split(":");
				Object propertyId = eventDetails[0];
				if (propertyId == null) {
					propertyId = ROW_HEADER_FAKE_PROPERTY_ID;
				}
				if (isDoubleHeaderColumnCheckBoxVisible(propertyId)) {
					boolean check = Boolean.valueOf(eventDetails[1]);
					setDoubleHeaderColumnCheckBox(propertyId, true, check, true);
				}
			}
		}
	}

	/* Handle expand icon Event */
	/* Single Header */
	/**
	 * Handle column expand icon event.
	 *
	 * @param variables
	 *            the variables
	 */
	private void handleColumnExpandIconEvent(Map<String, Object> variables) {
		if (variables.containsKey("columnExpandIconEventColumn")) {
			Object cid = variables.get("columnExpandIconEventColumn");
			Object propertyId = null;
			if (cid != null) {
				propertyId = columnIdMap.get(cid.toString());

				Object curr = variables.get("columnExpandIconEventCurr");
				boolean currentValue = false;
				if (curr != null) {
					currentValue = Boolean.valueOf(curr.toString());
				}

				fireColumnExpandIconEvent(propertyId, currentValue);
			}
		}
	}

	/**
	 * Fire column expand icon event.
	 *
	 * @param propertyId
	 *            the property id
	 * @param currentValue
	 *            the current value
	 */
	private void fireColumnExpandIconEvent(Object propertyId, boolean currentValue) {
		/*
		 * Update the sizes on the server side. If a column previously had a
		 * expand ratio and the user resized the column then the expand ratio
		 * will be turned into a static pixel size.
		 */
		setColumnExpandIcon(propertyId, currentValue);

		fireEvent(new ColumnExpandIconEvent(this, propertyId, currentValue));
	}

	/**
	 * Handle expand icon updates.
	 *
	 * @param variables
	 *            the variables
	 */
	private void handleExpandIconUpdates(Map<String, Object> variables) {
		if (variables.containsKey("columnExpandIconUpdates")) {
			String[] events = (String[]) variables.get("columnExpandIconUpdates");
			for (String str : events) {
				String[] eventDetails = str.split(":");
				Object propertyId = columnIdMap.get(eventDetails[0]);
				if (propertyId == null) {
					propertyId = ROW_HEADER_FAKE_PROPERTY_ID;
				}
				if (isColumnExpandIcon(propertyId)) {
					boolean expanded = Boolean.valueOf(eventDetails[1]);
					setColumnExpandIcon(propertyId, expanded);
				}
			}
		}
	}
	/* Double Header */

	/**
	 * Handle double header column expand icon event.
	 *
	 * @param variables
	 *            the variables
	 */
	private void handleDoubleHeaderColumnExpandIconEvent(Map<String, Object> variables) {
		if (variables.containsKey("doublecolumnExpandIconEventColumn")) {
			Object cid = variables.get("doublecolumnExpandIconEventColumn");
			Object propertyId = null;
			if (cid != null) {
				propertyId = cid.toString();

				Object curr = variables.get("doublecolumnExpandIconEventCurr");
				boolean currentValue = false;
				if (curr != null) {
					currentValue = Boolean.valueOf(curr.toString());
				}

				fireDoubleHeaderColumnExpandIconEvent(propertyId, currentValue);
			}
		}
	}

	/**
	 * Fire double header column expand icon event.
	 *
	 * @param propertyId
	 *            the property id
	 * @param currentValue
	 *            the current value
	 */
	private void fireDoubleHeaderColumnExpandIconEvent(Object propertyId, boolean currentValue) {
		/*
		 * Update the sizes on the server side. If a column previously had a
		 * expand ratio and the user resized the column then the expand ratio
		 * will be turned into a static pixel size.
		 */
		setDoubleHeaderColumnExpandIcon(propertyId, currentValue);

		fireEvent(new DoubleHeaderColumnExpandIconEvent(this, propertyId, currentValue));
	}

	/**
	 * Handle double header expand icon updates.
	 *
	 * @param variables
	 *            the variables
	 */
	private void handleDoubleHeaderExpandIconUpdates(Map<String, Object> variables) {
		if (variables.containsKey("doublecolumnExpandIconUpdates")) {
			String[] events = (String[]) variables.get("doublecolumnExpandIconUpdates");
			for (String str : events) {
				String[] eventDetails = str.split(":");
				Object propertyId = eventDetails[0];
				if (propertyId == null) {
					propertyId = ROW_HEADER_FAKE_PROPERTY_ID;
				}
				if (isDoubleHeaderColumnExpandIcon(propertyId)) {
					boolean expanded = Boolean.valueOf(eventDetails[1]);
					setDoubleHeaderColumnExpandIcon(propertyId, expanded);
				}
			}
		}
	}

	/* Handle Radio Event */
	/* Single Header */
	/**
	 * Handle column radio check event.
	 *
	 * @param variables
	 *            the variables
	 */
	private void handleColumnRadioCheckEvent(Map<String, Object> variables) {
		if (variables.containsKey("singlecolumnRadioEventName")) {
			Object cid = variables.get("singlecolumnRadioEventName");
			String radioName = null;
			if (cid != null) {
				radioName = cid.toString();
				String previousHeader = SINGLE_HEADER;
				String currentHeader = SINGLE_HEADER;
				Object previousH = variables.get("singleColumnRadioEventPrevHeader");
				Object currentH = variables.get("singleColumnRadioEventCurHeader");
				if (previousH != null) {
					previousHeader = previousH.toString();
				}
				if (currentH != null) {
					currentHeader = currentH.toString();
				}
				Object prev = variables.get("singlecolumnRadioEventPrev");
				String previousValue = "";
				if (prev != null) {
					previousValue = columnIdMap.get(prev.toString()).toString();
				}

				Object curr = variables.get("singlecolumnRadioEventCurr");
				String currentValue = "";
				if (curr != null) {
					currentValue = columnIdMap.get(curr.toString()).toString();
				}

				fireColumnRadioCheckEvent(radioName, previousHeader, currentHeader, previousValue, currentValue);
			}
		}
	}

	/**
	 * Fire column radio check event.
	 *
	 * @param radioName
	 *            the radio name
	 * @param previousHeader
	 *            the previous header
	 * @param currentHeader
	 *            the current header
	 * @param previousValue
	 *            the previous value
	 * @param currentValue
	 *            the current value
	 */
	private void fireColumnRadioCheckEvent(String radioName, String previousHeader, String currentHeader,
			String previousValue, String currentValue) {
		/*
		 * Update the sizes on the server side. If a column previously had a
		 * expand ratio and the user resized the column then the expand ratio
		 * will be turned into a static pixel size.
		 */
		setColumnRadioButtonValue(radioName, currentValue);

		fireEvent(
				new ColumnRadioCheckEvent(this, radioName, previousHeader, currentHeader, previousValue, currentValue));
	}

	/**
	 * Handle column radio updates.
	 *
	 * @param variables
	 *            the variables
	 */
	private void handleColumnRadioUpdates(Map<String, Object> variables) {
		if (variables.containsKey("singlecolumnRadioUpdates")) {
			String[] events = (String[]) variables.get("singlecolumnRadioUpdates");
			singleRadioVal.clear();
			for (String str : events) {
				String[] eventDetails = str.split(":");
				String radioName = eventDetails[0];
				if (radioName != null) {
					setColumnRadioButtonValue(radioName, columnIdMap.get(eventDetails[1]));
				}
			}
		}
	}

	/* Double Header */
	/**
	 * Handle double header column radio check event.
	 *
	 * @param variables
	 *            the variables
	 */
	private void handleDoubleHeaderColumnRadioCheckEvent(Map<String, Object> variables) {
		if (variables.containsKey("doublecolumnRadioEventName")) {
			Object cid = variables.get("doublecolumnRadioEventName");
			String radioName = null;
			if (cid != null) {
				radioName = cid.toString();
				String previousHeader = DOUBLE_HEADER;
				String currentHeader = DOUBLE_HEADER;
				Object previousH = variables.get("doubleColumnRadioEventPrevHeader");
				Object currentH = variables.get("doubleColumnRadioEventCurHeader");
				if (previousH != null) {
					previousHeader = previousH.toString();
				}
				if (currentH != null) {
					currentHeader = currentH.toString();
				}

				Object prev = variables.get("doublecolumnRadioEventPrev");
				String previousValue = "";
				if (prev != null) {
					previousValue = prev.toString();
				}

				Object curr = variables.get("doublecolumnRadioEventCurr");
				String currentValue = "";
				if (curr != null) {
					currentValue = curr.toString();
				}

				fireDoubleHeaderColumnRadioCheckEvent(radioName, previousHeader, currentHeader, previousValue,
						currentValue);
			}
		}
	}

	/**
	 * Fire double header column radio check event.
	 *
	 * @param radioName
	 *            the radio name
	 * @param previousHeader
	 *            the previous header
	 * @param currentHeader
	 *            the current header
	 * @param previousValue
	 *            the previous value
	 * @param currentValue
	 *            the current value
	 */
	private void fireDoubleHeaderColumnRadioCheckEvent(String radioName, String previousHeader, String currentHeader,
			String previousValue, String currentValue) {
		/*
		 * Update the sizes on the server side. If a column previously had a
		 * expand ratio and the user resized the column then the expand ratio
		 * will be turned into a static pixel size.
		 */
		setDoubleHeaderColumnRadioButtonValue(radioName, currentValue);

		fireEvent(new DoubleHeaderColumnRadioCheckEvent(this, radioName, previousHeader, currentHeader, previousValue,
				currentValue));
	}

	/**
	 * Handle double header radio updates.
	 *
	 * @param variables
	 *            the variables
	 */
	private void handleDoubleHeaderRadioUpdates(Map<String, Object> variables) {
		if (variables.containsKey("doublecolumnRadioUpdates")) {
			String[] events = (String[]) variables.get("doublecolumnRadioUpdates");
			doubleRadioVal.clear();
			for (String str : events) {
				String[] eventDetails = str.split(":");
				String radioName = eventDetails[0];
				if (radioName != null) {
					setDoubleHeaderColumnRadioButtonValue(radioName, eventDetails[1]);
				}
			}
		}
	}

	/**
	 * Handles click event.
	 *
	 * @param variables
	 *            the variables
	 */
	private void handleDoubleHeaderClickEvent(Map<String, Object> variables) {

		// DoubleHeader click event
		if (variables.containsKey("doubleheaderClickEvent")) {

			MouseEventDetails details = MouseEventDetails.deSerialize((String) variables.get("doubleheaderClickEvent"));

			Object cid = variables.get("doubleheaderClickCID");
			Object propertyId = null;
			if (cid != null) {
				propertyId = cid.toString();
			}
			fireEvent(new DoubleHeaderClickEvent(this, propertyId, details));
		}

	}

	/**
	 * Handles the column resize event sent by the client.
	 *
	 * @param variables
	 *            the variables
	 */
	private void handleDoubleHeaderColumnResizeEvent(Map<String, Object> variables) {
		if (variables.containsKey("doublecolumnResizeEventColumn")) {
			Object cid = variables.get("doublecolumnResizeEventColumn");
			Object propertyId = null;
			if (cid != null) {
				propertyId = cid.toString();

				Object prev = variables.get("doublecolumnResizeEventPrev");
				int previousWidth = -1;
				if (prev != null) {
					previousWidth = Integer.valueOf(prev.toString());
				}

				Object curr = variables.get("doublecolumnResizeEventCurr");
				int currentWidth = -1;
				if (curr != null) {
					currentWidth = Integer.valueOf(curr.toString());
				}

				fireDoubleHeaderColumnResizeEvent(propertyId, previousWidth, currentWidth);
			}
		}
	}

	/**
	 * Fire double header column resize event.
	 *
	 * @param propertyId
	 *            the property id
	 * @param previousWidth
	 *            the previous width
	 * @param currentWidth
	 *            the current width
	 */
	private void fireDoubleHeaderColumnResizeEvent(Object propertyId, int previousWidth, int currentWidth) {
		/*
		 * Update the sizes on the server side. If a column previously had a
		 * expand ratio and the user resized the column then the expand ratio
		 * will be turned into a static pixel size.
		 */
		setDoubleHeaderColumnWidth(propertyId, currentWidth, true);

		fireEvent(new DoubleHeaderColumnResizeEvent(this, propertyId, previousWidth, currentWidth));
	}

	public boolean changeDoubleHeaderCollapsingVariables(Map<String, Object> variables) {
		boolean contentRefresh = false;
		if (variables.containsKey("doublecollapsedcolumns")) {
			try {
				final Object[] ids = (Object[]) variables.get("doublecollapsedcolumns");
				Set<Object> idSet = new HashSet<Object>();
				for (Object id : ids) {
					idSet.add(id.toString());
				}
				for (final Iterator<Object> it = doubleVisibleColumns.iterator(); it.hasNext();) {
					Object propertyId = it.next();
					if (isDoubleHeaderColumnCollapsed(propertyId)) {
						if (!idSet.contains(propertyId)) {
							changeDoubleHeaderColumnCollapsed(propertyId, false);
						}
					} else if (idSet.contains(propertyId)) {
						changeDoubleHeaderColumnCollapsed(propertyId, true);
					}
				}
			} catch (final Exception e) {
				// FIXME: Handle exception
				getLogger().log(Level.FINER, "Could not determine double header column collapsing state", e);
			}
			contentRefresh = true;
		}
		return contentRefresh;
	}

	/**
	 * Change double header variables.
	 *
	 * @param source
	 *            the source
	 * @param variables
	 *            the variables
	 */
	public void changeDoubleHeaderVariables(Object source, Map<String, Object> variables) {

		handleDoubleHeaderClickEvent(variables);

		handleDoubleHeaderColumnResizeEvent(variables);

		handleDoubleHeaderColumnWidthUpdates(variables);
		handleDoubleHeaderColumnCheckEvent(variables);
		handleDoubleHeaderCheckBoxUpdates(variables);
		handleDoubleHeaderColumnRadioCheckEvent(variables);
		handleDoubleHeaderRadioUpdates(variables);
		handleDoubleHeaderColumnExpandIconEvent(variables);
		handleDoubleHeaderExpandIconUpdates(variables);
	}

	/**
	 * This event is fired when a column is resized. The event contains the
	 * columns property id which was fired, the previous width of the column and
	 * the width of the column after the resize.
	 */
	public static class DoubleHeaderColumnResizeEvent extends Component.Event {

		/**
		 * The Constant COLUMN_RESIZE_METHOD.
		 */
		public static final Method COLUMN_RESIZE_METHOD;

		static {
			try {
				COLUMN_RESIZE_METHOD = DoubleHeaderColumnResizeListener.class.getDeclaredMethod(
						"doubleHeaderColumnResize", new Class[] { DoubleHeaderColumnResizeEvent.class });
			} catch (final java.lang.NoSuchMethodException e) {
				// This should never happen
				throw new java.lang.RuntimeException(e);
			}
		}
		/**
		 * The previous width.
		 */
		private final int previousWidth;
		/**
		 * The current width.
		 */
		private final int currentWidth;
		/**
		 * The column property id.
		 */
		private final Object columnPropertyId;

		/**
		 * Constructor.
		 *
		 * @param source
		 *            The source of the event
		 * @param propertyId
		 *            The columns property id
		 * @param previous
		 *            The width in pixels of the column before the resize event
		 * @param current
		 *            The width in pixels of the column after the resize event
		 */
		public DoubleHeaderColumnResizeEvent(Component source, Object propertyId, int previous, int current) {
			super(source);
			previousWidth = previous;
			currentWidth = current;
			columnPropertyId = propertyId;
		}

		/**
		 * Get the column property id of the column that was resized.
		 *
		 * @return The column property id
		 */
		public Object getPropertyId() {
			return columnPropertyId;
		}

		/**
		 * Get the width in pixels of the column before the resize event.
		 *
		 * @return Width in pixels
		 */
		public int getPreviousWidth() {
			return previousWidth;
		}

		/**
		 * Get the width in pixels of the column after the resize event.
		 *
		 * @return Width in pixels
		 */
		public int getCurrentWidth() {
			return currentWidth;
		}
	}

	/**
	 * Interface for listening to column resize events.
	 *
	 * @see DoubleHeaderColumnResizeEvent
	 */
	public interface DoubleHeaderColumnResizeListener extends Serializable {

		/**
		 * This method is triggered when the column has been resized.
		 *
		 * @param event
		 *            The event which contains the column property id, the
		 *            previous width of the column and the current width of the
		 *            column
		 */
		public void doubleHeaderColumnResize(DoubleHeaderColumnResizeEvent event);
	}

	/**
	 * Adds a column resize listener to the Table. A column resize listener is
	 * called when a user resizes a columns width.
	 *
	 * @param listener
	 *            The listener to attach to the Table
	 */
	public void addDoubleHeaderColumnResizeListener(DoubleHeaderColumnResizeListener listener) {
		addListener(TableConstants.COLUMN_RESIZE_EVENT_ID, DoubleHeaderColumnResizeEvent.class, listener,
				DoubleHeaderColumnResizeEvent.COLUMN_RESIZE_METHOD);
	}

	/**
	 * Adds the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #addColumnResizeListener(ColumnResizeListener)}
	 */
	@Deprecated
	public void addListener(DoubleHeaderColumnResizeListener listener) {
		addDoubleHeaderColumnResizeListener(listener);
	}

	/**
	 * Removes a column resize listener from the Table.
	 *
	 * @param listener
	 *            The listener to remove
	 */
	public void removeDoubleHeaderColumnResizeListener(DoubleHeaderColumnResizeListener listener) {
		removeListener(TableConstants.COLUMN_RESIZE_EVENT_ID, DoubleHeaderColumnResizeEvent.class, listener);
	}

	/**
	 * Removes the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #removeColumnResizeListener(ColumnResizeListener)}
	 */
	@Deprecated
	public void removeListener(DoubleHeaderColumnResizeListener listener) {
		removeDoubleHeaderColumnResizeListener(listener);
	}

	/**
	 * Click event fired when clicking on the Table headers. The event includes
	 * a reference the the Table the event originated from, the property id of
	 * the column which header was pressed and details about the mouse event
	 * itself.
	 */
	public static class DoubleHeaderClickEvent extends ClickEvent {

		/**
		 * The Constant HEADER_CLICK_METHOD.
		 */
		public static final Method HEADER_CLICK_METHOD;

		static {
			try {
				// Set the header click method
				HEADER_CLICK_METHOD = DoubleHeaderClickListener.class.getDeclaredMethod("doubleHeaderClick",
						new Class[] { DoubleHeaderClickEvent.class });
			} catch (final java.lang.NoSuchMethodException e) {
				// This should never happen
				throw new java.lang.RuntimeException(e);
			}
		}
		// The property id of the column which header was pressed
		/**
		 * The column property id.
		 */
		private final Object columnPropertyId;

		/**
		 * Instantiates a new double header click event.
		 *
		 * @param source
		 *            the source
		 * @param propertyId
		 *            the property id
		 * @param details
		 *            the details
		 */
		public DoubleHeaderClickEvent(Component source, Object propertyId, MouseEventDetails details) {
			super(source, details);
			columnPropertyId = propertyId;
		}

		/**
		 * Gets the property id of the column which header was pressed.
		 *
		 * @return The column propety id
		 */
		public Object getPropertyId() {
			return columnPropertyId;
		}
	}

	/**
	 * Interface for the listener for column header mouse click events. The
	 * headerClick method is called when the user presses a header column cell.
	 *
	 * @see DoubleHeaderClickEvent
	 */
	public interface DoubleHeaderClickListener extends Serializable {

		/**
		 * Called when a user clicks a header column cell.
		 *
		 * @param event
		 *            The event which contains information about the column and
		 *            the mouse click event
		 */
		public void doubleHeaderClick(DoubleHeaderClickEvent event);
	}

	/**
	 * Adds a header click listener which handles the click events when the user
	 * clicks on a column header cell in the Table.
	 * <p>
	 * The listener will receive events which contain information about which
	 * column was clicked and some details about the mouse event.
	 * </p>
	 *
	 * @param listener
	 *            The handler which should handle the header click events.
	 */
	public void addDoubleHeaderClickListener(DoubleHeaderClickListener listener) {
		addListener(TableConstants.HEADER_CLICK_EVENT_ID, DoubleHeaderClickEvent.class, listener,
				DoubleHeaderClickEvent.HEADER_CLICK_METHOD);
	}

	/**
	 * Adds the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #addHeaderClickListener(HeaderClickListener)}
	 */
	@Deprecated
	public void addListener(DoubleHeaderClickListener listener) {
		addDoubleHeaderClickListener(listener);
	}

	/**
	 * Removes a header click listener.
	 *
	 * @param listener
	 *            The listener to remove.
	 */
	public void removeDoubleHeaderClickListener(DoubleHeaderClickListener listener) {
		removeListener(TableConstants.HEADER_CLICK_EVENT_ID, DoubleHeaderClickEvent.class, listener);
	}

	/**
	 * Removes the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #removeHeaderClickListener(HeaderClickListener)}
	 */
	@Deprecated
	public void removeListener(DoubleHeaderClickListener listener) {
		removeDoubleHeaderClickListener(listener);
	}
	/* Addad for checkBox */
	/* Single Header */

	/**
	 * The Class ColumnCheckEvent.
	 */
	public static class ColumnCheckEvent extends Component.Event {

		/**
		 * The Constant COLUMN_CHECK_METHOD.
		 */
		public static final Method COLUMN_CHECK_METHOD;

		static {
			try {
				COLUMN_CHECK_METHOD = ColumnCheckListener.class.getDeclaredMethod("columnCheck",
						new Class[] { ColumnCheckEvent.class });
			} catch (final java.lang.NoSuchMethodException e) {
				// This should never happen
				throw new java.lang.RuntimeException(e);
			}
		}
		/**
		 * The current value.
		 */
		private final boolean currentValue;
		/**
		 * The column property id.
		 */
		private final Object columnPropertyId;

		/**
		 * Constructor.
		 *
		 * @param source
		 *            The source of the event
		 * @param propertyId
		 *            The columns property id
		 * @param current
		 *            The checkbox boolean value of the column after the resize
		 *            event
		 */
		public ColumnCheckEvent(Component source, Object propertyId, boolean current) {
			super(source);
			currentValue = current;
			columnPropertyId = propertyId;
		}

		/**
		 * Get the column property id of the column that was checked.
		 *
		 * @return The column property id
		 */
		public Object getPropertyId() {
			return columnPropertyId;
		}

		/**
		 * Get the checkbox boolean value of the column after the check event.
		 *
		 * @return value in boolean
		 */
		public boolean isChecked() {
			return currentValue;
		}
	}

	/**
	 * Interface for listening to column check events.
	 *
	 * @see ColumnCheckEvent
	 */
	public interface ColumnCheckListener extends Serializable {

		/**
		 * This method is triggered when the column has been checked.
		 *
		 * @param event
		 *            The event which contains the column property id, the
		 *            previous checkbox value of the column and the current
		 *            checkbox value of the column
		 */
		public void columnCheck(ColumnCheckEvent event);
	}

	/**
	 * Adds a column check listener to the Table. A column check listener is
	 * called when a user checkes a columns.
	 *
	 * @param listener
	 *            The listener to attach to the Table
	 */
	public void addColumnCheckListener(ColumnCheckListener listener) {
		addListener("checkBoxChange", ColumnCheckEvent.class, listener, ColumnCheckEvent.COLUMN_CHECK_METHOD);
	}

	/**
	 * Adds the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #addColumnCheckListener(ColumnCheckListener)}
	 */
	@Deprecated
	public void addListener(ColumnCheckListener listener) {
		addColumnCheckListener(listener);
	}

	/**
	 * Removes a column check listener from the Table.
	 *
	 * @param listener
	 *            The listener to remove
	 */
	public void removeColumnCheckListener(ColumnCheckListener listener) {
		removeListener("checkBoxChange", ColumnCheckEvent.class, listener);
	}

	/**
	 * Removes the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #removeColumnCheckListener(ColumnCheckListener)}
	 */
	@Deprecated
	public void removeListener(ColumnCheckListener listener) {
		removeColumnCheckListener(listener);
	}

	/* DoubleHeader */
	/**
	 * The Class DoubleHeaderColumnCheckEvent.
	 */
	public static class DoubleHeaderColumnCheckEvent extends Component.Event {

		/**
		 * The Constant COLUMN_CHECK_METHOD.
		 */
		public static final Method COLUMN_CHECK_METHOD;

		static {
			try {
				COLUMN_CHECK_METHOD = DoubleHeaderColumnCheckListener.class.getDeclaredMethod("doubleHeaderColumnCheck",
						new Class[] { DoubleHeaderColumnCheckEvent.class });
			} catch (final java.lang.NoSuchMethodException e) {
				// This should never happen
				throw new java.lang.RuntimeException(e);
			}
		}
		/**
		 * The current value.
		 */
		private final boolean currentValue;
		/**
		 * The column property id.
		 */
		private final Object columnPropertyId;

		/**
		 * Constructor.
		 *
		 * @param source
		 *            The source of the event
		 * @param propertyId
		 *            The columns property id
		 * @param current
		 *            The checkbox boolean value of the column after the resize
		 *            event
		 */
		public DoubleHeaderColumnCheckEvent(Component source, Object propertyId, boolean current) {
			super(source);
			currentValue = current;
			columnPropertyId = propertyId;
		}

		/**
		 * Get the column property id of the column that was checked.
		 *
		 * @return The column property id
		 */
		public Object getPropertyId() {
			return columnPropertyId;
		}

		/**
		 * Get the checkbox boolean value of the column after the check event.
		 *
		 * @return value in boolean
		 */
		public boolean isChecked() {
			return currentValue;
		}
	}

	/**
	 * Interface for listening to column check events.
	 *
	 * @see DoubleHeaderColumnCheckEvent
	 */
	public interface DoubleHeaderColumnCheckListener extends Serializable {

		/**
		 * This method is triggered when the column has been checked.
		 *
		 * @param event
		 *            The event which contains the column property id, the
		 *            previous checkbox value of the column and the current
		 *            checkbox value of the column
		 */
		public void doubleHeaderColumnCheck(DoubleHeaderColumnCheckEvent event);
	}

	/**
	 * Adds a column check listener to the Table. A column check listener is
	 * called when a user checkes a columns.
	 *
	 * @param listener
	 *            The listener to attach to the Table
	 */
	public void addDoubleHeaderColumnCheckListener(DoubleHeaderColumnCheckListener listener) {
		addListener("checkBoxChange", DoubleHeaderColumnCheckEvent.class, listener,
				DoubleHeaderColumnCheckEvent.COLUMN_CHECK_METHOD);
	}

	/**
	 * Adds the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #addDoubleHeaderColumnCheckListener(DoubleHeaderColumnCheckListener)}
	 */
	@Deprecated
	public void addListener(DoubleHeaderColumnCheckListener listener) {
		addDoubleHeaderColumnCheckListener(listener);
	}

	/**
	 * Removes a column check listener from the Table.
	 *
	 * @param listener
	 *            The listener to remove
	 */
	public void removeDoubleHeaderColumnCheckListener(DoubleHeaderColumnCheckListener listener) {
		removeListener("checkBoxChange", DoubleHeaderColumnCheckEvent.class, listener);
	}

	/**
	 * Removes the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #removeDoubleHeaderColumnCheckListener(DoubleHeaderColumnCheckListener)}
	 */
	@Deprecated
	public void removeListener(DoubleHeaderColumnCheckListener listener) {
		removeDoubleHeaderColumnCheckListener(listener);
	}

	/* CheckBox Completed */
	/* Expand Icon Started */
	/* Single Header */
	/**
	 * The Class ColumnExpandIconEvent.
	 */
	public static class ColumnExpandIconEvent extends Component.Event {

		/**
		 * The Constant COLUMN_EXPAND_ICON_METHOD.
		 */
		public static final Method COLUMN_EXPAND_ICON_METHOD;

		static {
			try {
				COLUMN_EXPAND_ICON_METHOD = ColumnExpandIconListener.class.getDeclaredMethod("columnExpandIcon",
						new Class[] { ColumnExpandIconEvent.class });
			} catch (final java.lang.NoSuchMethodException e) {
				// This should never happen
				throw new java.lang.RuntimeException(e);
			}
		}
		/**
		 * The current value.
		 */
		private final boolean currentValue;
		/**
		 * The column property id.
		 */
		private final Object columnPropertyId;

		/**
		 * Constructor.
		 *
		 * @param source
		 *            The source of the event
		 * @param propertyId
		 *            The columns property id
		 * @param current
		 *            The checkbox boolean value of the column after the resize
		 *            event
		 */
		public ColumnExpandIconEvent(Component source, Object propertyId, boolean current) {
			super(source);
			currentValue = current;
			columnPropertyId = propertyId;
		}

		/**
		 * Get the column property id of the column that was checked.
		 *
		 * @return The column property id
		 */
		public Object getPropertyId() {
			return columnPropertyId;
		}

		/**
		 * Get the checkbox boolean value of the column after the check event.
		 *
		 * @return value in boolean
		 */
		public boolean isExpanded() {
			return currentValue;
		}
	}

	/**
	 * Interface for listening to column ExpandIcon events.
	 *
	 * @see ColumnExpandIconEvent
	 */
	public interface ColumnExpandIconListener extends Serializable {

		/**
		 * This method is triggered when the column has been checked.
		 *
		 * @param event
		 *            The event which contains the column property id, the
		 *            previous checkbox value of the column and the current
		 *            checkbox value of the column
		 */
		public void columnExpandIcon(ColumnExpandIconEvent event);
	}

	/**
	 * Adds a column ExpandIcon listener to the Table. A column check listener
	 * is called when a user checkes a columns.
	 *
	 * @param listener
	 *            The listener to attach to the Table
	 */
	public void addColumnExpandIconListener(ColumnExpandIconListener listener) {
		addListener("expandIconChange", ColumnExpandIconEvent.class, listener,
				ColumnExpandIconEvent.COLUMN_EXPAND_ICON_METHOD);
	}

	/**
	 * Adds the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #addColumnExpandIconListener(ColumnExpandIconListener)}
	 */
	@Deprecated
	public void addListener(ColumnExpandIconListener listener) {
		addColumnExpandIconListener(listener);
	}

	/**
	 * Removes a column ExpandIcon listener from the Table.
	 *
	 * @param listener
	 *            The listener to remove
	 */
	public void removeColumnExpandIconListener(ColumnExpandIconListener listener) {
		removeListener("expandIconChange", ColumnExpandIconEvent.class, listener);
	}

	/**
	 * Removes the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #removeColumnExpandIconListener(ColumnExpandIconListener)}
	 */
	@Deprecated
	public void removeListener(ColumnExpandIconListener listener) {
		removeColumnExpandIconListener(listener);
	}
	/* Double Header */

	/**
	 * The Class DoubleHeaderColumnExpandIconEvent.
	 */
	public static class DoubleHeaderColumnExpandIconEvent extends Component.Event {

		/**
		 * The Constant COLUMN_EXPAND_ICON_METHOD.
		 */
		public static final Method COLUMN_EXPAND_ICON_METHOD;

		static {
			try {
				COLUMN_EXPAND_ICON_METHOD = DoubleHeaderColumnExpandIconListener.class.getDeclaredMethod(
						"doubleHeaderColumnExpandIcon", new Class[] { DoubleHeaderColumnExpandIconEvent.class });
			} catch (final java.lang.NoSuchMethodException e) {
				// This should never happen
				throw new java.lang.RuntimeException(e);
			}
		}
		/**
		 * The current value.
		 */
		private final boolean currentValue;
		/**
		 * The column property id.
		 */
		private final Object columnPropertyId;

		/**
		 * Constructor.
		 *
		 * @param source
		 *            The source of the event
		 * @param propertyId
		 *            The columns property id
		 * @param current
		 *            The checkbox boolean value of the column after the resize
		 *            event
		 */
		public DoubleHeaderColumnExpandIconEvent(Component source, Object propertyId, boolean current) {
			super(source);
			currentValue = current;
			columnPropertyId = propertyId;
		}

		/**
		 * Get the column property id of the column that was checked.
		 *
		 * @return The column property id
		 */
		public Object getPropertyId() {
			return columnPropertyId;
		}

		/**
		 * Get the checkbox boolean value of the column after the check event.
		 *
		 * @return value in boolean
		 */
		public boolean isExpanded() {
			return currentValue;
		}
	}

	/**
	 * Interface for listening to column ExpandIcon events.
	 *
	 * @see DoubleHeaderColumnExpandIconEvent
	 */
	public interface DoubleHeaderColumnExpandIconListener extends Serializable {

		/**
		 * This method is triggered when the column has been checked.
		 *
		 * @param event
		 *            The event which contains the column property id, the
		 *            previous checkbox value of the column and the current
		 *            checkbox value of the column
		 */
		public void doubleHeaderColumnExpandIcon(DoubleHeaderColumnExpandIconEvent event);
	}

	/**
	 * Adds a column ExpandIcon listener to the Table. A column check listener
	 * is called when a user checkes a columns.
	 *
	 * @param listener
	 *            The listener to attach to the Table
	 */
	public void addDoubleHeaderColumnExpandIconListener(DoubleHeaderColumnExpandIconListener listener) {
		addListener("expandIconChange", DoubleHeaderColumnExpandIconEvent.class, listener,
				DoubleHeaderColumnExpandIconEvent.COLUMN_EXPAND_ICON_METHOD);
	}

	/**
	 * Adds the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #addDoubleHeaderColumnExpandIconListener(DoubleHeaderColumnExpandIconListener)}
	 */
	@Deprecated
	public void addListener(DoubleHeaderColumnExpandIconListener listener) {
		addDoubleHeaderColumnExpandIconListener(listener);
	}

	/**
	 * Removes a column ExpandIcon listener from the Table.
	 *
	 * @param listener
	 *            The listener to remove
	 */
	public void removeDoubleHeaderColumnExpandIconListener(DoubleHeaderColumnExpandIconListener listener) {
		removeListener("expandIconChange", DoubleHeaderColumnExpandIconEvent.class, listener);
	}

	/**
	 * Removes the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #removeDoubleHeaderColumnExpandIconListener(DoubleHeaderColumnExpandIconListener)}
	 */
	@Deprecated
	public void removeListener(DoubleHeaderColumnExpandIconListener listener) {
		removeDoubleHeaderColumnExpandIconListener(listener);
	}

	/* Expand Icon Completed */
	/* RadioButton Started */
	/* SingleHeader */
	/**
	 * The Class ColumnRadioCheckEvent.
	 */
	public static class ColumnRadioCheckEvent extends Component.Event {

		/**
		 * The Constant COLUMN_RADIO_CHECK_METHOD.
		 */
		public static final Method COLUMN_RADIO_CHECK_METHOD;

		static {
			try {
				COLUMN_RADIO_CHECK_METHOD = ColumnRadioCheckListener.class.getDeclaredMethod("columnRadioCheck",
						new Class[] { ColumnRadioCheckEvent.class });
			} catch (final java.lang.NoSuchMethodException e) {
				// This should never happen
				throw new java.lang.RuntimeException(e);
			}
		}
		/**
		 * The previous header.
		 */
		private final String previousHeader;
		/**
		 * The current header.
		 */
		private final String currentHeader;
		/**
		 * The previous value.
		 */
		private final String previousValue;
		/**
		 * The current value.
		 */
		private final String currentValue;
		/**
		 * The radio button name.
		 */
		private final String radioButtonName;

		/**
		 * Constructor.
		 *
		 * @param source
		 *            The source of the event
		 * @param radioName
		 *            The radio buttans name
		 * @param previousHeader
		 *            the previous header
		 * @param currentHeader
		 *            the current header
		 * @param previous
		 *            The checkbox boolean value of the column before the check
		 *            event
		 * @param current
		 *            The checkbox boolean value of the column after the resize
		 *            event
		 */
		public ColumnRadioCheckEvent(Component source, String radioName, String previousHeader, String currentHeader,
				String previous, String current) {
			super(source);
			this.previousHeader = previousHeader;
			this.currentHeader = currentHeader;
			previousValue = previous;
			currentValue = current;
			radioButtonName = radioName;
		}

		/**
		 * Get the radio Button Name of the column that was checked.
		 *
		 * @return The column radio Button Name
		 */
		public String getRadioButtonName() {
			return radioButtonName;
		}

		/**
		 * Get the radioButton String value of the column before the check
		 * event.
		 *
		 * @return value in String
		 */
		public String getPreviousValue() {
			return previousValue;
		}

		/**
		 * Get the radioButton String value of the column after the check event.
		 *
		 * @return value in String
		 */
		public String getCurrentValue() {
			return currentValue;
		}

		/**
		 * Get the radioButton Previous Header Name where value of the
		 * radioButton before the check event.
		 *
		 * @return value in String
		 */
		public String getPreviousHeader() {
			return previousHeader;
		}

		/**
		 * Get the radioButton Current Header Name where value of the
		 * radioButton after the check event.
		 *
		 * @return value in String
		 */
		public String getCurrentHeader() {
			return currentHeader;
		}
	}

	/**
	 * Interface for listening to column check events.
	 *
	 * @see ColumnRadioCheckEvent
	 */
	public interface ColumnRadioCheckListener extends Serializable {

		/**
		 * This method is triggered when the column has been checked.
		 *
		 * @param event
		 *            The event which contains the column property id, the
		 *            previous radioButton value of the column and the current
		 *            radioButton value of the column
		 */
		public void columnRadioCheck(ColumnRadioCheckEvent event);
	}

	/**
	 * Adds a column check listener to the Table. A column check listener is
	 * called when a user checkes a columns.
	 *
	 * @param listener
	 *            The listener to attach to the Table
	 */
	public void addColumnRadioCheckListener(ColumnRadioCheckListener listener) {
		addListener("radioButtonChange", ColumnRadioCheckEvent.class, listener,
				ColumnRadioCheckEvent.COLUMN_RADIO_CHECK_METHOD);
	}

	/**
	 * Adds the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #addColumnRadioCheckListener(ColumnRadioCheckListener)}
	 */
	@Deprecated
	public void addListener(ColumnRadioCheckListener listener) {
		addColumnRadioCheckListener(listener);
	}

	/**
	 * Removes a column check listener from the Table.
	 *
	 * @param listener
	 *            The listener to remove
	 */
	public void removeColumnRadioCheckListener(ColumnRadioCheckListener listener) {
		removeListener("radioButtonChange", ColumnRadioCheckEvent.class, listener);
	}

	/**
	 * Removes the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #removeColumnRadioCheckListener(ColumnRadioCheckListener)}
	 */
	@Deprecated
	public void removeListener(ColumnRadioCheckListener listener) {
		removeColumnRadioCheckListener(listener);
	}

	/* DoubleHeader */
	/**
	 * The Class DoubleHeaderColumnRadioCheckEvent.
	 */
	public static class DoubleHeaderColumnRadioCheckEvent extends Component.Event {

		/**
		 * The Constant COLUMN_RADIO_CHECK_METHOD.
		 */
		public static final Method COLUMN_RADIO_CHECK_METHOD;

		static {
			try {
				COLUMN_RADIO_CHECK_METHOD = DoubleHeaderColumnRadioCheckListener.class.getDeclaredMethod(
						"doubleHeaderColumnRadioCheck", new Class[] { DoubleHeaderColumnRadioCheckEvent.class });
			} catch (final java.lang.NoSuchMethodException e) {
				// This should never happen
				throw new java.lang.RuntimeException(e);
			}
		}
		/**
		 * The previous header.
		 */
		private final String previousHeader;
		/**
		 * The current header.
		 */
		private final String currentHeader;
		/**
		 * The previous value.
		 */
		private final String previousValue;
		/**
		 * The current value.
		 */
		private final String currentValue;
		/**
		 * The radio button name.
		 */
		private final String radioButtonName;

		/**
		 * Constructor.
		 *
		 * @param source
		 *            The source of the event
		 * @param radioName
		 *            The radio buttans name
		 * @param previousHeader
		 *            the previous header
		 * @param currentHeader
		 *            the current header
		 * @param previous
		 *            The checkbox boolean value of the column before the check
		 *            event
		 * @param current
		 *            The checkbox boolean value of the column after the resize
		 *            event
		 */
		public DoubleHeaderColumnRadioCheckEvent(Component source, String radioName, String previousHeader,
				String currentHeader, String previous, String current) {
			super(source);
			this.previousHeader = previousHeader;
			this.currentHeader = currentHeader;
			previousValue = previous;
			currentValue = current;
			radioButtonName = radioName;
		}

		/**
		 * Get the radio Button Name of the column that was checked.
		 *
		 * @return The column radio Button Name
		 */
		public String getRadioButtonName() {
			return radioButtonName;
		}

		/**
		 * Get the radioButton String value of the column before the check
		 * event.
		 *
		 * @return value in String
		 */
		public String getPreviousValue() {
			return previousValue;
		}

		/**
		 * Get the radioButton String value of the column after the check event.
		 *
		 * @return value in String
		 */
		public String getCurrentValue() {
			return currentValue;
		}

		/**
		 * Get the radioButton Previous Header Name where value of the
		 * radioButton before the check event.
		 *
		 * @return value in String
		 */
		public String getPreviousHeader() {
			return previousHeader;
		}

		/**
		 * Get the radioButton Current Header Name where value of the
		 * radioButton after the check event.
		 *
		 * @return value in String
		 */
		public String getCurrentHeader() {
			return currentHeader;
		}
	}

	/**
	 * Interface for listening to column check events.
	 *
	 * @see DoubleHeaderColumnRadioCheckEvent
	 */
	public interface DoubleHeaderColumnRadioCheckListener extends Serializable {

		/**
		 * This method is triggered when the column has been checked.
		 *
		 * @param event
		 *            The event which contains the column property id, the
		 *            previous radioButton value of the column and the current
		 *            radioButton value of the column
		 */
		public void doubleHeaderColumnRadioCheck(DoubleHeaderColumnRadioCheckEvent event);
	}

	/**
	 * Adds a column check listener to the Table. A column check listener is
	 * called when a user checkes a columns.
	 *
	 * @param listener
	 *            The listener to attach to the Table
	 */
	public void addDoubleHeaderColumnRadioCheckListener(DoubleHeaderColumnRadioCheckListener listener) {
		addListener("radioButtonChange", DoubleHeaderColumnRadioCheckEvent.class, listener,
				DoubleHeaderColumnRadioCheckEvent.COLUMN_RADIO_CHECK_METHOD);
	}

	/**
	 * Adds the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #addDoubleHeaderColumnRadioCheckListener(DoubleHeaderColumnRadioCheckListener)}
	 */
	@Deprecated
	public void addListener(DoubleHeaderColumnRadioCheckListener listener) {
		addDoubleHeaderColumnRadioCheckListener(listener);
	}

	/**
	 * Removes a column check listener from the Table.
	 *
	 * @param listener
	 *            The listener to remove
	 */
	public void removeDoubleHeaderColumnRadioCheckListener(DoubleHeaderColumnRadioCheckListener listener) {
		removeListener("radioButtonChange", DoubleHeaderColumnRadioCheckEvent.class, listener);
	}

	/**
	 * Removes the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #removeDoubleHeaderColumnRadioCheckListener(DoubleHeaderColumnRadioCheckListener)}
	 */
	@Deprecated
	public void removeListener(DoubleHeaderColumnRadioCheckListener listener) {
		removeDoubleHeaderColumnRadioCheckListener(listener);
	}

	/* RadioButton Completed */
	/**
	 * Paint double header visible columns.
	 *
	 * @param target
	 *            the target
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintDoubleHeaderVisibleColumns(PaintTarget target) throws PaintException {

		target.startTag("doublevisiblecolumns");
		if (rowHeadersAreEnabled()) {
			target.startTag("dcolumn");
			target.addAttribute("dcid", ROW_HEADER_COLUMN_KEY);
			paintDoubleHeaderColumnWidth(target, ROW_HEADER_FAKE_PROPERTY_ID);
			paintDoubleHeaderColumnExpandRatio(target, ROW_HEADER_FAKE_PROPERTY_ID);
			target.endTag("dcolumn");
		}
		final Collection<?> sortables = getSortableContainerPropertyIds();
		for (Object colId : doubleVisibleColumns) {
			if (colId != null) {
				target.startTag("dcolumn");
				target.addAttribute("dcid", colId.toString());
				final String head = getDoubleHeaderColumnHeader(colId);
				target.addAttribute("caption", (head != null ? head : ""));

				if (isDoubleHeaderColumnCollapsed(colId)) {
					target.addAttribute("dcollapsed", true);
				}
				if (isDoubleHeaderVisible()) {
					if (getDoubleHeaderColumnIcon(colId) != null) {
						target.addAttribute("icon", getDoubleHeaderColumnIcon(colId));
					}
					if (sortables.contains(colId)) {
						target.addAttribute("dsortable", true);
					}
				}
				if (isDoubleHeaderColumnCheckBoxVisible(colId)) {
					target.addAttribute("checkbox", getDoubleHeaderColumnCheckBox(colId));
					target.addAttribute("checkboxdisabled", getDoubleHeaderColumnCheckBoxDisable(colId));
				}
				if (getDoubleHeaderColumnRadioButtonName(colId) != null) {
					target.addAttribute("radio", getDoubleHeaderColumnRadioButtonName(colId));
					target.addAttribute("radiochecked", colId.toString().equals(
							getDoubleHeaderColumnRadioButtonValue(getDoubleHeaderColumnRadioButtonName(colId))));
					target.addAttribute("radiodisabled", getDoubleHeaderColumnRadioButtonDisable(colId));
				}
				if (isDoubleHeaderColumnExpandIcon(colId)) {
					target.addAttribute("eicon", getDoubleHeaderColumnExpandIcon(colId));
				}
				if (!Align.LEFT.equals(getDoubleHeaderColumnAlignment(colId))) {
					target.addAttribute("dalign", getDoubleHeaderColumnAlignment(colId).toString());
				}
				paintDoubleHeaderColumnWidth(target, colId);
				paintDoubleHeaderColumnExpandRatio(target, colId);
				target.endTag("dcolumn");
			}
		}
		target.endTag("doublevisiblecolumns");
	}

	/**
	 * Paint double header available columns.
	 *
	 * @param target
	 *            the target
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintDoubleHeaderAvailableColumns(PaintTarget target) throws PaintException {
		if (doubleColumnCollapsingAllowed) {
			final HashSet<Object> collapsedCols = new HashSet<Object>();
			for (Object colId : doubleVisibleColumns) {
				if (isDoubleHeaderColumnCollapsed(colId)) {
					collapsedCols.add(colId);
				}
			}
			final String[] collapsedKeys = new String[collapsedCols.size()];
			int nextColumn = 0;
			for (Object colId : doubleVisibleColumns) {
				if (isDoubleHeaderColumnCollapsed(colId)) {
					collapsedKeys[nextColumn++] = colId.toString();
				}
			}
			target.addVariable(this, "doublecollapsedcolumns", collapsedKeys);

			final String[] noncollapsibleKeys = new String[doubleNonCollapsibleColumns.size()];
			nextColumn = 0;
			for (Object colId : doubleNonCollapsibleColumns) {
				noncollapsibleKeys[nextColumn++] = colId.toString();
			}
			target.addVariable(this, "doublenoncollapsiblecolumns", noncollapsibleKeys);
		}

	}

	/**
	 * Paint double header column order.
	 *
	 * @param target
	 *            the target
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintDoubleHeaderColumnOrder(PaintTarget target) throws PaintException {
		if (doubleColumnReorderingAllowed) {
			final String[] colorder = new String[doubleVisibleColumns.size()];
			int i = 0;
			for (Object colId : doubleVisibleColumns) {
				colorder[i++] = colId.toString();
			}
			target.addVariable(this, "doublecolumnorder", colorder);
		}
	}

	/**
	 * Gets the icon Resource for the specified column.
	 *
	 * @param propertyId
	 *            the propertyId indentifying the column.
	 * @return the icon for the specified column; null if the column has no icon
	 *         set, or if the column is not visible.
	 */
	public Resource getDoubleHeaderColumnIcon(Object propertyId) {
		return doubleColumnIcons.get(propertyId);
	}

	/**
	 * Sets the icon Resource for the specified column.
	 * <p>
	 * Throws IllegalArgumentException if the specified column is not visible.
	 * </p>
	 *
	 * @param propertyId
	 *            the propertyId identifying the column.
	 * @param icon
	 *            the icon Resource to set.
	 */
	public void setDoubleHeaderColumnIcon(Object propertyId, Resource icon) {

		if (icon == null) {
			doubleColumnIcons.remove(propertyId);
		} else {
			doubleColumnIcons.put(propertyId, icon);
		}

		markAsDirty();
	}

	/**
	 * Gets the header for the specified column.
	 *
	 * @param propertyId
	 *            the propertyId identifying the column.
	 * @return the header for the specified column if it has one.
	 */
	public String getDoubleHeaderColumnHeader(Object propertyId) {
		if (!isDoubleHeaderVisible()) {
			return null;
		}

		String header = doubleColumnHeaders.get(propertyId);
		if (header == null) {
			header = propertyId.toString();
		}

		return header;
	}

	/**
	 * Sets the column header for the specified column;.
	 *
	 * @param propertyId
	 *            the propertyId identifying the column.
	 * @param header
	 *            the header to set.
	 */
	public void setDoubleHeaderColumnHeader(Object propertyId, String header) {

		if (header == null) {
			doubleColumnHeaders.remove(propertyId);
		} else {
			doubleColumnHeaders.put(propertyId, header);
		}

		markAsDirty();
	}

	/**
	 * Gets the specified column's alignment.
	 *
	 * @param propertyId
	 *            the propertyID identifying the column.
	 * @return the specified column's alignment if it as one; {@link Align#LEFT}
	 *         otherwise.
	 */
	public Align getDoubleHeaderColumnAlignment(Object propertyId) {
		final Align a = doubleColumnAlignments.get(propertyId);
		return a == null ? Align.LEFT : a;
	}

	/**
	 * Sets the specified column's alignment.
	 *
	 * <p>
	 * Throws IllegalArgumentException if the alignment is not one of the
	 * following: {@link Align#LEFT}, {@link Align#CENTER} or
	 * {@link Align#RIGHT}
	 * </p>
	 *
	 * @param propertyId
	 *            the propertyID identifying the column.
	 * @param alignment
	 *            the desired alignment.
	 */
	public void setDoubleHeaderColumnAlignment(Object propertyId, Align alignment) {
		if (alignment == null || alignment == Align.LEFT) {
			doubleColumnAlignments.remove(propertyId);
		} else {
			doubleColumnAlignments.put(propertyId, alignment);
		}

		// Assures the visual refresh. No need to reset the page buffer before
		// as the content has not changed, only the alignments.
		refreshRenderedCells();
	}

	/**
	 * Checks if the specified column is collapsed.
	 *
	 * @param propertyId
	 *            the propertyID identifying the column.
	 * @return true if the column is collapsed; false otherwise;
	 */
	public boolean isDoubleHeaderColumnCollapsed(Object propertyId) {
		return doubleCollapsedColumns != null && doubleCollapsedColumns.contains(propertyId);
	}

	/**
	 * Sets whether the specified column is collapsed or not.
	 *
	 *
	 * @param propertyId
	 *            the propertyID identifying the column.
	 * @param collapsed
	 *            the desired collapsedness.
	 * @throws IllegalStateException
	 *             if column collapsing is not allowed
	 */
	public void setDoubleHeaderColumnCollapsed(Object propertyId, boolean collapsed) throws IllegalStateException {
		setDoubleHeaderColumnCollapsed(propertyId, collapsed, true);
	}

	/**
	 * Sets whether the specified column is collapsed or not.
	 *
	 *
	 * @param propertyId
	 *            the propertyID identifying the column.
	 * @param collapsed
	 *            the desired collapsedness.
	 * @throws IllegalStateException
	 *             if column collapsing is not allowed
	 */
	public void setDoubleHeaderColumnCollapsed(Object propertyId, boolean collapsed, boolean refresh)
			throws IllegalStateException {
		if (!isColumnCollapsingAllowed()) {
			throw new IllegalStateException("Column collapsing not allowed!");
		}
		if (collapsed && doubleNonCollapsibleColumns.contains(propertyId)) {
			throw new IllegalStateException("The column is noncollapsible!");
		}

		if (collapsed) {
			doubleCollapsedColumns.add(propertyId);
		} else {
			doubleCollapsedColumns.remove(propertyId);
		}
		String tmp[] = doubleMapVisCol.get(propertyId.toString());
		for (String s : tmp) {
			setColumnCollapsed(s, collapsed, true);
		}
		// Assures the visual refresh
		if (refresh) {
			refreshRowCache();
		}
	}

	/**
	 * Change double header column collapsed.
	 *
	 * @param propertyId
	 *            the property id
	 * @param collapsed
	 *            the collapsed
	 * @throws IllegalStateException
	 *             the illegal state exception
	 */
	private void changeDoubleHeaderColumnCollapsed(Object propertyId, boolean collapsed) throws IllegalStateException {
		if (!isColumnCollapsingAllowed()) {
			throw new IllegalStateException("Column collapsing not allowed!");
		}
		if (collapsed && doubleNonCollapsibleColumns.contains(propertyId)) {
			throw new IllegalStateException("The column is noncollapsible!");
		}

		if (collapsed) {
			doubleCollapsedColumns.add(propertyId);
		} else {
			doubleCollapsedColumns.remove(propertyId);
		}
		// Assures the visual refresh
		refreshRowCache();
	}

	/**
	 * Sets whether the given column is collapsible. Note that collapsible
	 * columns can only be actually collapsed (via UI or with
	 * {@link #setColumnCollapsed(Object, boolean) setColumnCollapsed()}) if
	 * {@link #isColumnCollapsingAllowed()} is true. By default all columns are
	 * collapsible.
	 *
	 * @param propertyId
	 *            the propertyID identifying the column.
	 * @param collapsible
	 *            true if the column should be collapsible, false otherwise.
	 */
	public void setDoubleHeaderColumnCollapsible(Object propertyId, boolean collapsible) {
		if (collapsible) {
			doubleNonCollapsibleColumns.remove(propertyId);
		} else {
			doubleNonCollapsibleColumns.add(propertyId);
			doubleCollapsedColumns.remove(propertyId);
		}
		refreshRowCache();
	}

	/**
	 * Checks if the given column is collapsible. Note that even if this method
	 * returns <code>true</code>, the column can only be actually collapsed (via
	 * UI or with {@link #setColumnCollapsed(Object, boolean)
	 * setColumnCollapsed()}) if {@link #isColumnCollapsingAllowed()} is also
	 * true.
	 *
	 * @param propertyId
	 *            the property id
	 * @return true if the column can be collapsed; false otherwise.
	 */
	public boolean isDoubleHeaderColumnCollapsible(Object propertyId) {
		return !doubleNonCollapsibleColumns.contains(propertyId);
	}

	/**
	 * Checks if column reordering is allowed.
	 *
	 * @return true if columns can be reordered; false otherwise.
	 */
	public boolean isDoubleHeaderColumnReorderingAllowed() {
		return doubleColumnReorderingAllowed;
	}

	/**
	 * Sets whether column reordering is allowed or not.
	 *
	 * @param columnReorderingAllowed
	 *            specifies whether column reordering is allowed.
	 */
	public void setDoubleHeaderColumnReorderingAllowed(boolean columnReorderingAllowed) {
		if (columnReorderingAllowed != this.doubleColumnReorderingAllowed) {
			this.doubleColumnReorderingAllowed = columnReorderingAllowed;
			markAsDirty();
		}
	}

	/*
	 * Arranges visible columns according to given columnOrder. Silently ignores
	 * colimnId:s that are not visible columns, and keeps the internal order of
	 * visible columns left out of the ordering (trailing). Silently does
	 * nothing if columnReordering is not allowed.
	 */
	/**
	 * Sets the double header column order.
	 *
	 * @param columnOrder
	 *            the new double header column order
	 */
	private void setDoubleHeaderColumnOrder(Object[] columnOrder) {
		if (columnOrder == null || !isColumnReorderingAllowed()) {
			return;
		}
		final LinkedList<Object> newOrder = new LinkedList<Object>();
		for (int i = 0; i < columnOrder.length; i++) {
			if (columnOrder[i] != null && doubleVisibleColumns.contains(columnOrder[i])) {
				doubleVisibleColumns.remove(columnOrder[i]);
				newOrder.add(columnOrder[i]);
			}
		}
		for (final Iterator<Object> it = doubleVisibleColumns.iterator(); it.hasNext();) {
			final Object columnId = it.next();
			if (!newOrder.contains(columnId)) {
				newOrder.add(columnId);
			}
		}
		doubleVisibleColumns = newOrder;

		// Assure visual refresh
		refreshRowCache();
	}

	/**
	 * Checks if is double header visible.
	 *
	 * @return true, if is double header visible
	 */
	public boolean isDoubleHeaderVisible() {
		return doubleHeadersVisible;
	}

	/**
	 * Setter for property columnHeaderMode.
	 *
	 * @param visible
	 *            the new double header visible
	 */
	public void setDoubleHeaderVisible(boolean visible) {
		doubleHeadersVisible = visible;
	}

	/**
	 * Paint double header column width.
	 *
	 * @param target
	 *            the target
	 * @param columnId
	 *            the column id
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintDoubleHeaderColumnWidth(PaintTarget target, final Object columnId) throws PaintException {
		if (doubleColumnWidths.containsKey(columnId)) {
			target.addAttribute("dwidth", getDoubleHeaderColumnWidth(columnId));
		}
	}

	/**
	 * Paint double header column expand ratio.
	 *
	 * @param target
	 *            the target
	 * @param columnId
	 *            the column id
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintDoubleHeaderColumnExpandRatio(PaintTarget target, final Object columnId) throws PaintException {
		if (doubleColumnExpandRatios.containsKey(columnId)) {
			target.addAttribute("der", getDoubleHeaderColumnExpandRatio(columnId));
		}
	}

	/**
	 * Gets the double header column width.
	 *
	 * @param propertyId
	 *            the property id
	 * @return the double header column width
	 */
	public int getDoubleHeaderColumnWidth(Object propertyId) {
		if (propertyId == null) {
			// Since propertyId is null, this is the row header. Use the magic
			// id to retrieve the width of the row header.
			propertyId = ROW_HEADER_FAKE_PROPERTY_ID;
		}
		final Integer width = doubleColumnWidths.get(propertyId);
		if (width == null) {
			return -1;
		}
		return width.intValue();
	}

	/**
	 * Gets the double header column expand ratio.
	 *
	 * @param propertyId
	 *            the property id
	 * @return the double header column expand ratio
	 */
	public float getDoubleHeaderColumnExpandRatio(Object propertyId) {
		final Float width = doubleColumnExpandRatios.get(propertyId);
		if (width == null) {
			return -1;
		}
		return width.floatValue();
	}

	/**
	 * Paint double header visible column order.
	 *
	 * @param target
	 *            the target
	 */
	private void paintDoubleHeaderVisibleColumnOrder(PaintTarget target) {
		// Visible column order

		if (isDoubleHeaderVisible()) {
			try {
				target.addAttribute("doublecolheaders", true);
			} catch (PaintException ex) {
				Logger.getLogger(ExtCustomTable.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		final ArrayList<String> visibleColOrder = new ArrayList<String>();
		for (Object columnId : doubleVisibleColumns) {
			if (!isDoubleHeaderColumnCollapsed(columnId)) {
				visibleColOrder.add(columnId.toString());
			}
		}
		target.addAttribute("doublevcolorder", visibleColOrder.toArray());

	}

	/**
	 * Paint double header visible column map.
	 *
	 * @param target
	 *            the target
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintDoubleHeaderVisibleColumnMap(PaintTarget target) throws PaintException {
		// Visible column order
		target.startTag("doublevisiblecolumnsmap");
		if (rowHeadersAreEnabled()) {
			String[] s = { "0" };
			target.addAttribute("0", s);
		}
		for (Object columnId : doubleVisibleColumns) {
			ArrayList<String> visibleColOrder = new ArrayList<String>();
			String tmp[] = doubleMapVisCol.get(columnId);
			for (String columnIdd : tmp) {
				Object columnIddc = getActualMapObject(visibleColumns, columnIdd);
				if (columnIddc != null) {
					visibleColOrder.add(columnIdMap.key(columnIddc));
				}
			}
			target.addAttribute(columnId.toString(), visibleColOrder.toArray());
		}
		target.endTag("doublevisiblecolumnsmap");
	}

	/**
	 * Sets the dependent left table.
	 *
	 * @param leftTable
	 *            the new dependent left table
	 */
	public void setDependentLeftTable(com.vaadin.ui.ExtCustomTable leftTable) {
		this.leftTable = leftTable;
	}

	/**
	 * Sets the dependent right table.
	 *
	 * @param rightTable
	 *            the new dependent right table
	 */
	public void setDependentRightTable(com.vaadin.ui.ExtCustomTable rightTable) {
		this.rightTable = rightTable;
	}

	/**
	 * Sets the double header visible columns.
	 *
	 * @param dvisibleColumns
	 *            the new double header visible columns
	 */
	public void setDoubleHeaderVisibleColumns(Object... dvisibleColumns) {

		// Visible columns must exist
		if (dvisibleColumns == null) {
			throw new NullPointerException("Can not set visible columns to null value");
		}

		final LinkedList<Object> newVC = new LinkedList<Object>();

		// Checks that the new visible columns contains no nulls, properties
		// exist and that there are no duplicates before adding them to newVC.
		for (int i = 0; i < dvisibleColumns.length; i++) {
			if (dvisibleColumns[i] == null) {
				throw new NullPointerException("Ids must be non-nulls");
			} else if (newVC.contains(dvisibleColumns[i])) {
				throw new IllegalArgumentException("Ids must be unique, duplicate id: " + dvisibleColumns[i]);
			} else {
				newVC.add(dvisibleColumns[i]);
			}
		}

		// Removes alignments, icons and headers from hidden columns
		this.doubleVisibleColumns = newVC;
	}

	/**
	 * Gets the double header visible columns.
	 *
	 * @return the double header visible columns
	 */
	public Object[] getDoubleHeaderVisibleColumns() {
		if (doubleVisibleColumns == null) {
			return null;
		}
		return doubleVisibleColumns.toArray();
	}

	/**
	 * Sets the double header column headers.
	 *
	 * @param columnHeaders
	 *            the new double header column headers
	 */
	public void setDoubleHeaderColumnHeaders(String... columnHeaders) {

		if (columnHeaders.length != doubleVisibleColumns.size()) {
			throw new IllegalArgumentException(
					"The length of the doubleheaders array must match the number of doubleheaders visible columns");
		}

		this.doubleColumnHeaders.clear();
		int i = 0;
		for (final Iterator<Object> it = doubleVisibleColumns.iterator(); it.hasNext()
				&& i < columnHeaders.length; i++) {
			this.doubleColumnHeaders.put(it.next(), columnHeaders[i]);
		}

		markAsDirty();
	}

	/**
	 * Gets the double header column headers.
	 *
	 * @return the double header column headers
	 */
	public String[] getDoubleHeaderColumnHeaders() {
		if (doubleColumnHeaders == null) {
			return null;
		}
		final String[] headers = new String[doubleVisibleColumns.size()];
		int i = 0;
		for (final Iterator<Object> it = doubleVisibleColumns.iterator(); it.hasNext(); i++) {
			headers[i] = getDoubleHeaderColumnHeader(it.next());
		}
		return headers;
	}

	/**
	 * Gets the checkbox value for the specified column.
	 *
	 * @param propertyId
	 *            the propertyId indentifying the column.
	 * @return the checkbox value for the specified column; null if the column
	 *         has no checkbox set, or if the column is not visible.
	 */
	public boolean getColumnCheckBox(Object propertyId) {
		return columncheckboxes.get(propertyId);
	}

	/**
	 * Checks if is column check box visible.
	 *
	 * @param propertyId
	 *            the property id
	 * @return true, if is column check box visible
	 */
	public boolean isColumnCheckBoxVisible(Object propertyId) {
		return columncheckboxes.get(propertyId) != null;
	}

	/**
	 * Sets the checkbox Resource for the specified column.
	 * <p>
	 * Throws IllegalArgumentException if the specified column is not visible.
	 * </p>
	 *
	 * @param propertyId
	 *            the propertyId identifying the column.
	 * @param visible
	 *            the visible
	 * @param checked
	 *            the checked value to set.
	 */
	public void setColumnCheckBox(Object propertyId, boolean visible, boolean checked) {
		setColumnCheckBox(propertyId, visible, checked, true);
	}

	/**
	 * Sets the checkbox Resource for the specified column.
	 * <p>
	 * Throws IllegalArgumentException if the specified column is not visible.
	 * </p>
	 *
	 * @param propertyId
	 *            the propertyId identifying the column.
	 * @param visible
	 *            the visible
	 * @param checked
	 *            the checked value to set.
	 */
	public void setColumnCheckBox(Object propertyId, boolean visible, boolean checked, boolean refresh) {
		if (visible) {
			columncheckboxes.put(propertyId, checked);
		}
		if (refresh) {
			markAsDirty();
		}
	}

	/**
	 * Sets the column check box.
	 *
	 * @param propertyId
	 *            the property id
	 * @param visible
	 *            the visible
	 */
	public void setColumnCheckBox(Object propertyId, boolean visible) {
		if (!visible) {
			if (columncheckboxes.containsKey(propertyId)) {
				columncheckboxes.remove(propertyId);
			}
		} else {
			columncheckboxes.put(propertyId, false);
		}
	}

	/**
	 * Sets the column check box disable.
	 *
	 * @param propertyId
	 *            the property id
	 * @param disable
	 *            the disable
	 */
	public void setColumnCheckBoxDisable(Object propertyId, boolean disable) {
		if (!disable) {
			if (columncheckboxesdisable.contains(propertyId)) {
				columncheckboxesdisable.remove(propertyId);
			}
		} else {
			columncheckboxesdisable.add(propertyId);
		}
	}

	/**
	 * Gets the column check box disable.
	 *
	 * @param propertyId
	 *            the property id
	 * @return the column check box disable
	 */
	public boolean getColumnCheckBoxDisable(Object propertyId) {
		return columncheckboxesdisable.contains(propertyId);
	}

	/**
	 * Sets the column radio button.
	 *
	 * @param propertyId
	 *            the property id
	 * @param name
	 *            the name
	 */
	public void setColumnRadioButton(Object propertyId, String name) {
		singlecolumnradioes.put(propertyId.toString(), name);
		if (!singlecolumnradionames.contains(name)) {
			singlecolumnradionames.add(name);
		}
	}

	/**
	 * Removes the column radio button.
	 *
	 * @param propertyId
	 *            the property id
	 */
	public void removeColumnRadioButton(Object propertyId) {
		if (singlecolumnradioes.containsKey(propertyId.toString())) {
			if (singlecolumnradionames.contains(getColumnRadioButtonName(propertyId))) {
				singlecolumnradionames.remove(getColumnRadioButtonName(propertyId));
			}
			singlecolumnradioes.remove(propertyId.toString());
		}
	}

	/**
	 * Sets the column radio button disable.
	 *
	 * @param propertyId
	 *            the property id
	 * @param disable
	 *            the disable
	 */
	public void setColumnRadioButtonDisable(Object propertyId, boolean disable) {
		if (!disable) {
			if (singlecolumnradiodisable.contains(propertyId.toString())) {
				singlecolumnradiodisable.remove(propertyId.toString());
			}
		} else {
			singlecolumnradiodisable.add(propertyId);
		}
	}

	/**
	 * Gets the column radio button disable.
	 *
	 * @param propertyId
	 *            the property id
	 * @return the column radio button disable
	 */
	public boolean getColumnRadioButtonDisable(Object propertyId) {
		return singlecolumnradiodisable.contains(propertyId);
	}

	/**
	 * Sets the column radio button value.
	 *
	 * @param name
	 *            the name
	 * @param propertyId
	 *            the property id
	 */
	public void setColumnRadioButtonValue(String name, Object propertyId) {
		if (name.equals(getColumnRadioButtonName(propertyId))) {
			singleRadioVal.put(name, propertyId.toString());
			if (radioButtonSinks) {
				if (doubleRadioVal.containsKey(name)) {
					doubleRadioVal.remove(name);
				}
				if (tripleRadioVal.containsKey(name)) {
					tripleRadioVal.remove(name);
				}
			}
		}
	}

	/**
	 * Gets the column radio button value.
	 *
	 * @param name
	 *            the name
	 * @return the column radio button value
	 */
	public String getColumnRadioButtonValue(String name) {
		if (singleRadioVal.containsKey(name)) {
			return singleRadioVal.get(name);
		} else {
			return null;
		}
	}

	/**
	 * Gets the column radio button name.
	 *
	 * @param propertyId
	 *            the property id
	 * @return the column radio button name
	 */
	public String getColumnRadioButtonName(Object propertyId) {
		if (singlecolumnradioes.containsKey(propertyId.toString())) {
			return singlecolumnradioes.get(propertyId.toString());
		} else {
			return null;
		}
	}

	/**
	 * Gets the single radio key.
	 *
	 * @param val
	 *            the val
	 * @return the single radio key
	 */
	private String getSingleRadioKey(String val) {
		Set<String> ts = mapSingleRadio.keySet();
		if (ts.size() > 0) {
			String[] s = (String[]) ts.toArray();
			for (int i = 0; i < s.length; i++) {
				String[] valary = mapSingleRadio.get(s[i]);
				for (int j = 0; j < valary.length; j++) {
					if (valary[j].equals(val)) {
						return s[i];
					}
				}
			}
		}
		return null;
	}

	/**
	 * Update column radio buttons name.
	 */
	private void updateColumnRadioButtonsName() {
		HashSet<String> singlecolumnradiona = new HashSet<String>(singlecolumnradionames);
		for (String s : singlecolumnradiona) {
			String[] tmp = getColumnRadioButtonArray(s);
			if (tmp.length <= 0) {
				singlecolumnradionames.remove(s);
			} else {
				mapSingleRadio.put(s, tmp);
			}
		}
	}

	/**
	 * Gets the column radio button array.
	 *
	 * @param name
	 *            the name
	 * @return the column radio button array
	 */
	public String[] getColumnRadioButtonArray(String name) {
		if (!singlecolumnradionames.contains(name)) {
			return null;
		} else {
			ArrayList<String> tmp = new ArrayList<String>();
			Set<String> x = singlecolumnradioes.keySet();
			for (String ob : x) {
				if (name.equals(singlecolumnradioes.get(ob))) {
					tmp.add(ob);
				}
			}
			String[] s = new String[tmp.size()];
			for (int i = 0; i < tmp.size(); i++) {
				s[i] = tmp.get(i);
			}
			return s;
		}
	}

	/**
	 * Paint column radio buttons.
	 *
	 * @param target
	 *            the target
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintColumnRadioButtons(PaintTarget target) throws PaintException {
		updateColumnRadioButtonsName();
		target.startTag("singleradiocolumns");
		for (String colId : singlecolumnradionames) {
			if (colId != null) {
				target.startTag("rcolumn");
				target.addAttribute("singleradio", colId);
				String[] s = mapSingleRadio.get(colId);
				for (int l = 0; l < s.length; l++) {
					s[l] = columnIdMap.key(s[l]);
				}
				target.addAttribute("singleradioarr", s);
				if (getColumnRadioButtonValue(colId) != null) {
					target.addAttribute("singleradioval", columnIdMap.key(getColumnRadioButtonValue(colId)));
				}
				target.endTag("rcolumn");
			}
		}
		target.endTag("singleradiocolumns");
	}

	/**
	 * Gets the checkbox value for the specified column.
	 *
	 * @param propertyId
	 *            the propertyId indentifying the column.
	 * @return the checkbox value for the specified column; null if the column
	 *         has no checkbox set, or if the column is not visible.
	 */
	public boolean getDoubleHeaderColumnCheckBox(Object propertyId) {
		return doublecolumncheckboxes.get(propertyId);
	}

	/**
	 * Checks if is double header column check box visible.
	 *
	 * @param propertyId
	 *            the property id
	 * @return true, if is double header column check box visible
	 */
	public boolean isDoubleHeaderColumnCheckBoxVisible(Object propertyId) {
		return doublecolumncheckboxes.get(propertyId) != null;
	}

	/**
	 * Sets the checkbox Resource for the specified column.
	 * <p>
	 * Throws IllegalArgumentException if the specified column is not visible.
	 * </p>
	 *
	 * @param propertyId
	 *            the propertyId identifying the column.
	 * @param visible
	 *            the visible
	 * @param checked
	 *            the checked value to set.
	 */
	public void setDoubleHeaderColumnCheckBox(Object propertyId, boolean visible, boolean checked) {
		setDoubleHeaderColumnCheckBox(propertyId, visible, checked, true);
	}

	/**
	 * Sets the checkbox Resource for the specified column.
	 * <p>
	 * Throws IllegalArgumentException if the specified column is not visible.
	 * </p>
	 *
	 * @param propertyId
	 *            the propertyId identifying the column.
	 * @param visible
	 *            the visible
	 * @param checked
	 *            the checked value to set.
	 */
	public void setDoubleHeaderColumnCheckBox(Object propertyId, boolean visible, boolean checked, boolean refresh) {

		if (visible) {
			doublecolumncheckboxes.put(propertyId, checked);
		}
		if (refresh) {
			markAsDirty();
		}

	}

	/**
	 * Sets the double header column check box.
	 *
	 * @param propertyId
	 *            the property id
	 * @param visible
	 *            the visible
	 */
	public void setDoubleHeaderColumnCheckBox(Object propertyId, boolean visible) {
		if (!visible) {
			if (doublecolumncheckboxes.containsKey(propertyId)) {
				doublecolumncheckboxes.remove(propertyId);
			}
		} else {
			doublecolumncheckboxes.put(propertyId, false);
		}
	}

	/**
	 * Sets the double header column check box disable.
	 *
	 * @param propertyId
	 *            the property id
	 * @param disable
	 *            the disable
	 */
	public void setDoubleHeaderColumnCheckBoxDisable(Object propertyId, boolean disable) {
		if (!disable) {
			if (doublecolumncheckboxesdisable.contains(propertyId)) {
				doublecolumncheckboxesdisable.remove(propertyId);
			}
		} else {
			doublecolumncheckboxesdisable.add(propertyId);
		}
	}

	/**
	 * Gets the double header column check box disable.
	 *
	 * @param propertyId
	 *            the property id
	 * @return the double header column check box disable
	 */
	public boolean getDoubleHeaderColumnCheckBoxDisable(Object propertyId) {
		return doublecolumncheckboxesdisable.contains(propertyId);
	}

	/**
	 * Sets the double header column radio button.
	 *
	 * @param propertyId
	 *            the property id
	 * @param name
	 *            the name
	 */
	public void setDoubleHeaderColumnRadioButton(Object propertyId, String name) {
		doublecolumnradioes.put(propertyId.toString(), name);
		if (!doublecolumnradionames.contains(name)) {
			doublecolumnradionames.add(name);
		}
	}

	/**
	 * Removes the double header column radio button.
	 *
	 * @param propertyId
	 *            the property id
	 */
	public void removeDoubleHeaderColumnRadioButton(Object propertyId) {
		if (doublecolumnradioes.containsKey(propertyId.toString())) {
			if (doublecolumnradionames.contains(getDoubleHeaderColumnRadioButtonName(propertyId))) {
				doublecolumnradionames.remove(getDoubleHeaderColumnRadioButtonName(propertyId));
			}
			doublecolumnradioes.remove(propertyId.toString());
		}
	}

	/**
	 * Sets the double header column radio button disable.
	 *
	 * @param propertyId
	 *            the property id
	 * @param disable
	 *            the disable
	 */
	public void setDoubleHeaderColumnRadioButtonDisable(Object propertyId, boolean disable) {
		if (!disable) {
			if (doublecolumnradiodisable.contains(propertyId.toString())) {
				doublecolumnradiodisable.remove(propertyId.toString());
			}
		} else {
			doublecolumnradiodisable.add(propertyId);
		}
	}

	/**
	 * Gets the double header column radio button disable.
	 *
	 * @param propertyId
	 *            the property id
	 * @return the double header column radio button disable
	 */
	public boolean getDoubleHeaderColumnRadioButtonDisable(Object propertyId) {
		return doublecolumnradiodisable.contains(propertyId);
	}

	/**
	 * Sets the double header column radio button value.
	 *
	 * @param name
	 *            the name
	 * @param propertyId
	 *            the property id
	 */
	public void setDoubleHeaderColumnRadioButtonValue(String name, Object propertyId) {
		if (name.equals(getDoubleHeaderColumnRadioButtonName(propertyId))) {
			doubleRadioVal.put(name, propertyId.toString());
			if (radioButtonSinks) {
				if (singleRadioVal.containsKey(name)) {
					singleRadioVal.remove(name);
				}
				if (tripleRadioVal.containsKey(name)) {
					tripleRadioVal.remove(name);
				}
			}
		}
	}

	/**
	 * Gets the double header column radio button value.
	 *
	 * @param name
	 *            the name
	 * @return the double header column radio button value
	 */
	public String getDoubleHeaderColumnRadioButtonValue(String name) {
		if (doubleRadioVal.containsKey(name)) {
			return doubleRadioVal.get(name);
		} else {
			return null;
		}
	}

	/**
	 * Gets the double header column radio button name.
	 *
	 * @param propertyId
	 *            the property id
	 * @return the double header column radio button name
	 */
	public String getDoubleHeaderColumnRadioButtonName(Object propertyId) {
		if (doublecolumnradioes.containsKey(propertyId.toString())) {
			return doublecolumnradioes.get(propertyId.toString());
		} else {
			return null;
		}
	}

	/**
	 * Gets the double radio key.
	 *
	 * @param val
	 *            the val
	 * @return the double radio key
	 */
	private String getDoubleRadioKey(String val) {
		Set<String> ts = mapDoubleRadio.keySet();
		if (ts.size() > 0) {
			String[] s = (String[]) ts.toArray();
			for (int i = 0; i < s.length; i++) {
				String[] valary = mapDoubleRadio.get(s[i]);
				for (int j = 0; j < valary.length; j++) {
					if (valary[j].equals(val)) {
						return s[i];
					}
				}
			}
		}
		return null;
	}

	/**
	 * Update double header column radio buttons name.
	 */
	private void updateDoubleHeaderColumnRadioButtonsName() {
		HashSet<String> doublecolumnradiona = new HashSet<String>(doublecolumnradionames);
		for (String s : doublecolumnradiona) {
			String[] tmp = getDoubleHeaderColumnRadioButtonArray(s);
			if (tmp.length <= 0) {
				doublecolumnradionames.remove(s);
			} else {
				mapDoubleRadio.put(s, tmp);
			}
		}
	}

	/**
	 * Gets the double header column radio button array.
	 *
	 * @param name
	 *            the name
	 * @return the double header column radio button array
	 */
	public String[] getDoubleHeaderColumnRadioButtonArray(String name) {
		if (!doublecolumnradionames.contains(name)) {
			return null;
		} else {
			ArrayList<String> tmp = new ArrayList<String>();
			Set<String> x = doublecolumnradioes.keySet();
			for (String ob : x) {
				if (name.equals(doublecolumnradioes.get(ob))) {
					tmp.add(ob);
				}
			}
			String[] s = new String[tmp.size()];
			for (int i = 0; i < tmp.size(); i++) {
				s[i] = tmp.get(i);
			}
			return s;
		}
	}

	/**
	 * Paint double header column radio buttons.
	 *
	 * @param target
	 *            the target
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintDoubleHeaderColumnRadioButtons(PaintTarget target) throws PaintException {
		updateDoubleHeaderColumnRadioButtonsName();
		target.startTag("doubleradiocolumns");
		for (String colId : doublecolumnradionames) {
			if (colId != null) {
				target.startTag("drcolumn");
				target.addAttribute("doubleradio", colId);
				target.addAttribute("doubleradioarr", mapDoubleRadio.get(colId));
				if (getDoubleHeaderColumnRadioButtonValue(colId) != null) {
					target.addAttribute("doubleradioval", getDoubleHeaderColumnRadioButtonValue(colId));
				}
				target.endTag("drcolumn");
			}
		}
		target.endTag("doubleradiocolumns");
	}

	/**
	 * Sets the double header column expand icon.
	 *
	 * @param propertyId
	 *            the property id
	 * @param expanded
	 *            the expanded
	 */
	public void setDoubleHeaderColumnExpandIcon(Object propertyId, boolean expanded) {
		doublecolumnexpandicons.put(propertyId.toString(), expanded);
	}

	/**
	 * Removes the double header column expand icon.
	 *
	 * @param propertyId
	 *            the property id
	 */
	public void removeDoubleHeaderColumnExpandIcon(Object propertyId) {
		if (doublecolumnexpandicons.containsKey(propertyId.toString())) {
			doublecolumnexpandicons.remove(propertyId.toString());
		}
	}

	/**
	 * Gets the double header column expand icon.
	 *
	 * @param propertyId
	 *            the property id
	 * @return the double header column expand icon
	 */
	public boolean getDoubleHeaderColumnExpandIcon(Object propertyId) {
		return doublecolumnexpandicons.get(propertyId.toString());
	}

	/**
	 * Checks if is double header column expand icon.
	 *
	 * @param propertyId
	 *            the property id
	 * @return true, if is double header column expand icon
	 */
	public boolean isDoubleHeaderColumnExpandIcon(Object propertyId) {
		if (doublecolumnexpandicons.containsKey(propertyId.toString())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Sets the column expand icon.
	 *
	 * @param propertyId
	 *            the property id
	 * @param expanded
	 *            the expanded
	 */
	public void setColumnExpandIcon(Object propertyId, boolean expanded) {
		columnexpandicons.put(propertyId.toString(), expanded);
	}

	/**
	 * Removes the column expand icon.
	 *
	 * @param propertyId
	 *            the property id
	 */
	public void removeColumnExpandIcon(Object propertyId) {
		if (columnexpandicons.containsKey(propertyId.toString())) {
			columnexpandicons.remove(propertyId.toString());
		}
	}

	/**
	 * Gets the column expand icon.
	 *
	 * @param propertyId
	 *            the property id
	 * @return the column expand icon
	 */
	public boolean getColumnExpandIcon(Object propertyId) {
		return columnexpandicons.get(propertyId.toString());
	}

	/**
	 * Checks if is column expand icon.
	 *
	 * @param propertyId
	 *            the property id
	 * @return true, if is column expand icon
	 */
	public boolean isColumnExpandIcon(Object propertyId) {
		if (columnexpandicons.containsKey(propertyId.toString())) {
			return true;
		} else {
			return false;
		}
	}

	// Added for TripleHeader
	/**
	 * Sets the triple header visible columns.
	 *
	 * @param tvisibleColumns
	 *            the new triple header visible columns
	 */
	public void setTripleHeaderVisibleColumns(Object... tvisibleColumns) {

		// Visible columns must exist
		if (tvisibleColumns == null) {
			throw new NullPointerException("Can not set visible columns to null value");
		}

		final LinkedList<Object> newVC = new LinkedList<Object>();

		// Checks that the new visible columns contains no nulls, properties
		// exist and that there are no duplicates before adding them to newVC.
		for (int i = 0; i < tvisibleColumns.length; i++) {
			if (tvisibleColumns[i] == null) {
				throw new NullPointerException("Ids must be non-nulls");
			} else if (newVC.contains(tvisibleColumns[i])) {
				throw new IllegalArgumentException("Ids must be unique, duplicate id: " + tvisibleColumns[i]);
			} else {
				newVC.add(tvisibleColumns[i]);
			}
		}

		// Removes alignments, icons and headers from hidden columns
		this.tripleVisibleColumns = newVC;
	}

	/**
	 * Sets the triple header column headers.
	 *
	 * @param columnHeaders
	 *            the new triple header column headers
	 */
	public void setTripleHeaderColumnHeaders(String... columnHeaders) {

		if (columnHeaders.length != tripleVisibleColumns.size()) {
			throw new IllegalArgumentException(
					"The length of the tripleheaders array must match the number of tripleheaders visible columns");
		}

		this.tripleColumnHeaders.clear();
		int i = 0;
		for (final Iterator<Object> it = tripleVisibleColumns.iterator(); it.hasNext()
				&& i < columnHeaders.length; i++) {
			this.tripleColumnHeaders.put(it.next(), columnHeaders[i]);
		}

		markAsDirty();
	}

	/**
	 * Gets the triple header column headers.
	 *
	 * @return the triple header column headers
	 */
	public String[] getTripleHeaderColumnHeaders() {
		if (tripleColumnHeaders == null) {
			return null;
		}
		final String[] headers = new String[tripleColumnHeaders.size()];
		int i = 0;
		for (final Iterator<Object> it = tripleVisibleColumns.iterator(); it.hasNext(); i++) {
			headers[i] = getTripleHeaderColumnHeader(it.next());
		}
		return headers;
	}

	/**
	 * Sets the triple header map.
	 *
	 * @param tmapVisibleCols
	 *            the tmap visible cols
	 */
	public void setTripleHeaderMap(Map<Object, Object[]> tmapVisibleCols) {
		if (tmapVisibleCols == null || tripleVisibleColumns.size() != tmapVisibleCols.size()) {
			throw new NullPointerException("Can not set visible columns map to null value");
		}

		this.tripleMapVisCol.clear();

		for (int i = 0; i < tripleVisibleColumns.size(); i++) {
			if (tripleVisibleColumns.get(i) == null) {
				throw new NullPointerException("Ids must be non-nulls");
			} else {
				String tmp[] = Arrays.copyOf(tmapVisibleCols.get(tripleVisibleColumns.get(i)),
						tmapVisibleCols.get(tripleVisibleColumns.get(i)).length, String[].class);
				this.tripleMapVisCol.put(((tripleVisibleColumns.get(i)).toString()), tmp);
			}
		}

	}

	/**
	 * Sets columns width (in pixels). Theme may not necessary respect very
	 * small or very big values. Setting width to -1 (default) means that theme
	 * will make decision of width.
	 *
	 * <p>
	 * Column can either have a fixed width or expand ratio. The latter one set
	 * is used. See @link {@link #setColumnExpandRatio(Object, float)}.
	 *
	 * @param propertyId
	 *            colunmns property id
	 * @param width
	 *            width to be reserved for colunmns content @since 4.0.3
	 */
	public void setTripleHeaderColumnWidth(Object propertyId, int width) {
		setTripleHeaderColumnWidth(propertyId, width, true);
	}

	/**
	 * Sets columns width (in pixels). Theme may not necessary respect very
	 * small or very big values. Setting width to -1 (default) means that theme
	 * will make decision of width.
	 *
	 * <p>
	 * Column can either have a fixed width or expand ratio. The latter one set
	 * is used. See @link {@link #setColumnExpandRatio(Object, float)}.
	 *
	 * @param propertyId
	 *            colunmns property id
	 * @param width
	 *            width to be reserved for colunmns content @since 4.0.3
	 */
	public void setTripleHeaderColumnWidth(Object propertyId, int width, boolean refresh) {
		if (propertyId == null || "0".equals(propertyId)) {
			// Since propertyId is null, this is the row header. Use the magic
			// id to store the width of the row header.
			propertyId = ROW_HEADER_FAKE_PROPERTY_ID;
		}

		// Setting column width should remove any expand ratios as well
		tripleColumnExpandRatios.remove(propertyId);

		if (width < 0) {
			tripleColumnWidths.remove(propertyId);
		} else {
			tripleColumnWidths.put(propertyId, width);
		}
		if (refresh) {
			markAsDirty();
		}
	}

	/**
	 * Sets the triple header visible.
	 *
	 * @param visible
	 *            the new triple header visible
	 */
	public void setTripleHeaderVisible(boolean visible) {
		tripleHeadersVisible = visible;
	}

	/**
	 * Handle triple header column width updates.
	 *
	 * @param variables
	 *            the variables
	 */
	private void handleTripleHeaderColumnWidthUpdates(Map<String, Object> variables) {
		if (variables.containsKey("tripleColumnWidthUpdates")) {
			String[] events = (String[]) variables.get("tripleColumnWidthUpdates");
			for (String str : events) {
				String[] eventDetails = str.split(":");
				Object propertyId = eventDetails[0];
				if (propertyId == null) {
					propertyId = ROW_HEADER_FAKE_PROPERTY_ID;
				}
				int width = Integer.valueOf(eventDetails[1]);
				setTripleHeaderColumnWidth(propertyId, width, false);
			}
		}
	}

	/**
	 * Handle triple header click event.
	 *
	 * @param variables
	 *            the variables
	 */
	private void handleTripleHeaderClickEvent(Map<String, Object> variables) {

		// TripleHeader click event
		if (variables.containsKey("tripleHeaderClickEvent")) {

			MouseEventDetails details = MouseEventDetails.deSerialize((String) variables.get("tripleHeaderClickEvent"));

			Object cid = variables.get("tripleHeaderClickCID");
			Object propertyId = null;
			if (cid != null) {
				propertyId = cid.toString();
			}
			fireEvent(new TripleHeaderClickEvent(this, propertyId, details));
		}

	}

	/**
	 * Handles the column resize event sent by the client.
	 *
	 * @param variables
	 *            the variables
	 */
	private void handleTripleHeaderColumnResizeEvent(Map<String, Object> variables) {
		if (variables.containsKey("tripleColumnResizeEventColumn")) {
			Object cid = variables.get("tripleColumnResizeEventColumn");
			Object propertyId = null;
			if (cid != null) {
				propertyId = cid.toString();

				Object prev = variables.get("tripleColumnResizeEventPrev");
				int previousWidth = -1;
				if (prev != null) {
					previousWidth = Integer.valueOf(prev.toString());
				}

				Object curr = variables.get("tripleColumnResizeEventCurr");
				int currentWidth = -1;
				if (curr != null) {
					currentWidth = Integer.valueOf(curr.toString());
				}

				fireTripleHeaderColumnResizeEvent(propertyId, previousWidth, currentWidth);
			}
		}
	}

	/**
	 * Fire triple header column resize event.
	 *
	 * @param propertyId
	 *            the property id
	 * @param previousWidth
	 *            the previous width
	 * @param currentWidth
	 *            the current width
	 */
	private void fireTripleHeaderColumnResizeEvent(Object propertyId, int previousWidth, int currentWidth) {
		/*
		 * Update the sizes on the server side. If a column previously had a
		 * expand ratio and the user resized the column then the expand ratio
		 * will be turned into a static pixel size.
		 */
		setTripleHeaderColumnWidth(propertyId, currentWidth, true);

		fireEvent(new TripleHeaderColumnResizeEvent(this, propertyId, previousWidth, currentWidth));
	}

	/**
	 * The Class TripleHeaderColumnResizeEvent.
	 */
	public static class TripleHeaderColumnResizeEvent extends Component.Event {

		/**
		 * The Constant COLUMN_RESIZE_METHOD.
		 */
		public static final Method COLUMN_RESIZE_METHOD;

		static {
			try {
				COLUMN_RESIZE_METHOD = TripleHeaderColumnResizeListener.class.getDeclaredMethod(
						"tripleHeaderColumnResize", new Class[] { TripleHeaderColumnResizeEvent.class });
			} catch (final java.lang.NoSuchMethodException e) {
				// This should never happen
				throw new java.lang.RuntimeException(e);
			}
		}
		/**
		 * The previous width.
		 */
		private final int previousWidth;
		/**
		 * The current width.
		 */
		private final int currentWidth;
		/**
		 * The column property id.
		 */
		private final Object columnPropertyId;

		/**
		 * Constructor.
		 *
		 * @param source
		 *            The source of the event
		 * @param propertyId
		 *            The columns property id
		 * @param previous
		 *            The width in pixels of the column before the resize event
		 * @param current
		 *            The width in pixels of the column after the resize event
		 */
		public TripleHeaderColumnResizeEvent(Component source, Object propertyId, int previous, int current) {
			super(source);
			previousWidth = previous;
			currentWidth = current;
			columnPropertyId = propertyId;
		}

		/**
		 * Get the column property id of the column that was resized.
		 *
		 * @return The column property id
		 */
		public Object getPropertyId() {
			return columnPropertyId;
		}

		/**
		 * Get the width in pixels of the column before the resize event.
		 *
		 * @return Width in pixels
		 */
		public int getPreviousWidth() {
			return previousWidth;
		}

		/**
		 * Get the width in pixels of the column after the resize event.
		 *
		 * @return Width in pixels
		 */
		public int getCurrentWidth() {
			return currentWidth;
		}
	}

	/**
	 * Interface for listening to column resize events.
	 *
	 * @see TripleHeaderColumnResizeEvent
	 */
	public interface TripleHeaderColumnResizeListener extends Serializable {

		/**
		 * This method is triggered when the column has been resized.
		 *
		 * @param event
		 *            The event which contains the column property id, the
		 *            previous width of the column and the current width of the
		 *            column
		 */
		public void tripleHeaderColumnResize(TripleHeaderColumnResizeEvent event);
	}

	/**
	 * Adds a column resize listener to the Table. A column resize listener is
	 * called when a user resizes a columns width.
	 *
	 * @param listener
	 *            The listener to attach to the Table
	 */
	public void addTripleHeaderColumnResizeListener(TripleHeaderColumnResizeListener listener) {
		addListener(TableConstants.COLUMN_RESIZE_EVENT_ID, TripleHeaderColumnResizeEvent.class, listener,
				TripleHeaderColumnResizeEvent.COLUMN_RESIZE_METHOD);
	}

	/**
	 * Adds the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #addColumnResizeListener(ColumnResizeListener)}
	 */
	@Deprecated
	public void addListener(TripleHeaderColumnResizeListener listener) {
		addTripleHeaderColumnResizeListener(listener);
	}

	/**
	 * Removes a column resize listener from the Table.
	 *
	 * @param listener
	 *            The listener to remove
	 */
	public void removeTripleHeaderColumnResizeListener(TripleHeaderColumnResizeListener listener) {
		removeListener(TableConstants.COLUMN_RESIZE_EVENT_ID, TripleHeaderColumnResizeEvent.class, listener);
	}

	/**
	 * Removes the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #removeColumnResizeListener(ColumnResizeListener)}
	 */
	@Deprecated
	public void removeListener(TripleHeaderColumnResizeListener listener) {
		removeTripleHeaderColumnResizeListener(listener);
	}

	/**
	 * Click event fired when clicking on the Table headers. The event includes
	 * a reference the the Table the event originated from, the property id of
	 * the column which header was pressed and details about the mouse event
	 * itself.
	 */
	public static class TripleHeaderClickEvent extends ClickEvent {

		/**
		 * The Constant HEADER_CLICK_METHOD.
		 */
		public static final Method HEADER_CLICK_METHOD;

		static {
			try {
				// Set the header click method
				HEADER_CLICK_METHOD = TripleHeaderClickListener.class.getDeclaredMethod("tripleHeaderClick",
						new Class[] { TripleHeaderClickEvent.class });
			} catch (final java.lang.NoSuchMethodException e) {
				// This should never happen
				throw new java.lang.RuntimeException(e);
			}
		}
		// The property id of the column which header was pressed
		/**
		 * The column property id.
		 */
		private final Object columnPropertyId;

		/**
		 * Instantiates a new triple header click event.
		 *
		 * @param source
		 *            the source
		 * @param propertyId
		 *            the property id
		 * @param details
		 *            the details
		 */
		public TripleHeaderClickEvent(Component source, Object propertyId, MouseEventDetails details) {
			super(source, details);
			columnPropertyId = propertyId;
		}

		/**
		 * Gets the property id of the column which header was pressed.
		 *
		 * @return The column propety id
		 */
		public Object getPropertyId() {
			return columnPropertyId;
		}
	}

	/**
	 * Interface for the listener for column header mouse click events. The
	 * headerClick method is called when the user presses a header column cell.
	 *
	 * @see TripleHeaderClickEvent
	 */
	public interface TripleHeaderClickListener extends Serializable {

		/**
		 * Called when a user clicks a header column cell.
		 *
		 * @param event
		 *            The event which contains information about the column and
		 *            the mouse click event
		 */
		public void tripleHeaderClick(TripleHeaderClickEvent event);
	}

	/**
	 * Adds a header click listener which handles the click events when the user
	 * clicks on a column header cell in the Table.
	 * <p>
	 * The listener will receive events which contain information about which
	 * column was clicked and some details about the mouse event.
	 * </p>
	 *
	 * @param listener
	 *            The handler which should handle the header click events.
	 */
	public void addTripleHeaderClickListener(TripleHeaderClickListener listener) {
		addListener(TableConstants.HEADER_CLICK_EVENT_ID, TripleHeaderClickEvent.class, listener,
				TripleHeaderClickEvent.HEADER_CLICK_METHOD);
	}

	/**
	 * Adds the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #addHeaderClickListener(HeaderClickListener)}
	 */
	@Deprecated
	public void addListener(TripleHeaderClickListener listener) {
		addTripleHeaderClickListener(listener);
	}

	/**
	 * Removes a header click listener.
	 *
	 * @param listener
	 *            The listener to remove.
	 */
	public void removeTripleHeaderClickListener(TripleHeaderClickListener listener) {
		removeListener(TableConstants.HEADER_CLICK_EVENT_ID, TripleHeaderClickEvent.class, listener);
	}

	/**
	 * Removes the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #removeHeaderClickListener(HeaderClickListener)}
	 */
	@Deprecated
	public void removeListener(TripleHeaderClickListener listener) {
		removeTripleHeaderClickListener(listener);
	}

	/**
	 * Gets the triple header visible columns.
	 *
	 * @return the triple header visible columns
	 */
	public Object[] getTripleHeaderVisibleColumns() {
		if (tripleVisibleColumns == null) {
			return null;
		}
		return tripleVisibleColumns.toArray();
	}

	/**
	 * Paint triple header visible columns.
	 *
	 * @param target
	 *            the target
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintTripleHeaderVisibleColumns(PaintTarget target) throws PaintException {

		target.startTag("triplevisiblecolumns");
		if (rowHeadersAreEnabled()) {
			target.startTag("tcolumn");
			target.addAttribute("tcid", ROW_HEADER_COLUMN_KEY);
			paintTripleHeaderColumnWidth(target, ROW_HEADER_FAKE_PROPERTY_ID);
			paintTripleHeaderColumnExpandRatio(target, ROW_HEADER_FAKE_PROPERTY_ID);
			target.endTag("tcolumn");
		}
		final Collection<?> sortables = getSortableContainerPropertyIds();
		for (Object colId : tripleVisibleColumns) {
			if (colId != null) {
				target.startTag("tcolumn");
				target.addAttribute("tcid", colId.toString());
				final String head = getTripleHeaderColumnHeader(colId);
				target.addAttribute("caption", (head != null ? head : ""));

				if (isTripleHeaderColumnCollapsed(colId)) {
					target.addAttribute("tcollapsed", true);
				}
				if (getTripleHeaderColumnIcon(colId) != null) {
					target.addAttribute("icon", getTripleHeaderColumnIcon(colId));
				}
				if (sortables.contains(colId)) {
					target.addAttribute("tsortable", true);
				}
				if (isTripleHeaderColumnCheckBoxVisible(colId)) {
					target.addAttribute("checkbox", getTripleHeaderColumnCheckBox(colId));
					target.addAttribute("checkboxdisabled", isTripleHeaderColumnCheckBoxDisable(colId));
				}
				if (getTripleHeaderColumnRadioButtonName(colId) != null) {
					target.addAttribute("radio", getTripleHeaderColumnRadioButtonName(colId));
					target.addAttribute("radiochecked", colId.toString().equals(
							getTripleHeaderColumnRadioButtonValue(getTripleHeaderColumnRadioButtonName(colId))));
					target.addAttribute("radiodisabled", isTripleHeaderColumnRadioButtonDisable(colId));
				}
				if (isTripleHeaderColumnExpandIcon(colId)) {
					target.addAttribute("eicon", getTripleHeaderColumnExpandIcon(colId));
				}
				if (!Align.LEFT.equals(getTripleHeaderColumnAlignment(colId))) {
					target.addAttribute("talign", getTripleHeaderColumnAlignment(colId).toString());
				}
				paintTripleHeaderColumnWidth(target, colId);
				paintTripleHeaderColumnExpandRatio(target, colId);
				target.endTag("tcolumn");
			}
		}

		target.endTag("triplevisiblecolumns");
	}

	/**
	 * Gets the icon Resource for the specified column.
	 *
	 * @param propertyId
	 *            the propertyId indentifying the column.
	 * @return the icon for the specified column; null if the column has no icon
	 *         set, or if the column is not visible.
	 */
	public Resource getTripleHeaderColumnIcon(Object propertyId) {
		return tripleColumnIcons.get(propertyId);
	}

	/**
	 * Sets the icon Resource for the specified column.
	 * <p>
	 * Throws IllegalArgumentException if the specified column is not visible.
	 * </p>
	 *
	 * @param propertyId
	 *            the propertyId identifying the column.
	 * @param icon
	 *            the icon Resource to set.
	 */
	public void setTripleHeaderColumnIcon(Object propertyId, Resource icon) {

		if (icon == null) {
			tripleColumnIcons.remove(propertyId);
		} else {
			tripleColumnIcons.put(propertyId, icon);
		}

		markAsDirty();
	}

	/**
	 * Paint triple header available columns.
	 *
	 * @param target
	 *            the target
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintTripleHeaderAvailableColumns(PaintTarget target) throws PaintException {
		if (tripleColumnCollapsingAllowed) {
			final HashSet<Object> collapsedCols = new HashSet<Object>();
			for (Object colId : tripleVisibleColumns) {
				if (isTripleHeaderColumnCollapsed(colId)) {
					collapsedCols.add(colId);
				}
			}
			final String[] collapsedKeys = new String[collapsedCols.size()];
			int nextColumn = 0;
			for (Object colId : tripleVisibleColumns) {
				if (isTripleHeaderColumnCollapsed(colId)) {
					collapsedKeys[nextColumn++] = colId.toString();
				}
			}
			target.addVariable(this, "triplecollapsedcolumns", collapsedKeys);

			final String[] noncollapsibleKeys = new String[tripleNonCollapsibleColumns.size()];
			nextColumn = 0;
			for (Object colId : tripleNonCollapsibleColumns) {
				noncollapsibleKeys[nextColumn++] = colId.toString();
			}
			target.addVariable(this, "triplenoncollapsiblecolumns", noncollapsibleKeys);
		}

	}

	/**
	 * Paint triple header column order.
	 *
	 * @param target
	 *            the target
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintTripleHeaderColumnOrder(PaintTarget target) throws PaintException {
		if (tripleColumnReorderingAllowed) {
			final String[] colorder = new String[tripleVisibleColumns.size()];
			int i = 0;
			for (Object colId : tripleVisibleColumns) {
				colorder[i++] = colId.toString();
			}
			target.addVariable(this, "triplecolumnorder", colorder);
		}
	}

	/**
	 * Gets the triple header column header.
	 *
	 * @param propertyId
	 *            the property id
	 * @return the triple header column header
	 */
	public String getTripleHeaderColumnHeader(Object propertyId) {
		if (!isTripleHeaderVisible()) {
			return null;
		}

		String header = tripleColumnHeaders.get(propertyId);
		if (header == null) {
			header = propertyId.toString();
		}

		return header;
	}

	/**
	 * Sets the column header for the specified column;.
	 *
	 * @param propertyId
	 *            the propertyId identifying the column.
	 * @param header
	 *            the header to set.
	 */
	public void setTripleHeaderColumnHeader(Object propertyId, String header) {

		if (header == null) {
			tripleColumnHeaders.remove(propertyId);
		} else {
			tripleColumnHeaders.put(propertyId, header);
		}

		markAsDirty();
	}

	/**
	 * Gets the specified column's alignment.
	 *
	 * @param propertyId
	 *            the propertyID identifying the column.
	 * @return the specified column's alignment if it as one; {@link Align#LEFT}
	 *         otherwise.
	 */
	public Align getTripleHeaderColumnAlignment(Object propertyId) {
		final Align a = tripleColumnAlignments.get(propertyId);
		return a == null ? Align.LEFT : a;
	}

	/**
	 * Sets the specified column's alignment.
	 *
	 * <p>
	 * Throws IllegalArgumentException if the alignment is not one of the
	 * following: {@link Align#LEFT}, {@link Align#CENTER} or
	 * {@link Align#RIGHT}
	 * </p>
	 *
	 * @param propertyId
	 *            the propertyID identifying the column.
	 * @param alignment
	 *            the desired alignment.
	 */
	public void setTripleHeaderColumnAlignment(Object propertyId, Align alignment) {
		if (alignment == null || alignment == Align.LEFT) {
			tripleColumnAlignments.remove(propertyId);
		} else {
			tripleColumnAlignments.put(propertyId, alignment);
		}

		// Assures the visual refresh. No need to reset the page buffer before
		// as the content has not changed, only the alignments.
		refreshRenderedCells();
	}

	/**
	 * Checks if the specified column is collapsed.
	 *
	 * @param propertyId
	 *            the propertyID identifying the column.
	 * @return true if the column is collapsed; false otherwise;
	 */
	public boolean isTripleHeaderColumnCollapsed(Object propertyId) {
		return tripleCollapsedColumns != null && tripleCollapsedColumns.contains(propertyId);
	}

	/**
	 * Sets whether the specified column is collapsed or not.
	 *
	 *
	 * @param propertyId
	 *            the propertyID identifying the column.
	 * @param collapsed
	 *            the desired collapsedness.
	 * @throws IllegalStateException
	 *             if column collapsing is not allowed
	 */
	public void setTripleHeaderColumnCollapsed(Object propertyId, boolean collapsed) throws IllegalStateException {
		if (!isColumnCollapsingAllowed()) {
			throw new IllegalStateException("Column collapsing not allowed!");
		}
		if (collapsed && tripleNonCollapsibleColumns.contains(propertyId)) {
			throw new IllegalStateException("The column is noncollapsible!");
		}

		if (collapsed) {
			tripleCollapsedColumns.add(propertyId);
		} else {
			tripleCollapsedColumns.remove(propertyId);
		}
		String tmp[] = tripleMapVisCol.get(propertyId.toString());
		for (String s : tmp) {
			setDoubleHeaderColumnCollapsed(s, collapsed, true);
		}
		// Assures the visual refresh
		refreshRowCache();
	}

	/**
	 * Change triple header column collapsed.
	 *
	 * @param propertyId
	 *            the property id
	 * @param collapsed
	 *            the collapsed
	 * @throws IllegalStateException
	 *             the illegal state exception
	 */
	private void changeTripleHeaderColumnCollapsed(Object propertyId, boolean collapsed) throws IllegalStateException {
		if (!isColumnCollapsingAllowed()) {
			throw new IllegalStateException("Column collapsing not allowed!");
		}
		if (collapsed && tripleNonCollapsibleColumns.contains(propertyId)) {
			throw new IllegalStateException("The column is noncollapsible!");
		}

		if (collapsed) {
			tripleCollapsedColumns.add(propertyId);
		} else {
			tripleCollapsedColumns.remove(propertyId);
		}
		// Assures the visual refresh
		refreshRowCache();
	}

	/**
	 * Sets whether the given column is collapsible. Note that collapsible
	 * columns can only be actually collapsed (via UI or with
	 * {@link #setColumnCollapsed(Object, boolean) setColumnCollapsed()}) if
	 * {@link #isColumnCollapsingAllowed()} is true. By default all columns are
	 * collapsible.
	 *
	 * @param propertyId
	 *            the propertyID identifying the column.
	 * @param collapsible
	 *            true if the column should be collapsible, false otherwise.
	 */
	public void setTripleHeaderColumnCollapsible(Object propertyId, boolean collapsible) {
		if (collapsible) {
			tripleNonCollapsibleColumns.remove(propertyId);
		} else {
			tripleNonCollapsibleColumns.add(propertyId);
			tripleCollapsedColumns.remove(propertyId);
		}
		refreshRowCache();
	}

	/**
	 * Checks if the given column is collapsible. Note that even if this method
	 * returns <code>true</code>, the column can only be actually collapsed (via
	 * UI or with {@link #setColumnCollapsed(Object, boolean)
	 * setColumnCollapsed()}) if {@link #isColumnCollapsingAllowed()} is also
	 * true.
	 *
	 * @param propertyId
	 *            the property id
	 * @return true if the column can be collapsed; false otherwise.
	 */
	public boolean isTripleHeaderColumnCollapsible(Object propertyId) {
		return !tripleNonCollapsibleColumns.contains(propertyId);
	}

	/**
	 * Checks if column reordering is allowed.
	 *
	 * @return true if columns can be reordered; false otherwise.
	 */
	public boolean isTripleHeaderColumnReorderingAllowed() {
		return tripleColumnReorderingAllowed;
	}

	/**
	 * Sets whether column reordering is allowed or not.
	 *
	 * @param columnReorderingAllowed
	 *            specifies whether column reordering is allowed.
	 */
	public void setTripleHeaderColumnReorderingAllowed(boolean columnReorderingAllowed) {
		if (columnReorderingAllowed != this.tripleColumnReorderingAllowed) {
			this.tripleColumnReorderingAllowed = columnReorderingAllowed;
			markAsDirty();
		}
	}

	/**
	 * Checks if is triple header visible.
	 *
	 * @return true, if is triple header visible
	 */
	public boolean isTripleHeaderVisible() {
		return tripleHeadersVisible;
	}

	/**
	 * Paint triple header column width.
	 *
	 * @param target
	 *            the target
	 * @param columnId
	 *            the column id
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintTripleHeaderColumnWidth(PaintTarget target, final Object columnId) throws PaintException {
		if (tripleColumnWidths.containsKey(columnId)) {
			target.addAttribute("twidth", getTripleHeaderColumnWidth(columnId));
		}
	}

	/**
	 * Paint triple header column expand ratio.
	 *
	 * @param target
	 *            the target
	 * @param columnId
	 *            the column id
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintTripleHeaderColumnExpandRatio(PaintTarget target, final Object columnId) throws PaintException {
		if (tripleColumnExpandRatios.containsKey(columnId)) {
			target.addAttribute("ter", getTripleHeaderColumnExpandRatio(columnId));
		}
	}

	/**
	 * Gets the triple header column width.
	 *
	 * @param propertyId
	 *            the property id
	 * @return the triple header column width
	 */
	public int getTripleHeaderColumnWidth(Object propertyId) {
		if (propertyId == null) {
			// Since propertyId is null, this is the row header. Use the magic
			// id to retrieve the width of the row header.
			propertyId = ROW_HEADER_FAKE_PROPERTY_ID;
		}
		final Integer width = tripleColumnWidths.get(propertyId);
		if (width == null) {
			return -1;
		}
		return width.intValue();
	}

	/**
	 * Gets the triple header column expand ratio.
	 *
	 * @param propertyId
	 *            the property id
	 * @return the triple header column expand ratio
	 */
	public float getTripleHeaderColumnExpandRatio(Object propertyId) {
		final Float width = tripleColumnExpandRatios.get(propertyId);
		if (width == null) {
			return -1;
		}
		return width.floatValue();
	}

	/**
	 * Setter for property columnHeaderMode.
	 *
	 * @param target
	 *            the target
	 */
	private void paintTripleHeaderVisibleColumnOrder(PaintTarget target) {
		// Visible column order

		if (isTripleHeaderVisible()) {
			try {
				target.addAttribute("triplecolheaders", true);
			} catch (PaintException ex) {
				Logger.getLogger(ExtCustomTable.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		final ArrayList<String> visibleColOrder = new ArrayList<String>();
		for (Object columnId : tripleVisibleColumns) {
			if (!isTripleHeaderColumnCollapsed(columnId)) {
				visibleColOrder.add(columnId.toString());
			}
		}
		target.addAttribute("triplevcolorder", visibleColOrder.toArray());

	}

	/**
	 * Paint triple header visible column map.
	 *
	 * @param target
	 *            the target
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintTripleHeaderVisibleColumnMap(PaintTarget target) throws PaintException {
		// Visible column order
		target.startTag("triplevisiblecolumnmap");
		if (rowHeadersAreEnabled()) {
			String[] s = { "0" };
			target.addAttribute("0", s);
		}
		for (Object columnId : tripleVisibleColumns) {
			String tmp[] = tripleMapVisCol.get(columnId);
			target.addAttribute(columnId.toString(), tmp);
		}
		target.endTag("triplevisiblecolumnmap");
	}

	public boolean changeTripleHeaderCollapsingVariables(Map<String, Object> variables) {
		boolean contentRefresh = false;
		if (variables.containsKey("triplecollapsedcolumns")) {
			try {
				final Object[] ids = (Object[]) variables.get("triplecollapsedcolumns");
				Set<Object> idSet = new HashSet<Object>();
				for (Object id : ids) {
					idSet.add(id.toString());
				}
				for (final Iterator<Object> it = tripleVisibleColumns.iterator(); it.hasNext();) {
					Object propertyId = it.next();
					if (isTripleHeaderColumnCollapsed(propertyId)) {
						if (!idSet.contains(propertyId)) {
							changeTripleHeaderColumnCollapsed(propertyId, false);
						}
					} else if (idSet.contains(propertyId)) {
						changeTripleHeaderColumnCollapsed(propertyId, true);
					}
				}
			} catch (final Exception e) {
				// FIXME: Handle exception
				getLogger().log(Level.FINER, "Could not determine triple header column collapsing state", e);
			}
			contentRefresh = true;
		}
		return contentRefresh;
	}

	/**
	 * Change triple header variables.
	 *
	 * @param source
	 *            the source
	 * @param variables
	 *            the variables
	 */
	public void changeTripleHeaderVariables(Object source, Map<String, Object> variables) {

		handleTripleHeaderClickEvent(variables);

		handleTripleHeaderColumnResizeEvent(variables);

		handleTripleHeaderColumnWidthUpdates(variables);
		handleTripleHeaderColumnCheckEvent(variables);
		handleTripleHeaderCheckBoxUpdates(variables);
		handleTripleHeaderColumnRadioCheckEvent(variables);
		handleTripleHeaderRadioUpdates(variables);
		handleTripleHeaderColumnExpandIconEvent(variables);
		handleTripleHeaderExpandIconUpdates(variables);
	}

	/**
	 * Handle triple header column check event.
	 *
	 * @param variables
	 *            the variables
	 */
	private void handleTripleHeaderColumnCheckEvent(Map<String, Object> variables) {
		if (variables.containsKey("tripleColumnCheckEventColumn")) {
			Object cid = variables.get("tripleColumnCheckEventColumn");
			Object propertyId = null;
			if (cid != null) {
				propertyId = cid.toString();

				Object curr = variables.get("tripleColumnCheckEventCurr");
				boolean currentValue = false;
				if (curr != null) {
					currentValue = Boolean.valueOf(curr.toString());
				}

				fireTripleHeaderColumnCheckEvent(propertyId, currentValue);
			}
		}
	}

	/**
	 * Fire triple header column check event.
	 *
	 * @param propertyId
	 *            the property id
	 * @param currentValue
	 *            the current value
	 */
	private void fireTripleHeaderColumnCheckEvent(Object propertyId, boolean currentValue) {
		/*
		 * Update the sizes on the server side. If a column previously had a
		 * expand ratio and the user resized the column then the expand ratio
		 * will be turned into a static pixel size.
		 */
		setTripleHeaderColumnCheckBox(propertyId, true, currentValue, true);

		fireEvent(new TripleHeaderColumnCheckEvent(this, propertyId, currentValue));
	}

	/**
	 * Handle triple header check box updates.
	 *
	 * @param variables
	 *            the variables
	 */
	private void handleTripleHeaderCheckBoxUpdates(Map<String, Object> variables) {
		if (variables.containsKey("tripleColumnCheckBoxUpdates")) {
			String[] events = (String[]) variables.get("tripleColumnCheckBoxUpdates");
			for (String str : events) {
				String[] eventDetails = str.split(":");
				Object propertyId = eventDetails[0];
				if (propertyId == null) {
					propertyId = ROW_HEADER_FAKE_PROPERTY_ID;
				}
				if (isTripleHeaderColumnCheckBoxVisible(propertyId)) {
					boolean check = Boolean.valueOf(eventDetails[1]);
					setTripleHeaderColumnCheckBox(propertyId, true, check, true);
				}
			}
		}
	}

	/**
	 * Gets the checkbox value for the specified column.
	 *
	 * @param propertyId
	 *            the propertyId indentifying the column.
	 * @return the checkbox value for the specified column; null if the column
	 *         has no checkbox set, or if the column is not visible.
	 */
	public boolean getTripleHeaderColumnCheckBox(Object propertyId) {
		return tripleColumnCheckboxes.get(propertyId);
	}

	/**
	 * Checks if is triple header column check box visible.
	 *
	 * @param propertyId
	 *            the property id
	 * @return true, if is triple header column check box visible
	 */
	public boolean isTripleHeaderColumnCheckBoxVisible(Object propertyId) {
		return tripleColumnCheckboxes.get(propertyId) != null;
	}

	/**
	 * Sets the checkbox Resource for the specified column.
	 * <p>
	 * Throws IllegalArgumentException if the specified column is not visible.
	 * </p>
	 *
	 * @param propertyId
	 *            the propertyId identifying the column.
	 * @param visible
	 *            the visible
	 * @param checked
	 *            the checked value to set.
	 */
	public void setTripleHeaderColumnCheckBox(Object propertyId, boolean visible, boolean checked) {
		setTripleHeaderColumnCheckBox(propertyId, visible, checked, true);
	}

	/**
	 * Sets the checkbox Resource for the specified column.
	 * <p>
	 * Throws IllegalArgumentException if the specified column is not visible.
	 * </p>
	 *
	 * @param propertyId
	 *            the propertyId identifying the column.
	 * @param visible
	 *            the visible
	 * @param checked
	 *            the checked value to set.
	 */
	public void setTripleHeaderColumnCheckBox(Object propertyId, boolean visible, boolean checked, boolean refresh) {

		if (visible) {
			tripleColumnCheckboxes.put(propertyId, checked);
		}
		if (refresh) {
			markAsDirty();
		}
	}

	/**
	 * Sets the triple header column check box.
	 *
	 * @param propertyId
	 *            the property id
	 * @param visible
	 *            the visible
	 */
	public void setTripleHeaderColumnCheckBox(Object propertyId, boolean visible) {
		if (!visible) {
			if (tripleColumnCheckboxes.containsKey(propertyId)) {
				tripleColumnCheckboxes.remove(propertyId);
			}
		} else {
			tripleColumnCheckboxes.put(propertyId, false);
		}
	}

	/**
	 * Sets the triple header column check box disable.
	 *
	 * @param propertyId
	 *            the property id
	 * @param disable
	 *            the disable
	 */
	public void setTripleHeaderColumnCheckBoxDisable(Object propertyId, boolean disable) {
		if (!disable) {
			if (tripleColumnCheckboxesDisable.contains(propertyId)) {
				tripleColumnCheckboxesDisable.remove(propertyId);
			}
		} else {
			tripleColumnCheckboxesDisable.add(propertyId);
		}
	}

	/**
	 * Checks if is triple header column check box disable.
	 *
	 * @param propertyId
	 *            the property id
	 * @return true, if is triple header column check box disable
	 */
	public boolean isTripleHeaderColumnCheckBoxDisable(Object propertyId) {
		return tripleColumnCheckboxesDisable.contains(propertyId);
	}

	/**
	 * Sets the triple header column radio button.
	 *
	 * @param propertyId
	 *            the property id
	 * @param name
	 *            the name
	 */
	public void setTripleHeaderColumnRadioButton(Object propertyId, String name) {
		tripleColumnRadioes.put(propertyId.toString(), name);
		if (!tripleColumnRadioNames.contains(name)) {
			tripleColumnRadioNames.add(name);
		}
	}

	/**
	 * Removes the triple header column radio button.
	 *
	 * @param propertyId
	 *            the property id
	 */
	public void removeTripleHeaderColumnRadioButton(Object propertyId) {
		if (tripleColumnRadioes.containsKey(propertyId.toString())) {
			if (tripleColumnRadioNames.contains(getTripleHeaderColumnRadioButtonName(propertyId))) {
				tripleColumnRadioNames.remove(getTripleHeaderColumnRadioButtonName(propertyId));
			}
			tripleColumnRadioes.remove(propertyId.toString());
		}
	}

	/**
	 * Sets the triple header column radio button disable.
	 *
	 * @param propertyId
	 *            the property id
	 * @param disable
	 *            the disable
	 */
	public void setTripleHeaderColumnRadioButtonDisable(Object propertyId, boolean disable) {
		if (!disable) {
			if (tripleColumnRadioDisable.contains(propertyId.toString())) {
				tripleColumnRadioDisable.remove(propertyId.toString());
			}
		} else {
			tripleColumnRadioDisable.add(propertyId);
		}
	}

	/**
	 * Checks if is triple header column radio button disable.
	 *
	 * @param propertyId
	 *            the property id
	 * @return true, if is triple header column radio button disable
	 */
	public boolean isTripleHeaderColumnRadioButtonDisable(Object propertyId) {
		return tripleColumnRadioDisable.contains(propertyId);
	}

	/**
	 * Sets the triple header column radio button value.
	 *
	 * @param name
	 *            the name
	 * @param propertyId
	 *            the property id
	 */
	public void setTripleHeaderColumnRadioButtonValue(String name, Object propertyId) {
		if (name.equals(getTripleHeaderColumnRadioButtonName(propertyId))) {
			tripleRadioVal.put(name, propertyId.toString());
			if (radioButtonSinks) {
				if (doubleRadioVal.containsKey(name)) {
					doubleRadioVal.remove(name);
				}
				if (singleRadioVal.containsKey(name)) {
					singleRadioVal.remove(name);
				}
			}
		}
	}

	/**
	 * Gets the triple header column radio button value.
	 *
	 * @param name
	 *            the name
	 * @return the triple header column radio button value
	 */
	public String getTripleHeaderColumnRadioButtonValue(String name) {
		if (tripleRadioVal.containsKey(name)) {
			return tripleRadioVal.get(name);
		} else {
			return null;
		}
	}

	/**
	 * Gets the triple header column radio button name.
	 *
	 * @param propertyId
	 *            the property id
	 * @return the triple header column radio button name
	 */
	public String getTripleHeaderColumnRadioButtonName(Object propertyId) {
		if (tripleColumnRadioes.containsKey(propertyId.toString())) {
			return tripleColumnRadioes.get(propertyId.toString());
		} else {
			return null;
		}
	}

	/**
	 * Gets the triple radio key.
	 *
	 * @param val
	 *            the val
	 * @return the triple radio key
	 */
	private String getTripleRadioKey(String val) {
		Set<String> ts = mapTripleRadio.keySet();
		if (ts.size() > 0) {
			String[] s = (String[]) ts.toArray();
			for (int i = 0; i < s.length; i++) {
				String[] valary = mapTripleRadio.get(s[i]);
				for (int j = 0; j < valary.length; j++) {
					if (valary[j].equals(val)) {
						return s[i];
					}
				}
			}
		}
		return null;
	}

	/**
	 * Update triple header column radio buttons name.
	 */
	private void updateTripleHeaderColumnRadioButtonsName() {
		HashSet<String> triplecolumnradiona = new HashSet<String>(tripleColumnRadioNames);
		for (String s : triplecolumnradiona) {
			String[] tmp = getTripleHeaderColumnRadioButtonArray(s);
			if (tmp.length <= 0) {
				tripleColumnRadioNames.remove(s);
			} else {
				mapTripleRadio.put(s, tmp);
			}
		}
	}

	/**
	 * Gets the triple header column radio button array.
	 *
	 * @param name
	 *            the name
	 * @return the triple header column radio button array
	 */
	public String[] getTripleHeaderColumnRadioButtonArray(String name) {
		if (!tripleColumnRadioNames.contains(name)) {
			return null;
		} else {
			ArrayList<String> tmp = new ArrayList<String>();
			Set<String> x = tripleColumnRadioes.keySet();
			for (String ob : x) {
				if (name.equals(tripleColumnRadioes.get(ob))) {
					tmp.add(ob);
				}
			}
			String[] s = new String[tmp.size()];
			for (int i = 0; i < tmp.size(); i++) {
				s[i] = tmp.get(i);
			}
			return s;
		}
	}

	/**
	 * Paint triple header column radio buttons.
	 *
	 * @param target
	 *            the target
	 * @throws PaintException
	 *             the paint exception
	 */
	private void paintTripleHeaderColumnRadioButtons(PaintTarget target) throws PaintException {
		updateTripleHeaderColumnRadioButtonsName();
		target.startTag("tripleradiocolumns");
		for (String colId : tripleColumnRadioNames) {
			if (colId != null) {
				target.startTag("trcolumn");
				target.addAttribute("tripleradio", colId);
				target.addAttribute("tripleradioarr", mapTripleRadio.get(colId));
				if (getTripleHeaderColumnRadioButtonValue(colId) != null) {
					target.addAttribute("tripleradioval", getTripleHeaderColumnRadioButtonValue(colId));
				}
				target.endTag("trcolumn");
			}
		}
		target.endTag("tripleradiocolumns");
	}

	/**
	 * Sets the triple header column expand icon.
	 *
	 * @param propertyId
	 *            the property id
	 * @param expanded
	 *            the expanded
	 */
	public void setTripleHeaderColumnExpandIcon(Object propertyId, boolean expanded) {
		tripleColumnExpandIcons.put(propertyId.toString(), expanded);
		// refreshRowCache();
		markAsDirty();
	}

	/**
	 * Removes the triple header column expand icon.
	 *
	 * @param propertyId
	 *            the property id
	 */
	public void removeTripleHeaderColumnExpandIcon(Object propertyId) {
		if (tripleColumnExpandIcons.containsKey(propertyId.toString())) {
			tripleColumnExpandIcons.remove(propertyId.toString());
		}
	}

	/**
	 * Gets the triple header column expand icon.
	 *
	 * @param propertyId
	 *            the property id
	 * @return the triple header column expand icon
	 */
	public boolean getTripleHeaderColumnExpandIcon(Object propertyId) {
		return tripleColumnExpandIcons.get(propertyId.toString());
	}

	/**
	 * Checks if is triple header column expand icon.
	 *
	 * @param propertyId
	 *            the property id
	 * @return true, if is triple header column expand icon
	 */
	public boolean isTripleHeaderColumnExpandIcon(Object propertyId) {
		if (tripleColumnExpandIcons.containsKey(propertyId.toString())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * The Class TripleHeaderColumnCheckEvent.
	 */
	public static class TripleHeaderColumnCheckEvent extends Component.Event {

		/**
		 * The Constant COLUMN_CHECK_METHOD.
		 */
		public static final Method COLUMN_CHECK_METHOD;

		static {
			try {
				COLUMN_CHECK_METHOD = TripleHeaderColumnCheckListener.class.getDeclaredMethod("tripleHeaderColumnCheck",
						new Class[] { TripleHeaderColumnCheckEvent.class });
			} catch (final java.lang.NoSuchMethodException e) {
				// This should never happen
				throw new java.lang.RuntimeException(e);
			}
		}
		/**
		 * The current value.
		 */
		private final boolean currentValue;
		/**
		 * The column property id.
		 */
		private final Object columnPropertyId;

		/**
		 * Constructor.
		 *
		 * @param source
		 *            The source of the event
		 * @param propertyId
		 *            The columns property id
		 * @param current
		 *            The checkbox boolean value of the column after the resize
		 *            event
		 */
		public TripleHeaderColumnCheckEvent(Component source, Object propertyId, boolean current) {
			super(source);
			currentValue = current;
			columnPropertyId = propertyId;
		}

		/**
		 * Get the column property id of the column that was checked.
		 *
		 * @return The column property id
		 */
		public Object getPropertyId() {
			return columnPropertyId;
		}

		/**
		 * Get the checkbox boolean value of the column after the check event.
		 *
		 * @return value in boolean
		 */
		public boolean isChecked() {
			return currentValue;
		}
	}

	/**
	 * Interface for listening to column check events.
	 *
	 * @see TripleHeaderColumnCheckEvent
	 */
	public interface TripleHeaderColumnCheckListener extends Serializable {

		/**
		 * This method is triggered when the column has been checked.
		 *
		 * @param event
		 *            The event which contains the column property id, the
		 *            previous checkbox value of the column and the current
		 *            checkbox value of the column
		 */
		public void tripleHeaderColumnCheck(TripleHeaderColumnCheckEvent event);
	}

	/**
	 * Adds a column check listener to the Table. A column check listener is
	 * called when a user checkes a columns.
	 *
	 * @param listener
	 *            The listener to attach to the Table
	 */
	public void addTripleHeaderColumnCheckListener(TripleHeaderColumnCheckListener listener) {
		addListener("checkBoxChange", TripleHeaderColumnCheckEvent.class, listener,
				TripleHeaderColumnCheckEvent.COLUMN_CHECK_METHOD);
	}

	/**
	 * Adds the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #addTripleHeaderColumnCheckListener(TripleHeaderColumnCheckListener)}
	 */
	@Deprecated
	public void addListener(TripleHeaderColumnCheckListener listener) {
		addTripleHeaderColumnCheckListener(listener);
	}

	/**
	 * Removes a column check listener from the Table.
	 *
	 * @param listener
	 *            The listener to remove
	 */
	public void removeTripleHeaderColumnCheckListener(TripleHeaderColumnCheckListener listener) {
		removeListener("checkBoxChange", TripleHeaderColumnCheckEvent.class, listener);
	}

	/**
	 * Removes the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #removeTripleHeaderColumnCheckListener(TripleHeaderColumnCheckListener)}
	 */
	@Deprecated
	public void removeListener(TripleHeaderColumnCheckListener listener) {
		removeTripleHeaderColumnCheckListener(listener);
	}

	/**
	 * Handle triple header column radio check event.
	 *
	 * @param variables
	 *            the variables
	 */
	private void handleTripleHeaderColumnRadioCheckEvent(Map<String, Object> variables) {
		if (variables.containsKey("tripleColumnRadioEventName")) {
			Object cid = variables.get("tripleColumnRadioEventName");
			String propertyId = null;
			if (cid != null) {
				propertyId = cid.toString();
				String previousHeader = TRIPLE_HEADER;
				String currentHeader = TRIPLE_HEADER;
				Object previousH = variables.get("tripleColumnRadioEventPrevHeader");
				Object currentH = variables.get("tripleColumnRadioEventCurHeader");
				if (previousH != null) {
					previousHeader = previousH.toString();
				}
				if (currentH != null) {
					currentHeader = currentH.toString();
				}
				Object prev = variables.get("tripleColumnRadioEventPrev");
				String previousValue = "";
				if (prev != null) {
					previousValue = prev.toString();
				}

				Object curr = variables.get("tripleColumnRadioEventCurr");
				String currentValue = "";
				if (curr != null) {
					currentValue = curr.toString();
				}

				fireTripleHeaderColumnRadioCheckEvent(propertyId, previousHeader, currentHeader, previousValue,
						currentValue);
			}
		}
	}

	/**
	 * Fire triple header column radio check event.
	 *
	 * @param radioName
	 *            the radio name
	 * @param previousHeader
	 *            the previous header
	 * @param currentHeader
	 *            the current header
	 * @param previousValue
	 *            the previous value
	 * @param currentValue
	 *            the current value
	 */
	private void fireTripleHeaderColumnRadioCheckEvent(String radioName, String previousHeader, String currentHeader,
			String previousValue, String currentValue) {
		/*
		 * Update the sizes on the server side. If a column previously had a
		 * expand ratio and the user resized the column then the expand ratio
		 * will be turned into a static pixel size.
		 */
		setTripleHeaderColumnRadioButtonValue(radioName, currentValue);

		fireEvent(new TripleHeaderColumnRadioCheckEvent(this, radioName, previousHeader, currentHeader, previousValue,
				currentValue));
	}

	/**
	 * Handle triple header radio updates.
	 *
	 * @param variables
	 *            the variables
	 */
	private void handleTripleHeaderRadioUpdates(Map<String, Object> variables) {
		if (variables.containsKey("tripleColumnRadioUpdates")) {
			String[] events = (String[]) variables.get("tripleColumnRadioUpdates");
			tripleRadioVal.clear();
			for (String str : events) {
				String[] eventDetails = str.split(":");
				String radioName = eventDetails[0];
				if (radioName != null) {
					setTripleHeaderColumnRadioButtonValue(radioName, eventDetails[1]);
				}
			}
		}
	}

	/**
	 * Handle triple header column expand icon event.
	 *
	 * @param variables
	 *            the variables
	 */
	private void handleTripleHeaderColumnExpandIconEvent(Map<String, Object> variables) {
		if (variables.containsKey("tripleColumnExpandIconEventColumn")) {
			Object cid = variables.get("tripleColumnExpandIconEventColumn");
			Object propertyId = null;
			if (cid != null) {
				propertyId = cid.toString();

				Object curr = variables.get("tripleColumnExpandIconEventCurr");
				boolean currentValue = false;
				if (curr != null) {
					currentValue = Boolean.valueOf(curr.toString());
				}
				fireTripleHeaderColumnExpandIconEvent(propertyId, currentValue);
			}
		}
	}

	/**
	 * Fire triple header column expand icon event.
	 *
	 * @param propertyId
	 *            the property id
	 * @param currentValue
	 *            the current value
	 */
	private void fireTripleHeaderColumnExpandIconEvent(Object propertyId, boolean currentValue) {
		/*
		 * Update the sizes on the server side. If a column previously had a
		 * expand ratio and the user resized the column then the expand ratio
		 * will be turned into a static pixel size.
		 */
		setTripleHeaderColumnExpandIcon(propertyId, currentValue);
		fireEvent(new TripleHeaderColumnExpandIconEvent(this, propertyId, currentValue));

	}

	/**
	 * Handle triple header expand icon updates.
	 *
	 * @param variables
	 *            the variables
	 */
	private void handleTripleHeaderExpandIconUpdates(Map<String, Object> variables) {
		if (variables.containsKey("tripleColumnExpandIconUpdates")) {
			String[] events = (String[]) variables.get("tripleColumnExpandIconUpdates");
			for (String str : events) {
				String[] eventDetails = str.split(":");
				Object propertyId = eventDetails[0];
				if (propertyId == null) {
					propertyId = ROW_HEADER_FAKE_PROPERTY_ID;
				}
				if (isTripleHeaderColumnExpandIcon(propertyId)) {
					boolean expanded = Boolean.valueOf(eventDetails[1]);
					setTripleHeaderColumnExpandIcon(propertyId, expanded);
				}
			}
		}
	}

	/**
	 * The Class TripleHeaderColumnExpandIconEvent.
	 */
	public static class TripleHeaderColumnExpandIconEvent extends Component.Event {

		/**
		 * The Constant COLUMN_EXPAND_ICON_METHOD.
		 */
		public static final Method COLUMN_EXPAND_ICON_METHOD;

		static {
			try {
				COLUMN_EXPAND_ICON_METHOD = TripleHeaderColumnExpandIconListener.class.getDeclaredMethod(
						"tripleHeaderColumnExpandIcon", new Class[] { TripleHeaderColumnExpandIconEvent.class });
			} catch (final java.lang.NoSuchMethodException e) {
				// This should never happen
				throw new java.lang.RuntimeException(e);
			}
		}
		/**
		 * The current value.
		 */
		private final boolean currentValue;
		/**
		 * The column property id.
		 */
		private final Object columnPropertyId;

		/**
		 * Constructor.
		 *
		 * @param source
		 *            The source of the event
		 * @param propertyId
		 *            The columns property id
		 * @param current
		 *            The checkbox boolean value of the column after the resize
		 *            event
		 */
		public TripleHeaderColumnExpandIconEvent(Component source, Object propertyId, boolean current) {
			super(source);
			currentValue = current;
			columnPropertyId = propertyId;
		}

		/**
		 * Get the column property id of the column that was checked.
		 *
		 * @return The column property id
		 */
		public Object getPropertyId() {
			return columnPropertyId;
		}

		/**
		 * Get the checkbox boolean value of the column after the check event.
		 *
		 * @return value in boolean
		 */
		public boolean isExpanded() {
			return currentValue;
		}
	}

	/**
	 * Interface for listening to column ExpandIcon events.
	 *
	 * @see TripleHeaderColumnExpandIconEvent
	 */
	public interface TripleHeaderColumnExpandIconListener extends Serializable {

		/**
		 * This method is triggered when the column has been checked.
		 *
		 * @param event
		 *            The event which contains the column property id, the
		 *            previous checkbox value of the column and the current
		 *            checkbox value of the column
		 */
		public void tripleHeaderColumnExpandIcon(TripleHeaderColumnExpandIconEvent event);
	}

	/**
	 * Adds a column ExpandIcon listener to the Table. A column check listener
	 * is called when a user checkes a columns.
	 *
	 * @param listener
	 *            The listener to attach to the Table
	 */
	public void addTripleHeaderColumnExpandIconListener(TripleHeaderColumnExpandIconListener listener) {
		addListener("expandIconChange", TripleHeaderColumnExpandIconEvent.class, listener,
				TripleHeaderColumnExpandIconEvent.COLUMN_EXPAND_ICON_METHOD);
	}

	/**
	 * Adds the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #addTripleHeaderColumnExpandIconListener(TripleHeaderColumnExpandIconListener)}
	 */
	@Deprecated
	public void addListener(TripleHeaderColumnExpandIconListener listener) {
		addTripleHeaderColumnExpandIconListener(listener);
	}

	/**
	 * Removes a column ExpandIcon listener from the Table.
	 *
	 * @param listener
	 *            The listener to remove
	 */
	public void removeTripleHeaderColumnExpandIconListener(TripleHeaderColumnExpandIconListener listener) {
		removeListener("expandIconChange", TripleHeaderColumnExpandIconEvent.class, listener);
	}

	/**
	 * Removes the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #removeTripleHeaderColumnExpandIconListener(TripleHeaderColumnExpandIconListener)}
	 */
	@Deprecated
	public void removeListener(TripleHeaderColumnExpandIconListener listener) {
		removeTripleHeaderColumnExpandIconListener(listener);
	}

	/**
	 * The Class TripleHeaderColumnRadioCheckEvent.
	 */
	public static class TripleHeaderColumnRadioCheckEvent extends Component.Event {

		/**
		 * The Constant COLUMN_RADIO_CHECK_METHOD.
		 */
		public static final Method COLUMN_RADIO_CHECK_METHOD;

		static {
			try {
				COLUMN_RADIO_CHECK_METHOD = TripleHeaderColumnRadioCheckListener.class.getDeclaredMethod(
						"tripleHeaderColumnRadioCheck", new Class[] { TripleHeaderColumnRadioCheckEvent.class });
			} catch (final java.lang.NoSuchMethodException e) {
				// This should never happen
				throw new java.lang.RuntimeException(e);
			}
		}
		/**
		 * The previous header.
		 */
		private final String previousHeader;
		/**
		 * The current header.
		 */
		private final String currentHeader;
		/**
		 * The previous value.
		 */
		private final String previousValue;
		/**
		 * The current value.
		 */
		private final String currentValue;
		/**
		 * The radio button name.
		 */
		private final String radioButtonName;

		/**
		 * Constructor.
		 *
		 * @param source
		 *            The source of the event
		 * @param radioName
		 *            The radio buttans name
		 * @param previousHeader
		 *            the previous header
		 * @param currentHeader
		 *            the current header
		 * @param previous
		 *            The checkbox boolean value of the column before the check
		 *            event
		 * @param current
		 *            The checkbox boolean value of the column after the resize
		 *            event
		 */
		public TripleHeaderColumnRadioCheckEvent(Component source, String radioName, String previousHeader,
				String currentHeader, String previous, String current) {
			super(source);
			this.previousHeader = previousHeader;
			this.currentHeader = currentHeader;
			previousValue = previous;
			currentValue = current;
			radioButtonName = radioName;
		}

		/**
		 * Get the radio Button Name of the column that was checked.
		 *
		 * @return The column radio Button Name
		 */
		public String getRadioButtonName() {
			return radioButtonName;
		}

		/**
		 * Get the radioButton String value of the column before the check
		 * event.
		 *
		 * @return value in String
		 */
		public String getPreviousValue() {
			return previousValue;
		}

		/**
		 * Get the radioButton String value of the column after the check event.
		 *
		 * @return value in String
		 */
		public String getCurrentValue() {
			return currentValue;
		}

		/**
		 * Get the radioButton Previous Header Name where value of the
		 * radioButton before the check event.
		 *
		 * @return value in String
		 */
		public String getPreviousHeader() {
			return previousHeader;
		}

		/**
		 * Get the radioButton Current Header Name where value of the
		 * radioButton after the check event.
		 *
		 * @return value in String
		 */
		public String getCurrentHeader() {
			return currentHeader;
		}
	}

	/**
	 * Interface for listening to column check events.
	 *
	 * @see TripleHeaderColumnRadioCheckEvent
	 */
	public interface TripleHeaderColumnRadioCheckListener extends Serializable {

		/**
		 * This method is triggered when the column has been checked.
		 *
		 * @param event
		 *            The event which contains the column property id, the
		 *            previous radioButton value of the column and the current
		 *            radioButton value of the column
		 */
		public void tripleHeaderColumnRadioCheck(TripleHeaderColumnRadioCheckEvent event);
	}

	/**
	 * Adds a column check listener to the Table. A column check listener is
	 * called when a user checkes a columns.
	 *
	 * @param listener
	 *            The listener to attach to the Table
	 */
	public void addTripleHeaderColumnRadioCheckListener(TripleHeaderColumnRadioCheckListener listener) {
		addListener("radioButtonChange", TripleHeaderColumnRadioCheckEvent.class, listener,
				TripleHeaderColumnRadioCheckEvent.COLUMN_RADIO_CHECK_METHOD);
	}

	/**
	 * Adds the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #addTripleHeaderColumnRadioCheckListener(TripleHeaderColumnRadioCheckListener)}
	 */
	@Deprecated
	public void addListener(TripleHeaderColumnRadioCheckListener listener) {
		addTripleHeaderColumnRadioCheckListener(listener);
	}

	/**
	 * Removes a column check listener from the Table.
	 *
	 * @param listener
	 *            The listener to remove
	 */
	public void removeTripleHeaderColumnRadioCheckListener(TripleHeaderColumnRadioCheckListener listener) {
		removeListener("radioButtonChange", TripleHeaderColumnRadioCheckEvent.class, listener);
	}

	/**
	 * Removes the listener.
	 *
	 * @param listener
	 *            the listener
	 * @deprecated As of 7.0, replaced by
	 *             {@link #removeTripleHeaderColumnRadioCheckListener(TripleHeaderColumnRadioCheckListener)}
	 */
	@Deprecated
	public void removeListener(TripleHeaderColumnRadioCheckListener listener) {
		removeTripleHeaderColumnRadioCheckListener(listener);
	}

	/**
	 * Re construct.
	 *
	 * @param isConstruct
	 *            the is construct
	 */
	public void reConstruct(boolean isConstruct) {
		this.isConstruct = isConstruct;
	}

	/**
	 * Checks if is re construct.
	 *
	 * @return true, if is re construct
	 */
	public boolean isReConstruct() {
		return isConstruct;
	}

	/**
	 * Gets the actual map object.
	 *
	 * @param objectList
	 *            the object list
	 * @param o
	 *            the o
	 * @return the actual map object
	 */
	private Object getActualMapObject(LinkedList<Object> objectList, Object o) {

		for (int i = 0; i < objectList.size(); i++) {
			Object x = objectList.get(i);
			if (o.toString().equals(x.toString())) {
				return x;
			}
		}
		return null;
	}

	/**
	 * Gets the manual page length.
	 *
	 * @return the manual page length
	 */
	protected int getManualPageLength() {
		return getPageLength();
	}

	/**
	 * Sink headers radio buttons.
	 *
	 * @param sink
	 *            the sink
	 */
	public void sinkHeadersRadioButtons(boolean sink) {
		this.radioButtonSinks = sink;
	}

	/**
	 * Checks if is sink headers radio buttons.
	 *
	 * @return true, if is sink headers radio buttons
	 */
	public boolean isSinkHeadersRadioButtons() {
		return radioButtonSinks;
	}
	// public RadioButton getRadioButton(String name) {
	// if (radioVal.containsKey(name)) {
	// return radioVal.get(name);
	// } else {
	// return null;
	// }
	// }
	// public void setRadioButton(String name,String header,String propertyId) {
	// if (radioVal.containsKey(name)) {
	// return radioVal.get(name);
	// } else {
	// return null;
	// }
	// }
	// public class RadioButton{
	// String radioButtonName;
	// String currentValue;
	// String previousValue;
	// String currentHeader;
	// String previousHeader;
	//
	// public RadioButton(String radioButtonName, String currentValue, String
	// previousValue, String currentHeader, String previousHeader) {
	// this.radioButtonName = radioButtonName;
	// this.currentValue = currentValue;
	// this.previousValue = previousValue;
	// this.currentHeader = currentHeader;
	// this.previousHeader = previousHeader;
	// }
	//
	// public RadioButton() {
	// }
	// public RadioButton(String radioButtonName) {
	// this.radioButtonName = radioButtonName;
	// }
	// public String getRadioButtonName() {
	// return radioButtonName;
	// }
	//
	// public void setRadioButtonName(String radioButtonName) {
	// this.radioButtonName = radioButtonName;
	// }
	//
	// public String getCurrentValue() {
	// return currentValue;
	// }
	//
	// public void setCurrentValue(String currentValue) {
	// this.currentValue = currentValue;
	// }
	//
	// public String getPreviousValue() {
	// return previousValue;
	// }
	//
	// public void setPreviousValue(String previousValue) {
	// this.previousValue = previousValue;
	// }
	//
	// public String getCurrentHeader() {
	// return currentHeader;
	// }
	//
	// public void setCurrentHeader(String currentHeader) {
	// this.currentHeader = currentHeader;
	// }
	//
	// public String getPreviousHeader() {
	// return previousHeader;
	// }
	//
	// public void setPreviousHeader(String previousHeader) {
	// this.previousHeader = previousHeader;
	// }
	//
	// }

	/**
	 * Checks if is refresh.
	 *
	 * @return true, if is refresh
	 */
	public boolean isRefresh() {
		return refresh;
	}

	/**
	 * Sets the refresh.
	 *
	 * @param refresh
	 *            the new refresh
	 */
	public void setRefresh(boolean refresh) {
		this.refresh = refresh;
		refreshRowCache();
	}

	/**
	 * Do sort operation.
	 *
	 * @param propertyId
	 *            the property id
	 * @param ascending
	 *            the ascending
	 * @return true, if successful
	 */
	protected boolean doSortOperation(Object[] propertyId, boolean[] ascending) {
		return false;
	}

	public Object[] getGeneratedColumn() {
		return columnGenerators.keySet().toArray();
	}

	/**
	 * The Enum Column Expand Icon Style.
	 */
	public enum ColumnExpandIconStyle {
		/** The Icon will look like [+] or [-] and those will be image. */
		ICON_IMAGE_PLUS_MINUS(IMAGE_AS_COLUMN_EXPAND_ICON_PLUS_MINUS),
		/**
		 * Icon will look like (->) or (<-).
		 */
		ARROW_CIRCLE(COLUMN_EXPAND_ICON_STYLE_ARROW_CIRCLE),
		/**
		 * Icon will look like [+] or [-].
		 */
		PLUS_MINUS_SQUARE(COLUMN_EXPAND_ICON_STYLE_PLUS_MINUS_SQUARE);
		private String columnExpandIconStyle;

		/**
		 * Instantiates a new Column Expand Icon Style.
		 *
		 * @param columnExpandIconStyle
		 *            the Column Expand Icon Style
		 */
		private ColumnExpandIconStyle(String columnExpandIconStyle) {
			this.columnExpandIconStyle = columnExpandIconStyle;
		}

		/**
		 * To string.
		 *
		 * @return the string
		 */
		@Override
		public String toString() {
			return columnExpandIconStyle;
		}

		/**
		 * Convert string to ColumnExpandIconStyle.
		 *
		 * @param string
		 *            the string
		 * @return the ColumnExpandIconStyle
		 */
		public ColumnExpandIconStyle convertStringToColumnExpandIconStyle(String string) {
			if (string == null) {
				return null;
			}
			if (string.equals(COLUMN_EXPAND_ICON_STYLE_ARROW_CIRCLE)) {
				return ColumnExpandIconStyle.ARROW_CIRCLE;
			} else if (string.equals(COLUMN_EXPAND_ICON_STYLE_PLUS_MINUS_SQUARE)) {
				return ColumnExpandIconStyle.PLUS_MINUS_SQUARE;
			} else if (string.equals(IMAGE_AS_COLUMN_EXPAND_ICON_PLUS_MINUS)) {
				return ColumnExpandIconStyle.ICON_IMAGE_PLUS_MINUS;
			} else {
				return null;
			}
		}
	}

	public ColumnExpandIconStyle getColumnExpandIconStyle() {
		return columnExpandIconStyle;
	}

	public void setColumnExpandIconStyle(ColumnExpandIconStyle columnExpandIconStyle) {
		if (columnExpandIconStyle != null) {
			this.columnExpandIconStyle = columnExpandIconStyle;
			for (ColumnExpandIconStyle style : ColumnExpandIconStyle.values()) {
				removeStyleName(style.toString());
			}
			addStyleName(columnExpandIconStyle.toString());
		}
	}

	public void setCheckAll(Object propertyId, boolean isChecked) {
		if (columncheckboxes.containsKey(propertyId)) {
			columncheckboxes.put(propertyId, isChecked);
			markAsDirty();
		}
	}
}
