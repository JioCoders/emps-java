package com.springboot.empc.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DateController {

    @Value("${spring.data.mongodb.uri}")
    private String conString;

    @GetMapping("/check/date={date}/time={time}")
    @ResponseStatus(HttpStatus.OK)
    public String checkDate(@PathVariable("date") String date, @PathVariable("time") String time) {
        return checkDateDiff(date, time);
    }

    String checkDateDiff(String dateString, String timeString) {
        log.info("requestparam.dateString---> " + dateString);
        log.info("requestparam.timeString---> " + timeString);
        String finalDateString = "No date Found-> current-time=" + Calendar.getInstance().getTime().toString();

        String connectionString = conString;
        String databaseName = "tododb";
        String collectionName = "employee";

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {

            MongoDatabase database = mongoClient.getDatabase(databaseName);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s1 = "2024-05-14 23:26:00";
            String s2 = "2024-05-14 23:26:18";
            Date startDate = dateFormat.parse(s1);
            Date endDate = dateFormat.parse(s2);
            // Date startDate = dateFormat.parse(sampleDate);
            // Date cDate = Calendar.getInstance().getTime();

            // Document doc = new Document("req_date", Document.parse(
            // "{$gte: ISODate('" + cDate + "'),$lte: ISODate('" + cDate + "')}"));

            BasicDBObject query = new BasicDBObject("request_date",
                    new BasicDBObject("$gte", s1).append("$lt", s2));

            System.out.println("-----------init------------->");
            for (Document document : collection.find(query)) {
                String dbDateString = document.getString("request_date");
                // Date finalDate = null;
                // if (dbDateString != null) {
                // Date dbDate = dateFormat.parse(dbDateString);
                System.out.println("document.dateStromg====>" + dbDateString);
                // finalDate = substractDate1Hr(dbDate);
                // finalDateString = dateFormat.format(finalDate);
                // System.out.println("document.finalDate----->" + finalDateString);
                // }
                // Date currenDate = Calendar.getInstance().getTime();

                // if (finalDate != null) {
                // if (currenDate.after(finalDate)) {
                // System.out.println("Record ID: " + document.getObjectId("_id") + ",
                // RequestDate: "
                // + dateFormat.format(finalDate));
                // } else if (currenDate.before(finalDate)) {
                // System.out.println("Record ID: " + document.getObjectId("_id") + ",
                // RequestDate: "
                // + dateFormat.format(finalDate));
                // }
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
