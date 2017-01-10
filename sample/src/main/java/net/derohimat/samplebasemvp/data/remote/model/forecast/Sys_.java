package net.derohimat.samplebasemvp.data.remote.model.forecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Sys_ {

    @SerializedName("pod")
    @Expose
    private String pod;

    /**
     * @return The pod
     */
    public String getPod() {
        return pod;
    }

    /**
     * @param pod The pod
     */
    public void setPod(String pod) {
        this.pod = pod;
    }

}
