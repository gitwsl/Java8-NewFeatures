//package com.demo.flink;
//
//import org.apache.flink.api.common.functions.FlatMapFunction;
//import org.apache.flink.api.java.tuple.Tuple2;
//import org.apache.flink.streaming.api.datastream.DataStream;
//import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
//import org.apache.flink.streaming.api.windowing.time.Time;
//
///**
// * @author lin.wang
// * @date 2020/10/14
// */
//public class FlinkTest {
//
//
//    public static void main(String[] args) throws Exception {
//
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//
//        DataStream<Tuple2<String, Integer>> dataStream = env
//                .socketTextStream("localhost", 9999)
//                .flatMap(new Splitter())
//                .keyBy(value -> value.f0)
//                .timeWindow(Time.seconds(5))
//                .sum(1);
//
//        dataStream.print();
//
//        env.execute("Window WordCount");
//    }
//
//    public static class Splitter implements FlatMapFunction<String, Tuple2<String, Integer>> {
//        @Override
//        public void flatMap(String s, org.apache.flink.util.Collector<Tuple2<String, Integer>> collector) throws Exception {
//            for (String word : s.split(" ")) {
//                collector.collect(new Tuple2<String, Integer>(word, 1));
//            }
//        }
//    }
//
//}