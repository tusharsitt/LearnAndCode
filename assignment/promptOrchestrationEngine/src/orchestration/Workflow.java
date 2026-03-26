package orchestration;

import core.PipelineContext;
import steps.WorkflowStep;

import java.util.List;

public class Workflow {
    private List<WorkflowStep> steps;

    public Workflow(List<WorkflowStep> steps) {
        this.steps = steps;
    }

    public PipelineContext run(String initialInput) {
        PipelineContext context = new PipelineContext(initialInput);

        for (WorkflowStep step : steps) {
            step.execute(context);
        }

        return context;
    }
}
