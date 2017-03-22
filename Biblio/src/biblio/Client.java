package biblio;

import javax.naming.Context;

public class Client {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Context ctx = new javax.naming.InitialContext();
        BiblioBeanRemote bb = (BiblioBeanRemote) ctx.lookup(
                BiblioBeanRemote.class.getName());
        bb.ajouter("Balzac", "Eugenie Grandet");
        bb.ajouter("Hugo", "Les Miserables");
        java.util.List<String> lt = bb.liste();
        for (String t : lt) System.out.println(t);
    }
}