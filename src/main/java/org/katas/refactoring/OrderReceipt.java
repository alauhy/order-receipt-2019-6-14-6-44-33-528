package org.katas.refactoring;

import java.util.List;
import java.util.stream.Collectors;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order order;

    final String title = "======Printing Orders======\n";
    final String sales_tax = "Sales Tax";
    final String total_amount = "Total Amount";
    final double tax = .10;
    final double taxedRate = 1.10;
    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        return createReceipt();

    }
    public String createReceipt(){
        StringBuilder receipet = new StringBuilder();


        receipet.append(title);


        receipet.append(order.getCustomerName());
        receipet.append(order.getCustomerAddress());



        receipet.append(getLineItems());



        receipet.append(sales_tax).append('\t').append(getTotalSalesTax());


        receipet.append(total_amount).append('\t').append(getTotalPrice());

        return receipet.toString();
    }

    public String getLineItems() {

        String lineItems = "";
        for (LineItem lineItem : order.getLineItems()) {
            lineItems += lineItem.getDescription();
            lineItems += ('\t');
            lineItems += (lineItem.getPrice());
            lineItems += ('\t');
            lineItems += (lineItem.getQuantity());
            lineItems += ('\t');
            lineItems += (lineItem.totalAmount());
            lineItems += ('\n');

        }
        return lineItems;
    }

    public List<Double> lineItemTotalAmount() {
        return order.getLineItems().stream().map(e -> e.totalAmount()).collect(Collectors.toList());
    }

    public double getTotalSalesTax() {

        return lineItemTotalAmount().stream().reduce(0.0, (a, b) -> {

            return a + b * tax;
        });

    }

    public double getTotalPrice() {
        return lineItemTotalAmount().stream().reduce(0.0,(a, b) -> a + b * taxedRate);

    }


}