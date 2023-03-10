import entity.GeographicareaEntity;
import entity.TotalincomeEntity;
import jakarta.persistence.*;

import java.util.List;


public class Main {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");

    public static void main(String[] args) {

        getGeoById(10);
        getGeoByLevel(2);
        //getAllTotalIncome();

    }

    // Task2
    public static void getGeoById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String query = "SELECT g FROM GeographicareaEntity g WHERE g.id = 10";
        TypedQuery<GeographicareaEntity> entityTypedQuery = entityManager.createQuery(query,GeographicareaEntity.class);
        try{
            List<GeographicareaEntity> geographicareaEntityList = entityTypedQuery.getResultList();
            GeographicareaEntity geographicareaEntity = geographicareaEntityList.get(0);
            System.out.println("Task 2 result:");
            System.out.println(geographicareaEntity.displayInfo());
            System.out.println();
        } catch (Exception e)
        {
            System.out.println("Error" + e.getMessage());
        } finally {
            entityManager.close();
        }
    }
    // Task3
    public static void getGeoByLevel(int level){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String query = "SELECT g FROM GeographicareaEntity g WHERE g.level = 2";

        TypedQuery<GeographicareaEntity> entityTypedQuery = entityManager.createQuery(query,GeographicareaEntity.class);
        try{
            List<GeographicareaEntity> geographicareaEntityList = entityTypedQuery.getResultList();
            System.out.println("Task3 result:");
            for (GeographicareaEntity geographicareaEntity : geographicareaEntityList) {
                System.out.println(geographicareaEntity.displayInfo());
            }
            System.out.println();
        } catch (Exception e)
        {
            System.out.println("Error" + e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    // Task4
//    public static void getAllTotalIncome(){
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        List<TotalincomeEntity> totalincomeEntityList = entityManager.createNamedQuery("findallIncome").getResultList();
//        for (TotalincomeEntity e:totalincomeEntityList) {
//            System.out.println(e.displayInfo());
//        }
//    }


}
