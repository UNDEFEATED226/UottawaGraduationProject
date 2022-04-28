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
import com.uottawa.project.entity.RelpMemberPartnershipScope3;
import com.uottawa.project.entity.RelpMemberPartnershipScope3PK;
import com.uottawa.project.service.RelpMemberPartnershipScope3Service;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RelpMemberPartnershipScope3Tests {

	@LocalServerPort
	private int port;

	@Autowired
	private RelpMemberPartnershipScope3Service relpMemberPartnershipScope3Service;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipScope3.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipScope3.sql" })
	public void addTest() {
		RelpMemberPartnershipScope3PK pk = new RelpMemberPartnershipScope3PK();
		pk.setMemberId(5L);
		pk.setScopeId(4L);
		RelpMemberPartnershipScope3 add = new RelpMemberPartnershipScope3();
		add.setId(pk);
		RelpMemberPartnershipScope3 event = relpMemberPartnershipScope3Service.add(add);
		assertEquals(event.getId().getMemberId().longValue(), 5L);
		assertEquals(event.getId().getScopeId().longValue(), 4L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipScope3.sql" })
	public void deleteByIdTest() {
		relpMemberPartnershipScope3Service.deleteById(2L, 4L);
		assertEquals(this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_member_partnership_scope3/find_all", List.class)
				.size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipScope3.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<RelpMemberPartnershipScope3> list = this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_member_partnership_scope3/find_all", List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipScope3.sql" })
	public void findByIdTest1() {
		RelpMemberPartnershipScope3 relation = relpMemberPartnershipScope3Service.findById(10L, 9L);
		assertEquals(relation, null);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipScope3.sql" })
	public void findByIdTest2() {
		RelpMemberPartnershipScope3 event = this.restTemplate.getForObject(
				"http://localhost:" + port + "/relp_member_partnership_scope3/find_by_id?memberId=2&scopeId=4",
				RelpMemberPartnershipScope3.class);
		assertEquals(event.getId().getMemberId().longValue(), 2L);
		assertEquals(event.getId().getScopeId().longValue(), 4L);
	}
}
