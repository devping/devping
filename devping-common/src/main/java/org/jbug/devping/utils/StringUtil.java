package org.jbug.devping.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * Created by jhouse on 10/12/14.
 */
public class StringUtil {

    public static <T>  List<T> setToList(TreeSet<T> treeSet) {
        List<T> tagListWithPrefix = new ArrayList<>();
        tagListWithPrefix.addAll(treeSet);
        return tagListWithPrefix;
    }

    public static List<String> arrayToList(String array,String delimeter){
        List<String> arrayList = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(array,delimeter);
        while (stringTokenizer.hasMoreTokens()) {
            arrayList.add(stringTokenizer.nextToken());
        }
        return arrayList;
    }

    public static List<String> arrayToList(String array){
        List<String> arrayList = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(array);
        while (stringTokenizer.hasMoreTokens()) {
            arrayList.add(stringTokenizer.nextToken());
        }
        return arrayList;
    }
}
