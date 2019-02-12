package beginner;

import com.sandwich.koan.Koan;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

public class AboutObjects {

    @Koan
    public void newObjectInstancesCanBeCreatedDirectly() {
        assertEquals(new Object() instanceof Object, true);
        //Everything extends Object, so you will always get true here (unless obj is null).
    }

    @Koan
    public void allClassesInheritFromObject() {
        class Foo {
        }

        Class<?>[] ancestors = getAncestors(new Foo());
        assertEquals(ancestors[0], Foo.class);
        // Don't need to give the beginner.AboutObjects$1Foo because we're already
        // in the AboutObjects
        assertEquals(ancestors[1], Object.class);

        // $1 -> a .class file that holds anonymous inner classed
    }

    @Koan
    public void objectToString() {
        Object object = new Object();
        // TODO: Why is it best practice to ALWAYS override toString?
        String expectedToString = MessageFormat.format("{0}@{1}", Object.class.getName(), Integer.toHexString(object.hashCode()));
        assertEquals(expectedToString, object.toString()); // hint: object.toString()

        // Integer.toHexString(object.hashCode()) returns to do with memory
    }

    @Koan
    public void toStringConcatenates() {
        final String string = "ha";
        Object object = new Object() {
            @Override
            public String toString() {
                return string;
            }
        };
        assertEquals(string + object, "haha");
    }

    @Koan
    public void toStringIsTestedForNullWhenInvokedImplicitly() {
        String string = "string";
        assertEquals(string + null, "stringnull");

        // Not surewhy -> look that up
    }

    private Class<?>[] getAncestors(Object object) {
        List<Class<?>> ancestors = new ArrayList<Class<?>>();
        Class<?> clazz = object.getClass();
        while (clazz != null) {
            ancestors.add(clazz);
            clazz = clazz.getSuperclass();
        }
        return ancestors.toArray(new Class[]{});
    }

}
