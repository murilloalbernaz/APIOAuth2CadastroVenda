package udemy.spring.angular.vendas.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import udemy.spring.angular.vendas.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
