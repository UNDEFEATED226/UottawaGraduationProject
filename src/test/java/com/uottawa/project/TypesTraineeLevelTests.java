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
import com.uottawa.project.entity.TypesTraineeLevel;
import com.uottawa.project.service.TypesTraineeLevelService;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TypesTraineeLevelTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TypesTraineeLevelService typesTraineeLevelService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-typesTraineeLevel.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesTraineeLevel.sql" })
	public void addTest() {
		TypesTraineeLevel level = typesTraineeLevelService.add(new TypesTraineeLevel());
		assertEquals(level.getId().longValue(), 3L);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesTraineeLevel.sql" })
	public void deleteByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesTraineeLevelService.deleteById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesTraineeLevel.sql" })
	public void deleteByIdTest2() {
		typesTraineeLevelService.deleteById(1L);
		assertEquals(this.restTemplate
				.getForObject("http://localhost:" + port + "/types_trainee_level/find_all", List.class).size(), 1);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesTraineeLevel.sql" })
	public void updateTest() {
		TypesTraineeLevel level = this.restTemplate.getForObject(
				"http://localhost:" + port + "/types_trainee_level/find_by_id?id=1", TypesTraineeLevel.class);
		level.setLevelEn("test updating");
		assertEquals(level.getLevelEn(), new String("test updating"));
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesTraineeLevel.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<TypesTraineeLevel> list = this.restTemplate
				.getForObject("http://localhost:" + port + "/types_trainee_level/find_all", List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesTraineeLevel.sql" })
	public void findByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesTraineeLevelService.findById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesTraineeLevel.sql" })
	public void findByIdTest2() {
		TypesTraineeLevel level = this.restTemplate.getForObject(
				"http://localhost:" + port + "/types_trainee_level/find_by_id?id=1", TypesTraineeLevel.class);
		assertEquals(level.getId().longValue(), 1L);
	}
}
