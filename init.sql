-- Criação da tabela de clientes correspondente à Entidade Java
CREATE TABLE IF NOT EXISTS tb_customer (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    document VARCHAR(20) NOT NULL UNIQUE
);

-- Inicialização com dados fictícios para carga inicial
INSERT INTO tb_customer (name, email, document) VALUES 
('Cristian Clever', 'cristian@email.com', '12345678901')
ON CONFLICT DO NOTHING;

INSERT INTO tb_customer (name, email, document) VALUES 
('Camila Abreu', 'camila@email.com', '98765432100')
ON CONFLICT DO NOTHING;