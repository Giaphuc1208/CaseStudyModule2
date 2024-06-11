package views;

import models.Booking;
import models.Room;
import ultils.Input;
import ultils.Validate;

import java.util.List;

public class ViewApartment {

    Validate validate = new Validate();

    public void displayAllRoom(List<Room> rooms) {
        for (Room room : rooms) {
            System.out.println(room.toString());
        }
    }

    public Room addRoom() {
        String roomNumber = "R-" + (int) (Math.random() * 100);
        String typeRoom = validate.inputNewTypeRoom();
        double price = validate.inputValidPrice();
        Room room = new Room(roomNumber, typeRoom, price);
        System.out.println("===+> Add Success!!");
        return room;
    }

    public int viewUpdateRoom() {
        System.out.println("1. Edit Type Room\n" +
                "2. Edit Price\n" +
                "3. Turn around\n" +
                "Select activity");
        int choice = Input.getInt();
        return choice;
    }

    public String findRoom() {
        System.out.println("Enter Room Number: ");
        String roomNumber = Input.getString();
        return roomNumber;
    }

    public boolean confirm() {
        do {
            System.out.println("Enter 'Y' to delete, 'N' to cancel");
            String confirm = Input.getString();
            if (confirm.equalsIgnoreCase("Y")) {
                return true;
            } else if (confirm.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.out.println("Your selection is not valid!");
            }
        } while (true);
    }

    public String viewDelete() {
        System.out.println("Enter Room Number u want to remove:");
        return Input.getString();
    }

    public String viewGetRoomNumber() {
        System.out.println("Enter room number you want to booking: ");
        return Input.getString();
    }

    public Booking add(Room room) {
        do {
            String bookingCode = "BK-" + (int) (Math.random() * 1000);
            System.out.println("Enter guest name: ");
            String guestName = Input.getString();
            System.out.println("Enter day check in(dd-MM-yyyy): ");
            String checkIn = Input.getString();
            if (!validate.isValidate(checkIn)) {
                System.out.println("Formatter is not valid!!");
                continue;
            }
            System.out.println("Enter day check out(dd-MM-yyyy): ");
            String checkOut = Input.getString();
            if (!validate.isValidate(checkOut)) {
                System.out.println("Formatter is not valid!!");
                continue;
            }
            System.out.println("Enter payment status(paid/unpaid): ");
            String status = Input.getString().toLowerCase();
            if (!status.equals("paid") && !status.equals("unpaid")) {
                continue;
            }
            return new Booking(room, bookingCode, guestName, checkIn, checkOut, status);
        } while (true);
    }

    public int viewUpdateBooking() {
        int select = -1;
        do {
            System.out.println("1. Update guest name\n" +
                    "2. Update CheckIn\n" +
                    "3. Update CheckOut\n" +
                    "0. Back to menu\n" +
                    "Select activity");
            try {
                select = Input.getInt();
            } catch (NumberFormatException e) {
                System.out.println("Format is not valid!");
            }
        } while (select < 0 || select > 3);
        return select;
    }

    public void viewRoomNotFound() {
        System.out.println("Room not found!");
    }

    public void viewAllBooking(List<Booking> bookings) {
        String leftAlignFormat = "| %-11s | %-25s | %-10s | %-11s | %-10s | %-10s | %-13s | %-11s |%n";
        System.out.println("--------------------------------------------------------LIST BOOKING---------------------------------------------------------\n");
        System.out.format("+-------------+---------------------------+------------+-------------+------------+------------+---------------+-------------+%n");
        System.out.format("| Booking code| Guest Name                | Room Number| Unit Price  |CheckIn     |CheckOut    | Date Creating |Total        |%n");
        System.out.format("+-------------+---------------------------+------------+-------------+------------+------------+---------------+-------------+%n");

        for (Booking booking : bookings) {
            System.out.format(leftAlignFormat, booking.getCode(), booking.getGuestName(), booking.getRoom().getRoomNumber()
                    , booking.getRoom().getPrice(), booking.getCheckIn(), booking.getCheckOut(), booking.getDateCreating(), booking.getTotal());
        }

        System.out.format("+-------------+---------------------------+------------+-------------+------------+------------+---------------+-------------+%n");
    }

    public String viewSearchBooking(){
        System.out.println("Enter booking code to found: ");
        return  Input.getString();
    }

    public String viewUpdateDate(){
        do{
            System.out.println("Enter another check-in date(dd-MM-yyyy): ");
            String date = Input.getString();
            if (!validate.isValidate(date)) {
                System.out.println("Formatter is not valid!!");
                continue;
            }
            return date;
        }while (true);
    }

    public String viewUpdateGuestName() {
        System.out.println("Enter new guest name: ");
        return Input.getString();
    }



}
