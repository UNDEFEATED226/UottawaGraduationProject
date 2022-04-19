package com.uottawa.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.uottawa.project.entity.MainProducts;

@Repository
public interface MainProductsRepository extends JpaRepository<MainProducts, Long> {
	public List<MainProducts> findAllByType(Long type);

	@Query(value = "SELECT * from main_Products\r\n" + "WHERE ([id] in (SELECT product_id from relp_Product_Member\r\n"
			+ "left JOIN main_Products on main_Products.[id]=relp_Product_Member.[product_id]\r\n"
			+ "left JOIN main_Members on main_Members.[id]=relp_Product_Member.[member_id]\r\n"
			+ "WHERE (relp_Product_Member.[member_id]=:memberId))\r\n" + ")\r\n" + "", nativeQuery = true)
	public List<MainProducts> findAll(@Param(value = "memberId") Long memberId);
}
