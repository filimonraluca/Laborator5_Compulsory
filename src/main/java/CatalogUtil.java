import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class CatalogUtil {
    public static void save(Catalog catalog)
            throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(catalog.getPath()))) {
            oos.writeObject(catalog);
        }
    }

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

    public static void view(Document doc) {
        Desktop desktop = Desktop.getDesktop();
        //… browse or open, depending of the location type
        try {
            URI url = new URI(doc.getLocation());
            desktop.browse(url);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

}