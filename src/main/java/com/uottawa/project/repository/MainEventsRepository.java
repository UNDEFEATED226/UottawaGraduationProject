package com.uottawa.project.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.MainEvents;

@Repository
public interface MainEventsRepository {

	void add(MainEvents event);

	void deleteById(Long id);

	void update(MainEvents event);

	List<MainEvents> findAll();
}
