-- Device table
CREATE TABLE Device (
    uid UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    type VARCHAR(100) NOT NULL,
    model VARCHAR(100),
    os VARCHAR(100)
);

-- Populate Device table
INSERT INTO Device (uid, type, model, os) VALUES ('550e8400-e29b-41d4-a716-446655440000', 'IOS', 'iPhone 16 Pro', 'iOS 17.6.1');
INSERT INTO Device (uid, type, model, os) VALUES ('7b9e14f1-12a8-41e7-91b4-3420c1e97007', 'ANDROID', 'Samsung Galaxy S25', 'Android 14.1');
INSERT INTO Device (uid, type, model, os) VALUES ('8d6e47b5-a09b-4e2e-93d3-4ad1c2eaf0bc', 'IOS', 'iPad Pro 13-inch', 'iOS 17.4.2');
INSERT INTO Device (uid, type, model, os) VALUES ('d162f9d8-7f3a-4e65-9121-6e5cf9e7f6b3', 'WEB', 'MacBook Pro 16-inch', 'macOS Sonoma 14.3');
INSERT INTO Device (uid, type, model, os) VALUES ('43cba1b9-91f4-42b3-9477-6e18c0f7fa7d', 'ANDROID', 'Google Pixel 9', 'Android 14.0.5');
INSERT INTO Device (uid, type, model, os) VALUES ('a381f1c7-4b74-4fa8-b1ea-89f79a3ef4e9', 'WEB', 'Dell XPS 15', 'Windows 11 Pro');


CREATE TABLE Detection (
    uid UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    device UUID NOT NULL,
    type VARCHAR(100) NOT NULL,
    time INT NOT NULL,
    app_name VARCHAR(100),
    app_type VARCHAR(100),
    FOREIGN KEY (device) REFERENCES Device(uid)
);

