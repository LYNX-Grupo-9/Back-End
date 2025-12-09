package br.com.exemplo.crudadvogado.infrastructure.web.controller;

import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.service.GraficoService;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.ParcelaJpaRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/grafico")
public class GraficoController {

    private final GraficoService graficoService;
    private final ParcelaJpaRepository parcelaJpaRepository;

    public GraficoController(GraficoService graficoService, ParcelaJpaRepository parcelaJpaRepository) {
        this.graficoService = graficoService;
        this.parcelaJpaRepository = parcelaJpaRepository;
    }

    // ===============================================================
    // FATURADO – Últimos 6 meses
    // ===============================================================

    @GetMapping("/grafico/faturado-ultimos-6-meses")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Map<String, BigDecimal>> getFaturadoUltimos6Meses() {

        List<Object[]> lista = parcelaJpaRepository.calcularFaturadoUltimos6Meses();

        if (lista == null || lista.isEmpty()) {
            return ResponseEntity.ok(zeroResponse("totalFaturado"));
        }

        Object[] resultado = lista.get(0);
        System.out.println("DEBUG FATURADO RESULTADO QUERY = " + java.util.Arrays.toString(resultado));

        Map<String, BigDecimal> response = new HashMap<>();
        response.put("totalFaturado30", convertToBD(resultado[0]));
        response.put("totalFaturado60", convertToBD(resultado[1]));
        response.put("totalFaturado90", convertToBD(resultado[2]));
        response.put("totalFaturado120", convertToBD(resultado[3]));
        response.put("totalFaturado150", convertToBD(resultado[4]));
        response.put("totalFaturado180", convertToBD(resultado[5]));

        return ResponseEntity.ok(response);
    }

    // ===============================================================
    // PENDENTE – Últimos 6 meses
    // ===============================================================

    @GetMapping("/grafico/pendente-ultimos-6-meses")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Map<String, BigDecimal>> getPendenteUltimos6Meses() {

        List<Object[]> lista = parcelaJpaRepository.calcularPendenteUltimos6Meses();

        if (lista == null || lista.isEmpty()) {
            return ResponseEntity.ok(zeroResponse("totalPendente"));
        }

        Object[] resultado = lista.get(0);
        System.out.println("DEBUG PENDENTE RESULTADO QUERY = " + java.util.Arrays.toString(resultado));

        Map<String, BigDecimal> response = new HashMap<>();
        response.put("totalPendente30", convertToBD(resultado[0]));
        response.put("totalPendente60", convertToBD(resultado[1]));
        response.put("totalPendente90", convertToBD(resultado[2]));
        response.put("totalPendente120", convertToBD(resultado[3]));
        response.put("totalPendente150", convertToBD(resultado[4]));
        response.put("totalPendente180", convertToBD(resultado[5]));

        return ResponseEntity.ok(response);
    }

    // ===============================================================
    // SUPORTE
    // ===============================================================

    private Map<String, BigDecimal> zeroResponse(String baseName) {
        Map<String, BigDecimal> map = new HashMap<>();
        map.put(baseName + "30", BigDecimal.ZERO);
        map.put(baseName + "60", BigDecimal.ZERO);
        map.put(baseName + "90", BigDecimal.ZERO);
        map.put(baseName + "120", BigDecimal.ZERO);
        map.put(baseName + "150", BigDecimal.ZERO);
        map.put(baseName + "180", BigDecimal.ZERO);
        return map;
    }

    private BigDecimal convertToBD(Object valor) {
        if (valor == null) return BigDecimal.ZERO;

        try {
            if (valor instanceof BigDecimal) return (BigDecimal) valor;
            if (valor instanceof BigInteger) return new BigDecimal((BigInteger) valor);
            if (valor instanceof Long) return BigDecimal.valueOf((Long) valor);
            if (valor instanceof Integer) return BigDecimal.valueOf((Integer) valor);
            if (valor instanceof Double) return BigDecimal.valueOf((Double) valor);
            if (valor instanceof Float) return BigDecimal.valueOf((Float) valor);

            return new BigDecimal(valor.toString());
        } catch (Exception e) {
            System.out.println("Erro ao converter para BigDecimal: " + valor);
            return BigDecimal.ZERO;
        }
    }
}
