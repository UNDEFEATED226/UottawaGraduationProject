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

import com.uottawa.project.entity.TypesGrantStatus;
import com.uottawa.project.service.TypesGrantStatusService;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TypesGrantStatusTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TypesGrantStatusService typesGrantStatusService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-typesGrantStatus.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesGrantStatus.sql" })
	public void addTest() {
		TypesGrantStatus product = typesGrantStatusService.add(new TypesGrantStatus());
		assertEquals(product.getId().longValue(), 3L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesGrantStatus.sql" })
	public void deleteByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesGrantStatusService.deleteById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesGrantStatus.sql" })
	public void deleteByIdTest2() {
		typesGrantStatusService.deleteById(1L);
		assertEquals(this.restTemplate
				.getForObject("http://localhost:" + port + "/types_grant_status/find_all", List.class).size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesGrantStatus.sql" })
	public void updateTest() {
		TypesGrantStatus product = this.restTemplate.getForObject(
				"http://localhost:" + port + "/types_grant_status/find_by_id?id=1", TypesGrantStatus.class);
		product.setStatusEn("test updating");
		assertEquals(product.getStatusEn(), new String("test updating"));
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesGrantStatus.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<TypesGrantStatus> list = this.restTemplate
				.getForObject("http://localhost:" + port + "/types_grant_status/find_all", List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesGrantStatus.sql" })
	public void findByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesGrantStatusService.findById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesGrantStatus.sql" })
	public void findByIdTest2() {
		TypesGrantStatus product = this.restTemplate.getForObject(
				"http://localhost:" + port + "/types_grant_status/find_by_id?id=1", TypesGrantStatus.class);
		assertEquals(product.getId().longValue(), 1L);
	}
}
