package br.edu.cefsa.ftt.ec;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class AppMongoDBLoad {
	
    // Libs: /lib folder or Maven project...
	// https://mvnrepository.com/artifact/com.google.code.gson/gson/2.8.5
	// https://mvnrepository.com/artifact/org.mongodb/mongo-java-driver/3.8.2
	// Tutorial: https://www.mongodb.com/blog/post/getting-started-with-mongodb-and-java-part-i
	
	public static void main(String[] args) {
		// TODO Desenvolver CRUD para o MongoDB
       System.out.println("App MongoDB");
       
       Random rand = new Random(); 
       
       MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
       MongoDatabase mongoDatabase = mongoClient.getDatabase("ftt");
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
       
       System.out.println("Docs qtt " + collection.countDocuments());
       
       mongoClient.close();
	
	}

}
