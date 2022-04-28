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

import com.uottawa.project.entity.TypesTargetStakeholder;
import com.uottawa.project.service.TypesTargetStakeholderService;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TypesTargetStakeholderTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TypesTargetStakeholderService typesTargetStakeholderService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-typesTargetStakeholder.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesTargetStakeholder.sql" })
	public void addTest() {
		TypesTargetStakeholder product = typesTargetStakeholderService.add(new TypesTargetStakeholder());
		assertEquals(product.getId().longValue(), 3L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesTargetStakeholder.sql" })
	public void deleteByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesTargetStakeholderService.deleteById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesTargetStakeholder.sql" })
	public void deleteByIdTest2() {
		typesTargetStakeholderService.deleteById(1L);
		assertEquals(this.restTemplate.getForObject("http://localhost:" + port + "/types_target_stakeholder/find_all", List.class)
				.size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesTargetStakeholder.sql" })
	public void updateTest() {
		TypesTargetStakeholder product = this.restTemplate
				.getForObject("http://localhost:" + port + "/types_target_stakeholder/find_by_id?id=1", TypesTargetStakeholder.class);
		product.setNameEn("test updating");
		assertEquals(product.getNameEn(), new String("test updating"));
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesTargetStakeholder.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<TypesTargetStakeholder> list = this.restTemplate.getForObject("http://localhost:" + port + "/types_target_stakeholder/find_all",
				List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesTargetStakeholder.sql" })
	public void findByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesTargetStakeholderService.findById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesTargetStakeholder.sql" })
	public void findByIdTest2() {
		TypesTargetStakeholder product = this.restTemplate
				.getForObject("http://localhost:" + port + "/types_target_stakeholder/find_by_id?id=1", TypesTargetStakeholder.class);
		assertEquals(product.getId().longValue(), 1L);
	}
}
