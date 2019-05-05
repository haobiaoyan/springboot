package com.example.springbootmultidatasource;

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
public class SpringbootMultidatasourceApplicationTests {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate1;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate2;


    @Test
    public void contextLoads() {
        //jdbcTemplate1.execute("select * from user");
        //jdbcTemplate2.update("insert into employee(name,sex) values(?,?)","张三",2);

        Assert.assertEquals("3",jdbcTemplate2.queryForObject("select count(1) from employee",String.class));

        List<String> list = jdbcTemplate2.queryForList("select name from employee",String.class);

        for (String s : list){
            System.out.println(s);
        }
    }

}
