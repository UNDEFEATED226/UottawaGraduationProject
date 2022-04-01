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
import com.uottawa.project.entity.TypesAffiliation;
import com.uottawa.project.service.TypesAffiliationService;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TypesAffiliationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TypesAffiliationService typesAffiliationService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-typesAffiliation.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesAffiliation.sql" })
	public void addTest() {
		TypesAffiliation affiliation = typesAffiliationService.add(new TypesAffiliation());
		assertEquals(affiliation.getId().longValue(), 3L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesAffiliation.sql" })
	public void deleteByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesAffiliationService.deleteById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesAffiliation.sql" })
	public void deleteByIdTest2() {
		typesAffiliationService.deleteById(1L);
		assertEquals(this.restTemplate
				.getForObject("http://localhost:" + port + "/types_affiliation/find_all", List.class).size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesAffiliation.sql" })
	public void updateTest() {
		TypesAffiliation affiliation = this.restTemplate.getForObject(
				"http://localhost:" + port + "/types_affiliation/find_by_id?id=1", TypesAffiliation.class);
		affiliation.setName("test updating");
		assertEquals(affiliation.getName(), new String("test updating"));
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesAffiliation.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<TypesAffiliation> list = this.restTemplate
				.getForObject("http://localhost:" + port + "/types_affiliation/find_all", List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesAffiliation.sql" })
	public void findByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesAffiliationService.findById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesAffiliation.sql" })
	public void findByIdTest2() {
		TypesAffiliation faculty = this.restTemplate.getForObject(
				"http://localhost:" + port + "/types_affiliation/find_by_id?id=1", TypesAffiliation.class);
		assertEquals(faculty.getId().longValue(), 1L);
	}
}
