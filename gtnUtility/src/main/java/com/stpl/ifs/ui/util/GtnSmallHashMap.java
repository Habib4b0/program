package com.stpl.ifs.ui.util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.stpl.ifs.ui.util.dto.Pairs;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 * Used to have the customized list like key and value pair It will have the
 * same behavior of ArrayList
 *
 * @author Sooriya.Lakshmanan
 */
public class GtnSmallHashMap {

    private final List<Pairs> listPair;

    public GtnSmallHashMap() {
        this.listPair = new ArrayList<>();
    }

    /**
     * To add the key and value in the list
     *
     * @param key
     * @param value
     */
    public void put(Object key, Object value) {

        Pairs pair = iterator(key);
       
        
        if (pair == null) {
            listPair.add(new Pairs(key, value));
        } else {
            pair.setValue(value);
        }
        
        
        
    }
    
    /**
     * To add all the key and value in the list
     *
     * @param hashMap 
     */
    public void putAll(GtnSmallHashMap hashMap) {
        for (int i = 0; i < hashMap.size(); i++) {
            Pairs dto = hashMap.getIndex(i);
            Pairs pair = iterator(dto.getKey());
            if (pair == null) {
                listPair.add(pair);
            } else {
                pair.setValue(pair.getValue());
            }
        }
    }

    /**
     * To remove the specified key
     *
     * @param key
     */
    public void remove(Object key) {
        listPair.remove(iterator(key));
    }
    
    /**
     * To remove the specified index
     *
     * @param index
     */
    public Object removeWithIndex(int index) {
       return listPair.remove(index).getValue();
    }

    /**
     * To get the Size of the list
     *
     * @return
     */
    public int size() {
        return listPair.size();
    }

    /**
     * To check the key is present in the GtnSmallHashMap or not
     *
     * @param key
     * @return
     */
    public boolean containsKey(Object key) {
        return iterator(key)!=null;
    }
    
    /**
     * To check the value is present in the GtnSmallHashMap or not
     *
     * @param value
     * @return
     */
    public boolean containsValue(Object value) {
        return iterateOverValue(value)!=null;
    }

    /**
     * To get the value using the key
     *
     * @param key
     * @return
     */
    public Object get(Object key) {
        Pairs dto = iterator(key);
        if(dto!=null){
        }
        return  dto == null ? null : iterator(key).getValue();
    }
    
    /**
     * To get the value using the key as integer
     *
     * @param key
     * @return
     */
    public Integer getInt(Object key) {
        Pairs dto = iterator(key);
        return dto == null ? 0 : (int) dto.getValue();
    }
    
    /**
     * To get the value using the key as integer
     *
     * @param key
     * @return
     */
    public String getString(Object key) {
        Pairs dto = iterator(key);
        return dto == null ? StringUtils.EMPTY : (String) iterator(key).getValue();
    }

    /**
     * To clear the list
     */
    public void clear() {
        listPair.clear();
    }

    /**
     * Returns <tt>true</tt> if this map contains no key-value mappings.
     *
     * @return <tt>true</tt> if this map contains no key-value mappings
     */
    public boolean isEmpty() {
        return listPair.isEmpty();
    }

    /**
     * Used to get the object using the index
     *
     * @param index
     * @return Object[]
     */
    public Pairs getIndex(final int index) {
        return listPair.get(index);
    }
    
    /**
     * To iterate over the list with key
     *
     * @param key
     * @return object[] of the particular key
     */
    private Pairs iterator(Object key) {
        for (int i = 0; i < listPair.size(); i++) {
            Pairs dto =  listPair.get(i);
            if (key.equals(dto.getKey())) {
                return dto;
            }
        }
        return null;
    }
    
    
    /**
     * To iterate over the list with value
     *
     * @param key
     * @return object[] of the particular key
     */
    private Pairs iterateOverValue(Object value) {
        for (int i = 0; i < listPair.size(); i++) {
            Pairs dto =  listPair.get(i);
            if (value.equals(dto.getValue())) {
                return dto;
            }
        }
        return null;
    }

}
