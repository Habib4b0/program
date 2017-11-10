/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.asi.ui.extfilteringtable.paged.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * The Class HierarchyString.
 *
 * @author Abhiram
 */
public class HierarchyString  implements Comparator<HierarchyString>{
    
    /** The string. */
    String string="";
    
    /**
     * Instantiates a new hierarchy string.
     */
    public HierarchyString(){
        
    }
    
    /**
     * Instantiates a new hierarchy string.
     *
     * @param string the string
     */
    public HierarchyString(String string){
        this.string=string;
    }
    
    /**
     * Gets the string.
     *
     * @return the string
     */
    public String getString() {
        return string;
    }

    /**
     * Sets the string.
     *
     * @param string the new string
     */
    public void setString(String string) {
        this.string = string;
    }
    
    /**
     * Compare.
     *
     * @param obj1 the obj1
     * @param obj2 the obj2
     * @return the int
     */
    @Override
    public int compare(HierarchyString obj1, HierarchyString obj2) {
        int value=0;
        try {
            if (obj1 != null && obj2 != null){ 
                String hierarchy1=obj1.getString();
                String hierarchy2=obj2.getString();
                if(hierarchy1 != null && hierarchy2 != null){
                    if(hierarchy1.isEmpty()&&hierarchy2.isEmpty()){
                        value=0;
                    }else if(hierarchy1.isEmpty()){
                        value=-1;
                    }else if(hierarchy2.isEmpty()){
                        value=1;
                    }else{
                        String[] hierarchyNoArray1 = hierarchy1.split("\\.");
                        String[] hierarchyNoArray2 = hierarchy2.split("\\.");
                        int i=0;
                        boolean hierarchy2Out=false;
                        boolean valueChange=false;
                        while(i<hierarchyNoArray1.length){
                            if(hierarchyNoArray2.length==i){
                                hierarchy2Out=true;                            
                                break;
                            }                        
                            Integer no1 = Integer.valueOf(hierarchyNoArray1[i]);
                            Integer no2 = Integer.valueOf(hierarchyNoArray2[i]);
                            value = no1.compareTo(no2);
                            if(value!=0){
                                valueChange=true;                            
                                break;
                            }                            
                            i++;                            
                        }
                        if(hierarchy2Out){
                            value=1;
                        }else if(!valueChange&&hierarchyNoArray1.length==i&&hierarchyNoArray2.length==i){
                            value=0;
                        }else if(!valueChange&&hierarchyNoArray1.length==i){
                            value=-1;
                        }
                    }
                }
            }
        }catch (Exception e) {
            
        }
        
        return value;
    }
    
    /**
     * Gets the hierarchy string list.
     *
     * @param strList the str list
     * @return the hierarchy string list
     */
    public static List<HierarchyString> getHierarchyStringList(List<String> strList){
        List<HierarchyString> list=new ArrayList<HierarchyString>();
        for (String string : strList) {
            list.add(new HierarchyString(string));
        }
        return list;
    }
    
    /**
     * Gets the hierarchy string list.
     *
     * @param strList the str list
     * @param sorted the sorted
     * @return the hierarchy string list
     */
    public static List<HierarchyString> getHierarchyStringList(List<String> strList,boolean sorted){
        List<HierarchyString> list=getHierarchyStringList(strList);
        if(sorted){
            Collections.sort(list, new HierarchyString());
        }
        return list;
    }
    
}
