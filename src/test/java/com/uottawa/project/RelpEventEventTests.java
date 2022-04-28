package com.uottawa.project;

import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import com.uottawa.project.entity.RelpEventEvent;
import com.uottawa.project.entity.RelpEventEventPK;
import com.uottawa.project.service.RelpEventEventService;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RelpEventEventTests {

	@LocalServerPort
	private int port;

	@Autowired
	private RelpEventEventService relpEventEventService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventEvent.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventEvent.sql" })
	public void addTest() {
		RelpEventEventPK pk = new RelpEventEventPK();
		pk.setPastEventId(5L);
		pk.setFutureEventId(4L);
		RelpEventEvent add = new RelpEventEvent();
		add.setId(pk);
		RelpEventEvent event = relpEventEventService.add(add);
		assertEquals(event.getId().getPastEventId().longValue(), 5L);
		assertEquals(event.getId().getFutureEventId().longValue(), 4L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventEvent.sql" })
	public void deleteByIdTest() {
		relpEventEventService.deleteById(1L, 2L);
		assertEquals(this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_event_event/find_all", List.class).size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventEvent.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<RelpEventEvent> list = this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_event_event/find_all", List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventEvent.sql" })
	public void findByIdTest1() {
		RelpEventEvent relation = relpEventEventService.findById(10L, 9L);
		assertEquals(relation, null);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventEvent.sql" })
	public void findByIdTest2() {
		RelpEventEvent event = this.restTemplate.getForObject(
				"http://localhost:" + port + "/relp_event_event/find_by_id?pastEventId=1&futureEventId=2",
				RelpEventEvent.class);
		assertEquals(event.getId().getPastEventId().longValue(), 1L);
		assertEquals(event.getId().getFutureEventId().longValue(), 2L);
	}
}
