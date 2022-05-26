package com.splenda.epi.security.payload.response;

public class JwtResponse {
    private String token;

    private Integer expToken;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private String nameRole;


    public JwtResponse(String accessToken, Integer expToken,  Long id, String username, String email, String nameRole) {
        this.token = accessToken;
        this.expToken = expToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.nameRole = nameRole;
    }


    public String getNameRole() {
        return nameRole;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getExpToken() { return expToken; }
}