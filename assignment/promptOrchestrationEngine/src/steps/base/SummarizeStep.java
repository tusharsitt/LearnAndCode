package steps.base;

import core.PipelineContext;
import exceptions.StepExecutionException;
import steps.WorkflowStep;

public class SummarizeStep implements WorkflowStep {
    @Override
    public void execute(PipelineContext context) throws StepExecutionException {
        String input = context.getPayload();
        context.setPayload("Summary: [" + input + "]");
        context.addLog("SummarizeStep", "Successfully summarized.");
    }
}