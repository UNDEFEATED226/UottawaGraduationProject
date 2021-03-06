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

import com.uottawa.project.entity.TypesGrantSource;
import com.uottawa.project.service.TypesGrantSourceService;


@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TypesGrantSourceTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TypesGrantSourceService typesGrantSourceService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-typesGrantSource.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesGrantSource.sql" })
	public void addTest() {
		TypesGrantSource product = typesGrantSourceService.add(new TypesGrantSource());
		assertEquals(product.getId().longValue(), 3L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesGrantSource.sql" })
	public void deleteByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesGrantSourceService.deleteById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesGrantSource.sql" })
	public void deleteByIdTest2() {
		typesGrantSourceService.deleteById(1L);
		assertEquals(this.restTemplate.getForObject("http://localhost:" + port + "/types_grant_source/find_all", List.class)
				.size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesGrantSource.sql" })
	public void updateTest() {
		TypesGrantSource product = this.restTemplate
				.getForObject("http://localhost:" + port + "/types_grant_source/find_by_id?id=1", TypesGrantSource.class);
		product.setTypeEn("test updating");
		assertEquals(product.getTypeEn(), new String("test updating"));
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesGrantSource.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<TypesGrantSource> list = this.restTemplate.getForObject("http://localhost:" + port + "/types_grant_source/find_all",
				List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesGrantSource.sql" })
	public void findByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesGrantSourceService.findById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesGrantSource.sql" })
	public void findByIdTest2() {
		TypesGrantSource product = this.restTemplate
				.getForObject("http://localhost:" + port + "/types_grant_source/find_by_id?id=1", TypesGrantSource.class);
		assertEquals(product.getId().longValue(), 1L);
	}
}
