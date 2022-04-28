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

import com.uottawa.project.entity.TypesMemberCat;
import com.uottawa.project.service.TypesMemberCatService;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TypesMemberCatTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TypesMemberCatService typesMemberCatService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-typesMemberCat.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesMemberCat.sql" })
	public void addTest() {
		TypesMemberCat product = typesMemberCatService.add(new TypesMemberCat());
		assertEquals(product.getId().longValue(), 3L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesMemberCat.sql" })
	public void deleteByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesMemberCatService.deleteById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesMemberCat.sql" })
	public void deleteByIdTest2() {
		typesMemberCatService.deleteById(1L);
		assertEquals(this.restTemplate.getForObject("http://localhost:" + port + "/types_member_cat/find_all", List.class)
				.size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesMemberCat.sql" })
	public void updateTest() {
		TypesMemberCat product = this.restTemplate
				.getForObject("http://localhost:" + port + "/types_member_cat/find_by_id?id=1", TypesMemberCat.class);
		product.setCatEn("test updating");
		assertEquals(product.getCatEn(), new String("test updating"));
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesMemberCat.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<TypesMemberCat> list = this.restTemplate.getForObject("http://localhost:" + port + "/types_member_cat/find_all",
				List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesMemberCat.sql" })
	public void findByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesMemberCatService.findById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesMemberCat.sql" })
	public void findByIdTest2() {
		TypesMemberCat product = this.restTemplate
				.getForObject("http://localhost:" + port + "/types_member_cat/find_by_id?id=1", TypesMemberCat.class);
		assertEquals(product.getId().longValue(), 1L);
	}
}
