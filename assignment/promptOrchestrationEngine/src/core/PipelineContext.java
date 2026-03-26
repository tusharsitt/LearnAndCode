package core;

import java.util.HashMap;
import java.util.Map;

public class PipelineContext {
    private String payload;
    private final Map<String, String> traceLogs;

    public PipelineContext(String initialPayload) {
        this.payload = initialPayload;
        this.traceLogs = new HashMap<>();
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public void addLog(String stepName, String log) {
        traceLogs.put(stepName, log);
    }

    public void printLogs() {
        System.out.println("--- Workflow Trace Logs ---");
        traceLogs.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
