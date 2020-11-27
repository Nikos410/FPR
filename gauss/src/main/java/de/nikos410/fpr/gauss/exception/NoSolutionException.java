package de.nikos410.fpr.gauss.exception;

/**
 * Signals that the Gauss algorithm could not be executed because the given input has no real solution.
 *
 * @author Nikos Epping
 */
public class NoSolutionException extends GaussAlgorithmException {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return "Matrix has no real solution.";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLocalizedMessage() {
        return "Matrix besitzt keine reelle Lösung.";
    }
}
