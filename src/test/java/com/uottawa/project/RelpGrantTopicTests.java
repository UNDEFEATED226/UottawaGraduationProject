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
import com.uottawa.project.entity.RelpGrantTopic;
import com.uottawa.project.entity.RelpGrantTopicPK;
import com.uottawa.project.service.RelpGrantTopicService;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RelpGrantTopicTests {

	@LocalServerPort
	private int port;

	@Autowired
	private RelpGrantTopicService relpGrantTopicService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-RelpGrantTopic.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-RelpGrantTopic.sql" })
	public void addTest() {
		RelpGrantTopicPK pk = new RelpGrantTopicPK();
		pk.setGrantId(5L);
		pk.setThemeId(4L);
		RelpGrantTopic add = new RelpGrantTopic();
		add.setId(pk);
		RelpGrantTopic event = relpGrantTopicService.add(add);
		assertEquals(event.getId().getGrantId().longValue(), 5L);
		assertEquals(event.getId().getThemeId().longValue(), 4L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-RelpGrantTopic.sql" })
	public void deleteByIdTest() {
		relpGrantTopicService.deleteById(1L, 2L);
		assertEquals(this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_grant_topic/find_all", List.class).size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-RelpGrantTopic.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<RelpGrantTopic> list = this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_grant_topic/find_all", List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-RelpGrantTopic.sql" })
	public void findByIdTest1() {
		RelpGrantTopic relation = relpGrantTopicService.findById(10L, 9L);
		assertEquals(relation, null);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-RelpGrantTopic.sql" })
	public void findByIdTest2() {
		RelpGrantTopic event = this.restTemplate.getForObject(
				"http://localhost:" + port + "/relp_grant_topic/find_by_id?themeId=2&grantId=1",
				RelpGrantTopic.class);
		assertEquals(event.getId().getThemeId().longValue(), 2L);
		assertEquals(event.getId().getGrantId().longValue(), 1L);
	}
}
