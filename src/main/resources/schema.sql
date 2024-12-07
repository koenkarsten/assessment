-- Enum table for DeviceType
CREATE TABLE DeviceType (
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- Populate DeviceType table
INSERT INTO DeviceType (id, name) VALUES (0, 'WEB');
INSERT INTO DeviceType (id, name) VALUES (1, 'ANDROID');
INSERT INTO DeviceType (id, name) VALUES (2, 'IOS');

-- Device table
CREATE TABLE Device (
    uid UUID PRIMARY KEY,
    type INT NOT NULL,
    model VARCHAR(100),
    os VARCHAR(100),
    FOREIGN KEY (type) REFERENCES DeviceType(id)
);

-- Populate Device table
INSERT INTO Device (uid, type, model, os) VALUES ('550e8400-e29b-41d4-a716-446655440000', 2, 'iPhone 16 Pro', 'iOS 17.6.1');


-- Enum table for DetectionType
CREATE TABLE DetectionType (
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- Populate DetectionType table
INSERT INTO DetectionType (id, name) VALUES (0, 'NONE');
INSERT INTO DetectionType (id, name) VALUES (1, 'NEW');
INSERT INTO DetectionType (id, name) VALUES (2, 'RESOLVED');

CREATE TABLE Detection (
    uid UUID PRIMARY KEY,
    type INT NOT NULL,
    time INT NOT NULL,
    app_name VARCHAR(100),
    app_type VARCHAR(100)
);

-- Populate Detections table
INSERT INTO Detection (uid, type, time, app_name, app_type) VALUES ('7f0cd03c-0ecd-4dba-b2c8-b288a562b8ea', 1, 1733582900, 'Rabobank Bankieren', 'Banking');

