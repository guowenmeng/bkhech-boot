package com.bkhech.boot.commons.util;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ShardingUtilTest {

    @Test
    void getGroupCount() {
    }

    @Test
    void getSubGroup() {
    }

    @Test
    void createRanges() {
    }

    @Test
    void combinationTest() {
        final List<Integer> waitToProcessDataList = IntStream.range(0, 100000).boxed().collect(Collectors.toList());
        process(waitToProcessDataList);
    }

    public List<String> process(List<Integer> waitToProcessDataList) {
        final int concurrence = 10;
        final List<String> results = new CopyOnWriteArrayList<>();

        final List<ShardingUtil.Range> ranges = ShardingUtil.createRanges(waitToProcessDataList.size(), 5);
        int groupCount = ShardingUtil.getGroupCount(ranges.size(), concurrence);

        System.out.printf("process: ranges.size=%s, groupCount=%s", ranges.size(), groupCount);

        for (int i = 0; i < groupCount; i++) {
            final ShardingUtil.SubGroup subGroup = ShardingUtil.getSubGroup(i, concurrence, ranges.size());
            final List<ShardingUtil.Range> subRanges = ranges.subList(subGroup.start, subGroup.end);

            CompletableFuture[] completableFutures = subRanges.stream().map(subRange -> CompletableFuture.supplyAsync(() -> {
                for (int index = subRange.from; index < subRange.to; index++) {
                    final Integer data = waitToProcessDataList.get(index);

                    //process. changed according to your actual logical
                    String result = String.valueOf(data);

                    results.add(result);
                }
                return null;
            })).toArray(CompletableFuture[]::new);

            try {
                CompletableFuture.allOf(completableFutures).get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return results;
    }
}