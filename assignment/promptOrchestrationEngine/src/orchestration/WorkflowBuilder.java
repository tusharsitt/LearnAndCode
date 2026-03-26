package orchestration;

import core.PipelineContext;
import steps.WorkflowStep;
import steps.decorators.ConditionalDecorator;
import steps.decorators.FallbackDecorator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class WorkflowBuilder {
    private final List<WorkflowStep> steps = new ArrayList<>();

    public WorkflowBuilder addStep(WorkflowStep step) {
        steps.add(step);
        return this;
    }

    public WorkflowBuilder addStepWithFallback(WorkflowStep primaryStep, WorkflowStep fallbackStep) {
        steps.add(new FallbackDecorator(primaryStep, fallbackStep));
        return this;
    }

    public WorkflowBuilder addConditionalStep(WorkflowStep step, Predicate<PipelineContext> condition) {
        steps.add(new ConditionalDecorator(step, condition));
        return this;
    }

    public Workflow build() {
        return new Workflow(steps);
    }
}