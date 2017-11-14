package org.asi.ui.custommenubar;

import com.vaadin.server.PaintException;
import com.vaadin.server.PaintTarget;
import com.vaadin.server.Resource;
import com.vaadin.shared.ui.menubar.MenuBarConstants;
import com.vaadin.shared.ui.menubar.MenuBarState;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Component.Focusable;
import com.vaadin.ui.LegacyComponent;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

@SuppressWarnings("serial")
public class CustomMenuBar extends AbstractComponent implements LegacyComponent,
        Focusable {

    // Items of the top-level menu
    private final List<CustomMenuItem> menuItems;

    // Number of items in this menu
    private int numberOfItems = 0;

    private CustomMenuItem moreItem;

    private boolean openRootOnHover;

    private boolean htmlContentAllowed;
    private boolean scrollable=false;
    private int pageLength=5;

    @Override
    protected MenuBarState getState() {
        return (MenuBarState) super.getState();
    }

    @Override
    protected MenuBarState getState(boolean markAsDirty) {
        return (MenuBarState) super.getState(markAsDirty);
    }

    /** Paint (serialise) the component for the client.
     * @throws com.vaadin.server.PaintException */
    @Override
    public void paintContent(PaintTarget target) throws PaintException {
        target.addAttribute(MenuBarConstants.OPEN_ROOT_MENU_ON_HOWER,
                openRootOnHover);
        target.addAttribute("scrollable",isScrollable());
        target.addAttribute("pageLength",getPageLength());
        if (isHtmlContentAllowed()) {
            target.addAttribute(MenuBarConstants.HTML_CONTENT_ALLOWED, true);
        }

        target.startTag("options");

        if (getWidth() > -1) {
            target.startTag("moreItem");
            target.addAttribute("text", moreItem.getText());
            if (moreItem.getIcon() != null) {
                target.addAttribute("icon", moreItem.getIcon());
            }
            target.endTag("moreItem");
        }

        target.endTag("options");
        target.startTag("items");

        // This generates the tree from the contents of the menu
        for (CustomMenuItem item : menuItems) {
            paintItem(target, item);
        }

        target.endTag("items");
    }

    private void paintItem(PaintTarget target, CustomMenuItem item)
            throws PaintException {
        if (!item.isVisible()) {
            return;
        }

        target.startTag("item");

        target.addAttribute("id", item.getId());

        if (item.getStyleName() != null) {
            target.addAttribute(MenuBarConstants.ATTRIBUTE_ITEM_STYLE,
                    item.getStyleName());
        }
       
        else if (item.isSeparator()) {
            target.addAttribute("separator", true);
        } else {
            target.addAttribute("text", item.getText());

            Command command = item.getCommand();
            if (command != null) {
                target.addAttribute("command", true);
            }

            Resource icon = item.getIcon();
            if (icon != null) {
                target.addAttribute(MenuBarConstants.ATTRIBUTE_ITEM_ICON, icon);
            }

            if (!item.isEnabled()) {
                target.addAttribute(MenuBarConstants.ATTRIBUTE_ITEM_DISABLED,
                        true);
            }
            target.addAttribute("closable",item.isClosable());
            target.addAttribute("itemclickable",item.isItemClickable());
            target.addAttribute("itemClickNotClosable",item.isItemClickNotClosable());
            target.addAttribute("checkAll",item.isCheckAll());
            target.addAttribute("itemClickSelectable",item. isItemClickSelectable());
            target.addAttribute("itemHoverSelectable",item. isItemHoverSelectable());

            String description = item.getDescription();
            if (description != null && description.length() > 0) {
                target.addAttribute(
                        MenuBarConstants.ATTRIBUTE_ITEM_DESCRIPTION,
                        description);
            }
            if (item.isCheckable()) {
                // if the "checked" attribute is present (either true or false),
                // the item is checkable
                target.addAttribute(MenuBarConstants.ATTRIBUTE_CHECKED,
                        item.isChecked());
            }
            if (item.hasChildren()) {
                for (CustomMenuItem child : item.getChildren()) {
                    paintItem(target, child);
                }
            }

        }

        target.endTag("item");
    }

    /** Deserialize changes received from client. */
    @Override
    public void changeVariables(Object source, Map<String, Object> variables) {
        Stack<CustomMenuItem> items = new Stack<CustomMenuItem>();
        boolean found = false;
        if (variables.containsKey("closeChildEvent")) {
            fireEvent(new SubMenuCloseEvent(this));
        }
        if (variables.containsKey("clickedId") || variables.containsKey("closeId") || variables.containsKey("itemClickId")) {
            Integer clickedId = null;
            if (variables.containsKey("clickedId")) {
                clickedId = (Integer) variables.get("clickedId");
            }else if(variables.containsKey("itemClickId")){
                clickedId = (Integer) variables.get("itemClickId");
            }else if(variables.containsKey("closeId")){
                clickedId = (Integer) variables.get("closeId");
            }
            if(clickedId!=null){
                Iterator<CustomMenuItem> itr = getItems().iterator();
                while (itr.hasNext()) {
                    items.push(itr.next());
                }

                CustomMenuItem tmpItem = null;

                // Go through all the items in the menu
                while (!found && !items.empty()) {
                    tmpItem = items.pop();
                    found = (clickedId.intValue() == tmpItem.getId());

                    if (tmpItem.hasChildren()) {
                        itr = tmpItem.getChildren().iterator();
                        while (itr.hasNext()) {
                            items.push(itr.next());
                        }
                    }

                }// while

                // If we got the clicked item, launch the command.
                if (found && tmpItem.isEnabled()) {
               if(variables.containsKey("clickedId")){
                        if (tmpItem.isCheckable()) {
                            tmpItem.setChecked(!tmpItem.isChecked());
                        }
                        if (null != tmpItem.getCommand()) {
                            tmpItem.getCommand().menuSelected(tmpItem);
                        }
            }else if(variables.containsKey("itemClickId")){
                        if (tmpItem.isCheckable()) {
                            tmpItem.settempChecked((Boolean) variables.get("itemChecked"));
                        }
                        CustomMenuItem parent = tmpItem.getParent();
                        if (parent != null) {
                            List<CustomMenuItem> menuItemss = parent.getChildren();
                            int size = menuItemss.size();
                            if (tmpItem.isCheckAll()) {
                                for (CustomMenuItem siblingItem : menuItemss) {
                                    if (!tmpItem.equals(siblingItem)) {
                                        siblingItem.settempChecked((Boolean) variables.get("itemChecked"));
                                    }
                                }
                            } else {
                                int x = 0;
                                boolean value = (Boolean) variables.get("chkValue");
                                int chkAllItem = Integer.valueOf(String.valueOf(variables.get("checkAllItem"))).intValue();
                                for (int i = 0; i < size && x < chkAllItem; i++) {
                                    CustomMenuItem siblingItem = menuItemss.get(i);
                                    if (siblingItem.isCheckAll()) {
                                        siblingItem.settempChecked(value);
                                        x++;
                                    }
                                }
                            }
                        }
                        fireEvent(new ItemClickEvent(this, tmpItem));
            }else if(variables.containsKey("closeId")){
                        fireEvent(new ItemCloseClickEvent(this, tmpItem));
                    }

                }
            }
        }// if
    }// changeVariables

    /**
     * Constructs an empty, horizontal menu
     */
    public CustomMenuBar() {
        menuItems = new ArrayList<CustomMenuItem>();
        setMoreCustomMenuItem(null);
    }

    /**
     * Add a new item to the menu bar. Command can be null, but a caption must
     * be given.
     *
     * @param caption
     *            the text for the menu item
     * @param command
     *            the command for the menu item
     * @throws IllegalArgumentException
     */
    public CustomMenuBar.CustomMenuItem addItem(String caption, CustomMenuBar.Command command) {
        return addItem(new MenuItemDTO(caption), null, command);
    }
    /**
     * Add a new item to the menu bar. Command can be null, but a captionDTO must
     * be given.
     *
     * @param captionDTO
     *            the MenuItemDTO for the menu item which contains caption
     * @param command
     *            the command for the menu item
     * @throws IllegalArgumentException
     */
    public CustomMenuBar.CustomMenuItem addItem(MenuItemDTO captionDTO, CustomMenuBar.Command command) {
        return addItem(captionDTO, null, command);
    }

    /**
     * Add a new item to the menu bar. Icon and command can be null, but a
     * caption must be given.
     *
     * @param caption
     *            the text for the menu item
     * @param icon
     *            the icon for the menu item
     * @param command
     *            the command for the menu item
     * @throws IllegalArgumentException
     */
    public CustomMenuBar.CustomMenuItem addItem(String caption, Resource icon,
            CustomMenuBar.Command command) {
        if (caption == null) {
            throw new IllegalArgumentException("caption cannot be null");
        }
        return addItem(new MenuItemDTO(caption), icon, command);

    }
    /**
     * Add a new item to the menu bar. Icon and command can be null, but a
     * captionDTO must be given.
     *
     * @param captionDTO
     *            the MenuItemDTO for the menu item which contains caption
     * @param icon
     *            the icon for the menu item
     * @param command
     *            the command for the menu item
     * @throws IllegalArgumentException
     */
    public CustomMenuBar.CustomMenuItem addItem(MenuItemDTO captionDTO, Resource icon,
            CustomMenuBar.Command command) {
        if (captionDTO == null) {
            throw new IllegalArgumentException("captionDTO cannot be null");
        }
        CustomMenuItem newItem = new CustomMenuItem(captionDTO, icon, command);
        menuItems.add(newItem);
        markAsDirty();

        return newItem;

    }

    /**
     * Add an item before some item. If the given item does not exist the item
     * is added at the end of the menu. Icon and command can be null, but a
     * caption must be given.
     *
     * @param caption
     *            the text for the menu item
     * @param icon
     *            the icon for the menu item
     * @param command
     *            the command for the menu item
     * @param itemToAddBefore
     *            the item that will be after the new item
     * @throws IllegalArgumentException
     */
    public CustomMenuBar.CustomMenuItem addItemBefore(String caption, Resource icon,
            CustomMenuBar.Command command, CustomMenuBar.CustomMenuItem itemToAddBefore) {
        if (caption == null) {
            throw new IllegalArgumentException("caption cannot be null");
        }
        return addItemBefore(new MenuItemDTO(caption), icon, command,itemToAddBefore);
    }
    /**
     * Add an item before some item. If the given item does not exist the item
     * is added at the end of the menu. Icon and command can be null, but a
     * captionDTO must be given.
     *
     * @param captionDTO
     *            the MenuItemDTO for the menu item which contains caption
     * @param icon
     *            the icon for the menu item
     * @param command
     *            the command for the menu item
     * @param itemToAddBefore
     *            the item that will be after the new item
     * @throws IllegalArgumentException
     */
    public CustomMenuBar.CustomMenuItem addItemBefore(MenuItemDTO captionDTO, Resource icon,
            CustomMenuBar.Command command, CustomMenuBar.CustomMenuItem itemToAddBefore) {
        if (captionDTO == null) {
            throw new IllegalArgumentException("captionDTO cannot be null");
        }

        CustomMenuItem newItem = new CustomMenuItem(captionDTO, icon, command);
        if (menuItems.contains(itemToAddBefore)) {
            int index = menuItems.indexOf(itemToAddBefore);
            menuItems.add(index, newItem);

        } else {
            menuItems.add(newItem);
        }

        markAsDirty();

        return newItem;
    }
    /**
     * Returns a list with all the CustomMenuItem objects in the menu bar
     *
     * @return a list containing the CustomMenuItem objects in the menu bar
     */
    public List<CustomMenuItem> getItems() {
        return menuItems;
    }

    /**
     * Remove first occurrence the specified item from the main menu
     *
     * @param item
     *            The item to be removed
     */
    public void removeItem(CustomMenuBar.CustomMenuItem item) {
        if (item != null) {
            menuItems.remove(item);
        }
        markAsDirty();
    }

    /**
     * Empty the menu bar
     */
    public void removeItems() {
        menuItems.clear();
        markAsDirty();
    }

    /**
     * Returns the size of the menu.
     *
     * @return The size of the menu
     */
    public int getSize() {
        return menuItems.size();
    }

    /**
     * Set the item that is used when collapsing the top level menu. All
     * "overflowing" items will be added below this. The item command will be
     * ignored. If set to null, the default item with a downwards arrow is used.
     *
     * The item command (if specified) is ignored.
     *
     * @param item
     */
    public void setMoreCustomMenuItem(CustomMenuItem item) {
        if (item != null) {
            moreItem = item;
        } else {
            moreItem = new CustomMenuItem(new MenuItemDTO(), null, null);
        }
        markAsDirty();
    }

    /**
     * Get the CustomMenuItem used as the collapse menu item.
     *
     * @return
     */
    public CustomMenuItem getMoreCustomMenuItem() {
        return moreItem;
    }

    /**
     * Using this method menubar can be put into a special mode where top level
     * menus opens without clicking on the menu, but automatically when mouse
     * cursor is moved over the menu. In this mode the menu also closes itself
     * if the mouse is moved out of the opened menu.
     * <p>
     * Note, that on touch devices the menu still opens on a click event.
     *
     * @param autoOpenTopLevelMenu
     *            true if menus should be opened without click, the default is
     *            false
     */
    public void setAutoOpen(boolean autoOpenTopLevelMenu) {
        if (autoOpenTopLevelMenu != openRootOnHover) {
            openRootOnHover = autoOpenTopLevelMenu;
            markAsDirty();
        }
    }

    /**
     * Detects whether the menubar is in a mode where top level menus are
     * automatically opened when the mouse cursor is moved over the menu.
     * Normally root menu opens only by clicking on the menu. Submenus always
     * open automatically.
     *
     * @return true if the root menus open without click, the default is false
     */
    public boolean isAutoOpen() {
        return openRootOnHover;
    }

    /**
     * Sets whether html is allowed in the item captions. If set to true, the
     * captions are passed to the browser as html and the developer is
     * responsible for ensuring no harmful html is used. If set to false, the
     * content is passed to the browser as plain text.
     *
     * @param htmlContentAllowed
     *            true if the captions are used as html, false if used as plain
     *            text
     */
    public void setHtmlContentAllowed(boolean htmlContentAllowed) {
        this.htmlContentAllowed = htmlContentAllowed;
        markAsDirty();
    }

    /**
     * Checks whether item captions are interpreted as html or plain text.
     *
     * @return true if the captions are used as html, false if used as plain
     *         text
     * @see #setHtmlContentAllowed(boolean)
     */
    public boolean isHtmlContentAllowed() {
        return htmlContentAllowed;
    }

    @Override
    public int getTabIndex() {
        return getState(false).tabIndex;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.vaadin.ui.Component.Focusable#setTabIndex(int)
     */
    @Override
    public void setTabIndex(int tabIndex) {
        getState().tabIndex = tabIndex;
    }

    @Override
    public void focus() {
        // Overridden only to make public
        super.focus();
    }

    /**
     * This interface contains the layer for menu commands of the
     * {@link com.vaadin.ui.MenuBar} class. It's method will fire when the user
     * clicks on the containing {@link com.vaadin.ui.MenuBar.CustomMenuItem}. The
     * selected item is given as an argument.
     */
    public interface Command extends Serializable {
        public void menuSelected(CustomMenuBar.CustomMenuItem selectedItem);
    }

    /**
     * A composite class for menu items and sub-menus. You can set commands to
     * be fired on user click by implementing the
     * {@link com.vaadin.ui.MenuBar.Command} interface. You can also add
     * multiple CustomMenuItems to a CustomMenuItem and create a sub-menu.
     *
     */
    public class CustomMenuItem implements Serializable {

        /** Private members * */
        private final int itsId;
        private Command itsCommand;
        private MenuItemDTO menuItemDTO;
        private List<CustomMenuItem> itsChildren;
        private Resource itsIcon;
        private CustomMenuItem itsParent;
        private boolean enabled = true;
        private boolean visible = true;
        private boolean isSeparator = false;
        private String styleName;
        private String description;
        private boolean checkable = false;
        private boolean checked = false;
        private boolean closable = false;
        private boolean itemClickable = false;
        private boolean itemClickNotClosable = false;
        private boolean checkAll = false;
        private boolean itemClickSelectable = false;
        private boolean itemHoverSelectable = true;
        /**
         * Constructs a new menu item that can optionally have an icon and a
         * command associated with it. Icon and command can be null, but a
         * caption must be given.
         *
         * @param text
         *            The text associated with the command
         * @param command
         *            The command to be fired
         * @throws IllegalArgumentException
         */
        public CustomMenuItem(MenuItemDTO captionDTO, Resource icon, CustomMenuBar.Command command) {
            if (captionDTO == null) {
                throw new IllegalArgumentException("caption cannot be null");
            }
            itsId = ++numberOfItems;
            menuItemDTO=captionDTO;
            itsIcon = icon;
            itsCommand = command;
        }

        /**
         * Checks if the item has children (if it is a sub-menu).
         *
         * @return True if this item has children
         */
        public boolean hasChildren() {
            return !isSeparator() && itsChildren != null;
        }

        /**
         * Adds a separator to this menu. A separator is a way to visually group
         * items in a menu, to make it easier for users to find what they are
         * looking for in the menu.
         *
         * @author Jouni Koivuviita / Vaadin Ltd.
         * @since 6.2.0
         */
        public CustomMenuBar.CustomMenuItem addSeparator() {
            CustomMenuItem item = addItem(new MenuItemDTO(), null, null);
            item.setSeparator(true);
            return item;
        }
        /**
         * Adds a separator to this menu. A separator is a way to visually group
         * items in a menu, to make it easier for users to find what they are
         * looking for in the menu.
         *
         * @author Jouni Koivuviita / Vaadin Ltd.
         * @since 6.2.0
         */

        public CustomMenuBar.CustomMenuItem addSeparatorBefore(CustomMenuItem itemToAddBefore) {
            CustomMenuItem item = addItemBefore(new MenuItemDTO(), null, null, itemToAddBefore);
            item.setSeparator(true);
            return item;
        }

        /**
         * Add a new item inside this item, thus creating a sub-menu. Command
         * can be null, but a caption must be given.
         *
         * @param caption
         *            the text for the menu item
         * @param command
         *            the command for the menu item
         */
        public CustomMenuBar.CustomMenuItem addItem(String caption, CustomMenuBar.Command command) {
            return addItem(new MenuItemDTO(caption), null, command);
        }
        /**
         * Add a new item inside this item, thus creating a sub-menu. Command
         * can be null, but a captionDTO must be given.
         *
         * @param captionDTO
     *            the MenuItemDTO for the menu item which contains caption
         * @param command
         *            the command for the menu item
         */
        public CustomMenuBar.CustomMenuItem addItem(MenuItemDTO captionDTO, CustomMenuBar.Command command) {
            return addItem(captionDTO, null, command);
        }
        /**
         * Add a new item inside this item, thus creating a sub-menu. Icon and
         * command can be null, but a caption must be given.
         *
         * @param caption
         *            the text for the menu item
         * @param icon
         *            the icon for the menu item
         * @param command
         *            the command for the menu item
         * @throws IllegalStateException
         *             If the item is checkable and thus cannot have children.
         */
        public CustomMenuBar.CustomMenuItem addItem(String caption, Resource icon,
                CustomMenuBar.Command command) throws IllegalStateException {
            return addItem(new MenuItemDTO(caption),icon,command);
        }
        /**
         * Add a new item inside this item, thus creating a sub-menu. Icon and
         * command can be null, but a captionDTO must be given.
         *
         * @param captionDTO
     *            the MenuItemDTO for the menu item which contains caption
         * @param icon
         *            the icon for the menu item
         * @param command
         *            the command for the menu item
         * @throws IllegalStateException
         *             If the item is checkable and thus cannot have children.
         */
        public CustomMenuBar.CustomMenuItem addItem(MenuItemDTO captionDTO, Resource icon,
                CustomMenuBar.Command command) throws IllegalStateException {
            if (isSeparator()) {
                throw new UnsupportedOperationException(
                        "Cannot add items to a separator");
            }
            if (isCheckable()) {
                throw new IllegalStateException(
                        "A checkable item cannot have children");
            }
            if (captionDTO == null) {
                throw new IllegalArgumentException("Caption cannot be null");
            }

            if (itsChildren == null) {
                itsChildren = new ArrayList<CustomMenuItem>();
            }

            CustomMenuItem newItem = new CustomMenuItem(captionDTO, icon, command);

            // The only place where the parent is set
            newItem.setParent(this);
            itsChildren.add(newItem);

            markAsDirty();

            return newItem;
        }
        /**
         * Add an item before some item. If the given item does not exist the
         * item is added at the end of the menu. Icon and command can be null,
         * but a caption must be given.
         *
         * @param caption
         *            the text for the menu item
         * @param icon
         *            the icon for the menu item
         * @param command
         *            the command for the menu item
         * @param itemToAddBefore
         *            the item that will be after the new item
         * @throws IllegalStateException
         *             If the item is checkable and thus cannot have children.
         */
        public CustomMenuBar.CustomMenuItem addItemBefore(String caption, Resource icon,
                CustomMenuBar.Command command, CustomMenuBar.CustomMenuItem itemToAddBefore)
                throws IllegalStateException {
            return addItemBefore(new MenuItemDTO(caption),icon,command,itemToAddBefore);
        }
        /**
         * Add an item before some item. If the given item does not exist the
         * item is added at the end of the menu. Icon and command can be null,
         * but a captionDTO must be given.
         *
         * @param captionDTO
     *            the MenuItemDTO for the menu item which contains caption
         * @param icon
         *            the icon for the menu item
         * @param command
         *            the command for the menu item
         * @param itemToAddBefore
         *            the item that will be after the new item
         * @throws IllegalStateException
         *             If the item is checkable and thus cannot have children.
         */
        public CustomMenuBar.CustomMenuItem addItemBefore(MenuItemDTO captionDTO, Resource icon,
                CustomMenuBar.Command command, CustomMenuBar.CustomMenuItem itemToAddBefore)
                throws IllegalStateException {
            if (isCheckable()) {
                throw new IllegalStateException(
                        "A checkable item cannot have children");
            }
            CustomMenuItem newItem = null;

            if (hasChildren() && itsChildren.contains(itemToAddBefore)) {
                int index = itsChildren.indexOf(itemToAddBefore);
                newItem = new CustomMenuItem(captionDTO, icon, command);
                newItem.setParent(this);
                itsChildren.add(index, newItem);
            } else {
                newItem = addItem(captionDTO, icon, command);
            }

            markAsDirty();

            return newItem;
        }

        /**
         * For the associated command.
         *
         * @return The associated command, or null if there is none
         */
        public Command getCommand() {
            return itsCommand;
        }

        /**
         * Gets the objects icon.
         *
         * @return The icon of the item, null if the item doesn't have an icon
         */
        public Resource getIcon() {
            return itsIcon;
        }

        /**
         * For the containing item. This will return null if the item is in the
         * top-level menu bar.
         *
         * @return The containing {@link com.vaadin.ui.CustomMenuBar.CustomMenuItem} , or
         *         null if there is none
         */
        public CustomMenuBar.CustomMenuItem getParent() {
            return itsParent;
        }

        /**
         * This will return the children of this item or null if there are none.
         *
         * @return List of children items, or null if there are none
         */
        public List<CustomMenuItem> getChildren() {
            return itsChildren;
        }

        /**
         * Gets the objects text
         *
         * @return The text
         */
        public java.lang.String getText() {
            return menuItemDTO.getCaption();
        }

        /**
         * Returns the number of children.
         *
         * @return The number of child items
         */
        public int getSize() {
            if (itsChildren != null) {
                return itsChildren.size();
            }
            return -1;
        }

        /**
         * Get the unique identifier for this item.
         *
         * @return The id of this item
         */
        public int getId() {
            return itsId;
        }

        /**
         * Set the command for this item. Set null to remove.
         *
         * @param command
         *            The MenuCommand of this item
         */
        public void setCommand(CustomMenuBar.Command command) {
            itsCommand = command;
        }

        /**
         * Sets the icon. Set null to remove.
         *
         * @param icon
         *            The icon for this item
         */
        public void setIcon(Resource icon) {
            itsIcon = icon;
            markAsDirty();
        }

        /**
         * Set the text of this object.
         *
         * @param text
         *            Text for this object
         */
        public void setText(java.lang.String text) {
            if (text != null) {
                menuItemDTO.setCaption(text);
            }
            markAsDirty();
        }

        public MenuItemDTO getMenuItem() {
            return menuItemDTO;
        }

        public void setMenuItem(MenuItemDTO menuItemDTO) {
            this.menuItemDTO = menuItemDTO;
        }

        /**
         * Remove the first occurrence of the item.
         *
         * @param item
         *            The item to be removed
         */
        public void removeChild(CustomMenuBar.CustomMenuItem item) {
            if (item != null && itsChildren != null) {
                itsChildren.remove(item);
                if (itsChildren.isEmpty()) {
                    itsChildren = null;
                }
                markAsDirty();
            }
        }

        /**
         * Empty the list of children items.
         */
        public void removeChildren() {
            if (itsChildren != null) {
                itsChildren.clear();
                itsChildren = null;
                markAsDirty();
            }
        }

        /**
         * Set the parent of this item. This is called by the addItem method.
         *
         * @param parent
         *            The parent item
         */
        protected void setParent(CustomMenuBar.CustomMenuItem parent) {
            itsParent = parent;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
            markAsDirty();
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setVisible(boolean visible) {
            this.visible = visible;
            markAsDirty();
        }

        public boolean isVisible() {
            return visible;
        }

        private void setSeparator(boolean isSeparator) {
            this.isSeparator = isSeparator;
            markAsDirty();
        }

        public boolean isSeparator() {
            return isSeparator;
        }

        public void setStyleName(String styleName) {
            this.styleName = styleName;
            markAsDirty();
        }

        public String getStyleName() {
            return styleName;
        }

        /**
         * Sets the items's description. See {@link #getDescription()} for more
         * information on what the description is. This method will trigger a
         * {@link RepaintRequestEvent}.
         *
         * @param description
         *            the new description string for the component.
         */
        public void setDescription(String description) {
            this.description = description;
            markAsDirty();
        }

        /**
         * <p>
         * Gets the items's description. The description can be used to briefly
         * describe the state of the item to the user. The description string
         * may contain certain XML tags:
         * </p>
         *
         * <p>
         * <table border=1>
         * <tr>
         * <td width=120><b>Tag</b></td>
         * <td width=120><b>Description</b></td>
         * <td width=120><b>Example</b></td>
         * </tr>
         * <tr>
         * <td>&lt;b></td>
         * <td>bold</td>
         * <td><b>bold text</b></td>
         * </tr>
         * <tr>
         * <td>&lt;i></td>
         * <td>italic</td>
         * <td><i>italic text</i></td>
         * </tr>
         * <tr>
         * <td>&lt;u></td>
         * <td>underlined</td>
         * <td><u>underlined text</u></td>
         * </tr>
         * <tr>
         * <td>&lt;br></td>
         * <td>linebreak</td>
         * <td>N/A</td>
         * </tr>
         * <tr>
         * <td>&lt;ul><br>
         * &lt;li>item1<br>
         * &lt;li>item1<br>
         * &lt;/ul></td>
         * <td>item list</td>
         * <td>
         * <ul>
         * <li>item1
         * <li>item2
         * </ul>
         * </td>
         * </tr>
         * </table>
         * </p>
         *
         * <p>
         * These tags may be nested.
         * </p>
         *
         * @return item's description <code>String</code>
         */
        public String getDescription() {
            return description;
        }

        /**
         * Gets the checkable state of the item - whether the item has checked
         * and unchecked states. If an item is checkable its checked state (as
         * returned by {@link #isChecked()}) is indicated in the UI.
         *
         * <p>
         * An item is not checkable by default.
         * </p>
         *
         * @return true if the item is checkable, false otherwise
         * @since 6.6.2
         */
        public boolean isCheckable() {
            return checkable;
        }

        /**
         * Sets the checkable state of the item. If an item is checkable its
         * checked state (as returned by {@link #isChecked()}) is indicated in
         * the UI.
         *
         * <p>
         * An item is not checkable by default.
         * </p>
         *
         * <p>
         * Items with sub items cannot be checkable.
         * </p>
         *
         * @param checkable
         *            true if the item should be checkable, false otherwise
         * @throws IllegalStateException
         *             If the item has children
         * @since 6.6.2
         */
        public void setCheckable(boolean checkable)
                throws IllegalStateException {
            if (hasChildren()) {
                throw new IllegalStateException(
                        "A menu item with children cannot be checkable");
            }
            this.checkable = checkable;
            markAsDirty();
        }
        /**
         * Gets the closable state of the item
         *
         * <p>
         * An item is not closable by default.
         * </p>
         *
         * @return true if the item is closable, false otherwise
         * @since 6.6.2
         */
        public boolean isClosable() {
            return closable;
        }

        /**
         * Sets the closable state of the item.
         *
         * <p>
         * An item is not closable by default.
         * </p>
         *
         * <p>
         * Items with sub items cannot be closable.
         * </p>
         *
         * @param closable
         *            true if the item should be closable, false otherwise
         * @throws IllegalStateException
         *             If the item has children
         * @since 6.6.2
         */
        public void setCloseable(boolean closable){
            this.closable = closable;
            markAsDirty();
        }
        public boolean isItemClickable() {
            return itemClickable;
        }
        public void setItemClickable(boolean itemClickable){
            this.itemClickable = itemClickable;
            markAsDirty();
        }
        public boolean isItemClickNotClosable() {
            return itemClickNotClosable;
        }
        public void setItemClickNotClosable(boolean itemClickNotClosable){
            this.itemClickNotClosable = itemClickNotClosable;
            markAsDirty();
        }
        public boolean isCheckAll() {
            return checkAll;
        }

        public void setCheckAll(boolean checkAll) {
            this.checkAll = checkAll;
            markAsDirty();
        }
        /*
         * This method is deprecated. Use isCheckAll() instead.
         */
        @Deprecated
        public boolean hasCheckAllProperty() {
            return isCheckAll();
        }
        /*
         * This method is deprecated. Use setCheckAll() instead.
         */
        @Deprecated
        public void setCheckAllProperty(boolean checkAllProperty){
            setCheckAll(checkAllProperty);
        }
        public boolean isItemClickSelectable() {
            return itemClickSelectable;
        }
        public void setItemClickSelectable(boolean itemClickSelectable){
            this.itemClickSelectable = itemClickSelectable;
            markAsDirty();
        }
        public boolean isItemHoverSelectable() {
            return itemHoverSelectable;
        }
        public void setItemHoverSelectable(boolean itemHoverSelectable){
            this.itemHoverSelectable = itemHoverSelectable;
            markAsDirty();
        }

        /**
         * Gets the checked state of the item (checked or unchecked). Only used
         * if the item is checkable (as indicated by {@link #isCheckable()}).
         * The checked state is indicated in the UI with the item, if the item
         * is checkable.
         *
         * <p>
         * An item is not checked by default.
         * </p>
         *
         * <p>
         * The CSS style corresponding to the checked state is "-checked".
         * </p>
         *
         * @return true if the item is checked, false otherwise
         * @since 6.6.2
         */
        public boolean isChecked() {
            return checked;
        }

        /**
         * Sets the checked state of the item. Only used if the item is
         * checkable (indicated by {@link #isCheckable()}). The checked state is
         * indicated in the UI with the item, if the item is checkable.
         *
         * <p>
         * An item is not checked by default.
         * </p>
         *
         * <p>
         * The CSS style corresponding to the checked state is "-checked".
         * </p>
         *
         * @return true if the item is checked, false otherwise
         * @since 6.6.2
         */
        public void setChecked(boolean checked) {
            this.checked = checked;
            markAsDirty();
        }
        public void settempChecked(boolean checked) {
            this.checked = checked;
        }

    }// class CustomMenuItem
    public static class ItemCloseClickEvent extends Component.Event {

        public static final Method CLOSE_CLICK_METHOD;
        private CustomMenuItem item=null;
        static {
            try {
                CLOSE_CLICK_METHOD = ItemCloseClickListener.class
                        .getDeclaredMethod("itemCloseClick",
                                new Class[]{ItemCloseClickEvent.class});
            } catch (final java.lang.NoSuchMethodException e) {
                // This should never happen
                e.printStackTrace();
                throw new java.lang.RuntimeException(e);
            }
        }

        public ItemCloseClickEvent(Component source, CustomMenuItem item) {
            super(source);
            this.item = item;
        }

        /**
         * Gets the minimize value
         *
         * @return
         */
        public CustomMenuItem getMenuItem() {
            return item;
        }
    }

    /**
     * Interface for the listener for double header column header mouse click
     * events. The doubleHeaderClick method is called when the user presses a
     * double header column cell.
     */
    public interface ItemCloseClickListener extends Serializable {

        /**
         * Called when a user clicks a double header column cell
         *
         * @param event The event which contains information about the double
         * header column and the mouse click event
         */
        public void itemCloseClick(ItemCloseClickEvent event);
    }

    /**
     * Adds a double header click listener which handles the click events when
     * the user clicks on a double header column header cell in the Table.
     * <p>
     * The listener will receive events which contain information about which
     * double header column was clicked and some details about the mouse event.
     * </p>
     *
     * @param listener The handler which should handle the double header click
     * events.
     */
    public void addItemCloseClickListener(ItemCloseClickListener listener) {
        addListener("ITEM_CLOSE_CLICK",
                ItemCloseClickEvent.class, listener,
                ItemCloseClickEvent.CLOSE_CLICK_METHOD);
    }

    /**
     * @param listener
     * @deprecated , replaced by
     * {@link #addDoubleHeaderClickListener(DoubleHeaderClickListener)}
     *
     */
    @Deprecated
    public void addListener(ItemCloseClickListener listener) {
        addItemCloseClickListener(listener);
    }

    /**
     * Removes a double header click listener
     *
     * @param listener The listener to remove.
     */
    public void removeItemCloseClickListener(ItemCloseClickListener listener) {
        removeListener("ITEM_CLOSE_CLICK",
                ItemCloseClickEvent.class, listener);
    }

    /**
     * @param listener
     * @deprecated , replaced by
     * {@link #removeDoubleHeaderClickListener(DoubleHeaderClickListener)}
     *
     */
    @Deprecated
    public void removeListener(ItemCloseClickListener listener) {
        removeItemCloseClickListener(listener);
    }
    /**
     * An interface used for listening to Window close events. Add the
     * CloseListener to a window and
     * {@link CloseListener#windowClose(CloseEvent)} will be called whenever the
     * user closes the window.
     *
     * <p>
     * Since Vaadin 6.5, removing a window using {@link #removeWindow(Window)}
     * fires the CloseListener.
     * </p>
     */
    public static class ItemClickEvent extends Component.Event {

        public static final Method ITEM_CLICK_METHOD;
        private boolean minimize = false;
        CustomMenuItem item=null;
        static {
            try {
                ITEM_CLICK_METHOD = ItemClickListener.class
                        .getDeclaredMethod("itemClick",
                                new Class[]{ItemClickEvent.class});
            } catch (final java.lang.NoSuchMethodException e) {
                // This should never happen
                e.printStackTrace();
                throw new java.lang.RuntimeException(e);
            }
        }

        public ItemClickEvent(Component source,  CustomMenuItem item) {
            super(source);
            this.item = item;
        }

        /**
         * Gets the menu item
         *
         * @return
         */
        public CustomMenuItem getMenuItem() {
            return item;
        }
    }

    /**
     * Interface for the listener for double header column header mouse click
     * events. The doubleHeaderClick method is called when the user presses a
     * double header column cell.
     */
    public interface ItemClickListener extends Serializable {

        /**
         * Called when a user clicks a double header column cell
         *
         * @param event The event which contains information about the double
         * header column and the mouse click event
         */
        public void itemClick(ItemClickEvent event);
    }

    /**
     * Adds a double header click listener which handles the click events when
     * the user clicks on a double header column header cell in the Table.
     * <p>
     * The listener will receive events which contain information about which
     * double header column was clicked and some details about the mouse event.
     * </p>
     *
     * @param listener The handler which should handle the double header click
     * events.
     */
    public void addItemClickListener(ItemClickListener listener) {
        addListener("ITEM_CLICK",
                ItemClickEvent.class, listener,
                ItemClickEvent.ITEM_CLICK_METHOD);
    }

    /**
     * @param listener
     * @deprecated , replaced by
     * {@link #addDoubleHeaderClickListener(DoubleHeaderClickListener)}
     *
     */
    @Deprecated
    public void addListener(ItemClickListener listener) {
        addItemClickListener(listener);
    }

    /**
     * Removes a double header click listener
     *
     * @param listener The listener to remove.
     */
    public void removeItemClickListener(ItemClickListener listener) {
        removeListener("ITEM_CLICK",
                ItemClickEvent.class, listener);
    }

    public boolean isScrollable() {
        return scrollable;
    }

    public void setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
        markAsDirty();
    }

    public int getPageLength() {
        return pageLength;
    }

    public void setPageLength(int pageLength) {
        this.pageLength = pageLength;
        markAsDirty();
    }

    /**
     * @param listener
     * @deprecated , replaced by
     * {@link #removeDoubleHeaderClickListener(DoubleHeaderClickListener)}
     *
     */
    @Deprecated
    public void removeListener(ItemClickListener listener) {
        removeItemClickListener(listener);
    }

    /**
     * An interface used for listening to Window close events. Add the
     * CloseListener to a window and
     * {@link CloseListener#windowClose(CloseEvent)} will be called whenever the
     * user closes the window.
     *
     * <p>
     * Since Vaadin 6.5, removing a window using {@link #removeWindow(Window)}
     * fires the CloseListener.
     * </p>
     */
    public static class SubMenuCloseEvent extends Component.Event {

        public static final Method SUB_MENU_CLOSE_METHOD;

        static {
            try {
                SUB_MENU_CLOSE_METHOD = SubMenuCloseListener.class
                        .getDeclaredMethod("subMenuClose",
                                new Class[]{SubMenuCloseEvent.class});
            } catch (final java.lang.NoSuchMethodException e) {
                // This should never happen
                e.printStackTrace();
                throw new java.lang.RuntimeException(e);
            }
        }

        public SubMenuCloseEvent(Component source) {
            super(source);
        }
    }

    /**
     * Interface for the listener for double header column header mouse click
     * events. The doubleHeaderClick method is called when the user presses a
     * double header column cell.
     */
    public interface SubMenuCloseListener extends Serializable {

        /**
         * Called when a user clicks a double header column cell
         *
         * @param event The event which contains information about the double
         * header column and the mouse click event
         */
        public void subMenuClose(SubMenuCloseEvent event);
    }

    /**
     * Adds a double header click listener which handles the click events when
     * the user clicks on a double header column header cell in the Table.
     * <p>
     * The listener will receive events which contain information about which
     * double header column was clicked and some details about the mouse event.
     * </p>
     *
     * @param listener The handler which should handle the double header click
     * events.
     */
    public void addSubMenuCloseListener(SubMenuCloseListener listener) {
        addListener("SUB_MENU_CLOSE",
                SubMenuCloseEvent.class, listener,
                SubMenuCloseEvent.SUB_MENU_CLOSE_METHOD);
    }

    /**
     * Removes a double header click listener
     *
     * @param listener The listener to remove.
     */
    public void removeSubMenuCloseListener(SubMenuCloseListener listener) {
        removeListener("SUB_MENU_CLOSE",
                SubMenuCloseEvent.class, listener);
    }
}
