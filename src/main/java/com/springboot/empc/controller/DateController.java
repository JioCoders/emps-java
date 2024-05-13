package com.springboot.empc.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DateController {

    @Value("${spring.data.mongodb.uri}")
    private String cs;

    @GetMapping("/check/date={date}/time={time}")
    @ResponseStatus(HttpStatus.OK)
    public String checkDate(@PathVariable("date") String dateString, @PathVariable("time") String timeString) {
        return checkDateDiff(dateString, timeString);
    }

    String checkDateDiff(String dateString, String timeString) {
        log.info("dateString---> " + dateString);
        log.info("timeString---> " + timeString);
        String finalDateString = "No date Found-> current-time=" + Calendar.getInstance().getTime().toString();
        // MongoDB connection parameters
        String connectionString = cs;
        String databaseName = "tododb";
        String collectionName = "user";

        // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // System.out.println("Start Date: " + dateFormat.format(startDate));

        // Connect to MongoDB
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            // Get the database
            MongoDatabase database = mongoClient.getDatabase(databaseName);

            // Get the collection
            MongoCollection<Document> collection = database.getCollection(collectionName);

            // Fetch records from MongoDB
            for (Document document : collection.find()) {
                String dbDateString = document.getString("request_date");
                if (dbDateString != null) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date dbDate = dateFormat.parse(dbDateString);
                    System.out.println("document.dateStromg====>" + dbDateString);
                    Date finalDate = substractDate1Hr(dbDate);
                    finalDateString = dateFormat.format(finalDate);
                    System.out.println("document.finalDate----->" + finalDateString);
                }
                // log.debug("document.getDate=========>" + document.getDate("request_date"));
                // Date requestDate = document.getDate("request_date"); // Assuming requestDate
                // is a field in your documents

                // Check if the request date falls within the specified time range
                // if (requestDate != null && requestDate.after(startDate) &&
                // requestDate.before(endDate)) {
                // // If yes, process the record
                // System.out.println("Record ID: " + document.getObjectId("_id") + ", RequestDate: "
                // + dateFormat.format(requestDate));
                // // Perform other operations as needed
                // } else {
                // // If no, skip the record or perform other actions
                // }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("collectionName", e.getMessage());
        }
        return finalDateString;
    }

    Date substractDate1Hr(Date currentDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);

        cal.add(Calendar.HOUR_OF_DAY, -1);

        Date finalDate = cal.getTime();
        return finalDate;
    }
}
