package steps.decorators;

import core.PipelineContext;
import exceptions.StepExecutionException;
import steps.WorkflowStep;

import java.util.function.Predicate;

public class ConditionalDecorator extends StepDecorator {
    private final Predicate<PipelineContext> condition;

    public ConditionalDecorator(WorkflowStep wrappedStep, Predicate<PipelineContext> condition) {
        super(wrappedStep);
        this.condition = condition;
    }

    @Override
    public void execute(PipelineContext context) throws StepExecutionException {
        if (condition.test(context)) {
            wrappedStep.execute(context);
        } else {
            context.addLog("ConditionalDecorator", "Condition not met. Skipping step.");
        }
    }
}
