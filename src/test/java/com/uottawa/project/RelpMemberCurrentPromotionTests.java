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
import com.uottawa.project.entity.RelpMemberCurrentPromotion;
import com.uottawa.project.entity.RelpMemberCurrentPromotionPK;
import com.uottawa.project.service.RelpMemberCurrentPromotionService;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RelpMemberCurrentPromotionTests {

	@LocalServerPort
	private int port;

	@Autowired
	private RelpMemberCurrentPromotionService relpMemberCurrentPromotionService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberCurrentPromotion.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberCurrentPromotion.sql" })
	public void addTest() {
		RelpMemberCurrentPromotionPK pk = new RelpMemberCurrentPromotionPK();
		pk.setMemberId(5L);
		pk.setPromotionId(4L);
		RelpMemberCurrentPromotion add = new RelpMemberCurrentPromotion();
		add.setId(pk);
		RelpMemberCurrentPromotion event = relpMemberCurrentPromotionService.add(add);
		assertEquals(event.getId().getMemberId().longValue(), 5L);
		assertEquals(event.getId().getPromotionId().longValue(), 4L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberCurrentPromotion.sql" })
	public void deleteByIdTest() {
		relpMemberCurrentPromotionService.deleteById(2L, 2L);
		assertEquals(this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_member_current_promotion/find_all", List.class).size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberCurrentPromotion.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<RelpMemberCurrentPromotion> list = this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_member_current_promotion/find_all", List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberCurrentPromotion.sql" })
	public void findByIdTest1() {
		RelpMemberCurrentPromotion relation = relpMemberCurrentPromotionService.findById(10L, 9L);
		assertEquals(relation, null);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpMemberCurrentPromotion.sql" })
	public void findByIdTest2() {
		RelpMemberCurrentPromotion event = this.restTemplate.getForObject(
				"http://localhost:" + port + "/relp_member_current_promotion/find_by_id?memberId=2&promotionId=2",
				RelpMemberCurrentPromotion.class);
		assertEquals(event.getId().getMemberId().longValue(), 2L);
		assertEquals(event.getId().getPromotionId().longValue(), 2L);
	}
}
