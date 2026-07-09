-- ==========================================
-- TABELA: ORGANIZADORA
-- ==========================================
CREATE TABLE organizadora (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    sigla VARCHAR(100),
    url VARCHAR(255),
    logo BYTEA
);

-- ==========================================
-- TABELA: ORGAO
-- ==========================================
CREATE TABLE orgao (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    sigla VARCHAR(255),
    url VARCHAR(255),
    esfera VARCHAR(15),
    estados VARCHAR(15),
    area_governo VARCHAR(15),
    logo BYTEA
);

-- ==========================================
-- TABELA: CRONOGRAMA
-- ==========================================
CREATE TABLE cronograma (
    id BIGSERIAL PRIMARY KEY
);

-- ==========================================
-- TABELA: CONCURSO
-- ==========================================
CREATE TABLE concurso (
    id BIGSERIAL PRIMARY KEY,
    ano INTEGER,
    organizadora_id BIGINT NOT NULL,
    orgao_id BIGINT NOT NULL,
    cronograma_id BIGINT
);

ALTER TABLE concurso
    ADD CONSTRAINT fk_concurso_organizadora
        FOREIGN KEY (organizadora_id) REFERENCES organizadora (id);

ALTER TABLE concurso
    ADD CONSTRAINT fk_concurso_orgao
        FOREIGN KEY (orgao_id) REFERENCES orgao (id);

ALTER TABLE concurso
    ADD CONSTRAINT fk_concurso_cronograma
        FOREIGN KEY (cronograma_id) REFERENCES cronograma (id);

-- ==========================================
-- TABELA: CARGO
-- ==========================================
CREATE TABLE cargo (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255),
    qtd_vagas INTEGER,
    qtd_inscritos INTEGER,
    salario NUMERIC(15,2),
    escolaridade VARCHAR(20),
    orgao_id BIGINT NOT NULL,
    concurso_id BIGINT NOT NULL
);

ALTER TABLE cargo
    ADD CONSTRAINT fk_cargo_orgao
        FOREIGN KEY (orgao_id) REFERENCES orgao (id);

ALTER TABLE cargo
    ADD CONSTRAINT fk_cargo_concurso
        FOREIGN KEY (concurso_id) REFERENCES concurso (id);

-- ==========================================
-- TABELA: PROVA
-- ==========================================
CREATE TABLE prova (
    id BIGSERIAL PRIMARY KEY,
    descricao VARCHAR(255),
    pontuacao_minima INTEGER,
    pontuacao_maxima INTEGER,
    tipo_prova VARCHAR(20),
    cargo_id BIGINT NOT NULL
);

ALTER TABLE prova
    ADD CONSTRAINT fk_prova_cargo
        FOREIGN KEY (cargo_id) REFERENCES cargo (id);

-- ==========================================
-- TABELA: GRUPO_DISCIPLINA
-- ==========================================
CREATE TABLE grupo_disciplina (
    id BIGSERIAL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    pontuacao_minima INTEGER,
    pontuacao_maxima INTEGER,
    reprovar_por_pontuacao_minima BOOLEAN NOT NULL,
    prova_id BIGINT NOT NULL
);

ALTER TABLE grupo_disciplina
    ADD CONSTRAINT fk_grupo_disciplina_prova
        FOREIGN KEY (prova_id) REFERENCES prova (id);

-- ==========================================
-- TABELA: DISCIPLINA
-- ==========================================
CREATE TABLE disciplina (
    id BIGSERIAL PRIMARY KEY,
    descricao VARCHAR(255),
    pontuacao_minima INTEGER NOT NULL,
    pontuacao_maxima INTEGER NOT NULL,
    reprovar_por_pontuacao_minima BOOLEAN NOT NULL,
    prova_id BIGINT NOT NULL,
    grupo_disciplina_id BIGINT NOT NULL
);

ALTER TABLE disciplina
    ADD CONSTRAINT fk_disciplina_prova
        FOREIGN KEY (prova_id) REFERENCES prova (id);

ALTER TABLE disciplina
    ADD CONSTRAINT fk_disciplina_grupo_disciplina
        FOREIGN KEY (grupo_disciplina_id) REFERENCES grupo_disciplina (id);

-- ==========================================
-- TABELA: ETAPA
-- ==========================================
CREATE TABLE etapa (
    id BIGSERIAL PRIMARY KEY,
    descricao VARCHAR(255),
    data DATE,
    cronograma_id BIGINT NOT NULL
);

ALTER TABLE etapa
    ADD CONSTRAINT fk_etapa_cronograma
        FOREIGN KEY (cronograma_id) REFERENCES cronograma (id);

-- ==========================================
-- TABELA: COTA
-- ==========================================
CREATE TABLE cota (
    id BIGSERIAL PRIMARY KEY,
    tipo_cota VARCHAR(20),
    qtd_vagas_totais INTEGER,
    qtd_vagas_cotas INTEGER,
    porcentagem_legal INTEGER,
    cargo_id BIGINT NOT NULL
);

ALTER TABLE cota
    ADD CONSTRAINT fk_cota_cargo
        FOREIGN KEY (cargo_id) REFERENCES cargo (id);