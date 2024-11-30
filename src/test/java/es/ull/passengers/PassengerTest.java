package es.ull.passengers;

import es.ull.flights.Flight;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class PassengerTest {

    private Passenger passenger;
    private Flight flight;

    @BeforeEach
    void setUp() {
        passenger = new Passenger("ID123", "John Doe", "US");
        flight = new Flight("AA1234", 2);
    }

    @Nested
    @DisplayName("Given a passenger with valid details")
    class PassengerCreationTests {

        @Test
        @DisplayName("Then the passenger should be created successfully")
        void testPassengerConstructor_ValidCountryCode() {
            assertDoesNotThrow(() -> new Passenger("ID456", "Jane Doe", "GB"), "Passenger creation should not throw an exception for valid country code");
        }

        @Test
        @DisplayName("Then it should throw an exception for an invalid country code")
        void testPassengerConstructor_InvalidCountryCode() {
            RuntimeException exception = assertThrows(RuntimeException.class, () -> new Passenger("ID456", "Jane Doe", "INVALID"));
            assertEquals("Invalid country code", exception.getMessage(), "Exception message should indicate invalid country code");
        }
    }

    @Nested
    @DisplayName("When the passenger joins a flight")
    class JoinFlightTests {

        @Test
        @DisplayName("Then the passenger should join the flight successfully")
        void testJoinFlight_Success() {
            passenger.joinFlight(flight);
            assertEquals(flight, passenger.getFlight(), "Passenger's flight should be set correctly");
            assertEquals(1, flight.getNumberOfPassengers(), "Number of passengers should be 1 after the passenger joins");
        }

        @Test
        @DisplayName("Then the passenger should be able to change flights")
        void testJoinFlight_ChangeFlight() {
            Flight newFlight = new Flight("BA5678", 2);
            passenger.joinFlight(flight);
            passenger.joinFlight(newFlight);
            assertEquals(newFlight, passenger.getFlight(), "Passenger should be assigned to the new flight");
            assertEquals(0, flight.getNumberOfPassengers(), "Previous flight should have no passengers after passenger changes flight");
            assertEquals(1, newFlight.getNumberOfPassengers(), "New flight should have 1 passenger after the passenger joins");
        }

        @Test
        @DisplayName("Then it should throw an exception if there are no seats available")
        void testJoinFlight_NoSeatsLeft() {
            flight.addPassenger(new Passenger("ID001", "Jane Doe", "GB"));
            flight.addPassenger(new Passenger("ID002", "Alice Smith", "CA"));
            RuntimeException exception = assertThrows(RuntimeException.class, () -> passenger.joinFlight(flight));
            assertEquals("Not enough seats for flight AA1234", exception.getMessage(), "Exception message should indicate no seats available");
        }

        @Test
        @DisplayName("Then it should allow the passenger to leave the flight by setting flight to null")
        void testLeaveFlight() {
            passenger.joinFlight(flight);
            passenger.joinFlight(null);  // Leaving the flight
            assertNull(passenger.getFlight(), "Passenger should not be assigned to any flight");
            assertEquals(0, flight.getNumberOfPassengers(), "Flight should have no passengers after passenger leaves");
        }

        @Test
        @DisplayName("Then it should throw an exception if the passenger cannot be removed from the previous flight")
        void testJoinFlight_CannotRemovePassenger() {
            Flight previousFlight = new Flight("BA1234", 2) {
                @Override
                public boolean removePassenger(Passenger passenger) {
                    return false; // Simulate failure to remove passenger
                }
            };
            passenger.joinFlight(previousFlight);
            RuntimeException exception = assertThrows(RuntimeException.class, () -> passenger.joinFlight(flight));
            assertEquals("Cannot remove passenger", exception.getMessage(), "Exception message should indicate that the passenger could not be removed from the previous flight");
        }

        @Test
        @DisplayName("Then it should throw an exception if the passenger cannot be added to the new flight")
        void testJoinFlight_CannotAddPassenger() {
            Flight newFlight = new Flight("BA5678", 2) {
                @Override
                public boolean addPassenger(Passenger passenger) {
                    return false; // Simulate failure to add passenger
                }
            };
            RuntimeException exception = assertThrows(RuntimeException.class, () -> passenger.joinFlight(newFlight));
            assertEquals("Cannot add passenger", exception.getMessage(), "Exception message should indicate that the passenger could not be added to the new flight");
        }
    }

    @Nested
    @DisplayName("Additional Passenger Method Tests")
    class AdditionalMethodTests {

        @Test
        @DisplayName("Then the passenger's flight should be set correctly using setFlight")
        void testSetFlight() {
            passenger.setFlight(flight);
            assertEquals(flight, passenger.getFlight(), "Flight should be set correctly using setFlight method");
        }

        @Test
        @DisplayName("Then the toString method should return the correct string representation")
        void testToString() {
            String expected = "Passenger John Doe with identifier: ID123 from US";
            assertEquals(expected, passenger.toString(), "toString method should return the correct representation of the passenger");
        }

        @Test
        @DisplayName("Then it should handle null flight in joinFlight without errors")
        void testJoinFlight_NullFlight() {
            assertDoesNotThrow(() -> passenger.joinFlight(null), "Passenger should be able to leave a flight by passing null");
        }
    }
}
