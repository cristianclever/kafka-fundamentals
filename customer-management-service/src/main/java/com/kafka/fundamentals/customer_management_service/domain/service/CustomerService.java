package com.kafka.fundamentals.customer_management_service.domain.service;


import com.kafka.fundamentals.customer_management_service.domain.model.Customer;
import com.kafka.fundamentals.customer_management_service.domain.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@AllArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;


    @Transactional
    public Customer createCustomer(Customer customer) {
        // 1. Salva no PostgreSQL

        Customer savedCustomer = customerRepository.save(customer);

        // 2. Prepara e envia o evento para o Kafka
        try {
            String payload = objectMapper.writeValueAsString(savedCustomer);
            String key = savedCustomer.getId().toString(); // Usando o ID gerado pelo banco como KEY do Kafka

            kafkaTemplate.send("customer-registered-topic", key, payload);

        } catch (JacksonException e) {
            // Em produção, trate esse erro de serialização de forma robusta
            throw new RuntimeException("Erro ao serializar dados do cliente para o Kafka", e);
        }

        return savedCustomer;
    }
}
