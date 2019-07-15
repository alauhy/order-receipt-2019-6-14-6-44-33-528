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
    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        return createReceipt();

    }
    public String createReceipt(){
        StringBuilder receipet = new StringBuilder();

        // print headers

        receipet.append(title);

        // print date, bill no, customer name
//        receipet.append("Date - " + order.getDate();
        receipet.append(order.getCustomerName());
        receipet.append(order.getCustomerAddress());
//        receipet.append(order.getCustomerLoyaltyNumber());

        // prints lineItems

        receipet.append(getLineItems());


        // prints the state tax
        receipet.append("Sales Tax").append('\t').append(getTotalSalesTx());

        // print total amount
        receipet.append("Total Amount").append('\t').append(getTotalAmount());

        return receipet.toString();
    }

    public String getLineItems() {
        // prints the state tax
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

    public double getTotalSalesTx() {

        return lineItemTotalAmount().stream().reduce(0.0,(a, b) -> a + b * .10);

    }

    public double getTotalAmount() {
        return lineItemTotalAmount().stream().reduce(0.0,(a, b) -> a + b * 1.10);

    }


}