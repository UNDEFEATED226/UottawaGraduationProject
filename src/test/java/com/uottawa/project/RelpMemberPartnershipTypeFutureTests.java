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
import com.uottawa.project.entity.RelpMemberPartnershipTypeFuture;
import com.uottawa.project.entity.RelpMemberPartnershipTypeFuturePK;
import com.uottawa.project.service.RelpMemberPartnershipTypeFutureService;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RelpMemberPartnershipTypeFutureTests {

	@LocalServerPort
	private int port;

	@Autowired
	private RelpMemberPartnershipTypeFutureService RelpMemberPartnershipTypeFutureService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipTypeFuture.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipTypeFuture.sql" })
	public void addTest() {
		RelpMemberPartnershipTypeFuturePK pk = new RelpMemberPartnershipTypeFuturePK();
		pk.setMemberId(5L);
		pk.setTypeId(4L);
		RelpMemberPartnershipTypeFuture add = new RelpMemberPartnershipTypeFuture();
		add.setId(pk);
		RelpMemberPartnershipTypeFuture event = RelpMemberPartnershipTypeFutureService.add(add);
		assertEquals(event.getId().getMemberId().longValue(), 5L);
		assertEquals(event.getId().getTypeId().longValue(), 4L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipTypeFuture.sql" })
	public void deleteByIdTest() {
		RelpMemberPartnershipTypeFutureService.deleteById(2L, 5L);
		assertEquals(this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_member_partnership_type_future/find_all", List.class)
				.size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipTypeFuture.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<RelpMemberPartnershipTypeFuture> list = this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_member_partnership_type_future/find_all", List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipTypeFuture.sql" })
	public void findByIdTest1() {
		RelpMemberPartnershipTypeFuture relation = RelpMemberPartnershipTypeFutureService.findById(20L, 9L);
		assertEquals(relation, null);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipTypeFuture.sql" })
	public void findByIdTest2() {
		RelpMemberPartnershipTypeFuture event = this.restTemplate.getForObject(
				"http://localhost:" + port + "/relp_member_partnership_type_future/find_by_id?memberId=2&typeId=5",
				RelpMemberPartnershipTypeFuture.class);
		assertEquals(event.getId().getMemberId().longValue(), 2L);
		assertEquals(event.getId().getTypeId().longValue(), 5L);
	}
}
