package testBase;

import org.apache.maven.shared.invoker.*;
import java.io.File;
import java.util.Collections;
import java.lang.*;

public class MyRunner {
    public static void main(String[] args) {
        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(new File("pom.xml"));
        request.setGoals(Collections.singletonList("install"));
        Invoker invoker = new DefaultInvoker();
        invoker.setMavenHome(new File(System.getenv("MAVEN_HOME")));
        try {
            invoker.execute(request);
        } catch (MavenInvocationException e) {
            e.printStackTrace();
        }
        System.out.println("Hellooooooooooo");
    }
}
