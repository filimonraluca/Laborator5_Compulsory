import java.awt.*;
import java.beans.XMLEncoder;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;

public class CatalogUtil {

   /* public static void saveSerialized(Catalog catalog)
            throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(catalog.getPath()))) {
            oos.writeObject(catalog);
        }
    }*/


    /**
     * Salveaza catalogul primit ca parametru
     * intr-un fisier XML
     *
     * @param catalog catalogul care trebuie salvat
     */

    public static void save(Catalog catalog) {
        FileOutputStream fo = null;
        try {
            fo = new FileOutputStream(new File(catalog.getPath()));
            XMLEncoder encoder = new XMLEncoder(fo);
            encoder.writeObject(catalog);
            encoder.close();
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*public static Catalog loadSerialized(String path)
            throws InvalidCatalogException {
        try (
                FileInputStream fileIn = new FileInputStream(path);
                ObjectInputStream in = new ObjectInputStream(fileIn)
        ) {
            return (Catalog) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new InvalidCatalogException(e);
        }
    }*/

    /**
     * Incarca catalogul dintr-un fisier XML
     * si il returneaza.
     *
     * @param path adresa de la care se citeste
     * @return catalogul citit
     */

    public static Catalog load(String path) throws InvalidCatalogException {
        try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(path))) {
            return (Catalog) decoder.readObject();
        } catch (IOException e) {
            throw new InvalidCatalogException(e);
        }
    }


    /**
     * Deschide documentul folosind metoda open sau browse
     * in functie de tipul locatiei.
     *
     * @param doc documentul ce trebuie deschis pentru vizualizare
     */

    public static void view(Document doc) {
        Desktop desktop = Desktop.getDesktop();
        if (doc.getLocation().startsWith("http")) {
            URI uri = null;
            try {
                uri = new URI(doc.getLocation());
                desktop.browse(uri);
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                File file = new File(doc.getLocation());
                desktop.open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}