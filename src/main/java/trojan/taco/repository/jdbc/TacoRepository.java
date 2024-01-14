package trojan.taco.repository.jdbc;

import trojan.taco.model.Taco;

public interface TacoRepository {

    Taco save(final Taco taco);
}
