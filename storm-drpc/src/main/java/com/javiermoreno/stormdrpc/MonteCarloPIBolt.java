package com.javiermoreno.stormdrpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class MonteCarloPIBolt extends BaseBasicBolt {
    private static final long serialVersionUID = 1L;
    
    private static final Logger log = LoggerFactory.getLogger(MonteCarloPIBolt.class);

    public void execute(Tuple input, BasicOutputCollector collector) {
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
    }

}
