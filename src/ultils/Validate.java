package ultils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validate {
    public String inputNewTypeRoom(){
        String type = "";
        while (type.isEmpty()){
            System.out.println("Enter new type room: ");
            type = Input.getString();
        }
        return type;
    }

    public double inputValidPrice() {
        double price = 0;
        while (price <= 0) {
            System.out.println("Enter new price: ");
            price = Input.getDou();
        }
        return price;
    }

    public boolean isValidate(String dateStr){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try{
            LocalDate date = LocalDate.parse(dateStr, dateTimeFormatter);
            return true;
        } catch (DateTimeParseException d){
            return false;
        }
    }
}
