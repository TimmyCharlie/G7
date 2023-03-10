import entity.GeographicareaEntity;
import entity.TotalincomeEntity;
import jakarta.persistence.*;

import java.util.List;


public class Main {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");

    public static void main(String[] args) {

        displayGeoById(10);
        displayGeoByLevel(2);
        //displayAllTotalIncome();
        displayHouseholdEntity();

    }

    // Task2
    public static void displayGeoById(int id) {
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
    public static void displayGeoByLevel(int level){
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
//    public static void displayAllTotalIncome(){
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        List<TotalincomeEntity> totalincomeEntityList = entityManager.createNamedQuery("findallIncome").getResultList();
//        for (TotalincomeEntity e:totalincomeEntityList) {
//            System.out.println(e.displayInfo());
//        }
//    }

    // Task 5
    public static void displayHouseholdEntity(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // a: One couple census family without other persons in the household
        String aQuery = "SELECT COUNT(*) FROM HouseholdEntity h " +
                "JOIN CensusyearEntity c ON h.censusYear = c.censusYearId " +
                "JOIN HouseholdtypeEntity ct ON h.householdType = ct.id " +
                "Where c.censusYear=2016 AND ct.description='One couple census family without other persons in the household'";
        // b: 2 or more members in the household
        String bQuery = "SELECT COUNT(*) FROM HouseholdEntity h " +
                "JOIN CensusyearEntity c ON h.censusYear = c.censusYearId " +
                "JOIN HouseholdsizeEntity cs ON h.householdSize = cs.id " +
                "Where c.censusYear=2016 AND cs.description='2 or more persons'";
        // c: At least 1 earner in the household
        String cQuery = "SELECT COUNT(*) FROM HouseholdEntity h " +
                "JOIN CensusyearEntity c ON h.censusYear = c.censusYearId " +
                "JOIN HouseholdearnersEntity he ON h.householdEarners = he.id " +
                "Where c.censusYear=2016 AND he.description='1 earner or more'";
        // d: Total income between $80,000 and $89,999
        String dQuery = "SELECT COUNT(*) FROM HouseholdEntity h " +
                "JOIN CensusyearEntity c ON h.censusYear = c.censusYearId " +
                "JOIN TotalincomeEntity ti ON h.totalIncome = ti.id " +
                "Where c.censusYear=2016 AND ti.description='$80,000 to $89,999'";

        try{
            System.out.println("Task5-a: "+entityManager.createQuery(aQuery).getSingleResult().toString());
            System.out.println("Task5-b: "+entityManager.createQuery(bQuery).getSingleResult().toString());
            System.out.println("Task5-c: "+entityManager.createQuery(cQuery).getSingleResult().toString());
            System.out.println("Task5-d: "+entityManager.createQuery(dQuery).getSingleResult().toString());
        } catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
        } finally {
            entityManager.close();
        }
    }


}
