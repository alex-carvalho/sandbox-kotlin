package br.ac;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Foo {

    public static void main(String[] args) {
        TestInteroperability.max(1,2);

        try {
            TestInteroperability.exceptionWithoutWithAnnotationForJavaCode();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
