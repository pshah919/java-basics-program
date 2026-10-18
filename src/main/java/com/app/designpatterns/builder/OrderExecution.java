package com.app.designpatterns.builder;

public class OrderExecution {

    private final String symbol;
    private final int quantity;
    private final double limitPrice;
    private final String timeInForce; // Optional

    private OrderExecution(Builder builder) {
        this.symbol = builder.symbol;
        this.quantity = builder.quantity;
        this.limitPrice = builder.limitPrice;
        this.timeInForce = builder.timeInForce;
    }

    // Step Interfaces
    public interface SymbolStep { QuantityStep symbol(String symbol); }
    public interface QuantityStep { PriceStep quantity(int quantity); }
    public interface PriceStep { BuildStep limitPrice(double price); }

    public interface BuildStep {
        BuildStep timeInForce(String tif);
        OrderExecution build();
    }

    public static SymbolStep builder() {
        return new Builder();
    }

    private static class Builder implements SymbolStep, QuantityStep, PriceStep, BuildStep {
        private String symbol;
        private int quantity;
        private double limitPrice;
        private String timeInForce = "GTC"; // Default value

        @Override public QuantityStep symbol(String symbol) { this.symbol = symbol; return this; }
        @Override public PriceStep quantity(int quantity) { this.quantity = quantity; return this; }
        @Override public BuildStep limitPrice(double price) { this.limitPrice = price; return this; }
        @Override public BuildStep timeInForce(String tif) { this.timeInForce = tif; return this; }

        @Override
        public OrderExecution build() {
            return new OrderExecution(this);
        }
    }

    public static void main(String[] args) {
        OrderExecution order = OrderExecution.builder()
                .symbol("ASML")      // 1. Symbol mandatory
                .quantity(100)       // 2. Quantity mandatory
                .limitPrice(850.50)  // 3. Price mandatory
                .timeInForce("IOC")  // 4. Optional
                .build();
    }
}
