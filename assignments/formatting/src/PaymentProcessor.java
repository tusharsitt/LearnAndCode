package com.payment.processing;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class PaymentProcessor {

    private static final BigDecimal MIN_AMOUNT = new BigDecimal("0.01");
    private static final BigDecimal DAILY_LIMIT = new BigDecimal("5000");
    private static final int MAX_RETRIES = 2;
    private static final String PAYMENT_SUCCESS = "Payment successful";
    private static final String PAYMENT_FAILED = "Payment failed";

    private final Logger logger;
    private final NotificationService notifier;
    private final Map<String, PaymentRecord> history;

    public PaymentProcessor(Logger logger, NotificationService notifier) {
        this.logger = logger;
        this.notifier = notifier;
        this.history = new HashMap<>();
    }

    public PaymentResult process(PaymentRequest request) {
        validate(request);

        int attempt = 0;
        while (attempt <= MAX_RETRIES) {
            try {
                record(request);
                notifySuccess(request);
                return new PaymentResult(PAYMENT_SUCCESS);
            } catch (Exception e) {
                attempt++;
                logger.log("Attempt " + attempt + " failed: " + e.getMessage());
            }
        }

        return new PaymentResult(PAYMENT_FAILED);
    }

    private void validate(PaymentRequest request) {
        if (request.amount().compareTo(MIN_AMOUNT) < 0) {
            throw new PaymentException("Amount too low");
        }
        checkLimit(request);
    }

    private void checkLimit(PaymentRequest request) {
        if (request.amount().compareTo(DAILY_LIMIT) > 0) {
            throw new PaymentException("Limit exceeded");
        }
    }

    private void record(PaymentRequest request) {
        String transactionId = generateId();
        PaymentRecord record = new PaymentRecord(
                request.customerId(),
                request.amount(),
                LocalDateTime.now()
        );

        history.put(transactionId, record);
    }

    private String generateId() {
        return "TXN-" + System.currentTimeMillis();
    }

    private void notifySuccess(PaymentRequest request) {
        String message = "Payment of " + request.amount() + " processed";
        notifier.send(request.customerId(), message);
    }
}