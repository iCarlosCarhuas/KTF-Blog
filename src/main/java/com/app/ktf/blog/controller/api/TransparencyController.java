package com.app.ktf.blog.controller.api;

import com.app.ktf.blog.entity.TransactionEntity;
import com.app.ktf.blog.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/estado")
public class TransparencyController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping
    public Map<String, Object> getTransparencyData() {
        List<TransactionEntity> transactions = transactionRepository.findAll();

        BigDecimal totalRaised = transactions.stream()
                .filter(t -> "DONATION".equals(t.getType()))
                .map(TransactionEntity::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<Map<String, Object>> publicLedger = transactions.stream().map(t -> {
            Map<String, Object> map = new HashMap<>();
            map.put("sponsor", t.getSponsorName());
            map.put("timestamp", t.getTimestamp());
            map.put("status", t.getStatus());
            // Enmascarar el monto individual como solicita el usuario
            map.put("amount", "**"); 
            return map;
        }).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("totalRaised", totalRaised);
        response.put("ledger", publicLedger);
        response.put("message", "KTF Learn Transparency Portal - Global Fund Audit");

        return response;
    }
}
