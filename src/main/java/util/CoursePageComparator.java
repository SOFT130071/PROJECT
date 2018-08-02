package util;

import entity.CoursePage;

import java.util.Comparator;

public class CoursePageComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        CoursePage c1 = (CoursePage) o1;
        CoursePage c2 = (CoursePage) o2;
        String[] l1 = c1.getNumber().split("\\.");
        String[] l2 = c2.getNumber().split("\\.");
        for (int i = 0; i < l1.length && i < l2.length; i++) {
            int flag = l1[i].compareTo(l2[i]);
            if (flag != 0)
                return flag;
        }
        if (l1.length < l2.length)
            return -1;
        else if (l1.length > l2.length)
            return 1;
        return 0;
    }
}
