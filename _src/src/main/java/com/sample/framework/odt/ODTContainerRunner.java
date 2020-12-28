package com.sample.framework.odt;

public class ODTContainerRunner<T extends ODTRunner> extends ODTRunner {

    public ODTContainerRunner() {
        // TODO Auto-generated constructor stub
    }

    protected ODTTestStep[] beforeSteps = {};
    protected ODTTestStep[] afterSteps = {};
    protected T[] steps;

    public ODTTestStep[] getBeforeSteps() {
        return beforeSteps;
    }

    public ODTTestStep[] getAfterSteps() {
        return afterSteps;
    }

    public T[] getSteps() {
        return steps;
    }

    @Override
    public void run() throws Exception {
        try {
            beforeRun();
            for (T step : this.getSteps()) {
                    step.run();
            }
        } catch (Throwable e) {
            this.setPassedState(false);
            this.onError(e);
        }
        afterRun();
    }

    @Override
    public void beforeRun() throws Exception {
        for (ODTTestStep step : this.getBeforeSteps()) {
            step.run();
        }
    }

    @Override
    public void afterRun() throws Exception {
        for (ODTTestStep step : this.getAfterSteps()) {
            step.run();
        }
    }

}
