package biblio;
import javax.ejb.Remote;
import java.util.List;
@Remote
public interface BiblioBeanRemote {
    public List<String> liste();
    public void ajouter(String author , String title); public Book getLivre(String title);
}