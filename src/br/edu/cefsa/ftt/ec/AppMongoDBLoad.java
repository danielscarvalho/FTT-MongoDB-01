package br.edu.cefsa.ftt.ec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.*;

public class AppMongoDBLoad {
	
    // Libs: /lib folder or Maven project...
	// https://mvnrepository.com/artifact/com.google.code.gson/gson/2.8.5
	// https://mvnrepository.com/artifact/org.mongodb/mongo-java-driver/3.8.2
	// Tutorial: https://www.mongodb.com/blog/post/getting-started-with-mongodb-and-java-part-i
	
	// https://mongodb.github.io/mongo-java-driver/3.4/driver/getting-started/quick-start/
	// https://university.mongodb.com/courses/M001/about
	
	public static void main(String[] args) {
		// TODO Criar CRUD
       System.out.println("App MongoDB");
       
       Random rand = new Random(); 
       
       //Database - Connection
       MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
       //Database - Schema
       MongoDatabase mongoDatabase = mongoClient.getDatabase("ftt");
       //Collection - Table
       MongoCollection<Document> collection = mongoDatabase.getCollection("ec-docs");
       
       List<Document> documents = new ArrayList<Document>();
       
       for (int i = 0; i < 10; i++) {
           
	       Document docFttec = new Document()
	    		   .append("date",    new Date().toString())
	    		   .append("val",     rand.nextFloat())
	    		   .append("sal",     rand.nextInt())
	    		   .append("docType", "ftt-ec-x")
	    		   .append("counter", i);
	       
	       documents.add(docFttec);
       }
       
       collection.insertMany(documents);
       
       //Create Document - JSON String
       collection.insertMany(Arrays.asList(
    	        Document.parse("{ item: 'canvas', qty: 100, size: { h: 28, w: 35.5, uom: 'cm' }, status: 'A', docType: 'book' }"),
    	        Document.parse("{ item: 'journal', qty: 25, size: { h: 14, w: 21, uom: 'cm' }, status: 'A', docType: 'book' }"),
    	        Document.parse("{ item: 'mat', qty: 85, size: { h: 27.9, w: 35.5, uom: 'cm' }, status: 'A', docType: 'book' }"),
    	        Document.parse("{ item: 'mousepad', qty: 25, size: { h: 19, w: 22.85, uom: 'cm' }, status: 'P', docType: 'book' }"),
    	        Document.parse("{ item: 'notebook', qty: 50, size: { h: 8.5, w: 11, uom: 'in' }, status: 'P', docType: 'book' }"),
    	        Document.parse("{ item: 'paper', qty: 100, size: { h: 8.5, w: 11, uom: 'in' }, status: 'D', docType: 'book' }"),
    	        Document.parse("{ item: 'planner', qty: 75, size: { h: 22.85, w: 30, uom: 'cm' }, status: 'D', docType: 'book' }"),
    	        Document.parse("{ item: 'postcard', qty: 45, size: { h: 10, w: 15.25, uom: 'cm' }, status: 'A', docType: 'book' }"),
    	        Document.parse("{ item: 'sketchbook', qty: 80, size: { h: 14, w: 21, uom: 'cm' }, status: 'A', docType: 'book' }"),
    	        Document.parse("{ item: 'sketch pad', qty: 95, size: { h: 22.85, w: 30.5, uom: 'cm' }, status: 'A', docType: 'book' }")
    	));
       
       
       //Delete Document
       
       collection.deleteOne(new Document().append("_id", new ObjectId("5ef2d8b8bf5bf617b927e477")));
       collection.deleteOne(new Document().append("name", "Maria Silva"));

       collection.insertOne(
            new Document("name", "MongoDB")
                 .append("docType", "database")
                 .append("count", 1)
                 .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
                 .append("info", new Document("x", 203).append("y", 102))
       );
       
       //Update Document
       collection.updateMany(eq("docType", "people"), 
    		   new Document("$set", new Document("status", "ok")));

       System.out.println("Docs qtt " + collection.countDocuments());
       
       mongoClient.close();
	
	}

}
