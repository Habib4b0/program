/*
 * Copyright 2015 Abhiram.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.asi.ui.customwindow;

import java.util.List;
import java.util.ListIterator;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.custommenubar.MenuItemDTO;

/**
 *
 * @author Abhiram
 */
abstract public class MinimizeTray extends CustomWindow{

    private CustomMenuBar barmenu = new CustomMenuBar();
    private CustomMenuBar.CustomMenuItem windows;
    private boolean posX;
    private boolean posY;
    public MinimizeTray() {
        super();
        setClosable(false);
        setHeaderVisible(false, true);
        setResizable(false);
        barmenu = new CustomMenuBar();
        windows = barmenu.addItem(new MenuItemDTO("Windows"), null);
        windows.setCloseable(true);
        setContent(barmenu);
        barmenu.addItemClickListener(new CustomMenuBar.ItemClickListener() {

            @Override
            public void itemClick(CustomMenuBar.ItemClickEvent event) {
                Object win = event.getMenuItem().getMenuItem().getWindow();
                if (win != null) {
                    if (win instanceof CustomWindow) {
                        ((CustomWindow) win).setMinimize(!((CustomWindow) win).getMinimize());
                    }
                }
            }
        });
        barmenu.addItemCloseClickListener(new CustomMenuBar.ItemCloseClickListener() {

            @Override
            public void itemCloseClick(CustomMenuBar.ItemCloseClickEvent event) {
                if (windows.equals(event.getMenuItem())) {
                    windowsCloseClick();   
                }
                Object win = event.getMenuItem().getMenuItem().getWindow();
                if (win != null) {
                    if (win instanceof CustomWindow) {
                        ((CustomWindow) win).close();
                    }
                }
            }
        });
    }

    public List<CustomMenuBar.CustomMenuItem> getWindowItems() {
        return windows.getChildren();
    }

    public boolean hasWindowItems() {
        return windows.hasChildren();
    }

    public CustomMenuBar.CustomMenuItem addWindowItem(CustomWindow window) {
        CustomMenuBar.CustomMenuItem item = getMenuItemFromWindow(window);
        if (item == null) {
            String caption = window.getCaption();
            if (caption.equals("")) {
                caption = "...";
            }
            item = windows.addItem(new MenuItemDTO(window, caption), null);
            item.setItemClickable(true);
            item.setCloseable(true);
        }
        return item;
    }

    public CustomMenuBar.CustomMenuItem removeWindowItem(CustomMenuBar.CustomMenuItem item) {
        if (item != null) {
            windows.removeChild(item);
        }
        return item;
    }

    public CustomMenuBar.CustomMenuItem removeWindowItem(CustomWindow window) {
        return removeWindowItem(getMenuItemFromWindow(window));
    }

    public CustomMenuBar.CustomMenuItem getMenuItemFromWindow(CustomWindow window) {
        if (windows.hasChildren()) {
            ListIterator<CustomMenuBar.CustomMenuItem> itr = windows.getChildren().listIterator();
            while (itr.hasNext()) {
                CustomMenuBar.CustomMenuItem item = itr.next();
                if (item.getMenuItem().getWindow().equals(window)) {
                    return item;
                }
            }
        }
        return null;
    }
    
    /** Minimize Tray Close click operation.
     * 	Inside “Success” of customized Confirm Dialog in this method “windowsCloseCunfirmed()" 
     *  must be called to close all Custom Window Added to Minimize Tray;
     */
    abstract protected void windowsCloseClick();
    
    /** Minimize Tray Close Confirmed.
     * 	Inside “Success” of customized Confirm Dialog of "windowsCloseClick()" method this 
     *  must be called to close all Custom Window Added to Minimize Tray;
     */
    public void windowsCloseConfirmed(){
         while (hasWindowItems()) {
                                CustomMenuBar.CustomMenuItem item = getWindowItems().get(0);
                                Object win = item.getMenuItem().getWindow();
                                if (win != null) {
                                    if (win instanceof CustomWindow) {
                                        ((CustomWindow) win).close();
                                    }
                                }
                            }
                            close();
    }

    @Override
    public void setPositionX(int positionX) {
        posX=true;
        super.setPositionX(positionX); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPositionY(int positionY) {
        posY=true;
        super.setPositionY(positionY); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isPosX() {
        return posX;
    }

    public boolean isPosY() {
        return posY;
    }
    
}
