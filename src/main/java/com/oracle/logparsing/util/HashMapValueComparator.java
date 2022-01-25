package com.oracle.logparsing.util;

import java.util.Comparator;
import java.util.Map;

public class HashMapValueComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
    }
}
