package com.javiermoreno.stormcluster;

import static backtype.storm.utils.Utils.sleep;
import static java.text.MessageFormat.format;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;


public class MensajesAlAzarSpout extends BaseRichSpout {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(MensajesAlAzarSpout.class);

    private static final int PAUSA_ENTRE_MENSAJES = 3*1000;
    private static final Random rnd = new Random();
    private static final String[] MENSAJES = 
            "Marriage is a trip between Niagra Falls and Reno.#\n\"Nobody listens unless you swear every other word.\" -- Kirk#\nNotice: All incoming fire has the right of way.#\nQuark! Quark! Beware the quantum duck!#\nI'm floating in a most peculiar way.#\nNever trust a computer you cannot carry#.\nBehaviorism is the art of pulling habits out#\nSDR: Shift Disk Right#\nThe Fifth Rule: You have taken yourself too seriously.#\nBearjoran takes over DS9 in Pooh D'Etat!#\nWomen: Can't live with 'em, can't shoot 'em, pass the beer nuts.#\nExpensive imported beer is good for the sole.#\nLove of Money: The root of all EVIL...#".split("#");
    private SpoutOutputCollector collector;

    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        log.info("Abriendo Spout.");
        this.collector = collector;
    }

    @Override
    public void nextTuple() {
    }
    
    @Override
    public void fail(Object id) {
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
    }



}
