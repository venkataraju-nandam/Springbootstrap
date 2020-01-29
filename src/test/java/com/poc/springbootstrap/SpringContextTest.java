package com.poc.springbootstrap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = SpringBootBootstrapLiveTest.class)
//@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
//@ActiveProfiles("logging-test")
public class SpringContextTest {
 
    @Test
    public void contextLoads() {
    }
}