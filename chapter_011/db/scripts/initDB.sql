MERGE INTO users (id, name, login, email, password, createDate, role)
KEY(id)
  VALUES (1, 'admin', 'admin', 'admin', 'admin', '2001-01-01', 'ADMIN');