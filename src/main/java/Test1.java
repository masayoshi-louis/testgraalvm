import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;

public class Test1 {

    public static class MyCls1 {
        private String str;

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }
    }

    public interface Js1 {
        void changeStr(MyCls1 obj);

        void printFirstChar(MyCls1 obj);

        void changeStrViaPy(Object py, IJsObj obj);

        void addFn(Object obj);

        void addMember(Object obj);

        void callFn(Object pyObj, Consumer<Object> fn);
    }

    public interface IJsObj {
        String str();
    }

    public interface Py1 {
        void showStr1(MyCls1 obj);

        void showStr2(IJsObj obj);

        void changeStr2(IJsObj obj);

        void setStr(IJsObj obj, String v);

        void printFirstChar(IJsObj obj);

        void show(Object obj);

        void showYourself();

        void addMember();

        void applyFn(Function<Integer, Integer> fn);
    }

    public static void main(String[] args) throws IOException {
        Context polyglot = Context.newBuilder().build();
        Value js1Evaled = polyglot.eval(Source.newBuilder("js", new File("js1.js")).build());
        Js1 js1 = js1Evaled.as(Js1.class);

        MyCls1 obj1 = new MyCls1();
        obj1.setStr("efg");
        js1.printFirstChar(obj1);
        js1.changeStr(obj1);
        System.out.println(obj1.str);

        Value jsObj2 = polyglot.eval("js", "({str:\"a\"})");
        System.out.println(jsObj2.getMemberKeys());
        IJsObj obj2 = jsObj2.as(IJsObj.class);
        System.out.println(obj2.str());

        Value pyEvaled = polyglot.eval(Source.newBuilder("python", new File("py1.py")).build());
        Py1 py1 = pyEvaled.as(Py1.class);
        py1.showStr1(obj1);
        py1.showStr2(obj2);
        py1.changeStr2(obj2);
        py1.showStr2(obj2);
        py1.setStr(obj2, "中文");
        py1.showStr2(obj2);
        System.out.println(obj2.str());
        py1.printFirstChar(obj2);

        js1.changeStrViaPy(py1, obj2);
        System.out.println(obj2.str());

        js1.addFn(py1);
        py1.show(polyglot);

        js1.addMember(py1);
        System.out.println(pyEvaled.getMember("k1").asString());

        py1.addMember();
        System.out.println(pyEvaled.getMemberKeys());
        py1.showYourself();

        js1.callFn(py1, x -> System.out.println("callback: " + x));

        py1.applyFn(x -> x * 10);
    }

}
