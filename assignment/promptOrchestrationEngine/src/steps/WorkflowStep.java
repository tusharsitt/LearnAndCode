package steps;

import core.PipelineContext;
import exceptions.StepExecutionException;

public interface WorkflowStep {
    void execute(PipelineContext context) throws StepExecutionException;
}
