package com.app.designpatterns.factory;

public class PaymentExample {

    public static void main(String[] args) {
        PaymentFactory factory = new CreditCardFactory();
        factory.executePayment(10);
    }
}

interface PaymentProcessor{
     void process(int amount);
}
abstract class PaymentFactory{
    abstract PaymentProcessor createProcessor();

    public void executePayment(int amount) {
        PaymentProcessor processor = createProcessor(); // Factory Method call
        processor.process(amount);
    }
}
class CreditCardPaymentProcess implements PaymentProcessor{

    @Override
    public void process(int amount) {
        System.out.println("processing payment"+amount);
    }
}
class CreditCardFactory extends PaymentFactory{

    @Override
    PaymentProcessor createProcessor() {
        return new CreditCardPaymentProcess();
    }
}
