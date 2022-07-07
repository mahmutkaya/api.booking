package stepdefinitions;

import io.cucumber.java.After;
import utilities.Context;

import static utilities.Api.BOOKING_ID;
import static utilities.Api.delete;

public class Hooks {

    @After("@deleteBooking")
    public void deleteBooking() {
        delete(String.format(BOOKING_ID, System.getProperty(Context.BOOKING_ID)))
                .statusCode(201);
    }
}
