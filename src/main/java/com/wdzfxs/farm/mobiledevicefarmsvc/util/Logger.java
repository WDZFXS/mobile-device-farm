package com.wdzfxs.farm.mobiledevicefarmsvc.util;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.Scanner;

@Slf4j
public class Logger {

    public static void logConsoleOutput(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNextLine()) {
            log.info("Console output:" + scanner.nextLine());
        }
    }

    public static void logErrorStream(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNextLine()) {
            log.error("Console ERROR:" + scanner.nextLine());
        }
    }
}