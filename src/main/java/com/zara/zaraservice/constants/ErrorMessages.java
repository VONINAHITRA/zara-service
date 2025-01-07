package com.zara.zaraservice.constants;

public class ErrorMessages {
    //Users
    public static final String EMAIL_ALREADY_EXISTS = "Un utilisateur avec cet e-mail existe déjà : %s.";
    public static final String PHONE_ALREADY_EXISTS = "Un utilisateur avec ce numéro de téléphone existe déjà : %s.";
    public static final String EMAIL_REQUIRED = "L'e-mail est obligatoire.";
    public static final String PHONE_REQUIRED = "Le numéro de téléphone est obligatoire.";
    public static final String USER_ID_REQUIRED = "Aucun utilisateur trouvé avec l'ID : %s.";
    public static final String USER_EMAIL_REQUIRED = "Aucun utilisateur trouvé avec l'e-mail : %s.";
    public static final String USER_PHONE_NUMBER_REQUIRED = "Aucun utilisateur trouvé avec le numéro de téléphone : %s.";
    public static final String USER_PASSWORD_REQUIRED = "Le mot de passe fourni pour le numéro de téléphone %s est incorrect.";
    public static final String USER_TOKEN_INVALID = "Le jeton fourni pour l'utilisateur avec le numéro de téléphone %s est invalide.";
    public static final String USER_TOKEN_NULL_EMPTY = "Le jeton d'actualisation ne peut pas être nul ou vide.";
    public static final String TOKEN_VALIDATION_FAILED = "La validation du token a échoué.";
    public static final String TOKEN_INVALID_OR_EXPIRED = "Token invalide ou expiré.";
    public static final String SESSION_DESTROYED = "Vous êtes déconnecté. Votre session a été détruite.";
    public static final String TOKEN_MISSING_OR_INVALID = "Le token n'est pas valide ou manquant.";
    public static final String USER_NOT_ACTIVE = "L'utilisateur avec l'identifiant %s %s n'est pas actif";

    //Consultants
    public static final String CONSULTANT_ID_REQUIRED = "Aucun consultant trouvé avec l'ID : %s.";

    //Clients
    public static final String CLIENT_ID_REQUIRED = "Aucun client trouvé avec l'ID : %s.";

    //Experiences
    public static final String EXPERIENCE_REQUIRED = "L'objet ExperienceDTO ne peut pas être nul.";
    public static final String EXPERIENCE_COMPANY_NAME_REQUIRED = "Le nom de l'entreprise est requis.";
    public static final String EXPERIENCE_CONSULTANT_ID_REQUIRED = "L'identifiant du consultant est requis.";
    public static final String EXPERIENCE_JOB_TITLE_REQUIRED = "Le titre du poste est requis.";
    public static final String EXPERIENCE_START_DATE_REQUIRED = "La date de début de l'expérience est requise.";
    public static final String EXPERIENCE_ID_REQUIRED = "Aucun expérience trouvé avec l'ID : %s.";
    public static final String EXPERIENCE_DATE_VERIFICATION = "La date de début doit être avant la date de fin.";

    //Formations
    public static final String FORMATION_REQUIRED = "L'objet FormationDTO ne peut pas être nul.";
    public static final String FORMATION_ESTABLISHMENT_REQUIRED = "Le nom de l'établissement est requis.";
    public static final String FORMATION_CONSULTANT_ID_REQUIRED = "L'identifiant du consultant est requis.";
    public static final String FORMATION_TITLE_REQUIRED = "Le titre de la formation est requis.";
    public static final String FORMATION_DATE_OBTAINED_REQUIRED = "La date d'obtention de la formation est requise.";
    public static final String FORMATION_ID_REQUIRED = "Aucune formation trouvée avec l'ID : %s.";
    public static final String FORMATION_DATE_VERIFICATION = "La date d'obtention doit être avant la date de fin.";

    //Feedback
    public static final String COMMENT_REQUIRED = "Le commentaire ne peut pas être vide ou nul.";
    public static final String RATING_RANGE = "La note doit être comprise entre 1 et 5.";
    public static final String FEEDBACK_ID_REQUIRED = "Aucune feedback trouvée avec l'ID : %s.";

    private ErrorMessages() {

    }
}
