package app.model;

public enum TaskStatus {


    FINISH("FINISH"), MAKING("MAKING"), REQUEST("REQUEST"), RESPONSE("RESPONSE"), CANCEL("CANCEL");

    private String name;

    TaskStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
