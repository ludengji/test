package mongodb;

import com.google.common.collect.Lists;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

import mongodb.model.Person;

/**
 * Created by meisei on 2017/3/9.
 */
public class TestConnect {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 27017;
        String username = "dbtest";
        String password = "hello";
        String dbName = "test";

        ServerAddress serverAddress = new ServerAddress(host, port);
        List<ServerAddress> addresses = Lists.newArrayList();
        addresses.add(serverAddress);
        List<MongoCredential> credentials = Lists.newArrayList();
        MongoCredential credential = MongoCredential.createCredential(username, dbName, password.toCharArray());
        credentials.add(credential);
        MongoClient mongoClient = new MongoClient(serverAddress, credentials);
        MongoDatabase database = mongoClient.getDatabase(dbName);
        System.out.println("connect successfully");

        MongoCollection<Document> docs = database.getCollection("test");
        docs.deleteMany(new Document());
        insert(docs);
        find(docs);

        String collectionName = "person";
        MongoTemplate template = new MongoTemplate(new SimpleMongoDbFactory(mongoClient, dbName));
        template.getCollection(collectionName);
        template.save(new Person("james", 11));
        List<Person> person = template.findAllAndRemove(new BasicQuery(new BasicDBObject("name","james")), Person.class);
        System.out.println(person);
    }

    private static void insert(MongoCollection<Document> docs) {
        List<Document> es = Lists.newArrayList(new Document("name", "john"), new Document("name", "kobe"));
        docs.insertMany(es);
    }

    private static void find(MongoCollection<Document> docs) {
        FindIterable<Document> findIterable = docs.find();
        for (Document document : findIterable) {
            System.out.println(document);
        }
    }
}
