package AnnotationOperation;


import java.lang.reflect.Field;



public class StringDeserializer {

    public static void main(String[] args) throws IllegalAccessException {
        Person p = new Person(null, "SONI", "harsha15soni@gmail.com");

        analyzeObjectForStringAnalysis(p);
        System.out.println(p);
    }

    private static void analyzeObjectForStringAnalysis(Person p) throws IllegalAccessException {
        for(Field field: p.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if(field.isAnnotationPresent(StringAnalyzer.class)) {
                StringAnalyzer analyzer = field.getAnnotation(StringAnalyzer.class);
                StringAnalyzer.Analyze[] types = analyzer.value();
                for(StringAnalyzer.Analyze analyze: types) {
                    if(analyze.equals(StringAnalyzer.Analyze.CAPITAL_CASE)) {
                        String data = (String) field.get(p);
                        data = capitalize(data);
                        field.set(p, data);
                    }

                    if(analyze.equals(StringAnalyzer.Analyze.SMALL_CASE)) {
                        String data = (String) field.get(p);
                        data = decapitalize(data);
                        field.set(p, data);
                    }
                    if(analyze.equals(StringAnalyzer.Analyze.NULL)) {
                        Object data = field.get(p);
                        if(data==null) throw new RuntimeException("NON NULL field is null for class: "+ p.getClass() + " for field: "+ field.getName());
                    }

                }
            }
        }
    }

    private static String capitalize(String c) {
        if(c!=null && c.length()>0) {
            char[] chars = c.toCharArray();
            for(int i=0;i<chars.length;i++) {
                chars[i] = Character.toUpperCase(chars[i]);
            }
            return new String(chars);
        } else {
            return c;
        }
    }

    private static String decapitalize(String c) {
        if(c!=null && c.length()>0) {
            char[] chars = c.toCharArray();
            for(int i=0;i<chars.length;i++) {
                chars[i] = Character.toLowerCase(chars[i]);
            }
            return new String(chars);
        } else {
            return c;
        }
    }
}
