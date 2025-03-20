package com.rbaudu.angel.analyzer.model;

import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) pour les résultats d'analyse.
 * Utilisé pour transférer les résultats via l'API REST.
 */
public class AnalysisResultDto {
    
    private LocalDateTime timestamp;
    private ActivityType activityType;
    private float confidence;
    private boolean personPresent;
    
    /**
     * Constructeur par défaut.
     */
    public AnalysisResultDto() {
    }
    
    /**
     * Constructeur avec tous les champs.
     * 
     * @param timestamp Horodatage de l'analyse
     * @param activityType Type d'activité détecté
     * @param confidence Score de confiance (entre 0.0 et 1.0)
     * @param personPresent Indique si une personne est présente
     */
    public AnalysisResultDto(LocalDateTime timestamp, ActivityType activityType, float confidence, boolean personPresent) {
        this.timestamp = timestamp;
        this.activityType = activityType;
        this.confidence = confidence;
        this.personPresent = personPresent;
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
}