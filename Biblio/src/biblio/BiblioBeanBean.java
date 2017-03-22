package biblio;

import javax.persistence.*;
import javax.ejb.Stateless;
import java.util.List;
import javax.persistence.Query;

@Stateless
public class BiblioBeanBean implements BiblioBeanRemote {
    @PersistenceContext(unitName = "Biblio-ejbPU")
    EntityManager persistance;

    public List<String> liste() {
        Query q = persistance.createNamedQuery("allbooks");
        try {
            return (List<String>) q.getResultList();
        } catch (ClassCastException e) {
            return null;
        }
    }

    public void ajouter(String author, String title) {
        Book b = new Book();
        b.setTitle(title);
        b.setAuthor(author);
        persistance.persist(b);
    }

    public Book getLivre(String title) {
        String texteRequete = "SELECT b FROM Book AS b WHERE b.title=" +
                title;
        Query requete = persistance.createQuery(texteRequete);
        Book resultat = null;
        try {
            resultat = (Book) requete.getSingleResult();
        } catch (NonUniqueResultException | EntityNotFoundException e) {
        }
        return resultat;
    }
}