package org.sahni;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class AuthSphereApplication {
    public static void main(String ... args) {
        Quarkus.run(args);
    }
}
