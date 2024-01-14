package trojan.taco.repository.jpa;

import trojan.taco.model.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {

}
