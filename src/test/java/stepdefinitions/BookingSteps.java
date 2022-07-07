package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import pojos.Booking;
import pojos.BookingDates;
import pojos.BookingOnCreate;
import utilities.Api;
import utilities.Context;

import static org.hamcrest.Matchers.notNullValue;

public class BookingSteps extends Api {

    Booking booking;
    BookingOnCreate bookingOnCreate;
    ValidatableResponse res;

    @Given("a booking with following details:")
    public void aBookingWithFollowingDetails(Booking bookingDt) {
        booking = bookingDt;
    }

    @Given("following booking dates:")
    public void followingBookingDates(BookingDates bookingDatesDt) {
        booking.setBookingdates(bookingDatesDt);
    }

    @When("^I create the booking(?: (.*))?$")
    public void iCreateTheBooking(String testCase) {
        res = post(BOOKING, booking);
    }

    @Then("the status code should be {int}")
    public void theStatusCodeShouldBeStatusCode(int statusCode) {
        res.assertThat().statusCode(statusCode);
    }

    @Then("response should contain booking details with an id")
    public void responseShouldContainBookingDetailsWithAnId() {
        res.assertThat().body("bookingid", notNullValue());
        bookingOnCreate = res.extract().as(BookingOnCreate.class);
        System.setProperty(Context.BOOKING_ID, String.valueOf(bookingOnCreate.getBookingid()));

        Assert.assertEquals("response contains different booking details",
                booking, bookingOnCreate.getBooking());
    }
}
