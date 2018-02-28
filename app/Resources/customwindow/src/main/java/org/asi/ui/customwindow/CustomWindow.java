package org.asi.ui.customwindow;

import com.vaadin.server.PaintException;
import com.vaadin.server.PaintTarget;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import org.vaadin.dialogs.ConfirmDialog;

public class CustomWindow extends Window {

    private boolean minimize = false;
    private int minimizeWidth = 0;
    private int minimizeLeft = 0;
    private boolean minimizeLeftSet = false;
    private int minimizeTop = 0;
    private boolean minimizeTopSet = false;
    private boolean headerVisible = true;
    private boolean closeBtnVisible = true;
    private boolean minimizeBtnVisible = true;
    private boolean minimizeToTray = false;
    final CustomWindow win = this;
    private MinimizeTray myTray;
    private CloseListener closelistener = new CloseListener() {

        @Override
        public void windowClose(CloseEvent e) {
            if (myTray != null) {
                myTray.removeWindowItem(win);
            }
        }
    };

    /**
     * Creates a new, empty window
     */
    public CustomWindow() {
        this("", null);
    }

    /**
     * Creates a new, empty window with a given title.
     *
     * @param caption the title of the window.
     */
    public CustomWindow(String caption) {
        this(caption, null);
    }

    /**
     * Creates a new, empty window with the given content and title.
     *
     * @param caption the title of the window.
     * @param content the contents of the window
     */
    public CustomWindow(String caption, Component content) {
        super(caption, content);
        setSizeUndefined();
    }
    /* ********************************************************************* */

    /*
     * (non-Javadoc)
     * 
     * @see com.vaadin.ui.Panel#paintContent(com.vaadin.server.PaintTarget)
     */
    @Override
    public synchronized void paintContent(PaintTarget target)
            throws PaintException {
        target.addAttribute("minimizetray", getMinimizeTray() != null);
        target.addAttribute("minimize", getMinimizeTray() != null ? false : getMinimize());
        target.addAttribute("minimizeleft", getMinimizeLeftPos());
        target.addAttribute("minimizetop", getMinimizeTopPos());
        target.addAttribute("minimizewidth", getMinimizeWidth());
        target.addAttribute("headervisible", isHeaderVisible());
        target.addAttribute("closebtnvisible", isCloseBtnVisible());
        target.addAttribute("minimizeBtnVisible", isMinimizeBtnVisible());
        // Contents of the window panel is painted
        super.paintContent(target);
    }
    /*
     * (non-Javadoc)
     * 
     * @see com.vaadin.ui.Panel#changeVariables(java.lang.Object, java.util.Map)
     */

