Projeto JPA e Hibernate
Bem-vindo ao repositório do Projeto JPA e Hibernate! Este projeto é dedicado ao estudo e aplicação da Java Persistence API (JPA) e sua implementação Hibernate. Aqui você encontrará exemplos práticos e conceitos fundamentais para trabalhar com mapeamento objeto-relacional (ORM) em Java.

Objetivos
Explorar a especificação JPA e a implementação Hibernate.
Compreender e aplicar as annotations de mapeamento de classes para entidades de banco de dados.
Utilizar a API de Criteria do JPA para consultas tipadas e dinamicamente construídas.
Realizar consultas usando JPQL (Java Persistence Query Language).


Funcionalidades
Mapeamento de Entidades
Utilizamos annotations para mapear classes Java para tabelas do banco de dados.

Exemplo:

@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "preco")
    private Double preco;
}

API de Criteria
A API de Criteria do JPA permite construir consultas de forma programática e tipada.

CriteriaBuilder builder = entityManager.getCriteriaBuilder();
CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
Root<Produto> root = query.from(Produto.class);
query.select(root).where(builder.equal(root.get("nome"), "Produto Exemplo"));

List<Produto> resultados = entityManager.createQuery(query).getResultList();
JPQL (Java Persistence Query Language)
JPQL é uma poderosa linguagem de consulta baseada em SQL, porém orientada a objetos.

Exemplo:

String jpql = "SELECT p FROM Produto p WHERE p.preco > :precoMinimo";
TypedQuery<Produto> query = entityManager.createQuery(jpql, Produto.class);
query.setParameter("precoMinimo", 100.0);
List<Produto> resultados = query.getResultList();

Pré-requisitos
Java 8 ou superior
Maven
Banco de Dados (Ex: MySQL, PostgreSQL)
Configuração
Clone o repositório:

git clone https://github.com/seuusuario/projeto-jpa-hibernate.git
Navegue até o diretório do projeto:

cd projeto-jpa-hibernate
Configure seu banco de dados no arquivo src/main/resources/META-INF/persistence.xml.

Compile e execute o projeto com Maven:

mvn clean install
mvn exec:java -Dexec.mainClass="com.seuusuario.App"
