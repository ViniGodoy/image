package br.com.vinigodoy.image.filters;

public interface FilterListener {
    void filterStarted(FilterEvent evt);

    void filterProgress(FilterEvent evt);

    void filterEnded(FilterEvent evt);
}
