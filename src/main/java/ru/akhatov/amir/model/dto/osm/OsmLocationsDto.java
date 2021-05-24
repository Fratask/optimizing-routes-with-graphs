package ru.akhatov.amir.model.dto.osm;

import java.util.List;

public class OsmLocationsDto {
    private List<FeatureDto> features;

    public List<FeatureDto> getFeatures() {
        return features;
    }

    public void setFeatures(List<FeatureDto> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return "OsmLocationsDto{" +
                "features=" + features +
                '}';
    }
}
