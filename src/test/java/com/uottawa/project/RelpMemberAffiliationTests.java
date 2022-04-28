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
import com.uottawa.project.entity.RelpMemberAffiliation;
import com.uottawa.project.entity.RelpMemberAffiliationPK;
import com.uottawa.project.service.RelpMemberAffiliationService;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RelpMemberAffiliationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private RelpMemberAffiliationService relpMemberAffiliationService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberAffiliation.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberAffiliation.sql" })
	public void addTest() {
		RelpMemberAffiliationPK pk = new RelpMemberAffiliationPK();
		pk.setMemberId(5L);
		pk.setAffiliationId(4L);
		RelpMemberAffiliation add = new RelpMemberAffiliation();
		add.setId(pk);
		RelpMemberAffiliation event = relpMemberAffiliationService.add(add);
		assertEquals(event.getId().getMemberId().longValue(), 5L);
		assertEquals(event.getId().getAffiliationId().longValue(), 4L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberAffiliation.sql" })
	public void deleteByIdTest() {
		relpMemberAffiliationService.deleteById(1L, 5L);
		assertEquals(this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_member_affiliation/find_all", List.class).size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberAffiliation.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<RelpMemberAffiliation> list = this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_member_affiliation/find_all", List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberAffiliation.sql" })
	public void findByIdTest1() {
		RelpMemberAffiliation relation = relpMemberAffiliationService.findById(10L, 9L);
		assertEquals(relation, null);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberAffiliation.sql" })
	public void findByIdTest2() {
		RelpMemberAffiliation event = this.restTemplate.getForObject(
				"http://localhost:" + port + "/relp_member_affiliation/find_by_id?memberId=1&affiliationId=5",
				RelpMemberAffiliation.class);
		assertEquals(event.getId().getMemberId().longValue(), 1L);
		assertEquals(event.getId().getAffiliationId().longValue(), 5L);
	}
}
