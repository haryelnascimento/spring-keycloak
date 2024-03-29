INSERT INTO TB_USER (LOGIN, EMAIL, PASSWORD, NAME, ACTIVE, BLOCKED, CPF, CREATED_AT, TELEPHONE)
VALUES ('admin', 'admin@test.com.br', '$2a$10$e.yO7vcxQuWn.Vvsra9WvOff5pdxkHECErJCVcYKFjPqFVJHcgTG6', 'Administrador Teste', true, false, null, now(), '62 34147645')
ON CONFLICT (login) DO NOTHING;

INSERT INTO TB_USER (LOGIN, EMAIL, PASSWORD, NAME, ACTIVE, BLOCKED, CPF, CREATED_AT, TELEPHONE)
VALUES ('user', 'user@test.com.br', '$2a$10$e.yO7vcxQuWn.Vvsra9WvOff5pdxkHECErJCVcYKFjPqFVJHcgTG6', 'Usuário Teste', true, false, null, now(), '62 34147644')
ON CONFLICT (login) DO NOTHING;


INSERT INTO TB_USER_ROLE(USER_ID, ROLE_ID)
WITH
  USER_ADMIN AS (
    SELECT ID FROM TB_USER WHERE LOGIN = 'admin'
  ),
  ROLE AS (
    SELECT ID FROM TB_ROLE WHERE ROLE = 'ADMIN'
  )
  SELECT USER_ADMIN.ID, ROLE.ID FROM USER_ADMIN, ROLE
ON CONFLICT (USER_ID, ROLE_ID) DO NOTHING;

INSERT INTO TB_USER_ROLE(USER_ID, ROLE_ID)
WITH
  USER_ADMIN AS (
    SELECT ID FROM TB_USER WHERE LOGIN = 'admin'
  ),
  ROLE AS (
    SELECT ID FROM TB_ROLE WHERE ROLE = 'USER'
  )
  SELECT USER_ADMIN.ID, ROLE.ID FROM USER_ADMIN, ROLE
ON CONFLICT (USER_ID, ROLE_ID) DO NOTHING;

INSERT INTO TB_USER_ROLE(USER_ID, ROLE_ID)
WITH
  USER_ADMIN AS (
    SELECT ID FROM TB_USER WHERE LOGIN = 'user'
  ),
  ROLE AS (
    SELECT ID FROM TB_ROLE WHERE ROLE = 'USER'
  )
  SELECT USER_ADMIN.ID, ROLE.ID FROM USER_ADMIN, ROLE
ON CONFLICT (USER_ID, ROLE_ID) DO NOTHING;