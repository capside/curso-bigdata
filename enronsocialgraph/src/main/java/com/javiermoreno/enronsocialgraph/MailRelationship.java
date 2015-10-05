/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javiermoreno.enronsocialgraph;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.WritableComparable;

/**
 *
 * @author ciberado
 */
public class MailRelationship implements WritableComparable {

    private String from;
    private String to;

    public MailRelationship() {
    }

    public MailRelationship(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        // we use boolean to handle nulls
        if (from == null || to == null) {
            out.writeBoolean(false);
        } else {
            out.writeBoolean(true);
            out.writeUTF(from);
            out.writeUTF(to);            
        }
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        boolean exists = in.readBoolean();
        if (exists == true) {
            from = in.readUTF();
            to = in.readUTF();
        }
    }

    @Override
    public int compareTo(Object o) {
        MailRelationship other = (MailRelationship) o;
        
        int result = this.from.compareTo(other.from);
        if (result == 0) {
            result = this.to.compareTo(other.to);
        }
        return result;
    }

    @Override
    public String toString() {
        return from + "->" + to;
    }

    
    
}
