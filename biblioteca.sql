-- Atidade 2 - Criação e insersão de dados
-- =========================================
-- Banco de dados: PostgreSQL
-- =========================================

-- 1. TABELA ITEM
CREATE TABLE item (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    codigo INTEGER NOT NULL UNIQUE,
    titulo VARCHAR(150) NOT NULL,
    tipo VARCHAR(20) NOT NULL
        CHECK (tipo IN ('livro', 'revista')),
    autor VARCHAR(100),
    edicao VARCHAR(50),
    disponivel BOOLEAN NOT NULL DEFAULT TRUE
);

-- 2. TABELA USUARIO
CREATE TABLE usuario (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    tipo VARCHAR(20) NOT NULL
        CHECK (tipo IN ('aluno', 'professor')),
    limite_itens INTEGER NOT NULL
        CHECK (limite_itens > 0)
);

-- 3. TABELA EMPRESTIMO
CREATE TABLE emprestimo (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    item_id INTEGER NOT NULL REFERENCES item(id),
    usuario_id INTEGER NOT NULL REFERENCES usuario(id),
    data_retirada DATE NOT NULL,
    data_devolucao_prevista DATE NOT NULL,
    data_devolucao DATE,
    valor_multa NUMERIC(10, 2) NOT NULL DEFAULT 0
        CHECK (valor_multa >= 0),

    CHECK (data_devolucao_prevista >= data_retirada),
    CHECK (
        data_devolucao IS NULL
        OR data_devolucao >= data_retirada
    )
);

-- =========================================
-- 4. INSERIR ITENS
-- =========================================

INSERT INTO item
    (codigo, titulo, tipo, autor, edicao, disponivel)
VALUES
    (101, 'Dom Casmurro', 'livro',
     'Machado de Assis', NULL, FALSE),

    (102, 'O Pequeno Principe', 'livro',
     'Antoine de Saint-Exupery', NULL, TRUE),

    (103, 'Revista de Tecnologia', 'revista',
     NULL, 'Edicao 25', TRUE),

    (104, 'Revista de Ciencias', 'revista',
     NULL, 'Edicao 12', TRUE);

-- =========================================
-- 5. INSERIR USUARIOS
-- =========================================

INSERT INTO usuario (nome, tipo, limite_itens)
VALUES
    ('Lucas', 'aluno', 3),
    ('Carlos', 'professor', 5);

-- =========================================
-- 6. INSERIR EMPRESTIMOS
-- =========================================

INSERT INTO emprestimo
    (item_id, usuario_id, data_retirada,
     data_devolucao_prevista, data_devolucao, valor_multa)
VALUES
    -- Emprestimo em aberto: ainda nao devolvido
    (1, 1, DATE '2026-10-10',
     DATE '2026-10-24', NULL, 0.00),

    -- Emprestimo ja devolvido
    (2, 2, DATE '2026-09-01',
     DATE '2026-09-15', DATE '2026-09-18', 1.50);

-- =========================================
-- 7. CONSULTAR OS DADOS
-- =========================================

SELECT * FROM item;

SELECT * FROM usuario;

SELECT * FROM emprestimo;
-- =========================================
-- Atividade 3 - Consultas
-- =========================================
-- 1. Listar todos os itens do acervo
SELECT
    i.codigo,
    i.titulo,
    i.tipo,
    i.disponivel
FROM item AS i
ORDER BY i.codigo;


-- 2. Listar os empréstimos que ainda estão em aberto
SELECT
    u.nome AS usuario,
    i.titulo AS item,
    e.data_retirada,
    e.data_devolucao_prevista
FROM emprestimo AS e
INNER JOIN usuario AS u
    ON e.usuario_id = u.id
INNER JOIN item AS i
    ON e.item_id = i.id
WHERE e.data_devolucao IS NULL
ORDER BY e.data_devolucao_prevista;


-- 3. Calcular o total de multas registradas por usuário
SELECT
    u.id,
    u.nome AS usuario,
    COALESCE(SUM(e.valor_multa), 0) AS total_multas
FROM usuario AS u
LEFT JOIN emprestimo AS e
    ON u.id = e.usuario_id
GROUP BY
    u.id,
    u.nome
ORDER BY total_multas DESC;


-- 4. Listar os itens que nunca foram emprestados
SELECT
    i.codigo,
    i.titulo,
    i.tipo
FROM item AS i
LEFT JOIN emprestimo AS e
    ON i.id = e.item_id
WHERE e.item_id IS NULL
ORDER BY i.codigo;