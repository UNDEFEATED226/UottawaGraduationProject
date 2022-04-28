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
import com.uottawa.project.entity.RelpMemberFuturePromotion;
import com.uottawa.project.entity.RelpMemberFuturePromotionPK;
import com.uottawa.project.service.RelpMemberFuturePromotionService;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RelpMemberFuturePromotionTests {

	@LocalServerPort
	private int port;

	@Autowired
	private RelpMemberFuturePromotionService relpMemberFuturePromotionService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-RelpMemberFuturePromotion.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-RelpMemberFuturePromotion.sql" })
	public void addTest() {
		RelpMemberFuturePromotionPK pk = new RelpMemberFuturePromotionPK();
		pk.setMemberId(5L);
		pk.setPromotionId(4L);
		RelpMemberFuturePromotion add = new RelpMemberFuturePromotion();
		add.setId(pk);
		RelpMemberFuturePromotion event = relpMemberFuturePromotionService.add(add);
		assertEquals(event.getId().getMemberId().longValue(), 5L);
		assertEquals(event.getId().getPromotionId().longValue(), 4L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-RelpMemberFuturePromotion.sql" })
	public void deleteByIdTest() {
		relpMemberFuturePromotionService.deleteById(1L, 8L);
		assertEquals(this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_member_future_promotion/find_all", List.class).size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-RelpMemberFuturePromotion.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<RelpMemberFuturePromotion> list = this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_member_future_promotion/find_all", List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-RelpMemberFuturePromotion.sql" })
	public void findByIdTest1() {
		RelpMemberFuturePromotion relation = relpMemberFuturePromotionService.findById(10L, 9L);
		assertEquals(relation, null);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-RelpMemberFuturePromotion.sql" })
	public void findByIdTest2() {
		RelpMemberFuturePromotion event = this.restTemplate.getForObject(
				"http://localhost:" + port + "/relp_member_future_promotion/find_by_id?memberId=1&promotionId=8",
				RelpMemberFuturePromotion.class);
		assertEquals(event.getId().getMemberId().longValue(), 1L);
		assertEquals(event.getId().getPromotionId().longValue(),8L);
	}
}
