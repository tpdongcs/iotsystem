package hcmut.edu.config;

/**
 * Created by bonamana2811 on 3/24/2017.
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.mongodb.MongoClient;

import java.net.UnknownHostException;
import java.util.Locale;
import java.util.ResourceBundle;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"hcmut.edu"})
@EnableMongoRepositories(basePackages={"hcmut.edu.repository"})
@Import(RepositoryRestMvcConfiguration.class)
public class ApplicationConfig {

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    public @Bean
    MongoDbFactory mongoDbFactory() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient("localhost",27017);
        return new SimpleMongoDbFactory(mongoClient, resourceBundle.getString("mongo.db.name"));
    }

    public @Bean
    MongoTemplate mongoTemplate() throws UnknownHostException {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }

    @Bean
    public MongoOperations mongoOperations() throws UnknownHostException {
        MongoOperations mongoOperations = (MongoOperations) mongoTemplate();
        return mongoOperations;
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".html");

        return viewResolver;
    }
}
