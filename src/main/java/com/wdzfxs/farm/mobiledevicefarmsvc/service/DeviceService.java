package com.wdzfxs.farm.mobiledevicefarmsvc.service;

import com.wdzfxs.farm.mobiledevicefarmsvc.model.Device;

import java.util.List;

public interface DeviceService {

    /*
    Get a list of connected mobile devices (Android) to the farm
     */
    List<Device> getConnectedDevices();
}