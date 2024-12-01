package es.ull.passengers;

import java.util.Arrays;
import java.util.Locale;
import es.ull.flights.Flight;

/**
 * Clase que representa a un pasajero.
 */
public class Passenger {

    private String identifier;   // Identificador único del pasajero
    private String name;         // Nombre del pasajero
    private String countryCode;  // Código del país del pasajero (en formato ISO)
    private Flight flight;       // Vuelo asociado al pasajero

    /**
     * Constructor de la clase Passenger.
     *
     * @param identifier Identificador único del pasajero.
     * @param name Nombre del pasajero.
     * @param countryCode Código del país del pasajero (en formato ISO).
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
     * @return Código del país del pasajero.
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Obtiene el vuelo al que está asociado el pasajero.
     *
     * @return Vuelo asociado al pasajero.
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * Asigna un pasajero a un vuelo.
     *
     * @param flight Vuelo al que el pasajero se unirá.
     * @throws RuntimeException Si no se puede eliminar o añadir el pasajero del/ al vuelo.
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
     * Asigna un vuelo al pasajero.
     *
     * @param flight Vuelo a asignar al pasajero.
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    /**
     * Representación en cadena de la información del pasajero.
     *
     * @return Información del pasajero en formato de cadena.
     */
    @Override
    public String toString() {
        return "Passenger " + getName() + " with identifier: " + getIdentifier() + " from " + getCountryCode();
    }
}
