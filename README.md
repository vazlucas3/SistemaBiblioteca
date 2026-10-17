
# Sistema de Biblioteca em Java

## Sobre o projeto

Sistema desenvolvido em Java para gerenciar o acervo de uma biblioteca,
controlando livros, revistas, usuários, empréstimos e devoluções.

## Funcionalidades

- Cadastro de livros e revistas.
- Controle de disponibilidade dos itens.
- Empréstimo e devolução de itens.
- Limite de empréstimos por usuário.
- Listagem do acervo.
- Cálculo de multas por atraso.
- Controle do usuário responsável por cada empréstimo.
- Arrays de tamanho fixo.

## Regras de negócio

### Itens

| Tipo | Prazo | Multa por dia |
|---|---:|---:|
| Livro | 14 dias | R$ 0,50 |
| Revista | 7 dias | R$ 1,00 |

### Usuários

| Tipo | Limite de empréstimos |
|---|---:|
| Aluno | 3 itens |
| Professor | 5 itens |

## Conceitos de orientação a objetos

- Classes abstratas
- Herança
- Polimorfismo
- Encapsulamento
- Sobrescrita de métodos

## Tecnologias

- Java
- IntelliJ IDEA
- Git
- GitHub

## Como executar

1. Abra o projeto no IntelliJ IDEA.
2. Certifique-se de ter um JDK instalado.
3. Abra `src/biblioteca/Main.java`.
4. Execute o método `main`.

## Estrutura do projeto

```text
SistemaBiblioteca/
├── src/
│   └── biblioteca/
│       ├── ItemBiblioteca.java
│       ├── Livro.java
│       ├── Revista.java
│       ├── Usuario.java
│       ├── Aluno.java
│       ├── Professor.java
│       ├── Biblioteca.java
│       └── Main.java
├── .gitignore
└── README.md
```

## Autor

Projeto acadêmico desenvolvido para praticar programação orientada a objetos em Java.
