package com.hoteleria_app.hoteleria_frontend.dto.auth;

import com.hoteleria_app.hoteleria_frontend.dto.reservation.ReservationProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {
    private String status;
    private String message;
    private int count;
    private CurrentUserDto currentUser;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CurrentUserDto {
        private Long id_user;
        private String name;
        private String lastname;
        private int active;
        private String email;
        private String phone;
        private String password;
        private List<String> accesses; // Assuming accesses is a list of strings
        private List<String> permissions; // Assuming permissions is a list
        // of strings
        private List<ReservationProfile> reservations; // Assuming
        // reservations is a list
        // of strings
        private String username;
        private List<String> authorities; // Assuming authorities is a list
        // of strings
        private boolean enabled;
        private boolean accountNonExpired;
        private boolean accountNonLocked;
        private boolean credentialsNonExpired;
    }
}
