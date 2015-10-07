package com.javiermoreno.stormdrpc;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.coordination.BatchOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBatchBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class PIAgregadorBolt extends BaseBatchBolt {
    
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PIAgregadorBolt.class);
    
    /** Identificación del drpc lanzado. */
    private Object requestId;
    /** Utilizado para emitir el resultado. */
    private BatchOutputCollector collector;
    /** Resultados parciales de PI */
    private List<Double> resultadosParcialesPI = new ArrayList<>();
    
    public void prepare(Map conf, TopologyContext context, BatchOutputCollector collector, Object id) {
        System.out.println(this);
        log.info(MessageFormat.format("Preparando agregador para request {0}", id));
        this.requestId = id;
        this.collector = collector;        
    }

    public void execute(Tuple tuple) {
    }

    public void finishBatch() {
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
    }

}
