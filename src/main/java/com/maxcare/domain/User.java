package com.maxcare.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(nullable = false, length = 254, unique = true)
    private String email;

    @Column(name = "password_hash", length = 254)
    private String passwordHash;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 5)
    private Language language = Language.PL;

    @Column(name = "activated_at")
    private Instant activatedAt;

    @Column(name = "token_version", nullable = false)
    private int tokenVersion;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Version
    private Long version;

    protected User() {}

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public UserRole getRole() {
        return role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public Language getLanguage() {
        return language;
    }

    public Instant getActivatedAt() {
        return activatedAt;
    }

    public int getTokenVersion() {
        return tokenVersion;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Long getVersion() {
        return version;
    }

    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }

    @PrePersist
    void onCreate() {
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;
        email = normalize(email);
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = Instant.now();
        email = normalize(email);
    }

    private User(String email, String passwordHash, String firstName, String lastName,
                 LocalDate dateOfBirth, UserRole role, UserStatus status, Language language) {
        this.email = normalize(Objects.requireNonNull(email, "email"));
        this.passwordHash = passwordHash;
        this.firstName = Objects.requireNonNull(firstName, "firstName");
        this.lastName = Objects.requireNonNull(lastName, "lastName");
        this.dateOfBirth = dateOfBirth;
        this.role = role;
        this.status = status;
        this.language = language != null ? language : Language.PL;
    }

    public static User newPatient(String email, String passwordHash, String firstName,
                                  String lastName, LocalDate dateOfBirth, Language language) {
        Objects.requireNonNull(passwordHash, "passwordHash");
        Objects.requireNonNull(dateOfBirth, "dateOfBirth is required for patients");
        return new User(email, passwordHash, firstName, lastName, dateOfBirth,
                UserRole.PATIENT, UserStatus.INACTIVE, language);
    }

    private static String normalize(String email) {
        return email.trim().toLowerCase(Locale.ROOT);
    }
}
