/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javiermoreno.enronsocialgraph;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Writable;

/**
 *
 * @author ciberado
 */
public class ArrayLongWritable extends ArrayWritable {

    public ArrayLongWritable() {
        super(LongWritable.class);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Writable current : super.get()) {
            sb.append(current).append(", ");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - ", ".length());
        }
        return sb.toString();
                
    }
    
}
