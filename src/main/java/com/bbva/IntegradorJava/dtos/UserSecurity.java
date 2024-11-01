package com.bbva.IntegradorJava.dtos;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSecurity {

    private Long id;
    private String username;
    private List<String> roles;
    private LocalDateTime createdAt;
    private LocalDateTime expirationDate;

    public UserSecurity (Claims claims) {
        this.id = Long.getLong(claims.getId());
        this.username = claims.getSubject();
        this.roles = Arrays.asList(claims.get("roles", String.class).split(","));
        this.createdAt = Instant.ofEpochMilli(claims.getIssuedAt().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        this.expirationDate = Instant.ofEpochMilli(claims.getExpiration().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}
    
    
}
