package com.stpl.gtn.gtn2o.ws.components.smallhashmap;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author STPL
 */
public class GtnSmallHashMap {

	private final List<GtnPairsBean> listPair;
        
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

		GtnPairsBean pair = iterator(key);

		if (pair == null) {
			listPair.add(new GtnPairsBean(key, value));
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
			GtnPairsBean dto = hashMap.getIndex(i);
			GtnPairsBean pair = iterator(dto.getKey());
			if (pair == null) {
				listPair.add(pair);
			} else {
                            Object value = pair.getValue();
                            pair.setValue(value);
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
		return iterator(key) != null;
	}

	/**
	 * To check the value is present in the GtnSmallHashMap or not
	 *
	 * @param value
	 * @return
	 */
	public boolean containsValue(Object value) {
		return iterateOverValue(value) != null;
	}

	/**
	 * To get the value using the key
	 *
	 * @param key
	 * @return
	 */
	public Object get(Object key) {
		GtnPairsBean dto = iterator(key);
		if (dto != null) {
			return dto.getValue();
		}
		return null;
	}

	/**
	 * To get the value using the key as integer
	 *
	 * @param key
	 * @return
	 */
	public Integer getInt(Object key) {
		GtnPairsBean dto = iterator(key);
		return dto == null ? 0 : (Integer) dto.getValue();
	}

	/**
	 * To get the value using the key as integer
	 *
	 * @param key
	 * @return
	 */
	public String getString(Object key) {
		GtnPairsBean dto = iterator(key);
		if (dto != null) {
			return (String) dto.getValue();
		}
		return null;
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
	public GtnPairsBean getIndex(final int index) {
		return listPair.get(index);
	}

	/**
	 * To iterate over the list with key
	 *
	 * @param key
	 * @return object[] of the particular key
	 */
	private GtnPairsBean iterator(Object key) {
		for (int i = 0; i < listPair.size(); i++) {
			GtnPairsBean dto = listPair.get(i);
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
	private GtnPairsBean iterateOverValue(Object value) {
		for (int i = 0; i < listPair.size(); i++) {
			GtnPairsBean dto = listPair.get(i);
			if (value.equals(dto.getValue())) {
				return dto;
			}
		}
		return null;
	}
}
