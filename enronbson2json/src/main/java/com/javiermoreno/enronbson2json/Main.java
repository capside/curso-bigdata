package com.javiermoreno.enronbson2json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.undercouch.bson4jackson.BsonFactory;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 *
 * @author Javier
 */
public class Main {

    public static void main(String[] args) throws IOException {
        File fIn = new File("C:\\tmp\\dump\\enron_mail\\messages.bson");
        InputStream in = new BufferedInputStream(new FileInputStream(fIn));
        File fOut = new File("C:\\tmp\\dump\\enron_mail\\messages.json");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fOut)));
        File fInboxOut = new File("C:\\tmp\\dump\\enron_mail\\messages_inbox.json");
        PrintWriter inboxOut = new PrintWriter(new BufferedWriter(new FileWriter(fInboxOut)));
        ObjectMapper mapperIn = new ObjectMapper(new BsonFactory());

        ObjectMapper mapperOut = new ObjectMapper(new JsonFactory());

        int totalCount = 0;
        int inboxCount = 0;
        int errorCount = 0;
        MappingIterator<Mail> it = mapperIn.readValues(new BsonFactory().createParser(fIn), Mail.class);
        while (it.hasNext() == true) {
            try {
                totalCount = totalCount + 1;
                Mail mail = it.next();
                String msg = mapperOut.writeValueAsString(mail);
                out.println(msg);
                if (mail.subFolder.equals("notes_inbox") == true) {
                    inboxOut.println(msg);
                    inboxCount = inboxCount + 1;
                }
                if (totalCount % 1000 == 0) {
                    System.out.println(totalCount);
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage()); 
                errorCount = errorCount + 1;
            }
        }

        in.close();
        out.close();
        inboxOut.close();
        System.out.println("Total: " + totalCount);
        System.out.println("Inbox: " + inboxCount);
        System.out.println("Errors: " + errorCount);
    }
}
