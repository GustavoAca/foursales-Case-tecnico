create table IF NOT EXISTS usuarios (
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
  primary key (id),
  unique key uq_usuario_email (email)
);

CREATE INDEX idx_usuarios_total_de_compras_realizadas ON usuarios(total_de_compras_realizadas);
create INDEX idx_usuarios_email on usuarios(email);

create table IF NOT EXISTS categorias (
  id binary(16) not null,
  descricao varchar(250) not null,
  nome varchar(30) not null,
  data_de_criacao timestamp null default current_timestamp,
  data_de_modificacao timestamp null default current_timestamp on update current_timestamp,
  criado_por varchar(100) null,
  modificado_por varchar(100) null,
  primary key (id)
);

create table IF NOT EXISTS subcategorias (
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

create INDEX idx_sucategorias_categoria_id on subcategorias(id);

create table IF NOT EXISTS produtos (
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

create INDEX idx_produtos_quantidade_em_estoque on produtos(quantidade_em_estoque);

create table IF NOT EXISTS pedidos (
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

create INDEX idx_pedidos_usuario_id on pedidos(usuario_id);
create INDEX idx_pedidos_usuario_id_valor_total on pedidos(usuario_id, valor_total);
create INDEX idx_pedidos_data_de_criacao_valor_total on pedidos(data_de_criacao, valor_total);

create table IF NOT EXISTS pagamentos (
  id binary(16) not null,
  pedido_id binary(16) not null,
  data_de_criacao timestamp null default current_timestamp,
  data_de_modificacao timestamp null default current_timestamp on update current_timestamp,
  criado_por varchar(100) null,
  modificado_por varchar(100) null,
  primary key (id),
  foreign key (pedido_id) references pedidos(id)
);

create INDEX idx_pagamentos_pedido_id on pagamentos(pedido_id);

create table IF NOT EXISTS produtos_pedidos (
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

create INDEX idx_produtos_pedidos_pedido_id on produtos_pedidos(pedido_id);
create INDEX idx_produtos_pedidos_produto_id on produtos_pedidos(produto_id);