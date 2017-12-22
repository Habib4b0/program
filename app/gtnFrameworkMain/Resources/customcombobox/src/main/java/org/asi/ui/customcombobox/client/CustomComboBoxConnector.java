package org.asi.ui.customcombobox.client;

import com.google.gwt.core.client.Scheduler;
import com.vaadin.client.ApplicationConnection;
import com.vaadin.client.Paintable;
import com.vaadin.client.UIDL;
import static com.vaadin.client.ui.AbstractComponentConnector.isRealUpdate;
import com.vaadin.v7.client.ui.AbstractFieldConnector;
import com.vaadin.client.ui.SimpleManagedLayout;
import org.asi.ui.customcombobox.CustomComboBox;

import com.vaadin.shared.ui.Connect;
import com.vaadin.v7.shared.ui.combobox.ComboBoxConstants;
import com.vaadin.v7.shared.ui.combobox.ComboBoxState;
import com.vaadin.v7.shared.ui.combobox.FilteringMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Connector binds client-side widget class to server-side component class
// Connector lives in the client and the @Connect annotation specifies the
// corresponding server-side component
@Connect(CustomComboBox.class)
public class CustomComboBoxConnector extends AbstractFieldConnector implements
        Paintable, SimpleManagedLayout {

    // oldSuggestionTextMatchTheOldSelection is used to detect when it's safe to
    // update textbox text by a changed item caption.
    private boolean oldSuggestionTextMatchTheOldSelection;

    /*
     * (non-Javadoc)
     * 
     * @see com.vaadin.client.Paintable#updateFromUIDL(com.vaadin.client.UIDL,
     * com.vaadin.client.ApplicationConnection)
     */
    @Override
    public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
        // Save details
        getWidget().client = client;
        getWidget().paintableId = uidl.getId();

        getWidget().readonly = isReadOnly();
        getWidget().updateReadOnly();

        if (!isRealUpdate(uidl)) {
            return;
        }

        // Inverse logic here to make the default case (text input enabled)
        // work without additional UIDL messages
        boolean noTextInput = uidl
                .hasAttribute(ComboBoxConstants.ATTR_NO_TEXT_INPUT)
                && uidl.getBooleanAttribute(ComboBoxConstants.ATTR_NO_TEXT_INPUT);
        getWidget().setTextInputEnabled(!noTextInput);

        // not a FocusWidget -> needs own tabindex handling
        getWidget().tb.setTabIndex(getState().tabIndex);

        if (uidl.hasAttribute("filteringmode")) {
            getWidget().filteringmode = FilteringMode.valueOf(uidl
                    .getStringAttribute("filteringmode"));
        }

        getWidget().immediate = getState().immediate;

        getWidget().nullSelectionAllowed = uidl.hasAttribute("nullselect");

        getWidget().nullSelectItem = uidl.hasAttribute("nullselectitem")
                && uidl.getBooleanAttribute("nullselectitem");

        getWidget().currentPage = uidl.getIntVariable("page");

        if (uidl.hasAttribute("pagelength")) {
            getWidget().pageLength = uidl.getIntAttribute("pagelength");
        }

        if (uidl.hasAttribute(ComboBoxConstants.ATTR_INPUTPROMPT)) {
            // input prompt changed from server
            getWidget().inputPrompt = uidl
                    .getStringAttribute(ComboBoxConstants.ATTR_INPUTPROMPT);
        } else {
            getWidget().inputPrompt = "";
        }

        getWidget().suggestionPopup.updateStyleNames(uidl, getState());

        getWidget().allowNewItem = uidl.hasAttribute("allownewitem");
        getWidget().addBlurValue=uidl.hasAttribute("addblurvalue");
        getWidget().lastNewItemString = null;

        final UIDL options = uidl.getChildUIDL(0);
        if (uidl.hasAttribute("totalMatches")) {
            getWidget().totalMatches = uidl.getIntAttribute("totalMatches");
        } else {
            getWidget().totalMatches = 0;
        }

        List<VCustomFilterSelect.FilterSelectSuggestion> newSuggestions = new ArrayList<VCustomFilterSelect.FilterSelectSuggestion>();

        for (final Iterator<?> i = options.getChildIterator(); i.hasNext();) {
            final UIDL optionUidl = (UIDL) i.next();
            final VCustomFilterSelect.FilterSelectSuggestion suggestion = getWidget().new FilterSelectSuggestion(
                    optionUidl);
            newSuggestions.add(suggestion);
        }

        // only close the popup if the suggestions list has actually changed
        boolean suggestionsChanged = !getWidget().initDone
                || !newSuggestions.equals(getWidget().currentSuggestions);

        // An ItemSetChangeEvent on server side clears the current suggestion
        // popup. Popup needs to be repopulated with suggestions from UIDL.
        boolean popupOpenAndCleared = false;

        oldSuggestionTextMatchTheOldSelection = false;

        if (suggestionsChanged) {
            oldSuggestionTextMatchTheOldSelection = isWidgetsCurrentSelectionTextInTextBox();
            getWidget().currentSuggestions.clear();

            if (!getWidget().waitingForFilteringResponse) {
                /*
                 * Clear the current suggestions as the server response always
                 * includes the new ones. Exception is when filtering, then we
                 * need to retain the value if the user does not select any of
                 * the options matching the filter.
                 */
                getWidget().currentSuggestion = null;
                /*
                 * Also ensure no old items in menu. Unless cleared the old
                 * values may cause odd effects on blur events. Suggestions in
                 * menu might not necessary exist in select at all anymore.
                 */
                getWidget().suggestionPopup.menu.clearItems();
                popupOpenAndCleared = getWidget().suggestionPopup.isAttached();

            }

            for (VCustomFilterSelect.FilterSelectSuggestion suggestion : newSuggestions) {
                getWidget().currentSuggestions.add(suggestion);
            }
        }

        // handle selection (null or a single value)
        if (uidl.hasVariable("selected")

        // In case we're switching page no need to update the selection as the
        // selection process didn't finish.
        // && getWidget().selectPopupItemWhenResponseIsReceived ==
        // VCustomFilterSelect.Select.NONE
        //
        ) {

            String[] selectedKeys = uidl.getStringArrayVariable("selected");
            if (selectedKeys.length > 0) {
                performSelection(selectedKeys[0]);
            } else {
                resetSelection();
            }
        }

        if ((getWidget().waitingForFilteringResponse && getWidget().lastFilter
                .toLowerCase().equals(uidl.getStringVariable("filter")))
                || popupOpenAndCleared) {

            getWidget().suggestionPopup.showSuggestions(
                    getWidget().currentSuggestions, getWidget().currentPage,
                    getWidget().totalMatches);

            getWidget().waitingForFilteringResponse = false;

            if (!getWidget().popupOpenerClicked
                    && getWidget().selectPopupItemWhenResponseIsReceived != VCustomFilterSelect.Select.NONE) {

                // we're paging w/ arrows
                Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
                    @Override
                    public void execute() {
                        navigateItemAfterPageChange();
                    }
                });
            }

            if (getWidget().updateSelectionWhenReponseIsReceived) {
                getWidget().suggestionPopup.menu
                        .doPostFilterSelectedItemAction();
            }
        }

        // Calculate minimum textarea width
        getWidget().updateSuggestionPopupMinWidth();

        getWidget().popupOpenerClicked = false;

        /*
         * if this is our first time we need to recalculate the root width.
         */
        if (!getWidget().initDone) {

            getWidget().updateRootWidth();
        }

        // Focus dependent style names are lost during the update, so we add
        // them here back again
        if (getWidget().focused) {
            getWidget().addStyleDependentName("focus");
        }

        getWidget().initDone = true;
    }

    /*
     * This method navigates to the proper item in the combobox page. This
     * should be executed after setSuggestions() method which is called from
     * vFilterSelect.showSuggestions(). ShowSuggestions() method builds the page
     * content. As far as setSuggestions() method is called as deferred,
     * navigateItemAfterPageChange method should be also be called as deferred.
     * #11333
     */
    private void navigateItemAfterPageChange() {
        if (getWidget().selectPopupItemWhenResponseIsReceived == VCustomFilterSelect.Select.LAST) {
            getWidget().suggestionPopup.selectLastItem();
        } else {
            getWidget().suggestionPopup.selectFirstItem();
        }

        // If you're in between 2 requests both changing the page back and
        // forth, you don't want this here, instead you need it before any
        // other request.
        // getWidget().selectPopupItemWhenResponseIsReceived =
        // VCustomFilterSelect.Select.NONE; // reset
    }

    private void performSelection(String selectedKey) {
        // some item selected
        for (VCustomFilterSelect.FilterSelectSuggestion suggestion : getWidget().currentSuggestions) {
            String suggestionKey = suggestion.getOptionKey();
            if (!suggestionKey.equals(selectedKey)) {
                continue;
            }
            if (!getWidget().waitingForFilteringResponse
                    || getWidget().popupOpenerClicked) {
                if (!suggestionKey.equals(getWidget().selectedOptionKey)
                        || suggestion.getReplacementString().equals(
                                getWidget().tb.getText())
                        || oldSuggestionTextMatchTheOldSelection) {
                    // Update text field if we've got a new
                    // selection
                    // Also update if we've got the same text to
                    // retain old text selection behavior
                    // OR if selected item caption is changed.
                    getWidget().setPromptingOff(
                            suggestion.getReplacementString());
                    getWidget().selectedOptionKey = suggestionKey;
                }
            }
            getWidget().currentSuggestion = suggestion;
            getWidget().setSelectedItemIcon(suggestion.getIconUri());
            // only a single item can be selected
            break;
        }
    }

    private boolean isWidgetsCurrentSelectionTextInTextBox() {
        return getWidget().currentSuggestion != null
                && getWidget().currentSuggestion.getReplacementString().equals(
                        getWidget().tb.getText());
    }

    private void resetSelection() {
        if (!getWidget().waitingForFilteringResponse
                || getWidget().popupOpenerClicked) {
            // select nulled
            if (!getWidget().focused) {
                /*
                 * client.updateComponent overwrites all styles so we must
                 * ALWAYS set the prompting style at this point, even though we
                 * think it has been set already...
                 */
                getWidget().setPromptingOff("");
                if (getWidget().enabled && !getWidget().readonly) {
                    getWidget().setPromptingOn();
                }
            } else {
                // we have focus in field, prompting can't be set on, instead
                // just clear the input if the value has changed from something
                // else to null
                if (getWidget().selectedOptionKey != null
                        || (getWidget().allowNewItem && !getWidget().tb
                                .getValue().isEmpty())) {
                    getWidget().tb.setValue("");
                }
            }
            getWidget().setSelectedItemIcon(null);
            getWidget().selectedOptionKey = null;
        }
    }

    @Override
    public VCustomFilterSelect getWidget() {
        return (VCustomFilterSelect) super.getWidget();
    }

    @Override
    public ComboBoxState getState() {
        return (ComboBoxState) super.getState();
    }

    @Override
    public void layout() {
        VCustomFilterSelect widget = getWidget();
        if (widget.initDone) {
            widget.updateRootWidth();
        }
    }

    @Override
    public void setWidgetEnabled(boolean widgetEnabled) {
        super.setWidgetEnabled(widgetEnabled);
        getWidget().enabled = widgetEnabled;
        getWidget().tb.setEnabled(widgetEnabled);
    }

}
