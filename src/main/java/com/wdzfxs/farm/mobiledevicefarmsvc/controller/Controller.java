package com.wdzfxs.farm.mobiledevicefarmsvc.controller;

import com.wdzfxs.farm.mobiledevicefarmsvc.model.Device;
import com.wdzfxs.farm.mobiledevicefarmsvc.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    private final DeviceService deviceService;

    public Controller(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping(value = "/devices")
    public ResponseEntity<List<Device>> connectedDevices() {
        return new ResponseEntity<>(deviceService.getConnectedDevices(), HttpStatus.OK);
    }
}