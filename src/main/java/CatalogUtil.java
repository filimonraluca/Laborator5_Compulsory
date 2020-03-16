import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class CatalogUtil {

    /**
     * Salveaza catalogul primit ca parametru
     * intr-un fisier extern.
     *  @param  catalog catalogul care trebuie salvat
     */
    public static void save(Catalog catalog)
            throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(catalog.getPath()))) {
            oos.writeObject(catalog);
        }
    }


    /**
     * Incarca catalogul dintr-un fisier extern
     * si il returneaza.
     * @param  path  adresa de la care se citeste
     * @return catalogul citit
     */

    public static Catalog load(String path)
            throws InvalidCatalogException {
        try (
                FileInputStream fileIn = new FileInputStream(path);
                ObjectInputStream in = new ObjectInputStream(fileIn)
        ) {
            return (Catalog) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new InvalidCatalogException(e);
        }
    }

    /**
     * Deschide documentul folosind metoda open sau browse
     * in functie de tipul locatiei.
     * @param  doc documentul ce trebuie deschis pentru vizualizare
     */

    public static void view(Document doc) {
        Desktop desktop = Desktop.getDesktop();
        try {
            if (doc.getLocation().startsWith("http"))
            {
                URI uri = new URI(doc.getLocation());
                desktop.browse(uri);
            }
            else
            {
                File file = new File(doc.getLocation());
                desktop.open(file);
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

}