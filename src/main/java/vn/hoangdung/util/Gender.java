package vn.hoangdung.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Gender {
    
    @JsonProperty("male")
    MALE,
    @JsonProperty("female")
    FEMALE,
    @JsonProperty("other")
    OTHER;

    // MALE, FEMALE, OTHER

}
