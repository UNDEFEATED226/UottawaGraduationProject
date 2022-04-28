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
import com.uottawa.project.entity.RelpMemberPartnershipType3;
import com.uottawa.project.entity.RelpMemberPartnershipType3PK;
import com.uottawa.project.service.RelpMemberPartnershipType3Service;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RelpMemberPartnershipType3Tests {

	@LocalServerPort
	private int port;

	@Autowired
	private RelpMemberPartnershipType3Service RelpMemberPartnershipType3Service;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipType3.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipType3.sql" })
	public void addTest() {
		RelpMemberPartnershipType3PK pk = new RelpMemberPartnershipType3PK();
		pk.setMemberId(5L);
		pk.setTypeId(4L);
		RelpMemberPartnershipType3 add = new RelpMemberPartnershipType3();
		add.setId(pk);
		RelpMemberPartnershipType3 event = RelpMemberPartnershipType3Service.add(add);
		assertEquals(event.getId().getMemberId().longValue(), 5L);
		assertEquals(event.getId().getTypeId().longValue(), 4L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipType3.sql" })
	public void deleteByIdTest() {
		RelpMemberPartnershipType3Service.deleteById(1L, 5L);
		assertEquals(this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_member_partnership_type3/find_all", List.class)
				.size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipType3.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<RelpMemberPartnershipType3> list = this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_member_partnership_type3/find_all", List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipType3.sql" })
	public void findByIdTest1() {
		RelpMemberPartnershipType3 relation = RelpMemberPartnershipType3Service.findById(20L, 9L);
		assertEquals(relation, null);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberPartnershipType3.sql" })
	public void findByIdTest2() {
		RelpMemberPartnershipType3 event = this.restTemplate.getForObject(
				"http://localhost:" + port + "/relp_member_partnership_type3/find_by_id?memberId=1&typeId=5",
				RelpMemberPartnershipType3.class);
		assertEquals(event.getId().getMemberId().longValue(), 1L);
		assertEquals(event.getId().getTypeId().longValue(), 5L);
	}
}
