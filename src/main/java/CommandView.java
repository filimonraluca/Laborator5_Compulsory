import javax.print.Doc;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class CommandView extends Commands{

    public CommandView() {
    }

    public CommandView(String[] argumente) {
        args = argumente;
    }


    private void view(Document doc) {
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
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }


    /**
     * Executa comanda View folosindu-se de clasa
     * CatalogUtil pentru incarcarea catalogului
     * de la path-ul primit ca argument
     */
    public void execute  () throws IllegalNrOfArgsException {
        if (args.length != 3) {
            throw new IllegalNrOfArgsException();
        }
        String path = args[1];
        String id = args[2];
        Catalog catalog = null;
        if (path.endsWith(".xml")) {
            try {
                catalog = CatalogUtil.load(path);
            } catch (InvalidCatalogException e) {
                e.printStackTrace();
            }
            Document doc = catalog.findById(id);
            view(doc);
        }
    }
}
