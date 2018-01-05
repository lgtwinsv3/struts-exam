package com.ej.example.action;

public class CustomActionForward {
    private boolean isRedirect = false;
    private String path = null;


    private Object model;

    public boolean isRedirect() {
        return isRedirect;
    }

    public void setRedirect(boolean redirect) {
        isRedirect = redirect;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }

//    public void setModel(Map<String, Object> ... model) { this.model = model;}
}
