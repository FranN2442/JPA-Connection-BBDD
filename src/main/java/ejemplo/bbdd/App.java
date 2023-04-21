package ejemplo.bbdd;

import java.util.Arrays;

import java.util.List;

public class App 
{
    private static JpaService jpaService = JpaService.getInstance();

    public static void main( String[] args )
    {
        try{

            createTipos();
            printTopTipos();



        } finally {

            jpaService.shutDown();

        }

    }

    private static void createTipos() {
        jpaService.runInTransaction(entityManager -> {
            Arrays.stream("Java,C++,C#,JavaScript,Rust,Go,Python,PHP".split(","))
                    .map(name -> new Tipos(name, (int) (Math.random() * 10)))
                    .forEach(entityManager::persist);
            return null;
        });
    }

    private static void printTopTipos() {
        List<Tipos> tipos = jpaService.runInTransaction(entityManager ->
                entityManager.createQuery(
                        "select t from  Tipos t where t.numero > 5",
                        Tipos.class
                ).getResultList());
 
        tipos.stream()
                .map(t -> t.getNombre() + ": " + t.getNumero())
                .forEach(System.out::println);
    }
}
