package gr.uom.market_week8;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketRepository extends JpaRepository<Product, Integer>{

	Product findByName(String pr);

}
