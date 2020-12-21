package com.wdzfxs.farm.mobiledevicefarmsvc.service;

import com.wdzfxs.farm.mobiledevicefarmsvc.cmd.ConsoleCommands;
import com.wdzfxs.farm.mobiledevicefarmsvc.cmd.Parser;
import com.wdzfxs.farm.mobiledevicefarmsvc.model.Device;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class DeviceServiceImpl implements DeviceService {

    private static final Pattern PATTERN = Pattern.compile("^(([a-zA-Z0-9_-]{2,}\\s+)|(\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}[:]\\d{1,5}\\s+))device\\s(usb:.+\\s)?product:.+\\smodel:.+\\sdevice:.+(\\s)?($)?(transport_id:\\d{1,5})?($)?", Pattern.MULTILINE);

    @Override
    public List<Device> getConnectedDevices() {
        ConsoleCommands cc = new ConsoleCommands();
        String output = cc.adbDevices()
                .replaceAll("List of devices attached(\\s)?", "")
                .replaceAll("\\s{2,}", " ");

        Matcher matcher = PATTERN.matcher(Objects.requireNonNull(output));

        List<Device> devices = new ArrayList<>();

        while (matcher.find()) {
            devices.add(Parser.parse(matcher.group()));
        }

        return devices;
    }
}