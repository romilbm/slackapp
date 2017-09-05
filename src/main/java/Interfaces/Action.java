package Interfaces;

public interface Action {
    void run();
    void setRequest(Request request);
    Response getResponse();
}
