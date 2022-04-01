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
import com.uottawa.project.entity.TypesFaculty;
import com.uottawa.project.service.TypesFacultyService;

@ActiveProfiles({"integration"})
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TypesFacultyTests{

	@LocalServerPort
	private int port;

	@Autowired
	private TypesFacultyService typesFacultyService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-typesFaculty.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesFaculty.sql" })
	public void addTest() {
		TypesFaculty faculty = typesFacultyService.add(new TypesFaculty());
		assertEquals(faculty.getId().longValue(), 3L);
	}
	
	@Test
	@Sql({ "classpath:sql/integration-test-typesFaculty.sql" })
	public void deleteByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesFacultyService.deleteById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesFaculty.sql" })
	public void deleteByIdTest2() {
		typesFacultyService.deleteById(1L);
		assertEquals(
				this.restTemplate.getForObject("http://localhost:" + port + "/types_faculty/find_all", List.class).size(),
				1);
	}
	
	@Test
	@Sql({ "classpath:sql/integration-test-typesFaculty.sql" })
	public void updateTest() {
		TypesFaculty faculty = this.restTemplate.getForObject("http://localhost:" + port + "/types_faculty/find_by_id?id=1",
				TypesFaculty.class);
		faculty.setNameEn("test updating");
		assertEquals(faculty.getNameEn(), new String("test updating"));
	}
	
	@Test
	@Sql({ "classpath:sql/integration-test-typesFaculty.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<TypesFaculty> list = this.restTemplate.getForObject("http://localhost:" + port + "/types_faculty/find_all",
				List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesFaculty.sql" })
	public void findByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesFacultyService.findById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesFaculty.sql" })
	public void findByIdTest2() {
		TypesFaculty faculty = this.restTemplate.getForObject("http://localhost:" + port + "/types_faculty/find_by_id?id=1",
				TypesFaculty.class);
		assertEquals(faculty.getId().longValue(), 1L);
	}
}
