package steps.base;

import core.PipelineContext;
import exceptions.StepExecutionException;
import steps.WorkflowStep;

public class GenerateStep implements WorkflowStep {
    @Override
    public void execute(PipelineContext context) throws StepExecutionException {

        String input = context.getPayload();
        context.setPayload("Generated product description based on: " + input);
        context.addLog("GenerateStep", "Successfully generated description.");
    }
}
