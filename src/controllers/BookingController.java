package controllers;
import models.Booking;
import models.Room;
import repositories.BookingRepo;
import service.BookingService;
import service.RoomService;
import ultils.Input;
import views.ViewApartment;

import java.util.ArrayList;
import java.util.List;

public class BookingController {
    private final RoomController roomController;
    private final List<Booking> list;
    private final ViewApartment view = new ViewApartment();
    private RoomService roomService = new RoomService();
    private BookingService bookingService = new BookingService();


    public BookingController(RoomController roomController) {
        this.roomController = roomController;
        list = new ArrayList<>();
    }
    public void menuBooking() {
        while (true) {
            int select;
            System.out.println("""
                     ========MENU BOOKING=======
                    |   1. Creating booking     |
                    |   2. Display all Booking  |
                    |   3. Searching booking    |
                    |   4. Edit booking         |\s
                    |  5. Return main menu      |
                    Choose activity:\s""");
            select = Input.getInt();
            switch (select) {
                case 1:
                    String roomNumber =  view.viewGetRoomNumber();
                    Room room =  roomService.findRoomByRoomNumber(roomNumber);
                    if (room != null) {
                        Booking booking = view.add(room);
                        bookingService.add(booking);
                    } else {
                        view.viewRoomNotFound();
                }
                case 2:
                        List<Booking> bookings = bookingService.getAllBooking();
                        view.viewAllBooking(bookings);
                    break;
                case 3:
                    String bookingCode = view.viewSearchBooking();
                    bookingService.searchBooking(bookingCode);
                    break;
                case 4:
                    String code = view.viewSearchBooking();
                    Booking booking = bookingService.searchBooking(code);
                    menuUpdate(booking);
                case 5:
                default:
                    System.out.println("This select is not available!!");
            }
        }
    }

    public void menuUpdate(Booking booking) {
        int choice;
        String str;
        while (true) {
            choice = view.viewUpdateBooking();
            switch (choice) {
                case 1:
                    str = view.viewUpdateGuestName();
                    bookingService.updateGuestName(str, booking.getGuestName());
                    break;
                case 2:
                    str = view.viewUpdateDate();
                    bookingService.updateCheckIn(str, booking.getCheckIn());
                    break;
                case 3:
                    str = view.viewUpdateDate();
                    bookingService.updateCheckOut(str, booking.getCheckOut());
                    break;
                case 0:
                    return;
            }
        }
    }
}


