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
import com.uottawa.project.entity.TypesEvent;
import com.uottawa.project.service.TypesEventService;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TypesEventTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TypesEventService TypesEventService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-typesEvent.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesEvent.sql" })
	public void addTest() {
		TypesEvent product = TypesEventService.add(new TypesEvent());
		assertEquals(product.getId().longValue(), 3L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesEvent.sql" })
	public void deleteByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			TypesEventService.deleteById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesEvent.sql" })
	public void deleteByIdTest2() {
		TypesEventService.deleteById(1L);
		assertEquals(this.restTemplate.getForObject("http://localhost:" + port + "/types_event/find_all", List.class)
				.size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesEvent.sql" })
	public void updateTest() {
		TypesEvent product = this.restTemplate
				.getForObject("http://localhost:" + port + "/types_event/find_by_id?id=1", TypesEvent.class);
		product.setTypeEn("test updating");
		assertEquals(product.getTypeEn(), new String("test updating"));
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesEvent.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<TypesEvent> list = this.restTemplate.getForObject("http://localhost:" + port + "/types_event/find_all",
				List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesEvent.sql" })
	public void findByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			TypesEventService.findById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesEvent.sql" })
	public void findByIdTest2() {
		TypesEvent product = this.restTemplate
				.getForObject("http://localhost:" + port + "/types_event/find_by_id?id=1", TypesEvent.class);
		assertEquals(product.getId().longValue(), 1L);
	}
}
