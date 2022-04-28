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
import com.uottawa.project.entity.RelpMemberPartnershipScope2;
import com.uottawa.project.entity.RelpMemberPartnershipScope2PK;
import com.uottawa.project.service.RelpMemberPartnershipScope2Service;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RelpMemberPartnershipScope2Tests {

	@LocalServerPort
	private int port;

	@Autowired
	private RelpMemberPartnershipScope2Service relpMemberPartnershipScope2Service;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipScope2.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipScope2.sql" })
	public void addTest() {
		RelpMemberPartnershipScope2PK pk = new RelpMemberPartnershipScope2PK();
		pk.setMemberId(5L);
		pk.setScopeId(4L);
		RelpMemberPartnershipScope2 add = new RelpMemberPartnershipScope2();
		add.setId(pk);
		RelpMemberPartnershipScope2 event = relpMemberPartnershipScope2Service.add(add);
		assertEquals(event.getId().getMemberId().longValue(), 5L);
		assertEquals(event.getId().getScopeId().longValue(), 4L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipScope2.sql" })
	public void deleteByIdTest() {
		relpMemberPartnershipScope2Service.deleteById(2L, 4L);
		assertEquals(this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_member_partnership_scope2/find_all", List.class)
				.size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipScope2.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<RelpMemberPartnershipScope2> list = this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_member_partnership_scope2/find_all", List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipScope2.sql" })
	public void findByIdTest1() {
		RelpMemberPartnershipScope2 relation = relpMemberPartnershipScope2Service.findById(10L, 9L);
		assertEquals(relation, null);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipScope2.sql" })
	public void findByIdTest2() {
		RelpMemberPartnershipScope2 event = this.restTemplate.getForObject(
				"http://localhost:" + port + "/relp_member_partnership_scope2/find_by_id?memberId=2&scopeId=4",
				RelpMemberPartnershipScope2.class);
		assertEquals(event.getId().getMemberId().longValue(), 2L);
		assertEquals(event.getId().getScopeId().longValue(), 4L);
	}
}
