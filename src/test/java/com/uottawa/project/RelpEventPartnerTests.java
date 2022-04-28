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

import com.uottawa.project.entity.RelpEventPartner;
import com.uottawa.project.entity.RelpEventPartnerPK;
import com.uottawa.project.service.RelpEventPartnerService;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RelpEventPartnerTests {

	@LocalServerPort
	private int port;

	@Autowired
	private RelpEventPartnerService RelpEventPartnerService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventPartner.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventPartner.sql" })
	public void addTest() {
		RelpEventPartnerPK pk = new RelpEventPartnerPK();
		pk.setEventId(5L);
		pk.setPartnerId(4L);
		RelpEventPartner add = new RelpEventPartner();
		add.setId(pk);
		RelpEventPartner event = RelpEventPartnerService.add(add);
		assertEquals(event.getId().getEventId().longValue(), 5L);
		assertEquals(event.getId().getPartnerId().longValue(), 4L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventPartner.sql" })
	public void deleteByIdTest() {
		RelpEventPartnerService.deleteById(4L, 8L);
		assertEquals(this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_event_partner/find_all", List.class).size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventPartner.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<RelpEventPartner> list = this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_event_partner/find_all", List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventPartner.sql" })
	public void findByIdTest1() {
		RelpEventPartner relation = RelpEventPartnerService.findById(10L, 9L);
		assertEquals(relation, null);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventPartner.sql" })
	public void findByIdTest2() {
		RelpEventPartner event = this.restTemplate.getForObject(
				"http://localhost:" + port + "/relp_event_partner/find_by_id?eventId=4&partnerId=8",
				RelpEventPartner.class);
		assertEquals(event.getId().getEventId().longValue(), 4L);
		assertEquals(event.getId().getPartnerId().longValue(), 8L);
	}
}
