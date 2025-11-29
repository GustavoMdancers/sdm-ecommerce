package br.unibh.sdm.ecommerce.tests;

import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

import br.unibh.sdm.ecommerce.entity.Store;
import br.unibh.sdm.ecommerce.persistance.StoreRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { PropertyPlaceholderAutoConfiguration.class, StoreTests.DynamoDBConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StoreTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreTests.class);

    @Configuration
    @EnableDynamoDBRepositories(basePackageClasses = StoreRepository.class)
    public static class DynamoDBConfig {

        @Value("${amazon.aws.accesskey}")
        private String amazonAWSAccessKey;

        @Value("${amazon.aws.secretkey}")
        private String amazonAWSSecretKey;

        public AWSCredentialsProvider amazonAWSCredentialsProvider() {
            return new AWSStaticCredentialsProvider(amazonAWSCredentials());
        }

        @Bean
        public AWSCredentials amazonAWSCredentials() {
            return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
        }

        @Bean
        public AmazonDynamoDB amazonDynamoDB() {
            return AmazonDynamoDBClientBuilder.standard().withCredentials(amazonAWSCredentialsProvider())
                    .withRegion("us-east-2").build();
        }
    }

    @Autowired
    private StoreRepository repository;

    @Test
    public void test1Create() throws ParseException {
        LOGGER.info("Adicionando item na loja...");
        Store e1 = new Store("Eletrônicos", "Smartphone Samsung Galaxy S21", "Samsung Galaxy S21", 799.99, 50);
        repository.save(e1);
        Store e2 = new Store("Eletrônicos", "Laptop Dell XPS 13", "Dell XPS 13", 999.99, 30);
        repository.save(e2);

        LOGGER.info("Pesquisando todos os itens ...");
        Iterable<Store> itens = repository.findAll();
        for (Store store : itens) {
            LOGGER.info("Itens encontrados: {}", store);
        }
    }

    @Test
    public void test2Delete() {
        LOGGER.info("Removendo itens ...");
        repository.deleteAll();

        List<Store> result = (List<Store>) repository.findAll();
        assertEquals(0, result.size());
    }

}
