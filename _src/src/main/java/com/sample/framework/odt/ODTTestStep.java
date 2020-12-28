package com.sample.framework.odt;

public abstract class ODTTestStep extends ODTRunner {

    public ODTTestStep() {
        // TODO Auto-generated constructor stub
    }

    public abstract void stepBody() throws Exception;
    @Override
    public void run() throws Exception {
        try {
            this.beforeRun();
            this.stepBody();
        } catch (Throwable e) {
            this.setPassedState(false);
            this.onError(e);
        }
        try {
            this.afterRun();
        } catch (Throwable e) {
            this.setPassedState(false);
            this.onError(e);
        }        
    }
}
