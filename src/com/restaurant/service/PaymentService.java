package com.restaurant.service;

import com.restaurant.model.Payment;
import com.restaurant.payment.PaymentMethod;
import com.restaurant.repository.PaymentRepository;
import java.util.List;

public class PaymentService {

    private final PaymentRepository repository;
    private int nextPaymentId = 1;

    public PaymentService() {
        repository = new PaymentRepository();
    }

    public Payment processPayment(PaymentMethod method, double amount) {
        method.pay(amount);

        Payment payment = new Payment(
                nextPaymentId++,
                amount,
                method.getMethodName(),
                "SUCCESS");

        repository.save(payment);
        return payment;
    }

    public List<Payment> getAllPayments() {
        return repository.findAll();
    }

    public Payment getPaymentById(int id) {
        return repository.findById(id);
    }
}