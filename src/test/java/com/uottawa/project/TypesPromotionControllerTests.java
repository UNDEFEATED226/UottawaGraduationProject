package com.uottawa.project;

import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;
import com.uottawa.project.entity.TypesPromotion;
import com.uottawa.project.service.TypesPromotionService;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TypesPromotionControllerTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TypesPromotionService typesPromotionService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-typesPromotion.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesPromotion.sql" })
	public void addTest() {
		TypesPromotion promotion = typesPromotionService.add(new TypesPromotion());
		assertEquals(promotion.getId().longValue(), 3L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesPromotion.sql" })
	public void deleteByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesPromotionService.deleteById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesPromotion.sql" })
	public void deleteByIdTest2() {
		typesPromotionService.deleteById(1L);
		assertEquals(this.restTemplate
				.getForObject("http://localhost:" + port + "/types_promotion/find_all", List.class).size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesPromotion.sql" })
	public void updateTest() {
		TypesPromotion promotion = this.restTemplate
				.getForObject("http://localhost:" + port + "/types_promotion/find_by_id?id=1", TypesPromotion.class);
		promotion.setNameEn("test updating");
		assertEquals(promotion.getNameEn(), new String("test updating"));
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesPromotion.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<TypesPromotion> list = this.restTemplate.getForObject("http://localhost:" + port + "/types_promotion/find_all",
				List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesPromotion.sql" })
	public void findByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesPromotionService.findById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesPromotion.sql" })
	public void findByIdTest2() {
		TypesPromotion promotion = this.restTemplate
				.getForObject("http://localhost:" + port + "/types_promotion/find_by_id?id=1", TypesPromotion.class);
		assertEquals(promotion.getId().longValue(), 1L);
	}
}
