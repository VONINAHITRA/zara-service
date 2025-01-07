-- Table AbstractEntity (héritée par toutes les entités)
-- Pas besoin de créer explicitement car chaque table héritant en aura les colonnes.

-- Table Users
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255),
    avatar VARCHAR(255),
    phoneNumber VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    token VARCHAR(255),
    role VARCHAR(50) NOT NULL,
    isActive BOOLEAN NOT NULL
);

-- Table Clients
CREATE TABLE clients (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    localisation VARCHAR(255),
    user_id BIGINT UNIQUE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

-- Table Consultants
CREATE TABLE consultants (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    interventionsCount INT,
    isAvailable BOOLEAN NOT NULL DEFAULT TRUE,
    localisation VARCHAR(255),
    specialization VARCHAR(255),
    description TEXT,
    user_id BIGINT UNIQUE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

-- Table Interventions
CREATE TABLE interventions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    localisation VARCHAR(255),
    statusRDV VARCHAR(50) NOT NULL,
    consultant_id BIGINT NOT NULL,
    client_id BIGINT NOT NULL,
    interventionDate TIMESTAMP NOT NULL,
    description TEXT,
    FOREIGN KEY (consultant_id) REFERENCES consultants (id) ON DELETE CASCADE,
    FOREIGN KEY (client_id) REFERENCES clients (id) ON DELETE CASCADE
);

-- Table Experiences
CREATE TABLE experiences (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    consultant_id BIGINT NOT NULL,
    company_name VARCHAR(255) NOT NULL,
    job_title VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    description TEXT,
    location VARCHAR(255),
    FOREIGN KEY (consultant_id) REFERENCES consultants (id) ON DELETE CASCADE
);

-- Table Formations
CREATE TABLE formations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    consultant_id BIGINT NOT NULL,
    establishment VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    dateObtained DATE,
    FOREIGN KEY (consultant_id) REFERENCES consultants (id) ON DELETE CASCADE
);

-- Table Feedbacks
CREATE TABLE feedbacks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    consultant_id BIGINT NOT NULL,
    client_id BIGINT NOT NULL,
    rating INT NOT NULL,
    comment TEXT,
    FOREIGN KEY (consultant_id) REFERENCES consultants (id) ON DELETE CASCADE,
    FOREIGN KEY (client_id) REFERENCES clients (id) ON DELETE CASCADE
);

-- Table Messages
CREATE TABLE messages (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sender_id BIGINT NOT NULL,
    receiver_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'SENT',
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (receiver_id) REFERENCES users (id) ON DELETE CASCADE
);
