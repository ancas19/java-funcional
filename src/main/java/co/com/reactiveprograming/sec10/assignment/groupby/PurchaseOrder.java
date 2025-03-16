package co.com.reactiveprograming.sec10.assignment.groupby;

import co.com.reactiveprograming.common.Util;
import com.github.javafaker.Commerce;

public record PurchaseOrder(
        String item,
        String category,
        Integer price
) {

    public static PurchaseOrder create(){
        Commerce commerce = Util.faker().commerce();
        return new PurchaseOrder(
                commerce.productName(),
                commerce.department(),
               Util.faker().number().numberBetween(1,1000)
        );
    }
}
