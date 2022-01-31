//package com.uottawa.project;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.uottawa.project.service.EventsService;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class EventsControllerTests {
//
//	@LocalServerPort
//	private int port;
//
//	@Autowired
//	private TestRestTemplate restTemplate;
//	
//	@Autowired 
//	private EventsService eventsService;
//	
//    @Test
//    @Sql({ "classpath:sql/integration-test-events.sql" })
//    public void emptyTest() {
//    }
//}
