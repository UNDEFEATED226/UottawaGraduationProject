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
import com.uottawa.project.entity.RelpMemberIndicatorFormat;
import com.uottawa.project.entity.RelpMemberIndicatorFormatPK;
import com.uottawa.project.service.RelpMemberIndicatorFormatService;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RelpMemberIndicatorFormatTests {

	@LocalServerPort
	private int port;

	@Autowired
	private RelpMemberIndicatorFormatService relpMemberIndicatorFormatService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-RelpMemberIndicatorFormat.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-RelpMemberIndicatorFormat.sql" })
	public void addTest() {
		RelpMemberIndicatorFormatPK pk = new RelpMemberIndicatorFormatPK();
		pk.setMemberId(5L);
		pk.setFormatId(4L);
		RelpMemberIndicatorFormat add = new RelpMemberIndicatorFormat();
		add.setId(pk);
		RelpMemberIndicatorFormat event = relpMemberIndicatorFormatService.add(add);
		assertEquals(event.getId().getMemberId().longValue(), 5L);
		assertEquals(event.getId().getFormatId().longValue(), 4L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-RelpMemberIndicatorFormat.sql" })
	public void deleteByIdTest() {
		relpMemberIndicatorFormatService.deleteById(1L, 2L);
		assertEquals(this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_member_indicator_format/find_all", List.class).size(),
				1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-RelpMemberIndicatorFormat.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<RelpMemberIndicatorFormat> list = this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_member_indicator_format/find_all", List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-RelpMemberIndicatorFormat.sql" })
	public void findByIdTest1() {
		RelpMemberIndicatorFormat relation = relpMemberIndicatorFormatService.findById(10L, 9L);
		assertEquals(relation, null);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-RelpMemberIndicatorFormat.sql" })
	public void findByIdTest2() {
		RelpMemberIndicatorFormat event = this.restTemplate.getForObject(
				"http://localhost:" + port + "/relp_member_indicator_format/find_by_id?memberId=1&promotionId=2",
				RelpMemberIndicatorFormat.class);
		assertEquals(event.getId().getMemberId().longValue(), 1L);
		assertEquals(event.getId().getFormatId().longValue(), 2L);
	}
}
