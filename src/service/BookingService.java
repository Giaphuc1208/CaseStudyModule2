package service;

import models.Booking;
import models.Room;
import repositories.BookingRepo;
import ultils.Input;
import ultils.Validate;

import java.util.ArrayList;
import java.util.List;

public class BookingService {
    BookingRepo bookingRepo = new BookingRepo();
    Validate validate = new Validate();

    public void add(Booking booking) {
        bookingRepo.add(booking);
    }

    public List<Booking> getAllBooking() {
        return bookingRepo.getAll();
    }

    public Booking searchBooking(String key) {
        List<Booking> result = getAllBooking();
        for (Booking booking : result) {
            if (booking.getCode().equals(key)) {
                System.out.println(booking.toMain());
                return booking;
            }
        }
        if (result.isEmpty()) {
            System.out.println("Booking not found!!");
        }
        return null;
    }


    public void updateGuestName(String newName, String name) {
        List<Booking> bookings = getAllBooking();
        for (Booking booking : bookings) {
            if (booking.getGuestName().equals(name)) {
                booking.setGuestName(newName);
                break;
            }
        }
        bookingRepo.writeFileToBookingCSV(bookings, false);
    }

    public void updateCheckIn(String newCheckIn, String checkIn) {
        List<Booking> bookings = getAllBooking();
        for (Booking booking : bookings) {
            if (booking.getCheckIn().equals(checkIn)) {
                booking.setCheckIn(newCheckIn);
                break;
            }
        }
        bookingRepo.writeFileToBookingCSV(bookings, false);
    }

    public void updateCheckOut(String newCheckOut, String checkOut) {
        List<Booking> bookings = getAllBooking();
        for (Booking booking : bookings) {
            if (booking.getCheckOut().equals(checkOut)) {
                booking.setCheckOut(newCheckOut);
                break;
            }
        }
        bookingRepo.writeFileToBookingCSV(bookings, false);
    }

}


