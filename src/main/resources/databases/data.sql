-- Insert Users
INSERT INTO users (firstName, lastName, phoneNumber, email, password, role, isActive) VALUES
('John', 'Doe', '123456789', 'john.doe@example.com', 'password123', 'CLIENT', TRUE),
('Jane', 'Smith', '987654321', 'jane.smith@example.com', 'password123', 'CONSULTANT', TRUE);

-- Insert Clients
INSERT INTO clients (localisation, user_id) VALUES
('Paris', 1);

-- Insert Consultants
INSERT INTO consultants (interventionsCount, isAvailable, localisation, specialization, description, user_id) VALUES
(10, TRUE, 'Lyon', 'Plumbing', 'Experienced plumber', 2);

-- Insert Interventions
INSERT INTO interventions (localisation, statusRDV, consultant_id, client_id, interventionDate, description) VALUES
('Marseille', 'CONFIRMED', 1, 1, '2025-01-15 14:00:00', 'Fixing kitchen sink');

-- Insert Experiences
INSERT INTO experiences (consultant_id, company_name, job_title, start_date, end_date, description, location) VALUES
(1, 'Plumbing Co.', 'Senior Plumber', '2020-01-01', '2023-01-01', 'Handled complex plumbing projects.', 'Paris');

-- Insert Formations
INSERT INTO formations (consultant_id, establishment, title, description, dateObtained) VALUES
(1, 'Technical University', 'Plumbing Certification', 'Certified in advanced plumbing.', '2019-05-10');

-- Insert Feedbacks
INSERT INTO feedbacks (consultant_id, client_id, rating, comment) VALUES
(1, 1, 5, 'Excellent service!');

-- Insert Messages
INSERT INTO messages (sender_id, receiver_id, content, status) VALUES
(1, 2, 'Hi, I need help with a plumbing issue.', 'SENT');
