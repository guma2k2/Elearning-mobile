package com.example.elearningmobile.model;


public class AuthenticationPostVm {
        private String email;
        private String password;

        public AuthenticationPostVm(String email, String password) {
                this.email = email;
                this.password = password;
        }

        public AuthenticationPostVm() {
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }
}
