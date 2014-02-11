/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.*;

/**
 *
 * @author benja_000
 */
public class CashRegister {

    private double bill = 0;
    private double salesTax = .056;
    private double totalBill = 0;
    private double totalBillWithTax = 0;
    private String[] item;
    private String foodName;
    private String foodPrice;
    private String order = "";
    private static final String SPLIT = "/";
    private static final String SPACE = " ";
    private static final String BREAK = "<br />";
    public static final String BILL = "Bill: ";
    public static final String SALES = "Sales Tax: ";
    public static final String GRAND = "Grand Total: ";

    public String getBillForCustomer(String[] orders) {
        for (String personOrder : orders) {
            item = personOrder.split(SPLIT);
            foodName = item[0];
            foodPrice = item[1];
            bill = bill + Double.valueOf(foodPrice);
            order = order + foodName + SPACE + Double.parseDouble(foodPrice) + BREAK;
        }
        totalBill = bill * salesTax;
        totalBillWithTax = totalBill + bill;
        order += BILL + bill + BREAK + SALES + salesTax + BREAK + GRAND + totalBillWithTax;
        return order;
    }
}
