import java.io.IOException;

public class CommandList extends Commands {

    public CommandList() {
    }


    public CommandList(String[] argumente) {
        args = argumente;
    }


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
