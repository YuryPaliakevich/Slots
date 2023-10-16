package by.slots.service.payment;

public interface BalanceService {

    void increase(int amount);

    void reduce(int amount);


    int getCurrent();

}
