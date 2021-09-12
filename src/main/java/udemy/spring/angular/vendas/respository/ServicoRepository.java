package udemy.spring.angular.vendas.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import udemy.spring.angular.vendas.model.entity.Servico;

import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

    @Query(value = "select * from Servico s inner join Cliente c on c.id = s.cliente_id where upper(c.nome) like upper(:nome) and month(s.data) = :mes", nativeQuery = true)
    public List<Servico> pesquisarMesCliente(@Param("nome") String nome,@Param("mes") Integer mes);
}
