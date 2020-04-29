package com.emp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EmployeeManagementServerApplicationTests {
//    @Ignore
    @Test
    void contextLoads() {
    	System.out.println("LOVE U DARLING");
    }

}
