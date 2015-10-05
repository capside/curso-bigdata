/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javiermoreno.enronsocialgraph;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 *
 * @author Javier
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Mail {

    @JsonProperty("_id") 
    public String id;
    public String body;
    public String subFolder;
    public String mailbox;
    public String filename;
    public Headers headers;

    @Override
    public String toString() {
        return "Mail{" + "id=" + id + ", body=" + body + ", subFolder=" + subFolder + ", mailbox=" + mailbox + ", filename=" + filename + ", headers=" + headers + '}';
    }

    
}
