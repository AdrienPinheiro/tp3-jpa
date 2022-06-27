package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

public class ConnexionJpaTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("livre-emprunt-tp");
        EntityManager em = emf.createEntityManager();

        Emprunt emprunt = em.find(Emprunt.class, 1);
        Set<Livre> livres = emprunt.getLivres();
        livres.forEach(System.out::println);


        TypedQuery<Emprunt> request = em.createQuery("SELECT e FROM Emprunt e", Emprunt.class);
        List<Emprunt> emprunts = request.getResultList();
        System.out.println("Nombre d'emprunt " + emprunts.size());
        emprunts.forEach(System.out::println);

        Client client = em.find(Client.class, 1);
        TypedQuery<Emprunt> empruntRequet = em.createQuery("SELECT e FROM Emprunt e WHERE e.client.id=:id", Emprunt.class).setParameter("id", client.getId());
        List<Emprunt> empruntsClient = empruntRequet.getResultList();
        System.out.println("Nombre d'emprunt pour le client " + client.getPrenom() + " " + client.getNom() + " : " + empruntsClient.size());
        empruntsClient.forEach(System.out::println);
    }
}
