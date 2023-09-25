package com.techcreate.entity;

public class Person {
    private String uuid;
    private String name;
    private String id;
    private String dob;
    private String uuidSimilar;

    public String getUuidSimilar() {
        return uuidSimilar;
    }

    public void setUuidSimilar(String uuidSimilar) {
        this.uuidSimilar = uuidSimilar;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDob() {
        return dob;
    }

    public String toString() {
        return "[uuid: "+uuid+" ,name: "+name+" ,id: "+id+" ,dob: "+dob+" ,uuidSimilar: "+uuidSimilar+"]";
    }
}