    @Override
    public void changeVariables(Object source, Map<String, Object> variables) {
        super.changeVariables(source, variables);
        if (variables.containsKey("minimizeClickEvent")) {
            final Boolean minimized = (Boolean) variables.get("minimizeClickEvent");
            minimize = minimized;
            if (getMinimizeTray() != null) {
                setVisible(false);
            }
            fireEvent(new MinimizeClickEvent(this, getMinimize()));
        }

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
    public static class MinimizeClickEvent extends Component.Event {

        public static final Method MINIMIZE_CLICK_METHOD;
        private boolean minimize = false;

        static {
            try {
                MINIMIZE_CLICK_METHOD = MinimizeClickListener.class
                        .getDeclaredMethod("minimizeClick",
                                new Class[]{MinimizeClickEvent.class});
            } catch (final java.lang.NoSuchMethodException e) {
                // This should never happen
                e.printStackTrace();
                throw new java.lang.RuntimeException(e);
            }
        }

        public MinimizeClickEvent(Component source, boolean minimize) {
            super(source);
            this.minimize = minimize;
        }

        /**
         * Gets the minimize value
         *
         * @return
         */
        public boolean getMinimizeValue() {
            return minimize;
        }
    }

    /**
     * Interface for the listener for double header column header mouse click
     * events. The doubleHeaderClick method is called when the user presses a
     * double header column cell.
     */
    public interface MinimizeClickListener extends Serializable {

        /**
         * Called when a user clicks a double header column cell
         *
         * @param event The event which contains information about the double
         * header column and the mouse click event
         */
        public void minimizeClick(MinimizeClickEvent event);
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
    public void addMinimizeClickListener(MinimizeClickListener listener) {
        addListener("MINIMIZE_CLICK",
                MinimizeClickEvent.class, listener,
                MinimizeClickEvent.MINIMIZE_CLICK_METHOD);
    }

    /**
     * @param listener
     * @deprecated , replaced by
     * {@link #addDoubleHeaderClickListener(DoubleHeaderClickListener)}
     *
     */
    @Deprecated
    public void addListener(MinimizeClickListener listener) {
        addMinimizeClickListener(listener);
    }

    /**
     * Removes a double header click listener
     *
     * @param listener The listener to remove.
     */
    public void removeMinimizeClickListener(MinimizeClickListener listener) {
        removeListener("MINIMIZE_CLICK",
                MinimizeClickEvent.class, listener);
    }

    /**
     * @param listener
     * @deprecated , replaced by
     * {@link #removeDoubleHeaderClickListener(DoubleHeaderClickListener)}
     *
     */
    @Deprecated
    public void removeListener(MinimizeClickListener listener) {
        removeMinimizeClickListener(listener);
    }

    public void setMinimize(boolean minimize) {
        this.minimize = minimize;
        if (getMinimizeTray() != null) {
            setVisible(!getMinimize());
        }
    }

    public boolean getMinimize() {
        return minimize;
    }

//    public void setMinimizeToTray(boolean minimizeToTray) {
//
//        if (minimizeToTray) {
//            setVisible(!getMinimize());
//            getMinimizeTray().addWindowItem(this);
//            
//           addCloseListener(closelistener);
//        } else if (isMinimizeToTray()) {
//            getMinimizeTray().removeWindowItem(this);
//            removeCloseListener(closelistener);
//        }
//        this.minimizeToTray = minimizeToTray;
//    }
    public void setMinimizeToTray(MinimizeTray minimizeTray) {
        myTray = getAvailableMinimizeTray();
        if (myTray == null) {
            myTray = createMinimizeTray(minimizeTray);
        }
        setMinimizeToTray();
    }

    public void setMinimizeToTray() {
        myTray = getAvailableMinimizeTray();
        if (myTray == null) {
            myTray = createMinimizeTray(null);
        }
        if (myTray != null) {
            setVisible(!getMinimize());
            myTray.addWindowItem(this);
            addCloseListener(closelistener);
        }
    }

    public void removeFromMinimizeTray() {
        if (myTray != null) {
            myTray.removeWindowItem(this);
            removeCloseListener(closelistener);
        }
    }

    public void setMinimizeWidth(int minimizeWidth) {
        if (minimizeWidth < 86) {
            minimizeWidth = 86;
        }
        this.minimizeWidth = minimizeWidth;
    }

    public int getMinimizeWidth() {
        if (minimizeWidth < 86) {
            minimizeWidth = 86;
        }
        if (minimizeWidth < 150 && this.getCaption().length() > 0) {
            minimizeWidth = 150;
        }
        return minimizeWidth;
    }

    public void setMinimizeLeftPos(int minimizeLeft) {
        minimizeLeftSet = true;
        this.minimizeLeft = minimizeLeft;
    }

    public int getMinimizeLeftPos() {
        UI ui = this.getUI();
        if (ui != null && !minimizeLeftSet) {
            minimizeLeftSet = true;
            minimizeLeft = 5;
            Iterator<Window> a = UI.getCurrent().getWindows().iterator();
            while (a.hasNext()) {
                Window next = a.next();
                if (next instanceof CustomWindow) {
                    if (!this.equals((CustomWindow) next)) {
                        minimizeLeft = minimizeLeft + ((CustomWindow) next).getMinimizeWidth() + 15;
                    }
                }
            }
        }
        return minimizeLeft;
    }

    public void setMinimizeTopPos(int minimizeTop) {
        minimizeTopSet = true;
        this.minimizeTop = minimizeTop;
    }

    public int getMinimizeTopPos() {
        if (!minimizeTopSet) {
            UI ui = this.getUI();
            if (ui != null) {
                minimizeTopSet = true;
                minimizeTop = ui.getPage().getBrowserWindowHeight() - 50;
            }
        }
        return minimizeTop;
    }

    public void setHeaderVisible(boolean headerVisible, boolean closeBtnVisible) {
        this.headerVisible = headerVisible;
        this.closeBtnVisible = closeBtnVisible;
    }

    public boolean isHeaderVisible() {
        return headerVisible;
    }

    public boolean isCloseBtnVisible() {
        return closeBtnVisible;
    }

    public MinimizeTray getMinimizeTray() {
        return myTray;
    }

    private MinimizeTray createMinimizeTray(MinimizeTray minimizeTray) {
        boolean nulTru = false;
        if (minimizeTray == null) {
            nulTru = true;
            minimizeTray = new MinimizeTray(){
                @Override
                protected void windowsCloseClick(){
                 ConfirmDialog.show(getUI(), "Please Confirm:", "You are going to close all the Window. Are you sure?",
                "OK", "Cancel", new ConfirmDialog.Listener() {

                    @Override
                    public void onClose(ConfirmDialog dialog) {
                        if (dialog.isConfirmed()) {
                            windowsCloseConfirmed();
                        }
                    }
                });
            }
            };
        }
        UI.getCurrent().addWindow(minimizeTray);
        if (nulTru) {
            minimizeTray.setPositionX(5);
            minimizeTray.setPositionY((int) (UI.getCurrent().getPage().getBrowserWindowHeight() - 60));
        } else {
            if (!minimizeTray.isPosX()) {
                minimizeTray.setPositionX(5);
            }
            if (!minimizeTray.isPosY()) {
                minimizeTray.setPositionY((int) (UI.getCurrent().getPage().getBrowserWindowHeight() - 60));
            }
        }
        return minimizeTray;
    }

    private MinimizeTray getAvailableMinimizeTray() {
        Iterator<Window> a = UI.getCurrent().getWindows().iterator();
        while (a.hasNext()) {
            Window next = a.next();
            if (next instanceof MinimizeTray) {
                return (MinimizeTray) next;
            }
        }
        return null;
    }

    public boolean isMinimizeBtnVisible() {
        return minimizeBtnVisible;
    }

    public void setMinimizeBtnVisible(boolean minimizeBtnVisible) {
        this.minimizeBtnVisible = minimizeBtnVisible;
    }

}
