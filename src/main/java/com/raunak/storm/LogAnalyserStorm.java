package com.raunak.storm;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

/**
 * Created by raunak.agrawal on 3/16/17.
 */
public class LogAnalyserStorm {

    public static void main(String[] args) throws Exception {

        Config config = new Config();
        config.setDebug(true);

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("call-log-reader-spout", new FakeCallLogReaderSpout());
        builder.setBolt("call-log-creator-bolt", new CallLogCreatorBolt()).shuffleGrouping("call-log-reader-spout");
        builder.setBolt("call-log-counter-bolt", new CallLogCounterBolt()).fieldsGrouping("call-log-creator-bolt",
                new Fields("call"));

        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("LogAnalyserStorm", config, builder.createTopology());
        Thread.sleep(10000);

        cluster.shutdown();

        System.out.println("Done");
    }
}
