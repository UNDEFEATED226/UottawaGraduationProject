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

import com.uottawa.project.entity.RelpEventGrant;
import com.uottawa.project.entity.RelpEventGrantPK;
import com.uottawa.project.service.RelpEventGrantService;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RelpEventGrantTests {

	@LocalServerPort
	private int port;

	@Autowired
	private RelpEventGrantService relpEventGrantService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventGrant.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventGrant.sql" })
	public void addTest() {
		RelpEventGrantPK pk = new RelpEventGrantPK();
		pk.setEventId(5L);
		pk.setGrantId(4L);
		RelpEventGrant add = new RelpEventGrant();
		add.setId(pk);
		RelpEventGrant event = relpEventGrantService.add(add);
		assertEquals(event.getId().getEventId().longValue(), 5L);
		assertEquals(event.getId().getGrantId().longValue(), 4L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventGrant.sql" })
	public void deleteByIdTest() {
		relpEventGrantService.deleteById(1L, 2L);
		assertEquals(this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_event_grant/find_all", List.class).size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventGrant.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<RelpEventGrant> list = this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_event_grant/find_all", List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventGrant.sql" })
	public void findByIdTest1() {
		RelpEventGrant relation = relpEventGrantService.findById(10L, 9L);
		assertEquals(relation, null);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventGrant.sql" })
	public void findByIdTest2() {
		RelpEventGrant event = this.restTemplate.getForObject(
				"http://localhost:" + port + "/relp_event_grant/find_by_id?eventId=1&grantId=1",
				RelpEventGrant.class);
		assertEquals(event.getId().getEventId().longValue(), 1L);
		assertEquals(event.getId().getGrantId().longValue(), 1L);
	}
}
