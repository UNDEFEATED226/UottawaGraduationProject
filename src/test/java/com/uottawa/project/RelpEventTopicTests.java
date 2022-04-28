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

import com.uottawa.project.entity.RelpEventTopic;
import com.uottawa.project.entity.RelpEventTopicPK;
import com.uottawa.project.service.RelpEventTopicService;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RelpEventTopicTests {

	@LocalServerPort
	private int port;

	@Autowired
	private RelpEventTopicService relpEventTopicService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-RelpEventTopic.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-RelpEventTopic.sql" })
	public void addTest() {
		RelpEventTopicPK pk = new RelpEventTopicPK();
		pk.setEventId(5L);
		pk.setThemeId(4L);
		RelpEventTopic add = new RelpEventTopic();
		add.setId(pk);
		RelpEventTopic event = relpEventTopicService.add(add);
		assertEquals(event.getId().getEventId().longValue(), 5L);
		assertEquals(event.getId().getThemeId().longValue(), 4L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-RelpEventTopic.sql" })
	public void deleteByIdTest() {
		relpEventTopicService.deleteById(1L, 2L);
		assertEquals(this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_event_topic/find_all", List.class).size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-RelpEventTopic.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<RelpEventTopic> list = this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_event_topic/find_all", List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-RelpEventTopic.sql" })
	public void findByIdTest1() {
		RelpEventTopic relation = relpEventTopicService.findById(10L, 9L);
		assertEquals(relation, null);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-RelpEventTopic.sql" })
	public void findByIdTest2() {
		RelpEventTopic event = this.restTemplate.getForObject(
				"http://localhost:" + port + "/relp_event_topic/find_by_id?eventId=1&themeId=2",
				RelpEventTopic.class);
		assertEquals(event.getId().getEventId().longValue(), 1L);
		assertEquals(event.getId().getThemeId().longValue(), 2L);
	}
}
