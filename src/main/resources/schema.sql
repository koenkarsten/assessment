-- Enum table for DeviceType
CREATE TABLE DeviceType (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- Populate DeviceType table
INSERT INTO DeviceType (name) VALUES ('WEB');
INSERT INTO DeviceType (name) VALUES ('ANDROID');
INSERT INTO DeviceType (name) VALUES ('IOS');

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
