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
import com.uottawa.project.entity.RelpMemberPartnershipScope1;
import com.uottawa.project.entity.RelpMemberPartnershipScope1PK;
import com.uottawa.project.service.RelpMemberPartnershipScope1Service;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RelpMemberPartnershipScope1Tests {

	@LocalServerPort
	private int port;

	@Autowired
	private RelpMemberPartnershipScope1Service relpMemberPartnershipScope1Service;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipScope1.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipScope1.sql" })
	public void addTest() {
		RelpMemberPartnershipScope1PK pk = new RelpMemberPartnershipScope1PK();
		pk.setMemberId(5L);
		pk.setScopeId(4L);
		RelpMemberPartnershipScope1 add = new RelpMemberPartnershipScope1();
		add.setId(pk);
		RelpMemberPartnershipScope1 event = relpMemberPartnershipScope1Service.add(add);
		assertEquals(event.getId().getMemberId().longValue(), 5L);
		assertEquals(event.getId().getScopeId().longValue(), 4L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipScope1.sql" })
	public void deleteByIdTest() {
		relpMemberPartnershipScope1Service.deleteById(2L, 4L);
		assertEquals(this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_member_partnership_scope1/find_all", List.class).size(),
				1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipScope1.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<RelpMemberPartnershipScope1> list = this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_member_partnership_scope1/find_all", List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipScope1.sql" })
	public void findByIdTest1() {
		RelpMemberPartnershipScope1 relation = relpMemberPartnershipScope1Service.findById(10L, 9L);
		assertEquals(relation, null);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipScope1.sql" })
	public void findByIdTest2() {
		RelpMemberPartnershipScope1 event = this.restTemplate.getForObject(
				"http://localhost:" + port + "/relp_member_partnership_scope1/find_by_id?memberId=2&scopeId=4",
				RelpMemberPartnershipScope1.class);
		assertEquals(event.getId().getMemberId().longValue(), 2L);
		assertEquals(event.getId().getScopeId().longValue(), 4L);
	}
}
