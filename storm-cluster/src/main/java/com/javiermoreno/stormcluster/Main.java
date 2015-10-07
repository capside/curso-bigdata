package com.javiermoreno.stormcluster;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;

/**
 */
public class Main {

    private static int ACK_TIMEOUT_SEC = 5;

    public static void main(String[] args) {
        Config conf = new Config();
        conf.setMessageTimeoutSecs(ACK_TIMEOUT_SEC);

        TopologyBuilder builder = new TopologyBuilder();

        // Cada uno de estos set crea un Executor nuevo
        MensajesAlAzarSpout spout = new MensajesAlAzarSpout();
        builder.setSpout("mensajesSource", spout, 1 /* número de spouts */);
        EchoConsoleBolt bolt = new EchoConsoleBolt();
        builder.setBolt("echoBolt", bolt, 1 /* número de bolts*/)
                .shuffleGrouping("mensajesSource");

        // Para clúster:
        // StormTopology topology = builder.createTopology();
        // StormSubmitter.submitTopology("mensatopologia", conf, topology);

        // Para simulación local:
        LocalCluster localCluster = new LocalCluster();
        StormTopology topology = builder.createTopology();
        localCluster.submitTopology("mensatopologia", conf, topology);
    }

}
