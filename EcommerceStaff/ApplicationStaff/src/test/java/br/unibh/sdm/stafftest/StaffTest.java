package br.unibh.sdm.stafftest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.unibh.sdm.ecommercestaff.Application;
import br.unibh.sdm.ecommercestaff.entity.Staff;
import br.unibh.sdm.ecommercestaff.persistance.StaffRepository;

/**
 * Classe de testes para a entidade Staff.
 * <br>
 * Para rodar, antes defina a seguinte variável de ambiente:
 * -Dspring.config.location=C:/Users/seu-usuario/ecommerce-staff/
 * <br>
 * Neste diretório, criar um arquivo application.properties contendo as seguintes variáveis:
 * <br>
 * # Connection parameters<br>
 * spring.datasource.url=jdbc:postgresql://HOST:5432/DBNAME<br>
 * spring.datasource.username=USER<br>
 * spring.datasource.password=PASSWORD<br>
 * <br>
 * spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true<br>
 * spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect<br>
 * <br>
 * # Hibernate ddl auto (create, create-drop, validate, update)<br>
 * spring.jpa.hibernate.ddl-auto=create<br>
 * 
 * @author ecommerce-staff
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StaffTest {

    private static Logger LOGGER = LoggerFactory.getLogger(StaffTest.class);
    private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    private StaffRepository repository;

    @Test
    public void teste1Criacao() throws ParseException {
        LOGGER.info("Criando objetos de Staff...");
        
        Staff s1 = new Staff(0, "João Antunes", "Gerente", 5000.00, "12345678901", 
                            "joao@gmail.com", "31988887777", df.parse("01/01/2000"), "joao", "1234");
        repository.save(s1);

        Staff s2 = new Staff(0, "Maria Silva", "Vendedor", 3000.00, "12345678902", 
                            "maria@gmail.com", "31988887778", df.parse("02/02/1995"), "maria", "1234");
        repository.save(s2);

        Staff s3 = new Staff(0, "Tiago Santos", "Atendente", 2500.00, "12345678903", 
                            "tiago@gmail.com", "31988887779", df.parse("03/03/1980"), "tiago", "1234");
        repository.save(s3);

        LOGGER.info("Pesquisando todos os Staff");
        Iterable<Staff> lista = repository.findAll();
        assertNotNull(lista.iterator());
        for (Staff staff : lista) {
            LOGGER.info(staff.toString());
        }

        LOGGER.info("Pesquisando um objeto específico");
        List<Staff> result = repository.findByName("Tiago Santos");
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getEmail(), "tiago@gmail.com");
        LOGGER.info("Encontrado: {}", result.get(0));
    }

    @Test
    public void teste2Exclusao() throws ParseException {
        LOGGER.info("Excluindo objetos de Staff...");
        
        List<Staff> result = repository.findByCpfStaff("12345678901");
        for (Staff staff : result) {
            LOGGER.info("Excluindo Staff id = " + staff.getId());
            repository.delete(staff);
        }

        result = repository.findByCpfStaff("12345678902");
        for (Staff staff : result) {
            LOGGER.info("Excluindo Staff id = " + staff.getId());
            repository.delete(staff);
        }

        result = repository.findByCpfStaff("12345678903");
        for (Staff staff : result) {
            LOGGER.info("Excluindo Staff id = " + staff.getId());
            repository.delete(staff);
        }

        result = repository.findByName("Tiago Santos");
        assertEquals(result.size(), 0);
        LOGGER.info("Exclusão feita com sucesso");
    }

}
