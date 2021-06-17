package laba;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args){
        Schedule pairMath = new Schedule("Math", 315, 8, 0, 0);
        pairMath.timeToPair(9,15, 50);

        Class reflected = Schedule.class;

        // Name of package and class
        System.out.println("Package name : " + reflected.getPackageName() + "\nReflected class name: " + reflected.getSimpleName());

        //Initialize constructor
        Constructor constructor = null;
        try {
            constructor = reflected.getConstructor(String.class, int.class, int.class, int.class, int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        //Initialize object
        Schedule object = null;
        try {
            object = (Schedule) constructor.newInstance("English", 35, 10, 0, 0);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //Methods with annotations and type of parameters
        Method[] methods = reflected.getDeclaredMethods();
        System.out.println("\nClass methods:");
        for(Method method: methods){
            System.out.print("Method " + method.getName());
            Class[] parameterType = method.getParameterTypes();
            System.out.print(" Parameters: ");
            for(Class param : parameterType){
                System.out.print(param.getName()+ " ");
            }
            System.out.println("");
            Annotation annotation = method.getAnnotation(Anotation.class);
            if (annotation != null && annotation.annotationType() == Anotation.class) {
                System.out.println("Annotation : " + annotation);
                Class<?>[] pType  = method.getParameterTypes();
                Object[] params = new Object[pType.length];
                for (int i = 0; i < pType.length; i++) {
                    if(pType[i].toString().equals("int")){
                        params[i] = 0;
                    }
                }
                try {
                    method.invoke(object, params);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}


