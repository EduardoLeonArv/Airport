package es.ull.flights;

import es.ull.passengers.Passenger;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FlightTest {

    private Flight flight;
    private Passenger passenger;

    @BeforeEach
    void setUp() {
        flight = new Flight("AA1234", 2);
        passenger = new Passenger("ID123", "John Doe", "US");
    }

    @Nested
    @DisplayName("Given a flight with valid details")
    class FlightCreationTests {

        @Test
        @DisplayName("Then it should be created successfully")
        void testFlightConstructor_ValidFlightNumber() {
            assertDoesNotThrow(() -> new Flight("BA456", 3), "Flight creation should not throw an exception for valid flight number");
        }

        @Test
        @DisplayName("Then it should throw an exception for an invalid flight number")
        void testFlightConstructor_InvalidFlightNumber() {
            RuntimeException exception = assertThrows(RuntimeException.class, () -> new Flight("INVALID", 3));
            assertEquals("Invalid flight number", exception.getMessage(), "Exception message should indicate invalid flight number");
        }
    }

    @Nested
    @DisplayName("When adding a passenger to the flight")
    class AddPassengerTests {

        @Test
        @DisplayName("Then it should add the passenger successfully if seats are available")
        void testAddPassenger_Success() {
            assertTrue(flight.addPassenger(passenger), "Passenger should be added successfully");
            assertEquals(1, flight.getNumberOfPassengers(), "Number of passengers should be 1 after adding a passenger");
        }

        @Test
        @DisplayName("Then it should throw an exception if no seats are available")
        void testAddPassenger_NoSeatsLeft() {
            flight.addPassenger(new Passenger("ID001", "Jane Doe", "GB"));
            flight.addPassenger(new Passenger("ID002", "Alice Smith", "CA"));
            RuntimeException exception = assertThrows(RuntimeException.class, () -> flight.addPassenger(passenger));
            assertEquals("Not enough seats for flight AA1234", exception.getMessage(), "Exception message should indicate no seats available");
        }
    }

    @Nested
    @DisplayName("When removing a passenger from the flight")
    class RemovePassengerTests {

        @Test
        @DisplayName("Then it should remove the passenger successfully if present")
        void testRemovePassenger_Success() {
            flight.addPassenger(passenger);
            assertTrue(flight.removePassenger(passenger), "Passenger should be removed successfully");
            assertEquals(0, flight.getNumberOfPassengers(), "Number of passengers should be 0 after removing a passenger");
        }

        @Test
        @DisplayName("Then it should fail if the passenger is not on the flight")
        void testRemovePassenger_NotFound() {
            assertFalse(flight.removePassenger(passenger), "Removing a non-existent passenger should return false");
        }
    }
}