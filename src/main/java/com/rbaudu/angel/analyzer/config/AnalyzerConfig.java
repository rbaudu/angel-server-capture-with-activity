package com.rbaudu.angel.analyzer.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * Configuration Spring pour le module d'analyse d'activités.
 * Cette classe assure le chargement des propriétés et la configuration
 * des ressources nécessaires au module d'analyse.
 */
@Configuration
@PropertySource("classpath:application.properties")
public class AnalyzerConfig {
    private static final Logger logger = LoggerFactory.getLogger(AnalyzerConfig.class);
    
    @Autowired
    private Environment env;
    
    @Autowired
    private ResourceLoader resourceLoader;
    
    @PostConstruct
    public void init() {
        logger.info("Initialisation de la configuration du module d'analyse d'activités");
        
        // Vérifier l'existence des répertoires de modèles
        checkModelDirectories();
    }
    
    /**
     * Vérifie l'existence des répertoires de modèles et les crée si nécessaire.
     */
    private void checkModelDirectories() {
        checkAndCreateDirectory("models");
        checkAndCreateDirectory("models/human_detection");
        checkAndCreateDirectory("models/activity_recognition");
        checkAndCreateDirectory("models/audio_classification");
    }
    
    /**
     * Vérifie l'existence d'un répertoire et le crée si nécessaire.
     * 
     * @param path Chemin relatif du répertoire à vérifier/créer
     */
    private void checkAndCreateDirectory(String path) {
        try {
            Resource resource = resourceLoader.getResource("classpath:" + path);
            File directory;
            
            try {
                directory = resource.getFile();
            } catch (IOException e) {
                // Si le répertoire n'existe pas dans le classpath, essayer de le créer
                directory = new File("src/main/resources/" + path);
                if (!directory.exists()) {
                    if (directory.mkdirs()) {
                        logger.info("Répertoire créé: {}", directory.getAbsolutePath());
                    } else {
                        logger.warn("Impossible de créer le répertoire: {}", directory.getAbsolutePath());
                    }
                }
            }
            
            if (directory.exists() && directory.isDirectory()) {
                logger.info("Répertoire vérifié: {}", directory.getAbsolutePath());
            } else {
                logger.warn("Le chemin n'est pas un répertoire valide: {}", directory.getAbsolutePath());
            }
        } catch (Exception e) {
            logger.error("Erreur lors de la vérification du répertoire: " + path, e);
        }
    }
    
    /**
     * Crée et configure les propriétés du module d'analyse.
     * 
     * @return AnalyzerProperties contenant les paramètres de configuration
     */
    @Bean
    public AnalyzerProperties analyzerProperties() {
        AnalyzerProperties properties = new AnalyzerProperties();
        
        // Chemins des modèles
        properties.setHumanDetectionModel(env.getProperty("angel.analyzer.humanDetectionModel", "models/human_detection/saved_model"));
        properties.setActivityRecognitionModel(env.getProperty("angel.analyzer.activityRecognitionModel", "models/activity_recognition/model"));
        properties.setAudioClassificationModel(env.getProperty("angel.analyzer.audioClassificationModel", "models/audio_classification/model"));
        
        // Paramètres généraux
        properties.setCaptureIntervalMs(env.getProperty("angel.analyzer.captureIntervalMs", Integer.class, 1000));
        properties.setPresenceThreshold(env.getProperty("angel.analyzer.presenceThreshold", Double.class, 0.5));
        properties.setActivityConfidenceThreshold(env.getProperty("angel.analyzer.activityConfidenceThreshold", Double.class, 0.6));
        properties.setHistorySize(env.getProperty("angel.analyzer.historySize", Integer.class, 5));
        
        // Dimensions des images d'entrée
        properties.setInputImageWidth(env.getProperty("angel.analyzer.inputImageWidth", Integer.class, 224));
        properties.setInputImageHeight(env.getProperty("angel.analyzer.inputImageHeight", Integer.class, 224));
        
        // Configuration audio
        properties.setAudioSampleRate(env.getProperty("angel.analyzer.audioSampleRate", Integer.class, 44100));
        properties.setAudioAnalysisEnabled(env.getProperty("angel.analyzer.audioAnalysisEnabled", Boolean.class, true));
        
        logger.info("Propriétés du module d'analyse configurées: {}", properties);
        return properties;
    }
}