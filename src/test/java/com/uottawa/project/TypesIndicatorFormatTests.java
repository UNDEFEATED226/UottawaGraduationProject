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

import com.uottawa.project.entity.TypesIndicatorFormat;
import com.uottawa.project.service.TypesIndicatorFormatService;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TypesIndicatorFormatTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TypesIndicatorFormatService typesIndicatorFormatService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-typesIndicatorFormat.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesIndicatorFormat.sql" })
	public void addTest() {
		TypesIndicatorFormat product = typesIndicatorFormatService.add(new TypesIndicatorFormat());
		assertEquals(product.getId().longValue(), 3L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesIndicatorFormat.sql" })
	public void deleteByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesIndicatorFormatService.deleteById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesIndicatorFormat.sql" })
	public void deleteByIdTest2() {
		typesIndicatorFormatService.deleteById(1L);
		assertEquals(this.restTemplate
				.getForObject("http://localhost:" + port + "/types_grant_status/find_all", List.class).size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesIndicatorFormat.sql" })
	public void updateTest() {
		TypesIndicatorFormat product = this.restTemplate.getForObject(
				"http://localhost:" + port + "/types_grant_status/find_by_id?id=1", TypesIndicatorFormat.class);
		product.setName("test updating");
		assertEquals(product.getName(), new String("test updating"));
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesIndicatorFormat.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<TypesIndicatorFormat> list = this.restTemplate
				.getForObject("http://localhost:" + port + "/types_grant_status/find_all", List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesIndicatorFormat.sql" })
	public void findByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesIndicatorFormatService.findById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesIndicatorFormat.sql" })
	public void findByIdTest2() {
		TypesIndicatorFormat product = this.restTemplate.getForObject(
				"http://localhost:" + port + "/types_grant_status/find_by_id?id=1", TypesIndicatorFormat.class);
		assertEquals(product.getId().longValue(), 1L);
	}
}
