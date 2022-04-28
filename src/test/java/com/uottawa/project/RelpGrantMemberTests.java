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

import com.uottawa.project.entity.RelpGrantMember;
import com.uottawa.project.entity.RelpGrantMemberPK;
import com.uottawa.project.service.RelpGrantMemberService;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RelpGrantMemberTests {

	@LocalServerPort
	private int port;

	@Autowired
	private RelpGrantMemberService relpGrantMemberService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-relpGrantMember.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpGrantMember.sql" })
	public void addTest() {
		RelpGrantMemberPK pk = new RelpGrantMemberPK();
		pk.setGrantId(5L);
		pk.setMemberId(4L);
		RelpGrantMember add = new RelpGrantMember();
		add.setId(pk);
		RelpGrantMember event = relpGrantMemberService.add(add);
		assertEquals(event.getId().getGrantId().longValue(), 5L);
		assertEquals(event.getId().getMemberId().longValue(), 4L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpGrantMember.sql" })
	public void deleteByIdTest() {
		relpGrantMemberService.deleteById(2L, 1L);
		assertEquals(this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_grant_member/find_all", List.class).size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpGrantMember.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<RelpGrantMember> list = this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_grant_member/find_all", List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpGrantMember.sql" })
	public void findByIdTest1() {
		RelpGrantMember relation = relpGrantMemberService.findById(10L, 9L);
		assertEquals(relation, null);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpGrantMember.sql" })
	public void findByIdTest2() {
		RelpGrantMember event = this.restTemplate.getForObject(
				"http://localhost:" + port + "/relp_grant_member/find_by_id?memberId=1&grantId=2",
				RelpGrantMember.class);
		assertEquals(event.getId().getMemberId().longValue(), 1L);
		assertEquals(event.getId().getGrantId().longValue(), 2L);
	}
}