-- Populate Detections table
INSERT INTO Detection (uid, device, type, time, app_name, app_type) VALUES ('7f0cd03c-0ecd-4dba-b2c8-b288a562b8ea', '550e8400-e29b-41d4-a716-446655440000','NEW', 1733582900, 'Rabobank Bankieren', 'Banking');
INSERT INTO Detection (uid, device, type, time, app_name, app_type) VALUES ('66239800-b488-4d94-9f90-6cab83aa3664', '550e8400-e29b-41d4-a716-446655440000','RESOLVED', 1733588951, 'Rabobank Bankieren', 'Banking');
INSERT INTO Detection (uid, device, type, time, app_name, app_type) VALUES ('3af58872-5990-46bd-a4d5-6e270bedf726', '550e8400-e29b-41d4-a716-446655440000','NONE', 1733588971, 'ING Bankieren', 'Banking');
INSERT INTO Detection (uid, device, type, time, app_name, app_type) VALUES ('8fa3b942-35b8-420b-b8db-7f9e7f524f5b', '7b9e14f1-12a8-41e7-91b4-3420c1e97007', 'NEW', 1733583001, 'WhatsApp', 'Messaging');
INSERT INTO Detection (uid, device, type, time, app_name, app_type) VALUES ('1d83c836-9bbd-4b2b-b74c-7393f38a50e8', '7b9e14f1-12a8-41e7-91b4-3420c1e97007', 'RESOLVED', 1733586002, 'WhatsApp', 'Messaging');
INSERT INTO Detection (uid, device, type, time, app_name, app_type) VALUES ('1ae98245-3e24-43d8-9a97-1a29c6374d27', '8d6e47b5-a09b-4e2e-93d3-4ad1c2eaf0bc', 'NEW', 1733590000, 'Facebook', 'Social Media');
INSERT INTO Detection (uid, device, type, time, app_name, app_type) VALUES ('a172b75c-1e38-4a02-865b-dfa230b67d65', '8d6e47b5-a09b-4e2e-93d3-4ad1c2eaf0bc', 'NONE', 1733591000, 'Facebook', 'Social Media');
INSERT INTO Detection (uid, device, type, time, app_name, app_type) VALUES ('9ab45130-98c2-4ee9-9af7-bf6ed8edb2bb', '8d6e47b5-a09b-4e2e-93d3-4ad1c2eaf0bc', 'RESOLVED', 1733592000, 'Instagram', 'Social Media');
INSERT INTO Detection (uid, device, type, time, app_name, app_type) VALUES ('8c4160d8-8ac6-4c4f-bfe2-33bd04c83bd2', 'd162f9d8-7f3a-4e65-9121-6e5cf9e7f6b3', 'NEW', 1733584003, 'Slack', 'Productivity');
INSERT INTO Detection (uid, device, type, time, app_name, app_type) VALUES ('56de3a69-fad8-44e5-bd1d-63c3542b7a4e', 'd162f9d8-7f3a-4e65-9121-6e5cf9e7f6b3', 'RESOLVED', 1733585005, 'Zoom', 'Communication');
INSERT INTO Detection (uid, device, type, time, app_name, app_type) VALUES ('4c90f8b7-6430-4d94-986b-6f7241d96c35', 'd162f9d8-7f3a-4e65-9121-6e5cf9e7f6b3', 'NONE', 1733586500, 'Slack', 'Productivity');
INSERT INTO Detection (uid, device, type, time, app_name, app_type) VALUES ('e33a5787-b6d1-41c8-b0be-e56d1b39036b', '43cba1b9-91f4-42b3-9477-6e18c0f7fa7d', 'NEW', 1733587000, 'Spotify', 'Music');
INSERT INTO Detection (uid, device, type, time, app_name, app_type) VALUES ('6b271e4f-3912-4625-b687-c23f63e3724c', '43cba1b9-91f4-42b3-9477-6e18c0f7fa7d', 'RESOLVED', 1733588000, 'Spotify', 'Music');
INSERT INTO Detection (uid, device, type, time, app_name, app_type) VALUES ('2d6c1ec4-80b3-490f-8a5a-efc9c2926539', '43cba1b9-91f4-42b3-9477-6e18c0f7fa7d', 'NONE', 1733588500, 'Google Drive', 'Storage');
INSERT INTO Detection (uid, device, type, time, app_name, app_type) VALUES ('7a1e5027-4813-4b80-92d3-10a938c5e576', 'a381f1c7-4b74-4fa8-b1ea-89f79a3ef4e9', 'NEW', 1733589000, 'Netflix', 'Entertainment');
INSERT INTO Detection (uid, device, type, time, app_name, app_type) VALUES ('d4c95ba0-3ad3-4092-a5a4-8de30833c5b1', 'a381f1c7-4b74-4fa8-b1ea-89f79a3ef4e9', 'RESOLVED', 1733589100, 'Netflix', 'Entertainment');
INSERT INTO Detection (uid, device, type, time, app_name, app_type) VALUES ('d86fc64f-b7ea-4d3f-92f2-965fcf4b8899', 'a381f1c7-4b74-4fa8-b1ea-89f79a3ef4e9', 'NONE', 1733589200, 'Prime Video', 'Entertainment');
INSERT INTO Detection (uid, device, type, time, app_name, app_type) VALUES ('45f8d423-2139-48b4-9906-96267434ec5e', '550e8400-e29b-41d4-a716-446655440000', 'NEW', 1733589300, 'Uber', 'Transport');
INSERT INTO Detection (uid, device, type, time, app_name, app_type) VALUES ('44a76c38-1d73-4ab6-9fb3-e1a89ffae7cd', '550e8400-e29b-41d4-a716-446655440000', 'RESOLVED', 1733589400, 'Lyft', 'Transport');
INSERT INTO Detection (uid, device, type, time, app_name, app_type) VALUES ('1871b3a2-64f9-41b6-bdd4-09a5c4828e44', '550e8400-e29b-41d4-a716-446655440000', 'NONE', 1733589500, 'Airbnb', 'Travel');
INSERT INTO Detection (uid, device, type, time, app_name, app_type) VALUES ('b2d0f3d4-3e39-4e85-9f72-b0f7c6898be6', '7b9e14f1-12a8-41e7-91b4-3420c1e97007', 'NEW', 1733590000, 'TikTok', 'Social Media');
INSERT INTO Detection (uid, device, type, time, app_name, app_type) VALUES ('9f2c065c-8b68-4c96-b2b1-19a04e8f5ee0', '7b9e14f1-12a8-41e7-91b4-3420c1e97007', 'RESOLVED', 1733590100, 'TikTok', 'Social Media');
INSERT INTO Detection (uid, device, type, time, app_name, app_type) VALUES ('17c91f3d-bff3-4eeb-9077-c5f3f57c4d4e', '7b9e14f1-12a8-41e7-91b4-3420c1e97007', 'NONE', 1733590200, 'Snapchat', 'Social Media');
