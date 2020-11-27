package de.nikos410.fpr.gauss.exception;

public class NoResultException extends GaussAlgorithmException {
    @Override
    public String getMessage() {
        return "Matrix has no real result.";
    }

    @Override
    public String getLocalizedMessage() {
        return "Matrix besitzt keine reelle Lösung.";
    }
}
