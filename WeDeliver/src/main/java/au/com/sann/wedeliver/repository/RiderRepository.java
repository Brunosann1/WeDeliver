package au.com.sann.wedeliver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import au.com.sann.wedeliver.model.Rider;

@Repository
public interface RiderRepository extends CrudRepository<Rider, Long>{

}
