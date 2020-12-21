package com.wdzfxs.farm.mobiledevicefarmsvc.model;

public class Device {

    private String address;
    private String usb;
    private String product;
    private String model;
    private String device;
    private Integer transportId;

    public Device() {
    }

    public Device(String address, String usb, String product, String model, String device, Integer transportId) {
        this.address = address;
        this.usb = usb;
        this.product = product;
        this.model = model;
        this.device = device;
        this.transportId = transportId;
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

    public Integer getTransportId() {
        return transportId;
    }

    public void setTransportId(Integer transportId) {
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