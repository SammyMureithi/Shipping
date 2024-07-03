package com.example.drone.Drone;

import java.util.List;

public class DroneResponse {
    private  Boolean ok;
    private String status;

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Drone> getDrones() {
        return drones;
    }

    public void setDrones(List<Drone> drones) {
        this.drones = drones;
    }

    public DroneResponse(Boolean ok, String status, List<Drone> drones) {
        this.ok = ok;
        this.status = status;
        this.drones = drones;
    }

    private List<Drone> drones;
}
