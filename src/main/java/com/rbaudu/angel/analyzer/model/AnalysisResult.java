package com.rbaudu.angel.analyzer.model;

import java.time.LocalDateTime;

/**
 * Représente le résultat d'une analyse d'activité.
 * Stocke les informations détaillées sur l'activité détectée.
 */
public class AnalysisResult {
    
    private LocalDateTime timestamp;
    private ActivityType activityType;
    private float confidence;
    private boolean personPresent;
    private long processingTimeMs;
    private String additionalInfo;
    
    /**
     * Constructeur par défaut.
     */
    public AnalysisResult() {
        this.timestamp = LocalDateTime.now();
        this.activityType = ActivityType.UNKNOWN;
        this.confidence = 0.0f;
        this.personPresent = false;
        this.processingTimeMs = 0;
    }
    
    /**
     * Constructeur avec le type d'activité et la confiance.
     * 
     * @param activityType Type d'activité détecté
     * @param confidence Score de confiance (entre 0.0 et 1.0)
     */
    public AnalysisResult(ActivityType activityType, float confidence) {
        this();
        this.activityType = activityType;
        this.confidence = confidence;
        this.personPresent = activityType != ActivityType.ABSENT;
    }
    
    // Getters et Setters
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    public ActivityType getActivityType() {
        return activityType;
    }
    
    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }
    
    public float getConfidence() {
        return confidence;
    }
    
    public void setConfidence(float confidence) {
        this.confidence = confidence;
    }
    
    public boolean isPersonPresent() {
        return personPresent;
    }
    
    public void setPersonPresent(boolean personPresent) {
        this.personPresent = personPresent;
    }
    
    public long getProcessingTimeMs() {
        return processingTimeMs;
    }
    
    public void setProcessingTimeMs(long processingTimeMs) {
        this.processingTimeMs = processingTimeMs;
    }
    
    public String getAdditionalInfo() {
        return additionalInfo;
    }
    
    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}