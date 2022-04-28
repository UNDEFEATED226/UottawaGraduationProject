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
import com.uottawa.project.entity.RelpMemberPartnershipType2;
import com.uottawa.project.entity.RelpMemberPartnershipType2PK;
import com.uottawa.project.service.RelpMemberPartnershipType2Service;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RelpMemberPartnershipType2Tests {

	@LocalServerPort
	private int port;

	@Autowired
	private RelpMemberPartnershipType2Service RelpMemberPartnershipType2Service;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipType2.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipType2.sql" })
	public void addTest() {
		RelpMemberPartnershipType2PK pk = new RelpMemberPartnershipType2PK();
		pk.setMemberId(5L);
		pk.setTypeId(4L);
		RelpMemberPartnershipType2 add = new RelpMemberPartnershipType2();
		add.setId(pk);
		RelpMemberPartnershipType2 event = RelpMemberPartnershipType2Service.add(add);
		assertEquals(event.getId().getMemberId().longValue(), 5L);
		assertEquals(event.getId().getTypeId().longValue(), 4L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipType2.sql" })
	public void deleteByIdTest() {
		RelpMemberPartnershipType2Service.deleteById(1L, 5L);
		assertEquals(this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_member_partnership_type2/find_all", List.class)
				.size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipType2.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<RelpMemberPartnershipType2> list = this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_member_partnership_type2/find_all", List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipType2.sql" })
	public void findByIdTest1() {
		RelpMemberPartnershipType2 relation = RelpMemberPartnershipType2Service.findById(20L, 9L);
		assertEquals(relation, null);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipType2.sql" })
	public void findByIdTest2() {
		RelpMemberPartnershipType2 event = this.restTemplate.getForObject(
				"http://localhost:" + port + "/relp_member_partnership_type2/find_by_id?memberId=1&typeId=5",
				RelpMemberPartnershipType2.class);
		assertEquals(event.getId().getMemberId().longValue(), 1L);
		assertEquals(event.getId().getTypeId().longValue(), 5L);
	}
}
