package co.com.reactiveprograming.sec06;

import co.com.reactiveprograming.common.Util;
import co.com.reactiveprograming.sec06.assignment.ExternalServiceClient;
import co.com.reactiveprograming.sec06.assignment.InventoryService;
import co.com.reactiveprograming.sec06.assignment.RevenueService;

/*
Ensure that external service is up and running
 */
public class Lec06Assigment {
    public static void main(String[] args) {
        ExternalServiceClient externalServiceClient = new ExternalServiceClient();
        InventoryService inventoryService = new InventoryService();
        RevenueService revenueService = new RevenueService();

        externalServiceClient.orderStream().subscribe(inventoryService::consume);
        externalServiceClient.orderStream().subscribe(revenueService::consume);

        inventoryService.stream()
                .subscribe(Util.subscriber("inventory"));

        revenueService.stream()
                .subscribe(Util.subscriber("revenue"));





        Util.sleepSeconds(30);
    }
}
