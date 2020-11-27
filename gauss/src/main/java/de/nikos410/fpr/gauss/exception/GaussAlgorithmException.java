package de.nikos410.fpr.gauss.exception;

public abstract class GaussAlgorithmException extends RuntimeException {
    @Override
    public abstract String getMessage();

    @Override
    public abstract String getLocalizedMessage();
}
