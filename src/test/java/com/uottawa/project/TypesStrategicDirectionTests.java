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

import com.uottawa.project.entity.TypesStrategicDirection;
import com.uottawa.project.service.TypesStrategicDirectionService;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TypesStrategicDirectionTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TypesStrategicDirectionService typesStrategicDirectionService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-typesStrategicDirection.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesStrategicDirection.sql" })
	public void addTest() {
		TypesStrategicDirection product = typesStrategicDirectionService.add(new TypesStrategicDirection());
		assertEquals(product.getId().longValue(), 3L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesStrategicDirection.sql" })
	public void deleteByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesStrategicDirectionService.deleteById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesStrategicDirection.sql" })
	public void deleteByIdTest2() {
		typesStrategicDirectionService.deleteById(1L);
		assertEquals(this.restTemplate.getForObject("http://localhost:" + port + "/types_strategic_direction/find_all", List.class)
				.size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesStrategicDirection.sql" })
	public void updateTest() {
		TypesStrategicDirection product = this.restTemplate
				.getForObject("http://localhost:" + port + "/types_strategic_direction/find_by_id?id=1", TypesStrategicDirection.class);
		product.setName("test updating");
		assertEquals(product.getName(), new String("test updating"));
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesStrategicDirection.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<TypesStrategicDirection> list = this.restTemplate.getForObject("http://localhost:" + port + "/types_strategic_direction/find_all",
				List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesStrategicDirection.sql" })
	public void findByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesStrategicDirectionService.findById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesStrategicDirection.sql" })
	public void findByIdTest2() {
		TypesStrategicDirection product = this.restTemplate
				.getForObject("http://localhost:" + port + "/types_strategic_direction/find_by_id?id=1", TypesStrategicDirection.class);
		assertEquals(product.getId().longValue(), 1L);
	}
}
