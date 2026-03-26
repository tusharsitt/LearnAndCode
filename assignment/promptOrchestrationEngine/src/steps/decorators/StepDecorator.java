package steps.decorators;

import steps.WorkflowStep;

public abstract class StepDecorator implements WorkflowStep {
    protected WorkflowStep wrappedStep;

    public StepDecorator(WorkflowStep wrappedStep) {
        this.wrappedStep = wrappedStep;
    }
}
