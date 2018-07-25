package com.stpl.gtn.gtn2o.ws.module.bcp.service;

public class GtnWsProcessService {

    private GtnWsProcessService() {
        super();
    }

    public static ProcessBuilder createProcess(String[] commands) {
        return new ProcessBuilder(doSanitize(commands));
    }

    public static String[] doSanitize(String[] command) {
        for (String commandTemp : command) {
            if (commandTemp.contains("rm")) {
                throw new IllegalArgumentException("Process Service can't accept commands with rm");
            }
        }
        return command;
    }
}
