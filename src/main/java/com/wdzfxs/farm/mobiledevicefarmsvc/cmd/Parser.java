package com.wdzfxs.farm.mobiledevicefarmsvc.cmd;

import com.wdzfxs.farm.mobiledevicefarmsvc.model.Device;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Parser {

    private static final Pattern ADDRESS_PATTERN = Pattern.compile("^((\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}[:]\\d{1,5})|([a-zA-Z0-9_-]{2,}))");
    private static final Pattern USB_PATTERN = Pattern.compile("usb:[a-zA-Z0-9_-]*\\S");
    private static final Pattern PRODUCT_PATTERN = Pattern.compile("product:[a-zA-Z0-9_-]*\\S");
    private static final Pattern MODEL_PATTERN = Pattern.compile("model:[a-zA-Z0-9_-]*\\S");
    private static final Pattern DEVICE_PATTERN = Pattern.compile("device:[a-zA-Z0-9_-]*\\S");
    private static final Pattern TRANSPORT_ID_PATTERN = Pattern.compile("transport_id:[0-9]{1,9}$");

    public static Device parse(String executionResult) {
        log.info("Incoming to parse: " + executionResult);
        Device device = new Device();

        Matcher matcher;

        matcher = ADDRESS_PATTERN.matcher(executionResult);
        device.setAddress(matcher.find() ? matcher.group() : null);

        matcher = USB_PATTERN.matcher(executionResult);
        device.setUsb(matcher.find() ? matcher.group().substring(4) : null);

        matcher = PRODUCT_PATTERN.matcher(executionResult);
        device.setProduct(matcher.find() ? matcher.group().substring(8) : null);

        matcher = MODEL_PATTERN.matcher(executionResult);
        device.setModel(matcher.find() ? matcher.group().substring(6) : null);

        matcher = DEVICE_PATTERN.matcher(executionResult);
        device.setDevice(matcher.find() ? matcher.group().substring(7) : null);

        matcher = TRANSPORT_ID_PATTERN.matcher(executionResult);
        device.setTransportId(matcher.find() ? Integer.parseInt(matcher.group().substring(13)) : -1);

        return device;
    }

    public static String parse(String executionResult, String regex) {
        log.info("Incoming to parse: " + executionResult);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(executionResult);

        return matcher.find() ? matcher.group() : null;
    }
}