package io.renren.modules.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class YelpReviews {

    @JsonProperty("possible_languages")
    private List<String> possibleLanguages;
    @JsonProperty("reviews")
    private List<ReviewsDTO> reviews;
    @JsonProperty("total")
    private Integer total;

    @NoArgsConstructor
    @Data
    public static class ReviewsDTO {
        @JsonProperty("id")
        private String id;
        @JsonProperty("url")
        private String url;
        @JsonProperty("text")
        private String text;
        @JsonProperty("rating")
        private Integer rating;
        @JsonProperty("time_created")
        private String timeCreated;
        @JsonProperty("user")
        private UserDTO user;

        @NoArgsConstructor
        @Data
        public static class UserDTO {
            @JsonProperty("id")
            private String id;
            @JsonProperty("profile_url")
            private String profileUrl;
            @JsonProperty("image_url")
            private String imageUrl;
            @JsonProperty("name")
            private String name;
        }
    }
}
