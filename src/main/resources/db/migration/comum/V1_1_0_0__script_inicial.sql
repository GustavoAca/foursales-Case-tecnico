create table usuarios (
  id binary(16) not null,
  email varchar(100) not null,
  senha varchar(100) not null,
  perfil varchar(30) not null,
  nome varchar(30) not null,
  total_de_compras_realizadas int not null,
  data_de_criacao timestamp null default current_timestamp,
  data_de_modificacao timestamp null default current_timestamp on update current_timestamp,
  criado_por varchar(100) null,
  modificado_por varchar(100) null,
  primary key (id)
);

alter table usuarios
add constraint uq_usuario_email UNIQUE (email);
CREATE INDEX idx_usuarios_total_de_compras_realizadas ON usuarios(total_de_compras_realizadas);
CREATE INDEX idx_usuarios_email ON usuarios(email);

create table categorias (
  id binary(16) not null,
  descricao varchar(250) not null,
  nome varchar(30) not null,
  data_de_criacao timestamp null default current_timestamp,
  data_de_modificacao timestamp null default current_timestamp on update current_timestamp,
  criado_por varchar(100) null,
  modificado_por varchar(100) null,
  primary key (id)
);

create table subcategorias (
  id binary(16) not null,
  categoria_id binary(16) not null,
  descricao varchar(250) not null,
  nome varchar(30) not null,
  data_de_criacao timestamp null default current_timestamp,
  data_de_modificacao timestamp null default current_timestamp on update current_timestamp,
  criado_por varchar(100) null,
  modificado_por varchar(100) null,
  primary key (id),
  foreign key (categoria_id) references categorias(id)
);

CREATE INDEX idx_sucategorias_categoria_id ON subcategorias(id);

create table produtos (
  id binary(16) not null,
  subcategoria_id binary(16) not null,
  nome varchar(30) not null,
  descricao varchar(250) not null,
  preco decimal(10, 2) not null,
  quantidade_em_estoque int not null,
  data_de_criacao timestamp null default current_timestamp,
  data_de_modificacao timestamp null default current_timestamp on update current_timestamp,
  criado_por varchar(100) null,
  modificado_por varchar(100) null,
  primary key (id),
  foreign key (subcategoria_id) references subcategorias(id)
);

CREATE INDEX idx_produtos_quantidade_em_estoque ON produtos(quantidade_em_estoque);

create table pedidos (
  id binary(16) not null,
  usuario_id binary(16) not null,
  status varchar(30) not null,
  valor_total decimal(10, 2) not null,
  data_de_criacao timestamp null default current_timestamp,
  data_de_modificacao timestamp null default current_timestamp on update current_timestamp,
  criado_por varchar(100) null,
  modificado_por varchar(100) null,
  primary key (id),
  foreign key (usuario_id) references usuarios(id)
);

CREATE INDEX idx_pedidos_usuario_id ON pedidos(usuario_id);
CREATE INDEX idx_pedidos_usuario_id_valor_total ON pedidos(usuario_id, valor_total);
CREATE INDEX idx_pedidos_data_de_criacao_valor_total ON pedidos(data_de_criacao, valor_total);

create table pagamentos (
  pedido_id binary(16) not null,
  data_de_criacao timestamp null default current_timestamp,
  data_de_modificacao timestamp null default current_timestamp on update current_timestamp,
  criado_por varchar(100) null,
  modificado_por varchar(100) null,
  primary key (pedido_id),
  foreign key (pedido_id) references pedidos(id)
);

CREATE INDEX idx_pagamentos_pedido_id ON pagamentos(pedido_id);

create table produtos_pedidos (
  id binary(16) not null,
  produto_id binary(16) not null,
  pedido_id binary(16) not null,
  quantidade int not null,
  preco decimal(10, 2) not null,
  estoque_disponivel tinyint(1) null,
  data_de_criacao timestamp null default current_timestamp,
  data_de_modificacao timestamp null default current_timestamp on update current_timestamp,
  criado_por varchar(100) null,
  modificado_por varchar(100) null,
  primary key (id),
  foreign key (produto_id) references produtos(id),
  foreign key (pedido_id) references pedidos(id)
);

CREATE INDEX idx_produtos_pedidos_pedido_id ON produtos_pedidos(pedido_id);
CREATE INDEX idx_produtos_pedidos_produto_id ON produtos_pedidos(produto_id);
