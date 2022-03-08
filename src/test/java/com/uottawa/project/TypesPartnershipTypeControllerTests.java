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
import com.uottawa.project.entity.TypesPartnershipType;
import com.uottawa.project.service.TypesPartnershipTypeService;

@ActiveProfiles({"integration"})
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TypesPartnershipTypeControllerTests{

	@LocalServerPort
	private int port;

	@Autowired
	private TypesPartnershipTypeService typesPartnershipTypeService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-typesPartnershipType.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesPartnershipType.sql" })
	public void addTest() {
		TypesPartnershipType type = typesPartnershipTypeService.add(new TypesPartnershipType());
		assertEquals(type.getId().longValue(), 3L);
	}
	
	@Test
	@Sql({ "classpath:sql/integration-test-typesPartnershipType.sql" })
	public void deleteByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesPartnershipTypeService.deleteById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesPartnershipType.sql" })
	public void deleteByIdTest2() {
		typesPartnershipTypeService.deleteById(1L);
		assertEquals(
				this.restTemplate.getForObject("http://localhost:" + port + "/types_partnership_type/find_all", List.class).size(),
				1);
	}
	
	@Test
	@Sql({ "classpath:sql/integration-test-typesPartnershipType.sql" })
	public void updateTest() {
		TypesPartnershipType type = this.restTemplate.getForObject("http://localhost:" + port + "/types_partnership_type/find_by_id?id=1",
				TypesPartnershipType.class);
		type.setTypeEn("test updating");
		assertEquals(type.getTypeEn(), new String("test updating"));
	}
	
	@Test
	@Sql({ "classpath:sql/integration-test-typesPartnershipType.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<TypesPartnershipType> list = this.restTemplate.getForObject("http://localhost:" + port + "/types_partnership_type/find_all",
				List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesPartnershipType.sql" })
	public void findByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesPartnershipTypeService.findById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesPartnershipType.sql" })
	public void findByIdTest2() {
		TypesPartnershipType type = this.restTemplate.getForObject("http://localhost:" + port + "/types_partnership_type/find_by_id?id=1",
				TypesPartnershipType.class);
		assertEquals(type.getId().longValue(), 1L);
	}
}
