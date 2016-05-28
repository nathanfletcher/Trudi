package com.ketecode.trudi;

import java.util.List;

/**
 * Created by KobbyFletcher on 5/28/16.
 */
public class Route {
    String name;
    String id;
    String fromTerminal;
    String toTerminal;
    List<Stop> busStops;

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

    public void setBusStops(List<Stop> busStops) {
        this.busStops = busStops;
    }
}
