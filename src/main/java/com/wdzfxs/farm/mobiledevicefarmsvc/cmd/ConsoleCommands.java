package com.wdzfxs.farm.mobiledevicefarmsvc.cmd;

import com.wdzfxs.farm.mobiledevicefarmsvc.util.AdbNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static com.wdzfxs.farm.mobiledevicefarmsvc.util.Logger.logErrorStream;

@Slf4j
public class ConsoleCommands {

    public String adbDevices() {
        String consoleOutput = exec("adb devices -l");
        log.info("Adb devices: " + consoleOutput);

        return consoleOutput;
    }

    public void startAdbServer() throws AdbNotFoundException {
        try {
            Runtime.getRuntime().exec("adb start-server");
        } catch (IOException e) {
            log.info(e.getMessage());
            throw new AdbNotFoundException("Failed to start adb server. If adb is not installed, check" +
                    " https://developer.android.com/studio/command-line/adb. If adb is installed, make sure that the " +
                    "path to adb folder is added to $PATH");
        }
    }

    public String exec(String command) {
        log.info("Command execution: " + command);
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert process != null;

        try (InputStream inputStream = process.getErrorStream()) {
            logErrorStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String result = "";
        try (InputStream inputStream = process.getInputStream(); Scanner scanner = new Scanner(inputStream)) {
            StringBuilder stringBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine());
            }
            result = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}