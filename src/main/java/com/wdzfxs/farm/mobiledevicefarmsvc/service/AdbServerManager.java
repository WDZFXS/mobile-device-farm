package com.wdzfxs.farm.mobiledevicefarmsvc.service;

import com.wdzfxs.farm.mobiledevicefarmsvc.cmd.ConsoleCommands;
import com.wdzfxs.farm.mobiledevicefarmsvc.util.AdbNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Component
public class AdbServerManager {

    private final ConsoleCommands cc = new ConsoleCommands();

    @PostConstruct
    public void startAdbServer() {
        try {
            cc.startAdbServer();
        } catch (AdbNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    @PreDestroy
    public void stopAdbServer() {
        cc.exec("adb kill-server");
    }
}