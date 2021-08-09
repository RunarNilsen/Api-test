
package POJO;

import java.util.List;
import javax.annotation.processing.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Item {

    @SerializedName("region_id")
    @Expose
    private Integer regionId;
    @SerializedName("region_name")
    @Expose
    private String regionName;
    @SerializedName("links")
    @Expose
    private List<Link> links = null;

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

}
