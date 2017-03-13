package com.local.test.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.WriteResult;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.local.test.conf.TestConfig;
import com.local.test.mongodb.model.Person;

/**
 * Created by meisei on 2017/3/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class TestMongo {

    public static final BasicQuery QUERY = new BasicQuery(new BasicDBObject("name", "james"));

    @Autowired
    private MongoTemplate mongoTemplate;


    @Before
    public void before() {
        String collectionName = "person";
        mongoTemplate.getCollection(collectionName);
        mongoTemplate.save(new Person("james", 11));
    }

    @Test
    public void testConnect() {
        Assert.assertNotNull(mongoTemplate);
    }

    @Test
    public void testFind() {
        Person person = mongoTemplate.findOne(QUERY, Person.class);
        Assert.assertNotNull(person);
    }

    @Test
    public void testCount() {
        Long count = mongoTemplate.count(QUERY, Person.class);
        Assert.assertNotNull(count);
    }

    @Test
    public void testDelete() {
        WriteResult writeResult = mongoTemplate.remove(QUERY, Person.class);
        Assert.assertNotNull(writeResult);
    }

    @After
    public void afterClass() {
        mongoTemplate.findAllAndRemove(QUERY, Person.class);
    }

}
