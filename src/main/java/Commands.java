public abstract class Commands {
    public String[] args;

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public abstract void execute() throws IllegalNrOfArgsException;
}
