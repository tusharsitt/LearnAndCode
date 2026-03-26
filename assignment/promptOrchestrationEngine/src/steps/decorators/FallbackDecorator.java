package steps.decorators;

import core.PipelineContext;
import exceptions.StepExecutionException;
import steps.WorkflowStep;

public class FallbackDecorator extends StepDecorator {
    private final WorkflowStep fallbackStep;

    public FallbackDecorator(WorkflowStep primaryStep, WorkflowStep fallbackStep) {
        super(primaryStep);
        this.fallbackStep = fallbackStep;
    }

    @Override
    public void execute(PipelineContext context) {
        try {
            wrappedStep.execute(context);
        } catch (StepExecutionException e) {
            context.addLog("FallbackDecorator", "Primary step failed: " + e.getMessage() + ". Executing fallback.");
            fallbackStep.execute(context);
        }
    }
}