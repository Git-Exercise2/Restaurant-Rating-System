package io.renren.modules.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class YelpRestaurant {
    @JsonProperty("businesses")
    private List<BusinessesDTO> businesses;
    @JsonProperty("total")
    private Integer total;
    @JsonProperty("region")
    private RegionDTO region;

    @NoArgsConstructor
    @Data
    public static class RegionDTO {
        @JsonProperty("center")
        private CenterDTO center;

        @NoArgsConstructor
        @Data
        public static class CenterDTO {
            @JsonProperty("longitude")
            private Double longitude;
            @JsonProperty("latitude")
            private Double latitude;
        }
    }

    @NoArgsConstructor
    @Data
    public static class BusinessesDTO {
        @JsonProperty("id")
        private String id;
        @JsonProperty("alias")
        private String alias;
        @JsonProperty("name")
        private String name;
        @JsonProperty("image_url")
        private String imageUrl;
        @JsonProperty("is_closed")
        private Boolean isClosed;
        @JsonProperty("url")
        private String url;
        @JsonProperty("review_count")
        private Integer reviewCount;
        @JsonProperty("categories")
        private List<CategoriesDTO> categories;
        @JsonProperty("rating")
        private Double rating;
        @JsonProperty("coordinates")
        private CoordinatesDTO coordinates;
        @JsonProperty("transactions")
        private List<String> transactions;
        @JsonProperty("price")
        private String price;
        @JsonProperty("location")
        private LocationDTO location;
        @JsonProperty("phone")
        private String phone;
        @JsonProperty("display_phone")
        private String displayPhone;
        @JsonProperty("distance")
        private Double distance;
        @JsonProperty("business_hours")
        private List<BusinessHoursDTO> businessHours;
        @JsonProperty("attributes")
        private AttributesDTO attributes;

        @NoArgsConstructor
        @Data
        public static class CoordinatesDTO {
            @JsonProperty("latitude")
            private Double latitude;
            @JsonProperty("longitude")
            private Double longitude;
        }

        @NoArgsConstructor
        @Data
        public static class LocationDTO {
            @JsonProperty("address1")
            private String address1;
            @JsonProperty("address2")
            private String address2;
            @JsonProperty("address3")
            private String address3;
            @JsonProperty("city")
            private String city;
            @JsonProperty("zip_code")
            private String zipCode;
            @JsonProperty("country")
            private String country;
            @JsonProperty("state")
            private String state;
            @JsonProperty("display_address")
            private List<String> displayAddress;
        }

        @NoArgsConstructor
        @Data
        public static class AttributesDTO {
            @JsonProperty("business_temp_closed")
            private Object businessTempClosed;
            @JsonProperty("menu_url")
            private String menuUrl;
            @JsonProperty("open24_hours")
            private Object open24Hours;
            @JsonProperty("waitlist_reservation")
            private Object waitlistReservation;
        }

        @NoArgsConstructor
        @Data
        public static class CategoriesDTO {
            @JsonProperty("alias")
            private String alias;
            @JsonProperty("title")
            private String title;
        }

        @NoArgsConstructor
        @Data
        public static class BusinessHoursDTO {
            @JsonProperty("open")
            private List<OpenDTO> open;
            @JsonProperty("hours_type")
            private String hoursType;
            @JsonProperty("is_open_now")
            private Boolean isOpenNow;

            @NoArgsConstructor
            @Data
            public static class OpenDTO {
                @JsonProperty("is_overnight")
                private Boolean isOvernight;
                @JsonProperty("start")
                private String start;
                @JsonProperty("end")
                private String end;
                @JsonProperty("day")
                private Integer day;
            }
        }
    }
}
