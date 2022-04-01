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

import com.uottawa.project.entity.TypesProduct;
import com.uottawa.project.service.TypesProductService;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TypesProductTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TypesProductService typesProductService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-typesProduct.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesProduct.sql" })
	public void addTest() {
		TypesProduct product = typesProductService.add(new TypesProduct());
		assertEquals(product.getId().longValue(), 3L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesProduct.sql" })
	public void deleteByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesProductService.deleteById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesProduct.sql" })
	public void deleteByIdTest2() {
		typesProductService.deleteById(1L);
		assertEquals(this.restTemplate.getForObject("http://localhost:" + port + "/types_product/find_all", List.class)
				.size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesProduct.sql" })
	public void updateTest() {
		TypesProduct product = this.restTemplate
				.getForObject("http://localhost:" + port + "/types_product/find_by_id?id=1", TypesProduct.class);
		product.setTypeEn("test updating");
		assertEquals(product.getTypeEn(), new String("test updating"));
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesProduct.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<TypesProduct> list = this.restTemplate.getForObject("http://localhost:" + port + "/types_product/find_all",
				List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesProduct.sql" })
	public void findByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesProductService.findById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesProduct.sql" })
	public void findByIdTest2() {
		TypesProduct product = this.restTemplate
				.getForObject("http://localhost:" + port + "/types_product/find_by_id?id=1", TypesProduct.class);
		assertEquals(product.getId().longValue(), 1L);
	}
}
