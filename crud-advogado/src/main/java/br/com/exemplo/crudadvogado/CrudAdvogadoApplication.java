package br.com.exemplo.crudadvogado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity")
public class CrudAdvogadoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudAdvogadoApplication.class, args);
    }

}
