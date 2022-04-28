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

import com.uottawa.project.entity.TypesTopic;
import com.uottawa.project.service.TypesTopicService;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TypesTopicTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TypesTopicService typesTopicService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-typesTopic.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesTopic.sql" })
	public void addTest() {
		TypesTopic product = typesTopicService.add(new TypesTopic());
		assertEquals(product.getId().longValue(), 3L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesTopic.sql" })
	public void deleteByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesTopicService.deleteById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesTopic.sql" })
	public void deleteByIdTest2() {
		typesTopicService.deleteById(1L);
		assertEquals(
				this.restTemplate.getForObject("http://localhost:" + port + "/types_topic/find_all", List.class).size(),
				1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesTopic.sql" })
	public void updateTest() {
		TypesTopic product = this.restTemplate.getForObject("http://localhost:" + port + "/types_topic/find_by_id?id=1",
				TypesTopic.class);
		product.setNameEn("test updating");
		assertEquals(product.getNameEn(), new String("test updating"));
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesTopic.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<TypesTopic> list = this.restTemplate.getForObject("http://localhost:" + port + "/types_topic/find_all",
				List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesTopic.sql" })
	public void findByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesTopicService.findById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesTopic.sql" })
	public void findByIdTest2() {
		TypesTopic product = this.restTemplate.getForObject("http://localhost:" + port + "/types_topic/find_by_id?id=1",
				TypesTopic.class);
		assertEquals(product.getId().longValue(), 1L);
	}
}
