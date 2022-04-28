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

import com.uottawa.project.entity.RelpEventProduct;
import com.uottawa.project.entity.RelpEventProductPK;
import com.uottawa.project.service.RelpEventProductService;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RelpEventProductTests {

	@LocalServerPort
	private int port;

	@Autowired
	private RelpEventProductService RelpEventProductService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventProduct.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventProduct.sql" })
	public void addTest() {
		RelpEventProductPK pk = new RelpEventProductPK();
		pk.setEventId(5L);
		pk.setProductId(4L);
		RelpEventProduct add = new RelpEventProduct();
		add.setId(pk);
		RelpEventProduct event = RelpEventProductService.add(add);
		assertEquals(event.getId().getEventId().longValue(), 5L);
		assertEquals(event.getId().getProductId().longValue(), 4L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventProduct.sql" })
	public void deleteByIdTest() {
		RelpEventProductService.deleteById(3L, 2L);
		assertEquals(this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_event_product/find_all", List.class).size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventProduct.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<RelpEventProduct> list = this.restTemplate
				.getForObject("http://localhost:" + port + "/relp_event_product/find_all", List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventProduct.sql" })
	public void findByIdTest1() {
		RelpEventProduct relation = RelpEventProductService.findById(10L, 9L);
		assertEquals(relation, null);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-relpEventProduct.sql" })
	public void findByIdTest2() {
		RelpEventProduct event = this.restTemplate.getForObject(
				"http://localhost:" + port + "/relp_event_product/find_by_id?eventId=3&productId=2",
				RelpEventProduct.class);
		assertEquals(event.getId().getEventId().longValue(), 3L);
		assertEquals(event.getId().getProductId().longValue(), 2L);
	}
}
