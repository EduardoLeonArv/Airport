/**
 * @file Passenger.java
 * @brief Clase que representa un pasajero.
 *
 * Esta clase gestiona la información de un pasajero, incluyendo su identificador único, nombre, país de origen, y vuelo asociado.
 */

package es.ull.passengers;

import java.util.Arrays;
import java.util.Locale;
import es.ull.flights.Flight;

/**
 * Clase que representa un pasajero.
 * Esta clase gestiona la información de un pasajero, incluyendo su identificador único, nombre, país de origen y vuelo asociado.
 */
public class Passenger {

    /** Identificador único del pasajero. */
    private String identifier;

    /** Nombre completo del pasajero. */
    private String name;

    /** Código del país del pasajero en formato ISO. */
    private String countryCode;

    /** Vuelo asignado al pasajero. */
    private Flight flight;

    /**
     * Constructor de la clase Passenger.
     *
     * @param identifier Identificador único del pasajero.
     * @param name Nombre completo del pasajero.
     * @param countryCode Código del país en formato ISO.
     * @throws RuntimeException Si el código de país no es válido.
     */
    public Passenger(String identifier, String name, String countryCode) {
        this.identifier = identifier;
        this.name = name;
        this.countryCode = countryCode;
    }

    /**
     * Obtiene el identificador del pasajero.
     *
     * @return Identificador del pasajero.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Obtiene el nombre del pasajero.
     *
     * @return Nombre del pasajero.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene el código de país del pasajero.
     *
     * @return Código de país en formato ISO.
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Asigna un vuelo al pasajero.
     *
     * @param flight Vuelo que se asignará al pasajero.
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    /**
     * Obtiene el vuelo asociado al pasajero.
     *
     * @return Vuelo asociado al pasajero.
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * Representa el pasajero como una cadena de texto.
     *
     * @return Representación en cadena del pasajero.
     */
    @Override
    public String toString() {
        return "Passenger{" +
                "identifier='" + identifier + '\'' +
                ", name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", flight=" + (flight != null ? flight.getFlightNumber() : "No flight") +
                '}';
    }
}
