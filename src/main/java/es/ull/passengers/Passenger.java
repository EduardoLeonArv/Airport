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

public class Passenger {

    private String identifier;   ///< Identificador único del pasajero.
    private String name;         ///< Nombre completo del pasajero.
    private String countryCode;  ///< Código del país del pasajero en formato ISO.
    private Flight flight;       ///< Vuelo asignado al pasajero.

    /**
     * @brief Constructor de la clase Passenger.
     *
     * @param identifier Identificador único del pasajero.
     * @param name Nombre completo del pasajero.
     * @param countryCode Código del país en formato ISO.
     * @throws RuntimeException Si el código de país no es válido.
     */
    public Passenger(String identifier, String name, String countryCode) {
        if (!Arrays.asList(Locale.getISOCountries()).contains(countryCode)) {
            throw new RuntimeException("Invalid country code");
        }

        this.identifier = identifier;
        this.name = name;
        this.countryCode = countryCode;
    }

    /**
     * @brief Obtiene el identificador del pasajero.
     *
     * @return Identificador del pasajero.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @brief Obtiene el nombre del pasajero.
     *
     * @return Nombre completo del pasajero.
     */
    public String getName() {
        return name;
    }

    /**
     * @brief Obtiene el código de país del pasajero.
     *
     * @return Código de país en formato ISO.
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @brief Obtiene el vuelo asignado al pasajero.
     *
     * @return Vuelo asignado al pasajero o null si no tiene vuelo asignado.
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * @brief Asocia un pasajero a un vuelo.
     *
     * @param flight Vuelo al que el pasajero será asignado.
     * @throws RuntimeException Si no es posible asignar o remover el pasajero.
     */
    public void joinFlight(Flight flight) {
        Flight previousFlight = this.flight;
        if (null != previousFlight) {
            if (!previousFlight.removePassenger(this)) {
                throw new RuntimeException("Cannot remove passenger");
            }
        }
        setFlight(flight);
        if (null != flight) {
            if (!flight.addPassenger(this)) {
                throw new RuntimeException("Cannot add passenger");
            }
        }
    }

    /**
     * @brief Asigna un vuelo al pasajero.
     *
     * @param flight Vuelo a asignar.
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    /**
     * @brief Representación en cadena del pasajero.
     *
     * @return Información del pasajero en formato legible.
     */
    @Override
    public String toString() {
        return "Passenger " + getName() + " with identifier: " + getIdentifier() + " from " + getCountryCode();
    }
}
