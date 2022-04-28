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
import com.uottawa.project.entity.RelpMemberStrategicDirection;
import com.uottawa.project.entity.RelpMemberStrategicDirectionPK;
import com.uottawa.project.service.RelpMemberStrategicDirectionService;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RelpMemberStrategicDirectionTests {

	@LocalServerPort
	private int port;

	@Autowired
	private RelpMemberStrategicDirectionService RelpMemberStrategicDirectionService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberStrategicDirection.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberStrategicDirection.sql" })
	public void addTest() {
		RelpMemberStrategicDirectionPK pk = new RelpMemberStrategicDirectionPK();
		pk.setMemberId(5L);
		pk.setDirectionId(4L);
		RelpMemberStrategicDirection add = new RelpMemberStrategicDirection();
		add.setId(pk);
		RelpMemberStrategicDirection event = RelpMemberStrategicDirectionService.add(add);
		assertEquals(event.getId().getMemberId().longValue(), 5L);
		assertEquals(event.getId().getDirectionId().longValue(), 4L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberStrategicDirection.sql" })
	public void deleteByIdTest() {
		RelpMemberStrategicDirectionService.deleteById(1L, 2L);
		assertEquals(this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_member_strategic_direction/find_all", List.class)
				.size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberStrategicDirection.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<RelpMemberStrategicDirection> list = this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_member_strategic_direction/find_all", List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberStrategicDirection.sql" })
	public void findByIdTest1() {
		RelpMemberStrategicDirection relation = RelpMemberStrategicDirectionService.findById(20L, 9L);
		assertEquals(relation, null);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberStrategicDirection.sql" })
	public void findByIdTest2() {
		RelpMemberStrategicDirection event = this.restTemplate.getForObject(
				"http://localhost:" + port + "/relp_member_strategic_direction/find_by_id?memberId=1&directionId=2",
				RelpMemberStrategicDirection.class);
		assertEquals(event.getId().getMemberId().longValue(), 1L);
		assertEquals(event.getId().getDirectionId().longValue(), 2L);
	}
}
