package app;

import core.PipelineContext;
import orchestration.Workflow;
import orchestration.WorkflowBuilder;
import steps.base.GenerateStep;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting AI Prompt Orchestration Engine...");

        Workflow workflow = new WorkflowBuilder()
                .addStep(new GenerateStep())
                .addConditionalStep(
                        new GenerateStep(),
                        context -> context.getPayload().contains("Summary")
                )
                .build();

        PipelineContext finalContext = workflow.run("User query about tech specs");

        System.out.println("\nFinal Output: " + finalContext.getPayload());
        System.out.println("");
        finalContext.printLogs();
    }
}
