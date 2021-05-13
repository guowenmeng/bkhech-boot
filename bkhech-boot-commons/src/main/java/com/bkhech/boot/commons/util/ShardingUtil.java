package com.bkhech.boot.commons.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guowm
 * @date 2021/4/28
 */
public class ShardingUtil {

    /**
     * 根据并发度分组
     * @param rangeSize
     * @param concurrence 并发度
     * @return
     */
    public static int getGroupCount(int rangeSize, int concurrence) {
        return rangeSize / concurrence + (rangeSize % concurrence == 0 ? 0 : 1);
    }

    /**
     * 获取子组
     * @param index 组游标
     * @param rangeSize 组总大小
     * @param concurrence
     * @return
     */
    public static SubGroup getSubGroup(int index, int concurrence, int rangeSize) {
        int start = index * concurrence;
        int end = start + concurrence;

        if (end > rangeSize) {
            end = rangeSize;
        }
        return new SubGroup(start, end);
    }

    /**
     * 切分
     * @param totalNumbers 总数据条数
     * @param numberOfMaxLimit 每组最大数据条数
     * @return 切分后的对象组
     */
    public static List<Range> createRanges(int totalNumbers, int numberOfMaxLimit) {
        ArrayList<Range> ranges = new ArrayList<>();

        int numberInRange = (int) Math.ceil((double) totalNumbers / (double) numberOfMaxLimit);
        for (int i = 0; i < numberInRange; i++) {
            if (i == numberInRange - 1) {
                ranges.add(new Range(i * numberOfMaxLimit, totalNumbers));
            } else {
                ranges.add(new Range(i * numberOfMaxLimit, (i + 1) * numberOfMaxLimit));
            }
        }
        return ranges;
    }

    public static class SubGroup {
        public final int start;
        public final int end;

        private SubGroup(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "SubGroup{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    /**
     * 范围：前闭后开， eg：[0,10）[10,11)
     */
    public static class Range {
        public final int from;
        public final int to;

        private Range(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "Range{" +
                    "from=" + from +
                    ", to=" + to +
                    '}';
        }
    }

}
