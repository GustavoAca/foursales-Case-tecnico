create table usuarios (
  id binary(16) not null,
  email varchar(250) not null,
  senha varchar(250) not null,
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

create table pedidos (
  id binary(16) not null,
  produto_id binary(16) not null,
  usuario_id binary(16) not null,
  status varchar(30) not null,
  valor_total decimal(10, 2) not null,
  data_de_criacao timestamp null default current_timestamp,
  data_de_modificacao timestamp null default current_timestamp on update current_timestamp,
  criado_por varchar(100) null,
  modificado_por varchar(100) null,
  primary key (id),
  foreign key (produto_id) references produtos(id),
  foreign key (usuario_id) references usuarios(id)
);

create table pagamentos (
  pedido_id binary(16) not null,
  data_de_criacao timestamp null default current_timestamp,
  data_de_modificacao timestamp null default current_timestamp on update current_timestamp,
  criado_por varchar(100) null,
  modificado_por varchar(100) null,
  primary key (pedido_id),
  foreign key (pedido_id) references pedidos(id)
);