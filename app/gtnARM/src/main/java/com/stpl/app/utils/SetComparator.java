/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Sathya.Seelan
 */
public class SetComparator implements Comparator<String> {

    @Override
    public int compare(String s1, String s2) {
        return s2.length() - s1.length();
    }

    public Set<String> compareList(Set<String> set) {
        SetComparator ss = new SetComparator();
        List list = new ArrayList<>(set);
        Collections.sort(list);
        Collections.sort(list, ss);
        return new HashSet<>(list);
    }
}
