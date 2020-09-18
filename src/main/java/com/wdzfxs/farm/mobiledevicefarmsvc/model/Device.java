package com.wdzfxs.farm.mobiledevicefarmsvc.model;

public class Device {

    private String address;
    private String usb;
    private String product;
    private String model;
    private String device;
    private int transportId;

    public Device() {
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public int getTransportId() {
        return transportId;
    }

    public void setTransportId(int transportId) {
        this.transportId = transportId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsb() {
        return usb;
    }

    public void setUsb(String usb) {
        this.usb = usb;
    }
}