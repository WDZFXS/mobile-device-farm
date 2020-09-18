package com.wdzfxs.farm.mobiledevicefarmsvc.service;

import com.wdzfxs.farm.mobiledevicefarmsvc.cmd.ConsoleCommands;
import com.wdzfxs.farm.mobiledevicefarmsvc.cmd.Parser;
import com.wdzfxs.farm.mobiledevicefarmsvc.model.Device;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DeviceServiceImpl implements DeviceService {

    private static final Pattern pattern = Pattern.compile("^(([a-zA-Z0-9_-]{2,}\\s+)|(\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}[:]\\d{1,5}\\s+))device\\s(usb:.+\\s)?product:.+\\smodel:.+\\sdevice:.+\\stransport_id:\\d{1,5}$", Pattern.MULTILINE);

    @Override
    public List<Device> getConnectedDevices() {
        String output = ConsoleCommands.adbDevices()
                .replaceAll("List of devices attached", "")
                .replaceAll("\\s{2,}", " ");
        Matcher matcher = pattern.matcher(output);

        List<Device> devices = new ArrayList<>();

        while (matcher.find()) {
            devices.add(Parser.parse(matcher.group()));
        }

        return devices;
    }
}