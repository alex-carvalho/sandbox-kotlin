
package br.ac;

import br.ac.basic.TestInteroperability;

import java.io.IOException;

public class Interoperability {

    public static void main(String[] args) {
        TestInteroperability.max(1,2);

        try {
            TestInteroperability.exceptionWithoutWithAnnotationForJavaCode();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
