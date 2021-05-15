package com.example.objects;

import java.util.Date;
import java.util.Map;

public class Associate {
    private String code;
    private String fullName;
    private String address;
    private Date dateOfBirth;
    private String resourceManager;
    private Map<String, String> metadata;
    private String role; // DEV, HR, SSA

    public Associate(){};

    public Associate(String code, String fullName, String address, Date dateOfBirth, String resourceManager, Map<String, String> metadata, String role){
        this.code = code;
        this.fullName = fullName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.resourceManager = resourceManager;
        this.metadata = metadata;
        this.role = role;
    }

    public Associate(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getResourceManager() {
        return resourceManager;
    }

    public void setResourceManager(String resourceManager) {
        this.resourceManager = resourceManager;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
