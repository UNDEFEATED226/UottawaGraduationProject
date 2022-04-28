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

import com.uottawa.project.entity.RelpEventMember;
import com.uottawa.project.entity.RelpEventMemberPK;
import com.uottawa.project.service.RelpEventMemberService;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RelpEventMemberTests {

	@LocalServerPort
	private int port;

	@Autowired
	private RelpEventMemberService relpEventMemberService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventMember.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventMember.sql" })
	public void addTest() {
		RelpEventMemberPK pk = new RelpEventMemberPK();
		pk.setEventId(5L);
		pk.setMemberId(4L);
		RelpEventMember add = new RelpEventMember();
		add.setId(pk);
		RelpEventMember event = relpEventMemberService.add(add);
		assertEquals(event.getId().getEventId().longValue(), 5L);
		assertEquals(event.getId().getMemberId().longValue(), 4L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventMember.sql" })
	public void deleteByIdTest() {
		relpEventMemberService.deleteById(1L, 14L);
		assertEquals(this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_event_member/find_all", List.class).size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventMember.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<RelpEventMember> list = this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_event_member/find_all", List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventMember.sql" })
	public void findByIdTest1() {
		RelpEventMember relation = relpEventMemberService.findById(10L, 9L);
		assertEquals(relation, null);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventMember.sql" })
	public void findByIdTest2() {
		RelpEventMember event = this.restTemplate.getForObject(
				"http://localhost:" + port + "/relp_event_member/find_by_id?eventId=1&memberId=14",
				RelpEventMember.class);
		assertEquals(event.getId().getEventId().longValue(), 1L);
		assertEquals(event.getId().getMemberId().longValue(), 14L);
	}
}
