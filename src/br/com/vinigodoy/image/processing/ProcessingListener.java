package br.com.vinigodoy.image.processing;

public interface ProcessingListener {
    void operationStarted(ProcessingEvent evt);

    void operationProgress(ProcessingEvent evt);

    void operationEnded(ProcessingEvent evt);
}
