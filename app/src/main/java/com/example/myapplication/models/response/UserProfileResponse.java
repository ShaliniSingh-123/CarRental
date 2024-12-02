package com.example.myapplication.models.response;

// This is the main UserProfile class that will contain the User data
public class UserProfileResponse {

    private boolean success;
    private String message;
    private User user;

    // Getters and Setters

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Inner User class representing the user data
    public static class User {
        private String fullName;
        private String email;
        private String mobile;
        private String photo;

        private String Address;

        // Getters and Setters

        public String getAddress() {
            return fullName;
        }

        public void setAddress(String address) {
            this.Address = address;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }
}

