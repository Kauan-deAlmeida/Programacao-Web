package school.sptech.projeto_05_validations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletoBancarioRepository extends JpaRepository<BoletoBancario, Integer> {
}
