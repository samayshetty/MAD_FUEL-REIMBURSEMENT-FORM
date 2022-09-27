package com.example.frf;

public class employee {
    String name;
    String vehicletype;
    String vehiclenumber;
    String department;

    public employee(String name, String vehicletype, String vehiclenumber, String department) {
        this.name = name;
        this.vehicletype = vehicletype;
        this.vehiclenumber = vehiclenumber;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getVehicletype() {
        return vehicletype;
    }

    public String getVehiclenumber() {
        return vehiclenumber;
    }

    public String getDepartment() {
        return department;
    }
}
