public class CommandReport extends Commands {

    /**
     * Executa comanda Report folosindu-se de clasa
     * CatalogUtil pentru incarcarea catalogului
     * de la path-ul primit ca argument, dupa care apeleaza
     * metoda reportHtml pe catalogul citit pentru a crea un
     * fisier html cu datele din interiorul catalogului.
     */
    public void execute() throws IllegalNrOfArgsException
    {
        if (args.length != 2) {
            throw new IllegalNrOfArgsException();
        }
        else
        {
            String path = args[1];
            Catalog catalog = null;
            try {
                catalog = CatalogUtil.load(path);
                catalog.reportHtml();
            } catch (InvalidCatalogException e) {
                e.printStackTrace();
            }
        }
    }
}
