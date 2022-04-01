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
import com.uottawa.project.entity.TypesFrequencyIndicator;
import com.uottawa.project.service.TypesFrequencyIndicatorService;

@ActiveProfiles({"integration"})
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TypesFrequencyIndicatorTests{

	@LocalServerPort
	private int port;

	@Autowired
	private TypesFrequencyIndicatorService typesFrequencyIndicatorService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-typesFrequencyIndicator.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesFrequencyIndicator.sql" })
	public void addTest() {
		TypesFrequencyIndicator indicator = typesFrequencyIndicatorService.add(new TypesFrequencyIndicator());
		assertEquals(indicator.getId().longValue(), 3L);
	}
	
	@Test
	@Sql({ "classpath:sql/integration-test-typesFrequencyIndicator.sql" })
	public void deleteByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesFrequencyIndicatorService.deleteById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesFrequencyIndicator.sql" })
	public void deleteByIdTest2() {
		typesFrequencyIndicatorService.deleteById(1L);
		assertEquals(
				this.restTemplate.getForObject("http://localhost:" + port + "/types_frequency_indicator/find_all", List.class).size(),
				1);
	}
	
	@Test
	@Sql({ "classpath:sql/integration-test-typesFrequencyIndicator.sql" })
	public void updateTest() {
		TypesFrequencyIndicator indicator = this.restTemplate.getForObject("http://localhost:" + port + "/types_frequency_indicator/find_by_id?id=1",
				TypesFrequencyIndicator.class);
		indicator.setFrequency("test updating");
		assertEquals(indicator.getFrequency(), new String("test updating"));
	}
	
	@Test
	@Sql({ "classpath:sql/integration-test-typesFrequencyIndicator.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<TypesFrequencyIndicator> list = this.restTemplate.getForObject("http://localhost:" + port + "/types_frequency_indicator/find_all",
				List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesFrequencyIndicator.sql" })
	public void findByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			typesFrequencyIndicatorService.findById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-typesFrequencyIndicator.sql" })
	public void findByIdTest2() {
		TypesFrequencyIndicator indicator = this.restTemplate.getForObject("http://localhost:" + port + "/types_frequency_indicator/find_by_id?id=1",
				TypesFrequencyIndicator.class);
		assertEquals(indicator.getId().longValue(), 1L);
	}
}
