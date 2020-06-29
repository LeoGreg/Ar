package am.basic.jdbcStart.repository.impl.pay;

public class MyThread implements Runnable {
    private PaymentService paymentService;
    public MyThread(PaymentService paymentService) {
        this.paymentService=paymentService;
    }

    @Override
    public void run() {


    }
}
