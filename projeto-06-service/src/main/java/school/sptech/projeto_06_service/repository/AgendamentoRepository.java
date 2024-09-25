package school.sptech.projeto_06_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.projeto_06_service.entity.Agendamento;

import java.time.LocalDate;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
    boolean existsByData(LocalDate data);
}
