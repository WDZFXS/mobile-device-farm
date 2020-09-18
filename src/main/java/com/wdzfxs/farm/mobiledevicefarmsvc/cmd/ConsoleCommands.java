package com.wdzfxs.farm.mobiledevicefarmsvc.cmd;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import static com.wdzfxs.farm.mobiledevicefarmsvc.util.Logger.logErrorStream;

@Slf4j
public class ConsoleCommands {

    public static final String USER = getCurrentUser();
    public static final String HOME = "/Users/" + USER;
    public static final String ANDROID_HOME = findAndroidHomePath();
    public static final String ADB_PATH = ANDROID_HOME + "/platform-tools/adb";

    public static String adbDevices() {
        String consoleOutput = exec(ADB_PATH + " devices -l");
        log.info("Adb devices: " + consoleOutput);

        return consoleOutput;
    }

    private static String findAndroidHomePath() {
        String consoleOutput = exec("cat " + HOME + "/.bash_profile");
        consoleOutput = Parser.parse(consoleOutput, "[$a-zA-Z0-9]?[a-zA-Z0-9/$-]*[/]Android[/]sdk");
        if (Objects.requireNonNull(consoleOutput).contains("$")) {
            if (consoleOutput.contains("$HOME")) {
                consoleOutput = consoleOutput.replaceAll("[$]HOME", HOME);
            } else
                log.error("ANDROID_PATH NOT FOUND");
        }
        return consoleOutput;
    }

    private static String getCurrentUser() {
        String user = exec("whoami");
        log.info("Current user: " + user);

        return user;
    }

    private static String exec(String command) {
        log.info("Command execution: " + command);
        Process process = null;

        try {
            process = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert process != null;

        logErrorStream(process.getErrorStream());

        Scanner scanner = new Scanner(process.getInputStream());
        StringBuilder stringBuilder = new StringBuilder();

        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }

        return stringBuilder.toString();
    }

}