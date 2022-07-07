package pojos;

import java.util.Objects;

public class BookingOnCreate {
    private int bookingid;
    private Booking booking;

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "BookingOnCreate{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookingOnCreate booking1)) return false;
        return bookingid == booking1.bookingid && booking.equals(booking1.booking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingid, booking);
    }
}
