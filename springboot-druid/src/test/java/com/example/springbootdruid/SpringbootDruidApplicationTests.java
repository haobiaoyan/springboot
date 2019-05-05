package com.example.springbootdruid;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDruidApplicationTests {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate secondaryJdbcTemplate;

    @Test
    public void contextLoads() {
        Assert.assertEquals("3",secondaryJdbcTemplate.queryForObject("select count(1) from employee",String.class));

        List<String> list = secondaryJdbcTemplate.queryForList("select name from employee",String.class);

        for (String s : list){
            System.out.println(s);
        }
    }

}
