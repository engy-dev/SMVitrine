package com.smconsulting.backend.model;

import com.smconsulting.backend.entity.Page;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.ColumnTransformer;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "contact_submissions", schema = "vitrine")
public class ContactRequest {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id")
    private Page page;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String email;

    private String phone;

    private String company;

    private String subject;

    @Column(nullable = false)
    private String message;

    @Column(name = "consent_given", nullable = false)
    private boolean consentGiven;

    @Column(name = "ip_address")
    @ColumnTransformer(write = "?::inet")
    private String ipAddress;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "has_responded", nullable = false)
    private boolean responded = false;

    @Column(name = "handled_by")
    private UUID handledByEmployeeId;

    @Column(name = "handled_at")
    private LocalDateTime handledAt;

    @Column(name = "internal_notes")
    private String internalNotes;

    @Column(name = "submitted_at", nullable = false)
    private LocalDateTime submittedAt;

    protected ContactRequest() {
        // Constructeur sans argument requis par JPA.
    }

    public ContactRequest(String fullName, String email, String phone, String company,
                           String subject, String message, boolean consentGiven,
                           String ipAddress, String userAgent, LocalDateTime submittedAt) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.company = company;
        this.subject = subject;
        this.message = message;
        this.consentGiven = consentGiven;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.submittedAt = submittedAt;
    }

    public UUID getId() {
        return id;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCompany() {
        return company;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public boolean isConsentGiven() {
        return consentGiven;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public boolean isResponded() {
        return responded;
    }

    public void setResponded(boolean responded) {
        this.responded = responded;
    }

    public UUID getHandledByEmployeeId() {
        return handledByEmployeeId;
    }

    public LocalDateTime getHandledAt() {
        return handledAt;
    }

    public String getInternalNotes() {
        return internalNotes;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }
}