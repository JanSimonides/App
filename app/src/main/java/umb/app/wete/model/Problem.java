package umb.app.wete.model;

public class Problem {

    private int id;
    private String question;
    private String solution;
    private boolean resolved;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Problem(){}

    public Problem(int id, String question, String solution, boolean resolved, String name) {
        this.id = id;
        this.question = question;
        this.solution = solution;
        this.resolved = resolved;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }
}
