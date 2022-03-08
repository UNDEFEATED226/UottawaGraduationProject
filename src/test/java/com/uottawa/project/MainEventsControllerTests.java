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
import com.uottawa.project.entity.MainEvents;
import com.uottawa.project.service.MainEventsService;

@ActiveProfiles({"integration"})
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Csi4900Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MainEventsControllerTests {

	@LocalServerPort
	private int port;

	@Autowired
	private MainEventsService mainEventsService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql({ "classpath:sql/integration-test-mainEvents.sql" })
	public void emptyTest() {
	}

	@Test
	@Sql({ "classpath:sql/integration-test-mainEvents.sql" })
	public void addTest() {
		MainEvents event = mainEventsService.add(new MainEvents());
		assertEquals(event.getId().longValue(), 3L);
	}
	
	@Test
	@Sql({ "classpath:sql/integration-test-mainEvents.sql" })
	public void deleteByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			mainEventsService.deleteById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-mainEvents.sql" })
	public void deleteByIdTest2() {
		mainEventsService.deleteById(1L);
		assertEquals(
				this.restTemplate.getForObject("http://localhost:" + port + "/main_events/find_all", List.class).size(),
				1);
	}
	
	@Test
	@Sql({ "classpath:sql/integration-test-mainEvents.sql" })
	public void updateTest() {
		MainEvents event = this.restTemplate.getForObject("http://localhost:" + port + "/main_events/find_by_id?id=1",
				MainEvents.class);
		event.setNameEn("test updating");
		assertEquals(event.getNameEn(), new String("test updating"));
	}
	
	@Test
	@Sql({ "classpath:sql/integration-test-mainEvents.sql" })
	public void findAllTest() {
		@SuppressWarnings("unchecked")
		List<MainEvents> list = this.restTemplate.getForObject("http://localhost:" + port + "/main_events/find_all",
				List.class);
		assertEquals(list.size(), 2);
	}

	@Test
	@Sql({ "classpath:sql/integration-test-mainEvents.sql" })
	public void findByIdTest1() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			mainEventsService.findById(3L);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-mainEvents.sql" })
	public void findByIdTest2() {
		MainEvents event = this.restTemplate.getForObject("http://localhost:" + port + "/main_events/find_by_id?id=1",
				MainEvents.class);
		assertEquals(event.getId().longValue(), 1L);
	}
}
