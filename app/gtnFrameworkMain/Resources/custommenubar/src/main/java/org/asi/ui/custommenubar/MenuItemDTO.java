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

package org.asi.ui.custommenubar;

/**
 *
 * @author Abhiram
 */
public class MenuItemDTO {
    int id=0;
    String caption="";
    Object window=null;
    public MenuItemDTO(){
        
    }
    public MenuItemDTO(String caption){
        this.caption = caption;
    }
    public MenuItemDTO(int id,String caption){
        this.id = id;
        this.caption = caption;
    }
    public int getId() {
        return id;
    }
    public MenuItemDTO(Object window,String caption){
        this.window = window;
        this.caption = caption;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Object getWindow() {
        return window;
    }

    public void setWindow(Object window,String caption) {
        this.window = window;
        this.caption = caption;
    }
    
}
