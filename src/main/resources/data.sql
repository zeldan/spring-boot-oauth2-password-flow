INSERT INTO privilege (privilege_id, name, description) VALUES (1, 'PRIVILEGE_ADMIN_READ', 'description for privilege admin read') ON CONFLICT DO NOTHING;
INSERT INTO privilege (privilege_id, name, description) VALUES (2, 'PRIVILEGE_USER_READ', 'description for privilege user read') ON CONFLICT DO NOTHING;

INSERT INTO role (role_id, name, description) VALUES (1, 'ROLE_ADMIN', 'description for role admin') ON CONFLICT DO NOTHING;
INSERT INTO role (role_id, name, description) VALUES (2, 'ROLE_USER', 'description for role user') ON CONFLICT DO NOTHING;

INSERT INTO role_privileges (role_role_id, privileges_privilege_id) VALUES (1, 1) ON CONFLICT DO NOTHING;
INSERT INTO role_privileges (role_role_id, privileges_privilege_id) VALUES (2, 2) ON CONFLICT DO NOTHING;

INSERT INTO account (id, enabled, username, password) VALUES (1, true, 'admin', '$2a$10$MTFVrdqbHOi.CCUhkrkZnOBdrZEfk3gzIUyZBdQvLWvdF/0pnkEO2') ON CONFLICT DO NOTHING;
INSERT INTO account (id, enabled, username, password) VALUES (2, true, 'user', '$2a$10$6KDklkImZgGANWR8pDAwSexf6Bt4Z9I0nDiwdih9Q38HI4eAkWk0u') ON CONFLICT DO NOTHING;

INSERT INTO account_roles (account_id, roles_role_id) VALUES (1, 1) ON CONFLICT DO NOTHING;
INSERT INTO account_roles (account_id, roles_role_id) VALUES (2, 2) ON CONFLICT DO NOTHING;