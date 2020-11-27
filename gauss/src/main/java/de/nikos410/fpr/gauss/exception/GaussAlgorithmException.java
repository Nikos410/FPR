package de.nikos410.fpr.gauss.exception;

/**
 * The base exception for everything that goes wrong while executing the Gauss algorithm.
 *
 * @author Nikos Epping
 */
public abstract class GaussAlgorithmException extends RuntimeException {

    /**
     * Returns the detail message of this exception.
     *
     * @return The detail message of this exception
     */
    @Override
    public abstract String getMessage();

    /**
     * Returns the localized detail message of this exception.
     *
     * @return The localized detail message of this exception.
     */
    @Override
    public abstract String getLocalizedMessage();
}
