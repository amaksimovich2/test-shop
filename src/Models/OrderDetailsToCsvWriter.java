package Models;

import Abstractions.IOrderDetailsWriter;
import Models.Discounts.DiscountCards;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class OrderDetailsToCsvWriter implements IOrderDetailsWriter {
    
    private final File _file;
    
    public OrderDetailsToCsvWriter(String filePath) {
        _file = new File(filePath);
    }

    @Override
    public Integer Write(Order order) {
        try {
            FileWriter outputFile = new FileWriter(_file);
            PrintWriter printWriter = new PrintWriter(outputFile);

            printWriter.println("Date;Time");
            printWriter.println(order.CreatedDate.getDate() + ";" + order.CreatedDate.getTime());
            printWriter.println("");
            printWriter.println("QTU;Description");
            
            
            printWriter.println("Discount card; Discount");
            var discount = DiscountCards.Discounts.get(order.DiscountCard) * 100;
            printWriter.println(order.DiscountCard + ";" + discount + "%");

            printWriter.close();
        }
        catch (Exception e) {
            e.getStackTrace();
            return 0;
        }
        
        return 1;
    }
}
