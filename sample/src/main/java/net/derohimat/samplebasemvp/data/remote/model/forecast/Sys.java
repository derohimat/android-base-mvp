package net.derohimat.samplebasemvp.data.remote.model.forecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Sys {

    @SerializedName("population")
    @Expose
    private Integer population;

    /**
     * @return The population
     */
    public Integer getPopulation() {
        return population;
    }

    /**
     * @param population The population
     */
    public void setPopulation(Integer population) {
        this.population = population;
    }

}
