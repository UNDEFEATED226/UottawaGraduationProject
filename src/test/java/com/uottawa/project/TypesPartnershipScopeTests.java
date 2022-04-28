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
import com.uottawa.project.entity.TypesPartnershipScope;
import com.uottawa.project.service.TypesPartnershipScopeService;

@ActiveProfiles({"integration"})
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TypesPartnershipScopeTests{

	@LocalServerPort
	private int port;

	@Autowired
	private TypesPartnershipScopeService typesPartnershipScopeService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-typesPartnershipScope.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesPartnershipScope.sql" })
	public void addTest() {
		TypesPartnershipScope type = typesPartnershipScopeService.add(new TypesPartnershipScope());
		assertEquals(type.getId().longValue(), 3L);
	}
	
	@Test
	@Sql({ "classpath:sql/integration-test-typesPartnershipScope.sql" })
	public void deleteByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesPartnershipScopeService.deleteById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesPartnershipScope.sql" })
	public void deleteByIdTest2() {
		typesPartnershipScopeService.deleteById(1L);
		assertEquals(
				this.restTemplate.getForObject("http://localhost:" + port + "/types_partnership_scope/find_all", List.class).size(),
				1);
	}
	
	@Test
	@Sql({ "classpath:sql/integration-test-typesPartnershipScope.sql" })
	public void updateTest() {
		TypesPartnershipScope type = this.restTemplate.getForObject("http://localhost:" + port + "/types_partnership_scope/find_by_id?id=1",
				TypesPartnershipScope.class);
		type.setScopeEn("test updating");
		assertEquals(type.getScopeEn(), new String("test updating"));
	}
	
	@Test
	@Sql({ "classpath:sql/integration-test-typesPartnershipScope.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<TypesPartnershipScope> list = this.restTemplate.getForObject("http://localhost:" + port + "/types_partnership_scope/find_all",
				List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesPartnershipScope.sql" })
	public void findByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesPartnershipScopeService.findById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesPartnershipScope.sql" })
	public void findByIdTest2() {
		TypesPartnershipScope type = this.restTemplate.getForObject("http://localhost:" + port + "/types_partnership_scope/find_by_id?id=1",
				TypesPartnershipScope.class);
		assertEquals(type.getId().longValue(), 1L);
	}
}
