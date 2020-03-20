import java.io.IOException;

public class CommandLoad extends Commands{

     public CommandLoad() {
     }

     public CommandLoad(String[] argumente) {
          args = argumente;
     }

     public void execute() throws IllegalNrOfArgsException {
          if (args.length != 2) {
               throw new IllegalNrOfArgsException();
          }
          String path = args[1];
          Catalog catalog = null;
          try {
               catalog = CatalogUtil.load(path);
          } catch (InvalidCatalogException e) {
               e.printStackTrace();
          }
     }
}
