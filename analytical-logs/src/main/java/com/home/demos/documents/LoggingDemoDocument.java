package main.java.com.home.demos.documents;

import org.springframework.stereotype.Component;

@Component
public class LoggingDemoDocument {
    public void save() {}
    public void saveWithoutActions(){}
    public void cancelActions(){}
    public void markAsRemoved(){}

    @Override
    public String toString() {
        return " Demo Document #MOCK Number";
    }
}
