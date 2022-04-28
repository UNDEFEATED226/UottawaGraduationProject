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
import com.uottawa.project.entity.RelpGrantMemberInvestigator;
import com.uottawa.project.entity.RelpGrantMemberInvestigatorPK;
import com.uottawa.project.service.RelpGrantMemberInvestigatorService;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RelpGrantMemberInvestigatorTests {

	@LocalServerPort
	private int port;

	@Autowired
	private RelpGrantMemberInvestigatorService relpGrantMemberInvestigatorService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-relpGrantMemberInvestigator.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpGrantMemberInvestigator.sql" })
	public void addTest() {
		RelpGrantMemberInvestigatorPK pk = new RelpGrantMemberInvestigatorPK();
		pk.setGrantId(5L);
		pk.setMemberId(4L);
		RelpGrantMemberInvestigator add = new RelpGrantMemberInvestigator();
		add.setId(pk);
		RelpGrantMemberInvestigator event = relpGrantMemberInvestigatorService.add(add);
		assertEquals(event.getId().getGrantId().longValue(), 5L);
		assertEquals(event.getId().getMemberId().longValue(), 4L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpGrantMemberInvestigator.sql" })
	public void deleteByIdTest() {
		relpGrantMemberInvestigatorService.deleteById(1L, 14L);
		assertEquals(this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_grant_member_investigator/find_all", List.class).size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpGrantMemberInvestigator.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<RelpGrantMemberInvestigator> list = this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_grant_member_investigator/find_all", List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpGrantMemberInvestigator.sql" })
	public void findByIdTest1() {
		RelpGrantMemberInvestigator relation = relpGrantMemberInvestigatorService.findById(10L, 9L);
		assertEquals(relation, null);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpGrantMemberInvestigator.sql" })
	public void findByIdTest2() {
		RelpGrantMemberInvestigator event = this.restTemplate.getForObject(
				"http://localhost:" + port + "/relp_grant_member_investigator/find_by_id?memberId=14&grantId=1",
				RelpGrantMemberInvestigator.class);
		assertEquals(event.getId().getGrantId().longValue(), 1L);
		assertEquals(event.getId().getMemberId().longValue(), 14L);
	}
}
