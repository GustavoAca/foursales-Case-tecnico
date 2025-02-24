insert into categorias (id, descricao, nome, data_de_criacao, data_de_modificacao, criado_por, modificado_por)
values
    (UUID_TO_BIN('2a8d607d-ef9f-11ef-b48c-42472f9b5b0f'), 'Categoria de casa e cozinha', 'Casa e Cozinha', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'FOURSALES', 'FOURSALES'),
    (UUID_TO_BIN('2a8d611f-ef9f-11ef-b48c-42472f9b5b0f'), 'Categoria de eletrônicos', 'Eletrônicos', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'FOURSALES', 'FOURSALES'),
    (UUID_TO_BIN('c06b2b69-ef9e-11ef-b48c-42472f9b5b0f'), 'Categoria de moda e vestuário', 'Moda e Vestuário', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'FOURSALES', 'FOURSALES');

insert into subcategorias(id, categoria_id, descricao, nome, data_de_criacao, data_de_modificacao, criado_por, modificado_por)
values
    (UUID_TO_BIN('8d8b89be-efa5-11ef-b48c-42472f9b5b0f'), UUID_TO_BIN('2a8d607d-ef9f-11ef-b48c-42472f9b5b0f'), 'Subcategoria de casa e cozinha, utensílios de cozinha', 'Utensílios de cozinha', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'FOURSALES', 'FOURSALES'),
    (UUID_TO_BIN('8d8b91e2-efa5-11ef-b48c-42472f9b5b0f'), UUID_TO_BIN('2a8d607d-ef9f-11ef-b48c-42472f9b5b0f'), 'Subcategoria de casa e cozinha, decoração', 'Decoração', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'FOURSALES', 'FOURSALES'),
    (UUID_TO_BIN('8d8b93b4-efa5-11ef-b48c-42472f9b5b0f'), UUID_TO_BIN('2a8d611f-ef9f-11ef-b48c-42472f9b5b0f'), 'Subcategoria de eletronicos, smartphones', 'Smartphones', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'FOURSALES', 'FOURSALES'),
    (UUID_TO_BIN('8d8b951a-efa5-11ef-b48c-42472f9b5b0f'), UUID_TO_BIN('2a8d611f-ef9f-11ef-b48c-42472f9b5b0f'), 'Subcategoria de eletronicos, televisores', 'Televisores', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'FOURSALES', 'FOURSALES'),
    (UUID_TO_BIN('8d8b9643-efa5-11ef-b48c-42472f9b5b0f'), UUID_TO_BIN('c06b2b69-ef9e-11ef-b48c-42472f9b5b0f'), 'Subcategoria de moda e vestuário', 'Roupas Masculinas', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'FOURSALES', 'FOURSALES'),
    (UUID_TO_BIN('8d8b976d-efa5-11ef-b48c-42472f9b5b0f'), UUID_TO_BIN('c06b2b69-ef9e-11ef-b48c-42472f9b5b0f'), 'Subcategoria de moda e vestuário', 'Roupas Femininas', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'FOURSALES', 'FOURSALES');

insert into produtos (id, subcategoria_id, nome, descricao, preco, quantidade_em_estoque, data_de_criacao, data_de_modificacao, criado_por, modificado_por)
values
    (UUID_TO_BIN('02e0c97d-efa7-11ef-b48c-42472f9b5b0f'), UUID_TO_BIN('8d8b89be-efa5-11ef-b48c-42472f9b5b0f'), 'Moto G54', 'Celular motorola moto G54, 256GB de armazenamento', 1050.00, 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'FOURSALES', 'FOURSALES'),
    (UUID_TO_BIN('02e0cf05-efa7-11ef-b48c-42472f9b5b0f'), UUID_TO_BIN('8d8b91e2-efa5-11ef-b48c-42472f9b5b0f'), 'Cortina blackout', 'Cortina 1,20mx0,6m', 10.00, 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'FOURSALES', 'FOURSALES');

insert into usuarios (id, email, senha, perfil, nome, total_de_compras_realizadas, data_de_criacao, data_de_modificacao, criado_por, modificado_por)
values
    (UUID_TO_BIN('00a3bdbb-ff88-4832-a261-99c8a617ef57'), 'galasdalas1@gmail.com', '$2a$10$Pl335iY8.kFtoDzGZwNZvezyBKazOv0cjINrMBVWDcTjx3daXgglS', 'ROLE_ADMIN', 'Gustavo Admin', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'FOURSALES', 'FOURSALES'),
    (UUID_TO_BIN('07c24b07-f62b-48d9-84a9-75a410d6dc3c'), 'galasdalas2@gmail.com', '$2a$10$V8EOtbXPSVF7drEJhEXGi.bSK0DgZ/orlADNcTW.IVjiT2W5o.R12', 'ROLE_USER', 'Gustavo User', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'FOURSALES', 'FOURSALES')