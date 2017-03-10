package conf;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by meisei on 2017/3/10.
 */
@Configuration
@PropertySource("classpath:application-test.properties")
public class TestConfig {
    @Value("${spring.data.mongodb.uri}")
    private String mongodbUri;

    @Value("${spring.data.mongodb.dbName}")
    private String dbName;

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() throws Exception {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean("mongoTemplate")
    public MongoTemplate mongoTemplate() {
        Mongo mongoClient = new MongoClient(new MongoClientURI(mongodbUri, new MongoClientOptions.Builder()
                .connectionsPerHost(20)
                .minConnectionsPerHost(20)
                .connectTimeout(20)
                .maxWaitTime(20)
                .socketKeepAlive(true)
                .socketTimeout(20)));
        return new MongoTemplate(mongoClient, dbName);
    }
}
