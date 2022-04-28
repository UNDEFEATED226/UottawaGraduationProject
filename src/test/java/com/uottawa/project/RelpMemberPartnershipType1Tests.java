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
import com.uottawa.project.entity.RelpMemberPartnershipType1;
import com.uottawa.project.entity.RelpMemberPartnershipType1PK;
import com.uottawa.project.service.RelpMemberPartnershipType1Service;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RelpMemberPartnershipType1Tests {

	@LocalServerPort
	private int port;

	@Autowired
	private RelpMemberPartnershipType1Service RelpMemberPartnershipType1Service;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipType1.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipType1.sql" })
	public void addTest() {
		RelpMemberPartnershipType1PK pk = new RelpMemberPartnershipType1PK();
		pk.setMemberId(5L);
		pk.setTypeId(4L);
		RelpMemberPartnershipType1 add = new RelpMemberPartnershipType1();
		add.setId(pk);
		RelpMemberPartnershipType1 event = RelpMemberPartnershipType1Service.add(add);
		assertEquals(event.getId().getMemberId().longValue(), 5L);
		assertEquals(event.getId().getTypeId().longValue(), 4L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipType1.sql" })
	public void deleteByIdTest() {
		RelpMemberPartnershipType1Service.deleteById(1L, 5L);
		assertEquals(this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_member_partnership_type1/find_all", List.class)
				.size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipType1.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<RelpMemberPartnershipType1> list = this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_member_partnership_type1/find_all", List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipType1.sql" })
	public void findByIdTest1() {
		RelpMemberPartnershipType1 relation = RelpMemberPartnershipType1Service.findById(10L, 9L);
		assertEquals(relation, null);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipType1.sql" })
	public void findByIdTest2() {
		RelpMemberPartnershipType1 event = this.restTemplate.getForObject(
				"http://localhost:" + port + "/relp_member_partnership_type1/find_by_id?memberId=1&typeId=5",
				RelpMemberPartnershipType1.class);
		assertEquals(event.getId().getMemberId().longValue(), 1L);
		assertEquals(event.getId().getTypeId().longValue(), 5L);
	}
}
