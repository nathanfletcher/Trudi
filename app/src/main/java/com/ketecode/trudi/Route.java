package com.ketecode.trudi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KobbyFletcher on 5/28/16.
 */
public class Route {
    String name;
    String id;
    String fromTerminal;
    String toTerminal;
    List<Stop> busStops = new ArrayList<Stop>();

    public Route(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromTerminal() {
        return fromTerminal;
    }

    public void setFromTerminal(String fromTerminal) {
        this.fromTerminal = fromTerminal;
    }

    public String getToTerminal() {
        return toTerminal;
    }

    public void setToTerminal(String toTerminal) {
        this.toTerminal = toTerminal;
    }

    public List<Stop> getBusStops() {
        return busStops;
    }

    public void addBusStop(String name, Double lat, Double lon) {
        busStops.add(new Stop(name, lat, lon));
    }

    public void addBusStop(Stop busStopObj) {
        busStops.add(busStopObj);
    }
}
