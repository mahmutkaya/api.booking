package stepdefinitions;

import io.cucumber.java.DataTableType;
import pojos.Booking;
import pojos.BookingDates;

import java.util.Map;

public class DataTableTypes {

    @DataTableType
    public Booking bookingEntry(Map<String, String> entry) {
        return new Booking(
                entry.get("firstname"),
                entry.get("lastname"),
                entry.get("totalprice"),
                entry.get("depositpaid"),
                entry.get("additionalneeds"));
    }

    @DataTableType
    public BookingDates bookingDatesEntry(Map<String, String> entry) {
        return new BookingDates(
                entry.get("checkin"),
                entry.get("checkout"));
    }
}
