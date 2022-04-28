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
import com.uottawa.project.entity.RelpMemberPartnershipScopeFuture;
import com.uottawa.project.entity.RelpMemberPartnershipScopeFuturePK;
import com.uottawa.project.service.RelpMemberPartnershipScopeFutureService;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RelpMemberPartnershipScopeFutureTests {

	@LocalServerPort
	private int port;

	@Autowired
	private RelpMemberPartnershipScopeFutureService relpMemberPartnershipScopeFutureService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipScopeFuture.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipScopeFuture.sql" })
	public void addTest() {
		RelpMemberPartnershipScopeFuturePK pk = new RelpMemberPartnershipScopeFuturePK();
		pk.setMemberId(5L);
		pk.setScopeId(4L);
		RelpMemberPartnershipScopeFuture add = new RelpMemberPartnershipScopeFuture();
		add.setId(pk);
		RelpMemberPartnershipScopeFuture event = relpMemberPartnershipScopeFutureService.add(add);
		assertEquals(event.getId().getMemberId().longValue(), 5L);
		assertEquals(event.getId().getScopeId().longValue(), 4L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipScopeFuture.sql" })
	public void deleteByIdTest() {
		relpMemberPartnershipScopeFutureService.deleteById(1L, 5L);
		assertEquals(this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_member_partnership_scope_future/find_all", List.class)
				.size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipScopeFuture.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<RelpMemberPartnershipScopeFuture> list = this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_member_partnership_scope_future/find_all", List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipScopeFuture.sql" })
	public void findByIdTest1() {
		RelpMemberPartnershipScopeFuture relation = relpMemberPartnershipScopeFutureService.findById(10L, 9L);
		assertEquals(relation, null);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipScopeFuture.sql" })
	public void findByIdTest2() {
		RelpMemberPartnershipScopeFuture event = this.restTemplate.getForObject(
				"http://localhost:" + port + "/relp_member_partnership_scope_future/find_by_id?memberId=1&scopeId=5",
				RelpMemberPartnershipScopeFuture.class);
		assertEquals(event.getId().getMemberId().longValue(), 1L);
		assertEquals(event.getId().getScopeId().longValue(), 5L);
	}
}
