package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import pojos.Booking;
import pojos.BookingDates;
import pojos.BookingOnCreate;
import utilities.Api;
import utilities.EndpointBuilder;

import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static java.util.Objects.nonNull;
import static org.hamcrest.Matchers.equalTo;
import static utilities.EndpointBuilder.BOOKING;
import static utilities.EndpointBuilder.BOOKING_ID;

public class BookingSteps extends Api {

    private final String BOOKINGID = "bookingid";

    Booking actualBooking;
    BookingOnCreate bookingOnCreate;
    SoftAssertions softAssertions = new SoftAssertions();

    @Given("^(?:I want to update the|a)? booking with following details:$")
    public void aBookingWithFollowingDetails(Booking bookingDt) {
        actualBooking = bookingDt;
    }

    @Given("following booking dates:")
    public void followingBookingDates(BookingDates bookingDatesDt) {
        actualBooking.setBookingdates(bookingDatesDt);
    }

    @When("^I (?:create|have)? the booking(?: (.*))?$")
    public void iCreateABooking(String testCase) {
        post(BOOKING, actualBooking);
        if (response.extract().response().statusCode() == 200) {
            bookingOnCreate = response.extract().as(BookingOnCreate.class);
        }
    }

    @When("I update the booking")
    public void iUpdateTheBooking() {
        put(format(BOOKING_ID, bookingOnCreate.getBookingid()), actualBooking);
    }

    @When("I partially update the booking")
    public void iPartiallyUpdateTheBooking() {
        patch(format(BOOKING_ID, bookingOnCreate.getBookingid()), actualBooking);
    }

    @When("I make request to get all booking IDs")
    public void iMakeRequestToGetAllBookingIDs() {
        get(BOOKING);
    }

    @When("I make request to get all booking IDs by following filters:")
    public void iMakeRequestToGetAllBookingIDsByFollowingFilters(DataTable params) {
        String endpoint = EndpointBuilder.buildBookingEndpoint(params.asMap());
        get(endpoint);
    }

    @When("I delete the booking")
    public void iDeleteTheBooking() {
        deleteBooking();
    }

    @Then("the status code should be {int}")
    public void theStatusCodeShouldBeStatusCode(int statusCode) {
        response.assertThat().statusCode(statusCode);
    }

    @Then("response should contain( new) booking details")
    public void responseShouldContainNewBookingDetails() {
        Booking expectedBooking;
        if (nonNull(response.extract().path(BOOKINGID))) {
            expectedBooking = response.extract().as(BookingOnCreate.class).getBooking();
        } else {
            expectedBooking = response.extract().as(Booking.class);
        }

        Assert.assertEquals("response contains different booking details",
                actualBooking, expectedBooking);
    }

    @Then("response should contain following booking details:")
    public void responseShouldContainFollowingBookingDetails(DataTable bookingDt) {
        bookingDt.asMap()
                .forEach((String k, String v) -> response.body(k, equalTo(v)));
    }

    @Then("response should contain all booking IDs")
    public void responseShouldContainAllBookingIDs() {
        List<Map<String, Integer>> jsonAsArrayList = response.extract().jsonPath().get();

        jsonAsArrayList.forEach((Map<String, Integer> e) ->
                softAssertions.assertThat(e.containsKey(BOOKINGID))
                        .as("Check all objects have bookingid field")
                        .isTrue()
        );

        softAssertions.assertAll();
    }

    @Then("all IDs returned should belong to bookings with following attributes:")
    public void allIDsReturnedShouldBelongToBookingsWithFollowingAttributes(DataTable filtersDt) {
        List<Integer> bookingIDs = response.extract().jsonPath().get(BOOKINGID);
        bookingIDs.forEach((Integer id) -> {
            get(format(BOOKING_ID, id));
            filtersDt.asMap()
                    .forEach((String key, Object value) -> response.body(key, equalTo(value)));
        });
    }

    @After("@deleteBooking")
    public void deleteBooking() {
        delete(format(BOOKING_ID, bookingOnCreate.getBookingid()))
                .statusCode(201);
    }
}
