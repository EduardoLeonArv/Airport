/**
 * @file Flight.java
 * @brief Define la clase Flight que representa un vuelo.
 */

package es.ull.flights;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import es.ull.passengers.Passenger;

/**
 * @class Flight
 * @brief Clase que modela un vuelo, gestionando los pasajeros y asientos disponibles.
 */
public class Flight {

    private String flightNumber;    /**< Número de vuelo. */
    private int seats;              /**< Número de asientos disponibles. */
    private Set<Passenger> passengers = new HashSet<>();  /**< Conjunto de pasajeros del vuelo. */

    private static String flightNumberRegex = "^[A-Z]{2}\\d{3,4}$"; /**< Expresión regular para validar el número de vuelo. */
    private static Pattern pattern = Pattern.compile(flightNumberRegex); /**< Patrón compilado para validar números de vuelo. */

    /**
     * @brief Constructor de la clase Flight.
     * @param flightNumber Número del vuelo.
     * @param seats Número de asientos disponibles.
     * @throws RuntimeException Si el número del vuelo no es válido.
     */
    public Flight(String flightNumber, int seats) {
        Matcher matcher = pattern.matcher(flightNumber);
        if (!matcher.matches()) {
            throw new RuntimeException("Invalid flight number");
        }
        this.flightNumber = flightNumber;
        this.seats = seats;
    }

    /**
     * @brief Obtiene el número del vuelo.
     * @return El número del vuelo.
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * @brief Obtiene el número de pasajeros actualmente en el vuelo.
     * @return El número de pasajeros.
     */
    public int getNumberOfPassengers() {
        return passengers.size();
    }

    /**
     * @brief Añade un pasajero al vuelo.
     * @param passenger Objeto Passenger que representa al pasajero a añadir.
     * @return True si el pasajero fue añadido exitosamente.
     * @throws RuntimeException Si no hay asientos disponibles.
     */
    public boolean addPassenger(Passenger passenger) {
        if (getNumberOfPassengers() >= seats) {
            throw new RuntimeException("Not enough seats for flight " + getFlightNumber());
        }
        passenger.setFlight(this);
        return passengers.add(passenger);
    }

    /**
     * @brief Elimina un pasajero del vuelo.
     * @param passenger Objeto Passenger que representa al pasajero a eliminar.
     * @return True si el pasajero fue eliminado exitosamente.
     */
    public boolean removePassenger(Passenger passenger) {
        passenger.setFlight(null);
        return passengers.remove(passenger);
    }
}