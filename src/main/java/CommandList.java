import java.io.IOException;

public class CommandList extends Commands {

    public CommandList() {
    }


    public CommandList(String[] argumente) {
        args = argumente;
    }


    /**
     * Executa comanda List folosindu-se de clasa
     * CatalogUtil pentru incarcarea catalogului
     * de la path-ul primit ca argument. Afiseaza
     * documentele din catalog.
     */

    @Override
    public void execute() throws IllegalNrOfArgsException {
        if (args.length != 2) {
            throw new IllegalNrOfArgsException();
        }
        String path = args[1];
        Catalog catalog = null;
        try {
            catalog = CatalogUtil.load(path);
            System.out.println(catalog.getDocuments());
        } catch (InvalidCatalogException e) {
            e.printStackTrace();
        }
    }
}
